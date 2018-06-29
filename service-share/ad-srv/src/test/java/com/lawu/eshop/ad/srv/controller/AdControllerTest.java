package com.lawu.eshop.ad.srv.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.ad.constants.AdEgainTypeEnum;
import com.lawu.eshop.ad.constants.AdPraiseStatusEnum;
import com.lawu.eshop.ad.constants.AdStatusEnum;
import com.lawu.eshop.ad.constants.AuditEnum;
import com.lawu.eshop.ad.constants.ClientTypeEnum;
import com.lawu.eshop.ad.constants.OrderTypeEnum;
import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PointPoolTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.constants.RelateTypeEnum;
import com.lawu.eshop.ad.param.AdFindParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.AdSolrParam;
import com.lawu.eshop.ad.param.AdSolrRealParam;
import com.lawu.eshop.ad.param.AdsolrFindParam;
import com.lawu.eshop.ad.param.ListAdParam;
import com.lawu.eshop.ad.param.OperatorAdParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.ad.srv.AdSrvApplicationTest;
import com.lawu.eshop.ad.srv.domain.AdDO;
import com.lawu.eshop.ad.srv.domain.FavoriteAdDO;
import com.lawu.eshop.ad.srv.domain.PointPoolDO;
import com.lawu.eshop.ad.srv.mapper.AdDOMapper;
import com.lawu.eshop.ad.srv.mapper.FavoriteAdDOMapper;
import com.lawu.eshop.ad.srv.mapper.PointPoolDOMapper;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.framework.web.HttpCode;
import com.lawu.utils.DateUtil;

