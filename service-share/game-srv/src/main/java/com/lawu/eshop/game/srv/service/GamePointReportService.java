package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.game.srv.bo.GamePointReportBO;

/**
 * 游戏积分统计
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月23日
 */
public interface GamePointReportService {
	
	/**
	 * 游戏积分日统计
	 * 
	 * @param today
	 * @return
	 */
	List<GamePointReportBO> getGamePointReport(String today);
	
	

}
