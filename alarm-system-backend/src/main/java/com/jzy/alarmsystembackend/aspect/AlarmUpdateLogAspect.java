package com.jzy.alarmsystembackend.aspect;

import cn.hutool.extra.spring.SpringUtil;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.mapper.log.AlarmUpdateLogMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.service.log.ILogImpl;
import com.jzy.alarmsystembackend.service.user.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Aspect
@Component
public class AlarmUpdateLogAspect {

    @Pointcut("@annotation(com.jzy.alarmsystembackend.annotations.Loggable) || " +
            " @annotation(com.jzy.alarmsystembackend.annotations.AlarmLog)")
    public void AlarmUpdateLogMethods() {
    }

    @AfterReturning(value = "AlarmUpdateLogMethods()", returning = "result")
    public void logAlarmUpdate(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Loggable loggable = AnnotatedElementUtils.getMergedAnnotation(signature.getMethod(), Loggable.class);
        ILogImpl lg = null;
        try {
            lg = SpringUtil.getBean(loggable.value());
            lg.log(signature.getMethod(), joinPoint.getArgs(), result, joinPoint.getTarget());
        } catch (Exception e) {
            log.error("日志切面错误 {}", e.getMessage(), e);
        }

    }
}
