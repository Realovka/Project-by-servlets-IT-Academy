package by.realovka.web.dao.dao;//package by.realovka.web.dao.dao;
//
//import by.realovka.web.dao.connection.DataSource;
//import by.realovka.web.dao.model.Trainer;
//
//import java.math.BigDecimal;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TrainerDaoImpl implements TrainerDao {
//
//    private static final String ADD_TRAINER = "INSERT INTO trainers VALUES (default , ?)";
//    private static final String FIND_ALL_TRAINERS = "SELECT * FROM trainers";
//    private static final String ADD_NEW_SALARY_TO_TRAINER = "INSERT INTO salaries VALUES (?,?)";
//    private static final String FIND_ALL_SALARIES_TRAINER = "SELECT t.trainer_id, t.trainer_name, s.salary " +
//            "  FROM trainers t join salaries s on t.trainer_id=s.trainer_id WHERE s.trainer_id = ? limit (?)";
//
//    private final DataSource dataSource = DataSource.getInstance();
//
//    private static volatile TrainerDaoImpl instance;
//
//    private TrainerDaoImpl() {
//
//    }
//
//    public static TrainerDaoImpl getInstance() {
//        if (instance == null) {
//            synchronized (TrainerDaoImpl.class) {
//                if (instance == null) {
//                    instance = new TrainerDaoImpl();
//                }
//            }
//        }
//        return instance;
//    }
//
//
//    @Override
//    public int addTrainer(String name) {
//        int result = 0;
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRAINER)) {
//            preparedStatement.setString(1, name);
//            result = preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public List<Trainer> findAllTrainers() {
//        List<Trainer> trainers = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TRAINERS)) {
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    trainers.add(new Trainer()
//                            .withId(resultSet.getLong("trainer_id"))
//                            .withName(resultSet.getString("trainer_name")));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return trainers;
//    }
//
//    @Override
//    public void addNewSalaryToTrainer(long trainerId, BigDecimal salary) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_SALARY_TO_TRAINER)) {
//            preparedStatement.setLong(1, trainerId);
//            preparedStatement.setBigDecimal(2, salary);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Trainer getTrainerSalaries(long trainerId, int monthsNumber) {
//        Trainer trainer = new Trainer();
//        List<BigDecimal> salaries = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SALARIES_TRAINER)) {
//            preparedStatement.setLong(1, trainerId);
//            preparedStatement.setInt(2, monthsNumber);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                while (resultSet.next()) {
//                    salaries.add(resultSet.getBigDecimal("salary"));
//                    trainer.setId(resultSet.getLong("trainer_id"));
//                    trainer.setName(resultSet.getString("trainer_name"));
//                    trainer.setSalary(salaries);
//
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return trainer;
//    }
//}
