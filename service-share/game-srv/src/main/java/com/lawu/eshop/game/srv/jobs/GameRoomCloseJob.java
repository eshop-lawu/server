package com.lawu.eshop.game.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.game.srv.service.GameRoomService;

/**
 * 游戏房间关闭
 *
 * @author meishuquan
 * @date 2018/4/3.
 */
public class GameRoomCloseJob implements SimpleJob {

    private static Logger logger = LoggerFactory.getLogger(GameRoomCloseJob.class);

    @Autowired
    private GameRoomService gameRoomService;

    @Override
    public void execute(ShardingContext shardingContext) {
        logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

        gameRoomService.closeInvalidGameRoom();

        logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
    }

}
