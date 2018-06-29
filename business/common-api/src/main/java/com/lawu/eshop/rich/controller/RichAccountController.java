package com.lawu.eshop.rich.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.activity.dto.IdentityInfoDTO;
import com.lawu.eshop.activity.dto.PersonalRichAccountDTO;
import com.lawu.eshop.activity.dto.RichDetailDTO;
import com.lawu.eshop.activity.dto.RichMyDiamondRecordInfoDTO;
import com.lawu.eshop.activity.dto.RichPowerInfoRecordDTO;
import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.rich.service.CommonRichAccountService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author lihj
 * @date 2018/5/2
 */
@Api(tags = "richAccount")
@RestController
@RequestMapping("richAccount/")
public class RichAccountController extends BaseController{

	@Autowired
	private CommonRichAccountService commonRichAccountService;

    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @ApiOperation(value = "获取首页个人E钻信息", notes = "获取首页个人E钻信息(李洪军)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getPersonalRichAccountInfo", method = RequestMethod.GET)
    public Result<PersonalRichAccountDTO> getPersonalRichAccountInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@RequestParam @ApiParam(required = true, value = "用户类型") UserTypeEnum userType){
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        return commonRichAccountService.getPersonalRichAccountInfo(userNum,userType);
    }

    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @ApiOperation(value = "获取首页瑞奇岛信息", notes = "获取首页瑞奇岛信息(李洪军)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRichInfo", method = RequestMethod.GET)
    public Result<RichDetailDTO> getRichInfo(@RequestParam @ApiParam(required = true, value = "用户类型") UserTypeEnum userType){
    	RichDetailDTO dto = commonRichAccountService.getRichInfo(userType);
    	return successGet(dto);
    }

    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @ApiOperation(value = "获取瑞奇岛当前动力值以及动力收支明细TOP10", notes = "获取瑞奇岛当前动力值以及动力收支明细TOP10(李洪军)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRichPowerInfoRecord", method = RequestMethod.GET)
    public Result<RichPowerInfoRecordDTO> getRichPowerInfoRecord(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	return commonRichAccountService.getRichPowerInfoRecord(userNum);
    }

    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @ApiOperation(value = "我的数字资产(E钻总数和领取记录TOP10)", notes = "我的数字资产(E钻总数和领取记录TOP10)(李洪军)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMyRichDiamondRecordInfo", method = RequestMethod.GET)
    public Result<RichMyDiamondRecordInfoDTO> getMyRichDiamondRecordInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
    	String userNum = UserUtil.getCurrentUserNum(getRequest());
    	return commonRichAccountService.getMyRichDiamondRecordInfo(userNum);
    }

    @Audit(date = "2018-05-07", reviewer = "孙林青")
    @SuppressWarnings("unchecked")
    @Authorization
	@ApiOperation(value = "我的身份", notes = "我的身份(张荣成)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getIdentityInfo", method = RequestMethod.GET)
    public Result<IdentityInfoDTO> getIdentityInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token){
    	String memberNum = UserUtil.getCurrentUserNum(getRequest());
    	IdentityInfoDTO result = commonRichAccountService.getIdentityInfo(memberNum);
    	return successGet(result);
    	
    }

    @Audit(date = "2018-05-07", reviewer = "孙林青")
	@SuppressWarnings({ "rawtypes" })
    @Authorization
    @ApiOperation(value = "领取钻石", notes = "领取钻石[1024,1037,1100](蒋鑫俊)", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(method = RequestMethod.PUT, value = "receiveDiamonds")
    public Result receiveDiamonds(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @Validated ReceiveDiamondsParam param) {
        String memberNum = UserUtil.getCurrentUserNum(getRequest());
        return successCreated(commonRichAccountService.receiveDiamonds(memberNum, param));
    }
}
