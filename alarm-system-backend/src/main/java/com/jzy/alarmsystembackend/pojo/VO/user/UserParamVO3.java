package com.jzy.alarmsystembackend.pojo.VO.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserParamVO3 implements Serializable {

    private static final long serialVersionUID = 6714120055734271741L;
    @JsonProperty("firm_id")
    private Integer firmId;
}
