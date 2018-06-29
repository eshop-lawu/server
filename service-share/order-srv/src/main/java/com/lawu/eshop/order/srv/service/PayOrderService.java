package com.lawu.eshop.order.srv.service;

import java.util.List;

import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.dto.ShoppingOrderCommissionDTO;
import com.lawu.eshop.order.param.MerchantPayOrderListParam;
import com.lawu.eshop.order.param.OperatorPayOrderParam;
import com.lawu.eshop.order.param.PayOrderDataParam;
import com.lawu.eshop.order.param.PayOrderListParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.srv.bo.PayOrderBO;
import com.lawu.eshop.order.srv.bo.PayOrderBaseBO;
import com.lawu.eshop.order.srv.bo.ThirdPayCallBackQueryPayOrderBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/4/11.
 */
public interface PayOrderService {
    /**
     * 增加买单记录
     * @param memberId
     * @param param
     * @return
     */
    PayOrderBO savePayOrderInfo(Long memberId, PayOrderDataParam param, String numNum);

    /**
     * 买单记录列表
     * @param memberId
     * @param param
     * @return
     */
    Page<PayOrderBO> getpayOrderList(Long memberId, PayOrderListParam param);

	/**
	 * 删除买单记录
	 *
	 * @param id 买单id
	 * @param memberId 会员id
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月11日
	 */
    void delPayOrderInfo(Long id, Long memberId);

    /**
     * 第三方支付时获取买单的实际总金额，用于调用第三方支付平台
     * @param orderId
     * @return
     * @author Yangqh
     */
    ThirdPayCallBackQueryPayOrderBO selectThirdPayCallBackPayOrder(String orderId);


	/**
	 * 查询未计算提成的买单
	 * @return
	 * @throws Exception
	 * @author yangqh
	 */
	List<ShoppingOrderCommissionDTO> selectNotCommissionOrder(int offset, int pageSize);

	/**
	 * 修改为已计算提成
	 * @param ids
	 * @return
	 * @author yangqh
	 */
	int updateCommissionStatus(List<Long> ids);

    Page<PayOrderBO> getMerchantPayOrderList(Long userId, MerchantPayOrderListParam param);

    /**
     * 获取买单记录
     *
     * @param id 买单id
     * @param memberId 会员id
     * @return
     * @author jiangxinjun
     * @date 2017年7月11日
     */
	PayOrderBO getOrderInfo(Long id, Long memberId);

    Page<PayOrderBO> getOperatorPayOrderList(OperatorPayOrderParam param);

    List<PayOrderBO> getAutoCommentPayOrderList();

    List<ReportRiseRerouceDTO> fansSaleTransformPay(ReportDataParam dparam);

    PayOrderBaseBO getPayOrderById(String id);
}
