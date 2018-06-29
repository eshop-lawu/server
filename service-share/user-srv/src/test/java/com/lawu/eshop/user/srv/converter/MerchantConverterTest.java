package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.CertifTypeEnum;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.MerchantDetailDTO;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.dto.MerchantStoreTypeEnum;
import com.lawu.eshop.user.srv.bo.MerchantBO;
import com.lawu.eshop.user.srv.bo.MerchantDetailBO;
import com.lawu.eshop.user.srv.bo.MerchantInfoBO;
import com.lawu.eshop.user.srv.domain.MerchantDO;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantConverterTest {

    @Test
    public void convertBO() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(200L);
        merchantDO.setNum("B0001");
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setHeadimg("pic");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setLevel(1);
        merchantDO.setGmtModified(new Date());
        merchantDO.setGmtCreate(new Date());
        merchantDO.setGtCid("8888");
        merchantDO.setRyToken("8888");
        MerchantBO merchantBO = MerchantConverter.convertBO(merchantDO);
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getId(), merchantBO.getId());
    }

    @Test
    public void convertDTO() {
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(200L);
        merchantBO.setAccount("1388888888");
        merchantBO.setNum("B0001");
        merchantBO.setHeadimg("pic");
        merchantBO.setLevel(1);
        MerchantDTO merchantDTO = MerchantConverter.convertDTO(merchantBO);
        Assert.assertNotNull(merchantDTO);
        Assert.assertEquals(merchantBO.getId(), merchantDTO.getId());
    }

    @Test
    public void convertSNSDTO() {
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(200L);
        merchantBO.setNum("B0001");
        merchantBO.setHeadimg("pic");
        merchantBO.setPrincipalName("test");
        MerchantSNSDTO merchantDTO = MerchantConverter.convertSNSDTO(merchantBO);
        Assert.assertNotNull(merchantDTO);
        Assert.assertEquals(merchantBO.getId(), merchantDTO.getId());
    }

    @Test
    public void convertBOS() {
        List<MerchantDO> merchantDOS = new ArrayList<>();
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setId(200L);
        merchantDO.setNum("B0001");
        merchantDO.setAccount("13888888888");
        merchantDO.setPwd("123456");
        merchantDO.setMobile("13888888888");
        merchantDO.setHeadimg("pic");
        merchantDO.setStatus(DataTransUtil.intToByte(1));
        merchantDO.setInviterId(200L);
        merchantDO.setInviterType(DataTransUtil.intToByte(2));
        merchantDO.setLevel(1);
        merchantDO.setGmtModified(new Date());
        merchantDO.setGmtCreate(new Date());
        merchantDO.setGtCid("8888");
        merchantDO.setRyToken("8888");
        merchantDOS.add(merchantDO);
        List<MerchantBO> merchantBOS = MerchantConverter.convertBOS(merchantDOS);
        Assert.assertNotNull(merchantBOS);
        Assert.assertEquals(merchantDO.getId(), merchantBOS.get(0).getId());
    }

    @Test
    public void convertListDOTS() {
        List<MerchantBO> merchantBOS = new ArrayList<>();
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(200L);
        merchantBO.setAccount("1388888888");
        merchantBO.setNum("B0001");
        merchantBO.setHeadimg("pic");
        merchantBO.setLevel(1);
        merchantBOS.add(merchantBO);
        List<MerchantDTO> memberDTOS = MerchantConverter.convertListDOTS(merchantBOS);
        Assert.assertNotNull(memberDTOS);
        Assert.assertEquals(merchantBO.getId(), memberDTOS.get(0).getId());
    }

    @Test
    public void convertInfoBO() {
        MerchantDO merchantDO = new MerchantDO();
        merchantDO.setAccount("13888888888");
        merchantDO.setHeadimg("pic");
        merchantDO.setGtCid("8888");
        merchantDO.setRyToken("8888");
        MerchantInfoBO merchantBO = MerchantConverter.convertInfoBO(merchantDO);
        Assert.assertNotNull(merchantBO);
        Assert.assertEquals(merchantDO.getAccount(), merchantBO.getAccount());
    }

    @Test
    public void convertPageDOTS() {
        Page<MerchantBO> pageMerchantBOS = new Page<>();
        List<MerchantBO> BOS = new ArrayList<>();
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setId(200L);
        merchantBO.setAccount("1388888888");
        merchantBO.setNum("B0001");
        merchantBO.setHeadimg("pic");
        merchantBO.setLevel(1);
        BOS.add(merchantBO);
        pageMerchantBOS.setCurrentPage(1);
        pageMerchantBOS.setTotalCount(10);
        pageMerchantBOS.setRecords(BOS);
        Page<MerchantDTO> pageDTO = MerchantConverter.convertPageDOTS(pageMerchantBOS);
        Assert.assertNotNull(pageDTO.getRecords());
        Assert.assertEquals(merchantBO.getId(), pageDTO.getRecords().get(0).getId());
    }

    @Test
    public void convertAccountDOTS(){
        List<MerchantBO> records = new ArrayList<>();
        MerchantBO merchantBO = new MerchantBO();
        merchantBO.setAccount("123");
        merchantBO.setId(1L);
        merchantBO.setNum("123");
        merchantBO.setGmtCreate(new Date());
        merchantBO.setIsFreeze(false);
        records.add(merchantBO);
        List<AccountDTO> list = MerchantConverter.convertAccountDOTS(records);
        Assert.assertNotNull(list);
        Assert.assertEquals(merchantBO.getNum(), list.get(0).getNum());
    }

    @Test
    public void convertMerchantDetailDTO() {
        MerchantDetailBO detailBO = new MerchantDetailBO();
        detailBO.setName("test");
        detailBO.setRegionName("44/4403");
        detailBO.setAddress("test");
        detailBO.setIndustryName("test");
        detailBO.setKeywords("test");
        detailBO.setIntro("test");
        detailBO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK);
        detailBO.setPrincipalName("test");
        detailBO.setPrincipalMobile("13666666666");
        detailBO.setCompanyName("test");
        detailBO.setRegNumber("test");
        detailBO.setCompanyAddress("test");
        detailBO.setLicenseIndate(new Date());
        detailBO.setManageType(MerchantStoreTypeEnum.NORMAL_MERCHANT);
        detailBO.setCertifType(CertifTypeEnum.CERTIF_TYPE_IDCARD);
        detailBO.setOperatorCardId("test");
        detailBO.setOperatorName("test");
        detailBO.setStoreUrl(new ArrayList<>());
        detailBO.setEnvironmentUrl(new ArrayList<>());
        detailBO.setIdcardUrl(new ArrayList<>());
        detailBO.setLicenseUrl(new ArrayList<>());
        detailBO.setLogoUrl("pic");
        detailBO.setOtherUrl(new ArrayList<>());

        MerchantDetailDTO detailDTO = MerchantConverter.convertDTO(detailBO);
        Assert.assertNotNull(detailDTO);
        Assert.assertEquals(detailBO.getName(), detailDTO.getName());
    }
}