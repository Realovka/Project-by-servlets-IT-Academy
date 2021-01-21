package by.realovka.web.service;

//import by.realovka.web.dao.model.Student;
//import by.realovka.web.dao.model.Trainer;

import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean saveUser(UserDTO userDTO);

    Optional<User> identificationUserByLoginAndPassword(String login, String password);

    List<User> getAllStudents();

    User createGroupByTrainer(User auth);

    User addStudentToGroup(User trainer, Long studentId);

    User getUserWithHisStudents(User auth);

    User getTrainerAndHisStudentsAfterAddTheme(User trainer, String nameTheme);

    User addOrUpdateMarkToStudent(User auth, String studentId, String themeName, int mark);

    User deleteMark(User auth, String studentId, String themeName);

}
