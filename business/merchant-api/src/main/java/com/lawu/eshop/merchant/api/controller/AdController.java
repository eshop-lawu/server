package com.lawu.eshop.merchant.api.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.ad.constants.ClientTypeEnum;
import com.lawu.eshop.ad.constants.PutWayEnum;
import com.lawu.eshop.ad.dto.AdDetailDTO;
import com.lawu.eshop.ad.dto.AdMerchantDTO;
import com.lawu.eshop.ad.dto.AdMerchantDetailDTO;
import com.lawu.eshop.ad.dto.AdSaveInfoDTO;
import com.lawu.eshop.ad.dto.IsMyDateDTO;
import com.lawu.eshop.ad.dto.MemberCountDTO;
import com.lawu.eshop.ad.dto.PointGetDetailDTO;
import com.lawu.eshop.ad.param.AdMerchantParam;
import com.lawu.eshop.ad.param.AdParam;
import com.lawu.eshop.ad.param.AdSaveParam;
import com.lawu.eshop.ad.param.FensCountParam;
import com.lawu.eshop.ad.param.PointGetDetailParam;
import com.lawu.eshop.ad.param.UserRedpacketValue;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.common.constants.AdTypeEnum;
import com.lawu.eshop.common.constants.ManageTypeEnum;
import com.lawu.eshop.common.constants.UserNameEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.constants.MobileTypeEnum;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import com.lawu.eshop.merchant.api.service.AdCountCacheService;
import com.lawu.eshop.merchant.api.service.AdService;
import com.lawu.eshop.merchant.api.service.MemberCountService;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.merchant.api.service.ProductService;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.product.dto.ProductRelateAdInfoDTO;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MerchantStoreAdInfoDTO;
import com.lawu.eshop.user.param.AreasCountQuery;
import com.lawu.eshop.user.param.FensCountQuery;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.framework.web.util.HeaderUtil;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 描述：广告管理
 * @author zhangrc
 * @date 2017/04/5
 */
