package com.lawu.eshop.property.srv.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.property.srv.bo.BankBO;
import com.lawu.eshop.property.srv.converter.BankConverter;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.domain.BankDOExample;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.service.BankService;

/**
 * 银行接口实现
 * @author zhangrc
 * @date 2017/03/29
 *
 */
@Service
public class BankServiceImpl implements BankService {
	
	@Autowired
	private BankDOMapper bankDOMapper;

	@Override
	public List<BankBO> findBank() {
		BankDOExample example = new BankDOExample();
		example.setOrderByClause("ordinal asc");
		example.createCriteria().andStatusEqualTo(new Byte("1"));
		List<BankDO> list=bankDOMapper.selectByExample(example);
		List<BankBO> BOS=BankConverter.convertBO(list);
		return BOS;
	}

}
