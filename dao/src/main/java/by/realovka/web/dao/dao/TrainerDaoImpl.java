package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary) {
        em.persist(trainerWithSalary);
    }

    @Override
    public List<TrainerWithSalary> getAllTrainerWithSalary() {
        return em.createQuery("from TrainerWithSalary", TrainerWithSalary.class).getResultList();
    }

    @Override
    public TrainerWithSalary getById(Long id) {
        return em.find(TrainerWithSalary.class, id);
    }

    @Override
    public void addSalaryToTrainer(Salary salary) {
        em.persist(salary);
    }

    @Override
    public List<Salary> getAverageSalary(Long id, Integer months) {
        return em.createQuery("from Salary where trainer_with_salary_id =: id", Salary.class)
                .setParameter("id", id)
                .setMaxResults(months)
                .getResultList();
    }

}
