package com.lawu.eshop.ad.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.ad.param.AdPlatformExtendParam;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformDOView;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformFlatView;
import com.lawu.eshop.ad.srv.domain.extend.AdPlatformVideoView;
import com.lawu.eshop.ad.srv.domain.extend.SelectAdEgainIdExample;

/**
 * @author meishuquan
 * @date 2017/6/16.
 */
public interface AdPlatformDOMapperExtend {

    List<AdPlatformDOView> getAdPlatformByTypePosition(AdPlatformExtendParam param);

	List<AdPlatformVideoView> selAdPlatformPositionAd(SelectAdEgainIdExample example);
	
	List<AdPlatformFlatView> selAdPlatformPositionFour(SelectAdEgainIdExample example);
	
	int updateByPrimaryKeySelective(AdPlatformDO record);
}
