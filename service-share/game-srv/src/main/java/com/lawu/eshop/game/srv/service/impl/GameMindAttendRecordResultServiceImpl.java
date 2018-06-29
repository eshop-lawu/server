package com.lawu.eshop.game.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordResultDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordResultDOMapper;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordResultService;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
@Service
public class GameMindAttendRecordResultServiceImpl implements GameMindAttendRecordResultService {

    @Autowired
    private GameMindAttendRecordResultDOMapper gameMindAttendRecordResultDOMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAttendRecordsResult(List<GameMindAttendRecordResultParam> oldRecords) {
        for (GameMindAttendRecordResultParam param : oldRecords) {
            GameMindAttendRecordResultDO resultDO = new GameMindAttendRecordResultDO();
            resultDO.setMindAttendId(param.getMindAttendId());
            resultDO.setAnswer(param.getAnswer());
            resultDO.setRightAnswer(param.getRightAnswer());
            resultDO.setPoint(param.getPoint());
            resultDO.setFlag(param.getFlag());
            resultDO.setQuestionId(param.getQuestionId());
            resultDO.setUseTime(param.getUseTime());
            resultDO.setGmtCreate(param.getGmtCreate());
            resultDO.setGmtModified(param.getGmtModified());
            gameMindAttendRecordResultDOMapper.insertSelective(resultDO);
        }
    }
}
