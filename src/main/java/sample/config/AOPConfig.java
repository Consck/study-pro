package sample.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-06-22 10:14
 */
@Configuration
@Aspect
public class AOPConfig {

    /**
     * 对所有使用Controller注解的方法进行拦截执行
     * @param point
     * @return
     * @throws Throwable
     */
//    @Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        /**
         * 此处调用方法前执行逻辑
         */
        System.out.println("Controller执行方法前，args:" + Arrays.toString(args));
        // 调用原有方法
        Object o = point.proceed();
        /**
         * 此处调用方法后执行逻辑
         */
        System.out.println("Controller执行方法后，o:" + o);
        return o;
    }

    /**
     * 对所有使用Service注解的方法进行拦截执行
     * @param
     * @return
     * @throws Throwable
     */
//    @Before("@within(org.springframework.stereotype.Service)")
    public void simpleAdviceBefore() throws Throwable {

        /**
         * 此处调用方法前执行逻辑
         */
        System.out.println("Service执行方法前");
    }

//    @After("@within(org.springframework.stereotype.Service)")
    public void simpleAdviceAfter() throws Throwable {

        /**
         * 此处调用方法后执行逻辑
         */
        System.out.println("Service执行方法后");
    }
}
