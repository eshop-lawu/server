package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.activity.constants.AbnormalStatusEnum;
import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketDetailOperatorParam;
import com.lawu.eshop.activity.param.HelpRedpacketUserParam;
import com.lawu.eshop.activity.param.SignAbnormalParam;
import com.lawu.eshop.activity.srv.bo.AttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketAttendPageBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketUserBO;
import com.lawu.eshop.activity.srv.bo.IdentifiedAsAbnormalBO;
import com.lawu.eshop.activity.srv.bo.SignAbnormalAccountBO;
import com.lawu.eshop.activity.srv.constants.PropertyConstant;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketActivityConverter;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketAttendDetailConverter;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketInviteRecordConverter;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketActivityDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDOExample.Criteria;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDO;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketInviteRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.HelpRedpacketAttendDetailDOExtend;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketActivityDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketInviteRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.HelpRedpacketAttendDetailDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketAttendDetailService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;
import com.lawu.eshop.mq.dto.user.FreezeAccountNotification;

/**
 * @author zhangrc
 * @Description 红包活动报名接口实现类
 * @date 2017年12月28日
 */
@Service
public class HelpRedpacketAttendDetailServiceImpl implements HelpRedpacketAttendDetailService {
	
	@Autowired
	private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;
	
	@Autowired
	private HelpRedpacketActivityDOMapper helpRedpacketActivityDOMapper;
	
	@Autowired
	private HelpRedpacketInviteRecordDOMapper helpRedpacketInviteRecordDOMapper;
	
	@Autowired
	private HelpRedpacketAttendDetailDOMapperExtend helpRedpacketAttendDetailDOMapperExtend;
	
	@Autowired
	@Qualifier("redpacketSendTransactionMainServiceImpl")
	private TransactionMainService<Reply> redpacketSendTransactionMainServiceImpl;
	
	@SuppressWarnings("deprecation")
    @Override
	@Transactional(rollbackFor = Exception.class)
	public AttendBO helpRedpacketAttend(HelpRedpacketAttendParam attendParam) {
		AttendBO attend = new AttendBO();
		Integer activityId = attendParam.getActivityId() == null ? PropertyConstant.HELP_REDPACKET_ACTIVITY_ID : attendParam.getActivityId();
		//判断用户是否已经参与活动
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		example.createCriteria().andUserNumEqualTo(attendParam.getUserNum()).andActivityIdEqualTo(activityId);
		long count = helpRedpacketAttendDetailDOMapper.countByExample(example);
		if (count > 0) { // 已经参与活动
			attend.setIsAttend(true);
			return attend;
		}
		HelpRedpacketActivityDO helpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(activityId);
		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAccount(attendParam.getAccount());
		record.setHeadimg(attendParam.getHeadimg());
		record.setNickname(attendParam.getNickname());
		record.setUserNum(attendParam.getUserNum());
		record.setWxOpenid(attendParam.getWxOpenid());
		record.setGmtCreate(new Date());
		record.setActivityId(activityId);
		record.setRedpacketType(helpRedpacketActivityDO != null ? helpRedpacketActivityDO.getRedpacketType() : RedPacketTypeEnum.BALANCE.getVal());
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.insertSelective(record);
		return attend;
	}


    @Override
    public HelpRedpacketAttendDetailBO selectByPrimaryKey(Long attendId) {
        HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(attendId);
        if(helpRedpacketAttendDetailDO == null){
            return null;
        }
        return HelpRedpacketAttendDetailConverter.converterBO(helpRedpacketAttendDetailDO);
    }

    @Override
    public void addHelpCount(Long attendId) {
        HelpRedpacketAttendDetailDOExtend helpRedpacketAttendDetailDOExtend = new HelpRedpacketAttendDetailDOExtend();
        helpRedpacketAttendDetailDOExtend.setId(attendId);
        helpRedpacketAttendDetailDOExtend.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapperExtend.addHelpCount(helpRedpacketAttendDetailDOExtend);
    }
    
