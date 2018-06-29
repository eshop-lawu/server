package com.lawu.eshop.merchant.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.DiscountPackageDetailDTO;
import com.lawu.eshop.mall.dto.DiscountPackageQueryDTO;
import com.lawu.eshop.mall.param.DiscountPackageImageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageImageUpdateParam;
import com.lawu.eshop.mall.param.DiscountPackageSaveParam;
import com.lawu.eshop.mall.param.DiscountPackageUpdateParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageContentUpdateForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageImageSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageImageUpdateForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageQueryForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageSaveForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageSaveWithImageForeignParam;
import com.lawu.eshop.mall.param.foreign.DiscountPackageUpdateForeignParam;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.DiscountPackageService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.user.constants.UploadFileTypeConstant;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.util.UploadFileUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author Sunny
 * @date 2017/3/27
 */
@Api(tags = "discountPackage")
@RestController
@RequestMapping(value = "discountPackage/")
public class DiscountPackageController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(DiscountPackageController.class);
	
	@Autowired
	private DiscountPackageService discountPackageService;
	
	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@Autowired
	private MerchantApiConfig merchantApiConfig;
	
	/**
	 * 根据商家id查询商家的所有优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询优惠套餐列表", notes = "根据商家id查询商家全部优惠套餐列表,不分页。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public Result<Page<DiscountPackageQueryDTO>> list(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐查询参数", required = true) @ModelAttribute @Validated DiscountPackageQueryForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<Page<DiscountPackageQueryDTO>> result = discountPackageService.listForMerchant(merchantId, param);
		return successGet(result);
	}
	
	/**
	 * 根据优惠套餐id查询优惠套餐详情
	 * 
	 * @param id 优惠套餐id
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询优惠套餐详情", notes = "查询单个优惠套餐详情。[]（蒋鑫俊）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Result<DiscountPackageDetailDTO> get(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,  @ApiParam(value = "优惠套餐id", required = true) @PathVariable("id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result<DiscountPackageDetailDTO> result = discountPackageService.get(id, merchantId);
		return successGet(result);
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
	@Deprecated
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存优惠套餐详情[Deprecated]", notes = "保存优惠套餐。[]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐保存参数", required = true) @ModelAttribute @Validated DiscountPackageSaveForeignParam discountPackageSaveForeignParam, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	HttpServletRequest request = getRequest();
        Map<String, String> images = new HashMap<>();
        
        Collection<Part> parts = null;
        try {
           parts = request.getParts();
        } catch (IOException | ServletException e) {
        	logger.error("读取上传文件异常", e);
            return successCreated(ResultCode.FAIL, e.getMessage());
        }
        
        if(parts != null && StringUtils.isNotEmpty(parts.toString())) {
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_MALL_DISCOUNT_PACKAGE, part, merchantApiConfig.getImageUploadUrl());
                String flag = map.get("resultFlag");
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get("imgUrl");
                    images.put(part.getName(), imgUrl);
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }
    	
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	Result<Long> getMerchantStoreIdResult = merchantStoreService.getMerchantStoreId(merchantId);
    	if (!isSuccess(getMerchantStoreIdResult)) {
    		return successCreated(getMerchantStoreIdResult);
    	}
    	
		DiscountPackageSaveParam discountPackageSaveParam = new DiscountPackageSaveParam();
		discountPackageSaveParam.setCoverImage(images.get("coverImage"));
		discountPackageSaveParam.setIsReservation(discountPackageSaveForeignParam.getIsReservation());
		discountPackageSaveParam.setMerchantStoreId(getMerchantStoreIdResult.getModel());
		discountPackageSaveParam.setName(discountPackageSaveForeignParam.getName());
		discountPackageSaveParam.setOtherInstructions(discountPackageSaveForeignParam.getOtherInstructions());
		discountPackageSaveParam.setPrice(discountPackageSaveForeignParam.getPrice());
		discountPackageSaveParam.setUseRules(discountPackageSaveForeignParam.getUseRules());
		discountPackageSaveParam.setUseTimeBegin(discountPackageSaveForeignParam.getUseTimeBegin());
		discountPackageSaveParam.setUseTimeEnd(discountPackageSaveForeignParam.getUseTimeEnd());
		discountPackageSaveParam.setUseTimeWeek(discountPackageSaveForeignParam.getUseTimeWeek());
		discountPackageSaveParam.setValidityPeriodBegin(discountPackageSaveForeignParam.getValidityPeriodBegin());
		discountPackageSaveParam.setValidityPeriodEnd(discountPackageSaveForeignParam.getValidityPeriodEnd());
		discountPackageSaveParam.setAdvanceBookingTime(discountPackageSaveForeignParam.getAdvanceBookingTime());
		discountPackageSaveParam.setPurchaseNotes(discountPackageSaveForeignParam.getPurchaseNotes());
		String discountPackageContentsStr = null;
		String discountPackageImagesStr = null;
		try {
			discountPackageContentsStr = URLDecoder.decode(discountPackageSaveForeignParam.getDiscountPackageContents(), "UTF-8");
			discountPackageImagesStr = URLDecoder.decode(discountPackageSaveForeignParam.getDiscountPackageImages(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("解码失败", e);
			return successCreated(ResultCode.FAIL, e.getMessage());
		}
		discountPackageSaveParam.setDiscountPackageContents(JSONObject.parseArray(discountPackageContentsStr, DiscountPackageContentSaveForeignParam.class));
		List<DiscountPackageImageSaveForeignParam> discountPackageImages = JSONObject.parseArray(discountPackageImagesStr, DiscountPackageImageSaveForeignParam.class);
		
		discountPackageSaveParam.setDiscountPackageImages(new ArrayList<>());
		for (int i = 0; i < discountPackageImages.size(); i++) {
			DiscountPackageImageSaveForeignParam discountPackageImage = discountPackageImages.get(i);
			DiscountPackageImageSaveParam discountPackageImageSaveParam = new DiscountPackageImageSaveParam();
			discountPackageImageSaveParam.setImage(images.get("discountPackageImage-"+i));
			discountPackageImageSaveParam.setDescription(discountPackageImage.getDescription());
			discountPackageSaveParam.getDiscountPackageImages().add(discountPackageImageSaveParam);
			
		}
		Result result = discountPackageService.save(merchantId, discountPackageSaveParam);
		return successCreated(result);
	}
	
	/**
	 * 保存优惠套餐
	 * 
	 * @param merchantId 商家id
	 * @param param 保存参数
	 * @return
	 * @author Sunny
	 * @date 2017年8月03日
	 */
	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "保存优惠套餐详情", notes = "保存优惠套餐。[]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Result save(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐保存参数", required = true) @RequestBody @Validated DiscountPackageSaveWithImageForeignParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	Result<Long> getMerchantStoreIdResult = merchantStoreService.getMerchantStoreId(merchantId);
    	if (!isSuccess(getMerchantStoreIdResult)) {
    		return successCreated(getMerchantStoreIdResult);
    	}
		DiscountPackageSaveParam discountPackageSaveParam = new DiscountPackageSaveParam();
		discountPackageSaveParam.setCoverImage(param.getCoverImage());
		discountPackageSaveParam.setIsReservation(param.getIsReservation());
		discountPackageSaveParam.setMerchantStoreId(getMerchantStoreIdResult.getModel());
		discountPackageSaveParam.setName(param.getName());
		discountPackageSaveParam.setOtherInstructions(param.getOtherInstructions());
		discountPackageSaveParam.setPrice(param.getPrice());
		discountPackageSaveParam.setUseRules(param.getUseRules());
		discountPackageSaveParam.setUseTimeBegin(param.getUseTimeBegin());
		discountPackageSaveParam.setUseTimeEnd(param.getUseTimeEnd());
		discountPackageSaveParam.setUseTimeWeek(param.getUseTimeWeek());
		discountPackageSaveParam.setValidityPeriodBegin(param.getValidityPeriodBegin());
		discountPackageSaveParam.setValidityPeriodEnd(param.getValidityPeriodEnd());
		discountPackageSaveParam.setDiscountPackageContents(param.getDiscountPackageContents());
		discountPackageSaveParam.setDiscountPackageImages(param.getDiscountPackageImages());
		discountPackageSaveParam.setAdvanceBookingTime(param.getAdvanceBookingTime());
		discountPackageSaveParam.setPurchaseNotes(param.getPurchaseNotes());
		Result result = discountPackageService.save(merchantId, discountPackageSaveParam);
		return successCreated(result);
	}
	
	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param discountPackageUpdateForeignParam
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Deprecated
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "更新优惠套餐[Deprecated]", notes = "更新优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public Result update(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id", required = true) @PathVariable("id") Long id, @ApiParam(value = "优惠套餐更新参数", required = true) @ModelAttribute  @Validated DiscountPackageUpdateForeignParam discountPackageUpdateForeignParam, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	
    	HttpServletRequest request = getRequest();
        Map<String, String> images = new HashMap<>();
        
        Collection<Part> parts = null;
        try {
           parts = request.getParts();
        } catch (IOException | ServletException e) {
        	logger.error("读取上传文件异常", e);
            return successCreated(ResultCode.FAIL, e.getMessage());
        }
        
        if(parts != null && StringUtils.isNotEmpty(parts.toString())) {
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_MALL_DISCOUNT_PACKAGE, part, merchantApiConfig.getImageUploadUrl());
                String flag = map.get("resultFlag");
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get("imgUrl");
                    images.put(part.getName(), imgUrl);
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }
    	
		DiscountPackageUpdateParam discountPackageUpdateParam = new DiscountPackageUpdateParam();
		discountPackageUpdateParam.setCoverImage(images.get("coverImage") != null ? images.get("coverImage") : null);
		discountPackageUpdateParam.setIsReservation(discountPackageUpdateForeignParam.getIsReservation());
		discountPackageUpdateParam.setName(discountPackageUpdateForeignParam.getName());
		discountPackageUpdateParam.setOtherInstructions(discountPackageUpdateForeignParam.getOtherInstructions());
		discountPackageUpdateParam.setPrice(discountPackageUpdateForeignParam.getPrice());
		discountPackageUpdateParam.setUseRules(discountPackageUpdateForeignParam.getUseRules());
		discountPackageUpdateParam.setUseTimeBegin(discountPackageUpdateForeignParam.getUseTimeBegin());
		discountPackageUpdateParam.setUseTimeEnd(discountPackageUpdateForeignParam.getUseTimeEnd());
		discountPackageUpdateParam.setUseTimeWeek(discountPackageUpdateForeignParam.getUseTimeWeek());
		discountPackageUpdateParam.setValidityPeriodBegin(discountPackageUpdateForeignParam.getValidityPeriodBegin());
		discountPackageUpdateParam.setValidityPeriodEnd(discountPackageUpdateForeignParam.getValidityPeriodEnd());
		discountPackageUpdateParam.setStatus(discountPackageUpdateForeignParam.getStatus());
		discountPackageUpdateParam.setAdvanceBookingTime(discountPackageUpdateForeignParam.getAdvanceBookingTime());
		discountPackageUpdateParam.setPurchaseNotes(discountPackageUpdateForeignParam.getPurchaseNotes());
		String discountPackageContentsStr = null;
		String discountPackageImagesStr = null;
		try {
			discountPackageContentsStr = URLDecoder.decode(discountPackageUpdateForeignParam.getDiscountPackageContents(), "UTF-8");
			discountPackageImagesStr = URLDecoder.decode(discountPackageUpdateForeignParam.getDiscountPackageImages(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("解码失败", e);
			return successCreated(ResultCode.FAIL, e.getMessage());
		}
		discountPackageUpdateParam.setDiscountPackageContents(JSONObject.parseArray(discountPackageContentsStr, DiscountPackageContentUpdateForeignParam.class));
		List<DiscountPackageImageUpdateForeignParam> discountPackageImages = JSONObject.parseArray(discountPackageImagesStr, DiscountPackageImageUpdateForeignParam.class);
		
		discountPackageUpdateParam.setDiscountPackageImages(new ArrayList<>());
		for (int i = 0; i < discountPackageImages.size(); i++) {
			DiscountPackageImageUpdateForeignParam discountPackageImage = discountPackageImages.get(i);
			DiscountPackageImageUpdateParam discountPackageImageUpdateParam = new DiscountPackageImageUpdateParam();
			discountPackageImageUpdateParam.setId(discountPackageImage.getId());
			discountPackageImageUpdateParam.setImage(images.get("discountPackageImage-"+i) != null ? images.get("discountPackageImage-"+i) : null);
			discountPackageImageUpdateParam.setDescription(discountPackageImage.getDescription());
			discountPackageUpdateParam.getDiscountPackageImages().add(discountPackageImageUpdateParam);
		};
    	
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.update(merchantId, id, discountPackageUpdateParam);
		return successCreated(result);
	}
	
	/**
	 * 更新优惠套餐
	 * 
	 * @param id
	 * @param param
	 * @return
	 * @author Sunny
	 * @date 2017年6月26日
	 */
	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "更新优惠套餐", notes = "更新优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "update/{id}", method = RequestMethod.POST)
	public Result update(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id", required = true) @PathVariable("id") Long id, @ApiParam(value = "优惠套餐更新参数", required = true) @RequestBody  @Validated DiscountPackageUpdateParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.update(merchantId, id, param);
		return successCreated(result);
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
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "删除优惠套餐", notes = "删除优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@Authorization
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public Result delete(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id") @PathVariable("id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result =  discountPackageService.delete(merchantId, id);
		if (!isSuccess(result)) {
			return successCreated(result);
		}
		return successDelete();
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
	@Deprecated
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "上架优惠套餐[Deprecated]", notes = "上架优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "up/{id}", method = RequestMethod.PUT)
	public Result up(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id") @PathVariable("id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.up(merchantId, id);
		return successCreated(result);
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
	@Deprecated
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "下架优惠套餐[Deprecated]", notes = "下架优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "down/{id}", method = RequestMethod.PUT)
	public Result down(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id") @PathVariable("id") Long id) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.down(merchantId, id);
		return successCreated(result);
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
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "批量删除优惠套餐", notes = "批量删除优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "DELETE")
	@ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
	@Authorization
	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Result delete(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id列表") @RequestBody @Validated @NotNull List<Long> idList, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.delete(merchantId, idList);
		if (!isSuccess(result)) {
			return successCreated(result);
		}
		return successDelete();
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
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "批量上架优惠套餐", notes = "批量上架优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "up", method = RequestMethod.PUT)
	public Result up(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id列表") @RequestBody @Validated @NotNull List<Long> idList, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result =	discountPackageService.up(merchantId, idList);
		return successCreated(result);
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
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "批量下架优惠套餐", notes = "批量下架优惠套餐。[1100|1024]（蒋鑫俊）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "down", method = RequestMethod.PUT)
	public Result down(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ApiParam(value = "优惠套餐id列表") @RequestBody @Validated @NotNull List<Long> idList, BindingResult bindingResult) {
		String message = validate(bindingResult);
    	if (message != null) {
    		return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
    	}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		Result result = discountPackageService.down(merchantId, idList);
		return successCreated(result);
	}
}
