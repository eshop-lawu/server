package com.lawu.eshop.game.srv.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.cache.constants.RandomGameCacheKey;
import com.lawu.eshop.game.srv.service.GameCommonCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GameCacheUpdateJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(GameCacheUpdateJob.class);

	@Autowired
	private GameCommonCacheService gameCommonCacheService;

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());

		gameCommonCacheService.batchUpdateMatchEatchother(RandomGameCacheKey.READY.getVal(),RandomGameCacheKey.START.getVal());

		logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}
}
