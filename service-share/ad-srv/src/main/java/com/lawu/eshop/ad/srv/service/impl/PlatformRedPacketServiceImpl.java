package com.lawu.eshop.ad.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.ad.constants.PlatformRedPacketStatusEnum;
import com.lawu.eshop.ad.param.PlatformRedPacketParam;
import com.lawu.eshop.ad.param.PlatformRedPacketQueryParam;
import com.lawu.eshop.ad.srv.bo.GetPlatformRedPacketBO;
import com.lawu.eshop.ad.srv.bo.PlatformRedPacketBO;
import com.lawu.eshop.ad.srv.converter.PlatformRedPacketConverter;
import com.lawu.eshop.ad.srv.domain.PlatformRedPacketDO;
import com.lawu.eshop.ad.srv.domain.PlatformRedPacketDOExample;
import com.lawu.eshop.ad.srv.domain.TakePlatformRedPacketDO;
import com.lawu.eshop.ad.srv.domain.TakePlatformRedPacketDOExample;
import com.lawu.eshop.ad.srv.mapper.PlatformRedPacketDOMapper;
import com.lawu.eshop.ad.srv.mapper.TakePlatformRedPacketDOMapper;
import com.lawu.eshop.ad.srv.service.PlatformRedPacketService;
import com.lawu.framework.core.page.Page;

@Service
public class PlatformRedPacketServiceImpl implements PlatformRedPacketService {
	
	@Autowired
	private PlatformRedPacketDOMapper platformRedPacketDOMapper;
	
	@Autowired
	private TakePlatformRedPacketDOMapper takePlatformRedPacketDOMapper;
	
	@Autowired
	@Qualifier("userTakePlatRedTransactionMainServiceImpl")
	private TransactionMainService<Reply> transactionMainService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveRedPacket(PlatformRedPacketParam param) {
		//设置红包时，将已经存在启用的设置为禁用
		PlatformRedPacketDO updateDO = new PlatformRedPacketDO();
		updateDO.setAuditorId(param.getAuditorId());
		updateDO.setStatus(PlatformRedPacketStatusEnum.DISENABLE.val);
		PlatformRedPacketDOExample example = new PlatformRedPacketDOExample();
		example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.ENABLE.val);
		platformRedPacketDOMapper.updateByExampleSelective(updateDO, example);
		
		PlatformRedPacketDO record = new PlatformRedPacketDO();
		record.setAuditorId(param.getAuditorId());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setMoney(param.getMoney());
		record.setSendCount(0);
		record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
		platformRedPacketDOMapper.insert(record);
		
	}

	@Override
	public void setRedPacket(Long id , Long auditorId) {
		
		PlatformRedPacketDO record = new PlatformRedPacketDO();
		record.setId(id);
		record.setAuditorId(auditorId);
		record.setGmtModified(new Date());
		record.setStatus(PlatformRedPacketStatusEnum.DISENABLE.val);
		platformRedPacketDOMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public Page<PlatformRedPacketBO> queryRedPacket(PlatformRedPacketQueryParam query) {
		
		PlatformRedPacketDOExample example = new PlatformRedPacketDOExample();
		example.setOrderByClause("gmt_create desc");
		Long count = platformRedPacketDOMapper.countByExample(example);
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		List<PlatformRedPacketDO> list = platformRedPacketDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<PlatformRedPacketBO> bos =PlatformRedPacketConverter.convertBOS(list);
		Page<PlatformRedPacketBO> page = new Page<PlatformRedPacketBO>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count.intValue());
		page.setRecords(bos);
		return page;
		
	}

	@Override
	public GetPlatformRedPacketBO getRedPacket(String userNum) {
		//领取红包之后判断是否已经领取
		GetPlatformRedPacketBO getRedPacketBO = new GetPlatformRedPacketBO();
		TakePlatformRedPacketDOExample tpExample = new TakePlatformRedPacketDOExample();
		tpExample.createCriteria().andUserNumEqualTo(userNum);
		
		Long count= takePlatformRedPacketDOMapper.countByExample(tpExample);
		
		if(count>0){
			getRedPacketBO.setGet(true);
			return getRedPacketBO;
		}
		
		PlatformRedPacketDOExample example = new PlatformRedPacketDOExample();
		example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.ENABLE.val);
		List<PlatformRedPacketDO> list = platformRedPacketDOMapper.selectByExample(example);
		if(!list.isEmpty()){
			TakePlatformRedPacketDO takePlatformRedPacketDO = new TakePlatformRedPacketDO();
			takePlatformRedPacketDO.setGmtCreate(new Date());
			takePlatformRedPacketDO.setPoint(list.get(0).getMoney());
			takePlatformRedPacketDO.setRedPacketId(list.get(0).getId());
			takePlatformRedPacketDO.setUserNum(userNum);
			Integer row = takePlatformRedPacketDOMapper.insert(takePlatformRedPacketDO);
			//发送消息
			if(row > 0 && row !=null){
				//更新发送红包个数
				PlatformRedPacketDO platformRedPacketDO = new PlatformRedPacketDO();
				platformRedPacketDO.setId(list.get(0).getId());
				platformRedPacketDO.setSendCount(list.get(0).getSendCount()+1);
				platformRedPacketDOMapper.updateByPrimaryKeySelective(platformRedPacketDO);
				getRedPacketBO.setId(takePlatformRedPacketDO.getId());
				getRedPacketBO.setGet(false);
				getRedPacketBO.setMoney(list.get(0).getMoney());
				
				transactionMainService.sendNotice(takePlatformRedPacketDO.getId());
			}
		}
		
		return getRedPacketBO;
	}

}
