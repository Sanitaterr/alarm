package com.jzy.alarmsystembackend.controller.log.alarm.particulars;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO10;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO12;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO3;
import com.jzy.alarmsystembackend.service.log.AlarmUpdateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class AlarmUpdateLogController {

    @Autowired
    private AlarmUpdateLogService alarmUpdateLogService;

    @PostMapping("/getAlarmUpdateLog")
    public AjaxResult getAllLogAuth() {
        return AjaxResult.successProjectInfoData(
                "get alarm update log success",
                alarmUpdateLogService.getAllLogAuth());
    }

    @PostMapping("/getAlarmUpdateLogPaged")
    public AjaxResult getAllLogAuthPaged(@RequestBody AlarmParticularsParamVO3 param) {
        return AjaxResult.successProjectInfoData(
                "get alarm update log paged success",
                alarmUpdateLogService.getAllLogAuthPaged(param.getPageNum(), param.getPageSize()));
    }

    @PostMapping("/selectCondition")
    public AjaxResult selectCondition(@RequestBody AlarmParticularsParamVO10 param) {
        return AjaxResult.successProjectInfoData(
                "success",
                alarmUpdateLogService.selectCondition(param));
    }

    @PostMapping("/selectConditionPaged")
    public AjaxResult selectConditionPaged(@RequestBody AlarmParticularsParamVO12 param) {
        return AjaxResult.successProjectInfoData(
                "success",
                alarmUpdateLogService.selectConditionPaged(param));
    }
}
