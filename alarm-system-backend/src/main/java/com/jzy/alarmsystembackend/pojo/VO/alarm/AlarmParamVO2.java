package com.jzy.alarmsystembackend.pojo.VO.alarm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * source, occurTime
 */
@Data
public class AlarmParamVO2 implements Serializable {

    private static final long serialVersionUID = -520270777176062835L;
    private String source;
    @JsonProperty("occur_time")
    private Timestamp occurTime;

}
