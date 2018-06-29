package com.lawu.eshop.game.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.OperatorGamePuzzlePicQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzlePicDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzlePicServiceImplTest {

    @Autowired
    private GamePuzzlePicService gamePuzzlePicService;

    @Autowired
    private GamePuzzlePicDOMapper gamePuzzlePicDOMapper;

    @Test
    @Transactional
    @Rollback
    public void saveGamePuzzlePic() {
        GamePuzzleParam param = new GamePuzzleParam();
        param.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        param.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        gamePuzzlePicService.saveGamePuzzlePic(param);
        List<GamePuzzlePicDO> picDOS = gamePuzzlePicDOMapper.selectByExample(null);
        Assert.assertNotNull(picDOS);
        Assert.assertEquals(1, picDOS.size());
    }

    @Test
    @Transactional
    @Rollback
    public void getGamePuzzlePic() {
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setUserNum("M001");
        picDO.setIsHard(true);
        picDO.setUserNickname("test");
        gamePuzzlePicDOMapper.insertSelective(picDO);

        GamePuzzlePicBO picBO = gamePuzzlePicService.getGamePuzzlePic(picDO.getId());
        Assert.assertNotNull(picBO);
        Assert.assertEquals(picDO.getUserNum(), picBO.getUserNum());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGamePuzzlePicStatus() {
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setUserNum("M001");
        picDO.setIsHard(true);
        picDO.setUserNickname("test");
        picDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        gamePuzzlePicDOMapper.insertSelective(picDO);

        gamePuzzlePicService.updateGamePuzzlePicStatus(picDO.getId(), GamePuzzlePicStatusEnum.DISABLED);
        GamePuzzlePicDO gamePuzzlePicDO = gamePuzzlePicDOMapper.selectByPrimaryKey(picDO.getId());
        Assert.assertNotNull(gamePuzzlePicDO);
        Assert.assertEquals(GamePuzzlePicStatusEnum.DISABLED.getVal(), gamePuzzlePicDO.getStatus());
    }

    @Test
    @Transactional
    @Rollback
    public void listOperatorGamePuzzlePic() {
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setUserNum("M001");
        picDO.setIsHard(true);
        picDO.setUserNickname("test");
        picDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setGmtCreate(new Date());
        picDO.setGmtModified(new Date());
        gamePuzzlePicDOMapper.insertSelective(picDO);

        OperatorGamePuzzlePicQuery query = new OperatorGamePuzzlePicQuery();
        query.setUserNickname("test");
        query.setBeginTime("2018-03-01");
        query.setEndTime("2030-03-01");
        query.setStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        Page<GamePuzzlePicBO> page = gamePuzzlePicService.listOperatorGamePuzzlePic(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void getRandomGamePuzzlePic() {
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setUserNum("M001");
        picDO.setIsHard(true);
        picDO.setUserNickname("test");
        picDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setIsCommon(true);
        picDO.setGmtCreate(new Date());
        picDO.setGmtModified(new Date());
        gamePuzzlePicDOMapper.insertSelective(picDO);

        List<GamePuzzlePicBO> picBOS = gamePuzzlePicService.getRandomGamePuzzlePic(GameHardLevelEnum.COMMONLY, 3);
        Assert.assertNotNull(picBOS);
        Assert.assertEquals(3, picBOS.size());
    }

}
