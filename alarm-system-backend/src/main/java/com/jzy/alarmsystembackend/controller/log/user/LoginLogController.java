package com.jzy.alarmsystembackend.controller.log.user;

import com.alibaba.fastjson.JSON;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.log.LoginLogService;
import com.jzy.alarmsystembackend.service.log.MyLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;


@Slf4j
@RestController
@RequestMapping("/log")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @PostMapping("/getAllLoginLog")
    public AjaxResult getAllLoginLog() {
        return AjaxResult.successProjectInfoData(
                "get all login log success",
                loginLogService.getAllLoginLog());
    }
}