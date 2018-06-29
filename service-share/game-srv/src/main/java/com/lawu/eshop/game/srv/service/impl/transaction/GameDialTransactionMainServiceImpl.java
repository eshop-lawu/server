package com.lawu.eshop.game.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.service.GameDialRecordService;
import com.lawu.eshop.mq.dto.game.GameDialNotification;
import com.lawu.eshop.mq.dto.game.reply.GameDialReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;


/**
 * 游戏模块，转盘抽奖 - 主事务
 *
 * @author meishuquan
 * @date 2018/3/16.
 */
@Service("gameDialTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.GAME_DIAL_DEDUCTION_POINT, topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_DIAL_DEDUCTION_POINT)
public class GameDialTransactionMainServiceImpl extends AbstractTransactionMainService<GameDialNotification, GameDialReply> {

    @Autowired
    private GameDialRecordService gameDialRecordService;

    @Override
    public GameDialNotification selectNotification(Long id) {
        GameDialRecordBO recordBO = gameDialRecordService.getGameDialRecord(id);
        if (recordBO == null) {
            return null;
        }

        GameDialNotification notification = new GameDialNotification();
        notification.setId(id);
        notification.setUserNum(recordBO.getUserNum());
        notification.setPoint(String.valueOf(recordBO.getPayPoint().intValue()));
        notification.setGmtExecute(recordBO.getGmtCreate());
        return notification;
    }

    @Override
    public void afterSuccess(Long id, GameDialReply reply) {
        if (reply.isResult()) {
            gameDialRecordService.updateGameDialRecordStatus(id, GameDialRecordStatusEnum.TAKE_PART_LOTTERY);
        } else {
            gameDialRecordService.updateGameDialRecordStatus(id, GameDialRecordStatusEnum.POINT_DEDUCT_FAIL);
        }
    }

}
