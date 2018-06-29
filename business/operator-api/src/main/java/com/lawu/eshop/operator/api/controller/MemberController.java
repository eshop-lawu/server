package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.manager.TokenCacheService;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.framework.web.impl.constants.UserTypeEnum;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.FreezeService;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.constants.FreezeTypeEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.param.AccountFreezeParam;
import com.lawu.eshop.user.param.AccountParam;
import com.lawu.eshop.user.param.FreezeParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/5/16.
 */
@Api(tags = "member")
@RestController
@RequestMapping(value = "member/")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    @Qualifier("memberTokenCacheService")
    private TokenCacheService memberTokenCacheService;

    @Autowired
    @Qualifier("merchantTokenCacheService")
    private TokenCacheService merchantTokenCacheService;

    @Autowired
    private FreezeService freezeService;

    @ApiOperation(value = "根据会员账号查询会员信息", notes = "根据会员账号查询会员信息。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    //@RequiresPermissions("index:store")
    @RequestMapping(value = "getMember/{account}", method = RequestMethod.GET)
    public Result<MemberDTO> getMember(@PathVariable @ApiParam(value = "会员账号") String account) {
        return memberService.getMemberByAccount(account);
    }

    @ApiOperation(value = "根据会员ID查询会员信息", notes = "根据会员ID查询会员信息。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("account:detail")
    @RequestMapping(value = "getMemberById/{id}", method = RequestMethod.GET)
    public Result<MemberDTO> getMemberById(@PathVariable @ApiParam(value = "会员账号") Long id) {
        return memberService.findMember(id);
    }

    @ApiOperation(value = "根据会员账号查询会员列表（商家，会员）", notes = "根据会员账号查询会员列表（商家，会员）（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getAccountList", method = RequestMethod.GET)
    @PageBody
    public Result<Page<AccountDTO>> getAccountList(@ModelAttribute AccountParam param) {
        if (UserTypeEnum.MEMBER.equals(param.getUserType())) {
            //用户
            return memberService.getAccountList(param);
        } else {
            return merchantService.getAccountList(param);
        }
    }

    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "冻结账户", notes = "冻结账户（商家，会员）（章勇）", httpMethod = "POST")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.ACCOUNT_FREEZE, idParamName="param.id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("account:freezeOper")
    @RequestMapping(value = "freezeAccount", method = RequestMethod.POST)
    public Result freezeAccount(@ModelAttribute AccountFreezeParam param) {
        FreezeParam freezeParam = new FreezeParam();
        freezeParam.setNum(param.getNum());
        freezeParam.setFreezeReason(param.getFreezeReason());
        freezeParam.setFreezeType(FreezeTypeEnum.BACKGROUND_FREEZE);
        freezeParam.setIsFreeze(param.getIsFreeze());
        return successCreated(freezeService.freeze(freezeParam));
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "强制退出", notes = "强制退出（蒋鑫俊）", httpMethod = "PUT")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.FORCED_EXIT)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("account:freezeOper")
    @RequestMapping(value = "forcedExit", method = RequestMethod.PUT)
    public Result forcedExit(@RequestParam("userType") UserTypeEnum userType, @RequestParam("account") String account) {
        return successCreated(freezeService.forcedExit(userType, account));
    }
}
