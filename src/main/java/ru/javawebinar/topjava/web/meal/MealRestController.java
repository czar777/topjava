package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.ConverterLocalDate;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/meals", produces = MediaType.APPLICATION_JSON_VALUE)
public class MealRestController extends AbstractMealController {

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping
    public List<MealTo> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal) {
        Meal created = super.create(meal);
        URI uriOfNweResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/rest/meals/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNweResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int id) {
        super.update(meal, id);
    }

//    @GetMapping("/filter")
//    public List<MealTo> getBetween(@RequestParam String startDate,@RequestParam String startTime,
//                                   @RequestParam String endDate,@RequestParam String endTime) {
//
//        LocalDate startD = parseLocalDate(startDate);
//        LocalDate endD = parseLocalDate(endDate);
//        LocalTime startT = parseLocalTime(startTime);
//        LocalTime endT = parseLocalTime(endTime);
//
//        List<MealTo> meals = super.getBetween(startD, startT, endD, endT);
//        return meals;
//    }

//    @GetMapping("/filter")
//    public List<MealTo> getBetween(@DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate startDate,
//                                   @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam LocalDate endDate,
//                                   @DateTimeFormat(pattern = "HH:mm") @RequestParam LocalTime startTime,
//                                   @DateTimeFormat(pattern = "HH:mm") @RequestParam LocalTime endTime) {
//
//        List<MealTo> meals = super.getBetween(startDate, startTime, endDate, endTime);
//        return meals;
//    }

    @GetMapping("/filter")
    public List<MealTo> getBetween(@RequestParam(required = false) LocalDate startDate,
                                   @RequestParam(required = false) LocalDate endDate,
                                   @RequestParam(required = false) LocalTime startTime,
                                   @RequestParam(required = false) LocalTime endTime) {

        List<MealTo> meals = super.getBetween(startDate, startTime, endDate, endTime);
        return meals;
    }

}