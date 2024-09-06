package com.jzy.alarmsystembackend.mapper.log;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzy.alarmsystembackend.pojo.DO.log.LoginLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {
}
