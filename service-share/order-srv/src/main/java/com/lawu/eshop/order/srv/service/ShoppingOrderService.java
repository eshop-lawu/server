package com.lawu.eshop.order.srv.service;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.mq.dto.order.reply.CreateOrderDeductionPointsReply;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderCreateOrderReply;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderPaymentStatusReply;
import com.lawu.eshop.mq.dto.property.ShoppingOrderPaymentNotification;
import com.lawu.eshop.order.dto.ReportRiseRateDTO;
import com.lawu.eshop.order.dto.ReportRiseRerouceDTO;
import com.lawu.eshop.order.param.ActivityProductBuyQueryParam;
import com.lawu.eshop.order.param.ReportDataParam;
import com.lawu.eshop.order.param.ShoppingOrderLogisticsInformationParam;
import com.lawu.eshop.order.param.ShoppingOrderRequestRefundParam;
import com.lawu.eshop.order.param.ShoppingOrderSettlementParam;
import com.lawu.eshop.order.param.ShoppingOrderUpdateInfomationParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMemberParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToMerchantParam;
import com.lawu.eshop.order.param.foreign.ShoppingOrderQueryForeignToOperatorParam;
import com.lawu.eshop.order.srv.bo.ShoppingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderExtendBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderIsNoOnGoingOrderBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderMoneyBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusBO;
import com.lawu.eshop.order.srv.bo.ShoppingOrderNumberOfOrderStatusForMerchantBO;
import com.lawu.eshop.order.srv.domain.ShoppingOrderDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingOrderItemExtendDO;
import com.lawu.eshop.order.srv.exception.CanNotFillInShippingLogisticsException;
import com.lawu.eshop.order.srv.exception.OrderCreationFailedException;
import com.lawu.eshop.order.srv.exception.OrderNotCanceledException;
import com.lawu.eshop.order.srv.exception.TheOrderIsBeingProcessedException;
import com.lawu.framework.core.page.Page;

/**
 * 购物订单服务接口
 *
 * @author Sunny
 * @date 2017/3/27
 */
public interface ShoppingOrderService {

    /**
     * 
     * @param params
     *            多个订单参数
     * @return 返回保存的订单id
     */
    List<Long> save(List<ShoppingOrderSettlementParam> params) throws WrongOperationException;

    /**
     * 根据会员id和查询参数分页查询订单列表
     * 
     * @param memberId
     *            会员id
     * @param param
     *            查询参数
     * @return 订单列表
     */
    Page<ShoppingOrderExtendBO> selectPageByMemberId(Long memberId, ShoppingOrderQueryForeignToMemberParam param);

    /**
     * 根据商家id和查询参数分页查询订单列表
     * 
     * @param merchantId
     *            商家id
     * @param param
     *            查询参数
     * @return
     */
    Page<ShoppingOrderExtendBO> selectPageByMerchantId(Long merchantId, ShoppingOrderQueryForeignToMerchantParam param);

    /**
     * 根据查询参数分页查询订单列表
     * 
     * @param param
     *            查询参数
     * @return
     */
    Page<ShoppingOrderExtendBO> selectPage(ShoppingOrderQueryForeignToOperatorParam param);

    /**
     * 查询订单详情
     * 
     * @param id
     *            购物订单id
     * @param memberId
     *            会员id
     * @param merchantId
     *            商家id
     * @return
     * @author jiangxinjun
     * @date 2017年7月10日
     */
    ShoppingOrderExtendBO get(Long id, Long memberId, Long merchantId);

    /**
     * 查询订单详情
     * 
     * @param id
     *            购物订单id
     * @return
     * @author jiangxinjun
     * @date 2017年7月10日
     */
    ShoppingOrderExtendBO get(Long id);

    /**
     * 根据id获取购物订单
     * 
     * @param id
     * @return
     */
    ShoppingOrderBO getShoppingOrder(Long id) throws DataNotExistException, IllegalOperationException;

    /**
     * 根据id获取购物订单
     * 
     * @param id
     *            购物订单id
     * @param memberId
     *            会员id
     * @param merchantId
     *            商家id
     * @return
     * @author jiangxinjun
     * @date 2017年7月10日
     */
    ShoppingOrderBO getShoppingOrder(Long id, Long memberId, Long merchantId)
            throws DataNotExistException, IllegalOperationException;

    /**
     * 取消购物订单
     * 
     * @param memberId
     *            会员id(可以为空,为空不处理鉴权)
     * @param id
     *            购物订单id
     * @return
     */
    void cancelOrder(Long memberId, Long id)
            throws DataNotExistException, IllegalOperationException, OrderNotCanceledException;

