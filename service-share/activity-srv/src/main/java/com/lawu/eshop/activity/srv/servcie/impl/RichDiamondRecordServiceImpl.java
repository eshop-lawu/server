package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.query.DiamondRecordQueryParam;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.constants.DiamondRecordTitleConstant;
import com.lawu.eshop.activity.srv.converter.RichDiamondRecordConvert;
import com.lawu.eshop.activity.srv.domain.RichAccountDO;
import com.lawu.eshop.activity.srv.domain.RichAccountDOExample;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDO;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.GetLuckyDiamondIdByIndexParam;
import com.lawu.eshop.activity.srv.domain.extend.TotalPowerParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdateAssignedQuantityParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdateLuckyAssignedQuantityParam;
import com.lawu.eshop.activity.srv.domain.extend.UpdatePowerSnapshootParam;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichDiamondRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichAccountDOExtendMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichDiamondDistributionRecordDOExtendMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichDiamondRecordDOExtendMapper;
import com.lawu.eshop.activity.srv.servcie.DiamondDistributionRecordService;
import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.activity.srv.servcie.RichDiamondRecordService;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@Service
public class RichDiamondRecordServiceImpl implements RichDiamondRecordService {

    @Autowired
    private RichDiamondRecordDOMapper richDiamondRecordDOMapper;
	
    @Autowired
    private RichAccountDOMapper richAccountDOMapper;
    
    @Autowired
    private RichAccountDOExtendMapper richAccountDOExtendMapper;
    
    @Autowired
    private RichDiamondRecordServiceImpl richDiamondRecordServiceImpl;
    
    @Autowired
    private RichDiamondRecordDOExtendMapper richDiamondRecordDOExtendMapper;
    
    @Autowired
    private RichConfigCacheService richConfigCacheService;
    
    @Autowired
    private RichDiamondDistributionRecordDOExtendMapper richDiamondDistributionRecordDOExtendMapper;
    
    @Autowired
    private DiamondDistributionRecordService diamondDistributionRecordService;
	
    @Override
    public List<RichDiamondRecordBO> getMyRichDiamondRecord(String userNum,RichDiamondRecordStatusEnum status,boolean flag) {
        RichDiamondRecordDOExample example =new RichDiamondRecordDOExample();
        example.createCriteria().andUserNumEqualTo(userNum)
            .andDirectionEqualTo(RichPowerRecordDirectionEnum.IN.getVal())
            .andStatusEqualTo(status.getVal());
        List<RichDiamondRecordDO>  list;
        if(flag){
            // E钻领取记录按照领取时间,倒序排序
            example.setOrderByClause("take_time desc");
            RowBounds rowBounds = new RowBounds(0, 10);
            list = richDiamondRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        }else{
            example.setOrderByClause("gmt_create desc");
            RowBounds rowBounds = new RowBounds(0, 20);
            list = richDiamondRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        }
        List<RichDiamondRecordBO> listBo = RichDiamondRecordConvert.convertRichDiamondRecordBO(list);
        return listBo;
    }
    
