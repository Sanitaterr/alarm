package com.jzy.alarmsystembackend.service.impl.alarm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.mapper.AlarmMapper;
import com.jzy.alarmsystembackend.pojo.DO.Alarm;
import com.jzy.alarmsystembackend.pojo.VO.AjaxResult;
import com.jzy.alarmsystembackend.service.alarm.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmMapper alarmMapper;

    /**
     * 查询所有报警
     * @return List<Alarm>
     */
    @Override
    public List<Alarm> selectAllAlarm() {
        return alarmMapper.selectList(null);
    }

    /**
     * 根据id查询所有报警
     * @param id id
     * @return Alarm
     */
    @Override
    public Alarm selectAlarmById(Integer id) {
        return alarmMapper.selectById(id);
    }

    /**
     * 根据报警源和报警发生时间查询报警
     * @param source 报警源
     * @param occurTime 报警发生时间
     * @return Alarm
     */
    @Override
    public Alarm selectAlarmBySourceAndOccurTime(String source, Timestamp occurTime) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Alarm::getSource, source)
                .eq(Alarm::getOccurTime, occurTime);
        return alarmMapper.selectOne(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return List<Alarm>
     */
    @Override
    public List<Alarm> selectAllHistoricalAlarmOrderedByOccurTime() {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Alarm::getConfirmStatus, true)
                .eq(Alarm::getRecoverStatus, true)
                .orderByDesc(Alarm::getOccurTime)
                .last("LIMIT 30");
        return alarmMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * @return List<Alarm>
     */
    @Override
    public List<Alarm> selectAllRealtimeAlarmOrderedByOccurTime() {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(Alarm::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(Alarm::getRecoverStatus, true))
                .orderByDesc(Alarm::getOccurTime)
                .last("LIMIT 30");
        return alarmMapper.selectList(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return List<Alarm>
     */
    @Override
    public IPage<Alarm> selectAllHistoricalAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(Alarm::getConfirmStatus, true)
                .eq(Alarm::getRecoverStatus, true)
                .orderByDesc(Alarm::getOccurTime);

        Page<Alarm> page = new Page<>(pageNum, pageSize);
        return alarmMapper.selectPage(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * @return List<Alarm>
     */
    @Override
    public IPage<Alarm> selectAllRealtimeAlarmOrderedByOccurTimePaged(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(Alarm::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(Alarm::getRecoverStatus, true))
                .orderByDesc(Alarm::getOccurTime);
        Page<Alarm> page = new Page<>(pageNum, pageSize);
        return alarmMapper.selectPage(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有实时报警，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     * @return List<Alarm>
     */
    @Override
    public List<Alarm> selectRealtimeAlarmOrdered() {
        LambdaQueryWrapper<Alarm> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .ne(Alarm::getConfirmStatus, true)
                .or(wrapper -> wrapper.ne(Alarm::getRecoverStatus, true))
                .orderByAsc(Alarm::getConfirmStatus)
                .orderByAsc(Alarm::getRecoverStatus)
                .orderByDesc(Alarm::getLevel)
                .orderByDesc(Alarm::getOccurTime)
                .last("LIMIT 10");
        return alarmMapper.selectList(lambdaQueryWrapper);
    }

}
