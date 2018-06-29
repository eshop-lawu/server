package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.srv.bo.RichDiamondInfoBO;
import com.lawu.eshop.activity.srv.converter.RichDiamondInfoConvert;
import com.lawu.eshop.activity.srv.domain.RichDiamondInfoDO;
import com.lawu.eshop.activity.srv.domain.RichDiamondInfoDOExample;
import com.lawu.eshop.activity.srv.mapper.RichDiamondInfoDOMapper;
import com.lawu.eshop.activity.srv.servcie.RichDiamondInfoService;

/** 
 * 
 * @author lihj
 * @date 2018年5月3日
 */
@Service
public class RichDiamondInfoServiceImpl implements RichDiamondInfoService {

	@Autowired
	private RichDiamondInfoDOMapper richDiamondInfoDOMapper;

	@Override
	public RichDiamondInfoBO getRichDiamondInfo() {
		RichDiamondInfoDOExample example = new RichDiamondInfoDOExample();
		example.createCriteria();
		List<RichDiamondInfoDO> list =richDiamondInfoDOMapper.selectByExample(example);
		return RichDiamondInfoConvert.convertRichDiamondInfoBO(list);
	}
	
}
