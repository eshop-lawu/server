package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.member.api.service.ProductModelService;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.ProductModelDataDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInfoDTO;
import com.lawu.eshop.product.dto.ShoppingCartProductModelDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
class MockProductModelService extends BaseController implements ProductModelService {


	@Override
	public Result<ShoppingCartProductModelDTO> getShoppingCartProductModel(@PathVariable("id") Long id) {
		ShoppingCartProductModelDTO dto = new ShoppingCartProductModelDTO();
		dto.setMerchantId(1L);
		dto.setId(1L);
		dto.setProductId(1L);
		dto.setPrice(new BigDecimal(1));
		return successCreated(dto);
	}

	@Override
	public Result<List<ShoppingCartProductModelDTO>> getShoppingCartProductModel(@RequestParam("ids") List<Long> ids) {
		ShoppingCartProductModelDTO dto = new ShoppingCartProductModelDTO();
		dto.setPrice(new BigDecimal(1));
		dto.setName("fdf");
		dto.setProductId(1L);
		dto.setId(1L);
		dto.setMerchantId(1L);
		dto.setFeatureImage("1.jpg");
		dto.setInventory(100);
		dto.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
		List<ShoppingCartProductModelDTO> list = new ArrayList<>();
		list.add(dto);
		return successCreated(list);
	}

    @Override
    public Result<SeckillActivityProductModelInfoDTO> seckillActivityProductModel(Long activityProductModelId) {
        return null;
    }

	@Override
	public Result<List<ProductModelDataDTO>> queryProductModel(@PathVariable("productId") Long productId) {
		return null;
	}
}
