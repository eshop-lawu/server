package com.lawu.eshop.game.srv.service;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;

/**
 * 随机匹配接口
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public interface RandomMatchService {
    
    /**
     * 检查匹配结果
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月12日
     * @updateDate 2018年5月12日
     */
    GameMatchResultDTO checkMatch(CheckCacheMatchParam param);
    
    /**
     * 释放机器人资源
     * @param robotUserNum
     * @author jiangxinjun
     * @createDate 2018年5月13日
     * @updateDate 2018年5月13日
     */
    void releaseRobotResources(String robotUserNum);
}
