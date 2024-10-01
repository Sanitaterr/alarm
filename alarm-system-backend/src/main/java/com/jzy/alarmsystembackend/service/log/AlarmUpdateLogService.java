package com.jzy.alarmsystembackend.service.log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jzy.alarmsystembackend.pojo.DO.log.AlarmUpdateLog;

import java.util.List;

public interface AlarmUpdateLogService {

    List<AlarmUpdateLog> getAllLogAuth();
    IPage<AlarmUpdateLog> getAllLogAuthPaged(Long pageNum, Long pageSize);
}
