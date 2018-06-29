package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.game.dto.GamePuzzleDifficultyDTO;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;

/**
 * @author zhangyong
 * @author 2018/3/20.
 */
public class GamePuzzleDiffcultyConverterTest {

    @Test
    public void convertBO() {
        GamePuzzleDifficultyDO difficultyDO = new GamePuzzleDifficultyDO();
        difficultyDO.setId(1L);
        difficultyDO.setCoefficient(2);
        difficultyDO.setType(GameHardLevelEnum.COMMONLY.getVal());
        difficultyDO.setChallengeTime(5);
        difficultyDO.setGmtCreate(new Date());
        difficultyDO.setGmtModified(new Date());
        GamePuzzleDifficultyBO difficultyBO = GamePuzzleDiffcultyConverter.convertBO(difficultyDO);
        Assert.assertEquals(difficultyBO.getId(), difficultyDO.getId());
        Assert.assertEquals(difficultyBO.getCoefficient(), difficultyDO.getCoefficient().intValue());
        Assert.assertEquals(difficultyBO.getLevel().getVal(), difficultyDO.getType());
        Assert.assertEquals(difficultyBO.getChallengeTime(), difficultyDO.getChallengeTime());
        Assert.assertEquals(difficultyBO.getGmtModified(), difficultyDO.getGmtModified());
        Assert.assertEquals(difficultyBO.getGmtCreate(), difficultyDO.getGmtCreate());
    }

    @Test
    public void convertDTO() {
        GamePuzzleDifficultyBO difficultyBO = new GamePuzzleDifficultyBO();
        difficultyBO.setId(1L);
        difficultyBO.setPoint(10);
        difficultyBO.setLevel(GameHardLevelEnum.COMMONLY);
        difficultyBO.setStar(2);
        GamePuzzleDifficultyDTO dto = GamePuzzleDiffcultyConverter.convertDTO(difficultyBO);
        Assert.assertEquals(difficultyBO.getId(), dto.getId());
        Assert.assertEquals(difficultyBO.getLevel(), dto.getLevel());
        Assert.assertEquals(difficultyBO.getPoint(), dto.getPoint());
        Assert.assertEquals(difficultyBO.getStar(), dto.getStar());
    }

    @Test
    public void convertDTOS() {
        List<GamePuzzleDifficultyBO> difficultyBOS = new ArrayList<>();
        GamePuzzleDifficultyBO difficultyBO = new GamePuzzleDifficultyBO();
        difficultyBO.setId(1L);
        difficultyBO.setPoint(10);
        difficultyBO.setLevel(GameHardLevelEnum.COMMONLY);
        difficultyBO.setStar(2);
        difficultyBOS.add(difficultyBO);
        List<GamePuzzleDifficultyDTO> difficultyDTOS = GamePuzzleDiffcultyConverter.convertDTOS(difficultyBOS);
        Assert.assertEquals(difficultyBO.getId(), difficultyDTOS.get(0).getId());
        Assert.assertEquals(difficultyBO.getLevel(), difficultyDTOS.get(0).getLevel());
        Assert.assertEquals(difficultyBO.getPoint(), difficultyDTOS.get(0).getPoint());
        Assert.assertEquals(difficultyBO.getStar(), difficultyDTOS.get(0).getStar());
    }
}