    @Override
    public Page<HelpRedpacketAttendPageBO> helpRedpacketAttendPageOperator(HelpRedpacketDetailOperatorParam detailParam) {
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        Criteria criteria = example.createCriteria();
        if (detailParam.getActivityId() != null) {
            criteria.andActivityIdEqualTo(detailParam.getActivityId());
        }
        if (StringUtils.isNotEmpty(detailParam.getAccount())) {
            criteria.andAccountEqualTo(detailParam.getAccount());
        }
        if (detailParam.getStatusEnum() != null) {
            criteria.andStatusEqualTo(detailParam.getStatusEnum().getVal());
        }
        if (detailParam.getIsLucky() != null) {
            criteria.andIsLuckyEqualTo(detailParam.getIsLucky());
        }
        if (detailParam.getAbnormalStatus() != null) {
            criteria.andAbnormalStatusEqualTo(detailParam.getAbnormalStatus().getVal());
        }
        RowBounds rowBounds = new RowBounds(detailParam.getOffset(), detailParam.getPageSize());
        List<HelpRedpacketAttendDetailDO> list = helpRedpacketAttendDetailDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        Page<HelpRedpacketAttendPageBO> page = new Page<>();
        page.setRecords(HelpRedpacketAttendDetailConverter.converterOperatorBO(list));
        page.setTotalCount((int) helpRedpacketAttendDetailDOMapper.countByExample(example));
        page.setCurrentPage(detailParam.getCurrentPage());

        return page;
    }

	@Override
	public HelpRedpacketAttendBO helpRedpacketAttendDetail(Integer activityId, String userNum, Integer helpCount) throws DataNotExistException{
		HelpRedpacketActivityDO helpRedpacketActivityDO = helpRedpacketActivityDOMapper.selectByPrimaryKey(activityId);
		if(helpRedpacketActivityDO == null){
			throw new DataNotExistException("活动数据不存在");
		}
		HelpRedpacketActivityBO helpRedpacketActivityBO = HelpRedpacketActivityConverter.convert(helpRedpacketActivityDO);
		HelpRedpacketAttendBO helpRedpacketAttendDetailBO = new HelpRedpacketAttendBO();
		HelpRedpacketActivityStatusEnum statusEnum = helpRedpacketActivityBO.getStatus();
		helpRedpacketAttendDetailBO.setActivityStatusEnum(statusEnum);
		helpRedpacketAttendDetailBO.setActivityTheme(helpRedpacketActivityBO.getActivityTheme());
		helpRedpacketAttendDetailBO.setPuzzlePic(helpRedpacketActivityBO.getPuzzlePic());
		helpRedpacketAttendDetailBO.setRules(helpRedpacketActivityBO.getRules());
		Date date = new Date(); //当前时间
		
		if(statusEnum == HelpRedpacketActivityStatusEnum.BEGINNING) {//开枪中
			//结束倒计时
			helpRedpacketAttendDetailBO.setEndTime(helpRedpacketActivityBO.getEndTime().getTime()-date.getTime()); 
		}else if(statusEnum == HelpRedpacketActivityStatusEnum.END){ //活动结束
			BigDecimal total = new BigDecimal("0");
			if (helpRedpacketActivityDO.getSentAmount() != null) {
				helpRedpacketAttendDetailBO.setTotalMoney(helpRedpacketActivityDO.getSentAmount());
			}else{
				total = helpRedpacketAttendDetailDOMapperExtend.getTotalMoney(activityId);
				if(total == null){
					total = BigDecimal.valueOf(0);
				}
				helpRedpacketAttendDetailBO.setTotalMoney(total);
			}
			
		}else{ //开始之前
			//开抢倒计时
			helpRedpacketAttendDetailBO.setStartTime(helpRedpacketActivityBO.getStartTime().getTime()-date.getTime());
		}
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		example.createCriteria().andUserNumEqualTo(userNum).andActivityIdEqualTo(activityId);
		List<HelpRedpacketAttendDetailDO> list = helpRedpacketAttendDetailDOMapper.selectByExample(example);
		//判断用户是否参入活动
		if(list.isEmpty()){
			helpRedpacketAttendDetailBO.setIsAttend(false);
			return helpRedpacketAttendDetailBO;
		}else{
			helpRedpacketAttendDetailBO.setIsAttend(true);
		}
		helpRedpacketAttendDetailBO.setId(list.get(0).getId());
		helpRedpacketAttendDetailBO.setHelpCount(list.get(0).getHelpCount()*helpRedpacketActivityBO.getMultiple().doubleValue()+1);
		helpRedpacketAttendDetailBO.setAccount(list.get(0).getAccount());
		//获取助力好友信息
		HelpRedpacketInviteRecordDOExample inviteRecordDOExample = new HelpRedpacketInviteRecordDOExample();
		inviteRecordDOExample.createCriteria().andAttendUserNumEqualTo(userNum).andActivityIdEqualTo(activityId);
		inviteRecordDOExample.setOrderByClause("gmt_create asc");
		RowBounds rowBounds = new RowBounds(0, helpCount);
		List<HelpRedpacketInviteRecordDO> inviterList = helpRedpacketInviteRecordDOMapper.selectByExampleWithRowbounds(inviteRecordDOExample, rowBounds);
		helpRedpacketAttendDetailBO.setInviteList(HelpRedpacketInviteRecordConverter.helpRedpacketInviteRecordConverterBOS(inviterList));
		
		//判断用户是否抢到过红包
		if(list.get(0).getStatus().byteValue() >= ActivityAttendStatusEnum.GET.getVal().byteValue()){
			helpRedpacketAttendDetailBO.setMoney(BigDecimal.valueOf(list.get(0).getFinalMoney()).divide(BigDecimal.valueOf(100)));
			helpRedpacketAttendDetailBO.setIsGet(true);
		}
		return helpRedpacketAttendDetailBO;
	}


