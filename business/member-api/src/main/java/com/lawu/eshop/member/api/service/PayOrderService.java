package com.lawu.eshop.member.api.service;

import com.lawu.eshop.order.dto.PayOrderBaseDTO;
import com.lawu.eshop.order.param.PayOrderDataParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.order.dto.MemberPayOrderInfoDTO;
import com.lawu.eshop.order.dto.PayOrderDTO;
import com.lawu.eshop.order.dto.PayOrderIdDTO;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.PayOrderParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
@FeignClient(value = "order-srv", path = "payOrder/")
public interface PayOrderService {

    @RequestMapping(value = "savePayOrderInfo/{memberId}", method = RequestMethod.POST)
    Result<PayOrderIdDTO> savePayOrderInfo(@PathVariable("memberId") Long memberId, @ModelAttribute PayOrderDataParam param, @RequestParam("numNum") String numNum);

    @RequestMapping(value = "getpayOrderList/{memberId}", method = RequestMethod.POST)
    Result<Page<PayOrderDTO>> getpayOrderList(@PathVariable("memberId") Long memberId, @ModelAttribute PayOrderListParam param);


    /**
     * 删除买单记录
     *
     * @param id       买单id
     * @param memberId 会员id
     * @return
     * @author jiangxinjun
     * @date 2017年7月11日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "delPayOrderInfo/{id}", method = RequestMethod.PUT)
    Result delPayOrderInfo(@PathVariable("id") Long id, @RequestParam("memberId") Long memberId);

    /**
     * 第三方支付时获取买单的实际总金额，用于调用第三方支付平台
     *
     * @param orderId
     * @return
     * @author Yangqh
     */
    @RequestMapping(method = RequestMethod.GET, value = "selectThirdPayCallBackQueryPayOrder")
    ThirdPayCallBackQueryPayOrderDTO selectThirdPayCallBackQueryPayOrder(@RequestParam("orderId") String orderId);

    /**
     * 用户买单详情
     *
     * @param id       买单id
     * @param memberId 会员id
     * @return
     * @author jiangxinjun
     * @date 2017年7月11日
     */
    @RequestMapping(value = "getOrderInfo", method = RequestMethod.GET)
    Result<MemberPayOrderInfoDTO> getOrderInfo(@RequestParam("id") Long id, @RequestParam("memberId") Long memberId);


    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getPayOrderById", method = RequestMethod.GET)
    PayOrderBaseDTO getPayOrderById(@RequestParam("id")String id);
}
