/**
 * 
 */
package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.product.srv.bo.ProductModelBO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;

/**
 * @author lihj
 * @date 2017年7月17日
 */
public class ProductModelConverterTest {

	@Test
	public void convertBO(){
		ProductModelDO pm =new ProductModelDO();
		pm.setId(1L);
		pm.setName("name");
		pm.setOriginalPrice(new BigDecimal(100));
		pm.setPrice(new BigDecimal(200));
		pm.setInventory(3);
		pm.setSalesVolume(5);
		ProductModelBO pb =ProductModelConverter.convertBO(pm);
		Assert.assertEquals(pb.getId(), pm.getId());
		Assert.assertEquals(pb.getName(), pm.getName());
		Assert.assertEquals(pb.getOriginalPrice(), pm.getOriginalPrice());
		Assert.assertEquals(pb.getPrice(), pm.getPrice());
		Assert.assertEquals(pb.getInventory(), pm.getInventory());
		Assert.assertEquals(pb.getSalesVolume(), pm.getSalesVolume());
		
	}
}