    @SuppressWarnings("deprecation")
    @Override
    public HelpRedpacketAttendDetailBO selectByAttendUserNum(Integer activityId, String attendUserNum) {
        if (activityId == null) {
            activityId = PropertyConstant.HELP_REDPACKET_ACTIVITY_ID;
        }
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        example.createCriteria().andUserNumEqualTo(attendUserNum).andActivityIdEqualTo(activityId);
        List<HelpRedpacketAttendDetailDO> doList = helpRedpacketAttendDetailDOMapper.selectByExample(example);
        if (doList == null || doList.isEmpty()) {
            return null;
        }
        return HelpRedpacketAttendDetailConverter.converterBO(doList.get(0));
    }


	@Override
	@Transactional(rollbackFor = Exception.class)
	public HelpRedpacketBO getHelpRedpacket(Integer activityId, String userNum) {
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample(); 
        example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(ActivityAttendStatusEnum.ALLOT.getVal()).andActivityIdEqualTo(activityId);
        List<HelpRedpacketAttendDetailDO> doList = helpRedpacketAttendDetailDOMapper.selectByExample(example);
		if (doList.isEmpty()) {
			return null;
		}
		HelpRedpacketAttendDetailDO helpRedpacketAttendDetailDO = doList.get(0);
		helpRedpacketAttendDetailDO.setStatus(ActivityAttendStatusEnum.GET.getVal());
		helpRedpacketAttendDetailDO.setTakeTime(new Date());
		int row = helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(helpRedpacketAttendDetailDO);
		HelpRedpacketBO helpRedpacketBO = new HelpRedpacketBO();
		if (row > 0) {
			HelpRedpacketActivityDO helpRedpacketActivityDO = helpRedpacketActivityDOMapper
					.selectByPrimaryKey(helpRedpacketAttendDetailDO.getActivityId());
			helpRedpacketBO.setOriginalMoney(helpRedpacketAttendDetailDO.getOriginalMoney());
			helpRedpacketBO.setMoney(BigDecimal.valueOf(helpRedpacketAttendDetailDO.getFinalMoney()).divide(BigDecimal.valueOf(100)));
			helpRedpacketBO.setMultiple(
					helpRedpacketAttendDetailDO.getHelpCount() * helpRedpacketActivityDO.getMultiple().doubleValue()+1);
	        if (RedPacketTypeEnum.BALANCE.getVal().equals(helpRedpacketAttendDetailDO.getRedpacketType())) {
	            redpacketSendTransactionMainServiceImpl.sendNotice(helpRedpacketAttendDetailDO.getId());
	        }
		}
        return helpRedpacketBO;
	}

	@Override
	public List<HelpRedpacketAttendDetailBO> listSendRedpacketAttendDetail(int offset, int pageSize) {
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		List<Byte> statusList = new ArrayList<>();
		statusList.add(ActivityAttendStatusEnum.GET.getVal());
		statusList.add(ActivityAttendStatusEnum.SEND_FAIL.getVal());
		statusList.add(ActivityAttendStatusEnum.REFUND.getVal());
		RowBounds rowBounds = new RowBounds(offset, pageSize);
		example.createCriteria().andStatusIn(statusList).andRedpacketTypeEqualTo(RedPacketTypeEnum.WX.getVal());
		List<HelpRedpacketAttendDetailDO> attendDetailDOS = helpRedpacketAttendDetailDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		return HelpRedpacketAttendDetailConverter.converterBOS(attendDetailDOS);
	}

