package com.lawu.eshop.mall.srv.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.dto.DiscountPackageDetailForMemberDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.srv.bo.DiscountPackageExtendBO;
import com.lawu.eshop.mall.srv.bo.DiscountPackagePurchaseNotesBO;
import com.lawu.eshop.mall.srv.converter.DiscountPackageConverter;
import com.lawu.eshop.mall.srv.converter.DiscountPackageExtendConverter;
import com.lawu.eshop.mall.srv.exception.DataNotExistException;
import com.lawu.eshop.mall.srv.exception.IllegalOperationException;
import com.lawu.eshop.mall.srv.service.DiscountPackagePurchaseNotesService;
import com.lawu.eshop.mall.srv.service.DiscountPackageService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@RestController
@RequestMapping(value = "discountPackage/")
public class DiscountPackageController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(DiscountPackageController.class);
	
	@Autowired
	private DiscountPackageService discountPackageService;
	
	@Autowired
	private DiscountPackagePurchaseNotesService discountPackagePurchaseNotesService;

	/**
	 * 根据商家id查询商家的所有优惠套餐<p>
	 * 用户端
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "member/list/{merchantId}", method = RequestMethod.GET)
	public Result<Page<DiscountPackageQueryDTO>> listForMember(@PathVariable("merchantId") Long merchantId) {
		Page<DiscountPackageQueryDTO> rtn = DiscountPackageConverter.convertDiscountPackageQueryDTOPage(discountPackageService.listForMember(merchantId));
		return successGet(rtn);
	}
	
	/**
	 * 根据商家id查询商家的所有优惠套餐<p>
	 * 商家端
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "merchant/list/{merchantId}", method = RequestMethod.POST)
	public Result<Page<DiscountPackageQueryDTO>> listForMerchant(@PathVariable("merchantId") Long merchantId, @RequestBody DiscountPackageQueryForeignParam param) {
		Page<DiscountPackageQueryDTO> rtn = DiscountPackageConverter.convertDiscountPackageQueryDTOPage(discountPackageService.listForMerchant(merchantId, param));
		return successGet(rtn);
	}
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "member/{id}", method = RequestMethod.GET)
	public Result<DiscountPackageDetailForMemberDTO> getByMember(@PathVariable("id") Long id) {
		DiscountPackageExtendBO discountPackageExtendBO = null;
		try{
			discountPackageExtendBO = discountPackageService.get(id);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		}
		DiscountPackageDetailForMemberDTO rtn = DiscountPackageExtendConverter.convertDiscountPackageDetailForMemberDTO(discountPackageExtendBO);
		if (StringUtils.isNotBlank(discountPackageExtendBO.getPurchaseNotes())) {
			List<DiscountPackagePurchaseNotesBO> discountPackagePurchaseNotesBOList = discountPackagePurchaseNotesService.list(discountPackageExtendBO.getPurchaseNotes());
			rtn.setPurchaseNotes(new ArrayList<>());
			for (DiscountPackagePurchaseNotesBO item : discountPackagePurchaseNotesBOList) {
				rtn.getPurchaseNotes().add(item.getNote());
			}
		}
		return successGet(rtn);
	}
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Result<DiscountPackageDetailDTO> get(@PathVariable("id") Long id, @RequestParam("merchantId") Long merchantId) {
		DiscountPackageExtendBO discountPackageExtendBO = null;
		try{
			discountPackageExtendBO = discountPackageService.get(id, merchantId);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successGet(DiscountPackageExtendConverter.convert(discountPackageExtendBO));
	}
	
	/**
	 * 保存优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @param discountPackageSaveParam 保存参数
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}", method = RequestMethod.POST)
	public Result save(@PathVariable("merchantId")Long merchantId, @RequestBody DiscountPackageSaveParam discountPackageSaveParam) {
		discountPackageService.save(merchantId, discountPackageSaveParam);
		return successCreated();
	}
	
	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param discountPackageUpdateParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/{id}", method = RequestMethod.PUT)
	public Result update(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id, @RequestBody DiscountPackageUpdateParam discountPackageUpdateParam) {
		try {
			discountPackageService.update(merchantId, id, discountPackageUpdateParam);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/delete/{id}", method = RequestMethod.PUT)
	public Result delete(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id) {
		try {
			discountPackageService.delete(merchantId, id);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/up/{id}", method = RequestMethod.PUT)
	public Result up(@PathVariable("merchantId")Long merchantId, @PathVariable("id")Long id) {
		try {
			discountPackageService.up(merchantId, id);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/down/{id}", method = RequestMethod.PUT)
	public Result down(@PathVariable("merchantId")Long merchantId, @PathVariable("id") Long id) {
		try {
			discountPackageService.down(merchantId, id);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 删除优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/delete", method = RequestMethod.PUT)
	public Result delete(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList) {
		try {
			discountPackageService.delete(merchantId, idList);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/up", method = RequestMethod.PUT)
	public Result up(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList) {
		try {
			discountPackageService.up(merchantId, idList);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
	/**
	 * 批量上架优惠套餐
	 * 
	 * @param merchantId
	 * @param id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "{merchantId}/down", method = RequestMethod.PUT)
	public Result down(@PathVariable("merchantId")Long merchantId, @RequestBody List<Long> idList) {
		try {
			discountPackageService.down(merchantId, idList);
		} catch (DataNotExistException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
		} catch (IllegalOperationException e) {
			logger.error(e.getMessage(), e);
			return successCreated(ResultCode.ILLEGAL_OPERATION, e.getMessage());
		}
		return successCreated();
	}
	
}
