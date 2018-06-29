package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.mall.param.MerchantFavoredParam;
import com.lawu.eshop.merchant.api.service.MerchantFavoredService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyong
 * @date 2017/4/10.
 */
@Api(tags = "merchantFavored")
@RestController
@RequestMapping(value = "merchantFavored/")
public class MerchantFavoredController extends BaseController {
    @Autowired
    private MerchantFavoredService merchantFavoredService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "新增优惠变更配置", notes = "新增优惠变更配置 [1004,1005,1000，1012] 章勇", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveMerchantFavoredInfo", method = RequestMethod.POST)
    public Result saveMerchantFavoredInfo(@ModelAttribute MerchantFavoredParam param, @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Long storeId = 0L;
        Result<Long> storeIdResult = merchantStoreService.getMerchantStoreId(merchantId);
        if(isSuccess(storeIdResult)){
            storeId = storeIdResult.getModel();
        }
        Result result = merchantFavoredService.saveMerchantFavoredInfo(merchantId, storeId, param);
        return result;
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "惠变更配置查看", notes = "惠变更配置查看 [1004,1002,1000] 章勇", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "findFavoredByMerchantId", method = RequestMethod.GET)
    public Result<MerchantFavoredDTO> findFavoredByMerchantId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Result<MerchantFavoredDTO> result = merchantFavoredService.findFavoredByMerchantId(merchantId);
        return result;
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "删除优惠变更配置", notes = "删除优惠变更配置 [1004,1002,1000] 章勇", httpMethod = "DELETE")
    @Authorization
    @ApiResponse(code = HttpCode.SC_NO_CONTENT, message = "success")
    @RequestMapping(value = "delMerchantFavoredInfo/{id}", method = RequestMethod.DELETE)
    public Result delMerchantFavoredInfo(@PathVariable("id") Long id,@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Long storeId = 0L;
        Result<Long> storeIdResult = merchantStoreService.getMerchantStoreId(merchantId);
        if(isSuccess(storeIdResult)){
            storeId = storeIdResult.getModel();
        }
        Result result = merchantFavoredService.delMerchantFavoredInfo(id, merchantId, storeId);
        return successDelete(result);
    }

    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "更新优惠变更配置", notes = "更新优惠变更配置 [1004,1005,1000，1012] 章勇", httpMethod = "PUT")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "updateMerchantFavoredInfo", method = RequestMethod.PUT)
    public Result updateMerchantFavoredInfo(@ModelAttribute MerchantFavoredParam param,@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        Long storeId = 0L;
        Result<Long> storeIdResult = merchantStoreService.getMerchantStoreId(merchantId);
        if(isSuccess(storeIdResult)){
            storeId = storeIdResult.getModel();
        }
        Result result = merchantFavoredService.updateMerchantFavoredInfo(merchantId, storeId, param);
        return result;
    }
}
