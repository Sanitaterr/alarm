package com.jzy.alarmsystembackend;

import com.jzy.alarmsystembackend.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.Timestamp;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AlarmSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmSystemBackendApplication.class, args);
    }

}
