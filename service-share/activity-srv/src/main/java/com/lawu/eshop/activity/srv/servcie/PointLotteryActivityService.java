package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.constants.PointLotteryActivityStatusEnum;
import com.lawu.eshop.activity.param.GenerateBasicNumberParam;
import com.lawu.eshop.activity.param.PointLotteryActivityParam;
import com.lawu.eshop.activity.query.OperatorPointLotteryActivityQuery;
import com.lawu.eshop.activity.query.PointLotteryActivityQuery;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public interface PointLotteryActivityService {

    /**
     * 积分抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<PointLotteryActivityBO> listPointLotteryActivity(PointLotteryActivityQuery query);

    /**
     * 根据id查询积分抽奖活动
     *
     * @param id
     * @return
     * @author meishuquan
     */
    PointLotteryActivityBO getPointLotteryActivity(Long id);
    
    
    /**
     * 生成基础中奖号码
     * @param snatchPrizesActivityId 积分夺宝活动id
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    Integer generateBasicNumber(Long snatchPrizesActivityId, GenerateBasicNumberParam param);
    
    /**
     * 保存中奖号码
     * @param snatchPrizesActivityId 积分夺宝活动id
     * @param prizeNumbers 中奖号码用','分隔
     * @author jiangxinjun
     * @createDate 2018年2月1日
     * @updateDate 2018年2月1日
     */
    void saveWinningNumber(Long snatchPrizesActivityId, String prizeNumbers);

    /**
     * 保存积分抽奖活动
     *
     * @param param
     * @author meishuquan
     */
    void savePointLotteryActivity(PointLotteryActivityParam param);

    /**
     * 运营平台抽奖活动列表
     *
     * @param query
     * @return
     * @author meishuquan
     */
    Page<PointLotteryActivityBO> listOperatorPointLotteryActivity(OperatorPointLotteryActivityQuery query);

    /**
     * 更新抽奖活动状态
     *
     * @param id
     * @param statusEnum
     * @author meishuquan
     */
    void updatePointLotteryActivityStatus(Long id, PointLotteryActivityStatusEnum statusEnum);

    /**
     * 定时更新抽奖活动进行中、已结束、已开奖状态
     *
     * @author meishuquan
     */
    void executeUpdatePointLotteryActivityStatus();

    /**
     * 查询进行中和即将开始的积分抽奖活动
     *
     * @return
     * @author meishuquan
     */
    List<PointLotteryActivityBO> listRelatePointLotteryActivity();

}
