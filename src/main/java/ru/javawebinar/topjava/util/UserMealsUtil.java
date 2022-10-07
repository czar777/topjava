package ru.javawebinar.topjava.util;

import javafx.util.Pair;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 1, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 1, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 1, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> filteredByCycles = new ArrayList<>();
        HashMap<LocalDate, Integer> userExcess = new HashMap<>();

        meals.sort(Comparator.comparing(UserMeal::getDateTime));

        for (UserMeal userMeal : meals) {
            LocalDate ld = userMeal.getDateTime().toLocalDate();
            userExcess.merge(ld, userMeal.getCalories(), Integer::sum);
        }
//        System.out.println("calories per day = " + caloriesPerDay);
//        for (Map.Entry<LocalDate, Integer> entry : userExcess.entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }
        for (UserMeal userMeal : meals) {
            int currentCaloriesPerDay = userExcess.get(userMeal.getDateTime().toLocalDate());

            if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                if (currentCaloriesPerDay > caloriesPerDay) {
                    filteredByCycles.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                } else {
                    filteredByCycles.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                }
            }
        }


        return filteredByCycles;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        meals.sort(Comparator.comparing(UserMeal::getDateTime));
        Map<LocalDate, Integer> map = meals.stream()
                .collect(Collectors.groupingBy(x -> x.getDateTime().toLocalDate(), Collectors.summingInt(x -> x.getCalories())));

        List<UserMealWithExcess> filteredByCycles = meals.stream()
                .filter(x -> TimeUtil.isBetweenHalfOpen(x.getDateTime().toLocalTime(), startTime, endTime))
                .map(x -> {
                    if (map.get(x.getDateTime().toLocalDate()) > caloriesPerDay) {
                        return new UserMealWithExcess(x.getDateTime(), x.getDescription(), x.getCalories(), true);
                    } else {
                        return new UserMealWithExcess(x.getDateTime(), x.getDescription(), x.getCalories(), false);
                    }
                }).collect(Collectors.toList());


        return filteredByCycles;
    }
}
