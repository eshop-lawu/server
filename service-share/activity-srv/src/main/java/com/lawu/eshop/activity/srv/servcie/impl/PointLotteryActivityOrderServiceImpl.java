package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.activity.constants.PointLotteryActivityOrderStatusEnum;
import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryAttentParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityOrderBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryAttentBO;
import com.lawu.eshop.activity.srv.converter.PointDrawLotteryActivityOrderConverter;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityDOExample;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityOrderDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityRecordDOExample;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityReportDO;
import com.lawu.eshop.activity.srv.domain.PointLotteryActivityReportDOExample;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityOrderDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.PointLotteryActivityReportDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.PointLotteryActivityDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.PointLotteryActivityOrderService;
import com.lawu.eshop.activity.srv.servcie.PointLotteryNumService;
import com.lawu.eshop.activity.srv.servcie.RichPowerTaskRecordService;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.mq.dto.activity.reply.PointLotteryReply;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/2/5.
 */
@Service
public class PointLotteryActivityOrderServiceImpl implements PointLotteryActivityOrderService {

    @Autowired
    private PointLotteryActivityOrderDOMapper pointLotteryActivityOrderDOMapper;

    @Autowired
    private PointLotteryActivityDOMapperExtend pointLotteryActivityDOMapperExtend;

    @Autowired
    private PointLotteryActivityDOMapper pointLotteryActivityDOMapper;

    @Autowired
    private PointLotteryActivityRecordDOMapper pointLotteryActivityRecordDOMapper;

    @Autowired
    @Qualifier("pointLotteryTransactionMainServiceImpl")
    private TransactionMainService<PointLotteryReply> pointLotteryTransactionMainServiceImpl;

    @Autowired
    private PointLotteryActivityReportDOMapper pointLotteryActivityReportDOMapper;

    @Autowired
    private PointLotteryNumService pointLotteryNumService;

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long savePointLotteryActivityOrder(PointLotteryAttentParam param) {
        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setUserNum(param.getUserNum());
        orderDO.setMobile(param.getMobile());
        orderDO.setPointLotteryActivityId(param.getPointLotteryActivityId());
        orderDO.setAttentCount(param.getAttentCount());
        orderDO.setPayPoint(param.getPayPoint());
        orderDO.setStatus(param.getStatusEnum().getVal());
        orderDO.setGmtCreate(new Date());
        pointLotteryActivityOrderDOMapper.insertSelective(orderDO);
        pointLotteryTransactionMainServiceImpl.sendNotice(orderDO.getId());
        return orderDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateLotteryOrderAndRecord(Long id, PointLotteryActivityOrderStatusEnum statusEnum) {
        PointLotteryActivityOrderDO orderDO = new PointLotteryActivityOrderDO();
        orderDO.setId(id);
        orderDO.setStatus(statusEnum.getVal());
        orderDO.setGmtModified(new Date());
        pointLotteryActivityOrderDOMapper.updateByPrimaryKeySelective(orderDO);

        if (statusEnum.equals(PointLotteryActivityOrderStatusEnum.SUCCESS)) {
            orderDO = pointLotteryActivityOrderDOMapper.selectByPrimaryKey(id);
            //更新积分抽奖次数
            PointLotteryActivityReportDOExample reportDOExample = new PointLotteryActivityReportDOExample();
            reportDOExample.createCriteria().andUserNumEqualTo(orderDO.getUserNum());
            List<PointLotteryActivityReportDO> reportDOS = pointLotteryActivityReportDOMapper.selectByExample(reportDOExample);
            PointLotteryActivityReportDO reportDO = new PointLotteryActivityReportDO();
            if (reportDOS.isEmpty()) {
                reportDO.setUserNum(orderDO.getUserNum());
                reportDO.setLotteryTimes(orderDO.getAttentCount());
                reportDO.setGmtCreate(new Date());
                pointLotteryActivityReportDOMapper.insertSelective(reportDO);
            } else {
                reportDO.setLotteryTimes(reportDOS.get(0).getLotteryTimes() + orderDO.getAttentCount());
                reportDO.setGmtModified(new Date());
                pointLotteryActivityReportDOMapper.updateByExampleSelective(reportDO, reportDOExample);
            }

            //保存积分抽奖记录
            for (int i = 0; i < orderDO.getAttentCount(); i++) {
                while (true) {
                    Result<Long> lotteryNumResult = pointLotteryNumService.getNewPointLotteryNum(orderDO.getPointLotteryActivityId());
                    Long pointLotteryActivityOrderId = savePointLotteryActivityRecord(orderDO, lotteryNumResult.getModel().intValue());
                    if (pointLotteryActivityOrderId > 0) {
                        pointLotteryActivityDOMapperExtend.updatePointLotteryActivityAttentNumber(orderDO.getPointLotteryActivityId());
                        break;
                    }
                }
            }

            //玩游戏完成瑞奇岛动力任务
            RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
            taskRecordParam.setMemberNum(orderDO.getUserNum());
            taskRecordParam.setType(PowerTaskTypeEnum.GAME);
            richPowerTaskRecordService.saveRichPowerTaskRecord(taskRecordParam);
        }
    }

    @Override
    public PointLotteryActivityOrderBO getPointLotteryActivityOrder(Long id) {
        PointLotteryActivityOrderDO orderDO = pointLotteryActivityOrderDOMapper.selectByPrimaryKey(id);
        return PointDrawLotteryActivityOrderConverter.convertBO(orderDO);
    }

    @Override
    public PointLotteryAttentBO getPointLotteryAttentInfo(Long pointLotteryActivityOrderId) {
        PointLotteryActivityRecordDOExample recordDOExample = new PointLotteryActivityRecordDOExample();
        recordDOExample.createCriteria().andPointLotteryActivityOrderIdEqualTo(pointLotteryActivityOrderId);
        List<PointLotteryActivityRecordDO> recordDOS = pointLotteryActivityRecordDOMapper.selectByExample(recordDOExample);
        PointLotteryAttentBO attentBO = new PointLotteryAttentBO();
        attentBO.setLotteryCnt(recordDOS.size());
        if (recordDOS.size() == 1) {
            attentBO.setLotteryNum(String.valueOf(recordDOS.get(0).getLotteryNum()));
        }

        PointLotteryActivityDOExample activityDOExample = new PointLotteryActivityDOExample();
        activityDOExample.createCriteria().andIdEqualTo(recordDOS.get(0).getPointLotteryActivityId());
        List<PointLotteryActivityDO> activityDOS = pointLotteryActivityDOMapper.selectByExample(activityDOExample);
        if (!activityDOS.isEmpty()) {
            attentBO.setLotteryTime(activityDOS.get(0).getLotteryTime());
        }
        return attentBO;
    }

    private Long savePointLotteryActivityRecord(PointLotteryActivityOrderDO orderDO, int lotteryNum) {
        try {
            PointLotteryActivityRecordDO recordDO = new PointLotteryActivityRecordDO();
            recordDO.setUserNum(orderDO.getUserNum());
            recordDO.setMobile(orderDO.getMobile());
            recordDO.setLotteryNum(lotteryNum);
            recordDO.setPointLotteryActivityId(orderDO.getPointLotteryActivityId());
            recordDO.setPointLotteryActivityOrderId(orderDO.getId());
            recordDO.setStatus(PointLotteryActivityRecordStatusEnum.NOT_WINNING.getVal());
            recordDO.setGmtCreate(new Date());
            pointLotteryActivityRecordDOMapper.insertSelective(recordDO);
            return recordDO.getId();
        } catch (Exception e) {
            return 0L;
        }
    }

}
