package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
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
import com.lawu.eshop.ad.param.InviterBountyParam;
import com.lawu.eshop.ad.param.InviterBountyQueryParam;
import com.lawu.eshop.ad.srv.AdSrvConfig;
import com.lawu.eshop.ad.srv.bo.GetInviterBountyBO;
import com.lawu.eshop.ad.srv.bo.InviterBountyBO;
import com.lawu.eshop.ad.srv.converter.InviterBountyConverter;
import com.lawu.eshop.ad.srv.domain.InviterBountyDO;
import com.lawu.eshop.ad.srv.domain.InviterBountyDOExample;
import com.lawu.eshop.ad.srv.domain.TakeInviterBountyDetailDO;
import com.lawu.eshop.ad.srv.domain.TakeInviterBountyRecordDO;
import com.lawu.eshop.ad.srv.domain.TakeInviterBountyRecordDOExample;
import com.lawu.eshop.ad.srv.domain.extend.InviterBountyDOView;
import com.lawu.eshop.ad.srv.mapper.InviterBountyDOMapper;
import com.lawu.eshop.ad.srv.mapper.TakeInviterBountyDetailDOMapper;
import com.lawu.eshop.ad.srv.mapper.TakeInviterBountyRecordDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.InviterBountyDOMapperExtend;
import com.lawu.eshop.ad.srv.mapper.extend.TakeInviterBountyRecordDOMapperExtend;
import com.lawu.eshop.ad.srv.service.InviterBountyService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

@Service
public class InviterBountyServiceImpl implements InviterBountyService {
	
	@Autowired
	private InviterBountyDOMapper inviterBountyDOMapper;
	
	@Autowired
	private TakeInviterBountyDetailDOMapper  takeInviterBountyDetailDOMapper;
	
	@Autowired
	@Qualifier("userTakeInviterBountyTransactionMainServiceImpl")
	private TransactionMainService<Reply> transactionMainService;
	
	@Autowired
	private InviterBountyDOMapperExtend inviterBountyDOMapperExtend;
	
	@Autowired
	private AdSrvConfig adSrvConfig;
	
	@Autowired
	private TakeInviterBountyRecordDOMapper takeInviterBountyRecordDOMapper;
	
	@Autowired
	private  TakeInviterBountyRecordDOMapperExtend takeInviterBountyRecordDOMapperExtend;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveInviterBounty(InviterBountyParam param) {
		//设置奖励金时，将已经存在启用的设置为禁用
		InviterBountyDO updateDO = new InviterBountyDO();
		updateDO.setAuditorId(param.getAuditorId());
		updateDO.setStatus(PlatformRedPacketStatusEnum.DISENABLE.val);
		InviterBountyDOExample example = new InviterBountyDOExample();
		example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.ENABLE.val);
		inviterBountyDOMapper.updateByExampleSelective(updateDO, example);
		
		InviterBountyDO record = new InviterBountyDO();
		record.setAuditorId(param.getAuditorId());
		record.setGmtCreate(new Date());
		record.setGmtModified(new Date());
		record.setMoney(param.getMoney());
		record.setSendCount(0);
		record.setStatus(PlatformRedPacketStatusEnum.ENABLE.val);
		inviterBountyDOMapper.insert(record);
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setInviterBounty(Long id , Long auditorId) {
		
		InviterBountyDO record = new InviterBountyDO();
		record.setId(id);
		record.setAuditorId(auditorId);
		record.setGmtModified(new Date());
		record.setStatus(PlatformRedPacketStatusEnum.DISENABLE.val);
		inviterBountyDOMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public Page<InviterBountyBO> queryInviterBounty(InviterBountyQueryParam query) {
		
		InviterBountyDOExample example = new InviterBountyDOExample();
		example.setOrderByClause("gmt_create desc");
		int count = inviterBountyDOMapper.countByExample(example);
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		List<InviterBountyDO> list = inviterBountyDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<InviterBountyBO> bos =InviterBountyConverter.convertBOS(list);
		Page<InviterBountyBO> page = new Page<InviterBountyBO>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(bos);
		return page;
		
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public GetInviterBountyBO getInviterBounty(String userNum,String regNum,UserTypeEnum userType) {
		
		InviterBountyDOExample example = new InviterBountyDOExample();
		example.createCriteria().andStatusEqualTo(PlatformRedPacketStatusEnum.ENABLE.val);
		List<InviterBountyDO> list = inviterBountyDOMapper.selectByExample(example);
		GetInviterBountyBO inviterBountyBO = new GetInviterBountyBO();
		
		if(list.isEmpty()){
			return null;
		}
		
		TakeInviterBountyRecordDOExample tibrExample = new TakeInviterBountyRecordDOExample();
		tibrExample.createCriteria().andTakeDateEqualTo(DateUtil.getNowDate()).andUserNumEqualTo(userNum);
		List<TakeInviterBountyRecordDO> records = takeInviterBountyRecordDOMapper.selectByExample(tibrExample);
		
		TakeInviterBountyRecordDO record = null;
		if(!records.isEmpty()){
			record = records.get(0);
			//当天可领取奖励金总数已满
			if(record.getTakeCount() >= adSrvConfig.getInviterBountyCount()){
				return null;
			}
		}
		
		TakeInviterBountyDetailDO takeInviterBountyDetailDO = new TakeInviterBountyDetailDO();
		takeInviterBountyDetailDO.setGmtCreate(new Date());
		takeInviterBountyDetailDO.setPoint(list.get(0).getMoney());
		takeInviterBountyDetailDO.setRedPacketId(list.get(0).getId());
		takeInviterBountyDetailDO.setUserNum(userNum);
		takeInviterBountyDetailDO.setRegNum(regNum);
		Integer row = takeInviterBountyDetailDOMapper.insert(takeInviterBountyDetailDO);
		
		//更新当天领取总数
		if (records.isEmpty()) {
			TakeInviterBountyRecordDO recordDO = new TakeInviterBountyRecordDO();
			recordDO.setGmtCreate(new Date());
			recordDO.setTakeCount(1);
			recordDO.setTakeDate(new Date());
			recordDO.setUserNum(userNum);
			recordDO.setUserType(userType.getValue());
			takeInviterBountyRecordDOMapper.insertSelective(recordDO);
			
		} else {
			takeInviterBountyRecordDOMapperExtend.updateTakeCountByPrimaryKey(record.getId());
		}
		
		inviterBountyBO.setMoney(list.get(0).getMoney());
		//发送消息
		if(row > 0 && row !=null){
			//更新发送奖励金个数
			takeInviterBountyRecordDOMapperExtend.updateSendCountByPrimaryKey(list.get(0).getId());
			
			transactionMainService.sendNotice(takeInviterBountyDetailDO.getId());
		}
		return inviterBountyBO ;
	}

	@Override
	public BigDecimal queryInviterBountyTotalMoney() {
		InviterBountyDOView view = inviterBountyDOMapperExtend.queryInviterBountyTotalMoney();
		if(view==null){
			return BigDecimal.valueOf(0);
		}
		return view.getTotalMoney();
	}

}
