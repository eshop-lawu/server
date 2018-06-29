/**
 * 
 */
package com.lawu.eshop.product.srv.service.impl;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.eshop.product.srv.service.FavoriteProductService;
import com.lawu.framework.core.page.Page;

/**
 * @author lihj
 * @date 2017年7月17日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class FavoriteProductServiceImplTest {

	private Logger log=Logger.getLogger(FavoriteProductServiceImplTest.class);
	
	@Autowired
	private FavoriteProductService favoriteProductService;
	
	/**
	 * 商品收藏
	 */
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void save(){
		Long memberId =1L;
		Long productId=1L;
		favoriteProductService.save(memberId, productId);
		FavoriteProductQuery query =new FavoriteProductQuery();
		Page<FavoriteProductBO> page =favoriteProductService.selectMyFavoriteProduct(memberId, query);
		Assert.assertEquals(page.getRecords().size(), 1);
	}
	
	/**
	 * 取消收藏
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void remove(){
		Long memberId =1L;
		Long productId=1L;
		favoriteProductService.save(memberId, productId);
		favoriteProductService.remove(productId, memberId);
		FavoriteProductQuery query =new FavoriteProductQuery();
		Page<FavoriteProductBO> page =favoriteProductService.selectMyFavoriteProduct(memberId, query);
		Assert.assertEquals(page.getRecords().size(), 0);
	}
	
	/**
	 * 我收藏的商品
	 */
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void selectMyFavoriteProduct(){
		Long memberId =1L;
		Long productId=1L;
		favoriteProductService.save(memberId, productId);
		FavoriteProductQuery query =new FavoriteProductQuery();
		Page<FavoriteProductBO> page =favoriteProductService.selectMyFavoriteProduct(memberId, query);
		Assert.assertEquals(page.getRecords().size(), 1);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Rollback
	@Test
	public void getUserFavorite(){
		Long memberId =1L;
		Long productId=1L;
		favoriteProductService.save(memberId, productId);
		Integer count = favoriteProductService.getUserFavorite(productId, memberId);
		Assert.assertEquals(count, Integer.valueOf(1));
	}
	
	
	
	
}
