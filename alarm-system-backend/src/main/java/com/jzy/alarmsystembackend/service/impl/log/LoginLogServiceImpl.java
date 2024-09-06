package com.jzy.alarmsystembackend.service.impl.log;

import com.alibaba.fastjson.JSON;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.mapper.log.LoginLogMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;
import com.jzy.alarmsystembackend.pojo.DO.log.LoginLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.service.log.LoginLogService;
import com.jzy.alarmsystembackend.service.log.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/9/6 16:41
 * @Description: TODO
 */
@Slf4j
@Service
public class LoginLogServiceImpl implements LoginLogService, MyLog {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public List<LoginLog> getAllLoginLog() {
        return loginLogMapper.selectList(null);
    }

    @Override
    public void log(Method method, Object[] args, Object result, Object target) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();

        LoginLog loginLog = new LoginLog(null, details.getRemoteAddress(), details.getSessionId(), new Timestamp(System.currentTimeMillis()));
        loginLogMapper.insert(loginLog);

        log.info("user logined:{} , args:{}", result, JSON.toJSONString(args));
    }
}