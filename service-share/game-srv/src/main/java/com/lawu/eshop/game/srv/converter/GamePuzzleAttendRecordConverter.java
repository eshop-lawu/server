package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleAttendRecordDO;

/**
 * @author lihj <br>
 * @date 2018/3/12
 */
public class GamePuzzleAttendRecordConverter {
	public static GamePuzzleAttendRecordDO convertGamePuzzleAttendRecordDO(GamePuzzleAttendRecordBO attendBO) {
		GamePuzzleAttendRecordDO game = new GamePuzzleAttendRecordDO();
		game.setUserNum(attendBO.getUserNum());
		game.setAttendNum(attendBO.getAttendNum());
		game.setAttendType(attendBO.getAttendType());
		game.setRoomNum(attendBO.getRoomNum());
		game.setAttendCount(attendBO.getAttendCount());
		game.setDifficulty(attendBO.getDifficulty());
		game.setAttendPoint(attendBO.getAttendPoint());
		game.setAttendStar(attendBO.getAttendStar());
		game.setPuzzlePicId(attendBO.getPuzzlePicId());
		game.setStatus(attendBO.getStatus());
		game.setGameScore(attendBO.getGameScore());
		game.setGameRank(attendBO.getGameRank());
		game.setGameUseTime(attendBO.getGameUseTime());
		game.setRewardPoint(attendBO.getRewardPoint());
		game.setRewardStar(attendBO.getRewardStar());
		game.setGmtModified(attendBO.getGmtModified());
		game.setGmtCreate(attendBO.getGmtCreate());
		return game;

	}

	public static GamePuzzleAttendRecordBO convertGamePuzzleAttendRecordDO(GamePuzzleAttendRecordDO game) {
		GamePuzzleAttendRecordBO bo = new GamePuzzleAttendRecordBO();
		bo.setId(game.getId());
		bo.setUserNum(game.getUserNum());
		bo.setAttendNum(game.getAttendNum());
		bo.setAttendType(game.getAttendType());
		bo.setRoomNum(game.getRoomNum());
		bo.setAttendCount(game.getAttendCount());
		bo.setDifficulty(game.getDifficulty());
		bo.setAttendPoint(game.getAttendPoint());
		bo.setAttendStar(game.getAttendStar());
		bo.setPuzzlePicId(game.getPuzzlePicId());
		bo.setStatus(game.getStatus());
		bo.setGameScore(game.getGameScore());
		bo.setGameRank(game.getGameRank());
		bo.setGameUseTime(game.getGameUseTime());
		bo.setRewardPoint(game.getRewardPoint());
		bo.setRewardStar(game.getRewardStar());
		bo.setGmtModified(game.getGmtModified());
		bo.setGmtCreate(game.getGmtCreate());
		return bo;
	}

	public static List<GamePuzzleRankReturnBO> convertGamePuzzleRankReturnBO(List<GamePuzzleAttendRecordDO> list) {
		List<GamePuzzleRankReturnBO> lt =new ArrayList<GamePuzzleRankReturnBO>();
		if(null ==list ||list.size()==0){
			return null;
		}
		for(GamePuzzleAttendRecordDO record :list ){
			GamePuzzleRankReturnBO rank =new GamePuzzleRankReturnBO();
			int star= record.getRewardStar()==null?0:record.getRewardStar();
			BigDecimal point =record.getRewardPoint()==null?new BigDecimal(0):record.getRewardPoint();
			rank.setGameStar(star);
			rank.setGamePoint(point);
			rank.setGameRank(record.getGameRank());
			rank.setGameScore(record.getGameScore()==null?0:record.getGameScore());
			rank.setUserNum(record.getUserNum());
			lt.add(rank);
		}
		
		return lt;
	}

    public static GamePuzzleAttendRecordBO convertGamePuzzleAttendRecordBO(GamePuzzleAttendRecordDO game) {
		GamePuzzleAttendRecordBO gamePuzzle =new GamePuzzleAttendRecordBO();
		gamePuzzle.setId(game.getId());
		gamePuzzle.setUserNum(game.getUserNum());
		gamePuzzle.setAttendNum(game.getAttendNum());
		gamePuzzle.setAttendType(game.getAttendType());
		gamePuzzle.setRoomNum(game.getRoomNum());
		gamePuzzle.setAttendCount(game.getAttendCount());
		gamePuzzle.setDifficulty(game.getDifficulty());
		gamePuzzle.setAttendPoint(game.getAttendPoint());
		gamePuzzle.setAttendStar(game.getAttendStar());
		gamePuzzle.setPuzzlePicId(game.getPuzzlePicId());
		gamePuzzle.setStatus(game.getStatus());
		gamePuzzle.setGameScore(game.getGameScore());
		gamePuzzle.setGameRank(game.getGameRank());
		gamePuzzle.setGameUseTime(game.getGameUseTime());
		gamePuzzle.setRewardPoint(game.getRewardPoint());
		gamePuzzle.setRewardStar(game.getRewardStar());
		gamePuzzle.setGmtCreate(game.getGmtCreate());
		gamePuzzle.setGmtModified(game.getGmtModified());
		return gamePuzzle;
    }
}
