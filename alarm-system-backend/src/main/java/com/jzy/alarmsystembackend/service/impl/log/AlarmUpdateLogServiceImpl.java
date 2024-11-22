package com.jzy.alarmsystembackend.service.impl.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.mapper.log.AlarmUpdateLogMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;
import com.jzy.alarmsystembackend.pojo.DO.log.LoginLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO10;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO12;
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

    /**
     * 查询所有日志
     * @return java.util.List<com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog>
     * @author jzy
     * @create 2024/10/9
     **/
    @Override
    public List<AlarmUpdateLog> getAllLogAuth() {
        LambdaQueryWrapper<AlarmUpdateLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmUpdateLog::getFirm, infoService.getInfo().getFirmId());
        return alarmUpdateLogMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public IPage<AlarmUpdateLog> getAllLogAuthPaged(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlarmUpdateLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        QueryWrapper<AlarmUpdateLog> wrapper = new QueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmUpdateLog::getFirm, infoService.getInfo().getFirmId());
        return alarmUpdateLogMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public List<AlarmUpdateLog> selectCondition(AlarmParticularsParamVO10 param) {
        LambdaQueryWrapper<AlarmUpdateLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (param.getUsername() != null) {
            lambdaQueryWrapper.eq(AlarmUpdateLog::getUsername, param.getUsername());
        }

        if (param.getType() != null) {
            lambdaQueryWrapper.eq(AlarmUpdateLog::getType, param.getType());
        }

        if (param.getStartTime() != null) {
            lambdaQueryWrapper.ge(AlarmUpdateLog::getTime, param.getStartTime());
        }
        if (param.getEndTime() != null) {
            lambdaQueryWrapper.le(AlarmUpdateLog::getTime, param.getEndTime());
        }

        return alarmUpdateLogMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public IPage<AlarmUpdateLog> selectConditionPaged(AlarmParticularsParamVO12 param) {
        LambdaQueryWrapper<AlarmUpdateLog> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (param.getUsername() != null) {
            lambdaQueryWrapper.eq(AlarmUpdateLog::getUsername, param.getUsername());
        }

        if (param.getType() != null) {
            lambdaQueryWrapper.eq(AlarmUpdateLog::getType, param.getType());
        }

        if (param.getStartTime() != null) {
            lambdaQueryWrapper.ge(AlarmUpdateLog::getTime, param.getStartTime());
        }
        if (param.getEndTime() != null) {
            lambdaQueryWrapper.le(AlarmUpdateLog::getTime, param.getEndTime());
        }

        Page<AlarmUpdateLog> page = new Page<>(param.getPageNum(), param.getPageSize());
        return alarmUpdateLogMapper.selectPage(page, lambdaQueryWrapper);
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
