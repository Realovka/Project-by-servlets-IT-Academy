package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

    List<Salary> findAll();

//    @Query(value = "SELECT t.id, t.name, s.value FROM trainer_with_salary_hb2 t join salary_hb2 s on t.id=s.trainer_with_salary_id WHERE s.trainer_with_salary_id = ?1 limit (?2)", nativeQuery = true)
//    List<Salary> findSalaries(Long trainerId, Integer months);
}
