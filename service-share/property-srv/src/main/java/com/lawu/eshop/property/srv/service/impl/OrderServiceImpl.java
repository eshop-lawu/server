package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.OrderRefundStatusEnum;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.mq.dto.order.reply.ShoppingOrderPaymentStatusReply;
import com.lawu.eshop.pay.ThirdPayRefundParam;
import com.lawu.eshop.pay.handle.AlipayBusinessHandle;
import com.lawu.eshop.pay.handle.WxpayBusinessHandle;
import com.lawu.eshop.pay.param.BizTypeEnum;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.property.constants.FreezeBizTypeEnum;
import com.lawu.eshop.property.constants.FreezeStatusEnum;
import com.lawu.eshop.property.constants.FreezeTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyInfoDirectionEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.param.NotifyCallBackParam;
import com.lawu.eshop.property.param.OrderAutoRefundConcurrentDataParam;
import com.lawu.eshop.property.param.OrderComfirmDataParam;
import com.lawu.eshop.property.param.OrderRefundDataParam;
import com.lawu.eshop.property.param.OrderReleaseFreezeParam;
import com.lawu.eshop.property.param.OrderSysJobParam;
import com.lawu.eshop.property.param.TransactionDetailSaveDataParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.domain.FreezeDO;
import com.lawu.eshop.property.srv.domain.FreezeDOExample;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample;
import com.lawu.eshop.property.srv.domain.extend.PropertyInfoDOEiditView;
import com.lawu.eshop.property.srv.mapper.FreezeDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.PropertyInfoDOMapperExtend;
import com.lawu.eshop.property.srv.service.OrderService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;

