/**
 * 
 */
package com.lawu.eshop.jobs.impl.ad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.lawu.eshop.jobs.JobsConfig;
import com.lawu.eshop.jobs.service.UserRedPacketJobService;

/**
 * 用户红包24小时超时job
 * 
 * @author lihj
 * @date 2017年8月7日
 */

public class UserRedpacketJob implements SimpleJob {

	private static Logger logger = LoggerFactory.getLogger(UserRedpacketJob.class);

	@Autowired
	private UserRedPacketJobService userRedPacketJobService;

	@Autowired
	private JobsConfig jobsConfig;

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.debug("------{}-{} starting------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
		userRedPacketJobService.executeUserRedPacketData(jobsConfig.getPageSize());
		logger.debug("------{}-{} finished------", this.getClass().getSimpleName(), shardingContext.getShardingItem());
	}

}
