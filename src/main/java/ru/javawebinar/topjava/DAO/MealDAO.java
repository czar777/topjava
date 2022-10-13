package ru.javawebinar.topjava.DAO;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealDAO {
    public List<MealTo> getAllMeal();

    public void createMeal(Meal meal);

    public void deleteMeal(int id);

    public void updateMeal(Meal meal);

    public Meal getMeal(int id);
}
