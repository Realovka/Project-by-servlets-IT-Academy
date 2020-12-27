package by.realovka.web.service;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    User getUserByLoginAndPassword(String login, String password);
    List<User> getAllStudents();
    Trainer addStudentToTrainer(Trainer trainer, String studentName);
    List<Student> addThemeToStudents(Trainer trainer, String themeName);
    List<Student> addOrUpdateMarkToStudent(Trainer trainer, String studentLogin, String theme, Integer mark);
    List<Student> deleteMark(Trainer trainer, String studentLogin, String theme);
    Student getStudentByLogin(String login);
}
