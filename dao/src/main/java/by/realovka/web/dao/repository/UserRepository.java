package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.User;

import java.util.Map;

public interface UserRepository {

    void save(User user);
    Map<String, User> findAll();
}
