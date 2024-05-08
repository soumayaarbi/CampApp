package com.example.campapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before("execution( * com.example.campapp.services.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Ietn mhod : " + name + " : ");
    }

    @AfterReturning("execution( * com.example.campapp.services.*.*(..))")
    public void logMethodExitWithoutError(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Out of method without errors : " + name );
    }

    @AfterThrowing("execution(* com.example.campapp.services.*.*(..))")
    public void logMethodExitWithError(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.error("Out of method with erros : " + name );
    }

    @After("execution(* com.example.campapp.services.*.*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("Out of method : " + name );
    }
}
