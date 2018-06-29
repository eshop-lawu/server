/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.param.OperatorProductCategoryParam;
import com.lawu.eshop.product.param.ProductCategoryParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.eshop.product.srv.mapper.ProductCategoryeDOMapper;
import com.lawu.eshop.product.srv.service.ProductCategoryService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductCategoryServiceTest {

	private Logger log=Logger.getLogger(ProductCategoryServiceTest.class);
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private ProductCategoryeDOMapper productCategoryeDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void save(){
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试".concat("1"));
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setPath("path");
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insert(pc);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findAll(){
		Integer idOne = saveCategory("1");
		Integer idTwo = saveCategory("2");
		List<ProductCategoryBO> list = productCategoryService.findAll();
		log.info(JSON.toJSON(list));
		Assert.assertEquals(list.size(), 2);
	}

	
	/**
	 * 通过ID查询
	 */
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void getById() {
		Integer idOne = saveCategory("1");
		Integer idTwo = saveCategory("2");
		ProductCategoryBO bo = productCategoryService.getById(idOne);
		List<ProductCategoryBO> list = productCategoryService.findAll();
		log.info(JSON.toJSON(list));
		Assert.assertNotNull(bo);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void getFullName() {
		Integer idZero = saveCategory("0");
		Integer idOne = saveCategory("1");
		Integer idTwo = saveTwoPathCategory("2",idZero+"/"+idOne);
		Integer idThree = saveThreeCategory("3",idZero+"/"+idOne+"/"+idTwo);
		List<ProductCategoryBO> list = productCategoryService.findAll();
		log.info(JSON.toJSON(list));
		String str = productCategoryService.getFullName(list.get(1).getId());
		log.info(str);
		Assert.assertEquals("测试1",str);
		String strTwo = productCategoryService.getFullName(list.get(2).getId());
		log.info(strTwo);
		Assert.assertEquals("测试0-测试1",strTwo);
		String strThree = productCategoryService.getFullName(list.get(3).getId());
		log.info(strThree);
		Assert.assertEquals("测试0-测试1-测试2",strThree);
	}
	 
	/**
	 * 查询推荐商品类别
	 * 
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void listRecommendProductCategory() {
		Integer idOne = saveCategory("1");
		Integer idTwo = saveCategory("2");
		List<ProductCategoryBO> list =productCategoryService.listRecommendProductCategory();
		log.info(list.size());
		Assert.assertEquals(list.size(),2);
	}
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void find() {
		Integer idOne = saveCategory("1");
		Integer idTwo = saveCategory("2");
		List<ProductCategoryBO> list = productCategoryService.find(Long.valueOf(-1));
		Assert.assertEquals(list.size(),2);
	}
	
	

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void getFullCategoryId() {
		Integer idOne = saveCategory("1");
		Integer idTwo = saveCategory("2");
		List<ProductCategoryBO> list =productCategoryService.listRecommendProductCategory();
		for (int i = 0; i < list.size(); i++) {
			String str = productCategoryService.getFullCategoryId(list.get(i).getId());
			log.info(str);
			Assert.assertEquals(str,list.get(i).getId().toString());
			
		}
	}
	
	
	/**
	 * @param
	 */
	private Integer saveCategory(String str) {
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试".concat(str));
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setPath("1");
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insert(pc);
		return pc.getId();
	}


	private Integer saveTwoPathCategory(String str,String path) {
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试".concat(str));
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setPath(path);
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insert(pc);
		return pc.getId();
	}


	private Integer saveThreeCategory(String str,String path) {
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试".concat(str));
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setPath(path);
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insert(pc);
		return pc.getId();
	}

	@Rollback
	@Test
	@Transactional
	public void addProductCategory() {
		ProductCategoryParam param = new ProductCategoryParam();
		param.setName("食品");
		param.setLevel((byte) 1);
		param.setParentId(0);
		param.setImageUrl("imageUrl");
		param.setOrdinal((byte) 2);
		param.setIsVirtual(false);
		productCategoryService.addProductCategory(param);
		List<ProductCategoryeDO> categoryDOS = productCategoryeDOMapper.selectByExample(null);
		Assert.assertEquals(param.getName(), categoryDOS.get(0).getName());
		Assert.assertEquals(param.getParentId(), categoryDOS.get(0).getParentId());
		Assert.assertEquals(param.getLevel().byteValue(), categoryDOS.get(0).getLevel().byteValue());
		Assert.assertEquals(param.getImageUrl(), categoryDOS.get(0).getImageUrl());
		Assert.assertEquals(param.getOrdinal().byteValue(), categoryDOS.get(0).getOrdinal().byteValue());
		Assert.assertEquals(param.getIsVirtual(), categoryDOS.get(0).getIsVirtual());
	}

	@Transactional
	@Test
	@Rollback
	public void delProductCategory(){
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试");
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insertSelective(pc);
		productCategoryService.delProductCategory(pc.getId());
		List<ProductCategoryeDO> categoryDOS = productCategoryeDOMapper.selectByExample(null);
		Assert.assertEquals(false, categoryDOS.get(0).getStatue());
	}

	@Test
	@Rollback
	@Transactional
	public void editProductCategory(){
		ProductCategoryParam param = new ProductCategoryParam();
		param.setName("食品");
		param.setLevel((byte) 1);
		param.setParentId(0);
		param.setImageUrl("imageUrl");
		param.setOrdinal((byte) 2);
		param.setIsVirtual(false);
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试");
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setStatue(true);
		pc.setType(Byte.valueOf("3"));
		productCategoryeDOMapper.insertSelective(pc);
		productCategoryService.editProductCategory(pc.getId(),param);
		List<ProductCategoryeDO> categoryDOS = productCategoryeDOMapper.selectByExample(null);
		Assert.assertEquals(param.getName(), categoryDOS.get(0).getName());
		Assert.assertEquals(param.getParentId(), categoryDOS.get(0).getParentId());
		Assert.assertEquals(param.getLevel().byteValue(), categoryDOS.get(0).getLevel().byteValue());
		Assert.assertEquals(param.getImageUrl(), categoryDOS.get(0).getImageUrl());
		Assert.assertEquals(param.getOrdinal().byteValue(), categoryDOS.get(0).getOrdinal().byteValue());
		Assert.assertEquals(param.getIsVirtual(), categoryDOS.get(0).getIsVirtual());
	}

	@Test
	@Rollback
	@Transactional
	public void editHot(){
		ProductCategoryeDO pc =new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试");
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setStatue(true);
		pc.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_NORMAL.getVal());
		productCategoryeDOMapper.insertSelective(pc);
		productCategoryService.editHot(pc.getId(),ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT);
		List<ProductCategoryeDO> categoryDOS = productCategoryeDOMapper.selectByExample(null);
		Assert.assertEquals(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal(), categoryDOS.get(0).getType());
	}

	@Test
	@Rollback
	@Transactional
	public void getHotProductCategory() {
		ProductCategoryeDO pc = new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试");
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(-1);
		pc.setStatue(true);
		pc.setImageUrl("image");
		pc.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal());
		productCategoryeDOMapper.insertSelective(pc);
		List<ProductCategoryBO> categoryBOS = productCategoryService.getHotProductCategory();
		Assert.assertEquals(pc.getName(), categoryBOS.get(0).getName());
		Assert.assertEquals(pc.getId(), categoryBOS.get(0).getId());
		Assert.assertEquals(pc.getImageUrl(), categoryBOS.get(0).getImageUrl());
	}

	@Test
	@Rollback
	@Transactional
	public void findOperatorAll() {
		ProductCategoryeDO pc = new ProductCategoryeDO();
		pc.setIsVirtual(true);
		pc.setLevel(Byte.valueOf("1"));
		pc.setName("测试");
		pc.setOrdinal(Byte.valueOf("2"));
		pc.setParentId(0);
		pc.setStatue(true);
		pc.setImageUrl("image");
		pc.setType(ProductCategoryTypeEnum.PRODUCT_CATEGORY_HOT.getVal());
		productCategoryeDOMapper.insertSelective(pc);
		OperatorProductCategoryParam param = new OperatorProductCategoryParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setLevel((byte) 1);
		param.setName(pc.getName());
		param.setParentId(pc.getParentId());
		Page<ProductCategoryBO> page = productCategoryService.findOperatorAll(param);
		Assert.assertEquals(pc.getName(), page.getRecords().get(0).getName());
		Assert.assertEquals(pc.getId(), page.getRecords().get(0).getId());
		Assert.assertEquals(pc.getImageUrl(), page.getRecords().get(0).getImageUrl());
		Assert.assertEquals(pc.getIsVirtual(), page.getRecords().get(0).getVirtual());
		Assert.assertEquals(pc.getOrdinal().byteValue(), page.getRecords().get(0).getOrdinal().byteValue());

	}
	
}
