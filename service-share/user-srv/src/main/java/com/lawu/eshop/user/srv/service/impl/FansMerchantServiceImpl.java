package com.lawu.eshop.user.srv.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.synchronization.lock.impl.LockConstant;
import com.lawu.eshop.synchronization.lock.impl.LockConstant.LockModule;
import com.lawu.eshop.synchronization.lock.impl.LockService;
import com.lawu.eshop.user.constants.FansInviteResultEnum;
import com.lawu.eshop.user.constants.FansMerchantChannelEnum;
import com.lawu.eshop.user.param.FansMerchantQueryParam;
import com.lawu.eshop.user.param.FensCountQuery;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListFansRealParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansRealParam;
import com.lawu.eshop.user.param.ListInviteFansRealWithContentParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.eshop.user.srv.bo.FansMerchantBO;
import com.lawu.eshop.user.srv.bo.FansMerchantQueryBO;
import com.lawu.eshop.user.srv.converter.FansMerchantConverter;
import com.lawu.eshop.user.srv.domain.FansInviteResultDO;
import com.lawu.eshop.user.srv.domain.FansMerchantDO;
import com.lawu.eshop.user.srv.domain.FansMerchantDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreProfileDOExample;
import com.lawu.eshop.user.srv.domain.extend.FansMerchantDOView;
import com.lawu.eshop.user.srv.domain.extend.FavoriteMerchantDOView;
import com.lawu.eshop.user.srv.domain.extend.FensCountView;
import com.lawu.eshop.user.srv.mapper.FansInviteResultDOMapper;
import com.lawu.eshop.user.srv.mapper.FansMerchantDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.mapper.MerchantStoreProfileDOMapper;
import com.lawu.eshop.user.srv.mapper.extend.FansMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.mapper.extend.FavoriteMerchantDOMapperExtend;
import com.lawu.eshop.user.srv.service.FansMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DistanceUtil;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
@Service
public class FansMerchantServiceImpl implements FansMerchantService {

	@Autowired
	private FansMerchantDOMapperExtend fansMerchantDOMapperExtend;

	@Autowired
	private FansMerchantDOMapper fansMerchantDOMapper;

	@Autowired
	private FansInviteResultDOMapper fansInviteResultDOMapper;

	@Autowired
	@Qualifier("memberFansTransactionMainServiceImpl")
	private TransactionMainService transactionMainService;

	@Autowired
	private FavoriteMerchantDOMapperExtend favoriteMerchantDOMapperExtend;

	@Autowired
	private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

	@Autowired
	private MerchantStoreProfileDOMapper merchantStoreProfileDOMapper;
	@Autowired
	private LockService lockService;

	@Override
	public List<FansMerchantBO> listInviteFans(Long merchantId, ListInviteFansParam param) {
		ListInviteFansRealParam listInviteFansRealParam = new ListInviteFansRealParam();
		listInviteFansRealParam.setMerchantId(merchantId);
		listInviteFansRealParam.setRegionPath(param.getRegionPath());
		listInviteFansRealParam.setSex(param.getUserSexEnum().val);
		listInviteFansRealParam.setAgeLimit(param.getIsAgeLimit());
		listInviteFansRealParam.setStartAge(param.getStartAge());
		listInviteFansRealParam.setEndAge(param.getEndAge());
		listInviteFansRealParam.setInviteCount(param.getInviteCount());
		List<FansMerchantDOView> fansMerchantDOViewList = fansMerchantDOMapperExtend.listInviteFans(listInviteFansRealParam);
		return FansMerchantConverter.convertBO(fansMerchantDOViewList);
	}

