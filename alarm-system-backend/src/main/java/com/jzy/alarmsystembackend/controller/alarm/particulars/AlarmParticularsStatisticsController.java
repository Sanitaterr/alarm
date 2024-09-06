package com.jzy.alarmsystembackend.controller.alarm.particulars;

import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO4;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO5;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO6;
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
public class AlarmParticularsStatisticsController {

    @Autowired
    private AlarmParticularsService alarmParticularsService;

    /**
     * 根据周月年查询报警报警数量
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmNumsByTime")
    public AjaxResult selectAlarmNumsByTime(@RequestBody AlarmParticularsParamVO4 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm nums by time success",
                alarmParticularsService.selectAlarmNumsByTimeAuth(param.getTimeType()));
    }

    /**
     * 根据报警类型查询报警数量
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmNumsByType")
    public AjaxResult selectAlarmNumsByType(@RequestBody AlarmParticularsParamVO5 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm nums by type success",
                alarmParticularsService.selectAlarmNumsByTypeAuth(param.getType()));
    }

    /**
     * 查找某一天的报警数量
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmNumsOneDay")
    public AjaxResult selectAlarmNumsOneDay(@RequestBody AlarmParticularsParamVO6 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm nums one day success",
                alarmParticularsService.selectAlarmNumsOneDayAuth(param.getTime()));
    }

    /**
     * 查找某一天的历史报警数量
     * @return AjaxResult
     */
    @PostMapping("/selectHistoricalAlarmNumsOneDay")
    public AjaxResult selectHistoricalAlarmNumsOneDay(@RequestBody AlarmParticularsParamVO6 param) {
        return AjaxResult.successProjectInfoData(
                "select historical alarm nums one day success",
                alarmParticularsService.selectHistoricalAlarmNumsOneDay(param.getTime()));
    }

    /**
     * 查找某一天的历史报警
     * @return AjaxResult
     */
    @PostMapping("/selectHistoricalAlarmOneDay")
    public AjaxResult selectHistoricalAlarmOneDay(@RequestBody AlarmParticularsParamVO6 param) {
        return AjaxResult.successProjectInfoData(
                "select historical alarm one day success",
                alarmParticularsService.selectHistoricalAlarmOneDay(param.getTime()));
    }

    /**
     * 查找某一天的实时报警数量
     * @return AjaxResult
     */
    @PostMapping("/selectRealtimeAlarmNumsOneDay")
    public AjaxResult selectRealtimeAlarmNumsOneDay(@RequestBody AlarmParticularsParamVO6 param) {
        return AjaxResult.successProjectInfoData(
                "select realtime alarm nums one day success",
                alarmParticularsService.selectRealtimeAlarmNumsOneDay(param.getTime()));
    }

    /**
     * 查找某一天的实时报警
     * @return AjaxResult
     */
    @PostMapping("/selectRealtimeAlarmOneDay")
    public AjaxResult selectRealtimeAlarmOneDay(@RequestBody AlarmParticularsParamVO6 param) {
        return AjaxResult.successProjectInfoData(
                "select realtime alarm one day success",
                alarmParticularsService.selectRealtimeAlarmOneDay(param.getTime()));
    }
}
