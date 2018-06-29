package com.lawu.eshop.merchant.api.mock.service;

import com.lawu.eshop.merchant.api.service.MerchantInfoService;
import com.lawu.eshop.user.dto.MerchantInfoDTO;
import com.lawu.eshop.user.dto.MerchantInfoFromInviteFansDTO;
import com.lawu.eshop.user.dto.MerchantInfoFromPublishAdDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.dto.param.MerchantSizeLinkDTO;
import com.lawu.eshop.user.param.MerchantProfileParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockMerchantInfoService extends BaseController implements MerchantInfoService {
    @Override
    public Result updateMerchantSizeLink(@ModelAttribute MerchantProfileParam merchantProfileParam, @PathVariable("id") Long id) {
        return successCreated();
    }

    @Override
    public Result<MerchantInfoDTO> getCurrentMerchantInfo(@PathVariable("merchantId") Long merchantId) {
        MerchantInfoDTO dto = new MerchantInfoDTO();
        return successGet(dto);
    }

    @Override
    public Result<MerchantSizeLinkDTO> getMerchantSizeLink(@PathVariable("merchantId") Long merchantId) {
        return successGet();
    }

	@Override
	public Result<MerchantInfoFromPublishAdDTO> getMerchantInfoFromPublishAd(Long merchantId) {
		MerchantInfoFromPublishAdDTO merchantInfoFromPublishAdDTO = new MerchantInfoFromPublishAdDTO();
		return successGet(merchantInfoFromPublishAdDTO);
	}

	@Override
	public Result<MerchantInfoFromInviteFansDTO> getMerchantInfoFromInviteFans(Long merchantId) {
		MerchantInfoFromInviteFansDTO merchantInfoFromInviteFansDTO = new MerchantInfoFromInviteFansDTO();
		return successGet(merchantInfoFromInviteFansDTO);
	}
}
