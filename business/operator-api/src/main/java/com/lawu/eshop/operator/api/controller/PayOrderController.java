package com.lawu.eshop.operator.api.controller;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MerchantStoreService;
import com.lawu.eshop.operator.api.service.PayOrderService;
import com.lawu.eshop.order.dto.OperatorPayOrderListDTO;
import com.lawu.eshop.order.param.OperatorPayOrderParam;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantDTO;
import com.lawu.eshop.user.dto.OperatorMerchantInfoDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyong
 * @date 2017/6/28.
 */
@Api(tags = "payOrder")
@RestController
@RequestMapping(value = "payOrder/")
public class PayOrderController extends BaseController{
    @Autowired
    private PayOrderService payOrderService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    /**
     * 运营平台查询买单列表
     * @param param
     * @return
     */

    @ApiOperation(value = "查询买单列表", notes = "查询买单列表。[1004,]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("payOrder:list")
    @PageBody
    @RequestMapping(value = "getOperatorPayOrderList", method = RequestMethod.GET)
    public Result<Page<OperatorPayOrderListDTO>> getOperatorPayOrderList(@ModelAttribute OperatorPayOrderParam param){
        if (StringUtils.isNotEmpty(param.getMerchantAccount())) {
            //查询商家Id
            Result<MerchantDTO> merchantInfo = merchantService.getMerchantByAccount(param.getMerchantAccount());
            if (merchantInfo.getModel() != null && merchantInfo.getModel().getId() != null) {
                param.setMerchantId(merchantInfo.getModel().getId());
            }
        }
        if (StringUtils.isNotEmpty(param.getMemberAccount())) {
            //查询用户Id
            Result<MemberDTO> memberInfo = memberService.getMemberByAccount(param.getMemberAccount());
            if (memberInfo.getModel() != null && memberInfo.getModel().getId() != null) {
                param.setMemberId(memberInfo.getModel().getId());
            }
        }
        Result<Page<OperatorPayOrderListDTO>> pages = payOrderService.getOperatorPayOrderList(param);
        if(pages.getModel().getRecords().isEmpty()){
            return pages;
        }else{
            for(OperatorPayOrderListDTO orderListDTO : pages.getModel().getRecords()){
                //查询商家账号，商家名称
                OperatorMerchantInfoDTO operatorMerchantInfoDTO =
                        merchantStoreService.getPayOrderMerchantInfo(orderListDTO.getMerchantId());
                if(operatorMerchantInfoDTO != null){
                    orderListDTO.setMerchantAccount(operatorMerchantInfoDTO.getAccount());
                    orderListDTO.setMerchantName(operatorMerchantInfoDTO.getName());
                }
                //查询用户账号
                if(StringUtils.isEmpty(param.getMemberAccount())){
                    orderListDTO.setMemberAccount(memberService.getMemberAccountById(orderListDTO.getMemberId()));
                }else{
                    orderListDTO.setMemberAccount(param.getMemberAccount());
                }

            }
            return pages;
        }

    }
}
