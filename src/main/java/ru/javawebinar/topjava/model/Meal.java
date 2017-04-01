package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.dao.MealDaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
public class Meal {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        id = MealDaoImpl.useCounter();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Meal(String description, int calories) {
        id = MealDaoImpl.useCounter();
        dateTime = LocalDateTime.now();
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public Integer getId() {
        return id;
    }
}
