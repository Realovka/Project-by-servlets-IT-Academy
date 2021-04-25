package by.realovka.web.dao.dao;

//import by.realovka.web.dao.dao.aspect.JpaTransaction;
import by.realovka.web.dao.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@AllArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    public EntityManager em;

    @Override
//    @JpaTransaction
    public User findByLogin(String login) {
        User user = null;
        try {
            user = em.createQuery("from User where login=: login ", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = new User();
        }
        return user;
    }

    @Override
//    @JpaTransaction
    public void save(User user) {
        em.persist(user);
    }

    @Override
//    @JpaTransaction
    public User identificationUser(String loginAndPassword) {
        User user = null;
        try {
         user = em.createQuery("from User where login_and_password =: loginAndPassword", User.class)
                    .setParameter("loginAndPassword", loginAndPassword)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = new User();
        }
        return user;
    }

    @Override
//    @JpaTransaction
    public List<Student> getAllStudents() {
        return em.createQuery("from Student", Student.class).getResultList();
    }

    @Override
//    @JpaTransaction
    public Trainer addGroupToTrainer(Trainer trainer) {
        em.merge(trainer);
        return em.find(Trainer.class, trainer.getId());
    }

    @Override
//    @JpaTransaction
    public User findById(Long id) {
        return em.find(User.class, id);
    }


    @Override
//    @JpaTransaction
    public void addStudentToGroup(Trainer trainer, Student student) {
        em.merge(trainer);
        em.merge(student);
        Group group = em.find(Group.class, trainer.getGroup().getId());
        group.setStudents(trainer.getGroup().getStudents());
        em.merge(group);
    }

    @Override
//    @JpaTransaction
    public void addThemeToGroup(List<Theme> themes) {
        for (Theme item : themes) {
            em.persist(item);
        }
    }

    @Override
//    @JpaTransaction
    public Trainer update(Long id, int mark, Trainer trainer) {
        em.createQuery("update Theme t set t.mark=:mark where t.id=:id ")
                .setParameter("mark", mark)
                .setParameter("id", id)
                .executeUpdate();
        trainer = em.find(Trainer.class, trainer.getId());
        return trainer;
    }

}