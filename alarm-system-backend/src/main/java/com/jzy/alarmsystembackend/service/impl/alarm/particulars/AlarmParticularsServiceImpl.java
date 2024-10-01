package com.jzy.alarmsystembackend.service.impl.alarm.particulars;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzy.alarmsystembackend.annotations.Loggable;
import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.util.TimeEnum;
import com.jzy.alarmsystembackend.pojo.VO.alarm.particulars.AlarmParticularsParamVO1;
import com.jzy.alarmsystembackend.service.alarm.particulars.AlarmParticularsService;
import com.jzy.alarmsystembackend.service.impl.log.AlarmUpdateLogServiceImpl;
import com.jzy.alarmsystembackend.service.user.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AlarmParticularsServiceImpl implements AlarmParticularsService {

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @SuppressWarnings("all")
    @Autowired
    private InfoService infoService;

    /**
     * 查询所有报警信息
     *
     * @return List<Alarm>
     */
    @Deprecated
    @Override
    public List<AlarmParticulars> selectAllAlarm() {
        return alarmParticularsMapper.selectList(null);
    }

    /**
     * 查询所有报警信息
     * <img src='https://profile-avatar.csdnimg.cn/1dd662a7799a42f0a187eb69e74d73a3_weixin_52971316.jpg!1'>
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllAlarmAuth() {
        Integer firmId = infoService.getInfo().getFirmId();
        return firmId == -1 ? alarmParticularsMapper.selectList(null) : alarmParticularsMapper.selectAlarmParticularsAuth(firmId);

    }

    /**
     * 查询所有报警信息
     * 带分页
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public IPage<AlarmParticulars> selectAllAlarmPagedAuth(Long pageNum, Long pageSize) {
        Integer firmId = infoService.getInfo().getFirmId();
        Page<AlarmParticulars> page = new Page<>(pageNum, pageSize);
        return firmId == -1 ? alarmParticularsMapper.selectPage(page, null) : alarmParticularsMapper.selectAlarmParticularsAuth(page, firmId);
    }

    /**
     * 根据id查询报警信息
     *
     * @param id id
     * @return Alarm
     */
    @Override
    public AlarmParticulars selectAlarmById(Integer id) {
        return alarmParticularsMapper.selectById(id);
    }

    /**
     * 根据报警源和报警发生时间查询报警信息
     *
     * @param source    报警源
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
     * 查询所有 历史 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     *
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
     * 查询所有 历史 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimeAuth() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true)
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     *
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
     * 查询所有 实时 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimeAuth() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)))
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 30");
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查询所有 历史 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     *
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
     * 查询所有 历史 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public IPage<AlarmParticulars> selectAllHistoricalAlarmOrderedByOccurTimePagedAuth(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true)
                .orderByDesc(AlarmParticulars::getOccurTime);

        Page<AlarmParticulars> page = new Page<>(pageNum, pageSize);
        return alarmParticularsMapper.selectAlarmParticularsByWrapperPagedAuth(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有 实时 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     *
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
     * 查询所有 实时 报警信息，获取最近的 30 条报警，然后按时间 倒序 排列显示
     * 带有分页
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public IPage<AlarmParticulars> selectAllRealtimeAlarmOrderedByOccurTimePagedAuth(Long pageNum, Long pageSize) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)))
                .orderByDesc(AlarmParticulars::getOccurTime);
        Page<AlarmParticulars> page = new Page<>(pageNum, pageSize);
        return alarmParticularsMapper.selectAlarmParticularsByWrapperPagedAuth(page, lambdaQueryWrapper);
    }

    /**
     * 查询所有实时报警信息，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     *
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

    /**
     * 查询所有实时报警信息，依次按报警确认状态、报警恢复状态、报警级别、报警发生时间对所有报警进行排序，获取最近的 10 条报警
     * By firmId
     *
     * @return List<Alarm>
     */
    @Override
    public List<AlarmParticulars> selectRealtimeAlarmOrderedAuth() {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)))
                .orderByAsc(AlarmParticulars::getConfirmStatus)
                .orderByAsc(AlarmParticulars::getRecoverStatus)
                .orderByDesc(AlarmParticulars::getLevel)
                .orderByDesc(AlarmParticulars::getOccurTime)
                .last("LIMIT 10");
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 根据周月年查询报警报警数量
     *
     * @param timeType timeType
     * @return List<Map < String, Object>>
     */
    @Override
    public List<Map<String, Object>> selectAlarmNumsByTimeAuth(String timeType) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.equals(timeType, "year") && !Objects.equals(timeType, "month") && !Objects.equals(timeType, "week")) {
            return null;
        }
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        return alarmParticularsMapper.selectAlarmNumsByTimeAuth(timeType, lambdaQueryWrapper);
    }

    /**
     * 根据报警类型查询报警数量
     *
     * @param type type
     * @return Integer
     */
    @Override
    public Integer selectAlarmNumsByTypeAuth(String type) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .eq(AlarmParticulars::getType, type);
        return alarmParticularsMapper.selectAlarmNumsByWrapperAuth(lambdaQueryWrapper);
    }

    public static final Integer SuCode = new Integer(-1);

    /**
     * 查找某一天的报警数量
     *
     * @param time time
     * @return Integer
     */
    @Override
    public Integer selectAlarmNumsOneDayAuth(String time) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        if (SuCode.equals(infoService.getInfo().getFirmId())) {
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .like(AlarmParticulars::getOccurTime, time);
        return alarmParticularsMapper.selectAlarmNumsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查找某一天的历史报警数量
     * @param time time
     * @return Integer
     */
    @Override
    public Integer selectHistoricalAlarmNumsOneDay(String time) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .like(AlarmParticulars::getOccurTime, time)
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true);
        return alarmParticularsMapper.selectAlarmNumsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查找某一天的历史报警
     * TODO
     * @param time time
     * @return List<AlarmParticulars>
     */
    @Override
    public List<AlarmParticulars> selectHistoricalAlarmOneDay(String time) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (infoService.getInfo().getFirmId() != -1) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .like(AlarmParticulars::getOccurTime, time)
                .eq(AlarmParticulars::getConfirmStatus, true)
                .eq(AlarmParticulars::getRecoverStatus, true);
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查找某一天的实时报警数量
     * @param time time
     * @return Integer
     */
    @Override
    public Integer selectRealtimeAlarmNumsOneDay(String time) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.equals(infoService.getInfo().getFirmId(), -1)) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .like(AlarmParticulars::getOccurTime, time)
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)));
        return alarmParticularsMapper.selectAlarmNumsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 查找某一天的实时报警
     * TODO
     * @param time time
     * @return List<AlarmParticulars>
     */
    @Override
    public List<AlarmParticulars> selectRealtimeAlarmOneDay(String time) {
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.equals(infoService.getInfo().getFirmId(), -1)) {
            lambdaQueryWrapper
                    .apply("firm_id = {0}", infoService.getInfo().getFirmId());
        }
        lambdaQueryWrapper
                .like(AlarmParticulars::getOccurTime, time)
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)));
        return alarmParticularsMapper.selectAlarmParticularsByWrapperAuth(lambdaQueryWrapper);
    }

    /**
     * 确认警报
     *
     * @param param AlarmParticularsParamVO1
     * @return Integer
     */
