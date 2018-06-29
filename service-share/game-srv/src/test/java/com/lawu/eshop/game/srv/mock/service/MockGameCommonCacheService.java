package com.lawu.eshop.game.srv.mock.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GamePuzzleCacheDetail;
import com.lawu.eshop.cache.dto.GamePuzzleCallBackCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.JoinGameCacheParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;
import com.lawu.eshop.game.srv.service.GameCommonCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
@Service
public class MockGameCommonCacheService extends BaseController implements GameCommonCacheService {
    @Override
    public Result joinCache(@RequestBody JoinGameCacheParam param) {
    	return null;
    }

    @Override
    public Result<GameMatchResultDTO> checkCacheMatchEatchother(@RequestBody CheckCacheMatchParam param) {
        return null;
    }

    @Override
    public void batchUpdateMatchEatchother(@RequestParam("key") String key, @RequestParam("newKey") String newKey) {

    }

    @Override
    public Result<Long> incrementAndGet(String key) {
        return null;
    }

	@Override
	public Result<GamePuzzleCallBackCacheDTO> setCallBackCache(GamePuzzleCacheDetail param) {
		return null;
	}

    @Override
    public Result matchingRobot(MatchingRobotParam param) {
        return null;
    }

    @Override
    public void exitMatchQueue(ExitMatchQueueParam param) {
    }
}
