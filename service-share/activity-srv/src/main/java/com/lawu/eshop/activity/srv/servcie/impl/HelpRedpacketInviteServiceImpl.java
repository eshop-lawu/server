package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.HelpTypeEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketInviteAbnormalDTO;
import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.param.DoHelpDataParam;
import com.lawu.eshop.activity.param.HelpRedpacketInviteRegParam;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.HelpRedpacketInviteAbnormalDOView;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketInviteRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.HelpRedpacketAttendDetailDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketAttendDetailService;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketInviteService;
import com.lawu.eshop.framework.web.impl.ResultCode;

/**
 * 红包助力
 * @author yangqh
 * @date 2017年12月28日
 */
@Service
public class HelpRedpacketInviteServiceImpl implements HelpRedpacketInviteService {

	private static Logger logger = LoggerFactory.getLogger(HelpRedpacketInviteServiceImpl.class);

	@Autowired
	private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;
	
    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
    
	@Autowired
	private HelpRedpacketInviteRecordDOMapper helpRedpacketInviteRecordDOMapper;

	@Autowired
	private HelpRedpacketAttendDetailDOMapperExtend helpRedpacketAttendDetailDOMapperExtend;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int doLoginHelp(DoHelpDataParam param) {

		HelpRedpacketAttendDetailBO helpRedpacketAttendDetailBO = helpRedpacketAttendDetailService.selectByPrimaryKey(param.getAttendId());

		if(helpRedpacketAttendDetailBO.getUserNum().equals(param.getUserNum())){
			logger.error(ResultCode.get(ResultCode.HELP_SELF_ERROR));
			return ResultCode.HELP_SELF_ERROR;
		}
		//一个活动一个用户只能助力一次
		HelpRedpacketInviteRecordDOExample example = new HelpRedpacketInviteRecordDOExample();
		example.createCriteria().andHelpUserNumEqualTo(param.getUserNum()).andActivityIdEqualTo(helpRedpacketAttendDetailBO.getActivityId());
		List<HelpRedpacketInviteRecordDO> doList = helpRedpacketInviteRecordDOMapper.selectByExample(example);
		if(doList != null && !doList.isEmpty()){
			logger.error(ResultCode.get(ResultCode.HELP_ONLY_ONE_TIME));
			return ResultCode.HELP_ONLY_ONE_TIME;
		}

		HelpRedpacketInviteRecordDO helpRedpacketInviteRecordDO = new HelpRedpacketInviteRecordDO();
		helpRedpacketInviteRecordDO.setAttendId(param.getAttendId());
		helpRedpacketInviteRecordDO.setActivityId(helpRedpacketAttendDetailBO.getActivityId());
		helpRedpacketInviteRecordDO.setAttendUserNum(helpRedpacketAttendDetailBO.getUserNum());
		helpRedpacketInviteRecordDO.setGmtCreate(new Date());
		helpRedpacketInviteRecordDO.setHelpType(HelpTypeEnum.LOGIN.getVal());
		helpRedpacketInviteRecordDO.setHelpUserAccount(param.getAccout());
		helpRedpacketInviteRecordDO.setHelpUserHeadimg(param.getHeadimg());
		helpRedpacketInviteRecordDO.setHelpUserNum(param.getUserNum());
		helpRedpacketInviteRecordDOMapper.insertSelective(helpRedpacketInviteRecordDO);

		//助力人数超过10人后不累加倍数
		if(helpRedpacketAttendDetailBO.getHelpCount().intValue() < PropertyConstant.HELP_REDPACKET_COUNT){
			helpRedpacketAttendDetailService.addHelpCount(param.getAttendId());
		}

		return ResultCode.SUCCESS;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void doRegHelp(HelpRedpacketInviteRegParam param) {
		if(StringUtils.isBlank(param.getRegOrigin())){
			param.setRegOrigin(String.valueOf(PropertyConstant.HELP_REDPACKET_ACTIVITY_ID));
		}
		HelpRedpacketInviteRecordDOExample example = new HelpRedpacketInviteRecordDOExample();
		example.createCriteria().andHelpUserNumEqualTo(param.getHelpUserNum()).andActivityIdEqualTo(Integer.valueOf(param.getRegOrigin()));
		List<HelpRedpacketInviteRecordDO> doList = helpRedpacketInviteRecordDOMapper.selectByExample(example);
		if(doList != null && !doList.isEmpty()){
			logger.error(ResultCode.get(ResultCode.HELP_ONLY_ONE_TIME));
			return;
		}
		HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(param.getAttendId());
		if (helpRedpacketAttendDetailDO == null) {
		    return;
		}
		HelpRedpacketInviteRecordDO helpRedpacketInviteRecordDO = new HelpRedpacketInviteRecordDO();
		helpRedpacketInviteRecordDO.setAttendId(param.getAttendId());
		helpRedpacketInviteRecordDO.setActivityId(Integer.valueOf(param.getRegOrigin()));
		helpRedpacketInviteRecordDO.setAttendUserNum(param.getAttendUserNum());
		helpRedpacketInviteRecordDO.setGmtCreate(new Date());
		helpRedpacketInviteRecordDO.setHelpType(HelpTypeEnum.REF.getVal());
		helpRedpacketInviteRecordDO.setHelpUserAccount(param.getHelpUserAccount());
		helpRedpacketInviteRecordDO.setHelpUserHeadimg(param.getHelpUserHeadimg());
		helpRedpacketInviteRecordDO.setHelpUserNum(param.getHelpUserNum());
		helpRedpacketInviteRecordDOMapper.insertSelective(helpRedpacketInviteRecordDO);

		//助力人数超过10人后不累加倍数
		HelpRedpacketAttendDetailBO helpRedpacketAttendDetailBO = helpRedpacketAttendDetailService.selectByPrimaryKey(param.getAttendId());
		if(helpRedpacketAttendDetailBO.getHelpCount().intValue() < PropertyConstant.HELP_REDPACKET_COUNT){
			helpRedpacketAttendDetailService.addHelpCount(param.getAttendId());
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateInviteRecordHeadimg(String helpUserNum, String helpUserHeadimg) {
		HelpRedpacketInviteRecordDOExample example = new HelpRedpacketInviteRecordDOExample();
		example.createCriteria().andHelpUserNumEqualTo(helpUserNum);
		HelpRedpacketInviteRecordDO hdo = new HelpRedpacketInviteRecordDO();
		hdo.setHelpUserHeadimg(helpUserHeadimg);
		helpRedpacketInviteRecordDOMapper.updateByExampleSelective(hdo,example);
	}

	@Override
	public HelpRedpacketInviteAbnormalDTO queryAbnormalInviteRecord(AbnormalInviteParam param) {
		HelpRedpacketInviteAbnormalDOView abnormalDOView = helpRedpacketAttendDetailDOMapperExtend.queryAbnormalInviteRecordDate(param);
		if(abnormalDOView == null){
			return null;
		}
		HelpRedpacketInviteAbnormalDTO abnormalDTO = new HelpRedpacketInviteAbnormalDTO();
		abnormalDTO.setMaxInviteTime(abnormalDOView.getMaxInviteTime());
		abnormalDTO.setMinInviteTime(abnormalDOView.getMinInviteTime());
		return abnormalDTO;
	}
}
