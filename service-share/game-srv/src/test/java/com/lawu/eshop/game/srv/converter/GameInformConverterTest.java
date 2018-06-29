package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.GameInformStatusEnum;
import com.lawu.eshop.game.dto.GameInformDTO;
import com.lawu.eshop.game.srv.bo.GameInformBO;
import com.lawu.eshop.game.srv.domain.GameInformDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameInformConverterTest {

    @Test
    public void convertGameInformBOS() {
        List<GameInformDO> list = new ArrayList<>();
        GameInformDO informDO = new GameInformDO();
        informDO.setAttendNum("A123456");
        informDO.setAuditorId(1);
        informDO.setAuditorName("test");
        informDO.setAuditTime(new Date());
        informDO.setCheat(false);
        informDO.setGameType(GameTypeEnum.DIAL.getVal());
        informDO.setGmtCreate(new Date());
        informDO.setId(1L);
        informDO.setQuestionError(false);
        informDO.setRemark("remark");
        informDO.setResultError(false);
        informDO.setStatus(GameInformStatusEnum.DEAL_WITH.getVal());
        informDO.setUserNum("M123456");
        list.add(informDO);
        List<GameInformBO> informBOS = GameInformConverter.convertGameInformBOS(list);
        Assert.assertEquals(informDO.getAttendNum(), informBOS.get(0).getAttendNum());
        Assert.assertEquals(informDO.getAuditorId(), informBOS.get(0).getAuditorId());
        Assert.assertEquals(informDO.getAuditorName(), informBOS.get(0).getAuditorName());
        Assert.assertEquals(informDO.getAuditTime(), informBOS.get(0).getAuditTime());
        Assert.assertEquals(informDO.getCheat(), informBOS.get(0).getCheat());
        Assert.assertEquals(informDO.getGameType(), informBOS.get(0).getGameType().getVal());
        Assert.assertEquals(informDO.getGmtCreate(), informBOS.get(0).getGmtCreate());
        Assert.assertEquals(informDO.getId(), informBOS.get(0).getId());
        Assert.assertEquals(informDO.getQuestionError(), informBOS.get(0).getQuestionError());
        Assert.assertEquals(informDO.getRemark(), informBOS.get(0).getRemark());
        Assert.assertEquals(informDO.getResultError(), informBOS.get(0).getResultError());
        Assert.assertEquals(informDO.getStatus(), informBOS.get(0).getStatus().getVal());
        Assert.assertEquals(informDO.getUserNum(), informBOS.get(0).getUserNum());

    }

    @Test
    public void convertGameInformDTOS(){
        List<GameInformBO> list = new ArrayList<>();
        GameInformBO informBO = new GameInformBO();
        informBO.setAttendNum("A123456");
        informBO.setAuditorId(1);
        informBO.setAuditorName("test");
        informBO.setAuditTime(new Date());
        informBO.setCheat(false);
        informBO.setGameType(GameTypeEnum.DIAL);
        informBO.setGmtCreate(new Date());
        informBO.setId(1L);
        informBO.setQuestionError(false);
        informBO.setRemark("remark");
        informBO.setResultError(false);
        informBO.setStatus(GameInformStatusEnum.DEAL_WITH);
        informBO.setUserNum("M123456");
        list.add(informBO);
        List<GameInformDTO> informDTOS = GameInformConverter.convertGameInformDTOS(list);
        Assert.assertEquals(informBO.getAttendNum(), informDTOS.get(0).getAttendNum());
        Assert.assertEquals(informBO.getAuditorId(), informDTOS.get(0).getAuditorId());
        Assert.assertEquals(informBO.getAuditorName(), informDTOS.get(0).getAuditorName());
        Assert.assertEquals(informBO.getAuditTime(), informDTOS.get(0).getAuditTime());
        Assert.assertEquals(informBO.getCheat(), informDTOS.get(0).getCheat());
        Assert.assertEquals(informBO.getGameType(), informDTOS.get(0).getGameType());
        Assert.assertEquals(informBO.getGmtCreate(), informDTOS.get(0).getGmtCreate());
        Assert.assertEquals(informBO.getId(), informDTOS.get(0).getId());
        Assert.assertEquals(informBO.getQuestionError(), informDTOS.get(0).getQuestionError());
        Assert.assertEquals(informBO.getRemark(), informDTOS.get(0).getRemark());
        Assert.assertEquals(informBO.getResultError(), informDTOS.get(0).getResultError());
        Assert.assertEquals(informBO.getStatus(), informDTOS.get(0).getStatus());
        Assert.assertEquals(informBO.getUserNum(), informDTOS.get(0).getUserNum());
    }
}
