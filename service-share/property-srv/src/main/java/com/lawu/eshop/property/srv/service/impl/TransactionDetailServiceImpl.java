package com.lawu.eshop.property.srv.service.impl;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.eshop.property.constants.*;
import com.lawu.eshop.property.param.*;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailMonthlyBillOfMerchantForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMemberForeignParam;
import com.lawu.eshop.property.param.foreign.TransactionDetailQueryForMerchantForeignParam;
import com.lawu.eshop.property.srv.bo.*;
import com.lawu.eshop.property.srv.converter.TotalSalesConverter;
import com.lawu.eshop.property.srv.converter.TransactionDetailConverter;
import com.lawu.eshop.property.srv.converter.UserIncomeExpenditureConverter;
import com.lawu.eshop.property.srv.domain.*;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample.Criteria;
import com.lawu.eshop.property.srv.domain.extend.*;
import com.lawu.eshop.property.srv.mapper.IncomeDailySummaryDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.WithdrawCashDOMapper;
import com.lawu.eshop.property.srv.mapper.extend.IncomeDailySummaryDOMapperExtend;
import com.lawu.eshop.property.srv.mapper.extend.TransactionDetailExtendDOMapper;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 交易明细服务实现
 *
 * @author Sunny
 * @date 2017/3/29
 */
