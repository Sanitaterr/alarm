package com.jzy.alarmsystembackend.service.log;

import java.lang.reflect.Method;

public interface ILogImpl {

    public void log(Method method, Object[] args, Object result, Object target);
}
