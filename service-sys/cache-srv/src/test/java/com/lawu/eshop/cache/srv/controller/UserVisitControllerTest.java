package com.lawu.eshop.cache.srv.controller;

import com.lawu.eshop.cache.srv.CacheSrvApplicationTest;
import com.lawu.eshop.cache.srv.controller.UserVisitController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author Leach
 * @date 2017/6/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CacheSrvApplicationTest.class)
@WebAppConfiguration
public class UserVisitControllerTest {
    private MockMvc mvc;


    @Autowired
    private UserVisitController userVisitController;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(userVisitController).build();
    }

    @Ignore
    @Test
    public void getUserVisitRecords() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/userVisit/delVisitRecords").param("time", "20170703");
        try {
            ResultActions perform = mvc.perform(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
