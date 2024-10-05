package com.jzy.alarmsystembackend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorResponse {
    private int code;
    private List<SensorData> data;
    private String message;
}


