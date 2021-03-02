
package by.realovka.web.service.service;


import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.dao.UserDaoImpl;
import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static by.realovka.web.dao.model.Role.STUDENT;
import static by.realovka.web.dao.model.Role.TRAINER;

@Slf4j
public class UserServiceImpl implements UserService {

    private static volatile UserServiceImpl instance;
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
    }


    @SneakyThrows
    @Override
    public boolean saveUser(UserDTO userDTO) {
        if (userDao.findByLogin(userDTO.getLogin()).equals(new User())) {
            String loginAndPassword = userDTO.getLogin().concat(userDTO.getPassword());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] loginAndPasswordHash = md5.digest(loginAndPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : loginAndPasswordHash) {
                builder.append(String.format("%02X", b));
            }
            if (userDTO.getRole().equals(TRAINER)) {
                User trainer = Trainer.builder()
                        .userName(userDTO.getLogin())
                        .age(userDTO.getAge())
                        .login(userDTO.getLogin())
                        .loginAndPassword(builder.toString())
                        .build();
                log.info("Save new user {}", trainer);
                userDao.save(trainer);
            } else if (userDTO.getRole().equals(STUDENT)) {
                User student = Student.builder()
                        .userName(userDTO.getLogin())
                        .age(userDTO.getAge())
                        .login(userDTO.getLogin())
                        .loginAndPassword(builder.toString())
                        .build();
                log.info("Save new user {}", student);
                userDao.save(student);
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
    public Trainer getById(Long id) {
        return getStudentsWithTrainerThemes((Trainer) userDao.findById(id));
    }

    @Override
    public List<Student> getAllStudents() {
        return userDao.getAllStudents();
    }


    @Override
    public Trainer createGroupByTrainer(Trainer trainer) {
        Group group = Group.builder()
                .name("Group ".concat(trainer.getUserName()))
                .trainer(trainer)
                .themes(new ArrayList<>())
                .students(new ArrayList<>())
                .build();
        trainer.setGroup(group);
        return getStudentsWithTrainerThemes(userDao.addGroupToTrainer(trainer, group));
    }

    @Override
    public Trainer addStudentToGroup(Trainer trainer, Long studentId) {
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
        userDao.addStudentToGroup(trainer);
        return getStudentsWithTrainerThemes((Trainer) userDao.findById(trainer.getId()));
    }


    @Override
    public Trainer getTrainerAndHisStudentsAfterAddTheme(Trainer trainer, String nameTheme) {
        Group group = trainer.getGroup();
        List<Theme> themes = new ArrayList<>();
        List<Student> students = trainer.getGroup().getStudents();
        for (Student item : students) {
            group.getThemes().add(Theme.builder()
                    .name(nameTheme)
                    .mark(0)
                    .student(item)
                    .group(group)
                    .build());
            themes.add(Theme.builder()
                    .name(nameTheme)
                    .mark(0)
                    .student(item)
                    .group(group)
                    .build());
        }
        userDao.addThemeToGroup(themes);
        return getStudentsWithTrainerThemes((Trainer) userDao.findById(trainer.getId()));
    }

    private Trainer getStudentsWithTrainerThemes(Trainer trainer) {
        if(trainer.getGroup() != null) {
            List<Student> students = trainer.getGroup().getStudents();
            for (Student item : students) {
                List<Theme> themes = item.getThemes().stream().filter(theme -> theme.getGroup().equals(trainer.getGroup())).collect(Collectors.toList());
                item.setThemes(themes);
            }
            trainer.getGroup().setStudents(students);
        }
            return trainer;
    }

    private Student getStudentWithAuthTrainerThemes(Trainer trainer) {
        Student student = trainer.getGroup().getStudents().get(0);
        List<Theme> themes = student.getThemes().stream().filter(theme -> theme.getGroup().equals(trainer.getGroup())).collect(Collectors.toList());
        student.setThemes(themes);
        return student;
    }

    @Override
    public Trainer addOrUpdateMark(Trainer trainer, String studentId, String themeId, int mark) {
        Long studentIdPars = Long.parseLong(studentId);
        Long themeIdPars = Long.parseLong(themeId);
        userDao.addOrUpdateMarkToStudent(studentIdPars, themeIdPars, mark);
        return getStudentsWithTrainerThemes(trainer);
    }


}