    @Override
    public void diamondDistribution() {
        // 判断是否已经到了钻石分配时间,以及达到了间隔时间
        Result<DiamondConfigCacheDTO> getCacheDiamondConfigResult = richConfigCacheService.getCacheDiamondConfig();
        DiamondConfigCacheDTO diamondConfig = getCacheDiamondConfigResult.getModel();
        Date initReleaseTime = DateUtil.formatDate(diamondConfig.getInitReleaseTime(), "HH:mm");
        Integer releaseInterval = diamondConfig.getReleaseInterval();
        Calendar releaseCalendar = Calendar.getInstance();
        releaseCalendar.setTime(initReleaseTime);
        Calendar now = Calendar.getInstance();
        Calendar releaseDateTime = Calendar.getInstance();
        releaseDateTime.set(Calendar.HOUR_OF_DAY, releaseCalendar.get(Calendar.HOUR_OF_DAY));
        releaseDateTime.set(Calendar.MINUTE, releaseCalendar.get(Calendar.MINUTE));
        releaseDateTime.set(Calendar.SECOND, 0);
        releaseDateTime.set(Calendar.MILLISECOND, 0);
        boolean isInSpecifiedTime = false;
        while (true) {
            releaseDateTime.add(Calendar.HOUR_OF_DAY, releaseInterval);
            if (now.get(Calendar.DAY_OF_YEAR) != releaseDateTime.get(Calendar.DAY_OF_YEAR)) {
                break;
            }
            // 提前一个小时分配钻石
            releaseDateTime.add(Calendar.HOUR_OF_DAY, -1);
            long offest = Math.abs(now.getTimeInMillis() - releaseDateTime.getTimeInMillis());
            // 误差控制在5分钟之内
            if (offest < 5 * 60 * 1000) {
                isInSpecifiedTime = true;
                break;
            }
            releaseDateTime.add(Calendar.HOUR_OF_DAY, 1);
        }
        if (!isInSpecifiedTime) {
            return;
        }
        
        // 记录用户动力值快照, 分页批量更新动力值快照
        updatePowerSnapshoot();
        
        // 分配普通E钻
        ordinaryDiamondDistribution(diamondConfig);
        
        // 分配幸运钻
        luckyDiamondDistribution(diamondConfig);
        
        // 更改状态为分配完成
        diamondDistributionRecordService.distributionCompleted();
    }
    
