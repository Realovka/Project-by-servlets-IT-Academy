package by.realovka.web.service.service;

import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;

public interface UserService {

    boolean saveUser(UserDTO userDTO);

    User identificationUserByLoginAndPassword(String login, String password);

    List<Student> getAllStudents(Trainer trainer);

    Trainer getById(Long id);

    Trainer createGroupByTrainer(Trainer trainer);

    Trainer addStudentToGroup(Trainer trainer, Long studentId);

    Trainer getTrainerAndHisStudentsAfterAddTheme(Trainer trainer, String nameTheme);

    Trainer addOrUpdateOrDeleteMark(Trainer trainer, String themeName, int mark);

}
