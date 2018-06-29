package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.param.RichPowerRecordParam;
import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;
import com.lawu.eshop.activity.srv.converter.RichPowerRecordConvert;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.RichPowerRecordDOMapper;
import com.lawu.eshop.activity.srv.servcie.RichPowerRecordService;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@Service
public class RichPowerRecordServiceImpl implements RichPowerRecordService {
	
	@Autowired
	private RichPowerRecordDOMapper richPowerRecordDOMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRichPowerRecord(RichPowerRecordParam param) {
		RichPowerRecordDO record = new RichPowerRecordDO();
		record.setDirection(param.getDirectionEnum().getVal());
		record.setGmtCreate(new Date());
		record.setPower(param.getPower());
		if(param.getUserType() == UserTypeEnum.MEMBER){
			record.setSource(param.getTypeEnum().getVal());
			record.setTitle(param.getTypeEnum().getName());
		}else{
			record.setSource(param.getMerchantTypeEnum().getVal());
			record.setTitle(param.getMerchantTypeEnum().getName());
		}
		
		record.setUserNum(param.getMemberNum());
		richPowerRecordDOMapper.insert(record);
	}
	
	@Override
	public List<RichPowerRecordBO> getRichPowerInfoRecord(String userNum) {
		RichPowerRecordDOExample example = new RichPowerRecordDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
		example.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(0, 10);
		List<RichPowerRecordDO> list = richPowerRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return RichPowerRecordConvert.convertRichPowerRecordBO(list);
	} 

}
