package by.realovka.web.dao.dao;

import by.realovka.web.dao.connection.DataSource;
import by.realovka.web.dao.model.Role;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.User;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;


public class UserDaoImpl implements UserDao {

    private static final String CREATE_USER = "INSERT INTO users VALUES (default,?,?,?,?,?::user_role,default)";
    private static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login= ?";
    private static final String IDENT_USER = "SELECT * FROM users WHERE login_and_password = ?";
    private static final String GET_ALL_STUDENTS = "SELECT * FROM users WHERE status = 'STUDENT'";
    private static final String FIND_MAX_GROUP_ID = "SELECT MAX(group_id) from users";
    private static final String ADD_GROUP_TO_TRAINER = "UPDATE users SET group_id = ? WHERE id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id= ?";
    private static final String ADD_STUDENT_TO_GROUP = "INSERT INTO groups VALUES (?,?)";
    private static final String FIND_ALL_STUDENTS_OF_TRAINER_IN_GROUPS = "SELECT DISTINCT * FROM groups WHERE group_id = ?";
    private static final String FIND_ALL_STUDENTS_OF_TRAINER_WITH_THERE_THEMES = "SELECT u.id, u.user_name, u.login, u.status, t.student_id, " +
            " t.group_id, t.name_theme, t.mark FROM users u FULL JOIN themes t ON u.id=t.student_id WHERE u.id IN(?)";
    private static final String ADD_THEME_TO_STUDENTS = "INSERT INTO themes VALUES (?,?,?, default )";
    private static final String FIND_ALL_TRAINER_THEMES = "SELECT DISTINCT * FROM themes WHERE group_id = ?";

    private final DataSource dataSource = DataSource.getInstance();

    private static volatile UserDaoImpl instance;

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void createUser(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, String.valueOf(user.getLoginAndPassword()));
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByLogin(String login) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
//                User user = new User.UserBuilder().login(login).build();
                    return Optional.of(new User(login));
                }
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<User> identificationUser(String loginAndPassword) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(IDENT_USER)) {
            preparedStatement.setString(1, loginAndPassword);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new User(rs.getLong("id"),
                            rs.getString("user_name"),
                            rs.getString("login"),
                            Role.valueOf(rs.getString("status")),
                            rs.getLong("group_id")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAllStudents() {
        List<User> students = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STUDENTS);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    User user = new User(rs.getLong("id"), rs.getString("user_name"),
                            rs.getString("login"), Role.valueOf(rs.getString("status")));
                    students.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public long getMaxInGroupId() {
        long id = 0;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAX_GROUP_ID);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                id = rs.getLong("max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void addGroupToTrainer(long groupId, Long trainerId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_GROUP_TO_TRAINER)) {
            preparedStatement.setLong(1, groupId);
            preparedStatement.setLong(2, trainerId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findTrainerAfterAdditionGroup(User auth) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, auth.getId());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    auth.setGroupId(rs.getLong("group_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auth;
    }

    @Override
    public void addStudentToGroup(long groupId, Long studentId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_STUDENT_TO_GROUP)) {
            preparedStatement.setLong(1, groupId);
            preparedStatement.setLong(2, studentId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Long> findAllTrainerStudentsInGroups(long groupId) {
        List<Long> studentsId = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_STUDENTS_OF_TRAINER_IN_GROUPS)) {
            preparedStatement.setLong(1, groupId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    studentsId.add(rs.getLong("student_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsId;
    }

    @Override
    public List<User> findAllTrainerStudents(List<Long> studentsId, User auth) {
        Map<Long, User> userMap = new HashMap<>();
        Map<String, Theme> themeMap = new HashMap<>();
        if (studentsId.size() > 0) {
            String studentsIdIN = studentsId.stream()
                    .map(x -> String.valueOf(x))
                    .collect(Collectors.joining(", ", "(", ")"));

            studentsIdIN = FIND_ALL_STUDENTS_OF_TRAINER_WITH_THERE_THEMES.replace("(?)", studentsIdIN);

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(studentsIdIN)) {
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        themeMap.putIfAbsent(rs.getString("name_theme"), new Theme()
                                .withIdGroup(rs.getLong("group_id"))
                                .withName(rs.getString("name_theme"))
                                .withMark(rs.getInt("mark")));
                        userMap.putIfAbsent(rs.getLong("id"), new User()
                                .withId(rs.getLong("id"))
                                .withUserName(rs.getString("user_name"))
                                .withLogin(rs.getString("login"))
                                .withRole(Role.valueOf(rs.getString("status"))));
                        userMap.computeIfPresent(rs.getLong("id"), (id, user) ->
                                user.withThemes(new ArrayList<>(themeMap.values())));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(userMap.values());
    }


    @Override
    public void addThemeToStudents(List<Long> studentsId, User auth, String themeName) {
        if (studentsId.size() > 0) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADD_THEME_TO_STUDENTS)) {
                for (int i = 0; i < studentsId.size(); i++) {
                    preparedStatement.setLong(1, studentsId.get(i));
                    preparedStatement.setLong(2, auth.getGroupId());
                    preparedStatement.setString(3, themeName);
                    preparedStatement.executeUpdate();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addThemesToOneStudent(List<Theme> themes, long studentId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_THEME_TO_STUDENTS)) {
            for (int i = 0; i < themes.size(); i++) {
                preparedStatement.setLong(1, studentId);
                preparedStatement.setLong(2, themes.get(i).getIdGroup());
                preparedStatement.setString(3, themes.get(i).getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Theme> findAllTrainerTheme(User trainer) {
        Map<String, Theme> themesMap = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TRAINER_THEMES)) {
            preparedStatement.setLong(1, trainer.getGroupId());
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    themesMap.putIfAbsent(rs.getString("name_theme"), new Theme(rs.getLong("group_id"), rs.getString("name_theme")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(themesMap.values());
    }

}