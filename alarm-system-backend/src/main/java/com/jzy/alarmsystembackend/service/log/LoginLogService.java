package com.jzy.alarmsystembackend.service.log;

import com.jzy.alarmsystembackend.pojo.DO.log.LoginLog;

import java.util.List;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/9/6 16:39
 * @Description: TODO
 */
public interface LoginLogService {

    List<LoginLog> getAllLoginLog();
}
