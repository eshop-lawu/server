package com.lawu.eshop.property.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.common.param.CommissionJobParam;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.*;
import com.lawu.eshop.property.dto.AdCommissionResultDTO;
import com.lawu.eshop.property.param.*;
import com.lawu.eshop.property.srv.domain.LoveDetailDO;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.domain.PointDetailDOExample;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample.Criteria;
import com.lawu.eshop.property.srv.domain.extend.PropertyInfoDOEiditView;
import com.lawu.eshop.property.srv.mapper.LoveDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.PropertyInfoDOMapperExtend;
import com.lawu.eshop.property.srv.service.CommissionService;
import com.lawu.eshop.property.srv.service.CommissionUtilImpl;
import com.lawu.eshop.property.srv.service.PointDetailService;
import com.lawu.eshop.property.srv.service.PropertyService;
import com.lawu.eshop.property.srv.service.TransactionDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommissionServiceImpl implements CommissionService {

    private static Logger logger = LoggerFactory.getLogger(CommissionServiceImpl.class);

    @Autowired
    private TransactionDetailService transactionDetailService;
    @Autowired
    private PropertyInfoDOMapperExtend propertyInfoDOMapperExtend;
    @Autowired
    private LoveDetailDOMapper loveDetailDOMapper;
    @Autowired
    private PointDetailService pointDetailService;
    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;
    @Autowired
    private PointDetailDOMapper pointDetailDOMapper;
    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;
    @Autowired
    private PropertyService propertySrevice;
    @Autowired
    private CommissionUtilImpl commissionUtilImpl;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int calculation(CommissionJobParam param) {

        //最后一级统一进积分，不计爱心账户
        if (param.isLast()) {
            //按实际积分兑换比例进行
            String name = PropertyType.MEMBER_THIRD_PAY_POINT;
            if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                name = PropertyType.MERCHANT_THIRD_PAY_POINT;
            }
            BigDecimal currentScale = BigDecimal.valueOf(Double.valueOf(propertySrevice.getValue(name)));
            param.setActureMoneyIn(param.getActureMoneyIn().multiply(currentScale));

            if (param.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {

                // 先判断该上级是否已算过提成
                PointDetailDOExample example = new PointDetailDOExample();
                com.lawu.eshop.property.srv.domain.PointDetailDOExample.Criteria criteria = example.createCriteria();
                criteria.andBizIdEqualTo(param.getBizId().toString()).andUserNumEqualTo(param.getUserNum())
                        .andPointTypeEqualTo(param.getTypeVal());
                List<PointDetailDO> dos = pointDetailDOMapper.selectByExample(example);
                logger.info("isLast dos={},size={}", dos, dos.size());
                if (!dos.isEmpty()) {
                    logger.info("isLast已计算过提成，重复计算，直接返回！");
                    return ResultCode.SUCCESS;
                }

                // 新增积分明细
                PointDetailSaveDataParam pointDetailSaveDataParam = new PointDetailSaveDataParam();
                pointDetailSaveDataParam.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
                pointDetailSaveDataParam.setUserNum(param.getUserNum());
                pointDetailSaveDataParam.setTitle(param.getTypeName());
                pointDetailSaveDataParam.setPointType(param.getTypeVal());
                pointDetailSaveDataParam.setPoint(param.getActureMoneyIn());
                pointDetailSaveDataParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
                pointDetailSaveDataParam.setBizId(param.getBizId().toString());
                pointDetailService.save(pointDetailSaveDataParam);

                // 更新用户资产
                PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
                infoDoView.setUserNum(param.getUserNum());
                infoDoView.setPoint(param.getActureMoneyIn());
                infoDoView.setGmtModified(new Date());
                propertyInfoDOMapperExtend.updatePropertyInfoAddPoint(infoDoView);

            } else if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {

                // 先判断该上级是否已算过提成
                PointDetailDOExample example = new PointDetailDOExample();
                com.lawu.eshop.property.srv.domain.PointDetailDOExample.Criteria criteria = example.createCriteria();
                criteria.andBizIdEqualTo(param.getBizId().toString()).andUserNumEqualTo(param.getUserNum())
                        .andPointTypeEqualTo(param.getTypeVal());
                List<PointDetailDO> dos = pointDetailDOMapper.selectByExample(example);
                logger.info("isLast dos={},size={}", dos, dos.size());
                if (!dos.isEmpty()) {
                    logger.info("isLast已计算过提成，重复计算，直接返回！");
                    return ResultCode.SUCCESS;
                }

                // 新增积分明细
                PointDetailSaveDataParam pointDetailSaveDataParam = new PointDetailSaveDataParam();
                pointDetailSaveDataParam.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
                pointDetailSaveDataParam.setUserNum(param.getUserNum());
                pointDetailSaveDataParam.setTitle(param.getTypeName());
                pointDetailSaveDataParam.setPointType(param.getTypeVal());
                pointDetailSaveDataParam.setPoint(param.getActureMoneyIn());
                pointDetailSaveDataParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
                pointDetailSaveDataParam.setBizId(param.getBizId().toString());
                pointDetailService.save(pointDetailSaveDataParam);

                // 更新用户资产
                PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
                infoDoView.setUserNum(param.getUserNum());
                infoDoView.setPoint(param.getActureMoneyIn());
                infoDoView.setGmtModified(new Date());
                propertyInfoDOMapperExtend.updatePropertyInfoAddPoint(infoDoView);
            }
        } else {

            // 先查询该上级是否已经算过提成
            TransactionDetailDOExample example = new TransactionDetailDOExample();
            Criteria criteria = example.createCriteria();
            criteria.andBizIdEqualTo(param.getBizId().toString()).andUserNumEqualTo(param.getUserNum())
                    .andTransactionTypeEqualTo(param.getTypeVal());
            List<TransactionDetailDO> dos = transactionDetailDOMapper.selectByExample(example);
            logger.info("isLast dos={},size={}", dos, dos.size());
            if (!dos.isEmpty()) {
                logger.info("已计算过提成，重复计算，直接返回！");
                return ResultCode.SUCCESS;
            }

            PropertyInfoDOExample examplePropertyInfo = new PropertyInfoDOExample();
            examplePropertyInfo.createCriteria().andUserNumEqualTo(param.getUserNum());
            List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(examplePropertyInfo);

            // 计爱心账户
            LoveDetailDO loveDetailDO = new LoveDetailDO();
            loveDetailDO.setTitle(param.getLoveTypeName());
            loveDetailDO.setLoveNum(IdWorkerHelperImpl.generate(BizIdType.LOVE));
            loveDetailDO.setUserNum(param.getUserNum());
            loveDetailDO.setLoveType(param.getLoveTypeVal());
            loveDetailDO.setAmount(param.getActureLoveIn());
            loveDetailDO.setRemark("");
            loveDetailDO.setGmtCreate(new Date());
            loveDetailDO.setBizId(param.getTempBidId() == null ? "0" : param.getTempBidId().toString());
            loveDetailDO.setPreviousAmount((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getLoveAccount());
            loveDetailDOMapper.insertSelective(loveDetailDO);

            // 加会员财产爱心账户
            PropertyInfoDOEiditView infoDoView1 = new PropertyInfoDOEiditView();
            infoDoView1.setUserNum(param.getUserNum());
            infoDoView1.setLoveAccount(param.getActureLoveIn());
            infoDoView1.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoAddLove(infoDoView1);

            // 新增点广告的余额交易明细
            TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
            tdsParam.setTransactionNum(IdWorkerHelperImpl.generate(BizIdType.TRANSACTION));
            tdsParam.setUserNum(param.getUserNum());
            tdsParam.setTitle(param.getTypeName());
            tdsParam.setTransactionType(param.getTypeVal());
            tdsParam.setTransactionAccount("");
            tdsParam.setTransactionAccountType(TransactionPayTypeEnum.BALANCE.getVal());
            tdsParam.setAmount(param.getActureMoneyIn());
            tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
            tdsParam.setBizId(param.getBizId().toString());
            tdsParam.setPreviousAmount((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getBalance());
            tdsParam.setTransactionDesc(param.getTransactionDesc());
            transactionDetailService.save(tdsParam);

            // 加用户（会员或商家）财产余额
            PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
            infoDoView.setUserNum(param.getUserNum());
            infoDoView.setBalance(param.getActureMoneyIn());
            infoDoView.setGmtModified(new Date());
            propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);
        }
        return ResultCode.SUCCESS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculationAd(CommissionParam commissionParam, List<CommissionInvitersUserDTO> inviters, CommissionEnum commissionEnum) {
        Map<String, BigDecimal> property = propertySrevice.getAdCommissionPropertys();
        BigDecimal loveAccountScale = property.get(PropertyType.love_account_scale);// 爱心账户比例
        BigDecimal actualCommissionScope = property.get("acture_in_scale");// 实际提成比例=1-爱心账户(0.003)
        BigDecimal adCommission0 = property.get(PropertyType.ad_commission_0);

        int m = 0;
        for (int i = 0; i < inviters.size(); i++) {
            CommissionJobParam param = new CommissionJobParam();
            param.setUserNum(inviters.get(i).getUserNum());
            param.setBizId(commissionParam.getId());
            param.setTempBidId(commissionParam.getTempId());
            BigDecimal clickMoney = commissionParam.getActualAmount();
            BigDecimal sale_commission = property.get(PropertyType.ad_commission_ + inviters.get(i).getDept());
            if (inviters.get(i).getDept() == 3) {
                param.setLast(true);
            }
            CommissionResultParam commissionResultparam = new CommissionResultParam();
            commissionResultparam.setBeforeMoney(clickMoney);
            commissionResultparam.setCommission0(adCommission0);
            commissionResultparam.setCurrentCommission(sale_commission);
            commissionResultparam.setActualCommissionScope(actualCommissionScope);
            commissionResultparam.setLoveAccountScale(loveAccountScale);
            commissionResultparam.setDept(inviters.get(i).getDept());
            AdCommissionResultDTO rntDTO = commissionUtilImpl.getCommissionResult(commissionResultparam);
            param.setActureMoneyIn(rntDTO.getActureMoneyIn());
            param.setActureLoveIn(rntDTO.getActureLoveIn());

            if (inviters.get(i).getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                param.setTypeVal(MemberTransactionTypeEnum.LOWER_INCOME.getValue());
                param.setTypeName(MemberTransactionTypeEnum.LOWER_INCOME.getName());
                param.setTransactionDesc(MemberTransactionTypeEnum.LOWER_INCOME.getDescPrefix());
            } else if (inviters.get(i).getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                param.setTypeVal(MerchantTransactionTypeEnum.LOWER_INCOME.getValue());
                param.setTypeName(MerchantTransactionTypeEnum.LOWER_INCOME.getName());
                param.setTransactionDesc(MerchantTransactionTypeEnum.LOWER_INCOME.getDescPrefix());
            }
            param.setLoveTypeVal(LoveTypeEnum.AD_COMMISSION.getValue());
            param.setLoveTypeName(LoveTypeEnum.AD_COMMISSION.getName());

            logger.info("{}提成比例（获得提成账号编号：{},id={}）；基础金额(a)：{}，参与提成金额比例(b)={},提成比例(c)={}（所得比例(d)={}|爱心账户比例(e)={}）；所得（实际实际[a*b*c*d]={},爱心账户[[a*b*c*e]]={}）", commissionEnum, inviters.get(i).getUserNum(), commissionParam.getId(), clickMoney, adCommission0, sale_commission, actualCommissionScope, loveAccountScale, param.getActureMoneyIn(), param.getActureLoveIn());

            try {
                m = commonTransaction(param, m);
            } catch (Exception e) {
                logger.error("{}提成保存资产异常！", commissionEnum);
                e.printStackTrace();
            }
        }
        // 所有上线提成计算成功才算成功
        if (m != inviters.size()) {
            throw new RuntimeException(commissionEnum + "提成存在部分上级提成失败的情况");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void calculationSalesAndVolume(CommissionParam commissionParam, List<CommissionInvitersUserDTO> inviters, CommissionEnum commissionEnum) {
        Map<String, BigDecimal> property = propertySrevice.getSaleCommissionPropertys();
        BigDecimal saleCommissionAddScope = property.get(PropertyType.sale_commission_add_scope);// 每上升一个级别提成的幅度
        BigDecimal loveAccountScale = property.get(PropertyType.love_account_scale);// 爱心账户比例
        BigDecimal actualCommissionScope = property.get("acture_in_scale");// 实际提成比例=1-爱心账户(0.003)
        BigDecimal saleCommission0 = property.get(PropertyType.sale_commission_0);

        int m = 0;
        for (int i = 0; i < inviters.size(); i++) {
            String userNum = inviters.get(i).getUserNum();
            CommissionJobParam param = new CommissionJobParam();
            param.setUserNum(userNum);
            param.setBizId(commissionParam.getId());
            param.setTempBidId(commissionParam.getId());
            BigDecimal actualMoney = commissionParam.getActualAmount();
            BigDecimal saleCommission = property.get(PropertyType.sale_commission_ + inviters.get(i).getDept());
            if (inviters.get(i).getDept() == 3) {
                param.setLast(true);
            }
            BigDecimal level = new BigDecimal(inviters.get(i).getLevel());
            BigDecimal actualCommission = saleCommission.add(saleCommissionAddScope.multiply(level.subtract(new BigDecimal("1"))));//没升一个级别+0.005
            CommissionResultParam commissionResultparam = new CommissionResultParam();
            commissionResultparam.setBeforeMoney(actualMoney);
            commissionResultparam.setCommission0(saleCommission0);
            commissionResultparam.setCurrentCommission(actualCommission);
            commissionResultparam.setActualCommissionScope(actualCommissionScope);
            commissionResultparam.setLoveAccountScale(loveAccountScale);
            commissionResultparam.setDept(inviters.get(i).getDept());
            AdCommissionResultDTO rntDTO = commissionUtilImpl.getCommissionResult(commissionResultparam);
            param.setActureMoneyIn(rntDTO.getActureMoneyIn());
            param.setActureLoveIn(rntDTO.getActureLoveIn());

            if (inviters.get(i).getFlag() == 1) {
                if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                    param.setTypeVal(MemberTransactionTypeEnum.SALES_COMMISSION.getValue());
                    param.setTypeName(MemberTransactionTypeEnum.SALES_COMMISSION.getName());
                    param.setTransactionDesc(MemberTransactionTypeEnum.SALES_COMMISSION.getDescPrefix());
                } else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                    param.setTypeVal(MerchantTransactionTypeEnum.SALES_COMMISSION.getValue());
                    param.setTypeName(MerchantTransactionTypeEnum.SALES_COMMISSION.getName());
                    param.setTransactionDesc(MerchantTransactionTypeEnum.SALES_COMMISSION.getDescPrefix());
                }
                param.setLoveTypeVal(LoveTypeEnum.SALES_COMMISSION.getValue());
                param.setLoveTypeName(LoveTypeEnum.SALES_COMMISSION.getName());
            } else if (inviters.get(i).getFlag() == 2) {
                if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                    param.setTypeVal(MemberTransactionTypeEnum.VOLUME_COMMISSION.getValue());
                    param.setTypeName(MemberTransactionTypeEnum.VOLUME_COMMISSION.getName());
                    param.setTransactionDesc(MemberTransactionTypeEnum.VOLUME_COMMISSION.getDescPrefix());
                } else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                    param.setTypeVal(MerchantTransactionTypeEnum.VOLUME_COMMISSION.getValue());
                    param.setTypeName(MerchantTransactionTypeEnum.VOLUME_COMMISSION.getName());
                    param.setTransactionDesc(MerchantTransactionTypeEnum.VOLUME_COMMISSION.getDescPrefix());
                }
                param.setLoveTypeVal(LoveTypeEnum.VOLUME_COMMISSION.getValue());
                param.setLoveTypeName(LoveTypeEnum.VOLUME_COMMISSION.getName());
            }

            logger.info("{}提成（订单ID={}，交易用户编号={}，交易商家编号={}，获得提成账号编号：{}）；基础金额(a)：{}，参与提成金额比例(b){}，实际提成比例(c)：{}（初始提成比例(c1)={}, 等级(c2)={},每上升一级幅度累加比例(c3)={}）；所得（实际提成金额：{}，爱心账户金额：{}）", commissionEnum, commissionParam.getId(), commissionParam.getMemberNum(), commissionParam.getMerchantNum(), userNum, actualMoney, saleCommission0, actualCommission, saleCommission, level, saleCommissionAddScope, rntDTO.getActureMoneyIn(), rntDTO.getActureLoveIn());

            try {
                m = commonTransaction(param, m);
            } catch (Exception e) {
                logger.error("{}提成保存资产异常！", commissionEnum);
                e.printStackTrace();
            }
        }
        // 所有上线提成计算成功才算成功
        if (m != inviters.size()) {
            throw new RuntimeException(commissionEnum + "提成存在部分上级提成失败的情况");
        }
    }

    public int commonTransaction(CommissionJobParam param, int m) {
        int retCode;
        if (param.isLast()) {
            retCode = commissionLast(param);
        } else {
            retCode = commissionLately(param);
        }
        if (ResultCode.SUCCESS == retCode) {
            m++;
        }
        return m;
    }

    /**
     * 计算上2级提成
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int commissionLately(CommissionJobParam param) {

        TransactionDetailDOExample example = new TransactionDetailDOExample();
        Criteria criteria = example.createCriteria();
        criteria.andBizIdEqualTo(param.getBizId().toString()).andUserNumEqualTo(param.getUserNum())
                .andTransactionTypeEqualTo(param.getTypeVal());
        List<TransactionDetailDO> dos = transactionDetailDOMapper.selectByExample(example);
        logger.info("isLast dos={},size={}", dos, dos.size());
        if (!dos.isEmpty()) {
            logger.info("已计算过提成，重复计算，直接返回！");
            return ResultCode.SUCCESS;
        }

        PropertyInfoDOExample examplePropertyInfo = new PropertyInfoDOExample();
        examplePropertyInfo.createCriteria().andUserNumEqualTo(param.getUserNum());
        List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(examplePropertyInfo);

        // 计爱心账户
        LoveDetailDO loveDetailDO = new LoveDetailDO();
        loveDetailDO.setTitle(param.getLoveTypeName());
        loveDetailDO.setLoveNum(IdWorkerHelperImpl.generate(BizIdType.LOVE));
        loveDetailDO.setUserNum(param.getUserNum());
        loveDetailDO.setLoveType(param.getLoveTypeVal());
        loveDetailDO.setAmount(param.getActureLoveIn());
        loveDetailDO.setRemark("");
        loveDetailDO.setGmtCreate(new Date());
        loveDetailDO.setBizId(param.getBizId() == null ? "0" : param.getBizId().toString());
        loveDetailDO.setPreviousAmount((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getLoveAccount());
        loveDetailDOMapper.insertSelective(loveDetailDO);

        // 加会员财产爱心账户
        PropertyInfoDOEiditView infoDoView1 = new PropertyInfoDOEiditView();
        infoDoView1.setUserNum(param.getUserNum());
        infoDoView1.setLoveAccount(param.getActureLoveIn());
        infoDoView1.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddLove(infoDoView1);

        // 新增点广告的余额交易明细
        TransactionDetailSaveDataParam tdsParam = new TransactionDetailSaveDataParam();
        tdsParam.setTransactionNum(IdWorkerHelperImpl.generate(BizIdType.TRANSACTION));
        tdsParam.setUserNum(param.getUserNum());
        tdsParam.setTitle(param.getTypeName());
        tdsParam.setTransactionType(param.getTypeVal());
        tdsParam.setTransactionAccount("");
        tdsParam.setTransactionAccountType(TransactionPayTypeEnum.BALANCE.getVal());
        tdsParam.setAmount(param.getActureMoneyIn());
        tdsParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        tdsParam.setBizId(param.getBizId().toString());
        tdsParam.setPreviousAmount((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getBalance());
        tdsParam.setTransactionDesc(param.getTransactionDesc());
        transactionDetailService.save(tdsParam);

        // 加用户（会员或商家）财产余额
        PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
        infoDoView.setUserNum(param.getUserNum());
        infoDoView.setBalance(param.getActureMoneyIn());
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddBalance(infoDoView);

        // 统计播报汇总，不在事务中
        try {
            CountIncomeBroadcastParam countIncomeBroadcastParam = new CountIncomeBroadcastParam();
            countIncomeBroadcastParam.setUserNum(tdsParam.getUserNum());
            countIncomeBroadcastParam.setMoney(tdsParam.getAmount());
            countIncomeBroadcastParam.setIncomeType(PayTypeEnum.BALANCE.getVal());
            transactionDetailService.countIncomeBroadcast(countIncomeBroadcastParam);
        } catch (Exception ex){
            logger.error("收益计算统计播报时异常，{}",ex.getMessage());
            ex.getStackTrace();
        }

        return ResultCode.SUCCESS;
    }

    /**
     * 计算最后一级
     * 最后一级统一进积分，不计爱心账户，按实际积分余额/兑换比例进行
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int commissionLast(CommissionJobParam param) {

        String name = PropertyType.MEMBER_THIRD_PAY_POINT;
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            name = PropertyType.MERCHANT_THIRD_PAY_POINT;
        }
        BigDecimal currentScale = BigDecimal.valueOf(Double.valueOf(propertySrevice.getValue(name)));
        param.setActureMoneyIn(param.getActureMoneyIn().multiply(currentScale));

        // 先判断该上级是否已算过提成
        PointDetailDOExample example = new PointDetailDOExample();
        com.lawu.eshop.property.srv.domain.PointDetailDOExample.Criteria criteria = example.createCriteria();
        criteria.andBizIdEqualTo(param.getBizId().toString()).andUserNumEqualTo(param.getUserNum())
                .andPointTypeEqualTo(param.getTypeVal());
        List<PointDetailDO> dos = pointDetailDOMapper.selectByExample(example);
        logger.info("isLast dos={},size={}", dos, dos.size());
        if (!dos.isEmpty()) {
            logger.info("isLast已计算过提成，重复计算，直接返回！");
            return ResultCode.SUCCESS;
        }

        // 新增积分明细
        PointDetailSaveDataParam pointDetailSaveDataParam = new PointDetailSaveDataParam();
        pointDetailSaveDataParam.setPointNum(IdWorkerHelperImpl.generate(BizIdType.POINT));
        pointDetailSaveDataParam.setUserNum(param.getUserNum());
        pointDetailSaveDataParam.setTitle(param.getTypeName());
        pointDetailSaveDataParam.setPointType(param.getTypeVal());
        pointDetailSaveDataParam.setPoint(param.getActureMoneyIn());
        pointDetailSaveDataParam.setDirection(PropertyInfoDirectionEnum.IN.getVal());
        pointDetailSaveDataParam.setBizId(param.getBizId().toString());
        pointDetailService.save(pointDetailSaveDataParam);

        // 更新用户资产
        PropertyInfoDOEiditView infoDoView = new PropertyInfoDOEiditView();
        infoDoView.setUserNum(param.getUserNum());
        infoDoView.setPoint(param.getActureMoneyIn());
        infoDoView.setGmtModified(new Date());
        propertyInfoDOMapperExtend.updatePropertyInfoAddPoint(infoDoView);

        // 统计播报汇总，不在事务中
        try {
            CountIncomeBroadcastParam countIncomeBroadcastParam = new CountIncomeBroadcastParam();
            countIncomeBroadcastParam.setUserNum(pointDetailSaveDataParam.getUserNum());
            countIncomeBroadcastParam.setMoney(pointDetailSaveDataParam.getPoint());
            countIncomeBroadcastParam.setIncomeType(PayTypeEnum.POINT.getVal());
            transactionDetailService.countIncomeBroadcast(countIncomeBroadcastParam);
        } catch (Exception ex){
            logger.error("收益计算统计播报时异常，{}",ex.getMessage());
            ex.getStackTrace();
        }

        return ResultCode.SUCCESS;
    }

}
