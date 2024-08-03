package com.jzy.alarmsystembackend.service.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.util.Map;

public interface LoginService {
    public String getToken(String username, String password);
}