	@Override
	public Page<HelpRedpacketUserBO> getHelpRedpacketUser(HelpRedpacketUserParam param) {
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		example.createCriteria().andStatusGreaterThanOrEqualTo(ActivityAttendStatusEnum.GET.getVal()).andActivityIdEqualTo(param.getActivityId());
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		 example.setOrderByClause("take_time asc");
		List<HelpRedpacketAttendDetailDO> list = helpRedpacketAttendDetailDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		Page<HelpRedpacketUserBO> page = new Page<>();
		page.setRecords(HelpRedpacketAttendDetailConverter.converterUserBO(list));
		page.setTotalCount((int) helpRedpacketAttendDetailDOMapper.countByExample(example));
		page.setCurrentPage(param.getCurrentPage());
		
		return page;
	}

	@Transactional(rollbackFor = Exception.class)
    @Override
    public void settingInvalid(FreezeAccountNotification freezeAccountNotification) {
        HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
        example.createCriteria().andUserNumEqualTo(freezeAccountNotification.getUserNum()).andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal());
        List<HelpRedpacketAttendDetailDO> records = helpRedpacketAttendDetailDOMapper.selectByExample(example);
        // 如果没有查询到改记录直接返回
        if (records == null || records.isEmpty()) {
            return;
        }
        Map<Integer, Long> relationshipMap = new HashMap<>();
        records.forEach(item -> {
            relationshipMap.put(item.getActivityId(), item.getId());
        });
        HelpRedpacketActivityDOExample helpRedpacketActivityDOExample = new HelpRedpacketActivityDOExample();
        helpRedpacketActivityDOExample.createCriteria().andIdIn(new ArrayList<>(relationshipMap.keySet())).andEndTimeGreaterThanOrEqualTo(new Date());
        List<HelpRedpacketActivityDO> helpRedpacketActivityDOList = helpRedpacketActivityDOMapper.selectByExample(helpRedpacketActivityDOExample);
        // 如果没有查询到改记录直接返回
        if (helpRedpacketActivityDOList == null || helpRedpacketActivityDOList.isEmpty()) {
            return;
        }
        helpRedpacketActivityDOList.forEach(item -> {
            HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
            record.setId(relationshipMap.get(item.getId()));
            if (freezeAccountNotification.getIsFreeze()) {
                // 在冻结之间判断参与详情状态,如果参与状态是已报名,则标记为
                record.setAbnormalStatus(AbnormalStatusEnum.ABNORMAL.getVal());
            } else {
                record.setAbnormalStatus(AbnormalStatusEnum.WHITE_LIST.getVal());
            }
            record.setGmtModified(new Date());
            helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(record);
        });
    }


	@Transactional(rollbackFor = Exception.class)
    @Override
    public List<IdentifiedAsAbnormalBO> identifiedAsAbnormal(List<String> userNums, int activityId) {
        // 标识当前用户的活动参与记录为异常
       /* HelpRedpacketAttendDetailDOExample helpRedpacketAttendDetailDOExample = new HelpRedpacketAttendDetailDOExample();
        helpRedpacketAttendDetailDOExample.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal());*/

        // 查询当前用户的邀请记录
        HelpRedpacketInviteRecordDOExample helpRedpacketInviteRecordDOExample = new HelpRedpacketInviteRecordDOExample();
        helpRedpacketInviteRecordDOExample.createCriteria().andAttendUserNumIn(userNums).andActivityIdEqualTo(activityId);
        List<HelpRedpacketInviteRecordDO> inviteRecordList = helpRedpacketInviteRecordDOMapper.selectByExample(helpRedpacketInviteRecordDOExample);

        List<String> helpUserNums = new ArrayList<>();
        inviteRecordList.forEach(item -> {
           /* helpRedpacketAttendDetailDOExample.clear();
            helpRedpacketAttendDetailDOExample.createCriteria().andActivityIdEqualTo(activityId).andUserNumEqualTo(item.getHelpUserNum()).andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal());
             record.setGmtModified(new Date());
             int affectedRows = helpRedpacketAttendDetailDOMapper.updateByExampleSelective(record, helpRedpacketAttendDetailDOExample);
            if (affectedRows > 0) {
                IdentifiedAsAbnormalBO identifiedAsAbnormalBO = new IdentifiedAsAbnormalBO();
                identifiedAsAbnormalBO.setAccount(item.getHelpUserAccount());
                identifiedAsAbnormalBO.setUserNum(item.getHelpUserNum());
                inviteUserList.add(identifiedAsAbnormalBO);
            }*/
			helpUserNums.add(item.getHelpUserNum());
        });

		List<IdentifiedAsAbnormalBO> inviteUserList = new ArrayList<>();
		List<String> helpAttendUserNums = new ArrayList<>();

        if (!helpUserNums.isEmpty()) {
			HelpRedpacketAttendDetailDOExample helpRedpacketAttendDetailDOExample = new HelpRedpacketAttendDetailDOExample();
			helpRedpacketAttendDetailDOExample.createCriteria()
					.andActivityIdEqualTo(activityId)
					.andUserNumIn(helpUserNums)
					.andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal())
					.andAbnormalStatusNotEqualTo(AbnormalStatusEnum.WHITE_LIST.getVal());
			List<HelpRedpacketAttendDetailDO> helpUsers = helpRedpacketAttendDetailDOMapper.selectByExample(helpRedpacketAttendDetailDOExample);
			helpUsers.forEach(item -> {
           /* helpRedpacketAttendDetailDOExample.clear();
            helpRedpacketAttendDetailDOExample.createCriteria().andActivityIdEqualTo(activityId).andUserNumEqualTo(item.getHelpUserNum()).andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal());
             record.setGmtModified(new Date());
             int affectedRows = helpRedpacketAttendDetailDOMapper.updateByExampleSelective(record, helpRedpacketAttendDetailDOExample);
            if (affectedRows > 0) {
                IdentifiedAsAbnormalBO identifiedAsAbnormalBO = new IdentifiedAsAbnormalBO();
                identifiedAsAbnormalBO.setAccount(item.getHelpUserAccount());
                identifiedAsAbnormalBO.setUserNum(item.getHelpUserNum());
                inviteUserList.add(identifiedAsAbnormalBO);
            }*/
				IdentifiedAsAbnormalBO identifiedAsAbnormalBO = new IdentifiedAsAbnormalBO();
				identifiedAsAbnormalBO.setAccount(item.getAccount());
				identifiedAsAbnormalBO.setUserNum(item.getUserNum());
				inviteUserList.add(identifiedAsAbnormalBO);

				helpAttendUserNums.add(item.getUserNum());
			});
		}

		helpAttendUserNums.addAll(userNums);


		HelpRedpacketAttendDetailDOExample helpUpdateDOExample = new HelpRedpacketAttendDetailDOExample();
		helpUpdateDOExample.createCriteria().andUserNumIn(helpAttendUserNums).andActivityIdEqualTo(activityId);

		HelpRedpacketAttendDetailDO record = new HelpRedpacketAttendDetailDO();
		record.setAbnormalStatus(AbnormalStatusEnum.ABNORMAL.getVal());
		record.setGmtModified(new Date());
		helpRedpacketAttendDetailDOMapper.updateByExampleSelective(record, helpUpdateDOExample);

        return inviteUserList;
    }


	@Override
	@Transactional(rollbackFor = Exception.class)
	public void executeUpdateActivitySuspectedStatus() {
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		example.createCriteria().andAbnormalStatusEqualTo(AbnormalStatusEnum.NORMAL.getVal())
				.andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal()).andGmtCreateGreaterThanOrEqualTo(DateUtil.stringToDate("2018-02-28 16:40:00"));
		HelpRedpacketAttendDetailDO detailDO = new HelpRedpacketAttendDetailDO();
		detailDO.setAbnormalStatus(AbnormalStatusEnum.SUSPECTED.getVal());
		helpRedpacketAttendDetailDOMapper.updateByExampleSelective(detailDO, example);
	}

	@Override
	public List<SignAbnormalAccountBO> queryAttendDetailList(SignAbnormalParam param) {
		List<Byte> status = new ArrayList<>();
		status.add(AbnormalStatusEnum.NORMAL.getVal());
		status.add(AbnormalStatusEnum.SUSPECTED.getVal());
		HelpRedpacketAttendDetailDOExample example = new HelpRedpacketAttendDetailDOExample();
		example.createCriteria().andAbnormalStatusIn(status)
				.andStatusEqualTo(ActivityAttendStatusEnum.ATTEND.getVal()).andIdGreaterThan(param.getAttendId());
		RowBounds rowBounds = new RowBounds(param.getOffset(),param.getPageSize());
		List<HelpRedpacketAttendDetailDO> list = helpRedpacketAttendDetailDOMapper.selectByExampleWithRowbounds(example,rowBounds);

		return HelpRedpacketAttendDetailConverter.converterAbnormalBOS(list);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateActivityAbnormalStatus(Long id,Byte status) {
		HelpRedpacketAttendDetailDO detailDO = new HelpRedpacketAttendDetailDO();
		detailDO.setId(id);
		detailDO.setAbnormalStatus(status);
		helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(detailDO);
	}

}
