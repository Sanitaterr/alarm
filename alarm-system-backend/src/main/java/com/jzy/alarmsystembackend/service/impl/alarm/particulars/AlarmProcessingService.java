package com.jzy.alarmsystembackend.service.impl.alarm.particulars;

import com.jzy.alarmsystembackend.config.properties.AlarmTaskProperties;
import com.jzy.alarmsystembackend.util.TimeEnum;
import com.jzy.alarmsystembackend.service.alarm.particulars.AlarmParticularsService;
import com.jzy.alarmsystembackend.util.AbstractSimpleBufferedConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class AlarmProcessingService {

    @Autowired
    private AbstractSimpleBufferedConsumer abstractSimpleBufferedConsumer;

    @Autowired
    private AlarmParticularsService alarmParticularsService;

    @Autowired
    private AlarmTaskProperties alarmTaskProperties;

    @Autowired
    private TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledTask;

    @Scheduled(fixedRate = 10 * 1000) // 每10秒执行一次
    public void processQueue() {
        abstractSimpleBufferedConsumer.flush();
        log.info("定时刷新成功");
    }

    @PostConstruct
    public void startDynamicTask() {
        Long number = alarmTaskProperties.getNumber();
        TimeEnum timeEnum = alarmTaskProperties.getUnit();
        // 初始调度
        scheduleTask(number, timeEnum);
    }

    public void scheduleTask(Long number, TimeEnum timeEnum) {
        if (scheduledTask != null && !scheduledTask.isCancelled()) {
            // 取消之前的任务
            scheduledTask.cancel(true);
        }

        if (number == null || timeEnum == null) {
            log.error("Invalid parameters: number or timeEnum is null");
            return;
        }

        Long fixedRateInMillis = alarmTaskProperties.getFixedRateInMillis();
        log.error(String.valueOf(fixedRateInMillis));

        scheduledTask = taskScheduler.scheduleAtFixedRate(() -> {
            alarmParticularsService.alarmParticularsTimingHandling(number, timeEnum);
            log.info("过期报警已处理");
        }, fixedRateInMillis);
    }
}
