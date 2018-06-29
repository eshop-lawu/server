package com.lawu.eshop.merchant.api.mock.service;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.ad.dto.AdPayInfoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdDetailDTO;
import com.lawu.eshop.ad.dto.AdMerchantDTO;
import com.lawu.eshop.ad.dto.AdMerchantDetailDTO;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.IsMyDateDTO;
import com.lawu.eshop.ad.dto.PointGetDetailDTO;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.merchant.api.service.AdService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/7/24.
 */
@Service
public class MockAdService extends BaseController implements AdService {

    @Override
    public Result saveAd(@RequestBody AdSaveParam adSaveParam) {
        return successCreated();
    }

    @Override
    public Result<Page<AdMerchantDTO>> selectListByMerchant(@RequestBody AdMerchantParam adMerchantParam, @RequestParam("memberId") Long memberId) {
        return successCreated();
    }

    @Override
    public Result updateStatus(@PathVariable("id") Long id) {
        return successCreated();
    }

    @Override
    public Result remove(@PathVariable("id") Long id) {
        return successCreated();
    }

    @Override
    public Result<AdDTO> selectAbById(@PathVariable("id") Long id) {
        return successGet();
    }

    @Override
    public Result<AdMerchantDetailDTO> selectById(@PathVariable("id") Long id) {
        AdMerchantDetailDTO dto = new AdMerchantDetailDTO();
        dto.setTotalPoint(BigDecimal.valueOf(100));
        dto.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        dto.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        return successGet(dto);
    }

    @Override
    public Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable("merchantId") Long merchantId) {
        IsExistsRedPacketDTO dto = new IsExistsRedPacketDTO();
        dto.setIsExistsRedPacket(true);
        return successGet(dto);
    }

    @Override
    public Result batchDeleteAd(@RequestParam("ids") List<Long> ids, @RequestParam("merchantId") Long merchantId) {
        return successDelete();
    }

    @Override
    public Result<AdDetailDTO> selectDetail(@PathVariable("id") Long id) {
        AdDetailDTO dto = new AdDetailDTO();
        dto.setProductId(10L);
        return successGet(dto);
    }

    @Override
    public Result<IsMyDateDTO> isMyData(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId) {
        IsMyDateDTO dto = new IsMyDateDTO();
        dto.setFlag(true);
        return successGet(dto);
    }

    @Override
    public Result<AdPayInfoDTO> selectAdPayInfoById(@PathVariable("id") Long id) {
        return null;
    }

	@Override
	public Result<Boolean> isPay(@PathVariable("id") Long id) {
		return null;
	}

	@Override
	public Result<Page<PointGetDetailDTO>> getDetailPage(PointGetDetailParam param) {
		return null;
	}
}
