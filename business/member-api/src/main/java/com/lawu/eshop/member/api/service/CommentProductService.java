package com.lawu.eshop.member.api.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
@FeignClient(value = "mall-srv")
public interface CommentProductService {

    /**
     * 用户新增评价
     *
     * @param memberId
     * @param param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "commentProduct/saveCommentProductInfo/{memberId}")
    Result saveCommentProductInfo(@PathVariable("memberId") Long memberId, @ModelAttribute CommentProductParam param, @RequestParam("headImg") String headImg);

    /**
     * 获取用户评价列表（全部）
     * @param listParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "commentProduct/getCommentProducts")
    public Result<Page<CommentDTO>> getCommentProducts(@ModelAttribute CommentProductListParam listParam);

    /**
     * 获取评价列表（有图）
     * @param listParam
     * @return
     */
    @RequestMapping(method = RequestMethod.POST,value = "commentProduct/getCommentProductsWithImgs")
    public Result<Page<CommentDTO>> getCommentProductsWithImgs(@ModelAttribute CommentProductListParam listParam);

    /**
     * 获取商品好评率
     * @param productId
     * @return
     */
    @RequestMapping(value = "commentProduct/getCommentAvgGrade/{productId}",method = RequestMethod.GET)
    public Result<CommentGradeDTO> getCommentAvgGrade(@PathVariable("productId") Long productId);

}
