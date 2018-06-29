package com.lawu.eshop.property.srv.service.impl.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.game.PuzzleGameDeductionNotification;
import com.lawu.eshop.mq.dto.game.reply.PuzzleGameDeductionReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 拼图游戏扣除积分从事物
 * 
 * @author lihj <br>
 * @date 2018/3/13
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_PUZZLE_GAME_DEDUCTION_POINT)
public class PuzzleGameDeductionPointTransactionFollowServiceImpl
		extends AbstractTransactionFollowService<PuzzleGameDeductionNotification, PuzzleGameDeductionReply> {

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;

	@Autowired
	private PointDetailService pointDetailService;

	@Override
	public void execute(PuzzleGameDeductionNotification puzzle) {
		PropertyInfoDataParam param = new PropertyInfoDataParam();
		param.setUserNum(puzzle.getUserNum());
		param.setPoint(puzzle.getPoint());
		param.setBizId(String.valueOf(puzzle.getId()));
		param.setGmtExecute(new Date());
		param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_PUZZLE);
		propertyInfoDataService.doHanlderMinusPoint(param);
	}

	@Override
	public PuzzleGameDeductionReply getReply(PuzzleGameDeductionNotification puzzle) {
		CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
		param.setUserNum(puzzle.getUserNum());
		param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_PUZZLE);
		param.setBizIds(String.valueOf(puzzle.getId()));
		boolean result = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
		PuzzleGameDeductionReply reply = new PuzzleGameDeductionReply();
		reply.setResult(result);
		return reply;
	}

}
