package com.lawu.eshop.activity.srv.servcie.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.RichDiamondRecordSourceEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordStatusEnum;
import com.lawu.eshop.activity.constants.RichDiamondRecordTypeEnum;
import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.activity.srv.bo.IdentityInfoBO;
import com.lawu.eshop.activity.srv.bo.PersonalRichAccountBO;
import com.lawu.eshop.activity.srv.bo.RichConfigBO;
import com.lawu.eshop.activity.srv.bo.RichDetailBO;
import com.lawu.eshop.activity.srv.bo.RichDiamondInfoBO;
import com.lawu.eshop.activity.srv.bo.RichDiamondRecordBO;
import com.lawu.eshop.activity.srv.bo.RichMyDiamondRecordInfoBO;
import com.lawu.eshop.activity.srv.bo.RichPowerInfoRecordBO;
import com.lawu.eshop.activity.srv.bo.RichPowerRecordBO;
import com.lawu.eshop.activity.srv.converter.RichAccountConvert;
import com.lawu.eshop.activity.srv.domain.RichAccountDO;
import com.lawu.eshop.activity.srv.domain.RichAccountDOExample;
import com.lawu.eshop.activity.srv.domain.RichDiamondRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDO;
import com.lawu.eshop.activity.srv.domain.RichPowerTaskRecordDOExample;
import com.lawu.eshop.activity.srv.domain.extend.DiamondDistributionParam;
import com.lawu.eshop.activity.srv.mapper.RichAccountDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichDiamondRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.RichPowerTaskRecordDOMapper;
import com.lawu.eshop.activity.srv.mapper.extend.RichAccountDOExtendMapper;
import com.lawu.eshop.activity.srv.servcie.RichAccountService;
import com.lawu.eshop.activity.srv.servcie.RichConfigCacheService;
import com.lawu.eshop.activity.srv.servcie.RichConfigService;
import com.lawu.eshop.activity.srv.servcie.RichDiamondInfoService;
import com.lawu.eshop.activity.srv.servcie.RichDiamondRecordService;
import com.lawu.eshop.activity.srv.servcie.RichPowerRecordService;
import com.lawu.eshop.activity.srv.servcie.RichPowerTaskRecordService;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.cache.dto.DiamondConfigCacheDTO;
import com.lawu.eshop.cache.dto.PowerTaskConfigBaseCacheDTO;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.IllegalOperationException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.framework.web.Result;

/**
 * 瑞奇岛账户service
 * @author lihj
 * @date 2018/5/2
 */
@Service
public class RichAccountServiceImpl implements RichAccountService{

    @Autowired
    private RichAccountDOMapper richAccountDOMapper;
    
    @Autowired
    private RichConfigCacheService richConfigCacheService;
    
    @Autowired
    private RichAccountDOExtendMapper richAccountDOExtendMapper;
    
    @Autowired
    private RichDiamondRecordService richDiamondRecordService;
    
    @Autowired
    private RichDiamondInfoService richDiamondInfoService;
    
    @Autowired
    private RichConfigService richConfigService;
    
    @Autowired
    private RichDiamondRecordDOMapper richDiamondRecordDOMapper;
    
    @Autowired
    private RichPowerRecordService richPowerRecordService;

    @Autowired
    RichPowerTaskRecordService richPowerTaskRecordService;

