package aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: yichuan
 * @Date: 2020/6/8 10:59 上午
 * @Description:
 */
@Component
@Aspect
@Order(1)
@Slf4j
public class LoggingAspect {

    //在实现类中的每个方法执行前,后以及发生异常等信息打印出来,需要把日志信息抽取出来,写到对应的切面的类中,LoggingAspect.java中;

    @Pointcut("execution(public * aop.ArithmeticCalculator+.div(..))")
    private void logMethod() {
    }

    /**
     * 将类变成切面类的步骤
     * 1. 在类上使用@Component注解,将切面类加入到IOC容器中
     * 2. 在类上使用@Aspect 注解使之成为切面类
     */
    @Before("execution(* aop.*.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName:"+getMethodName(joinPoint));
        System.out.println("[前置通知] the method [" + methodName + "] begins with " + Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(value = "execution(* aop.*.*(..))", throwing = "e")
    public void afterThrowingMethod(JoinPoint joinPoint, NullPointerException e) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("methodName:"+getMethodName(joinPoint));
        System.out.println("[异常通知] the method [" + methodName + "] occurs exception: " + e);
    }

    private String getMethodName(JoinPoint jp) {
        String clazzName = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        return clazzName + "." + methodName;
    }

    //    @Around(value = "execution(* aop.*.*(..))")
    @Around("logMethod()")
    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
//        String methodName = jp.getSignature().getName();
//        Object result = null;
//        try {
//            System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
//            //执行目标方法
//            result = jp.proceed();
//            System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with " + result);
//        } catch (Throwable e) {
//            System.out.println("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
//        }
//
//        System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
//
        try {
            return jp.proceed();
        } finally {
            try {
                LogTag log = jp.getTarget().getClass().getAnnotation(LogTag.class);
                if (log == null || !log.ignored()) {
                    System.out.println(log.appName());
                }
            } catch (Throwable e) {
                log.error("catch error,e", e);
            }
        }

    }


}
