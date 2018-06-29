package com.lawu.eshop.activity.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDO;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.RedpacketSendRecordDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RedpacketSendRecordControllerTest {

    private MockMvc mvc;

    @Autowired
    private RedpacketSendRecordController redpacketSendRecordController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(redpacketSendRecordController).build();
    }

    @Autowired
    private RedpacketSendRecordDOMapper redpacketSendRecordDOMapper;

    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listSendRedpacketMchBillno() {
        RequestBuilder request = get("/redpacketSendRecord/listSendRedpacketMchBillno").param("offset", "0").param("pageSize", "0");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveRedpacketSendRecord() {
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setAccount("18267122441");
        attendDetailDO.setHeadimg("head/1503319642099389798.jpg");
        attendDetailDO.setNickname("E店用户");
        attendDetailDO.setUserNum("M894378717298556928");
        attendDetailDO.setWxOpenid("1234");
        attendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
        attendDetailDO.setGmtCreate(new Date());
        attendDetailDO.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.insertSelective(attendDetailDO);

        RedpacketSendRecordDO recordDO = new RedpacketSendRecordDO();
        recordDO.setActivityId(100);
        recordDO.setAttendDetailId(attendDetailDO.getId());
        recordDO.setUserNum("test");
        recordDO.setOpenid("test");
        recordDO.setReturnCode("success");
        recordDO.setReturnMsg("test");
        recordDO.setErrCode("10");
        recordDO.setErrCodeDes("10");
        recordDO.setMchBillno("12");
        recordDO.setSendListId("14");
        recordDO.setTotalAmount(100);
        recordDO.setStatus(RedpacketSendRecordStatusEnum.SENDING.getVal());
        redpacketSendRecordDOMapper.insertSelective(recordDO);

        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setActivityId(100);
        param.setAttendDetailId(attendDetailDO.getId());
        param.setUserNum("test");
        param.setOpenid("test");
        param.setReturnCode("success");
        param.setReturnMsg("test");
        param.setResultCode("success");
        param.setErrCode("10");
        param.setErrCodeDes("10");
        param.setMchBillno("12");
        param.setSendListId("14");
        param.setTotalAmount(100);
        param.setStatusEnum(RedpacketSendRecordStatusEnum.SENDING);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/redpacketSendRecord/saveRedpacketSendRecord").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateRedpacketSendRecordStatus() {
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setAccount("18267122441");
        attendDetailDO.setHeadimg("head/1503319642099389798.jpg");
        attendDetailDO.setNickname("E店用户");
        attendDetailDO.setUserNum("M894378717298556928");
        attendDetailDO.setWxOpenid("1234");
        attendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
        attendDetailDO.setGmtCreate(new Date());
        attendDetailDO.setActivityId(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID);
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.insertSelective(attendDetailDO);

        RedpacketSendRecordDO recordDO = new RedpacketSendRecordDO();
        recordDO.setActivityId(100);
        recordDO.setAttendDetailId(attendDetailDO.getId());
        recordDO.setUserNum("test");
        recordDO.setOpenid("test");
        recordDO.setReturnCode("success");
        recordDO.setReturnMsg("test");
        recordDO.setErrCode("10");
        recordDO.setErrCodeDes("10");
        recordDO.setMchBillno("12");
        recordDO.setSendListId("14");
        recordDO.setTotalAmount(100);
        recordDO.setStatus(RedpacketSendRecordStatusEnum.SENDING.getVal());
        redpacketSendRecordDOMapper.insertSelective(recordDO);

        RedpacketSendRecordParam param = new RedpacketSendRecordParam();
        param.setAttendDetailId(attendDetailDO.getId());
        param.setMchBillno("12");
        param.setRcvTime(new Date());
        param.setRefundTime(new Date());
        param.setStatusEnum(RedpacketSendRecordStatusEnum.RECEIVED);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = put("/redpacketSendRecord/updateRedpacketSendRecordStatus").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
