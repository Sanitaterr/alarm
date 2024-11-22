package com.jzy.alarmsystembackend.service.log;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.log.LoginLog;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO11;

import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/9/6 16:39
 * @Description: TODO
 */
public interface LoginLogService {

    List<LoginLog> getAllLoginLog();
    IPage<LoginLog> getAllLoginLogPaged(Long pageNum, Long pageSize);
    List<LoginLog> selectByIp(String ip);
    IPage<LoginLog> selectByIpPaged(AlarmParticularsParamVO11 param);
}
