package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.user.UserParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.user.UserParamVO3;
import com.jzy.alarmsystembackend.service.user.InfoService;
import com.jzy.alarmsystembackend.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/user/getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.successProjectInfoData("get info success", infoService.getInfo());
    }

    @PostMapping("/user/updateFirm")
    public AjaxResult updateFirm(@RequestBody UserParamVO3 param) {
        return AjaxResult.successProjectInfoData("update firm success", infoService.updateFirm(param.getFirmId()));
    }

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/test")
    public String test() {
        redisCache.setCacheObject("k1", "Hello World!");
        return "Hello World!";
    }
}
