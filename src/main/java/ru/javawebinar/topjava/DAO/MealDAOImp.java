package ru.javawebinar.topjava.DAO;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDAOImp implements MealDAO {
    private Map<Integer, Meal> mealMap = new ConcurrentHashMap<>();

    private static final Logger log = getLogger(MealDAOImp.class);

    private AtomicInteger atomicInt = new AtomicInteger(0);


    {
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        createMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static final int caloriesPerDay = 2000;

    @Override
    public Collection<Meal> getAllMeal() {
        log.debug("DAO getAllMeal");
        mealMap.values().forEach(System.out::println);
        return mealMap.values();
    }

    @Override
    public void createMeal(Meal meal) {
        log.debug("DAO createMeal");
        if (meal.getId() == null) {
            meal.setId(atomicInt.getAndIncrement());
        }
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int id) {
        log.debug("DAO deleteMeal");
        mealMap.remove(id);
    }

    @Override
    public Meal getMeal(int id) {
        log.debug("DAO getMeal");
        return mealMap.get(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        log.debug("DAO updateMeal");
        mealMap.get(meal.getId()).setCalories(meal.getCalories());
        mealMap.get(meal.getId()).setDateTime(meal.getDateTime());
        mealMap.get(meal.getId()).setDescription(meal.getDescription());
    }
}
