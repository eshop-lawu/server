package com.lawu.eshop.activity.srv.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.HelpRedpacketAttendDetailDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketActivityService;
import com.lawu.framework.core.event.AsyncEventHandle;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年1月4日
 * @updateDate 2018年1月4日
 */
@Component
public class GenerateNormalRedpacketEventHandle implements AsyncEventHandle<GenerateNormalRedpacketEvent> {
    
    private static final Logger log = LoggerFactory.getLogger(GenerateNormalRedpacketEventHandle.class);

    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapperExtend helpRedpacketAttendDetailDOMapperExtend;
    
    @Override
    public void execute(GenerateNormalRedpacketEvent event) {
        HelpRedpacketActivityBO helpRedpacketActivityBO = helpRedpacketActivityService.get(event.getActivityId());
        
        //获取总抽奖用户数量（排除已经获取大奖的用户）
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        example.createCriteria().andActivityIdEqualTo(event.getActivityId()).andIsLuckyEqualTo(false).andAbnormalStatusEqualTo(AbnormalStatusEnum.NORMAL.getVal());
        long count = helpRedpacketAttendDetailDOMapper.countByExample(example);
        
        // 可用于分配的金额
        BigDecimal distributionAmount = event.getTotalAutoAmount().subtract(event.getRedpacketAmount());
        
        BigDecimal totalFinalMoney = new BigDecimal(0);
        int batchListSize = 100;
        List<HelpRedpacketAttendDetailDO> batchList = new ArrayList<>(batchListSize);
        // 奖池，保存中奖下标以及金额
        Set<Integer> przePool = new HashSet<>((int) count);
        // 红包金额已达上限
        Set<Integer> fullAmountPrzePool = new HashSet<>((int) count);
        // 随机分配红包金额
        Random random = new Random();
        // 循环生成方案
        for(;distributionAmount.doubleValue() > 0D;) {
            // 批量保存
            if (batchList.size() >= batchListSize || distributionAmount.compareTo(totalFinalMoney) <= 0 || (przePool.size() + fullAmountPrzePool.size()) >= count) {
                try {
                    helpRedpacketActivityService.batchSaveNormalRedpacket(batchList);
                    distributionAmount = distributionAmount.subtract(totalFinalMoney);
                    if (distributionAmount.doubleValue() < 0D) {
                        break;
                    }
                } catch (Exception e) {
                    log.error("批量保存失败", e);
                }
                // 清空批量保存集合
                batchList.clear();
                totalFinalMoney = new BigDecimal(0);
                if ((przePool.size() + fullAmountPrzePool.size()) >= count) {
                    przePool.clear();
                }
            }
            int randomIndex = (int) (random.nextDouble() * count);
            if (przePool.contains(randomIndex) || fullAmountPrzePool.contains(randomIndex)) {
                continue;
            }
            Integer id = helpRedpacketAttendDetailDOMapperExtend.getIdWithIndex(randomIndex, event.getActivityId(), false);
            HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(Long.valueOf(id));
            BigDecimal availableRedpacketAmount = helpRedpacketActivityBO.getMaxRedpacket().subtract(helpRedpacketAttendDetailDO.getOriginalMoney());
            BigDecimal redpacketAmount = new BigDecimal(random.nextDouble()).multiply(availableRedpacketAmount);
            redpacketAmount = redpacketAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal finalMoney = redpacketAmount.multiply(new BigDecimal(1 + helpRedpacketActivityBO.getMultiple().doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_DOWN);
            // 如果剩余可分配金额小于最小红包金额
            if (distributionAmount.doubleValue() < totalFinalMoney.add(finalMoney).doubleValue()) {
                finalMoney = distributionAmount.subtract(totalFinalMoney);
                redpacketAmount = new BigDecimal(finalMoney.doubleValue() / (1 + helpRedpacketActivityBO.getMultiple().doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_DOWN);
            }
            totalFinalMoney = totalFinalMoney.add(finalMoney);
            przePool.add(randomIndex);
            HelpRedpacketAttendDetailDO helpRedpacketAttendDetailUpdateDO = new HelpRedpacketAttendDetailDO();
            helpRedpacketAttendDetailUpdateDO.setId(helpRedpacketAttendDetailDO.getId());
            helpRedpacketAttendDetailUpdateDO.setOriginalMoney(helpRedpacketAttendDetailDO.getOriginalMoney() != null ? helpRedpacketAttendDetailDO.getOriginalMoney().add(redpacketAmount) : redpacketAmount);
            helpRedpacketAttendDetailUpdateDO.setFinalMoney(helpRedpacketAttendDetailDO.getFinalMoney() != null ? helpRedpacketAttendDetailDO.getFinalMoney() + finalMoney.multiply(new BigDecimal(100)).intValue() : finalMoney.multiply(new BigDecimal(100)).intValue());
            
            /*
             *  因为有小数,存在精度问题,当availableRedpacketAmount为0时,校验计算的金额是否等于保存的金额,如果不等于,补齐缺失那一部分
             *  如果计算的金额是相等的则不计入批量更新集合里面
             */
            BigDecimal calculationAmount = helpRedpacketAttendDetailUpdateDO.getOriginalMoney().multiply(new BigDecimal(1 + helpRedpacketActivityBO.getMultiple().doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_DOWN);
            BigDecimal storageAmount = new BigDecimal(helpRedpacketAttendDetailUpdateDO.getFinalMoney()).divide(new BigDecimal(100));
            if (calculationAmount.doubleValue() != storageAmount.doubleValue()) {
                BigDecimal compensationAmount = calculationAmount.subtract(storageAmount);
                if (distributionAmount.compareTo(compensationAmount) >= 0) {
                    if (compensationAmount.doubleValue() > 0D || redpacketAmount.doubleValue() != 0D) {
                        helpRedpacketAttendDetailUpdateDO.setFinalMoney(calculationAmount.multiply(new BigDecimal(100)).intValue());
                        totalFinalMoney = totalFinalMoney.add(compensationAmount);
                    }
                }
            } else if (availableRedpacketAmount.doubleValue() == 0D) {
                fullAmountPrzePool.add(randomIndex);
            }
            helpRedpacketAttendDetailUpdateDO.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
            helpRedpacketAttendDetailUpdateDO.setAllotTime(new Date());
            helpRedpacketAttendDetailUpdateDO.setGmtModified(new Date());
            batchList.add(helpRedpacketAttendDetailUpdateDO);
        }
    }
}
