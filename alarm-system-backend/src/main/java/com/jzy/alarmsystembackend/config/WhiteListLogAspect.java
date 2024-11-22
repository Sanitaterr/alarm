package com.jzy.alarmsystembackend.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class WhiteListLogAspect {

    @Autowired
    private LoggingProperties loggingProperties;

    private AntPathMatcher pathMatcher = new AntPathMatcher();

//    @Around("execution(* com.jzy.alarmsystembackend.controller..*(..))")
//    public Object doBefore(ProceedingJoinPoint pjp) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String requestURI = request.getRequestURI();
//        long beg = System.currentTimeMillis();
//        Object result= pjp.proceed();
//
//        if (shouldLog(requestURI)) {
//            addLog((MethodSignature) pjp.getSignature(),pjp.getArgs(),result,System.currentTimeMillis()-beg);
//        }
//
//        return result;
//    }

    @Around("execution(* com.jzy.alarmsystembackend.controller..*(..))")
    public Object doBefore(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            log.error("No request attributes found for current thread");
            return pjp.proceed(); // 或者抛出异常
        }

        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        long beg = System.currentTimeMillis();
        Object result = pjp.proceed();

        if (shouldLog(requestURI)) {
            addLog((MethodSignature) pjp.getSignature(), pjp.getArgs(), result, System.currentTimeMillis() - beg);
        }

        return result;
    }


    /**
     * 日志记录入库操作
     */
    public void addLog(MethodSignature method,Object[] args, Object outParams, long time) {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        log.info("\n\r=======================================\n\r" +
                        "请求地址:{} \n\r" +
                        "请求方式:{} \n\r" +
                        "请求类方法:{} \n\r" +
                        "请求方法参数:{} \n\r" +
                        "返回报文:{} \n\r" +
                        "处理耗时:{} ms \n\r" +
                        "=======================================\n\r",
                request.getRequestURI(),
                request.getMethod(),
                method,
                JSONUtil.toJsonStr(filterArgs(args)),
                JSONUtil.toJsonStr(outParams) ,
                time
        );
    }

    /**
     * 过滤参数
     * @param args
     * @return
     */
    private List<Object> filterArgs(Object[] args) {
        return Arrays.stream(args).filter(object -> !(object instanceof MultipartFilter)
                && !(object instanceof HttpServletRequest)
                && !(object instanceof HttpServletResponse)
        ).collect(Collectors.toList());
    }

    private boolean shouldLog(String requestURI) {
        return loggingProperties.getIncludePaths().stream().anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }
}

