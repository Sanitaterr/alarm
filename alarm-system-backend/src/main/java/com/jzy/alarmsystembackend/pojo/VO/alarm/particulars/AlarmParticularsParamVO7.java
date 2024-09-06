package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * alarm_id<br>
 * occur_time;
 */
@Data
public class AlarmParticularsParamVO7 implements Serializable {

    private static final long serialVersionUID = 6241198605902833533L;
    /**
     * 报警器id
     */
    @JsonProperty("alarm_id")
    private Integer alarmId;
    /**
     * 报警发生时间
     */
    @JsonProperty("occur_time")
    private Timestamp occurTime;
}
