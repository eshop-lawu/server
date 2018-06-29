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

import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.game.query.OperatorGamePuzzleUserPicQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicUploadNumberBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleUserPicDOMapper;
import com.lawu.eshop.game.srv.service.GamePuzzleUserPicService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleUserPicServiceImplTest {

    @Autowired
    private GamePuzzleUserPicService gamePuzzleUserPicService;

    @Autowired
    private GamePuzzleUserPicDOMapper gamePuzzleUserPicDOMapper;

    @Test
    @Transactional
    @Rollback
    public void saveGamePuzzleUserPic() {
        GamePuzzleParam param = new GamePuzzleParam();
        param.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        param.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.CHECK_PENDING);
        gamePuzzleUserPicService.saveGamePuzzleUserPic(param);
        List<GamePuzzleUserPicDO> picDOS = gamePuzzleUserPicDOMapper.selectByExample(null);
        Assert.assertNotNull(picDOS);
        Assert.assertEquals(1, picDOS.size());
    }

    @Test
    @Transactional
    @Rollback
    public void getGamePuzzleUserPicUploadNumber() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picDO.setUserNum("M001");
        gamePuzzleUserPicDOMapper.insertSelective(picDO);

        GamePuzzleUserPicUploadNumberBO numberBO = gamePuzzleUserPicService.getGamePuzzleUserPicUploadNumber(picDO.getUserNum());
        Assert.assertNotNull(numberBO);
        Assert.assertEquals(1, numberBO.getUploadNumber().intValue());
    }

    @Test
    @Transactional
    @Rollback
    public void listGamePuzzleUserPic() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picDO.setUserNum("M001");
        gamePuzzleUserPicDOMapper.insertSelective(picDO);

        GamePuzzleUserPicRealQuery query = new GamePuzzleUserPicRealQuery();
        query.setUserNum(picDO.getUserNum());
        Page<GamePuzzleUserPicBO> page = gamePuzzleUserPicService.listGamePuzzleUserPic(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void listOperatorGamePuzzleUserPic() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picDO.setUserNum("M001");
        picDO.setUserNickname("test");
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        gamePuzzleUserPicDOMapper.insertSelective(picDO);

        OperatorGamePuzzleUserPicQuery query = new OperatorGamePuzzleUserPicQuery();
        query.setUserNickname(picDO.getUserNickname());
        query.setBeginTime("2018-03-01");
        query.setEndTime("2030-03-01");
        query.setStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        Page<GamePuzzleUserPicBO> page = gamePuzzleUserPicService.listOperatorGamePuzzleUserPic(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGamePuzzleUserPicStatus() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setStatus(GamePuzzleUserPicStatusEnum.CHECK_PENDING.getVal());
        picDO.setUserNum("M001");
        picDO.setUserNickname("test");
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        gamePuzzleUserPicDOMapper.insertSelective(picDO);

        gamePuzzleUserPicService.updateGamePuzzleUserPicStatus(picDO.getId(), GamePuzzleUserPicStatusEnum.HAVE_USE);
        GamePuzzleUserPicDO gamePuzzleUserPicDO = gamePuzzleUserPicDOMapper.selectByPrimaryKey(picDO.getId());
        Assert.assertNotNull(gamePuzzleUserPicDO);
        Assert.assertEquals(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal(), gamePuzzleUserPicDO.getStatus());
    }

}
