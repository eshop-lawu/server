package com.lawu.eshop.member.api.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.concurrentqueue.requestctrl.ConcurrentTaskExecutor;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketAttendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketUserDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.eshop.activity.param.HelpRedpacketAttendParam;
import com.lawu.eshop.activity.param.HelpRedpacketUserParam;
import com.lawu.eshop.common.constants.UserNameEnum;
import com.lawu.eshop.concurrentqueue.impl.AbstractBaseConcurrentTask;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.HelpRedpacketActivityService;
import com.lawu.eshop.member.api.service.HelpRedpacketAttendDetailService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.WeixinUserService;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.WeixinUserInfoDTO;
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
 * 活动详情接口
 * @Description
 * @author zhangrc
 * @date 2017年12月28日
 */
@Api(tags = "helpRedpacketAttendDetail", value = "活动详情")
@RestController
@RequestMapping(value = "helpRedpacketAttendDetail/")
public class HelpRedpacketAttendDetailController extends BaseController{
	
	@Autowired
	private HelpRedpacketAttendDetailService helpRedpacketAttendDetailService;
	
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
	
	@Autowired
	private WeixinUserService weixinUserService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private ConcurrentTaskExecutor concurrentTaskExecutor;
	
	@Autowired
	private MemberApiConfig memberApiConfig;

	@Audit(date = "2018-01-02", reviewer = "孙林青")
	@SuppressWarnings({ "unchecked", "deprecation" })
	@ApiOperation(value = "活动详情", notes = "活动详情（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	//@Authorization
	@RequestMapping(value = "helpRedpacketAttendDetail", method = RequestMethod.GET)
	public Result<HelpRedpacketAttendDTO> helpRedpacketAttendDetail(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam(name = "activityId", required= false) @ApiParam(value = "红包活动id", required = true) Integer activityId, @RequestParam(required = false) @ApiParam( value = "显示助力好友数目") Integer helpCount) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		if(userNum == null){
			userNum = "";
		}
		if(helpCount == null ){
			helpCount =10;
		}
		Result<HelpRedpacketAttendDTO> result = null;
		if (activityId == null) {
		    result = helpRedpacketAttendDetailService.helpRedpacketAttendDetail(userNum,helpCount);
		} else {
		    result = helpRedpacketAttendDetailService.helpRedpacketAttendDetail(activityId, userNum,helpCount);
		}
		result.getModel().setWeiChatSubScription(memberApiConfig.getWxSubScriptionUrl());
		return successGet(result);
	}

	@Audit(date = "2018-01-02", reviewer = "孙林青")
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@ApiOperation(value = "活动报名", notes = "活动报名[1100|9003|9004|9005]（张荣成）", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "helpRedpacketAttend", method = RequestMethod.POST)
	public Result helpRedpacketAttend(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam(name = "activityId", required= false) @ApiParam(value = "红包活动id", required = true) Integer activityId) {
		Result<RedpacketActivityInfoOfAttendDTO> infoOfAttendResult = null;
		if (activityId == null) {
		    infoOfAttendResult =  helpRedpacketActivityService.infoOfAttend();
		} else {
		    infoOfAttendResult =  helpRedpacketActivityService.infoOfAttend(activityId);
		}
		if (!isSuccess(infoOfAttendResult)) {
		    return successCreated(infoOfAttendResult);
		}
		HelpRedpacketAttendParam attendParam = new HelpRedpacketAttendParam();
		attendParam.setActivityId(activityId);
		if (RedPacketTypeEnum.WX.equals(infoOfAttendResult.getModel().getRedpacketType())) {
		    String userNum = UserUtil.getCurrentUserNum(getRequest());
    		//判断是否绑定微信公众号
    		Result<WeixinUserInfoDTO> userResult = weixinUserService.getWeixinUserInfo(userNum);
    		if(!isSuccess(userResult)){
    			return userResult;
    		}
    		WeixinUserInfoDTO weixinUserInfoDTO = userResult.getModel();
    		attendParam.setWxOpenid(weixinUserInfoDTO.getWxOpenid());
            if (StringUtils.isEmpty(weixinUserInfoDTO.getNickname())) {
                attendParam.setNickname(UserNameEnum.MEMBER.getName());
            } else {
                attendParam.setNickname(weixinUserInfoDTO.getNickname());
            }
		}
		Long memberId = UserUtil.getCurrentUserId(getRequest());
		Result<MemberDTO> findMemberInfoByIdResult =  memberService.findMemberInfoById(memberId);
		if(!isSuccess(findMemberInfoByIdResult)){
            return successCreated(findMemberInfoByIdResult);
        }
		MemberDTO memberDTO  = findMemberInfoByIdResult.getModel();
		if(memberDTO.getFreeze()){
			 return successCreated(ResultCode.ACCOUNT_IS_FREEZE);
		}
		attendParam.setAccount(memberDTO.getAccount());
		if(StringUtils.isEmpty(memberDTO.getHeadimg())){
			attendParam.setHeadimg(memberApiConfig.getDefaultHeadimg());
		}else{
			attendParam.setHeadimg(memberDTO.getHeadimg());
		}
		attendParam.setUserNum(memberDTO.getNum());
		Result<HelpRedpacketAttendDTO> result = helpRedpacketAttendDetailService.helpRedpacketAttend(attendParam);
		return successCreated(result);
	}

	@Audit(date = "2018-01-02", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "领取红包", notes = "领取红包[9006|9007]（张荣成）", httpMethod = "PUT")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@Authorization
	@RequestMapping(value = "getHelpRedpacket", method = RequestMethod.PUT)
	public Result<HelpRedpacketDTO> getHelpRedpacket(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @RequestParam(name = "activityId", required = false) @ApiParam(value = "红包活动id", required = true) Integer activityId) {
		String userNum = UserUtil.getCurrentUserNum(getRequest());
		Result<HelpRedpacketDTO> res ;
		res = (Result<HelpRedpacketDTO>) concurrentTaskExecutor.execute(new AbstractBaseConcurrentTask<Result<HelpRedpacketDTO>, Result<HelpRedpacketDTO>>() {
            @SuppressWarnings("deprecation")
            @Override
            public Result<HelpRedpacketDTO> execute() {
                if (activityId == null) {
                    return helpRedpacketAttendDetailService.getHelpRedpacket(userNum);
                } else {
                    return helpRedpacketAttendDetailService.getHelpRedpacket(activityId, userNum);
                }
            }
            
            @Override
            public Result<HelpRedpacketDTO> executeWhenSuccess(Result<HelpRedpacketDTO> successInfo) {
                return successInfo;
            }
        });
		
		return res;
	}

	@Audit(date = "2018-01-04", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "领取用户滚动", notes = "领取用户滚动（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getHelpRedpacketUser", method = RequestMethod.GET)
	public Result<HelpRedpacketUserDTO> getHelpRedpacketUser( @ModelAttribute @ApiParam(required = true, value = "查询信息") @Validated HelpRedpacketUserParam param, BindingResult bindingResult) {
		String message = validate(bindingResult);
		if (message != null) {
		    return successGet(ResultCode.REQUIRED_PARM_EMPTY, message);
		}
	    Result<Page<HelpRedpacketUserDTO>> result = helpRedpacketAttendDetailService.getHelpRedpacketUser(param);
		return successGet(result);
	}

}
