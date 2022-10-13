package ru.javawebinar.topjava.DAO;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDAOImp implements MealDAO {
    private static int staticId = 1;

    private List<Meal> meals = Collections.synchronizedList(new ArrayList<>());

    private static final Logger log = getLogger(MealDAOImp.class);

    {
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(staticId++, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    private static final int caloriesPerDay = 2000;

    @Override
    public List<MealTo> getAllMeal() {
        log.debug("DAO getAllMeal");
        List<MealTo> mealTos = new ArrayList<>();
        for (Meal meal : meals) {
            mealTos.add(new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), true));
        }

        return mealTos;
    }

    @Override
    public void createMeal(Meal meal) {
        log.debug("DAO createMeal");
        meal.setId(staticId++);
        meals.add(meal);
    }

    @Override
    public void deleteMeal(int id) {
        log.debug("DAO deleteMeal");
        meals.remove(id);
    }

    @Override
    public Meal getMeal(int id) {
        log.debug("DAO getMeal");
        return meals.get(id);
    }

    @Override
    public void updateMeal(Meal meal) {
        log.debug("DAO updateMeal");
        meals.get(meal.getId()).setCalories(meal.getCalories());
        meals.get(meal.getId()).setDateTime(meal.getDateTime());
        meals.get(meal.getId()).setDescription(meal.getDescription());
    }
}
