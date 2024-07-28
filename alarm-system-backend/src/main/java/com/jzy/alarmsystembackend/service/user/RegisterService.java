package com.jzy.alarmsystembackend.service.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;

import java.util.Map;

public interface RegisterService {
    public AjaxResult register(String username, String password, String confirmedPassword);
}
