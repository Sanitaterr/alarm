package com.jzy.alarmsystembackend.controller.firm;

import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.pojo.VO.firm.FirmParamVO1;
import com.jzy.alarmsystembackend.service.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/firm")
public class FirmController {

    @Autowired
    private FirmService firmService;

    @PostMapping("/addFirm")
    private AjaxResult addFirm() {
        return null;
    }

    @PostMapping("/selectAlarmByFirmId")
    public AjaxResult selectAlarmByFirmId(@RequestBody FirmParamVO1 param) {
        List<AlarmParticulars> alarms = firmService.selectAlarmByFirmId(param.getFirmId());
        return AjaxResult.successProjectInfoData("select alarm by firmId success", alarms);
    }
}
