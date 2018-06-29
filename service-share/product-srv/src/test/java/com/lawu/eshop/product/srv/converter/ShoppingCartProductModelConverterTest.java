/**
 * 
 */
package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.ShoppingCartProductModelDTO;
import com.lawu.eshop.product.srv.bo.ShoppingCartProductModelBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;


/**
 * @author lihj
 * @date 2017年7月17日
 */
public class ShoppingCartProductModelConverterTest {

	
	@Test
	public void convert(){
		ProductModelDO pm =new ProductModelDO();
		pm.setId(1L);
		pm.setInventory(2);
		pm.setMerchantId(3L);
		pm.setName("name");
		pm.setOriginalPrice(new BigDecimal(100));
		pm.setPrice(new BigDecimal(200));
		pm.setProductId(5L);
		pm.setStatus(true);
		ProductDO p =new ProductDO();
		p.setName("name1");
		p.setFeatureImage("featureImage");
		p.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
		p.setIsAllowRefund(false);
		
		ShoppingCartProductModelBO  shop =ShoppingCartProductModelConverter.convert(pm,p);
		Assert.assertEquals(shop.getId(), pm.getId());
		Assert.assertEquals(shop.getInventory(), pm.getInventory());
		Assert.assertEquals(shop.getMerchantId(), pm.getMerchantId());
		Assert.assertEquals(shop.getName(), pm.getName());
		Assert.assertEquals(shop.getOriginalPrice(), pm.getOriginalPrice());
		Assert.assertEquals(shop.getPrice(), pm.getPrice());
		Assert.assertEquals(shop.getProductId(), pm.getProductId());
		Assert.assertEquals(shop.getProductName(), p.getName());
		Assert.assertEquals(shop.getFeatureImage(), p.getFeatureImage());
		Assert.assertEquals(shop.getStatus(), ProductStatusEnum.PRODUCT_STATUS_UP);
		Assert.assertEquals(shop.getIsAllowRefund(), p.getIsAllowRefund());
	}
	
	@Test
	public void convertShop(){
		ShoppingCartProductModelBO sc =new ShoppingCartProductModelBO();
		sc.setFeatureImage("featureImage");
		sc.setId(2L);
		sc.setInventory(4);
		sc.setIsAllowRefund(false);
		sc.setMerchantId(5L);
		sc.setName("name");
		sc.setOriginalPrice(new BigDecimal(100));
		sc.setPrice(new BigDecimal(200));
		sc.setProductName("productName");
		sc.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
		sc.setGmtDown(new Date());
		ShoppingCartProductModelDTO scp =ShoppingCartProductModelConverter.convert(sc);
		Assert.assertEquals(scp.getFeatureImage(), sc.getFeatureImage());
		Assert.assertEquals(scp.getId(), sc.getId());
		Assert.assertEquals(scp.getInventory(), sc.getInventory());
		Assert.assertEquals(scp.getIsAllowRefund(), sc.getIsAllowRefund());
		Assert.assertEquals(scp.getMerchantId(), sc.getMerchantId());
		Assert.assertEquals(scp.getName(), sc.getName());
		Assert.assertEquals(scp.getOriginalPrice(), sc.getOriginalPrice());
		Assert.assertEquals(scp.getPrice(), sc.getPrice());
		Assert.assertEquals(scp.getProductId(), sc.getProductId());
		Assert.assertEquals(scp.getProductName(), sc.getProductName());
		Assert.assertEquals(scp.getStatus(), sc.getStatus());
		Assert.assertEquals(scp.getGmtDown(), sc.getGmtDown());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
