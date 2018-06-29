package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.property.srv.bo.CashFeeParamBO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyInfoDirectionEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.param.CashBillDataParam;
import com.lawu.eshop.property.param.CashChargeDataParam;
import com.lawu.eshop.property.param.CashDataParam;
import com.lawu.eshop.property.param.TransactionDetailSaveDataParam;
import com.lawu.eshop.property.srv.PropertySrvConfig;
import com.lawu.eshop.property.srv.bo.CashFeeBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashDetailBO;
import com.lawu.eshop.property.srv.bo.WithdrawCashQueryBO;
import com.lawu.eshop.property.srv.converter.WithdrawCashBOConverter;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.WithdrawCashDO;
import com.lawu.eshop.property.srv.domain.WithdrawCashDOExample;
import com.lawu.eshop.property.srv.domain.extend.PropertyInfoDOView;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.PropertyInfoDOMapperExtend;
import com.lawu.eshop.property.srv.service.CashManageFrontService;
import com.lawu.eshop.property.srv.service.PropertyInfoService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@Service
public class CashManageFrontServiceImpl implements CashManageFrontService {

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;
    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyInfoDOMapperExtend propertyInfoDOMapperExtend;
    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;
    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private PropertyInfoService propertyInfoService;
    @Autowired
    private PropertySrvConfig propertySrvConfig;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(CashDataParam cash) {

        // 校验最小金额
        String minMoney = propertyService.getValue(PropertyType.CASH_MIN_MONEY);
        double dCashMoney = Double.parseDouble(cash.getCashMoney());
        if (dCashMoney < Double.parseDouble(minMoney)) {
            return ResultCode.CASH_MORE_NUM_MAX_MONEY_ERROR;
        }

        BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(cash.getBusinessBankAccountId());
        if (bankAccountDO.getAccountType().equals(BankAccountTypeEnum.ALIPAY.getVal()) && dCashMoney > Double.parseDouble(propertySrvConfig.getCashMax())) {
            return ResultCode.CASH_MAX;
        }
        if (bankAccountDO.getAccountType().equals(BankAccountTypeEnum.WEIXIN.getVal()) && dCashMoney > Double.parseDouble(propertySrvConfig.getCashMaxWx())) {
            return ResultCode.CASH_MAX_WX;
        }

        // 校验资产财产记录、余额、支付密码、冻结情况
        int retCode = propertyInfoService.validateBalance(cash.getUserNum(), cash.getCashMoney(), true, cash.getPayPwd());
        if (retCode != ResultCode.SUCCESS) {
            return retCode;
        }

        // 校验提交的银行卡是否正确
        BankAccountDO bankAccountDo = bankAccountDOMapper.selectByPrimaryKey(cash.getBusinessBankAccountId());
        if (bankAccountDo == null) {
            return ResultCode.PROPERTY_CASH_BANK_NOT_EXIST;
        } else if (!bankAccountDo.getUserNum().trim().equals(cash.getUserNum())) {
            return ResultCode.PROPERTY_CASH_BANK_NOT_MATCH;
        }

        WithdrawCashDO withdrawCashDO = new WithdrawCashDO();

        //申请操作时计算手续费
//        String currentScale = propertyService.getValue(PropertyType.CASH_SCALE);
//        double dCurrentScale = Double.parseDouble(currentScale);
//        WithdrawCashDOExample example = new WithdrawCashDOExample();
//        example.createCriteria().andUserNumEqualTo(cash.getUserNum()).andGmtCreateGreaterThanOrEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
//        long count = withdrawCashDOMapper.countByExample(example);
//        double money = 0;
//        if (new Long(count).intValue() > 0) {
//            String minusMoney = "0";
//            if(BankAccountTypeEnum.BANK.getVal().equals(bankAccountDO.getAccountType())){
//                propertyService.getValue(PropertyType.CASH_GREATER_ONE_MINUS_MONEY);
//            }
//            money = dCashMoney * dCurrentScale - Double.parseDouble(minusMoney);
//        } else {
//            money = dCashMoney * dCurrentScale;
//        }
//        withdrawCashDO.setCurrentScale(currentScale);
//        withdrawCashDO.setMoney(BigDecimal.valueOf(money));

        CashChargeDataParam cashChargeDataParam = new CashChargeDataParam();
        cashChargeDataParam.setCashMoney(cash.getCashMoney());
        cashChargeDataParam.setUserNum(cash.getUserNum());
        cashChargeDataParam.setBusinessBankAccountId(cash.getBusinessBankAccountId());
        CashFeeBO cashFeeBO = calculationFee(cashChargeDataParam);
        withdrawCashDO.setCurrentScale(cashFeeBO.getCurrentScale());
        withdrawCashDO.setMoney(cashFeeBO.getActualMoney());

        withdrawCashDO.setCashMoney(new BigDecimal(cash.getCashMoney()));
        withdrawCashDO.setUserNum(cash.getUserNum());
        withdrawCashDO.setUserType(cash.getUserType());
        withdrawCashDO.setChannel(bankAccountDO.getAccountType());
        withdrawCashDO.setStatus(CashStatusEnum.APPLY.getVal());
        withdrawCashDO.setBusinessBankAccountId(cash.getBusinessBankAccountId());
        withdrawCashDO.setCashNumber(IdWorkerHelperImpl.generate(BizIdType.CASH));
        withdrawCashDO.setGmtCreate(new Date());
        withdrawCashDO.setAccount(cash.getAccount());
        withdrawCashDO.setName(cash.getName());
        withdrawCashDO.setProvinceId(cash.getProvinceId());
        withdrawCashDO.setCityId(cash.getCityId());
        withdrawCashDO.setAreaId(cash.getAreaId());
        withdrawCashDO.setRegionFullName(cash.getRegionFullName());
        withdrawCashDO.setRemark(bankAccountDO.getNote());
        withdrawCashDO.setAccountName(bankAccountDO.getAccountName());
        withdrawCashDO.setAccountNumber(bankAccountDO.getAccountNumber());
        withdrawCashDOMapper.insertSelective(withdrawCashDO);

        // 新增交易明细
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        if (cash.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
            tdsParam.setTitle(MemberTransactionTypeEnum.WITHDRAW.getName());
            tdsParam.setTransactionDesc(MemberTransactionTypeEnum.WITHDRAW.getDescPrefix());
        } else if (cash.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            tdsParam.setTitle(MerchantTransactionTypeEnum.WITHDRAW.getName());
            tdsParam.setTransactionDesc(MerchantTransactionTypeEnum.WITHDRAW.getDescPrefix());
        }
        tdsParam.setTransactionNum(IdWorkerHelperImpl.generate(BizIdType.CASH));
        tdsParam.setUserNum(cash.getUserNum());
        tdsParam.setTransactionType(cash.getTransactionType());
        tdsParam.setTransactionAccount(cash.getAccount());
        tdsParam.setTransactionAccountType(TransactionPayTypeEnum.BALANCE.getVal());
        tdsParam.setAmount(new BigDecimal(cash.getCashMoney()));
        tdsParam.setDirection(PropertyInfoDirectionEnum.OUT.getVal());
        tdsParam.setBizId(withdrawCashDO.getId().toString());
        transactionDetailService.save(tdsParam);

        PropertyInfoDOExample infoExample = new PropertyInfoDOExample();
        infoExample.createCriteria().andUserNumEqualTo(cash.getUserNum());
        List<PropertyInfoDO> infoList = propertyInfoDOMapper.selectByExample(infoExample);
        // 更新财产记录，减
        PropertyInfoDOView infoDoView = new PropertyInfoDOView();
        infoDoView.setId(infoList.get(0).getId());
        infoDoView.setBalance(new BigDecimal(cash.getCashMoney()));
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updateByPrimaryKeySelective(infoDoView);

        return ResultCode.SUCCESS;
    }

