package by.realovka.web.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class RequestLoggingAspect {

    @Pointcut("execution(* by.realovka.web.app.controller.*.*(..))")
    public void controllers() {

    }

    @Before("controllers()")
    public void before(JoinPoint joinPoint) {
        log.info("It's before: {}", joinPoint.getSignature().getName());
        log.info("-------------");
    }
}
