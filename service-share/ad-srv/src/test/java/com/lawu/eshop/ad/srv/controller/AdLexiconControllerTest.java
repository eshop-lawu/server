package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.AdLexiconDOMapper;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.web.HttpCode;

/**
 * @author Leach
 * @date 2017/7/10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class AdLexiconControllerTest {

    private MockMvc mvc;

    @Autowired
    private AdLexiconController adLexiconController;

    @Autowired
    private AdLexiconDOMapper adLexiconDOMapper;
    
    @Autowired
    private AdDOMapper adDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(adLexiconController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
        RequestBuilder request = post("/adLexicon/save").param("title", "test");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectList() {
		AdDO adRecord = new AdDO();
		adRecord.setGmtCreate(new Date());
		adRecord.setGmtModified(new Date());
		adRecord.setAdCount(1);
		adRecord.setAreas("44");
		adRecord.setTitle("selectList-title");
		adRecord.setMerchantStoreId(1001l);
		adRecord.setMerchantStoreName("E店商家");
		adRecord.setManageType(ManageTypeEnum.ENTITY.getVal());
		adRecord.setLogoUrl("store/1494582624025648402.png");
		long adId = adDOMapper.insert(adRecord);
        RequestBuilder request = get("/adLexicon/selectList").param("adId", adRecord.getId().toString());
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

}
