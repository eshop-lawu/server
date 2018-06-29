package com.lawu.eshop.jobs.service.impl;

import java.math.BigDecimal;

import com.lawu.eshop.ad.dto.MemberAdRecodeCommissionDTO;
import com.lawu.eshop.jobs.service.ClickAdCommissionService;
import com.lawu.eshop.jobs.service.SaleAndVolumeCommissionService;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.constants.PropertyType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test.xml" })
public class CommissionServiceImplTest {

	private static Logger logger = LoggerFactory.getLogger(CommissionServiceImplTest.class);

	@Autowired
	private ClickAdCommissionService clickAdCommissionService;
	@Autowired
	private SaleAndVolumeCommissionService saleAndVolumeCommissionService;

	@Test
	public void executeAutoClickAdCommission() {
		MemberAdRecodeCommissionDTO memberAdRecodeCommissionDTO = new MemberAdRecodeCommissionDTO();
		memberAdRecodeCommissionDTO.setMemberNum("M00000001");
		memberAdRecodeCommissionDTO.setAdId(1L);
		memberAdRecodeCommissionDTO.setId(1L);
		memberAdRecodeCommissionDTO.setPoint(new BigDecimal(1));
		clickAdCommissionService.executeAutoClickAdCommission(memberAdRecodeCommissionDTO,true);
	}

	@Test
	public void executePayOrderCommission() {
		ShoppingOrderCommissionDTO shoppingOrderCommissionDTO = new ShoppingOrderCommissionDTO();
		shoppingOrderCommissionDTO.setId(1L);
		shoppingOrderCommissionDTO.setActualAmount(new BigDecimal(10));
		shoppingOrderCommissionDTO.setMemberNum("M0000001");
		shoppingOrderCommissionDTO.setMerchantNum("B0000001");
		saleAndVolumeCommissionService.commission(shoppingOrderCommissionDTO, CommissionEnum.PAY,true);
	}

	@Test
	public void executeProductOrderCommission() {
		ShoppingOrderCommissionDTO shoppingOrderCommissionDTO = new ShoppingOrderCommissionDTO();
		shoppingOrderCommissionDTO.setId(1L);
		shoppingOrderCommissionDTO.setActualAmount(new BigDecimal(10));
		shoppingOrderCommissionDTO.setMemberNum("M0000001");
		shoppingOrderCommissionDTO.setMerchantNum("B0000001");
		saleAndVolumeCommissionService.commission(shoppingOrderCommissionDTO,CommissionEnum.SHOPPING,true);
	}
}
