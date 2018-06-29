package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.DistributionStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.constants.DiamondRecordTitleConstant;
import com.lawu.eshop.activity.srv.domain.RichDiamondDistributionRecordDO;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDO;
import com.lawu.eshop.activity.srv.mapper.RichDiamondDistributionRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichDiamondRecordDOMapper;

/**
 * @author jiangxinjun
 * @createDate 2018年5月9日
 * @updateDate 2018年5月9日
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class DiamondDistributionRecordServiceImplTest {
    
    @Autowired
    private DiamondDistributionRecordService diamondDistributionRecordService;
    
    @Autowired
    private RichDiamondDistributionRecordDOMapper richDiamondDistributionRecordDOMapper;
    
    @Autowired
    private RichDiamondRecordDOMapper richDiamondRecordDOMapper;
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void save() {
        Long residents = 1L;
        BigDecimal expectedOrdinaryAllocations = BigDecimal.valueOf(100);
        Long recordId = diamondDistributionRecordService.save(residents, expectedOrdinaryAllocations);
        Assert.assertNotNull(recordId);
        RichDiamondDistributionRecordDO actual = richDiamondDistributionRecordDOMapper.selectByPrimaryKey(recordId);
        Assert.assertNotNull(actual);
        Assert.assertEquals(residents, actual.getResidents());
        Assert.assertEquals(expectedOrdinaryAllocations.doubleValue(), actual.getExpectedOrdinaryAllocations().doubleValue(), 0D);
        Assert.assertEquals(0L, actual.getAllocatedLuckyResidents().longValue());
        Assert.assertEquals(0L, actual.getAllocatedResidents().longValue());
        Assert.assertEquals(0L, actual.getLuckyResidents().longValue());
        Assert.assertEquals(DistributionStatusEnum.ALLOCATION.getVal(), actual.getStatus());
        Assert.assertEquals(BigDecimal.ZERO.doubleValue(), actual.getExpectedLuckyAllocations().doubleValue(), 0D);
        Assert.assertEquals(BigDecimal.ZERO.doubleValue(), actual.getRealLuckAllocations().doubleValue(), 0D);
        Assert.assertEquals(BigDecimal.ZERO.doubleValue(), actual.getRealOrdinaryAllocations().doubleValue(), 0D);
        Assert.assertNotNull(actual.getGmtCreate());
        Assert.assertNotNull(actual.getGmtModified());
        Assert.assertNull(actual.getGmtAllocationsComplete());
        Assert.assertNull(actual.getGmtGrantComplete());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void updateLuckyDiamondNumber() {
        RichDiamondDistributionRecordDO record = new RichDiamondDistributionRecordDO();
        record.setResidents(1L);
        record.setExpectedOrdinaryAllocations(BigDecimal.valueOf(100));
        record.setExpectedLuckyAllocations(BigDecimal.ZERO);
        record.setLuckyResidents(0L);
        record.setAllocatedResidents(0L);
        record.setRealOrdinaryAllocations(BigDecimal.ZERO);
        record.setAllocatedLuckyResidents(0L);
        record.setRealLuckAllocations(BigDecimal.ZERO);
        record.setStatus(DistributionStatusEnum.ALLOCATION.getVal());
        record.setGmtModified(new Date());
        record.setGmtCreate(new Date());
        richDiamondDistributionRecordDOMapper.insert(record);
        
        Long luckyResidents = 1L;
        BigDecimal expectedLuckyAllocations = BigDecimal.valueOf(10);
        Long recordId = diamondDistributionRecordService.updateLuckyDiamondNumber(luckyResidents, expectedLuckyAllocations);
        RichDiamondDistributionRecordDO actual = richDiamondDistributionRecordDOMapper.selectByPrimaryKey(recordId);
        Assert.assertEquals(luckyResidents.longValue(), actual.getLuckyResidents().longValue());
        Assert.assertEquals(expectedLuckyAllocations.doubleValue(), actual.getExpectedLuckyAllocations().doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void distributionCompleted() {
        RichDiamondDistributionRecordDO richDiamondDistributionRecordDO = new RichDiamondDistributionRecordDO();
        richDiamondDistributionRecordDO.setResidents(1L);
        richDiamondDistributionRecordDO.setExpectedOrdinaryAllocations(BigDecimal.valueOf(100));
        richDiamondDistributionRecordDO.setExpectedLuckyAllocations(BigDecimal.valueOf(10));
        richDiamondDistributionRecordDO.setLuckyResidents(1L);
        richDiamondDistributionRecordDO.setAllocatedResidents(0L);
        richDiamondDistributionRecordDO.setRealOrdinaryAllocations(BigDecimal.ZERO);
        richDiamondDistributionRecordDO.setAllocatedLuckyResidents(0L);
        richDiamondDistributionRecordDO.setRealLuckAllocations(BigDecimal.ZERO);
        richDiamondDistributionRecordDO.setStatus(DistributionStatusEnum.ALLOCATION.getVal());
        richDiamondDistributionRecordDO.setGmtModified(new Date());
        richDiamondDistributionRecordDO.setGmtCreate(new Date());
        richDiamondDistributionRecordDOMapper.insert(richDiamondDistributionRecordDO);
        
        RichDiamondRecordDO richDiamondRecordDO1 = new RichDiamondRecordDO();
        richDiamondRecordDO1.setUserNum("M00001");
        richDiamondRecordDO1.setAmount(BigDecimal.valueOf(0.25));
        richDiamondRecordDO1.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
        richDiamondRecordDO1.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
        richDiamondRecordDO1.setStatus(RichDiamondRecordStatusEnum.HASASSIGNED.getVal());
        richDiamondRecordDO1.setTitle(DiamondRecordTitleConstant.DAYGET);
        richDiamondRecordDO1.setType(RichDiamondRecordTypeEnum.ECOIN.getVal());
        richDiamondRecordDO1.setGmtCreate(new Date());
        richDiamondRecordDOMapper.insert(richDiamondRecordDO1);
        
        RichDiamondRecordDO richDiamondRecordDO2 = new RichDiamondRecordDO();
        richDiamondRecordDO2.setUserNum("M00001");
        richDiamondRecordDO2.setAmount(BigDecimal.valueOf(0.25));
        richDiamondRecordDO2.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
        richDiamondRecordDO2.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
        richDiamondRecordDO2.setStatus(RichDiamondRecordStatusEnum.HASASSIGNED.getVal());
        richDiamondRecordDO2.setTitle(DiamondRecordTitleConstant.DAYGET);
        richDiamondRecordDO2.setType(RichDiamondRecordTypeEnum.LUCKCOIN.getVal());
        richDiamondRecordDO2.setGmtCreate(new Date());
        richDiamondRecordDOMapper.insert(richDiamondRecordDO2);
        
        diamondDistributionRecordService.distributionCompleted();
        
        RichDiamondDistributionRecordDO actual = richDiamondDistributionRecordDOMapper.selectByPrimaryKey(richDiamondDistributionRecordDO.getId());
        Assert.assertEquals(1L, actual.getAllocatedResidents().longValue());
        Assert.assertEquals(richDiamondRecordDO1.getAmount().doubleValue(), actual.getRealOrdinaryAllocations().doubleValue(), 0d);
        Assert.assertEquals(1L, actual.getResidents().longValue());
        Assert.assertEquals(richDiamondRecordDO2.getAmount().doubleValue(), actual.getRealLuckAllocations().doubleValue(), 0d);
        Assert.assertEquals(DistributionStatusEnum.ALLOCATED.getVal(), actual.getStatus());
        Assert.assertNotNull(actual.getGmtAllocationsComplete());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void releaseCompleted() {
        RichDiamondDistributionRecordDO richDiamondDistributionRecordDO = new RichDiamondDistributionRecordDO();
        richDiamondDistributionRecordDO.setResidents(1L);
        richDiamondDistributionRecordDO.setExpectedOrdinaryAllocations(BigDecimal.valueOf(100));
        richDiamondDistributionRecordDO.setExpectedLuckyAllocations(BigDecimal.valueOf(10));
        richDiamondDistributionRecordDO.setLuckyResidents(1L);
        richDiamondDistributionRecordDO.setAllocatedResidents(0L);
        richDiamondDistributionRecordDO.setRealOrdinaryAllocations(BigDecimal.valueOf(0.25D));
        richDiamondDistributionRecordDO.setAllocatedLuckyResidents(0L);
        richDiamondDistributionRecordDO.setRealLuckAllocations(BigDecimal.valueOf(0.25D));
        richDiamondDistributionRecordDO.setStatus(DistributionStatusEnum.ALLOCATED.getVal());
        richDiamondDistributionRecordDO.setGmtModified(new Date());
        richDiamondDistributionRecordDO.setGmtCreate(new Date());
        richDiamondDistributionRecordDOMapper.insert(richDiamondDistributionRecordDO);
        
        diamondDistributionRecordService.releaseCompleted();
        
        RichDiamondDistributionRecordDO actual = richDiamondDistributionRecordDOMapper.selectByPrimaryKey(richDiamondDistributionRecordDO.getId());
        Assert.assertEquals(DistributionStatusEnum.ISSUED.getVal(), actual.getStatus());
        Assert.assertNotNull(actual.getGmtGrantComplete());
    }
}
