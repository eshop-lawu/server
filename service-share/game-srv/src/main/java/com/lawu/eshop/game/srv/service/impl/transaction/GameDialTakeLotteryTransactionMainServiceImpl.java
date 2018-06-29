package com.lawu.eshop.game.srv.service.impl.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.compensating.transaction.annotation.CompensatingTransactionMain;
import com.lawu.compensating.transaction.service.impl.AbstractTransactionMainService;
import com.lawu.eshop.common.constants.PayTypeEnum;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.constants.TransactionConstant;
import com.lawu.eshop.game.srv.service.GameDialPrizeService;
import com.lawu.eshop.game.srv.service.GameDialRecordService;
import com.lawu.eshop.mq.dto.game.GameDialTakeLotteryNotification;
import com.lawu.eshop.mq.dto.game.reply.GameDialTakeLotteryReply;
import com.lawu.eshop.mq.impl.constants.MqConstant;


/**
 * 活动模块，领奖(余额/积分) - 主事务
 *
 * @author meishuquan
 * @date 2018/2/28.
 */
@Service("gameDialTakeLotteryTransactionMainServiceImpl")
@CompensatingTransactionMain(value = TransactionConstant.GAME_DIAL_TAKE_LOTTERY, topic = MqConstant.TOPIC_GAME_SRV, tags = MqConstant.TAG_GAME_DIAL_TAKE_LOTTERY)
public class GameDialTakeLotteryTransactionMainServiceImpl extends AbstractTransactionMainService<GameDialTakeLotteryNotification, GameDialTakeLotteryReply> {

    @Autowired
    private GameDialRecordService gameDialRecordService;

    @Autowired
    private GameDialPrizeService gameDialPrizeService;

    @Override
    public GameDialTakeLotteryNotification selectNotification(Long id) {
        GameDialRecordBO recordBO = gameDialRecordService.getGameDialRecord(id);
        if (recordBO == null) {
            return null;
        }
        GameDialPrizeBO prizeBO = gameDialPrizeService.getGameDialPrize(recordBO.getGameDialPrizeId());
        if (prizeBO == null) {
            return null;
        }

        GameDialTakeLotteryNotification notification = new GameDialTakeLotteryNotification();
        notification.setId(id);
        notification.setUserNum(recordBO.getUserNum());
        notification.setMoney(String.valueOf(prizeBO.getPrice()));
        notification.setPayTypeEnum(PayTypeEnum.getEnum(prizeBO.getPrizeType()));
        notification.setGmtExecute(recordBO.getGmtCreate());
        return notification;
    }

    @Override
    public void afterSuccess(Long id, GameDialTakeLotteryReply reply) {
        if (reply.isResult()) {
            gameDialRecordService.updateGameDialRecordStatus(id, GameDialRecordStatusEnum.SEND_LOTTERY);
        }
    }

}
