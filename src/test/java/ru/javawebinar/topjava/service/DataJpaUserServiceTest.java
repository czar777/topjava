package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles({"datajpa", "hsqldb"})
public class DataJpaUserServiceTest extends PUserServiceTest {

    @Test
    public void getMealWithUser() {
        User user = service.getUserWithMeals(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
        MEAL_MATCHER.assertMatch(meals, user.getMeals());
    }

}