@Api(tags = "ad")
@RestController
@RequestMapping(value = "ad/")
public class AdController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(AdController.class);

    @Autowired
    private AdService adService;
    
    @Autowired
    private PropertyInfoService propertyInfoService;
    
    @Autowired
    private MemberCountService memberCountService;

    @Autowired
    private MerchantStoreService merchantStoreService;

    @Autowired
	private MerchantApiConfig merchantApiConfig;
    
    @Autowired
   	private ProductService productService;

    @Autowired
   	private AdCountCacheService adCountCacheService;
    
    @Autowired
    private MemberService memberService;
    
    
    @Autowired
    private EventPublisher eventPublisher;


	@Audit(date = "2017-08-08", reviewer = "孙林青")
    @Authorization
    @ApiOperation(value = "添加广告(2.4)", notes = "添加广告[1011|5000|5003|5010|5011|5012|6024|6026]（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "saveAdvert", method = RequestMethod.POST)
    public Result saveAdvert(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@ModelAttribute @ApiParam(required = true, value = "广告信息") AdParam adParam) {
    	Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	
    	if(adParam.getTypeEnum()!=AdTypeEnum.AD_TYPE_PACKET){
			if(StringUtils.isEmpty(adParam.getBeginTime()) || adParam.getBeginTime()==""){
				return successCreated(ResultCode.AD_BEGIN_TIME_NOT_EXIST);
			}
			if (adParam.getTypeEnum() == AdTypeEnum.AD_TYPE_FLAT || adParam.getTypeEnum() == AdTypeEnum.AD_TYPE_VIDEO) {

				if (adParam.getTotalPoint()
						.compareTo(adParam.getPoint().multiply(BigDecimal.valueOf(adParam.getAdCount()))) != 0) {
					return successCreated(ResultCode.AD_RED_PACKET_POINT_ERROR);
				}

				// 判断积分是否足够
				Result<PropertyInfoFreezeDTO> resultFreeze = propertyInfoService.getPropertyinfoFreeze(userNum);
				if (isSuccess(resultFreeze)) {
					if (PropertyinfoFreezeEnum.YES.equals(resultFreeze.getModel().getStatus())) {
						return successCreated(ResultCode.PROPERTYINFO_FREEZE_YES);
					}
				} else {
					return successCreated(resultFreeze.getRet());
				}
				Result<PropertyPointDTO> rs = propertyInfoService.getPropertyPoint(userNum);
				PropertyPointDTO propertyPointDTO = rs.getModel();

				if (adParam.getTotalPoint().intValue() > propertyPointDTO.getPoint().intValue()) {
					return successCreated(ResultCode.AD_POINT_NOT_ENOUGH);
				}
			}
			
		}else{
			
			if(adParam.getAdCount()>1000000){
				return successCreated(ResultCode.AD_RED_PACKET_COUNT_ERROR);
			}
			if(adParam.getTotalPoint().divide(new BigDecimal(adParam.getAdCount()), 2, RoundingMode.HALF_UP).compareTo(new BigDecimal(UserRedpacketValue.MIN_USERREDPACKET_COUNT))==-1){
				
				return successCreated(ResultCode.AD_RED_PACKET_POINT_ERROR,"投放金额不能低于"+new BigDecimal(adParam.getAdCount()).multiply(new BigDecimal(UserRedpacketValue.MIN_USERREDPACKET_COUNT)));
			}
			
		}
    	
    	Integer count=0;
    	if(adParam.getTypeEnum()==AdTypeEnum.AD_TYPE_PRAISE){
    		
    		if(adParam.getPutWayEnum()!=null && adParam.getPutWayEnum()==PutWayEnum.PUT_WAY_AREAS){
    			String areas=adParam.getAreas();
    			AreasCountQuery query = new AreasCountQuery();
    			query.setAreas(areas);
    			if (adParam.getSexEnum() != null) {
					query.setSexEnum(adParam.getSexEnum());
				}
				if (adParam.getMinAge() != null) {
					query.setMinAge(adParam.getMinAge());
				}
				if (adParam.getMaxAge() != null) {
					query.setMaxAge(adParam.getMaxAge());
				}
    			count=memberCountService.findMemberCount(query);
    			
    		}else if(adParam.getPutWayEnum()!=null && adParam.getPutWayEnum()==PutWayEnum.PUT_WAY_FENS){
    			FensCountQuery query = new FensCountQuery();
    			query.setMerchantId(merchantId);
				if (adParam.getSexEnum() != null) {
					query.setSexEnum(adParam.getSexEnum());
				}
				if (adParam.getMinAge() != null) {
					query.setMinAge(adParam.getMinAge());
				}
				if (adParam.getMaxAge() != null) {
					query.setMaxAge(adParam.getMaxAge());
				}
    			count=memberCountService.findFensCount(query);
        	}
    		
    		count =(int)Math.ceil(count * (merchantApiConfig.getAdPraiseAllotProb()));
			
    		count = count>10?count:10;
    		
    		if(adParam.getTotalPoint().divide(new BigDecimal(count), 2, RoundingMode.HALF_UP).compareTo(new BigDecimal(UserRedpacketValue.MIN_USERREDPACKET_COUNT))==-1){
    			return successCreated(ResultCode.AD_RED_PACKET_POINT_ERROR,"投放金额不能低于"+new BigDecimal(count).multiply(new BigDecimal(UserRedpacketValue.MIN_USERREDPACKET_COUNT)));
			}
    		adParam.setAdCount(count);
    		 
    	}
    	
    	Result<MerchantStoreAdInfoDTO> storeRs=merchantStoreService.selectMerchantStoreAdInfo(merchantId);
    	AdSaveParam adSave=new AdSaveParam();
    	adSave.setAdParam(adParam);
    	if(!isSuccess(storeRs)){
    		 return successCreated(storeRs.getRet());
    	}
    	String  platform = HeaderUtil.getRequestPlatform(getRequest());
		
		if(platform==""){
			return successCreated(ResultCode.GET_HEADER_ERROR);
		}
		if(Byte.valueOf(platform)==MobileTypeEnum.Android.val || Byte.valueOf(platform)==MobileTypeEnum.IOS.val){
			adSave.setClentType(ClientTypeEnum.MOBLIE);
		}else{
			adSave.setClentType(ClientTypeEnum.PC);
		}
    	MerchantStoreAdInfoDTO storeDTO= storeRs.getModel();
    	if(storeDTO!=null){
    		adSave.setLatitude(storeDTO.getLatitude());
        	adSave.setLongitude(storeDTO.getLongitude());
            adSave.setLogoUrl(StringUtils.isEmpty(storeDTO.getLogoUrl()) ? merchantApiConfig.getDefaultHeadimg() : storeDTO.getLogoUrl());
            if(storeDTO.getManageType()!=null){
        		adSave.setManageType(ManageTypeEnum.getEnum(storeDTO.getManageType().val));
        	}
        	adSave.setMerchantStoreId(storeDTO.getMerchantStoreId());
            adSave.setMerchantStoreName(StringUtils.isEmpty(storeDTO.getName()) ? "E店商家" : storeDTO.getName());
            adSave.setMerchantRegionPath(storeDTO.getRegionPath());
    	}
    	adSave.setMediaUrl(adParam.getMediaUrl());
    	adSave.setVideoImgUrl(adParam.getVideoImgUrl());
    	adSave.setMerchantId(merchantId);
    	adSave.setUserNum(userNum);
    	Result<AdSaveInfoDTO> result = adService.saveAd(adSave);

    	//将广告总数存入缓存
		if(isSuccess(result) && result.getModel().getId()>0){
		     adCountCacheService.setAdCountRecord(Long.parseLong(String.valueOf(result.getModel().getId())), result.getModel().getAdCount());
		}
		//获取动力任务
		RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
		taskRecordParam.setMerchantNum(userNum);
		taskRecordParam.setType(MerchantPowerTaskTypeEnum.AD);
		eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
		
        return result;
    }
    
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "广告列表", notes = "广告列表,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectListByMerchant", method = RequestMethod.GET)
    public Result<Page<AdMerchantDTO>> selectListByMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @ModelAttribute @ApiParam( value = "查询信息") AdMerchantParam adMerchantParam) {
    	Long memberId=UserUtil.getCurrentUserId(getRequest());
    	return adService.selectListByMerchant(adMerchantParam, memberId);
    }
    

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "广告操作下架", notes = "广告操作下架,[5001]（张荣成）", httpMethod = "PUT")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "unShelve/{id}", method = RequestMethod.PUT)
    public Result unShelve(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
    	Long merchantId=UserUtil.getCurrentUserId(getRequest());
    	Result<IsMyDateDTO> result=adService.isMyData(id, merchantId);
    	if(isSuccess(result)){
    		if(result.getModel().isFlag()){
    			return adService.updateStatus(id);
    		}else{
    			return successCreated(ResultCode.NOT_FOUND_DATA);
    		}
    	}else{
    		return successCreated(ResultCode.FAIL);
    	}

    }


    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "广告操作删除", notes = "广告操作删除,[]（张荣成）", httpMethod = "DELETE")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "remove/{id}", method = RequestMethod.DELETE)
    public Result remove(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
    	Long merchantId=UserUtil.getCurrentUserId(getRequest());
    	Result<IsMyDateDTO> result=adService.isMyData(id, merchantId);
    	if(isSuccess(result)){
    		if(result.getModel().isFlag()){
    			adService.remove(id);
    			return successDelete();
    		}else{
    			return successCreated(ResultCode.NOT_FOUND_DATA);
    		}
    	}else{
    		return successCreated(ResultCode.FAIL);
    	}


    }

	@Audit(date = "2017-05-03", reviewer = "孙林青")
    @ApiOperation(value = "广告再次投放", notes = "广告再次投放,[]（张荣成）", httpMethod = "POST")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "againPutAd/{id}", method = RequestMethod.POST)
    public Result againPutAd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
    		@PathVariable @ApiParam(required = true, value = "广告id") Long id,
    		@RequestParam @ApiParam(required = true, value = "广告投放时间") String beginTime) {
		Result<AdMerchantDetailDTO> rs= adService.selectById(id);
    	if(!isSuccess(rs)){
    		return successCreated(rs.getRet());
    	}
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	AdMerchantDetailDTO adDTO=rs.getModel();
    	if (adDTO.getTypeEnum() == AdTypeEnum.AD_TYPE_FLAT || adDTO.getTypeEnum() == AdTypeEnum.AD_TYPE_VIDEO) {
    		Result<PropertyPointDTO>  propertyPointRs=propertyInfoService.getPropertyPoint(userNum);
        	PropertyPointDTO propertyPointDTO=propertyPointRs.getModel();
        	
        	if(adDTO.getTotalPoint().intValue()>propertyPointDTO.getPoint().intValue()){
        		return successCreated(ResultCode.AD_POINT_NOT_ENOUGH);
        	}
    	}
    	
    	Result<MerchantStoreAdInfoDTO> storeRs=merchantStoreService.selectMerchantStoreAdInfo(merchantId);
    	AdSaveParam adSave=new AdSaveParam();
    	AdParam adParam=new AdParam();
    	adParam.setTitle(adDTO.getTitle());
    	adParam.setAdCount(adDTO.getAdCount());
    	adParam.setAreas(adDTO.getAreas());
    	adParam.setBeginTime(beginTime);
    	adParam.setContent(adDTO.getContent());
    	adParam.setPoint(adDTO.getPoint());
    	adParam.setTotalPoint(adDTO.getTotalPoint());
    	adParam.setPutWayEnum(adDTO.getPutWayEnum());
    	adParam.setRadius(adDTO.getRadius());
    	adParam.setTypeEnum(adDTO.getTypeEnum());
    	adParam.setRelateId(adDTO.getProductId());
    	adParam.setRelateType(adDTO.getRelateType());
    	adParam.setRegionName(adDTO.getRegionName());
    	adParam.setFileSize(adDTO.getFileSize());
    	adParam.setFileTime(adDTO.getVideoTime());
    	adParam.setPraiseType(adDTO.getPraiseType());
    	adParam.setSexEnum(adDTO.getSexEnum());
    	adParam.setMinAge(adDTO.getMinAge());
    	adParam.setMaxAge(adDTO.getMaxAge());
    	adSave.setAdParam(adParam);
    	if(isSuccess(storeRs)){
    		MerchantStoreAdInfoDTO storeDTO= storeRs.getModel();
        	if(storeDTO!=null){
        		adSave.setLatitude(storeDTO.getLatitude());
            	adSave.setLongitude(storeDTO.getLongitude());
                adSave.setLogoUrl(StringUtils.isEmpty(storeDTO.getLogoUrl()) ? merchantApiConfig.getDefaultHeadimg() : storeDTO.getLogoUrl());
                if(storeDTO.getManageType()!=null){
            		adSave.setManageType(ManageTypeEnum.getEnum(storeDTO.getManageType().val));
            	}
            	adSave.setMerchantStoreId(storeDTO.getMerchantStoreId());
                adSave.setMerchantStoreName(StringUtils.isEmpty(storeDTO.getName()) ? "E店商家" : storeDTO.getName());
                adSave.setMerchantRegionPath(storeDTO.getRegionPath());
        	}
    	}
    	String  platform = HeaderUtil.getRequestPlatform(getRequest());
    	if(platform==""){
			return successCreated(ResultCode.GET_HEADER_ERROR);
		}
		if(Byte.valueOf(platform)==MobileTypeEnum.Android.val || Byte.valueOf(platform)==MobileTypeEnum.IOS.val){
			adSave.setClentType(ClientTypeEnum.MOBLIE);
		}else{
			adSave.setClentType(ClientTypeEnum.PC);
		}
    	adSave.setMediaUrl(adDTO.getMediaUrl());
    	adSave.setVideoImgUrl(adDTO.getVideoImgUrl());
    	adSave.setMerchantId(merchantId);
    	adSave.setUserNum(userNum);
    	Result<AdSaveInfoDTO> result = adService.saveAd(adSave);

    	//将广告总数存入缓存
		if(isSuccess(result) && result.getModel().getId()>0){
		     adCountCacheService.setAdCountRecord(Long.parseLong(String.valueOf(result.getModel().getId())), result.getModel().getAdCount());
		}
		
		//获取动力任务
		RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
		taskRecordParam.setMerchantNum(userNum);
		taskRecordParam.setType(MerchantPowerTaskTypeEnum.AD);
		eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
    	return result;
	}

	@Audit(date = "2017-05-12", reviewer = "孙林青")
    @ApiOperation(value = "广告详情(再次投放回显)", notes = "广告详情,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectById/{id}", method = RequestMethod.GET)
    public Result<AdMerchantDetailDTO> selectById(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
		Result<AdMerchantDetailDTO> result = adService.selectById(id);
		if(!isSuccess(result)){
			return successCreated(result.getRet());
		}
		if(result.getModel().getProductId()!=null && result.getModel().getProductId() > 0){
			Result<ProductRelateAdInfoDTO>  proResult = productService.selectProductRelateAdInfo(result.getModel().getProductId());
			if(!isSuccess(proResult)){
				return successCreated(proResult.getRet());
			}
			result.getModel().setProductImgUrl(proResult.getModel().getImgUrl());
			result.getModel().setProductName(proResult.getModel().getName());
		}
		return result;
    }

	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-07-04", reviewer = "孙林青")
	@ApiOperation(value = "广告批量删除", notes = "广告批量删除,[]（张荣成）", httpMethod = "DELETE")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "batchDeleteAd", method = RequestMethod.DELETE)
	public Result batchDeleteAd(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "广告ids,中间以“/”隔开  如100/101") String ids) {
		String[] strIds = ids.split("/");
		List<Long> adIds = new ArrayList<>();
		for (String str : strIds) {
			adIds.add(Long.valueOf(str));
		}
		Long merchantId=UserUtil.getCurrentUserId(getRequest());
		Result rs = adService.batchDeleteAd(adIds,merchantId);
		return successDelete();
	}
	

	@Audit(date = "2017-07-04", reviewer = "孙林青")
    @ApiOperation(value = "广告详情", notes = "广告详情,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectDetail/{id}", method = RequestMethod.GET)
    public Result<AdDetailDTO> selectDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
		Result<AdDetailDTO> result = adService.selectDetail(id);
		if(!isSuccess(result)){
			return successCreated(result.getRet());
		}
		if (result.getModel().getProductId() != null && result.getModel().getProductId() > 0) {
			Result<ProductRelateAdInfoDTO>  proResult = productService.selectProductRelateAdInfo(result.getModel().getProductId());
			if(!isSuccess(proResult)){
				return successCreated(proResult.getRet());
			}
			result.getModel().setProductImgUrl(proResult.getModel().getImgUrl());
			result.getModel().setProductName(proResult.getModel().getName());
		}
		
		return result;
    }
	

	@Audit(date = "2017-10-20", reviewer = "杨清华")
    @ApiOperation(value = "广告是否支付成功", notes = "广告是否支付成功,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "isPay/{id}", method = RequestMethod.GET)
    public Result<Boolean> isPay(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@PathVariable @ApiParam(required = true, value = "广告id") Long id) {
		return adService.isPay(id);
    }

	@Audit(date = "2017-11-30", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "广告领取详情", notes = "广告领取详情,[]（张荣成）", httpMethod = "GET")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getDetailPage", method = RequestMethod.GET)
	Result<Page<PointGetDetailDTO>> getDetailPage(
			@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam(required = true, value = "领取参数") PointGetDetailParam param){
		 Result<Page<PointGetDetailDTO>>  result = adService.getDetailPage(param);
		 List<PointGetDetailDTO> list = result.getModel().getRecords();
		 
		 List<Long> memberIds = new ArrayList<>();
		 if(list.isEmpty()){
			 return successGet(result);
		 }
		 
		 for (PointGetDetailDTO pointGetDetailDTO : list) {
			 memberIds.add(pointGetDetailDTO.getMemberId());
		 }
		 Result<List<MemberDTO>>  mResult = memberService.getMemberByIds(memberIds);
		 
		 for (MemberDTO memberDTO : mResult.getModel()) {
			 
			 for (PointGetDetailDTO pointGetDetailDTO : list) {
				 if(memberDTO.getId().intValue()==pointGetDetailDTO.getMemberId().intValue()){
					 pointGetDetailDTO.setAccount(memberDTO.getMobile().replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
					 pointGetDetailDTO.setUrl(memberDTO.getHeadimg());
					 if(StringUtils.isNotEmpty(memberDTO.getNickname())){
						 StringUtil.anonymous(memberDTO.getNickname());
						 //pointGetDetailDTO.setName(memberDTO.getNickname().substring(0,1)+"**"+memberDTO.getNickname().substring(memberDTO.getNickname().length()-1,memberDTO.getNickname().length())); 
					 }else{
						 StringUtil.anonymous(UserNameEnum.MEMBER.getName());
					 }
					 
				 }
				 
			 }
			 
		 }
		 
		 return successGet(result);
	}


	@Audit(date = "2018-05-15", reviewer = "孙林青")
	@ApiOperation(value = "条件查询粉丝总数", notes = "条件查询粉丝总数,[]（张荣成）", httpMethod = "GET")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "findFensCount", method = RequestMethod.GET)
	public Result<MemberCountDTO> findFensCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute FensCountParam param) {
		Long merchantId=UserUtil.getCurrentUserId(getRequest());
		FensCountQuery query = new FensCountQuery();
		query.setMaxAge(param.getMaxAge());
		query.setMinAge(param.getMinAge());
		query.setSexEnum(param.getSexEnum());
		query.setMerchantId(merchantId);
		Integer count=memberCountService.findFensCount(query);
		MemberCountDTO countDTO = new MemberCountDTO();
		countDTO.setCount(count);
		return successGet(countDTO);
	}


	@Audit(date = "2018-05-15", reviewer = "孙林青")
	@ApiOperation(value = "条件查询用户总数", notes = "条件查询用户总数,[]（张荣成）", httpMethod = "GET")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "findMemberCount", method = RequestMethod.GET)
	public Result<MemberCountDTO> findMemberCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute AreasCountQuery param) {
		Integer count=memberCountService.findMemberCount(param);
		MemberCountDTO countDTO = new MemberCountDTO();
		countDTO.setCount(count);
		return successGet(countDTO);
	}

}
