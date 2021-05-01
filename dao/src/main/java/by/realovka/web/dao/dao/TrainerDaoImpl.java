package by.realovka.web.dao.dao;

import by.realovka.web.dao.dao.aspect.JpaTransaction;
import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TrainerDaoImpl implements TrainerDao {

    EntityManagerHelper helper;


    @Override
    @JpaTransaction
    public void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary) {
        helper.getEntityManager().persist(trainerWithSalary);
    }

    @Override
    @JpaTransaction
    public List<TrainerWithSalary> getAllTrainerWithSalary() {
        return helper.getEntityManager().createQuery("from TrainerWithSalary", TrainerWithSalary.class).getResultList();
    }

    @Override
    @JpaTransaction
    public TrainerWithSalary getById(Long id) {
        return helper.getEntityManager().find(TrainerWithSalary.class, id);
    }

    @Override
    @JpaTransaction
    public void addSalaryToTrainer(Salary salary) {
        helper.getEntityManager().persist(salary);
    }

    @Override
    @JpaTransaction
    public List<Salary> getAverageSalary(Long id, Integer months) {
        return helper.getEntityManager().createQuery("from Salary where trainer_with_salary_id =: id", Salary.class)
                .setParameter("id", id)
                .setMaxResults(months)
                .getResultList();
    }

}
