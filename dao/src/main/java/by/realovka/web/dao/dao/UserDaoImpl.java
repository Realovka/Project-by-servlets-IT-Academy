package by.realovka.web.dao.dao;


import by.realovka.web.dao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

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
    public User findByLogin(String login) {
        User user = null;
        begin();
        try {
            user = getEm().createQuery("from User where login=: login ", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = new User();
        }
        commit();
        return user;
    }

    @Override
    public void save(User user) {
        begin();
        getEm().persist(user);
        commit();
    }

    @Override
    public User identificationUser(String loginAndPassword) {
        User user = null;
        begin();
        try {
            user = getEm().createQuery("from User where login_and_password =: loginAndPassword", User.class)
                    .setParameter("loginAndPassword", loginAndPassword)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = new User();
        }
        commit();
        return user;
    }

    @Override
    public List<Student> getAllStudents() {
        begin();
        List<Student> students = getEm().createQuery("from Student", Student.class).getResultList();
        commit();
        return students;
    }

    @Override
    public Trainer addGroupToTrainer(Trainer trainer) {
        begin();
        getEm().merge(trainer);
        trainer = getEm().find(Trainer.class, trainer.getId());
        commit();
        return trainer;
    }

    @Override
    public User findById(Long id) {
        begin();
        User user = getEm().find(User.class, id);
        commit();
        return user;
    }


    @Override
    public void addStudentToGroup(Trainer trainer, Student student) {
        begin();
        getEm().merge(trainer);
        getEm().merge(student);
        Group group = getEm().find(Group.class, trainer.getGroup().getId());
        group.setStudents(trainer.getGroup().getStudents());
        getEm().merge(group);
        commit();
    }

    @Override
    public void addThemeToGroup(List<Theme> themes) {
        begin();
        for (Theme item : themes) {
            getEm().persist(item);
        }
        commit();
    }

    @Override
    public Trainer addOrUpdateOrDeleteMark(Long id, int mark, Trainer trainer) {
        begin();
        getEm().createQuery("update Theme t set t.mark=:mark where t.id=:id ")
                .setParameter("mark", mark)
                .setParameter("id", id)
                .executeUpdate();
        trainer = getEm().find(Trainer.class, trainer.getId());
        commit();
        return trainer;
    }

}