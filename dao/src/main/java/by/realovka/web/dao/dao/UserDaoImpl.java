package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import java.util.List;


public class UserDaoImpl implements UserDao {

    private static volatile UserDaoImpl instance;

    private EntityManagerHelper entityManagerHelper = EntityManagerHelper.getInstance();

    private UserDaoImpl() {

    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            synchronized (UserDaoImpl.class) {
                if (instance == null) {
                    instance = new UserDaoImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User findByLogin(String login) {
        User user;
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        try {
            user = em.createQuery("from User where login=: login ", User.class).setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
        trx.commit();
        em.close();
        return user;
    }


    @Override
    public void save(User user) {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.persist(user);
        trx.commit();
        em.close();
    }


    @Override
    public User identificationUser(String loginAndPassword) {
        User user;
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        try {
            user = entityManagerHelper.getEntityManager().createQuery("from User where login_and_password =: loginAndPassword", User.class).setParameter("loginAndPassword", loginAndPassword).getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
        trx.commit();
        em.close();
        return user;
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        List<Student> allStudents = em.createQuery("from Student", Student.class).getResultList();
        trx.commit();
        em.close();
        return allStudents;
    }


    @Override
    public Trainer addGroupToTrainer(Trainer trainer, Group group) {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.merge(trainer);
        trainer = em.find(Trainer.class, trainer.getId());
        trx.commit();
        em.close();
        return trainer;
    }

    @Override
    public User findById(Long id) {
        User user;
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        user = em.find(User.class, id);
        trx.commit();
        em.close();
        return user;
    }



    @Override
    public void addStudentToGroup(Trainer trainer, Student student) {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.merge(trainer);
        em.merge(student);
        Group group = em.find(Group.class, trainer.getGroup().getId());
        group.setStudents(trainer.getGroup().getStudents());
        em.merge(group);
        trx.commit();
        em.close();
    }


    @Override
    public void addThemeToGroup(List<Theme> themes) {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        for (int i = 0; i < themes.size(); i++) {
            em.persist(themes.get(i));
        }
        trx.commit();
        em.close();
    }


    @Override
    public Trainer addOrUpdateOrDeleteMarkToStudent(Long id, int mark, Trainer trainer) {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        em.createQuery("update Theme t set t.mark=:mark where t.id=:id ").setParameter("mark", mark).setParameter("id", id).executeUpdate();
        trainer = em.find(Trainer.class, trainer.getId());
        trx.commit();
        em.close();
        return trainer;
    }

}