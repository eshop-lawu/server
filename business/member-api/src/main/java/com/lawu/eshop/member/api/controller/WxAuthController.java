package com.lawu.eshop.member.api.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.mall.dto.VerifyCodeDTO;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.AuthService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.VerifyCodeService;
import com.lawu.eshop.member.api.service.WeixinUserService;
import com.lawu.eshop.member.api.service.WxLoginMemberService;
import com.lawu.eshop.user.constants.WxBindTypeEnum;
import com.lawu.eshop.user.dto.ThirdLoginAuthDTO;
import com.lawu.eshop.user.param.WeixinUserParam;
import com.lawu.eshop.user.param.WxLoginMemberParam;
import com.lawu.eshop.user.param.WxMemberBindParam;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.fastdfs.ClientParams;
import com.lawu.media.util.UploadParam;
import com.lawu.media.util.WxUploadUtil;
import com.lawu.media.util.upload.FileUploadTypeEnum;
import com.lawu.utils.DateUtil;
import com.lawu.weixinapi.dto.WeixinUserDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author Leach
 * @date 2018/1/2
 */
@Api(tags = "wxAuth", value = "微信授权接口")
@RestController
@RequestMapping(value = "wxAuth/")
public class WxAuthController extends BaseController {

    private HttpClient httpclient = HttpClients.createDefault();

    @Autowired
    private MemberApiConfig memberApiConfig;

    @Autowired
    private WeixinUserService weixinUserService;
    
    @Autowired
    private WxLoginMemberService wxLoginMemberService;
    
    @Autowired
    private VerifyCodeService verifyCodeService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private AuthService authService;

    @Audit(date = "2018-01-02", reviewer = "孙林青")
    @ApiOperation(value = "H5授权重定向", notes = "调整到微信服务器，授权完之后跳转回来(孙林青)", httpMethod = "POST")
    @RequestMapping(value = "redirect")
    public ModelAndView redirect(@RequestParam @ApiParam(name = "redirectURL", required = true, value = "授权之后回跳的地址") String redirectURL) throws UnsupportedEncodingException {
        String authUrl = String.format(memberApiConfig.getWxAuthRedirectUrl(),
                memberApiConfig.getWxAuthAppKey(),
                URLEncoder.encode(memberApiConfig.getMemberH5Ip() + redirectURL, "utf-8"),
                "snsapi_userinfo", 1);
        return createMav("redirect:" + authUrl, null);
    }

    @Audit(date = "2018-01-02", reviewer = "孙林青")
    @ApiOperation(value = "获取微信用户信息", notes = "获取微信用户信息 (梅述全)", httpMethod = "GET")
    @RequestMapping(value = "getWxUser", method = RequestMethod.GET)
    public Result<WeixinUserDTO> getWxUser(@RequestParam @ApiParam(name = "code", required = true, value = "code") String code) throws IOException {
        return getUser(WxBindTypeEnum.H5_BIND, code);
    }

