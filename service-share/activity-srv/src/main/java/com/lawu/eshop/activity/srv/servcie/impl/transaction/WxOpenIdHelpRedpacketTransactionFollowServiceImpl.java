package com.lawu.eshop.activity.srv.servcie.impl.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.mq.dto.user.HelpRedpacktWxOpenIdNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * @author zhangrc
 * @date 2018/02/23
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_HELP_RED_PACKET_REFLASH_ATTEND_WX_OPENID)
public class WxOpenIdHelpRedpacketTransactionFollowServiceImpl extends AbstractTransactionFollowService<HelpRedpacktWxOpenIdNotification, Reply> {


    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;

    @Override
    public void execute(HelpRedpacktWxOpenIdNotification notification) {
    	HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
    	example.createCriteria().andUserNumEqualTo(notification.getUserNum()).andRedpacketTypeEqualTo(RedPacketTypeEnum.WX.getVal());
    	List<HelpRedpacketAttendDetailDO> list = helpRedpacketAttendDetailDOMapper.selectByExample(example);
    	if(list.isEmpty()){
    		return ;
    	}
    	HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
    	record.setWxOpenid(notification.getOpenId());
    	helpRedpacketAttendDetailDOMapper.updateByExampleSelective(record, example);
    	
    }
}
