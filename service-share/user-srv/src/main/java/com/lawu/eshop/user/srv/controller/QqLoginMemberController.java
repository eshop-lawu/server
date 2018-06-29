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
import com.lawu.eshop.user.dto.QqLoginMemberDTO;
import com.lawu.eshop.user.param.QqLoginMemberParam;
import com.lawu.eshop.user.srv.bo.QqLoginMemberBO;
import com.lawu.eshop.user.srv.converter.QqLoginMemberConverter;
import com.lawu.eshop.user.srv.service.InviteService;
import com.lawu.eshop.user.srv.service.MemberProfileService;
import com.lawu.eshop.user.srv.service.QqLoginMemberService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * QQ第三方登录
 * @Description
 * @author zhangrc
 * @date 2018年1月16日
 */
@RestController
@RequestMapping(value = "qqLoginMember/")
public class QqLoginMemberController extends BaseController{
	
	
	@Autowired
	private QqLoginMemberService qqLoginMemberService;
	
	@Autowired
	private  MemberProfileService memberProfileService;

    @Autowired
    private InviteService inviteService;
	
	
	private static Logger logger = LoggerFactory.getLogger(QqLoginMemberController.class);
	
	/**
	 * QQ登录
	 * @param openId
	 * @return
	 */
	@RequestMapping(value = "qqLogin", method = RequestMethod.GET)
	public Result<LoginUserDTO> qqLogin(@RequestParam String openId){
		
		QqLoginMemberBO qqLoginMemberBO = qqLoginMemberService.qqLogin(openId);
		
		if(qqLoginMemberBO ==  null){
			return successCreated(ResultCode.QQ_LOGIN_IS_NOT_BIND);
		}
		
		LoginUserDTO qqLoginMemberDTO = QqLoginMemberConverter.qqLoginMemberConverterDTO(qqLoginMemberBO);
		// 更新最后登录时间
		memberProfileService.updateLastLoginTime(qqLoginMemberDTO.getId());

		// 更新推荐关系
		inviteService.updateInviteUserProfileInfo(qqLoginMemberDTO.getNum(), qqLoginMemberDTO.getId());
		return successGet(qqLoginMemberDTO);
		
	}
	
	/**
	 * QQ账户绑定
	 * @param openId
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "qqMemberBind", method = RequestMethod.POST)
	public Result qqMemberBind(@RequestParam String openId, @RequestParam String account, @RequestParam String imgUrl){
		try {
			qqLoginMemberService.qqMemberBind(openId,account,imgUrl);
			return successCreated();
			
	    } catch (DataNotExistException e) {
	         logger.error(e.getMessage(), e);
	         return successCreated(ResultCode.QQ_LOGIN_IS_NOT_BIND);
	    }
		
	}

	/**
	 * QQ用户信息保存
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "qqLoginMemberSave", method = RequestMethod.POST)
	public Result  qqLoginMemberSave(@RequestBody QqLoginMemberParam param){
		
		qqLoginMemberService.QqLoginMemberSave(param);
		
		return successCreated();
	}
	
	@RequestMapping(value = "getHeadImg", method = RequestMethod.GET)
	public Result<String> getHeadImg(@RequestParam String openId){
		String img = qqLoginMemberService.getHeadImg(openId);
		return successGet(img);
	}

}
