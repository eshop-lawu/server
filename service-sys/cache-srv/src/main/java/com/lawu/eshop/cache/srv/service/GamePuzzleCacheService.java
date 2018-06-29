package com.lawu.eshop.cache.srv.service;

import java.util.List;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public interface GamePuzzleCacheService {
    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     *
     * @param userNum
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月16日
     */
    List<String> readyStartGame(String userNum, String attendNum);

	Long incrementCount(String attendNum);
}
