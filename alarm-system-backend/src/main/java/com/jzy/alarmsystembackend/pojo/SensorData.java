package com.jzy.alarmsystembackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorData {
    private double co2;
    private double hchoPpm;
    private double humidity;
    private double light;
    private String mac;
    private double o3Ppm;
    private double pm10;
    private double pm25;
    private int power;
    private double pressure;
    private double temperature;
    private long time;
    private double tvocPpm;
    private String type;
    private double uv;
    private double vocIndex;
    private double vocRaw;
}