	@Override
	public List<FansMerchantBO> listInviteFansWithContent(Long merchantId, ListInviteFansWithContentParam param) {
		ListInviteFansRealWithContentParam listInviteFansRealWithContentParam = new ListInviteFansRealWithContentParam();
		listInviteFansRealWithContentParam.setMerchantId(merchantId);
		listInviteFansRealWithContentParam.setRegionPath(param.getRegionPath());
		listInviteFansRealWithContentParam.setSex(param.getUserSexEnum().val);
		listInviteFansRealWithContentParam.setAgeLimit(param.getIsAgeLimit());
		listInviteFansRealWithContentParam.setStartAge(param.getStartAge());
		listInviteFansRealWithContentParam.setEndAge(param.getEndAge());
		listInviteFansRealWithContentParam.setInviteCount(param.getInviteCount());
		listInviteFansRealWithContentParam.setInviteType((int) param.getInviteType());
		if (StringUtils.isNotEmpty(param.getNums())) {
			String[] num = param.getNums().split(",");
			List<String> numList = Arrays.asList(num);
			listInviteFansRealWithContentParam.setNums(numList);
		}
		List<FansMerchantDOView> list = fansMerchantDOMapperExtend.listInviteFansWithContent(listInviteFansRealWithContentParam);
		return FansMerchantConverter.convertBO(list);
	}

	@Override
	public Page<FansMerchantBO> pageListInviteFans(Long merchantId, PageListInviteFansParam param) {
		ListInviteFansRealParam listInviteFansRealParam = new ListInviteFansRealParam();
		listInviteFansRealParam.setMerchantId(merchantId);
		listInviteFansRealParam.setRegionPath(param.getRegionPath());
		listInviteFansRealParam.setSex(param.getUserSexEnum().val);
		listInviteFansRealParam.setAgeLimit(param.getIsAgeLimit());
		listInviteFansRealParam.setStartAge(param.getStartAge());
		listInviteFansRealParam.setEndAge(param.getEndAge());
		listInviteFansRealParam.setInviteCount(param.getInviteCount());
		listInviteFansRealParam.setCurrentPage(param.getCurrentPage());
		listInviteFansRealParam.setPageSize(param.getPageSize());
		List<FansMerchantDOView> fansMerchantDOViewList = fansMerchantDOMapperExtend.pageListInviteFans(listInviteFansRealParam);
		Page<FansMerchantBO> page = new Page<>();
		page.setCurrentPage(param.getCurrentPage());
		Integer count = fansMerchantDOMapperExtend.countInviteFans(listInviteFansRealParam);
		if (param.getInviteCount() != null && param.getInviteCount() < count) {
			count = param.getInviteCount();
		}
		page.setTotalCount(count);
		page.setRecords(FansMerchantConverter.convertBO(fansMerchantDOViewList));
		return page;
	}

	@Override
	public Page<FansMerchantBO> listFans(Long merchantId, ListFansParam listFansParam) {
		ListFansRealParam listFansRealParam = new ListFansRealParam();
		listFansRealParam.setMerchantId(merchantId);
		listFansRealParam.setRegionPath(listFansParam.getRegionPath());
		listFansRealParam.setCurrentPage(listFansParam.getCurrentPage());
		listFansRealParam.setPageSize(listFansParam.getPageSize());
		List<FansMerchantDOView> fansMerchantDOViewList = fansMerchantDOMapperExtend.listFans(listFansRealParam);

		Page<FansMerchantBO> page = new Page<>();
		page.setRecords(FansMerchantConverter.convertBO(fansMerchantDOViewList));
		page.setTotalCount(fansMerchantDOMapperExtend.countFans(listFansRealParam));
		page.setCurrentPage(listFansParam.getCurrentPage());
		return page;
	}

	@Override
	public FansMerchantBO getFansMerchant(Long memberId, Long merchantId) {
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		fansMerchantDOExample.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(merchantId).andStatusEqualTo((byte) 1);
		List<FansMerchantDO> fansMerchantDOS = fansMerchantDOMapper.selectByExample(fansMerchantDOExample);
		return fansMerchantDOS.isEmpty() ? null : FansMerchantConverter.convertBO(fansMerchantDOS.get(0));
	}