    /**
     * 更新用户动力值快照
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    public void updatePowerSnapshoot() {
        while (true) {
            int affectedRows = updatePowerSnapshootExcute();
            if (affectedRows <= 0) {
                break;
            }
        }
    }
    
    /**
     * 批量更新用户的动力值快照
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    @Transactional(rollbackFor = Exception.class)
    public int updatePowerSnapshootExcute() {
        UpdatePowerSnapshootParam updatePowerSnapshootParam = new UpdatePowerSnapshootParam();
        updatePowerSnapshootParam.setPageSize(100);
        return richAccountDOExtendMapper.updatePowerSnapshoot(updatePowerSnapshootParam);
    }
    
    /**
     * 分配E钻
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    public void ordinaryDiamondDistribution(DiamondConfigCacheDTO diamondConfig) {
        // 获取当前所有满足获取E钻条件用户的所有动力值
        TotalPowerParam param = new TotalPowerParam();
        param.setPower(0);
        Long totalPower = richAccountDOExtendMapper.totalPower(param);
        if (totalPower == null || totalPower <= 0) {
            return;
        }
        
        int stopGrowingTime = diamondConfig.getDiamondGrowTime();
        Date stopGrowingDate = DateUtil.add(new Date(), -stopGrowingTime, Calendar.HOUR_OF_DAY);
        RichAccountDOExample richAccountDOExample = new RichAccountDOExample();
        richAccountDOExample.createCriteria().andPowerSnapshootGreaterThan(0)
            .andLastTakeTimeGreaterThanOrEqualTo(stopGrowingDate);
        long count = richAccountDOMapper.countByExample(richAccountDOExample);
        
        Long recordId = diamondDistributionRecordService.save(count, diamondConfig.getDailyDiamond());
        
        for (int i = 0; i < count; ) {
            RowBounds rowBounds = new RowBounds(i, i += 100);
            List<RichAccountDO> list = richAccountDOMapper.selectByExampleWithRowbounds(richAccountDOExample, rowBounds);
            richDiamondRecordServiceImpl.ordinaryDiamondDistributionExcute(list, totalPower, diamondConfig, recordId);
        }
    }
    
    /**
     * 批量分配E钻
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    @Transactional(rollbackFor = Exception.class)
    public void ordinaryDiamondDistributionExcute(List<RichAccountDO> list, Long totalPower, DiamondConfigCacheDTO diamondConfig, Long recordId) {
        // 获取当次E钻分配总量
        BigDecimal totalOrdinaryDiamond = diamondConfig.getDailyDiamond();
        BigDecimal realOrdinaryAllocations = BigDecimal.ZERO;
        BigDecimal merchantRealOrdinaryAllocations = BigDecimal.ZERO;
        for (RichAccountDO item : list) {
            BigDecimal diamond = new BigDecimal(item.getPowerSnapshoot())
                    .multiply(totalOrdinaryDiamond).divide(new BigDecimal(totalPower), 5, RoundingMode.DOWN);
            if (diamond.compareTo(diamondConfig.getOnceDiamond()) > 0) {
                diamond = diamondConfig.getOnceDiamond();
            }
            if (UserTypeEnum.MEMBER == UserTypeEnum.getEnum(item.getUserType())) {
                realOrdinaryAllocations = realOrdinaryAllocations.add(diamond);
            } else {
                merchantRealOrdinaryAllocations = merchantRealOrdinaryAllocations.add(diamond);
            }
            RichDiamondRecordDO richDiamondRecordDO = new RichDiamondRecordDO();
            richDiamondRecordDO.setRelatedId(recordId);
            richDiamondRecordDO.setUserType(item.getUserType());
            richDiamondRecordDO.setAmount(diamond);
            richDiamondRecordDO.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
            richDiamondRecordDO.setGmtCreate(new Date());
            richDiamondRecordDO.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
            richDiamondRecordDO.setStatus(RichDiamondRecordStatusEnum.HASASSIGNED.getVal());
            richDiamondRecordDO.setTitle(DiamondRecordTitleConstant.DAYGET);
            richDiamondRecordDO.setType(RichDiamondRecordTypeEnum.ECOIN.getVal());
            richDiamondRecordDO.setUserNum(item.getUserNum());
            richDiamondRecordDOMapper.insert(richDiamondRecordDO);
        }
        UpdateAssignedQuantityParam param = new UpdateAssignedQuantityParam();
        param.setAllocatedResidents(Long.valueOf(list.size()));
        param.setRealOrdinaryAllocations(realOrdinaryAllocations);
        param.setMerchantRealOrdinaryAllocations(merchantRealOrdinaryAllocations);
        param.setId(recordId);
        richDiamondDistributionRecordDOExtendMapper.updateAssignedQuantity(param);
    }
    
    /**
     * 分配幸运钻
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    public void luckyDiamondDistribution(DiamondConfigCacheDTO diamondConfig) {
        /*
         *  获取当前所有满足获取E钻条件用户的数量
         */
        // 抽取幸运钻的最小动力值
        Integer minPower = diamondConfig.getGainDiamondLuckyPower();
        // 计算停止分配钻石的时间
        int stopGrowingTime = diamondConfig.getDiamondGrowTime();
        Date stopGrowingDate = DateUtil.add(new Date(), -stopGrowingTime, Calendar.HOUR_OF_DAY);
        RichAccountDOExample richAccountDOExample = new RichAccountDOExample();
        richAccountDOExample.createCriteria().andPowerSnapshootGreaterThanOrEqualTo(minPower)
            .andLastTakeTimeGreaterThanOrEqualTo(stopGrowingDate);
        long count = richAccountDOMapper.countByExample(richAccountDOExample);
        if (count <= 0) {
            return;
        }
        
        BigDecimal winnerPercentage = diamondConfig.getGainDiamondLuckyScale();
        int winnerNumber = new BigDecimal(count).multiply(winnerPercentage.divide(new BigDecimal(100))).intValue();
        if (winnerNumber <= 0 || winnerNumber >= count) {
            return;
        }
        // 下标以及下标对应的账户id
        Map<Long, Long> luckyDiamondPool = new HashMap<>(winnerNumber);
        GetLuckyDiamondIdByIndexParam getLuckyDiamondIdByIndexParam = new GetLuckyDiamondIdByIndexParam();
        getLuckyDiamondIdByIndexParam.setPower(minPower);
        getLuckyDiamondIdByIndexParam.setStopGrowingDate(stopGrowingDate);
        while (true) {
            Long idx = count - 1;
            if (count > 1) {
                idx =  ThreadLocalRandom.current().nextLong(count - 1);
            }
            if (!luckyDiamondPool.containsKey(idx)) {
                getLuckyDiamondIdByIndexParam.setIdx(idx);
                Long id = richAccountDOExtendMapper.getLuckyDiamondIdByIndex(getLuckyDiamondIdByIndexParam);
                luckyDiamondPool.put(idx, id);
            }
            if (luckyDiamondPool.size() >= winnerNumber) {
                break;
            }
        }  
        
