package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.RichPowerRecordDirectionEnum;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.activity.param.RichPowerRecordParam;
import com.lawu.eshop.activity.srv.bo.MerchantRichPowerTaskDetailBO;
import com.lawu.eshop.activity.srv.domain.RichAccountDOExample;
import com.lawu.eshop.activity.srv.domain.RichMerchantPowerTaskRecordDO;
import com.lawu.eshop.activity.srv.domain.RichMerchantPowerTaskRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.RichAccountAddPowerParam;
import com.lawu.eshop.activity.srv.domain.extend.RichMerchantPowerTaskRecordDOExtend;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichMerchantPowerTaskRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichAccountDOExtendMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichMerchantPowerTaskRecordDOMapperExtend;
import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.activity.srv.servcie.RichMerchantPowerTaskRecordService;
import com.lawu.eshop.activity.srv.servcie.RichPowerRecordService;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.MerchantPowerTaskConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTasksDTO;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年6月7日
 */
@Service
public class RichMerchantPowerTaskRecordServiceImpl implements RichMerchantPowerTaskRecordService {

	
	@Autowired
	private RichMerchantPowerTaskRecordDOMapper richMerchantPowerTaskRecordDOMapper;
	
	@Autowired
	private RichConfigCacheService richConfigCacheService;
	
	@Autowired
	private RichAccountDOMapper richAccountDOMapper;
	
	@Autowired
	private RichAccountDOExtendMapper richAccountDOExtendMapper;
	
	@Autowired
	private RichPowerRecordService richPowerRecordService;
	
