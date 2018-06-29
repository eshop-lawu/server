package com.lawu.eshop.user.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.user.param.AbnormalJobParam;
import com.lawu.eshop.user.param.SameIpCountParam;
import com.lawu.eshop.user.srv.bo.AbnormalAccountBO;
import com.lawu.eshop.user.srv.bo.MemberProfileBO;
import com.lawu.eshop.user.srv.converter.MemberProfileConverter;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberDOExample;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.eshop.user.srv.domain.extend.AbnormalAccountDOView;
import com.lawu.eshop.user.srv.mapper.MemberDOMapper;
import com.lawu.eshop.user.srv.mapper.MemberProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.MemberDOMapperExtend;
import com.lawu.eshop.user.srv.service.MemberProfileService;

@Service
public class MemberProfileServiceImpl implements MemberProfileService {
	
	@Autowired
	private MemberProfileDOMapper memberProfileDOMapper;

	@Autowired
	private MemberDOMapperExtend memberDOMapperExtend;

	@Autowired
	private MemberDOMapper memberDOMapper;

	@Override
	public Integer getMemberCount(Long id) {
		MemberProfileDO memberProfile = memberProfileDOMapper.selectByPrimaryKey(id);
		if (memberProfile == null) {
			return 0;
		}else{
			return memberProfile.getInviteMemberCount();
		}
		
	}

	@Override
	public Integer getMerchantCount(Long id) {
		MemberProfileDO memberProfile = memberProfileDOMapper.selectByPrimaryKey(id);
		if (memberProfile == null) {
			return 0;
		}else{
			return memberProfile.getInviteMerchantCount();
		}
	}

	/**
	 * 根据会员id 查询会员扩展信息
	 * @param id
	 * @return
	 */
	@Override
	public MemberProfileBO get(Long id) {
		MemberProfileDO memberProfileDO = memberProfileDOMapper.selectByPrimaryKey(id);
		MemberProfileBO memberProfileBO = MemberProfileConverter.convert(memberProfileDO);
		return memberProfileBO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateLastLoginTime(Long id) {
		MemberProfileDO profileDO = new MemberProfileDO();
		profileDO.setId(id);
		profileDO.setGmtLastLogin(new Date());
		memberProfileDOMapper.updateByPrimaryKeySelective(profileDO);
	}

	@Override
	public List<AbnormalAccountBO> abnormalMemberList(AbnormalJobParam param) {
		List<AbnormalAccountDOView> list = memberDOMapperExtend.abnormalMemberList(param);
		return MemberProfileConverter.convertAbnormalBOS(list);
	}

	@Override
	public List<Integer> memberSameIpCount(Long id,Byte type) {
		SameIpCountParam param = new SameIpCountParam();
		param.setId(id);
		param.setType(type);
		return memberDOMapperExtend.memberSameIpCount(param);
	}

	@Override
	public int inviteMemberTotalCount(Long id, Byte type) {
		SameIpCountParam param = new SameIpCountParam();
		param.setId(id);
		param.setType(type);
		return memberDOMapperExtend.inviteMemberTotalCount(param);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateHelpRichTask(String userNum) {
		MemberDOExample example = new MemberDOExample();
		example.createCriteria().andNumEqualTo(userNum);
		List<MemberDO> memberDOS = memberDOMapper.selectByExample(example);
		if (memberDOS.isEmpty()) {
			return;
		}

		MemberProfileDO profileDO = new MemberProfileDO();
		profileDO.setId(memberDOS.get(0).getId());
		profileDO.setIsHelpRichTask(true);
		profileDO.setGmtModified(new Date());
		memberProfileDOMapper.updateByPrimaryKeySelective(profileDO);
	}

}
