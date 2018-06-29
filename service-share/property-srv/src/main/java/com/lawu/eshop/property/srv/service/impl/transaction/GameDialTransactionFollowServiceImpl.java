package com.lawu.eshop.property.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionFollow;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionFollowService;
import com.lawu.eshop.mq.dto.game.GameDialNotification;
import com.lawu.eshop.mq.dto.game.reply.GameDialReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.param.CheckRepeatOfPropertyOperationParam;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyInfoDataService;

/**
 * 游戏模块，转盘抽奖 - 从事务
 *
 * @author meishuquan
 * @date 2018/3/16
 */
@Service
@CompensatingTransactionFollow(topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_DIAL_DEDUCTION_POINT)
public class GameDialTransactionFollowServiceImpl extends AbstractTransactionFollowService<GameDialNotification, GameDialReply> {

    @Autowired
    private PropertyInfoDataService propertyInfoDataService;

    @Autowired
    private PointDetailService pointDetailService;

    @Override
    public void execute(GameDialNotification notification) {
        PropertyInfoDataParam param = new PropertyInfoDataParam();
        param.setUserNum(notification.getUserNum());
        param.setPoint(notification.getPoint());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_LUCKY_DIAL);
        param.setBizId(String.valueOf(notification.getId()));
        param.setGmtExecute(notification.getGmtExecute());
        propertyInfoDataService.doHanlderMinusPoint(param);
    }

    @Override
    public GameDialReply getReply(GameDialNotification notification) {
        CheckRepeatOfPropertyOperationParam param = new CheckRepeatOfPropertyOperationParam();
        param.setUserNum(notification.getUserNum());
        param.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.POINT_GAME_LUCKY_DIAL);
        param.setBizIds(String.valueOf(notification.getId()));
        boolean result = pointDetailService.verifyRepeatByUserNumAndTransactionTypeAndBizId(param);
        GameDialReply reply = new GameDialReply();
        reply.setResult(result);
        return reply;
    }

}
