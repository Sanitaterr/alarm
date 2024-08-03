package com.jzy.alarmsystembackend.pojo.VO.mqtt;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class MqttParamVO1 implements Serializable {

    private static final long serialVersionUID = -4065031254207338887L;
    private Integer id;
    private String type;
    private Integer level;
    private Timestamp occurTime;
    private Integer firmId;
}
