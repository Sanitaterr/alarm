package com.jzy.alarmsystembackend.service.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.util.Map;

public interface LoginService {
    public AjaxResult getToken(String username, String password);
}
