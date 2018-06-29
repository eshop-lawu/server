package com.lawu.eshop.game.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.game.constants.AttendTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzlePicStatusEnum;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.param.GameAccountParam;
import com.lawu.eshop.game.param.GamePuzzleCheckDeductionPointParam;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.param.GamePuzzleStandaloneParam;
import com.lawu.eshop.game.srv.GameSrvApplicationTest;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GameAccountService;
import com.lawu.eshop.game.srv.service.GamePuzzlePicService;
import com.lawu.framework.web.HttpCode;

/**
 * @author lihj
 * @date 2018/4/2
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GameSrvApplicationTest.class)
public class GamePuzzleAttendRecordControllerTest {
    @Autowired
    private GamePuzzleAttendRecordController gamePuzzleAttendRecordController;
    private MockMvc mvc;

    @Autowired
    private GamePuzzlePicService gamePuzzlePicService;
    @Autowired
    private GameAccountService gameAccountService;
    @Autowired
    private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(gamePuzzleAttendRecordController).build();
    }

    @Test
    @Transactional
    @Rollback
    public void loadingStandalone(){
        initData();
        GamePuzzleStandaloneParam param =new GamePuzzleStandaloneParam();
        param.setUserNum("M1");
        param.setSubStar(1);
        String json = JSONObject.toJSONString(param);
        RequestBuilder request =post("/gamePuzzleAttend/loadingStandalone").contentType(MediaType.APPLICATION_JSON).content(json);
        try{
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }catch (Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @Transactional
    @Rollback
    public void checkDeductionPointSucc(){
        initData();
        GamePuzzleStandaloneParam saveparam =new GamePuzzleStandaloneParam();
        saveparam.setUserNum("M1");
        saveparam.setSubStar(1);
        String savejson = JSONObject.toJSONString(saveparam);
        RequestBuilder saverequest =post("/gamePuzzleAttend/loadingStandalone").contentType(MediaType.APPLICATION_JSON).content(savejson);
        try{
            ResultActions saveperform = mvc.perform(saverequest);
            String content = saveperform.andReturn().getResponse().getContentAsString();
            GamePuzzleCheckDeductionPointParam param =new GamePuzzleCheckDeductionPointParam();
            String attendNum = JSONObject.parseObject(JSONObject.parseObject(content).get("model").toString()).get("attendNum").toString();
            param.setAttendNum(attendNum);
            param.setUserNum("M1");
            param.setAttendType(AttendTypeEnum.STANDALONE);
            String json = JSONObject.toJSONString(param);
            RequestBuilder request =post("/gamePuzzleAttend/checkDeductionPointSucc").contentType(MediaType.APPLICATION_JSON).content(json);
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        }catch(Exception e){
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }




    private void initData() {
        //初始化游戏账户
        GameAccountParam accountParam =new GameAccountParam();
        accountParam.setAccount("15012345678");
        accountParam.setType(GameTypeEnum.PUZZLE);
        accountParam.setUserNum("M1");
        gameAccountService.getAccountInfo(accountParam);
        //初始化拼图数据
        GamePuzzleParam puzzParam =new GamePuzzleParam();
        puzzParam.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam.setUserNum("M1");
        puzzParam.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam.setUserNickname("测试001");
        puzzParam.setImgPath("www.baidu.com");
        puzzParam.setIsCommon(true);
        puzzParam.setIsHard(true);
        puzzParam.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam);

        GamePuzzleParam puzzParam1 =new GamePuzzleParam();
        puzzParam1.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam1.setUserNum("M1");
        puzzParam1.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam1.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam1.setUserNickname("测试001");
        puzzParam1.setImgPath("www.youku.com");
        puzzParam1.setIsCommon(true);
        puzzParam1.setIsHard(true);
        puzzParam1.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam1);

        GamePuzzleParam puzzParam3 =new GamePuzzleParam();
        puzzParam3.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzParam3.setUserNum("M1");
        puzzParam3.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.HAVE_USE);
        puzzParam3.setPicStatusEnum(GamePuzzlePicStatusEnum.ENABLED);
        puzzParam3.setUserNickname("测试001");
        puzzParam3.setImgPath("www.google.com");
        puzzParam3.setIsCommon(true);
        puzzParam3.setIsHard(true);
        puzzParam3.setIsSimple(true);
        gamePuzzlePicService.saveGamePuzzlePic(puzzParam3);

        //
        GamePuzzleDifficultyDO difficulty = new GamePuzzleDifficultyDO();
        difficulty.setCoefficient(3);
        difficulty.setType(GameHardLevelEnum.SIMPLE.getVal());
        difficulty.setGmtCreate(new Date());
        difficulty.setGmtModified(new Date());
        difficulty.setChallengeTime(30);
        difficulty.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty);
        GamePuzzleDifficultyDO difficulty1 = new GamePuzzleDifficultyDO();
        difficulty1.setCoefficient(3);
        difficulty1.setType(GameHardLevelEnum.COMMONLY.getVal());
        difficulty1.setGmtCreate(new Date());
        difficulty1.setGmtModified(new Date());
        difficulty1.setChallengeTime(30);
        difficulty1.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty1);
        GamePuzzleDifficultyDO difficulty2 = new GamePuzzleDifficultyDO();
        difficulty2.setCoefficient(3);
        difficulty2.setType(GameHardLevelEnum.DIFFICULTY.getVal());
        difficulty2.setGmtCreate(new Date());
        difficulty2.setGmtModified(new Date());
        difficulty2.setChallengeTime(30);
        difficulty2.setSecScore("[{'level':1,'point':90,'second':1}]");
        gamePuzzleDifficultyDOMapper.insertSelective(difficulty2);
    }

}
