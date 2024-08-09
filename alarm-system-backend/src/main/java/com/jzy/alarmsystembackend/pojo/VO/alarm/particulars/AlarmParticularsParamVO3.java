package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import lombok.Data;

import java.io.Serializable;

/**
 * pageNum pageSize
 */
@Data
public class AlarmParticularsParamVO3 implements Serializable {

    private static final long serialVersionUID = 7957226765623374053L;
    private Long pageNum;
    private Long pageSize;
}
