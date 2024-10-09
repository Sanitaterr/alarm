package com.jzy.alarmsystembackend.service.alarm.particulars;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.util.TimeEnum;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * AlarmParticularsService
 */
public interface AlarmParticularsService {
    List<AlarmParticulars> selectAllAlarm();
    List<AlarmParticulars> selectAllAlarmAuth();
    IPage<AlarmParticulars> selectAllAlarmPagedAuth(Long pageNum, Long pageSize);
    AlarmParticulars selectAlarmById(Integer id);
    AlarmParticulars selectAlarmBySourceAndOccurTime(String source, Timestamp occurTime);

    List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTime();
    List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimeAuth();

    List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTime();
    List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimeAuth();

    IPage<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);
    IPage<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimePagedAuth(Long pageNum, Long pageSize);
    IPage<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize);
    IPage<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimePagedAuth(Long pageNum, Long pageSize);

    List<AlarmParticulars> selectRealtimeAlarmOrdered();
    List<AlarmParticulars> selectRealtimeAlarmOrderedAuth();

    List<Map<String, Object>> selectAlarmNumsByTimeAuth(String timeType);

    Integer selectAlarmNumsByTypeAuth(String type);

    Integer selectAlarmNumsOneDayAuth(String time);

    Integer selectHistoricalAlarmNumsOneDay(String time);
    List<AlarmParticulars> selectHistoricalAlarmOneDay(String time);

    Integer selectRealtimeAlarmNumsOneDay(String time);
    List<AlarmParticulars> selectRealtimeAlarmOneDay(String time);

    Integer alarmConfirm(AlarmParticularsParamVO1 param);

    Integer alarmRecover(AlarmParticularsParamVO1 param);

    Integer alarmParticularsTimingHandling(Long number, TimeEnum timeEnum);

    AlarmParticulars getLatest();
}
