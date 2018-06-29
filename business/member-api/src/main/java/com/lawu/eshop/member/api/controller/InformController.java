/**
 * 
 */
package com.lawu.eshop.member.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.param.InformParam;
import com.lawu.eshop.mall.param.InformSaveParam;
import com.lawu.eshop.member.api.service.InformService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 举报管理
 * @author lihj
 * @date 2017年7月27日
 */
@Api(tags="inform")
@RestController
@RequestMapping(value="inform/")
public class InformController extends BaseController{

	@Autowired
	private InformService informService;

	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@ApiOperation(value="添加举报信息",notes="添加举报信息(李洪军)",httpMethod="POST")
	@Authorization
	@ApiResponse(code=HttpCode.SC_CREATED,message="success")
	@RequestMapping(value="addInform",method=RequestMethod.POST)
	public Result addReport(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,@ModelAttribute @ApiParam(required=true,value="举报信息") InformParam param ){
		HttpServletRequest request =getRequest();
		if(param == null){
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		InformSaveParam info =new InformSaveParam();
		info.setContent(param.getContent());
		info.setGmtCreate(new Date());
		info.setGmtModified(new Date());
		info.setInformerAccount(UserUtil.getCurrentAccount(request));
		info.setInformerUserNum(UserUtil.getCurrentUserNum(request));
		info.setInformtItemId(param.getInformtItemId());
		info.setInformtItemName(param.getInformtItemName());
		info.setInformType(param.getInformType().getVal());
		info.setStatus((byte) 0);
		Result result = informService.addInform(info);
		return successCreated(result);
	}
	
}
