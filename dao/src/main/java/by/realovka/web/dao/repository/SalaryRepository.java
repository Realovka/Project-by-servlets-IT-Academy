package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findSalariesByTrainerWithSalaryId(Long trainerId);

}
