package by.realovka.web.service;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.model.Trainer;

import java.util.List;
import java.util.Map;

public interface TrainerService {

    void saveTrainer(Trainer trainer);
    Map<String, Trainer> getAllTrainers();
    TrainerDTO getAverageSalary(String trainerName, Integer monthsNumber);
    boolean existTrainer(String trainerName);
    void addSalaryByOneMonth(String trainerName, Double salary);
}
