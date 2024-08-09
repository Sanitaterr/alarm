package com.jzy.alarmsystembackend.mapper.alarm;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AlarmParticularsMapper extends BaseMapper<AlarmParticulars> {
    List<AlarmParticulars> selectAlarmParticularsAuth(Integer firmId);
    AlarmParticulars selectAlarmParticularsByIdAuth(Integer id);
    List<AlarmParticulars> selectAlarmParticularsByWrapperAuth(@Param(Constants.WRAPPER) Wrapper<AlarmParticulars> wrapper);
}
