package com.lawu.eshop.merchant.api.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.BusinessDepositService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.property.dto.BusinessDepositDetailDTO;
import com.lawu.eshop.user.constants.BusinessDepositStatusEnum;
import com.lawu.eshop.user.constants.UploadFileTypeConstant;
import com.lawu.eshop.user.dto.MerchantAuditInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.eshop.user.param.ApplyPhysicalStoreParam;
import com.lawu.eshop.user.param.ApplyStoreParam;
import com.lawu.eshop.user.param.MerchantStoreParam;
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
 * 商家扩展信息
 * Created by Administrator on 2017/3/23.
 */
@Api(tags = "merchantStore")
@RestController
@RequestMapping(value = "merchantStore/")
public class MerchantStoreController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(MerchantStoreController.class);
    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
    private MerchantApiConfig merchantApiConfig;

    @Autowired
    private BusinessDepositService businessDepositService;


    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "根据商家门店id查询门店信息", notes = "根据商家门店id查询门店信息，成功返回门店信息。（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "findMerchantStoreInfo/{merchantStoreId}", method = RequestMethod.GET)
    public Result<MerchantStoreDTO> selectMerchantStore(@PathVariable("merchantStoreId") @ApiParam(required = true, value = "门店ID") Long merchantStoreId, @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Result<MerchantStoreDTO> result = merchantStoreService.selectMerchantStore(merchantStoreId);
        return result;
    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @Deprecated
    @ApiOperation(value = "新增门店信息", notes = "错误信息 [1012]（章勇）", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveMerchantStoreInfo", method = RequestMethod.POST)
    public Result saveMerchantStoreInfo(@ModelAttribute @ApiParam MerchantStoreParam merchantStoreParam,
                                        @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        HttpServletRequest request = getRequest();
        Long merchantId = UserUtil.getCurrentUserId(request);
        StringBuilder idCardUrls = new StringBuilder();        //身份证照
        StringBuilder storeUrls = new StringBuilder();        //门店照
        StringBuilder environmentUrls = new StringBuilder();        //店内环境照
        StringBuilder storeLogoUrls = new StringBuilder();    //店铺logo
        StringBuilder licenseUrls = new StringBuilder();        //营业执照
        StringBuilder otherUrls = new StringBuilder();
        Collection<Part> parts = null;
        try {
            parts = request.getParts();

        } catch (IOException e) {
            logger.info("stackTrace:{}",e);
            return successCreated(e.getMessage());
        } catch (ServletException ex) {
            logger.info("ServletException:{}",ex);
        }
        if (parts != null && StringUtils.isNotEmpty(parts.toString())) {
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_STORE, part, merchantApiConfig.getImageUploadUrl());
                String flag = map.get(UploadFileTypeConstant.RESULT_FLAG);
                String fileName = part.getName();
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get(UploadFileTypeConstant.IMG_URL);
                    if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_STORE) && StringUtils.isNotEmpty(imgUrl)) {
                        storeUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_ENVIRONMENT) && StringUtils.isNotEmpty(imgUrl)) {
                        environmentUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_LOGO) && StringUtils.isNotEmpty(imgUrl)) {
                        storeLogoUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_IDCARD) && StringUtils.isNotEmpty(imgUrl)) {
                        idCardUrls.append(imgUrl + ",");
                    }
                      if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_LICENCE) && StringUtils.isNotEmpty(imgUrl)) {
                        licenseUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_OTHER) && StringUtils.isNotEmpty(imgUrl)) {
                        otherUrls.append(imgUrl + ",");
                    }
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }
        //判断回显照片
        if (StringUtils.isNotEmpty(merchantStoreParam.getStoreUrl())) {
            merchantStoreParam.setStoreUrl(storeUrls + merchantStoreParam.getStoreUrl());
        } else {
            merchantStoreParam.setStoreUrl(storeUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getEnvironmentUrl())) {
            merchantStoreParam.setEnvironmentUrl(environmentUrls + merchantStoreParam.getEnvironmentUrl());
        } else {
            merchantStoreParam.setEnvironmentUrl(environmentUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getLogoUrl())) {
            merchantStoreParam.setLogoUrl(storeLogoUrls + merchantStoreParam.getLogoUrl());
        } else {
            merchantStoreParam.setLogoUrl(storeLogoUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getIdcardUrl())) {
            merchantStoreParam.setIdcardUrl(idCardUrls + merchantStoreParam.getIdcardUrl());
        } else {
            merchantStoreParam.setIdcardUrl(idCardUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getLicenseUrl())) {
            merchantStoreParam.setLicenseUrl(licenseUrls + merchantStoreParam.getLicenseUrl());
        } else {
            merchantStoreParam.setLicenseUrl(licenseUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getOtherUrl())) {
            merchantStoreParam.setOtherUrl(otherUrls + merchantStoreParam.getOtherUrl());
        } else {
            merchantStoreParam.setOtherUrl(otherUrls.toString());
        }

        return merchantStoreService.saveMerchantStoreInfo(merchantId, merchantStoreParam);


    }

    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @Deprecated
    @ApiOperation(value = "修改门店信息TO审核", notes = "错误信息 [2008]（章勇）", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveMerchantStoreAuditInfo/{merchantStoreId}", method = RequestMethod.POST)
    public Result saveMerchantStoreAuditInfo(@PathVariable("merchantStoreId") Long merchantStoreId, @ModelAttribute @ApiParam MerchantStoreParam merchantStoreParam,
                                             @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        HttpServletRequest request = getRequest();
        Long merchantId = UserUtil.getCurrentUserId(request);
        StringBuilder idCardUrls = new StringBuilder();        //身份证照
        StringBuilder storeUrls = new StringBuilder();        //门店照
        StringBuilder environmentUrls = new StringBuilder();        //店内环境照
        StringBuilder storeLogoUrls = new StringBuilder();    //店铺logo
        StringBuilder licenseUrls = new StringBuilder();        //营业执照
        StringBuilder otherUrls = new StringBuilder();

        Collection<Part> parts = null;
        try {
            parts = request.getParts();

        } catch (IOException e) {
            logger.info("IOException:{}",e);
            return successCreated(e.getMessage());
        } catch (ServletException ex) {
            logger.info("ServletException:{}","Servlet异常");
        }
        if (parts != null && StringUtils.isNotEmpty(parts.toString())) {
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_STORE, part, merchantApiConfig.getImageUploadUrl());
                String flag = map.get(UploadFileTypeConstant.RESULT_FLAG);
                String fileName = part.getName();
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get(UploadFileTypeConstant.IMG_URL);
                    if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_STORE) && StringUtils.isNotEmpty(imgUrl)) {
                        storeUrls.append(imgUrl + ",");
                    }  if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_ENVIRONMENT) && StringUtils.isNotEmpty(imgUrl)) {
                        environmentUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_LOGO) && StringUtils.isNotEmpty(imgUrl)) {
                        storeLogoUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_IDCARD) && StringUtils.isNotEmpty(imgUrl)) {
                        idCardUrls.append(imgUrl + ",");
                    }
                     if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_LICENCE) && StringUtils.isNotEmpty(imgUrl)) {
                        licenseUrls.append(imgUrl + ",");
                    }
                     if (StringUtils.isNotEmpty(imgUrl) && fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_OTHER) ) {
                        otherUrls.append(imgUrl + ",");
                    }
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }
        //判断回显照片
        if (StringUtils.isNotEmpty(merchantStoreParam.getStoreUrl())) {
            merchantStoreParam.setStoreUrl(storeUrls + merchantStoreParam.getStoreUrl());
        } else {
            merchantStoreParam.setStoreUrl(storeUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getEnvironmentUrl())) {
            merchantStoreParam.setEnvironmentUrl(environmentUrls + merchantStoreParam.getEnvironmentUrl());
        } else {
            merchantStoreParam.setEnvironmentUrl(environmentUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getLogoUrl())) {
            merchantStoreParam.setLogoUrl(storeLogoUrls + merchantStoreParam.getLogoUrl());
        } else {
            merchantStoreParam.setLogoUrl(storeLogoUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getIdcardUrl())) {
            merchantStoreParam.setIdcardUrl(idCardUrls + merchantStoreParam.getIdcardUrl());
        } else {
            merchantStoreParam.setIdcardUrl(idCardUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getLicenseUrl())) {
            merchantStoreParam.setLicenseUrl(licenseUrls + merchantStoreParam.getLicenseUrl());
        } else {
            merchantStoreParam.setLicenseUrl(licenseUrls.toString());
        }
        if (StringUtils.isNotEmpty(merchantStoreParam.getOtherUrl())) {
            merchantStoreParam.setOtherUrl(otherUrls + merchantStoreParam.getOtherUrl());
        } else {
            merchantStoreParam.setOtherUrl(otherUrls.toString());
        }

        return merchantStoreService.saveMerchantStoreAuditInfo(merchantStoreId, merchantId, merchantStoreParam);

    }

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "查询门店审核信息", notes = "查询门店审审核信息 [1012,2009]（章勇）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMerchantAuditInfo", method = RequestMethod.GET)
    Result<MerchantAuditInfoDTO> getMerchantAuditInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Result<MerchantAuditInfoDTO> result = merchantStoreService.getMerchantAuditInfo(merchantId);
        Result<BusinessDepositDetailDTO> business = businessDepositService.selectDeposit(String.valueOf(merchantId));
        if(business.getModel() != null){
            result.getModel().setDepositStatusEnum(BusinessDepositStatusEnum.getEnum(business.getModel().getBusinessDepositStatusEnum().getVal()));
        }
        return result;
    }

    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "申请实体店铺", notes = "申请实体店铺 [2008,1100]（章勇）", httpMethod = "POST")
    @Authorization
    @Deprecated
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "applyPhysicalStore", method = RequestMethod.POST)
    public Result applyPhysicalStore(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                     @ModelAttribute @ApiParam ApplyPhysicalStoreParam param){
        HttpServletRequest request = getRequest();
        Long merchantId = UserUtil.getCurrentUserId(request);
        StringBuilder storeUrls = new StringBuilder();        //门店照
        StringBuilder environmentUrls = new StringBuilder();        //店内环境照
        StringBuilder storeLogoUrls = new StringBuilder();    //店铺logo

        Collection<Part> parts = null;
        try {
            parts = request.getParts();

        } catch (IOException e) {
            logger.info("IOException {}",e.getStackTrace().toString());
            return successCreated(e.getMessage());
        } catch (ServletException ex) {
            logger.info("ServletException:{}",ex);
        }
        if (parts != null && StringUtils.isNotEmpty(parts.toString())) {
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_STORE, part, merchantApiConfig.getImageUploadUrl());
                String flag = map.get("resultFlag");
                String fileName = part.getName();
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get("imgUrl");
                    if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_STORE) &&  StringUtils.isNotEmpty(imgUrl)) {
                        storeUrls.append(imgUrl + ",");
                    }
                    if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_ENVIRONMENT) &&  StringUtils.isNotEmpty(imgUrl)) {
                        environmentUrls.append(imgUrl + ",");
                    }
                    if (fileName.contains(UploadFileTypeConstant.IMAGE_TYPE_LOGO) &&  StringUtils.isNotEmpty(imgUrl)) {
                        storeLogoUrls.append(imgUrl + ",");
                    }
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }
        ApplyStoreParam applyStoreParam = new ApplyStoreParam();
        //判断回显照片
        if (StringUtils.isNotEmpty(param.getStoreUrl())) {
            applyStoreParam.setStoreUrl(storeUrls + param.getStoreUrl());
        } else {
            applyStoreParam.setStoreUrl(storeUrls.toString());
        }
        if (StringUtils.isNotEmpty(param.getEnvironmentUrl())) {
            applyStoreParam.setEnvironmentUrl(environmentUrls + param.getEnvironmentUrl());
        } else {
            applyStoreParam.setEnvironmentUrl(environmentUrls.toString());
        }
        if (StringUtils.isNotEmpty(param.getLogoUrl())) {
            applyStoreParam.setLogoUrl(storeLogoUrls + param.getLogoUrl());
        } else {
            applyStoreParam.setLogoUrl(storeLogoUrls.toString());
        }

        return merchantStoreService.applyPhysicalStore(merchantId,applyStoreParam);
    }

    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "加入7天退货保障", notes = "加入7天退货保障 [2009]（梅述全）", httpMethod = "PUT")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "updateNoReasonReturn", method = RequestMethod.PUT)
    Result updateNoReasonReturn(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return merchantStoreService.updateNoReasonReturn(merchantId);
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "查询是否加入7天退货保障", notes = "查询是否加入7天退货保障(false--未加入，true--已加入) [1002]（梅述全）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "findIsNoReasonReturn", method = RequestMethod.GET)
    Result<Boolean> findIsNoReasonReturn(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return merchantStoreService.findIsNoReasonReturnById(merchantId);
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "新增店铺(new)", notes = "错误信息 [1012]（章勇）", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addMerchantStoreInfo", method = RequestMethod.POST)
    public Result addMerchantStoreInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                       @ModelAttribute @ApiParam MerchantStoreParam merchantStoreParam ) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return merchantStoreService.saveMerchantStoreInfo(merchantId, merchantStoreParam);
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "修改门店信息(new)", notes = "错误信息 [2008]（章勇）", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "addMerchantStoreAuditInfo/{merchantStoreId}", method = RequestMethod.POST)
    public Result addMerchantStoreAuditInfo( @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                              @PathVariable("merchantStoreId") Long merchantStoreId,
                                             @ModelAttribute @ApiParam MerchantStoreParam merchantStoreParam) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return merchantStoreService.saveMerchantStoreAuditInfo(merchantStoreId, merchantId, merchantStoreParam);
    }

}
