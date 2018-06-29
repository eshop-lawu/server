package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.CommentOperatorDTO;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentMerchantBO;
import com.lawu.eshop.mall.srv.converter.CommentMerchantConverter;
import com.lawu.eshop.mall.srv.service.CommentMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
@RestController
@RequestMapping(value = "commentMerchant")
public class CommentMerchantController extends BaseController {

    @Autowired
    private CommentMerchantService commentMerchantService;

    /**
     * 新增商家评价信息
     *
     * @param memberId
     * @param param
     * @param commentPic
     * @return
     */
    @RequestMapping(value = "saveCommentMerchantInfo/{memberId}", method = RequestMethod.POST)
    public Result saveCommentMerchantInfo(@PathVariable("memberId") Long memberId, @RequestBody CommentMerchantParam param,
                                          @RequestParam("commentPic") String commentPic) {

        Integer id = commentMerchantService.saveCommentMerchantInfo(memberId, param, commentPic);
        if (id == null || id < 0) {
            successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated();
    }

    /**
     * 商家评论信息列表（全部）
     *
     * @param listParam
     * @return
     */
    @RequestMapping(value = "getCommentMerchantAllList", method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getCommentMerchantAllList(@RequestBody CommentMerchantListParam listParam) {
        Page<CommentMerchantBO> commentMerchantBOPage = commentMerchantService.getCommentMerchantAllList(listParam);

        List<CommentMerchantBO> commentMerchantBOS = commentMerchantBOPage.getRecords();

        List<CommentDTO> commentDTOS = CommentMerchantConverter.converterDTOS(commentMerchantBOS);
        Page<CommentDTO> pages = new Page<CommentDTO>();
        pages.setRecords(commentDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentMerchantBOPage.getTotalCount());
        return successGet(pages);
    }

    @RequestMapping(value = "getCommentMerchantListWithImgs", method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getCommentMerchantListWithImgs(@RequestBody CommentMerchantListParam listParam) {
        Page<CommentMerchantBO> commentMerchantBOPage = commentMerchantService.getCommentMerchantListWithImgs(listParam);

        List<CommentMerchantBO> commentMerchantBOS = commentMerchantBOPage.getRecords();

        List<CommentDTO> commentDTOS = CommentMerchantConverter.converterDTOS(commentMerchantBOS);
        Page<CommentDTO> pages = new Page<CommentDTO>();
        pages.setRecords(commentDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentMerchantBOPage.getTotalCount());
        return successGet(pages);
    }

    @RequestMapping(value = "replyMerchantComment/{commentId}", method = RequestMethod.PUT)
    public Result replyMerchantComment(@PathVariable("commentId") Long commentId,
                                       @RequestParam("replyContent") String replyContent,
                                       @RequestParam("merchantId") Long merchantId) {
        //查询评论信息
        CommentMerchantBO commentMerchantBO = commentMerchantService.findMerchantComment(commentId,merchantId);
        if (commentMerchantBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if(StringUtils.isNotEmpty(commentMerchantBO.getReplyContent())){
            return successCreated(ResultCode.COMMENT_REPEAT_REPLY);
        }
        int rows = commentMerchantService.replyMerchantComment(commentId, replyContent);
        if (rows == 0 || rows < 0) {
            return successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "getCommentAvgGrade/{merchantId}", method = RequestMethod.GET)
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("merchantId") Long merchantId) {

        CommentGradeBO commentGradeBO = commentMerchantService.getCommentAvgGrade(merchantId);
        if (commentGradeBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        CommentGradeDTO commentGradeDTO = new CommentGradeDTO();
        commentGradeDTO.setAverageConsumeAmount(commentGradeBO.getAverageConsumeAmount());
        commentGradeDTO.setGoodGrad(commentGradeBO.getGoodGrad());
        commentGradeDTO.setAvgGrade(commentGradeBO.getAvgGrade());
        return successGet(commentGradeDTO);
    }

    @RequestMapping(value = "getCommentMerchantListOperator", method = RequestMethod.POST)
    public Result<Page<CommentOperatorDTO>> getCommentMerchantListOperator(@RequestBody CommentListParam listParam) {
        Page<CommentOperatorDTO> pages = new Page<CommentOperatorDTO>();
        Page<CommentMerchantBO> commentMerchantBOPage = commentMerchantService.getCommentMerchantListOperator(listParam);
        if(commentMerchantBOPage == null){
            return successGet(pages);
        }
        List<CommentMerchantBO> commentMerchantBOS = commentMerchantBOPage.getRecords();
        List<CommentOperatorDTO> commentOperatorDTOS = CommentMerchantConverter.converterOperatorDTOS(commentMerchantBOS);
        pages.setRecords(commentOperatorDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentMerchantBOPage.getTotalCount());
        return successGet(pages);
    }

    @RequestMapping(value = "delCommentMerchantInfo/{commentId}",method = RequestMethod.DELETE)
    public Result delCommentMerchantInfo(@PathVariable("commentId") Long commentId){
        if (commentId == null) {
            return successDelete(ResultCode.REQUIRED_PARM_EMPTY);
        }
        commentMerchantService.delCommentMerchantInfo(commentId);
        return  successDelete(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "getGradeByOrderId", method = RequestMethod.GET)
    public Byte getGradeByOrderId(@RequestParam("id") Long id, @RequestParam("memberId") Long memberId) {
        Byte grade = commentMerchantService.getGradeByOrderId(id, memberId);
        return grade;
    }

    @RequestMapping(value = "payOrderAutoComment",method = RequestMethod.POST)
    Result payOrderAutoComment(@RequestBody PayOrderAutoCommentParam param){
        commentMerchantService.payOrderAutoComment(param);
        return successCreated();
    }

}
