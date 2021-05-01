
package by.realovka.web.service.service;


import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.dto.GroupDto;
import by.realovka.web.dao.dto.UserDto;
import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.dao.dto.StudentDto;
import by.realovka.web.dao.dto.ThemeDto;
import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Group;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static by.realovka.web.dao.model.Role.ADMIN;
import static by.realovka.web.dao.model.Role.STUDENT;
import static by.realovka.web.dao.model.Role.TRAINER;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @SneakyThrows
    @Override
    public boolean saveUser(UserDto userDto) {
        if (userDao.findByLogin(userDto.getLogin()).equals(new User())) {
            String loginAndPassword = userDto.getLogin().concat(userDto.getPassword());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] loginAndPasswordHash = md5.digest(loginAndPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : loginAndPasswordHash) {
                builder.append(String.format("%02X", b));
            }
            if (userDto.getRole().equals(TRAINER)) {
                Trainer trainer = Trainer.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginAndPassword(builder.toString())
                        .build();
                log.info("Save new user {}", trainer);
                userDao.save(trainer);
            } else if (userDto.getRole().equals(STUDENT)) {
                Student student = Student.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginAndPassword(builder.toString())
                        .build();
                log.info("Save new user {}", student);
                userDao.save(student);
            } else if (userDto.getRole().equals(ADMIN)) {
                Admin admin = Admin.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginAndPassword(builder.toString())
                        .build();
                log.info("Save new user {}", admin);
                userDao.save(admin);
            }
            return true;
        } else {
            return false;
        }
    }


    @SneakyThrows
    @Override
    public User identificationUserByLoginAndPassword(String login, String password) {
        String loginAndPasswordFromUI = login.concat(password);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] loginAndPasswordHashFromUI = messageDigest.digest(loginAndPasswordFromUI.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : loginAndPasswordHashFromUI) {
            builder.append(String.format("%02X", b));
        }
        String loginAndPassword = String.valueOf(builder);
        User user = userDao.identificationUser(loginAndPassword);
        log.info("Identification user {}", user);
        return user;
    }

    @Override
    public TrainerDto getById(Long id) {
        Trainer trainer = getStudentsWithTrainerThemes((Trainer) userDao.findById(id));
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .group(GroupDto.builder()
                        .id(trainer.getGroup().getId())
                        .students(trainer.getGroup().getStudents().stream().map(student ->
                                StudentDto.builder()
                                        .id(student.getId())
                                        .name(student.getName())
                                        .theme(student.getThemes().stream().map(theme ->
                                                ThemeDto.builder()
                                                        .id(theme.getId())
                                                        .name(theme.getName())
                                                        .mark(theme.getMark())
                                                        .build()).collect(Collectors.toList()))
                                        .build()).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @Override
    public TrainerDto getTrainer(Long id) {
        Trainer trainer = (Trainer) userDao.findById(id);
        if (trainer.getGroup() == null) {
            return TrainerDto.builder()
                    .id(trainer.getId())
                    .name(trainer.getName())
                    .age(trainer.getAge())
                    .login(trainer.getLogin())
                    .loginAndPassword(trainer.getLoginAndPassword())
                    .build();
        } else {
            return TrainerDto.builder()
                    .id(trainer.getId())
                    .name(trainer.getName())
                    .age(trainer.getAge())
                    .login(trainer.getLogin())
                    .loginAndPassword(trainer.getLoginAndPassword())
                    .group(GroupDto.builder()
                            .id(trainer.getGroup().getId())
                            .build())
                    .build();
        }
    }

    @Override
    public TrainerDto createGroupByTrainer(TrainerDto trainerAuth) {
        Trainer trainer = Trainer.builder()
                .id(trainerAuth.getId())
                .name(trainerAuth.getName())
                .login(trainerAuth.getLogin())
                .age(trainerAuth.getAge())
                .loginAndPassword(trainerAuth.getLoginAndPassword())
                .build();
        Group group = Group.builder()
                .name("Group ".concat(trainer.getName()))
                .trainer(trainer)
                .themes(new ArrayList<>())
                .students(new ArrayList<>())
                .build();
        trainer.setGroup(group);
        trainer = userDao.addGroupToTrainer(trainer);
        return getById(trainer.getId());
    }

    @Override
    public List<StudentDto> getStudentsFromUniversityWhoDontStudyAtAuthTrainer(TrainerDto trainerDto) {
        List<Student> allStudentsFromUniversity = userDao.getAllStudents();
        List<StudentDto> allStudentsFromUniversityDto = allStudentsFromUniversity.stream()
                .map(student -> StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .theme(student.getThemes().stream().filter(theme -> theme.getGroup().getId().equals(trainerDto.getGroup().getId())).map
                                (theme ->
                                        ThemeDto.builder()
                                                .id(theme.getId())
                                                .name(theme.getName())
                                                .mark(theme.getMark())
                                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
        allStudentsFromUniversityDto.removeAll(trainerDto.getGroup().getStudents());
        return allStudentsFromUniversityDto;
    }

    @Override
    public TrainerDto addStudentToGroup(TrainerDto trainerDto, Long studentId) {
        Trainer trainer = (Trainer) userDao.findById(trainerDto.getId());
        List<Theme> themesNewStudentInGroup = new ArrayList<>();
        Student student = (Student) userDao.findById(studentId);
        if (trainer.getGroup().getStudents().size() > 0) {
            List<Theme> themes = getStudentWithAuthTrainerThemes(trainer).getThemes();
            for (Theme item : themes) {
                themesNewStudentInGroup.add(Theme.builder()
                        .name(item.getName())
                        .student(student)
                        .group(item.getGroup())
                        .build());
            }
            student.getThemes().addAll(themesNewStudentInGroup);
        }
        trainer.getGroup().getStudents().add(student);
        userDao.addStudentToGroup(trainer, student);
        return getById(trainer.getId());
    }

    @Override
    public TrainerDto getTrainerAndHisStudentsAfterAddTheme(TrainerDto trainerDto, String nameTheme) {
        Trainer trainer = (Trainer) userDao.findById(trainerDto.getId());
        Group group = trainer.getGroup();
        List<Theme> themes = new ArrayList<>();
        List<Student> students = trainer.getGroup().getStudents();
        for (Student item : students) {
            themes.add(Theme.builder()
                    .name(nameTheme)
                    .mark(0)
                    .student(item)
                    .group(group)
                    .build());
        }
        userDao.addThemeToGroup(themes);
        return getById(trainerDto.getId());
    }

    @Override
    public TrainerDto addOrUpdateOrDeleteMark(TrainerDto trainerDto, Long themeId, Integer mark) {
        Trainer trainer = (Trainer) userDao.findById(trainerDto.getId());
        userDao.addOrUpdateOrDeleteMark(themeId, mark, trainer);
        return getById(trainerDto.getId());
    }


    private Trainer getStudentsWithTrainerThemes(Trainer trainer) {
        if (trainer.getGroup() != null) {
            List<Student> students = trainer.getGroup().getStudents();
            for (Student item : students) {
                List<Theme> themes = item.getThemes().stream().
                        filter(theme -> theme.getGroup().equals(trainer.getGroup()))
                        .collect(Collectors.toList());
                item.setThemes(themes);
            }
            trainer.getGroup().setStudents(students);
        }
        return trainer;
    }

    private Student getStudentWithAuthTrainerThemes(Trainer trainer) {
        Student student = trainer.getGroup().getStudents().get(0);
        List<Theme> themes = student.getThemes().stream()
                .filter(theme -> theme.getGroup().equals(trainer.getGroup()))
                .collect(Collectors.toList());
        student.setThemes(themes);
        return student;
    }
}