package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAll();
    Student findStudentById(Long id);

}
