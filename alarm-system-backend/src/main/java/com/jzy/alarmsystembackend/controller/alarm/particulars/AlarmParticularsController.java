package com.jzy.alarmsystembackend.controller.alarm.particulars;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO2;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO3;
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
public class AlarmParticularsController {

    @Autowired
    private AlarmParticularsService alarmParticularsService;


    @PostMapping("/test")
    public String test() {
        return "test";
    }

    /**
     * 查询所有报警
     * @return AjaxResult
     */
    @PostMapping("/selectAllAlarm")
    public AjaxResult selectAllAlarm() {
        return AjaxResult.successProjectInfoData(
                "select all alarm success",
                alarmParticularsService.selectAllAlarmAuth());
    }

    /**
     * 查询所有报警
     * 带分页
     * @return AjaxResult
     */
    @PostMapping("/selectAllAlarmPaged")
    public AjaxResult selectAllAlarm(@RequestBody AlarmParticularsParamVO3 param) {
        return AjaxResult.successProjectInfoData(
                "select all alarm paged success",
                alarmParticularsService.selectAllAlarmPagedAuth(param.getPageNum(), param.getPageSize()));
    }

    /**
     * 根据id查询所有报警
     * @param param AlarmParamVO1
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmById")
    public AjaxResult selectAlarmById(@RequestBody AlarmParticularsParamVO1 param) {
        return AjaxResult.successProjectInfoData(
                "select alarm by id success",
                alarmParticularsService.selectAlarmById(param.getId()));
    }

    /**
     * 根据报警源和报警发生时间查询报警
     * @param param AlarmParamVO2
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmBySourceAndOccurTime")
    public AjaxResult selectAlarmBySourceAndOccurTime(@RequestBody AlarmParticularsParamVO2 param) {
        System.out.println(param.getOccurTime());
        return AjaxResult.successProjectInfoData(
                "select alarm by source and occur_time success",
                alarmParticularsService.selectAlarmBySourceAndOccurTime(param.getSource(), param.getOccurTime()));
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return AjaxResult
     */
    @PostMapping("/selectAllHistoricalAlarmOrderedByOccurTime")
    public AjaxResult selectAllHistoricalAlarmOrderedByOccurTime() {
        return AjaxResult.successProjectInfoData(
                "select all historical alarm ordered by occurTime success",
                alarmParticularsService.selectAllHistoricalAlarmOrderedByOccurTimeAuth());
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return AjaxResult
     */
    @PostMapping("/selectAllRealtimeAlarmOrderedByOccurTime")
    public AjaxResult selectAllRealtimeAlarmOrderedByOccurTime() {
        return AjaxResult.successProjectInfoData(
                "select all realtime alarm ordered by occurTime success",
                alarmParticularsService.selectAllRealtimeAlarmOrderedByOccurTimeAuth());
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return AjaxResult
     */
    @PostMapping("/selectAllHistoricalAlarmOrderedByOccurTimePaged")
    public AjaxResult selectAllHistoricalAlarmOrderedByOccurTimePaged(@RequestBody AlarmParticularsParamVO3 param) {
        log.info(param.getPageNum() + " " + param.getPageSize());
        return AjaxResult.successProjectInfoData(
                "select all historical alarm ordered by occurTime aged success",
                alarmParticularsService.selectAllHistoricalAlarmOrderedByOccurTimePagedAuth(param.getPageNum(), param.getPageSize()));
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return AjaxResult
     */
    @PostMapping("/selectAllRealtimeAlarmOrderedByOccurTimePaged")
    public AjaxResult selectAllRealtimeAlarmOrderedByOccurTimePaged(@RequestBody AlarmParticularsParamVO3 param) {
        return AjaxResult.successProjectInfoData(
                "select all realtime alarm ordered by occurTime paged success",
                alarmParticularsService.selectAllRealtimeAlarmOrderedByOccurTimePagedAuth(param.getPageNum(), param.getPageSize()));
    }

    /**
     * 查询所有实时报警，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     * @return AjaxResult
     */
    @PostMapping("/selectRealtimeAlarmOrdered")
    public AjaxResult selectRealtimeAlarmOrdered() {
        return AjaxResult.successProjectInfoData(
                "select realtime alarm ordered success",
                alarmParticularsService.selectRealtimeAlarmOrderedAuth()
        );
    }
}
