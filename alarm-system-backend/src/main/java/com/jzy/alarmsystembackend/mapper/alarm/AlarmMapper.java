package com.jzy.alarmsystembackend.mapper.alarm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzy.alarmsystembackend.pojo.DO.Alarm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlarmMapper extends BaseMapper<Alarm> {
}
