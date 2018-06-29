/**
 *
 */
package com.lawu.eshop.member.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.fastdfs.ClientParams;
import com.lawu.media.util.FastDFSUploadUtils;
import com.lawu.media.util.UploadParam;
import com.lawu.media.util.upload.FastDFSResult;
import com.lawu.media.util.upload.FileUploadDTO;
import com.lawu.media.util.upload.FileUploadTypeEnum;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 统一上传接口
 *
 * @author lihj
 * @date 2017年7月26日
 */
@Api(tags = "upload", value = "统一上传接口")
@RestController
@RequestMapping(value = "upload/")
public class UploadController extends BaseController {

	@Autowired
	private MemberApiConfig memberApiConfig;

	@Audit(date = "2017-08-01", reviewer = "孙林青")
	@Authorization
	@ApiOperation(value = "统一上传接口", notes = "上传接口(李洪军)[上传类型为图片时返回图片路径是FileUrl、上传类型为视频时返回的视频路径为FileUrl、视频第三秒的截图文件路径是CutImgUrl]", httpMethod = "POST")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public Result<FileUploadDTO> uploadFile(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
			@RequestParam @ApiParam(required = true, value = "上传类型图片大写(IMG)，视频(VIDEO),其他(OTHER)") String uploadType,
			@RequestParam(required = false) @ApiParam(value = "上传文件下标或标识(非必填)") String fileIndex) {
		HttpServletRequest request = getRequest();
		UploadParam uparam = new UploadParam();
		uparam.setBaseImageDir(memberApiConfig.getImageUploadUrl());
		uparam.setDir(FileDirConstant.DIR_HEAD);
		uparam.setFileUploadTypeEnum(FileUploadTypeEnum.getEnum(uploadType));
		uparam.setFfmpegUrl(memberApiConfig.getFfmpegUrl());
		ClientParams cp = new ClientParams();
		cp.setTrackerServer(memberApiConfig.getTrackerServers());
		cp.setTrackerHttpPort(memberApiConfig.getTrackerHttpPort());
		uparam.setCparam(cp);
		FastDFSResult fastResult = FastDFSUploadUtils.upload(request, uparam);
		Result<FileUploadDTO> result = new Result<FileUploadDTO>();
		switch (fastResult.getFenum()) {
			case FD_UPLOAD_SUCCESS:
				result.setRet(ResultCode.SUCCESS);
				result.setModel(new FileUploadDTO(fastResult.getFileUrl(), fastResult.getCutImgUrl(), fileIndex));
				break;
			case FD_FILE_ERROR:
				result.setRet(ResultCode.FAIL);
				result.setMsg(ResultCode.get(ResultCode.FD_FILE_ERROR));
				break;
			case FD_FILE_IMG_BIG:
				result.setRet(ResultCode.FAIL);
				result.setMsg(ResultCode.get(ResultCode.FD_FILE_IMG_BIG));
				break;
			case UPLOAD_SIZE_BIGER:
				result.setRet(ResultCode.FAIL);
				result.setMsg(ResultCode.get(ResultCode.UPLOAD_SIZE_BIGER));
				break;
			case FD_FILE_CUT_ERROR:
				result.setRet(ResultCode.FAIL);
				result.setMsg(ResultCode.get(ResultCode.FD_FILE_CUT_ERROR));
				break;
		}
		return successCreated(result);
	}

}
