package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/10/9 14:29
 * @Description: TODO
 */
@Data
public class AlarmParticularsParamVO11 implements Serializable {

    private String ip;
    private Long pageNum;
    private Long pageSize;
}
