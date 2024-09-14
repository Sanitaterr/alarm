package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.controller.log.user.LoginLogController;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.user.UserParamVO1;
import com.jzy.alarmsystembackend.service.impl.log.LoginLogServiceImpl;
import com.jzy.alarmsystembackend.service.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;


    @Loggable(LoginLogServiceImpl.class)
    @PostMapping("/user/login")
    public AjaxResult getToken(@RequestBody UserParamVO1 param) {
        return AjaxResult.successProjectInfoData("login success",
                loginService.getToken(param.getUsername(), param.getPassword()));
    }
}
