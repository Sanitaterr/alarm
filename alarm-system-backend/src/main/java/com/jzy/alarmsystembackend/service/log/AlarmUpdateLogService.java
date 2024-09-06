package com.jzy.alarmsystembackend.service.log;

import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;

import java.util.List;

public interface AlarmUpdateLogService {

    List<AlarmUpdateLog> getAllLogAuth();
}
