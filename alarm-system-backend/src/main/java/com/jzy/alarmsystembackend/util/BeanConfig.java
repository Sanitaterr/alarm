package com.jzy.alarmsystembackend.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public AbstractSimpleBufferedConsumer get() {
        return new AlarmParticularsBufferedConsumer();
    }
}
