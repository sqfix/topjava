package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    private final Integer id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, int id) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Date " + dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", Description " + description +
                ", Calories " + calories +
                ", Exceed " + exceed +
                ", ID " + id;
    }

    public boolean isExceed() {
        return exceed;
    }
}
