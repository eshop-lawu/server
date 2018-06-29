package com.lawu.eshop.game.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleAttendRecordDOMapper;
import com.lawu.eshop.mq.dto.game.PuzzleGameDeductionNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 拼图积分退还主事物
 * @author lihj
 * @Date 2018年3月26日
 */
@Service("puzzleGameRefundPointsTransactionMainServiceImpl")
@CompensatingTransactionMain(value=TransactionConstant.PUZZLE_GAME_REFUND_POINTS,topic=MqConstant.TOPIC_GAME_SRV,tags=MqConstant.TAG_GAME_PUZZLE_REFUND_POINTS)
public class PuzzleGameRefundPointsTransactionMainServiceImpl extends AbstractTransactionMainService<PuzzleGameDeductionNotification,Reply>{

	@Autowired
	private GamePuzzleAttendRecordDOMapper gamePuzzleAttendRecordDOMapper;  
	
	@Override
	public PuzzleGameDeductionNotification selectNotification(Long relateId) {
		PuzzleGameDeductionNotification notification =new PuzzleGameDeductionNotification();
		GamePuzzleAttendRecordDO gamePuzzle = gamePuzzleAttendRecordDOMapper.selectByPrimaryKey(relateId);
		notification.setPoint(String.valueOf(gamePuzzle.getAttendPoint()));
		notification.setTransactionId(relateId);
		notification.setUserNum(gamePuzzle.getUserNum());
		return notification;
	}

}
