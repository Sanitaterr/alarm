package com.jzy.alarmsystembackend.controller.alarm.particulars;

import com.jzy.alarmsystembackend.pojo.DO.alarm.Alarm;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.alarm.AlarmParamVO4;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.service.alarm.particulars.AlarmParticularsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JZY
 * @version 1.0
 * Create by 2024/11/3 10:53
 * @Description: TODO
 */
@Slf4j
@RestController
public class MapController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate; // 使用WebSocket的SimMessagingTemplate

    /**
     *
     */
    public void notifyFrontend(AlarmParticulars alarmParticulars) {
        // 使用WebSocket向前端推送数据
        messagingTemplate.convertAndSend("/topic/alarm", alarmParticulars);
    }
}
