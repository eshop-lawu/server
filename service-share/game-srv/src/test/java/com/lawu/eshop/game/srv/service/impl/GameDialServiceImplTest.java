package com.lawu.eshop.game.srv.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.game.param.GameDialParam;
import com.lawu.eshop.game.query.GameDialQuery;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.eshop.game.srv.domain.GameDialDO;
import com.lawu.eshop.game.srv.mapper.GameDialDOMapper;
import com.lawu.eshop.game.srv.service.GameDialService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2018/3/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GameDialServiceImplTest {

    @Autowired
    private GameDialService gameDialService;

    @Autowired
    private GameDialDOMapper gameDialDOMapper;

    @Test
    @Transactional
    @Rollback
    public void saveGameDial() {
        GameDialParam param = new GameDialParam();
        param.setFreeCount(1);
        param.setImgPath("png");
        param.setName("test");
        param.setPoint(10);
        param.setShareAttendCount(1);

        gameDialService.saveGameDial(param);
        List<GameDialDO> dialDOS = gameDialDOMapper.selectByExample(null);
        Assert.assertNotNull(dialDOS);
        Assert.assertEquals(1, dialDOS.size());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameDial() {
        GameDialDO dialDO = new GameDialDO();
        dialDO.setName("test");
        dialDO.setStatus((byte) 1);
        gameDialDOMapper.insertSelective(dialDO);

        GameDialBO dialBO = gameDialService.getGameDial();
        Assert.assertNotNull(dialBO);
        Assert.assertEquals(dialDO.getName(), dialBO.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void updateGameDial() {
        GameDialDO dialDO = new GameDialDO();
        dialDO.setName("test");
        dialDO.setStatus((byte) 1);
        gameDialDOMapper.insertSelective(dialDO);

        GameDialParam param = new GameDialParam();
        param.setName("newtest");
        dialDO.setStatus((byte) 1);
        gameDialService.updateGameDial(dialDO.getId(), param);
        GameDialDO gameDialDO = gameDialDOMapper.selectByPrimaryKey(dialDO.getId());
        Assert.assertNotNull(gameDialDO);
        Assert.assertEquals(param.getName(), gameDialDO.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void getGameDialById() {
        GameDialDO dialDO = new GameDialDO();
        dialDO.setName("test");
        dialDO.setStatus((byte) 1);
        gameDialDOMapper.insertSelective(dialDO);

        GameDialBO dialBO = gameDialService.getGameDialById(dialDO.getId());
        Assert.assertNotNull(dialBO);
        Assert.assertEquals(dialDO.getName(), dialBO.getName());
    }

    @Test
    @Transactional
    @Rollback
    public void page() {
        GameDialDO dialDO = new GameDialDO();
        dialDO.setName("test");
        dialDO.setStatus((byte) 1);
        gameDialDOMapper.insertSelective(dialDO);

        GameDialQuery query = new GameDialQuery();
        Page<GameDialBO> page = gameDialService.page(query);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1, page.getRecords().size());
    }

}
