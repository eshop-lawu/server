/**
 *
 */
package com.lawu.eshop.operator.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.operator.api.OperatorApiConfig;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
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
 * @author zhangrc
 * @date 2017年8月7日
 */
@Api(tags = "upload", value = "统一上传接口")
@RestController
@RequestMapping(value = "upload/")
public class UploadController extends BaseController {

    @Autowired
    private OperatorApiConfig operatorApiConfig;

    @ApiOperation(value = "统一上传接口", notes = "上传接口(张荣成)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Result<FileUploadDTO> uploadFile(@RequestParam @ApiParam(required = true, value = "上传类型图片(IMG)，视频(VIDEO),其他(OTHER)") String uploadType) {
        HttpServletRequest request = getRequest();
        UploadParam uparam = new UploadParam();
        uparam.setBaseImageDir(operatorApiConfig.getImageUploadUrl());
        uparam.setDir(FileDirConstant.AD_PLAT_FORM_URL);
        uparam.setFileUploadTypeEnum(FileUploadTypeEnum.getEnum(uploadType));
        uparam.setFfmpegUrl(operatorApiConfig.getFfmpegUrl());
        ClientParams cp = new ClientParams();
        cp.setTrackerServer(operatorApiConfig.getTrackerServers());
        cp.setTrackerHttpPort(operatorApiConfig.getTrackerHttpPort());
        uparam.setCparam(cp);
        FastDFSResult fastResult = FastDFSUploadUtils.upload(request, uparam);
        Result<FileUploadDTO> result = new Result<FileUploadDTO>();
        switch (fastResult.getFenum()) {
            case FD_UPLOAD_SUCCESS:
                result.setRet(HttpCode.SC_CREATED);
                result.setModel(new FileUploadDTO(fastResult.getFileUrl(), fastResult.getCutImgUrl()));
                break;
            case FD_FILE_ERROR:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_ERROR));
                break;
            case FD_FILE_IMG_BIG:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_IMG_BIG));
                break;
            case UPLOAD_SIZE_BIGER:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.UPLOAD_SIZE_BIGER));
                break;
            case FD_FILE_CUT_ERROR:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_CUT_ERROR));
                break;
            case FD_FILE_VIDEO_CODE_ERROR:
                result.setRet(ResultCode.FD_FILE_VIDEO_CODE_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_VIDEO_CODE_ERROR));
                break;
        }
        return successCreated(result.getModel());
    }

    @ApiOperation(value = "批量上传文件", notes = "批量上传文件 (梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequestMapping(value = "batchUploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Result<FileUploadDTO> batchUploadFile(@RequestParam @ApiParam(required = true, value = "上传类型图片(IMG)，视频(VIDEO),其他(OTHER)") String uploadType) {
        HttpServletRequest request = getRequest();
        UploadParam uparam = new UploadParam();
        uparam.setBaseImageDir(operatorApiConfig.getImageUploadUrl());
        uparam.setDir(FileDirConstant.AD_PLAT_FORM_URL);
        uparam.setFileUploadTypeEnum(FileUploadTypeEnum.getEnum(uploadType));
        uparam.setFfmpegUrl(operatorApiConfig.getFfmpegUrl());
        ClientParams cp = new ClientParams();
        cp.setTrackerServer(operatorApiConfig.getTrackerServers());
        cp.setTrackerHttpPort(operatorApiConfig.getTrackerHttpPort());
        uparam.setCparam(cp);
        FastDFSResult fastResult = FastDFSUploadUtils.upload(request, uparam);
        Result<FileUploadDTO> result = new Result<>();
        switch (fastResult.getFenum()) {
            case FD_UPLOAD_SUCCESS:
                result.setRet(HttpCode.SC_CREATED);
                result.setModel(new FileUploadDTO(fastResult.getFileUrl(), fastResult.getCutImgUrl()));
                break;
            case FD_FILE_ERROR:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_ERROR));
                break;
            case FD_FILE_IMG_BIG:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_IMG_BIG));
                break;
            case UPLOAD_SIZE_BIGER:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.UPLOAD_SIZE_BIGER));
                break;
            case FD_FILE_CUT_ERROR:
                result.setRet(HttpCode.SC_INTERNAL_SERVER_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_CUT_ERROR));
                break;
            case FD_FILE_VIDEO_CODE_ERROR:
                result.setRet(ResultCode.FD_FILE_VIDEO_CODE_ERROR);
                result.setMsg(ResultCode.get(ResultCode.FD_FILE_VIDEO_CODE_ERROR));
                break;
        }
        return successCreated(result.getModel());
    }

}
