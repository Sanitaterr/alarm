package com.jzy.alarmsystembackend.pojo.VO.alarm;

import lombok.Data;

import java.io.Serializable;

/**
 * pageNum<br>
 * pageSize
 */
@Data
public class AlarmParamVO1 implements Serializable {

    private static final long serialVersionUID = -6988884657661148193L;
    private Long pageNum;
    private Long pageSize;
}
