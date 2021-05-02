package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Salary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SalaryPagingRepository extends PagingAndSortingRepository<Salary, Long> {

    Page<Salary> findByTrainerWithSalaryId(Long trainerId, Pageable pageable);
}
