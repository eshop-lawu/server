package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.constants.GameDialStatusEnum;
import com.lawu.eshop.game.dto.GameDialDTO;
import com.lawu.eshop.game.srv.bo.GameDialBO;
import com.lawu.eshop.game.srv.domain.GameDialDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameDialConverterTest {

    @Test
    public void converBO() {
        GameDialDO dialDO = new GameDialDO();
        dialDO.setId(1L);
        dialDO.setName("test");
        dialDO.setImgPath("jj.png");
        dialDO.setPoint(10);
        dialDO.setFreeCount(1);
        dialDO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        dialDO.setGmtModified(new Date());
        dialDO.setGmtCreate(new Date());
        dialDO.setShareAttendCount(2);
        GameDialBO dialBO = GameDialConverter.converBO(dialDO);
        Assert.assertEquals(dialDO.getId(), dialBO.getId());
        Assert.assertEquals(dialDO.getName(), dialBO.getName());
        Assert.assertEquals(dialDO.getImgPath(), dialBO.getImgPath());
        Assert.assertEquals(dialDO.getPoint(), dialBO.getPoint());
        Assert.assertEquals(dialDO.getFreeCount(), dialBO.getFreeCount());
        Assert.assertEquals(dialDO.getStatus(), dialBO.getStatus());
        Assert.assertEquals(dialDO.getGmtCreate(), dialBO.getGmtCreate());
        Assert.assertEquals(dialDO.getGmtModified(), dialBO.getGmtModified());
        Assert.assertEquals(dialDO.getShareAttendCount(), dialBO.getShareAttendCount());
    }

    @Test
    public void converBOS() {
        List<GameDialDO> dialDOS = new ArrayList<>();
        GameDialDO dialDO = new GameDialDO();
        dialDO.setId(1L);
        dialDO.setName("test");
        dialDO.setImgPath("jj.png");
        dialDO.setPoint(10);
        dialDO.setFreeCount(1);
        dialDO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        dialDO.setGmtModified(new Date());
        dialDO.setGmtCreate(new Date());
        dialDO.setShareAttendCount(2);
        dialDOS.add(dialDO);
        List<GameDialBO> dialBOS = GameDialConverter.converBOS(dialDOS);
        Assert.assertEquals(dialDO.getId(), dialBOS.get(0).getId());
        Assert.assertEquals(dialDO.getName(), dialBOS.get(0).getName());
        Assert.assertEquals(dialDO.getImgPath(), dialBOS.get(0).getImgPath());
        Assert.assertEquals(dialDO.getPoint(), dialBOS.get(0).getPoint());
        Assert.assertEquals(dialDO.getFreeCount(), dialBOS.get(0).getFreeCount());
        Assert.assertEquals(dialDO.getStatus(), dialBOS.get(0).getStatus());
        Assert.assertEquals(dialDO.getGmtCreate(), dialBOS.get(0).getGmtCreate());
        Assert.assertEquals(dialDO.getGmtModified(), dialBOS.get(0).getGmtModified());
        Assert.assertEquals(dialDO.getShareAttendCount(), dialBOS.get(0).getShareAttendCount());
    }

    @Test
    public void converDTO() {
        GameDialDTO dialNullDTO = GameDialConverter.converDTO(null);
        Assert.assertEquals(null,dialNullDTO.getFreeCount());
        GameDialBO dialBO = new GameDialBO();
        dialBO.setId(1L);
        dialBO.setName("test");
        dialBO.setImgPath("yy.png");
        dialBO.setPoint(10);
        dialBO.setFreeCount(2);
        dialBO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        dialBO.setShareAttendCount(2);
        GameDialDTO dialDTO = GameDialConverter.converDTO(dialBO);
        Assert.assertEquals(dialBO.getId(), dialDTO.getId());
        Assert.assertEquals(dialBO.getName(), dialDTO.getName());
        Assert.assertEquals(dialBO.getImgPath(), dialDTO.getImgPath());
        Assert.assertEquals(dialBO.getPoint(), dialDTO.getPoint());
        Assert.assertEquals(dialBO.getFreeCount(), dialDTO.getFreeCount());
        Assert.assertEquals(dialBO.getStatus(), dialDTO.getStatus().getVal());
        Assert.assertEquals(dialBO.getShareAttendCount(), dialDTO.getShareAttendCount());

    }

    @Test
    public void converDTOS() {
        List<GameDialBO> dialBOS = new ArrayList<>();
        GameDialBO dialBO = new GameDialBO();
        dialBO.setId(1L);
        dialBO.setName("test");
        dialBO.setImgPath("yy.png");
        dialBO.setPoint(10);
        dialBO.setFreeCount(2);
        dialBO.setStatus(GameDialStatusEnum.ENABLED.getVal());
        dialBO.setShareAttendCount(2);
        dialBOS.add(dialBO);
        List<GameDialDTO> dialDTOS = GameDialConverter.converDTOS(dialBOS);
        Assert.assertEquals(dialBO.getId(), dialDTOS.get(0).getId());
        Assert.assertEquals(dialBO.getName(), dialDTOS.get(0).getName());
        Assert.assertEquals(dialBO.getImgPath(), dialDTOS.get(0).getImgPath());
        Assert.assertEquals(dialBO.getPoint(), dialDTOS.get(0).getPoint());
        Assert.assertEquals(dialBO.getFreeCount(), dialDTOS.get(0).getFreeCount());
        Assert.assertEquals(dialBO.getStatus(), dialDTOS.get(0).getStatus().getVal());
        Assert.assertEquals(dialBO.getShareAttendCount(), dialDTOS.get(0).getShareAttendCount());
    }
}
