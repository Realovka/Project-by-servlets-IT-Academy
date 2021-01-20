package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    void createUser(User user);

    Optional<User> findByLogin(String login) throws SQLException;

    Optional<User> identificationUser(String loginAndPassword);

    List<User> getAllStudents();

    void addGroupToTrainer(long groupId, Long trainerId);

    long getMaxInGroupId();

    User findTrainerAfterAdditionGroup(User auth);

    void addStudentToGroup(long id, Long studentId);

    List<Long> findAllTrainerStudentsInGroups(long groupId);

    List<User> findAllTrainerStudents(List<Long> studentsId, User auth);

    void addThemeToStudents (List<Long> studentsId, User auth, String themeName);

    List<Theme> findAllTrainerTheme(User auth);

    void addThemesToOneStudent (List<Theme> themes, long studentId);

    void addOrUpdateStudentMark (int mark, Long studentId, String themeName);

}
