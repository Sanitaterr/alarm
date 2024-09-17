package com.jzy.alarmsystembackend.service.impl.alarm.particulars;

import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.util.AbstractSimpleBufferedConsumer;
import com.jzy.alarmsystembackend.util.AlarmParticularsBufferedConsumer;
import com.jzy.alarmsystembackend.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AlarmProcessingService {

    @Autowired
    private AbstractSimpleBufferedConsumer abstractSimpleBufferedConsumer;

    @Scheduled(fixedRate = 10 * 1000) // 每10秒执行一次
    public void processQueue() {
        abstractSimpleBufferedConsumer.flush();
        log.info("定时刷新成功");
    }
}
