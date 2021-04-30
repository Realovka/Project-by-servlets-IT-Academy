package by.realovka.web.service.service;

import by.realovka.web.dao.dto.StudentDto;
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

    List<StudentDto> getStudentsFromUniversityWhoDontStudyAtAuthTrainer(TrainerDto trainerDto);

    TrainerDto addStudentToGroup(TrainerDto trainer, Long studentId);

    TrainerDto getTrainerAndHisStudentsAfterAddTheme(TrainerDto trainer, String nameTheme);

    TrainerDto addOrUpdateOrDeleteMark(TrainerDto trainer, Long themeId, Integer mark);

}
