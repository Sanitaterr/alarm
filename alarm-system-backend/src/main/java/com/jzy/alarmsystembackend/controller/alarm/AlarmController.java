package com.jzy.alarmsystembackend.controller.alarm;

import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO1;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO2;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO3;
import com.jzy.alarmsystembackend.service.alarm.AlarmService;
import com.jzy.alarmsystembackend.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @Autowired
    private AlarmService alarmService;


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
                alarmService.selectAllAlarm());
    }

    /**
     * 根据id查询所有报警
     * @param param AlarmParamVO1
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmById")
    public AjaxResult selectAlarmById(@RequestBody AlarmParamVO1 param) {
        return AjaxResult.successProjectInfoData(
                String.format("select alarm by id: '%d' success", param.getId()),
                alarmService.selectAlarmById(param.getId()));
    }

    /**
     * 根据报警源和报警发生时间查询报警
     * @param param AlarmParamVO2
     * @return AjaxResult
     */
    @PostMapping("/selectAlarmBySourceAndOccurTime")
    public AjaxResult selectAlarmBySourceAndOccurTime(@RequestBody AlarmParamVO2 param) {
        System.out.println(param.getOccurTime());
        return AjaxResult.successProjectInfoData(
                String.format("select alarm by source: '%s' and occur_time: '%s' success", param.getSource(), param.getOccurTime()),
                alarmService.selectAlarmBySourceAndOccurTime(param.getSource(), param.getOccurTime()));
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return AjaxResult
     */
    @PostMapping("/selectAllHistoricalAlarmOrderedByOccurTime")
    public AjaxResult selectAllHistoricalAlarmOrderedByOccurTime() {
        return AjaxResult.successProjectInfoData(
                "select all historical alarm ordered by occurTime success",
                alarmService.selectAllHistoricalAlarmOrderedByOccurTime());
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return AjaxResult
     */
    @PostMapping("/selectAllRealtimeAlarmOrderedByOccurTime")
    public AjaxResult selectAllRealtimeAlarmOrderedByOccurTime() {
        return AjaxResult.successProjectInfoData(
                "select all realtime alarm ordered by occurTime success",
                alarmService.selectAllRealtimeAlarmOrderedByOccurTime());
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return AjaxResult
     */
    @PostMapping("/selectAllHistoricalAlarmOrderedByOccurTimePaged")
    public AjaxResult selectAllHistoricalAlarmOrderedByOccurTimePaged(@RequestBody AlarmParamVO3 param) {
        log.info(param.getPageNum() + " " + param.getPageSize());
        return AjaxResult.successProjectInfoData(
                "select all historical alarm ordered by occurTime aged success",
                alarmService.selectAllHistoricalAlarmOrderedByOccurTimePaged(param.getPageNum(), param.getPageSize()));
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return AjaxResult
     */
    @PostMapping("/selectAllRealtimeAlarmOrderedByOccurTimePaged")
    public AjaxResult selectAllRealtimeAlarmOrderedByOccurTimePaged(@RequestBody AlarmParamVO3 param) {
        return AjaxResult.successProjectInfoData(
                "select all realtime alarm ordered by occurTime paged success",
                alarmService.selectAllRealtimeAlarmOrderedByOccurTimePaged(param.getPageNum(), param.getPageSize()));
    }

    /**
     * 查询所有实时报警，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     * @return AjaxResult
     */
    @PostMapping("/selectRealtimeAlarmOrdered")
    public AjaxResult selectRealtimeAlarmOrdered() {
        return AjaxResult.successProjectInfoData(
                "select realtime alarm ordered success",
                alarmService.selectRealtimeAlarmOrdered()
        );
    }
}
