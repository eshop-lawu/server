package com.lawu.eshop.product.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.constants.StatusEnum;
import com.lawu.eshop.product.constant.ActivityStatusEnum;
import com.lawu.eshop.product.constant.SeckillActivityProductEnum;
import com.lawu.eshop.product.param.JoinSeckillActivityParam;
import com.lawu.eshop.product.param.ModelParam;
import com.lawu.eshop.product.param.SeckillActivityJoinParam;
import com.lawu.eshop.product.param.SeckillActivityManageParam;
import com.lawu.eshop.product.srv.bo.SeckillActivityDetailBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityInfoBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityJoinBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityManageDetailBO;
import com.lawu.eshop.product.srv.converter.SeckillActivityJoinConverter;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductDOExample;
import com.lawu.eshop.product.srv.domain.ProductModelDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDOExample;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductModelDO;
import com.lawu.eshop.product.srv.domain.extend.SeckillActivityDOView;
import com.lawu.eshop.product.srv.mapper.ProductDOMapper;
import com.lawu.eshop.product.srv.mapper.ProductModelDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductDOMapper;
import com.lawu.eshop.product.srv.mapper.SeckillActivityProductModelDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.SeckillActivityDOMapperExtend;
import com.lawu.eshop.product.srv.service.SeckillActivityJoinService;
import com.lawu.framework.core.page.Page;

@Service
public class SeckillActivityJoinServiceImpl implements SeckillActivityJoinService {
	
	@Autowired
	private SeckillActivityDOMapper seckillActivityDOMapper;
	
	@Autowired
	private SeckillActivityDOMapperExtend seckillActivityDOMapperExtend;
	
	@Autowired
	private SeckillActivityProductDOMapper seckillActivityProductDOMapper;
	
	@Autowired
	private ProductDOMapper productDOMapper;
	
	@Autowired
	private ProductModelDOMapper productModelDOMapper;
	
	@Autowired
	private SeckillActivityProductModelDOMapper seckillActivityProductModelDOMapper;

	@Override
	public Page<SeckillActivityJoinBO> queryPage(SeckillActivityJoinParam param) {
		
		SeckillActivityDOExample example = new SeckillActivityDOExample();
		example.createCriteria().andActivityStatusEqualTo(ActivityStatusEnum.PUBLISHED.getValue()).andStatusEqualTo(StatusEnum.VALID.getValue());
		example.setOrderByClause("start_date asc");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<SeckillActivityDO> list = seckillActivityDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		Page<SeckillActivityJoinBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		long count = seckillActivityDOMapper.countByExample(example);
		page.setTotalCount((int) count);
		page.setRecords(SeckillActivityJoinConverter.seckillActivityJoinBOConverter(list));
		
		return page;
	}

	@Override
	public Page<SeckillActivityManageBO> queryManagePage(SeckillActivityManageParam param) {
		
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<SeckillActivityDOView> list = seckillActivityDOMapperExtend.queryManagePage(param.getMerchantId(), rowBounds);
		Page<SeckillActivityManageBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		int count = seckillActivityDOMapperExtend.countManage(param.getMerchantId());
		page.setTotalCount(count);
		page.setRecords(SeckillActivityJoinConverter.seckillActivityJoinManageBOConverter(list));
		
		return page;
	}

	@Override
	public SeckillActivityDetailBO querySeckillActivityDetail(Long id,Long merchantId) {
		
		SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
		SeckillActivityDetailBO seckillActivityDetailBO = SeckillActivityJoinConverter.seckillActivityJoinDetailBOConverter(seckillActivityDO);
		SeckillActivityProductDOExample example = new SeckillActivityProductDOExample();
		example.createCriteria().andActivityIdEqualTo(id);
		Long joinCount = seckillActivityProductDOMapper.countByExample(example);
		seckillActivityDetailBO.setJoinCount(joinCount.intValue());
		
		return seckillActivityDetailBO;
	}

