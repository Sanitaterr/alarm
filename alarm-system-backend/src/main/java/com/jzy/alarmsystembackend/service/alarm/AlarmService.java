package com.jzy.alarmsystembackend.service.alarm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.alarm.Alarm;

import java.util.List;

public interface AlarmService {

    List<Alarm> selectAllAlarmAuth();
    IPage<Alarm> selectAllAlarmPagedAuth(Long pageNum, Long pageSize);
    Alarm selectAlarmById(Integer id);
    Alarm selectAlarmBySource(String source);
    Integer addAlarmAuth(Alarm alarm);
    Integer deleteAlarmAuth(Alarm alarm);
    Integer updateAlarmAuth(Alarm alarm);
}
