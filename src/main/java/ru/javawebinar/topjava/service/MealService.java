package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private MealRepository repository;

    @Autowired
    public MealService(MealRepository mealRepository) {
        this.repository = mealRepository;
    }

    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Meal get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Meal> getAll(int userId) {
        List<Meal> allMeal = repository.getAll(userId);
        if (allMeal == null) {
            throw new NotFoundException("Еда отсутствует");
        }
        return allMeal;
    }

    public void update(Meal meal) {
        checkNotFoundWithId(repository.save(meal), meal.getId());
    }

    public void filtration(LocalDate fromDate, LocalDate beforeDate, LocalTime fromTime, LocalTime beforeTime) {

        repository.filtration(fromDate, beforeDate, fromTime, beforeTime);
    }

}