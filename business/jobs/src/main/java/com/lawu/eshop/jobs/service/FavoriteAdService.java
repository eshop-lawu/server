package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lawu.eshop.ad.dto.FavoriteAdPraiseWarnDTO;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.framework.web.Result;

/**
 * 
 * @author zhangrc
 * @date 2017/07/17
 *
 */
@FeignClient(value = "ad-srv")
public interface FavoriteAdService {
	
	@RequestMapping(value = "favoriteAd/selectFavoriteAdPraise", method = RequestMethod.POST)
    Result<List<FavoriteAdPraiseWarnDTO>> selectFavoriteAdPraise(@RequestBody PraiseWarnParam param);
	
	
	@RequestMapping(value = "favoriteAd/updateIsSend/{id}", method = RequestMethod.PUT)
    Result updateIsSend(@PathVariable("id") Long id);
	
}
