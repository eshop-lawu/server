package com.lawu.eshop.user.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.common.SolrInputDocument;
import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantInfoForShoppingCartDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.dto.NewMerchantStoreDTO;
import com.lawu.eshop.user.dto.RecommendFoodDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindMerchantInfoDTO;
import com.lawu.eshop.user.dto.ShoppingOrderFindUserInfoDTO;
import com.lawu.eshop.user.dto.ShoppingStoreDetailDTO;
import com.lawu.eshop.user.dto.StoreDetailDTO;
import com.lawu.eshop.user.dto.StoreSolrInfoDTO;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreProfileBO;
import com.lawu.eshop.user.srv.bo.NewMerchantStoreBO;
import com.lawu.eshop.user.srv.bo.PayOrderStoreInfoBO;
import com.lawu.eshop.user.srv.bo.RecommendFoodBO;
import com.lawu.eshop.user.srv.bo.ShoppingOrderFindMerchantInfoBO;
import com.lawu.eshop.user.srv.bo.ShoppingStoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreDetailBO;
import com.lawu.eshop.user.srv.bo.StoreSolrInfoBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.extend.NewMerchantStoreDOView;
import com.lawu.eshop.user.srv.domain.extend.PayOrderStoreInfoView;
import com.lawu.eshop.user.srv.domain.extend.RecommendFoodDOview;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantStoreConverterTest {

    @Test
    public void convertBO() {
        MerchantStoreProfileDO merchantStoreProfileDO = new MerchantStoreProfileDO();
        merchantStoreProfileDO.setMerchantId(200L);
        merchantStoreProfileDO.setRegNumber("8888");
        merchantStoreProfileDO.setOperatorCardId("8888");
        merchantStoreProfileDO.setManageType(DataTransUtil.intToByte(1));
        merchantStoreProfileDO.setCertifType(DataTransUtil.intToByte(1));
        MerchantStoreProfileBO merchantStoreProfileBO = MerchantStoreConverter.convertBO(merchantStoreProfileDO);
        Assert.assertNotNull(merchantStoreProfileBO);
        Assert.assertEquals(merchantStoreProfileDO.getMerchantId(), merchantStoreProfileBO.getMerchantId());
    }

    @Test
    public void coverter() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setId(300L);
        merchantStoreDO.setName("test");
        merchantStoreDO.setPrincipalName("test");
        merchantStoreDO.setRegionPath("44/4403/440303");
        merchantStoreDO.setAddress("大冲商务中心");
        merchantStoreDO.setIndustryPath("10");
        merchantStoreDO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreDO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreDO.setIntro("test");
        merchantStoreDO.setPrincipalMobile("13888888888");
        merchantStoreDO.setIsNoReasonReturn(true);
        merchantStoreDO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreDO.setRegionName("广东省深圳市南山区");
        merchantStoreDO.setIndustryName("食品");
        MerchantStoreInfoBO merchantStoreInfoBO = MerchantStoreConverter.coverter(merchantStoreDO);
        Assert.assertNotNull(merchantStoreInfoBO);
        Assert.assertEquals(merchantStoreDO.getId(), merchantStoreInfoBO.getMerchantStoreId());
    }

    @Test
    public void coverDTO() {
        MerchantStoreInfoBO merchantStoreInfoBO = new MerchantStoreInfoBO();
        merchantStoreInfoBO.setMerchantStoreId(300L);
        merchantStoreInfoBO.setName("test");
        merchantStoreInfoBO.setPrincipalName("test");
        merchantStoreInfoBO.setRegionPath("44/4403/440303");
        merchantStoreInfoBO.setAddress("大冲商务中心");
        merchantStoreInfoBO.setIndustryPath("10");
        merchantStoreInfoBO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreInfoBO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreInfoBO.setIntro("test");
        merchantStoreInfoBO.setPrincipalMobile("13888888888");

        merchantStoreInfoBO.setCompanyAddress("test");
        merchantStoreInfoBO.setCompanyName("test");
        merchantStoreInfoBO.setRegNumber("8888");
        merchantStoreInfoBO.setLicenseIndate(new Date());
        merchantStoreInfoBO.setManageType(DataTransUtil.intToByte(1));
        merchantStoreInfoBO.setCertifType(DataTransUtil.intToByte(1));
        merchantStoreInfoBO.setOperatorCardId("8888");
        merchantStoreInfoBO.setOperatorName("test");

        merchantStoreInfoBO.setEnvironmentUrl("pic");
        merchantStoreInfoBO.setIdcardUrl("pic");
        merchantStoreInfoBO.setLicenseUrl("pic");
        merchantStoreInfoBO.setLogoUrl("pic");
        merchantStoreInfoBO.setOtherUrl("pic");
        merchantStoreInfoBO.setStoreUrl("pic");
        merchantStoreInfoBO.setAuditSuccess(true);
        merchantStoreInfoBO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_CHECKED);
        merchantStoreInfoBO.setRegionName("广东省深圳市南山区");
        merchantStoreInfoBO.setIndustryName("食品");
        MerchantStoreDTO merchantStoreDTO = MerchantStoreConverter.coverDTO(merchantStoreInfoBO);
        Assert.assertNotNull(merchantStoreDTO);
        Assert.assertEquals(merchantStoreInfoBO.getMerchantStoreId(), merchantStoreDTO.getMerchantStoreId());
    }

    @Test
    public void couverDOByParam() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setIndustryPath("10");
        merchantStoreParam.setPrincipalName("test");
        merchantStoreParam.setName("test");
        merchantStoreParam.setAddress("test");
        merchantStoreParam.setIntro("test");
        merchantStoreParam.setLatitude(BigDecimal.valueOf(104.23));
        merchantStoreParam.setLongitude(BigDecimal.valueOf(22.36));
        merchantStoreParam.setPrincipalMobile("13888888888");
        merchantStoreParam.setRegionPath("44/4403/440303");
        merchantStoreParam.setRegionName("广东省深圳市南山区");
        merchantStoreParam.setIndustryName("食品");

        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);
        merchantStoreParam.setCompanyAddress("test");
        merchantStoreParam.setLicenseIndate(new Date());
        merchantStoreParam.setOperatorCardId("8888");
        merchantStoreParam.setCompanyName("test");
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setOperatorName("test");
        merchantStoreParam.setRegNumber("8888");
        MerchantStoreDO merchantStoreDO = (MerchantStoreDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 1);
        Assert.assertNotNull(merchantStoreDO);
        Assert.assertEquals(merchantStoreParam.getIndustryPath(), merchantStoreDO.getIndustryPath());

        MerchantStoreProfileDO merchantStoreProfileDO = (MerchantStoreProfileDO) MerchantStoreConverter.couverDOByParam(merchantStoreParam, 2);
        Assert.assertNotNull(merchantStoreProfileDO);
        Assert.assertEquals(merchantStoreParam.getCertifType().val, merchantStoreProfileDO.getCertifType());
    }

    @Test
    public void convertDTO() {
        StoreDetailBO storeDetailBO = new StoreDetailBO();
        storeDetailBO.setMerchantId(200L);
        storeDetailBO.setUserNum("B0001");
        storeDetailBO.setName("test");
        storeDetailBO.setRegionName("广东省深圳市南山区");
        storeDetailBO.setAddress("大冲商务中心");
        storeDetailBO.setPrincipalMobile("13888888888");
        storeDetailBO.setStorePic("pic");
        storeDetailBO.setPicCount(10);
        storeDetailBO.setIntro("test");
        storeDetailBO.setFavoriteNumber(10);
        storeDetailBO.setFavorite(true);
        storeDetailBO.setAverageConsumeAmount(BigDecimal.valueOf(10));
        storeDetailBO.setAverageScore(BigDecimal.valueOf(10));
        storeDetailBO.setFeedbackRate(BigDecimal.valueOf(10));
        storeDetailBO.setBuyNumbers(10);
        StoreDetailDTO storeDetailDTO = MerchantStoreConverter.convertDTO(storeDetailBO);
        Assert.assertNotNull(storeDetailDTO);
        Assert.assertEquals(storeDetailBO.getMerchantId(), storeDetailDTO.getMerchantId());
    }

    @Test
    public void convertDTO1() {
        ShoppingStoreDetailBO shoppingStoreDetailBO = new ShoppingStoreDetailBO();
        shoppingStoreDetailBO.setMerchantId(200L);
        shoppingStoreDetailBO.setName("test");
        shoppingStoreDetailBO.setLogoPic("pic");
        shoppingStoreDetailBO.setFansCount(10);
        shoppingStoreDetailBO.setFans(true);
        shoppingStoreDetailBO.setFavorite(true);
        ShoppingStoreDetailDTO shoppingStoreDetailDTO = MerchantStoreConverter.convertDTO(shoppingStoreDetailBO);
        Assert.assertNotNull(shoppingStoreDetailDTO);
        Assert.assertEquals(shoppingStoreDetailBO.getMerchantId(), shoppingStoreDetailDTO.getMerchantId());
    }

    @Test
    public void convertBO1() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setName("test");
        merchantStoreDO.setRegionName("广东省深圳市南山区");
        merchantStoreDO.setAddress("大冲商务中心");
        merchantStoreDO.setPrincipalMobile("13888888888");
        merchantStoreDO.setIntro("test");
        merchantStoreDO.setFavoriteNumber(10);
        merchantStoreDO.setAverageConsumeAmount(BigDecimal.valueOf(10));
        merchantStoreDO.setAverageScore(BigDecimal.valueOf(10));
        merchantStoreDO.setFeedbackRate(BigDecimal.valueOf(10));
        merchantStoreDO.setBuyNumbers(10);
        StoreDetailBO storeDetailBO = MerchantStoreConverter.convertBO(merchantStoreDO);
        Assert.assertNotNull(storeDetailBO);
        Assert.assertEquals(merchantStoreDO.getMerchantId(), storeDetailBO.getMerchantId());
    }

    @Test
    public void convert() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setId(300L);
        merchantStoreDO.setIsNoReasonReturn(true);
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setName("test");

        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setNum("B0001");
        ShoppingOrderFindMerchantInfoBO merchantStoreNoReasonReturnBO = MerchantStoreConverter.convert(merchantStoreDO, merchantDO);
        Assert.assertNotNull(merchantStoreNoReasonReturnBO);
        Assert.assertEquals(merchantStoreDO.getId(), merchantStoreNoReasonReturnBO.getMerchantStoreId());
    }

    @Test
    public void convert1() {
        ShoppingOrderFindMerchantInfoBO merchantStoreNoReasonReturnBO = new ShoppingOrderFindMerchantInfoBO();
        merchantStoreNoReasonReturnBO.setMerchantId(200L);
        merchantStoreNoReasonReturnBO.setMerchantStoreId(300L);
        merchantStoreNoReasonReturnBO.setMerchantStoreRegionPath("1/2/3");
        merchantStoreNoReasonReturnBO.setMerchantStoreName("test");
        merchantStoreNoReasonReturnBO.setMerchantNum("B0001");
        merchantStoreNoReasonReturnBO.setIsNoReasonReturn(true);
        merchantStoreNoReasonReturnBO.setIsFans(true);
        ShoppingOrderFindMerchantInfoDTO rtn = MerchantStoreConverter.convert(merchantStoreNoReasonReturnBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(merchantStoreNoReasonReturnBO.getMerchantId(), rtn.getMerchantId());
    }

    @Test
    public void convertShoppingOrderFindMerchantInfoDTOList() {
        List<ShoppingOrderFindMerchantInfoBO> merchantStoreNoReasonReturnBOList = new ArrayList<>();
        ShoppingOrderFindMerchantInfoBO shoppingOrderFindMerchantInfoBO = new ShoppingOrderFindMerchantInfoBO();
        shoppingOrderFindMerchantInfoBO.setMerchantId(200L);
        shoppingOrderFindMerchantInfoBO.setMerchantStoreId(300L);
        shoppingOrderFindMerchantInfoBO.setMerchantStoreRegionPath("1/2/3");
        shoppingOrderFindMerchantInfoBO.setMerchantStoreName("test");
        shoppingOrderFindMerchantInfoBO.setMerchantNum("B0001");
        shoppingOrderFindMerchantInfoBO.setIsNoReasonReturn(true);
        shoppingOrderFindMerchantInfoBO.setIsFans(true);
        merchantStoreNoReasonReturnBOList.add(shoppingOrderFindMerchantInfoBO);
        List<ShoppingOrderFindMerchantInfoDTO> merchantStoreNoReasonReturnDTOList = MerchantStoreConverter.convertShoppingOrderFindMerchantInfoDTOList(merchantStoreNoReasonReturnBOList);
        Assert.assertNotNull(merchantStoreNoReasonReturnDTOList);
        Assert.assertEquals(shoppingOrderFindMerchantInfoBO.getMerchantId(), merchantStoreNoReasonReturnDTOList.get(0).getMerchantId());
    }

    @Test
    public void convertStoreBO() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreDO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreDO.setName("test");
        merchantStoreDO.setId(300L);
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setIndustryPath("10");
        merchantStoreDO.setIndustryName("食品");
        merchantStoreDO.setRegionPath("44/4403/440303");
        MerchantStoreBO merchantStoreBO = MerchantStoreConverter.convertStoreBO(merchantStoreDO);
        Assert.assertNotNull(merchantStoreBO);
        Assert.assertEquals(merchantStoreDO.getLongitude(), merchantStoreBO.getLongitude());
    }

    @Test
    public void convertStoreDTO() {
        MerchantStoreBO merchantStoreBO = new MerchantStoreBO();
        merchantStoreBO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreBO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreBO.setName("test");
        merchantStoreBO.setId(300L);
        merchantStoreBO.setMerchantId(200L);
        merchantStoreBO.setIndustryPath("10");
        merchantStoreBO.setIndustryName("食品");
        merchantStoreBO.setRegionPath("44/4403/440303");
        MerchantStoreDTO merchantStoreDTO = MerchantStoreConverter.convertStoreDTO(merchantStoreBO);
        Assert.assertNotNull(merchantStoreDTO);
        Assert.assertEquals(merchantStoreBO.getLongitude(), merchantStoreDTO.getLongitude());
    }

    @Test
    public void convertStoreBOS() {
        List<MerchantStoreDO> merchantStoreDOList = new ArrayList<>();
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreDO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreDO.setName("test");
        merchantStoreDO.setId(300L);
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setIndustryPath("10");
        merchantStoreDO.setIndustryName("食品");
        merchantStoreDO.setRegionPath("44/4403/440303");
        merchantStoreDOList.add(merchantStoreDO);
        List<MerchantStoreBO> merchantStoreBOS = MerchantStoreConverter.convertStoreBO(merchantStoreDOList);
        Assert.assertNotNull(merchantStoreBOS);
        Assert.assertEquals(merchantStoreDO.getLongitude(), merchantStoreBOS.get(0).getLongitude());
    }

    @Test
    public void convertStoreDTOS() {
        List<MerchantStoreBO> merchantStoreBOList = new ArrayList<>();
        MerchantStoreBO merchantStoreBO = new MerchantStoreBO();
        merchantStoreBO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreBO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreBO.setName("test");
        merchantStoreBO.setId(300L);
        merchantStoreBO.setMerchantId(200L);
        merchantStoreBO.setIndustryPath("10");
        merchantStoreBO.setIndustryName("食品");
        merchantStoreBO.setRegionPath("44/4403/440303");
        merchantStoreBOList.add(merchantStoreBO);
        List<MerchantStoreDTO> merchantStoreDTOS = MerchantStoreConverter.convertStoreDTO(merchantStoreBOList);
        Assert.assertNotNull(merchantStoreDTOS);
        Assert.assertEquals(merchantStoreBO.getLongitude(), merchantStoreDTOS.get(0).getLongitude());
    }

    @Test
    public void convertSolrInputDocument() {
        MerchantStoreDO merchantStoreDO = new MerchantStoreDO();
        merchantStoreDO.setId(300L);
        merchantStoreDO.setMerchantId(200L);
        merchantStoreDO.setName("test");
        merchantStoreDO.setRegionPath("44/4403/440303");
        merchantStoreDO.setLongitude(BigDecimal.valueOf(104.23));
        merchantStoreDO.setLatitude(BigDecimal.valueOf(22.36));
        merchantStoreDO.setIndustryPath("10");
        merchantStoreDO.setIndustryName("食品");
        merchantStoreDO.setFavoriteNumber(10);
        merchantStoreDO.setAverageConsumeAmount(BigDecimal.valueOf(10));
        merchantStoreDO.setAverageScore(BigDecimal.valueOf(10));
        SolrInputDocument document = MerchantStoreConverter.convertSolrInputDocument(merchantStoreDO, "pic");
        Assert.assertNotNull(document);
    }

    @Test
    public void convert3() {
        List<ShoppingOrderFindMerchantInfoBO> merchantStoreNoReasonReturnBOList = new ArrayList<>();
        ShoppingOrderFindMerchantInfoBO shoppingOrderFindMerchantInfoBO = new ShoppingOrderFindMerchantInfoBO();
        shoppingOrderFindMerchantInfoBO.setMerchantId(200L);
        shoppingOrderFindMerchantInfoBO.setMerchantStoreId(300L);
        shoppingOrderFindMerchantInfoBO.setMerchantStoreName("test");
        shoppingOrderFindMerchantInfoBO.setMerchantNum("B0001");
        shoppingOrderFindMerchantInfoBO.setIsNoReasonReturn(true);
        shoppingOrderFindMerchantInfoBO.setIsFans(true);
        merchantStoreNoReasonReturnBOList.add(shoppingOrderFindMerchantInfoBO);

        MemberBO memberBO = new MemberBO();
        memberBO.setName("M0001");
        ShoppingOrderFindUserInfoDTO rtn = MerchantStoreConverter.convert(merchantStoreNoReasonReturnBOList, memberBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(shoppingOrderFindMerchantInfoBO.getMerchantId(), rtn.getShoppingOrderFindMerchantInfoDTOList().get(0).getMerchantId());
    }

    @Test
    public void coverStoreSolrDTOS() {
        List<StoreSolrInfoBO> storeSolrInfoBOS = new ArrayList<>();
        StoreSolrInfoBO storeSolrInfoBO = new StoreSolrInfoBO();
        storeSolrInfoBO.setMerchantId(200L);
        storeSolrInfoBO.setMerchantStoreId(300L);
        storeSolrInfoBO.setIndustryPath("10");
        storeSolrInfoBO.setIndustryName("食品");
        storeSolrInfoBOS.add(storeSolrInfoBO);
        List<StoreSolrInfoDTO> storeSolrInfoDTOS = MerchantStoreConverter.coverStoreSolrDTOS(storeSolrInfoBOS);
        Assert.assertNotNull(storeSolrInfoDTOS);
        Assert.assertEquals(storeSolrInfoBO.getMerchantId(), storeSolrInfoDTOS.get(0).getMerchantId());
    }

    @Test
    public void coverPayOrderStoreInfoBO() {
        PayOrderStoreInfoView storeInfoView = new PayOrderStoreInfoView();
        storeInfoView.setMerchantStoreId(300L);
        storeInfoView.setName("test");
        storeInfoView.setAddress("test");
        storeInfoView.setPrincipalMobile("13888888888");
        storeInfoView.setPath("pic");
        PayOrderStoreInfoBO payOrderStoreInfoBO = MerchantStoreConverter.coverPayOrderStoreInfoBO(storeInfoView);
        Assert.assertNotNull(payOrderStoreInfoBO);
        Assert.assertEquals(storeInfoView.getMerchantStoreId(), payOrderStoreInfoBO.getMerchantStoreId());
    }

    @Test
    public void convert2() {
        MerchantStoreInfoBO merchantStoreInfoBO = new MerchantStoreInfoBO();
        merchantStoreInfoBO.setMerchantStoreId(300L);
        merchantStoreInfoBO.setName("test");
        MerchantInfoForShoppingCartDTO rtn = MerchantStoreConverter.convert(merchantStoreInfoBO);
        Assert.assertNotNull(rtn);
        Assert.assertEquals(merchantStoreInfoBO.getMerchantStoreId(), rtn.getMerchantStoreId());
    }

    @Test
    public void convertNewStoreBO() {
        NewMerchantStoreDOView storeDOView = new NewMerchantStoreDOView();
        storeDOView.setMerchantId(200L);
        storeDOView.setMerchantStoreId(300L);
        storeDOView.setName("test");
        storeDOView.setIndustryName("test");
        storeDOView.setRegionName("test");
        storeDOView.setAddress("test");
        storeDOView.setStorePic("pic");
        List<NewMerchantStoreDOView> storeDOViews = new ArrayList<>();
        storeDOViews.add(storeDOView);
        List<NewMerchantStoreBO> storeBOS = MerchantStoreConverter.convertNewStoreBO(storeDOViews);
        Assert.assertNotNull(storeBOS);
        Assert.assertEquals(storeDOView.getMerchantId(), storeBOS.get(0).getMerchantId());
    }

    @Test
    public void convertNewStoreDTO() {
        NewMerchantStoreBO storeBO = new NewMerchantStoreBO();
        storeBO.setMerchantId(200L);
        storeBO.setMerchantStoreId(300L);
        storeBO.setName("test");
        storeBO.setIndustryName("test");
        storeBO.setRegionName("test");
        storeBO.setAddress("test");
        storeBO.setStorePic("test");
        List<NewMerchantStoreBO> storeBOS = new ArrayList<>();
        storeBOS.add(storeBO);
        List<NewMerchantStoreDTO> storeDTOS = MerchantStoreConverter.convertNewStoreDTO(storeBOS);
        Assert.assertNotNull(storeDTOS);
        Assert.assertEquals(storeBO.getMerchantId(), storeDTOS.get(0).getMerchantId());
    }

    @Test
    public void convertRecommendStoreBO() {
        RecommendFoodDOview foodDOview = new RecommendFoodDOview();
        foodDOview.setMerchantId(200L);
        foodDOview.setMerchantStoreId(300L);
        foodDOview.setName("test");
        foodDOview.setIndustryName("test");
        foodDOview.setRegionName("test");
        foodDOview.setAddress("test");
        foodDOview.setStorePic("test");
        foodDOview.setLongitude(BigDecimal.valueOf(104.23));
        foodDOview.setLatitude(BigDecimal.valueOf(22.36));
        foodDOview.setAverageScore(BigDecimal.valueOf(10));
        foodDOview.setAverageConsumeAmount(BigDecimal.valueOf(10));
        foodDOview.setBuyNumbers(10);
        foodDOview.setCommentsCount(10);
        List<RecommendFoodDOview> foodDOviews = new ArrayList<>();
        foodDOviews.add(foodDOview);
        List<RecommendFoodBO> foodBOS = MerchantStoreConverter.convertRecommendStoreBO(foodDOviews);
        Assert.assertNotNull(foodBOS);
        Assert.assertEquals(foodDOview.getMerchantId(), foodBOS.get(0).getMerchantId());
    }

    @Test
    public void convertRecommendStoreDTO() {
        RecommendFoodBO foodBO = new RecommendFoodBO();
        foodBO.setMerchantId(200L);
        foodBO.setMerchantStoreId(300L);
        foodBO.setName("test");
        foodBO.setIndustryName("test");
        foodBO.setRegionName("test");
        foodBO.setAddress("test");
        foodBO.setStorePic("test");
        foodBO.setLongitude(BigDecimal.valueOf(104.23));
        foodBO.setLatitude(BigDecimal.valueOf(22.36));
        foodBO.setAverageScore(BigDecimal.valueOf(10));
        foodBO.setAverageConsumeAmount(BigDecimal.valueOf(10));
        foodBO.setBuyNumbers(10);
        foodBO.setCommentsCount(10);
        List<RecommendFoodBO> foodBOS = new ArrayList<>();
        foodBOS.add(foodBO);
        List<RecommendFoodDTO> foodDTOS = MerchantStoreConverter.convertRecommendStoreDTO(foodBOS);
        Assert.assertNotNull(foodDTOS);
        Assert.assertEquals(foodBO.getMerchantId(), foodDTOS.get(0).getMerchantId());
    }

}
