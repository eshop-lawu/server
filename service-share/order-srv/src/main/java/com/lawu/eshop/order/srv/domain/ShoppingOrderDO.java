package com.lawu.eshop.order.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShoppingOrderDO implements Serializable {
    /**
     *
     * 主键
     * shopping_order.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 用户ID
     * shopping_order.member_id
     *
     * @mbg.generated
     */
    private Long memberId;

    /**
     *
     * 会员编号
     * shopping_order.member_num
     *
     * @mbg.generated
     */
    private String memberNum;

    /**
     *
     * 会员昵称
     * shopping_order.member_nickname
     *
     * @mbg.generated
     */
    private String memberNickname;

    /**
     *
     * 商家ID
     * shopping_order.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     *
     * 门店id
     * shopping_order.merchant_store_id
     *
     * @mbg.generated
     */
    private Long merchantStoreId;

    /**
     *
     * 商家门店区域（省市区id）
     * shopping_order.merchant_store_region_path
     *
     * @mbg.generated
     */
    private String merchantStoreRegionPath;

    /**
     *
     * 抢购活动id
     * shopping_order.activity_id
     *
     * @mbg.generated
     */
    private Long activityId;

    /**
     *
     * 抢购活动商品id
     * shopping_order.activity_product_id
     *
     * @mbg.generated
     */
    private Long activityProductId;

    /**
     *
     * 商家编号
     * shopping_order.merchant_num
     *
     * @mbg.generated
     */
    private String merchantNum;

    /**
     *
     * 商家名称
     * shopping_order.merchant_name
     *
     * @mbg.generated
     */
    private String merchantName;

    /**
     *
     * 收货人姓名
     * shopping_order.consignee_name
     *
     * @mbg.generated
     */
    private String consigneeName;

    /**
     *
     * 收货人地址
     * shopping_order.consignee_address
     *
     * @mbg.generated
     */
    private String consigneeAddress;

    /**
     *
     * 收货人手机号码
     * shopping_order.consignee_mobile
     *
     * @mbg.generated
     */
    private String consigneeMobile;

    /**
     *
     * 订单备注(退货理由)
     * shopping_order.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * 买家留言
     * shopping_order.message
     *
     * @mbg.generated
     */
    private String message;

    /**
     *
     * 运费
     * shopping_order.freight_price
     *
     * @mbg.generated
     */
    private BigDecimal freightPrice;

    /**
     *
     * 运费抵扣积分
     * shopping_order.freight_price_deduction_points
     *
     * @mbg.generated
     */
    private BigDecimal freightPriceDeductionPoints;

    /**
     *
     * 商品总价
     * shopping_order.commodity_total_price
     *
     * @mbg.generated
     */
    private BigDecimal commodityTotalPrice;

    /**
     *
     * 抵扣积分
     * shopping_order.deduction_points
     *
     * @mbg.generated
     */
    private BigDecimal deductionPoints;

    /**
     *
     * 积分抵扣金额
     * shopping_order.deduction_points_amount
     *
     * @mbg.generated
     */
    private BigDecimal deductionPointsAmount;

    /**
     *
     * 金额兑积分比率
     * shopping_order.exchange_rate
     *
     * @mbg.generated
     */
    private BigDecimal exchangeRate;

    /**
     *
     * 订单总价
     * shopping_order.order_total_price
     *
     * @mbg.generated
     */
    private BigDecimal orderTotalPrice;

    /**
     *
     * 实际支付给商家的金额
     * shopping_order.actual_amount
     *
     * @mbg.generated
     */
    private BigDecimal actualAmount;

    /**
     *
     * 是否计算过提成(0-没有计算过提成|1-计算过提成)
     * shopping_order.commission_status
     *
     * @mbg.generated
     */
    private Byte commissionStatus;

    /**
     *
     * 订单的总状态(0-待处理|1-待付款|2-待发货|3-待收货|4-交易成功|5-交易关闭|6-退款中)
     * shopping_order.order_status
     *
     * @mbg.generated
     */
    private Byte orderStatus;

    /**
     *
     * 状态(0删除1正常)
     * shopping_order.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * 发送提醒的次数
     * shopping_order.send_time
     *
     * @mbg.generated
     */
    private Integer sendTime;

    /**
     *
     * 购买前用户是否是商家的粉丝
     * shopping_order.is_fans
     *
     * @mbg.generated
     */
    private Boolean isFans;

    /**
     *
     * 是否支持无理由退货,0否 1是
     * shopping_order.is_no_reason_return
     *
     * @mbg.generated
     */
    private Boolean isNoReasonReturn;

    /**
     *
     * 是否自动收货(0-否|1-是)
     * shopping_order.is_automatic_receipt
     *
     * @mbg.generated
     */
    private Boolean isAutomaticReceipt;

    /**
     *
     * 当前订单是否完成(0-未完成|1-已完成)
     * shopping_order.is_done
     *
     * @mbg.generated
     */
    private Boolean isDone;

    /**
     *
     * 是否有退款项(0-没有|1-有)
     * shopping_order.is_refund_items
     *
     * @mbg.generated
     */
    private Boolean isRefundItems;

    /**
     *
     * 对应的购物车相应的id(多个id用,分隔)
     * shopping_order.shopping_cart_ids_str
     *
     * @mbg.generated
     */
    private String shoppingCartIdsStr;

    /**
     *
     * 订单编号
     * shopping_order.order_num
     *
     * @mbg.generated
     */
    private String orderNum;

    /**
     *
     * 支付方式(1-余额 2-支付宝 3-微信)
     * shopping_order.payment_method
     *
     * @mbg.generated
     */
    private Byte paymentMethod;

    /**
     *
     * 第三方支付交易号
     * shopping_order.third_number
     *
     * @mbg.generated
     */
    private String thirdNumber;

    /**
     *
     * 是否需要物流(0-不需要|1-需要)
     * shopping_order.is_needs_logistics
     *
     * @mbg.generated
     */
    private Boolean isNeedsLogistics;

    /**
     *
     * 运单编号
     * shopping_order.waybill_num
     *
     * @mbg.generated
     */
    private String waybillNum;

    /**
     *
     * 快递公司id
     * shopping_order.express_company_id
     *
     * @mbg.generated
     */
    private Integer expressCompanyId;

    /**
     *
     * 快递公司编码
     * shopping_order.express_company_code
     *
     * @mbg.generated
     */
    private String expressCompanyCode;

    /**
     *
     * 快递公司名称
     * shopping_order.express_company_name
     *
     * @mbg.generated
     */
    private String expressCompanyName;

    /**
     *
     * 计算提成的时间
     * shopping_order.gmt_commission
     *
     * @mbg.generated
     */
    private Date gmtCommission;

    /**
     *
     * 付款时间
     * shopping_order.gmt_payment
     *
     * @mbg.generated
     */
    private Date gmtPayment;

    /**
     *
     * 发货时间
     * shopping_order.gmt_transport
     *
     * @mbg.generated
     */
    private Date gmtTransport;

    /**
     *
     * 成交时间
     * shopping_order.gmt_transaction
     *
     * @mbg.generated
     */
    private Date gmtTransaction;

    /**
     *
     * 订单完成时间
     * shopping_order.gmt_done
     *
     * @mbg.generated
     */
    private Date gmtDone;

    /**
     *
     * 创建时间
     * shopping_order.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * 修改时间
     * shopping_order.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shopping_order
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.id
     *
     * @return the value of shopping_order.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.id
     *
     * @param id the value for shopping_order.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.member_id
     *
     * @return the value of shopping_order.member_id
     *
     * @mbg.generated
     */
    public Long getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.member_id
     *
     * @param memberId the value for shopping_order.member_id
     *
     * @mbg.generated
     */
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.member_num
     *
     * @return the value of shopping_order.member_num
     *
     * @mbg.generated
     */
    public String getMemberNum() {
        return memberNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.member_num
     *
     * @param memberNum the value for shopping_order.member_num
     *
     * @mbg.generated
     */
    public void setMemberNum(String memberNum) {
        this.memberNum = memberNum == null ? null : memberNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.member_nickname
     *
     * @return the value of shopping_order.member_nickname
     *
     * @mbg.generated
     */
    public String getMemberNickname() {
        return memberNickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.member_nickname
     *
     * @param memberNickname the value for shopping_order.member_nickname
     *
     * @mbg.generated
     */
    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname == null ? null : memberNickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.merchant_id
     *
     * @return the value of shopping_order.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.merchant_id
     *
     * @param merchantId the value for shopping_order.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.merchant_store_id
     *
     * @return the value of shopping_order.merchant_store_id
     *
     * @mbg.generated
     */
    public Long getMerchantStoreId() {
        return merchantStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.merchant_store_id
     *
     * @param merchantStoreId the value for shopping_order.merchant_store_id
     *
     * @mbg.generated
     */
    public void setMerchantStoreId(Long merchantStoreId) {
        this.merchantStoreId = merchantStoreId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.merchant_store_region_path
     *
     * @return the value of shopping_order.merchant_store_region_path
     *
     * @mbg.generated
     */
    public String getMerchantStoreRegionPath() {
        return merchantStoreRegionPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.merchant_store_region_path
     *
     * @param merchantStoreRegionPath the value for shopping_order.merchant_store_region_path
     *
     * @mbg.generated
     */
    public void setMerchantStoreRegionPath(String merchantStoreRegionPath) {
        this.merchantStoreRegionPath = merchantStoreRegionPath == null ? null : merchantStoreRegionPath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.activity_id
     *
     * @return the value of shopping_order.activity_id
     *
     * @mbg.generated
     */
    public Long getActivityId() {
        return activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.activity_id
     *
     * @param activityId the value for shopping_order.activity_id
     *
     * @mbg.generated
     */
    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.activity_product_id
     *
     * @return the value of shopping_order.activity_product_id
     *
     * @mbg.generated
     */
    public Long getActivityProductId() {
        return activityProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.activity_product_id
     *
     * @param activityProductId the value for shopping_order.activity_product_id
     *
     * @mbg.generated
     */
    public void setActivityProductId(Long activityProductId) {
        this.activityProductId = activityProductId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.merchant_num
     *
     * @return the value of shopping_order.merchant_num
     *
     * @mbg.generated
     */
    public String getMerchantNum() {
        return merchantNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.merchant_num
     *
     * @param merchantNum the value for shopping_order.merchant_num
     *
     * @mbg.generated
     */
    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum == null ? null : merchantNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.merchant_name
     *
     * @return the value of shopping_order.merchant_name
     *
     * @mbg.generated
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.merchant_name
     *
     * @param merchantName the value for shopping_order.merchant_name
     *
     * @mbg.generated
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName == null ? null : merchantName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.consignee_name
     *
     * @return the value of shopping_order.consignee_name
     *
     * @mbg.generated
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.consignee_name
     *
     * @param consigneeName the value for shopping_order.consignee_name
     *
     * @mbg.generated
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName == null ? null : consigneeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.consignee_address
     *
     * @return the value of shopping_order.consignee_address
     *
     * @mbg.generated
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.consignee_address
     *
     * @param consigneeAddress the value for shopping_order.consignee_address
     *
     * @mbg.generated
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress == null ? null : consigneeAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.consignee_mobile
     *
     * @return the value of shopping_order.consignee_mobile
     *
     * @mbg.generated
     */
    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.consignee_mobile
     *
     * @param consigneeMobile the value for shopping_order.consignee_mobile
     *
     * @mbg.generated
     */
    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile == null ? null : consigneeMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.remark
     *
     * @return the value of shopping_order.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.remark
     *
     * @param remark the value for shopping_order.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.message
     *
     * @return the value of shopping_order.message
     *
     * @mbg.generated
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.message
     *
     * @param message the value for shopping_order.message
     *
     * @mbg.generated
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.freight_price
     *
     * @return the value of shopping_order.freight_price
     *
     * @mbg.generated
     */
    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.freight_price
     *
     * @param freightPrice the value for shopping_order.freight_price
     *
     * @mbg.generated
     */
    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.freight_price_deduction_points
     *
     * @return the value of shopping_order.freight_price_deduction_points
     *
     * @mbg.generated
     */
    public BigDecimal getFreightPriceDeductionPoints() {
        return freightPriceDeductionPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.freight_price_deduction_points
     *
     * @param freightPriceDeductionPoints the value for shopping_order.freight_price_deduction_points
     *
     * @mbg.generated
     */
    public void setFreightPriceDeductionPoints(BigDecimal freightPriceDeductionPoints) {
        this.freightPriceDeductionPoints = freightPriceDeductionPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.commodity_total_price
     *
     * @return the value of shopping_order.commodity_total_price
     *
     * @mbg.generated
     */
    public BigDecimal getCommodityTotalPrice() {
        return commodityTotalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.commodity_total_price
     *
     * @param commodityTotalPrice the value for shopping_order.commodity_total_price
     *
     * @mbg.generated
     */
    public void setCommodityTotalPrice(BigDecimal commodityTotalPrice) {
        this.commodityTotalPrice = commodityTotalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.deduction_points
     *
     * @return the value of shopping_order.deduction_points
     *
     * @mbg.generated
     */
    public BigDecimal getDeductionPoints() {
        return deductionPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.deduction_points
     *
     * @param deductionPoints the value for shopping_order.deduction_points
     *
     * @mbg.generated
     */
    public void setDeductionPoints(BigDecimal deductionPoints) {
        this.deductionPoints = deductionPoints;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.deduction_points_amount
     *
     * @return the value of shopping_order.deduction_points_amount
     *
     * @mbg.generated
     */
    public BigDecimal getDeductionPointsAmount() {
        return deductionPointsAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.deduction_points_amount
     *
     * @param deductionPointsAmount the value for shopping_order.deduction_points_amount
     *
     * @mbg.generated
     */
    public void setDeductionPointsAmount(BigDecimal deductionPointsAmount) {
        this.deductionPointsAmount = deductionPointsAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.exchange_rate
     *
     * @return the value of shopping_order.exchange_rate
     *
     * @mbg.generated
     */
    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.exchange_rate
     *
     * @param exchangeRate the value for shopping_order.exchange_rate
     *
     * @mbg.generated
     */
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.order_total_price
     *
     * @return the value of shopping_order.order_total_price
     *
     * @mbg.generated
     */
    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.order_total_price
     *
     * @param orderTotalPrice the value for shopping_order.order_total_price
     *
     * @mbg.generated
     */
    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.actual_amount
     *
     * @return the value of shopping_order.actual_amount
     *
     * @mbg.generated
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.actual_amount
     *
     * @param actualAmount the value for shopping_order.actual_amount
     *
     * @mbg.generated
     */
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.commission_status
     *
     * @return the value of shopping_order.commission_status
     *
     * @mbg.generated
     */
    public Byte getCommissionStatus() {
        return commissionStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.commission_status
     *
     * @param commissionStatus the value for shopping_order.commission_status
     *
     * @mbg.generated
     */
    public void setCommissionStatus(Byte commissionStatus) {
        this.commissionStatus = commissionStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.order_status
     *
     * @return the value of shopping_order.order_status
     *
     * @mbg.generated
     */
    public Byte getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.order_status
     *
     * @param orderStatus the value for shopping_order.order_status
     *
     * @mbg.generated
     */
    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.status
     *
     * @return the value of shopping_order.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.status
     *
     * @param status the value for shopping_order.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.send_time
     *
     * @return the value of shopping_order.send_time
     *
     * @mbg.generated
     */
    public Integer getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.send_time
     *
     * @param sendTime the value for shopping_order.send_time
     *
     * @mbg.generated
     */
    public void setSendTime(Integer sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_fans
     *
     * @return the value of shopping_order.is_fans
     *
     * @mbg.generated
     */
    public Boolean getIsFans() {
        return isFans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_fans
     *
     * @param isFans the value for shopping_order.is_fans
     *
     * @mbg.generated
     */
    public void setIsFans(Boolean isFans) {
        this.isFans = isFans;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_no_reason_return
     *
     * @return the value of shopping_order.is_no_reason_return
     *
     * @mbg.generated
     */
    public Boolean getIsNoReasonReturn() {
        return isNoReasonReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_no_reason_return
     *
     * @param isNoReasonReturn the value for shopping_order.is_no_reason_return
     *
     * @mbg.generated
     */
    public void setIsNoReasonReturn(Boolean isNoReasonReturn) {
        this.isNoReasonReturn = isNoReasonReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_automatic_receipt
     *
     * @return the value of shopping_order.is_automatic_receipt
     *
     * @mbg.generated
     */
    public Boolean getIsAutomaticReceipt() {
        return isAutomaticReceipt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_automatic_receipt
     *
     * @param isAutomaticReceipt the value for shopping_order.is_automatic_receipt
     *
     * @mbg.generated
     */
    public void setIsAutomaticReceipt(Boolean isAutomaticReceipt) {
        this.isAutomaticReceipt = isAutomaticReceipt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_done
     *
     * @return the value of shopping_order.is_done
     *
     * @mbg.generated
     */
    public Boolean getIsDone() {
        return isDone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_done
     *
     * @param isDone the value for shopping_order.is_done
     *
     * @mbg.generated
     */
    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_refund_items
     *
     * @return the value of shopping_order.is_refund_items
     *
     * @mbg.generated
     */
    public Boolean getIsRefundItems() {
        return isRefundItems;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_refund_items
     *
     * @param isRefundItems the value for shopping_order.is_refund_items
     *
     * @mbg.generated
     */
    public void setIsRefundItems(Boolean isRefundItems) {
        this.isRefundItems = isRefundItems;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.shopping_cart_ids_str
     *
     * @return the value of shopping_order.shopping_cart_ids_str
     *
     * @mbg.generated
     */
    public String getShoppingCartIdsStr() {
        return shoppingCartIdsStr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.shopping_cart_ids_str
     *
     * @param shoppingCartIdsStr the value for shopping_order.shopping_cart_ids_str
     *
     * @mbg.generated
     */
    public void setShoppingCartIdsStr(String shoppingCartIdsStr) {
        this.shoppingCartIdsStr = shoppingCartIdsStr == null ? null : shoppingCartIdsStr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.order_num
     *
     * @return the value of shopping_order.order_num
     *
     * @mbg.generated
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.order_num
     *
     * @param orderNum the value for shopping_order.order_num
     *
     * @mbg.generated
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.payment_method
     *
     * @return the value of shopping_order.payment_method
     *
     * @mbg.generated
     */
    public Byte getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.payment_method
     *
     * @param paymentMethod the value for shopping_order.payment_method
     *
     * @mbg.generated
     */
    public void setPaymentMethod(Byte paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.third_number
     *
     * @return the value of shopping_order.third_number
     *
     * @mbg.generated
     */
    public String getThirdNumber() {
        return thirdNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.third_number
     *
     * @param thirdNumber the value for shopping_order.third_number
     *
     * @mbg.generated
     */
    public void setThirdNumber(String thirdNumber) {
        this.thirdNumber = thirdNumber == null ? null : thirdNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.is_needs_logistics
     *
     * @return the value of shopping_order.is_needs_logistics
     *
     * @mbg.generated
     */
    public Boolean getIsNeedsLogistics() {
        return isNeedsLogistics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.is_needs_logistics
     *
     * @param isNeedsLogistics the value for shopping_order.is_needs_logistics
     *
     * @mbg.generated
     */
    public void setIsNeedsLogistics(Boolean isNeedsLogistics) {
        this.isNeedsLogistics = isNeedsLogistics;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.waybill_num
     *
     * @return the value of shopping_order.waybill_num
     *
     * @mbg.generated
     */
    public String getWaybillNum() {
        return waybillNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.waybill_num
     *
     * @param waybillNum the value for shopping_order.waybill_num
     *
     * @mbg.generated
     */
    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum == null ? null : waybillNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.express_company_id
     *
     * @return the value of shopping_order.express_company_id
     *
     * @mbg.generated
     */
    public Integer getExpressCompanyId() {
        return expressCompanyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.express_company_id
     *
     * @param expressCompanyId the value for shopping_order.express_company_id
     *
     * @mbg.generated
     */
    public void setExpressCompanyId(Integer expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.express_company_code
     *
     * @return the value of shopping_order.express_company_code
     *
     * @mbg.generated
     */
    public String getExpressCompanyCode() {
        return expressCompanyCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.express_company_code
     *
     * @param expressCompanyCode the value for shopping_order.express_company_code
     *
     * @mbg.generated
     */
    public void setExpressCompanyCode(String expressCompanyCode) {
        this.expressCompanyCode = expressCompanyCode == null ? null : expressCompanyCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.express_company_name
     *
     * @return the value of shopping_order.express_company_name
     *
     * @mbg.generated
     */
    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.express_company_name
     *
     * @param expressCompanyName the value for shopping_order.express_company_name
     *
     * @mbg.generated
     */
    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName == null ? null : expressCompanyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_commission
     *
     * @return the value of shopping_order.gmt_commission
     *
     * @mbg.generated
     */
    public Date getGmtCommission() {
        return gmtCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_commission
     *
     * @param gmtCommission the value for shopping_order.gmt_commission
     *
     * @mbg.generated
     */
    public void setGmtCommission(Date gmtCommission) {
        this.gmtCommission = gmtCommission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_payment
     *
     * @return the value of shopping_order.gmt_payment
     *
     * @mbg.generated
     */
    public Date getGmtPayment() {
        return gmtPayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_payment
     *
     * @param gmtPayment the value for shopping_order.gmt_payment
     *
     * @mbg.generated
     */
    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_transport
     *
     * @return the value of shopping_order.gmt_transport
     *
     * @mbg.generated
     */
    public Date getGmtTransport() {
        return gmtTransport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_transport
     *
     * @param gmtTransport the value for shopping_order.gmt_transport
     *
     * @mbg.generated
     */
    public void setGmtTransport(Date gmtTransport) {
        this.gmtTransport = gmtTransport;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_transaction
     *
     * @return the value of shopping_order.gmt_transaction
     *
     * @mbg.generated
     */
    public Date getGmtTransaction() {
        return gmtTransaction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_transaction
     *
     * @param gmtTransaction the value for shopping_order.gmt_transaction
     *
     * @mbg.generated
     */
    public void setGmtTransaction(Date gmtTransaction) {
        this.gmtTransaction = gmtTransaction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_done
     *
     * @return the value of shopping_order.gmt_done
     *
     * @mbg.generated
     */
    public Date getGmtDone() {
        return gmtDone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_done
     *
     * @param gmtDone the value for shopping_order.gmt_done
     *
     * @mbg.generated
     */
    public void setGmtDone(Date gmtDone) {
        this.gmtDone = gmtDone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_create
     *
     * @return the value of shopping_order.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_create
     *
     * @param gmtCreate the value for shopping_order.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shopping_order.gmt_modified
     *
     * @return the value of shopping_order.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shopping_order.gmt_modified
     *
     * @param gmtModified the value for shopping_order.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}