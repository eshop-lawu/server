/**
 * 
 */
package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.dto.FavoriteProductDTO;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.eshop.product.srv.domain.extend.FavoriteProductView;


/**
 * @author lihj
 * @date 2017年7月13日
 */
public class FavoriteProductConverterTest {

	@Test
	public void convertBO(){
		FavoriteProductView view =new FavoriteProductView();
		view.setName("测试");
		view.setFeatureImage("www.baidu.com");
		FavoriteProductBO bo =FavoriteProductConverter.convertBO(view);
		Assert.assertEquals(bo.getName(), view.getName());
		Assert.assertEquals(bo.getFeatureImage(), view.getFeatureImage());
	}
	
	@Test
	public void convertBOS(){
		List<FavoriteProductView> favoriteProductViews =new ArrayList<FavoriteProductView>();
		FavoriteProductView view =new FavoriteProductView();
		view.setProductId(1L);
		view.setName("测试");
		view.setFeatureImage("www.baidu.com");
		view.setPrice(new BigDecimal(100));
		view.setContent("内容");
		favoriteProductViews.add(view);
		List<FavoriteProductBO> list =FavoriteProductConverter.convertBOS(favoriteProductViews);
		Assert.assertEquals(list.get(0).getId(), view.getProductId());
		Assert.assertEquals(list.get(0).getName(), view.getName());
		Assert.assertEquals(list.get(0).getFeatureImage(), view.getFeatureImage());
		Assert.assertEquals(list.get(0).getPrice(), view.getPrice());
		Assert.assertEquals(list.get(0).getContent(), view.getContent());
	}
	
	@Test
	public void convertDTO(){
		FavoriteProductBO bo =new FavoriteProductBO();
		bo.setId(1L);
		bo.setName("张三");
		bo.setFeatureImage("www.baidu.com");
		bo.setPrice(new BigDecimal(100));
		bo.setContent("内容");
		FavoriteProductDTO dto =FavoriteProductConverter.convertDTO(bo);
		Assert.assertEquals(dto.getId(), bo.getId());
		Assert.assertEquals(dto.getName(), bo.getName());
		Assert.assertEquals(dto.getFeatureImage(), bo.getFeatureImage());
		Assert.assertEquals(dto.getPrice(), bo.getPrice());
		Assert.assertEquals(dto.getContent(), bo.getContent());
	}

}
