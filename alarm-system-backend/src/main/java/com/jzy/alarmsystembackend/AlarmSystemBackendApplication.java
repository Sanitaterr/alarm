package com.jzy.alarmsystembackend;

import com.jzy.alarmsystembackend.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableScheduling
public class AlarmSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmSystemBackendApplication.class, args);
    }

}
