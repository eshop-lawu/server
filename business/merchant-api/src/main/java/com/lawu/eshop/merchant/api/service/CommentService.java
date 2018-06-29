package com.lawu.eshop.merchant.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.CommentProductIdDTO;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
@FeignClient(value = "mall-srv")
public interface CommentService {
    /**
     * 商家回复商品评论
     * @param commentId
     * @param replyContent
     * @return
     */
    @RequestMapping(value = "commentProduct/replyProductComment/{commentId}", method = RequestMethod.PUT)
    public Result replyProductComment(@PathVariable("commentId") Long commentId,
                                      @RequestParam("replyContent") String replyContent,
                                      @RequestParam("merchantId") Long merchantId);

    /**
     * 商家回复评论商家
     * @param commentId
     * @param replyContent
     * @return
     */
    @RequestMapping(value = "commentMerchant/replyMerchantComment/{commentId}", method = RequestMethod.PUT)
    Result replyMerchantComment(@PathVariable("commentId") Long commentId,
                                @RequestParam("replyContent") String replyContent,
                                @RequestParam("merchantId") Long merchantId);

    /**
     * 根据商家ID查询商品评论信息
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "commentProduct/getProductCommentListByMerchantId",method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getProductCommentListByMerchantId(@ModelAttribute CommentMerchantListParam pageParam);

    /**
     * 获取用户评价商品ids
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "commentProduct/getProductCommentIdsByMerchantId",method = RequestMethod.POST)
    public Result<Page<CommentProductIdDTO>> getProductCommentIdsByMerchantId(@ModelAttribute CommentMerchantListParam pageParam);


    /**
     * 获取用户评价列表（全部）
     * @param listParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "commentProduct/getCommentProducts")
    public Result<Page<CommentDTO>> getCommentProducts(@ModelAttribute CommentProductListParam listParam);

    @RequestMapping(value = "commentMerchant/getCommentMerchantAllList", method = RequestMethod.POST)
    public Result<Page<CommentDTO>> getCommentMerchantAllList(@ModelAttribute CommentMerchantListParam listParam);

    /**
     * 获取商品好评率
     *
     * @param productId
     * @return
     */
    @RequestMapping(value = "commentProduct/getCommentAvgGrade/{productId}", method = RequestMethod.GET)
    Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("productId") Long productId);

}
