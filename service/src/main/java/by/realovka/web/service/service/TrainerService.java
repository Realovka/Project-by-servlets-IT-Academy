package by.realovka.web.service.service;


import by.realovka.web.dao.dto.TrainerDto;

import java.util.List;

public interface TrainerService {

    boolean addTrainer (String name);

    List<TrainerDto> getAllTrainers();

    void addNewSalaryToTrainer(Long trainerId, String salary);

    TrainerDto getAverageSalary(Long trainerId, String months);

}
