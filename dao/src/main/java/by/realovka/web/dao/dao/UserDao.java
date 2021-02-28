package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.*;

import java.util.List;

public interface UserDao {

    User findByLogin(String login);

    void save(User user);

    User identificationUser(String loginAndPassword);

    List<Student> getAllStudents();

    Trainer addGroupToTrainer(Trainer trainer, Group group);

    User findById(Long id);

//    Set<Student> findAllTrainerStudents(Trainer trainer);

    void addStudentToGroup(Trainer trainer, Student student, List<Theme> themes);

    void addThemeToGroup(List<Theme> themes);

    void addOrUpdateMarkToStudent(Theme theme);

}
