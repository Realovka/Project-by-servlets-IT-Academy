package by.realovka.web.service.service;

import by.realovka.web.dao.dao.TrainerDao;
import by.realovka.web.dao.dao.TrainerDaoImpl;
import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.model.Trainer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TrainerServiceImpl implements TrainerService {

    private static volatile TrainerServiceImpl instance;
    private final TrainerDao trainerDao = TrainerDaoImpl.getInstance();

    private TrainerServiceImpl() {
    }

    public static TrainerServiceImpl getInstance() {
        if (instance == null) {
            synchronized (TrainerDaoImpl.class) {
                if (instance == null) {
                    instance = new TrainerServiceImpl();
                }
            }
        }
        return instance;
    }


    @Override
    public boolean addTrainer(String name) {
        if (trainerDao.addTrainer(name) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        List<TrainerDTO> trainerDTO = new ArrayList<>();
        List<Trainer> trainers = trainerDao.findAllTrainers();
        for (Trainer item : trainers) {
            trainerDTO.add(new TrainerDTO()
                    .withId(item.getId())
                    .withName(item.getName()));
        }
        return trainerDTO;
    }

    @Override
    public void addNewSalaryToTrainer(String trainerId, String salary) {
        long trainerIdPars = Long.parseLong(trainerId);
        BigDecimal salaryPars = BigDecimal.valueOf(Double.parseDouble(salary));
        trainerDao.addNewSalaryToTrainer(trainerIdPars, salaryPars);
    }

    @Override
    public TrainerDTO getAverageSalary(String trainerId, String months) {
        long trainerIdPars = Long.parseLong(trainerId);
        int monthsPars = Integer.parseInt(months);
        Trainer trainer = trainerDao.getTrainerSalaries(trainerIdPars, monthsPars);
        List<BigDecimal> salary = trainer.getSalary();
        BigDecimal sumSalary = new BigDecimal(0);
        for (BigDecimal item : salary) {
            sumSalary = sumSalary.add(item);
        }
        BigDecimal averageSalary = sumSalary.divide(new BigDecimal(salary.size()));
        return new TrainerDTO()
                .withName(trainer.getName())
                .withAverageSalary(averageSalary);
    }

}
