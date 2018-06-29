package com.lawu.eshop.mall.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;
import com.lawu.eshop.mall.srv.converter.DiscountPackagePurchaseNotesConverter;
import com.lawu.eshop.mall.srv.service.DiscountPackagePurchaseNotesService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "discountPackagePurchaseNotes/")
public class DiscountPackagePurchaseNotesController extends BaseController {

	@Autowired
	private DiscountPackagePurchaseNotesService discountPackagePurchaseNotesService;

	/**
	 * 查询所有的购买须知
	 * 
	 * @return
	 * @author Sunny
	 * @date 2017年7月31日
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<List<DiscountPackagePurchaseNotesDTO>> list() {
		List<DiscountPackagePurchaseNotesBO> list = discountPackagePurchaseNotesService.list();
		return successGet(DiscountPackagePurchaseNotesConverter.convertDiscountPackagePurchaseNotesDTOList(list));
	}
	
}
