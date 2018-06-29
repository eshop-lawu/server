package com.lawu.eshop.product.srv.service.impl;

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

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.constant.CustomTypeEnum;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.ProductCustomSpecBO;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDO;
import com.lawu.eshop.product.srv.domain.ProductCustomSpecDOExample;
import com.lawu.eshop.product.srv.mapper.ProductCustomSpecDOMapper;
import com.lawu.eshop.product.srv.service.ProductCustomSpecService;
import com.lawu.framework.core.page.Page;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月19日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class ProductCustomSpecServiceTest {
	
	@Autowired
	private ProductCustomSpecService productCustomSpecService;
	
	@Autowired
	private ProductCustomSpecDOMapper productCustomSpecDOMapper;
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void specPage(){
		ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
		productCustomSpecDO.setRelateId(1l);
		productCustomSpecDO.setRelateName("衣服");
		productCustomSpecDO.setSpecName("test");
		productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
		productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
		productCustomSpecDO.setGmtCreate(new Date());
		productCustomSpecDO.setGmtModified(new Date());
		productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		
		ProductCustomSpecPageQuery query =new ProductCustomSpecPageQuery();
		query.setCurrentPage(1);
		query.setPageSize(20);
		Page<ProductCustomSpecBO> page =productCustomSpecService.specPage(query);
		Assert.assertEquals(page.getRecords().size(), 1);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void dealCustomSpec(){
		ProductCustomSpecDO productCustomSpecDO = new ProductCustomSpecDO();
		productCustomSpecDO.setRelateId(1l);
		productCustomSpecDO.setRelateName("衣服");
		productCustomSpecDO.setSpecName("test");
		productCustomSpecDO.setCustomType(CustomTypeEnum.CATEGORY.getVal());
		productCustomSpecDO.setStatus(CustomSpecStatusEnum.ON_DEAL_WITH.getVal());
		productCustomSpecDO.setGmtCreate(new Date());
		productCustomSpecDO.setGmtModified(new Date());
		Integer id = productCustomSpecDOMapper.insertSelective(productCustomSpecDO);
		
		productCustomSpecService.dealCustomSpec(Long.parseLong(id.toString()),CustomSpecStatusEnum.DEAL_WITH);
		
		ProductCustomSpecDOExample example = new ProductCustomSpecDOExample();
		example.createCriteria().andStatusEqualTo(CustomSpecStatusEnum.DEAL_WITH.getVal());
		List<ProductCustomSpecDO> list = productCustomSpecDOMapper.selectByExample(example);
		Assert.assertEquals(list.size(), 1);
	}
	

}
