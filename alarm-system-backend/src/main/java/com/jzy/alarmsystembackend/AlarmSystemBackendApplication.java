package com.jzy.alarmsystembackend;

import com.jzy.alarmsystembackend.config.properties.AlarmTaskProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
@ConfigurationPropertiesScan("com.jzy.alarmsystembackend.config.properties")
public class AlarmSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmSystemBackendApplication.class, args);
    }

}
