package ru.javawebinar.topjava.repository.jpa;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;

@Repository
public class JpaMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(JpaMealRepository.class);
    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createQuery("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Query query = em.createQuery("SELECT m from Meal m WHERE m.id=:id AND m.user.id=:userId");
        query.setParameter("id", id);
        query.setParameter("userId", userId);
        return (Meal) query.getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.debug("getAll");
        Query query = em.createQuery("SELECT m from Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        Query query = em.createQuery("SELECT m from Meal m WHERE m.user.id=:userId AND m.dateTime >=: startDateTime AND m.dateTime <: endDateTime ORDER BY m.dateTime DESC");
        query.setParameter("userId", userId);
        query.setParameter("endDateTime", endDateTime);
        query.setParameter("startDateTime", startDateTime);
        return query.getResultList();
    }
}