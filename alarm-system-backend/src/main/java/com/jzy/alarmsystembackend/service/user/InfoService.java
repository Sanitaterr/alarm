package com.jzy.alarmsystembackend.service.user;

import com.jzy.alarmsystembackend.pojo.DO.User;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.util.Map;

public interface InfoService {
    /**
     * 从验证信息中获取用户数据
     * @return
     */
    User getInfo();

    Integer updateFirm(Integer firmId);
}
