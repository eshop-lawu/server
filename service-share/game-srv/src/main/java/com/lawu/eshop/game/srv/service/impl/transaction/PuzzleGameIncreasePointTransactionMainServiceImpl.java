package com.lawu.eshop.game.srv.service.impl.transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.mq.dto.game.PuzzleGameDeductionNotification;
import com.lawu.eshop.mq.dto.game.reply.PuzzleGameDeductionReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 拼图 游戏增加积分主事物
 * @author lihj <br>
 * @date 2018/3/13
 */
@Service
@CompensatingTransactionMain(value=TransactionConstant.PUZZLE_GAME_INCREASE_POINT,topic=MqConstant.TOPIC_GAME_SRV,tags=MqConstant.TAG_PUZZLE_GAME_INCREASE_POINT)
public class PuzzleGameIncreasePointTransactionMainServiceImpl extends AbstractTransactionMainService<PuzzleGameDeductionNotification, PuzzleGameDeductionReply>{
;
	private Logger logger =LoggerFactory.getLogger(PuzzleGameDeductionPointTransactionMainServiceImpl.class);
	
	@Autowired
	private GamePuzzleAttendRecordService  gamePuzzleAttendRecordService;
	@Autowired
	private GameAccountService gameAccountService;
	
	@Override
	public PuzzleGameDeductionNotification selectNotification(Long id) {
		GamePuzzleAttendRecordBO bo =gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
		if(null ==bo){
			return null;
		}
		PuzzleGameDeductionNotification notification =new PuzzleGameDeductionNotification();
		notification.setId(id);
		notification.setUserNum(bo.getUserNum());
		notification.setPoint(bo.getRewardPoint()+"");
		return notification;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void afterSuccess(Long id, PuzzleGameDeductionReply reply) {
		if(reply.isResult()){
			gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id,GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
			List<GameAccountAllotParam> listAllot =new ArrayList<>();
			GamePuzzleAttendRecordBO bo =gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
			logger.info("增加积分主事物afterSuccess回来参数是"+bo.getUserNum()+"增加星星"+bo.getRewardStar());
			if(bo.getRewardStar()>0){
				GameAccountStarParam gameAccount =new GameAccountStarParam();
				gameAccount.setAttendNum(bo.getAttendNum());
				gameAccount.setGameType(GameTypeEnum.PUZZLE);
				gameAccount.setRecordEnum(GameAttendRecordStatusEnum.GAMEPLAYSUCCESS);
				GameAccountAllotParam allotParam = new GameAccountAllotParam();
				allotParam.setStar(bo.getRewardStar());
				allotParam.setUserNum(bo.getUserNum());
				listAllot.add(allotParam);
				gameAccount.setList(listAllot);
				gameAccountService.dealStar(gameAccount);
			}
		}else{
			gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id,GameAttendRecordStatusEnum.GAMEPLAYFAIL);
		}
	}
	
	
}
