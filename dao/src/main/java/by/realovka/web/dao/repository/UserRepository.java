package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUsersByLogin(String login);
    User findUsersByLoginAndPassword(String loginAndPassword);
    User findUsersById(Long id);


}
