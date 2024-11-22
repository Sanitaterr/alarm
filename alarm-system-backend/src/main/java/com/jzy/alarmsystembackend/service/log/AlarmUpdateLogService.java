package com.jzy.alarmsystembackend.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO10;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO12;

import java.util.List;

public interface AlarmUpdateLogService {

    List<AlarmUpdateLog> getAllLogAuth();
    IPage<AlarmUpdateLog> getAllLogAuthPaged(Long pageNum, Long pageSize);
    List<AlarmUpdateLog> selectCondition(AlarmParticularsParamVO10 param);
    IPage<AlarmUpdateLog> selectConditionPaged(AlarmParticularsParamVO12 param);
}
