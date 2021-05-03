package by.realovka.web.service.service;


import by.realovka.web.dao.dto.TrainerDto;

import java.util.List;

public interface TrainerService {

    boolean addTrainer (String name);

    List<TrainerDto> getAllTrainers();

    void addNewSalaryToTrainer(Long trainerId, Double salary);

    TrainerDto getAverageSalary(Long trainerId, Integer months);

}
