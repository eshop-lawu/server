package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public interface GameDialService {

	/**
	 * 保存游戏设置
	 * @param param
	 */
	void saveGameDial(GameDialParam param);

    /**
     * 查询转盘游戏
     *
     * @return
     * @author meishuquan
     */
    GameDialBO getGameDial();
    
    /**
     * 修改
     * @param id
     * @param param
     */
    void updateGameDial(Long id ,GameDialParam param);

    /**
     * 查询
     * @param id
     * @return
     */
	GameDialBO getGameDialById(Long id);
	
	/**
	 * 查询列表
	 * @return
	 */
	Page<GameDialBO> page(GameDialQuery query);
	
	
	void setGameDial(Long id ,GameDialStatusEnum statusEnum);
    

}
