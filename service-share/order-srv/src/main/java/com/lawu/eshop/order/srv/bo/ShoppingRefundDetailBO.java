package com.lawu.eshop.order.srv.bo;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.order.constants.ShoppingRefundTypeEnum;

public class ShoppingRefundDetailBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 购物订单项id
     */
    private Long shoppingOrderItemId;

    /**
     * 退款类型
     */
    private ShoppingRefundTypeEnum type;

    /**
     * 退货原因
     */
    private String reason;

    /**
     * 退款描述
     */
    private String description;

    /**
     * 凭证图片
     */
    private String voucherPicture;

    /**
     * 拒绝退款理由
     */
    private String refusalReasons;

    /**
     * 退款金额
     */
    private BigDecimal amount;

    /**
     * 退还积分
     */
    private BigDecimal point;

    /**
     * 实际退款金额
     */
    private BigDecimal actualAmount;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人地址
     */
    private String consigneeAddress;

    /**
     * 收货人手机号码
     */
    private String consigneeMobile;

    /**
     * 快递公司id
     */
    private Integer expressCompanyId;

    /**
     * 快递公司编码
     */
    private String expressCompanyCode;

    /**
     * 快递公司名称
     */
    private String expressCompanyName;

    /**
     * 物流编号
     */
    private String waybillNum;

    /**
     * 状态
     */
    private StatusEnum status;

    /**
     * 商家是否同意退货申请
     */
    private Boolean isAgree;

    /**
     * 退款时间
     */
    private Date gmtRefund;

    /**
     * 商家确认时间
     */
    private Date gmtConfirmed;

    /**
     * 商家填写退货地址时间
     */
    private Date gmtFill;

    /**
     * 买家提交退货物流时间
     */
    private Date gmtSubmit;

    /**
     * 平台介入时间
     */
    private Date gmtIntervention;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    private String refuseImages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShoppingOrderItemId() {
        return shoppingOrderItemId;
    }

    public void setShoppingOrderItemId(Long shoppingOrderItemId) {
        this.shoppingOrderItemId = shoppingOrderItemId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getExpressCompanyId() {
        return expressCompanyId;
    }

    public void setExpressCompanyId(Integer expressCompanyId) {
        this.expressCompanyId = expressCompanyId;
    }

    public String getExpressCompanyCode() {
        return expressCompanyCode;
    }

    public void setExpressCompanyCode(String expressCompanyCode) {
        this.expressCompanyCode = expressCompanyCode;
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

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getRefuseImages() {
        return refuseImages;
    }

    public void setRefuseImages(String refuseImages) {
        this.refuseImages = refuseImages;
    }
}