package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.game.PuzzleGameDeductionNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

@Service
@CompensatingTransactionFollow(topic=MqConstant.TOPIC_GAME_SRV,tags=MqConstant.TAG_GAME_PUZZLE_REFUND_POINTS)
public class PuzzleGameRefundPointsTransactionFollowServiceImpl extends AbstractTransactionFollowService<PuzzleGameDeductionNotification,Reply>{

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;
    
	@Override
	public void execute(PuzzleGameDeductionNotification notification) {
		PropertyInfoDataParam param = new PropertyInfoDataParam();
		param.setUserNum(notification.getUserNum());
		param.setPoint(notification.getPoint());
		param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_PUZZLE_BACK);
		param.setBizId(String.valueOf(notification.getTransactionId()));
		propertyInfoDataService.doHanlderAddPoint(param);
	}

}
