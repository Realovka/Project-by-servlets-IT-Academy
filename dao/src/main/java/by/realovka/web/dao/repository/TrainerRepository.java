package by.realovka.web.dao.repository;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.model.Trainer;

import java.util.List;
import java.util.Map;


public interface TrainerRepository {

    void save(Trainer trainer);
    Map<String, Trainer> findAll();
    List<TrainerDTO> getListTrainerDTO();
}
