package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.product.constant.CustomSpecStatusEnum;
import com.lawu.eshop.product.dto.ProductCustomSpecDTO;
import com.lawu.eshop.product.dto.ProductQueryDTO;
import com.lawu.eshop.product.query.ProductCustomSpecPageQuery;
import com.lawu.eshop.product.srv.bo.ProductCustomSpecBO;
import com.lawu.eshop.product.srv.converter.ProductCustomSpecConverter;
import com.lawu.eshop.product.srv.service.ProductCustomSpecService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年4月16日
 */
@RestController
@RequestMapping(value = "productCustomSpec/")
public class ProductCustomSpecController extends BaseController{
	
	@Autowired
	private ProductCustomSpecService productCustomSpecService;

	@RequestMapping(value = "specPage", method = RequestMethod.POST)
	public Result<Page<ProductCustomSpecDTO>> specPage(@RequestBody ProductCustomSpecPageQuery query) {
		Page<ProductCustomSpecBO> page = productCustomSpecService.specPage(query);
		List<ProductCustomSpecBO> list = page.getRecords();
		List<ProductCustomSpecDTO> dtos = ProductCustomSpecConverter.convertDTOS(list);

		Page<ProductCustomSpecDTO> retPage = new Page<>();
		retPage.setCurrentPage(query.getCurrentPage());
		retPage.setTotalCount(page.getTotalCount());
		retPage.setRecords(dtos);

		return successCreated(retPage);
	}
	
	
	@RequestMapping(value = "dealCustomSpec/{id}", method = RequestMethod.PUT)
	public Result dealCustomSpec(@PathVariable Long id ,@RequestParam CustomSpecStatusEnum statusEnum){
		productCustomSpecService.dealCustomSpec(id, statusEnum);
		return successCreated();
	}

}
