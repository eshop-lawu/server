package com.lawu.eshop.merchant.api.controller;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.merchant.api.service.MerchantAuditService;
import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyong
 * @date 2017/7/17.
 */
@Api(tags = "merchantAudit")
@RestController
@RequestMapping(value = "merchantAudit/")
public class MerchantAuditController extends BaseController{

    @Autowired
    private MerchantAuditService merchantAuditService;

    @Audit(date = "2017-07-18", reviewer = "章勇")
    @ApiOperation(value = "获取最新一条审核信息", notes = "获取最新一条审核信息。[2009] （章勇）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRecentMerchantAuditRecord", method = RequestMethod.GET)
    public Result<MerchantStoreAuditDTO> getRecentMerchantAuditRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
        Long userId = UserUtil.getCurrentUserId(getRequest());

        return merchantAuditService.getRecentMerchantAuditRecord(userId);
    }
}
