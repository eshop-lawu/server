package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.param.MerchantStoreParam;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreAuditDO;
import com.lawu.utils.DataTransUtil;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantStoreAuditConverterTest {


    @Test
    public void convertBO() {
        MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
        merchantStoreAuditDO.setId(300L);
        merchantStoreAuditDO.setMerchantId(200L);
        merchantStoreAuditDO.setMerchantStoreId(300L);
        merchantStoreAuditDO.setContent("test");
        merchantStoreAuditDO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreAuditDO.setType(DataTransUtil.intToByte(1));
        merchantStoreAuditDO.setAuditorId(10);
        merchantStoreAuditDO.setRemark("test");
        merchantStoreAuditDO.setAuditTime(new Date());
        merchantStoreAuditDO.setGmtModified(new Date());
        MerchantStoreAuditBO merchantStoreAuditBO = MerchantStoreAuditConverter.convertBO(merchantStoreAuditDO);
        Assert.assertNotNull(merchantStoreAuditBO);
        Assert.assertEquals(merchantStoreAuditDO.getId(), merchantStoreAuditBO.getId());
    }

    @Test
    public void convertDTO() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setName("test");
        merchantStoreParam.setRegionPath("44/4403/440303");
        merchantStoreParam.setRegionName("广东省深圳市南山区");
        merchantStoreParam.setAddress("大冲商务中心");
        merchantStoreParam.setLongitude(new BigDecimal(104.23));
        merchantStoreParam.setLatitude(new BigDecimal(22.36));
        merchantStoreParam.setIndustryPath("10");
        merchantStoreParam.setIndustryName("食品");
        merchantStoreParam.setIntro("店铺介绍");
        merchantStoreParam.setPrincipalName("测试人");
        merchantStoreParam.setPrincipalMobile("13888888888");
        merchantStoreParam.setCompanyName("test");
        merchantStoreParam.setCompanyAddress("test");
        merchantStoreParam.setRegNumber("test");
        merchantStoreParam.setLicenseIndate(new Date());
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);
        merchantStoreParam.setOperatorCardId("8888");
        merchantStoreParam.setOperatorName("test");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");

        MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
        merchantStoreAuditBO.setId(300L);
        merchantStoreAuditBO.setMerchantStoreId(300L);
        merchantStoreAuditBO.setMerchantId(200L);
        merchantStoreAuditBO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreAuditBO.setType(DataTransUtil.intToByte(1));
        merchantStoreAuditBO.setAuditorId(10);
        merchantStoreAuditBO.setRemark("test");
        merchantStoreAuditBO.setAuditTime(new Date());
        merchantStoreAuditBO.setGmtModified(new Date());
        merchantStoreAuditBO.setContent(com.alibaba.fastjson.JSONObject.toJSONString(merchantStoreParam));
        MerchantStoreAuditDTO merchantStoreAuditDTO = MerchantStoreAuditConverter.convertDTO(merchantStoreAuditBO);
        Assert.assertNotNull(merchantStoreAuditDTO);
        Assert.assertEquals(merchantStoreParam.getName(), merchantStoreAuditDTO.getName());
    }

    @Test
    public void convertBOS() {
        List<MerchantStoreAuditDO> merchantStoreAuditDOS = new ArrayList<>();
        MerchantStoreAuditDO merchantStoreAuditDO = new MerchantStoreAuditDO();
        merchantStoreAuditDO.setId(300L);
        merchantStoreAuditDO.setMerchantId(200L);
        merchantStoreAuditDO.setMerchantStoreId(300L);
        merchantStoreAuditDO.setContent("test");
        merchantStoreAuditDO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreAuditDO.setType(DataTransUtil.intToByte(1));
        merchantStoreAuditDO.setAuditorId(10);
        merchantStoreAuditDO.setRemark("test");
        merchantStoreAuditDO.setAuditTime(new Date());
        merchantStoreAuditDO.setGmtModified(new Date());
        merchantStoreAuditDOS.add(merchantStoreAuditDO);
        List<MerchantStoreAuditBO> merchantStoreAuditBOS = MerchantStoreAuditConverter.convertBO(merchantStoreAuditDOS);
        Assert.assertNotNull(merchantStoreAuditBOS);
        Assert.assertEquals(merchantStoreAuditDO.getId(), merchantStoreAuditBOS.get(0).getId());
    }

    @Test
    public void convertDTOS() {
        MerchantStoreParam merchantStoreParam = new MerchantStoreParam();
        merchantStoreParam.setName("test");
        merchantStoreParam.setRegionPath("44/4403/440303");
        merchantStoreParam.setRegionName("广东省深圳市南山区");
        merchantStoreParam.setAddress("大冲商务中心");
        merchantStoreParam.setLongitude(new BigDecimal(104.23));
        merchantStoreParam.setLatitude(new BigDecimal(22.36));
        merchantStoreParam.setIndustryPath("10");
        merchantStoreParam.setIndustryName("食品");
        merchantStoreParam.setIntro("店铺介绍");
        merchantStoreParam.setPrincipalName("测试人");
        merchantStoreParam.setPrincipalMobile("13888888888");
        merchantStoreParam.setCompanyName("test");
        merchantStoreParam.setCompanyAddress("test");
        merchantStoreParam.setRegNumber("test");
        merchantStoreParam.setLicenseIndate(new Date());
        merchantStoreParam.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        merchantStoreParam.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);
        merchantStoreParam.setOperatorCardId("8888");
        merchantStoreParam.setOperatorName("test");
        merchantStoreParam.setLogoUrl("pic");
        merchantStoreParam.setStoreUrl("pic");
        merchantStoreParam.setEnvironmentUrl("pic");
        merchantStoreParam.setIdcardUrl("pic");
        merchantStoreParam.setLicenseUrl("pic");
        merchantStoreParam.setOtherUrl("pic");

        List<MerchantStoreAuditBO> merchantStoreAuditBOS = new ArrayList<>();
        MerchantStoreAuditBO merchantStoreAuditBO = new MerchantStoreAuditBO();
        merchantStoreAuditBO.setId(300L);
        merchantStoreAuditBO.setMerchantStoreId(300L);
        merchantStoreAuditBO.setMerchantId(200L);
        merchantStoreAuditBO.setStatus(DataTransUtil.intToByte(1));
        merchantStoreAuditBO.setType(DataTransUtil.intToByte(1));
        merchantStoreAuditBO.setAuditorId(10);
        merchantStoreAuditBO.setRemark("test");
        merchantStoreAuditBO.setAuditTime(new Date());
        merchantStoreAuditBO.setGmtModified(new Date());
        merchantStoreAuditBO.setContent(com.alibaba.fastjson.JSONObject.toJSONString(merchantStoreParam));
        merchantStoreAuditBOS.add(merchantStoreAuditBO);
        List<MerchantStoreAuditDTO> merchantStoreAuditDTOS = MerchantStoreAuditConverter.convertDTO(merchantStoreAuditBOS);
        Assert.assertNotNull(merchantStoreAuditDTOS);
        Assert.assertEquals(merchantStoreAuditBO.getId(), merchantStoreAuditDTOS.get(0).getId());
    }

}
