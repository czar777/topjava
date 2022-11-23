package ru.javawebinar.topjava;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
//        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.getEnvironment().setActiveProfiles("jpa");
//        ctx.load("spring/spring-app.xml");
//        ctx.refresh();
//        print(ctx);
//        ctx.close();
//        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "datajpa");
//
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml");
//        context.getEnvironment().setActiveProfiles("jpa");

//        context.refresh();
//
//        String[] beanDefinitionNames = context.getBeanDefinitionNames();
//
//        MealRestController restController = context.getBean(MealRestController.class);
//
//        restController.create(new Meal(LocalDateTime.now(), "Обэд", 444));
//
//        for (String s : beanDefinitionNames) {
//            System.out.println(s);
//        }
//
//
//        context.close();
//        print(context);

//        try (ConfigurableApplicationContext appCtx =
//                     new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {

        try (ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext()) {

            appCtx.getEnvironment().setActiveProfiles("jpa", "postgres");
            appCtx.setConfigLocations("spring/spring-app.xml", "spring/spring-db.xml");
            appCtx.refresh();
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru",
                    "password", Role.ADMIN));
            System.out.println();

            MealRestController mealController = appCtx.getBean(MealRestController.class);
            List<MealTo> filteredMealsWithExcess =
                    mealController.getBetween(
                            LocalDate.of(2020, Month.JANUARY, 30), LocalTime.of(7, 0),
                            LocalDate.of(2020, Month.JANUARY, 31), LocalTime.of(11, 0));
            filteredMealsWithExcess.forEach(System.out::println);
            System.out.println();
            System.out.println(mealController.getBetween(null, null, null, null));


            String[] beanDefinitionNames = appCtx.getBeanDefinitionNames();

            MealRestController restController = appCtx.getBean(MealRestController.class);

            restController.create(new Meal(LocalDateTime.now(), "Обэд", 444));

            for (String s : beanDefinitionNames) {
                System.out.println(s);
            }
        }

    }

//    private static void print(ApplicationContext ctx){
//        String welcome = ctx.getBean("jpa", String.class);
//        System.out.println("Active profile: " + welcome);
//    }
}
