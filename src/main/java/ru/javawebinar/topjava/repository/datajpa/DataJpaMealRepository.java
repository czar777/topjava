package ru.javawebinar.topjava.repository.datajpa;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        System.out.println("datajpa");
        User user = crudUserRepository.findById(userId).orElse(null);
        meal.setUser(user);

        if (meal.isNew()) {
            crudRepository.save(meal);
            return meal;
        }

        if (crudRepository.findByIdAndUserId(meal.id(), userId) == null) {
            return null;
        }

        return crudRepository.save(meal);

    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.deleteByIdAndUserId(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findByIdAndUserId(id, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Meal getMealWithUser(int id, int userId) {
        Meal meal = crudRepository.findByIdAndUserId(id, userId);
        Hibernate.initialize(meal.getUser());
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAllByUserId(Sort.by(Sort.Direction.DESC, "dateTime"), userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.findAllByDate(startDateTime, endDateTime, userId);
    }
}
