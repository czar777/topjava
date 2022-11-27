package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Set;

public interface UserRepository {
    // null if not found, when updated
    User save(User user);

    // false if not found
    boolean delete(int id);

    // null if not found
    User get(int id);

    // null if not found
    User getByEmail(String email);

    List<User> getAll();

    default User getWithMeals(int id) {
        throw new UnsupportedOperationException();
    }

    default void addUserRoles(Set<Role> roles, int userId) {
    }

    default void deleteUserRoles(Set<Role> roles, int userId) {

    }
}