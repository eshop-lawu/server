package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DistributionStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.query.DiamondDistributionRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.DiamondDistributionRecordBO;
import com.lawu.eshop.activity.srv.converter.DiamondDistributionRecordConverter;
import com.lawu.eshop.activity.srv.domain.RichDiamondDistributionRecordDO;
import com.lawu.eshop.activity.srv.domain.RichDiamondDistributionRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.StatisticsDiamondNumberDO;
import com.lawu.eshop.activity.srv.domain.extend.StatisticsDiamondNumberParam;
import com.lawu.eshop.activity.srv.mapper.RichDiamondDistributionRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichDiamondInfoDOExtendMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichDiamondRecordDOExtendMapper;
import com.lawu.eshop.activity.srv.servcie.DiamondDistributionRecordService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;

/**
 * 金蛋分配记录实现类
 * @author jiangxinjun
 * @createDate 2018年5月7日
 * @updateDate 2018年5月7日
 */
@Service
public class DiamondDistributionRecordServiceImpl implements DiamondDistributionRecordService {
    
    @Autowired
    private RichDiamondDistributionRecordDOMapper richDiamondDistributionRecordDOMapper;
    
    @Autowired
    private RichDiamondRecordDOExtendMapper richDiamondRecordDOExtendMapper;
    
