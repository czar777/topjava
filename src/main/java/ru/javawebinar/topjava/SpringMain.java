package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            MealRestController controller = appCtx.getBean("mealRestController", MealRestController.class);
            List<Meal> allMeal = controller.getAll();

            for (Meal meal : allMeal) {
                System.out.println(meal);
            }

            System.out.println("Meal id = 1: " + controller.get(1));

            controller.delete(1);
            controller.delete(2);

            Meal meal1 = controller.create(new Meal(LocalDateTime.of(2022, Month.JANUARY, 30, 10, 0), "Завтрак", 900, SecurityUtil.authUserId()));
            System.out.println(meal1);

            System.out.println("--------------------------------");

            for (Meal meal : controller.getAll()) {
                System.out.println(meal);
            }
        }
    }
}
