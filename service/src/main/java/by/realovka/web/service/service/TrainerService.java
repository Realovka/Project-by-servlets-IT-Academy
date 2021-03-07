package by.realovka.web.service.service;


import by.realovka.web.dao.dto.TrainerDTO;

import java.util.List;

public interface TrainerService {

    void addTrainer (String name);

    List<TrainerDTO> getAllTrainers();

    void addNewSalaryToTrainer(String trainerId, String salary);

    TrainerDTO getAverageSalary(String trainerId, String months);

}
