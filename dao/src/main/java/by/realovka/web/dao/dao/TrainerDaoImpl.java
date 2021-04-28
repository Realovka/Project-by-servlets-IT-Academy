package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    private ThreadLocal<EntityManager> em = new ThreadLocal<>();
    private EntityManagerFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public EntityManager getEm() {
        if (em.get() == null) {
            em.set(factory.createEntityManager());
        }
        return em.get();
    }

    public void begin() {
        getEm().getTransaction().begin();
    }

    public void commit() {
        getEm().getTransaction().commit();
    }

    public void rollback() {
        getEm().getTransaction().rollback();
    }

    @Override
    public void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary) {
        begin();
        getEm().persist(trainerWithSalary);
        commit();
    }

    @Override
    public TrainerWithSalary getTrainerWithSalaryByName(String name) {
        begin();
        TrainerWithSalary trainerWithSalary = getEm().createQuery("from TrainerWithSalary where name =: name", TrainerWithSalary.class)
                .setParameter("name", name).getSingleResult();
        commit();
        return trainerWithSalary;
    }

    @Override
    public List<TrainerWithSalary> getAllTrainerWithSalary() {
        begin();
        List<TrainerWithSalary> trainer = getEm().createQuery("from TrainerWithSalary", TrainerWithSalary.class).getResultList();
        commit();
        return trainer;
    }

    @Override
    public TrainerWithSalary getById(Long id) {
        begin();
        TrainerWithSalary trainerWithSalary = getEm().find(TrainerWithSalary.class, id);
        commit();
        return trainerWithSalary;
    }

    @Override
    public void addSalaryToTrainer(Salary salary) {
        begin();
        getEm().persist(salary);
        commit();
    }

    @Override
    public List<Salary> getAverageSalary(Long id, Integer months) {
        begin();
        List<Salary> salaries = getEm().createQuery("from Salary where trainer_with_salary_id =: id", Salary.class)
                .setParameter("id", id)
                .setMaxResults(months)
                .getResultList();
        commit();
        return salaries;
    }

}
