package com.jzy.alarmsystembackend.service.impl.firm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jzy.alarmsystembackend.mapper.AlarmMapper;
import com.jzy.alarmsystembackend.mapper.FirmMapper;
import com.jzy.alarmsystembackend.pojo.DO.Alarm;
import com.jzy.alarmsystembackend.pojo.DO.Firm;
import com.jzy.alarmsystembackend.service.firm.FirmService;
import com.jzy.alarmsystembackend.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FirmServiceImpl implements FirmService {

    @Autowired
    private FirmMapper firmMapper;

    @Autowired
    private AlarmMapper alarmMapper;

    @Override
    public Integer addFirm(Firm Firm) {
        return null;
    }

    @Override
    public List<Alarm> selectAlarmByFirmId(Integer firmId) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getFirmId, firmId);
        return alarmMapper.selectList(lambdaQueryWrapper);
    }
}