@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {

    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;

    @Autowired
    private TransactionDetailExtendDOMapper transactionDetailExtendDOMapper;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    private WithdrawCashDOMapper withdrawCashDOMapper;

    @Autowired
    private IncomeDailySummaryDOMapper incomeDailySummaryDOMapper;

    @Autowired
    private IncomeDailySummaryDOMapperExtend incomeDailySummaryDOMapperExtend;

    /**
     * 根据用户编号、查询参数分页查询交易明细  - 商家
     *
     * @param userNum 用户编号
     * @param param   查询参数
     * @return
     */
    @Override
    public Page<TransactionDetailBO> findPageByUserNumForMerchant(String userNum, TransactionDetailQueryForMerchantParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);
        if (param.getConsumptionType() != null) {
            criteria.andDirectionEqualTo(param.getConsumptionType().getValue());
        }
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);

        Page<TransactionDetailBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(count);

        // 如果返回的总记录为0，直接返回page
        if (count <= 0) {
            return page;
        }

        transactionDetailDOExample.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        List<TransactionDetailBO> transactionDetailBOS = TransactionDetailConverter.convertBOS(transactionDetailDOMapper.selectByExampleWithRowbounds(transactionDetailDOExample, rowBounds));
        page.setRecords(transactionDetailBOS);

        return page;
    }

    /**
     * 根据用户编号、查询参数分页查询交易明细  - 买家
     *
     * @param userNum 用户编号
     * @param param   查询参数
     * @return
     */
    @Deprecated
    @Override
    public Page<TransactionDetailBO> findPageByUserNumForMember(String userNum, TransactionDetailQueryForMemberParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);

        if (param.getTransactionType() != null) {
            criteria.andTransactionTypeEqualTo(param.getTransactionType().getValue());
        }

        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);

        Page<TransactionDetailBO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(count);

        // 如果返回的总记录为0，直接返回page
        if (count <= 0) {
            return page;
        }

        transactionDetailDOExample.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        List<TransactionDetailDO> list = transactionDetailDOMapper.selectByExampleWithRowbounds(transactionDetailDOExample, rowBounds);

        page.setRecords(TransactionDetailConverter.convertBOS(list));

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int save(TransactionDetailSaveDataParam param) {

        TransactionDetailDO transactionDetailDO = new TransactionDetailDO();
        if (param.getPreviousAmount() == null) {
            PropertyInfoDOExample example = new PropertyInfoDOExample();
            example.createCriteria().andUserNumEqualTo(param.getUserNum());
            List<PropertyInfoDO> propertyInfoList = propertyInfoDOMapper.selectByExample(example);
            transactionDetailDO.setPreviousAmount((propertyInfoList == null || propertyInfoList.isEmpty()) ? new BigDecimal(0) : propertyInfoList.get(0).getBalance());
        } else {
            transactionDetailDO.setPreviousAmount(param.getPreviousAmount());
        }
        //保存省市区用于代理商区域统计
        if (param.getRegionPath() != null && !"".equals(param.getRegionPath()) && !"null".equals(param.getRegionPath())) {
            String[] regions = param.getRegionPath().split("/");
            transactionDetailDO.setProvinceId(regions.length > 0 ? Integer.valueOf(regions[0]) : 0);
            transactionDetailDO.setCityId(regions.length > 1 ? Integer.valueOf(regions[1]) : 0);
            transactionDetailDO.setAreaId(regions.length > 2 ? Integer.valueOf(regions[2]) : 0);
        }
        transactionDetailDO.setTitle(param.getTitle());
        transactionDetailDO.setTransactionNum(IdWorkerHelperImpl.generate(BizIdType.TRANSACTION));
        transactionDetailDO.setUserNum(param.getUserNum());
        transactionDetailDO.setTransactionType(param.getTransactionType());
        transactionDetailDO.setTransactionAccount(param.getTransactionAccount());
        transactionDetailDO.setTransactionAccountType(param.getTransactionAccountType());
        transactionDetailDO.setAmount(param.getAmount());
        transactionDetailDO.setDirection(param.getDirection());
        transactionDetailDO.setThirdTransactionNum(param.getThirdTransactionNum() == null ? "" : param.getThirdTransactionNum());
        transactionDetailDO.setBizId(param.getBizId() == null ? "" : param.getBizId());
        transactionDetailDO.setRemark(param.getRemark());
        transactionDetailDO.setGmtCreate(param.getGmtExecute() == null ? new Date() : param.getGmtExecute());
        transactionDetailDO.setBizNum(param.getBizNum() == null ? "" : param.getBizNum());
        transactionDetailDO.setTransactionDesc(param.getTransactionDesc() == null ? "" : param.getTransactionDesc());
        transactionDetailDOMapper.insertSelective(transactionDetailDO);
        param.setId(transactionDetailDO.getId());
        return ResultCode.SUCCESS;
    }

    @Override
    public boolean verifyOrderIsPaySuccess(NotifyCallBackParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(param.getUserNum()).andThirdTransactionNumEqualTo(param.getTradeNo());
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Page<TransactionDetailBO> getBackageRechargePageList(TransactionDetailQueryForBackageParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        if (StringUtils.isNotEmpty(param.getUserNum())) {
            criteria.andUserNumEqualTo(param.getUserNum());
        }

        List<Byte> transactionTypeList = new ArrayList<>();
        if (param.getMemberTransactionType() == null && param.getMerchantTransactionType() == null) {
            transactionTypeList.add(MemberTransactionTypeEnum.BACKAGE.getValue());
            transactionTypeList.add(MemberTransactionTypeEnum.RECHARGE_BALANCE.getValue());
            transactionTypeList.add(MerchantTransactionTypeEnum.BACKAGE.getValue());
            transactionTypeList.add(MerchantTransactionTypeEnum.RECHARGE.getValue());
            criteria.andTransactionTypeIn(transactionTypeList);
        } else {
            if (param.getMemberTransactionType() != null) {
                transactionTypeList.add(MemberTransactionTypeEnum.BACKAGE.getValue());
                transactionTypeList.add(MemberTransactionTypeEnum.RECHARGE_BALANCE.getValue());
                criteria.andTransactionTypeIn(transactionTypeList);
            }
            if (param.getMerchantTransactionType() != null) {
                transactionTypeList.add(MerchantTransactionTypeEnum.BACKAGE.getValue());
                transactionTypeList.add(MerchantTransactionTypeEnum.RECHARGE.getValue());
                criteria.andTransactionTypeIn(transactionTypeList);
            }
        }
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);

        Page<TransactionDetailBO> page = new Page<TransactionDetailBO>();
        page.setCurrentPage(param.getCurrentPage());
        page.setTotalCount(count);

        // 如果返回的总记录为0，直接返回page
        if (count <= 0) {
            return page;
        }

        transactionDetailDOExample.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());

        List<TransactionDetailBO> transactionDetailBOS = TransactionDetailConverter.convertBOS(transactionDetailDOMapper.selectByExampleWithRowbounds(transactionDetailDOExample, rowBounds));
        page.setRecords(transactionDetailBOS);

        return page;
    }

    /**
     * 查询平台销售金额
     *
     * @param param
     * @return
     */
    @Override
    public List<TotalSalesBO> selectTotalSales(TotalSalesQueryParam param) {
        TotalSalesQueryExample example = new TotalSalesQueryExample();
        String dateStr = DateUtil.getDateFormat(param.getDate(), "yyyy-MM-dd");
        example.setStart(DateUtil.formatDate(dateStr + " 00:00:00", DateUtil.DATETIME_DEFAULT_FORMAT));
        example.setEnd(DateUtil.formatDate(dateStr + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT));
        List<TotalSalesDO> totalSalesDOList = transactionDetailExtendDOMapper.selectTotalSales(example);
        return TotalSalesConverter.convertTotalSalesBOList(totalSalesDOList);
    }

    /**
     * 查询用户收入支出
     *
     * @param param
     * @return
     */
    @Override
    public List<UserIncomeExpenditureBO> selectUserIncomeExpenditure(UserIncomeExpenditureQueryParam param) {
        UserIncomeExpenditureExample example = new UserIncomeExpenditureExample();
        String firstDateStr = DateUtil.getDateFormat(DateUtil.getFirstDayOfMonth(param.getDate()), "yyyy-MM-dd");
        String lastDateStr = DateUtil.getDateFormat(DateUtil.getLastDayOfMonth(param.getDate()), "yyyy-MM-dd");
        example.setStart(DateUtil.formatDate(firstDateStr + " 00:00:00", DateUtil.DATETIME_DEFAULT_FORMAT));
        example.setEnd(DateUtil.formatDate(lastDateStr + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT));
        example.setOffset(param.getOffset());
        example.setPageSize(param.getPageSize());
        List<UserIncomeExpenditureDO> userIncomeExpenditureDOList = transactionDetailExtendDOMapper.selectUserIncomeExpenditure(example);
        return UserIncomeExpenditureConverter.convertUserIncomeExpenditureBOList(userIncomeExpenditureDOList);
    }

    @Override
    public List<IncomeMsgBO> getIncomeMsgDataList(String begin, String end, int offset, int pageSize) {
        IncomeMsgExample example = new IncomeMsgExample();
        example.setBegin(begin);
        example.setEnd(end);
        example.setOffset(offset);
        example.setPageSize(pageSize);
        List<IncomeMsgDOView> list = transactionDetailExtendDOMapper.getIncomeMsgDataList(example);
        List<IncomeMsgBO> bos = new ArrayList<>();
        for (IncomeMsgDOView view : list) {
            IncomeMsgBO bo = new IncomeMsgBO();
            bo.setType(view.getbType());
            bo.setMoney(view.getMoney());
            bo.setUserNum(view.getUserNum());
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public List<IncomeMsgBO> getIncomeMsgTotalDataList(String begin, String end) {
        IncomeMsgExample example = new IncomeMsgExample();
        example.setBegin(begin);
        example.setEnd(end);
        List<IncomeMsgDOView> list = transactionDetailExtendDOMapper.getIncomeMsgTotalDataList(example);
        List<IncomeMsgBO> bos = new ArrayList<>();
        for (IncomeMsgDOView view : list) {
            IncomeMsgBO bo = new IncomeMsgBO();
            bo.setMoney(view.getMoney());
            bo.setUserNum(view.getUserNum());
            bo.setPayTypeEnum(PayTypeEnum.BALANCE);
            bos.add(bo);
        }
        return bos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int summaryIncome(List<IncomeMsgBO> bos) {
        for(IncomeMsgBO bo : bos){
            IncomeDailySummaryDO incomeSummaryDO = new IncomeDailySummaryDO();
            incomeSummaryDO.setUserNum(bo.getUserNum());
            if(bo.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
                incomeSummaryDO.setUserType(UserTypeEnum.MEMBER.getVal());
            } else if(bo.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
                incomeSummaryDO.setUserType(UserTypeEnum.MERCHANT.getVal());
            }
            incomeSummaryDO.setMoney(bo.getMoney());
            incomeSummaryDO.setIncomeType(bo.getPayTypeEnum().getVal());
            incomeSummaryDO.setGmtReport(DateUtil.formatDate(DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),DateUtil.DATE_DEFAULT_FORMAT),DateUtil.DATE_DEFAULT_FORMAT));
            incomeSummaryDO.setGmtCreate(new Date());

            IncomeDailySummaryDOExample incomeDailySummaryDOExampleDel = new IncomeDailySummaryDOExample();
            incomeDailySummaryDOExampleDel.createCriteria().andGmtReportEqualTo(DateUtil.formatDate(DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),DateUtil.DATE_DEFAULT_FORMAT),DateUtil.DATE_DEFAULT_FORMAT)).andUserNumEqualTo(bo.getUserNum()).andIncomeTypeEqualTo(incomeSummaryDO.getIncomeType());
            incomeDailySummaryDOMapper.deleteByExample(incomeDailySummaryDOExampleDel);

            incomeDailySummaryDOMapper.insertSelective(incomeSummaryDO);
        }
        return ResultCode.SUCCESS;
    }

    @Override
    public int countIncomeBroadcast(CountIncomeBroadcastParam countIncomeBroadcastParam) {
        IncomeDailySummaryDOExample incomeDailySummaryDOExample = new IncomeDailySummaryDOExample();
        Date gmtReport = DateUtil.formatDate(DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),DateUtil.DATE_DEFAULT_FORMAT),DateUtil.DATE_DEFAULT_FORMAT);
        incomeDailySummaryDOExample.createCriteria().andGmtReportEqualTo(gmtReport).andUserNumEqualTo(countIncomeBroadcastParam.getUserNum()).andIncomeTypeEqualTo(countIncomeBroadcastParam.getIncomeType());
        int count = new Long(incomeDailySummaryDOMapper.countByExample(incomeDailySummaryDOExample)).intValue();
        if(count == 0) {
            IncomeDailySummaryDO incomeSummaryDO = new IncomeDailySummaryDO();
            incomeSummaryDO.setUserNum(countIncomeBroadcastParam.getUserNum());
            if(countIncomeBroadcastParam.getUserNum().startsWith(UserCommonConstant.MEMBER_NUM_TAG)){
                incomeSummaryDO.setUserType(UserTypeEnum.MEMBER.getVal());
            } else if(countIncomeBroadcastParam.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)){
                incomeSummaryDO.setUserType(UserTypeEnum.MERCHANT.getVal());
            }
            incomeSummaryDO.setMoney(countIncomeBroadcastParam.getMoney());
            incomeSummaryDO.setIncomeType(countIncomeBroadcastParam.getIncomeType());
            incomeSummaryDO.setGmtReport(gmtReport);
            incomeSummaryDO.setGmtCreate(new Date());
            incomeDailySummaryDOMapper.insertSelective(incomeSummaryDO);

        } else {
            countIncomeBroadcastParam.setGmtReport(DateUtil.getDateFormat(DateUtil.getDayBefore(new Date()),DateUtil.DATE_DEFAULT_FORMAT));
            incomeDailySummaryDOMapperExtend.addMoeny(countIncomeBroadcastParam);

        }
        return ResultCode.SUCCESS;
    }

    /**
     * 查询平台销售金额 group by area
     *
     * @param param
     * @return
     */
    @Override
    public List<TotalSalesGroupByAreaBO> selectTotalSalesGroupByArea(TotalSalesQueryParam param) {
        TotalSalesQueryExample example = new TotalSalesQueryExample();
        String dateStr = DateUtil.getDateFormat(param.getDate(), "yyyy-MM-dd");
        example.setStart(DateUtil.formatDate(dateStr + " 00:00:00", DateUtil.DATETIME_DEFAULT_FORMAT));
        example.setEnd(DateUtil.formatDate(dateStr + " 23:59:59", DateUtil.DATETIME_DEFAULT_FORMAT));
        List<TotalSalesGroupByAreaDO> totalSalesDOList = transactionDetailExtendDOMapper
                .selectTotalSalesGroupByArea(example);
        List<TotalSalesGroupByAreaBO> boList = new ArrayList<TotalSalesGroupByAreaBO>();
        if (totalSalesDOList != null && !totalSalesDOList.isEmpty()) {
            for (TotalSalesGroupByAreaDO DO : totalSalesDOList) {
                TotalSalesGroupByAreaBO bo = new TotalSalesGroupByAreaBO();
                bo.setAmount(DO.getAmount());
                bo.setAreaId(DO.getAreaId());
                bo.setCityId(DO.getCityId());
                bo.setProvinceId(DO.getProvinceId());
                bo.setTransactionType(MerchantTransactionTypeEnum.getEnum(DO.getTransactionType()));
                bo.setType(DO.getType());
                boList.add(bo);
            }
        }
        return boList;
    }

    @Override
    public boolean verifyByUserNumAndTransactionTypeAndBizId(BalancePayDataParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        Byte transactionType = param.getMemberTransactionTypeEnum().getValue();
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            transactionType = param.getMerchantTransactionTypeEnum().getValue();
        }
        criteria.andUserNumEqualTo(param.getUserNum()).andTransactionTypeEqualTo(transactionType).andBizIdEqualTo(param.getBizIds());
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyByUserNumAndTransactionTypeAndBizId(BalancePayValidateDataParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        Byte transactionType = param.getMemberTransactionTypeEnum().getValue();
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            transactionType = param.getMerchantTransactionTypeEnum().getValue();
        }
        criteria.andUserNumEqualTo(param.getUserNum()).andTransactionTypeEqualTo(transactionType).andBizIdEqualTo(param.getBizIds());
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean verifyOrderByUserNumAndTransactionType(BalancePayValidateDataParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        Byte transactionType = param.getMemberTransactionTypeEnum().getValue();
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            transactionType = param.getMerchantTransactionTypeEnum().getValue();
        }
        criteria.andUserNumEqualTo(param.getUserNum()).andTransactionTypeEqualTo(transactionType);
        transactionDetailDOExample.setOrderByClause(" id desc ");
        List<TransactionDetailDO> DOList = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);
        for (TransactionDetailDO tdo : DOList) {
            if (param.getBizIds().contains(",")) {
                if (tdo.getBizId().equals(param.getBizIds())) {
                    return true;
                }
            } else {
                String bizIds = tdo.getBizId();
                String[] arrayBizId = bizIds.split(",");
                List<String> bizIdsList = Arrays.asList(arrayBizId);
                if (bizIdsList.contains(param.getBizIds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean verifyOrderByUserNumAndTransactionType(BalancePayDataParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        Byte transactionType = param.getMemberTransactionTypeEnum().getValue();
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            transactionType = param.getMerchantTransactionTypeEnum().getValue();
        }
        criteria.andUserNumEqualTo(param.getUserNum()).andTransactionTypeEqualTo(transactionType);
        transactionDetailDOExample.setOrderByClause(" id desc ");
        List<TransactionDetailDO> DOList = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);
        for (TransactionDetailDO tdo : DOList) {
            if (param.getBizIds().contains(",")) {
                if (tdo.getBizId().equals(param.getBizIds())) {
                    return true;
                }
            } else {
                String bizIds = tdo.getBizId();
                String[] arrayBizId = bizIds.split(",");
                List<String> bizIdsList = Arrays.asList(arrayBizId);
                if (bizIdsList.contains(param.getBizIds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String packageTitle(MemberTransactionTypeEnum memberTransactionTypeEnum, MerchantTransactionTypeEnum merchantTransactionTypeEnum, String title) {
        String titleRet = "";
        if (memberTransactionTypeEnum != null) {
            if (title == null || "".equals(title)) {
                titleRet = memberTransactionTypeEnum.getName();
            } else {
                if (MemberTransactionTypeEnum.MERCHANT_RED_SWEEP.getValue().equals(memberTransactionTypeEnum.getValue()) ||
                        MemberTransactionTypeEnum.PAY_ORDERS.getValue().equals(memberTransactionTypeEnum.getValue()) ||
                        MemberTransactionTypeEnum.PAY.getValue().equals(memberTransactionTypeEnum.getValue())) {
                    titleRet = title;
                } else if (MemberTransactionTypeEnum.MEMBER_RED_SWEEP.getValue().equals(memberTransactionTypeEnum.getValue())) {
                    titleRet = memberTransactionTypeEnum.getName() + "-来自" + title;
                } else {
                    titleRet = title;
                }
            }
        } else if (merchantTransactionTypeEnum != null) {
            if (title == null || "".equals(title)) {
                titleRet = merchantTransactionTypeEnum.getName();
            } else {
                titleRet = title;
            }
        }
        return titleRet;
    }

    /**
     * 根据会员编号和查询参数分页查询交易明细
     *
     * @param userNum 会员编号
     * @param param   查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @Override
    public Page<TransactionDetailBO> page(String userNum, TransactionDetailQueryForMemberForeignParam param) {
        Page<TransactionDetailBO> rtn = new Page<>();
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);
        if (!MemberTransactionCategoryEnum.ALL.equals(param.getTransactionCategory())) {
            criteria.andTransactionTypeIn(MemberTransactionTypeEnum.getEnum(param.getTransactionCategory()));
        }
        if (param.getDate() != null) {
            criteria.andGmtCreateBetween(DateUtil.getFirstSecondOfDay(DateUtil.getFirstDayOfMonth(param.getDate())), DateUtil.getLastSecondOfDay(DateUtil.getLastDayOfMonth(param.getDate())));
        }
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);
        rtn.setCurrentPage(param.getCurrentPage());
        rtn.setTotalCount(count);
        // 如果返回的总记录为0，直接返回page
        if (count <= 0 || param.getOffset() >= count) {
            return rtn;
        }
        transactionDetailDOExample.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<TransactionDetailDO> list = transactionDetailDOMapper.selectByExampleWithRowbounds(transactionDetailDOExample, rowBounds);
        rtn.setRecords(TransactionDetailConverter.convertBOS(list));
        return rtn;
    }

    /**
     * 根据会员编号和查询参数月结账单
     *
     * @param userNum 会员编号
     * @param param   查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @Override
    public MonthlyBillBO monthlyBill(String userNum, TransactionDetailMonthlyBillOfMemberForeignParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);
        if (!MemberTransactionCategoryEnum.ALL.equals(param.getTransactionCategory())) {
            criteria.andTransactionTypeIn(MemberTransactionTypeEnum.getEnum(param.getTransactionCategory()));
        }
        if (param.getDate() != null) {
            criteria.andGmtCreateBetween(DateUtil.getFirstSecondOfDay(DateUtil.getFirstDayOfMonth(param.getDate())), DateUtil.getLastSecondOfDay(DateUtil.getLastDayOfMonth(param.getDate())));
        }
        List<MonthlyBillDO> monthlyBillDOList = transactionDetailExtendDOMapper.selectMonthlyBill(transactionDetailDOExample);
        return TransactionDetailConverter.convertMonthlyBillBO(monthlyBillDOList);
    }

    /**
     * 根据会员编号和查询参数分页查询交易明细
     *
     * @param userNum 会员编号
     * @param param   查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @Override
    public Page<TransactionDetailBO> page(String userNum, TransactionDetailQueryForMerchantForeignParam param) {
        Page<TransactionDetailBO> rtn = new Page<>();
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);
        if (!MerchantTransactionCategoryEnum.ALL.equals(param.getTransactionCategory())) {
            List<Byte> merchantTransactionType = MerchantTransactionTypeEnum.getEnum(param.getTransactionCategory());
            if (merchantTransactionType != null && !merchantTransactionType.isEmpty()) {
                criteria.andTransactionTypeIn(merchantTransactionType);
            }
        }
        if (param.getDate() != null) {
            criteria.andGmtCreateBetween(DateUtil.getFirstSecondOfDay(DateUtil.getFirstDayOfMonth(param.getDate())), DateUtil.getLastSecondOfDay(DateUtil.getLastDayOfMonth(param.getDate())));
        }
        int count = transactionDetailDOMapper.countByExample(transactionDetailDOExample);
        rtn.setCurrentPage(param.getCurrentPage());
        rtn.setTotalCount(count);
        // 如果返回的总记录为0，直接返回page
        if (count <= 0 || param.getOffset() >= count) {
            return rtn;
        }
        transactionDetailDOExample.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<TransactionDetailDO> list = transactionDetailDOMapper.selectByExampleWithRowbounds(transactionDetailDOExample, rowBounds);
        rtn.setRecords(TransactionDetailConverter.convertBOS(list));
        return rtn;
    }

    /**
     * 根据会员编号和查询参数月结账单
     *
     * @param userNum 会员编号
     * @param param   查询参数
     * @return
     * @author jiangxinjun
     * @date 2017年10月20日
     */
    @Override
    public MonthlyBillBO monthlyBill(String userNum, TransactionDetailMonthlyBillOfMerchantForeignParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        criteria.andUserNumEqualTo(userNum);
        if (!MerchantTransactionCategoryEnum.ALL.equals(param.getTransactionCategory())) {
            List<Byte> merchantTransactionType = MerchantTransactionTypeEnum.getEnum(param.getTransactionCategory());
            if (merchantTransactionType != null && !merchantTransactionType.isEmpty()) {
                criteria.andTransactionTypeIn(merchantTransactionType);
            }
        }
        if (param.getDate() != null) {
            criteria.andGmtCreateBetween(DateUtil.getFirstSecondOfDay(DateUtil.getFirstDayOfMonth(param.getDate())), DateUtil.getLastSecondOfDay(DateUtil.getLastDayOfMonth(param.getDate())));
        }
        List<MonthlyBillDO> monthlyBillDOList = transactionDetailExtendDOMapper.selectMonthlyBill(transactionDetailDOExample);
        return TransactionDetailConverter.convertMonthlyBillBO(monthlyBillDOList);
    }

    @Override
    public TransactionDetailH5InfoBO getById(Long id) {
        TransactionDetailDO transactionDetailDO = transactionDetailDOMapper.selectByPrimaryKey(id);
        TransactionDetailH5InfoBO bo = new TransactionDetailH5InfoBO();
        bo.setTransactionType(transactionDetailDO.getTransactionType());
        bo.setTitle(transactionDetailDO.getTitle());
        bo.setDirection(ConsumptionTypeEnum.getEnum(transactionDetailDO.getDirection()));
        bo.setAmount(transactionDetailDO.getAmount());
        bo.setTransactionDesc(transactionDetailDO.getTransactionDesc());
        bo.setGmtCreate(transactionDetailDO.getGmtCreate());
        bo.setTransactionNum(transactionDetailDO.getTransactionNum());
        if (TransactionPayTypeEnum.BALANCE.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
            bo.setOtherDesc("余额支付");
        } else if (TransactionPayTypeEnum.ALIPAY.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
            bo.setOtherDesc("支付宝支付");
        } else if (TransactionPayTypeEnum.WX.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
            bo.setOtherDesc("微信支付");
        } else if (TransactionPayTypeEnum.PLAT.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
            bo.setOtherDesc("平台支付");
        }

        if (MemberTransactionTypeEnum.WITHDRAW.getValue().equals(transactionDetailDO.getTransactionType()) || MerchantTransactionTypeEnum.WITHDRAW.getValue().equals(transactionDetailDO.getTransactionType())) {
            WithdrawCashDO withdrawCashDO = withdrawCashDOMapper.selectByPrimaryKey(Long.parseLong(transactionDetailDO.getBizId()));
            bo.setOtherDesc(withdrawCashDO.getRemark());
        }
        if (MemberTransactionTypeEnum.WITHDRAW_BACK.getValue().equals(transactionDetailDO.getTransactionType()) || MerchantTransactionTypeEnum.WITHDRAW_BACK.getValue().equals(transactionDetailDO.getTransactionType())) {
            bo.setOtherDesc("原路退回-余额");
        }
        if (MemberTransactionTypeEnum.REFUND_ORDERS.getValue().equals(transactionDetailDO.getTransactionType()) || MemberTransactionTypeEnum.USER_REDPACKET_ADD.getValue().equals(transactionDetailDO.getTransactionType()) || MerchantTransactionTypeEnum.AD_DOWN.getValue().equals(transactionDetailDO.getTransactionType())) {
            if (TransactionPayTypeEnum.BALANCE.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
                bo.setOtherDesc("原路退回-余额");
            } else if (TransactionPayTypeEnum.ALIPAY.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
                bo.setOtherDesc("原路退回-支付宝");
            } else if (TransactionPayTypeEnum.WX.getVal().equals(transactionDetailDO.getTransactionAccountType())) {
                bo.setOtherDesc("原路退回-微信");
            }
        }
        bo.setBizId(transactionDetailDO.getBizId());
        return bo;
    }

    @Override
    public boolean verifyThirdPayRepeat(NotifyCallBackParam param) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        Criteria criteria = transactionDetailDOExample.createCriteria();
        Byte transactionType = param.getMemberTransactionTypeEnum().getValue();
        if (param.getUserNum().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
            transactionType = param.getMerchantTransactionTypeEnum().getValue();
        }
        criteria.andUserNumEqualTo(param.getUserNum()).andTransactionTypeEqualTo(transactionType);
        transactionDetailDOExample.setOrderByClause(" id desc ");
        List<TransactionDetailDO> DOList = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);
        for (TransactionDetailDO tdo : DOList) {
            if (param.getBizIds().contains(",")) {
                if (tdo.getBizId().equals(param.getBizIds())) {
                    return true;
                }
            } else {
                String bizIds = tdo.getBizId();
                String[] arrayBizId = bizIds.split(",");
                List<String> bizIdsList = Arrays.asList(arrayBizId);
                if (bizIdsList.contains(param.getBizIds())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getAmountByThirdNumber(String tradeNo) {
        TransactionDetailDOExample transactionDetailDOExample = new TransactionDetailDOExample();
        transactionDetailDOExample.createCriteria().andThirdTransactionNumEqualTo(tradeNo);
        List<TransactionDetailDO> list = transactionDetailDOMapper.selectByExample(transactionDetailDOExample);

        if (list == null || list.isEmpty()) {
            return "-1";
        }
        BigDecimal amount = list.get(0).getAmount().setScale(2, BigDecimal.ROUND_DOWN);
        return amount.toString();
    }
}