/**
 * @author zhangrc
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdSrvApplicationTest.class)
@WebAppConfiguration
public class AdControllerTest {

    private MockMvc mvc;

    @Autowired
    private AdController adController;

    @Autowired
    private AdDOMapper adDOMapper;
    
    @Autowired
    private FavoriteAdDOMapper favoriteAdDOMapper;
    
    @Autowired
    private PointPoolDOMapper pointPoolDOMapper;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(adController).build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void save() {
    	AdSaveParam adSaveParam=new AdSaveParam();
    	adSaveParam.setLatitude(BigDecimal.valueOf(22.547153));
    	adSaveParam.setLongitude(BigDecimal.valueOf(113.960333));
    	adSaveParam.setMerchantId(1002l);
    	adSaveParam.setUserNum("B856392484215848969");
    	adSaveParam.setLogoUrl("store/1494582624025648402.png");
    	adSaveParam.setMerchantStoreId(1001l);
    	adSaveParam.setMerchantStoreName("E店商家");
    	adSaveParam.setManageType(ManageTypeEnum.ENTITY);
    	adSaveParam.setMediaUrl("ad_image/1494582624025648401.png");
    	AdParam param=new AdParam();
    	param.setAdCount(20);
    	param.setBeginTime("2017-05-17 11:35:00");
    	param.setContent("广告测试内容");
    	param.setPoint(BigDecimal.valueOf(0.5));
    	param.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
    	param.setRegionName("全国");
    	param.setTitle("广告测试标题");
    	param.setTotalPoint(BigDecimal.valueOf(100));
    	param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
    	param.setRelateType(RelateTypeEnum.PRODUCT_TYPE);
    	adSaveParam.setAdParam(param);
    	String requestJson = JSONObject.toJSONString(adSaveParam);
        RequestBuilder request = post("/ad/saveAd").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void savePacket() {
    	AdSaveParam adSaveParam=new AdSaveParam();
    	adSaveParam.setLatitude(BigDecimal.valueOf(22.547153));
    	adSaveParam.setLongitude(BigDecimal.valueOf(113.960333));
    	adSaveParam.setMerchantId(1002l);
    	adSaveParam.setUserNum("B856392484215848969");
    	adSaveParam.setLogoUrl("store/1494582624025648402.png");
    	adSaveParam.setMerchantStoreId(1001l);
    	adSaveParam.setMerchantStoreName("E店商家");
    	adSaveParam.setManageType(ManageTypeEnum.ENTITY);
    	adSaveParam.setMediaUrl("ad_image/1494582624025648401.png");
    	AdParam param=new AdParam();
    	param.setAdCount(20);
    	param.setPutWayEnum(PutWayEnum.PUT_WAY_COMMON);
    	param.setTotalPoint(BigDecimal.valueOf(100));
    	param.setTypeEnum(AdTypeEnum.AD_TYPE_PACKET);
    	adSaveParam.setAdParam(param);
    	String requestJson = JSONObject.toJSONString(adSaveParam);
        RequestBuilder request = post("/ad/saveAd").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void selectListByMerchant() {
    	AdMerchantParam adMerchantParam=new AdMerchantParam();
        adMerchantParam.setPageSize(10);
        adMerchantParam.setCurrentPage(1);
        adMerchantParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adMerchantParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        adMerchantParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);  
        String requestListJson = JSONObject.toJSONString(adMerchantParam);
        try {
            RequestBuilder request = post("/ad/selectListByMerchant").contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("memberId", "1");
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
    public void updateStatus() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
		
        try {
            RequestBuilder request = put("/ad/updateStatus/"+ad.getId());
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
    public void updateStatusTwoWeek() {
    	AdDO ad=new AdDO();
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
		ad.setBeginTime(DateUtil.getDateTimeFormat("2017-06-10 00:00:00"));
		ad.setContent("广告测试内容");
		ad.setPoint(BigDecimal.valueOf(0.5));
		ad.setPutWay(PutWayEnum.PUT_WAY_AREAS.val);
		ad.setRegionName("全国");
		ad.setTitle("广告测试标题");
		ad.setTotalPoint(BigDecimal.valueOf(100));
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
		
        try {
            RequestBuilder request = put("/ad/updateStatus/"+ad.getId());
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
    public void remove() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
		
        try {
            RequestBuilder request = put("/ad/remove/"+ad.getId());
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
    public void selectAbById() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        FavoriteAdDO favoriteAdDO=new FavoriteAdDO();
        favoriteAdDO.setAdId(ad.getId());
        favoriteAdDO.setMemberId(1l);
        favoriteAdDO.setMemberNum("M000001");
        favoriteAdDO.setGmtCreate(new Date());
        favoriteAdDOMapper.insert(favoriteAdDO);
		
        try {
            RequestBuilder request = get("/ad/selectAbById/"+ad.getId()).param("memberId", "1");;
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void clickAd() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        try {
        	
            RequestBuilder request = put("/ad/clickAd/"+ad.getId()).param("memberId", "1").param("num", "aaa");
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
    public void clickAdOver() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(20);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        try {
        	
            RequestBuilder request = put("/ad/clickAd/"+ad.getId()).param("memberId", "1").param("num", "aaa");
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
    public void auditVideo() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_VIDEO.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_AUDIT.val);
        adDOMapper.insertSelective(ad);
        
        String requestListJson = JSONObject.toJSONString(AuditEnum.AD_AUDIT_PASS);
        try {
            RequestBuilder request = put("/ad/auditVideo/"+ad.getId()).contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("auditorId", "1").param("remark", "aaa");
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
    public void selectListByPlatForm() {
    	AdFindParam adPlatParam=new AdFindParam();
        adPlatParam.setCurrentPage(1);
        adPlatParam.setPageSize(20);
        adPlatParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        adPlatParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        adPlatParam.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        String requestListJson = JSONObject.toJSONString(adPlatParam);
        try {
            RequestBuilder request = post("/ad/selectListByPlatForm").contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("memberId", "1");
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
    public void selectListByMember() {
    	AdMemberParam adMemberParam=new AdMemberParam();
        adMemberParam.setCurrentPage(1);
        adMemberParam.setPageSize(20);
        adMemberParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        String requestListJson = JSONObject.toJSONString(adMemberParam);
        try {
            RequestBuilder request = post("/ad/selectListByMember").contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("memberId", "1");
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
    public void selectChoiceness() {
    	AdMemberParam adMemberParam=new AdMemberParam();
        adMemberParam.setCurrentPage(1);
        adMemberParam.setPageSize(20);
        adMemberParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        String requestListJson = JSONObject.toJSONString(adMemberParam);
        try {
            RequestBuilder request = post("/ad/selectChoiceness").contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("memberId", "1");
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
    public void selectPraiseListByMember() {
    	 AdPraiseParam adPraiseParam=new AdPraiseParam();
         adPraiseParam.setCurrentPage(1);
         adPraiseParam.setPageSize(20);
         adPraiseParam.setStatusEnum(AdPraiseStatusEnum.AD_STATUS_TOBEGIN);
        String requestListJson = JSONObject.toJSONString(adPraiseParam);
        try {
            RequestBuilder request = post("/ad/selectPraiseListByMember").contentType(MediaType.APPLICATION_JSON).content(requestListJson).param("memberId", "1");
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
    public void clickPraise() {
    	AdDO ad=new AdDO();
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
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_NO_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        try {
            RequestBuilder request = put("/ad/clickPraise/"+ad.getId()).param("memberId", "1").param("num", "aaa");
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
    public void clickPraiseEnd() {
    	AdDO ad=new AdDO();
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
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        
        try {
            RequestBuilder request = put("/ad/clickPraise/"+ad.getId()).param("memberId", "1").param("num", "aaa");
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
    public void recommendAdByType() {
        AdSolrRealParam param=new AdSolrRealParam();
        param.setMemberId(100L);
        param.setMerchantIds(new ArrayList<>());
        param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        param.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        param.setOrderTypeEnum(OrderTypeEnum.AD_POINT_DESC);
        String requestListJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/ad/recommendAdByType").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void recommendEgain() {
        AdSolrRealParam param=new AdSolrRealParam();
        param.setMemberId(100L);
        param.setMerchantIds(new ArrayList<>());
        param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        param.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        param.setOrderTypeEnum(OrderTypeEnum.AD_POINT_DESC);
        String requestListJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/ad/recommendEgain").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void listAdRank() {
        AdSolrRealParam param=new AdSolrRealParam();
        param.setMemberId(100L);
        param.setMerchantIds(new ArrayList<>());
        param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        param.setStatusEnum(AdStatusEnum.AD_STATUS_ADD);
        param.setOrderTypeEnum(OrderTypeEnum.AD_POINT_DESC);
        String requestListJson = JSONObject.toJSONString(param);
        try {
            RequestBuilder request = post("/ad/listAdRank").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void getRedPacket() {
    	AdDO ad=new AdDO();
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
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setMemberId(1l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
        try {
            RequestBuilder request = put("/ad/getRedPacket").param("merchantId", "1").param("memberId", "1").param("memberNum", "aaa");
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
    public void getAllAd() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_VIDEO.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_AUDIT.val);
        adDOMapper.insertSelective(ad);
        try {
            RequestBuilder request = get("/ad/getAllAd");
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
    public void updateViewCount() {
    	
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        try {
            RequestBuilder request = put("/ad/updateViewCount/"+ad.getId()).param("count", "20");
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
    public void listAllAd() {
    	ListAdParam listAdParam=new ListAdParam();
        listAdParam.setPageSize(10);
        listAdParam.setCurrentPage(1);
        listAdParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        listAdParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        listAdParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        listAdParam.setTitle("广告");
        String requestListJson = JSONObject.toJSONString(listAdParam);
        try {
            RequestBuilder request = post("/ad/listAllAd").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void getAd() {
    	
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        try {
            RequestBuilder request = get("/ad/getAd/"+ad.getId());
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
    public void selectAbPraiseById() {
    	
    	AdDO ad=new AdDO();
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
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        
        PointPoolDO pointPoolDO=new PointPoolDO();
        pointPoolDO.setAdId(ad.getId());
        pointPoolDO.setGmtCreate(new Date());
        pointPoolDO.setGmtModified(new Date());
        pointPoolDO.setMerchantId(1002l);
        pointPoolDO.setOrdinal(0);
        pointPoolDO.setPoint(BigDecimal.valueOf(15));
        pointPoolDO.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
        pointPoolDO.setType(PointPoolTypeEnum.AD_TYPE_PRAISE.val);
        pointPoolDOMapper.insert(pointPoolDO);
		
        try {
            RequestBuilder request = get("/ad/selectAdPraiseById/"+ad.getId()).param("memberId", "1002");
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_ACCEPTED )).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void operatorUpdateAdStatus() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        ad.setClientType(ClientTypeEnum.MOBLIE.getVal());
        adDOMapper.insertSelective(ad);
        try {
            RequestBuilder request = put("/ad/operatorUpdateAdStatus/"+ad.getId()).param("adStatusEnum", "AD_STATUS_OUT");
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
    public void listFlatVideoAd() {
    	ListAdParam listAdParam=new ListAdParam();
        listAdParam.setPageSize(10);
        listAdParam.setCurrentPage(1);
        listAdParam.setPutWayEnum(PutWayEnum.PUT_WAY_AREAS);
        listAdParam.setStatusEnum(AdStatusEnum.AD_STATUS_PUTING);
        listAdParam.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
        listAdParam.setTitle("广告");
        String requestListJson = JSONObject.toJSONString(listAdParam);
        try {
            RequestBuilder request = post("/ad/listFlatVideoAd").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void rebuildAdIndex() {
    	
        try {
            RequestBuilder request = get("/ad/rebuildAdIndex/");
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
    public void delInvalidAdIndex() {
    	
        try {
            RequestBuilder request = get("/ad/delInvalidAdIndex/");
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
    public void getRedPacketInfo() {
    	
        try {
            RequestBuilder request = get("/ad/getRedPacketInfo/"+1l);
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
    public void isExistsRedPacket() {
    	
        try {
            RequestBuilder request = get("/ad/isExistsRedPacket/"+1l);
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
    public void selectDetail() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        try {
            RequestBuilder request = get("/ad/selectDetail/"+ad.getId());
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
    public void isMyData() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        try {
            RequestBuilder request = get("/ad/isMyData/"+ad.getId()).param("merchantId", "1");
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
    public void selectReportAdEarnings() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_ADD.val);
        Integer id=adDOMapper.insertSelective(ad);
        try {
            RequestBuilder request = get("/ad/selectReportAdEarnings/");
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
    public void pageAdEgain() {
    	
        AdEgainInternalParam param =new AdEgainInternalParam();
        
        List<Long> merchantIds=new ArrayList<>();
        merchantIds.add(1002l);
        param.setMerchantIds(merchantIds);
        
        List<String> areas=new ArrayList<>();
        areas.add("11");
        param.setAreas(areas);
        
        param.setLatitude(22.547153);
        param.setLongitude(113.960333);
        
        param.setCurrentPage(1);
        param.setPageSize(20);
        param.setTypeEnum(AdEgainTypeEnum.AD_TYPE_FLAT);
        
        String requestJson = JSONObject.toJSONString(param);
        
        try {
            RequestBuilder request = put("/ad/pageAdEgain/"+1l).contentType(MediaType.APPLICATION_JSON).content(requestJson);
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }*/
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void queryAdByTitle() {
    	
    	AdsolrFindParam param =new AdsolrFindParam();
    	
    	AdSolrParam adParam = new AdSolrParam();
    	adParam.setTitle("广告");
    	param.setAdSolrParam(adParam);
    	param.setMemberId(1l);
        param.setCurrentPage(1);
        param.setPageSize(20);
        
        param.setLongitude(114.026);
        param.setLatitude(22.5461);
        List<Long> merchantIds = new ArrayList<>();
        merchantIds.add(1001l);
        param.setMerchantIds(merchantIds);
        param.setRegionPath("11/1101/110101");
        String requestJson = JSONObject.toJSONString(param);
        
        try {
            RequestBuilder request = post("/ad/queryAdByTitle/").contentType(MediaType.APPLICATION_JSON).content(requestJson);
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
    public void selectById() {

    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
		ad.setHits(0);
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);

        try {
            RequestBuilder request = get("/ad/selectById/"+ad.getId());
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
    public void selectOperatorAdAll() {
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        Integer id=adDOMapper.insertSelective(ad);
        
    	OperatorAdParam operatorAdParam=new OperatorAdParam();
    	operatorAdParam.setAdEgainType(AdEgainTypeEnum.AD_TYPE_FLAT);
        String requestListJson = JSONObject.toJSONString(operatorAdParam);

        try {
            RequestBuilder request = post("/ad/selectOperatorAdAll/").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
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
    public void soldOutAdByMerchantId(){
        try {
            RequestBuilder request = put("/ad/soldOutAdByMerchantId").param("merchantId","1");
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
    public void delAllAdIndex(){
        RequestBuilder request = get("/ad/delAllAdIndex");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void downOperatorById(){
        RequestBuilder request = put("/ad/downOperatorById").param("id","1").param("auditorId","1").param("remark", "aa");
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_CREATED)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_CREATED, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void selectMerchantNumByAdId(){
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTING.val);
        adDOMapper.insertSelective(ad);
        RequestBuilder request = get("/ad/selectMerchantNumByAdId").param("id",ad.getId().toString());
        try {
            ResultActions perform = mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();
            Assert.assertEquals(HttpCode.SC_OK, mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
    
    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getDetailPage(){
    	AdDO ad=new AdDO();
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
		ad.setType(AdTypeEnum.AD_TYPE_FLAT.getVal());
        ad.setGmtCreate(new Date());
        ad.setGmtModified(new Date());
        ad.setHits(0);
        ad.setStatus(AdStatusEnum.AD_STATUS_PUTED.val);
        adDOMapper.insertSelective(ad);
        
        PointGetDetailParam param  = new PointGetDetailParam();
        param.setId(ad.getId());
        param.setCurrentPage(1);
		param.setPageSize(20);
		param.setTypeEnum(AdTypeEnum.AD_TYPE_FLAT);
		String requestListJson = JSONObject.toJSONString(param);
		
        try {
            RequestBuilder request = post("/ad/getDetailPage/").contentType(MediaType.APPLICATION_JSON).content(requestListJson);
            ResultActions perform= mvc.perform(request);
            MvcResult mvcResult = perform.andExpect(status().is(HttpCode.SC_OK)).andDo(MockMvcResultHandlers.print()).andReturn();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }
    
}
