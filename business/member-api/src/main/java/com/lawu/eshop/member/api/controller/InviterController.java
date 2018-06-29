package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.service.InviterService;
import com.lawu.eshop.member.api.service.MemberProfileService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.MerchantInviterService;
import com.lawu.eshop.user.dto.EFriendInviterDTO;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.InviteeMechantCountDTO;
import com.lawu.eshop.user.dto.InviteeMemberCountDTO;
import com.lawu.eshop.user.dto.InviterDTO;
import com.lawu.eshop.user.dto.MerchantInviterDTO;
import com.lawu.eshop.user.param.EFriendQueryDataParam;
import com.lawu.eshop.user.param.EFriendQueryParam;
import com.lawu.eshop.user.param.MemberQuery;
import com.lawu.eshop.user.param.MerchantInviterParam;
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
 * @author zhangrc
 * @date 2017/3/27
 */
@Api(tags = "inviter")
@RestController
@RequestMapping(value = "inviter/")
public class InviterController extends BaseController {

    @Autowired
    private MerchantInviterService merchantInviterService;

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private InviterService inviterService;
    
    @Autowired
    private MemberProfileService memberProfileService; 
    
    /**
     * 我的商家推荐总数量
     * @param token
     * @return
    */
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "我的商家推荐总数量", notes = "我的商家推荐总数量,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMerchantCount", method = RequestMethod.GET)
    public Result<InviteeMechantCountDTO> getMerchantCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
	   Long id=UserUtil.getCurrentUserId(getRequest());
	   Result<InviteeMechantCountDTO>  count=memberProfileService.getMerchantCount(id);
	   return count;
    }
    
    /**
	 * 我的E友总数量
	 * @return
	 */
    @Audit(date = "2017-04-01", reviewer = "孙林青")
	@ApiOperation(value = "我的E友总数量", notes = "我的E友总数量,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getMemberCount", method = RequestMethod.GET)
    public Result<InviteeMemberCountDTO> getMemberCount(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
	   Long id=UserUtil.getCurrentUserId(getRequest());
	   Result<InviteeMemberCountDTO>  count=memberProfileService.getMemberCount(id);
	   return count;
    }
	
	/**
	 * 我推荐的商家
	 * @param token
	 * @param pageQuery
	 * @return
	 */
	@Deprecated
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "我推荐的商家", notes = "我推荐的商家查询,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectInviteeMerchant", method = RequestMethod.GET)
    public Result<Page<MerchantInviterDTO>> selectInviteeMerchant(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                 @ModelAttribute @ApiParam( value = "查询信息") MerchantInviterParam pageQuery) {
    	Long userId=UserUtil.getCurrentUserId(getRequest());
    	Byte memberType=1;
    	Result<Page<MerchantInviterDTO>>  pageDTOS=merchantInviterService.getMerchantByInviter(userId,pageQuery,memberType);
    	return pageDTOS;
    }
    
    /**
     * 我的E友
     * @param token
     * @param query
     * @return
     */
    @Deprecated
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "我的E友", notes = "我的E有查询,[]（张荣成）", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectInviterMember", method = RequestMethod.GET)
    public Result<Page<EfriendDTO>> findMemberListByUser(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true, value = "查询信息") MemberQuery query) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        Byte inviterType=1;
        Result<Page<EfriendDTO>> page = memberService.findMemberListByUser(userId, query,inviterType);
        return page;
    }

    @AutoTesting
    @Audit(date = "2017-04-01", reviewer = "孙林青")
    @ApiOperation(value = "查询邀请人", notes = "根据账号查询邀请人信息。[1002] (梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getInviter/{account}", method = RequestMethod.GET)
    public Result<InviterDTO> getInviterByAccount(@PathVariable @ApiParam(required = true, value = "邀请人账号") String account) {
        return inviterService.getInviterByAccount(account);
    }

    /**
     * 我的E友
     * @param token
     * @param param
     * @since since v2.3.0
     * @return
     */
    @Audit(date = "2017-08-01", reviewer = "孙林青")
    @ApiOperation(value = "我的E友", notes = "我的E友，[](杨清华)", httpMethod = "GET")
    @Authorization
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "selectEFriend", method = RequestMethod.GET)
    public Result<Page<EFriendInviterDTO>> selectEFriend(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true, value = "查询信息") EFriendQueryParam param) {
        Long userId = UserUtil.getCurrentUserId(getRequest());
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        EFriendQueryDataParam dataParam = new EFriendQueryDataParam();
        dataParam.setUserId(userId);
        dataParam.setUserNum(userNum);
        dataParam.setQueryContent(param.getQueryContent());
        dataParam.setTypeEnum(param.getTypeEnum());
        dataParam.setCurrentPage(param.getCurrentPage());
        dataParam.setPageSize(param.getPageSize());
        Result<Page<EFriendInviterDTO>> page = inviterService.selectEFriend(dataParam);
        return page;
    }
}
