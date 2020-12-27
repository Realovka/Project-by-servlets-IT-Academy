package by.realovka.web.service;

import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import by.realovka.web.service.exception.*;

import java.util.*;


public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserRepository userRepository;

    private UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserServiceImpl getInstance(UserRepository userRepository) {
        if (instance == null) {
            instance = new UserServiceImpl(userRepository);
        }
        return instance;
    }

    @Override
    public void saveUser(User user) {
        Map<String, User> users = userRepository.findAll();
        if (users.isEmpty() || !users.containsKey(user.getLogin())) {
            userRepository.save(user);
        } else {
            if (users.containsKey(user.getLogin()))
                throw new SuchLoginIsAlreadyExistException("Such user's login is already exist");
        }
    }


    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        Map<String, User> users = userRepository.findAll();
        if (!users.containsKey(login)) {
            throw new NoSuchUserException("No such user!");
        }
        if (users.containsKey(login) && !users.get(login).getPassword().equals(password)) {
            throw new PasswordIsInvalidException("Password is invalid!");
        }
        if (users.containsKey(login) && users.get(login).getPassword().equals(password)) {
            user = users.get(login);
        }
        return user;
    }

    @Override
    public List<User> getAllStudents() {
        List<User> students = new ArrayList<>();
        Map<String, User> users = userRepository.findAll();
        Collection<User> userCollection = users.values();
        for (User item : userCollection) {
            if (item.getClass().equals(Student.class)) {
                students.add(item);
            }
        }
        return students;
    }

    @Override
    public Trainer addStudentToTrainer(Trainer trainer, String studentName) {
        Map<String, User> users = userRepository.findAll();
        Trainer trainerFromMap = (Trainer) users.get(trainer.getLogin());
        Student studentFromMap = (Student) users.get(studentName);
        trainerFromMap.getStudents().add(studentFromMap);
        return trainer;

    }

    @Override
    public List<Student> addThemeToStudents(Trainer trainer, String theme) {
        if (theme.equals("")){
            throw new ThemeFormatIsWrongException("You didn't enter name of theme");
        }
        List<Student> students = getListStudentsAuthTrainer(trainer);
        for (Student item : students) {
            item.getThemes().add(new Theme(theme));
        }
        return students;
    }

    @Override
    public List<Student> addOrUpdateMarkToStudent(Trainer trainer, String studentLogin, String theme, Integer mark) {
        if (mark>100 || mark<0){
            throw new MarkFormatIsInvalidException("Mark format is wrong!");
        }
        List<Student> students = getListStudentsAuthTrainer(trainer);
        for (Student item : students) {
            if (item.getLogin().equals(studentLogin)) {
                List<Theme> themes = item.getThemes();
                for (Theme var : themes) {
                    if (var.getName().equals(theme)) {
                        var.setMark(mark);
                    }
                }
            }
        }
        return students;
    }

    private List<Student> getListStudentsAuthTrainer(Trainer trainer) {
        Map<String, User> users = userRepository.findAll();
        Trainer trainerFromMap = (Trainer) users.get(trainer.getLogin());
        List<Student> students = trainerFromMap.getStudents();
        return students;
    }


    @Override
    public List<Student> deleteMark(Trainer trainer, String studentLogin, String theme) {
        List<Student> students = getListStudentsAuthTrainer(trainer);
        for (Student item : students) {
            if (item.getLogin().equals(studentLogin)) {
                List<Theme> themes = item.getThemes();
                for (Theme var : themes) {
                    if (var.getName().equals(theme)) {
                        var.setMark(0);
                    }
                }

            }
        }
        return students;
    }

    @Override
    public Student getStudentByLogin(String login){
        User result = null;
        List<User> students = getAllStudents();
        for(User item : students){
            if (item.getLogin().equals(login)){
                result = item;
            }
        }
       return (Student) result;
    }

}