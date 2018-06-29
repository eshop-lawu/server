package com.lawu.eshop.activity.srv.servcie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.param.DiamondConfigParam;
import com.lawu.eshop.activity.param.PowerTaskBaseConfigParam;
import com.lawu.eshop.activity.param.PowerTaskConfigParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.DiamondConfigBO;
import com.lawu.eshop.activity.srv.bo.PowerTaskConfigBO;
import com.lawu.eshop.activity.srv.bo.RichConfigBO;
import com.lawu.eshop.activity.srv.domain.RichConfigDO;
import com.lawu.eshop.activity.srv.mapper.RichConfigDOMapper;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;

/**
 * @author meishuquan
 * @date 2018/5/7.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichConfigServiceImplTest {

    @Autowired
    private RichConfigService richConfigService;

    @Autowired
    private RichConfigDOMapper richConfigDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void savePowerTaskConfig() {
        PowerTaskConfigParam param = new PowerTaskConfigParam();
        List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
        PowerTaskBaseConfigParam configParam = new PowerTaskBaseConfigParam();
        configParam.setType(PowerTaskTypeEnum.LOGIN);
        configParam.setTaskCount(1);
        configParam.setPowerCount(1);
        configParam.setStatus(PowerTaskStatusEnum.ENABLED);
        tasks.add(configParam);
        param.setTasks(tasks);
        param.setPowerEffectiveTime(new Date());
        richConfigService.savePowerTaskConfig(param);

        List<RichConfigDO> list = richConfigDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void effectiveJob() {
        PowerTaskConfigParam param = new PowerTaskConfigParam();
        List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
        PowerTaskBaseConfigParam configParam = new PowerTaskBaseConfigParam();
        configParam.setType(PowerTaskTypeEnum.LOGIN);
        configParam.setTaskCount(1);
        configParam.setPowerCount(1);
        configParam.setStatus(PowerTaskStatusEnum.ENABLED);
        tasks.add(configParam);
        param.setTasks(tasks);
        param.setPowerEffectiveTime(new Date());
        richConfigService.savePowerTaskConfig(param);

        DiamondConfigParam diamondConfigParam = new DiamondConfigParam();
        diamondConfigParam.setRichPopulation(300);
        diamondConfigParam.setCreationPeople(100);
        diamondConfigParam.setDailyDiamond(BigDecimal.valueOf(100));
        diamondConfigParam.setOnceDiamond(BigDecimal.valueOf(0.25));
        diamondConfigParam.setDailyDiamondLucky(BigDecimal.valueOf(20));
        diamondConfigParam.setOnceDiamondLucky(BigDecimal.valueOf(3));
        diamondConfigParam.setGainDiamondLuckyPower(10);
        diamondConfigParam.setGainDiamondLuckyConsumePower(5);
        diamondConfigParam.setGainDiamondLuckyScale(BigDecimal.valueOf(10));
        diamondConfigParam.setInitPower(30);
        diamondConfigParam.setInitReleaseTime("08:00");
        diamondConfigParam.setReleaseInterval(2);
        diamondConfigParam.setIsOpen(true);
        diamondConfigParam.setDiamondGrowTime(48);
        diamondConfigParam.setDiamondEffectiveTime(new Date());
        diamondConfigParam.setNotice("test");
        richConfigService.saveDiamondConfig(diamondConfigParam);
        richConfigService.effectiveJob();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPowerTaskConfig() {
        PowerTaskConfigParam param = new PowerTaskConfigParam();
        List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
        PowerTaskBaseConfigParam configParam = new PowerTaskBaseConfigParam();
        configParam.setType(PowerTaskTypeEnum.LOGIN);
        configParam.setTaskCount(1);
        configParam.setPowerCount(1);
        configParam.setStatus(PowerTaskStatusEnum.ENABLED);
        tasks.add(configParam);
        param.setTasks(tasks);
        param.setPowerEffectiveTime(new Date());
        richConfigService.savePowerTaskConfig(param);

        PowerTaskConfigBO configBO = richConfigService.getPowerTaskConfig();
        Assert.assertNotNull(configBO);
        Assert.assertEquals(param.getPowerEffectiveTime(), configBO.getPowerEffectiveTime());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRichConfig() {
        PowerTaskConfigParam param = new PowerTaskConfigParam();
        List<PowerTaskBaseConfigParam> tasks = new ArrayList<>();
        PowerTaskBaseConfigParam configParam = new PowerTaskBaseConfigParam();
        configParam.setType(PowerTaskTypeEnum.LOGIN);
        configParam.setTaskCount(1);
        configParam.setPowerCount(1);
        configParam.setStatus(PowerTaskStatusEnum.ENABLED);
        tasks.add(configParam);
        param.setTasks(tasks);
        param.setPowerEffectiveTime(new Date());
        richConfigService.savePowerTaskConfig(param);

        DiamondConfigParam diamondConfigParam = new DiamondConfigParam();
        diamondConfigParam.setRichPopulation(300);
        diamondConfigParam.setCreationPeople(100);
        diamondConfigParam.setDailyDiamond(BigDecimal.valueOf(100));
        diamondConfigParam.setOnceDiamond(BigDecimal.valueOf(0.25));
        diamondConfigParam.setDailyDiamondLucky(BigDecimal.valueOf(20));
        diamondConfigParam.setOnceDiamondLucky(BigDecimal.valueOf(3));
        diamondConfigParam.setGainDiamondLuckyPower(10);
        diamondConfigParam.setGainDiamondLuckyConsumePower(5);
        diamondConfigParam.setGainDiamondLuckyScale(BigDecimal.valueOf(10));
        diamondConfigParam.setInitPower(30);
        diamondConfigParam.setInitReleaseTime("08:00");
        diamondConfigParam.setReleaseInterval(2);
        diamondConfigParam.setIsOpen(true);
        diamondConfigParam.setDiamondGrowTime(48);
        diamondConfigParam.setDiamondEffectiveTime(new Date());
        diamondConfigParam.setNotice("test");
        richConfigService.saveDiamondConfig(diamondConfigParam);

        RichConfigBO configBO = richConfigService.getRichConfig();
        Assert.assertNotNull(configBO);
        Assert.assertEquals(diamondConfigParam.getNotice(), configBO.getNotice());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveDiamondConfig() {
        DiamondConfigParam param = new DiamondConfigParam();
        param.setRichPopulation(300);
        param.setCreationPeople(100);
        param.setDailyDiamond(BigDecimal.valueOf(100));
        param.setOnceDiamond(BigDecimal.valueOf(0.25));
        param.setDailyDiamondLucky(BigDecimal.valueOf(20));
        param.setOnceDiamondLucky(BigDecimal.valueOf(3));
        param.setGainDiamondLuckyPower(10);
        param.setGainDiamondLuckyConsumePower(5);
        param.setGainDiamondLuckyScale(BigDecimal.valueOf(10));
        param.setInitPower(30);
        param.setInitReleaseTime("08:00");
        param.setReleaseInterval(2);
        param.setIsOpen(true);
        param.setDiamondGrowTime(48);
        param.setDiamondEffectiveTime(new Date());
        param.setNotice("test");
        richConfigService.saveDiamondConfig(param);

        List<RichConfigDO> list = richConfigDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getDiamondConfig() {
        DiamondConfigParam param = new DiamondConfigParam();
        param.setRichPopulation(300);
        param.setCreationPeople(100);
        param.setDailyDiamond(BigDecimal.valueOf(100));
        param.setOnceDiamond(BigDecimal.valueOf(0.25));
        param.setDailyDiamondLucky(BigDecimal.valueOf(20));
        param.setOnceDiamondLucky(BigDecimal.valueOf(3));
        param.setGainDiamondLuckyPower(10);
        param.setGainDiamondLuckyConsumePower(5);
        param.setGainDiamondLuckyScale(BigDecimal.valueOf(10));
        param.setInitPower(30);
        param.setInitReleaseTime("08:00");
        param.setReleaseInterval(2);
        param.setIsOpen(true);
        param.setDiamondGrowTime(48);
        param.setDiamondEffectiveTime(new Date());
        param.setNotice("test");
        richConfigService.saveDiamondConfig(param);

        DiamondConfigBO configBO = richConfigService.getDiamondConfig();
        Assert.assertNotNull(configBO);
        Assert.assertEquals(param.getRichPopulation(), configBO.getRichPopulation());
    }

}
