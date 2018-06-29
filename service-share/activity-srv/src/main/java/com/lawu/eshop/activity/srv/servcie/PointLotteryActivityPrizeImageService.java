package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.constants.PointLotteryActivityPrizeImageTypeEnum;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityPrizeImageBO;

/**
 * @author meishuquan
 * @date 2018/2/1.
 */
public interface PointLotteryActivityPrizeImageService {

    /**
     * 查询抽奖活动图片
     *
     * @param pointLotteryActivityId
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    List<String> listImagePath(Long pointLotteryActivityId, PointLotteryActivityPrizeImageTypeEnum typeEnum);

    /**
     * 抽奖活动图片列表
     *
     * @param pointLotteryActivityId
     * @param typeEnum
     * @return
     * @author meishuquan
     */
    List<PointLotteryActivityPrizeImageBO> listPointLotteryActivityPrizeImage(Long pointLotteryActivityId, PointLotteryActivityPrizeImageTypeEnum typeEnum);

}
