package com.lawu.eshop.user.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.user.param.UserFreezeRecordParam;
import com.lawu.eshop.user.srv.UserSrvApplicationTest;
import com.lawu.framework.web.HttpCode;

/**
 * @author meishuquan
 * @date 2017/9/11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserSrvApplicationTest.class)
@WebAppConfiguration
public class UserFreezeRecordControllerTest {

    private MockMvc mvc;

    @Autowired
    private UserFreezeRecordController userFreezeRecordController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userFreezeRecordController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveUserFreezeRecord() {
        UserFreezeRecordParam param = new UserFreezeRecordParam();
        param.setUserNum("M0001");
        param.setAccount("13666666666");
        param.setUserType(UserTypeEnum.MEMBER.getValue());
        param.setCause("系统冻结");
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/userFreezeRecord/saveUserFreezeRecord").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

}
