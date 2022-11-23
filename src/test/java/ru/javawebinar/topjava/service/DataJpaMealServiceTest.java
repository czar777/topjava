package ru.javawebinar.topjava.service;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.MEAL_MATCHER;
import static ru.javawebinar.topjava.MealTestData.meal1;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ActiveProfiles({"datajpa", "hsqldb"})
public class DataJpaMealServiceTest extends PMealServiceTest{

    @Test
    public void getMealWithUser() {
        Meal meal = service.getMealWithUser(meal1.id(), USER_ID);
        MEAL_MATCHER.assertMatch(meal, meal1);
    }
}
