package com.lawu.eshop.order.dto.foreign;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.order.constants.RefundStatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;
import com.lawu.eshop.order.dto.ShoppingRefundProcessDTO;

import io.swagger.annotations.ApiModelProperty;

public class ShoppingRefundDetailDTO {

    /**
     * 退款详情id
     */
    @ApiModelProperty(value = "退款详情id", required = true)
    private Long id;

    /**
     * 购物订单id
     */
    @ApiModelProperty(value = "购物订单id", required = true)
    private Long shoppingOrderId;

    /**
     * 退款类型
     */
    @ApiModelProperty(value = "退款类型(REFUND-退款|RETURN_REFUND-退货退款)", required = true)
    private ShoppingRefundTypeEnum type;

    /**
     * 退货原因
     */
    @ApiModelProperty(value = "退款原因", required = true)
    private String reason;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额", required = true)
    private BigDecimal amount;
    
    /**
     * 退还积分
     */
    @ApiModelProperty(value = "退还积分", required = true)
    private BigDecimal point;

    /**
     * 实际退款金额
     */
    @ApiModelProperty(value = "实际退款金额", required = true)
    private BigDecimal actualAmount;
    
    /**
     * 收货人姓名
     */
    @ApiModelProperty(value = "收货人姓名", required = true)
    private String consigneeName;

    /**
     * 收货人地址
     */
    @ApiModelProperty(value = "收货人地址", required = true)
    private String consigneeAddress;

    /**
     * 收货人手机号码
     */
    @ApiModelProperty(value = "收货人手机号码", required = true)
    private String consigneeMobile;

    /**
     * 快递公司名称
     */
    @ApiModelProperty(value = "快递公司名称", required = true)
    private String expressCompanyName;

    /**
     * 物流编号
     */
    @ApiModelProperty(value = "物流编号", required = true)
    private String waybillNum;

    /**
     * 商家是否同意退货申请
     */
    @ApiModelProperty(value = "商家是否同意退货申请", required = true)
    private Boolean isAgree;

    /**
     * 退款时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "退款时间", required = true)
    private Date gmtRefund;

    /**
     * 商家确认时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商家确认时间", required = true)
    private Date gmtConfirmed;

    /**
     * 商家填写退货地址时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商家填写退货地址时间", required = true)
    private Date gmtFill;

    /**
     * 买家提交退货物流时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "买家提交退货物流时间", required = true)
    private Date gmtSubmit;

    /**
     * 平台介入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "平台介入时间", required = true)
    private Date gmtIntervention;

    /**
     * 退款申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "退款申请时间", required = true)
    private Date gmtCreate;

    /**
     * 倒计时
     */
    @ApiModelProperty(value = "倒计时", required = false)
    private Long countdown;

    /**
     * 退款描述
     */
    @ApiModelProperty(value = "退款描述", required = false)
    private String describe;

    /**
     * 凭证图片
     */
    @ApiModelProperty(value = "凭证图片", required = false)
    private String voucherPicture;

    /**
     * 拒绝退款理由
     */
    @ApiModelProperty(value = "拒绝退款理由", required = false)
    private String refusalReasons;

    /**
     * 退款到账时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "退款到账时间", required = false)
    private Date gmtToAccount;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式(BALANCE-余额|WX-微信|ALIPAY-支付宝)", required = true)
    private TransactionPayTypeEnum paymentMethod;

    /**
     * 退款状态
     */
    @ApiModelProperty(value = "退款状态|TO_BE_CONFIRMED 待商家确认|FILL_RETURN_ADDRESS 填写退货地址|TO_BE_RETURNED 待退货|TO_BE_REFUNDED 待退款|REFUND_SUCCESSFULLY 退款成功|REFUND_FAILED 退款失败|PLATFORM_INTERVENTION 平台介入")
    private RefundStatusEnum refundStatus;

    /**
     * 退款流程
     */
    @ApiModelProperty(value = "退款流程", required = true)
    private List<ShoppingRefundProcessDTO> shoppingRefundProcessList;

    @ApiModelProperty(value = "拒绝退款图片")
    private String refuseImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingOrderId() {
        return shoppingOrderId;
    }

    public void setShoppingOrderId(Long shoppingOrderId) {
        this.shoppingOrderId = shoppingOrderId;
    }

    public ShoppingRefundTypeEnum getType() {
        return type;
    }

    public void setType(ShoppingRefundTypeEnum type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public Boolean getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(Boolean isAgree) {
        this.isAgree = isAgree;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public Date getGmtConfirmed() {
        return gmtConfirmed;
    }

    public void setGmtConfirmed(Date gmtConfirmed) {
        this.gmtConfirmed = gmtConfirmed;
    }

    public Date getGmtFill() {
        return gmtFill;
    }

    public void setGmtFill(Date gmtFill) {
        this.gmtFill = gmtFill;
    }

    public Date getGmtSubmit() {
        return gmtSubmit;
    }

    public void setGmtSubmit(Date gmtSubmit) {
        this.gmtSubmit = gmtSubmit;
    }

    public Date getGmtIntervention() {
        return gmtIntervention;
    }

    public void setGmtIntervention(Date gmtIntervention) {
        this.gmtIntervention = gmtIntervention;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getCountdown() {
        return countdown;
    }

    public void setCountdown(Long countdown) {
        this.countdown = countdown;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getVoucherPicture() {
        return voucherPicture;
    }

    public void setVoucherPicture(String voucherPicture) {
        this.voucherPicture = voucherPicture;
    }

    public String getRefusalReasons() {
        return refusalReasons;
    }

    public void setRefusalReasons(String refusalReasons) {
        this.refusalReasons = refusalReasons;
    }

    public Date getGmtToAccount() {
        return gmtToAccount;
    }

    public void setGmtToAccount(Date gmtToAccount) {
        this.gmtToAccount = gmtToAccount;
    }

    public TransactionPayTypeEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(TransactionPayTypeEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public RefundStatusEnum getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatusEnum refundStatus) {
        this.refundStatus = refundStatus;
    }

    public List<ShoppingRefundProcessDTO> getShoppingRefundProcessList() {
        return shoppingRefundProcessList;
    }

    public void setShoppingRefundProcessList(List<ShoppingRefundProcessDTO> shoppingRefundProcessList) {
        this.shoppingRefundProcessList = shoppingRefundProcessList;
    }

    public String getRefuseImages() {
        return refuseImages;
    }

    public void setRefuseImages(String refuseImages) {
        this.refuseImages = refuseImages;
    }
}