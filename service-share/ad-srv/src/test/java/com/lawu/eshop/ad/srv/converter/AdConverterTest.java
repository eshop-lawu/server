package com.lawu.eshop.ad.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdDetailDTO;
import com.lawu.eshop.ad.dto.AdEgainDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdMerchantDTO;
import com.lawu.eshop.ad.dto.AdMerchantDetailDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.AdSolrDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.srv.bo.AdBO;
import com.lawu.eshop.ad.srv.bo.AdDetailBO;
import com.lawu.eshop.ad.srv.bo.AdEgainBO;
import com.lawu.eshop.ad.srv.bo.AdEgainDetailBO;
import com.lawu.eshop.ad.srv.bo.AdPointBO;
import com.lawu.eshop.ad.srv.bo.ChoicenessAdBO;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @author zhangrc
 * @date 2017/07/19
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class AdConverterTest {

	@Test
    public void convertBO(){
		
		AdDO ad=new AdDO();
		ad.setId(1l);
		ad.setAdCount(10);
		ad.setBeginTime(new Date());
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setViewcount(20);
        ad.setAreas("11");
        ad.setRadius(15);
        ad.setAuditorId(12);
        ad.setRemark("审核");
        AdBO  bo = AdConverter.convertBO(ad);
        
        Assert.assertNotNull(bo);
    }
	
	
	@Test
    public void convertBOS(){
		
		AdDO ad=new AdDO();
		ad.setId(1l);
		ad.setAdCount(10);
		ad.setBeginTime(new Date());
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setViewcount(20);
        ad.setAreas("11");
        ad.setRadius(15);
        ad.setAuditorId(12);
        ad.setRemark("审核");
        
        List<AdDO> list = new ArrayList<AdDO>();
        list.add(ad);
        List<AdBO>  boList = AdConverter.convertBOS(list);
        
        Assert.assertNotNull(boList);
    }
	
	@Test
    public void convertDTO(){
        
        AdBO adBO=new AdBO();
        adBO.setId(1l);
		adBO.setAdCount(10);
		adBO.setBeginTime(new Date());
		adBO.setIsFavorite(true);
		adBO.setIsPraise(false);
		adBO.setNumber(10);
		adBO.setMediaUrl("ad_image/1494582624025648401.png");
		adBO.setMerchantId(1l);
		adBO.setGmtCreate(new Date());
		adBO.setAdCount(10);
		adBO.setPoint(BigDecimal.valueOf(10));
		adBO.setTitle("广告测试内容");
		adBO.setTypeEnum(AdTypeEnum.AD_TYPE_PRAISE);
		adBO.setPutWayEnum(PutWayEnum.PUT_WAY_FENS);
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
		adBO.setTotalPoint(BigDecimal.valueOf(10));
		adBO.setViewCount(10);
		adBO.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		adBO.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		adBO.setAreas("11/12");
		adBO.setRadius(15);
		adBO.setContent("内容");
        
        AdDTO  dto = AdConverter.convertDTO(adBO);
        
        Assert.assertNotNull(dto);
    }
	
	
	@Test
    public void convertDTOS(){
        
        AdBO adBO=new AdBO();
        adBO.setId(1l);
		adBO.setAdCount(10);
		adBO.setBeginTime(new Date());
		adBO.setMediaUrl("ad_image/1494582624025648401.png");
		adBO.setMerchantId(1l);
		adBO.setGmtCreate(new Date());
		adBO.setAdCount(10);
		adBO.setPoint(BigDecimal.valueOf(10));
		adBO.setTitle("广告测试内容");
		adBO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adBO.setPutWayEnum(PutWayEnum.PUT_WAY_FENS);
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
		adBO.setTotalPoint(BigDecimal.valueOf(10));
		adBO.setViewCount(10);
		adBO.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		adBO.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		adBO.setAreas("11/12");
		adBO.setRadius(15);
		adBO.setContent("内容");
        
        List<AdBO> list = new ArrayList<AdBO>();
        list.add(adBO);
        List<AdDTO>  dtoList = AdConverter.convertDTOS(list);
        
        Assert.assertNotNull(dtoList);
    }
	
	@Test
    public void convertMerchantAdDTO(){
        
        AdBO adBO=new AdBO();
        
        adBO.setId(1l);
		adBO.setAdCount(10);
		adBO.setBeginTime(new Date());
		adBO.setMediaUrl("ad_image/1494582624025648401.png");
		adBO.setMerchantId(1l);
		adBO.setGmtCreate(new Date());
		adBO.setAdCount(10);
		adBO.setPoint(BigDecimal.valueOf(10));
		adBO.setTitle("广告测试内容");
		adBO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adBO.setPutWayEnum(PutWayEnum.PUT_WAY_FENS);
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
		adBO.setTotalPoint(BigDecimal.valueOf(10));
		adBO.setViewCount(10);
		adBO.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		adBO.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		adBO.setAreas("11/12");
		adBO.setRadius(15);
		adBO.setContent("内容");
        
        AdMerchantDTO  dtoList = AdConverter.convertMerchantAdDTO(adBO);
        
        Assert.assertNotNull(dtoList);
    }
	
	@Test
    public void convertPraiseDTOS(){
        
        AdBO adBO=new AdBO();
        adBO.setId(1l);
		adBO.setAdCount(10);
		adBO.setBeginTime(new Date());
		adBO.setMediaUrl("ad_image/1494582624025648401.png");
		adBO.setMerchantId(1l);
		adBO.setGmtCreate(new Date());
		adBO.setAdCount(10);
		adBO.setPoint(BigDecimal.valueOf(10));
		adBO.setTitle("广告测试内容");
		adBO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adBO.setPutWayEnum(PutWayEnum.PUT_WAY_FENS);
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
		adBO.setTotalPoint(BigDecimal.valueOf(10));
		adBO.setViewCount(10);
		adBO.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		adBO.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		adBO.setAreas("11/12");
		adBO.setRadius(15);
		adBO.setContent("内容");
        
        List<AdBO> list = new ArrayList<AdBO>();
        list.add(adBO);
        List<AdPraiseDTO>  dtoList = AdConverter.convertPraiseDTOS(list);
        
        Assert.assertNotNull(dtoList);
    }
	
	@Test
    public void convertMerchantDetailAdDTO(){
        
        AdBO adBO=new AdBO();
        
        adBO.setId(1l);
		adBO.setAdCount(10);
		adBO.setBeginTime(new Date());
		adBO.setMediaUrl("ad_image/1494582624025648401.png");
		adBO.setMerchantId(1l);
		adBO.setGmtCreate(new Date());
		adBO.setAdCount(10);
		adBO.setPoint(BigDecimal.valueOf(10));
		adBO.setTitle("广告测试内容");
		adBO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adBO.setPutWayEnum(PutWayEnum.PUT_WAY_FENS);
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
		adBO.setTotalPoint(BigDecimal.valueOf(10));
		adBO.setViewCount(10);
		adBO.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		adBO.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		adBO.setAreas("11/12");
		adBO.setRadius(15);
		adBO.setContent("内容");
        
		AdMerchantDetailDTO  dto = AdConverter.convertMerchantDetailAdDTO(adBO);
        
        Assert.assertNotNull(dto);
    }
	
	@Test
    public void convertDetailBO(){
		
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        
        AdDetailBO  bo = AdConverter.convertDetailBO(ad);
        
        Assert.assertNotNull(bo);
    }
	
	@Test
    public void convertDetailDTO(){
		
		AdDetailBO adDetailBO=new AdDetailBO();
		
		adDetailBO.setId(1l);
		adDetailBO.setAdCount(11);
		adDetailBO.setMediaUrl("ad_image/1494582624025648401.png");
		adDetailBO.setGmtCreate(new Date());
		adDetailBO.setAdCount(10);
		adDetailBO.setPoint(BigDecimal.valueOf(10));
		adDetailBO.setTitle("广告单元测试");
		adDetailBO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adDetailBO.setPutWayEnum(PutWayEnum.PUT_WAY_RADAR);
		adDetailBO.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
		adDetailBO.setTotalPoint(BigDecimal.valueOf(100));
		adDetailBO.setRadius(15);
		adDetailBO.setContent("aaaa");
		adDetailBO.setRadius(15);
		adDetailBO.setRegionName("全国");
		adDetailBO.setRemark("test");
		adDetailBO.setAuditTime(new Date());
        
		AdDetailDTO  dto = AdConverter.convertDetailDTO(adDetailBO);
        
        Assert.assertNotNull(dto);
    }
	
	
	@Test
    public void convert(){
		
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        
        AdEgainBO  bo = AdConverter.convert(ad,true);
        
        Assert.assertNotNull(bo);
    }
	
	@Test
    public void convertList(){
		
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        
        List<AdDO> adDOList =new ArrayList<>();
        adDOList.add(ad);
        
        List<AdPointBO>  boList = AdConverter.convert(adDOList);
        
        Assert.assertNotNull(boList);
    }
	
	
	@Test
    public void convertAdPointDTOList(){
		
		AdPointBO bo = new AdPointBO();
		bo.setId(1l);
		bo.setImgUrl("ad_image/1494582624025648401.png");
		bo.setLogoUrl("ad_image/1494582624025648401.png");
		bo.setManageType(ManageTypeEnum.COMMON);
		bo.setMerchantStoreId(1001l);
		bo.setTitle("广告标题");
		bo.setTotalPoint(BigDecimal.valueOf(100));
		
		List<AdPointBO> adPointBOList =new ArrayList<>();
		adPointBOList.add(bo);
        
		List<AdPointDTO>  dtoList = AdConverter.convertAdPointDTOList(adPointBOList);
        
        Assert.assertNotNull(dtoList);
    }
	
	
	@Test
    public void convertChoicenessAdBO(){
		
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        
        ChoicenessAdBO  bo = AdConverter.convertChoicenessAdBO(ad,true);
        
        Assert.assertNotNull(bo);
    }
	
	
	@Test
    public void convertChoicenessAdDTOPage(){
		
		Page<ChoicenessAdBO> pageChoicenessAdBO =new Page<>();
		
		ChoicenessAdBO bo = new ChoicenessAdBO();
		bo.setBeginTime(new Date());
		bo.setContent("广告内容");
		bo.setId(1l);
		bo.setIsFavorite(true);
		bo.setLogoUrl("ad_image/1494582624025648401.png");
		bo.setManageType(ManageTypeEnum.COMMON);
		bo.setMediaUrl("ad_image/1494582624025648401.png");
		bo.setMerchantStoreId(1001l);
		bo.setMerchantStoreName("E店商家");
		bo.setStatus(AdPraiseStatusEnum.AD_STATUS_SHOOT);
		bo.setTitle("广告标题");
		bo.setTotalPoint(BigDecimal.valueOf(100));
		bo.setType(AdEgainTypeEnum.AD_TYPE_FLAT);
		bo.setVideoImgUrl("ad_image/1494582624025648401.png");
		bo.setViewcount(10);
		
		List<ChoicenessAdBO> list = new ArrayList<>();
		list.add(bo);
		pageChoicenessAdBO.setCurrentPage(1);
		pageChoicenessAdBO.setTotalCount(20);
		pageChoicenessAdBO.setRecords(list);
		
		Page<ChoicenessAdDTO>  page = AdConverter.convertChoicenessAdDTOPage(pageChoicenessAdBO);
        
        Assert.assertNotNull(page.getRecords());
    }
	
	@Test
    public void convertAdEgainDetailBO(){
		
		AdDO ad=new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setRelateId(1001l);
        ad.setRelateType(RelateTypeEnum.PRODUCT_TYPE.getVal());
        
        AdEgainDetailBO  bo = AdConverter.convertAdEgainDetailBO(ad);
        
        Assert.assertNotNull(bo);
    }
	
	@Test
    public void convertAdEgainDTO(){
		
		AdEgainDetailBO ad=new AdEgainDetailBO();
		
		ad.setMerchantId(1002l);
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName(("E店商家"));
		ad.setManageType(ManageTypeEnum.COMMON);
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setIsClickAd(true);
		ad.setIsFavorite(false);
		ad.setVideoImgUrl("ad_image/1494582624025648401.png");
		ad.setContent("广告测试内容");
		ad.setTitle("广告测试标题");
		ad.setRelateId(1001l);
        ad.setMerchantStoreName("E店商家");
        ad.setType(AdEgainTypeEnum.AD_TYPE_FLAT);
        ad.setRelateType(RelateTypeEnum.MERCHANT_STORE_TYPE);
        ad.setViewCount(100);
        AdEgainDTO  dto = AdConverter.convertAdEgainDTO(ad);
        
        Assert.assertNotNull(dto);
    }

	@Test
	public void convertAdPraiseDTO() {
		AdSolrDTO solrDTO = new AdSolrDTO();
		solrDTO.setId(10L);
		solrDTO.setMerchantStoreId(200L);
		solrDTO.setMediaUrl("test");
		solrDTO.setTitle("test");
		solrDTO.setContent("test");
		solrDTO.setMerchantStoreName("test");
		solrDTO.setTotalPoint(20.0);
		solrDTO.setHits(10);
		solrDTO.setLogoUrl(solrDTO.getLogoUrl());

		AdBO adBO = new AdBO();
		adBO.setId(100L);
		adBO.setBeginTime(new Date());
		adBO.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);

		AdPraiseDTO dto = AdConverter.convertDTO(solrDTO);
		Assert.assertNotNull(dto);
		Assert.assertEquals(solrDTO.getId(), dto.getId());
		Assert.assertEquals(adBO.getBeginTime(), dto.getBeginTime());
	}

	@Test
	public void convertAdFlatVideoDTOS() {
		List<AdSolrDTO> solrDTOS = new ArrayList<>();
		AdSolrDTO adSolrDTO = new AdSolrDTO();
		adSolrDTO.setId(10L);
		adSolrDTO.setMerchantStoreId(200L);
		adSolrDTO.setMediaUrl("test");
		adSolrDTO.setVideoImgUrl("test");
		adSolrDTO.setTitle("test");
		adSolrDTO.setContent("test");
		adSolrDTO.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		adSolrDTO.setMerchantStoreName("test");
		adSolrDTO.setLogoUrl("test");
		adSolrDTO.setHits(10);
		solrDTOS.add(adSolrDTO);

		List<AdFlatVideoDTO> dtos = AdConverter.convertAdFlatVideoDTOS(solrDTOS);
		Assert.assertNotNull(dtos);
		Assert.assertEquals(adSolrDTO.getId(), dtos.get(0).getId());
	}
}
