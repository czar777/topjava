package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    int deleteByIdAndUserId(int id, int UserId);

    Meal findByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserId(Sort date_time, int userId);

    @Query("SELECT m FROM Meal m WHERE m.dateTime >= ?1 AND m.dateTime < ?2 AND m.user.id = ?3 ORDER BY m.dateTime DESC")
    List<Meal> findAllByDate(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
