package com.lawu.eshop.member.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.RejectedExecutionException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.authorization.util.UserUtil;
import com.lawu.concurrentqueue.bizctrl.BusinessExecuteException;
import com.lawu.concurrentqueue.bizctrl.annotation.BusinessInventoryCtrl;
import com.lawu.eshop.ad.constants.AdPage;
import com.lawu.eshop.ad.constants.AdPraiseWords;
import com.lawu.eshop.ad.constants.PraiseTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.dto.AdDTO;
import com.lawu.eshop.ad.dto.AdEgainQueryDTO;
import com.lawu.eshop.ad.dto.AdFlatVideoDTO;
import com.lawu.eshop.ad.dto.AdLexiconDTO;
import com.lawu.eshop.ad.dto.AdPointDTO;
import com.lawu.eshop.ad.dto.AdPraiseDTO;
import com.lawu.eshop.ad.dto.ChoicenessAdDTO;
import com.lawu.eshop.ad.dto.ClickAdPointDTO;
import com.lawu.eshop.ad.dto.PraisePointDTO;
import com.lawu.eshop.ad.param.AdChoicenessInternalParam;
import com.lawu.eshop.ad.param.AdChoicenessParam;
import com.lawu.eshop.ad.param.AdEgainInternalParam;
import com.lawu.eshop.ad.param.AdEgainParam;
import com.lawu.eshop.ad.param.AdMemberParam;
import com.lawu.eshop.ad.param.AdPointForeignParam;
import com.lawu.eshop.ad.param.AdPointInternalParam;
import com.lawu.eshop.ad.param.AdPointParam;
import com.lawu.eshop.ad.param.AdPraiseParam;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.concurrentqueue.impl.BusinessKey;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.AdExtendService;
import com.lawu.eshop.member.api.service.AdLexiconService;
import com.lawu.eshop.member.api.service.AdService;
import com.lawu.eshop.member.api.service.FansMerchantService;
import com.lawu.eshop.member.api.service.FavoriteAdService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantProfileService;
import com.lawu.eshop.member.api.service.MerchantStoreService;
import com.lawu.eshop.member.api.service.PropertyInfoDataService;
import com.lawu.eshop.property.param.PointDetailQueryData1Param;
import com.lawu.eshop.user.dto.AdQueryMemberInfoDTO;
import com.lawu.eshop.user.dto.MerchantProfileDTO;
import com.lawu.eshop.user.dto.MerchantStoreDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.DistanceUtil;

@Service
public class AdExtendServiceImpl extends BaseController implements AdExtendService {

	@Autowired
	private AdService adService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MerchantStoreService merchantStoreService;
	

	@Autowired 
	private FansMerchantService fansMerchantService;

	@Autowired
	private MerchantProfileService merchantProfileService;

	@Autowired(required = true)
	private MemberApiConfig memberApiConfig;

	@Autowired
	private FavoriteAdService favoriteAdService;

	@Autowired
	private PropertyInfoDataService propertyInfoDataService;

	@Autowired
	private AdLexiconService adLexiconService;
	
	private static Logger logger = LoggerFactory.getLogger(AdExtendServiceImpl.class);

	@Override
	public Result<Page<AdDTO>> selectListByMember(AdEgainParam adEgainParam) {
		
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		
		AdMemberParam param = new AdMemberParam();
		param.setCurrentPage(adEgainParam.getCurrentPage());
		param.setPageSize(adEgainParam.getPageSize());
		param.setTypeEnum(AdTypeEnum.getEnum(adEgainParam.getTypeEnum().getVal()));
		param.setLatitude(adEgainParam.getLatitude());
		param.setLongitude(adEgainParam.getLongitude());
		
		Result<Page<AdDTO>> pageDTOS = adService.selectListByMember(param, memberId);
		List<AdDTO> list = pageDTOS.getModel().getRecords();
		List<AdDTO> newList = adFilter(param, list, memberId);
		AdPage<AdDTO> adpage = new AdPage<>();
		List<AdDTO> screenList = adpage.page(newList, param.getPageSize(), param.getCurrentPage());
		for (AdDTO adDTO : screenList) {
			Result<Boolean> resultFavoriteAd = favoriteAdService.isFavoriteAd(adDTO.getId(), memberId);
			if (isSuccess(resultFavoriteAd)) {
				adDTO.setIsFavorite(resultFavoriteAd.getModel());
			}
			if(StringUtils.isEmpty(adDTO.getName())){
				adDTO.setName("E店商家");
			}
			if(StringUtils.isEmpty(adDTO.getLogoUrl())){
				adDTO.setLogoUrl(memberApiConfig.getDefaultHeadimg());
			}
			
		}
		Page<AdDTO> newPage = new Page<>();
		newPage.setCurrentPage(pageDTOS.getModel().getCurrentPage());
		newPage.setTotalCount(newList.size());
		newPage.setRecords(screenList);
		return successGet(newPage);
	}

