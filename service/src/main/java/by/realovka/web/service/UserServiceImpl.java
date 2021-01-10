package by.realovka.web.service;

import by.realovka.web.dao.dao.UserDao;
//import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.dao.UserDaoImpl;
import by.realovka.web.dao.dto.UserDTO;
import by.realovka.web.dao.model.Theme;
//import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.service.exception.*;
import lombok.SneakyThrows;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;
    private UserDao userDao;

    private UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public static UserServiceImpl getInstance(UserDao userDao) {
        if (instance == null) {
            return new UserServiceImpl(userDao);
        }
        return instance;
    }

    @SneakyThrows
    @Override
    public boolean saveUser(UserDTO userDTO) {
        if (UserDaoImpl.getInstance().findByLogin(userDTO.getLogin()).isEmpty()) { //TODO
            String loginAndPassword = userDTO.getLogin().concat(userDTO.getPassword());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] loginAndPasswordHash = md5.digest(loginAndPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : loginAndPasswordHash){
                builder.append(String.format("%02X", b));
            }
            User user = new User(userDTO.getUserName(), userDTO.getAge(), userDTO.getLogin(), builder, userDTO.getRole());
            UserDaoImpl.getInstance().createUser(user);
            return true;
        } else {
            return false;
        }
    }


    @SneakyThrows
    @Override
    public User identificationUserByLoginAndPassword(String login, String password) {
        User auth = null;
        String loginAndPasswordFromUI = login.concat(password); //TODO
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] loginAndPasswordHashFromUI = messageDigest.digest(loginAndPasswordFromUI.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : loginAndPasswordHashFromUI){
            builder.append(String.format("%02X", b));
        }
        String loginAndPassword = String.valueOf(builder);
        if (UserDaoImpl.getInstance().identificationUser(loginAndPassword).isPresent()) {
            auth = UserDaoImpl.getInstance().identificationUser(loginAndPassword).get();
        }
        return auth;
    }
//
//    @Override
//    public List<User> getAllStudents() {
//        Map<String, User> users = userRepository.findAll();
//        Collection<User> userCollection = users.values();
//        return userCollection.stream().filter(user -> user.getClass().equals(Student.class))
//                .sorted(Comparator.comparing(User::getLogin))
//                .collect(Collectors.toList());
//    }

//    @Override
//    public Trainer addStudentToTrainer(Trainer trainer, String studentName) {
//        Map<String, User> users = userRepository.findAll();
//        Trainer trainerFromMap = (Trainer) users.get(trainer.getLogin());
//        Student studentFromMap = (Student) users.get(studentName);
//        trainerFromMap.getStudents().add(studentFromMap);
//        return trainer;
//
//    }

//    @Override
//    public List<Student> addThemeToStudents(Trainer trainer, String theme) {
//        if (theme.equals("")){
//            throw new ThemeFormatIsWrongException("You didn't enter name of theme");
//        }
//        List<Student> students = getListStudentsAuthTrainer(trainer);
//        for (Student item : students) {
//            item.getThemes().add(new Theme(theme));
//        }
//        return students;
//    }
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