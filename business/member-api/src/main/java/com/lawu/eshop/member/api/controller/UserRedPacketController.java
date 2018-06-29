/**
 *
 */
package com.lawu.eshop.member.api.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.lawu.autotest.client.AutoTesting;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedPacketAddReturnDTO;
import com.lawu.eshop.ad.dto.UserRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedPacketReturnDTO;
import com.lawu.eshop.ad.dto.UserRedpacketMaxMoneyDTO;
import com.lawu.eshop.ad.param.UserRedPacketAddParam;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectParam;
import com.lawu.eshop.ad.param.UserRedpacketValue;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.AdCountRecordService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.UserRedPacketService;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.RongYunDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.QrCodeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 用户红包
 *
 * @author lihj
 * @date 2017年8月3日
 */
@Api(tags = "userRedPacket")
@RestController
@RequestMapping(value = "userRedPacket/")
public class UserRedPacketController extends BaseController {

	@Autowired
	private UserRedPacketService userRedPacketService;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberApiConfig memberApiConfig;
	
	@Autowired
	private AdCountRecordService adCountRecordService;
	
	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "新增用户红包", notes = "新增用户红包（李洪军）", httpMethod = "POST")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "addUserRedPacket", method = RequestMethod.POST)
	public Result<UserRedPacketAddReturnDTO> addUserRedPacket(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam(required = true, value = "新增红包信息") UserRedPacketAddParam param) {
		HttpServletRequest request = getRequest();
		if (null == param) {
			return successCreated(ResultCode.REQUIRED_PARM_EMPTY);
		}
		if (param.getTotalCount() > UserRedpacketValue.MAX_USERREDPACKET_COUNT) {
			return successCreated(ResultCode.MAX_USERREDPACKET_COUNT);
		}
		if (param.getTotalMoney().compareTo(UserRedpacketValue.MAX_MONTY) > 0) {
			return successCreated(ResultCode.MAX_USERREDPACKET_MONTY);
		}
		int flag =param.getTotalMoney().divide(new BigDecimal(param.getTotalCount()), 4, RoundingMode.HALF_UP).compareTo(new BigDecimal(UserRedpacketValue.MIN_USERREDPACKET_COUNT));
		if(flag<0){
			return successCreated(ResultCode.MIN_USERREDPACKET_MONTY);
		}
		UserRedPacketSaveParam saveParam = new UserRedPacketSaveParam();
		saveParam.setRedPacketPutWayEnum(param.getRedPacketPutWayEnum());
		saveParam.setTotalCount(param.getTotalCount());
		saveParam.setTotalMoney(param.getTotalMoney());
		saveParam.setUserAccount(UserUtil.getCurrentAccount(request));
		saveParam.setUserNum(UserUtil.getCurrentUserNum(request));
		Result<RongYunDTO> member = memberService.getRongYunInfoByNum(UserUtil.getCurrentUserNum(request));
		saveParam.setNickname(member.getModel().getNickName());
		Result<UserRedPacketAddReturnDTO> result = userRedPacketService.addUserRedPacket(saveParam);
		if(isSuccess(result)){
			adCountRecordService.setUserRedPacketCountRecord(result.getModel().getId(), param.getTotalCount());
		}
		return successCreated(result);
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "查询用户红包列表信息", notes = "查询用户红包列表信息(李洪军)", httpMethod = "GET")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "selectUserRedPacketList", method = RequestMethod.GET)
	public Result<Page<UserRedPacketDTO>> selectUserRedPacketList(
			@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@ModelAttribute @ApiParam(required = true, value = "查询信息") UserRedPacketSelectParam param) {
		 UserRedPacketSelectNumParam userparam =new UserRedPacketSelectNumParam();
		 userparam.setCurrentPage(param.getCurrentPage());
		 userparam.setPageSize(param.getPageSize());
		 userparam.setNum(UserUtil.getCurrentUserNumByToken(token));
		 Result<Page<UserRedPacketDTO>>  result=userRedPacketService.selectUserRedPacketList(userparam);
		 if(!isSuccess(result)){
			 return successCreated(result.getRet());
		 }
		 List<UserRedPacketDTO> list = result.getModel().getRecords();
		 for (UserRedPacketDTO userRedPacketDTO : list) {
			 userRedPacketDTO.setMemberId(Long.parseLong(UserUtil.getCurrentUserIdByToken(token)));
		 }
		 return result;
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "生成用户红包二维码", notes = "生成用户红包二维码(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getUserQrCode", method = RequestMethod.GET)
	public void getUserQrCode(@RequestParam @ApiParam(required = true, value = "token") String token,
			@RequestParam @ApiParam(required = true, value = "红包ID") Long redPacketId) throws IOException {
		String memberId = UserUtil.getCurrentUserIdByToken(token);
		if (null != memberId) {
			HttpServletResponse response = getResponse();
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.setContentType("image/jpeg");
			String content = memberApiConfig.getMemberQrCode() + memberId + "/" + redPacketId;
			BufferedImage buff = QrCodeUtil.generateQrCode(content);
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(buff, "jpeg", out);
			out.close();
		}
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "扫描用户分享红包二维码", notes = "扫描用户分享红包二维码(李洪军)", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getUserQrCodeContent/{memberId}/{redPacketId}", method = RequestMethod.GET)
	public void getUserQrCodeContent(@PathVariable @ApiParam(required = true, value = "用户ID") Long memberId,
			@PathVariable @ApiParam(required = true, value = "红包ID") Long redPacketId) throws IOException {
		HttpServletResponse response = getResponse();
		if (memberId == null || memberId <= 0) {
			return;
		}
		if (redPacketId == null || redPacketId <= 0) {
			return;
		}
		Result<IsExistsRedPacketDTO> result = userRedPacketService.isExistsRedPacket(redPacketId);
		if (result.getModel().getIsExistsRedPacket()) {// 还可以领取
			response.sendRedirect(memberApiConfig.getMemberShareRedpacketUrl() + "?memberId=" + memberId
					+ "&redPacketId=" + redPacketId);
		} else {
			// 不能领取了
			response.sendRedirect(
					memberApiConfig.getMemberShareOverUrl() + "?memberId=" + memberId + "&redPacketId=" + redPacketId);
		}
	}

	@AutoTesting
	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "获取红包中最大值", notes = "获取红包中最大值", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getUserRedpacketMaxMoney", method = RequestMethod.POST)
	public Result<UserRedPacketReturnDTO> getUserRedpacketMaxMoney(@RequestParam @ApiParam(required = true, value = "红包ID") Long redPacketId,@RequestParam @ApiParam(required = true, value = "发红包者ID") Long memberId) {
		Result<UserRedpacketMaxMoneyDTO> result = userRedPacketService.getUserRedpacketMaxMoney(redPacketId);
		if (!isSuccess(result)) {
			return successGet(result.getRet());
		}
		UserRedPacketReturnDTO dto =new UserRedPacketReturnDTO();
		dto.setMoney(result.getModel().getMoney());
		Result<UserDTO> user= memberService.findMemberInfo(memberId);
		if(null==user||null==user.getModel()){
			return successGet(ResultCode.NOT_FOUND_DATA);
		}
		dto.setInviterAccount(user.getModel().getAccount());
		dto.setHeadUrl(user.getModel().getHeadimg());
		dto.setNickName(user.getModel().getNickname());
		Result<IsExistsRedPacketDTO> get = userRedPacketService.isExistsRedPacket(redPacketId);
		dto.setIsGetAll(get.getModel().getIsExistsRedPacket());
		return successGet(dto);
	}

	@Audit(date = "2017-08-08", reviewer = "孙林青")
	@ApiOperation(value = "领取用户红包", notes = "领取用户红包[1002]", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getUserRedpacketMoney", method = RequestMethod.POST)
	public Result<UserRedpacketMaxMoneyDTO> getUserRedpacketMoney(@RequestParam @ApiParam(required = true, value = "手机号") String phoneNumber,
			@RequestParam @ApiParam(required = true, value = "红包ID") Long redPacketId) {
		Result<MemberDTO> user = memberService.getMemberByAccount(phoneNumber);
		if(!isSuccess(user)){
			return successCreated(user.getRet());
		}
		if(user.getModel()==null){
			return successCreated(ResultCode.RESOURCE_NOT_FOUND);
		}
		Result<UserRedpacketMaxMoneyDTO> result = userRedPacketService.getUserRedpacketMoney(redPacketId, user.getModel().getNum());
		return result;
	}



	@Audit(date = "2018-05-15", reviewer = "孙林青")
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "领取用户红包", notes = "领取用户红包[1002]", httpMethod = "POST")
	@Authorization
	@ApiResponse(code = HttpCode.SC_OK, message = "success")
	@RequestMapping(value = "getShareUserRedpacketMoney", method = RequestMethod.POST)
	public Result<UserRedpacketMaxMoneyDTO> getShareUserRedpacketMoney(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "红包ID") Long redPacketId) {
		Result<UserRedpacketMaxMoneyDTO> result = userRedPacketService.getUserRedpacketMoney(redPacketId, UserUtil.getCurrentUserNum(getRequest()));
		return successCreated(result);
	}

}
