package com.lawu.eshop.product.srv.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.MemberGradeEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.constant.SeckillActivityProductEnum;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.ModelParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.eshop.product.srv.ProductSrvApplicationTest;
import com.lawu.eshop.product.srv.bo.SeckillActivityDetailBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityJoinBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageDetailBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductDOMapper;
import com.lawu.eshop.product.srv.service.SeckillActivityJoinService;
import com.lawu.framework.core.page.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductSrvApplicationTest.class)
public class SeckillActivityJoinServiceTest {
	
	private Logger log=Logger.getLogger(SeckillActivityJoinServiceTest.class);
	
	@Autowired
	private SeckillActivityJoinService seckillActivityJoinService;
	
	@Autowired
	private SeckillActivityDOMapper seckillActivityDOMapper;
	
	@Autowired
	private SeckillActivityProductDOMapper seckillActivityProductDOMapper;
	
	@Autowired
	private ProductDOMapper productDOMapper;
	 
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void queryPage(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		SeckillActivityJoinParam param = new SeckillActivityJoinParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		Page<SeckillActivityJoinBO> page = seckillActivityJoinService.queryPage(param);
		Assert.assertEquals(page.getRecords().size(), 1);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void queryManagePage(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		SeckillActivityProductDO seckillActivityProductDO = new SeckillActivityProductDO();
		seckillActivityProductDO.setActivityId(record.getId());
		seckillActivityProductDO.setAttentionCount(0);
		seckillActivityProductDO.setGmtCreate(new Date());
		seckillActivityProductDO.setGmtModified(new Date());
		seckillActivityProductDO.setLeftCount(10);
		seckillActivityProductDO.setMerchantId(1000l);
		seckillActivityProductDO.setOriginalPrice(BigDecimal.valueOf(10));
		seckillActivityProductDO.setProductId(1l);
		seckillActivityProductDO.setProductModelCount(10);
		seckillActivityProductDO.setProductName("商品");
		seckillActivityProductDO.setProductPicture("");
		seckillActivityProductDO.setStatus(SeckillActivityProductEnum.UNAUDIT.getValue());
		seckillActivityProductDO.setTurnover(BigDecimal.valueOf(0));
		
		seckillActivityProductDOMapper.insertSelective(seckillActivityProductDO);
		
		SeckillActivityManageParam param = new SeckillActivityManageParam();
		param.setCurrentPage(1);
		param.setPageSize(20);
		param.setMerchantId(seckillActivityProductDO.getMerchantId());
		Page<SeckillActivityManageBO> page = seckillActivityJoinService.queryManagePage(param);
		Assert.assertEquals(page.getRecords().size(), 1);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void querySeckillActivityDetail(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		SeckillActivityProductDO seckillActivityProductDO = new SeckillActivityProductDO();
		seckillActivityProductDO.setActivityId(record.getId());
		seckillActivityProductDO.setAttentionCount(0);
		seckillActivityProductDO.setGmtCreate(new Date());
		seckillActivityProductDO.setGmtModified(new Date());
		seckillActivityProductDO.setLeftCount(10);
		seckillActivityProductDO.setMerchantId(1000l);
		seckillActivityProductDO.setOriginalPrice(BigDecimal.valueOf(10));
		seckillActivityProductDO.setProductId(1l);
		seckillActivityProductDO.setProductModelCount(10);
		seckillActivityProductDO.setProductName("商品");
		seckillActivityProductDO.setProductPicture("");
		seckillActivityProductDO.setStatus(SeckillActivityProductEnum.UNAUDIT.getValue());
		seckillActivityProductDO.setTurnover(BigDecimal.valueOf(0));
		
		seckillActivityProductDOMapper.insertSelective(seckillActivityProductDO);
		
		 SeckillActivityDetailBO  seckillActivityDetailBO = seckillActivityJoinService.querySeckillActivityDetail(record.getId(), seckillActivityProductDO.getMerchantId());
		Assert.assertNotNull(seckillActivityDetailBO);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void querySeckillActivityManageDetail(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		SeckillActivityProductDO seckillActivityProductDO = new SeckillActivityProductDO();
		seckillActivityProductDO.setActivityId(record.getId());
		seckillActivityProductDO.setAttentionCount(0);
		seckillActivityProductDO.setGmtCreate(new Date());
		seckillActivityProductDO.setGmtModified(new Date());
		seckillActivityProductDO.setLeftCount(10);
		seckillActivityProductDO.setMerchantId(1000l);
		seckillActivityProductDO.setOriginalPrice(BigDecimal.valueOf(10));
		seckillActivityProductDO.setProductId(1l);
		seckillActivityProductDO.setProductModelCount(10);
		seckillActivityProductDO.setProductName("商品");
		seckillActivityProductDO.setProductPicture("");
		seckillActivityProductDO.setStatus(SeckillActivityProductEnum.UNAUDIT.getValue());
		seckillActivityProductDO.setTurnover(BigDecimal.valueOf(0));
		
		seckillActivityProductDOMapper.insertSelective(seckillActivityProductDO);
		
		SeckillActivityManageDetailBO seckillActivityManageDetailBO = seckillActivityJoinService.querySeckillActivityManageDetail(record.getId(), seckillActivityProductDO.getMerchantId());
		Assert.assertNotNull(seckillActivityManageDetailBO);
	}
	

	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void joinSeckillActivity(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		ProductDO product = new ProductDO();
		product.setAverageDailySales(BigDecimal.valueOf(10));
		product.setCategoryId(1);
		product.setContent("lll");
		product.setFeatureImage("");
		product.setGmtCreate(new Date());
		product.setGmtDown(new Date());
		product.setGmtModified(new Date());
		product.setImageContent("");
		product.setIsAllowRefund(true);
		product.setKeywords("商品");
		product.setMaxPrice(BigDecimal.valueOf(10));
		product.setMerchantId(1000l);
		product.setMerchantNum("M1000000");
		product.setMinPrice(BigDecimal.valueOf(10));
		product.setName("商品名称");
		product.setStatus(Byte.valueOf("2"));
		product.setTotalInventory(20);
		product.setTotalSalesVolume(30);
		product.setRemark("11111");
		product.setTotalFavorite(0);
		product.setNum("");
		 
		productDOMapper.insertSelective(product);
		
		JoinSeckillActivityParam joinParam = new JoinSeckillActivityParam();
		List<ModelParam> list = new ArrayList<>();
		ModelParam model = new ModelParam();
		model.setCount(10);
		model.setModelId(1l);
		list.add(model);
		joinParam.setModelList(list);
		joinParam.setProductId(product.getId());
		joinParam.setSeckillActivityId(record.getId());
		
		seckillActivityJoinService.joinSeckillActivity(joinParam, 1000l);
		
		List<SeckillActivityProductDO> sapList = seckillActivityProductDOMapper.selectByExample(null);
		Assert.assertEquals(sapList.size(), 1);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
	public void querySeckillActivityInfo(){
		SeckillActivityDO record = new SeckillActivityDO();
		record.setActivityStatus(ActivityStatusEnum.NOT_STARTED.getValue());
		record.setEndDate(new Date());
		record.setGmtCreate(new Date());
		record.setIsRemind(false);
		record.setMemberLevel(MemberGradeEnum.GOLD.getVal());
		record.setName("抢购活动");
		record.setProductValidCount(20);
		record.setSellingPrice(BigDecimal.valueOf(9.9));
		record.setStartDate(new Date());
		record.setStatus(Byte.valueOf("1"));
		seckillActivityDOMapper.insertSelective(record);
		
		SeckillActivityProductDO seckillActivityProductDO = new SeckillActivityProductDO();
		seckillActivityProductDO.setActivityId(record.getId());
		seckillActivityProductDO.setAttentionCount(0);
		seckillActivityProductDO.setGmtCreate(new Date());
		seckillActivityProductDO.setGmtModified(new Date());
		seckillActivityProductDO.setLeftCount(10);
		seckillActivityProductDO.setMerchantId(1000l);
		seckillActivityProductDO.setOriginalPrice(BigDecimal.valueOf(10));
		seckillActivityProductDO.setProductId(1l);
		seckillActivityProductDO.setProductModelCount(10);
		seckillActivityProductDO.setProductName("商品");
		seckillActivityProductDO.setProductPicture("");
		seckillActivityProductDO.setStatus(SeckillActivityProductEnum.UNAUDIT.getValue());
		seckillActivityProductDO.setTurnover(BigDecimal.valueOf(0));
		
		seckillActivityProductDOMapper.insertSelective(seckillActivityProductDO);
		
		SeckillActivityInfoBO seckillActivityInfoBO = seckillActivityJoinService.querySeckillActivityInfo(record.getId(),seckillActivityProductDO.getMerchantId(),seckillActivityProductDO.getProductId());
		Assert.assertNotNull(seckillActivityInfoBO); 
	}
	
}
