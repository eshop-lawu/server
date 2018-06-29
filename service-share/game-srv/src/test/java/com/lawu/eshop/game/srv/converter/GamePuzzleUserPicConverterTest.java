package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicOperatorDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzleUserPicBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleUserPicDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GamePuzzleUserPicConverterTest {

    @Test
    public void converBO() {
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setId(1L);
        picDO.setUserNum("m1231456");
        picDO.setUserNickname("name");
        picDO.setImgPath("image");
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setIsSimple(false);
        picDO.setIsCommon(false);
        picDO.setIsHard(false);
        picDO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());

        GamePuzzleUserPicBO picBO = GamePuzzleUserPicConverter.converBO(picDO);
        Assert.assertEquals(picBO.getId(), picDO.getId());
        Assert.assertEquals(picBO.getUserNum(), picDO.getUserNum());
        Assert.assertEquals(picBO.getUserNickname(), picDO.getUserNickname());
        Assert.assertEquals(picBO.getImgPath(), picDO.getImgPath());
        Assert.assertEquals(picBO.getType(), picDO.getType());
        Assert.assertEquals(picBO.getIsHard(), picDO.getIsHard());
        Assert.assertEquals(picBO.getIsCommon(), picDO.getIsCommon());
        Assert.assertEquals(picBO.getIsSimple(), picDO.getIsSimple());
        Assert.assertEquals(picBO.getStatus(), picDO.getStatus());
        Assert.assertEquals(picBO.getGmtCreate(), picDO.getGmtCreate());
        Assert.assertEquals(picBO.getGmtModified(), picDO.getGmtModified());
    }

    @Test
    public void converBOS() {
        List<GamePuzzleUserPicDO> picDOS = new ArrayList<>();
        GamePuzzleUserPicDO picDO = new GamePuzzleUserPicDO();
        picDO.setId(1L);
        picDO.setUserNum("m1231456");
        picDO.setUserNickname("name");
        picDO.setImgPath("image");
        picDO.setType(GamePuzzleTypeEnum.PUZZLE.getVal());
        picDO.setIsSimple(false);
        picDO.setIsCommon(false);
        picDO.setIsHard(false);
        picDO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picDO.setGmtModified(new Date());
        picDO.setGmtCreate(new Date());
        picDOS.add(picDO);
        List<GamePuzzleUserPicBO> list = GamePuzzleUserPicConverter.converBOS(picDOS);
        Assert.assertEquals(list.get(0).getId(), picDO.getId());
        Assert.assertEquals(list.get(0).getUserNum(), picDO.getUserNum());
        Assert.assertEquals(list.get(0).getUserNickname(), picDO.getUserNickname());
        Assert.assertEquals(list.get(0).getImgPath(), picDO.getImgPath());
        Assert.assertEquals(list.get(0).getType(), picDO.getType());
        Assert.assertEquals(list.get(0).getIsHard(), picDO.getIsHard());
        Assert.assertEquals(list.get(0).getIsCommon(), picDO.getIsCommon());
        Assert.assertEquals(list.get(0).getIsSimple(), picDO.getIsSimple());
        Assert.assertEquals(list.get(0).getStatus(), picDO.getStatus());
        Assert.assertEquals(list.get(0).getGmtCreate(), picDO.getGmtCreate());
        Assert.assertEquals(list.get(0).getGmtModified(), picDO.getGmtModified());
    }

    @Test
    public void converDTO() {
        GamePuzzleUserPicBO picBO = new GamePuzzleUserPicBO();
        picBO.setImgPath("image");
        picBO.setIsSimple(false);
        picBO.setIsCommon(false);
        picBO.setIsHard(false);
        picBO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picBO.setGmtCreate(new Date());
        GamePuzzleUserPicDTO picDTO = GamePuzzleUserPicConverter.converDTO(picBO);
        Assert.assertEquals(picBO.getImgPath(), picDTO.getImgPath());
        Assert.assertEquals(picBO.getIsSimple(), picDTO.getIsSimple());
        Assert.assertEquals(picBO.getIsCommon(), picDTO.getIsCommon());
        Assert.assertEquals(picBO.getIsHard(), picDTO.getIsHard());
        Assert.assertEquals(picBO.getStatus(), picDTO.getStatusEnum().getVal());
        Assert.assertEquals(picBO.getGmtCreate(), picDTO.getGmtCreate());
    }

    @Test
    public void converDTOS() {
        List<GamePuzzleUserPicBO> picBOS = new ArrayList<>();
        GamePuzzleUserPicBO picBO = new GamePuzzleUserPicBO();
        picBO.setImgPath("image");
        picBO.setIsSimple(false);
        picBO.setIsCommon(false);
        picBO.setIsHard(false);
        picBO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picBO.setGmtCreate(new Date());
        picBOS.add(picBO);
        List<GamePuzzleUserPicDTO> picDTOS = GamePuzzleUserPicConverter.converDTOS(picBOS);
        Assert.assertEquals(picBO.getImgPath(), picDTOS.get(0).getImgPath());
        Assert.assertEquals(picBO.getIsSimple(), picDTOS.get(0).getIsSimple());
        Assert.assertEquals(picBO.getIsCommon(), picDTOS.get(0).getIsCommon());
        Assert.assertEquals(picBO.getIsHard(), picDTOS.get(0).getIsHard());
        Assert.assertEquals(picBO.getStatus(), picDTOS.get(0).getStatusEnum().getVal());
        Assert.assertEquals(picBO.getGmtCreate(), picDTOS.get(0).getGmtCreate());
    }

    @Test
    public void converOperatorDTOS() {
        List<GamePuzzleUserPicBO> picBOS = new ArrayList<>();
        GamePuzzleUserPicBO picBO = new GamePuzzleUserPicBO();
        picBO.setImgPath("image");
        picBO.setIsSimple(true);
        picBO.setIsCommon(true);
        picBO.setIsHard(true);
        picBO.setStatus(GamePuzzleUserPicStatusEnum.HAVE_USE.getVal());
        picBO.setGmtCreate(new Date());
        picBOS.add(picBO);
        List<GamePuzzleUserPicOperatorDTO> operatorDTOS = GamePuzzleUserPicConverter.converOperatorDTOS(picBOS);
        Assert.assertEquals(picBO.getImgPath(), operatorDTOS.get(0).getImgPath());
        Assert.assertEquals(picBO.getIsSimple(), operatorDTOS.get(0).getIsSimple());
        Assert.assertEquals(picBO.getIsCommon(), operatorDTOS.get(0).getIsCommon());
        Assert.assertEquals(picBO.getIsHard(), operatorDTOS.get(0).getIsHard());
        Assert.assertEquals(picBO.getStatus(), operatorDTOS.get(0).getStatusEnum().getVal());
        Assert.assertEquals(picBO.getGmtCreate(), operatorDTOS.get(0).getGmtCreate());

    }
}
