package com.jzy.alarmsystembackend.service.alarm;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;

import java.sql.Timestamp;
import java.util.List;

public interface AlarmService {
    List<AlarmParticulars> selectAllAlarm();
    List<AlarmParticulars> selectAllAlarmAuth();
    AlarmParticulars selectAlarmById(Integer id);
    AlarmParticulars selectAlarmBySourceAndOccurTime(String source, Timestamp occurTime);

    List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTime();
    List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimeAuth();

    List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTime();
    List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimeAuth();

    IPage<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);
    IPage<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);

    List<AlarmParticulars> selectRealtimeAlarmOrdered();

}
