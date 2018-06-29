package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdPlatformOperatorDTO;
import com.lawu.eshop.ad.dto.OperatorAdDTO;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.param.AdPlatformUpdateParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.AdPlatformService;
import com.lawu.eshop.operator.api.service.AdService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.ProductAuditService;
import com.lawu.eshop.operator.api.service.ProductService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.product.dto.ProductEditInfoDTO;
import com.lawu.eshop.product.dto.ProductPlatDTO;
import com.lawu.eshop.product.param.ProductParam;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.dto.MerchantStorePlatDTO;
import com.lawu.eshop.user.param.MerchantStorePlatParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：广告管理
 * @author zhangrc
 * @date 2017/04/5
 */
@Api(tags = "adPlatform")
@RestController
@RequestMapping(value = "adPlatform/")
public class AdPlatformController extends BaseController {

    @Autowired
    private AdPlatformService adPlatformService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductAuditService productAuditService;
    
    
    @Autowired
    private MerchantStoreService merchantStoreService;
    
    @Autowired
    private AdService adService;
   

    @PageBody
    @ApiOperation(value = "广告信息查询", notes = "广告信息查询[]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectList", method = RequestMethod.POST)
    @RequiresPermissions("adPlatForm:list")
    public Result<Page<AdPlatformOperatorDTO>> list(@RequestBody @ApiParam AdPlatformFindParam queryParams) {
    	if (queryParams.getTypeEnum().val == TypeEnum.TYPE_ALL.val) {
    		queryParams.setTypeEnum(null);
        }
    	if (queryParams.getPositionEnum().val == PositionEnum.TYPE_ALL.val) {
    		queryParams.setPositionEnum(null);
        }
    	Result<Page<AdPlatformOperatorDTO>> adPlatformDTOS = adPlatformService.selectList(queryParams);
        if(!isSuccess(adPlatformDTOS)){
        	return successCreated(adPlatformDTOS.getRet());
   	    }
        Page<AdPlatformOperatorDTO> page=adPlatformDTOS.getModel();
  		List<AdPlatformOperatorDTO> list= page.getRecords();
  		for (AdPlatformOperatorDTO adPlatformDTO : list) {
  			 if(adPlatformDTO.getTypeEnum() == TypeEnum.TYPE_PRODUCT && adPlatformDTO.getProductId() !=null){
  				 Result<ProductEditInfoDTO>  productRs=productAuditService.selectEditProductById(adPlatformDTO.getProductId());
  				Result<MerchantStoreDTO> result = merchantStoreService.selectMerchantStoreByMId(productRs.getModel().getMerchantId());
  	   			 if(isSuccess(productRs)){
  	   				adPlatformDTO.setRelateName(productRs.getModel().getName());
  	   			    adPlatformDTO.setMerchantName(result.getModel().getPrincipalName());
  	   			 }
  			 }
  			if(adPlatformDTO.getTypeEnum() == TypeEnum.TYPE_STORE){
  				Result<MerchantStoreDTO>  merchantRs=merchantStoreService.selectMerchantStore(adPlatformDTO.getMerchantStoreId());
  				if(isSuccess(merchantRs)){
  				  adPlatformDTO.setMerchantName(merchantRs.getModel().getPrincipalName());
  				  adPlatformDTO.setRelateName(merchantRs.getModel().getName());
  				}
  			}
  			if(adPlatformDTO.getTypeEnum() == TypeEnum.TYPE_AD){
  				Result<AdDTO>  adRs=adService.getAdById(adPlatformDTO.getAdId());
  				if(!isSuccess(adRs)){
  					return successCreated(adRs.getRet());
  				}
  				Result<MerchantStoreDTO> result = merchantStoreService.selectMerchantStoreByMId(adRs.getModel().getMerchantId());
  				adPlatformDTO.setRelateName(adRs.getModel().getTitle());
 				adPlatformDTO.setMerchantName(result.getModel().getPrincipalName());
 				if(adRs.getModel().getTypeEnum()!=AdTypeEnum.AD_TYPE_VIDEO){
 					if(!StringUtils.isEmpty(adPlatformDTO.getMediaUrl())){
 						adPlatformDTO.setMediaUrl(adPlatformDTO.getMediaUrl());
 					}else{
 						adPlatformDTO.setMediaUrl(adRs.getModel().getMediaUrl());
 					}
 					
 				}else{
 					adPlatformDTO.setMediaUrl(adRs.getModel().getVideoImgUrl());
 				}
  			}
  			 
		}
        return adPlatformDTOS;
    }

    
    @ApiOperation(value = "删除广告", notes = "删除广告[]（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.DELETE ,title = LogTitleEnum.AD_PLATE_FORM_DELETE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresPermissions("adPlatForm:del")
    @RequestMapping(value = "removeAdPlatform/{id}", method = RequestMethod.PUT)
    public Result remove(@PathVariable @ApiParam(required = true, value = "广告id") Long id,@RequestParam PositionEnum positionEnum) {
        return adPlatformService.removeAdPlatform(id,positionEnum);
    }
    
    
    @ApiOperation(value = "下架广告", notes = "下架广告[]（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_PLATE_FORM_DOWN,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresPermissions("adPlatForm:soldOut")
    @RequestMapping(value = "unShelve/{id}", method = RequestMethod.PUT)
    public Result unShelve(@PathVariable @ApiParam(required = true, value = "广告id") Long id,@RequestParam PositionEnum positionEnum) {
        return adPlatformService.unShelve(id,positionEnum);
    }

    @ApiOperation(value = "上架广告", notes = "上架广告。（梅述全）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_PLATE_FORM_UP,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("adPlatForm:putAway")
    @RequestMapping(value = "onShelve/{id}", method = RequestMethod.PUT)
    public Result onShelve(@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
        return adPlatformService.onShelve(id);
    }
    
 
    
    @ApiOperation(value = "添加广告", notes = "添加广告[1011|1015|1010]（张荣成）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.AD_PLATE_FORM_ADD)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("adPlatForm:add")
    @RequestMapping(value = "saveAdPlatform", method = RequestMethod.POST)
    public Result saveAdPlatform(@ModelAttribute @ApiParam(required = true, value = "广告信息") AdPlatformParam adPlatform) {
        return adPlatformService.saveAdPlatform(adPlatform);
    }
    

    @ApiOperation(value = "修改广告", notes = "修改广告[1011|1015|1010]（张荣成）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_PLATE_FORM_UPDATE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("adPlatForm:edit")
    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public Result update(@PathVariable @ApiParam(required = true, value = "广告id") Long id,
                         @ModelAttribute @ApiParam(required = true, value = "广告信息") AdPlatformParam adPlatform) {
        return adPlatformService.update(id, adPlatform);
    }

    
    @ApiOperation(value = "单个广告查询", notes = "单个广告查询[]（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "select/{id}", method = RequestMethod.GET)
    public Result<AdPlatformOperatorDTO> setPositon(@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
        return adPlatformService.select(id);
    }
    
    

    @PageBody
    @ApiOperation(value = "查询所有商家的商品", notes = "查询所有商家的商品  [](张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "selectProductByPlat", method = RequestMethod.GET)
    public Result<Page<ProductPlatDTO>> selectProductByPlat(@ModelAttribute @ApiParam ProductParam param) {
    	Result<Page<ProductPlatDTO>> page = productService.selectProductByPlat(param);
    	List<ProductPlatDTO> list = page.getModel().getRecords();
    	if(!list.isEmpty()){
    		for (ProductPlatDTO productPlatDTO : list) {
    			Result<MerchantStoreDTO> result = merchantStoreService.selectMerchantStoreByMId(productPlatDTO.getMerchantId());
    			if(isSuccess(result) && result.getModel() != null){
    				productPlatDTO.setPrincipalName(result.getModel().getPrincipalName());
    			}
			}
    	}
    	
        return page;
    }
    

    @PageBody
    @ApiOperation(value = "查询所有店铺", notes = "查询所有店铺  [](张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "selectAllMerchantStore", method = RequestMethod.GET)
    public Result<Page<MerchantStorePlatDTO>> selectAllMerchantStore(@ModelAttribute @ApiParam MerchantStorePlatParam param) {
        return merchantStoreService.selectAllMerchantStore(param);
    }

    @PageBody
    @ApiOperation(value = "广告列表", notes = "查询广告列表（张荣成）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectOperatorAdAll", method = RequestMethod.GET)
    public Result<Page<OperatorAdDTO>> selectOperatorAdAll(@ModelAttribute @ApiParam OperatorAdParam operatorAdParam) {
        Result<Page<OperatorAdDTO>> result = adService.selectOperatorAdAll(operatorAdParam);
        
        if(!isSuccess(result)){
        	return successCreated(result.getRet());
        }
        List<OperatorAdDTO> list = result.getModel().getRecords();
    	if(!list.isEmpty()){
    		for (OperatorAdDTO operatorAdDTO : list) {
    			Result<MerchantStoreDTO> store = merchantStoreService.selectMerchantStoreByMId(operatorAdDTO.getMerchantId());
    			if(isSuccess(store) && store.getModel() != null){
    				operatorAdDTO.setPrincipalName(store.getModel().getPrincipalName());
    			}
			}
    	}
        return result;
    }
    
    
    @ApiOperation(value = "修改排序", notes = "修改排序（张荣成）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.AD_PLATE_PRIORITY_UPDATE,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("adPlatForm:edit")
    @RequestMapping(value = "updatePriority/{id}", method = RequestMethod.PUT)
    public Result updatePriority(@PathVariable @ApiParam(required = true, value = "广告id") Long id,
                         @ModelAttribute @ApiParam(required = true, value = "广告信息") AdPlatformUpdateParam param) {
        return adPlatformService.updatePriority(id, param);
    }

}
