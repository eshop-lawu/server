package com.lawu.eshop.mall.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.dto.DiscountPackagePurchaseNotesDTO;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.DiscountPackagePurchaseNotesDO;
import com.lawu.eshop.mall.srv.mapper.DiscountPackagePurchaseNotesDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangyong
 * @date 2017/7/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class DiscountPackagePurchaseNotesControllerTest {
    private MockMvc mvc;

    @Autowired
    private DiscountPackagePurchaseNotesController discountPackagePurchaseNotesController;

    @Autowired
    private DiscountPackagePurchaseNotesDOMapper discountPackagePurchaseNotesDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(discountPackagePurchaseNotesController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void list() throws Exception {
    	DiscountPackagePurchaseNotesDO expected = new DiscountPackagePurchaseNotesDO();
    	expected.setGmtCreate(new Date());
    	expected.setNote("免费停车");
    	discountPackagePurchaseNotesDOMapper.insert(expected);
    	
        RequestBuilder request = get("/discountPackagePurchaseNotes/list");
        ResultActions perform = mvc.perform(request);
        MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK))
        		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
        		.andExpect(MockMvcResultMatchers.jsonPath("$.ret").value(1000))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();
        
        List<DiscountPackagePurchaseNotesDTO> actual = JSONObject.parseArray(JSONObject.parseObject(mvcResult.getResponse().getContentAsString()).getString("model"), DiscountPackagePurchaseNotesDTO.class);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
        assertDiscountPackagePurchaseNotesDTO(expected, actual.get(0));
    }
    
    private void assertDiscountPackagePurchaseNotesDTO(DiscountPackagePurchaseNotesDO expected, DiscountPackagePurchaseNotesDTO actual) {
    	Assert.assertNotNull(actual);
    	Assert.assertEquals(expected.getNote(), actual.getNote());
    	Assert.assertEquals(expected.getId(), actual.getId());
    }
}
