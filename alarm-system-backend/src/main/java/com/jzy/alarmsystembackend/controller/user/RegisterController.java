package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.user.UserParamVO2;
import com.jzy.alarmsystembackend.service.user.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/register")
    public AjaxResult register(@RequestBody UserParamVO2 param) {
        return registerService.register(param.getUsername(), param.getPassword(), param.getConfirmedPassword());
    }
}