    @Autowired
    private RichPowerTaskRecordDOMapper richPowerTaskRecordDOMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PersonalRichAccountBO getPersonalRichAccountInfo(String userNum,UserTypeEnum userType) {
        RichAccountDOExample example =new RichAccountDOExample();
        example.createCriteria().andUserNumEqualTo(userNum);
        long count = richAccountDOMapper.countByExample(example);
        if(count == 0){
        	DiamondConfigCacheDTO diaConfig = richConfigCacheService.getCacheDiamondConfig().getModel();
            PowerTaskConfigBaseCacheDTO powConfig = richConfigCacheService.getPowerTaskConfigCache(PowerTaskTypeEnum.LOGIN).getModel();
            RichAccountDO rich =new RichAccountDO();
            rich.setUserNum(userNum);
            Result<Integer> incr =richConfigCacheService.increaseMemberNum();
            int orderNum =incr.getModel()==null?1:incr.getModel();
            rich.setOrderNum(orderNum);
            rich.setDiamond(BigDecimal.ZERO);
            rich.setDiamondLucky(BigDecimal.ZERO);
            rich.setDiamondTotal(BigDecimal.ZERO);
            rich.setPower(diaConfig.getInitPower());//配置信息中获取
            rich.setPowerSnapshoot(0);
            rich.setLastTakeTime(new Date());
            rich.setGmtCreate(new Date());
            rich.setGmtModified(new Date());
            rich.setUserType(userType.getValue());
            richAccountDOMapper.insert(rich);
            List<RichDiamondRecordBO> list = richDiamondRecordService.getMyRichDiamondRecord(userNum,RichDiamondRecordStatusEnum.NORECEIVE,false);
            RichPowerTaskRecordParam saveParam =new RichPowerTaskRecordParam();
            saveParam.setMemberNum(userNum);
            saveParam.setType(PowerTaskTypeEnum.LOGIN);
            richPowerTaskRecordService.saveRichPowerTaskRecord(saveParam);
            PersonalRichAccountBO bo = RichAccountConvert.convertPersonalRichAccountBO(rich,list);
            bo.setPower(bo.getPower()+powConfig.getPowerCount());
            bo.setFirstOpen(true);
            return bo;
        }else{
        	List<RichAccountDO> list = richAccountDOMapper.selectByExample(example);
        	List<RichDiamondRecordBO> listRecord = richDiamondRecordService.getMyRichDiamondRecord(userNum,RichDiamondRecordStatusEnum.NORECEIVE,false);
        	return RichAccountConvert.convertPersonalRichAccountBO(list.get(0), listRecord);
        	 
        }
    }

	@Override
	public RichDetailBO getRichInfo(UserTypeEnum userType) {
		RichAccountDOExample example =new RichAccountDOExample();
        example.createCriteria();
        long count = richAccountDOMapper.countByExample(example);
        RichAccountDOExample exampleQuery =new RichAccountDOExample();
        exampleQuery.createCriteria().andUserTypeEqualTo(userType.getValue());
        exampleQuery.setOrderByClause("power desc");
        RowBounds rowBounds = new RowBounds(0, 10);
        List<RichAccountDO> listAccount = richAccountDOMapper.selectByExampleWithRowbounds(exampleQuery, rowBounds);
        RichDiamondInfoBO richInfo = richDiamondInfoService.getRichDiamondInfo();
        RichDetailBO bo =new RichDetailBO();
        bo.setCreatorsPeople(Integer.valueOf(String.valueOf(count)));
        bo.setTotalRichPeople(Integer.valueOf(String.valueOf(count)));
        RichConfigBO config = richConfigService.getRichConfig();
        if(null != config){
        	bo.setNotice(config.getNotice());
        }
        bo.setPowerList(RichAccountConvert.convertPowerList(listAccount));
        DiamondConfigCacheDTO diaConfig = richConfigCacheService.getCacheDiamondConfig().getModel();
        bo.setDayRichDiamond(diaConfig.getDailyDiamond());//配置中获取
        bo.setTotalRichDiamond(richInfo.getDiamondLuckySent().add(richInfo.getDiamondSent()));
		return bo;
	}

	@Override
	public RichPowerInfoRecordBO getRichPowerInfoRecord(String userNum) {
		RichAccountDOExample example =new RichAccountDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
	    List<RichAccountDO> listAccount = richAccountDOMapper.selectByExample(example);
	    RichPowerInfoRecordBO rich =new RichPowerInfoRecordBO();
	    rich.setTotalPower(listAccount.get(0).getPower());
	    List<RichPowerRecordBO> list = richPowerRecordService.getRichPowerInfoRecord(userNum);
	    rich.setListRichPowerRecord(list);
		return rich;
	}

	@Override
	public RichMyDiamondRecordInfoBO getMyRichDiamondRecordInfo(String userNum) {
		RichAccountDOExample example =new RichAccountDOExample();
		example.createCriteria().andUserNumEqualTo(userNum);
	    List<RichAccountDO> listAccount = richAccountDOMapper.selectByExample(example);
	    RichMyDiamondRecordInfoBO rich =new RichMyDiamondRecordInfoBO();
	    rich.setTotalDiamond(listAccount.get(0).getDiamondTotal());
	    List<RichDiamondRecordBO> listRecord = richDiamondRecordService.getMyRichDiamondRecord(userNum,RichDiamondRecordStatusEnum.RECEIVE,true);
	    rich.setRecordList(RichAccountConvert.convertRichMyDiamondRecordDetailBO(listRecord));
		return rich;
	}

