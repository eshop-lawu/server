package com.lawu.eshop.game.srv.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.cache.constants.RandomGameCacheKey;
import com.lawu.eshop.game.srv.service.GameAccountService;

/**
 * 每个月定时星星清零
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月15日
 */
public class GameAccountStarResetJob implements SimpleJob {
	
	
	private static Logger logger = LoggerFactory.getLogger(GameAccountStarResetJob.class);

	@Autowired
	private GameAccountService gameAccountService;

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

		gameAccountService.accountStarReset();

		logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}

}
