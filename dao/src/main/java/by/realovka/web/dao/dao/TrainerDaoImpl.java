package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Salary;
import by.realovka.web.dao.model.TrainerWithSalary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TrainerDaoImpl implements TrainerDao {

    private EntityManagerAndEntityTransactionHelper emet = EntityManagerAndEntityTransactionHelper.getInstance();

//    private static volatile TrainerDaoImpl instance;
//
//    private TrainerDaoImpl() {
//
//    }
//
//    public static TrainerDaoImpl getInstance() {
//        if (instance == null) {
//            synchronized (TrainerDaoImpl.class) {
//                if (instance == null) {
//                    instance = new TrainerDaoImpl();
//                }
//            }
//        }
//        return instance;
//    }

    @Override
    public void saveTrainerWithSalary(TrainerWithSalary trainerWithSalary) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.persist(trainerWithSalary);
        emet.closeEntityManager(objects);
    }

    @Override
    public List<TrainerWithSalary> getAllTrainerWithSalary() {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        List<TrainerWithSalary> trainers = em.createQuery("from TrainerWithSalary", TrainerWithSalary.class).getResultList();
        emet.closeEntityManager(objects);
        return trainers;
    }

    @Override
    public TrainerWithSalary getById(Long id) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        TrainerWithSalary trainerWithSalary = em.find(TrainerWithSalary.class, id);
        emet.closeEntityManager(objects);
        return trainerWithSalary;
    }

    @Override
    public void addSalaryToTrainer(Salary salary) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.persist(salary);
        emet.closeEntityManager(objects);
    }

    @Override
    public List<Salary> getAverageSalary(Long id, Integer months) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        List<Salary> salaries = em.createQuery("from Salary where trainer_with_salary_id =: id", Salary.class)
                .setParameter("id", id)
                .setMaxResults(months)
                .getResultList();
        emet.closeEntityManager(objects);
        return salaries;
    }

}
