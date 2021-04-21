package by.realovka.web.service.service;

import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean saveUser(UserDTO userDTO);

    User identificationUserByLoginAndPassword(String login, String password);

    List<Student> getAllStudentsWithoutTrainerStudents(Trainer trainer);

    Trainer getById(Long id);

    Trainer createGroupByTrainer(Trainer trainer);

    Trainer addStudentToGroup(Trainer trainer, Long studentId);

    Trainer getTrainerAndHisStudentsAfterAddTheme(Trainer trainer, String nameTheme);

    Trainer addOrUpdateOrDeleteMark(Trainer trainer, Long themeId, Integer mark);

    Trainer getStudentsWithTrainerThemes(Trainer trainer);

}
