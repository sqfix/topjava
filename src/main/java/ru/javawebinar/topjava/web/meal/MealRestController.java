package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

/**
 * Created by Max on 02.04.2017.
 */
@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAll(){
        LOG.info("getAll");
        return service.getAll();
    }

    public Meal get(int id){
        LOG.info("get meal " + id);
        return service.get(id);
    }

    public Meal create(Meal meal){
        LOG.info("create "+ meal);
        return service.save(meal);
    }

    public void delete(int id){
        LOG.info("delete meal "+ id);
        service.delete(id);
    }

    public void update(Meal meal, int id){
        LOG.info("update meal " +meal);
        service.update(meal);
    }
}
