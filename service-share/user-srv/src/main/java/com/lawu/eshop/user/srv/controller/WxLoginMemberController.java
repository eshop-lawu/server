package com.lawu.eshop.user.srv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.dto.LoginUserDTO;
import com.lawu.eshop.user.param.WxLoginMemberParam;
import com.lawu.eshop.user.srv.bo.WxLoginMemberBO;
import com.lawu.eshop.user.srv.converter.WxLoginMemberConverter;
import com.lawu.eshop.user.srv.service.InviteService;
import com.lawu.eshop.user.srv.service.MemberProfileService;
import com.lawu.eshop.user.srv.service.WxLoginMemberService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 微信第三方登录
 *
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
@RestController
@RequestMapping(value = "wxLoginMember/")
public class WxLoginMemberController extends BaseController{

	@Autowired
	private WxLoginMemberService wxLoginMemberService;

	@Autowired
	private  MemberProfileService memberProfileService;

    @Autowired
    private InviteService inviteService;


	private static Logger logger = LoggerFactory.getLogger(WxLoginMemberController.class);

	/**
	 * 微信登录
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "wxLogin", method = RequestMethod.GET)
	public Result<LoginUserDTO> wxLogin(@RequestParam String openId){

		WxLoginMemberBO wxLoginMemberBO = wxLoginMemberService.wxLogin(openId);

		if(wxLoginMemberBO ==  null){
			return successCreated(ResultCode.WEI_XIN_LOGIN_IS_NOT_BIND);
		}

		LoginUserDTO wxLoginMemberDTO = WxLoginMemberConverter.wxLoginMemberConverterDTO(wxLoginMemberBO);

		//更新最后登录时间
		memberProfileService.updateLastLoginTime(wxLoginMemberDTO.getId());

        //更新推荐关系
        inviteService.updateInviteUserProfileInfo(wxLoginMemberDTO.getNum(),wxLoginMemberDTO.getId());

		return successGet(wxLoginMemberDTO);

	}

	/**
	 * 微信账户绑定
	 * @param openId
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "wxMemberBind", method = RequestMethod.POST)
	public Result wxMemberBind(@RequestParam String openId, @RequestParam String account,@RequestParam String imgUrl){
		try {
			wxLoginMemberService.wxMemberBind(openId,account,imgUrl);
			return successCreated();

	    } catch (DataNotExistException e) {
	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.WEI_XIN_LOGIN_IS_NOT_BIND);
	    }

	}

	/**
	 * 获取微信图像
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "getHeadImg", method = RequestMethod.GET)
	public Result<String> getHeadImg(@RequestParam String openId){
		String img = wxLoginMemberService.getHeadImg(openId);
		return successGet(img);
	}


	/**
	 * 微信用户信息保存
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "wxLoginMemberSave", method = RequestMethod.POST)
	public Result  wxLoginMemberSave(@RequestBody WxLoginMemberParam param){

		wxLoginMemberService.wxLoginMemberSave(param);

		return successCreated();
	}


}
