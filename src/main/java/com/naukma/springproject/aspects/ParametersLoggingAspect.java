package com.naukma.springproject.aspects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ParametersLoggingAspect {

    Logger logger = LogManager.getLogger("FileLogger");

    @Before("@annotation(com.naukma.springproject.aspects.ParametersLogging)")
    public void parametersLog(JoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        StringBuilder logMessage = new StringBuilder("Method - " + joinPoint.getSignature().getName() + ", arguments:");
        for(Object o: arguments){
            logMessage.append(" ").append(o.toString());
        }
        logger.info(MarkerManager.getMarker("IMPORTANT"), logMessage.toString());
    }

    @AfterReturning(value = "@annotation(com.naukma.springproject.aspects.ParametersLogging)", returning = "returnValue")
    public void valueLog(JoinPoint joinPoint, Object returnValue){
        String logMessage = "Method return value: " + returnValue.toString();
        logger.info(MarkerManager.getMarker("IMPORTANT"), logMessage);
    }
}
