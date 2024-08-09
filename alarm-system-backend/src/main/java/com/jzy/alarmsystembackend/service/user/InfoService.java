package com.jzy.alarmsystembackend.service.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.util.Map;

public interface InfoService {
    Map<String, String> getInfo();
    Integer updateFirm(Integer firmId);
    Integer getFirmId();
}
