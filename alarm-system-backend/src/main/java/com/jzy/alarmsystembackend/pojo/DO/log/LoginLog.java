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
@TableName(value = "login_log")
public class LoginLog {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * ip
     */
    private String ip;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * time
     */
    private Timestamp time;
}