	@Deprecated
	@Override
	public Result<List<AdDTO>> selectAdTopList(AdPointParam adPointParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		AdMemberParam param = new AdMemberParam();
		param.setCurrentPage(adPointParam.getCurrentPage());
		param.setPageSize(adPointParam.getPageSize());
		param.setOrderTypeEnum(adPointParam.getOrderTypeEnum());
		param.setLatitude(adPointParam.getLatitude());
		param.setLongitude(adPointParam.getLongitude());
		Result<Page<AdDTO>> pageDTOS = adService.selectListByMember(param, memberId);
		List<AdDTO> newList = adFilter(param, pageDTOS.getModel().getRecords(), memberId);
		if (newList.size() > 9) {
			newList = newList.subList(0, 10);
		} else {
			newList = newList.subList(0, newList.size());
		}
		
		for (AdDTO adDTO : newList) {
			Result<Boolean> resultFavoriteAd = favoriteAdService.isFavoriteAd(adDTO.getId(), memberId);
			if (isSuccess(resultFavoriteAd)) {
				adDTO.setIsFavorite(resultFavoriteAd.getModel());
			}
			if (adDTO.getName() == null) {
				adDTO.setName("E店商家");
			} 
			if (adDTO.getLogoUrl() == null) {
				adDTO.setLogoUrl(memberApiConfig.getDefaultHeadimg());
			} 
		}
		return successGet(newList);
	}

	@Override
	public Result<Page<AdPraiseDTO>> selectAdPraiseList(AdPraiseParam adPraiseParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<Page<AdDTO>> pageDTOS = adService.selectPraiseListByMember(adPraiseParam, memberId);
		List<AdDTO> list = pageDTOS.getModel().getRecords();
		AdMemberParam adMemberParam = new AdMemberParam();
		if(adPraiseParam.getTransRegionPath()!=null){
			adMemberParam.setTransRegionPath(adPraiseParam.getTransRegionPath());
		}
		List<AdDTO> newList = adFilter(adMemberParam, list, memberId);
		AdPage<AdDTO> adpage = new AdPage<>();
		List<AdDTO> screenList = adpage.page(newList, adPraiseParam.getPageSize(), adPraiseParam.getCurrentPage());
		List<AdPraiseDTO> adPraiseDTOS = new ArrayList<>();
		for (AdDTO adDTO : screenList) {
			AdPraiseDTO praise = new AdPraiseDTO();
			praise.setId(adDTO.getId());
			praise.setTitle(adDTO.getTitle());
			praise.setBeginTime(adDTO.getBeginTime());
			praise.setTotalPoint(adDTO.getTotalPoint());
			if (adDTO.getNumber() == null) {
				praise.setCount(0);
			} else {
				praise.setCount(adDTO.getNumber());
			}
			praise.setNeedBeginTime(adDTO.getNeedBeginTime());
			praise.setMediaUrl(adDTO.getMediaUrl());
			praise.setIsPraise(adDTO.getIsPraise());
			praise.setMerchantId(adDTO.getMerchantId());
			praise.setCount(adDTO.getNumber());
			Result<Boolean> resultFavoriteAd = favoriteAdService.isFavoriteAd(adDTO.getId(), memberId);
			if (isSuccess(resultFavoriteAd)) {
				praise.setIsFavorite(resultFavoriteAd.getModel());
			}
			praise.setMerchantStoreId(adDTO.getMerchantStoreId());
			if (StringUtils.isNotEmpty(adDTO.getName())) {
				praise.setName(adDTO.getName());
			} else {
				praise.setName("E店商家");
			}
			if (StringUtils.isNotEmpty(adDTO.getLogoUrl())) {
				praise.setLogoUrl(adDTO.getLogoUrl());
			} else {
				praise.setLogoUrl(memberApiConfig.getDefaultHeadimg());
			}
			if (adDTO.getManageTypeEnum() != null) {
				praise.setManageTypeEnum(adDTO.getManageTypeEnum());
			}
			adPraiseDTOS.add(praise);
		}

		Page<AdPraiseDTO> newPage = new Page<>();
		newPage.setCurrentPage(pageDTOS.getModel().getCurrentPage());
		newPage.setTotalCount(newList.size());
		newPage.setRecords(adPraiseDTOS);
		return successGet(newPage);
	}

