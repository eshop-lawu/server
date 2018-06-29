package com.lawu.eshop.activity.srv.controller;

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

import com.lawu.eshop.activity.srv.ActivitySrvApplicationTest;
import com.lawu.framework.web.HttpCode;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
/**
 * 
 * @author lihj
 * @date 2018年5月8日
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ActivitySrvApplicationTest.class)
public class RichAccountControllerTest {
	private MockMvc mvc;
	
	@Autowired
	private RichAccountController richAccountController;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(richAccountController).build();
	}

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getPersonalRichAccountInfo(){
    	 RequestBuilder request =get("/richAccount/getPersonalRichAccountInfo").param("userNum","M00001");
    	 try{
    		 ResultActions perform = mvc.perform(request);
    		 MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
             Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
    	 }catch(Exception e){
    		 Assert.fail(e.getMessage());
    	 }
    }
	
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getRichInfo(){
    	RequestBuilder request =get("/richAccount/getRichInfo");
    	try{
    		ResultActions perform = mvc.perform(request);
   		 	MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
    	}catch(Exception e){
    		Assert.fail(e.getMessage());
    	}
    }
    
    
	
}