//    @AlarmLog("确认警报")
    @Loggable(value = AlarmUpdateLogServiceImpl.class, args = "确认警报")
//    @AlarmType("确认警报")
    @Override
    public Integer alarmConfirm(AlarmParticularsParamVO1 param) {
        AlarmParticulars alarmParticulars = selectAlarmById(param.getId());
        if (alarmParticulars == null) {
            return null;
        }
        Boolean confirmStatus = alarmParticulars.getConfirmStatus();
        if (confirmStatus) {
            return null;
        } else {
            alarmParticulars.setConfirmStatus(true);
            alarmParticulars.setConfirmTime(new Timestamp(System.currentTimeMillis()));
            return alarmParticularsMapper.updateById(alarmParticulars);
        }
    }

    /**
     * 恢复警报
     *
     * @param param AlarmParticularsParamVO1
     * @return Integer
     */
    @Loggable(value = AlarmUpdateLogServiceImpl.class, args = "恢复警报")
    @Override
    public Integer alarmRecover(AlarmParticularsParamVO1 param) {
        AlarmParticulars alarmParticulars = selectAlarmById(param.getId());
        if (alarmParticulars == null) {
            return null;
        }
        Boolean recoverStatus = alarmParticulars.getRecoverStatus();
        if (recoverStatus) {
            return null;
        } else {
            alarmParticulars.setRecoverStatus(true);
            alarmParticulars.setRecoverTime(new Timestamp(System.currentTimeMillis()));
            return alarmParticularsMapper.updateById(alarmParticulars);
        }
    }

    /**
     *
     * 根据预设的timeEnum，设置到期处理机制
     * @param timeEnum timeEnum
     * @return Integer
     * @author jzy
     * @create 2024/9/17
     **/
    @Override
    public Integer alarmParticularsTimingHandling(Long number, TimeEnum timeEnum) {
        long currentTimeMillis = System.currentTimeMillis();

        TimeUnit timeUnit = null;
        if (timeEnum == TimeEnum.DAYS) {
            timeUnit = TimeUnit.DAYS;
        } else if (timeEnum == TimeEnum.HOURS) {
            timeUnit = TimeUnit.HOURS;
        } else if (timeEnum == TimeEnum.MINUTES) {
            timeUnit = TimeUnit.MINUTES;
        }
        // 根据传入的 timeEnum 计算时间差，并转换为毫秒
        long timeDifferenceMillis = TimeUnit.MILLISECONDS.convert(number, timeUnit);

        // 计算需要筛选的最早发生时间
        Timestamp thresholdTime = new Timestamp(currentTimeMillis - timeDifferenceMillis);

        // 更新操作，将符合条件的记录的 confirmStatus 和 recoverStatus 设置为 true
        LambdaQueryWrapper<AlarmParticulars> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .lt(AlarmParticulars::getOccurTime, thresholdTime)
                .and(wrapper -> wrapper
                        .ne(AlarmParticulars::getConfirmStatus, true)
                        .or(wrapper1 -> wrapper1
                                .ne(AlarmParticulars::getRecoverStatus, true)));
        List<AlarmParticulars> list = alarmParticularsMapper.selectList(lambdaQueryWrapper);

        int updatedCount = 0;

        for (AlarmParticulars record : list) {
            LambdaUpdateWrapper<AlarmParticulars> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            lambdaUpdateWrapper
                    .eq(AlarmParticulars::getId, record.getId());

            if (!record.getConfirmStatus()) {
                lambdaUpdateWrapper
                        .set(AlarmParticulars::getConfirmStatus, true)
                        .set(AlarmParticulars::getConfirmTime, new Timestamp(currentTimeMillis));
            }
            if (!record.getRecoverStatus()) {
                lambdaUpdateWrapper
                        .set(AlarmParticulars::getRecoverStatus, true)
                        .set(AlarmParticulars::getRecoverTime, new Timestamp(currentTimeMillis));
            }

            // 执行更新操作，更新单条记录
            updatedCount += alarmParticularsMapper.update(null, lambdaUpdateWrapper);
        }

        return updatedCount;
    }
}
