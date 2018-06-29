package com.lawu.eshop.beh.analyze.srv.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.beh.analyze.constants.ProcessEnum;
import com.lawu.eshop.beh.analyze.srv.BehAnalyzeSrvApplicationTest;
import com.lawu.eshop.beh.analyze.srv.domain.InviteAbnormalDecideRecordDO;
import com.lawu.eshop.beh.analyze.srv.mapper.InviteAbnormalDecideRecordDOMapper;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2018/1/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BehAnalyzeSrvApplicationTest.class)
@WebAppConfiguration
public class InviteAbnormalDecideRecordControllerTest {
    private MockMvc mvc;

    @Autowired
    private InviteAbnormalDecideRecordDOMapper inviteAbnormalDecideRecordDOMapper;

    @Autowired
    private InviteAbnormalDecideRecordController inviteAbnormalDecideRecordController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(inviteAbnormalDecideRecordController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void get() {
        try {
            RequestBuilder request = MockMvcRequestBuilders.get("/inviteAbnormalDecideRecord/2");
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M123456");
        recordDO.setAccount("1311111111");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        try {
            RequestBuilder request = MockMvcRequestBuilders.get("/inviteAbnormalDecideRecord/" + recordDO.getId());
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Test
    @Rollback
    public void notProcessed() {
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M123456");
        recordDO.setAccount("1311111111");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        try {
            RequestBuilder request = MockMvcRequestBuilders.put("/inviteAbnormalDecideRecord/notProcessed/" + recordDO.getId());
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    @Rollback
    @Transactional(rollbackFor = Exception.class)
    public void freeze() {
        InviteAbnormalDecideRecordDO recordDO = new InviteAbnormalDecideRecordDO();
        recordDO.setGmtModified(new Date());
        recordDO.setGmtCreate(new Date());
        recordDO.setUserNum("M123456");
        recordDO.setAccount("1311111111");
        recordDO.setType(UserTypeEnum.MEMBER.getValue());
        recordDO.setIsAbnormal(false);
        recordDO.setIsEarlyHf(false);
        recordDO.setIsShortHf(true);
        recordDO.setIsIpHf(false);
        recordDO.setIsNoticed(false);
        recordDO.setIsManyShortHf(false);
        recordDO.setIsLongHf(false);
        recordDO.setIsManyLongHf(false);
        recordDO.setProcessType(ProcessEnum.NORMAL.getVal());
        recordDO.setIsOneDayHf(false);
        inviteAbnormalDecideRecordDOMapper.insertSelective(recordDO);
        try {
            RequestBuilder request = MockMvcRequestBuilders.put("/inviteAbnormalDecideRecord/freeze/" + recordDO.getId());
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
}
