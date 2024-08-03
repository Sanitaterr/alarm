package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.user.UserParamVO1;
import com.jzy.alarmsystembackend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public AjaxResult getToken(@RequestBody UserParamVO1 param) {
        return AjaxResult.successProjectInfoData("login success", loginService.getToken(param.getUsername(), param.getPassword()));
    }
}
