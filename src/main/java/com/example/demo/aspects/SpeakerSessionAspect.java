package com.example.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class SpeakerSessionAspect {

    @Around("@annotation(com.example.demo.aspects.Log)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();
        log.info("AROUND: {} called with id: {}", methodName, methodArgs[0]);

        Object res = joinPoint.proceed();
        log.info("AROUND: {} returned {}", methodName, res);
        return res;
    }

}
