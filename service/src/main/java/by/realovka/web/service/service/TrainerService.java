package by.realovka.web.service.service;


import by.realovka.web.dao.dto.TrainerDto;

import java.util.List;

public interface TrainerService {

    void addTrainer (String name);

    List<TrainerDto> getAllTrainers();

    void addNewSalaryToTrainer(String trainerId, String salary);

    TrainerDto getAverageSalary(String trainerId, String months);

}
