package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.game.dto.GameMindQuestionDTO;
import com.lawu.eshop.game.dto.GameRandomQuestionDTO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;

/**
 * @author zhangyong
 * @date 2018/3/20.
 */
public class GameMindQuestionConverterTest {

    @Test
    public void convertGameMindQuestionBOS() {
        List<GameMindQuestionDO> list = new ArrayList<>();
        GameMindQuestionDO questionDO = new GameMindQuestionDO();
        questionDO.setAnswers("[\"鸡\",\"鸭\",\"鹅\",\"牛\"]");
        questionDO.setCategoryName("知识题");
        questionDO.setId(1L);
        questionDO.setRightAnswer(1);
        questionDO.setStatus(GameConfigStatusEnum.ENABLE.getVal());
        questionDO.setTitle("test");
        list.add(questionDO);
        List<GameMindQuestionBO> questionBOS = GameMindQuestionConverter.convertGameMindQuestionBOS(list);
        Assert.assertEquals(questionDO.getAnswers(), questionBOS.get(0).getAnswers());
        Assert.assertEquals(questionDO.getCategoryName(), questionBOS.get(0).getCategoryName());
        Assert.assertEquals(questionDO.getId(), questionBOS.get(0).getId());
        Assert.assertEquals(questionDO.getRightAnswer(), questionBOS.get(0).getRightAnswer());
        Assert.assertEquals(questionDO.getStatus(), questionBOS.get(0).getStatus().getVal());
        Assert.assertEquals(questionDO.getTitle(), questionBOS.get(0).getTitle());
    }

    @Test
    public void convertGameMindQuestionDTOS() {
        List<GameMindQuestionBO> list = new ArrayList<>();
        GameMindQuestionBO questionBO = new GameMindQuestionBO();
        questionBO.setAnswers("[\"鸡\",\"鸭\",\"鹅\",\"牛\"]");
        questionBO.setCategoryName("知识题");
        questionBO.setId(1L);
        questionBO.setRightAnswer(1);
        questionBO.setStatus(GameConfigStatusEnum.ENABLE);
        questionBO.setTitle("test");
        list.add(questionBO);
        List<GameMindQuestionDTO> questionDTOS = GameMindQuestionConverter.convertGameMindQuestionDTOS(list);
        Assert.assertEquals(questionBO.getAnswers(), questionDTOS.get(0).getAnswers());
        Assert.assertEquals(questionBO.getCategoryName(), questionDTOS.get(0).getCategoryName());
        Assert.assertEquals(questionBO.getId(), questionDTOS.get(0).getId());
        Assert.assertEquals(questionBO.getRightAnswer(), questionDTOS.get(0).getRightAnswer());
        Assert.assertEquals(questionBO.getStatus(), questionDTOS.get(0).getStatus());
        Assert.assertEquals(questionBO.getTitle(), questionDTOS.get(0).getTitle());
    }

    @Test
    public void converter() {
        List<GameMindQuestionDO> questionDOS = new ArrayList<>();
        GameMindQuestionDO questionDO = new GameMindQuestionDO();
        questionDO.setAnswers("[\"鸡\",\"鸭\",\"鹅\",\"牛\"]");
        questionDO.setCategoryName("游戏题");
        questionDO.setTitle("王者荣耀牛魔西部大镖客的身上有什么动物");
        questionDO.setId(1L);
        questionDOS.add(questionDO);
        List<GameMindQuestionBO> questionBOS = GameMindQuestionConverter.convertGameMindQuestionBOS(questionDOS);
        Assert.assertEquals(questionDO.getAnswers(), questionBOS.get(0).getAnswers());
        Assert.assertEquals(questionDO.getCategoryName(), questionBOS.get(0).getCategoryName());
        Assert.assertEquals(questionDO.getId(), questionBOS.get(0).getId());
        Assert.assertEquals(questionDO.getTitle(), questionBOS.get(0).getTitle());
    }

    @Test
    public void converterDTOS() {
        List<GameMindQuestionBO> questionBOS = new ArrayList<>();
        GameMindQuestionBO questionBO = new GameMindQuestionBO();
        questionBO.setAnswers("[\"鸡\",\"鸭\",\"鹅\",\"牛\"]");
        questionBO.setCategoryName("游戏题");
        questionBO.setTitle("王者荣耀牛魔西部大镖客的身上有什么动物");
        questionBO.setId(1L);
        questionBOS.add(questionBO);
        List<GameRandomQuestionDTO> questionDTOS = GameMindQuestionConverter.converterDTOS(questionBOS);
        Assert.assertEquals(questionBO.getCategoryName(), questionDTOS.get(0).getCategoryName());
        Assert.assertEquals(questionBO.getId(), questionDTOS.get(0).getId());
        Assert.assertEquals(questionBO.getTitle(), questionDTOS.get(0).getTitle());
    }

    @Test
    public void convertGameMindQuestionBO() {
        GameMindQuestionDO record = new GameMindQuestionDO();
        record.setAnswers("[\"鸡\",\"鸭\",\"鹅\",\"牛\"]");
        record.setCategoryName("游戏题");
        record.setId(1L);
        record.setRightAnswer(1);
        record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
        record.setTitle("王者荣耀牛魔西部大镖客的身上有什么动物");
        GameMindQuestionBO questionBO = GameMindQuestionConverter.convertGameMindQuestionBO(record);
        Assert.assertEquals(record.getId(), questionBO.getId());
        Assert.assertEquals(record.getCategoryName(), questionBO.getCategoryName());
        Assert.assertEquals(record.getAnswers(), questionBO.getAnswers());
        Assert.assertEquals(record.getRightAnswer(), questionBO.getRightAnswer());
        Assert.assertEquals(record.getTitle(), questionBO.getTitle());
        Assert.assertEquals(record.getStatus(), questionBO.getStatus().getVal());
    }
}
