package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.param.DealInformParam;
import com.lawu.eshop.game.param.GameInformParam;
import com.lawu.eshop.game.query.GameInformQuery;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.framework.core.page.Page;

/**
 * 游戏举报
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public interface GameInformService {
	
	/**
	 * 举报
	 * @param userNum
	 * @param param
	 */
	void saveGameInform(String userNum ,GameInformParam param) throws WrongOperationException ;
	
	/**
	 * 举报列表
	 * @param query
	 * @return
	 */
	Page<GameInformBO> page(GameInformQuery query);
	
	/**
	 * 处理举报
	 * @param param
	 */
	void dealInform(DealInformParam param);
	
	

}
