package by.realovka.web.service.service;

import by.realovka.web.dao.dto.TrainerDto;
import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import by.realovka.web.dao.repository.SalaryPagingRepository;
import by.realovka.web.dao.repository.SalaryRepository;
import by.realovka.web.dao.repository.TrainerWithSalaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TrainerServiceImpl implements TrainerService {

    private TrainerWithSalaryRepository trainerWithSalaryRepository;
    private SalaryRepository salaryRepository;
    private SalaryPagingRepository repository;

    @Override
    public boolean addTrainer(String name) {
        TrainerWithSalary trainerWithSalary = TrainerWithSalary.builder()
                .name(name)
                .build();
        if (trainerWithSalaryRepository.findTrainerWithSalariesByName(name) != null) {
            trainerWithSalaryRepository.save(trainerWithSalary);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        List<TrainerDto> trainerDTO = new ArrayList<>();
        List<TrainerWithSalary> trainers = trainerWithSalaryRepository.findAll();
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
        TrainerWithSalary trainerWithSalary = trainerWithSalaryRepository.findTrainerWithSalariesById(trainerId);
        Salary newSalary = Salary.builder()
                .value(salaryPars)
                .trainerWithSalary(trainerWithSalary)
                .build();
        salaryRepository.save(newSalary);
    }

    @Override
    public TrainerDto getAverageSalary(Long trainerId, String months) {
        Integer monthsPars = Integer.parseInt(months);
        Page<Salary> salaries = repository.findByTrainerWithSalaryId(trainerId, PageRequest.of(0, monthsPars, Sort.Direction.DESC, "id"));
        BigDecimal sum = new BigDecimal(0);
        for (Salary item : salaries) {
            sum = sum.add(item.getValue());
        }
        BigDecimal averageSalary = sum.divide(BigDecimal.valueOf(monthsPars));
        TrainerWithSalary trainer = trainerWithSalaryRepository.findTrainerWithSalariesById(trainerId);
        TrainerDto trainerDTO = TrainerDto.builder()
                .name(trainer.getName())
                .averageSalary(averageSalary)
                .build();
        return trainerDTO;
    }

}
