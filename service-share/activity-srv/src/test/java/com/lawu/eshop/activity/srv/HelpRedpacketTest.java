package com.lawu.eshop.activity.srv;

import java.math.BigDecimal;
import java.util.Random;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;

/**
 * 抽奖测试
 * 
 * @author jiangxinjun
 * @createDate 2018年1月5日
 * @updateDate 2018年1月5日
 */
public class HelpRedpacketTest {

    @Ignore
    @Test
    public void test() {
        BigDecimal maxRedpacket = new BigDecimal(80);
        BigDecimal multiple = new BigDecimal(0.5);
        Random random = new Random();
        
        BigDecimal totalAutoAmount = new BigDecimal(5000000);
        // 可用于分配的金额
        BigDecimal distributionAmount = totalAutoAmount.subtract(new BigDecimal(0));
        
        BigDecimal totalFinalMoney = new BigDecimal(0);
        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = new HelpRedpacketAttendDetailDO();
        helpRedpacketAttendDetailDO.setOriginalMoney(new BigDecimal(0));
        helpRedpacketAttendDetailDO.setFinalMoney(0);
        helpRedpacketAttendDetailDO.setHelpCount(3);
        
        for (;distributionAmount.doubleValue() > 0D;) {
            BigDecimal redpacketAmount = new BigDecimal(random.nextDouble() * (maxRedpacket.doubleValue()));
            redpacketAmount = redpacketAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal finalMoney = redpacketAmount.multiply(new BigDecimal(1 + multiple.doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
            // 如果剩余可分配金额小于最小红包金额
            if (distributionAmount.doubleValue() < totalFinalMoney.add(finalMoney).doubleValue()) {
                finalMoney = distributionAmount.subtract(totalFinalMoney).setScale(2, BigDecimal.ROUND_HALF_UP);
                redpacketAmount = new BigDecimal(finalMoney.doubleValue() / (1 + multiple.doubleValue() * helpRedpacketAttendDetailDO.getHelpCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            totalFinalMoney = totalFinalMoney.add(finalMoney);
            helpRedpacketAttendDetailDO.setOriginalMoney(helpRedpacketAttendDetailDO.getOriginalMoney() != null ? helpRedpacketAttendDetailDO.getOriginalMoney().add(redpacketAmount) : redpacketAmount);
            helpRedpacketAttendDetailDO.setFinalMoney(helpRedpacketAttendDetailDO.getFinalMoney() != null ? helpRedpacketAttendDetailDO.getFinalMoney() + finalMoney.multiply(new BigDecimal(100)).intValue() : finalMoney.multiply(new BigDecimal(100)).intValue());
            distributionAmount = distributionAmount.subtract(totalFinalMoney);
            totalFinalMoney = new BigDecimal(0);
            if (totalAutoAmount.subtract(distributionAmount).doubleValue() != helpRedpacketAttendDetailDO.getFinalMoney().doubleValue()/100) {
                Assert.assertEquals(totalAutoAmount.subtract(distributionAmount).doubleValue(), helpRedpacketAttendDetailDO.getFinalMoney().doubleValue()/100, 0D);
            }
        }
        Assert.assertEquals(totalAutoAmount.doubleValue(), helpRedpacketAttendDetailDO.getFinalMoney().doubleValue() / 100, 0D);
    }
    
}
