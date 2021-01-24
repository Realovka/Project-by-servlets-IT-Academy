package by.realovka.web.service.service;

import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.dao.UserDaoImpl;
import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class UserServiceImpl implements UserService {

    private static volatile UserServiceImpl instance;
    private final UserDao userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
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
        if (userDao.findByLogin(userDTO.getLogin()).isEmpty()) { //TODO
            String loginAndPassword = userDTO.getLogin().concat(userDTO.getPassword());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] loginAndPasswordHash = md5.digest(loginAndPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : loginAndPasswordHash) {
                builder.append(String.format("%02X", b));
            }
            User user = new User()
                    .withUserName(userDTO.getUserName())
                    .withAge(userDTO.getAge())
                    .withLogin(userDTO.getLogin())
                    .withLoginAndPassword(builder)
                    .withRole(userDTO.getRole());
            log.info("Save new user {}", user);
            userDao.createUser(user);
            return true;
        } else {
            return false;
        }
    }


    @SneakyThrows
    @Override
    public Optional<User> identificationUserByLoginAndPassword(String login, String password) {
        Optional<User> user;
        String loginAndPasswordFromUI = login.concat(password);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] loginAndPasswordHashFromUI = messageDigest.digest(loginAndPasswordFromUI.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : loginAndPasswordHashFromUI) {
            builder.append(String.format("%02X", b));
        }
        String loginAndPassword = String.valueOf(builder);
        user = userDao.identificationUser(loginAndPassword);
        log.info("Identification user {}", user);
        return user;
    }

    @Override
    public List<User> getAllStudents() {
        return userDao.getAllStudents();
    }

    @Override
    public User createGroupByTrainer(User auth) {
        long maxGroupId = userDao.getMaxInGroupId();
        userDao.addGroupToTrainer(maxGroupId + 1, auth.getId());
        return userDao.findTrainerAfterAdditionGroup(auth);
    }

    @Override
    public User getUserWithHisStudents(User auth) {
        List<Long> studentsId = userDao.findAllTrainerStudentsInGroups(auth.getGroupId());
        List<User> students = userDao.findAllTrainerStudents(studentsId, auth);
        List<User> trainerWithHisStudents = getStudentsWithThemesAuthTrainer(students, auth);
        auth.setStudents(trainerWithHisStudents);
        return auth;
    }

    @Override
    public User addStudentToGroup(User auth, Long studentId) {
        userDao.addStudentToGroup(auth.getGroupId(), studentId);
        List<Theme> themes = userDao.findAllTrainerTheme(auth);
        userDao.addThemesToOneStudent(themes, studentId);
        getUserWithHisStudents(auth);
        return auth;
    }

    @Override
    public User getTrainerAndHisStudentsAfterAddTheme(User auth, String nameTheme) {
        List<Long> studentsId = userDao.findAllTrainerStudentsInGroups(auth.getGroupId());
        userDao.addThemeToStudents(studentsId, auth, nameTheme);
        getUserWithHisStudents(auth);
        return auth;
    }

    @Override
    public User addOrUpdateMarkToStudent(User auth, String studentId, String themeName, int mark) {
        Long studentIdConvert = Long.parseLong(studentId);
        userDao.addOrUpdateStudentMark(mark, studentIdConvert, themeName);
        getUserWithHisStudents(auth);
        return auth;
    }

    @Override
    public User deleteMark(User auth, String studentId, String themeName) {
        Long studentIdConvert = Long.parseLong(studentId);
        userDao.deleteMark(studentIdConvert, themeName);
        getUserWithHisStudents(auth);
        return auth;
    }

    @Override
    public User findAllThemesAndMarkOfStudent(User auth) {
        List<Theme> themes = userDao.findAllThemesAndMarksOfStudent(auth.getId());
        auth.setThemes(themes);
        return auth;
    }

    //to show students in situation when the trainer added students but didn't create a theme
    private List<User> getStudentsWithThemesAuthTrainer(List<User> students, User auth) {
        for (User item : students) {
            List<Theme> themes = item.getThemes();
            List<Theme> themesAuthTrainer = themes.stream()
                    .filter(t -> t.getIdGroup() == auth.getGroupId())
                    .collect(Collectors.toList());
            item.setThemes(themesAuthTrainer);
        }
        return students;
    }

}