package com.lawu.eshop.game.srv.converter;

import java.util.List;

import com.lawu.eshop.game.dto.GameMindResultDTO;
import com.lawu.eshop.game.srv.bo.GameMindAttendRecordBO;
import com.lawu.eshop.game.srv.bo.GameMindResultBO;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/15.
 */
public class GameMindAttendRecordConverter {
    public static GameMindAttendRecordBO converterBO(List<GameMindAttendRecordDO> list) {
        if(list.isEmpty()){
            return null;
        }
        GameMindAttendRecordBO recordBO = new GameMindAttendRecordBO();
        recordBO.setId(list.get(0).getId());
        recordBO.setUserNum(list.get(0).getUserNum());
        recordBO.setAttendCount(list.get(0).getAttendCount());
        recordBO.setAttendNum(list.get(0).getAttendNum());
        recordBO.setAttendPoint(list.get(0).getAttendPoint());
        recordBO.setAttendStar(list.get(0).getAttendStar());
        recordBO.setAttendType(list.get(0).getAttendType());
        recordBO.setGameRank(list.get(0).getGameRank());
        recordBO.setGameScore(list.get(0).getGameScore());
        recordBO.setGmtCreate(list.get(0).getGmtCreate());
        recordBO.setGmtModified(list.get(0).getGmtModified());
        recordBO.setQuestionUseTime(list.get(0).getQuestionUseTime());
        return recordBO;
    }

    public static GameMindResultDTO converterDTO(GameMindResultBO resultBO) {
        GameMindResultDTO resultDTO = new GameMindResultDTO();
        resultDTO.setAwardPoint(resultBO.getAwardPoint());
        resultDTO.setIsFinish(resultBO.getIsFinish());
        resultDTO.setIsTrue(resultBO.getIsTrue());
        resultDTO.setAwardStar(resultBO.getAwardStar());
        resultDTO.setPoint(resultBO.getPoint());
        resultDTO.setStatus(resultBO.getStatus());
        resultDTO.setRightAnwser(resultBO.getRightAnswer());
        resultDTO.setTotalScore(resultBO.getTotalScore());
        resultDTO.setAttendNum(resultBO.getAttendNum());
        return resultDTO;
    }
}
