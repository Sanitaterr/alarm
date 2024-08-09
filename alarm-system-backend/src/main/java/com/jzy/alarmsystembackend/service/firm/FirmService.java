package com.jzy.alarmsystembackend.service.firm;

import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.DO.Firm;

import java.util.List;

public interface FirmService {
    Integer addFirm(Firm Firm);
    List<AlarmParticulars> selectAlarmByFirmId(Integer firmId);
}
