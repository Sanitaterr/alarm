package com.jzy.alarmsystembackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzy.alarmsystembackend.pojo.DO.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}