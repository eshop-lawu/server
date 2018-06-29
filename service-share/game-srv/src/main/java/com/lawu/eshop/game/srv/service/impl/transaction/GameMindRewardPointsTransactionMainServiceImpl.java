package com.lawu.eshop.game.srv.service.impl.transaction;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.mq.dto.game.DeductionPointsNotification;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 头脑PK奖励积分-主模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service("gameMindRewardPointsTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.GAME_MIND_INCREASE_POINTS, topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_MIND_INCREASE_POINTS)
public class GameMindRewardPointsTransactionMainServiceImpl extends
        AbstractTransactionMainService<DeductionPointsNotification, Reply> {
    
    @Autowired
    private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;
    
    @Override
    public DeductionPointsNotification selectNotification(Long gameMindAttendRecordId) {
        DeductionPointsNotification notification = new DeductionPointsNotification();
        GameMindAttendRecordDO gameMindAttendRecordDO = gameMindAttendRecordDOMapper.selectByPrimaryKey(gameMindAttendRecordId);
        notification.setBizId(gameMindAttendRecordId);
        notification.setGmtExecute(new Date());
        notification.setMemberNum(gameMindAttendRecordDO.getUserNum());
        notification.setPoint(gameMindAttendRecordDO.getRewardPoint());
        return notification;
    }
    
}
