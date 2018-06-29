package com.lawu.eshop.user.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.mq.dto.user.HelpRedpacktWxOpenIdNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.user.srv.constants.TransactionConstant;
import com.lawu.eshop.user.srv.domain.WeixinUserDO;
import com.lawu.eshop.user.srv.mapper.WeixinUserDOMapper;

/**
 * 修改助力红包微信opendID - 主事务
 *
 * @author zhangrc
 * @date 2018/02/23
 */
@Service("wxOpenIdUpdateTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.HELP_PACKET_REFLASH_WX_OPENID, topic = MqConstant.TOPIC_USER_SRV, tags = MqConstant.TAG_HELP_RED_PACKET_REFLASH_ATTEND_WX_OPENID)
public class WxOpenIdUpdateTransactionMainServiceImpl extends AbstractTransactionMainService<HelpRedpacktWxOpenIdNotification, Reply> {

	@Autowired
	private WeixinUserDOMapper weixinUserDOMapper;

    @Override
    public HelpRedpacktWxOpenIdNotification selectNotification(Long wxUserId) {
    	WeixinUserDO record = weixinUserDOMapper.selectByPrimaryKey(wxUserId);
    	HelpRedpacktWxOpenIdNotification notification = new HelpRedpacktWxOpenIdNotification();
    	notification.setOpenId(record.getOpenid());
    	notification.setUserNum(record.getUserNum());
        return notification;
    }


}
