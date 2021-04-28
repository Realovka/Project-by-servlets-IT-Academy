package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;

import java.util.List;

public interface TrainerDao {

    void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary);

    TrainerWithSalary getTrainerWithSalaryByName(String name);

    List<TrainerWithSalary> getAllTrainerWithSalary();

    TrainerWithSalary getById (Long id);

    void addSalaryToTrainer(Salary salary);

    List<Salary> getAverageSalary(Long id, Integer months);

}
