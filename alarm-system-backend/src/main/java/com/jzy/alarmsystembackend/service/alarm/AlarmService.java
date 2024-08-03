package com.jzy.alarmsystembackend.service.alarm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.Alarm;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.sql.Timestamp;
import java.util.List;

public interface AlarmService {
    List<Alarm> selectAllAlarm();
    Alarm selectAlarmById(Integer id);
    Alarm selectAlarmBySourceAndOccurTime(String source, Timestamp occurTime);

    List<Alarm> selectAllHistoricalAlarmOrderedByOccurTime();
    List<Alarm> selectAllRealtimeAlarmOrderedByOccurTime();

    IPage<Alarm> selectAllHistoricalAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);
    IPage<Alarm> selectAllRealtimeAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);

    List<Alarm> selectRealtimeAlarmOrdered();

}
