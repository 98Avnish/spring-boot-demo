package com.example.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Aspect
@Slf4j
@Service
public class SessionLoggingAspect {

    @Before("execution(* *.getSession(..))")
    public void before() {
        log.info("BEFORE: GetSession method called.");
    }

    @After("execution(* *.getSession(..))")
    public void after() {
        log.info("AFTER: GetSession method executed.");
    }

    @Around("execution(* *.getSession(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        log.info("AROUND: {} called with id: {}", methodName, methodArgs[0]);

        Object res = joinPoint.proceed();
        log.info("AROUND: {} returned {}", methodName, res);
        return res;
    }
}
