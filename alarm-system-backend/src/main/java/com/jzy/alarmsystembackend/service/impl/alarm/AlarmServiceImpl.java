package com.jzy.alarmsystembackend.service.impl.alarm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.mapper.alarm.AlarmMapper;
import com.jzy.alarmsystembackend.mapper.log.AlarmUpdateLogMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.Alarm;
import com.jzy.alarmsystembackend.service.alarm.AlarmService;
import com.jzy.alarmsystembackend.service.user.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmMapper alarmMapper;

    @Autowired
    private InfoService infoService;

    @Autowired
    private AlarmUpdateLogMapper alarmUpdateLogMapper;

    /**
     * 查询所有报警器
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public List<Alarm> selectAllAlarmAuth() {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getFirmId, infoService.getInfo().getFirmId());
        return alarmMapper.selectList(lambdaQueryWrapper);
    }


    /**
     * 查询所有报警器
     * 带分页
     * By firmId
     *
     * @param pageNum  pageNum
     * @param pageSize pageSize
     * @return IPage<Alarm>
     */
    @Override
    public IPage<Alarm> selectAllAlarmPagedAuth(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getFirmId, infoService.getInfo().getFirmId());
        Page<Alarm> page = new Page<>(pageNum, pageSize);
        return alarmMapper.selectPage(page, lambdaQueryWrapper);
    }

    /**
     * 根据id查找报警器
     *
     * @param id id
     * @return Alarm
     */
    @Override
    public Alarm selectAlarmById(Integer id) {
        return alarmMapper.selectById(id);
    }

    /**
     * 根据source查找报警器
     *
     * @param source source
     * @return Alarm
     */
    @Override
    public Alarm selectAlarmBySource(String source) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Alarm::getSource, source);
        return alarmMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 新增一个报警器
     * By firmId
     *
     * @param alarm alarm
     * @return Integer
     */
    @Override
    public Integer addAlarmAuth(Alarm alarm) {
        log.info(String.valueOf(alarm.getFirmId()));
        return alarmMapper.insert(alarm);
    }

    /**
     * 删除一个报警器
     * By firmId
     *
     * @param alarm alarm
     * @return Integer
     */
    @Override
    public Integer deleteAlarmAuth(Alarm alarm) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Alarm::getSource, alarm.getSource())
                .eq(Alarm::getFirmId, alarm.getFirmId());
        return alarmMapper.delete(lambdaQueryWrapper);
    }

    /**
     * 修改一个报警器信息
     * By firmId
     *
     * @param alarm alarm
     * @return Integer
     */
    @Override
    public Integer updateAlarmAuth(Alarm alarm) {
        return alarmMapper.updateById(alarm);
    }
}
