//package by.realovka.web.service;
//
//import by.realovka.web.dao.dto.TrainerDTO;
//import by.realovka.web.dao.model.Trainer;
//import by.realovka.web.dao.repository.TrainerRepository;
//import by.realovka.web.service.exception.NoSuchTrainerException;
//import by.realovka.web.service.exception.NumberOfMonthsIsInvalidException;
//import by.realovka.web.service.exception.SuchTrainerIsAlreadyExistException;
//
//import java.util.*;
//
//
//public class TrainerServiceImpl implements TrainerService {
//
//    private static TrainerServiceImpl instance;
//
//    private TrainerRepository trainerRepository;
//
//    private TrainerServiceImpl(TrainerRepository trainerRepository) {
//        this.trainerRepository = trainerRepository;
//    }
//
//    public static TrainerServiceImpl getInstance(TrainerRepository trainerRepository) {
//        if (instance == null) {
//            instance = new TrainerServiceImpl(trainerRepository);
//        }
//        return instance;
//    }
//
//    @Override
//    public void saveTrainer(Trainer trainer) {
//        Map<String, Trainer> trainers = trainerRepository.findAll();
//        if (trainers.containsKey(trainer.getUserName())) {
//            throw new SuchTrainerIsAlreadyExistException("Such trainer is already exist!");
//        } else {
//            trainerRepository.save(trainer);
//        }
//    }
//
//    @Override
//    public Map<String, Trainer> getAllTrainers() {
//        return trainerRepository.findAll();
//    }
//
//    @Override
//    public TrainerDTO getAverageSalary(String trainerName, Integer monthsNumber) {
//        Map<String, Trainer> map = trainerRepository.findAll();
//        double averageSalary = 0;
//        if (!map.containsKey(trainerName)) {
//            throw new NoSuchTrainerException("No such trainer!");
//        } else {
//            Trainer trainer = map.get(trainerName);
//            List<Double> salaries = trainer.getSalary();
//            if (salaries.size() < monthsNumber) {
//                throw new NumberOfMonthsIsInvalidException("Trainer didn't work so many months");
//            } else {
//                double allSalary = 0;
//                for (int i = 0; i < monthsNumber; i++) {
//                    allSalary += salaries.get(i);
//                }
//                averageSalary = allSalary / monthsNumber;
//            }
//        }
//        return new TrainerDTO(trainerName, averageSalary, monthsNumber);
//    }
//
//    @Override
//    public boolean existTrainer(String trainerName) {
//        Map<String, Trainer> map = trainerRepository.findAll();
//        if (map.containsKey(trainerName)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public void addSalaryByOneMonth(String trainerName, Double salary) {
//        Map<String, Trainer> map = trainerRepository.findAll();
//        Trainer trainer = map.get(trainerName);
//        trainer.getSalary().add(salary);
//    }
//}
