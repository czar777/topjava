package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MealTestData {

    public static final List<Meal> allMealsUser = Arrays.asList(
            new Meal(100006, LocalDateTime.of(2020, 1, 31, 20, 00, 00), "Ужин", 410),
            new Meal(100005, LocalDateTime.of(2020, 1, 31, 13, 00, 00), "Обед", 500),
            new Meal(100004, LocalDateTime.of(2020, 1, 31, 10, 00, 00), "Завтрак", 1000),
            new Meal(100003, LocalDateTime.of(2020, 1, 31, 00, 00, 00), "Еда на граничное значение", 100),
            new Meal(100002, LocalDateTime.of(2020, 1, 30, 20, 00, 00), "Ужин", 500),
            new Meal(100001, LocalDateTime.of(2020, 1, 30, 13, 00, 00), "Обед", 1000),
            new Meal(100000, LocalDateTime.of(2020, 1, 30, 10, 00, 00), "Завтрак", 500)
            );

    public static final List<Meal> allMealsAdmin = Arrays.asList(
            new Meal(100013, LocalDateTime.of(2020, 1, 28, 20, 00, 00), "Обед", 410),
            new Meal(100012, LocalDateTime.of(2020, 1, 28, 13, 00, 00), "Обед", 500),
            new Meal(100011, LocalDateTime.of(2020, 1, 28, 10, 00, 00), "Завтрак", 1000),
            new Meal(100010, LocalDateTime.of(2020, 1, 28, 00, 00, 00), "Еда на граничное значение", 100),
            new Meal(100009, LocalDateTime.of(2020, 1, 27, 20, 00, 00), "Ужин", 500),
            new Meal(100008, LocalDateTime.of(2020, 1, 27, 13, 00, 00), "Обед", 1000),
            new Meal(100007, LocalDateTime.of(2020, 1, 27, 10, 00, 00), "Завтрак", 500)
    );
}

