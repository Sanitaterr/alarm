package com.jzy.alarmsystembackend.config.properties;

import com.jzy.alarmsystembackend.util.TimeEnum;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/9/17 22:42
 * @Description: TODO
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "alarm.task.fixed-rate")
public class AlarmTaskProperties {

    private Long number;
    private TimeEnum unit;
    private Long fixedRateInMillis;

    @PostConstruct
    public void init() {
            fixedRateInMillis *= 24 * 60 * 60 * 1000;
    }
}
