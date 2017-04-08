package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    private final static String SQL_SAVE_MEAL = "INSERT INTO meals (meal_id, user_id, description, calories, time) VALUES (?,?,?,?)";
    private final static String SQL_GET_MEAL = "SELECT meal_id, user_id, description, calories, time FROM meals WHERE meal_id = ?";
    private final static String SQL_REMOVE_MEAL_BY_ID = "DELETE FROM meals WHERE meal_id = ?";
    private final static String SQL_GET_ALL_MEALS = "SELECT meal_id, user_id, description, calories, time FROM meals";

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertMeal;


    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("meal_id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        jdbcTemplate.update(SQL_SAVE_MEAL,
                meal.getId(), userId, meal.getDescription(), meal.getCalories(), meal.getTime());
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update(SQL_REMOVE_MEAL_BY_ID, id) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return jdbcTemplate.queryForObject(SQL_GET_MEAL, ROW_MAPPER, id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        //for some reason, this place throws NPE
        List<Meal> meals = jdbcTemplate.query(SQL_GET_ALL_MEALS, (resultSet, i) -> new Meal() {{
            setId(resultSet.getInt(1));
            setDescription(resultSet.getString("description"));
            setCalories(resultSet.getInt("calories"));
        }});
        if (meals.isEmpty() || meals == null)
            return Collections.emptyList();
        else
            return sortedMealList(meals);
    }

    private List<Meal> sortedMealList(List<Meal> meals) {
        meals.sort(Comparator.comparing(Meal::getDateTime));
        return meals;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return new ArrayList<Meal>() {{
            for (Meal meal : getAll(1))
                if (DateTimeUtil.isBetween(meal.getDateTime(), startDate, endDate))
                    add(meal);
        }};
    }
}
