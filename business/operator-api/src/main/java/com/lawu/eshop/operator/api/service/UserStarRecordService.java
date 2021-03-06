package com.lawu.eshop.operator.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2018/3/10.
 */
@FeignClient(value = "game-srv")
public interface UserStarRecordService {

    @RequestMapping(value = "userStar/getStarRankList", method = RequestMethod.POST)
    Result<Page<UserStarRankListDTO>> getStarRankList(@ModelAttribute RankListParam param);

    @RequestMapping(value = "userStar/editStarStatus/{id}", method = RequestMethod.PUT)
    Result editStarStatus(@PathVariable("id") Long id , @RequestParam("status") Boolean status);

    @RequestMapping(value = "userStar/addStarById/{id}", method = RequestMethod.PUT)
    Result addStarById(@PathVariable("id") Long id, @RequestParam("starCount") Integer starCount);
}
