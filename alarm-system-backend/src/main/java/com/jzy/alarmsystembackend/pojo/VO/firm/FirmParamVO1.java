package com.jzy.alarmsystembackend.pojo.VO.firm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * firmId
 */
@Data
public class FirmParamVO1 implements Serializable {

    private static final long serialVersionUID = 7948251314193145717L;
    @JsonProperty("firm_id")
    private Integer firmId;
}
