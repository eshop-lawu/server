package com.lawu.eshop.game.srv.service.impl.transaction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.game.param.GameAccountAllotParam;
import com.lawu.eshop.game.param.GameAccountStarParam;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GameCommonCacheService;
import com.lawu.eshop.game.srv.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.mq.dto.game.PuzzleGameDeductionNotification;
import com.lawu.eshop.mq.dto.game.reply.PuzzleGameDeductionReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.framework.web.Result;
/**
 * 拼图扣除积分主事物
 * @author lihj
 * @Date 2018年3月13日
 */
@Service
@CompensatingTransactionMain(value=TransactionConstant.PUZZLE_GAME_DEDUCTION_POINT,topic=MqConstant.TOPIC_GAME_SRV,tags=MqConstant.TAG_PUZZLE_GAME_DEDUCTION_POINT)
public class PuzzleGameDeductionPointTransactionMainServiceImpl extends AbstractTransactionMainService<PuzzleGameDeductionNotification,PuzzleGameDeductionReply>{

	private Logger logger =LoggerFactory.getLogger(PuzzleGameDeductionPointTransactionMainServiceImpl.class);
	@Autowired
	private GamePuzzleAttendRecordService  gamePuzzleAttendRecordService;
	@Autowired
	private GameAccountService gameAccountService;
	@Autowired
	private GameCommonCacheService gameCommonCacheService;
	@Autowired
	@Qualifier("puzzleGameRefundPointsTransactionMainServiceImpl")
	private TransactionMainService<Reply> puzzleGameRefundPointsTransactionMainServiceImpl;
	
	@Override
	public PuzzleGameDeductionNotification selectNotification(Long id) {
		GamePuzzleAttendRecordBO bo =gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
		if(null ==bo){
			return null;
		}
		PuzzleGameDeductionNotification notification =new PuzzleGameDeductionNotification();
		notification.setId(id);
		notification.setUserNum(bo.getUserNum());
		notification.setPoint(String.valueOf(bo.getAttendPoint()));
		
		return notification;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void afterSuccess(Long id, PuzzleGameDeductionReply reply) {
		logger.info("扣除积分主事物afterSuccess回来参数是"+id+"  "+reply.isResult());
		GamePuzzleAttendRecordBO bo =gamePuzzleAttendRecordService.getPuzzleAttendRecordById(id);
		GamePuzzleCacheDetail cacheDetail =new GamePuzzleCacheDetail();
		cacheDetail.setAttendNum(bo.getAttendNum());
		cacheDetail.setId(bo.getId());
		cacheDetail.setUserNum(bo.getUserNum());
		cacheDetail.setStar(bo.getAttendStar());
		if(reply.isResult()){ //处理成功,扣除积分成功
			gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id,GameAttendRecordStatusEnum.ATTENDSUCCESS);
			List<GameAccountAllotParam> listAllot =new ArrayList<>();
			GameAccountStarParam gameAccount =new GameAccountStarParam();
			gameAccount.setAttendNum(bo.getAttendNum());
			gameAccount.setGameType(GameTypeEnum.PUZZLE);
			gameAccount.setRecordEnum(GameAttendRecordStatusEnum.ATTENDSUCCESS);
			GameAccountAllotParam allotParam = new GameAccountAllotParam();
			allotParam.setStar(bo.getAttendStar());
			allotParam.setUserNum(bo.getUserNum());
			listAllot.add(allotParam);
			gameAccount.setList(listAllot);
			gameAccountService.dealStar(gameAccount);
			cacheDetail.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
		}else{//扣除积分失败
			gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id,GameAttendRecordStatusEnum.ATTENDFAIL);
			cacheDetail.setStatus(GameAttendRecordStatusEnum.ATTENDFAIL.getVal());
		}
		int count = gamePuzzleAttendRecordService.getPuzzleAttendRecordByAttendNum(bo.getAttendNum());
		//Result result = gameCommonCacheService.incrementAndGet(bo.getAttendNum());
		Result<GamePuzzleCallBackCacheDTO> cacheResult = gameCommonCacheService.setCallBackCache(cacheDetail);
		GamePuzzleCallBackCacheDTO dto = cacheResult.getModel();
		int successCount =0;
		if(count==dto.getDetailList().size()){
			for(GamePuzzleCacheDetail puzzle :dto.getDetailList()){
				if(puzzle.getStatus().equals(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal())){
					successCount++;
				}
			}
			if(successCount==1){
				for(GamePuzzleCacheDetail puzzle :dto.getDetailList()){
					if(puzzle.getStatus().equals(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal())){
						puzzleGameRefundPointsTransactionMainServiceImpl.sendNotice(puzzle.getId());
						gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(puzzle.getId(),GameAttendRecordStatusEnum.REFUND);
						List<GameAccountAllotParam> listAllot = new ArrayList<>();
						GameAccountStarParam gameAccount = new GameAccountStarParam();
						gameAccount.setAttendNum(puzzle.getAttendNum());
						gameAccount.setGameType(GameTypeEnum.PUZZLE);
						gameAccount.setRecordEnum(GameAttendRecordStatusEnum.REFUND);
						GameAccountAllotParam allotParam = new GameAccountAllotParam();
						allotParam.setStar(puzzle.getStar());
						allotParam.setUserNum(puzzle.getUserNum());
						listAllot.add(allotParam);
						gameAccount.setList(listAllot);
						gameAccountService.dealStar(gameAccount);
					}
				}
			}
		}
		/*if(!bo.getAttendType().equals(AttendTypeEnum.STANDALONE)){
			logger.info("2扣除积分主事物afterSuccess回来记录是count==="+ count);
			if(Integer.valueOf(result.getModel().toString())==count){
				//判断是否只有一个扣除成功的
				List<GamePuzzleAttendRecordDO> list = gamePuzzleAttendRecordService.getAttendRecordByAttendAndStatus(bo.getAttendNum(), GameAttendRecordStatusEnum.ATTENDSUCCESS);
				logger.info("3扣除积分主事物afterSuccess回来记录是list==="+ JSON.toJSONString(list));
				if(list.size()==1){
					logger.info("4扣除积分主事物afterSuccess回来记录是if===");
					puzzleGameRefundPointsTransactionMainServiceImpl.sendNotice(list.get(0).getId());
					gamePuzzleAttendRecordService.updateGamePuzzleAttendRecordStatus(id,GameAttendRecordStatusEnum.REFUND);
					List<GameAccountAllotParam> listAllot =new ArrayList<>();
					GameAccountStarParam gameAccount =new GameAccountStarParam();
					gameAccount.setAttendNum(bo.getAttendNum());
					gameAccount.setGameType(GameTypeEnum.PUZZLE);
					gameAccount.setRecordEnum(GameAttendRecordStatusEnum.REFUND);
					GameAccountAllotParam allotParam = new GameAccountAllotParam();
					allotParam.setStar(bo.getAttendStar());
					allotParam.setUserNum(bo.getUserNum());
					listAllot.add(allotParam);
					gameAccountService.dealStar(gameAccount);
				}
			}
		}*/
	}
	
}
