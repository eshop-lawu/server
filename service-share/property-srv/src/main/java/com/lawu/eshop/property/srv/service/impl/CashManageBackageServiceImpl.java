package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.pay.TradeConstants;
import com.lawu.eshop.pay.WxMmpaymkttransfersParam;
import com.lawu.eshop.pay.handle.AlipayBusinessHandle;
import com.lawu.eshop.pay.handle.WxpayBusinessHandle;
import com.lawu.eshop.pay.param.AlipayToaccountTransferParam;
import com.lawu.eshop.pay.param.BizTypeEnum;
import com.lawu.eshop.pay.sdk.alipay.AliPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.base.WxPayConfigParam;
import com.lawu.eshop.pay.sdk.weixin.sdk.common.JsonResult;
import com.lawu.eshop.property.constants.*;
import com.lawu.eshop.property.param.*;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQueryBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBackageQuerySumBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashReportBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashTotalReportBO;
import com.lawu.eshop.property.srv.constans.ThirdplatformConstant;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.PayPlatformLogDO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDOExample;
import com.lawu.eshop.property.srv.domain.WithdrawCashDOExample.Criteria;
import com.lawu.eshop.property.srv.domain.extend.PropertyInfoDOEiditView;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashDOView;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashOperDOView;
import com.lawu.eshop.property.srv.domain.extend.WithdrawCashTotalReportDOView;
import com.lawu.eshop.property.srv.exception.ThirdCashTransferException;
import com.lawu.eshop.property.srv.exception.ThirdCashTransferExceptionResult;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.PayPlatformLogDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.PropertyInfoDOMapperExtend;
import com.lawu.eshop.property.srv.mapper.extend.WithdrawCashDOMapperExtend;
import com.lawu.eshop.property.srv.service.CashManageBackageService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CashManageBackageServiceImpl implements CashManageBackageService {

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;
    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;
    @Autowired
    private WithdrawCashDOMapperExtend withdrawCashDOMapperExtend;
    @Autowired
    private PropertyInfoDOMapperExtend propertyInfoDOMapperExtend;
    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private PropertySrvConfig propertySrvConfig;
    @Autowired
    private PayPlatformLogDOMapper payPlatformLogDOMapper;

    @Override
    public Page<WithdrawCashBackageQueryBO> findCashInfo(CashBackageQueryDataParam param) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();

        Criteria criteria1 = example.createCriteria();
        criteria1.andUserTypeEqualTo(param.getUserTypeEnum().getVal());
        if (StringUtils.isNotEmpty(param.getBeginDate())) {
            criteria1.andGmtCreateGreaterThanOrEqualTo(DateUtil.stringToDate(param.getBeginDate() + " 00:00:00"));
        }
        if (StringUtils.isNotEmpty(param.getEndDate())) {
            criteria1.andGmtCreateLessThanOrEqualTo(DateUtil.stringToDate(param.getEndDate() + " 23:59:59"));
        }
        if (!CashStatusEnum.ALL.getVal().equals(param.getCashStatsuEnum().getVal())) {
            criteria1.andStatusEqualTo(param.getCashStatsuEnum().getVal());
        }
        if (!BankAccountTypeEnum.ALL.getVal().equals(param.getBankAccountTypeEnum().getVal())) {
            criteria1.andChannelEqualTo(param.getBankAccountTypeEnum().getVal());
        }

        if (param.getRegionPath() != null && !"".equals(param.getRegionPath())) {
            if (param.getRegionPath().split("/").length == 1) {
                criteria1.andProvinceIdEqualTo(Integer.valueOf(param.getRegionPath().split("/")[0]));
            } else if (param.getRegionPath().split("/").length == 2) {
                criteria1.andCityIdEqualTo(Integer.valueOf(param.getRegionPath().split("/")[1]));
            } else if (param.getRegionPath().split("/").length == 3) {
                criteria1.andAreaIdEqualTo(Integer.valueOf(param.getRegionPath().split("/")[2]));
            }
        }

        if (param.getContent() != null && !"".equals(param.getContent().trim())) {
            example.clear();
            Criteria criteria2 = example.or();
            criteria2.andAccountEqualTo(param.getContent());
            criteria2.getAllCriteria().addAll(criteria1.getAllCriteria());

            Criteria criteria3 = example.or();
            criteria3.andCashNumberEqualTo(param.getContent());
            criteria3.getAllCriteria().addAll(criteria1.getAllCriteria());

            Criteria criteria4 = example.or();
            criteria4.andNameLike("%" + param.getContent() + "%");
            criteria4.getAllCriteria().addAll(criteria1.getAllCriteria());
        }


        if (StringUtils.isNotBlank(param.getSortName()) && StringUtils.isNotBlank(param.getSortOrder())) {
            String sortName = "";
            if ("gmtCreate".equals(param.getSortName())) {
                sortName = "gmt_create";
            }
            example.setOrderByClause(sortName + " " + param.getSortOrder());
        }

        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        Page<WithdrawCashBackageQueryBO> page = new Page<WithdrawCashBackageQueryBO>();
        long tatolCount = withdrawCashDOMapper.countByExample(example);
        page.setTotalCount(new Long(tatolCount).intValue());
        page.setCurrentPage(param.getCurrentPage());
        List<WithdrawCashDO> listDOS = withdrawCashDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<WithdrawCashBackageQueryBO> cbos = new ArrayList<WithdrawCashBackageQueryBO>();
        for (WithdrawCashDO cdo : listDOS) {
            WithdrawCashBackageQueryBO bqbo = new WithdrawCashBackageQueryBO();
            bqbo.setId(cdo.getId());
            bqbo.setUserNum(cdo.getUserNum());
            bqbo.setAccount(cdo.getAccount());
            bqbo.setName(cdo.getName());
            bqbo.setRegionFullName(cdo.getRegionFullName());
            bqbo.setStatus(CashStatusEnum.getEnum(cdo.getStatus()).getName());
            bqbo.setCashStatsuEnum(CashStatusEnum.getEnum(cdo.getStatus()));
            if (BankAccountTypeEnum.BANK.getVal().equals(cdo.getChannel())) {
                BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(cdo.getBusinessBankAccountId());
                bqbo.setBusinessBankAccount(bankAccountDO.getAccountName());
                bqbo.setBankNo(bankAccountDO.getAccountNumber());
                bqbo.setBankName(bankAccountDO.getNote() == null ? "" : bankAccountDO.getNote().substring(0, bankAccountDO.getNote().indexOf('(')));
                bqbo.setBankBranchName(bankAccountDO.getSubBranchName());
                bqbo.setBankRegionName(bankAccountDO.getRegionName());
            } else {
                bqbo.setBusinessBankAccount(cdo.getAccountName());
                bqbo.setBankNo(cdo.getAccountNumber());
            }

            bqbo.setCashNumber(cdo.getCashNumber());
            bqbo.setAuditUserName(cdo.getAuditUserName() == null ? "" : cdo.getAuditUserName());
            bqbo.setAuditFaildReason(cdo.getAuditFaildReason() == null ? "" : cdo.getAuditFaildReason());
            bqbo.setGmtCreate(DateUtil.getDateFormat(cdo.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
            if (cdo.getGmtModified() != null) {
                bqbo.setGmtModified(DateUtil.getDateFormat(cdo.getGmtModified(), "yyyy-MM-dd HH:mm:ss"));
            } else {
                bqbo.setGmtModified("");
            }
            bqbo.setCashMoney(cdo.getCashMoney());
//            if (CashStatusEnum.APPLY.getVal().equals(cdo.getStatus()) || CashStatusEnum.FAILURE.getVal().equals(cdo.getStatus())) {
//                bqbo.setPoundage(new BigDecimal("0"));
//                bqbo.setMoney(new BigDecimal("0"));
//            } else {
                bqbo.setPoundage(cdo.getCashMoney().subtract(cdo.getMoney()));
                bqbo.setMoney(cdo.getMoney());
//            }
            bqbo.setChannel(BankAccountTypeEnum.getEnum(cdo.getChannel()).getName());
            cbos.add(bqbo);
        }
        page.setRecords(cbos);
        return page;
    }

    @Override
    public WithdrawCashBackageQuerySumBO getTotalNum(CashBackageQuerySumParam param) {
        WithdrawCashDOView view = new WithdrawCashDOView();
        view.setUserType(param.getUserTypeEnum().getVal());
        view.setStatus(CashStatusEnum.SUCCESS.getVal());
        view = withdrawCashDOMapperExtend.getTotalNum(view);

        WithdrawCashBackageQuerySumBO bo = new WithdrawCashBackageQuerySumBO();
        bo.setSuccessMoney(view.getSuccessMoney());
        bo.setSuccessNums(view.getSuccessNums());
        return bo;
    }

    @Override
    public Page<WithdrawCashBackageQueryBO> findCashInfoDetail(CashBackageQueryDetailParam param) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();

        Criteria criteria1 = example.createCriteria();
        criteria1.andUserNumEqualTo(param.getUserNum());

        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        Page<WithdrawCashBackageQueryBO> page = new Page<WithdrawCashBackageQueryBO>();
        long tatolCount = withdrawCashDOMapper.countByExample(example);
        page.setTotalCount(new Long(tatolCount).intValue());
        page.setCurrentPage(param.getCurrentPage());
        List<WithdrawCashDO> listDOS = withdrawCashDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<WithdrawCashBackageQueryBO> cbos = new ArrayList<WithdrawCashBackageQueryBO>();
        for (WithdrawCashDO cdo : listDOS) {
            WithdrawCashBackageQueryBO bqbo = new WithdrawCashBackageQueryBO();
            bqbo.setId(cdo.getId());
            bqbo.setUserNum(cdo.getUserNum());
            bqbo.setAccount(cdo.getAccount());
            bqbo.setName(cdo.getName());
            bqbo.setRegionFullName(cdo.getRegionFullName());
            bqbo.setStatus(CashStatusEnum.getEnum(cdo.getStatus()).getName());
            bqbo.setCashStatsuEnum(CashStatusEnum.getEnum(cdo.getStatus()));
            BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(cdo.getBusinessBankAccountId());
            bqbo.setBusinessBankAccount(bankAccountDO.getAccountName());
            bqbo.setBankNo(bankAccountDO.getAccountNumber());
            bqbo.setBankName(bankAccountDO.getNote() == null ? "" : bankAccountDO.getNote().substring(0, bankAccountDO.getNote().indexOf('(')));
            bqbo.setBankBranchName(bankAccountDO.getSubBranchName());

            bqbo.setCashNumber(cdo.getCashNumber());
            bqbo.setAuditUserName(cdo.getAuditUserName() == null ? "" : cdo.getAuditUserName());
            bqbo.setAuditFaildReason(cdo.getAuditFaildReason() == null ? "" : cdo.getAuditFaildReason());
            bqbo.setGmtCreate(DateUtil.getDateFormat(cdo.getGmtCreate(), "yyyy-MM-dd HH:mm:ss"));
            if (cdo.getGmtModified() != null) {
                bqbo.setGmtModified(DateUtil.getDateFormat(cdo.getGmtModified(), "yyyy-MM-dd HH:mm:ss"));
            } else {
                bqbo.setGmtModified("");
            }
            bqbo.setMoney(cdo.getMoney());
            bqbo.setBankRegionName(bankAccountDO.getRegionName());
            bqbo.setChannel(BankAccountTypeEnum.getEnum(cdo.getChannel()).getName());
            cbos.add(bqbo);
        }
        page.setRecords(cbos);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateWithdrawCash(CashBackageOperDataParam param) {

        String id = param.getId();
//        String currentScale = propertyService.getValue(PropertyType.CASH_SCALE);
//        double dCurrentScale = Double.parseDouble(currentScale);

        //重复处理
        WithdrawCashDO wcdo = withdrawCashDOMapper.selectByPrimaryKey(Long.valueOf(id));
        if ((CashStatusEnum.ACCEPT.getVal().equals(param.getCashOperEnum().getVal()) && !CashStatusEnum.APPLY.getVal().equals(wcdo.getStatus()))
                || (!CashStatusEnum.ACCEPT.getVal().equals(param.getCashOperEnum().getVal()) && !CashStatusEnum.ACCEPT.getVal().equals(wcdo.getStatus()))) {
            return ResultCode.REPEAT_OPERATE;
        }

        List<WithdrawCashOperDOView> paramList = new ArrayList<WithdrawCashOperDOView>();
        WithdrawCashOperDOView view = new WithdrawCashOperDOView();

        //受理操作时计算手续费
//        if (CashStatusEnum.ACCEPT.getVal().equals(param.getCashOperEnum().getVal())) {
//            WithdrawCashDOExample example = new WithdrawCashDOExample();
//            example.createCriteria().andUserNumEqualTo(wcdo.getUserNum()).andStatusGreaterThanOrEqualTo(CashStatusEnum.ACCEPT.getVal()).andGmtCreateGreaterThanOrEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//            long count = withdrawCashDOMapper.countByExample(example);
//            double dCashMoney = wcdo.getCashMoney().doubleValue();
//            double money = 0;
//            if (new Long(count).intValue() > 0) {
//                String minusMoney = propertyService.getValue(PropertyType.CASH_GREATER_ONE_MINUS_MONEY);
//                money = dCashMoney * dCurrentScale - Double.parseDouble(minusMoney);
//            } else {
//                money = dCashMoney * dCurrentScale;
//            }
//            view.setCurrentScale(currentScale);
//            view.setMoney(BigDecimal.valueOf(money));
//        }

        view.setId(Integer.valueOf(id));
        view.setStatus(param.getCashOperEnum().getVal());
        view.setAuditFailReason(param.getFailReason() == null ? "" : param.getFailReason());
        view.setAuditUserId(param.getAuditUserId() == null ? 0 : param.getAuditUserId());
        view.setAuditUserName(param.getAuditUserName() == null ? "" : param.getAuditUserName());
        view.setGmtModified(new Date());
        if (CashOperEnum.ACCEPT.getVal().equals(param.getCashOperEnum().getVal())) {
            view.setGmtAccept(new Date());
        } else {
            view.setGmtFinish(new Date());
        }
        paramList.add(view);
        withdrawCashDOMapperExtend.updateBatchWithdrawCashStatus(paramList);

        if (CashStatusEnum.SUCCESS.getVal().equals(param.getCashOperEnum().getVal())) {

            if (!BankAccountTypeEnum.BANK.getVal().equals(wcdo.getChannel())) {
                BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(wcdo.getBusinessBankAccountId());
                JsonResult jsonResultQuery = new JsonResult();
                JsonResult jsonResultTransfer = new JsonResult();
                if (BankAccountTypeEnum.ALIPAY.getVal().equals(wcdo.getChannel())) {
                    AliPayConfigParam aliPayConfigParam = new AliPayConfigParam();
                    aliPayConfigParam.setAlipayGatewayUrl(propertySrvConfig.getAlipayGateway());
                    aliPayConfigParam.setAlipayAppIdMember(propertySrvConfig.getAlipayAppIdMember());
                    aliPayConfigParam.setAlipayPrivateKey(propertySrvConfig.getAlipayPrivateKey());
                    aliPayConfigParam.setAlipayEdianMemberPublicKey(propertySrvConfig.getAlipayEdianMemberPublicKey());
                    aliPayConfigParam.setAlipaySignType(propertySrvConfig.getAlipaySignType());
                    aliPayConfigParam.setAlipayInputCharset(propertySrvConfig.getAlipayInputCharset());

                    AlipayBusinessHandle.transOrderQuery(wcdo.getCashNumber(), jsonResultQuery, aliPayConfigParam);

                    //幂等处理
                    if (TradeConstants.AlipayTransOrderQuery_Code_SUCCESS.equals(jsonResultQuery.getCode())) {
                        if (TradeConstants.AlipayTransOrderQuery_STATUS_SUCCESS.equals(jsonResultQuery.getStatus())) {
                            return ResultCode.SUCCESS;
                        }
                        ThirdCashTransferException thirdCashTransferException = new ThirdCashTransferException(jsonResultQuery.getMessage());
                        thirdCashTransferException.setThirdCashTransferExceptionResult(new ThirdCashTransferExceptionResult(wcdo.getCashNumber(), BankAccountTypeEnum.getEnum(wcdo.getChannel()), jsonResultQuery.getCode(), jsonResultQuery.getSubCode(), jsonResultQuery.getStatus()));
                        throw thirdCashTransferException;

                    }
                    if (!TradeConstants.AlipayTransOrderQuery_SubCode_ORDER_NOT_EXIST.equals(jsonResultQuery.getSubCode())) {
                        ThirdCashTransferException thirdCashTransferException = new ThirdCashTransferException(jsonResultQuery.getMessage());
                        thirdCashTransferException.setThirdCashTransferExceptionResult(new ThirdCashTransferExceptionResult(wcdo.getCashNumber(), BankAccountTypeEnum.getEnum(wcdo.getChannel()), jsonResultQuery.getCode(), jsonResultQuery.getSubCode(), jsonResultQuery.getStatus()));
                        throw thirdCashTransferException;
                    }

                    AlipayToaccountTransferParam transferParam = new AlipayToaccountTransferParam();
                    transferParam.setOutBizNo(wcdo.getCashNumber());
                    transferParam.setAmount(wcdo.getMoney());
                    transferParam.setPayeeType(TradeConstants.AlipayPayeeType);
                    transferParam.setPayeeAccount(bankAccountDO.getAccountNumber());
                    transferParam.setPayeeRealName(bankAccountDO.getAccountName());
                    transferParam.setPayerShowName(ThirdplatformConstant.payerShowName);
                    transferParam.setRemark(ThirdplatformConstant.transferRemark);
                    AlipayBusinessHandle.toaccountTransfer(transferParam, jsonResultTransfer, aliPayConfigParam);

                } else if (BankAccountTypeEnum.WEIXIN.getVal().equals(wcdo.getChannel())) {
                    WxPayConfigParam wxPayConfigParam = new WxPayConfigParam();
                    if(wcdo.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
                        wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppIdMember());
                        wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchIdMember());
                        wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathMember());
                        wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordMember());
                    } else if(wcdo.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
                        wxPayConfigParam.setWxpayAppIdMember(propertySrvConfig.getWxpayAppIdBusiness());
                        wxPayConfigParam.setWxpayMchIdMember(propertySrvConfig.getWxpayMchIdBusiness());
                        wxPayConfigParam.setWxpayCertBasePath(propertySrvConfig.getWxpayCertLocalPathBusinessApp());
                        wxPayConfigParam.setWxpayCertPasswordMember(propertySrvConfig.getWxpayCertPasswordBusinessApp());
                    }
                    wxPayConfigParam.setWxpayHttpsRequestClassName(propertySrvConfig.getWxpayHttpsRequestClassName());
                    wxPayConfigParam.setWxpayKeyApp(propertySrvConfig.getWxpayKeyApp());
                    wxPayConfigParam.setWxpayMmpaymkttransfersApi(propertySrvConfig.getWxpayMmpaymkttransfersApi());
                    wxPayConfigParam.setBizTypeEnum(BizTypeEnum.MMPAY);

                    WxMmpaymkttransfersParam wxMmpaymkttransfersParam = new WxMmpaymkttransfersParam();
                    wxMmpaymkttransfersParam.setPartnerTradeNo(wcdo.getCashNumber());
                    wxMmpaymkttransfersParam.setOpenid(bankAccountDO.getAccountNumber());
                    wxMmpaymkttransfersParam.setReUserName(bankAccountDO.getAccountName());
                    wxMmpaymkttransfersParam.setAmount(wcdo.getMoney().toString());
                    wxMmpaymkttransfersParam.setDesc(ThirdplatformConstant.transferRemark);
                    try {
                        WxpayBusinessHandle.mmpaymkttransfers(wxMmpaymkttransfersParam,jsonResultTransfer,wxPayConfigParam);
                    } catch (Exception e) {
                        jsonResultTransfer.setSuccess(false);
                        jsonResultTransfer.setMessage(e.getMessage());
                        e.printStackTrace();
                    }
                }
                if (!jsonResultTransfer.isSuccess()) {
                    ThirdCashTransferException thirdCashTransferException = new ThirdCashTransferException(jsonResultTransfer.getMessage());
                    thirdCashTransferException.setThirdCashTransferExceptionResult(new ThirdCashTransferExceptionResult(wcdo.getCashNumber(), BankAccountTypeEnum.getEnum(wcdo.getChannel()), jsonResultTransfer.getCode(), jsonResultTransfer.getSubCode(), jsonResultTransfer.getStatus()));
                    throw thirdCashTransferException;
                }
            }

            //修改银行卡为永久绑定
            BankAccountDO record = new BankAccountDO();
            record.setId(wcdo.getBusinessBankAccountId());
            record.setIsBindForever(true);
            record.setGmtModified(new Date());
            bankAccountDOMapper.updateByPrimaryKeySelective(record);
        }

        if (!CashStatusEnum.FAILURE.getVal().equals(param.getCashOperEnum().getVal())) {
            return ResultCode.SUCCESS;
        }

        // 失败的情况要回滚数据
        // 新增交易明细
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTransactionNum(IdWorkerHelperImpl.generate(BizIdType.TRANSACTION));
        tdsParam.setUserNum(wcdo.getUserNum());
        if (wcdo.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            tdsParam.setTitle(MemberTransactionTypeEnum.WITHDRAW_BACK.getName());
            tdsParam.setTransactionType(MemberTransactionTypeEnum.WITHDRAW_BACK.getValue());
            tdsParam.setTransactionDesc(MemberTransactionTypeEnum.WITHDRAW_BACK.getDescPrefix());
        } else if (wcdo.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            tdsParam.setTitle(MerchantTransactionTypeEnum.WITHDRAW_BACK.getName());
            tdsParam.setTransactionType(MerchantTransactionTypeEnum.WITHDRAW_BACK.getValue());
            tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.WITHDRAW_BACK.getDescPrefix());
        }
        tdsParam.setTransactionAccount(wcdo.getAccount());
        tdsParam.setTransactionAccountType(TransactionPayTypeEnum.BALANCE.getVal());
        tdsParam.setAmount(wcdo.getCashMoney());
        tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setBizId(wcdo.getId() == null ? "" : wcdo.getId().toString());
        transactionDetailService.save(tdsParam);

        PropertyInfoDOEiditView infoView = new PropertyInfoDOEiditView();
        infoView.setBalance(wcdo.getCashMoney());
        infoView.setUserNum(wcdo.getUserNum());
        infoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoView);
        return ResultCode.SUCCESS;
    }

    @Override
    public int saveThirdplatformLog(String userNum, ThirdCashTransferException tte) {
        PayPlatformLogDO payPlatformLogDO = new PayPlatformLogDO();
        payPlatformLogDO.setUserNum(userNum);
        payPlatformLogDO.setOutBizNo(tte.getThirdCashTransferExceptionResult().getOutBizNo());
        payPlatformLogDO.setCode(tte.getThirdCashTransferExceptionResult().getCode());
        payPlatformLogDO.setSubCode(tte.getThirdCashTransferExceptionResult().getSubCode());
        payPlatformLogDO.setErrCode(tte.getThirdCashTransferExceptionResult().getErrCode());
        payPlatformLogDO.setPlatformType(tte.getThirdCashTransferExceptionResult().getBankAccountTypeEnum().getVal());
        payPlatformLogDO.setResponse(tte.getMessage());
        payPlatformLogDO.setGmtCreate(new Date());
        return payPlatformLogDOMapper.insertSelective(payPlatformLogDO);
    }

    @Override
    public List<WithdrawCashReportBO> selectWithdrawCashListByDateAndStatus(WithdrawCashReportParam param) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        Date begin = DateUtil.formatDate(param.getDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.formatDate(param.getDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        example.createCriteria().andStatusEqualTo(param.getStatus()).andGmtFinishBetween(begin, end);
        List<WithdrawCashDO> rntList = withdrawCashDOMapper.selectByExample(example);
        List<WithdrawCashReportBO> wrbs = new ArrayList<WithdrawCashReportBO>();
        for (WithdrawCashDO cdo : rntList) {
            WithdrawCashReportBO wrb = new WithdrawCashReportBO();
            wrb.setId(cdo.getId());
            wrb.setUserNum(cdo.getUserNum());
            wrb.setFinishDate(DateUtil.getDateFormat(cdo.getGmtFinish(), "yyyy-MM-dd"));
            wrb.setCashMoney(cdo.getCashMoney());
            wrbs.add(wrb);
        }
        return wrbs;
    }

    @Override
    public WithdrawCashTotalReportBO selectWithdrawCashTotalByDateAndStatus(WithdrawCashReportParam param) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        String begin = param.getDate() + " 00:00:00";
        String end = param.getDate() + " 23:59:59";
        WithdrawCashReportTotalParam query = new WithdrawCashReportTotalParam();
        query.setStatus(param.getStatus());
        query.setBegin(begin);
        query.setEnd(end);
        List<WithdrawCashTotalReportDOView> viewList = withdrawCashDOMapperExtend.selectWithdrawCashTotalByDateAndStatus(query);
        BigDecimal memberMoney = new BigDecimal("0");
        BigDecimal merchantMoney = new BigDecimal("0");
        if (viewList != null && !viewList.isEmpty()) {
            for (WithdrawCashTotalReportDOView view : viewList) {
                if (view.getUserType() == 1) {
                    memberMoney = view.getCashMoney();
                } else if (view.getUserType() == 2) {
                    merchantMoney = view.getCashMoney();
                }
            }
        }
        WithdrawCashTotalReportBO bo = new WithdrawCashTotalReportBO();
        bo.setMemberCashMoney(memberMoney);
        bo.setMerchantCashMoney(merchantMoney);
        return bo;
    }

    @Override
    public List<WithdrawCashReportBO> selectAgentWithdrawCashList(AgentWithdrawCashReportParam param) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        Date begin = DateUtil.formatDate(param.getDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.formatDate(param.getDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        example.createCriteria().andStatusEqualTo(param.getStatus()).andGmtFinishBetween(begin, end).andCityIdEqualTo(param.getCityId());
        List<WithdrawCashDO> rntList = withdrawCashDOMapper.selectByExample(example);
        List<WithdrawCashReportBO> wrbs = new ArrayList<>();
        WithdrawCashReportBO wrb;
        for (WithdrawCashDO cdo : rntList) {
            wrb = new WithdrawCashReportBO();
            wrb.setId(cdo.getId());
            wrb.setUserNum(cdo.getUserNum());
            wrb.setFinishDate(DateUtil.getDateFormat(cdo.getGmtFinish(), "yyyy-MM-dd"));
            wrb.setCashMoney(cdo.getCashMoney());
            wrbs.add(wrb);
        }
        return wrbs;
    }

    @Override
    public WithdrawCashTotalReportBO selectAgentWithdrawCashTotal(AgentWithdrawCashReportParam param) {
        String begin = param.getDate() + " 00:00:00";
        String end = param.getDate() + " 23:59:59";
        AgentWithdrawCashReportTotalParam query = new AgentWithdrawCashReportTotalParam();
        query.setBegin(begin);
        query.setEnd(end);
        query.setStatus(param.getStatus());
        query.setCityId(param.getCityId());
        List<WithdrawCashTotalReportDOView> viewList = withdrawCashDOMapperExtend.selectAgentWithdrawCashTotal(query);
        BigDecimal memberMoney = new BigDecimal("0");
        BigDecimal merchantMoney = new BigDecimal("0");
        if (viewList != null && !viewList.isEmpty()) {
            for (WithdrawCashTotalReportDOView view : viewList) {
                if (view.getUserType() == 1) {
                    memberMoney = view.getCashMoney();
                } else if (view.getUserType() == 2) {
                    merchantMoney = view.getCashMoney();
                }
            }
        }
        WithdrawCashTotalReportBO bo = new WithdrawCashTotalReportBO();
        bo.setMemberCashMoney(memberMoney);
        bo.setMerchantCashMoney(merchantMoney);
        return bo;
    }

}
