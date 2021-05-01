package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.TrainerWithSalary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerWithSalaryRepository extends JpaRepository<TrainerWithSalary, Long> {

    TrainerWithSalary findTrainerWithSalariesByName(String name);

    List<TrainerWithSalary> findAll();

    TrainerWithSalary findTrainerWithSalariesById(Long id);

}
