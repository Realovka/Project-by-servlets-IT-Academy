package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryImpl implements UserRepository {

    private Map<String, User> map = new HashMap<>();

    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {

    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(User user) {
      map.put(user.getUserName(),user);
    }

    @Override
    public Map<String, User> findAll(){
        return map;
    }

}
