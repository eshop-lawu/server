/**
 *
 */
package com.lawu.eshop.ad.srv.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.lawu.compensating.transaction.Reply;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.ad.constants.PointPoolStatusEnum;
import com.lawu.eshop.ad.constants.PropertyType;
import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;
import com.lawu.eshop.ad.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.eshop.ad.param.UserPacketRefundParam;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;
import com.lawu.eshop.ad.param.UserRedPacketUpdateParam;
import com.lawu.eshop.ad.srv.AdSrvConfig;
import com.lawu.eshop.ad.srv.bo.UserRedPacketAddReturnBO;
import com.lawu.eshop.ad.srv.bo.UserRedPacketBO;
import com.lawu.eshop.ad.srv.converter.UserRedPacketConverter;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDO;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDOExample;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDOExample.Criteria;
import com.lawu.eshop.ad.srv.domain.UserTakedRedPacketDO;
import com.lawu.eshop.ad.srv.domain.UserTakedRedPacketDOExample;
import com.lawu.eshop.ad.srv.domain.extend.UserRedpacketMaxMoney;
import com.lawu.eshop.ad.srv.mapper.UserRedPacketDOMapper;
import com.lawu.eshop.ad.srv.mapper.UserTakedRedPacketDOMapper;
import com.lawu.eshop.ad.srv.mapper.extend.UserTakedRedpacketBOMapperExtend;
import com.lawu.eshop.ad.srv.service.AdCountRecordService;
import com.lawu.eshop.ad.srv.service.UserRedPacketService;
import com.lawu.eshop.synchronization.lock.impl.LockConstant.LockModule;
import com.lawu.eshop.synchronization.lock.impl.LockService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;
import com.lawu.utils.RedPacketStrategy;
import com.lawu.utils.SpiltRedPacketUntil;

