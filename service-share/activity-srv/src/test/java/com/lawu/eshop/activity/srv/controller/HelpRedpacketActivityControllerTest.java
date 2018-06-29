package com.lawu.eshop.activity.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.dto.GenerateLargeRedpacketDTO;
import com.lawu.eshop.activity.dto.GenerateRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityQueryDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.GenerateRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.event.GenerateNormalRedpacketEvent;
import com.lawu.eshop.activity.srv.event.GenerateNormalRedpacketEventHandle;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class HelpRedpacketActivityControllerTest {
    
    @Autowired
    private WebApplicationContext context;
    private MockMvc mvc;
    
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    @Autowired
    private HelpRedpacketActivityDOMapper helpRedpacketActivityDOMapper;
    
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
    @Autowired
    private GenerateNormalRedpacketEventHandle generateNormalRedpacketEventHandle;
    
    @SuppressWarnings("deprecation")
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void isOpen() throws Exception {
        HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setRedpacketType(RedPacketTypeEnum.WX.getVal());
        helpRedpacketActivityDOMapper.updateByPrimaryKey(expected);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/isOpen").contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        HelpRedpacketActivityOpenDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), HelpRedpacketActivityOpenDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getIsOpen(), actual.getOpen());
        Assert.assertNotNull(actual.getStatus());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getIsCloseEntry(), actual.getCloseEntry());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void detail() throws Exception {
        HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setTotalAutoAmount(new BigDecimal(0));
        expected.setTotalManualAmount(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(expected);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/detail/" + expected.getId()).contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        HelpRedpacketActivityDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), HelpRedpacketActivityDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getEndTime().getTime(), actual.getEndTime().getTime(), 1000);
        Assert.assertEquals(expected.getIsOpen(), actual.getOpen());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getRegEndTime().getTime(), actual.getRegEndTime().getTime(), 1000);
        Assert.assertEquals(expected.getRegStartTime().getTime(), actual.getRegStartTime().getTime(), 1000);
        Assert.assertEquals(expected.getStartTime().getTime(), actual.getStartTime().getTime(), 1000);
        Assert.assertNotNull(actual.getStatus());
        Assert.assertEquals(expected.getTotalAutoAmount().doubleValue(), actual.getTotalAutoAmount().doubleValue(), 0D);
        Assert.assertEquals(expected.getTotalManualAmount().doubleValue(), actual.getTotalManualAmount().doubleValue(), 0D);
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(expected.getIsCloseEntry(), actual.getCloseEntry());
        Assert.assertEquals(expected.getRedpacketType(), actual.getRedpacketType());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void update() throws Exception {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(0));
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        HelpRedpacketActivityUpdateParam expected = new HelpRedpacketActivityUpdateParam();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(0.01));
        expected.setMinRedpacket(new BigDecimal(0.01));
        expected.setMultiple(new BigDecimal(0.01));
        expected.setOpen(true);
        expected.setCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setRedpacketType(RedPacketTypeEnum.BALANCE);
        String content = JSONObject.toJSONString(expected);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/update/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        HelpRedpacketActivityBO actual = helpRedpacketActivityService.get(helpRedpacketActivityDO.getId());
        Assert.assertEquals(expected.getActivityTheme(), actual.getActivityTheme());
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getOpen(), actual.getIsOpen());
        Assert.assertEquals(expected.getRegEndTime(), actual.getRegEndTime());
        Assert.assertEquals(expected.getRegStartTime(), actual.getRegStartTime());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(HelpRedpacketActivityStatusEnum.END, actual.getStatus());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getCloseEntry(), actual.getIsCloseEntry());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void generateLargeRedpacket() throws Exception {
        /*
         *  活动已经结束
         *  未生成大额红包
         */
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        HelpRedpacketActivityBO helpRedpacketActivityBO = helpRedpacketActivityService.get(helpRedpacketActivityDO.getId());
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityBO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        HelpRedpacketAttendDetailDO record1 = new HelpRedpacketAttendDetailDO();
        record1.setAccount("123457");
        record1.setActivityId(helpRedpacketActivityBO.getId());
        record1.setGmtCreate(new Date());
        record1.setGmtModified(new Date());
        record1.setHeadimg("test.jpg");
        record1.setHelpCount(0);
        record1.setNickname("test");
        record1.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record1.setUserNum("M00002");
        helpRedpacketAttendDetailDOMapper.insert(record1);
        recordList.add(record1);
        
        GenerateLargeRedpacketParam param = new GenerateLargeRedpacketParam();
        param.setList(new ArrayList<>());
        GenerateRedpacketParam item = new GenerateRedpacketParam();
        // 生成1个100块的大奖红包
        item.setRedpacketAmount(new BigDecimal(100));
        item.setRedpacketIdx(0);
        item.setRedpacketQuantity(1);
        param.getList().add(item);
        String content = JSONObject.toJSONString(param);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/generateLargeRedpacket/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        GenerateLargeRedpacketDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), GenerateLargeRedpacketDTO.class);
        Assert.assertNotNull(actual);
        GenerateRedpacketDTO generateRedpacketDTO = actual.getRedpacketList().get(0);
        Assert.assertNotNull(generateRedpacketDTO);
        recordList.forEach(recordItem -> {
            if (!generateRedpacketDTO.getAttendDetailId().equals(recordItem.getId())) {
                BigDecimal recordMaxRedpacketAmount = helpRedpacketActivityBO.getMaxRedpacket().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityBO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
                Assert.assertEquals(recordMaxRedpacketAmount.doubleValue(), actual.getExpectedMaxRedpacketAmount().doubleValue(), 0D);
                BigDecimal recordMinRedpacketAmount = helpRedpacketActivityBO.getMinRedpacket().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityBO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP);
                Assert.assertEquals(recordMinRedpacketAmount.doubleValue(), actual.getExpectedMinRedpacketAmount().doubleValue(), 0D);
                return;
            }
            Assert.assertEquals(recordItem.getAccount(), generateRedpacketDTO.getAccount());
            Assert.assertEquals(item.getRedpacketAmount().doubleValue(), generateRedpacketDTO.getOriginalMoney().doubleValue(), 0D);
            Assert.assertEquals(item.getRedpacketAmount().multiply(new BigDecimal(1 + recordItem.getHelpCount() * helpRedpacketActivityBO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP), generateRedpacketDTO.getFinalMoney());
            Assert.assertEquals(item.getRedpacketIdx(), generateRedpacketDTO.getGenerateLargeRedpacketIndex());
            Assert.assertEquals(recordItem.getHelpCount(), generateRedpacketDTO.getHelpCount());
            Assert.assertEquals(recordItem.getUserNum(), generateRedpacketDTO.getUserNum());
            Assert.assertEquals(recordItem.getHeadimg(), generateRedpacketDTO.getHeadimg());
        });
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(JSONObject.toJSONString(param), actualHelpRedpacketActivityDO.getManualInfo());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void saveLargeRedpacket() throws Exception {
        GenerateLargeRedpacketParam param = new GenerateLargeRedpacketParam();
        param.setList(new ArrayList<>());
        GenerateRedpacketParam item = new GenerateRedpacketParam();
        // 生成1个100块的大奖红包
        item.setRedpacketAmount(new BigDecimal(100));
        item.setRedpacketIdx(0);
        item.setRedpacketQuantity(1);
        param.getList().add(item);
        
        /*
         *  活动已经结束
         *  已经生成过大额红包
         */
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setManualInfo(JSONObject.toJSONString(param));
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        helpRedpacketAttendDetailDOMapper.insert(record);
        
        List<SaveRedpacketParam> params = new ArrayList<>();
        SaveRedpacketParam saveRedpacketParam = new SaveRedpacketParam();
        saveRedpacketParam.setAttendDetailId(record.getId());
        saveRedpacketParam.setGenerateLargeRedpacketIndex(item.getRedpacketIdx());
        params.add(saveRedpacketParam);
        String content = JSONObject.toJSONString(params);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/saveLargeRedpacket/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(record.getId());
        Assert.assertNotNull(actual.getAllotTime());
        Assert.assertEquals(item.getRedpacketAmount().doubleValue(), actual.getOriginalMoney().doubleValue(), 0D);
        Assert.assertEquals(item.getRedpacketAmount().multiply(new BigDecimal(1 + actual.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() * 100, actual.getFinalMoney().intValue(), 0D);
        Assert.assertEquals(true, actual.getIsLucky());
        Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(actualHelpRedpacketActivityDO.getTotalManualAmount().doubleValue(), actual.getFinalMoney().doubleValue() / 100, 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void generateNormalRedpacket() throws Exception {
        /*
         *  活动已经结束
         *  已经保存过大额红包
         */
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        GenerateNormalRedpacketParam param = new GenerateNormalRedpacketParam();
        param.setTotalAutoAmount(new BigDecimal(50));
        String content = JSONObject.toJSONString(param);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/generateNormalRedpacket/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), actualHelpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), 0D);
        
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        event.setTotalAutoAmount(param.getTotalAutoAmount());
        generateNormalRedpacketEventHandle.execute(event);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void againGenerateNormalRedpacket() throws Exception {
        /*
         *  活动已经结束
         *  已经保存过大额红包
         */
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(50));
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        GenerateNormalRedpacketParam param = new GenerateNormalRedpacketParam();
        param.setTotalAutoAmount(new BigDecimal(50));
        String content = JSONObject.toJSONString(param);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/againGenerateNormalRedpacket/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        HelpRedpacketActivityDO actualHelpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(helpRedpacketActivityDO.getId());
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), actualHelpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), 0D);
        
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        event.setTotalAutoAmount(param.getTotalAutoAmount());
        generateNormalRedpacketEventHandle.execute(event);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(param.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void continueGenerateNormalRedpacket() throws Exception {
        /*
         *  活动已经结束
         *  已经保存过大额红包
         */
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date(System.currentTimeMillis() + 10 * 60 * 1000));
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(50));
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加两条红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ALLOT.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setOriginalMoney(new BigDecimal(1));
        record.setFinalMoney(100);
        record.setAllotTime(new Date());
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/continueGenerateNormalRedpacket/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        GenerateNormalRedpacketEvent event = new GenerateNormalRedpacketEvent(new Object());
        event.setActivityId(helpRedpacketActivityDO.getId());
        generateNormalRedpacketEventHandle.execute(event);
        
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            HelpRedpacketAttendDetailDO actual = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(item.getId());
            Assert.assertNotNull(actual.getAllotTime());
            Assert.assertEquals(ActivityAttendStatusEnum.ALLOT.getVal(), actual.getStatus());
            Assert.assertNotNull(actual.getOriginalMoney());
            Assert.assertNotNull(actual.getFinalMoney());
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(actual.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(helpRedpacketActivityDO.getTotalAutoAmount().doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void getNormalRedpacketTotalAmount() throws Exception {
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setTotalManualAmount(new BigDecimal(100));
        helpRedpacketActivityDO.setTotalAutoAmount(new BigDecimal(50));
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        
        // 添加红包参与详情记录
        List<HelpRedpacketAttendDetailDO> recordList = new ArrayList<>();
        // 是大奖
        HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123456");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00001");
        record.setIsLucky(true);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123457");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00002");
        record.setIsLucky(false);
        record.setFinalMoney(10000);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        record = new HelpRedpacketAttendDetailDO();
        record.setAccount("123458");
        record.setActivityId(helpRedpacketActivityDO.getId());
        record.setGmtCreate(new Date());
        record.setGmtModified(new Date());
        record.setHeadimg("test.jpg");
        record.setHelpCount(0);
        record.setNickname("test");
        record.setStatus(ActivityAttendStatusEnum.ATTEND.getVal());
        record.setUserNum("M00003");
        record.setIsLucky(false);
        record.setFinalMoney(10000);
        helpRedpacketAttendDetailDOMapper.insert(record);
        recordList.add(record);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/getNormalRedpacketTotalAmount/" + helpRedpacketActivityDO.getId()).contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        BigDecimal actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), BigDecimal.class);
        Assert.assertNotNull(actual);
        BigDecimal totalAutoAmount = new BigDecimal(0);
        for(HelpRedpacketAttendDetailDO item : recordList) {
            if (item.getIsLucky()) {
                continue;
            }
            totalAutoAmount = totalAutoAmount.add(new BigDecimal(item.getFinalMoney().doubleValue() / 100));
        }
        Assert.assertEquals(actual.doubleValue(), totalAutoAmount.doubleValue(), 0D);
    }
    
    @SuppressWarnings("unchecked")
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void list() throws Exception {
        Map<Integer, HelpRedpacketActivityDO> map = new HashMap<>();
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRedpacketType(RedPacketTypeEnum.BALANCE.getVal());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        map.put(helpRedpacketActivityDO.getId(), helpRedpacketActivityDO);
        
        RedpacketActivityQueryParam param = new RedpacketActivityQueryParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        String content = JSONObject.toJSONString(param);
        RequestBuilder request = MockMvcRequestBuilders.put("/helpRedpacketActivity/list").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        Page<JSONObject> page = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), Page.class);
        Assert.assertNotNull(page);
        Assert.assertNotNull(page.getRecords());
        Assert.assertNotNull(page.getTotalCount());
        Assert.assertEquals(param.getCurrentPage(), page.getCurrentPage());
        List<HelpRedpacketActivityQueryDTO> list = JSONObject.parseArray(page.getRecords().toString(), HelpRedpacketActivityQueryDTO.class);
        for (HelpRedpacketActivityQueryDTO item : list) {
            HelpRedpacketActivityDO actual = map.get(item.getId());
            if (actual == null) continue;
            Assert.assertNotNull(item);
            Assert.assertEquals(actual.getId(), item.getId());
            Assert.assertEquals(actual.getIsOpen(), item.getOpen());
            Assert.assertEquals(actual.getMaxRedpacket().doubleValue(), item.getMaxRedpacket().doubleValue(), 0D);
            Assert.assertEquals(actual.getMinRedpacket().doubleValue(), item.getMinRedpacket().doubleValue(), 0D);
            Assert.assertEquals(actual.getMultiple().doubleValue(), item.getMultiple().doubleValue(), 0D);
            Assert.assertEquals(actual.getRegStartTime().getTime(), item.getRegStartTime().getTime(), 1000L);
            Assert.assertEquals(HelpRedpacketActivityStatusEnum.END, item.getStatus());
        }
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void save() throws Exception {
        HelpRedpacketActivitySaveParam expected = new HelpRedpacketActivitySaveParam();
        expected.setActivityTheme("testSave");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(90));
        expected.setMinRedpacket(new BigDecimal(0.8));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setOpen(true);
        expected.setCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setStartPic("startPic.jpg");
        expected.setEndPic("endPic.jpg");
        expected.setEndUrl("endUrl");
        expected.setRedpacketType(RedPacketTypeEnum.BALANCE);
        
        String content = JSONObject.toJSONString(expected);
        
        RequestBuilder request = MockMvcRequestBuilders.post("/helpRedpacketActivity/save").contentType(MediaType.APPLICATION_JSON).content(content);
        ResultActions perform = mvc.perform(request);
        perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_CREATED))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        
        HelpRedpacketActivityDOExample example = new HelpRedpacketActivityDOExample();
        example.createCriteria().andActivityThemeEqualTo(expected.getActivityTheme());
        HelpRedpacketActivityDO actual = helpRedpacketActivityDOMapper.selectByExample(example).get(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getEndTime(), actual.getEndTime());
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(expected.getOpen(), actual.getIsOpen());
        Assert.assertEquals(expected.getMaxRedpacket().doubleValue(), actual.getMaxRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMinRedpacket().doubleValue(), actual.getMinRedpacket().doubleValue(), 0D);
        Assert.assertEquals(expected.getMultiple().doubleValue(), actual.getMultiple().doubleValue(), 0D);
        Assert.assertEquals(expected.getRegEndTime(), actual.getRegEndTime());
        Assert.assertEquals(expected.getRegStartTime(), actual.getRegStartTime());
        Assert.assertEquals(expected.getStartTime(), actual.getStartTime());
        Assert.assertEquals(expected.getWxActName(), actual.getWxActName());
        Assert.assertEquals(expected.getWxRemark(), actual.getWxRemark());
        Assert.assertEquals(expected.getWxSendName(), actual.getWxSendName());
        Assert.assertEquals(expected.getWxWishing(), actual.getWxWishing());
        Assert.assertEquals(expected.getStartPic(), actual.getStartPic());
        Assert.assertEquals(expected.getEndPic(), actual.getEndPic());
        Assert.assertEquals(expected.getEndUrl(), actual.getEndUrl());
        Assert.assertEquals(expected.getCloseEntry(), actual.getIsCloseEntry());
        Assert.assertEquals(expected.getRedpacketType().getVal(), actual.getRedpacketType());
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void openActivityList() throws Exception {
        Map<Integer, HelpRedpacketActivityDO> map = new HashMap<>();
        HelpRedpacketActivityDO helpRedpacketActivityDO = new HelpRedpacketActivityDO();
        helpRedpacketActivityDO.setActivityTheme("test");
        helpRedpacketActivityDO.setEndTime(new Date());
        helpRedpacketActivityDO.setMaxRedpacket(new BigDecimal(80));
        helpRedpacketActivityDO.setMinRedpacket(new BigDecimal(1));
        helpRedpacketActivityDO.setMultiple(new BigDecimal(0.5));
        helpRedpacketActivityDO.setIsOpen(true);
        helpRedpacketActivityDO.setRegEndTime(new Date());
        helpRedpacketActivityDO.setRegStartTime(new Date());
        helpRedpacketActivityDO.setStartTime(new Date());
        helpRedpacketActivityDO.setWxActName("wxActName");
        helpRedpacketActivityDO.setWxRemark("wxRemark");
        helpRedpacketActivityDO.setWxSendName("wxSendName");
        helpRedpacketActivityDO.setWxWishing("wxWishing");
        helpRedpacketActivityDO.setGmtCreate(new Date());
        helpRedpacketActivityDO.setGmtModified(new Date());
        helpRedpacketActivityDO.setIsCloseEntry(false);
        helpRedpacketActivityDO.setRedpacketType(RedPacketTypeEnum.BALANCE.getVal());
        helpRedpacketActivityDOMapper.insert(helpRedpacketActivityDO);
        map.put(helpRedpacketActivityDO.getId(), helpRedpacketActivityDO);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/openActivityList").contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        List<HelpRedpacketActivityOpenDTO> list = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), HelpRedpacketActivityOpenDTO.class);
        Assert.assertNotNull(list);
        Assert.assertEquals(1, list.size());
        for (HelpRedpacketActivityOpenDTO item : list) {
            HelpRedpacketActivityDO expected = map.get(item.getId());
            Assert.assertNotNull(item);
            Assert.assertEquals(expected.getId(), item.getId());
            Assert.assertEquals(expected.getIsOpen(), item.getOpen());
            Assert.assertEquals(HelpRedpacketActivityStatusEnum.END, item.getStatus());
            Assert.assertEquals(expected.getStartPic(), item.getStartPic());
            Assert.assertEquals(expected.getEndPic(), item.getEndPic());
            Assert.assertEquals(expected.getEndUrl(), item.getEndUrl());
            Assert.assertEquals(expected.getIsCloseEntry(), item.getCloseEntry());
        }
    }
    
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    @Test
    public void infoOfAttend() throws Exception {
        HelpRedpacketActivityDO expected = new HelpRedpacketActivityDO();
        expected.setActivityTheme("test");
        expected.setEndTime(new Date());
        expected.setMaxRedpacket(new BigDecimal(80));
        expected.setMinRedpacket(new BigDecimal(1));
        expected.setMultiple(new BigDecimal(0.5));
        expected.setIsOpen(true);
        expected.setIsCloseEntry(false);
        expected.setRegEndTime(new Date());
        expected.setRegStartTime(new Date());
        expected.setStartTime(new Date());
        expected.setWxActName("wxActName");
        expected.setWxRemark("wxRemark");
        expected.setWxSendName("wxSendName");
        expected.setWxWishing("wxWishing");
        expected.setTotalAutoAmount(new BigDecimal(0));
        expected.setTotalManualAmount(new BigDecimal(0));
        expected.setGmtCreate(new Date());
        expected.setGmtModified(new Date());
        expected.setRedpacketType(RedPacketTypeEnum.BALANCE.getVal());
        helpRedpacketActivityDOMapper.insert(expected);
        
        RequestBuilder request = MockMvcRequestBuilders.get("/helpRedpacketActivity/infoOfAttend/" + expected.getId().toString()).contentType(MediaType.APPLICATION_JSON);
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(MockMvcResultMatchers.status().is(HttpCode.SC_OK))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        RedpacketActivityInfoOfAttendDTO actual = JSONObject.parseObject(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), RedpacketActivityInfoOfAttendDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getRedpacketType(), actual.getRedpacketType().getVal());
    }
}
