package com.jzy.alarmsystembackend.pojo.VO.alarm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * source<br>
 * firm_id
 */
@Data
public class AlarmParamVO4 implements Serializable {

    private static final long serialVersionUID = -8172837188807421878L;
    private String source;
    @JsonProperty("firm_id")
    private Integer firmId;
}
