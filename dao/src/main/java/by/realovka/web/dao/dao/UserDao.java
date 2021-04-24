package by.realovka.web.dao.dao;


import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    User findByLogin(String login);

    void save(User user);

    User identificationUser(String loginAndPassword);

    List<Student> getAllStudents();

    Trainer addGroupToTrainer(Trainer trainer);

    User findById(Long id);

    void addStudentToGroup(Trainer trainer, Student student);

    void addThemeToGroup(List<Theme> themes);

    Trainer update(Long id, int mark, Trainer trainer);

}
