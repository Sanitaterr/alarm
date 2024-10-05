package com.jzy.alarmsystembackend.service.impl.alarm.particulars;

import com.jzy.alarmsystembackend.Constant.GlobalConstant;
import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.SensorData;
import com.jzy.alarmsystembackend.pojo.SensorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AlarmScheduled {

    @Autowired
    private WebClient webClient;

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @Scheduled(fixedRate = 1 * 1000)
    public void getAlarmParticulars() {
        String url = "https://sensor.jaalee.com/v1/open/data/all";

        webClient.get()
                .uri(url)
                .header("Authorization", GlobalConstant.token)
                .retrieve()
                .bodyToMono(SensorResponse.class)
                .doOnNext(response -> {
                    SensorResponse sensorResponse = response;
                    List<SensorData> data = sensorResponse.getData();
                    data.forEach(t -> {
                        double temperature = t.getTemperature();
                        Timestamp timestamp = new Timestamp(t.getTime());
                        String mac = t.getMac();
                        int num = 21;
                        if (Objects.equals(mac, "D93F5BBE3782")) {
                            num = 41;
                        } else if (Objects.equals(mac, "CB5957EB8CB0")) {
                            num = 61;
                        } else if (Objects.equals(mac, "ECE67BF6C106")) {
                            num = 81;
                        }
                        log.info(temperature + " " + timestamp);
                        if (temperature > 30) {
                            log.error("温度过高超过30度");
                            AlarmParticulars alarmParticulars = new AlarmParticulars(null, num, "泊森有限公司一楼生产车间" + num + "号", "温度过高", 1, new Timestamp(System.currentTimeMillis()), null, null, null, null, null);
                            alarmParticularsMapper.insert(alarmParticulars);
                            log.error("数据插入成功");
                        }
                    });
                }).subscribe();
    }
}
