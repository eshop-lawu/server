package com.lawu.eshop.game.srv.service.impl.transaction;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.cache.constants.GameAttendRecordStatusEnum;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.domain.GameMindAttendRecordDO;
import com.lawu.eshop.game.srv.mapper.GameMindAttendRecordDOMapper;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordService;
import com.lawu.eshop.mq.dto.game.DeductionPointsNotification;
import com.lawu.eshop.mq.dto.game.reply.DeductionPointsReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;

/**
 * 头脑PK扣除积分-主模块
 * 
 * @author jiangxinjun
 * @createDate 2018年3月12日
 * @updateDate 2018年3月12日
 */
@Service("gameMindDeductionPointsTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.GAME_MIND_DEDUCTION_POINTS, topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_MIND_DEDUCTION_POINTS)
public class GameMindDeductionPointsTransactionMainServiceImpl extends
        AbstractTransactionMainService<DeductionPointsNotification, DeductionPointsReply> {
    
    @Autowired
    private GameMindAttendRecordDOMapper gameMindAttendRecordDOMapper;
    
    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;
    
    @Override
    public DeductionPointsNotification selectNotification(Long gameMindAttendRecordId) {
        DeductionPointsNotification notification = new DeductionPointsNotification();
        GameMindAttendRecordDO gameMindAttendRecordDO = gameMindAttendRecordDOMapper.selectByPrimaryKey(gameMindAttendRecordId);
        notification.setBizId(gameMindAttendRecordId);
        notification.setGmtExecute(new Date());
        notification.setMemberNum(gameMindAttendRecordDO.getUserNum());
        notification.setPoint(new BigDecimal(gameMindAttendRecordDO.getAttendPoint()));
        return notification;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void afterSuccess(Long gameMindAttendRecordId, DeductionPointsReply reply) {
        GameAttendRecordStatusEnum attendRecordStatus = null;
        if (reply.getIsSuccess()) {
            attendRecordStatus = GameAttendRecordStatusEnum.ATTENDSUCCESS;
        } else {
            attendRecordStatus = GameAttendRecordStatusEnum.ATTENDFAIL;
        }
        gameMindAttendRecordService.deductionPointsCallback(gameMindAttendRecordId, attendRecordStatus, false);
    }
    
}
