package com.lawu.eshop.game.srv.service;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public interface GameDialPrizeService {

    /**
     * 查询转盘游戏奖品
     *
     * @param gameDialId
     * @param minInventory
     * @return
     * @author meishuquan
     */
    List<GameDialPrizeBO> listGameDialPrize(Long gameDialId, Integer minInventory);
    
    /**
     * 参与转盘的奖品列表
     * 
     * @param query
     * @return
     */
    Page<GameDialPrizeBO> page(GameDailPrizeQuery query);
    
    /**
     * 新增修改参与商品信息
     * @param id
     * @param param
     */
    void updateGameDialPrize(GameDailPrizeParam param) throws WrongOperationException;
    
    /**
     * 禁用启用
     * 
     * @param id
     * @param statusEnum
     */
    void setGameDialPrizeStatus(Long id,GameDialPrizeStatusEnum statusEnum) throws WrongOperationException;

    /**
     * 根据id查询奖品信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    GameDialPrizeBO getGameDialPrize(Long id);

    /**
     * 查询剩余奖品概率
     * 
     * @param gameDialPrizeId
     * @return
     */
	BigDecimal getLeftPrizeRate(Long gameDialId);

}
