package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
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

    @PostMapping("/login")
    public AjaxResult getToken(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        System.out.println("123");
        return loginService.getToken(username, password);
    }

}
