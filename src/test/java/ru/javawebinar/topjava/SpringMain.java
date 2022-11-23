package ru.javawebinar.topjava;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
//        try (ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext()) {
//
//            appCtx.getEnvironment().setActiveProfiles("jpa", "postgres");
//            appCtx.setConfigLocations("spring/spring-app.xml", "spring/spring-db.xml");
//            appCtx.refresh();
//            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
//            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
//            adminUserController.create(new User(null, "userName", "email@mail.ru",
//                    "password", Role.ADMIN));
//            System.out.println();
//
//            MealRestController mealController = appCtx.getBean(MealRestController.class);
//            List<MealTo> filteredMealsWithExcess =
//                    mealController.getBetween(
//                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
//                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
//            filteredMealsWithExcess.forEach(System.out::println);
//            System.out.println();
//            System.out.println(mealController.getBetween(null, null, null, null));
//
//
//            String[] beanDefinitionNames = appCtx.getBeanDefinitionNames();
//
//            MealRestController restController = appCtx.getBean(MealRestController.class);
//
//            restController.create(new Meal(LocalDateTime.now(), "Обэд", 444));
//
//            for (String s : beanDefinitionNames) {
//                System.out.println(s);
//            }
//        }

        Meal meal = new Meal(LocalDateTime.now(), "Обэд", 333);


    }

    private static void ss(Timestamp timestamp) {
        System.out.println(timestamp);
    }

}
