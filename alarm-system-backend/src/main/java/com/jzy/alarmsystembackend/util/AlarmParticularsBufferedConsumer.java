package com.jzy.alarmsystembackend.util;

import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * TODO ConfigurationProperties DisposableBean
 */
@Slf4j
public class AlarmParticularsBufferedConsumer extends AbstractSimpleBufferedConsumer<AlarmParticulars> implements DisposableBean {
    @Override
    public long getBufferSize() {
        return maxSize;
    }

    @Setter
    private int maxSize;
    private static AlarmParticularsBufferedConsumer alarmParticularsBufferedConsumer;

    public static AlarmParticularsBufferedConsumer getAlarmParticularsBufferedConsumer() {
        return alarmParticularsBufferedConsumer;
    }

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @Autowired
    private RedisCache redisCache;

    public AlarmParticularsBufferedConsumer() {
        super(10);
        alarmParticularsBufferedConsumer = this;
    }

    @Override
    public void accept(AlarmParticulars alarmParticulars) {
        System.out.println("Processing: " + alarmParticulars);
        alarmParticularsMapper.insert(alarmParticulars);

        // 将报警数据缓存到 Redis
        log.warn("" + alarmParticulars);
        String redisKey = "alarm:" + alarmParticulars.getId();
        log.warn(redisKey);
        redisCache.setCacheObject(redisKey, alarmParticulars);
        // 可选：设置过期时间
        redisCache.expire(redisKey, 5, TimeUnit.MINUTES);
        log.info("Inserted alarm to Redis with key {}", redisKey);
    }

    @NotNull
    @Override
    public Consumer<AlarmParticulars> andThen(@NotNull Consumer<? super AlarmParticulars> after) {
        return super.andThen(after);
    }

    @Override
    public void destroy() throws Exception {
        alarmParticularsBufferedConsumer.flush();
        log.info("Bean被销毁!!!!!");
    }

}
