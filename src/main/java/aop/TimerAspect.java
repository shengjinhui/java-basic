package aop;

import TimeUtil.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.PipedReader;

/**
 * @Author: yichuan
 * @Date: 2020/11/10 6:52 下午
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class TimerAspect {

    @Pointcut("execution(public * *(..))")
    private void timerMethod() {
    }


    @Around("timerMethod()")
    public Object countMethodExecuteTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        try {
            return joinPoint.proceed();
        } finally {
            Long executeTime = System.currentTimeMillis() - start;
            log.info(">>>>>>>>>>method:{} execute time:{}s<<<<<<<<<<<<", methodName, TimeUtil.formatMs2Second(executeTime));
        }
    }


}
