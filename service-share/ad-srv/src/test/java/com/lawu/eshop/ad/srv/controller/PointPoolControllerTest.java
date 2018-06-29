package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class PointPoolControllerTest {
	
	 private MockMvc mvc;
	
	 @Autowired
     private  PointPoolController  pointPoolController;
	 
	 @Autowired
     private PointPoolDOMapper pointPoolDOMapper;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(pointPoolController).build();
     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void selectMemberList() {
    	 PointPoolDO pointPoolDO=new PointPoolDO();
         pointPoolDO.setAdId(1l);
         pointPoolDO.setGmtCreate(new Date());
         pointPoolDO.setGmtModified(new Date());
         pointPoolDO.setMerchantId(1002l);
         pointPoolDO.setOrdinal(0);
         pointPoolDO.setPoint(BigDecimal.valueOf(15));
         pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
         pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PACKET.val);
         pointPoolDO.setMemberId(1l);
         pointPoolDO.setMemberNum("aaa");
         pointPoolDOMapper.insert(pointPoolDO);
         try {
             RequestBuilder request = get("/pointPool/selectMemberList/"+pointPoolDO.getMemberId());
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }

}
