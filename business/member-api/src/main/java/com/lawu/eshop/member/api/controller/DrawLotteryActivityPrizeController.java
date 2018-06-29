package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.activity.dto.DrawLotteryActivityPrizeDetailDTO;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.DrawLotteryActivityPrizeService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
@Api(tags = "drawLotteryActivityPrize")
@RestController
@RequestMapping(value = "drawLotteryActivityPrize/")
public class DrawLotteryActivityPrizeController extends BaseController {

    @Autowired
    private DrawLotteryActivityPrizeService drawLotteryActivityPrizeService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "奖品详情", notes = "奖品详情。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getPrizeDetail/{id}", method = RequestMethod.GET)
    public Result<DrawLotteryActivityPrizeDetailDTO> getPrizeDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                    @PathVariable @ApiParam(required = true, value = "奖品ID") Long id) {
        Result<DrawLotteryActivityPrizeDetailDTO> result = drawLotteryActivityPrizeService.getPrizeDetail(id);
        DrawLotteryActivityPrizeDetailDTO detailDTO = result.getModel();
        if (detailDTO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        Result<MerchantStoreDTO> storeDTOResult = merchantStoreService.getMerchantStoreById(detailDTO.getMerchantStoreId());
        if (isSuccess(storeDTOResult)) {
            detailDTO.setStoreName(storeDTOResult.getModel().getName());
        }
        String storeLogo = merchantStoreService.getLogoUrlByStoreId(detailDTO.getMerchantStoreId());
        detailDTO.setStoreLogo(storeLogo);
        return successGet(detailDTO);
    }

}
