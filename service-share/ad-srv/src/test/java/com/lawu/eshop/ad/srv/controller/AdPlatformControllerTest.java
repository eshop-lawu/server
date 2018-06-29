package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import com.lawu.eshop.ad.constants.AdPlatformStatusEnum;
import com.lawu.eshop.ad.constants.PositionEnum;
import com.lawu.eshop.ad.constants.TypeEnum;
import com.lawu.eshop.ad.param.AdPlatformFindParam;
import com.lawu.eshop.ad.param.AdPlatformParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.AdPlatformDO;
import com.lawu.eshop.ad.srv.mapper.AdPlatformDOMapper;
import com.lawu.framework.web.HttpCode;

/**
 * @author zhangrc
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class AdPlatformControllerTest {

    private MockMvc mvc;

    @Autowired
    private AdPlatformController adPlatformController;

    @Autowired
    private AdPlatformDOMapper adPlatformDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(adPlatformController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
    	AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setRegionPath("44/4403/440305");
    	String requestJson = JSONObject.toJSONString(adPlatformParam);
        RequestBuilder request = post("/adPlatform/saveAdPlatform").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void selectByPosition() {
    	
    	 String requestListJson = JSONObject.toJSONString(PositionEnum.SHOPPING_HOT);
    	
        try {
            RequestBuilder request = post("/adPlatform/selectByPosition").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectList() {
    	AdPlatformFindParam param=new AdPlatformFindParam();
        param.setCurrentPage(1);
		param.setPageSize(20);
		param.setTitle("要购物");
		param.setPositionEnum(PositionEnum.SHOPPING_HOT);
        String requestListJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/adPlatform/selectList").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
  
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void removeAdPlatform() {
    	AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.DOWN.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
		
        try {
            RequestBuilder request = delete("/adPlatform/removeAdPlatform/"+adPlatformDO.getId());
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_NO_CONTENT )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void unShelve() {
    	AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
		
        try {
            RequestBuilder request = put("/adPlatform/unShelve/"+adPlatformDO.getId());
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void onShelve() {
    	AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.DOWN.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
		
        try {
            RequestBuilder request = put("/adPlatform/onShelve/"+adPlatformDO.getId());
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void update() {
    	AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
		
		AdPlatformParam adPlatformParam=new AdPlatformParam();
		adPlatformParam.setTitle("要购物热门商品");
		adPlatformParam.setPositionEnum(PositionEnum.SHOPPING_HOT);
		adPlatformParam.setContent("测试");
		adPlatformParam.setTypeEnum(TypeEnum.TYPE_PRODUCT);
		adPlatformParam.setProductId(10061l);
		adPlatformParam.setRegionPath("44/4403/440305");
		adPlatformParam.setAdId(1001l);
    	String requestJson = JSONObject.toJSONString(adPlatformParam);
		
        try {
            RequestBuilder request = post("/adPlatform/update/"+adPlatformDO.getId()).contentType(MediaType.APPLICATION_JSON).content(requestJson);
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void select() {
    	AdPlatformDO adPlatformDO=new AdPlatformDO();
		adPlatformDO.setTitle("要购物热门商品ss");
		adPlatformDO.setPosition(PositionEnum.SHOPPING_HOT.val);
		adPlatformDO.setContent("测试");
		adPlatformDO.setType(TypeEnum.TYPE_PRODUCT.val);
		adPlatformDO.setProductId(10061l);
		adPlatformDO.setGmtCreate(new Date());
		adPlatformDO.setGmtModified(new Date());
		adPlatformDO.setStatus(AdPlatformStatusEnum.UP.val);
		adPlatformDO.setMediaUrl("ad_image/1494240515322705608.jpg");

		adPlatformDOMapper.insert(adPlatformDO);
        try {
            RequestBuilder request = get("/adPlatform/select/"+adPlatformDO.getId());
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAdPlatformByTypePosition() {
    	
        try {
            RequestBuilder request = get("/adPlatform/getAdPlatformByTypePosition").param("typeEnum","TYPE_STORE").param("positionEnum","POSITON_SHOP_TOP");
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getAdPlatformByTypePositionRegionPath() {
    	
        try {
            RequestBuilder request = get("/adPlatform/getAdPlatformByTypePositionRegionPath").param("typeEnum","TYPE_STORE").param("positionEnum","POSITON_SHOP_TOP").param("regionPath", "11/1101/110101");
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectByProductIdAndStatus() {
    	
        try {
            RequestBuilder request = get("/adPlatform/selectByProductIdAndStatus").param("productId", "1");
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    
}