	@Autowired
	private RichMerchantPowerTaskRecordDOMapperExtend richMerchantPowerTaskRecordDOMapperExtend;
	
	
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveRichPowerTaskRecord(RichMerchantPowerTaskRecordParam param) {
		
		Result<DiamondConfigCacheDTO> diamond = richConfigCacheService.getCacheDiamondConfig();
		
		if (diamond.getModel() == null || !diamond.getModel().getIsOpen()) {
			return;
		}

		RichAccountDOExample accountDOExample = new RichAccountDOExample();
		accountDOExample.createCriteria().andUserNumEqualTo(param.getMerchantNum());
		int accountCount = (int) richAccountDOMapper.countByExample(accountDOExample);
		if (accountCount == 0) {
			return;
		}

		RichMerchantPowerTaskRecordDOExample example = new RichMerchantPowerTaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(param.getMerchantNum());
		int count = (int) richMerchantPowerTaskRecordDOMapper.countByExample(example);
		
		MerchantPowerTaskTypeEnum type = param.getType();
		//获取动力配置
		Result<MerchantPowerTaskConfigCacheDTO>  configResult = richConfigCacheService.getMerchantPowerTaskConfigCache(type);
		MerchantPowerTaskConfigCacheDTO config = configResult.getModel();
		//判断邀请E友和专场活动是否在时间范围内
		if(type == MerchantPowerTaskTypeEnum.INVITE_FRIEND || type == MerchantPowerTaskTypeEnum.ACTIVITY){
			if (!DateUtil.belongCalendar(new Date(), config.getBeginTime(), config.getEndTime()))
				return;
		}
		//如果没有记录，则初始化
		if(count == 0){ 
			RichMerchantPowerTaskRecordDO record = new RichMerchantPowerTaskRecordDO();
			record.setUserNum(param.getMerchantNum());
			record.setGmtCreate(new Date());
			record.setGmtModified(new Date());
			richMerchantPowerTaskRecordDOMapper.insertSelective(record);
		}
		
		List<RichMerchantPowerTaskRecordDO> list = richMerchantPowerTaskRecordDOMapper.selectByExample(example);
		
		RichMerchantPowerTaskRecordDO recordDO = list.get(0);
		
		if (type == MerchantPowerTaskTypeEnum.LOGIN) {
			if (recordDO.getIsLogin()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == MerchantPowerTaskTypeEnum.ALIPAY_BIND) {
			if (recordDO.getIsBindingAlipay()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == MerchantPowerTaskTypeEnum.WX_BIND) {
			if (recordDO.getIsBindingWx()) {
				return;
			} else {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}
		} else if (type == MerchantPowerTaskTypeEnum.AD) {
			if (recordDO.getAdCount() == config.getTaskCount() - 1) {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}else{
				updatePowerTaskRecord(param);
			}
		} else if (type == MerchantPowerTaskTypeEnum.NEW_SHOPPING) {
			if (recordDO.getNewProductCount() == config.getTaskCount() - 1) {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}else{
				updatePowerTaskRecord(param);
			}
		}else if (type == MerchantPowerTaskTypeEnum.ACTIVITY) {
			if (recordDO.getActivityCount() == config.getTaskCount() - 1) {
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param, config.getPowerCount());
			}else{
				updatePowerTaskRecord(param);
			}
		}else if(type == MerchantPowerTaskTypeEnum.INVITE_FRIEND){
			
			if(recordDO.getFriendInviteCount() >= config.getTaskCount()){
				updatePowerTaskRecord(param);
				return;
			}else{
				updatePowerTaskRecord(param);
				saveRichPowerRecord(param,config.getPowerCount());
			}
			
		}else if(type == MerchantPowerTaskTypeEnum.INVITE_FENS){
			
			if(recordDO.getFensInviteCount() >= config.getTaskCount()-param.getFensInviteCount()){
				//判断是否邀请粉丝获取动力
				Result<Boolean> result = richConfigCacheService.isGetTask(param.getMerchantNum(), param.getType());
				if(!result.getModel()){
					updatePowerTaskRecord(param);
					saveRichPowerRecord(param,config.getPowerCount());
				}
				return;
			}else{
				updatePowerTaskRecord(param);
			}
			
		}else if(type == MerchantPowerTaskTypeEnum.RECHARGE_POINT){
			
			if(recordDO.getRechargePoint() >= config.getTaskCount()-param.getPoint()){
				//判断是否邀请粉丝获取动力
				Result<Boolean> result = richConfigCacheService.isGetTask(param.getMerchantNum(), param.getType());
				if(!result.getModel()){
					updatePowerTaskRecord(param);
					saveRichPowerRecord(param,config.getPowerCount());
				}
				return;
			}else{
				updatePowerTaskRecord(param);
			}
			
		}
		

	}

	@Override
	public Integer getMerchantPowerTaskRecordListCount() {
		return (int) richMerchantPowerTaskRecordDOMapper.countByExample(null);
	}

	@Override
	public void resetMerchantTaskRecord(Integer currentPage, Integer pageSize) {
		RowBounds rowBounds = new RowBounds((currentPage - 1) * pageSize, pageSize);
		List<RichMerchantPowerTaskRecordDO> recordDOS = richMerchantPowerTaskRecordDOMapper.selectByExampleWithRowbounds(null, rowBounds);
		if (!recordDOS.isEmpty()) {
			//获取动力配置
			Result<MerchantPowerTaskConfigCacheDTO> configResult = richConfigCacheService.getMerchantPowerTaskConfigCache(MerchantPowerTaskTypeEnum.INVITE_FRIEND);
			MerchantPowerTaskConfigCacheDTO config = configResult.getModel();

			RichMerchantPowerTaskRecordDOExample example = new RichMerchantPowerTaskRecordDOExample();
			example.createCriteria().andIdGreaterThanOrEqualTo(recordDOS.get(0).getId()).andIdLessThanOrEqualTo(recordDOS.get(recordDOS.size() - 1).getId());
			RichMerchantPowerTaskRecordDO recordDO = new RichMerchantPowerTaskRecordDO();
			recordDO.setIsLogin(false);
			if (config!= null && config.getEndTime()!= null && DateUtil.isOverdue(config.getEndTime())) {
				recordDO.setFriendInviteCount(0);
			}
			Result<MerchantPowerTaskConfigCacheDTO> activeCache = richConfigCacheService.getMerchantPowerTaskConfigCache(MerchantPowerTaskTypeEnum.ACTIVITY);
			MerchantPowerTaskConfigCacheDTO activeConfig = activeCache.getModel();
			if (activeConfig!= null && activeConfig.getEndTime()!= null && DateUtil.isOverdue(activeConfig.getEndTime())) {
				recordDO.setActivityCount(0);
			}
			recordDO.setFensInviteCount(0);
			recordDO.setAdCount(0);
			recordDO.setNewProductCount(0);
			recordDO.setGmtModified(new Date());
			recordDO.setAdCount(0);
			recordDO.setRechargePoint(0);
			richMerchantPowerTaskRecordDOMapper.updateByExampleSelective(recordDO, example);
		}
	}

	@Override
	public List<MerchantRichPowerTaskDetailBO> getPowerTasks(String memberNum) {
		Result<PowerTasksDTO> result = richConfigCacheService.getMerchantPowerTaskConfigCaches();
		List<MerchantPowerTaskConfigCacheDTO> merchantList = result.getModel().getMerchantList();
		RichMerchantPowerTaskRecordDOExample example = new RichMerchantPowerTaskRecordDOExample();
		example.createCriteria().andUserNumEqualTo(memberNum);
		List<RichMerchantPowerTaskRecordDO> recordDOS = richMerchantPowerTaskRecordDOMapper.selectByExample(example);
		if (recordDOS.isEmpty()) {
			return new ArrayList<>();
		}
		List<MerchantRichPowerTaskDetailBO> detailBOS = new ArrayList<>();
		MerchantRichPowerTaskDetailBO detailBO;
		for (MerchantPowerTaskConfigCacheDTO cacheDTO : merchantList) {
			detailBO = new MerchantRichPowerTaskDetailBO();
			detailBO.setPowerCount(cacheDTO.getPowerCount());
			detailBO.setTaskCount(cacheDTO.getTaskCount());
			detailBO.setBeginTime(cacheDTO.getBeginTime());
			detailBO.setEndTime(cacheDTO.getEndTime());
			detailBO.setStatus(cacheDTO.getStatus());
			MerchantPowerTaskTypeEnum type = cacheDTO.getType();
			detailBO.setType(type);
			Integer residue = 0;
			if (type == MerchantPowerTaskTypeEnum.LOGIN) {
				detailBO.setIsFinish(recordDOS.get(0).getIsLogin());
			} else if (type == MerchantPowerTaskTypeEnum.ALIPAY_BIND) {
				detailBO.setIsFinish(recordDOS.get(0).getIsBindingAlipay());
			} else if (type == MerchantPowerTaskTypeEnum.WX_BIND) {
				detailBO.setIsFinish(recordDOS.get(0).getIsBindingWx());
			} else if (type == MerchantPowerTaskTypeEnum.AD) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getAdCount();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getAdCount() >= cacheDTO.getTaskCount());
			} else if (type == MerchantPowerTaskTypeEnum.NEW_SHOPPING) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getNewProductCount();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getNewProductCount() >= cacheDTO.getTaskCount());
			} else if (type == MerchantPowerTaskTypeEnum.INVITE_FRIEND) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getFriendInviteCount();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getFriendInviteCount() >= cacheDTO.getTaskCount());
			} else if (type == MerchantPowerTaskTypeEnum.RECHARGE_POINT) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getRechargePoint();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getRechargePoint() >= cacheDTO.getTaskCount());
			} else if (type == MerchantPowerTaskTypeEnum.INVITE_FENS) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getFensInviteCount();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getFensInviteCount() >= cacheDTO.getTaskCount());
			} else if (type == MerchantPowerTaskTypeEnum.ACTIVITY) {
				residue = cacheDTO.getTaskCount() - recordDOS.get(0).getActivityCount();
				detailBO.setResidueCount(residue <= 0 ? 0 : residue);
				detailBO.setIsFinish(recordDOS.get(0).getActivityCount() >= cacheDTO.getTaskCount());
			}

			detailBOS.add(detailBO);
		}
		return detailBOS;
	}


	/**
	 * 收支记录
	 * 
	 * @param param
	 * @param power
	 */
	private void saveRichPowerRecord(RichMerchantPowerTaskRecordParam param,int power){
		//账户加动力值
		RichAccountAddPowerParam powerParam = new RichAccountAddPowerParam();
		powerParam.setMemberNum(param.getMerchantNum());
		powerParam.setPower(power);
		richAccountDOExtendMapper.addMemberPower(powerParam);
		
		MerchantPowerTaskTypeEnum type = param.getType();
		RichPowerRecordParam powerRecord = new RichPowerRecordParam();
		powerRecord.setDirectionEnum(RichPowerRecordDirectionEnum.IN);
		powerRecord.setMemberNum(param.getMerchantNum());
		powerRecord.setMerchantTypeEnum(type);
		powerRecord.setPower(power);
		powerRecord.setUserType(UserTypeEnum.MERCHANT);
		richPowerRecordService.saveRichPowerRecord(powerRecord);
	}
	
	
	/**
	 * 修改动力任务记录
	 * 
	 * @param param
	 */
	private void updatePowerTaskRecord(RichMerchantPowerTaskRecordParam param){
		RichMerchantPowerTaskRecordDOExtend extend = new RichMerchantPowerTaskRecordDOExtend();
		extend.setMerchantNum(param.getMerchantNum());
		extend.setPoint(param.getPoint());
		extend.setType(param.getType().getVal());
		extend.setFensInviteCount(param.getFensInviteCount());
		richMerchantPowerTaskRecordDOMapperExtend.updatePowerTaskRecord(extend);
	}


}
