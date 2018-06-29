package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.param.RichPowerRecordParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.RichPowerTaskDetailBO;
import com.lawu.eshop.activity.srv.domain.RichAccountDOExample;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.RichAccountAddPowerParam;
import com.lawu.eshop.activity.srv.domain.extend.RichPowerTaskRecordDOExtend;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichPowerRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichPowerTaskRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichAccountDOExtendMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichPowerTaskRecordDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.activity.srv.servcie.RichPowerRecordService;
import com.lawu.eshop.activity.srv.servcie.RichPowerTaskRecordService;
import com.lawu.eshop.cache.constants.PowerTaskStatusEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.cache.dto.PowerTasksDTO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年5月2日
 */
@Service
public class RichPowerTaskRecordServiceImpl implements RichPowerTaskRecordService {

	@Autowired
	private RichPowerTaskRecordDOMapper richPowerTaskRecordDOMapper;
	
	@Autowired
	private RichPowerTaskRecordDOMapperExtend richPowerTaskRecordDOMapperExtend;
	
	@Autowired
	private RichPowerRecordService richPowerRecordService;
	
	@Autowired
	private RichConfigCacheService richConfigCacheService;
	
	@Autowired
	private RichAccountDOMapper richAccountDOMapper;
	
	@Autowired
	private RichAccountDOExtendMapper richAccountDOExtendMapper;
	
	private static Logger logger = LoggerFactory.getLogger(RichPowerTaskRecordServiceImpl.class);
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRichPowerTaskRecord(RichPowerTaskRecordParam param) {
		
		Result<DiamondConfigCacheDTO> diamond = richConfigCacheService.getCacheDiamondConfig();
		
		if(diamond.getModel() == null || !diamond.getModel().getIsOpen()){
			return;
		}
		
		RichAccountDOExample accountDOExample = new RichAccountDOExample();
		accountDOExample.createCriteria().andUserNumEqualTo(param.getMemberNum());
		int accountCount = (int)richAccountDOMapper.countByExample(accountDOExample);
		if (accountCount == 0) {
			return;
		}

		PowerTaskTypeEnum type = param.getType();

		//获取动力配置
		Result<PowerTaskConfigBaseCacheDTO> result = richConfigCacheService.getPowerTaskConfigCache(type);
		PowerTaskConfigBaseCacheDTO config = result.getModel();
		
		if (config.getStatus() == PowerTaskStatusEnum.DISABLED) {
			return;
		}

		if (type == PowerTaskTypeEnum.INVITE) {
			if (!DateUtil.belongCalendar(new Date(), config.getBeginTime(), config.getEndTime()))
				return;
		}
		
		RichPowerTaskRecordDOExample example = new RichPowerTaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getMemberNum());
		int count = (int)richPowerTaskRecordDOMapper.countByExample(example);

		if(count == 0){
			RichPowerTaskRecordDO record = new RichPowerTaskRecordDO();
			record.setUserNum(param.getMemberNum());
			record.setGmtCreate(new Date());
			record.setGmtModified(new Date());
			richPowerTaskRecordDOMapper.insertSelective(record);
		}

		List<RichPowerTaskRecordDO> list = richPowerTaskRecordDOMapper.selectByExample(example);
		RichPowerTaskRecordDO recordDO = list.get(0);
		
