package com.jzy.alarmsystembackend.pojo.VO.alarm.particulars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/10/9 9:17
 * @Description: TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlarmParticularsParamVO8 implements Serializable {



    /**
     *
     * 排序规则 true正序 / false倒序
     * @create 2024/10/9
     **/
    private Boolean orderRule;
    /**
     *
     * 报警等级
     * @create 2024/10/9
     **/
    private Integer level;
    /**
     *
     * 报警类型
     * @create 2024/10/9
     **/
    private String type;
    /**
     *
     * 报警发生查询开始时间
     * @create 2024/10/9
     **/
    private Timestamp startTime;
    /**
     *
     * 报警发生查询结束时间
     * @create 2024/10/9
     **/
    private Timestamp endTime;
    /**
     *
     * 报警是否已确认
     * @create 2024/10/9
     **/
    private Boolean confirmStatus;
    /**
     *
     * 报警是否已恢复
     * @create 2024/10/9
     **/
    private Boolean recoverStatus;
}