	@SuppressWarnings("unchecked")
	@Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "获取微信登录用户信息", notes = "获取微信用户信息 (张荣成)", httpMethod = "GET")
	@RequestMapping(value = "getWxLoginUser", method = RequestMethod.GET)
	public Result<WeixinUserDTO> getWxLoginUser(
			@RequestParam @ApiParam(name = "code", required = true, value = "code") String code) throws IOException {
    	
		Result<WeixinUserDTO> result = authService.getAuthUserInfo(code);
		if(!isSuccess(result)){
			return successCreated(result.getRet());
		}
		WeixinUserDTO userDTO = result.getModel();
		
		WxLoginMemberParam param = new WxLoginMemberParam();
		param.setCity(userDTO.getCity());
		param.setCountry(userDTO.getCountry());
		param.setHeadimgurl(userDTO.getHeadimgurl());
		param.setGroupid(userDTO.getGroupid());
		param.setLanguage(userDTO.getLanguage());
		param.setNickname(userDTO.getNickname());
		param.setOpenid(userDTO.getOpenid());
		param.setProvince(userDTO.getProvince());
		param.setRemark(userDTO.getRemark());
		param.setSex(userDTO.getSex());
		param.setSubscribe(userDTO.getSubscribe());
		param.setSubscribeTime(userDTO.getSubscribeTime());
		param.setTagidList(userDTO.getTagidList());
		param.setUnionid(userDTO.getUnionid());
		
		wxLoginMemberService.wxLoginMemberSave(param);
		
		return successGet(result);
	}
    
    private Result<WeixinUserDTO> getUser(WxBindTypeEnum type,String code) throws ParseException, IOException{
    	
    	String useInfoUrl = String.format(memberApiConfig.getWxAuthGetUserInfoUrl(), memberApiConfig.getWxAuthAppKey(),
				code);
		HttpGet httpGet = new HttpGet(useInfoUrl);
		HttpResponse response = this.httpclient.execute(httpGet);
		String resJson = EntityUtils.toString(response.getEntity(), "utf-8");
		if (StringUtils.isEmpty(resJson)) {
			return successGet(ResultCode.FAIL);
		}
		JSONObject json = JSON.parseObject(resJson);
		if (json.getInteger("ret") != ResultCode.SUCCESS) {
			return successGet(ResultCode.FAIL);
		}

		JSONObject model = JSON.parseObject(json.get("model").toString());
		WeixinUserParam param = new WeixinUserParam();
		param.setOpenid(model.getString("openid"));
		param.setNickname(model.getString("nickname"));
		param.setSex(model.getInteger("sex"));
		param.setCity(model.getString("city"));
		param.setProvince(model.getString("province"));
		param.setCountry(model.getString("country"));
		param.setHeadimgurl(model.getString("headimgurl"));
		param.setLanguage(model.getString("language"));
		param.setUnionid(model.getString("unionid"));
		param.setRemark(model.getString("remark"));
		param.setGroupid(model.getLong("groupid"));
		param.setSubscribe(model.getBoolean("subscribe"));
		param.setTagidList(model.getString("tagidList"));
		param.setSubscribeTime(model.get("subscribeTime") == null ? null
				: DateUtil.getDateTimeFormat(model.getString("subscribeTime")));
		param.setTypeEnum(type);
		
		weixinUserService.saveWeixinUser(param);

		// 请求useInfoUrl获取微信用户json信息，解析为WeixinUserDTO
		WeixinUserDTO weixinUserDTO = new WeixinUserDTO();
		weixinUserDTO.setOpenid(param.getOpenid());
		return successGet(weixinUserDTO);
    }


	@Audit(date = "2018-01-17", reviewer = "孙林青")
    @ApiOperation(value = "获取appid", notes = "配置在服务端获取(张荣成)", httpMethod = "GET")
	@RequestMapping(value = "getAppId")
	public Result<ThirdLoginAuthDTO> getAppId(){
		ThirdLoginAuthDTO dto = new ThirdLoginAuthDTO();
		dto.setWxAppId(memberApiConfig.getWxMpAppid());
		dto.setQqAppId(memberApiConfig.getQqAuthAppKey());
		return successGet(dto); 
	}


	@Audit(date = "2018-01-17", reviewer = "孙林青")
	@ApiOperation(value = "微信绑定账户", notes = "微信绑定账户 [1027|1013|1016|1025|2025](张荣成)", httpMethod = "POST")
	@RequestMapping(value = "wxMemberBind/{verifyCodeId}")
	public Result wxMemberBind(@PathVariable @ApiParam(required = true, value = "手机验证码ID") Long verifyCodeId,
			@ModelAttribute @ApiParam  WxMemberBindParam bindParam){
		
		Result<VerifyCodeDTO> smsResult = verifyCodeService.verifySmsCode(verifyCodeId, bindParam.getSmsCode());
		if (!isSuccess(smsResult)) {
			return successGet(ResultCode.VERIFY_SMS_CODE_FAIL);
		}
		VerifyCodeDTO verifyCodeDTO = smsResult.getModel();
		if (!bindParam.getAccount().equals(verifyCodeDTO.getMobile())) {
			return successGet(ResultCode.NOT_SEND_SMS_MOBILE);
		}
		if (DateUtil.smsIsOverdue(verifyCodeDTO.getGmtCreate(), memberApiConfig.getSmsValidMinutes())) {
			return successGet(ResultCode.VERIFY_SMS_CODE_OVERTIME);
		}
		
		Result<String> resultImg = wxLoginMemberService.getHeadImg(bindParam.getOpenId());
		
		UploadParam uparam = new UploadParam();
		uparam.setBaseImageDir(memberApiConfig.getImageUploadUrl());
		uparam.setDir(FileDirConstant.DIR_HEAD);
		uparam.setFileUploadTypeEnum(FileUploadTypeEnum.IMG);
		ClientParams cp = new ClientParams();
		cp.setTrackerServer(memberApiConfig.getTrackerServers());
		cp.setTrackerHttpPort(memberApiConfig.getTrackerHttpPort());
		uparam.setCparam(cp);
		
		Result accountResult = memberService.getMemberByAccount(bindParam.getAccount());
		String imgUrl="";
		
		if(!isSuccess(accountResult)){
			try {
				imgUrl = WxUploadUtil.upload(resultImg.getModel(), uparam);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Result result = wxLoginMemberService.wxMemberBind(bindParam.getOpenId(),bindParam.getAccount(),imgUrl);
		
		return successCreated(result); 
	}
	

}
