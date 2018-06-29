package com.lawu.eshop.user.srv.mapper.extend;

import java.util.List;

import com.lawu.eshop.user.srv.domain.extend.MerchantInfoFromInviteFansDOView;

public interface MerchantInfoFromInviteFansDOMapperExtend {

	List<MerchantInfoFromInviteFansDOView> getMerchantInfoFromInviteFans(Long merchantId);
	
}
