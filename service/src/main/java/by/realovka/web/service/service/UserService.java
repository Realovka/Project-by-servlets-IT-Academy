package by.realovka.web.service.service;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.dao.dto.UserDto;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;

import java.util.List;

public interface UserService {

    boolean saveUser(UserDto userDTO);

    User identificationUserByLoginAndPassword(String login, String password);

    TrainerDto getById(Long id);

    TrainerDto getTrainer(Long id);

    TrainerDto createGroupByTrainer(TrainerDto trainer);

    Trainer addStudentToGroup(Trainer trainer, Long studentId);

    Trainer getTrainerAndHisStudentsAfterAddTheme(Trainer trainer, String nameTheme);

    Trainer addOrUpdateOrDeleteMark(Trainer trainer, Long themeId, Integer mark);

    Trainer getStudentsWithTrainerThemes(Trainer trainer);

}
