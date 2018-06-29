package com.lawu.eshop.mall.param;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author zhangyong
 * @date 2017/5/4.
 */
public class MessageTempParam {

    /**
     * {0}用户昵称、{1}订单编号、{2}运单编号、{3}余额、{4}充值金额、{5}当前积分、{6}消费金额
     * {7}优惠金额、{8}退款编号、{9}商品名称、{10}收益金额、{11}收益积分、{12}商家名称
     * {13}广告名称、{14}门店名称、{15}消费积分、{16}充值编号、{17}广告类型名称、{18}失败原因
     * {19}代发货商品数量、{20}快递名称、{21}退款金额、{22}订单金额、{23}工单问题、{24}回复工单的内容
     * {25}充值积分、{26}活动名称、{27}推荐E友奖励金、{28}实际退款金额、{29}退还积分、{30}分享的用户昵称、{31}游戏类型名称
     */
    @ApiModelProperty(value = "用户昵称{0}")
    private String userName;

    @ApiModelProperty(value = "订单编号{1}")
    private String orderNum;

    @ApiModelProperty(value = "运单编号{2}")
    private String waybillNum;

    @ApiModelProperty(value = "余额{3}")
    private BigDecimal balance;

    @ApiModelProperty(value = "充值金额{4}")
    private BigDecimal rechargeBalance;

    @ApiModelProperty(value = "当前积分{5}")
    private BigDecimal point;

    @ApiModelProperty(value = "消费金额{6}")
    private BigDecimal expendAmount;

    @ApiModelProperty(value = "优惠金额{7}")
    private BigDecimal favoredAmount;

    @ApiModelProperty(value = "退款编号{8}")
    private String refundNum;

    @ApiModelProperty(value = "商品名称{9}")
    private String productName;

    @ApiModelProperty(value = "收益金额{10}")
    private BigDecimal earningAmount;

    @ApiModelProperty(value = "收益积分{11}")
    private BigDecimal earningPoint;

    @ApiModelProperty(value = "商家名称{12}")
    private String merchantName;

    @ApiModelProperty(value = "广告名称{13}")
    private String adName;

    @ApiModelProperty(value = "门店名称{14}")
    private String storeName;

    @ApiModelProperty(value = "消费积分{15}")
    private BigDecimal expendPoint;

    @ApiModelProperty(value = "充值编号{16}")
    private String rechargeNum;

    @ApiModelProperty(value = "广告类型名称{17}")
    private String adTypeName;

    @ApiModelProperty(value = "失败原因{18}")
    private String failReason;

    @ApiModelProperty(value = "代发货商品数量{19}")
    private Integer productCount;

    @ApiModelProperty(value = "快递名称{20}")
    private String expressCompanyName;

    @ApiModelProperty(value = "退款金额{21}")
    private BigDecimal refundAmount;

    @ApiModelProperty(value = "订单金额{22}")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "工单问题{23}")
    private String workOrderContent;

    @ApiModelProperty(value = "回复工单的内容{24}")
    private String replyWorkOrderContent;

    @ApiModelProperty(value = "充值积分{25}")
    private BigDecimal rechargePoint;

    @ApiModelProperty(value = "活动名称{26}")
    private String activityName;

    @ApiModelProperty(value = "推荐E友奖励金{27}")
    private BigDecimal inviteBounty;

    @ApiModelProperty(value = "实际退款金额{28}")
    private BigDecimal actualAmount;

    @ApiModelProperty(value = "退还积分{29}")
    private BigDecimal refundPoints;

    @ApiModelProperty(value = "分享的用户昵称{30}")
    private String shareUserName;

    @ApiModelProperty(value = "游戏类型名称{31}")
    private String gameTypeName;

    public String getWorkOrderContent() {
        return workOrderContent;
    }

    public void setWorkOrderContent(String workOrderContent) {
        this.workOrderContent = workOrderContent;
    }

    public String getReplyWorkOrderContent() {
        return replyWorkOrderContent;
    }

    public void setReplyWorkOrderContent(String replyWorkOrderContent) {
        this.replyWorkOrderContent = replyWorkOrderContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getWaybillNum() {
        return waybillNum;
    }

    public void setWaybillNum(String waybillNum) {
        this.waybillNum = waybillNum;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getRechargeBalance() {
        return rechargeBalance;
    }

    public void setRechargeBalance(BigDecimal rechargeBalance) {
        this.rechargeBalance = rechargeBalance;
    }

    public BigDecimal getPoint() {
        return point;
    }

    public void setPoint(BigDecimal point) {
        this.point = point;
    }

    public BigDecimal getExpendAmount() {
        return expendAmount;
    }

    public void setExpendAmount(BigDecimal expendAmount) {
        this.expendAmount = expendAmount;
    }

    public BigDecimal getFavoredAmount() {
        return favoredAmount;
    }

    public void setFavoredAmount(BigDecimal favoredAmount) {
        this.favoredAmount = favoredAmount;
    }

    public String getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(String refundNum) {
        this.refundNum = refundNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getEarningAmount() {
        return earningAmount;
    }

    public void setEarningAmount(BigDecimal earningAmount) {
        this.earningAmount = earningAmount;
    }

    public BigDecimal getEarningPoint() {
        return earningPoint;
    }

    public void setEarningPoint(BigDecimal earningPoint) {
        this.earningPoint = earningPoint;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public BigDecimal getExpendPoint() {
        return expendPoint;
    }

    public void setExpendPoint(BigDecimal expendPoint) {
        this.expendPoint = expendPoint;
    }

    public String getRechargeNum() {
        return rechargeNum;
    }

    public void setRechargeNum(String rechargeNum) {
        this.rechargeNum = rechargeNum;
    }

    public String getAdTypeName() {
        return adTypeName;
    }

    public void setAdTypeName(String adTypeName) {
        this.adTypeName = adTypeName;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public BigDecimal getRechargePoint() {
        return rechargePoint;
    }

    public void setRechargePoint(BigDecimal rechargePoint) {
        this.rechargePoint = rechargePoint;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public BigDecimal getInviteBounty() {
        return inviteBounty;
    }

    public void setInviteBounty(BigDecimal inviteBounty) {
        this.inviteBounty = inviteBounty;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getRefundPoints() {
        return refundPoints;
    }

    public void setRefundPoints(BigDecimal refundPoints) {
        this.refundPoints = refundPoints;
    }

    public String getShareUserName() {
        return shareUserName;
    }

    public void setShareUserName(String shareUserName) {
        this.shareUserName = shareUserName;
    }

    public String getGameTypeName() {
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName) {
        this.gameTypeName = gameTypeName;
    }
}
