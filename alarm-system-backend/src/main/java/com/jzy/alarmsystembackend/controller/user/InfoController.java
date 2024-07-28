package com.jzy.alarmsystembackend.controller.user;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.user.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("/info")
    public AjaxResult getInfo() {
        return infoService.getInfo();
    }
}
