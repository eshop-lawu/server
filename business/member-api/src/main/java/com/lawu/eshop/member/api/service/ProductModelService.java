package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.product.dto.ProductModelDataDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInfoDTO;
import com.lawu.eshop.product.dto.ShoppingCartProductModelDTO;
import com.lawu.framework.web.Result;

/**
 * 商品型号接口
 * 
 * @author Sunny
 * @date 2017/3/30
 */
@FeignClient(value = "product-srv", path = "productModel/")
public interface ProductModelService {

	/**
	 * 根据商品型号ID查询购物车商品信息
	 * 
	 * @param id
	 *            商品型号ID
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shoppingCart/{id}")
	Result<ShoppingCartProductModelDTO> getShoppingCartProductModel(@PathVariable("id") Long id);

	/**
	 * 根据商品型号ID列表查询购物车商品信息
	 * 
	 * @param ids
	 *            商品型号ID列表
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "shoppingCart/list")
	Result<List<ShoppingCartProductModelDTO>> getShoppingCartProductModel(@RequestParam("ids") List<Long> ids);
	
	   /**
     * 根据抢购活动商品型号id
     * 查询抢购活动所需要的商品型号数据
     * 
     * @param activityProductModelId 抢购活动商品型号id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    @RequestMapping(value = "seckillActivity/{activityProductModelId}", method = RequestMethod.GET)
    Result<SeckillActivityProductModelInfoDTO> seckillActivityProductModel(@PathVariable("activityProductModelId") Long activityProductModelId);

	/**
	 * 商品id查询商品型号
	 *
	 * @param productId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "queryProductModel/{productId}")
	Result<List<ProductModelDataDTO>> queryProductModel(@PathVariable("productId") Long productId);

}
