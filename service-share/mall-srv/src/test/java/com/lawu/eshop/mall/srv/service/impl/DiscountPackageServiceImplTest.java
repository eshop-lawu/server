package com.lawu.eshop.mall.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.mall.constants.DiscountPackageStatusEnum;
import com.lawu.eshop.mall.constants.DiscountPackageStatusQueryEnum;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageImageUpdateParam;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.DiscountPackageBO;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageImageDO;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageContentDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageDOMapper;
import com.lawu.eshop.mall.srv.mapper.DiscountPackageImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.extend.DiscountPackageExtendDOMapper;
import com.lawu.eshop.mall.srv.service.DiscountPackageService;

/**
 * @author zhangyong
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class DiscountPackageServiceImplTest {

	@Autowired
	private DiscountPackageDOMapper discountPackageDOMapper;
	
	@Autowired
	private DiscountPackageService discountPackageService;

	@Autowired
	private DiscountPackageExtendDOMapper discountPackageExtendDOMapper;

	@Autowired
	private DiscountPackageContentDOMapper discountPackageContentDOMapper;

	@Autowired
	private DiscountPackageImageDOMapper discountPackageImageDOMapper;

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listForMember() {
		DiscountPackageDO discountPackageDO = new DiscountPackageDO();
		discountPackageDO.setMerchantId(1L);
		discountPackageDO.setGmtCreate(new Date());
		discountPackageDO.setIsReservation(true);
		discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
		discountPackageDOMapper.insert(discountPackageDO);
		List<DiscountPackageBO> list = discountPackageService.listForMember(1L);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());

	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listForMerchant() {
		DiscountPackageDO discountPackageDO = new DiscountPackageDO();
		discountPackageDO.setMerchantId(1L);
		discountPackageDO.setGmtCreate(new Date());
		discountPackageDO.setIsReservation(true);
		discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
		discountPackageDOMapper.insert(discountPackageDO);
		DiscountPackageQueryForeignParam param = new DiscountPackageQueryForeignParam();
		param.setStatus(DiscountPackageStatusQueryEnum.UP);
		List<DiscountPackageBO> list = discountPackageService.listForMerchant(1L, param);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void get() {
		DiscountPackageDO discountPackageDO = new DiscountPackageDO();
		discountPackageDO.setMerchantId(1L);
		discountPackageDO.setGmtCreate(new Date());
		discountPackageDO.setIsReservation(true);
		discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
		discountPackageDOMapper.insert(discountPackageDO);

		DiscountPackageContentDO discountPackageContentDO = new DiscountPackageContentDO();
		discountPackageContentDO.setGmtCreate(new Date());
		discountPackageContentDO.setDiscountPackageId(discountPackageDO.getId());
		discountPackageContentDO.setName("test");
		discountPackageContentDO.setStatus(StatusEnum.VALID.getValue());
		discountPackageContentDOMapper.insert(discountPackageContentDO);

		DiscountPackageImageDO image = new DiscountPackageImageDO();
		image.setGmtCreate(new Date());
		image.setDiscountPackageId(discountPackageDO.getId());
		image.setImage("default.png");
		image.setStatus(StatusEnum.VALID.getValue());
		discountPackageImageDOMapper.insert(image);

		DiscountPackageExtendBO discountPackageExtendBO = discountPackageService.get(discountPackageDO.getId());
		Assert.assertNotNull(discountPackageExtendBO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getByMerchantId() {
		DiscountPackageDO discountPackageDO = new DiscountPackageDO();
		discountPackageDO.setMerchantId(1L);
		discountPackageDO.setGmtCreate(new Date());
		discountPackageDO.setIsReservation(true);
		discountPackageDO.setStatus(DiscountPackageStatusEnum.UP.getValue());
		discountPackageDOMapper.insert(discountPackageDO);

		DiscountPackageContentDO discountPackageContentDO = new DiscountPackageContentDO();
		discountPackageContentDO.setGmtCreate(new Date());
		discountPackageContentDO.setDiscountPackageId(discountPackageDO.getId());
		discountPackageContentDO.setName("test");
		discountPackageContentDO.setStatus(StatusEnum.VALID.getValue());
		discountPackageContentDOMapper.insert(discountPackageContentDO);

		DiscountPackageImageDO image = new DiscountPackageImageDO();
		image.setGmtCreate(new Date());
		image.setDiscountPackageId(discountPackageDO.getId());
		image.setImage("default.png");
		image.setStatus(StatusEnum.VALID.getValue());
		discountPackageImageDOMapper.insert(image);

		DiscountPackageExtendBO discountPackageExtendBO = discountPackageService.get(discountPackageDO.getId(), discountPackageDO.getMerchantId());
		Assert.assertNotNull(discountPackageExtendBO);
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void save() {
		List<DiscountPackageContentSaveForeignParam> discountPackageContents = new ArrayList<>();
		DiscountPackageContentSaveForeignParam contentSaveForeignParam = new DiscountPackageContentSaveForeignParam();
		contentSaveForeignParam.setName("test");
		contentSaveForeignParam.setQuantity(1);
		contentSaveForeignParam.setUnit("unit");
		contentSaveForeignParam.setUnitPrice(BigDecimal.TEN);
		discountPackageContents.add(contentSaveForeignParam);
		List<DiscountPackageImageSaveParam> discountPackageImages = new ArrayList<>();
		DiscountPackageImageSaveParam imageSaveParam = new DiscountPackageImageSaveParam();
		imageSaveParam.setImage("default.png");
		imageSaveParam.setDescription("description");
		discountPackageImages.add(imageSaveParam);

		DiscountPackageSaveParam param = new DiscountPackageSaveParam();
		param.setIsReservation(true);
		param.setName("test");
		param.setCoverImage("default.png");
		param.setDiscountPackageContents(discountPackageContents);
		param.setMerchantStoreId(1L);
		param.setDiscountPackageImages(discountPackageImages);
		param.setAdvanceBookingTime("30分钟");
		param.setPurchaseNotes("1,2,3");
		discountPackageService.save(1L, param);
		List<DiscountPackageDO> list = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());

	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void update() {

		DiscountPackageDO discountPackageDO = new DiscountPackageDO();
		discountPackageDO.setMerchantId(1L);
		discountPackageDO.setGmtCreate(new Date());
		discountPackageDO.setName("test");
		discountPackageDO.setIsReservation(true);
		discountPackageDO.setStatus((byte) 1);
		discountPackageDO.setOriginalPrice(BigDecimal.TEN);
		discountPackageDO.setGmtUp(new Date());
		discountPackageDO.setMerchantStoreId(1L);
		discountPackageDO.setUseTimeBegin(new Date());
		discountPackageDO.setPrice(BigDecimal.TEN);
		discountPackageDO.setValidityPeriodEnd(new Date());
		discountPackageDO.setValidityPeriodBegin(new Date());
		discountPackageDO.setUseTimeBegin(new Date());
		discountPackageDO.setUseTimeEnd(new Date());
		discountPackageDO.setUseTimeWeek("1");
		discountPackageDOMapper.insert(discountPackageDO);
		DiscountPackageContentDO contentDO = new DiscountPackageContentDO();
		contentDO.setDiscountPackageId(discountPackageDO.getId());
		contentDO.setGmtCreate(new Date());
		contentDO.setName("test");
		contentDO.setQuantity(1);
		contentDO.setStatus((byte) 1);
		contentDO.setSubtotal(BigDecimal.TEN);
		contentDO.setUnitPrice(BigDecimal.TEN);
		discountPackageContentDOMapper.insert(contentDO);
		DiscountPackageImageDO imageDO = new DiscountPackageImageDO();
		imageDO.setGmtCreate(new Date());
		imageDO.setImage("default.png");
		imageDO.setDiscountPackageId(discountPackageDO.getId());
		imageDO.setStatus((byte) 1);
		imageDO.setDescription("");
		discountPackageImageDOMapper.insert(imageDO);

		List<DiscountPackageContentUpdateForeignParam> updateForeignParams = new ArrayList<>();
		DiscountPackageContentUpdateForeignParam updateForeignParam = new DiscountPackageContentUpdateForeignParam();
		updateForeignParam.setUnitPrice(BigDecimal.ONE);
		updateForeignParam.setQuantity(1);
		updateForeignParam.setUnit("unit");
		updateForeignParams.add(updateForeignParam);
		List<DiscountPackageImageUpdateParam> imageUpdateParams = new ArrayList<>();
		DiscountPackageImageUpdateParam imageUpdateParam = new DiscountPackageImageUpdateParam();
		imageUpdateParam.setDescription("TEST");
		imageUpdateParams.add(imageUpdateParam);

		DiscountPackageUpdateParam param2 = new DiscountPackageUpdateParam();
		param2.setAdvanceBookingTime("30分钟");
		param2.setPurchaseNotes("1,2,3");
		param2.setName("test2");
		param2.setDiscountPackageContents(updateForeignParams);
		param2.setDiscountPackageImages(imageUpdateParams);
		discountPackageService.update(1L, discountPackageDO.getId(), param2);
		List<DiscountPackageDO> packageDOS = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(packageDOS);
		Assert.assertEquals(1, packageDOS.size());
		Assert.assertEquals("test2", packageDOS.get(0).getName());

	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void delete() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		discountPackageService.delete(1L, packageDO.getId());
		List<DiscountPackageDO> discountPackageDOS = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(discountPackageDOS);
		Assert.assertEquals(1, discountPackageDOS.size());
		Assert.assertEquals(StatusEnum.INVALID.getValue(), discountPackageDOS.get(0).getStatus());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void up() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		discountPackageService.up(1L, packageDO.getId());
		List<DiscountPackageDO> list = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(DiscountPackageStatusEnum.UP.getValue(), list.get(0).getStatus());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void down() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		discountPackageService.down(1L, packageDO.getId());
		List<DiscountPackageDO> list = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(DiscountPackageStatusEnum.DOWN.getValue(), list.get(0).getStatus());

	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteList() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		List<Long> ids = new ArrayList<>();
		ids.add(packageDO.getId());
		discountPackageDOMapper.insert(packageDO);
		ids.add(packageDO.getId());
		discountPackageService.delete(1L, ids);
		List<DiscountPackageDO> discountPackageDOS = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(discountPackageDOS);
		Assert.assertEquals(2, discountPackageDOS.size());
		Assert.assertEquals(StatusEnum.INVALID.getValue(), discountPackageDOS.get(0).getStatus());
		Assert.assertEquals(StatusEnum.INVALID.getValue(), discountPackageDOS.get(1).getStatus());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void upList() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		List<Long> ids = new ArrayList<>();
		ids.add(packageDO.getId());
		discountPackageDOMapper.insert(packageDO);
		ids.add(packageDO.getId());
		discountPackageService.up(1L, ids);
		List<DiscountPackageDO> list = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertEquals(DiscountPackageStatusEnum.UP.getValue(), list.get(0).getStatus());
		Assert.assertEquals(DiscountPackageStatusEnum.UP.getValue(), list.get(1).getStatus());
	}

	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void downList() {
		DiscountPackageDO packageDO = new DiscountPackageDO();
		packageDO.setGmtCreate(new Date());
		packageDO.setPrice(BigDecimal.TEN);
		packageDO.setName("test");
		packageDO.setMerchantId(1L);
		packageDO.setStatus(StatusEnum.VALID.getValue());
		packageDO.setMerchantStoreId(1L);
		discountPackageDOMapper.insert(packageDO);
		List<Long> ids = new ArrayList<>();
		ids.add(packageDO.getId());
		discountPackageDOMapper.insert(packageDO);
		ids.add(packageDO.getId());
		discountPackageService.down(1L, ids);
		List<DiscountPackageDO> list = discountPackageDOMapper.selectByExample(null);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertEquals(DiscountPackageStatusEnum.DOWN.getValue(), list.get(0).getStatus());
		Assert.assertEquals(DiscountPackageStatusEnum.DOWN.getValue(), list.get(1).getStatus());
	}

}
