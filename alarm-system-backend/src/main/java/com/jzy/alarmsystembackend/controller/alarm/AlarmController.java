package com.jzy.alarmsystembackend.controller.alarm;

import com.jzy.alarmsystembackend.pojo.DO.alarm.Alarm;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO2;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO3;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO4;
import com.jzy.alarmsystembackend.service.alarm.AlarmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/backend")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;

    @PostMapping("/selectAllAlarm")
    public AjaxResult selectAllAlarm() {
        return AjaxResult.successProjectInfoData(
                "select all alarm success",
                alarmService.selectAllAlarmAuth());
    }

    @PostMapping("/selectAllAlarmPaged")
    public AjaxResult selectAllAlarmPaged(@RequestBody AlarmParamVO1 param) {
        return AjaxResult.successProjectInfoData(
                "select all alarm paged success",
                alarmService.selectAllAlarmPagedAuth(param.getPageNum(), param.getPageSize()));
    }

    @PostMapping("/selectAlarmById")
    public AjaxResult selectAlarmById(@RequestBody AlarmParamVO2 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm by id success",
                alarmService.selectAlarmById(param.getId()));
    }

    @PostMapping("/selectAlarmBySource")
    public AjaxResult selectAlarmBySource(@RequestBody AlarmParamVO3 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm by source success",
                alarmService.selectAlarmBySource(param.getSource()));
    }

    @PostMapping("/addAlarm")
    public AjaxResult addAlarm(@RequestBody AlarmParamVO4 param) {
        return AjaxResult.successProjectInfoData(
                "add alarm success",
                alarmService.addAlarmAuth(new Alarm(null, param.getSource(), param.getFirmId())));
    }

    @PostMapping("/deleteAlarm")
    public AjaxResult deleteAlarm(@RequestBody AlarmParamVO4 param) {
        return AjaxResult.successProjectInfoData(
                "delete alarm success",
                alarmService.deleteAlarmAuth(new Alarm(null, param.getSource(), param.getFirmId())));
    }

    @PostMapping("/updateAlarm")
    public AjaxResult updateAlarm(@RequestBody Alarm param) {
        return AjaxResult.successProjectInfoData(
                "update alarm success",
                alarmService.updateAlarmAuth(param));
    }
}
