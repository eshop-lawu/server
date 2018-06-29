package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public interface GameDialRecordService {

    /**
     * 中奖记录
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<GameDialRecordInfoBO> listGameDialRecord(GameDialRecordUserQuery query);

    /**
     * 根据状态查询用户抽奖记录
     *
     * @param gameDialId
     * @param userNum
     * @param statusEnum
     * @return
     * @author meishuquan
     */
    Long getTakePartLottery(Long gameDialId, String userNum, GameDialRecordStatusEnum statusEnum);

    /**
     * 保存抽奖记录
     *
     * @param param
     * @return
     * @author meishuquan
     */
    Long saveGameDialRecord(TakePartLotteryParam param) throws Exception;

    /**
     * 根据id查询抽奖记录
     *
     * @param id
     * @return
     * @author meishuquan
     */
    GameDialRecordBO getGameDialRecord(Long id);

    /**
     * 根据id更新抽奖记录状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updateGameDialRecordStatus(Long id, GameDialRecordStatusEnum statusEnum);

    /**
     * 领奖
     *
     * @param param
     * @author meishuquan
     */
    void takeLottery(TakeLotteryParam param);

    /**
     * 中奖
     *
     * @param id
     * @param gameDialPrizeId
     * @return
     */
    Integer winLottery(Long id, Long gameDialPrizeId) throws Exception;
    
    
    /**
     * 运营后台查询中奖记录列表
     * @param query
     * @return
     */
    Page<GameDialRecordBO> page(GameDailRecordPageQuery query);

}
