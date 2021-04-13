package by.realovka.web.dao.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Component
public class EntityManagerAndEntityTransactionHelper {

    private static volatile EntityManagerAndEntityTransactionHelper instance;

    private static EntityManagerHelper entityManagerHelper = EntityManagerHelper.getInstance();

    private EntityManagerAndEntityTransactionHelper() {
    }

    public static EntityManagerAndEntityTransactionHelper getInstance() {
        if (instance == null) {
            synchronized (EntityManagerAndEntityTransactionHelper.class) {
                if (instance == null) {
                    instance = new EntityManagerAndEntityTransactionHelper();
                }
            }
        }
        return instance;
    }

    public List<Object> getEntityManagerAndEntityTransaction() {
        EntityManager em = entityManagerHelper.getEntityManager();
        EntityTransaction trx = em.getTransaction();
        trx.begin();
        return List.of(em, trx);
    }

    public void closeEntityManager(List<Object> objects) {
        EntityTransaction trx = (EntityTransaction) objects.get(1);
        trx.commit();
        EntityManager em = (EntityManager) objects.get(0);
        em.close();
    }
}
