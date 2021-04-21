package by.realovka.web.dao.dao;

import by.realovka.web.dao.dao.aspect.JpaTransaction;
import by.realovka.web.dao.model.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    protected final EntityManagerHelper helper = EntityManagerHelper.getInstance();

    @Override
    @JpaTransaction
    public User findByLogin(String login) {
        return helper.getEntityManager().createQuery("from User where login=: login ", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    @Override
    @JpaTransaction
    public void save(User user) {
        helper.getEntityManager().persist(user);
    }

    @Override
    @JpaTransaction
    public User identificationUser(String loginAndPassword) {
        return helper.getEntityManager().createQuery("from User where login_and_password =: loginAndPassword", User.class)
                .setParameter("loginAndPassword", loginAndPassword)
                .getSingleResult();
    }

    @Override
    @JpaTransaction
    public List<Student> getAllStudents() {
        return helper.getEntityManager().createQuery("from Student", Student.class).getResultList();
    }

    @Override
    @JpaTransaction
    public Trainer addGroupToTrainer(Trainer trainer) {
        helper.getEntityManager().merge(trainer);
        return helper.getEntityManager().find(Trainer.class, trainer.getId());
    }

    @Override
    @JpaTransaction
    public User findById(Long id) {
        return helper.getEntityManager().find(User.class, id);
    }


    @Override
    @JpaTransaction
    public void addStudentToGroup(Trainer trainer, Student student) {
        helper.getEntityManager().merge(trainer);
        helper.getEntityManager().merge(student);
        Group group = helper.getEntityManager().find(Group.class, trainer.getGroup().getId());
        group.setStudents(trainer.getGroup().getStudents());
        helper.getEntityManager().merge(group);
    }

    @Override
    @JpaTransaction
    public void addThemeToGroup(List<Theme> themes) {

        for (Theme item : themes) {
            helper.getEntityManager().persist(item);
        }

    }

    @Override
    @JpaTransaction
    public Trainer addOrUpdateOrDeleteMarkToStudent(Long id, int mark, Trainer trainer) {
        helper.getEntityManager().createQuery("update Theme t set t.mark=:mark where t.id=:id ")
                .setParameter("mark", mark)
                .setParameter("id", id)
                .executeUpdate();
        return helper.getEntityManager().find(Trainer.class, trainer.getId());
    }

}