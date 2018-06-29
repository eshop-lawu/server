package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.game.param.UserStarRecordParam;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.bo.UserRankBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2018/3/9.
 */
public interface UserStarRecordService {
    void addUserStartRecord(UserStarRecordParam param);

    Page<RankListBO> getStarRankList(RankListParam param);

    UserRankBO currentUserRank(String userNum);

    void editStarStatus(Long id, Boolean status);

    void addStarById(Long id, Integer starCount);
}
