package com.lawu.eshop.mall.srv.controller;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.mall.dto.*;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mall.srv.converter.CommentProductConverter;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
@RestController
@RequestMapping(value = "commentProduct")
public class CommentProductController extends BaseController {

    @Autowired
    private CommentProductService commentProductService;

    /**
     * 新增用户商品评价
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "saveCommentProductInfo/{memberId}", method = RequestMethod.POST)
    public Result saveCommentProductInfo(@PathVariable("memberId") Long memberId, @RequestBody CommentProductParam param, @RequestParam("headImg") String headImg) {
        Integer id = commentProductService.saveCommentProductInfo(memberId, param, headImg);
        if (id == null || id < 0) {
            successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }

    /**
     * 获取评论信息列表(全部)
     *
     * @param listParam
     * @return
     */
    @RequestMapping(value = "getCommentProducts", method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getCommentProducts(@RequestBody CommentProductListParam listParam) {

        Page<CommentProductBO> commentProductBOPage = commentProductService.getCommentProducts(listParam);

        List<CommentProductBO> commentProductBOS = commentProductBOPage.getRecords();

        List<CommentDTO> commentProductDTOS = CommentProductConverter.converterDTOS(commentProductBOS);
        Page<CommentDTO> pages = new Page<>();
        pages.setRecords(commentProductDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentProductBOPage.getTotalCount());
        return successGet(pages);
    }

    /**
     * 商品评论信息列表（有图）
     * @param listParam
     * @return
     */
    @RequestMapping(value = "getCommentProductsWithImgs", method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getCommentProductsWithImgs(@RequestBody CommentProductListParam listParam) {
        Page<CommentProductBO> commentProductBOPage = commentProductService.getCommentProductsWithImgs(listParam);

        List<CommentProductBO> commentProductBOS = commentProductBOPage.getRecords();

        List<CommentDTO> commentProductDTOS = CommentProductConverter.converterDTOS(commentProductBOS);
        Page<CommentDTO> pages = new Page<CommentDTO>();
        pages.setRecords(commentProductDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentProductBOPage.getTotalCount());
        return successGet(pages);
    }

    /**
     * 回复商家评论
     * @param commentId
     * @param replyContent
     * @return
     */
    @RequestMapping(value = "replyProductComment/{commentId}", method = RequestMethod.PUT)
    public Result replyProductComment(@PathVariable("commentId") Long commentId,
                                      @RequestParam("replyContent") String replyContent,
                                      @RequestParam("merchantId") Long merchantId) {
        //查询评论信息
        CommentProductBO commentProductBO = commentProductService.findProductComment(commentId,merchantId);
        if (commentProductBO == null) {
            return successCreated(ResultCode.RESOURCE_NOT_FOUND);
        }
        if(StringUtils.isNotEmpty(commentProductBO.getReplyContent())){
            return successCreated(ResultCode.COMMENT_REPEAT_REPLY);
        }
        int rows = commentProductService.replyProductComment(commentId, replyContent);
        if (rows == 0 || rows < 0) {
            return successCreated(ResultCode.SAVE_FAIL);
        }
        return successCreated(ResultCode.SUCCESS);
    }


    /**
     * 屏蔽评价信息
     * @param commentId
     * @return
     */
    @RequestMapping(value = "delCommentProductInfo/{commentId}",method = RequestMethod.DELETE)
    public Result delCommentProductInfo(@PathVariable("commentId") Long commentId){
        if (commentId == null) {
            return successDelete(ResultCode.REQUIRED_PARM_EMPTY);
        }
        commentProductService.delCommentProductInfo(commentId);
        return  successDelete(ResultCode.SUCCESS);
    }

    /**
     * 获取综合评价及好评率
     * @param productId
     * @return
     */
    @RequestMapping(value = "getCommentAvgGrade/{productId}",method = RequestMethod.GET)
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("productId") Long productId) {

        CommentGradeBO commentGradeBO = commentProductService.getCommentAvgGrade(productId);
        if(commentGradeBO == null){
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        CommentGradeDTO commentGradeDTO = new CommentGradeDTO();
        commentGradeDTO.setGoodGrad(commentGradeBO.getGoodGrad());
        commentGradeDTO.setAvgGrade(commentGradeBO.getAvgGrade());
        return successGet(commentGradeDTO);
    }


    @RequestMapping(value = "getCommentProductListOperator",method = RequestMethod.POST)
    public Result<Page<CommentOperatorDTO>> getCommentProductListOperator(@RequestBody CommentListParam listParam){
        Page<CommentOperatorDTO> pages = new Page<CommentOperatorDTO>();
        Page<CommentProductBO> commentProductBOPage = commentProductService.getCommentProductListOperator(listParam);
        if(commentProductBOPage == null){
            return successGet(pages);
        }

        List<CommentProductBO> commentProductBOS = commentProductBOPage.getRecords();
        List<CommentOperatorDTO> commentOperatorDTOS = CommentProductConverter.converterOperatorDTOS(commentProductBOS);
        pages.setRecords(commentOperatorDTOS);
        pages.setCurrentPage(listParam.getCurrentPage());
        pages.setTotalCount(commentProductBOPage.getTotalCount());
        return successGet(pages);
    }

    /**
     * 根据商家ID查询商品评论信息
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "getProductCommentListByMerchantId",method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getProductCommentListByMerchantId(@RequestBody CommentMerchantListParam pageParam){

        if (pageParam == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        Page<CommentProductBO> commentProductBOPage = commentProductService.getProductCommentListByMerchantId(pageParam);

        List<CommentProductBO> commentProductBOS = commentProductBOPage.getRecords();

        List<CommentDTO> commentProductDTOS = CommentProductConverter.converterDTOS(commentProductBOS);
        Page<CommentDTO> pages = new Page<>();
        pages.setRecords(commentProductDTOS);
        pages.setCurrentPage(pageParam.getCurrentPage());
        pages.setTotalCount(commentProductBOPage.getTotalCount());
        return successGet(pages);
    }

    /**
     * 获取用户评价商品ids
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "getProductCommentIdsByMerchantId",method = RequestMethod.POST)
    public Result<Page<CommentProductIdDTO>> getProductCommentIdsByMerchantId(@RequestBody CommentMerchantListParam pageParam){
        Page<CommentProductBO> commentProductBOPage = commentProductService.getProductCommentIdsByMerchantId(pageParam);
        if(commentProductBOPage == null){
            return successGet(new Page<>());
        }
        List<CommentProductBO> commentProductBOS = commentProductBOPage.getRecords();

        List<CommentProductIdDTO> commentProductDTOS = CommentProductConverter.converterProIdDTOS(commentProductBOS);
        Page<CommentProductIdDTO> pages = new Page<>();
        pages.setRecords(commentProductDTOS);
        pages.setCurrentPage(pageParam.getCurrentPage());
        pages.setTotalCount(commentProductBOPage.getTotalCount());
        return successGet(pages);
    }

    /**
     * 根据商品ID删除评论
     * @param productModelId
     * @return
     */
    @RequestMapping(value = "delCommentByProductModelId",method = RequestMethod.DELETE)
    public Result delCommentByProductModelId(@RequestParam(value = "productModelId") Long productModelId){
        if(productModelId == null){
            return successDelete(ResultCode.REQUIRED_PARM_EMPTY);
        }
        commentProductService.delCommentByProductModelId(productModelId);
        return successDelete();
    }

    /**
     * 根据订单项删除评价商品
     * @param orderItemId
     * @return
     */
    @RequestMapping(value = "delCommentByOrderItemId",method = RequestMethod.DELETE)
    public Result delCommentByOrderItemId(@RequestParam(value = "orderItemId") Long orderItemId){
        if(orderItemId == null){
            return successDelete(ResultCode.REQUIRED_PARM_EMPTY);
        }
        commentProductService.delCommentByOrderItemId(orderItemId);
        return successDelete();
    }

    /**
     * 用户端商品详情，查询商品最近1条商品评价
     * @param productId
     * @return
     * @author yangqh
     */
    @RequestMapping(value = "geNewlyProductComment",method = RequestMethod.GET)
    public Result<List<MemberProductCommentDTO>> geNewlyProductComment(@RequestParam Long productId){
    	if(productId == null){
    		return successGet(ResultCode.ID_EMPTY);
    	}
    	List<MemberProductCommentDTO> dtos = commentProductService.geNewlyProductComment(productId);
    	return successGet(dtos);
    }
    
    /**
     * 用户端商品详情，查询商品评价数量
     * @param productId
     * @return
     */
    @RequestMapping(value = "getProductCommentCount",method = RequestMethod.GET)
    public Result<Integer> getProductCommentCount(@RequestParam Long productId){
    	if(productId == null){
    		return successGet(ResultCode.ID_EMPTY);
    	}
    	Integer count = commentProductService.getProductCommentCount(productId);
    	return successGet(count);
    }
}
