package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.product.dto.MemberProductStoreDTO;
import com.lawu.eshop.user.dto.MerchantAdInfoDTO;
import com.lawu.eshop.user.dto.MerchantInfoForShoppingCartDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreFavorInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreStatusDTO;
import com.lawu.eshop.user.dto.PayOrderMerchantStoreInfoDTO;
import com.lawu.eshop.user.dto.PayOrderStoreInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindMerchantInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindUserInfoDTO;
import com.lawu.eshop.user.dto.ShoppingStoreDetailDTO;
import com.lawu.eshop.user.dto.StoreDetailDTO;
import com.lawu.eshop.user.dto.StoreSolrInfoDTO;
import com.lawu.eshop.user.dto.VisitUserInfoDTO;
import com.lawu.eshop.user.param.ShoppingOrderFindUserInfoParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;


@Service
public class MockMerchantStoreService extends BaseController implements MerchantStoreService {

    @Override
    public Result<MerchantInfoForShoppingCartDTO> getMerchantInfoForShoppingCart(@PathVariable("merchantId") Long merchantId) {
        MerchantInfoForShoppingCartDTO dto = new MerchantInfoForShoppingCartDTO();
        dto.setMerchantStoreId(1L);
        dto.setMerchantStoreName("name");
        return successCreated(dto);
    }

    @Override
    public Result findIsNoReasonReturnById(@RequestParam("merchantId") Long merchantId) {
        return null;
    }

    @Override
    public Result<ShoppingOrderFindUserInfoDTO> shoppingOrderFindUserInfo(@RequestBody ShoppingOrderFindUserInfoParam param) {
        ShoppingOrderFindUserInfoDTO dto = new ShoppingOrderFindUserInfoDTO();
        dto.setMemberNum("Mfdfdf");
        List<ShoppingOrderFindMerchantInfoDTO> ddtos = new ArrayList<>();
        ShoppingOrderFindMerchantInfoDTO ddto = new ShoppingOrderFindMerchantInfoDTO();
        ddto.setMerchantId(1L);
        ddto.setIsFans(true);
        ddto.setIsNoReasonReturn(true);
        ddto.setMerchantNum("M4343");
        ddto.setMerchantStoreId(1L);
        ddto.setMerchantStoreRegionPath("1/2/3");
        ddto.setMerchantStoreName("fdfdf");
        ddtos.add(ddto);
        dto.setShoppingOrderFindMerchantInfoDTOList(ddtos);
        return successCreated(dto);
    }

    @Override
    public Result<StoreDetailDTO> getStoreDetailById(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
        StoreDetailDTO dto = new StoreDetailDTO();
        dto.setMerchantId(1L);
        return successCreated(dto);
    }

    @Override
    public Result<MerchantStoreDTO> selectMerchantStoreByMerchantId(@PathVariable("merchantId") Long merchantId) {
        return null;
    }

    @Override
    public Result<MerchantStoreDTO> selectMerchantStoreByMId(@RequestParam("merchantId") Long merchantId) {
        MerchantStoreDTO dto = new MerchantStoreDTO();
        dto.setName("嘉旺");
        dto.setMerchantStoreId(1L);
        dto.setLogoUrl("/logo/1.jpg");
        dto.setLatitude(new BigDecimal("22.23"));
        dto.setLongitude(new BigDecimal("120.343"));
        return successCreated(dto);
    }

    @Override
    public MerchantStoreDTO findStoreNameAndImgByMerchantId(@PathVariable("merchantId") Long merchantId) {
        return null;
    }

    @Override
    public Result<ShoppingStoreDetailDTO> getShoppingStoreDetailById(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId) {
        ShoppingStoreDetailDTO dto = new ShoppingStoreDetailDTO();
        return successCreated(dto);
    }

    @Override
    public Result<MerchantStoreDTO> getMerchantStoreById(@PathVariable("id") Long id) {
        return null;
    }

    @Override
    public Result<MemberProductStoreDTO> getMemberProductDetailStore(@RequestParam("merchantId") Long merchantId) {
        MemberProductStoreDTO dto = new MemberProductStoreDTO();
        dto.setFansNum("1");
        dto.setLogo("1.jpg");
        dto.setStoreId(1L);
        dto.setStoreName("storename");
        dto.setSupportEleven(true);
        dto.setUpProductNum("111");
        return successCreated(dto);
    }

    @Override
    public Result<ManageTypeEnum> getManageType(@RequestParam("merchantId") Long merchantId) {
        return successCreated(ManageTypeEnum.COMMON);
    }

    @Override
    public Result<List<PayOrderStoreInfoDTO>> getPayOrderStoreInfo(@RequestParam("merchantIds") List<Long> merchantIds) {
        PayOrderStoreInfoDTO dto = new PayOrderStoreInfoDTO();
        dto.setMerchantId(1L);
        dto.setName("name");
        dto.setStoreUrl("1.jpg");
        List<PayOrderStoreInfoDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<List<StoreSolrInfoDTO>> getMerchantStoreByIds(@RequestParam("merchantStoreIds") List<Long> merchantStoreIds) {
        StoreSolrInfoDTO dto = new StoreSolrInfoDTO();
        dto.setMerchantStoreId(1L);
        dto.setIndustryName("fdfd");
        dto.setIndustryPath("fdfd");
        dto.setMerchantId(1L);
        List<StoreSolrInfoDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<List<MerchantAdInfoDTO>> getAdMerchantStoreByIds(@RequestParam("merchantIds") List<Long> merchantIds) {
        MerchantAdInfoDTO dto = new MerchantAdInfoDTO();
        dto.setMerchantId(1L);
        dto.setMerchantStoreId(1L);
        dto.setName("嘉旺");
        dto.setPath("/path/1.jpg");
        dto.setManageTypeEnum(ManageTypeEnum.COMMON);

        return null;
    }

    @Override
    public MerchantStoreStatusDTO merchantStoreIsExist(@PathVariable("id") Long id) {
        MerchantStoreStatusDTO dto = new MerchantStoreStatusDTO();
        dto.setExist(true);
        //dto.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CHECKED.val);//
        dto.setStatus(MerchantStatusEnum.MERCHANT_STATUS_CANCEL.val);
        return dto;
    }

    @Override
    public String getLogoUrlByStoreId(@PathVariable("id") Long id) {
        return "1.jpg";
    }

    @Override
    public String getStoreUrlByStoreId(@PathVariable("id") Long id) {
        return "fk.jpg";
    }

    @Override
    public PayOrderMerchantStoreInfoDTO getPayOrderDetailStoreInfo(@RequestParam("merchantId") Long merchantId) {
        PayOrderMerchantStoreInfoDTO dto = new PayOrderMerchantStoreInfoDTO();
        dto.setName("name");
        return dto;
    }

    @Override
    public VisitUserInfoDTO findAccountAndRegionPathByNum(@RequestParam("merchantNum") String merchantNum) {
        VisitUserInfoDTO dto = new VisitUserInfoDTO();
        dto.setRegionPath("1/11/111");
        dto.setAccount("1122212");
        return dto;
    }

	@Override
	public Result<MerchantStoreFavorInfoDTO> selectMerchantStoreFavor(Long merchantId) {
		// TODO Auto-generated method stub
		return null;
	}
}
