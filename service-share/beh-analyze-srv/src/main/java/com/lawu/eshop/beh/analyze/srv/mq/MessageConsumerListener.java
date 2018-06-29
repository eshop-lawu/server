package com.lawu.eshop.beh.analyze.srv.mq;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.beh.analyze.constants.AbnormalEnum;
import com.lawu.eshop.beh.analyze.param.AbnormalAddParam;
import com.lawu.eshop.beh.analyze.param.AbnormalRecordParam;
import com.lawu.eshop.beh.analyze.srv.AnalyzeSrvConfig;
import com.lawu.eshop.beh.analyze.srv.service.AbnormalRecordService;
import com.lawu.eshop.beh.analyze.srv.service.RegMachineService;
import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.mq.dto.user.RegNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.synchronization.lock.impl.LockConstant;
import com.lawu.eshop.synchronization.lock.impl.LockService;
import com.lawu.framework.web.Result;
import com.lawu.mq.message.impl.AbstractMessageConsumerListener;
import com.lawu.utils.DateUtil;

/**
 *
 */
@Service
public class MessageConsumerListener extends AbstractMessageConsumerListener {

    private static Logger logger = LoggerFactory.getLogger(MessageConsumerListener.class);

    @Autowired
    private RegMachineService regMachineService;

    @Autowired
    private AnalyzeSrvConfig analyzeSrvConfig;

    @Autowired
    private AbnormalRecordService abnormalRecordService;

    @Autowired
    private LockService lockService;

    @Override
    public void consumeMessage(String topic, String tags, Object message) {
        if (MqConstant.TOPIC_USER_SRV.equals(topic)) {
            if (MqConstant.TAG_REG.equals(tags)) {
                RegNotification notification = (RegNotification) message;
                if (StringUtils.isEmpty(notification.getInviteNum()) || notification.getGmtCreate() == null) {
                    //没有推荐人
                    return;
                }
                //记录redis
                AbnormalInfoParam infoParam = new AbnormalInfoParam();
                infoParam.setUserNum(notification.getInviteNum());
                infoParam.setShortCount(analyzeSrvConfig.getShortFrequencyCount());
                infoParam.setShortTime(analyzeSrvConfig.getShortFrequencyTime());
                infoParam.setManyShortTime(analyzeSrvConfig.getShortManyFrequencyTime());
                infoParam.setLongCount(analyzeSrvConfig.getLongFrequencyCount());
                infoParam.setLongTime(analyzeSrvConfig.getLongFrequencyTime());
                infoParam.setManyLongTime(analyzeSrvConfig.getLongManyFrequencyTime());
                infoParam.setOneDayTime(analyzeSrvConfig.getOneDayFrequencyTime());

                //短高频，长高频，一天注册上限，多次短高，多次长高
                regMachineService.addHfRegRedisRecord(infoParam);
                //凌晨1点--7点
                String startTime = DateUtil.getDateFormat(notification.getGmtCreate(), "yyyy-MM-dd " + analyzeSrvConfig.getEarlyStartTime());
                String endTime = DateUtil.getDateFormat(notification.getGmtCreate(), "yyyy-MM-dd " + analyzeSrvConfig.getEarlyEndTime());
                Date startDate = DateUtil.formatDate(startTime, "yyyy-MM-dd HH:mm:ss" );
                Date endDate = DateUtil.formatDate(endTime, "yyyy-MM-dd HH:mm:ss" );
                if (DateUtil.belongCalendar(notification.getGmtCreate(), startDate, endDate)) {
                    //凌晨注册
                    EarlyAbnormalParam earlyAbnormalParam = new EarlyAbnormalParam();
                    earlyAbnormalParam.setUserNum(notification.getUserNum());
                    Date earlyTime = DateUtil.getDayAfter(DateUtil.formatDate(startTime,"yyyy-MM-dd HH:mm:ss"));
                    earlyAbnormalParam.setTime(earlyTime);
                    regMachineService.addEarlyHfRedisRecord(earlyAbnormalParam);
                }

                AbnormalRecordParam recordParam = isAbnormalAccount(notification.getInviteNum());
                AbnormalAddParam param = new AbnormalAddParam();
                param.setAccount(notification.getInviteAccount());
                param.setUserNum(notification.getInviteNum());
                if (notification.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                    param.setType(UserTypeEnum.MEMBER);
                } else {
                    param.setType(UserTypeEnum.MERCHANT);
                }
                if (recordParam != null) {
                    param.setShortHf(recordParam.getShortHfFlag());
                    param.setManyShortHf(recordParam.getManyShortHfFlag());
                    param.setLongHf(recordParam.getLongHfFlag());
                    param.setManyLongHf(recordParam.getManyLongHfFlag());
                    param.setOneDayHf(recordParam.getOneDayFlag());
                    param.setEarlyHf(recordParam.getEarlyFlag());
                    param.setIpHf(false);
                    if (AbnormalEnum.MAYBE_ABNORMAL.equals(recordParam.getAbnormal())) {
                        //存在异常状态
                        abnormalRecordService.addOrUpdateAbnormalRecord(param);
                    }
                }

            }
        }
    }

    /**
     * 判断是否存在异常条件
     *
     * @param num
     * @return
     */
    private AbnormalRecordParam isAbnormalAccount(String num) {
        boolean isLock = lockService.tryLock(LockConstant.LockModule.LOCK_BEH_ANALYZE_SRV, LockConstant.QUERY_ABNORMAL_CACHE.concat(num));
        if (!isLock) {
            return  null;
        }
        Result<AbnormalRedisCountDTO> countDTOResult = regMachineService.getAbnormalCount(num);
        Boolean shortHfFlag = countDTOResult.getModel().getShortHfCount() > analyzeSrvConfig.getShortFrequencyCount();
        Boolean longHfFlag = countDTOResult.getModel().getLongHfCount() > analyzeSrvConfig.getLongFrequencyCount();
        Boolean manyShortHfFlag = countDTOResult.getModel().getManyShortHfCount() > analyzeSrvConfig.getShortManyFrequencyCount();
        Boolean manyLongHfFlag = countDTOResult.getModel().getManyLongHfCount() > analyzeSrvConfig.getLongManyFrequencyCount();
        Boolean oneDayFlag = countDTOResult.getModel().getOneDayCount() > analyzeSrvConfig.getOneDayFrequencyCount();
        Boolean earlyFlag = countDTOResult.getModel().getEarlyCount() > analyzeSrvConfig.getEarlyFrequencyCount();
        AbnormalRecordParam param = new AbnormalRecordParam();
        param.setLongHfFlag(longHfFlag);
        param.setShortHfFlag(shortHfFlag);
        param.setManyLongHfFlag(manyLongHfFlag);
        param.setManyShortHfFlag(manyShortHfFlag);
        param.setOneDayFlag(oneDayFlag);
        param.setEarlyFlag(earlyFlag);
        if (shortHfFlag || longHfFlag || manyShortHfFlag || manyLongHfFlag || oneDayFlag || earlyFlag) {
            param.setAbnormal(AbnormalEnum.MAYBE_ABNORMAL);
        } else {
            param.setAbnormal(AbnormalEnum.NORMAL);
        }
        lockService.unLock(LockConstant.LockModule.LOCK_BEH_ANALYZE_SRV, LockConstant.QUERY_ABNORMAL_CACHE.concat(num));
        return param;
    }
}
