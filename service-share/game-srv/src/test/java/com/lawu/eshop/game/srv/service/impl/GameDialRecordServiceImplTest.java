package com.lawu.eshop.game.srv.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.constants.GameDialPrizeStatusEnum;
import com.lawu.eshop.game.constants.GameDialRecordStatusEnum;
import com.lawu.eshop.game.param.TakeLotteryParam;
import com.lawu.eshop.game.param.TakePartLotteryParam;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.eshop.game.query.GameDialRecordUserQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameDialRecordBO;
import com.lawu.eshop.game.srv.bo.GameDialRecordInfoBO;
import com.lawu.eshop.game.srv.domain.GameDialAccountDO;
import com.lawu.eshop.game.srv.domain.GameDialPrizeDO;
import com.lawu.eshop.game.srv.domain.GameDialRecordDO;
import com.lawu.eshop.game.srv.mapper.GameDialAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialPrizeDOMapper;
import com.lawu.eshop.game.srv.mapper.GameDialRecordDOMapper;
import com.lawu.eshop.game.srv.service.GameDialRecordService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameDialRecordServiceImplTest {

    @Autowired
    private GameDialRecordService gameDialRecordService;

    @Autowired
    private GameDialRecordDOMapper gameDialRecordDOMapper;

    @Autowired
    private GameDialPrizeDOMapper gameDialPrizeDOMapper;

    @Autowired
    private GameDialAccountDOMapper gameDialAccountDOMapper;

    @Test
    @Transactional
    @Rollback
    public void listGameDialRecord() {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setInventory(10);
        prizeDO.setStatus(GameDialPrizeStatusEnum.VALID.getVal());
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        recordDO.setGameDialPrizeId(prizeDO.getId());
        recordDO.setUserNum("M001");
        gameDialRecordDOMapper.insertSelective(recordDO);

        GameDialRecordUserQuery query = new GameDialRecordUserQuery();
        query.setUserNum(recordDO.getUserNum());
        query.setGameDialId(recordDO.getGameDialId());
        Page<GameDialRecordInfoBO> page = gameDialRecordService.listGameDialRecord(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void getTakePartLottery() {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        recordDO.setGameDialPrizeId(10L);
        recordDO.setUserNum("M001");
        gameDialRecordDOMapper.insertSelective(recordDO);

        Long result = gameDialRecordService.getTakePartLottery(recordDO.getGameDialId(), recordDO.getUserNum(), GameDialRecordStatusEnum.GET_LOTTERY);
        Assert.assertNotNull(result);
        Assert.assertEquals(recordDO.getId(), result);
    }

    @Test
    @Transactional
    @Rollback
    public void saveGameDialRecord() throws Exception {
        GameDialAccountDO accountDO = new GameDialAccountDO();
        accountDO.setUserNum("M001");
        accountDO.setFreeCount(1);
        gameDialAccountDOMapper.insertSelective(accountDO);

        TakePartLotteryParam param = new TakePartLotteryParam();
        param.setUserId(100L);
        param.setUserNum("M001");
        param.setUserAccount("13666666666");
        param.setGameDialId(10L);
        param.setStatusEnum(GameDialRecordStatusEnum.TAKE_PART_LOTTERY);
        param.setPayPoint(param.getPayPoint());
        Long result = gameDialRecordService.saveGameDialRecord(param);
        Assert.assertNotNull(result);

        GameDialAccountDO gameDialAccountDO = gameDialAccountDOMapper.selectByPrimaryKey(accountDO.getId());
        Assert.assertNotNull(gameDialAccountDO);
        Assert.assertEquals(0, gameDialAccountDO.getFreeCount().intValue());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameDialRecord() throws Exception {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setGameDialPrizeId(10L);
        recordDO.setGameDialId(100L);
        gameDialRecordDOMapper.insertSelective(recordDO);

        GameDialRecordBO recordBO = gameDialRecordService.getGameDialRecord(recordDO.getId());
        Assert.assertNotNull(recordBO);
        Assert.assertEquals(recordDO.getGameDialId(), recordBO.getGameDialId());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGameDialRecordStatus() throws Exception {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setGameDialPrizeId(10L);
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        gameDialRecordDOMapper.insertSelective(recordDO);

        gameDialRecordService.updateGameDialRecordStatus(recordDO.getId(), GameDialRecordStatusEnum.TAKE_LOTTERY);
        GameDialRecordDO gameDialRecordDO = gameDialRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(gameDialRecordDO);
        Assert.assertEquals(GameDialRecordStatusEnum.TAKE_LOTTERY.getVal(), gameDialRecordDO.getStatus());
    }

    @Test
    @Transactional
    @Rollback
    public void takeLottery() throws Exception {
        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setGameDialPrizeId(10L);
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.GET_LOTTERY.getVal());
        gameDialRecordDOMapper.insertSelective(recordDO);

        TakeLotteryParam param = new TakeLotteryParam();
        param.setId(recordDO.getId());
        param.setConsigneeAddress("test");
        param.setConsigneeMobile("13666666666");
        param.setConsigneeName("test");
        gameDialRecordService.takeLottery(param);
        GameDialRecordDO gameDialRecordDO = gameDialRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(gameDialRecordDO);
        Assert.assertEquals(GameDialRecordStatusEnum.TAKE_LOTTERY.getVal(), gameDialRecordDO.getStatus());
    }

    @Test
    @Transactional
    @Rollback
    public void winLottery() throws Exception {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setInventory(10);
        prizeDO.setIsSendPrize(false);
        prizeDO.setPrizeType((byte) 4);
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setGameDialPrizeId(10L);
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.TAKE_PART_LOTTERY.getVal());
        gameDialRecordDOMapper.insertSelective(recordDO);

        gameDialRecordService.winLottery(recordDO.getId(), prizeDO.getId());
        GameDialRecordDO gameDialRecordDO = gameDialRecordDOMapper.selectByPrimaryKey(recordDO.getId());
        Assert.assertNotNull(gameDialRecordDO);
        Assert.assertEquals(GameDialRecordStatusEnum.GET_LOTTERY.getVal(), gameDialRecordDO.getStatus());

        GameDialPrizeDO gameDialPrizeDO = gameDialPrizeDOMapper.selectByPrimaryKey(prizeDO.getId());
        Assert.assertNotNull(gameDialPrizeDO);
        Assert.assertEquals(9, gameDialPrizeDO.getInventory().intValue());
    }

    @Test
    @Transactional
    @Rollback
    public void page() throws Exception {
        GameDialPrizeDO prizeDO = new GameDialPrizeDO();
        prizeDO.setGameDialId(100L);
        prizeDO.setInventory(10);
        prizeDO.setIsSendPrize(false);
        prizeDO.setPrizeType((byte) 4);
        prizeDO.setName("test");
        gameDialPrizeDOMapper.insertSelective(prizeDO);

        GameDialRecordDO recordDO = new GameDialRecordDO();
        recordDO.setUserNum("M001");
        recordDO.setUserAccount("13666666666");
        recordDO.setConsigneeName("test");
        recordDO.setConsigneeMobile("13666666666");
        recordDO.setGameDialPrizeId(prizeDO.getId());
        recordDO.setGameDialId(100L);
        recordDO.setStatus(GameDialRecordStatusEnum.SEND_LOTTERY.getVal());
        gameDialRecordDOMapper.insertSelective(recordDO);

        GameDailRecordPageQuery query = new GameDailRecordPageQuery();
        query.setConsigneeMobile(recordDO.getConsigneeMobile());
        query.setConsigneeName(recordDO.getConsigneeName());
        query.setUserAccount(recordDO.getUserAccount());
        query.setStatusEnum(GameDialRecordStatusEnum.SEND_LOTTERY);
        Page<GameDialRecordBO> page = gameDialRecordService.page(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getTotalCount().intValue());
    }

}
