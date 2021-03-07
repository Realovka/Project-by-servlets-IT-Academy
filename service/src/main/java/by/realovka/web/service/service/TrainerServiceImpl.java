package by.realovka.web.service.service;

import by.realovka.web.dao.dao.TrainerDao;
import by.realovka.web.dao.dao.TrainerDaoImpl;
import by.realovka.web.dao.dto.TrainerDTO;
import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;

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
    public void addTrainer(String name) {
        TrainerWithSalary trainerWithSalary = TrainerWithSalary.builder()
                .name(name)
                .build();
        trainerDao.saveTrainerWithSalary(trainerWithSalary);
    }

    @Override
    public List<TrainerDTO> getAllTrainers() {
        List<TrainerDTO> trainerDTO = new ArrayList<>();
        List<TrainerWithSalary> trainers = trainerDao.getAllTrainerWithSalary();
        for (TrainerWithSalary item : trainers) {
            trainerDTO.add(TrainerDTO.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build());
        }
        return trainerDTO;
    }

    @Override
    public void addNewSalaryToTrainer(String trainerId, String salary) {
        Long trainerIdPars = Long.parseLong(trainerId);
        BigDecimal salaryPars = BigDecimal.valueOf(Double.parseDouble(salary));
        TrainerWithSalary trainerWithSalary = trainerDao.getById(trainerIdPars);
        Salary newSalary = Salary.builder()
                .value(salaryPars)
                .trainerWithSalary(trainerWithSalary)
                .build();
        trainerDao.addSalaryToTrainer(newSalary);
    }

    @Override
    public TrainerDTO getAverageSalary(String trainerId, String months) {
        Long trainerIdPars = Long.parseLong(trainerId);
        Integer monthsPars = Integer.parseInt(months);
        List<Salary> salaries = trainerDao.getAverageSalary(trainerIdPars, monthsPars);
        BigDecimal sum = new BigDecimal(0);
        for(Salary item : salaries) {
            sum = sum.add(item.getValue());
        }
        BigDecimal averageSalary = sum.divide(BigDecimal.valueOf(salaries.size()));
        TrainerWithSalary trainer = trainerDao.getById(trainerIdPars);
        TrainerDTO trainerDTO = TrainerDTO.builder()
                .name(trainer.getName())
                .averageSalary(averageSalary)
                .build();
        return trainerDTO;
    }

}
