package com.jzy.alarmsystembackend.service.impl.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.mapper.log.AlarmUpdateLogMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.service.log.AlarmUpdateLogService;
import com.jzy.alarmsystembackend.service.log.MyLog;
import com.jzy.alarmsystembackend.service.user.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class AlarmUpdateLogServiceImpl implements AlarmUpdateLogService, MyLog {


    @Autowired
    private AlarmUpdateLogMapper alarmUpdateLogMapper;

    @Autowired
    private InfoService infoService;

    @Override
    public List<AlarmUpdateLog> getAllLogAuth() {
        LambdaQueryWrapper<AlarmUpdateLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmUpdateLog::getFirm, infoService.getInfo().getFirmId());
        return alarmUpdateLogMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public void log(Method method, Object[] args, Object result, Object target) {
        User user = infoService.getInfo();
        String username = user.getUsername();
        Integer firmId = user.getFirmId();


        Loggable type = AnnotatedElementUtils.getMergedAnnotation(method, Loggable.class);
        String typeStr = "";
        if (type != null && type.args().length > 0) {
            typeStr = type.args()[0];
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Boolean status = true;

        AlarmParticularsParamVO1 param = (AlarmParticularsParamVO1) args[0];

        AlarmUpdateLog logEntry = new AlarmUpdateLog();
        logEntry.setUsername(username);
        logEntry.setFirm(firmId);
        logEntry.setType(typeStr);
        logEntry.setTime(currentTime);
        logEntry.setAlarmParticularsId(param.getId());
        logEntry.setStatus(status);

        alarmUpdateLogMapper.insert(logEntry);

        log.info("记录了操作日志: {}", logEntry);
    }
}