    @Override
    public Page<WithdrawCashQueryBO> findCashList(CashBillDataParam cash) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        example.createCriteria().andUserNumEqualTo(cash.getUserNum());
        example.setOrderByClause(" id desc ");
        RowBounds rowBounds = new RowBounds(cash.getOffset(), cash.getPageSize());
        Page<WithdrawCashQueryBO> page = new Page<WithdrawCashQueryBO>();
        long totalCount = withdrawCashDOMapper.countByExample(example);
        page.setTotalCount(new Long(totalCount).intValue());
        page.setCurrentPage(cash.getCurrentPage());
        List<WithdrawCashDO> listDOS = withdrawCashDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        List<WithdrawCashQueryBO> cbos = new ArrayList<WithdrawCashQueryBO>();
        for (WithdrawCashDO cdo : listDOS) {
            WithdrawCashQueryBO bo = new WithdrawCashQueryBO();
            bo.setId(cdo.getId());
            bo.setTitle("提现金额");
            bo.setCashMoney(cdo.getCashMoney());
            bo.setCashStatusEnum(CashStatusEnum.getEnum(cdo.getStatus()));
            bo.setCdate(cdo.getGmtCreate());
            bo.setAcceptDate(cdo.getGmtAccept());
            bo.setFinishDate(cdo.getGmtFinish());
            if (CashStatusEnum.FAILURE.getVal().equals(cdo.getStatus())) {
                bo.setRemark(cdo.getAuditFaildReason());
            } else {
                bo.setActualCashMoney(cdo.getMoney());
                bo.setChargedMoney(cdo.getMoney() == null ? new BigDecimal(0) : cdo.getCashMoney().subtract(cdo.getMoney()));
            }
            cbos.add(bo);
        }
        page.setRecords(cbos);
        return page;
    }

    @Override
    public WithdrawCashDetailBO cashDetail(Long id) {
        WithdrawCashDO cdo = withdrawCashDOMapper.selectByPrimaryKey(id);
        if (cdo == null) {
            return null;
        }
        WithdrawCashDetailBO bo = new WithdrawCashDetailBO();
        bo.setId(cdo.getId());
        bo.setTitle("提现");
        bo.setCashMoney(cdo.getCashMoney());
        bo.setMoney(cdo.getMoney());
        if (CashStatusEnum.APPLY.getVal().equals(cdo.getStatus())) {
            bo.setCashStatus("申请中");
        } else if (CashStatusEnum.ACCEPT.getVal().equals(cdo.getStatus())) {
            bo.setCashStatus("受理中");
        } else if (CashStatusEnum.SUCCESS.getVal().equals(cdo.getStatus())) {
            bo.setCashStatus("成功");
        } else if (CashStatusEnum.FAILURE.getVal().equals(cdo.getStatus())) {
            bo.setCashStatus("失败");
        }
        bo.setCashNumber(cdo.getCashNumber());
        bo.setCdate(DateUtil.getDateFormat(cdo.getGmtCreate(), "yyyy-MM-dd"));
        bo.setAcceptDate(DateUtil.getDateFormat(cdo.getGmtAccept(), "yyyy-MM-dd HH:mm"));
        bo.setFinishDate(DateUtil.getDateFormat(cdo.getGmtFinish(), "yyyy-MM-dd HH:mm"));
        bo.setFaildReason(cdo.getAuditFaildReason());

        BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(cdo.getBusinessBankAccountId());
        bo.setBankInfo(bankAccountDO.getNote());

        return bo;
    }

    /**
     * 查询提现详情
     *
     * @param ids 提现id列表
     * @return
     */
    @Override
    public List<WithdrawCashBO> list(List<Long> ids) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        example.createCriteria().andIdIn(ids);

        List<WithdrawCashDO> withdrawCashDOList = withdrawCashDOMapper.selectByExample(example);

        return WithdrawCashBOConverter.convert(withdrawCashDOList);
    }

    /**
     * 是否存在提现
     */
    @Override
    public Boolean isExistCash(String userNum, Long bankAccountId) {
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        List<Byte> status = new ArrayList<>();
        status.add(new Byte("1"));
        status.add(new Byte("2"));
        example.createCriteria().andUserNumEqualTo(userNum).andBusinessBankAccountIdEqualTo(bankAccountId)
                .andStatusIn(status);
        long count = withdrawCashDOMapper.countByExample(example);
        if (new Long(count).intValue() > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public CashFeeBO calculationFee(CashChargeDataParam cash) {
        CashFeeBO bo = new CashFeeBO();
        BankAccountDO bankAccountDo = bankAccountDOMapper.selectByPrimaryKey(cash.getBusinessBankAccountId());
        String currentScaleStr = propertyService.getValue(PropertyType.CASH_SCALE);
        BigDecimal currentScale = new BigDecimal(currentScaleStr);
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        example.createCriteria().andUserNumEqualTo(cash.getUserNum()).andGmtCreateGreaterThanOrEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss")).andChannelEqualTo(BankAccountTypeEnum.BANK.getVal());
        long count = withdrawCashDOMapper.countByExample(example);
        BigDecimal money;
        BigDecimal minusMoney = new BigDecimal(0);
        if (new Long(count).intValue() > 0) {
            if (BankAccountTypeEnum.BANK.getVal().equals(bankAccountDo.getAccountType())) {
                minusMoney = new BigDecimal(propertyService.getValue(PropertyType.CASH_GREATER_ONE_MINUS_MONEY));
            }
            money = new BigDecimal(cash.getCashMoney()).multiply(currentScale).subtract(minusMoney);
        } else {
            money = new BigDecimal(cash.getCashMoney()).multiply(currentScale);
        }
        bo.setActualMoney(money);
        bo.setServiceMoney(minusMoney);
        bo.setChargeMoney(new BigDecimal(cash.getCashMoney()).subtract(new BigDecimal(cash.getCashMoney()).multiply(currentScale)));
        bo.setCurrentScale(currentScaleStr);
        return bo;
    }

    @Override
    public CashFeeParamBO getCalculationFeeParam(String userNum) {
        String currentScaleStr = propertyService.getValue(PropertyType.CASH_SCALE);
        CashFeeParamBO bo = new CashFeeParamBO();
        bo.setScale(currentScaleStr);
        WithdrawCashDOExample example = new WithdrawCashDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andGmtCreateGreaterThanOrEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(new Date(), "yyyy-MM") + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss")).andChannelEqualTo(BankAccountTypeEnum.BANK.getVal());
        long count = withdrawCashDOMapper.countByExample(example);
        BigDecimal chargeMoney = new BigDecimal(0);
        if (new Long(count).intValue() > 0) {
            chargeMoney = new BigDecimal(propertyService.getValue(PropertyType.CASH_GREATER_ONE_MINUS_MONEY));
        }
        bo.setChargeMoney(chargeMoney.toString());
        return bo;
    }

}