    @Autowired
    private RichDiamondInfoDOExtendMapper richDiamondInfoDOExtendMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long save(Long residents, BigDecimal expectedOrdinaryAllocations) {
        RichDiamondDistributionRecordDO record = new RichDiamondDistributionRecordDO();
        record.setResidents(residents);
        record.setExpectedOrdinaryAllocations(expectedOrdinaryAllocations);
        record.setExpectedLuckyAllocations(BigDecimal.ZERO);
        record.setLuckyResidents(0L);
        record.setAllocatedResidents(0L);
        record.setRealOrdinaryAllocations(BigDecimal.ZERO);
        record.setAllocatedLuckyResidents(0L);
        record.setRealLuckAllocations(BigDecimal.ZERO);
        record.setMerchantRealLuckAllocations(BigDecimal.ZERO);
        record.setMerchantRealOrdinaryAllocations(BigDecimal.ZERO);
        record.setStatus(DistributionStatusEnum.ALLOCATION.getVal());
        record.setGmtModified(new Date());
        record.setGmtCreate(new Date());
        richDiamondDistributionRecordDOMapper.insert(record);
        return record.getId();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long updateLuckyDiamondNumber(Long luckyResidents, BigDecimal expectedLuckyAllocations) {
        RichDiamondDistributionRecordDOExample example = new RichDiamondDistributionRecordDOExample();
        example.createCriteria().andStatusEqualTo(DistributionStatusEnum.ALLOCATION.getVal());
        List<RichDiamondDistributionRecordDO> list = richDiamondDistributionRecordDOMapper.selectByExample(example);
        RichDiamondDistributionRecordDO richDiamondDistributionRecordDO = list.get(0);
        
        // 更新幸运钻数据
        RichDiamondDistributionRecordDO diamondDistributionRecordUpdateDO = new RichDiamondDistributionRecordDO();
        diamondDistributionRecordUpdateDO.setId(richDiamondDistributionRecordDO.getId());
        diamondDistributionRecordUpdateDO.setLuckyResidents(luckyResidents);
        diamondDistributionRecordUpdateDO.setExpectedLuckyAllocations(expectedLuckyAllocations);
        diamondDistributionRecordUpdateDO.setGmtModified(new Date());
        richDiamondDistributionRecordDOMapper.updateByPrimaryKeySelective(diamondDistributionRecordUpdateDO);
        return richDiamondDistributionRecordDO.getId();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void distributionCompleted() {
        RichDiamondDistributionRecordDOExample example = new RichDiamondDistributionRecordDOExample();
        example.createCriteria().andStatusEqualTo(DistributionStatusEnum.ALLOCATION.getVal());
        example.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(0, 1);
        List<RichDiamondDistributionRecordDO> list = richDiamondDistributionRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        RichDiamondDistributionRecordDO richDiamondDistributionRecordDO = list.get(0);
        
        // 最终再次统计分配数量
        StatisticsDiamondNumberParam param = new StatisticsDiamondNumberParam();
        param.setUserType(UserTypeEnum.MEMBER.getValue());
        param.setType(RichDiamondRecordTypeEnum.ECOIN.getVal());
        StatisticsDiamondNumberDO statisticsDiamondNumber = richDiamondRecordDOExtendMapper.statisticsDiamondNumber(param);
        param.setType(RichDiamondRecordTypeEnum.LUCKCOIN.getVal());
        StatisticsDiamondNumberDO statisticsLuckyDiamondNumber = richDiamondRecordDOExtendMapper.statisticsDiamondNumber(param);
        
        param.setUserType(UserTypeEnum.MERCHANT.getValue());
        param.setType(RichDiamondRecordTypeEnum.ECOIN.getVal());
        StatisticsDiamondNumberDO statisticsMerchantDiamondNumber = richDiamondRecordDOExtendMapper.statisticsDiamondNumber(param);
        param.setType(RichDiamondRecordTypeEnum.LUCKCOIN.getVal());
        StatisticsDiamondNumberDO statisticsMerchantLuckyDiamondNumber = richDiamondRecordDOExtendMapper.statisticsDiamondNumber(param);
        
        RichDiamondDistributionRecordDO record = new RichDiamondDistributionRecordDO();
        record.setId(richDiamondDistributionRecordDO.getId());
        record.setAllocatedResidents(statisticsDiamondNumber.getCount());
        record.setRealOrdinaryAllocations(statisticsDiamondNumber.getDiamond());
        record.setAllocatedLuckyResidents(statisticsLuckyDiamondNumber.getCount());
        record.setRealLuckAllocations(statisticsLuckyDiamondNumber.getDiamond());
        record.setMerchantRealOrdinaryAllocations(statisticsMerchantDiamondNumber.getDiamond());
        record.setMerchantRealLuckAllocations(statisticsMerchantLuckyDiamondNumber.getDiamond());
        record.setStatus(DistributionStatusEnum.ALLOCATED.getVal());
        record.setGmtAllocationsComplete(new Date());
        record.setGmtModified(new Date());
        richDiamondDistributionRecordDOMapper.updateByPrimaryKeySelective(record);
        
        BigDecimal diamond = BigDecimal.ZERO;
        if (statisticsDiamondNumber.getDiamond() != null) {
            diamond = diamond.add(statisticsDiamondNumber.getDiamond());
        }
        if (statisticsMerchantDiamondNumber.getDiamond() != null) {
            diamond = diamond.add(statisticsMerchantDiamondNumber.getDiamond());
        }
        BigDecimal luckDiamond = BigDecimal.ZERO;
        if (statisticsLuckyDiamondNumber.getDiamond() != null) {
            luckDiamond = luckDiamond.add(statisticsDiamondNumber.getDiamond());
        }
        if (statisticsMerchantLuckyDiamondNumber.getDiamond() != null) {
            luckDiamond = luckDiamond.add(statisticsMerchantLuckyDiamondNumber.getDiamond());
        }
        // 更新瑞奇岛E钻数量
        richDiamondInfoDOExtendMapper.updateDiamondQuantity(diamond, luckDiamond);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releaseCompleted() {
        RichDiamondDistributionRecordDOExample example = new RichDiamondDistributionRecordDOExample();
        example.createCriteria().andStatusEqualTo(DistributionStatusEnum.ALLOCATED.getVal());
        example.setOrderByClause("gmt_create desc");
        RowBounds rowBounds = new RowBounds(0, 1);
        List<RichDiamondDistributionRecordDO> list = richDiamondDistributionRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (list.isEmpty()) {
            return;
        }
        RichDiamondDistributionRecordDO richDiamondDistributionRecordDO = list.get(0);
        
        RichDiamondDistributionRecordDO record = new RichDiamondDistributionRecordDO();
        record.setId(richDiamondDistributionRecordDO.getId());
        record.setStatus(DistributionStatusEnum.ISSUED.getVal());
        record.setGmtGrantComplete(new Date());
        record.setGmtModified(new Date());
        richDiamondDistributionRecordDOMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Page<DiamondDistributionRecordBO> page(DiamondDistributionRecordQueryParam param) {
        RichDiamondDistributionRecordDOExample example = new RichDiamondDistributionRecordDOExample();
        if (param.getStatus() != null) {
            example.createCriteria().andStatusEqualTo(param.getStatus().getVal());
        }
        long count = richDiamondDistributionRecordDOMapper.countByExample(example);
        Page<DiamondDistributionRecordBO> rtn = new Page<>();
        rtn.setCurrentPage(param.getCurrentPage());
        rtn.setTotalCount((int) count);
        if (count <= 0 || count <= param.getOffset()) {
            return rtn;
        }
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<RichDiamondDistributionRecordDO> list = richDiamondDistributionRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(DiamondDistributionRecordConverter.convert(list));
        return rtn;
    }

}
