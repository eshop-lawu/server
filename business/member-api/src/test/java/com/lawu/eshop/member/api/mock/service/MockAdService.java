package com.lawu.eshop.member.api.mock.service;/**
 * Created by ${Yangqh} on 2017/7/24.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdEgainDTO;
import com.lawu.eshop.ad.dto.AdEgainQueryDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.AdSolrDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.dto.ClickAdPointDTO;
import com.lawu.eshop.ad.dto.HaveAdPraiseDTO;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.PointPoolDTO;
import com.lawu.eshop.ad.dto.PraisePointDTO;
import com.lawu.eshop.ad.dto.RedPacketInfoDTO;
import com.lawu.eshop.ad.param.AdChoicenessInternalParam;
import com.lawu.eshop.ad.param.AdEgainInternalParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdPointInternalParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.member.api.service.AdService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * <p> </p>
 *
 * @author yangqh
 * @date 2017/7/24 16:39
 */
@Service
public class MockAdService extends BaseController implements AdService{

    @Override
    public Result<Page<AdDTO>> selectListByMember(@RequestBody AdMemberParam adMemberParam, @RequestParam("memberId") Long memberId) {
        AdDTO dto = new AdDTO();
        dto.setId(1L);
        dto.setMerchantId(1L);
        dto.setTitle("title");
        dto.setMediaUrl("/media/1.jpg");
        dto.setContent("content");
        dto.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        dto.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        dto.setBeginTime(new Date());
        dto.setAreas("110101");
        dto.setRadius(1);
        dto.setPoint(new BigDecimal("1"));
        dto.setTotalPoint(new BigDecimal("1"));
        dto.setAdCount(1);
        dto.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        dto.setGmtCreate(new Date());
        dto.setViewCount(1);
        dto.setName("name");
        dto.setMerchantStoreId(1L);
        dto.setNumber(1);
        dto.setLogoUrl("/logo/1.jpg");
        dto.setNeedBeginTime(1L);
        dto.setIsFavorite(true);
        dto.setIsPraise(false);
        dto.setAdPraiseStatusEnum(AdPraiseStatusEnum.AD_STATUS_END);
        dto.setManageTypeEnum(ManageTypeEnum.COMMON);
        dto.setVideoImgUrl("video/1.jpg");
        dto.setAccount("account");
        dto.setAuditorId(1);
        dto.setAuditorName("auditname");
        dto.setIsClickAd(false);
        dto.setRemark("remark");
        List<AdDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<AdDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<AdEgainDTO> selectAbById(Long id, Long memberId) {
    	AdEgainDTO addto = new AdEgainDTO();
        addto.setId(1L);
        addto.setViewCount(1);
        addto.setContent("content");
        addto.setIsFavorite(false);
        addto.setTitle("title");
        addto.setMediaUrl("/mediaurl/1.jpg");
        addto.setVideoImgUrl("/video/1.mp4");
        addto.setMerchantId(1L);
        addto.setMerchantStoreId(1L);
        addto.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        addto.setIsClickAd(false);
        return successCreated(addto);
    }

    @Override
    public Result<Page<AdDTO>> selectPraiseListByMember(@RequestBody AdPraiseParam adPraiseParam, @RequestParam("memberId") Long memberId) {
        AdDTO dto = new AdDTO();
        dto.setId(1L);
        dto.setMerchantId(1L);
        dto.setTitle("title");
        dto.setMediaUrl("/media/1.jpg");
        dto.setContent("content");
        dto.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        dto.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        dto.setBeginTime(new Date());
        dto.setAreas("110101");
        dto.setRadius(1);
        dto.setPoint(new BigDecimal("1"));
        dto.setTotalPoint(new BigDecimal("1"));
        dto.setAdCount(1);
        dto.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        dto.setGmtCreate(new Date());
        dto.setViewCount(1);
        dto.setName("name");
        dto.setMerchantStoreId(1L);
        dto.setNumber(1);
        dto.setLogoUrl("/logo/1.jpg");
        dto.setNeedBeginTime(1L);
        dto.setIsFavorite(true);
        dto.setIsPraise(false);
        dto.setAdPraiseStatusEnum(AdPraiseStatusEnum.AD_STATUS_END);
        dto.setManageTypeEnum(ManageTypeEnum.COMMON);
        dto.setVideoImgUrl("video/1.jpg");
        dto.setAccount("account");
        dto.setAuditorId(1);
        dto.setAuditorName("auditname");
        dto.setIsClickAd(false);
        dto.setRemark("remark");
        List<AdDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<AdDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<List<PointPoolDTO>> selectMemberList(@RequestParam("id") Long id) {
        PointPoolDTO dto = new PointPoolDTO();
        dto.setMemberId(1L);
        dto.setPoint(new BigDecimal("100"));
        List<PointPoolDTO> list = new ArrayList<>();
        list.add(dto);
        return successCreated(list);
    }

    @Override
    public Result<PraisePointDTO> clickPraise(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId, @RequestParam("num") String num) {
        return null;
    }

    @Override
    public Result<ClickAdPointDTO> clickAd(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId, @RequestParam("num") String num) {
        ClickAdPointDTO dto = new ClickAdPointDTO();
        dto.setPoint(new BigDecimal("1"));
        dto.setAddPoint(new BigDecimal("0.997"));
        return successCreated(dto);
    }

    @Override
    public Result<Page<AdSolrDTO>> queryAdByTitle(@ModelAttribute AdsolrFindParam adSolrParam) {
        AdSolrDTO dto = new AdSolrDTO();
        List<AdSolrDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<AdSolrDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<Page<AdDTO>> selectChoiceness(@ModelAttribute AdMemberParam param) {
        AdDTO dto = new AdDTO();
        dto.setId(1L);
        dto.setMerchantId(1L);
        dto.setTitle("title");
        dto.setMediaUrl("/media/1.jpg");
        dto.setContent("content");
        dto.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        dto.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        dto.setBeginTime(new Date());
        dto.setAreas("110101");
        dto.setRadius(1);
        dto.setPoint(new BigDecimal("1"));
        dto.setTotalPoint(new BigDecimal("1"));
        dto.setAdCount(1);
        dto.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        dto.setGmtCreate(new Date());
        dto.setViewCount(1);
        dto.setName("name");
        dto.setMerchantStoreId(1L);
        dto.setNumber(1);
        dto.setLogoUrl("/logo/1.jpg");
        dto.setNeedBeginTime(1L);
        dto.setIsFavorite(true);
        dto.setIsPraise(false);
        dto.setAdPraiseStatusEnum(AdPraiseStatusEnum.AD_STATUS_END);
        dto.setManageTypeEnum(ManageTypeEnum.COMMON);
        dto.setVideoImgUrl("video/1.jpg");
        dto.setAccount("account");
        dto.setAuditorId(1);
        dto.setAuditorName("auditname");
        dto.setIsClickAd(false);
        dto.setRemark("remark");
        List<AdDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<AdDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<PraisePointDTO> getRedPacket(@RequestParam("merchantId") Long merchantId, @RequestParam("memberId") Long memberId, @RequestParam("memberNum") String memberNum) {
        PraisePointDTO dto = new PraisePointDTO();
        dto.setPoint(new BigDecimal("100"));
        return successCreated(dto);
    }

    @Override
    public Result<RedPacketInfoDTO> getRedPacketInfo(@PathVariable("merchantId") Long merchantId) {
        RedPacketInfoDTO dto = new RedPacketInfoDTO();
        dto.setPoint(new BigDecimal("100"));
        dto.setInviterAccount("1232312312");
        return successCreated(dto);
    }

    @Override
    public Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable("merchantId") Long merchantId) {
        IsExistsRedPacketDTO dto = new IsExistsRedPacketDTO();
        return successCreated(dto);
    }

    @Override
    public Result<Page<AdEgainQueryDTO>> selectPageAdEgain(@PathVariable("memberId") Long memberId, @RequestBody AdEgainInternalParam param) {
        AdEgainQueryDTO dto = new AdEgainQueryDTO();
        List<AdEgainQueryDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<AdEgainQueryDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<List<AdPointDTO>> selectAdPoint(@RequestBody AdPointInternalParam param) {
        AdPointDTO dto = new AdPointDTO();
        List<AdPointDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        return successCreated(rtnList);
    }

    @Override
    public Result<Page<ChoicenessAdDTO>> selectChoiceness(@PathVariable("memberId") Long memberId, @RequestBody AdChoicenessInternalParam param) {
        ChoicenessAdDTO dto = new ChoicenessAdDTO();
        List<ChoicenessAdDTO> rtnList = new ArrayList<>();
        rtnList.add(dto);
        Page<ChoicenessAdDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setTotalCount(1);
        page.setRecords(rtnList);
        return successCreated(page);
    }

    @Override
    public Result<Page<AdFlatVideoDTO>> getRecommendAdByType(@RequestBody AdSolrRealParam param) {
        return null;
    }

    @Override
    public Result<Page<AdPraiseDTO>> getRecommendEgain(@RequestBody AdSolrRealParam param) {
        return null;
    }

    @Override
    public Result<List<AdDTO>> listAdRank(@RequestBody AdSolrRealParam param) {
        return null;
    }


    @Override
    public Result<Page<AdDTO>> listAd(@RequestBody AdSolrRealParam param) {
        return null;
    }

    @Override
	public Result<AdPraiseDTO> selectAdPraiseById(Long id, Long memberId) {
		AdPraiseDTO addto = new AdPraiseDTO();
        addto.setId(1L);
        addto.setContent("content");
        addto.setIsFavorite(false);
        addto.setTitle("title");
        addto.setMediaUrl("/mediaurl/1.jpg");
        addto.setMerchantId(1L);
        addto.setMerchantStoreId(1L);
        addto.setIsPraise(true);
        addto.setNeedBeginTime(100L);
        addto.setBeginTime(new Date());
        addto.setTotalPoint(new BigDecimal("100"));
        addto.setClickPraiseAdTimes(20);
        addto.setIsDoHanlderMinusPoint(true);
        addto.setLogoUrl("/logourl/1.jpg");
        addto.setName("E店商家");
        addto.setManageTypeEnum(ManageTypeEnum.COMMON);
        return successCreated(addto);
	}

	@Override
	public Result<Integer> getInventory(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<HaveAdPraiseDTO> haveAdPraise() {
		// TODO Auto-generated method stub
		return null;
	}
}
