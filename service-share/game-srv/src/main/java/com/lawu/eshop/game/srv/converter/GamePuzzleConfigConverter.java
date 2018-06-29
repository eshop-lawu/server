package com.lawu.eshop.game.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.common.constants.EnableEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.dto.*;
import com.lawu.eshop.game.param.GameAttendSaveParam;
import com.lawu.eshop.game.srv.bo.GameAttendSaveReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleConfigBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleGetPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleRankReturnBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleResultBO;
import com.lawu.eshop.game.srv.bo.PuzzleCheckIsOverBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleConfigDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.utils.DateUtil;
import com.lawu.utils.RandomPicUtil;

/**
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleConfigConverter {

    public static List<GamePuzzleGetPicDTO> convertGamePuzzleGetPicBO(List<GamePuzzleGetPicBO> list) {
        List<GamePuzzleGetPicDTO> ldto = new ArrayList<GamePuzzleGetPicDTO>();
        for (GamePuzzleGetPicBO listbo : list) {
            GamePuzzleGetPicDTO dto = new GamePuzzleGetPicDTO();
            dto.setId(listbo.getId());
            dto.setCoefficient(listbo.getCoefficient());
            dto.setCutImgNum(listbo.getCutImgNum());
            dto.setImgPath(listbo.getImgPath());
            dto.setKey(listbo.getKey());
            dto.setLevel(listbo.getLevel());
            dto.setAttendType(listbo.getAttendType());
            dto.setUserNickname(listbo.getUserNickname());
            ldto.add(dto);
        }
        return ldto;
    }

    public static GamePuzzleGetPicBO convertGamePuzzleGetPicBO(GamePuzzlePicBO gamePuzzlePicBO, GamePuzzleDifficultyBO diffBo) {
        GamePuzzleGetPicBO bo = new GamePuzzleGetPicBO();
        bo.setId(gamePuzzlePicBO.getId());
        bo.setCoefficient(diffBo.getCoefficient());
        bo.setImgPath(gamePuzzlePicBO.getImgPath());
        bo.setUserNickname(gamePuzzlePicBO.getUserNickname());
        String date = DateUtil.getDateTimeFormat(gamePuzzlePicBO.getGmtCreate());
        bo.setKey(Integer.valueOf(date.substring(date.length() - 1, date.length())));
        bo.setCutImgNum(RandomPicUtil.getRandomCutPic(bo.getCoefficient()));
        return bo;
    }


    public static GamePuzzleAttendRecordBO convertInitGamePuzzleAttendRecordBO(GamePuzzleDifficultyDO game,
                                                                               GameAttendSaveParam param, String userNum, String attendNum) {
        GamePuzzleAttendRecordBO attend = new GamePuzzleAttendRecordBO();
        attend.setUserNum(userNum);
        attend.setAttendType(param.getAttendType().getVal());
        attend.setAttendNum(attendNum);
        if (param.getAttendType().getVal().equals(AttendTypeEnum.STANDALONE.getVal())) {
            attend.setAttendCount(AttendTypeEnum.STANDALONE.getVal().intValue());
        }
        attend.setDifficulty(game.getType());
        attend.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
        attend.setGmtModified(new Date());
        attend.setGmtCreate(new Date());
        return attend;
    }

    public static GameAttendSaveReturnDTO convertGameAttendSaveReturnDTO(GameAttendSaveReturnBO attendBO) {
        GameAttendSaveReturnDTO dto = new GameAttendSaveReturnDTO();
        dto.setFlag(attendBO.isFlag());
        dto.setRoomMaster(attendBO.getRoomMaster());
        dto.setAttendNum(attendBO.getAttendNum());
        return dto;
    }

    public static GamePuzzleResultDTO convertGamePuzzleResultDTO(GamePuzzleResultBO bo) {
        GamePuzzleResultDTO dto = new GamePuzzleResultDTO();
        dto.setFlag(bo.isFlag());
        dto.setGameRank(bo.getGameRank());
        dto.setGameScore(bo.getGameScore());
        dto.setGameStar(bo.getGameStar());
        dto.setOver(bo.isOver());
        dto.setUserNum(bo.getUserNum());
        dto.setLastCount(bo.getLastCount());
        return dto;
    }

    public static GamePuzzleAttendRecordBO convertRandomGamePuzzleAttendRecordBO(GameAttendSaveParam param, String userNum, String attendNum) {
        GamePuzzleAttendRecordBO gamePuzz = new GamePuzzleAttendRecordBO();
        gamePuzz.setUserNum(userNum);
        gamePuzz.setAttendType(param.getAttendType().getVal());
        gamePuzz.setAttendNum(attendNum);
        gamePuzz.setAttendCount(AttendTypeEnum.getEnum(param.getAttendType().getVal()).getVal().intValue());
        gamePuzz.setAttendPoint(param.getAttendPoint());
        gamePuzz.setAttendStar(param.getAttendStar());
        if (param.isFree()) {
            gamePuzz.setStatus(GameAttendRecordStatusEnum.ATTENDSUCCESS.getVal());
        } else {
            gamePuzz.setStatus(GameAttendRecordStatusEnum.INITSTATUS.getVal());
        }
        gamePuzz.setGmtModified(new Date());
        gamePuzz.setGmtCreate(new Date());
        return gamePuzz;
    }


    public static GamePuzzleConfigBO converterBO(List<GamePuzzleConfigDO> configDOS) {
        GamePuzzleConfigBO configBO = new GamePuzzleConfigBO();
        if (configDOS.isEmpty()) {
            return configBO;
        }
        configBO.setId(configDOS.get(0).getId());
        configBO.setAttendMaxPoint(configDOS.get(0).getAttendMaxPoint());
        configBO.setAttendPoint(configDOS.get(0).getAttendPoint());
        configBO.setAwardPoint(configDOS.get(0).getAwardPoint());
        configBO.setAwardStar(configDOS.get(0).getAwardStar());
        configBO.setCountDown(configDOS.get(0).getCountDown());
        configBO.setDeductStar(configDOS.get(0).getDeductStar());
        configBO.setRoomMaxNum(configDOS.get(0).getRoomMaxNum());
        configBO.setSecScore(configDOS.get(0).getSecScore());
        configBO.setShareAttendCount(configDOS.get(0).getShareAttendCount());
        configBO.setStatusEnum(GameConfigStatusEnum.getEnum(configDOS.get(0).getStatus()));
        configBO.setFreeCount(configDOS.get(0).getFreeCount());
        configBO.setPicCount(configDOS.get(0).getPicCount());
        configBO.setSuccessScore(configDOS.get(0).getSuccessScore());
        configBO.setForbiddenRemark(configDOS.get(0).getForbiddenRemark());
        configBO.setRobotStatus(EnableEnum.getEnum(configDOS.get(0).getRobotStatus()));
        configBO.setRobotEffectiveTime(configDOS.get(0).getRobotEffectiveTime());
        return configBO;
    }


    public static GamePuzzleConfigDTO converterDTO(GamePuzzleConfigBO config) {
        GamePuzzleConfigDTO configDTO = new GamePuzzleConfigDTO();
        if (config == null) {
            return configDTO;
        }
        configDTO.setId(config.getId());
        configDTO.setAttendMaxPoint(config.getAttendMaxPoint());
        configDTO.setAttendPoint(config.getAttendPoint());
        configDTO.setAwardPoint(config.getAwardPoint());
        configDTO.setAwardStar(config.getAwardStar());
        configDTO.setCountDown(config.getCountDown());
        configDTO.setDeductStar(config.getDeductStar());
        configDTO.setRoomMaxNum(config.getRoomMaxNum());
        configDTO.setShareAttendCount(config.getShareAttendCount());
        configDTO.setStatusEnum(config.getStatusEnum());
        configDTO.setFreeCount(config.getFreeCount());
        configDTO.setPicCount(config.getPicCount());
        configDTO.setSuccessScore(config.getSuccessScore());
        configDTO.setForbiddenRemark(config.getForbiddenRemark());
        configDTO.setRobotStatus(config.getRobotStatus());
        configDTO.setRobotEffectiveTime(config.getRobotEffectiveTime());
        return configDTO;
    }

    public static List<GamePuzzleGetPicDTO> convertGamePuzzleGetPicDTO(List<GamePuzzleGetPicBO> dtoBO) {
        List<GamePuzzleGetPicDTO> list = new ArrayList<GamePuzzleGetPicDTO>();
        if (null == dtoBO || dtoBO.size() == 0) {
            return list;
        }
        for (GamePuzzleGetPicBO pic : dtoBO) {
            GamePuzzleGetPicDTO getPic = new GamePuzzleGetPicDTO();
            getPic.setId(pic.getId());
            getPic.setImgPath(pic.getImgPath());
            getPic.setLevel(pic.getLevel());
            getPic.setAttendType(pic.getAttendType());
            getPic.setCoefficient(pic.getCoefficient());
            getPic.setCanUseTime(pic.getCanUseTime());
            getPic.setKey(pic.getKey());
            getPic.setCutImgNum(pic.getCutImgNum());
            getPic.setUserNickname(pic.getUserNickname());
            getPic.setScore(pic.getScore());
            getPic.setSingleMaxScore(pic.getSingleMaxScore());
            list.add(getPic);
        }
        return list;
    }

	public static GamePuzzleGetPicReturnDTO convertGamePuzzleGetPicReturnDTO(List<GamePuzzleGetPicDTO> dto) {
		GamePuzzleGetPicReturnDTO rdto = new GamePuzzleGetPicReturnDTO();
		rdto.setPuzzleInfo(dto);
		return rdto;
	}

	public static List<GamePuzzleRankReturnDTO> convertGamePuzzleConfigConverter(List<GamePuzzleRankReturnBO> list) {
		List<GamePuzzleRankReturnDTO> lt =new ArrayList<GamePuzzleRankReturnDTO>();
		for(GamePuzzleRankReturnBO game:list){
			GamePuzzleRankReturnDTO dto =new GamePuzzleRankReturnDTO();
			dto.setGamePoint(game.getGamePoint().intValue());
			dto.setGameRank(game.getGameRank());
			dto.setGameScore(game.getGameScore()==-1?0:game.getGameScore());
			dto.setGameStar(game.getGameStar());
			dto.setUserNum(game.getUserNum());
			dto.setTotalStar(game.getTotalStar());
			lt.add(dto);
		}
		return lt;
	}

	public static PuzzleCheckIsOverDTO convertPuzzleCheckIsOverDTO(PuzzleCheckIsOverBO puzzle) {
		PuzzleCheckIsOverDTO dto =new PuzzleCheckIsOverDTO();
		dto.setIsOver(puzzle.getIsOver());
		dto.setCount(puzzle.getCount());
		return dto;
	}

    public static GamePuzzleAttendRecordDTO convertGamePuzzleAttendRecordDTO(GamePuzzleAttendRecordBO bo) {
        GamePuzzleAttendRecordDTO dto = new GamePuzzleAttendRecordDTO();
        dto.setId(bo.getId());
        dto.setUserNum(bo.getUserNum());
        dto.setAttendNum(bo.getAttendNum());
        dto.setAttendType(bo.getAttendType());
        dto.setRoomNum(bo.getRoomNum());
        dto.setAttendCount(bo.getAttendCount());
        dto.setDifficulty(bo.getDifficulty());
        dto.setAttendPoint(bo.getAttendPoint());
        dto.setAttendStar(bo.getAttendStar());
        dto.setPuzzlePicId(bo.getPuzzlePicId());
        dto.setStatus(bo.getStatus());
        dto.setGameScore(bo.getGameScore());
        dto.setGameRank(bo.getGameRank());
        dto.setGameUseTime(bo.getGameUseTime());
        dto.setRewardPoint(bo.getRewardPoint());
        dto.setRewardStar(bo.getRewardStar());
        dto.setGmtCreate(bo.getGmtCreate());
        dto.setGmtModified(bo.getGmtModified());
        return dto;
    }
}
