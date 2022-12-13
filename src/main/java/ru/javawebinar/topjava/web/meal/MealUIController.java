package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@RestController
@RequestMapping(value = "/ajax/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealUIController extends AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(MealUIController.class);

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        List<MealTo> all = super.getAll();
        return all;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrCreate(@RequestParam String dateTime,
                               @RequestParam String description,
                               @RequestParam int calories,
                               @RequestParam(required = false) Integer id) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime),
                description,
                calories);

        if (id == null) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }

    @GetMapping("/filter")
    public List<MealTo> getBetween(@RequestParam(required = false) LocalDate startDate,
                                   @RequestParam(required = false) LocalDate endDate,
                                   @RequestParam(required = false) LocalTime startTime,
                                   @RequestParam(required = false) LocalTime endTime) {
        log.info("MealUIController between");
        List<MealTo> between = super.getBetween(startDate, startTime, endDate, endTime);
        return between;
    }
}
