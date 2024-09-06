package com.jzy.alarmsystembackend.controller.alarm.particulars;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO7;
import com.jzy.alarmsystembackend.service.alarm.particulars.AlarmParticularsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmParticularsStatusController {

    @Autowired
    private AlarmParticularsService alarmParticularsService;

    /**
     * 确认警报
     * @return AjaxResult
     */
    @PostMapping("/alarmConfirm")
    public AjaxResult alarmConfirm(@RequestBody AlarmParticularsParamVO1 param) {
        return AjaxResult.successProjectInfoData(
                "alarm confirm success",
                alarmParticularsService.alarmConfirm(param)
        );
    }

    /**
     * 恢复警报
     * @return AjaxResult
     */
    @PostMapping("/alarmRecover")
    public AjaxResult alarmRecover(@RequestBody AlarmParticularsParamVO1 param) {
        return AjaxResult.successProjectInfoData(
                "alarm recover success",
                alarmParticularsService.alarmRecover(param)
        );
    }
}
