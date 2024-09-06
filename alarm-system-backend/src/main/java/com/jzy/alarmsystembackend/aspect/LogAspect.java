package com.jzy.alarmsystembackend.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.service.log.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogAspect {

//    @Pointcut("@annotation(com.jzy.alarmsystembackend.annotations.Loggable) || " +
//            " @annotation(com.jzy.alarmsystembackend.annotations.AlarmLog)")
    @Pointcut("@annotation(com.jzy.alarmsystembackend.annotations.Loggable)")
    public void AlarmUpdateLogMethods() {
    }

    @AfterReturning(value = "AlarmUpdateLogMethods()", returning = "result")
    public void myLog(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Loggable loggable = AnnotatedElementUtils.getMergedAnnotation(signature.getMethod(), Loggable.class);
        MyLog lg = null;
        try {
            lg = SpringUtil.getBean(loggable.value());
            lg.log(signature.getMethod(), joinPoint.getArgs(), result, joinPoint.getTarget());
        } catch (Exception e) {
            log.error("日志切面错误 {}", e.getMessage(), e);
        }

    }
}