@Service
public class OrderServiceImpl implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private PropertyInfoDOMapperExtend propertyInfoDOMapperExtend;
    @Autowired
    private FreezeDOMapper freezeDOMapper;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Autowired
    private PropertySrvConfig propertySrvConfig;

    @Autowired
    @Qualifier("payOrderTransactionMainServiceImpl")
    private TransactionMainService<Reply> payOrderTransactionMainServiceImpl;

    @Autowired
    @Qualifier("shoppingOrderPaymentTransactionMainServiceImpl")
    private TransactionMainService<ShoppingOrderPaymentStatusReply> shoppingOrderPaymentTransactionMainServiceImpl;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    @Qualifier("payOrderPaymentUpdateUserGradeTransactionMainServiceImpl")
    private TransactionMainService<Reply> payOrderPaymentUpdateUserGradeTransactionMainServiceImpl;

    @Autowired
    @Qualifier("shoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl")
    private TransactionMainService<Reply> shoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl;

    /**
     * 商品订单第三方付款后回调处理：新增会员交易记录，<更新订单状态>
     * <p>
     * 此时暂不保存商家交易明细和加余额，确认收货后处理
     *
     * @param param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doHandleOrderPayNotify(NotifyCallBackParam param) {
        boolean isPay = transactionDetailService.verifyThirdPayRepeat(param);
        if (isPay) {
            logger.info("第三方支付重复回调(判断幂等)-购物");
            return ResultCode.PROCESSED_RETURN_SUCCESS;
        }

        // 新增会员交易记录
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(param.getTitle());
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTransactionAccount(param.getBuyerLogonId());
        tdsParam.setTransactionType(MemberTransactionTypeEnum.PAY_ORDERS.getValue());
        tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam.setAmount(new BigDecimal(param.getTotalFee()));
        tdsParam.setBizId(param.getBizIds());
        tdsParam.setThirdTransactionNum(param.getTradeNo());
        tdsParam.setDirection(PropertyInfoDirectionEnum.OUT.getVal());
        tdsParam.setBizNum(param.getOutTradeNo());
        tdsParam.setRegionPath(param.getRegionPath());
        tdsParam.setTransactionDesc(MemberTransactionTypeEnum.PAY_ORDERS.getDescPrefix() + param.getTitle());
        transactionDetailService.save(tdsParam);

        //更新订单状态 发送MQ消息更新订单状态
        shoppingOrderPaymentTransactionMainServiceImpl.sendNotice(tdsParam.getId());

        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doHandlePayOrderNotify(NotifyCallBackParam param) {

        boolean isPay = transactionDetailService.verifyThirdPayRepeat(param);
        if (isPay) {
            logger.info("第三方支付重复回调(判断幂等)-买单");
            return ResultCode.PROCESSED_RETURN_SUCCESS;
        }

        // 新增会员交易明细
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(transactionDetailService.packageTitle(MemberTransactionTypeEnum.PAY, null, param.getTitle()));
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTransactionAccount(param.getBuyerLogonId());
        tdsParam.setTransactionType(MemberTransactionTypeEnum.PAY.getValue());
        tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam.setAmount(new BigDecimal(param.getTotalFee()));
        tdsParam.setDirection(PropertyInfoDirectionEnum.OUT.getVal());
        tdsParam.setBizId(param.getBizIds());
        tdsParam.setThirdTransactionNum(param.getTradeNo());
        tdsParam.setBizNum(param.getOutTradeNo());
        tdsParam.setTransactionDesc(MemberTransactionTypeEnum.PAY.getDescPrefix() + param.getTitle());
        transactionDetailService.save(tdsParam);

        // 新增商家交易明细
        TransactionDetailSaveDataParam tdsParam1 = new TransactionDetailSaveDataParam();
        tdsParam1.setTitle(transactionDetailService.packageTitle(null, MerchantTransactionTypeEnum.PAY, param.getTitleMerchant()));
        tdsParam1.setUserNum(param.getSideUserNum());
        tdsParam1.setTransactionAccount(param.getBuyerLogonId());
        tdsParam1.setTransactionType(MerchantTransactionTypeEnum.PAY.getValue());
        tdsParam1.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam1.setAmount(new BigDecimal(param.getTotalFee()));
        tdsParam1.setBizId(param.getBizIds());
        tdsParam1.setThirdTransactionNum(param.getTradeNo());
        tdsParam1.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setBizNum(param.getOutTradeNo());
        tdsParam1.setRegionPath(param.getRegionPath());
        tdsParam1.setTransactionDesc(MerchantTransactionTypeEnum.PAY.getDescPrefix() + param.getTitleMerchant());
        transactionDetailService.save(tdsParam1);

        // 加商家财产余额
        PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
        infoDoView.setUserNum(param.getSideUserNum());
        infoDoView.setBalance(new BigDecimal(param.getTotalFee()));
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);

        // 更新订单状态
        payOrderTransactionMainServiceImpl.sendNotice(tdsParam.getId());

        //发送消息给用户模块更新用户会员等级
        payOrderPaymentUpdateUserGradeTransactionMainServiceImpl.sendNotice(tdsParam.getId());

        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int comfirmDelivery(OrderComfirmDataParam param) {
            /*
             * 保证MQ事务的幂等性
		 */
        FreezeDOExample freezeDOExample = new FreezeDOExample();
        FreezeDOExample.Criteria criteria = freezeDOExample.createCriteria();
        criteria.andBizIdEqualTo(Long.valueOf(param.getBizId())).andFundTypeEqualTo(FreezeTypeEnum.PRODUCT_ORDER.getVal());
        int count = freezeDOMapper.countByExample(freezeDOExample);
        if (count > 0) {
            return ResultCode.SUCCESS;
        }

        PropertyInfoDOExample propertyDOExample = new PropertyInfoDOExample();
        propertyDOExample.createCriteria().andUserNumEqualTo(param.getUserNum());
        List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(propertyDOExample);
        if (propertyInfoList == null || propertyInfoList.isEmpty()) {
            return ResultCode.PROPERTY_INFO_NULL;
        }

        FreezeDO freezeDO = new FreezeDO();
        freezeDO.setUserNum(param.getUserNum());
        freezeDO.setMoney(new BigDecimal(param.getTotalOrderMoney()));
        freezeDO.setOriginalMoney(new BigDecimal(param.getTotalOrderMoney()));
        freezeDO.setFundType(FreezeTypeEnum.PRODUCT_ORDER.getVal());
        freezeDO.setFundBizType(FreezeBizTypeEnum.PRODUCT_ORDER_COMFIRM_DELIVERY.getVal());
        freezeDO.setBizId(Long.valueOf(param.getBizId()));
        freezeDO.setStatus(FreezeStatusEnum.FREEZE.getVal());
        freezeDO.setGmtCreate(new Date());
        String days = propertyService.getValue(PropertyType.PRODUCT_ORDER_MONEY_FREEZE_DAYS);
        freezeDO.setDays(Integer.valueOf(days));
        freezeDO.setOrderNum(param.getOrderNum());
        freezeDO.setPreviousMoney(propertyInfoList.get(0).getFreezeMoney());
        freezeDOMapper.insertSelective(freezeDO);

        PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
        infoDoView.setUserNum(param.getUserNum());
        infoDoView.setFreezeMoney(new BigDecimal(param.getTotalOrderMoney()));
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddFreeze(infoDoView);

        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doRefundScopeInside(OrderRefundDataParam param) throws Exception {

        // 商家同意订单退款（确认收货后7天内）,区分余额支付和第三方支付
        // （校验：区分是否是最后一次退款，校验冻结资金记录是否存在和数量，退款金额不能大于冻结金额）
        // 余额支付：处理商家冻结资金，新增会员交易订单退款交易记录，加会员财产余额
        // 第三方支付：处理商家冻结资金，新增会员交易订单退款交易记录，原路退回会员支付账户
        // <异步通知修改订单状态>
        if (OrderRefundStatusEnum.FINISH.getVal().equals(param.getOrderRefundStatusEnum().getVal())) {
            PropertyInfoDOExample propertyDOExample = new PropertyInfoDOExample();
            propertyDOExample.createCriteria().andUserNumEqualTo(param.getUserNum());
            List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(propertyDOExample);
            if (propertyInfoList == null || propertyInfoList.isEmpty()) {
                return ResultCode.PROPERTY_INFO_NULL;
            }

            FreezeDOExample example = new FreezeDOExample();
            example.createCriteria().andUserNumEqualTo(param.getUserNum()).andFundTypeEqualTo(FreezeTypeEnum.PRODUCT_ORDER.getVal()).andBizIdEqualTo(Long.valueOf(param.getOrderId()));
            example.setOrderByClause(" id desc ");
            List<FreezeDO> freezeDOS = freezeDOMapper.selectByExample(example);
            if (freezeDOS == null || freezeDOS.isEmpty()) {
                return ResultCode.FREEZE_NULL;
            } else {
                FreezeDO freeze = freezeDOS.get(0);
                if (freeze.getMoney().compareTo(new BigDecimal(param.getRefundExtraMoney())) < 0) {
                    return ResultCode.FREEZE_MONEY_LESS_REFUND_MONEY;
                }

                //释放历史冻结资金
                FreezeDO freezeDORelease = new FreezeDO();
                freezeDORelease.setStatus(FreezeStatusEnum.RELEASE.getVal());
                freezeDORelease.setGmtModified(new Date());
                FreezeDOExample exampleRelease = new FreezeDOExample();
                exampleRelease.createCriteria().andUserNumEqualTo(param.getUserNum()).andFundTypeEqualTo(FreezeTypeEnum.PRODUCT_ORDER.getVal()).andBizIdEqualTo(Long.valueOf(param.getOrderId()));
                freezeDOMapper.updateByExampleSelective(freezeDORelease, exampleRelease);

                FreezeDO freezeDO = new FreezeDO();
                if (param.isLast()) {
                    freezeDO.setMoney(new BigDecimal("0")); //确认收货后退款，当退到最后一个orderItem时，需讲将订单运费直接转入商家账户，冻结资金直接计入0
                    freezeDO.setStatus(FreezeStatusEnum.RELEASE.getVal());
                } else {
                    BigDecimal money = freeze.getMoney().subtract(new BigDecimal(param.getRefundExtraMoney()));
                    freezeDO.setMoney(money);
                    if (money.compareTo(BigDecimal.ZERO) == 0 || money.compareTo(BigDecimal.ZERO) == -1) {
                        freezeDO.setStatus(FreezeStatusEnum.RELEASE.getVal());
                    } else {
                        freezeDO.setStatus(FreezeStatusEnum.FREEZE.getVal());
                    }
                }
                freezeDO.setUserNum(param.getUserNum());
                freezeDO.setOriginalMoney(freeze.getOriginalMoney());
                freezeDO.setFundType(FreezeTypeEnum.PRODUCT_ORDER.getVal());
                freezeDO.setFundBizType(FreezeBizTypeEnum.PRODUCT_ORDER_MERCHANT_DOREFUND.getVal());
                freezeDO.setBizId(freeze.getBizId());
                if (param.getOrderItemIds() != null && !"".equals(param.getOrderItemIds())) {
                    freezeDO.setItemId(Long.valueOf(param.getOrderItemIds()));
                }
                freezeDO.setGmtCreate(new Date());
                freezeDO.setDays(freeze.getDays());
                freezeDO.setOrderNum(freeze.getOrderNum());
                freezeDO.setPreviousMoney(propertyInfoList.get(0).getFreezeMoney());
                freezeDOMapper.insertSelective(freezeDO);
            }

            PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
            infoDoView.setUserNum(param.getUserNum());
            infoDoView.setFreezeMoney(new BigDecimal(param.getRefundMoney()));
            infoDoView.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoMinusFreeze(infoDoView);
        }

        // 当退到最后一个orderItem时且订单已发货，需将订单运费直接转入商家账户，并直接计入冻结资金为0
        if (OrderRefundStatusEnum.IN_PROCESSING.getVal().equals(param.getOrderRefundStatusEnum().getVal()) && param.isLast() && new BigDecimal(param.getFreightMoney()).compareTo(BigDecimal.ZERO) == 1 ) {
            TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
            tdsParam.setTitle(MerchantTransactionTypeEnum.FREIGHT_REFUND.getName() + "-" + param.getOrderItemProdcutName());
            tdsParam.setUserNum(param.getUserNum());
            tdsParam.setTransactionType(MerchantTransactionTypeEnum.FREIGHT_REFUND.getValue());
            tdsParam.setTransactionAccount("");
            tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
            tdsParam.setAmount(new BigDecimal(param.getFreightMoney()));
            tdsParam.setBizId(param.getOrderItemIds());
            tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
            tdsParam.setThirdTransactionNum(param.getTradeNo() == null ? "" : param.getTradeNo());
            tdsParam.setBizNum(IdWorkerHelperImpl.generate(BizIdType.REFUND));
            tdsParam.setTransactionDesc(param.getOrderItemProdcutName());
            tdsParam.setGmtExecute(param.getGmtExecute());
            transactionDetailService.save(tdsParam);
        }

        // 新增会员订单退款交易记录
        //用户全额积分抵扣时退款不需要生成0的退款记录
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setBizNum(IdWorkerHelperImpl.generate(BizIdType.REFUND));
        if(new BigDecimal(param.getRefundMoney()).compareTo(BigDecimal.ZERO) == 1) {
            tdsParam.setTitle(transactionDetailService.packageTitle(MemberTransactionTypeEnum.REFUND_ORDERS, null, param.getOrderItemProdcutName()));
            tdsParam.setUserNum(param.getSideUserNum());
            tdsParam.setTransactionType(MemberTransactionTypeEnum.REFUND_ORDERS.getValue());
            tdsParam.setTransactionAccount("");
            tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
            tdsParam.setAmount(new BigDecimal(param.getRefundMoney()));
            tdsParam.setBizId(param.getOrderItemIds());
            tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
            tdsParam.setThirdTransactionNum(param.getTradeNo() == null ? "" : param.getTradeNo());
            tdsParam.setTransactionDesc(param.getOrderItemProdcutName());
            tdsParam.setGmtExecute(param.getGmtExecute());
            transactionDetailService.save(tdsParam);
        }

        JsonResult jsonResult = new JsonResult();
        if (TransactionPayTypeEnum.BALANCE.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
            // 加会员财产余额
            PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
            infoDoView.setUserNum(param.getSideUserNum());
            infoDoView.setBalance(new BigDecimal(param.getRefundMoney()));
            infoDoView.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);
            jsonResult.setSuccess(true);

        } else {
            ThirdPayRefundParam rparam = new ThirdPayRefundParam();
            rparam.setRefundId(tdsParam.getBizNum());
            rparam.setRefundMoney(param.getRefundMoney());
            rparam.setTradeNo(param.getTradeNo());
            if (TransactionPayTypeEnum.ALIPAY.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
                AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
                aliPayConfigParam.setAlipayRefundUrl(propertySrvConfig.getAlipayRefundUrl());
                aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdMember());
                aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
                aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianMemberPublicKey());
                aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
                aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
                aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
                AlipayBusinessHandle.refund(rparam, jsonResult, aliPayConfigParam);

            } else if (TransactionPayTypeEnum.WX.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {

                TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
                transactionDetailDOExample.createCriteria().andThirdTransactionNumEqualTo(param.getTradeNo()).andUserNumEqualTo(param.getSideUserNum());
                List<TransactionDetailDO> transactionDetailList = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);
                if (transactionDetailList == null || transactionDetailList.isEmpty()) {
                    logger.error("微信订单({})退款根据第三方订单号({})和用户编号({})查询交易明细支付总金额时返回为空！", param.getOrderId(), param.getTradeNo(), param.getSideUserNum());
                    return ResultCode.FAIL;
                }
                TransactionDetailDO transactionDetailDO = transactionDetailList.get(0);
                rparam.setTotalMoney(transactionDetailDO.getAmount().toString());

                WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
                wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppIdMember());
                wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchIdMember());
                wxPayConfigParam.setWxpayKey(propertySrvConfig.getWxpayKey());
                wxPayConfigParam.setWxpayAppId(propertySrvConfig.getWxpayAppId());
                wxPayConfigParam.setWxpayMchId(propertySrvConfig.getWxpayMchId());
                wxPayConfigParam.setWxpayCertLocalPathMember(propertySrvConfig.getWxpayCertLocalPathMember());
                wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordMember());
                wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathMember());
                wxPayConfigParam.setWxpayRefundApi(propertySrvConfig.getWxpayRefundApi());
                wxPayConfigParam.setWxpayHttpsRequestClassName(propertySrvConfig.getWxpayHttpsRequestClassName());
                wxPayConfigParam.setWxpayKeyApp(propertySrvConfig.getWxpayKeyApp());
                wxPayConfigParam.setBizTypeEnum(BizTypeEnum.REFUND);
                WxpayBusinessHandle.refund(rparam, jsonResult, wxPayConfigParam);
            }
        }

        if (jsonResult.isSuccess()) {
            // 更新订单item状态---目前是先修改order-item状态后处理资产
        } else {
            throw new RuntimeException(jsonResult.getMessage());
        }
        return ResultCode.SUCCESS;
    }

    /**
     * 重复回调到业务层判断已支付时，自动退款给用户
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int autoRefundConcurrent(OrderAutoRefundConcurrentDataParam param) throws Exception {
        String refundMoney = transactionDetailService.getAmountByThirdNumber(param.getTradeNo());
        // 新增会员订单退款交易记录
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(transactionDetailService.packageTitle(MemberTransactionTypeEnum.REFUND_ORDERS, null, param.getOrderItemProdcutName()));
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTransactionType(MemberTransactionTypeEnum.REFUND_ORDERS.getValue());
        tdsParam.setTransactionAccount("");
        tdsParam.setTransactionAccountType(param.getTransactionPayTypeEnum().getVal());
        tdsParam.setAmount(new BigDecimal(refundMoney));
        tdsParam.setBizId(param.getBizId());
        tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setThirdTransactionNum(param.getTradeNo() == null ? "" : param.getTradeNo());
        tdsParam.setBizNum(IdWorkerHelperImpl.generate(BizIdType.REFUND));
        tdsParam.setTransactionDesc(param.getOrderItemProdcutName());
        tdsParam.setRemark("购物支付重复回调MQ延时问题导致支付多次，系统自动退款给用户！");
        transactionDetailService.save(tdsParam);

        JsonResult jsonResult = new JsonResult();
        if (TransactionPayTypeEnum.BALANCE.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
            // 加会员财产余额
            PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
            infoDoView.setUserNum(param.getUserNum());
            infoDoView.setBalance(new BigDecimal(refundMoney));
            infoDoView.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);
            jsonResult.setSuccess(true);
        } else {
            ThirdPayRefundParam rparam = new ThirdPayRefundParam();
            rparam.setRefundId(tdsParam.getBizNum());
            rparam.setRefundMoney(refundMoney);
            rparam.setTradeNo(param.getTradeNo());
            if (TransactionPayTypeEnum.ALIPAY.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
                AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
                aliPayConfigParam.setAlipayRefundUrl(propertySrvConfig.getAlipayRefundUrl());
                aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdMember());
                aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
                aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianMemberPublicKey());
                aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
                aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());
                aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
                AlipayBusinessHandle.refund(rparam, jsonResult, aliPayConfigParam);

            } else if (TransactionPayTypeEnum.WX.getVal().equals(param.getTransactionPayTypeEnum().getVal())) {
                rparam.setTotalMoney(refundMoney);
                WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
                wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppIdMember());
                wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchIdMember());
                wxPayConfigParam.setWxpayKey(propertySrvConfig.getWxpayKey());
                wxPayConfigParam.setWxpayAppId(propertySrvConfig.getWxpayAppId());
                wxPayConfigParam.setWxpayMchId(propertySrvConfig.getWxpayMchId());
                wxPayConfigParam.setWxpayCertLocalPathMember(propertySrvConfig.getWxpayCertLocalPathMember());
                wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordMember());
                wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathMember());
                wxPayConfigParam.setWxpayRefundApi(propertySrvConfig.getWxpayRefundApi());
                wxPayConfigParam.setWxpayHttpsRequestClassName(propertySrvConfig.getWxpayHttpsRequestClassName());
                wxPayConfigParam.setWxpayKeyApp(propertySrvConfig.getWxpayKeyApp());
                wxPayConfigParam.setBizTypeEnum(BizTypeEnum.REFUND);
                WxpayBusinessHandle.refund(rparam, jsonResult, wxPayConfigParam);
            }
        }
        if (!jsonResult.isSuccess()) {
            logger.error("重复回调到业务srvcent判断已支付时，自动退款给用户失败！" + jsonResult.getMessage());
            throw new RuntimeException(jsonResult.getMessage());
        }
        return ResultCode.SUCCESS;
    }

    /**
     * 定时任务调用 确认收货后7天，订单冻结金额自动加入商家余额账户 :新增商家订单付款交易记录，释放冻结资金，加商家财产余额
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int comfirmReleaseFreeze(OrderReleaseFreezeParam param) {
        String userNums = param.getUserNums();
        String orderIds = param.getOrderIds();
        String regionPaths = param.getRegionPaths();
        String productNames = param.getOrderItemProductName();
        Byte payWays = param.getPayWays();
        String memberNum = param.getMemberNum();

        FreezeDOExample example = new FreezeDOExample();
        example.createCriteria().andUserNumEqualTo(userNums).andFundTypeEqualTo(FreezeTypeEnum.PRODUCT_ORDER.getVal())
                .andBizIdEqualTo(Long.valueOf(orderIds)).andStatusEqualTo(FreezeStatusEnum.FREEZE.getVal());
        example.setOrderByClause(" id desc ");
        List<FreezeDO> freezeDOS = freezeDOMapper.selectByExample(example);
        if (freezeDOS != null && !freezeDOS.isEmpty() && freezeDOS.size() == 1) {
            FreezeDO freeze = freezeDOS.get(0);
            Long freezeId = freeze.getId();

            // 新增商家订单付款交易记录
            TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
            tdsParam.setTitle(productNames);
            tdsParam.setUserNum(userNums);
            tdsParam.setTransactionType(MerchantTransactionTypeEnum.ORDER.getValue());
            tdsParam.setTransactionAccountType(payWays);
            tdsParam.setAmount(freeze.getMoney());
            tdsParam.setBizId(orderIds);
            tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
            tdsParam.setRegionPath(regionPaths);
            tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.ORDER.getDescPrefix() + productNames);
            tdsParam.setRemark(memberNum);
            tdsParam.setGmtExecute(param.getGmtExecute());
            transactionDetailService.save(tdsParam);

            // 释放冻结资金
            FreezeDO freezeDO = new FreezeDO();
            freezeDO.setId(freezeId);
            freezeDO.setStatus(FreezeStatusEnum.RELEASE.getVal());
            freezeDO.setGmtModified(new Date());
            freezeDO.setRemark("Release by Timed task!");
            freezeDOMapper.updateByPrimaryKeySelective(freezeDO);

            // 加商家财产余额，减冻结资金
            PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
            infoDoView.setUserNum(userNums);
            infoDoView.setBalance(freeze.getMoney());
            infoDoView.setFreezeMoney(freeze.getMoney());
            infoDoView.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoAddBalanceMinusFreeze(infoDoView);

            //发消息更新用户等级
            shoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl.sendNotice(tdsParam.getId());
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int comfirmSysJob(OrderSysJobParam param) {
        String userNums = param.getUserNums();
        String orderIds = param.getOrderIds();
        String regionPaths = param.getRegionPaths();
        String productNames = param.getOrderItemProductName();
        Byte payWays = param.getPayWays();
        String orderActualMoneys = param.getOrderActualMoney();

        // 新增商家订单付款交易记录
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTitle(productNames);
        tdsParam.setUserNum(userNums);
        tdsParam.setTransactionType(MerchantTransactionTypeEnum.ORDER.getValue());
        tdsParam.setTransactionAccountType(payWays);
        tdsParam.setAmount(new BigDecimal(orderActualMoneys));
        tdsParam.setBizId(orderIds);
        tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setRegionPath(regionPaths);
        tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.ORDER.getDescPrefix() + productNames);
        tdsParam.setRemark(param.getMemberNum());
        tdsParam.setGmtExecute(param.getGmtExecute());
        transactionDetailService.save(tdsParam);

        // 加商家财产余额
        PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
        infoDoView.setUserNum(userNums);
        infoDoView.setBalance(new BigDecimal(orderActualMoneys));
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);

        //发消息更新用户等级
        shoppingOrderPaymentUpdateUserGradeTransactionMainServiceImpl.sendNotice(tdsParam.getId());

        return ResultCode.SUCCESS;
    }

}
