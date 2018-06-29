package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
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
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.param.PraiseWarnParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.FavoriteAdDOMapper;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.web.HttpCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class FavoriteAdControllerTest {
	
	 private MockMvc mvc;

     @Autowired
     private FavoriteAdController favoriteAdController;
     
     
     @Autowired
     private FavoriteAdDOMapper favoriteAdDOMapper;
     
     
     @Autowired
     private AdDOMapper adDOMapper;

     @Before
     public void setUp() throws Exception {
         mvc = MockMvcBuilders.standaloneSetup(favoriteAdController).build();
     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void save() {
         try {
             RequestBuilder request = put("/favoriteAd/save").param("memberId", "1").param("adId", "1").param("userNum", "M000001");
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void saveExist() {
    	 FavoriteAdDO favoriteAdDO = new FavoriteAdDO();
    	 favoriteAdDO.setAdId(1l);
    	 favoriteAdDO.setGmtCreate(new Date());
    	 favoriteAdDO.setMemberId(1l);
    	 favoriteAdDO.setMemberNum("M000001");
    	 favoriteAdDOMapper.insert(favoriteAdDO);
         try {
             RequestBuilder request = put("/favoriteAd/save").param("memberId", favoriteAdDO.getMemberId().toString()).param("adId", favoriteAdDO.getAdId().toString()).param("userNum", "M000001");
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void remove() {
    	 FavoriteAdDO favoriteAdDO=new FavoriteAdDO();
    	 favoriteAdDO.setAdId(1l);
    	 favoriteAdDO.setGmtCreate(new Date());
    	 favoriteAdDO.setMemberId(1l);
    	 favoriteAdDO.setMemberNum("M000001");
    	 favoriteAdDOMapper.insert(favoriteAdDO);
         try {
             RequestBuilder request = delete("/favoriteAd/remove/"+favoriteAdDO.getAdId()).param("memberId", "1");
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     
     
     /*@Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void selectMyFavoriteAd() {
    	 FavoriteAdParam paramQuery=new FavoriteAdParam();
     	 paramQuery.setCurrentPage(1);
     	 paramQuery.setPageSize(10);
     	 paramQuery.setTypeEnum(FavoriteAdTypeEnum.AD_TYPE_EGAIN);
         String requestListJson = JSONObject.toJSONString(paramQuery);
         try {
             RequestBuilder request = post("/favoriteAd/selectMyFavoriteAd").param("memberId", "1").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     */
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void isFavoriteAd() {
         try {
             RequestBuilder request = get("/favoriteAd/isFavoriteAd").param("adId", "1").param("memberId", "1");
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void updateIsSend() {
         try {
             RequestBuilder request = put("/favoriteAd/updateIsSend/"+1);
             ResultActions perform= mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }
     
     @Transactional(rollbackFor = Exception.class)
     @Rollback
     @Test
     public void selectFavoriteAdPraise() {
		AdDO ad = new AdDO();
		ad.setMerchantLatitude(BigDecimal.valueOf(22.547153));
		ad.setMerchantLongitude(BigDecimal.valueOf(113.960333));
		ad.setMerchantId(1002l);
		ad.setMerchantNum("B856392484215848969");
		ad.setMerchantStoreId(1001l);
		ad.setMerchantStoreName("E店商家");
		ad.setManageType(ManageTypeEnum.ENTITY.getVal());
		ad.setLogoUrl("store/1494582624025648402.png");
		ad.setMediaUrl("ad_image/1494582624025648401.png");
		ad.setAdCount(20);
		ad.setBeginTime(new Date());
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_PRAISE.getVal());
		ad.setGmtCreate(new Date());
		ad.setGmtModified(new Date());
         ad.setHits(0);
         ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
         Integer id=adDOMapper.insertSelective(ad);
    	 
    	 FavoriteAdDO favoriteAdDO=new FavoriteAdDO();
    	 favoriteAdDO.setAdId(ad.getId());
    	 favoriteAdDO.setGmtCreate(new Date());
    	 favoriteAdDO.setMemberId(1l);
    	 favoriteAdDO.setMemberNum("M000001");
    	 favoriteAdDOMapper.insert(favoriteAdDO);
         try {
			PraiseWarnParam param = new PraiseWarnParam();
			param.setCurrentPage(1);
			param.setPageSize(20);
	    	String requestJson = JSONObject.toJSONString(param);
			RequestBuilder request = get("/favoriteAd/selectFavoriteAdPraise").contentType(MediaType.APPLICATION_JSON).content(requestJson);
			ResultActions perform = mvc.perform(request);
             MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

         } catch (Exception e) {
             e.printStackTrace();
             Assert.fail(e.getMessage());
         }

     }

}
