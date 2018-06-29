package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.constants.GameDialPrizeTypeEnum;
import com.lawu.eshop.game.param.GameDailPrizeParam;
import com.lawu.eshop.game.query.GameDailPrizeQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameDialPrizeBO;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;
import com.lawu.eshop.game.srv.mapper.GameDialPrizeDOMapper;
import com.lawu.eshop.game.srv.service.GameDialPrizeService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameDialPrizeServiceImplTest {

    @Autowired
    private GameDialPrizeService gameDialPrizeService;

    @Autowired
    private GameDialPrizeDOMapper gameDialPrizeDOMapper;

    @Test
    @Transactional
    @Rollback
    public void listGameDialPrize() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setInventory(10);
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        List<GameDialPrizeBO> prizeBOS = gameDialPrizeService.listGameDialPrize(prizeDO.getGameDialId(), null);
        Assert.assertNotNull(prizeBOS);
        Assert.assertEquals(1, prizeBOS.size());
    }

    @Test
    @Transactional
    @Rollback
    public void page() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setName("test");
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setInventory(10);
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        GameDailPrizeQuery query = new GameDailPrizeQuery();
        query.setName("test");
        query.setStatusEnum(GameDialPrizeStatusEnum.VALID);
        Page<GameDialPrizeBO> page = gameDialPrizeService.page(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGameDialPrize() {
        GameDailPrizeParam param = new GameDailPrizeParam();
        param.setName("test");
        param.setGameDialId(100L);
        param.setFreightPrice(BigDecimal.ZERO);
        param.setPrizeType(GameDialPrizeTypeEnum.MONEY);
        gameDialPrizeService.updateGameDialPrize(param);

        List<GameDialPrizeDO> prizeDOS = gameDialPrizeDOMapper.selectByExample(null);
        Assert.assertNotNull(prizeDOS);
        Assert.assertEquals(1, prizeDOS.size());
    }

    @Test
    @Transactional
    @Rollback
    public void setGameDialPrizeStatus() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setName("test");
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setInventory(10);
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        gameDialPrizeService.setGameDialPrizeStatus(prizeDO.getId(), GameDialPrizeStatusEnum.INVALID);
        GameDialPrizeDO gameDialPrizeDO = gameDialPrizeDOMapper.selectByPrimaryKey(prizeDO.getId());
        Assert.assertNotNull(gameDialPrizeDO);
        Assert.assertEquals(GameDialPrizeStatusEnum.INVALID.getVal(), gameDialPrizeDO.getStatus());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameDialPrize() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setName("test");
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setInventory(10);
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        GameDialPrizeBO prizeBO = gameDialPrizeService.getGameDialPrize(prizeDO.getId());
        Assert.assertNotNull(prizeBO);
        Assert.assertEquals(prizeDO.getGameDialId(), prizeBO.getGameDialId());
    }

    @Test
    @Transactional
    @Rollback
    public void getLeftPrizeRate() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setName("test");
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        prizeDO.setInventory(10);
        prizeDO.setRate(BigDecimal.valueOf(10));
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        BigDecimal leftPrizeRate = gameDialPrizeService.getLeftPrizeRate(prizeDO.getGameDialId());
        Assert.assertNotNull(leftPrizeRate);
        Assert.assertEquals(leftPrizeRate.intValue(), (BigDecimal.valueOf(100).subtract(prizeDO.getRate())).intValue());
    }

}
