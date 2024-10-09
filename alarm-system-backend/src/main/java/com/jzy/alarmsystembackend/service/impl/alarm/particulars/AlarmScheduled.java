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
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class AlarmScheduled {

    @Autowired
    private WebClient webClient;

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    // 存储最近的报警时间，key 为传感器的 MAC 地址，value 为最近的报警时间
    private final Map<String, Timestamp> recentAlarms = new ConcurrentHashMap<>();

    // 存储最近的温度值，key 为传感器的 MAC 地址，value 为最近的温度值
    private final Map<String, Double> recentTemperatures = new ConcurrentHashMap<>();

    // 定义报警的时间间隔，例如 5 分钟（以毫秒为单位）
    private static final long ALARM_INTERVAL_MS = 1 * 60 * 1000;

    private static final int WARNINGTEM = 30;

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

                        // 获取该传感器上一次记录的温度值
                        Double lastTemperature = recentTemperatures.get(mac);

                        // 如果温度与上一次相同，则跳过日志输出
                        if (lastTemperature == null || lastTemperature != temperature) {
                            // 更新最近温度
                            recentTemperatures.put(mac, temperature);

                            // 输出日志
                            if (temperature < WARNINGTEM) {
                                log.info(temperature + " " + timestamp);
                            } else {
                                log.error(temperature + " " + timestamp);
                            }
                        }

                        // 获取传感器最近的报警时间
                        Timestamp lastAlarmTime = recentAlarms.get(mac);

                        if (temperature > WARNINGTEM && (lastAlarmTime == null ||
                                (System.currentTimeMillis() - lastAlarmTime.getTime()) > ALARM_INTERVAL_MS)) {
                            log.error("温度过高超过" + WARNINGTEM + "度");
                            AlarmParticulars alarmParticulars = new AlarmParticulars(null, num, "泊森有限公司一楼生产车间" + num + "号", "温度过高", 1, new Timestamp(System.currentTimeMillis()), null, null, null, null, "温度过高达到" + String.format("%.2f", temperature) + "度");
                            alarmParticularsMapper.insert(alarmParticulars);
                            log.error("数据插入成功");

                            // 更新该传感器的最近报警时间
                            recentAlarms.put(mac, new Timestamp(System.currentTimeMillis()));
                        }
                    });
                }).subscribe();
    }
}
