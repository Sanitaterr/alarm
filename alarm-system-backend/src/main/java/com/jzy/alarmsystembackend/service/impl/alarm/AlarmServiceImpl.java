package com.jzy.alarmsystembackend.service.impl.alarm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.Alarm;
import com.jzy.alarmsystembackend.pojo.DO.AlarmParticulars;
import com.jzy.alarmsystembackend.service.alarm.AlarmService;
import com.jzy.alarmsystembackend.service.user.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @Autowired
    private InfoService infoService;

    /**
     * 查询所有报警
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllAlarm() {
        return alarmParticularsMapper.selectList(null);
    }

    /**
     * 查询所有报警
     * By firmId
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllAlarmAuth() {
        Integer firmId = infoService.getFirmId();
        return alarmParticularsMapper.selectAlarmParticularsAuth(firmId);

    }

    /**
     * 根据id查询报警
     * @param id id
     * @return Alarm
     */
    @Override
    public AlarmParticulars selectAlarmById(Integer id) {
        return alarmParticularsMapper.selectById(id);
    }

    /**
     * 根据报警源和报警发生时间查询报警
     * @param source 报警源
     * @param occurTime 报警发生时间
     * @return Alarm
     */
    @Override
    public AlarmParticulars selectAlarmBySourceAndOccurTime(String source, Timestamp occurTime) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmParticulars::getSource, source)
                .eq(AlarmParticulars::getOccurTime, occurTime);
        return alarmParticularsMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTime() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true)
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * By firmId
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimeAuth() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .apply("firm_id = " + infoService.getFirmId())
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true)
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTime() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(AlarmParticulars::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(AlarmParticulars::getRecoverStatus, true))
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * By firmId
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimeAuth() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .apply("firm_id = " + infoService.getFirmId())
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)))
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return List<Alarm>
     */
    @Override
    public IPage<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true)
                .orderByDesc(AlarmParticulars::getOccurTime);

        Page<AlarmParticulars> page = new Page<>(pageNum, pageSize);
        return alarmParticularsMapper.selectPage(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return List<Alarm>
     */
    @Override
    public IPage<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(AlarmParticulars::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(AlarmParticulars::getRecoverStatus, true))
                .orderByDesc(AlarmParticulars::getOccurTime);
        Page<AlarmParticulars> page = new Page<>(pageNum, pageSize);
        return alarmParticularsMapper.selectPage(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有实时报警，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectRealtimeAlarmOrdered() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(AlarmParticulars::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(AlarmParticulars::getRecoverStatus, true))
                .orderByAsc(AlarmParticulars::getConfirmStatus)
                .orderByAsc(AlarmParticulars::getRecoverStatus)
                .orderByDesc(AlarmParticulars::getLevel)
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 10");
        return alarmParticularsMapper.selectList(lambdaQueryWrapper);
    }

}
