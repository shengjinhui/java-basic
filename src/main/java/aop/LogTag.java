package aop;

import java.lang.annotation.*;

/**
 * @Author: yichuan
 * @Date: 2020/6/8 3:58 下午
 * @Description:
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogTag {
    boolean ignored() default false;

    String appName() default "test";
}