	@Override
	public IdentityInfoBO getIdentityInfo(String memberNum) {
		RichAccountDOExample example =new RichAccountDOExample();
		example.createCriteria().andUserNumEqualTo(memberNum);
	    List<RichAccountDO> listAccount = richAccountDOMapper.selectByExample(example);
	    IdentityInfoBO info = new IdentityInfoBO();
	    info.setIdCardNum(listAccount.get(0).getIdCardNum());
	    info.setName(listAccount.get(0).getName());
		return info;
	}
	
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void receiveDiamonds(String memberNum, ReceiveDiamondsParam param) {
        RichDiamondRecordDO richDiamondRecordDO = richDiamondRecordDOMapper.selectByPrimaryKey(param.getRecordId());
        if (richDiamondRecordDO == null) {
            throw new DataNotExistException("钻石收支记录不存在");
        }
        if (!richDiamondRecordDO.getUserNum().equals(memberNum)) {
            throw new IllegalOperationException("非法操作钻石收支记录");
        }
        RichDiamondRecordStatusEnum recordStatus = RichDiamondRecordStatusEnum.getEnum(richDiamondRecordDO.getStatus());
        if (RichDiamondRecordStatusEnum.RECEIVE == recordStatus) {
            throw new WrongOperationException("钻石已经领取");
        }
        RichDiamondRecordSourceEnum recordSource = RichDiamondRecordSourceEnum.getEnum(richDiamondRecordDO.getSource());
        if (RichDiamondRecordSourceEnum.DAYGET != recordSource) {
            throw new WrongOperationException("钻石来源异常");
        }
        RichAccountDOExample example = new RichAccountDOExample();
        example.createCriteria().andUserNumEqualTo(memberNum);
        List<RichAccountDO> list = richAccountDOMapper.selectByExample(example);
        if (list == null || list.isEmpty()) {
            throw new DataNotExistException("钻石账户记录不存在");
        }
        RichAccountDO richAccountDO = list.get(0);
        DiamondDistributionParam diamondDistributionParam = new DiamondDistributionParam();
        diamondDistributionParam.setId(richAccountDO.getId());
        RichDiamondRecordTypeEnum recordType = RichDiamondRecordTypeEnum.getEnum(richDiamondRecordDO.getType());
        if (RichDiamondRecordTypeEnum.ECOIN == recordType) {
            diamondDistributionParam.setDiamond(richDiamondRecordDO.getAmount());
        } else {
            diamondDistributionParam.setDiamondLucky(richDiamondRecordDO.getAmount());
        }
        richAccountDOExtendMapper.receiveDiamonds(diamondDistributionParam);
        
        RichDiamondRecordDO richDiamondRecordUpdateDO = new RichDiamondRecordDO();
        richDiamondRecordUpdateDO.setId(richDiamondRecordDO.getId());
        richDiamondRecordUpdateDO.setTakeTime(new Date());
        richDiamondRecordUpdateDO.setStatus(RichDiamondRecordStatusEnum.RECEIVE.getVal());
        richDiamondRecordDOMapper.updateByPrimaryKeySelective(richDiamondRecordUpdateDO);
    }

	@Override
	public void bindAlipayInfo(String memberNum, String alipayUserId) {
		 RichAccountDOExample example = new RichAccountDOExample();
		 example.createCriteria().andUserNumEqualTo(memberNum);
		 RichAccountDO rich =new RichAccountDO();
		 rich.setAlipayBindTime(new Date());
		 rich.setAlipayUserId(alipayUserId);
		 rich.setGmtModified(new Date());
		 richAccountDOMapper.updateByExampleSelective(rich, example);

       /* RichPowerTaskRecordDO richPowerTaskRecordDO = new RichPowerTaskRecordDO();
        richPowerTaskRecordDO.setIsBindingAlipay(true);
        richPowerTaskRecordDO.setGmtModified(new Date());
        RichPowerTaskRecordDOExample richPowerTaskRecordDOExample = new RichPowerTaskRecordDOExample();
        richPowerTaskRecordDOExample.createCriteria().andUserNumEqualTo(memberNum);
        richPowerTaskRecordDOMapper.updateByExampleSelective(richPowerTaskRecordDO,richPowerTaskRecordDOExample);*/
	}
}
