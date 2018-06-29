package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.dto.MerchantStatusEnum;
import com.lawu.eshop.user.srv.bo.MerchantInviterBO;
import com.lawu.eshop.user.srv.domain.extend.InviterMerchantDOView;
import com.lawu.framework.core.page.Page;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MerchantInviterConverterTest {

	@Test
	public void convertMerchantInviterBOS() {
		List<InviterMerchantDOView> inviterMerchantDOS=new ArrayList<>();
		InviterMerchantDOView inviterMerchantDOView=new InviterMerchantDOView();
		inviterMerchantDOView.setAccount("1366666666");
		inviterMerchantDOView.setName("test");
		inviterMerchantDOView.setPrincipalName("test");
		inviterMerchantDOView.setRegionName("广东省深圳市南山区");
		inviterMerchantDOView.setGmtCreate(new Date());
		inviterMerchantDOView.setStatus(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK.val);
		inviterMerchantDOView.setPath("pic");
		inviterMerchantDOView.setAddress("大冲商务中心");
		inviterMerchantDOView.setInviterCount(10);
		inviterMerchantDOS.add(inviterMerchantDOView);
		List<MerchantInviterBO> merchantInviterBOS=MerchantInviterConverter.convertMerchantInviterBOS(inviterMerchantDOS);
		Assert.assertNotNull(merchantInviterBOS);
		Assert.assertEquals(inviterMerchantDOView.getAccount(), merchantInviterBOS.get(0).getAccount());
	}
	
	@Test
    public void convertMDTO() {
		MerchantInviterBO merchantInviterBO=new MerchantInviterBO();
		merchantInviterBO.setAccount("13888888888");
		merchantInviterBO.setRegionName("广东省深圳市南山区");
		merchantInviterBO.setAddress("大冲商务中心");
		merchantInviterBO.setGmtCreate(new Date());
		merchantInviterBO.setName("test");
		merchantInviterBO.setPrincipalName("test");
		merchantInviterBO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK);
		merchantInviterBO.setPath("pic");
		merchantInviterBO.setInviterCount(10);
		merchantInviterBO.setAddress("大冲商务中心");
        MerchantInviterDTO merchantInviterDTO = MerchantInviterConverter.convertMDTO(merchantInviterBO);
		Assert.assertNotNull(merchantInviterDTO);
		Assert.assertEquals(merchantInviterBO.getRegionName(), merchantInviterDTO.getRegionName());
    }
	
    @Test
	public void convertMIListDOTS() {
		List<MerchantInviterBO> merchantInviterBOS=new ArrayList<>();
		MerchantInviterBO merchantInviterBO=new MerchantInviterBO();
		merchantInviterBO.setAccount("13888888888");
		merchantInviterBO.setRegionName("广东省深圳市南山区");
		merchantInviterBO.setAddress("大冲商务中心");
		merchantInviterBO.setGmtCreate(new Date());
		merchantInviterBO.setName("test");
		merchantInviterBO.setPrincipalName("test");
		merchantInviterBO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK);
		merchantInviterBO.setPath("pic");
		merchantInviterBO.setInviterCount(10);
		merchantInviterBO.setAddress("大冲商务中心");
		merchantInviterBOS.add(merchantInviterBO);
		List<MerchantInviterDTO> merchantInviterDTOS=MerchantInviterConverter.convertMIListDOTS(merchantInviterBOS);
		Assert.assertNotNull(merchantInviterDTOS);
		Assert.assertEquals(merchantInviterBO.getRegionName(), merchantInviterDTOS.get(0).getRegionName());
	}

	@Test
	public void convertPageMIDOTS() {
		Page<MerchantInviterBO> merchantInviterBOPage=new Page<>();
		List<MerchantInviterBO> BOS=new ArrayList<>();
		MerchantInviterBO merchantInviterBO=new MerchantInviterBO();
		merchantInviterBO.setAccount("13888888888");
		merchantInviterBO.setRegionName("广东省深圳市南山区");
		merchantInviterBO.setAddress("大冲商务中心");
		merchantInviterBO.setGmtCreate(new Date());
		merchantInviterBO.setName("test");
		merchantInviterBO.setPrincipalName("test");
		merchantInviterBO.setStatusEnum(MerchantStatusEnum.MERCHANT_STATUS_UNCHECK);
		merchantInviterBO.setPath("pic");
		merchantInviterBO.setInviterCount(10);
		merchantInviterBO.setAddress("大冲商务中心");
		BOS.add(merchantInviterBO);
		merchantInviterBOPage.setCurrentPage(1);
		merchantInviterBOPage.setTotalCount(10);
		merchantInviterBOPage.setRecords(BOS);
		Page<MerchantInviterDTO> pageDTO=MerchantInviterConverter.convertPageMIDOTS(merchantInviterBOPage);
		Assert.assertNotNull(pageDTO.getRecords());
		Assert.assertEquals(merchantInviterBO.getRegionName(), pageDTO.getRecords().get(0).getRegionName());
	}

}