	@Override
	public SeckillActivityManageDetailBO querySeckillActivityManageDetail(Long id, Long merchantId) {
		SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
		BigDecimal money = new BigDecimal(0);
		if(seckillActivityDO != null){
			money = seckillActivityDO.getSellingPrice();
		}
		SeckillActivityProductDOExample example = new SeckillActivityProductDOExample();
		example.createCriteria().andActivityIdEqualTo(id).andMerchantIdEqualTo(merchantId);
		List<SeckillActivityProductDO> list = seckillActivityProductDOMapper.selectByExample(example);
		SeckillActivityManageDetailBO seckillActivityManageDetailBO = SeckillActivityJoinConverter.seckillActivityManageDetailBOConverter(seckillActivityDO);
		seckillActivityManageDetailBO.setList(SeckillActivityJoinConverter.seckillActivityProductManageBOConverter(list,money));
		return seckillActivityManageDetailBO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void joinSeckillActivity(JoinSeckillActivityParam joinParam, Long merchantId) {
		
		List<ModelParam> list = joinParam.getModelList();
		int moelCount=0;
		for (ModelParam modelParam : list) {
			int count = modelParam.getCount();
			//当前型号是否为该商品
			ProductModelDOExample pmExample = new ProductModelDOExample();
			pmExample.createCriteria().andProductIdEqualTo(joinParam.getProductId()).andIdEqualTo(modelParam.getModelId());
			int modelCount = new Long(productModelDOMapper.countByExample(pmExample)).intValue();
			if(count > 0 && modelCount > 0){
				moelCount +=count;
			}
			
		}
		
		//总型号为0直接返回
		if(moelCount == 0){
			return ;
		}
		
		ProductDO productDO = productDOMapper.selectByPrimaryKey(joinParam.getProductId());
		
		//保存商品参加记录
		SeckillActivityProductDO seckillActivityProductDO = new SeckillActivityProductDO();
		seckillActivityProductDO.setActivityId(joinParam.getSeckillActivityId());
		seckillActivityProductDO.setAttentionCount(0);
		seckillActivityProductDO.setGmtCreate(new Date());
		seckillActivityProductDO.setGmtModified(new Date());
		seckillActivityProductDO.setLeftCount(moelCount);
		seckillActivityProductDO.setMerchantId(merchantId);
		seckillActivityProductDO.setOriginalPrice(productDO.getMinPrice());
		seckillActivityProductDO.setProductId(joinParam.getProductId());
		seckillActivityProductDO.setProductModelCount(moelCount);
		seckillActivityProductDO.setProductName(productDO.getName());
		seckillActivityProductDO.setProductPicture(productDO.getFeatureImage());
		seckillActivityProductDO.setStatus(SeckillActivityProductEnum.UNAUDIT.getValue());
		seckillActivityProductDO.setTurnover(BigDecimal.valueOf(0));
		if (joinParam.getSaleMoney() != null) {
			seckillActivityProductDO.setSaleMoney(joinParam.getSaleMoney());
		}
		
		seckillActivityProductDOMapper.insertSelective(seckillActivityProductDO);
		
		for (ModelParam modelParam : list) {
			//当前型号是否为该商品
			ProductModelDOExample pmExample = new ProductModelDOExample();
			pmExample.createCriteria().andProductIdEqualTo(joinParam.getProductId()).andIdEqualTo(modelParam.getModelId());
			int modelCount = new Long(productModelDOMapper.countByExample(pmExample)).intValue();
			
			if(modelParam.getCount()>0 && modelCount > 0){
				
				SeckillActivityProductModelDO seckillActivityProductModelDO = new SeckillActivityProductModelDO();
				seckillActivityProductModelDO.setActivityProductId(seckillActivityProductDO.getId());
				seckillActivityProductModelDO.setCount(modelParam.getCount());
				seckillActivityProductModelDO.setGmtCreate(new Date());
				seckillActivityProductModelDO.setLeftCount(modelParam.getCount());
				seckillActivityProductModelDO.setProductModelId(modelParam.getModelId());
				seckillActivityProductModelDO.setGmtModified(new Date());
				seckillActivityProductModelDOMapper.insertSelective(seckillActivityProductModelDO);
			}
		}
	}

	@Override
	public SeckillActivityInfoBO querySeckillActivityInfo(Long id,Long merchantId,Long productId) {
		SeckillActivityInfoBO info = new SeckillActivityInfoBO();
		
		SeckillActivityProductDOExample sexample = new SeckillActivityProductDOExample();
		sexample.createCriteria().andActivityIdEqualTo(id).andProductIdEqualTo(productId);
		Long thisJoinCount = seckillActivityProductDOMapper.countByExample(sexample);
		
		if (thisJoinCount != null && thisJoinCount.intValue() > 0) {
			info.setIsExists(true);
			return info;
		} else {
			info.setIsExists(false);
		}
		
		
		ProductDOExample pexample = new ProductDOExample();
		pexample.createCriteria().andMerchantIdEqualTo(merchantId).andIdEqualTo(productId);
		
		Long count = productDOMapper.countByExample(pexample);
		
		if (count != null && count > 0) {
			info.setIsCheckProduct(true);
		} else {
			info.setIsCheckProduct(false);
			return info;
		}
		
		SeckillActivityProductDOExample example = new SeckillActivityProductDOExample();
		example.createCriteria().andActivityIdEqualTo(id);
		Long commitCount = seckillActivityProductDOMapper.countByExample(example);
		
		
		SeckillActivityDO seckillActivityDO = seckillActivityDOMapper.selectByPrimaryKey(id);
		
		
		if (seckillActivityDO.getActivityStatus() != ActivityStatusEnum.PUBLISHED.getValue()) {
			info.setIsJoin(false);
			return info;
		} else {
			info.setIsJoin(true);
		}
		
		if(commitCount!=null && seckillActivityDO.getProductValidCount()<=commitCount.intValue()){
			info.setIsOverCount(true);
			return info;
		}else{
			info.setIsOverCount(false);
		}
		
		return info;
	}

	@Override
	public Boolean isJoinActivity(Long productId) {
		
		int count = seckillActivityDOMapperExtend.selectJoinStatus(productId);
		
		return count > 0;
	}

	

}
