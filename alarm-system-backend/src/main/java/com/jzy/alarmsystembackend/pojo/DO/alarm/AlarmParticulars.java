package com.jzy.alarmsystembackend.pojo.DO.alarm;

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
@TableName(value = "alarm_particulars")
public class AlarmParticulars {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 报警器id
     */
    private Integer alarmId;
    /**
     * 报警源
     */
    private String source;
    /**
     * 报警类型
     */
    private String type;
    /**
     * 报警级别
     */
    private Integer level;
    /**
     * 报警发生时间
     */
    private Timestamp occurTime;
    /**
     * 报警确认时间
     */
    private Timestamp confirmTime;
    /**
     * 报警恢复时间
     */
    private Timestamp recoverTime;
    /**
     * 报警确认状态
     */
    private Boolean confirmStatus;
    /**
     * 报警恢复状态
     */
    private Boolean recoverStatus;
    /**
     * 与报警相关的其他信息
     */
    private String additionalInfo;
}
