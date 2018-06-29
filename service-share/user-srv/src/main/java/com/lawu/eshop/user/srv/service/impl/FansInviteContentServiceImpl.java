package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.mq.dto.user.reply.InviteFansReply;
import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.eshop.user.param.UserFansParam;
import com.lawu.eshop.user.srv.bo.FansInviteContentBO;
import com.lawu.eshop.user.srv.converter.FansInviteContentConverter;
import com.lawu.eshop.user.srv.domain.FansInviteContentDO;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.extend.FansInviteContentDOView;
import com.lawu.eshop.user.srv.mapper.FansInviteContentDOMapper;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.FansInviteContentDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.FansMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.FansInviteContentService;

@Service
public class FansInviteContentServiceImpl implements FansInviteContentService{

	private static Map<String, Object> inviteFansMap = new HashMap<>();

	@Autowired
	private FansInviteContentDOMapper fansInviteContentDOMapper;
	
	@Autowired
	private FansMerchantDOMapper fansMerchantDOMapper;
	
	@Autowired
	private MemberDOMapper memberDOMapper;

	@Autowired
	private FansInviteContentDOMapperExtend fansInviteContentDOMapperExtend;

	@Autowired
	private FansMerchantDOMapperExtend fansMerchantDOMapperExtend;

	@Autowired
	@Qualifier("merchantFansTransactionMainServiceImpl")
	private TransactionMainService merchantFansTransactionMainServiceImpl;

	@Autowired
	@Qualifier("inviteFansTransactionMainServiceImpl")
	private TransactionMainService<InviteFansReply> inviteFansTransactionMainServiceImpl;
	
	@Override
	public FansInviteContentBO selectInviteContentById(Long id) {
		FansInviteContentDO ficDo = fansInviteContentDOMapper.selectByPrimaryKey(id);
		FansInviteContentBO result = FansInviteContentConverter.converterDoToBo(ficDo);
		return result;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void dealOverdueFansInvite(Integer pageSize) {
		UserFansParam param = new UserFansParam();
		param.setGmtCreate(new Date());
		param.setCurrentPage(1);
		param.setPageSize(pageSize);

		while (true){
			List<FansInviteContentDOView> viewList = fansInviteContentDOMapperExtend.listOverdueFansInvite(param);
			if (viewList == null || viewList.isEmpty()) {
				return;
			}
			FansInviteContentDO inviteContentDO = new FansInviteContentDO();
			inviteContentDO.setIsOverdue(true);
			inviteContentDO.setGmtModified(new Date());
			for (FansInviteContentDOView view : viewList) {
				param.setMerchantId(view.getMerchantId());
				int count = fansMerchantDOMapperExtend.countOverdueFans(param);
				fansMerchantDOMapperExtend.delOverdueFans(param);

				inviteContentDO.setId(view.getId());
				inviteContentDO.setRefuseNumber(count);
				fansInviteContentDOMapper.updateByPrimaryKeySelective(inviteContentDO);

				if (count > 0) {
					merchantFansTransactionMainServiceImpl.sendNotice(view.getId());
				}
			}
		}
	}


	@Transactional(rollbackFor = Exception.class)
	@Override
	public Long saveInviteContentService(FansInviteContentExtendParam inviteContentParam) {
		String[] num = inviteContentParam.getNums().split(",");
		Date date = new Date();
		for(String s : num) {
			MemberDOExample memberDOExample = new MemberDOExample();
			memberDOExample.createCriteria().andNumEqualTo(s);
			List<MemberDO> memberDO = memberDOMapper.selectByExample(memberDOExample);
			FansMerchantDO fansMerchantDO = new FansMerchantDO();
			fansMerchantDO.setMemberId(memberDO.get(0).getId());
			fansMerchantDO.setMerchantId(inviteContentParam.getMerchantId());
			fansMerchantDO.setChannel((byte)2);
			fansMerchantDO.setGmtCreate(date);
			fansMerchantDO.setStatus((byte)0);
			fansMerchantDOMapper.insert(fansMerchantDO);
		}
		FansInviteContentDO fansInviteContentDO = FansInviteContentConverter.converterFansInviteContentParam(inviteContentParam);
		fansInviteContentDO.setGmtCreate(date);
		fansInviteContentDO.setGmtModified(date);
		fansInviteContentDOMapper.insertSelective(fansInviteContentDO);
		return fansInviteContentDO.getId();
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Long saveInviteContentExtendService(FansInviteContentExtendParam inviteContentParam) {
		String[] idArr = inviteContentParam.getIds().split(",");
		Date date = new Date();
		FansMerchantDO fansMerchantDO;
		List<FansMerchantDO> fansMerchantDOS = new ArrayList<>();
		for (String id : idArr) {
			fansMerchantDO = new FansMerchantDO();
			fansMerchantDO.setMemberId(Long.valueOf(id));
			fansMerchantDO.setMerchantId(inviteContentParam.getMerchantId());
			fansMerchantDO.setChannel((byte) 2);
			fansMerchantDO.setStatus((byte) 0);
			fansMerchantDO.setGmtCreate(date);
			fansMerchantDOS.add(fansMerchantDO);
		}
		fansMerchantDOMapperExtend.batchInsertFansMerchant(fansMerchantDOS);

		FansInviteContentDO fansInviteContentDO = FansInviteContentConverter.converterFansInviteContentParam(inviteContentParam);
		fansInviteContentDO.setGmtCreate(date);
		fansInviteContentDO.setGmtModified(date);
		fansInviteContentDOMapper.insertSelective(fansInviteContentDO);
		Long fansInviteContentDOId = fansInviteContentDO.getId();

		inviteFansMap.put("regionName", inviteContentParam.getRegionName());
		inviteFansMap.put("inviteFansCount", inviteContentParam.getInviteFansCount());
		inviteFansMap.put("sex", inviteContentParam.getSex());
		inviteFansMap.put("age", inviteContentParam.getAge());
		inviteFansTransactionMainServiceImpl.sendNotice(fansInviteContentDOId);
		return fansInviteContentDOId;
	}

	@Override
	public Map<String, Object> getInviteFansMap() {
		return inviteFansMap;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateFansInviteDetailId(Long id, Long fansInviteDetailId) {
		FansInviteContentDO fansInviteContentDO = new FansInviteContentDO();
		fansInviteContentDO.setId(id);
		fansInviteContentDO.setFansInviteDetailId(fansInviteDetailId);
		fansInviteContentDOMapper.updateByPrimaryKeySelective(fansInviteContentDO);
	}

}