    /**
     * 
     * @param memberId
     *            会员id
     * @param id
     *            购物订单id
     * @author jiangxinjun
     * @date 2017年7月10日
     */
    void deleteOrder(Long memberId, Long id);

    /**
     * 支付成功之后 修改购物订单以及订单项状态为待发货
     * 
     * @param id
     *            购物订单id
     */
    void paymentSuccessful(ShoppingOrderPaymentNotification notification);

    /**
     * 确认收货之后 修改购物订单以及订单项状态为交易成功
     * 
     * @param id
     *            购物订单id
     * @param memberId
     *            会员id
     * @return
     */
    void tradingSuccess(Long id, Long memberId);

    /**
     * 确认收货之后 修改购物订单以及订单项状态为交易成功
     * 
     * @param id
     *            购物订单id
     * @param isAutomaticReceipt
     *            是否是自动收货
     * @return
     */
    void tradingSuccess(Long id, boolean isAutomaticReceipt);

    /**
     * 确认收货之后 修改购物订单以及订单项状态为交易成功
     * 
     * @param id
     *            购物订单id
     * @param memberId
     *            会员id
     * @param isAutomaticReceipt
     *            是否是自动收货
     * @return
     */
    void tradingSuccess(Long id, Long memberId, boolean isAutomaticReceipt);

    /**
     * 买家申请退款 修改订单项状态为待商家确认
     * 
     * @param shoppingOrderitemId
     *            购物订单项id
     * @param memberId
     *            会员id
     * @param param
     *            退款参数
     * @return
     */
    void requestRefund(Long shoppingOrderitemId, Long memberId, ShoppingOrderRequestRefundParam param);

    /**
     * 商家填写物流信息
     * 
     * @param id
     *            购物订单id
     * @param merchantId
     *            商家id
     * @param param
     *            物流信息参数
     * @throws DataNotExistException
     * @throws IllegalOperationException
     * @throws CanNotFillInShippingLogisticsException
     * @author jiangxinjun
     * @date 2017年7月11日
     */
    void fillLogisticsInformation(Long id, Long merchantId, ShoppingOrderLogisticsInformationParam param)
            throws DataNotExistException, IllegalOperationException, CanNotFillInShippingLogisticsException;

    /**
     * 第三方支付时获取订单原始总金额，用于调用第三方支付平台
     * 
     * @param orderIds
     * @return
     * @author Yangqh
     */
    ShoppingOrderMoneyBO selectOrderMoney(String orderIds)
            throws TheOrderIsBeingProcessedException, OrderCreationFailedException;

    /**
     * 用于第三方回调获取订单总金额
     * 
     * @param orderIds
     * @return
     * @author jiangxinjun
     * @date 2017年9月29日
     */
    ShoppingOrderMoneyBO orderMoneyForNotify(String orderIds);

    /**
     * 减少产品库存成功回调 更改订单的状态为待支付状态 删除对应的购物车记录
     * 
     * @param id
     *            购物订单id
     * @author Sunny
     */
    void minusInventorySuccess(Long id, ShoppingOrderCreateOrderReply reply);

    /**
     * 更新订单信息
     * 
     * @param id
     *            购物订单id
     * @param param
     *            查询参数
     */
    void updateInformation(Long id, ShoppingOrderUpdateInfomationParam param);

    /**
     * 根据商家的id查询商家是否有进行中的订单
     * 
     * @param merchantId
     *            商家的id
     * @return
     * @author Sunny
     */
    ShoppingOrderIsNoOnGoingOrderBO isNoOnGoingOrder(Long merchantId);

    /**
     * 根据购物订单项查询订单以及订单项
     * 
     * @param ShoppingOrderItemId
     *            购物订单项id
     * @param isAll
     *            是否查找全部的订单项
     * @return
     * @author Sunny
     */
    ShoppingOrderExtendBO getByShoppingOrderItemId(Long shoppingOrderItemId);

    /**
     * 自动提醒发货
     * 
     * @author jiangxinjun
     * @createDate 2017年11月14日
     * @updateDate 2017年11月14日
     */
    void executeAutoRemindShipments();

    /**
     * 查询各种订单状态的数量 To Member
     * 
     * @param memberId
     *            会员id
     * @return
     * @author Sunny
     */
    ShoppingOrderNumberOfOrderStatusBO numberOfOrderStartus(Long memberId);

