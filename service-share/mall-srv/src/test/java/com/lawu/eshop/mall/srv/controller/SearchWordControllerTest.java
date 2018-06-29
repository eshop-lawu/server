package com.lawu.eshop.mall.srv.controller;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.mall.constants.SearchWordTypeEnum;
import com.lawu.eshop.mall.param.SearchWordParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.domain.SearchWordDO;
import com.lawu.eshop.mall.srv.mapper.SearchWordDOMapper;
import com.lawu.framework.web.HttpCode;

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

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author zhangyong
 * @date 2017/7/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
@WebAppConfiguration
public class SearchWordControllerTest {
    private MockMvc mvc;

    @Autowired
    private  SearchWordController searchWordController;

    @Autowired
    private SearchWordDOMapper searchWordDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(searchWordController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveSearchWord(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = post("/searchWord/saveSearchWord").param("word","word").param("searchWordTypeEnum","WORD_TYPE_STORE");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void deleteSearchWord(){
        RequestBuilder request = null;
        ResultActions perform = null;
        request = delete("/searchWord/deleteSearchWord/1");
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SearchWordDO searchWordDO = new SearchWordDO();
        searchWordDO.setGmtCreate(new Date());
        searchWordDO.setType(SearchWordTypeEnum.WORD_TYPE_PRODUCT.val);
        searchWordDO.setWord("111");
        searchWordDOMapper.insert(searchWordDO);
        request = delete("/searchWord/deleteSearchWord/"+searchWordDO.getId());
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void listSearchWord(){
        SearchWordParam param = new SearchWordParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setSearchWordTypeEnum(SearchWordTypeEnum.WORD_TYPE_PRODUCT);
        String data = JSONObject.toJSONString(param);
        RequestBuilder request = null;
        ResultActions perform = null;
        request = post("/searchWord/listSearchWord").contentType(MediaType.APPLICATION_JSON).content(data);
        try {
            perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
