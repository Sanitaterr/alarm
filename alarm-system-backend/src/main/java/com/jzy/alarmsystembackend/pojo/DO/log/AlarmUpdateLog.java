package com.jzy.alarmsystembackend.pojo.DO.log;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "alarm_update_log")
public class AlarmUpdateLog {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 操作人员
     */
    private String username;
    /**
     * 所属部门id
     */
    private Integer firm;
    /**
     * 操作类型
     */
    private String type;
    /**
     * 操作时间
     */
    private Timestamp time;
    /**
     * 操作对象id
     */
    private Integer alarmParticularsId;
    /**
     * 操作状态 1成功 0失败
     */
    private Boolean status;
}
