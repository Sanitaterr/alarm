package com.jzy.alarmsystembackend.controller.alarm.particulars;

import com.jzy.alarmsystembackend.Constant.GlobalConstant;
import com.jzy.alarmsystembackend.pojo.SensorData;
import com.jzy.alarmsystembackend.pojo.SensorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private WebClient webClient;

    @GetMapping("/callExternalApi")
    public Mono<String> getTest() {
        String url = "https://sensor.jaalee.com/v1/open/login";
        return webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }

    @PostMapping("/sendAccountCode")
    public Mono<String> login() {
        String url = "https://sensor.jaalee.com/v1/open/login";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("account", "18457181015");
        requestBody.put("code", "1320");
        requestBody.put("timeZone", "Asia/Shanghai");

        return webClient.post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> {
                    log.info("Response: " + response);
                });
    }

    @PostMapping("/getAll")
    public Mono<SensorResponse> getAll() {
        String url = "https://sensor.jaalee.com/v1/open/data/all";

        return webClient.get()
                .uri(url)
                .header("Authorization", GlobalConstant.token)
                .retrieve()
                .bodyToMono(SensorResponse.class)
                .doOnNext(response -> {
                    log.info("Response: " + response);
                    SensorResponse sensorResponse = response;
                    List<SensorData> data = sensorResponse.getData();
                    data.forEach(t -> log.info(t.getTemperature() + " " + new Timestamp(t.getTime())));
                });
    }
}