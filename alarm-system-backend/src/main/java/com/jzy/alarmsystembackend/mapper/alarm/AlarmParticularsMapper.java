package com.jzy.alarmsystembackend.mapper.alarm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AlarmParticularsMapper extends BaseMapper<AlarmParticulars> {
    List<AlarmParticulars> selectAlarmParticularsAuth(Integer firmId);
    IPage<AlarmParticulars> selectAlarmParticularsAuth(Page<AlarmParticulars> page, Integer firmId);
    AlarmParticulars selectAlarmParticularsByIdAuth(Integer id);
    List<AlarmParticulars> selectAlarmParticularsByWrapperAuth(@Param(Constants.WRAPPER) Wrapper<AlarmParticulars> wrapper);
    IPage<AlarmParticulars> selectAlarmParticularsByWrapperPagedAuth(Page<AlarmParticulars> page, @Param(Constants.WRAPPER) Wrapper<AlarmParticulars> wrapper);

    @MapKey("timeUnit")
    List<Map<String, Object>> selectAlarmNumsByTimeAuth(String timeType, @Param(Constants.WRAPPER) Wrapper<AlarmParticulars> wrapper);

    Integer selectAlarmNumsByWrapperAuth(@Param(Constants.WRAPPER) Wrapper<AlarmParticulars> wrapper);

//    Integer alarmParticularsTimingHandling(TimeEnum timeEnum);
}
