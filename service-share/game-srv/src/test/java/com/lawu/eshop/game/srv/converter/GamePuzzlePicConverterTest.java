package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzlePicOperatorDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePicBO;
import com.lawu.eshop.game.srv.domain.GamePuzzlePicDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GamePuzzlePicConverterTest {

    @Test
    public void converBO() {
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setId(1L);
        picDO.setUserNum("M123456");
        picDO.setUserNickname("name");
        picDO.setImgPath("image");
        picDO.setType((byte) 1);
        picDO.setIsSimple(true);
        picDO.setIsCommon(false);
        picDO.setIsHard(false);
        picDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        GamePuzzlePicBO puzzlePicBO = GamePuzzlePicConverter.converBO(picDO);
        Assert.assertEquals(picDO.getId(), puzzlePicBO.getId());
        Assert.assertEquals(picDO.getUserNickname(), puzzlePicBO.getUserNickname());
        Assert.assertEquals(picDO.getUserNum(), puzzlePicBO.getUserNum());
        Assert.assertEquals(picDO.getImgPath(), puzzlePicBO.getImgPath());
        Assert.assertEquals(picDO.getType(), puzzlePicBO.getType());
        Assert.assertEquals(picDO.getIsCommon(), puzzlePicBO.getIsCommon());
        Assert.assertEquals(picDO.getIsHard(), puzzlePicBO.getIsHard());
        Assert.assertEquals(picDO.getIsSimple(), puzzlePicBO.getIsSimple());
        Assert.assertEquals(picDO.getStatus(), puzzlePicBO.getStatus());
    }

    @Test
    public void converBOS() {
        List<GamePuzzlePicDO> picDOS = new ArrayList<>();
        GamePuzzlePicDO picDO = new GamePuzzlePicDO();
        picDO.setId(1L);
        picDO.setUserNum("M123456");
        picDO.setUserNickname("name");
        picDO.setImgPath("image");
        picDO.setType((byte) 1);
        picDO.setIsSimple(true);
        picDO.setIsCommon(false);
        picDO.setIsHard(false);
        picDO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        picDOS.add(picDO);

        List<GamePuzzlePicBO> picBOS = GamePuzzlePicConverter.converBOS(picDOS);
        Assert.assertEquals(picDO.getId(), picBOS.get(0).getId());
        Assert.assertEquals(picDO.getUserNickname(), picBOS.get(0).getUserNickname());
        Assert.assertEquals(picDO.getUserNum(), picBOS.get(0).getUserNum());
        Assert.assertEquals(picDO.getImgPath(), picBOS.get(0).getImgPath());
        Assert.assertEquals(picDO.getType(), picBOS.get(0).getType());
        Assert.assertEquals(picDO.getIsCommon(), picBOS.get(0).getIsCommon());
        Assert.assertEquals(picDO.getIsHard(), picBOS.get(0).getIsHard());
        Assert.assertEquals(picDO.getIsSimple(), picBOS.get(0).getIsSimple());
        Assert.assertEquals(picDO.getStatus(), picBOS.get(0).getStatus());
    }

    @Test
    public void converOperatorDTO() {
        GamePuzzlePicBO picBO = new GamePuzzlePicBO();
        picBO.setId(1L);
        picBO.setUserNickname("NAME");
        picBO.setImgPath("image");
        picBO.setIsSimple(true);
        picBO.setIsCommon(true);
        picBO.setIsHard(true);
        picBO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picBO.setGmtCreate(new Date());
        GamePuzzlePicOperatorDTO operatorDTO = GamePuzzlePicConverter.converOperatorDTO(picBO);
        Assert.assertEquals(picBO.getId(), operatorDTO.getId());
        Assert.assertEquals(picBO.getUserNickname(), operatorDTO.getUserNickname());
        Assert.assertEquals(picBO.getImgPath(), operatorDTO.getImgPath());
        Assert.assertEquals(picBO.getIsSimple(), operatorDTO.getIsSimple());
        Assert.assertEquals(picBO.getIsHard(), operatorDTO.getIsHard());
        Assert.assertEquals(picBO.getIsCommon(), operatorDTO.getIsCommon());
        Assert.assertEquals(picBO.getStatus(), operatorDTO.getStatusEnum().getVal());
        Assert.assertEquals(picBO.getGmtCreate(), operatorDTO.getGmtCreate());
    }

    @Test
    public void converOperatorDTOS() {
        List<GamePuzzlePicBO> picBOS = new ArrayList<>();
        GamePuzzlePicBO picBO = new GamePuzzlePicBO();
        picBO.setId(1L);
        picBO.setUserNickname("NAME");
        picBO.setImgPath("image");
        picBO.setIsSimple(true);
        picBO.setIsCommon(true);
        picBO.setIsHard(true);
        picBO.setStatus(GamePuzzlePicStatusEnum.ENABLED.getVal());
        picBO.setGmtCreate(new Date());
        picBOS.add(picBO);
        List<GamePuzzlePicOperatorDTO> operatorDTOS = GamePuzzlePicConverter.converOperatorDTOS(picBOS);
        Assert.assertEquals(picBO.getId(), operatorDTOS.get(0).getId());
        Assert.assertEquals(picBO.getUserNickname(), operatorDTOS.get(0).getUserNickname());
        Assert.assertEquals(picBO.getImgPath(), operatorDTOS.get(0).getImgPath());
        Assert.assertEquals(picBO.getIsSimple(), operatorDTOS.get(0).getIsSimple());
        Assert.assertEquals(picBO.getIsHard(), operatorDTOS.get(0).getIsHard());
        Assert.assertEquals(picBO.getIsCommon(), operatorDTOS.get(0).getIsCommon());
        Assert.assertEquals(picBO.getStatus(), operatorDTOS.get(0).getStatusEnum().getVal());
        Assert.assertEquals(picBO.getGmtCreate(), operatorDTOS.get(0).getGmtCreate());
    }
}
