package com.lawu.eshop.game.srv.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
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
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.dto.GameRoomDetailsDTO;
import com.lawu.eshop.game.constants.GameRoomStatusEnum;
import com.lawu.eshop.game.param.ExitGameRoomParam;
import com.lawu.eshop.game.param.GameRoomParam;
import com.lawu.eshop.game.param.JoinGameRoomParam;
import com.lawu.eshop.game.param.UpdateGameRoomPlayerReadyStatusParam;
import com.lawu.eshop.game.query.GameRoomQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameRoomBO;
import com.lawu.eshop.game.srv.bo.GameRoomCreateBO;
import com.lawu.eshop.game.srv.domain.GameRoomDO;
import com.lawu.eshop.game.srv.mapper.GameRoomDOMapper;
import com.lawu.eshop.game.srv.service.GameRoomService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;
import com.lawu.utils.PwdUtil;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameRoomServiceImplTest {

    @Autowired
    private GameRoomService gameRoomService;

    @Autowired
    private GameRoomDOMapper gameRoomDOMapper;

    @Test
    @Transactional
    @Rollback
    public void saveGameRoom() {
        GameRoomParam param = new GameRoomParam();
        param.setUserNum("M001");
        param.setAccount("13666666666");
        param.setPlayerInfo("test");
        param.setPoint(BigDecimal.valueOf(10));
        param.setTypeEnum(GameTypeEnum.PUZZLE);
        param.setStatusEnum(GameRoomStatusEnum.WAITING);
        param.setHardLevelEnum(GameHardLevelEnum.SIMPLE);
        GameRoomCreateBO bo = gameRoomService.saveGameRoom(param);
        List<GameRoomDO> roomDOS = gameRoomDOMapper.selectByExample(null);
        Assert.assertNotNull(roomDOS);
        Assert.assertEquals(1, roomDOS.size());
        Assert.assertEquals(bo.getRoomId(), roomDOS.get(0).getId());
        Assert.assertEquals(bo.getRoomNum(), roomDOS.get(0).getRoomNum());
    }

    @Test
    @Transactional
    @Rollback
    public void listGameRoom() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);
        GameRoomDO gameRoomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());

        GameRoomQuery query = new GameRoomQuery();
        query.setRoomNum(gameRoomDO.getRoomNum());
        query.setMinPoint(BigDecimal.valueOf(1));
        query.setMaxPoint(BigDecimal.valueOf(100));
        query.setTypeEnum(GameTypeEnum.PUZZLE);
        Page<GameRoomBO> page = gameRoomService.listGameRoom(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameRoom() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);

        GameRoomBO roomBO = gameRoomService.getGameRoom(roomDO.getId());
        Assert.assertNotNull(roomBO);
        Assert.assertEquals(roomDO.getUserNum(), roomBO.getUserNum());
    }

    @Test
    @Transactional
    @Rollback
    public void verifyGameRoomPwd() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPwd(PwdUtil.generate("123456"));
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);

        Boolean result = gameRoomService.verifyGameRoomPwd(roomDO.getId(), "123456");
        Assert.assertNotNull(result);
        Assert.assertTrue(result);
    }

    @Test
    @Transactional
    @Rollback
    public void joinGameRoom() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPwd(PwdUtil.generate("123456"));
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);
        GameRoomDO gameRoomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());

        JoinGameRoomParam param = new JoinGameRoomParam();
        param.setRoomNum(gameRoomDO.getRoomNum());
        param.setGameType(GameTypeEnum.PUZZLE);
        param.setHeadImg("png");
        param.setNickName("test");
        param.setUserNum("M001");
        param.setGameType(GameTypeEnum.PUZZLE);
        GameRoomDetailsDTO detailsDTO = gameRoomService.joinGameRoom(param);
        Assert.assertNotNull(detailsDTO);
        Assert.assertTrue(detailsDTO.getIsDissolution());
    }

    @Test
    @Transactional
    @Rollback
    public void exitGameRoom() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPwd(PwdUtil.generate("123456"));
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);
        GameRoomDO gameRoomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());

        ExitGameRoomParam param = new ExitGameRoomParam();
        param.setRoomNum(gameRoomDO.getRoomNum());
        param.setCurrentUserNum("M002");
        param.setUserNum("M001");
        param.setGameType(GameTypeEnum.PUZZLE);
        GameRoomDetailsDTO detailsDTO = gameRoomService.exitGameRoom(param);
        Assert.assertNotNull(detailsDTO);
        Assert.assertTrue(detailsDTO.getIsDissolution());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGameRoomPlayerReadyStatus() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPwd(PwdUtil.generate("123456"));
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);
        GameRoomDO gameRoomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());

        UpdateGameRoomPlayerReadyStatusParam param = new UpdateGameRoomPlayerReadyStatusParam();
        param.setRoomNum(gameRoomDO.getRoomNum());
        param.setUserNum("M001");
        param.setGameType(GameTypeEnum.PUZZLE);
        GameRoomDetailsDTO detailsDTO = gameRoomService.updateGameRoomPlayerReadyStatus(param);
        Assert.assertNotNull(detailsDTO);
        Assert.assertTrue(detailsDTO.getIsDissolution());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameRoomByRoomNum() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);
        roomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());

        GameRoomBO roomBO = gameRoomService.getGameRoomByRoomNum(roomDO.getRoomNum(), GameTypeEnum.getEnum(roomDO.getType()));
        Assert.assertNotNull(roomBO);
        Assert.assertEquals(roomDO.getUserNum(), roomBO.getUserNum());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameRoomByUserNum() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        gameRoomDOMapper.insertSelective(roomDO);

        GameRoomBO roomBO = gameRoomService.getGameRoomByUserNum(roomDO.getUserNum(), GameTypeEnum.getEnum(roomDO.getType()));
        Assert.assertNotNull(roomBO);
        Assert.assertEquals(roomDO.getUserNum(), roomBO.getUserNum());
    }

    @Test
    @Transactional
    @Rollback
    public void closeInvalidGameRoom() {
        GameRoomDO roomDO = new GameRoomDO();
        roomDO.setUserNum("M001");
        roomDO.setAccount("13666666666");
        roomDO.setPlayerInfo("test");
        roomDO.setPoint(BigDecimal.valueOf(10));
        roomDO.setType(GameTypeEnum.PUZZLE.getVal());
        roomDO.setStatus(GameRoomStatusEnum.WAITING.getVal());
        roomDO.setHardLevel(GameHardLevelEnum.SIMPLE.getVal());
        roomDO.setGmtModified(DateUtil.add(new Date(), -2, Calendar.HOUR));
        gameRoomDOMapper.insertSelective(roomDO);

        gameRoomService.closeInvalidGameRoom();
        GameRoomDO gameRoomDO = gameRoomDOMapper.selectByPrimaryKey(roomDO.getId());
        Assert.assertNotNull(gameRoomDO);
        Assert.assertEquals(GameRoomStatusEnum.FINISHED.getVal(), gameRoomDO.getStatus());
    }

}
