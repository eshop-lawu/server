package com.lawu.eshop.merchant.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.param.RichMerchantPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.MerchantPowerTaskTypeEnum;
import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.merchant.api.event.EventPublisher;
import com.lawu.eshop.merchant.api.service.FansInviteContentService;
import com.lawu.eshop.merchant.api.service.FansMerchantService;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.MerchantStoreService;
import com.lawu.eshop.merchant.api.service.MessageService;
import com.lawu.eshop.merchant.api.service.PropertyInfoService;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.constants.PropertyinfoFreezeEnum;
import com.lawu.eshop.property.dto.PropertyInfoFreezeDTO;
import com.lawu.eshop.property.dto.PropertyPointDTO;
import com.lawu.eshop.property.param.PropertyInfoDataParam;
import com.lawu.eshop.user.dto.FansMerchantDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.param.FansInviteContentExtendParam;
import com.lawu.eshop.user.param.InviteFansParam;
import com.lawu.eshop.user.param.InviteFansWithContentExtendParam;
import com.lawu.eshop.user.param.InviteFansWithContentParam;
import com.lawu.eshop.user.param.ListFansParam;
import com.lawu.eshop.user.param.ListInviteFansParam;
import com.lawu.eshop.user.param.ListInviteFansWithContentParam;
import com.lawu.eshop.user.param.PageListInviteFansParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/4/6.
 */
@Api(tags = "fansMerchant")
@RestController
@RequestMapping(value = "fansMerchant/")
public class FansMerchantController extends BaseController {

	@Autowired
	private FansMerchantService fansMerchantService;

	@Autowired
	private PropertyInfoService propertyInfoService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private MerchantStoreService merchantStoreService;
	
	@Autowired
	private FansInviteContentService fansInviteContentService;

	@Autowired
	private EventPublisher eventPublisher;

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "查询粉丝会员", notes = "查询可邀请成为粉丝的会员。[1100] (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "listInviteFans", method = RequestMethod.GET)
	public Result<List<FansMerchantDTO>> listInviteFans(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam ListInviteFansParam param) {
		long merchantId = UserUtil.getCurrentUserId(getRequest());
		return fansMerchantService.listInviteFans(merchantId, param);
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "分页查询粉丝会员", notes = "分页查询可邀请成为粉丝的会员。 (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "pageListInviteFans", method = RequestMethod.GET)
	public Result<Page<FansMerchantDTO>> pageListInviteFans(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam PageListInviteFansParam param) {
		long merchantId = UserUtil.getCurrentUserId(getRequest());
		return fansMerchantService.pageListInviteFans(merchantId, param);
	}

	@Deprecated
	@SuppressWarnings("rawtypes")
	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "邀请粉丝", notes = "邀请会员成为粉丝。[1002|1004|1022|1023|6002|6024] (梅述全)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "inviteFans", method = RequestMethod.POST)
	public Result inviteFans(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute InviteFansParam param) {
		if (StringUtils.isEmpty(param.getNums())) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		String[] numArray = param.getNums().split(",");
		int inviteFansCount = numArray.length;
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());

		Result<PropertyInfoFreezeDTO> resultFreeze = propertyInfoService.getPropertyinfoFreeze(userNum);
		if (isSuccess(resultFreeze)) {
			if (PropertyinfoFreezeEnum.YES.equals(resultFreeze.getModel().getStatus())) {
				return successCreated(ResultCode.PROPERTYINFO_FREEZE_YES);
			}
		} else {
			return successCreated(resultFreeze.getRet());
		}

		Result<Boolean> pwdResult = propertyInfoService.varifyPayPwd(userNum, param.getPayPwd());
		if (!isSuccess(pwdResult)) {
			return pwdResult;
		}
		if (!pwdResult.getModel()) {
			return successCreated(ResultCode.PAY_PWD_ERROR);
		}

		// 邀请粉丝扣除积分、插入粉丝邀请记录
		PropertyInfoDataParam propertyInfoDataParam = new PropertyInfoDataParam();
		propertyInfoDataParam.setUserNum(userNum);
		propertyInfoDataParam.setPoint(String.valueOf(inviteFansCount));
		propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INVITE_FANS);
		propertyInfoDataParam.setMerchantId(merchantId);
		propertyInfoDataParam.setRegionName(StringUtils.isEmpty(param.getRegionName()) ? "全国" : param.getRegionName());
		propertyInfoDataParam.setInviteFansCount(inviteFansCount);
		propertyInfoDataParam.setSex(param.getUserSexEnum().val);
		propertyInfoDataParam.setAge(param.getIsAgeLimit() ? param.getStartAge() + "-" + param.getEndAge() : "");
		Result result = propertyInfoService.inviteFans(propertyInfoDataParam);
		if (!isSuccess(result)) {
			return result;
		}

