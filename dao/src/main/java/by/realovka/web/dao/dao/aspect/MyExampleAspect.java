//package by.realovka.web.dao.dao.aspect;
//
//import by.realovka.web.dao.dao.EntityManagerHelper;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//
//@Component
//@AllArgsConstructor
//@Aspect
//public class MyExampleAspect {
//
//    private final EntityManagerHelper helper;
//
//    @SneakyThrows
//    @Around("@target(MyExample)")
//    public Object transaction(ProceedingJoinPoint jp) {
//        EntityManager em = helper.getEntityManager();
//        EntityTransaction trx = em.getTransaction();
//        trx.begin();
//        Object result = jp.proceed();
//        trx.commit();
//        em.close();
//        return result;
//    }
//}
