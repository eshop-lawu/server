package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.constants.MemberAdRecordStatusEnum;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.MemberAdRecordDO;
import com.lawu.eshop.ad.srv.mapper.MemberAdRecordDOMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class ClickAdCommissionControllerTest {
	

    private MockMvc mvc;

    @Autowired
    private ClickAdCommissionController clickAdCommissionController;
    
    @Autowired
    private MemberAdRecordDOMapper memberAdRecordDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(clickAdCommissionController).build();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getNoneCommissionAds() {
    	MemberAdRecordDO memberAdRecordDO=new MemberAdRecordDO();
        memberAdRecordDO.setAdId(1l);
        memberAdRecordDO.setClickDate(new Date());
        memberAdRecordDO.setGmtCommission(new Date());
        memberAdRecordDO.setGmtCreate(new Date());
        memberAdRecordDO.setMemberId(1l);
        memberAdRecordDO.setMemberNum("aa");
        memberAdRecordDO.setOriginalPoint(BigDecimal.valueOf(0.5));
        memberAdRecordDO.setPoint(BigDecimal.valueOf(0.4));
        memberAdRecordDO.setStatus(MemberAdRecordStatusEnum.NONE.getVal());
        memberAdRecordDOMapper.insert(memberAdRecordDO);
        
        try {
            RequestBuilder request = get("/commission/getNoneCommissionAds");
            ResultActions perform= mvc.perform(request);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void updateMemberAdRecardStatus() {
    	
        try {
            RequestBuilder request = post("/commission/updateMemberAdRecardStatus").param("id", "1");
            ResultActions perform= mvc.perform(request);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

}
