package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * timeType: year, month, week
 */
@Data
public class AlarmParticularsParamVO4 implements Serializable {

    private static final long serialVersionUID = 7600042404105382319L;
    /**
     * year, month, week
     */
    @JsonProperty("time_type")
    private String timeType;
}
