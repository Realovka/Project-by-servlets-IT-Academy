package by.realovka.web.dao.dao;

import by.realovka.web.dao.model.Group;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

//    private static volatile UserDaoImpl instance;
//
    private EntityManagerAndEntityTransactionHelper emet = EntityManagerAndEntityTransactionHelper.getInstance();

//    private UserDaoImpl() {
//
//    }
////
//    public static UserDaoImpl getInstance() {
//        if (instance == null) {
//            synchronized (UserDaoImpl.class) {
//                if (instance == null) {
//                    instance = new UserDaoImpl();
//                }
//            }
//        }
//        return instance;
//    }


    @Override
    public User findByLogin(String login) {
        User user;
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        try {
            EntityManager em = (EntityManager) objects.get(0);
            user = em.createQuery("from User where login=: login ", User.class).setParameter("login", login).getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
        emet.closeEntityManager(objects);
        return user;
    }


    @Override
    public void save(User user) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.persist(user);
        emet.closeEntityManager(objects);
    }


    @Override
    public User identificationUser(String loginAndPassword) {
        User user;
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        try {
            EntityManager em = (EntityManager) objects.get(0);
            user = em.createQuery("from User where login_and_password =: loginAndPassword", User.class)
                    .setParameter("loginAndPassword", loginAndPassword)
                    .getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }
        emet.closeEntityManager(objects);
        return user;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        List<Student> allStudents = em.createQuery("from Student", Student.class).getResultList();
        emet.closeEntityManager(objects);
        return allStudents;
    }

    @Override
    public Trainer addGroupToTrainer(Trainer trainer) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.merge(trainer);
        trainer = em.find(Trainer.class, trainer.getId());
        emet.closeEntityManager(objects);
        return trainer;
    }


    @Override
    public User findById(Long id) {
        User user;
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        user = em.find(User.class, id);
        emet.closeEntityManager(objects);
        return user;
    }


    @Override
    public void addStudentToGroup(Trainer trainer, Student student) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.merge(trainer);
        em.merge(student);
        Group group = em.find(Group.class, trainer.getGroup().getId());
        group.setStudents(trainer.getGroup().getStudents());
        em.merge(group);
        emet.closeEntityManager(objects);
    }


    @Override
    public void addThemeToGroup(List<Theme> themes) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        for(Theme item : themes) {
            em.persist(item);
        }
        emet.closeEntityManager(objects);
    }


    @Override
    public Trainer addOrUpdateOrDeleteMarkToStudent(Long id, int mark, Trainer trainer) {
        List<Object> objects = emet.getEntityManagerAndEntityTransaction();
        EntityManager em = (EntityManager) objects.get(0);
        em.createQuery("update Theme t set t.mark=:mark where t.id=:id ").setParameter("mark", mark).setParameter("id", id).executeUpdate();
        trainer = em.find(Trainer.class, trainer.getId());
        emet.closeEntityManager(objects);
        return trainer;
    }

}