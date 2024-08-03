package com.jzy.alarmsystembackend;

import com.jzy.alarmsystembackend.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class AlarmSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmSystemBackendApplication.class, args);
    }

}
