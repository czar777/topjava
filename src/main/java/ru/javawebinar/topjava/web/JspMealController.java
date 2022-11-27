package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {

    private static final Logger log = LoggerFactory.getLogger(JspMealController.class);

    private final MealService service;

    @Autowired
    public JspMealController(MealService service) {
        this.service = service;
    }

    @GetMapping("/meals")
    public String getAll(Model model) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("meals", MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay()));
        return "/meals";
    }

    @GetMapping("/meals/mealForm")
    public String newMeal(Model model) {
        log.info("get mealForm");
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),
                "", 1000));
        return "mealForm";
    }

    @PostMapping("/meals")
    public String create(@RequestParam("dateTime") String dateTime, @RequestParam("description") String description,
                         @RequestParam("calories") int calories) {
        log.info("post mealForm");
        Meal meal = new Meal();
        meal.setDateTime(convertStringToLocalDateTime(dateTime));
        meal.setDescription(description);
        meal.setCalories(calories);
        service.create(meal, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @GetMapping("/meals/{id}/mealUpdate")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("meal", service.get(id, SecurityUtil.authUserId()));
        return "mealUpdate";
    }

    @PostMapping("/meals/update")
    public String update(@RequestParam("dateTime") String dateTime, @RequestParam("description") String description,
                         @RequestParam("calories") int calories, @RequestParam("id") int id) {
        Meal meal = new Meal();
        meal.setDateTime(convertStringToLocalDateTime(dateTime));
        meal.setDescription(description);
        meal.setCalories(calories);
        meal.setId(id);

        service.update(meal, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @GetMapping("/meals/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id, SecurityUtil.authUserId());
        return "redirect:/meals";
    }

    @PostMapping("/meals/filter")
    public String filter(Model model,
            @RequestParam("startDate") String startDateString, @RequestParam("endDate") String endDateString,
                         @RequestParam("startTime") String startTimeString, @RequestParam("endTime") String endTimeString
    ) {
        LocalDate startDate = parseLocalDate(startDateString);
        LocalDate endDate = parseLocalDate(endDateString);
        LocalTime startTime = parseLocalTime(startTimeString);
        LocalTime endTime = parseLocalTime(endTimeString);

        List<Meal> mealsDateFiltered = service.getBetweenInclusive(startDate, endDate, SecurityUtil.authUserId());
        List<MealTo> meals = MealsUtil.getFilteredTos(mealsDateFiltered, SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);

        model.addAttribute("meals", meals);

        return "/meals";
    }

    private LocalDateTime convertStringToLocalDateTime(String localDateTimeString) {
        String s = localDateTimeString.replaceAll("T", " ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(s, formatter);
        return dateTime;
    }
}
