package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/10/9 11:02
 * @Description: TODO
 */
@Data
public class AlarmParticularsParamVO10 implements Serializable {
    /**
     * 操作人员
     */
    private String username;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 操作查询开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp startTime;
    /**
     * 操作查询截止时间
     **/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp endTime;
}
