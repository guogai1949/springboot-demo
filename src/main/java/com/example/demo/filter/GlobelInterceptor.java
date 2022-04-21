package com.example.demo.filter;

import com.example.demo.common.anotations.TestAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobelInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(GlobelInterceptor.class);

    @Pointcut("execution(* com.example.demo.controller.*Controller.*(..))")
    public void controller(){

    }

    @Pointcut("@annotation(com.example.demo.common.anotations.TestAnnotation)")
    public void pointcut() {

    }

    @Pointcut("@annotation(com.example.demo.common.anotations.TestAnnotation1)")
    public void pointcut1() {

    }

    @Around("controller()")
    public Object processController(ProceedingJoinPoint poj) throws Throwable {
        Signature signature = poj.getSignature();
        logger.info("切面：{}",signature);
        return poj.proceed();
    }

    @Before("pointcut() && @annotation(testAnnotation)")
    public void pointcutBefore(JoinPoint jp, TestAnnotation testAnnotation) throws Throwable {
        String[] args = testAnnotation.args();
        for(String arg : args) {
            logger.info(arg);
        }
    }

    @After("pointcut1()")
    public void pointcutAfter(JoinPoint jp) throws Throwable {
        logger.info("切面在方法之后");
    }
}