	@Override
	public Result<AdPraiseDTO> selectAbPraiseById(Long id) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<AdPraiseDTO> resultAd = adService.selectAdPraiseById(id, memberId);

		AdPraiseDTO praise = resultAd.getModel();
		if (StringUtils.isEmpty(praise.getName())) {
			praise.setName("E店商家");
		} else if(StringUtils.isEmpty(praise.getLogoUrl())) {
			praise.setLogoUrl(memberApiConfig.getDefaultHeadimg());
		}
		PointDetailQueryData1Param param = new PointDetailQueryData1Param();
		param.setBizId(id.toString());
		//param.setPointType(MemberTransactionTypeEnum.PRAISE_AD.getValue());
		param.setUserNum(userNum);
		Result<Integer> result = propertyInfoDataService.getPointDetailByUserNumAndPointTypeAndBizId(param);
		if (isSuccess(result)) {
			if (result.getModel() == 1){
				praise.setIsDoHanlderMinusPoint(true);
			}
			else{
				praise.setIsDoHanlderMinusPoint(false);
			}
		}
		praise.setClickPraiseAdTimes(memberApiConfig.getClickPraiseAdTimes());
		praise.setPraiseProb(memberApiConfig.getClickPraiseProb());
		List<String> list = new ArrayList<>();
		list.add(AdPraiseWords.WORD_A);
		list.add(AdPraiseWords.WORD_B);
		list.add(AdPraiseWords.WORD_C);
		list.add(AdPraiseWords.WORD_D);
		praise.setWords(list);
		return successGet(praise);
	}

	/**
	 * 公用方法广告过滤
	 *
	 * @param list
	 * @param adMemberParam
	 * @return
	 */
	public List<AdDTO> adFilterNoMemberId(AdMemberParam adMemberParam, List<AdDTO> list) {
		String memberPath = null;
		if(adMemberParam.getTransRegionPath()!=null){
			memberPath = adMemberParam.getTransRegionPath();
		}
		List<AdDTO> newList = new ArrayList<>();
		for (AdDTO adDTO : list) {
			if (adDTO.getPutWayEnum().val == 1) { // 区域
				if (adDTO.getAreas() == null || adDTO.getAreas() == "") {
					newList.add(adDTO);
				} else {
					if (memberPath != null) {
						String[] memberPaths = memberPath.split("/");
						String[] path = adDTO.getAreas().split(",");
						for (String s : path) {
							for (String mp : memberPaths) {
								if (s.equals(mp)) {
									newList.add(adDTO);
								}
								continue;
							}
						}
					}
				}
				
			} else if (adDTO.getPutWayEnum().val == 2) {//没有登录不考虑粉丝
				// 获取商家粉丝，判断当前用户是否属于商家粉丝
				/*Result<Boolean> rs = fansMerchantService.isFansMerchant(adDTO.getMerchantId(), memberId);
				if (rs.getModel()){
					newList.add(adDTO);
				}*/
			} else {
				if (adMemberParam.getLongitude() == null || adMemberParam.getLatitude() == null) {
					continue;
				} else {
					Result<MerchantStoreDTO> rs = merchantStoreService.selectMerchantStoreByMId(adDTO.getMerchantId());
					MerchantStoreDTO dto = rs.getModel();
					if (dto != null) {
						int distance = DistanceUtil.getDistance(adMemberParam.getLongitude(), adMemberParam.getLatitude(), dto.getLongitude().doubleValue(), dto.getLatitude().doubleValue());
						if (adDTO.getRadius() != null && adDTO.getRadius() > distance / 1000) {
							newList.add(adDTO);
						}
					}
				}
			}
			
		}
		return newList;
		
	}
	/**
	 * 公用方法广告过滤
	 *
	 * @param list
	 * @param memberId
	 * @return
	 */
	public List<AdDTO> adFilter(AdMemberParam adMemberParam, List<AdDTO> list, Long memberId) {
		String memberPath =null;
		
		if(adMemberParam!=null && adMemberParam.getTransRegionPath()!=null){
			memberPath = adMemberParam.getTransRegionPath();
		}
		List<AdDTO> newList = new ArrayList<>();
		for (AdDTO adDTO : list) {
			if (adDTO.getPutWayEnum().val == PutWayEnum.PUT_WAY_AREAS.val) { // 区域
				if (adDTO.getAreas() == null || adDTO.getAreas() == "") {
					newList.add(adDTO);
				} else {
					if (memberPath != null) {
						String[] memberPaths = memberPath.split("/");
						String[] path = adDTO.getAreas().split(",");
						for (String s : path) {
							for (String mp : memberPaths) {
								if (s.equals(mp)) {
									newList.add(adDTO);
								}
								continue;
							}
						}
					}
					
				}

			} else if (adDTO.getPutWayEnum().val == PutWayEnum.PUT_WAY_FENS.val) {
				// 获取商家粉丝，判断当前用户是否属于商家粉丝
				if(memberId != 0) {
					Result<Boolean> rs = fansMerchantService.isFansMerchant(adDTO.getMerchantId(), memberId);
					if (rs.getModel()){
						newList.add(adDTO);
					}
				}
			} else {
				if (adMemberParam.getLongitude() == null || adMemberParam.getLatitude() == null) {
					continue;
				} else {
					Result<MerchantStoreDTO> rs = merchantStoreService.selectMerchantStoreByMId(adDTO.getMerchantId());
					MerchantStoreDTO dto = rs.getModel();
					if (dto == null) {
						continue;
					}
					int distance = DistanceUtil.getDistance(adMemberParam.getLongitude(), adMemberParam.getLatitude(), dto.getLongitude().doubleValue(), dto.getLatitude().doubleValue());
					if (adDTO.getRadius() != null && adDTO.getRadius() > distance / 1000) {
						newList.add(adDTO);
					}
				}
			}

		}
		return newList;

	}
	
	@BusinessInventoryCtrl(idParamIndex = 0, businessKey = BusinessKey.AD_CLICK_KEY, using = AdBusinessDecisionServiceImpl.class, isLock = true)
	@Override
	public Result<PraisePointDTO> clickPraise(Long id,PraiseTypeEnum typeEnum,Long memberId,String num) {
		
		Result<PraisePointDTO> rs;
		try {
			if(typeEnum == PraiseTypeEnum.PRAISE_TYPE_PUZZLE){
				rs = adService.clickPraise(id, memberId, num);
			}else{
				Random random = new Random();
				Integer r = random.nextInt(memberApiConfig.getClickPraiseSumProb())
						% (memberApiConfig.getClickPraiseSumProb() + 1);
				if (r > 0 && r < memberApiConfig.getClickPraiseProb()) {
					rs = adService.clickPraise(id, memberId, num);
					
				} else {
					throw new BusinessExecuteException(ResultCode.AD_PRAISE_NOT_RATE,ResultCode.get(ResultCode.AD_PRAISE_NOT_RATE) );
				}
			}
			if (!isSuccess(rs)) {
				throw new BusinessExecuteException(rs.getRet(), rs.getMsg());
			}
			return rs;
		} catch (RejectedExecutionException e) {
			return successCreated(ResultCode.FAIL);
		}

	}

	@Deprecated
	@Override
	public Result<Page<AdDTO>> selectChoiceness(AdChoicenessParam adChoicenessParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());

		AdMemberParam param = new AdMemberParam();
		param.setCurrentPage(adChoicenessParam.getCurrentPage());
		param.setPageSize(adChoicenessParam.getPageSize());
		param.setLatitude(adChoicenessParam.getLatitude());
		param.setLongitude(adChoicenessParam.getLongitude());
		param.setTransRegionPath(adChoicenessParam.getTransRegionPath());
		Result<Page<AdDTO>> pageDTOS = adService.selectChoiceness(param);
		Page<AdDTO> newPage = new Page<>();
		List<AdDTO> newList =null;
		if(memberId==0){
			if(null==param.getTransRegionPath()){
				return successGet(newPage);
			}
			newList=adFilterNoMemberId(param, pageDTOS.getModel().getRecords());
		}else{
			newList=adFilter(param, pageDTOS.getModel().getRecords(), memberId);
		}
		AdPage<AdDTO> adpage = new AdPage<>();
		List<AdDTO> screenList = adpage.page(newList, param.getPageSize(), param.getCurrentPage());

		for (AdDTO adDTO : screenList) {
			Result<Boolean> resultFavoriteAd = favoriteAdService.isFavoriteAd(adDTO.getId(), memberId);
			if (isSuccess(resultFavoriteAd)) {
				adDTO.setIsFavorite(resultFavoriteAd.getModel());
			}
			adDTO.setMerchantStoreId(adDTO.getMerchantStoreId());
			if (StringUtils.isEmpty(adDTO.getName())) {
				adDTO.setName("E店商家");
			} 
			if (StringUtils.isEmpty(adDTO.getLogoUrl())) {
				adDTO.setLogoUrl(memberApiConfig.getDefaultHeadimg());
			} 
			
		}

		newPage.setCurrentPage(adChoicenessParam.getCurrentPage());
		newPage.setTotalCount(newList.size());
		newPage.setRecords(screenList);

		return successGet(newPage);
	}

	@Deprecated
	@Override
	public Result<Page<AdFlatVideoDTO>> selectEgainAd(AdEgainParam adEgainParam) {

		Long memberId = UserUtil.getCurrentUserId(getRequest());

		AdMemberParam param = new AdMemberParam();
		param.setCurrentPage(adEgainParam.getCurrentPage());
		param.setPageSize(adEgainParam.getPageSize());
		param.setTypeEnum(AdTypeEnum.getEnum(adEgainParam.getTypeEnum().getVal()));
		param.setLatitude(adEgainParam.getLatitude());
		param.setLongitude(adEgainParam.getLongitude());
		param.setTransRegionPath(adEgainParam.getTransRegionPath());
		Result<Page<AdDTO>> pageDTOS = adService.selectListByMember(param, memberId);

		List<AdDTO> newList = adFilter(param, pageDTOS.getModel().getRecords(), memberId);

		AdPage<AdDTO> adpage = new AdPage<>();
		List<AdDTO> screenList = adpage.page(newList, param.getPageSize(), param.getCurrentPage());

		List<AdFlatVideoDTO> egainList = new ArrayList<>();
		
		for (AdDTO adDTO : screenList) {
			AdFlatVideoDTO adFlatVideoDTO = new AdFlatVideoDTO();
			adFlatVideoDTO.setId(adDTO.getId());
			adFlatVideoDTO.setContent(adDTO.getContent());
			adFlatVideoDTO.setGmtCreate(adDTO.getGmtCreate());
			adFlatVideoDTO.setMediaUrl(adDTO.getMediaUrl());
			adFlatVideoDTO.setVideoImgUrl(adDTO.getVideoImgUrl());
			adFlatVideoDTO.setMerchantId(adDTO.getMerchantId());
			adFlatVideoDTO.setTitle(adDTO.getTitle());
			adFlatVideoDTO.setPutWayEnum(adDTO.getPutWayEnum());
			adFlatVideoDTO.setStatusEnum(adDTO.getStatusEnum());
			adFlatVideoDTO.setTypeEnum(adDTO.getTypeEnum());
			adFlatVideoDTO.setBeginTime(adDTO.getBeginTime());
			adFlatVideoDTO.setViewCount(adDTO.getViewCount());
			adFlatVideoDTO.setVideoImgUrl(adDTO.getVideoImgUrl());
			Result<Boolean> resultFavoriteAd = favoriteAdService.isFavoriteAd(adDTO.getId(), memberId);
			if (isSuccess(resultFavoriteAd)) {
				adFlatVideoDTO.setIsFavorite(resultFavoriteAd.getModel());
			}
			adDTO.setMerchantStoreId(adDTO.getMerchantStoreId());
			if (StringUtils.isNotEmpty(adDTO.getName())) {
				adFlatVideoDTO.setName(adDTO.getName());
			} else {
				adFlatVideoDTO.setName("E店商家");
			}
			if (StringUtils.isNotEmpty(adDTO.getLogoUrl())) {
				adFlatVideoDTO.setLogoUrl(adDTO.getLogoUrl());
			} else {
				adFlatVideoDTO.setLogoUrl(memberApiConfig.getDefaultHeadimg());
			}
			if (adDTO.getManageTypeEnum() != null) {
				adFlatVideoDTO.setManageTypeEnum(adDTO.getManageTypeEnum());
			}
			// 广告词
			Result<List<AdLexiconDTO>> adLexiconDTOS = adLexiconService.selectList(adDTO.getId());
			if (isSuccess(adLexiconDTOS)) {
				adFlatVideoDTO.setLexiconList(adLexiconDTOS.getModel());
			}
			Result<MerchantProfileDTO> mpRs = merchantProfileService.getMerchantProfile(adDTO.getMerchantId());
			if (isSuccess(mpRs)) {
				adFlatVideoDTO.setJdUrl(mpRs.getModel().getJdUrl());
				adFlatVideoDTO.setTaobaoUrl(mpRs.getModel().getTaobaoUrl());
				adFlatVideoDTO.setTmallUrl(mpRs.getModel().getTmallUrl());
				adFlatVideoDTO.setWebsiteUrl(mpRs.getModel().getWebsiteUrl());
			}
			egainList.add(adFlatVideoDTO);
		}
		Page<AdFlatVideoDTO> newPage = new Page<>();
		newPage.setCurrentPage(pageDTOS.getModel().getCurrentPage());
		newPage.setTotalCount(newList.size());
		newPage.setRecords(egainList);
		return successGet(newPage);
	}

	@BusinessInventoryCtrl(idParamIndex = 0, businessKey = BusinessKey.AD_CLICK_KEY, using = AdBusinessDecisionServiceImpl.class)
	@Override
	public Result<ClickAdPointDTO> clickAd(Long id, Long memberId, String num) throws BusinessExecuteException {

		Result<ClickAdPointDTO> result = adService.clickAd(id, memberId, num);

		if (!isSuccess(result)) {
			throw new BusinessExecuteException(result.getRet(), result.getMsg());
		}
		return result;
	}

	/**
	 * 查询E赚广告
	 *
	 * @param adEgainParam
	 * @return
	 * @author jiangxinjun
	 * @date 2017年7月18日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Result<Page<AdEgainQueryDTO>> selectEgain(AdEgainParam adEgainParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		// 获取查询广告所需要的信息
		Result<AdQueryMemberInfoDTO> adQueryMemberInfoResult = memberService.adQueryMemberInfo(memberId);
		if (!isSuccess(adQueryMemberInfoResult)) {
			return successGet(adQueryMemberInfoResult);
		}
		AdQueryMemberInfoDTO adQueryMemberInfoDTO = adQueryMemberInfoResult.getModel();
		AdEgainInternalParam adEgainInternalParam = new AdEgainInternalParam();
		adEgainInternalParam.setCurrentPage(adEgainParam.getCurrentPage());
		adEgainInternalParam.setLatitude(adEgainParam.getLatitude());
		adEgainInternalParam.setLongitude(adEgainParam.getLongitude());
		adEgainInternalParam.setPageSize(adEgainParam.getPageSize());
		adEgainInternalParam.setTypeEnum(adEgainParam.getTypeEnum());
		adEgainInternalParam.setMerchantIds(adQueryMemberInfoDTO.getFansList());
		if (StringUtils.isNotBlank(adQueryMemberInfoDTO.getRegionPath())) {
			adEgainInternalParam.setAreas(Arrays.asList(StringUtils.split(adEgainParam.getTransRegionPath(), "/")));
		}
		Result<Page<AdEgainQueryDTO>> result = adService.selectPageAdEgain(memberId, adEgainInternalParam);
		return successGet(result);
	}

    /**
     * 积分榜，人气榜查询
     *
     * @param adPointParam
     * @return
     * @author jiangxinjun
     * @date 2017年7月19日
     */
	@SuppressWarnings("unchecked")
	@Override
	public Result<List<AdPointDTO>> selectAdTop(AdPointForeignParam adPointParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		// 获取查询广告所需要的信息
		Result<AdQueryMemberInfoDTO> adQueryMemberInfoResult = memberService.adQueryMemberInfo(memberId);
		if (!isSuccess(adQueryMemberInfoResult)) {
			return successGet(adQueryMemberInfoResult);
		}
		AdQueryMemberInfoDTO adQueryMemberInfoDTO = adQueryMemberInfoResult.getModel();
		AdPointInternalParam adPointInternalParam = new AdPointInternalParam();
		adPointInternalParam.setLatitude(adPointParam.getLatitude());
		adPointInternalParam.setLongitude(adPointParam.getLongitude());
		adPointInternalParam.setOrderTypeEnum(adPointParam.getOrderTypeEnum());
		adPointInternalParam.setMerchantIds(adQueryMemberInfoDTO.getFansList());
		if (StringUtils.isNotBlank(adQueryMemberInfoDTO.getRegionPath())) {
			adPointInternalParam.setAreas(Arrays.asList(StringUtils.split(adQueryMemberInfoDTO.getRegionPath(), "/")));
		}
		Result<List<AdPointDTO>> result = adService.selectAdPoint(adPointInternalParam);
		return successGet(result);
	}

    /**
     * 查询精品推荐广告列表
     *
     * @param adChoicenessParam
     * @return
     * @author jiangxinjun
     * @date 2017年7月19日
     */
	@SuppressWarnings("unchecked")
	@Override
	public Result<Page<ChoicenessAdDTO>> selectChoicenessAd(AdChoicenessParam adChoicenessParam) {
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		// 获取查询广告所需要的信息
		Result<AdQueryMemberInfoDTO> adQueryMemberInfoResult = memberService.adQueryMemberInfo(memberId);
		if (!isSuccess(adQueryMemberInfoResult)) {
			return successGet(adQueryMemberInfoResult);
		}
		AdQueryMemberInfoDTO adQueryMemberInfoDTO = adQueryMemberInfoResult.getModel();
		AdChoicenessInternalParam adChoicenessInternalParam = new AdChoicenessInternalParam();
		adChoicenessInternalParam.setLatitude(adChoicenessParam.getLatitude());
		adChoicenessInternalParam.setLongitude(adChoicenessParam.getLongitude());
		adChoicenessInternalParam.setMerchantIds(adQueryMemberInfoDTO.getFansList());
		if (StringUtils.isNotBlank(adQueryMemberInfoDTO.getRegionPath())) {
			adChoicenessInternalParam.setAreas(Arrays.asList(StringUtils.split(adChoicenessParam.getTransRegionPath(), "/")));
		}
		Result<Page<ChoicenessAdDTO>> result = adService.selectChoiceness(memberId, adChoicenessInternalParam);
		return successGet(result);
	}

	

}
