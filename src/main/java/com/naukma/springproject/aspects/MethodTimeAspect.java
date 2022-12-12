package com.naukma.springproject.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodTimeAspect {
    Logger logger = LogManager.getLogger("FileLogger");

    @Around("@annotation(com.naukma.springproject.aspects.LogExeTime)")
    public Object logExeTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();
        double exeTime = (endTime - startTime) / 1000000.0;
        String message = "Class name: " + joinPoint.getSignature().getDeclaringTypeName() + " | "
                + "Method name: " + joinPoint.getSignature().getName() + " | "
                + "Execution time = " + exeTime + "ms";
        logger.info(MarkerManager.getMarker("IMPORTANT"), message);
        return result;
    }
}
