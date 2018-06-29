package com.lawu.eshop.user.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.user.srv.domain.FansInviteResultDO;
import com.lawu.eshop.user.srv.domain.FansInviteResultDOExample;
import com.lawu.eshop.user.srv.domain.FansInviteResultDOExample.Criteria;
import com.lawu.eshop.user.srv.mapper.FansInviteResultDOMapper;
import com.lawu.eshop.user.srv.service.FansInviteResultService;
@Service
public class FansInviteResultServiceImpl implements FansInviteResultService{

	@Autowired
	private FansInviteResultDOMapper fansInviteResultDOMapper;
	
	/**
	 * 用户获取消息是否被处理过的状态
	 */
	@Override
	public int selectInviteResultById(Long id) {
		FansInviteResultDOExample fansInviteResultDOExample = new FansInviteResultDOExample();
		Criteria c = fansInviteResultDOExample.createCriteria();
		c.andMessageIdEqualTo(id);
		List<FansInviteResultDO> list = fansInviteResultDOMapper.selectByExample(fansInviteResultDOExample);
		if(list != null && !list.isEmpty()) {
			return list.get(0).getStatus();
		}
		//未处理是2
		return 2;
	}

}
