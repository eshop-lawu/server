package com.lawu.eshop.game.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStartParam;
import com.lawu.eshop.game.srv.domain.extend.StarRankListDOView;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public interface UserStarRecordDOMapperExtend {
    void addStarRecord(UserStartParam startParam);

    void reduceStarMonthRecord(UserStartParam startParam);

    List<StarRankListDOView> getStarRankList(RankListParam param);

    int getStarRankListCount(RankListParam param);
}
