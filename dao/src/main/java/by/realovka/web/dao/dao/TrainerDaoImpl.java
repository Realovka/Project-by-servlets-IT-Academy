package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class TrainerDaoImpl implements TrainerDao {

    private EntityManagerHelper helper = EntityManagerHelper.getInstance();

    private static volatile TrainerDaoImpl instance;

    private TrainerDaoImpl() {

    }

    public static TrainerDaoImpl getInstance() {
        if (instance == null) {
            synchronized (TrainerDaoImpl.class) {
                if (instance == null) {
                    instance = new TrainerDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary) {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.persist(trainerWithSalary);
        trx.commit();
        em.close();
    }

    @Override
    public List<TrainerWithSalary> getAllTrainerWithSalary() {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        List<TrainerWithSalary> trainers = em.createQuery("from TrainerWithSalary", TrainerWithSalary.class).getResultList();
        trx.commit();
        em.close();
        return trainers;
    }

    @Override
    public TrainerWithSalary getById(Long id) {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        TrainerWithSalary trainerWithSalary = em.find(TrainerWithSalary.class, id);
        trx.commit();
        em.close();
        return trainerWithSalary;
    }

    @Override
    public void addSalaryToTrainer(Salary salary) {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.persist(salary);
        trx.commit();
        em.close();
    }

    @Override
    public List<Salary> getAverageSalary(Long id, Integer months) {
        EntityManager em = helper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        List<Salary> salaries = em.createQuery("from Salary where trainer_with_salary_id =: id", Salary.class)
                .setParameter("id", id)
                .setMaxResults(months)
                .getResultList();
        trx.commit();
        em.close();
        return salaries;
    }

}
