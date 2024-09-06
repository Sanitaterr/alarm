package com.jzy.alarmsystembackend.service.impl.firm;

import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.mapper.FirmMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.DO.Firm;
import com.jzy.alarmsystembackend.service.firm.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmMapper firmMapper;

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @Override
    public Integer addFirm(Firm Firm) {
        return null;
    }

    @Override
    public List<AlarmParticulars> selectAlarmByFirmId(Integer firmId) {
        return alarmParticularsMapper.selectAlarmParticularsAuth(firmId);
    }
}
