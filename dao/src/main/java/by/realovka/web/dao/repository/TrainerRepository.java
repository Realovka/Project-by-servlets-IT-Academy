package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {

    Trainer findTrainerById(Long id);
}
