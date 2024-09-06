package com.jzy.alarmsystembackend.controller.log.user;

import com.alibaba.fastjson.JSON;
import com.jzy.alarmsystembackend.service.log.ILogImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
@Slf4j
public class LoginLogImpl implements ILogImpl {
    @Override
    public void log(Method method, Object[] args, Object result, Object target) {
        log.info("user logined:{} , args:{}", result, JSON.toJSONString(args));
    }
}
