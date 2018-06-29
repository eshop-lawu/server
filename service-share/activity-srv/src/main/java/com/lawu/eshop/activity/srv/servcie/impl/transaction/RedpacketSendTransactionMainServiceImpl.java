package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.srv.constants.TransactionConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.mq.dto.activity.RedpacketSendNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 活动模块，红包发放(进余额)-主模块
 * 
 * @author jiangxinjun
 * @createDate 2018年1月16日
 * @updateDate 2018年1月16日
 */
@Service("redpacketSendTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.RED_PACKET_SEND, topic = MqConstant.TOPIC_ACTIVITY_SRV, tags = MqConstant.TAG_RED_PACKET_SEND)
public class RedpacketSendTransactionMainServiceImpl extends AbstractTransactionMainService<RedpacketSendNotification, Reply> {
	
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
    @Override
    public RedpacketSendNotification selectNotification(Long attendDetailId) {
        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(attendDetailId);
        RedpacketSendNotification notification  = new RedpacketSendNotification();
        notification.setAttendDetailId(helpRedpacketAttendDetailDO.getId());
        notification.setRedpacketAmount(new BigDecimal(helpRedpacketAttendDetailDO.getFinalMoney()).divide(new BigDecimal(100)));
        notification.setUserNum(helpRedpacketAttendDetailDO.getUserNum());
        notification.setGmtExecute(helpRedpacketAttendDetailDO.getTakeTime());
        return notification;
    }
    
    @Override
    public void afterSuccess(Long relateId, Reply reply) {
        HelpRedpacketAttendDetailDO update = new HelpRedpacketAttendDetailDO();
        update.setId(relateId);
        update.setStatus(ActivityAttendStatusEnum.SEND_SUCCESS.getVal());
        update.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(update);
    }
}
