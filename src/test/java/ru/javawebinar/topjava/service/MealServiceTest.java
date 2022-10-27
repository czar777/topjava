package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.Assert.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal mealActual = mealService.get(100_000, 100_000);
        Meal mealExpected = MealTestData.allMealsUser.get(6);
        assertEquals(mealExpected, mealActual);
    }

    @Test
    public void delete() {
        mealService.delete(100_000, 100_000);
        try {
            Meal meal = mealService.get(100_000, 100_000);
            fail();
        } catch (NotFoundException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void deleteAlienMeal() {
        mealService.delete(100_000, 100_000);
        try {
            Meal meal = mealService.get(100_000, 100_000);
            fail();
        } catch (NotFoundException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = mealService.getBetweenInclusive(LocalDate.of(2020, 1, 30), LocalDate.of(2020, 1, 31), 100_000);
        List<Meal> expected = MealTestData.allMealsUser;
        assertEquals(expected, actual);
    }

    @Test
    public void getAll() {
        List<Meal> expected = MealTestData.allMealsUser;
        List<Meal> actual = mealService.getAll(100_000);
        assertEquals(expected, actual);
    }

    @Test
    public void update() {
        Meal expected = mealService.get(100_000, 100_000);
        expected.setCalories(333);
        mealService.update(expected, 100_000);
        Meal actual = mealService.get(100_000, 100_000);
        assertEquals(expected, actual);
    }

    @Test
    public void create() {
        Meal expected = new Meal(LocalDateTime.now(), "Ужин", 400);
        int id = mealService.create(expected, 100_000).getId();
        expected.setId(id);
        Meal actual = mealService.get(id, 100_000);
        assertEquals(expected, actual);
    }
}