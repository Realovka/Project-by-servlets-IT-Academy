package by.realovka.web.dao.repository;

import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.model.Trainer;

import java.util.*;

public class TrainerRepositoryImpl implements TrainerRepository {

    private List<TrainerDTO> result = new ArrayList<>();

    private Map<String, Trainer> map = new HashMap<>();

    private static TrainerRepositoryImpl instance;

    private TrainerRepositoryImpl() {

    }

    public static TrainerRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new TrainerRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void save(Trainer trainer) {
        map.put(trainer.getUserName(),trainer);
    }

    @Override
    public Map<String, Trainer> findAll() {
        return map;
    }

    @Override
    public List<TrainerDTO> getListTrainerDTO(){
        return result;
    }
}