    /**
     * 查询未计算提成订单
     * 
     * @return
     * @author Sunny
     */
    List<ShoppingOrderBO> commissionShoppingOrder(int offset, int pageSize);

    /**
     * 根据订单id更新购物订单的提成状态和提成时间
     * 
     * @param ids
     *            购物订单id集合
     * @return
     * @author Sunny
     */
    void updateCommissionStatus(List<Long> ids);

    /**
     * 查询各种订单状态的数量 To Merchant
     * 
     * @param merchantId
     *            商家id
     * @return
     * @author Sunny
     */
    ShoppingOrderNumberOfOrderStatusForMerchantBO numberOfOrderStartusByMerchant(Long merchantId);

    /**
     * 统计商家的交易数据
     * 
     * @param param
     * @return
     * @author Sunny
     */
    ReportRiseRateDTO selectByTransactionData(ReportDataParam param);

    /**
     * 粉丝数据-消费转化
     * 
     * @param param
     * @return
     * @author Sunny
     */
    List<ReportRiseRerouceDTO> fansSaleTransform(ReportDataParam param);

    /**
     * 分页查询查找符合自动取消的未付款订单 用于定时任务
     * 
     * @param currentPage
     * @param pageSize
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    List<ShoppingOrderDO> selectAutoCancelOrder(int offset, int pageSize);

    /**
     * 分页查询查找符合自动提醒即将取消的未付款订单 用于定时任务
     * 
     * @param currentPage
     * @param pageSize
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    List<ShoppingOrderDO> selectAutoRemindToBeCancelledOrder(int offset, int pageSize);

    /**
     * 执行自动提醒即将取消的未付款订单
     * 
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    void executeAutoRemindToBeCancelledOrder(ShoppingOrderDO shoppingOrderDO);

    /**
     * 分页查询符合自动评论订单 用于定时任务
     * 
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    List<ShoppingOrderItemExtendDO> selectAutoCommentOrder(int offset, int pageSize);

    /**
     * 执行自动评论订单
     * 
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    void executeAutoCommentOrder(ShoppingOrderItemExtendDO shoppingOrderItemExtendDO);

    /**
     * 分页查询订单收货之后，超过退款申请时间的订单
     * 
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    List<ShoppingOrderDO> selectAutoReleaseFrozenFundsOrder(int offset, int pageSize);

    /**
     * 执行订单收货之后，超过退款申请时间的订单
     * 
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    void executeAutoReleaseFrozenFundsOrder(ShoppingOrderDO shoppingOrderDO);

    /**
     * 分页查询符合自动收货的订单
     * 
     * @author jiangxinjun
     * @createDate 2017年11月14日
     * @updateDate 2017年11月14日
     */
    List<ShoppingOrderDO> selectAutoReceiptOrder(int currentPage, int pageSize);
    
    /**
     * 查询当前用户是否购买过抢购商品
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    Long isBuy(ActivityProductBuyQueryParam param);
    
    /**
     * 分页查询查找符合自动取消的未付款抢购活动订单 用于定时任务
     * 
     * @param currentPage
     * @param pageSize
     * @author jiangxinjun
     * @createDate 2017年11月13日
     * @updateDate 2017年11月13日
     */
    List<ShoppingOrderBO> selectCancelSeckillActivityOrder(int offset, int pageSize);
    
    /**
     * 分页查询查找符合自动提醒即将取消的未付款抢购订单 <p>
     * 用于定时任务
     * 
     * @param currentPage
     * @param pageSize
     * @author jiangxinjun
     * @createDate 2017年12月04日
     * @updateDate 2017年12月04日
     */
    List<ShoppingOrderDO> selectRemindAboutCancelSeckillActivityOrder(int offset, int pageSize);
    
    
    /**
     * 查询订单支付状态,用户支付回调回复信息
     * 
     * @param notification
     * @author jiangxinjun
     * @createDate 2018年1月12日
     * @updateDate 2018年1月12日
     */
    ShoppingOrderPaymentStatusReply paymentStatus(ShoppingOrderPaymentNotification notification);

    /**
     * 根据订单ID 查询所有订单积分抵扣金额总数
     * @param orderIds
     * @return
     */
    BigDecimal selectDeductionPointsAmount(String orderIds);
    
    /**
     * 根据扣除积分的状态,处理订单的状态
     * 
     * @param id
     * @param reply
     * @author jiangxinjun
     * @createDate 2018年3月12日
     * @updateDate 2018年3月12日
     */
    void deductionPoints(Long id, CreateOrderDeductionPointsReply reply);
}