        Long totalPower = 0L;
        List<Long> idList = new ArrayList<>(luckyDiamondPool.values());
        for (int i = 0; i < idList.size(); ) {
            TotalPowerParam param = new TotalPowerParam();
            param.setIds(idList.subList(i, i += 100 > idList.size() ? idList.size() : i));
            totalPower = totalPower + richAccountDOExtendMapper.totalPower(param);
        }
        
        Long recordId = diamondDistributionRecordService.updateLuckyDiamondNumber(Long.valueOf(winnerNumber), diamondConfig.getDailyDiamondLucky());
        
        for (int i = 0 ; i < idList.size(); ) {
            List<Long> subList = idList.subList(i, i += 100 > idList.size() ? idList.size() : i);
            richDiamondRecordServiceImpl.luckyDiamondDistributionExcute(subList, totalPower, diamondConfig, recordId);
        }
    }
    
    /**
     * 批量分配幸运钻
     * 
     * @author jiangxinjun
     * @createDate 2018年5月3日
     * @updateDate 2018年5月3日
     */
    @Transactional(rollbackFor = Exception.class)
    public void luckyDiamondDistributionExcute(List<Long> ids, Long totalPower, DiamondConfigCacheDTO diamondConfig, Long recordId) {
        // 获取当次幸运钻钻分配总量
        BigDecimal totalLuckyDiamond = diamondConfig.getDailyDiamondLucky();
        BigDecimal realOrdinaryAllocations = BigDecimal.ZERO;
        BigDecimal merchantRealOrdinaryAllocations = BigDecimal.ZERO;
        for (Long id : ids) {
            RichAccountDO richAccountDO = richAccountDOMapper.selectByPrimaryKey(id);
            BigDecimal luckyDiamond = new BigDecimal(richAccountDO.getPowerSnapshoot())
                    .multiply(totalLuckyDiamond).divide(new BigDecimal(totalPower), 5, RoundingMode.DOWN);
            if (luckyDiamond.compareTo(diamondConfig.getOnceDiamondLucky()) > 0) {
                luckyDiamond = diamondConfig.getOnceDiamondLucky();
            }
            if (UserTypeEnum.MEMBER == UserTypeEnum.getEnum(richAccountDO.getUserType())) {
                realOrdinaryAllocations = realOrdinaryAllocations.add(luckyDiamond);
            } else {
                merchantRealOrdinaryAllocations = merchantRealOrdinaryAllocations.add(luckyDiamond);
            }
            
            RichDiamondRecordDO richDiamondRecordDO = new RichDiamondRecordDO();
            richDiamondRecordDO.setRelatedId(recordId);
            richDiamondRecordDO.setUserType(richAccountDO.getUserType());
            richDiamondRecordDO.setAmount(luckyDiamond);
            richDiamondRecordDO.setDirection(RichPowerRecordDirectionEnum.IN.getVal());
            richDiamondRecordDO.setGmtCreate(new Date());
            richDiamondRecordDO.setSource(RichDiamondRecordSourceEnum.DAYGET.getVal());
            richDiamondRecordDO.setStatus(RichDiamondRecordStatusEnum.HASASSIGNED.getVal());
            richDiamondRecordDO.setTitle(DiamondRecordTitleConstant.DAYGET);
            richDiamondRecordDO.setType(RichDiamondRecordTypeEnum.LUCKCOIN.getVal());
            richDiamondRecordDO.setUserNum(richAccountDO.getUserNum());
            richDiamondRecordDOMapper.insert(richDiamondRecordDO);
        }
        UpdateLuckyAssignedQuantityParam param = new UpdateLuckyAssignedQuantityParam();
        param.setAllocatedLuckyResidents(Long.valueOf(ids.size()));
        param.setRealLuckyAllocations(realOrdinaryAllocations);
        param.setMerchantRealLuckyAllocations(merchantRealOrdinaryAllocations);
        param.setId(recordId);
        richDiamondDistributionRecordDOExtendMapper.updateLuckyAssignedQuantity(param);
    }
    
    @Override
    public void grantDiamonds() {
        // 判断是否到了发送时间
        Result<DiamondConfigCacheDTO> getCacheDiamondConfigResult = richConfigCacheService.getCacheDiamondConfig();
        DiamondConfigCacheDTO diamondConfig = getCacheDiamondConfigResult.getModel();
        Date initReleaseTime = DateUtil.formatDate(diamondConfig.getInitReleaseTime(), "HH:mm");
        Integer releaseInterval = diamondConfig.getReleaseInterval();
        Calendar releaseCalendar = Calendar.getInstance();
        releaseCalendar.setTime(initReleaseTime);
        Calendar now = Calendar.getInstance();
        Calendar releaseDateTime = Calendar.getInstance();
        releaseDateTime.set(Calendar.HOUR_OF_DAY, releaseCalendar.get(Calendar.HOUR_OF_DAY));
        releaseDateTime.set(Calendar.MINUTE, releaseCalendar.get(Calendar.MINUTE));
        releaseDateTime.set(Calendar.SECOND, 0);
        releaseDateTime.set(Calendar.MILLISECOND, 0);
        boolean isInSpecifiedTime = false;
        while (true) {
            releaseDateTime.add(Calendar.HOUR_OF_DAY, releaseInterval);
            if (now.get(Calendar.DAY_OF_YEAR) != releaseDateTime.get(Calendar.DAY_OF_YEAR)) {
                break;
            }
            long offest = Math.abs(now.getTimeInMillis() - releaseDateTime.getTimeInMillis());
            // 误差控制在5分钟之内
            if (offest < 5 * 60 * 1000) {
                isInSpecifiedTime = true;
                break;
            }
        }
        if (!isInSpecifiedTime) {
            return;
        }
        
        while (true) {
            int affectedRows = richDiamondRecordServiceImpl.grantDiamondsExcute();
            if (affectedRows <= 0) {
                break;
            }
        }
        
        diamondDistributionRecordService.releaseCompleted();
    }
    
    @Transactional(rollbackFor = Exception.class)
    public int grantDiamondsExcute() {
        return richDiamondRecordDOExtendMapper.grantDiamonds();
    }

    @Override
    public Page<RichDiamondRecordBO> page(DiamondRecordQueryParam param) {
        RichDiamondRecordDOExample example = new RichDiamondRecordDOExample();
        RichDiamondRecordDOExample.Criteria criteria = example.createCriteria();
        if (param.getDirection() != null) {
            criteria.andDirectionEqualTo(param.getDirection().getVal());
        }
        if (param.getRelatedId() != null) {
            criteria.andRelatedIdEqualTo(param.getRelatedId());
        }
        if (param.getStatus() != null) {
            criteria.andStatusEqualTo(param.getStatus().getVal());
        }
        if (param.getSource() != null) {
            criteria.andSourceEqualTo(param.getSource().getVal());
        }
        if (param.getType() != null) {
            criteria.andTypeEqualTo(param.getType().getVal());
        }
        if (StringUtils.isNotBlank(param.getUserNum())) {
            criteria.andUserNumEqualTo(param.getUserNum());
        }
        if (param.getUserType() != null) {
            criteria.andUserTypeEqualTo(param.getUserType().getValue());
        }
        long count = richDiamondRecordDOMapper.countByExample(example);
        Page<RichDiamondRecordBO> rtn = new Page<>();
        rtn.setTotalCount((int) count);
        rtn.setCurrentPage(param.getCurrentPage());
        if (count <= 0 || param.getOffset() >= count) {
            return rtn;
        }
        example.setOrderByClause("gmt_create DESC");
        RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
        List<RichDiamondRecordDO> list = richDiamondRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        rtn.setRecords(RichDiamondRecordConvert.convertRichDiamondRecordBO(list));
        return rtn;
    }
}
