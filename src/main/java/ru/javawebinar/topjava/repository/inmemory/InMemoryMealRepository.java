package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private LocalDate fromDate = LocalDate.MIN;
    private LocalDate beforeDate = LocalDate.MAX;
    private LocalTime fromTime = LocalTime.MIN;
    private LocalTime beforeTime = LocalTime.MAX;

    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            log.info("save {}", meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {} size repository = {}", id, repository.size());
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll {}", userId);
        List<Meal> allMeal = repository.values().stream()
                .filter(x -> x.getUserId() == userId)
                .filter(x -> DateTimeUtil.isBetweenHalfOpenDate(x.getDate(), fromDate, beforeDate))
                .filter(x -> DateTimeUtil.isBetweenHalfOpen(x.getTime(), fromTime, beforeTime))
                .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .collect(Collectors.toList());
        return allMeal;
    }

    @Override
    public void filtration(LocalDate fromDate, LocalDate beforeDate, LocalTime fromTime, LocalTime beforeTime) {
        log.info("filtration repository");
        this.fromDate = fromDate;
        this.beforeDate = beforeDate;
        this.fromTime = fromTime;
        this.beforeTime = beforeTime;
    }
}

