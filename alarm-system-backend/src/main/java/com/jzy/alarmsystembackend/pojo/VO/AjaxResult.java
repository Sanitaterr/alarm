package com.jzy.alarmsystembackend.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @Description: TODO(ajax操作消息提醒)
 * @author jzy
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int ERROR_WITH_NO_MESSAGE = 1;
    public static final int ERROR_WITH_MSG = 500;
    public static final int SUCCESS = 200;
    private int code;
    private String msg;
    private T data;

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static AjaxResult error()
    {
        return error(ERROR_WITH_NO_MESSAGE, "操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(String msg)
    {
        return error(ERROR_WITH_MSG, msg);
    }

    /**
     * 返回错误消息
     *
     * @param code 错误码
     * @param msg 内容
     * @return 错误消息
     */
    public static AjaxResult error(int code, String msg)
    {
        return new AjaxResult(code,msg,null);
    }

    /**
     * 返回成功消息
     *
     * @param msg 内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg)
    {
        return new AjaxResult(SUCCESS,msg,null);
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success()
    {
        return AjaxResult.success("操作成功");
    }

    public static AjaxResult successData(Object value){
        return new AjaxResult(SUCCESS,null,value);
    }

    /**
     * 返回项目基本信息
     * @param msg
     * @param data
     * @return
     */
    public static AjaxResult successProjectInfoData(String msg, Object data){
        return new AjaxResult(200, msg, data);
    }

}