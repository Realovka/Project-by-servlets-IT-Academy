package by.realovka.web.service.service;

import by.realovka.web.dao.dao.TrainerDao;
import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainerServiceImpl implements TrainerService {

//    private static volatile TrainerServiceImpl instance;
    private final TrainerDao trainerDao;

    @Autowired
    public TrainerServiceImpl(TrainerDao trainerDao) {
        this.trainerDao = trainerDao;
    }

    //    private TrainerServiceImpl() {
//    }
//
//    public static TrainerServiceImpl getInstance() {
//        if (instance == null) {
//            synchronized (TrainerDaoImpl.class) {
//                if (instance == null) {
//                    instance = new TrainerServiceImpl();
//                }
//            }
//        }
//        return instance;
//    }

    @Override
    public void addTrainer(String name) {
        TrainerWithSalary trainerWithSalary = TrainerWithSalary.builder()
                .name(name)
                .build();
        trainerDao.saveTrainerWithSalary(trainerWithSalary);
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        List<TrainerDto> trainerDTO = new ArrayList<>();
        List<TrainerWithSalary> trainers = trainerDao.getAllTrainerWithSalary();
        for (TrainerWithSalary item : trainers) {
            trainerDTO.add(TrainerDto.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .build());
        }
        return trainerDTO;
    }

    @Override
    public void addNewSalaryToTrainer(Long trainerId, String salary) {
        BigDecimal salaryPars = BigDecimal.valueOf(Double.parseDouble(salary));
        TrainerWithSalary trainerWithSalary = trainerDao.getById(trainerId);
        Salary newSalary = Salary.builder()
                .value(salaryPars)
                .trainerWithSalary(trainerWithSalary)
                .build();
        trainerDao.addSalaryToTrainer(newSalary);
    }

    @Override
    public TrainerDto getAverageSalary(Long trainerId, String months) {
        Integer monthsPars = Integer.parseInt(months);
        List<Salary> salaries = trainerDao.getAverageSalary(trainerId, monthsPars);
        BigDecimal sum = new BigDecimal(0);
        for(Salary item : salaries) {
            sum = sum.add(item.getValue());
        }
        BigDecimal averageSalary = sum.divide(BigDecimal.valueOf(salaries.size()));
        TrainerWithSalary trainer = trainerDao.getById(trainerId);
        TrainerDto trainerDTO = TrainerDto.builder()
                .name(trainer.getName())
                .averageSalary(averageSalary)
                .build();
        return trainerDTO;
    }

}