		Result<String> stringResult = merchantStoreService.getNameBymerchantId(merchantId);
		// 给会员发送站内消息
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(merchantId);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS);
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setMerchantName("E店商家");
		if (isSuccess(stringResult)) {
			messageTempParam.setMerchantName(stringResult.getModel());
		}
		for (String num : numArray) {
			Result<UserDTO> userDTOResult = memberService.getMemberByNum(num);
			messageTempParam.setUserName("E店会员");
			if (isSuccess(userDTOResult) && StringUtils.isNotEmpty(userDTOResult.getModel().getNickname())) {
				messageTempParam.setUserName(userDTOResult.getModel().getNickname());
			}
			messageInfoParam.setMessageParam(messageTempParam);
			messageService.saveMessage(num, messageInfoParam);
		}

		// 给商家发送站内消息
		Result<PropertyPointDTO> propertyPointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(merchantId);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS_MERCHANT);
		messageTempParam = new MessageTempParam();
		messageTempParam.setExpendPoint(new BigDecimal(inviteFansCount));
		messageTempParam.setPoint(propertyPointDTOResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(userNum, messageInfoParam);
		return successCreated();
	}

	@Audit(date = "2017-04-12", reviewer = "孙林青")
	@ApiOperation(value = "粉丝列表", notes = "商户粉丝列表。 (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "listFans", method = RequestMethod.GET)
	public Result<Page<FansMerchantDTO>> listFans(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam ListFansParam listFansParam) {
		long merchantId = UserUtil.getCurrentUserId(getRequest());
		return fansMerchantService.listFans(merchantId, listFansParam);
	}

	@Audit(date = "2017-04-21", reviewer = "孙林青")
	@ApiOperation(value = "粉丝数量", notes = "查询商家粉丝数量。 (梅述全)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "countFans", method = RequestMethod.GET)
	public Result<Integer> countFans(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
		long merchantId = UserUtil.getCurrentUserId(getRequest());
		return fansMerchantService.countFans(merchantId);
	}

	@Deprecated
	@Audit(date = "2017-08-18", reviewer = "李洪军")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "邀请粉丝(含邀请内容)", notes = "邀请会员成为粉丝。[1002|1004|1022|1023|6002|6024] (洪钦明)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "inviteFansWithContent", method = RequestMethod.POST)
	public Result inviteFansWithContent(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute InviteFansWithContentParam param) {
		if (StringUtils.isEmpty(param.getNums())) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		String[] numArray = param.getNums().split(",");
		int inviteFansCount = numArray.length;
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());

		Result<PropertyInfoFreezeDTO> resultFreeze = propertyInfoService.getPropertyinfoFreeze(userNum);
		if (isSuccess(resultFreeze)) {
			if (PropertyinfoFreezeEnum.YES.equals(resultFreeze.getModel().getStatus())) {
				return successCreated(ResultCode.PROPERTYINFO_FREEZE_YES);
			}
		} else {
			return successCreated(resultFreeze.getRet());
		}

		Result<Boolean> pwdResult = propertyInfoService.varifyPayPwd(userNum, param.getPayPwd());
		if (!isSuccess(pwdResult)) {
			return pwdResult;
		}
		if (!pwdResult.getModel()) {
			return successCreated(ResultCode.PAY_PWD_ERROR);
		}

		// 邀请粉丝扣除积分、插入粉丝邀请记录
		PropertyInfoDataParam propertyInfoDataParam = new PropertyInfoDataParam();
		propertyInfoDataParam.setUserNum(userNum);
		propertyInfoDataParam.setPoint(String.valueOf(inviteFansCount));
		propertyInfoDataParam.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.INVITE_FANS);
		propertyInfoDataParam.setMerchantId(merchantId);
		propertyInfoDataParam.setRegionName(StringUtils.isEmpty(param.getRegionName()) ? "全国" : param.getRegionName());
		propertyInfoDataParam.setInviteFansCount(inviteFansCount);
		propertyInfoDataParam.setSex(param.getUserSexEnum().val);
		propertyInfoDataParam.setAge(param.getIsAgeLimit() ? param.getStartAge() + "-" + param.getEndAge() : "");
		Result result = propertyInfoService.inviteFans(propertyInfoDataParam);
		if (!isSuccess(result)) {
			return result;
		}
		
		FansInviteContentExtendParam fansInviteContentExtendParam = new FansInviteContentExtendParam();
		fansInviteContentExtendParam.setFansInviteDetailId(Long.valueOf(result.getModel().toString()));
		fansInviteContentExtendParam.setInviteContent(param.getInviteContent());
		fansInviteContentExtendParam.setLogoUrl(param.getLogoUrl());
		fansInviteContentExtendParam.setMerchantId(merchantId);
		fansInviteContentExtendParam.setMerchantNum(userNum);
		fansInviteContentExtendParam.setMerchantStoreIntro(param.getMerchantStoreIntro());
		fansInviteContentExtendParam.setMerchantStoreName(param.getMerchantStoreName());
		fansInviteContentExtendParam.setUrl(param.getUrl());
		fansInviteContentExtendParam.setNums(param.getNums());
		result = fansInviteContentService.saveFansInviteContent(fansInviteContentExtendParam);
		if (!isSuccess(result)) {
			return result;
		}
		
		Result<String> stringResult = merchantStoreService.getNameBymerchantId(merchantId);
		// 给会员发送站内消息
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(Long.valueOf(result.getModel().toString()));
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS);
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setMerchantName("E店商家");
		if (isSuccess(stringResult)) {
			messageTempParam.setMerchantName(stringResult.getModel());
		}
		for (String num : numArray) {
			Result<UserDTO> userDTOResult = memberService.getMemberByNum(num);
			messageTempParam.setUserName("E店会员");
			if (isSuccess(userDTOResult) && StringUtils.isNotEmpty(userDTOResult.getModel().getNickname())) {
				messageTempParam.setUserName(userDTOResult.getModel().getNickname());
			}
			messageInfoParam.setMessageParam(messageTempParam);
			messageService.saveMessage(num, messageInfoParam);
		}
		// 给商家发送站内消息
		Result<PropertyPointDTO> propertyPointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(merchantId);
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS_MERCHANT);
		messageTempParam = new MessageTempParam();
		messageTempParam.setExpendPoint(new BigDecimal(inviteFansCount));
		messageTempParam.setPoint(propertyPointDTOResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(userNum, messageInfoParam);
		return successCreated();
	}



	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "邀请粉丝(含邀请内容,含邀请条件)", notes = "邀请会员成为粉丝。[1002|1022|1023|1035|6002|6010|6024] (洪钦明)", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@Authorization
	@RequestMapping(value = "inviteFansWithContentExtend", method = RequestMethod.POST)
	public Result inviteFansWithContentExtend(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute InviteFansWithContentExtendParam param) {
		Long merchantId = UserUtil.getCurrentUserId(getRequest());
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		ListInviteFansWithContentParam listInviteFansWithContentParam = new ListInviteFansWithContentParam();
		listInviteFansWithContentParam.setEndAge(param.getEndAge());
		listInviteFansWithContentParam.setInviteCount(param.getInviteCount());
		listInviteFansWithContentParam.setInviteType(param.getInviteTypeEnum().getVal());
		listInviteFansWithContentParam.setIsAgeLimit(param.getIsAgeLimit());
		listInviteFansWithContentParam.setNums(param.getNums());
		listInviteFansWithContentParam.setRegionPath(param.getRegionPath());
		listInviteFansWithContentParam.setStartAge(param.getStartAge());
		listInviteFansWithContentParam.setUserSexEnum(param.getUserSexEnum());
		Result<List<FansMerchantDTO>> fmtDTOlist = fansMerchantService.listInviteFansWithContent(merchantId, listInviteFansWithContentParam);
		if (fmtDTOlist.getModel() == null || fmtDTOlist.getModel().isEmpty()) {
			return successCreated(ResultCode.INVITE_FANS_NOT_EXIST);
		}
		StringBuilder sbId = new StringBuilder();
		StringBuilder sbNum = new StringBuilder();
		for (FansMerchantDTO dto : fmtDTOlist.getModel()) {
			sbId.append(dto.getMemberId()).append(",");
			sbNum.append(dto.getNum()).append(",");
		}
		String ids = sbId.substring(0, sbId.length() - 1);
		String nums = sbNum.substring(0, sbNum.length() - 1);
		int inviteFansCount = 0;
		if(listInviteFansWithContentParam.getInviteCount() != null && listInviteFansWithContentParam.getInviteCount() > 0) {
			inviteFansCount = listInviteFansWithContentParam.getInviteCount();
		} else {
			inviteFansCount = fmtDTOlist.getModel().size();
		}

		Result<Boolean> pwdResult = propertyInfoService.varifyPayPwd(userNum, param.getPayPwd());
		if (!isSuccess(pwdResult)) {
			return pwdResult;
		}
		if (!pwdResult.getModel()) {
			return successCreated(ResultCode.PAY_PWD_ERROR);
		}

		Result pointResult = propertyInfoService.validatePoint(userNum, String.valueOf(inviteFansCount));
		if (!isSuccess(pointResult)) {
			return pointResult;
		}

		FansInviteContentExtendParam fansInviteContentExtendParam = new FansInviteContentExtendParam();
		fansInviteContentExtendParam.setFansInviteDetailId(0L);
		fansInviteContentExtendParam.setInviteContent(param.getInviteContent());
		fansInviteContentExtendParam.setLogoUrl(param.getLogoUrl());
		fansInviteContentExtendParam.setMerchantId(merchantId);
		fansInviteContentExtendParam.setMerchantNum(userNum);
		fansInviteContentExtendParam.setMerchantStoreIntro(param.getMerchantStoreIntro());
		fansInviteContentExtendParam.setMerchantStoreName(param.getMerchantStoreName());
		fansInviteContentExtendParam.setUrl(param.getUrl());
		fansInviteContentExtendParam.setIds(ids);
		//事务消息参数
		fansInviteContentExtendParam.setRegionName(StringUtils.isEmpty(param.getRegionName()) ? "全国" : param.getRegionName());
		fansInviteContentExtendParam.setInviteFansCount(inviteFansCount);
		fansInviteContentExtendParam.setSex(param.getUserSexEnum().val);
		fansInviteContentExtendParam.setAge(param.getIsAgeLimit() ? param.getStartAge() + "-" + param.getEndAge() : "");
		Result result = fansInviteContentService.saveFansInviteExtendContent(fansInviteContentExtendParam);

		// 给商家发送站内消息
		Result<PropertyPointDTO> propertyPointDTOResult = propertyInfoService.getPropertyPoint(userNum);
		MessageInfoParam messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(Long.valueOf(result.getModel().toString()));
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS_MERCHANT);
		MessageTempParam messageTempParam = new MessageTempParam();
		messageTempParam.setExpendPoint(new BigDecimal(inviteFansCount));
		messageTempParam.setPoint(propertyPointDTOResult.getModel().getPoint().setScale(2, BigDecimal.ROUND_HALF_UP));
		messageInfoParam.setMessageParam(messageTempParam);
		messageService.saveMessage(userNum, messageInfoParam);

		// 给会员发送站内消息
		messageInfoParam = new MessageInfoParam();
		messageInfoParam.setRelateId(Long.valueOf(result.getModel().toString()));
		messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_INVITE_FANS);
		messageTempParam = new MessageTempParam();
		messageTempParam.setMerchantName("E店商家");
		if (StringUtils.isNotEmpty(param.getMerchantStoreName())) {
			messageTempParam.setMerchantName(param.getMerchantStoreName());
		}
		eventPublisher.publishInviteFansSendMessageEvent(messageInfoParam, messageTempParam, nums);

		//邀请粉丝触发动力任务
		RichMerchantPowerTaskRecordParam taskRecordParam = new RichMerchantPowerTaskRecordParam();
		taskRecordParam.setMerchantNum(userNum);
		taskRecordParam.setType(MerchantPowerTaskTypeEnum.INVITE_FENS);
		taskRecordParam.setFensInviteCount(inviteFansCount);
		eventPublisher.publishRichPowerTaskEvent(taskRecordParam);
		return successCreated();
	}
}
