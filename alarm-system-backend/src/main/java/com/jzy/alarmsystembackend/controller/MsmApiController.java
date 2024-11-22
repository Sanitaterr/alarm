package com.jzy.alarmsystembackend.controller;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
* @author JZY
* @version 1.0
* Create by 2024/10/6 13:38
* @Description: TODO
*/
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {


    @Autowired
    private MsmService msmService;

    @GetMapping(value = "/send/{phone}")
    public AjaxResult code(@PathVariable String phone) {
        Map<String,Object> param = new HashMap<>();
        param.put("unit_name", "泊森");
        param.put("address", "一号仓库");
        param.put("time", "2024-11-02 12:59:57.343");
        param.put("value", 1);
        param.put("value1", 2);

        return AjaxResult.successProjectInfoData(
                "send success",
                msmService.send(param,phone)
        );
    }
}