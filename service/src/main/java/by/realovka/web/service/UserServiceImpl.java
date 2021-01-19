package by.realovka.web.service;

import by.realovka.web.dao.dao.UserDao;
import by.realovka.web.dao.dao.UserDaoImpl;
import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.*;
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
            User user = new User(userDTO.getUserName(), userDTO.getAge(), userDTO.getLogin(), builder, userDTO.getRole());
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
        String loginAndPasswordFromUI = login.concat(password); //TODO
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
    public User addStudentToGroup(User trainer, Long studentId) {
        userDao.addStudentToGroup(trainer.getGroupId(), studentId);
        List<Theme> themes = userDao.findAllTrainerTheme(trainer);
        userDao.addThemesToOneStudent(themes, studentId);
        List<Long> studentsId = userDao.findAllTrainerStudentsInGroups(trainer.getGroupId());
        List<User> students = userDao.findAllTrainerStudents(studentsId, trainer);
        List<User> studentsWithThemesAuthTrainer = getStudentsWithThemesAuthTrainer(students, trainer);
        trainer.setStudents(studentsWithThemesAuthTrainer);
        return trainer;
    }

    @Override
    public User getTrainerAndHisStudents(User trainer) {
        List<Long> studentsId = userDao.findAllTrainerStudentsInGroups(trainer.getGroupId());
        List<User> students = userDao.findAllTrainerStudents(studentsId, trainer);
        List<User> studentsWithThemesAuthTrainer = getStudentsWithThemesAuthTrainer(students, trainer);
        trainer.setStudents(studentsWithThemesAuthTrainer);
        return trainer;
    }


    private List<User> getStudentsWithThemesAuthTrainer(List<User> students, User auth) {
        for(User item : students) {
            List<Theme> themes = item.getThemes();
            List<Theme> themesAuthTrainer = themes.stream()
                                            .filter(t->t.getIdGroup()==auth.getGroupId())
                                            .collect(Collectors.toList());
            item.setThemes(themesAuthTrainer);
        }
        return students;
    }

    @Override
    public User getTrainerAndHisStudentsAfterAddTheme(User trainer, String nameTheme) {
        List<Long> studentsId = userDao.findAllTrainerStudentsInGroups(trainer.getGroupId());
        userDao.addThemeToStudents(studentsId, trainer, nameTheme);
        List<User> students = userDao.findAllTrainerStudents(studentsId,trainer);
        List<User> studentsWithThemesAuthTrainer = getStudentsWithThemesAuthTrainer(students, trainer);
        trainer.setStudents(studentsWithThemesAuthTrainer);
        return trainer;
    }


//
//    @Override
//    public List<Student> addOrUpdateMarkToStudent(Trainer trainer, String studentLogin, String theme, Integer mark) {
//        if (mark>100 || mark<0){
//            throw new MarkFormatIsInvalidException("Mark format is wrong!");
//        }
//        List<Student> students = getListStudentsAuthTrainer(trainer);
//        for (Student item : students) {
//            if (item.getLogin().equals(studentLogin)) {
//                List<Theme> themes = item.getThemes();
//                for (Theme var : themes) {
//                    if (var.getName().equals(theme)) {
//                        var.setMark(mark);
//                    }
//                }
//            }
//        }
//        return students;
//    }

//    private List<Student> getListStudentsAuthTrainer(Trainer trainer) {
//        Map<String, User> users = userRepository.findAll();
//        Trainer trainerFromMap = (Trainer) users.get(trainer.getLogin());
//        List<Student> students = trainerFromMap.getStudents();
//        return students;
//    }


//    @Override
//    public List<Student> deleteMark(Trainer trainer, String studentLogin, String theme) {
//        List<Student> students = getListStudentsAuthTrainer(trainer);
//        for (Student item : students) {
//            if (item.getLogin().equals(studentLogin)) {
//                List<Theme> themes = item.getThemes();
//                for (Theme var : themes) {
//                    if (var.getName().equals(theme)) {
//                        var.setMark(0);
//                    }
//                }
//            }
//        }
//        return students;
//    }
//
//    @Override
//    public Student getStudentByLogin(String login){
//        User result = null;
//        List<User> students = getAllStudents();
//        Stream<User> studentStream = students.stream();
//        studentStream.forEach(x -> x.getLogin().equals(login));
//       return (Student) result;
//    }

}