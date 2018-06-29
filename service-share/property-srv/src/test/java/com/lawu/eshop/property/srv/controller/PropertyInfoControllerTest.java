package com.lawu.eshop.property.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.property.constants.FreezeStatusEnum;
import com.lawu.eshop.property.param.FreezeQueryParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.FreezeDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.mapper.FreezeDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.PwdUtil;

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

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author yangqh
 * @date 2017/7/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
@WebAppConfiguration
public class PropertyInfoControllerTest {

    private MockMvc mvc;

    @Autowired
    private PropertyInfoController propertyInfoController;

    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    @Autowired
    private FreezeDOMapper freezeDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(propertyInfoController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updatePayPwd() {
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = put("/propertyInfo/updatePayPwd/M10001").param("originalPwd","123456").param("newPwd","111111");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void resetPayPwd() {
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = put("/propertyInfo/resetPayPwd/M10001").param("newPwd","111111");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void setPayPwd() {
        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setPayPassword(PwdUtil.generate("123456"));
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = put("/propertyInfo/setPayPwd/M10001").param("newPwd","111111");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void isSetPayPwd() {

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = get("/propertyInfo/isSetPayPwd/M10001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void varifyPayPwd() {

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = get("/propertyInfo/varifyPayPwd").param("userNum","M10001").param("payPwd","111111");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPropertyinfoFreeze() {

        PropertyInfoDO propertyInfoDO = new PropertyInfoDO();
        propertyInfoDO.setUserNum("M10001");
        propertyInfoDO.setGmtCreate(new Date());
        propertyInfoDO.setBalance(new BigDecimal("200"));
        propertyInfoDO.setPoint(new BigDecimal("200"));
        propertyInfoDO.setLoveAccount(new BigDecimal("0"));
        propertyInfoDO.setFreeze(FreezeStatusEnum.FREEZE.getVal());
        propertyInfoDOMapper.insertSelective(propertyInfoDO);

        RequestBuilder request = get("/propertyInfo/getPropertyinfoFreeze/M10001");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getFreezeList() {
        FreezeDO fdo = new FreezeDO();
        fdo.setOrderNum("1111111111");
        fdo.setUserNum("M34343");
        fdo.setGmtCreate(new Date());
        fdo.setMoney(new BigDecimal(1));
        fdo.setBizId(1L);
        fdo.setDays(7);
        fdo.setFundType(new Byte("1"));
        fdo.setOriginalMoney(new BigDecimal(1));
        fdo.setStatus(new Byte("0"));
        freezeDOMapper.insert(fdo);

        FreezeQueryParam param = new FreezeQueryParam();
        param.setUserNum("M34343");
        param.setCurrentPage(1);
        param.setPageSize(10);
        String requestJson = JSONObject.toJSONString(param);
        RequestBuilder request = post("/propertyInfo/getFreezeList").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
