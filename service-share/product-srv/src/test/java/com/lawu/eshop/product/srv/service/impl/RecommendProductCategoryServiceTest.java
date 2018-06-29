/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.RecommendProductCategoryBO;
import com.lawu.eshop.product.srv.domain.RecommendProductCategoryDO;
import com.lawu.eshop.product.srv.mapper.RecommendProductCategoryDOMapper;
import com.lawu.eshop.product.srv.service.RecommendProductCategoryService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月18日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
@WebAppConfiguration
public class RecommendProductCategoryServiceTest {

	private Logger log = Logger.getLogger(ProductServiceTest.class);

	@Autowired
	private RecommendProductCategoryService recommendProductCategoryService;

	@Autowired
	private RecommendProductCategoryDOMapper recommendProductCategoryDOMapper;
	   
	/**
	 * 保存商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void saveRecommendProductCategory() {
		EditRecommendProductCategoryParam param = new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("categoryName");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		recommendProductCategoryService.saveRecommendProductCategory(param);
		RecommendProductCategoryBO bo = recommendProductCategoryService.getRecommendProductCategoryById(1L);
		log.info(JSON.toJSON(bo));
	}

	/**
	 * 根据ID删除商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void deleteRecommendProductCategoryById() {
		EditRecommendProductCategoryParam param = new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("categoryName");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		recommendProductCategoryService.saveRecommendProductCategory(param);
		RecommendProductCategoryBO bo = recommendProductCategoryService.getRecommendProductCategoryById(1L);
		log.info(JSON.toJSON(bo));
		recommendProductCategoryService.deleteRecommendProductCategoryById(1L);
		RecommendProductCategoryBO bo1 = recommendProductCategoryService.getRecommendProductCategoryById(1L);
		log.info(JSON.toJSON(bo1));
	}

	/**
	 * 根据ID修改商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void updateRecommendProductCategoryById() {
		RecommendProductCategoryDO rbo =new RecommendProductCategoryDO();
		rbo.setCategoryId(1);
		rbo.setCategoryName("categoryName");
		rbo.setGmtCreate(new Date());
		rbo.setIsvisible(true);
		rbo.setOrdinal(Byte.valueOf("1"));
		int id = recommendProductCategoryDOMapper.insert(rbo);
		ListRecommendProductCategoryParam listparam =new ListRecommendProductCategoryParam();
		listparam.setCurrentPage(1);
		listparam.setPageSize(100);
		Page<RecommendProductCategoryBO> list = recommendProductCategoryService.listRecommendProductCategory(listparam);
		for (int i = 0; i < list.getRecords().size(); i++) {
			EditRecommendProductCategoryParam editParam = new EditRecommendProductCategoryParam();
			editParam.setCategoryName("测试12312312");
			recommendProductCategoryService.updateRecommendProductCategoryById(list.getRecords().get(i).getId(), editParam);
			RecommendProductCategoryBO bo = recommendProductCategoryService.getRecommendProductCategoryById(list.getRecords().get(i).getId());
			log.info(JSON.toJSON(bo));
			Assert.assertNotNull(bo);
			Assert.assertEquals(bo.getCategoryName(), "测试12312312");
		}
	}

	/**
	 * 根据ID查询商品类别
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getRecommendProductCategoryById() {
		EditRecommendProductCategoryParam param = new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("categoryName");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		recommendProductCategoryService.saveRecommendProductCategory(param);
		ListRecommendProductCategoryParam listparam =new ListRecommendProductCategoryParam();
		listparam.setCurrentPage(1);
		listparam.setPageSize(100);
		Page<RecommendProductCategoryBO> list = recommendProductCategoryService.listRecommendProductCategory(listparam);
		for (int i = 0; i < list.getRecords().size(); i++) {
			RecommendProductCategoryBO bo = recommendProductCategoryService.getRecommendProductCategoryById(list.getRecords().get(i).getId());
			Assert.assertNotNull(bo);
			
		}
	}

	/**
	 * 商品类别列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listRecommendProductCategory() {
		EditRecommendProductCategoryParam param = new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("categoryName");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		recommendProductCategoryService.saveRecommendProductCategory(param);
		EditRecommendProductCategoryParam param1 = new EditRecommendProductCategoryParam();
		param1.setCategoryId(2);
		param1.setCategoryName("categoryName111");
		param1.setIsvisible(false);
		param1.setOrdinal(Byte.valueOf("2"));
		recommendProductCategoryService.saveRecommendProductCategory(param1);
		ListRecommendProductCategoryParam listparam =new ListRecommendProductCategoryParam();
		listparam.setCurrentPage(1);
		listparam.setPageSize(100);
		Page<RecommendProductCategoryBO> list = recommendProductCategoryService.listRecommendProductCategory(listparam);
		log.info(JSON.toJSON(list));
	}

	/**
	 * 商品类别列表
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void listAllRecommendProductCategory() {
		EditRecommendProductCategoryParam param = new EditRecommendProductCategoryParam();
		param.setCategoryId(1);
		param.setCategoryName("categoryName");
		param.setIsvisible(true);
		param.setOrdinal(Byte.valueOf("1"));
		recommendProductCategoryService.saveRecommendProductCategory(param);
		EditRecommendProductCategoryParam param1 = new EditRecommendProductCategoryParam();
		param1.setCategoryId(2);
		param1.setCategoryName("categoryName111");
		param1.setIsvisible(false);
		param1.setOrdinal(Byte.valueOf("2"));
		recommendProductCategoryService.saveRecommendProductCategory(param1);
		List<RecommendProductCategoryBO> list = recommendProductCategoryService.listAllRecommendProductCategory();
		log.info(JSON.toJSON(list));
		Assert.assertEquals(list.size(), 2);
	}

	@Transactional
	@Test
	@Rollback
	public void getHotRecommendProductCategory(){
		RecommendProductCategoryDO categoryDO = new RecommendProductCategoryDO();
		categoryDO.setImagePath("image");
		categoryDO.setGmtCreate(new Date());
		categoryDO.setIsHot(true);
		categoryDO.setIsvisible(true);
		categoryDO.setCategoryId(3);
		categoryDO.setCategoryName("食品");
		categoryDO.setOrdinal((byte) 1);
		recommendProductCategoryDOMapper.insertSelective(categoryDO);
		List<RecommendProductCategoryBO> categoryBOS = recommendProductCategoryService.getHotRecommendProductCategory();
		Assert.assertEquals(categoryDO.getId(),categoryBOS.get(0).getId());
		Assert.assertEquals(categoryDO.getCategoryName(),categoryBOS.get(0).getCategoryName());
		Assert.assertEquals(categoryDO.getGmtCreate(),categoryBOS.get(0).getGmtCreate());
		Assert.assertEquals(categoryDO.getImagePath(),categoryBOS.get(0).getImagePath());
		Assert.assertEquals(categoryDO.getIsHot(),categoryBOS.get(0).getIsHot());
		Assert.assertEquals(categoryDO.getIsvisible(),categoryBOS.get(0).getIsvisible());
		Assert.assertEquals(categoryDO.getOrdinal(),categoryBOS.get(0).getOrdinal());
		Assert.assertEquals(categoryDO.getId(),categoryBOS.get(0).getId());
	}


	@Transactional
	@Rollback
	@Test
	public void editHot(){
		RecommendProductCategoryDO categoryDO = new RecommendProductCategoryDO();
		categoryDO.setImagePath("image");
		categoryDO.setGmtCreate(new Date());
		categoryDO.setIsHot(false);
		categoryDO.setIsvisible(true);
		categoryDO.setCategoryId(3);
		categoryDO.setCategoryName("食品");
		categoryDO.setOrdinal((byte) 1);
		recommendProductCategoryDOMapper.insertSelective(categoryDO);
		recommendProductCategoryService.editHot(categoryDO.getId(),true);
		List<RecommendProductCategoryDO> categoryDOS = recommendProductCategoryDOMapper.selectByExample(null);
		Assert.assertEquals(true,categoryDOS.get(0).getIsHot());
	}

}
