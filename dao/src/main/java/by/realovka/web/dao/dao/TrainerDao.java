package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Trainer;

import java.math.BigDecimal;
import java.util.List;

public interface TrainerDao {

    int addTrainer(String name);

    List<Trainer> findAllTrainers();

    void addNewSalaryToTrainer(long trainerId, BigDecimal salary);

    Trainer getTrainerSalaries(long trainerId, int monthsNumber);
}