/**
 * 用户红包ServiceImpl
 *
 * @author lihj
 * @date 2017年8月3日
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService ,InitializingBean{

	@Autowired
	private UserRedPacketDOMapper userRedPacketDOMapper;
	@Autowired
	private UserTakedRedPacketDOMapper userTakedRedPacketDOMapper;
	@Autowired
	private UserTakedRedpacketBOMapperExtend userTakedRedpacketBOMapperExtend;
	@Autowired
	@Qualifier("memberRedPacketRefundTransactionMainServiceImpl")
	private TransactionMainService<Reply> memberRedPacketRefundTransactionMainServiceImpl;

	@Autowired
	@Qualifier("memberGetRedPacketTransactionMainServiceImpl")
	private TransactionMainService<Reply> memberGetRedPacketTransactionMainServiceImpl;
	
	@Autowired
	private AdCountRecordService adCountRecordService;
	
	@Autowired
	private LockService lockService;
	
	@Autowired(required = true)
	private AdSrvConfig adSrvConfig;
	
	private RedPacketStrategy redPacketStrategy;
	/**
	 * 新增用户红包
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserRedPacketAddReturnBO addUserRedPacket(UserRedPacketSaveParam param) {
		UserRedPacketDO userRed = UserRedPacketConverter.converDO(param);
		Integer i = userRedPacketDOMapper.insert(userRed);
		if (null == i || i < 0) {
			return null;
		}
		UserRedPacketAddReturnBO dto =UserRedPacketConverter.convertAddBO(userRed);
		return dto;
	}

	/**
	 * 查询用户红包列表
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Page<UserRedPacketBO> selectUserRedPacketList(UserRedPacketSelectNumParam param) {
		UserRedPacketDOExample example = new UserRedPacketDOExample();
		Criteria cr = example.createCriteria();
		cr.andUserNumEqualTo(param.getNum());
		cr.andPayTypeIsNotNull();
		example.setOrderByClause("status asc");
		example.setOrderByClause("gmt_create desc");
		RowBounds rowBounds = new RowBounds(param.getOffset(), param.getPageSize());
		List<UserRedPacketDO> listDO = userRedPacketDOMapper.selectByExampleWithRowbounds(example, rowBounds);
		List<UserRedPacketBO> listBO = Lists.newArrayList();
		for (UserRedPacketDO userRed : listDO) {
			UserRedPacketBO userBO = UserRedPacketConverter.convertBO(userRed);
			Date overdueDate = DateUtil.getDayAfter(userBO.getGmtCreate());// 获取红包过期时间
			if(overdueDate.getTime()<System.currentTimeMillis()&& userRed.getStatus().equals(UserRedPacketEnum.USER_STATUS_EFFECTIVE.val)){
				userBO.setUserRedPacketEnum(UserRedPacketEnum.USER_STATUS_OUT);
			}
			listBO.add(userBO);
		}
		Page<UserRedPacketBO> page = new Page<UserRedPacketBO>();
		page.setCurrentPage(param.getCurrentPage());
		page.setRecords(listBO);
		return page;
	}

	/**
	 * 判断用户红包是否领取完毕
	 * 
	 * @param redPacketId
	 * @return true 还有、false没有了
	 */
	@Override
	public boolean isExistsRedPacket(Long redPacketId) {
		UserRedPacketDO userRedpacket =userRedPacketDOMapper.selectByPrimaryKey(redPacketId);
		return userRedpacket.getStatus()== UserRedPacketEnum.USER_STATUS_EFFECTIVE.val? true : false;
	}

	@Override
	public UserPacketRefundParam selectBackTotalMoney(Long userRedpacketId){
		UserRedPacketDO userRedpacket = userRedPacketDOMapper.selectByPrimaryKey(userRedpacketId);
		UserPacketRefundParam param =UserRedPacketConverter.convertReFund(userRedpacket);
		return param;
	}	


	/**
	 * 用户领取红包
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserRedpacketMaxMoney getUserRedpacketMoney(Long redPacketId, String userNum) {
		Boolean flag = lockService.tryLock(LockModule.LOCK_AD_SRV, "AD_USER_RED_PACKET_LOCK_", redPacketId);

		UserRedpacketMaxMoney getMoney = new UserRedpacketMaxMoney();
		getMoney.setFlag(false);
		getMoney.setSysWords(false);

		if (flag) {

			Result<Object> result = adCountRecordService.getUserRedPacketCountRecord(redPacketId);
			// 是否真正抢到广告
			if (result.getModel() != null && (int) result.getModel() >= 0) {
				UserRedPacketDO userRedPacketDO = userRedPacketDOMapper.selectByPrimaryKey(redPacketId);

				//已领取个数
				UserTakedRedPacketDOExample example = new UserTakedRedPacketDOExample();
				example.createCriteria().andUserRedPackIdEqualTo(redPacketId);
				int count = userTakedRedPacketDOMapper.countByExample(example);

				//已领取总金额
				UserRedpacketMaxMoney view = userTakedRedpacketBOMapperExtend.getSumMoney(redPacketId);
				BigDecimal subMoney = new BigDecimal(0);
				//剩余积分
				if (view == null) {
					subMoney = userRedPacketDO.getTotalMoney().subtract(BigDecimal.valueOf(0));
				} else {
					subMoney = userRedPacketDO.getTotalMoney().subtract(view.getMaxMoney());
				}
				BigDecimal money = new BigDecimal(0);
				byte type;
				if (userRedPacketDO.getType() == RedPacketPutWayEnum.PUT_WAY_LUCK.val) { //手气红包
					if(userRedPacketDO.getId().longValue() <= adSrvConfig.getStrategyMemberRedpacketId().longValue()){
						Double point = SpiltRedPacketUntil.spiltRedPackets(userRedPacketDO.getTotalMoney().doubleValue(),subMoney.doubleValue(), userRedPacketDO.getTotalCount(), count);
						money = BigDecimal.valueOf(point);
					}else{
						money = redPacketStrategy.drawRedPacket(userRedPacketDO.getTotalMoney(), userRedPacketDO.getTotalCount(), userRedPacketDO.getGmtCreate().getTime(), userRedPacketDO.getId(), subMoney, userRedPacketDO.getTotalCount()-count);
					}
					
					type = RedPacketPutWayEnum.PUT_WAY_LUCK.val;
				} else { //普通红包
					if (count == userRedPacketDO.getTotalCount() - 1) {
						money = subMoney;
					} else {
						money = userRedPacketDO.getTotalMoney().divide(new BigDecimal(userRedPacketDO.getTotalCount()), 2, RoundingMode.HALF_UP);
					}
					type = RedPacketPutWayEnum.PUT_WAY_COMMON.val;
				}

				UserTakedRedPacketDO userTabed = new UserTakedRedPacketDO();

				userTabed.setGmtModified(new Date());
				userTabed.setGmtCreate(new Date());
				userTabed.setTakedTime(new Date());
				userTabed.setUserNum(userNum);
				userTabed.setMoney(money);
				userTabed.setOrdinal(count);
				userTabed.setUserRedPackId(redPacketId);
				userTabed.setType(type);
				userTabed.setStatus(PointPoolStatusEnum.AD_POINT_GET.val);
				userTakedRedPacketDOMapper.insert(userTabed);

				if (userRedPacketDO.getTotalCount() - 1 == count || count >= userRedPacketDO.getTotalCount()) {
					userRedPacketDO.setGmtModified(new Date());
					userRedPacketDO.setStatus(UserRedPacketEnum.USER_STATUS_OVER.val);
					userRedPacketDOMapper.updateByPrimaryKeySelective(userRedPacketDO);
				}
				
				getMoney.setFlag(true);
				getMoney.setMaxMoney(userTabed.getMoney());

				//发送消息修改积分
				memberGetRedPacketTransactionMainServiceImpl.sendNotice(userTabed.getId());
			} 

			lockService.unLock(LockModule.LOCK_AD_SRV, "AD_USER_RED_PACKET_LOCK_", redPacketId);

		}else {
			getMoney.setSysWords(true);
		}
		
		return getMoney;
	}

	/**
	 * 查询最大的红包金额
	 */
	@Override
	public UserRedpacketMaxMoney getUserRedpacketMaxMoney(Long redPacketId) {
		UserRedPacketDO  userRedPacketDO = userRedPacketDOMapper.selectByPrimaryKey(redPacketId);
		if (userRedPacketDO == null) {
			return null;
		}
		UserRedpacketMaxMoney userRedpacketMaxMoney = new UserRedpacketMaxMoney();
		userRedpacketMaxMoney.setMaxMoney(userRedPacketDO.getTotalMoney().divide(BigDecimal.valueOf(userRedPacketDO.getTotalCount()),2, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(PropertyType.ad_red_packet_default)));
		return userRedpacketMaxMoney;
	}

	/**
	 * 根据红包ID 获取红包金额、和orderNum支付时调用第三方用
	 */
	@Override
	public ThirdPayCallBackQueryPayOrderDTO selectUserRedPacketInfoForThrid(Long redPacketId) {
		UserRedPacketDO userRed = userRedPacketDOMapper.selectByPrimaryKey(redPacketId);
		ThirdPayCallBackQueryPayOrderDTO third = UserRedPacketConverter.convertThirdPay(userRed);
		return third;
	}

	/**
	 * 第三方回调更新
	 * 
	 */
	@Override
	public boolean updateUserPacketInfo(UserRedPacketUpdateParam paran) {
		UserRedPacketDO user =UserRedPacketConverter.convertDO(paran);
		userRedPacketDOMapper.updateByPrimaryKeySelective(user);
		return true;
	}

	@Override
	public boolean checkUserGetRedpacket(Long redPacketId, String userNum) {
		UserTakedRedPacketDOExample userTakedExample = new UserTakedRedPacketDOExample();
		userTakedExample.createCriteria().andUserRedPackIdEqualTo(redPacketId).andUserNumEqualTo(userNum)
				.andStatusEqualTo(PointPoolStatusEnum.AD_POINT_GET.val);
		int count = userTakedRedPacketDOMapper.countByExample(userTakedExample);
		
		return count==0 ?true :false;
	}

	/**
	 * 红包过期定时任务
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void executeUserRedPacketData(Integer pageSize) {
		Date date = DateUtil.getDayBefore(new Date());// 前一天的时间
		UserRedPacketDOExample example = new UserRedPacketDOExample();
		List<Byte> status = new ArrayList<>();
		status.add(UserRedPacketEnum.USER_STATUS_EFFECTIVE.val);
		status.add(UserRedPacketEnum.USER_STATUS_OVER.val);
		Criteria cr = example.createCriteria();
		cr.andGmtCreateLessThan(date);
		cr.andStatusIn(status);

		int offset = 0;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
		while (true) {
			List<UserRedPacketDO> list = userRedPacketDOMapper.selectByExampleWithRowbounds(example, rowBounds);
			if (list.isEmpty()) {
				return;
			}

			for (UserRedPacketDO userRed : list) {
				BigDecimal totalBackMoney = new BigDecimal(0);
				UserRedpacketMaxMoney view = userTakedRedpacketBOMapperExtend.getSumMoney(userRed.getId());
				if (view == null) {
					totalBackMoney = userRed.getTotalMoney();
				} else {
					totalBackMoney = userRed.getTotalMoney().subtract(view.getMaxMoney());
				}

				userRed.setGmtModified(new Date());
				userRed.setStatus(UserRedPacketEnum.USER_STATUS_OUT.val);
				userRed.setRefundMoney(totalBackMoney);
				userRedPacketDOMapper.updateByPrimaryKeySelective(userRed);

				if (totalBackMoney.compareTo(BigDecimal.valueOf(0)) == 1) {
					memberRedPacketRefundTransactionMainServiceImpl.sendNotice(userRed.getId());
				}
			}
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		redPacketStrategy = new RedPacketStrategy(new BigDecimal(adSrvConfig.getMemberRedpacketMinMultiple()),
				new BigDecimal(adSrvConfig.getMemberRedpacketMaxMultiple()),
				adSrvConfig.getMemberRedpacketBigAmountCount(),
				new BigDecimal(adSrvConfig.getMemberRedpacketBigAmountMinMultiple()),
				new BigDecimal(adSrvConfig.getMemberRedpacketBigAmountMaxMultiple()),
				new BigDecimal(adSrvConfig.getMemberRedpacketAmountMinPercent()),new BigDecimal(adSrvConfig.getMemberRedpacketAmountMaxPercent()));
	}
	
}
