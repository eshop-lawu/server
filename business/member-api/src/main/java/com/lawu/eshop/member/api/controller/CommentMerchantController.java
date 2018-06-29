package com.lawu.eshop.member.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.constants.FileDirConstant;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.CommentMerchantDTO;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.member.api.MemberApiConfig;
import com.lawu.eshop.member.api.service.CommentMerchantService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.user.constants.UploadFileTypeConstant;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.media.util.UploadFileUtil;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
@Api(tags = "commentMerchant")
@RestController
@RequestMapping("commentMerchant/")
public class CommentMerchantController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CommentMerchantController.class);
    @Autowired
    private CommentMerchantService commentMerchantService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberApiConfig memberApiConfig;

    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @Deprecated
    @ApiOperation(value = "用户评价商家", notes = "用户评价商家 [1005，1000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "saveCommentMerchantInfo", method = RequestMethod.POST)
    public Result saveCommentMerchantInfo(@ModelAttribute CommentMerchantParam param,@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        HttpServletRequest request = getRequest();
        Long memberId = UserUtil.getCurrentUserId(request);
        StringBuilder commentPic = new StringBuilder();
        Collection<Part> parts = null;
        try {
            parts = request.getParts();

        } catch (IOException e) {
            logger.error("IOException {}",e);
            return successCreated(e.getMessage());
        }
        catch (ServletException ex){
            logger.info("ServletException :{}",ex);
        }
        if(parts != null &&StringUtils.isNotEmpty(parts.toString())){
            for (Part part : parts) {
                Map<String, String> map = UploadFileUtil.uploadImages(request, FileDirConstant.DIR_STORE, part, memberApiConfig.getImageUploadUrl());
                String flag = map.get("resultFlag");
                if (UploadFileTypeConstant.UPLOAD_RETURN_TYPE.equals(flag)) {
                    //有图片上传成功返回,拼接图片url
                    String imgUrl = map.get("imgUrl");
                    commentPic.append(imgUrl + ",");
                } else {
                    return successCreated(Integer.valueOf(flag));
                }
            }
        }

        return commentMerchantService.saveCommentMerchantInfo(memberId, param, commentPic.toString());
    }

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "用户评价商家列表（全部）", notes = "用户评价商家 [1005，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getCommentMerchantList", method = RequestMethod.GET)
    public Result<Page<CommentMerchantDTO>> getCommentMerchantList(@ModelAttribute @ApiParam CommentMerchantListParam listParam) {
        List<CommentMerchantDTO> commentMerchantDTOS = new ArrayList<>();
        Page<CommentMerchantDTO> pages = new Page<>();
        //获取评论列表
        Result<Page<CommentDTO>> result = commentMerchantService.getCommentMerchantAllList(listParam);
        if(result.getModel() == null || result.getModel() .getRecords().isEmpty()){
            pages.setCurrentPage(listParam.getCurrentPage());
            pages.setTotalCount(result.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
            for (CommentDTO commentDTO : result.getModel().getRecords()) {
                CommentMerchantDTO commentMerchantDTO = new CommentMerchantDTO();
                commentMerchantDTO.setAnonymous(commentDTO.getAnonymous());
                commentMerchantDTO.setContent(commentDTO.getContent());
                commentMerchantDTO.setGmtCreate(commentDTO.getGmtCreate());
                commentMerchantDTO.setReplyContent(commentDTO.getReplyContent());
                commentMerchantDTO.setImgUrls(commentDTO.getImgUrls());
                commentMerchantDTO.setId(commentDTO.getId());
                commentMerchantDTO.setGrade(commentDTO.getGrade());
                commentMerchantDTO.setAvgSpend(commentDTO.getAvgSpend());
                //查询评论用户信息
                Result<UserDTO> user = memberService.findMemberInfo(commentDTO.getMemberId());
                if(commentDTO.getAnonymous()){
                    commentMerchantDTO.setNickName(StringUtil.anonymous(user.getModel().getNickname()));
                    commentMerchantDTO.setHeadImg(memberApiConfig.getDefaultHeadimg());
                }else{
                    commentMerchantDTO.setNickName(user.getModel().getNickname());
                    commentMerchantDTO.setHeadImg(user.getModel().getHeadimg());
                }
                commentMerchantDTO.setLevel(user.getModel().getLevel());
                commentMerchantDTO.setMemberGrade(user.getModel().getGradeEnum());
                commentMerchantDTOS.add(commentMerchantDTO);
            }
        pages.setCurrentPage(result.getModel().getCurrentPage());
        pages.setTotalCount(result.getModel().getTotalCount());
        pages.setRecords(commentMerchantDTOS);
        return successGet(pages);
    }

    @AutoTesting
    @Audit(date = "2017-04-15", reviewer = "孙林青")
    @ApiOperation(value = "用户评价商家列表（有图）", notes = "用户评价商家（有图） [1005，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getCommentMerchantListWithImgs", method = RequestMethod.GET)
    public Result<Page<CommentMerchantDTO>> getCommentMerchantListWithImgs(@ModelAttribute @ApiParam CommentMerchantListParam listParam) {
        List<CommentMerchantDTO> commentMerchantDTOS = new ArrayList<>();
        Page<CommentMerchantDTO> pages = new Page<>();
        //获取评论列表
        Result<Page<CommentDTO>> result = commentMerchantService.getCommentMerchantListWithImgs(listParam);
        if(result.getModel() == null || result.getModel() .getRecords().isEmpty()){
            pages.setCurrentPage(listParam.getCurrentPage());
            pages.setTotalCount(result.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
            for (CommentDTO commentDTO : result.getModel().getRecords()) {
                CommentMerchantDTO commentMerchantDTO = new CommentMerchantDTO();
                commentMerchantDTO.setAnonymous(commentDTO.getAnonymous());
                commentMerchantDTO.setContent(commentDTO.getContent());
                commentMerchantDTO.setGmtCreate(commentDTO.getGmtCreate());
                commentMerchantDTO.setReplyContent(commentDTO.getReplyContent());
                commentMerchantDTO.setImgUrls(commentDTO.getImgUrls());
                commentMerchantDTO.setGrade(commentDTO.getGrade());
                commentMerchantDTO.setId(commentDTO.getId());
                commentMerchantDTO.setAvgSpend(commentDTO.getAvgSpend());
                //查询评论用户信息
                Result<UserDTO> user = memberService.findMemberInfo(commentDTO.getMemberId());
                if(commentDTO.getAnonymous()){
                    commentMerchantDTO.setHeadImg(memberApiConfig.getDefaultHeadimg());
                    commentMerchantDTO.setNickName(StringUtil.anonymous(user.getModel().getNickname()));
                }else{
                    commentMerchantDTO.setHeadImg(user.getModel().getHeadimg());
                    commentMerchantDTO.setNickName(user.getModel().getNickname());
                }
                commentMerchantDTO.setLevel(user.getModel().getLevel());
                if(user.getModel().getGradeEnum() != null){
                    commentMerchantDTO.setMemberGrade(user.getModel().getGradeEnum());
                }
                commentMerchantDTOS.add(commentMerchantDTO);
            }

        pages.setCurrentPage(result.getModel().getCurrentPage());
        pages.setTotalCount(result.getModel().getTotalCount());
        pages.setRecords(commentMerchantDTOS);
        return successGet(pages);
    }

    @AutoTesting
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "查询商家评价好评率，综合评分", notes = "查询商家评价好评率，综合评分 [1004，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getCommentAvgGrade/{merchantId}", method = RequestMethod.GET)
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("merchantId") @ApiParam(value = "商家ID",required = true) Long merchantId){
        if(merchantId == null){
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        return commentMerchantService.getCommentAvgGrade(merchantId);
    }

    @Audit(date = "2017-08-08", reviewer = "孙林青")
    @ApiOperation(value = "用户评价商家(new)", notes = "用户评价商家 [1005，1000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "addCommentMerchantInfo", method = RequestMethod.POST)
    public Result addCommentMerchantInfo(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                          @ModelAttribute CommentMerchantParam param,
                                          @RequestParam(required = false) @ApiParam(value = "图片路径") String imageUrls ) {
        Long memberId = UserUtil.getCurrentUserId(getRequest());
        if(StringUtils.isEmpty(imageUrls)){
            imageUrls = ",";
        }
        return commentMerchantService.saveCommentMerchantInfo(memberId, param, imageUrls);
    }

}
