package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.MerchantFavoredDTO;
import com.lawu.eshop.member.api.service.MerchantFavoredService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.user.dto.MerchantStoreFavorInfoDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/19.
 */
@Api(tags = "favored")
@RestController
@RequestMapping(value = "merchantFavored")
public class MerchantFavoredController extends BaseController {

    @Autowired
    private MerchantFavoredService merchantFavoredService;
    
    @Autowired
    private MerchantStoreService merchantStoreService;

    @AutoTesting
    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "会员根据商家ID查询优惠买单信息", notes = "会员根据商家ID查询优惠买单信息。[1004]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "findFavoredByMerchantId/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantFavoredDTO> findFavoredByMerchantId(@PathVariable("merchantId") Long merchantId) {
        if (merchantId == null || merchantId < 0) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Result<MerchantFavoredDTO> result = merchantFavoredService.findFavoredByMerchantId(merchantId);
        MerchantFavoredDTO merchantFavoredDTO = result.getModel();
        if(merchantFavoredDTO == null){
        	merchantFavoredDTO = new MerchantFavoredDTO();
        }
       
        Result<MerchantStoreFavorInfoDTO> stoResult = merchantStoreService.selectMerchantStoreFavor(merchantId);
        if (!isSuccess(stoResult)) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        merchantFavoredDTO.setName(stoResult.getModel().getName());
        merchantFavoredDTO.setUserNum(stoResult.getModel().getUserNum());
        merchantFavoredDTO.setStorePic(stoResult.getModel().getPicStore());
        
        return successGet(merchantFavoredDTO);
    }
}