		if (type == PowerTaskTypeEnum.LOGIN) {
			if (recordDO.getIsLogin()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == PowerTaskTypeEnum.ALIPAY_BIND) {
			logger.info("----------------支付宝绑定状态："+recordDO.getIsBindingAlipay());
			if (recordDO.getIsBindingAlipay()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				logger.info("----------------收支记录前--------------");
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == PowerTaskTypeEnum.USER_AUTH) {
			if (recordDO.getIsBindingAlipay()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == PowerTaskTypeEnum.AD) {
			if (recordDO.getAdCount() == config.getTaskCount() - 1) {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}else{
				updatePowerTaskRecord(param);
			}
		} else if (type == PowerTaskTypeEnum.GAME) {
			if (recordDO.getGameCount() == config.getTaskCount() - 1) {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}else{
				updatePowerTaskRecord(param);
			}
		}else if(type == PowerTaskTypeEnum.INVITE){
			
			if(recordDO.getFriendInviteCount() >= config.getTaskCount()){
				updatePowerTaskRecord(param);
				return;
			}else{
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param,config.getPowerCount());
			}
			
		}else if(type == PowerTaskTypeEnum.SHOPPING){
			if (recordDO.getShoppingAmount() >= config.getTaskCount() - param.getShoppingAmount()) {
				// 查询今天是否已经获取到动力
				Result<Boolean> res = richConfigCacheService.isShoppingTask(param.getMemberNum());
				
				if(!res.getModel()){
					updatePowerTaskRecord(param);
					saveRichPowerRecord(param, config.getPowerCount());
				}
				
			} else {
				updatePowerTaskRecord(param);
			}
		}
		
	}
	
	
	/**
	 * 收支记录
	 * 
	 * @param param
	 * @param power
	 */
	private void saveRichPowerRecord(RichPowerTaskRecordParam param,int power){
		logger.info("----------------收支记录后--------------");
		//账户加动力值
		RichAccountAddPowerParam powerParam = new RichAccountAddPowerParam();
		powerParam.setMemberNum(param.getMemberNum());
		powerParam.setPower(power);
		richAccountDOExtendMapper.addMemberPower(powerParam);
		
		PowerTaskTypeEnum type = param.getType();
		RichPowerRecordParam powerRecord = new RichPowerRecordParam();
		powerRecord.setDirectionEnum(RichPowerRecordDirectionEnum.IN);
		powerRecord.setMemberNum(param.getMemberNum());
		powerRecord.setTypeEnum(type);
		powerRecord.setPower(power);
		powerRecord.setUserType(UserTypeEnum.MEMBER);
		richPowerRecordService.saveRichPowerRecord(powerRecord);
	}
	
	
	/**
	 * 修改动力任务记录
	 * 
	 * @param param
	 */
	private void updatePowerTaskRecord(RichPowerTaskRecordParam param){
		RichPowerTaskRecordDOExtend extend = new RichPowerTaskRecordDOExtend();
		extend.setMemberNum(param.getMemberNum());
		extend.setShoppingAmount(param.getShoppingAmount());
		extend.setType(param.getType().getVal());
		richPowerTaskRecordDOMapperExtend.updatePowerTaskRecord(extend);
	}


	@Override
	public List<RichPowerTaskDetailBO> getPowerTasks(String memberNum) {
		
		Result<PowerTasksDTO> result = richConfigCacheService.getPowerTaskConfigCaches();
		List<PowerTaskConfigBaseCacheDTO> configs = result.getModel().getList();
		
		List<RichPowerTaskDetailBO> list = new ArrayList<RichPowerTaskDetailBO>();
		
		RichPowerTaskRecordDOExample example = new RichPowerTaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(memberNum);
		List<RichPowerTaskRecordDO> tasks = richPowerTaskRecordDOMapper.selectByExample(example);
		
		RichPowerTaskRecordDO recordDO = null ;
		if(!tasks.isEmpty()){
			recordDO= tasks.get(0);
		}
		
		for (PowerTaskConfigBaseCacheDTO powerTaskConfigBaseCacheDTO : configs) {
			RichPowerTaskDetailBO detail = new RichPowerTaskDetailBO();
			detail.setPowerCount(powerTaskConfigBaseCacheDTO.getPowerCount());
			detail.setTaskCount(powerTaskConfigBaseCacheDTO.getTaskCount());
			detail.setBeginTime(powerTaskConfigBaseCacheDTO.getBeginTime());
			detail.setEndTime(powerTaskConfigBaseCacheDTO.getEndTime());
			detail.setStatus(powerTaskConfigBaseCacheDTO.getStatus());
			PowerTaskTypeEnum type = powerTaskConfigBaseCacheDTO.getType();
			detail.setType(type);
			if (recordDO == null) {
				detail.setIsFinish(false);
				continue;
			}
			if (type == PowerTaskTypeEnum.LOGIN) {
				detail.setIsFinish(recordDO.getIsLogin());
			} else if (type == PowerTaskTypeEnum.ALIPAY_BIND) {
				detail.setIsFinish(recordDO.getIsBindingAlipay());
			} else if (type == PowerTaskTypeEnum.USER_AUTH) {
				detail.setIsFinish(recordDO.getIsIdentityAuth());
			} else if (type == PowerTaskTypeEnum.AD) {
				detail.setResidueCount(powerTaskConfigBaseCacheDTO.getTaskCount()-recordDO.getAdCount());
				detail.setIsFinish(recordDO.getAdCount() >= powerTaskConfigBaseCacheDTO.getTaskCount());
			} else if (type == PowerTaskTypeEnum.GAME) {
				detail.setResidueCount(powerTaskConfigBaseCacheDTO.getTaskCount()-recordDO.getGameCount());
				detail.setIsFinish(recordDO.getGameCount() >= powerTaskConfigBaseCacheDTO.getTaskCount());
			} else if (type == PowerTaskTypeEnum.INVITE) {
				detail.setResidueCount(powerTaskConfigBaseCacheDTO.getTaskCount()-recordDO.getFriendInviteCount());
				detail.setIsFinish(recordDO.getFriendInviteCount() >= powerTaskConfigBaseCacheDTO.getTaskCount());
			} else if (type == PowerTaskTypeEnum.SHOPPING) {
				detail.setResidueCount(powerTaskConfigBaseCacheDTO.getTaskCount()-recordDO.getShoppingAmount());
				detail.setIsFinish(recordDO.getShoppingAmount() >= powerTaskConfigBaseCacheDTO.getTaskCount());
			}

			list.add(detail);
		}

		return list;
	}


	@Override
	public int getPowerTaskRecordListCount() {
		return (int) richPowerTaskRecordDOMapper.countByExample(null);
	}

	@Override
	public void resetTaskRecord(Integer currentPage, Integer pageSize) {
		RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize, pageSize);
		List<RichPowerTaskRecordDO> recordDOS = richPowerTaskRecordDOMapper.selectByExampleWithRowbounds(null, rowBounds);
		if (!recordDOS.isEmpty()) {
			//获取动力配置
			Result<PowerTaskConfigBaseCacheDTO> result = richConfigCacheService.getPowerTaskConfigCache(PowerTaskTypeEnum.INVITE);
			PowerTaskConfigBaseCacheDTO config = result.getModel();
			
			RichPowerTaskRecordDOExample example = new RichPowerTaskRecordDOExample();
			example.createCriteria().andIdGreaterThanOrEqualTo(recordDOS.get(0).getId()).andIdLessThanOrEqualTo(recordDOS.get(recordDOS.size() - 1).getId());
			RichPowerTaskRecordDO recordDO = new RichPowerTaskRecordDO();
			recordDO.setIsLogin(false);
			if(DateUtil.isOverdue(config.getEndTime())){
				recordDO.setFriendInviteCount(0);
			}
			recordDO.setGameCount(0);
			recordDO.setAdCount(0);
			recordDO.setShoppingAmount(0);
			recordDO.setGmtModified(new Date());
			richPowerTaskRecordDOMapper.updateByExampleSelective(recordDO, example);
		}
	}


}
