package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;

public interface UserDao {

    User findByLogin(String login);

    void save(User user);

    User identificationUser(String loginAndPassword);

    List<Student> getAllStudents();

    Trainer addGroupToTrainer(Trainer trainer);

    User findById(Long id);

//    Set<Student> findAllTrainerStudents(Trainer trainer);

    void addStudentToGroup(Trainer trainer);

    void addThemeToGroup(List<Theme> themes);

    Trainer addOrUpdateMarkToStudent(Long id, int mark, Trainer trainer);

}