	@Override
	public List<Long> findMerchant(Long memberId) {
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		fansMerchantDOExample.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo((byte) 1);
		List<FansMerchantDO> fansMerchantDOS = fansMerchantDOMapper.selectByExample(fansMerchantDOExample);
		List<Long> merchantIds = new ArrayList<>();
		for (FansMerchantDO fansMerchantDO : fansMerchantDOS) {
			merchantIds.add(fansMerchantDO.getMerchantId());
		}
		return merchantIds;
	}

	@Override
	public Integer findFensCount(Long merchantId) {
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		fansMerchantDOExample.createCriteria().andMerchantIdEqualTo(merchantId).andStatusEqualTo((byte) 1);
		int count = fansMerchantDOMapper.countByExample(fansMerchantDOExample);
		return count;
	}

	@Override
	public Integer findMerchantFensCount(FensCountQuery query) {
		FensCountView view = new FensCountView();
		view.setMinAge(query.getMinAge());
		view.setMaxAge(query.getMaxAge());
		view.setMerchantId(query.getMerchantId());
		if (query.getSexEnum() != null) {
			view.setSex(query.getSexEnum().val);
		}
		Integer count = fansMerchantDOMapperExtend.findMerchantFensCount(view);
		return count;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveFansMerchant(Long merchantId, Long memberId, FansMerchantChannelEnum channelEnum) {
		boolean isLock = lockService.tryLock(0, 5000, LockModule.LOCK_USER_SRV,
				LockConstant.CREATE_ORDER_BECOME_FANS.concat(memberId.toString()).concat("_").concat(merchantId.toString()));
		if (!isLock) {
			return;
		}
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		FansMerchantDOExample.Criteria criteria = fansMerchantDOExample.createCriteria();
		criteria.andMemberIdEqualTo(memberId);
		criteria.andMerchantIdEqualTo(merchantId);
		criteria.andStatusEqualTo((byte) 1);
		int result = fansMerchantDOMapper.countByExample(fansMerchantDOExample);
		if (result > 0) {
			return;
		}

		FansMerchantDO fansMerchantDO = new FansMerchantDO();
		fansMerchantDO.setMemberId(memberId);
		fansMerchantDO.setMerchantId(merchantId);
		fansMerchantDO.setChannel(channelEnum.getValue());
		fansMerchantDO.setGmtCreate(new Date());
		fansMerchantDOMapper.insertSelective(fansMerchantDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveFansMerchantFromInvite(Long merchantId, Long memberId, Long messageId, Boolean dealWay) {
		FansInviteResultDO fansInviteResultDO = new FansInviteResultDO();
		Date date = new Date();
		fansInviteResultDO.setMemberId(memberId);
		fansInviteResultDO.setMerchantId(merchantId);
		fansInviteResultDO.setMessageId(messageId);
		fansInviteResultDO.setGmtCreate(date);
		fansInviteResultDO.setGmtModified(date);
		FansMerchantDOExample fansMerchantDOExample = new FansMerchantDOExample();
		com.lawu.eshop.user.srv.domain.FansMerchantDOExample.Criteria cta = fansMerchantDOExample.createCriteria();
		cta.andMemberIdEqualTo(memberId);
		cta.andMerchantIdEqualTo(merchantId);
		cta.andStatusEqualTo((byte) 0);
		List<FansMerchantDO> list = fansMerchantDOMapper.selectByExample(fansMerchantDOExample);
		Long i = 0L;
		if (list != null && !list.isEmpty()) {
			i = list.get(0).getId();
		}
		if (dealWay) {
			FansMerchantDO fansMerchantDO = new FansMerchantDO();
			fansMerchantDO.setId(i);
			fansMerchantDO.setStatus((byte) 1);
			fansMerchantDO.setGmtCreate(new Date());
			int rows = fansMerchantDOMapper.updateByPrimaryKeySelective(fansMerchantDO);
			fansInviteResultDO.setStatus(FansInviteResultEnum.AGREE.getValue());
			fansInviteResultDOMapper.insert(fansInviteResultDO);
			if (rows > 0) {
				transactionMainService.sendNotice(fansMerchantDO.getId());
			}
		} else {
			fansInviteResultDO.setStatus(FansInviteResultEnum.REFUSE.getValue());
			fansInviteResultDOMapper.insert(fansInviteResultDO);
		}
	}

	@Override
	public FansMerchantBO getFansMerchantById(Long id) {
		FansMerchantDO fansMerchantDO = fansMerchantDOMapper.selectByPrimaryKey(id);
		return FansMerchantConverter.convertBO(fansMerchantDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeFansMerchant(Long merchantId, Long memberId) {
		FansMerchantDOExample example = new FansMerchantDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andMerchantIdEqualTo(merchantId);
		fansMerchantDOMapper.deleteByExample(example);
	}

	@Override
	public Integer getAttentionMerchantCount(Long memberId) {
		FansMerchantDOExample example = new FansMerchantDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo((byte) 1);
		return fansMerchantDOMapper.countByExample(example);
	}

	@Override
	public Page<FansMerchantQueryBO> getFansMerchantList(Long memberId, FansMerchantQueryParam pageQuery) {
		FansMerchantDOExample example = new FansMerchantDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andChannelEqualTo(FansMerchantChannelEnum.FOLLOW.getValue());
		FavoriteMerchantDOView view = new FavoriteMerchantDOView();
		view.setMemberId(memberId);
		RowBounds rowBounds = new RowBounds(pageQuery.getOffset(), pageQuery.getPageSize());
		List<FavoriteMerchantDOView> list = favoriteMerchantDOMapperExtend.selectFansMerchantByRowbounds(view, rowBounds);
		List<FansMerchantQueryBO> listBO = new ArrayList<>();
		for (FavoriteMerchantDOView favoriteMerchantDOView : list) {
			FansMerchantQueryBO favoriteMerchantBO = FansMerchantConverter.convertFansMerchantQueryBO(favoriteMerchantDOView);
			if (pageQuery.getLongitude() != null && pageQuery.getLatitude() != null) {
				int distance = DistanceUtil.getDistance(pageQuery.getLongitude(), pageQuery.getLatitude(),
						favoriteMerchantDOView.getLongitude().doubleValue(), favoriteMerchantDOView.getLatitude().doubleValue());
				favoriteMerchantBO.setDistance(distance);
			}
			// 获取门店logo
			MerchantStoreImageDOExample msidExample = new MerchantStoreImageDOExample();
			msidExample.createCriteria().andMerchantIdEqualTo(favoriteMerchantDOView.getMerchantId()).andStatusEqualTo(true)
					.andTypeEqualTo(new Byte("3"));
			List<MerchantStoreImageDO> msiList = merchantStoreImageDOMapper.selectByExample(msidExample);
			if (!msiList.isEmpty()) {
				favoriteMerchantBO.setPath(msiList.get(0).getPath());
			}
			MerchantStoreProfileDOExample merExam =new MerchantStoreProfileDOExample();
			merExam.createCriteria().andMerchantIdEqualTo(favoriteMerchantDOView.getMerchantId());
			List<MerchantStoreProfileDO> lt = merchantStoreProfileDOMapper.selectByExample(merExam);
			if(!lt.isEmpty()){
				favoriteMerchantBO.setTypeEnum(ManageTypeEnum.getEnum(lt.get(0).getManageType()));
			}
			listBO.add(favoriteMerchantBO);
		}
		Page<FansMerchantQueryBO> page = new Page<>();
		int count = fansMerchantDOMapper.countByExample(example);
		page.setTotalCount(count);
		page.setCurrentPage(pageQuery.getCurrentPage());
		page.setRecords(listBO);
		return page;
	}

}
