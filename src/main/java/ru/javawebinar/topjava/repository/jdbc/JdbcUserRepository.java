package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    private final UserExtractor userExtractor;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, UserExtractor userExtractor) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.userExtractor = userExtractor;
    }

    @Override
    @Transactional
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
            addUserRoles(user.getRoles(), user.id());
            return user;
        } else if (namedParameterJdbcTemplate.update("""
                   UPDATE users SET name=:name, email=:email, password=:password, 
                   registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id;
                """, parameterSource) == 0) {

            return null;
        }
        setUserRoles(user.getRoles(), user.getId());
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
//    @EntityGraph(attributePaths = {"roles"})
    public User get(int id) {
        Map<Integer, User> users = jdbcTemplate.query
                ("SELECT * FROM users LEFT JOIN user_roles ur on users.id = ur.user_id WHERE id=?", userExtractor, id);
        return DataAccessUtils.singleResult(new ArrayList<>(users.values()));
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        Map<Integer, User> users = jdbcTemplate.query("SELECT * FROM users LEFT JOIN user_roles ur " +
                "on users.id = ur.user_id WHERE email=?", userExtractor, email);
        return DataAccessUtils.singleResult(new ArrayList<>(users.values()));
    }

    @Override
    public List<User> getAll() {
        Map<Integer, User> users = jdbcTemplate.query
                ("SELECT * FROM users u LEFT JOIN user_roles ur ON (u.id = ur.user_id) ORDER BY name, email", userExtractor);
        return new ArrayList<>(users.values());
    }

    @Override
    @Transactional
    public void addUserRoles(Set<Role> roles, int userId) {
        for (Role role : roles) {
            jdbcTemplate.update("INSERT INTO user_roles (user_id, role) VALUES (?, ?)",
                    userId, role.toString().toUpperCase());
        }
    }

    @Override
    @Transactional
    public void setUserRoles(Set<Role> roles, int userId) {
        for (Role role : roles) {
            jdbcTemplate.update("UPDATE user_roles SET role = ? WHERE user_id = ?",
                    role.toString().toUpperCase(), userId);
        }
    }

    @Override
    @Transactional
    public void deleteUserRoles(Set<Role> roles, int userId) {
        for (Role role : roles) {
            jdbcTemplate.update("DELETE FROM user_roles WHERE user_id = ? AND role = ?",
                    userId, role.toString().toUpperCase());
        }
    }
}
