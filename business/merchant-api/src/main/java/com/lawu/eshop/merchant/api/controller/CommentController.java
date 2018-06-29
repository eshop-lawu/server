package com.lawu.eshop.merchant.api.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentGradeDTO;
import com.lawu.eshop.mall.dto.CommentMerchantInfoDTO;
import com.lawu.eshop.mall.dto.CommentProductIdDTO;
import com.lawu.eshop.mall.dto.CommentProductInfoDTO;
import com.lawu.eshop.mall.dto.MerchantProductCommentListDTO;
import com.lawu.eshop.mall.dto.ProductCommentListDTO;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.merchant.api.MerchantApiConfig;
import com.lawu.eshop.merchant.api.service.CommentService;
import com.lawu.eshop.merchant.api.service.MemberService;
import com.lawu.eshop.merchant.api.service.ProductService;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/4/7.
 */
@Api(tags = "comment")
@RestController
@RequestMapping("comment/")
public class CommentController extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;
    @Autowired
    private MerchantApiConfig merchantApiConfig;

    /**
     * 商家回复商品评价
     *
     * @param commentId
     * @param replyContent
     * @param token
     * @return
     */
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "商家回复商品评价", notes = "商家回复商品评价 [1002，1005,1000]（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "replyProductComment/{commentId}", method = RequestMethod.PUT)
    public Result replyProductComment(@PathVariable("commentId") @ApiParam(required = true, value = "评价ID") Long commentId,
                                      @RequestParam("replyContent") @ApiParam(required = true, value = "回复内容") String replyContent,
                                      @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return commentService.replyProductComment(commentId, replyContent,merchantId);
    }

    /**
     * 商家回复商品评价
     *
     * @param commentId
     * @param replyContent
     * @param token
     * @return
     */
    @Audit(date = "2017-04-12", reviewer = "孙林青")
    @ApiOperation(value = "商家回复商家评价", notes = "商家回复商家评价 [1002，1005,1000]（章勇）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "replyMerchantComment/{commentId}", method = RequestMethod.PUT)
    public Result replyMerchantComment(@PathVariable("commentId") @ApiParam(required = true, value = "评价ID") Long commentId,
                                       @RequestParam("replyContent") @ApiParam(required = true, value = "回复内容") String replyContent,
                                       @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        return commentService.replyMerchantComment(commentId, replyContent, merchantId);
    }

    @Audit(date = "2017-04-21", reviewer = "孙林青")
    @ApiOperation(value = "根据商家ID查询商品评论信息", notes = "根据商家ID查询商品评论信息 [1002，1004,1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getProductCommentListByMerchantId", method = RequestMethod.GET)
    public Result<Page<ProductCommentListDTO>> getProductCommentListByMerchantId(
            @RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
            @ModelAttribute @ApiParam(required = true) CommentListParam listparam){
        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        if (listparam == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setMerchantId(merchantId);
        param.setCurrentPage(listparam.getCurrentPage());
        param.setPageSize(listparam.getPageSize());
        List<ProductCommentListDTO> productCommentListDTOS = new ArrayList<>();
        Page<ProductCommentListDTO> pages = new Page<>();
        Result<Page<CommentDTO>> comments = commentService.getProductCommentListByMerchantId(param);
        if (comments.getModel() == null || comments.getModel().getRecords().isEmpty()) {
            pages.setCurrentPage(param.getCurrentPage());
            pages.setTotalCount(comments.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        for (CommentDTO commentDTO : comments.getModel().getRecords()) {
            //设置评论信息
            ProductCommentListDTO commentListDTO = new ProductCommentListDTO();
            commentListDTO.setContent(commentDTO.getContent());
            commentListDTO.setReplyContent(commentDTO.getReplyContent());
            commentListDTO.setAnonymous(commentDTO.getAnonymous());
            commentListDTO.setGmtCreate(commentDTO.getGmtCreate());
            commentListDTO.setImgUrls(commentDTO.getImgUrls());
            commentListDTO.setId(commentDTO.getId());
            commentListDTO.setGrade(commentDTO.getGrade());
            //查询评论用户信息
            Result<UserDTO> user = memberService.findMemberInfo(commentDTO.getMemberId());
            if(commentDTO.getAnonymous()){
                commentListDTO.setHeadImg(merchantApiConfig.getDefaultHeadimg());
                commentListDTO.setNickName(StringUtil.anonymous(user.getModel().getNickname()));
            }else{
                commentListDTO.setHeadImg(user.getModel().getHeadimg());
                commentListDTO.setNickName(user.getModel().getNickname());
            }
            commentListDTO.setLevel(user.getModel().getLevel());
            if(user.getModel().getGradeEnum() != null){
                commentListDTO.setMemberGrade(user.getModel().getGradeEnum());
            }
            //查询商品信息
            Result<com.lawu.eshop.product.dto.CommentProductInfoDTO> product = productService.selectCommentProductInfo(commentDTO.getProductModelId());
            commentListDTO.setName(product.getModel().getName());
            commentListDTO.setPrice(product.getModel().getPrice());
            commentListDTO.setFeatureImage(product.getModel().getFeatureImage());
            commentListDTO.setSpec(product.getModel().getModelName());
            productCommentListDTOS.add(commentListDTO);
        }
        pages.setCurrentPage(param.getCurrentPage());
        pages.setTotalCount(comments.getModel().getTotalCount());
        pages.setRecords(productCommentListDTOS);
        return successGet(pages);
    }


    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "商品评价筛选商品信息列表", notes = "商品评价筛选商品信息列表 [1002，1004,1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getProductsByMerchantId", method = RequestMethod.GET)
    public Result<Page<CommentProductInfoDTO>> getProductsByMerchantId(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true) CommentListParam listparam) {

        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        if (listparam == null) {
            return successGet(ResultCode.REQUIRED_PARM_EMPTY);
        }
        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setMerchantId(merchantId);
        param.setCurrentPage(listparam.getCurrentPage());
        param.setPageSize(listparam.getPageSize());
        List<CommentProductInfoDTO> commentProductInfoDTOS = new ArrayList<>();
        Page<CommentProductInfoDTO> pages = new Page<>();
        Result<Page<CommentProductIdDTO>> productIds = commentService.getProductCommentIdsByMerchantId(param);
        if (productIds.getModel() == null || productIds.getModel().getRecords().isEmpty()) {
            pages.setCurrentPage(param.getCurrentPage());
            pages.setTotalCount(productIds.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        for (CommentProductIdDTO commentProductIdDTO : productIds.getModel().getRecords()) {
            //查询商品信息
            CommentProductInfoDTO commentProductInfoDTO = new CommentProductInfoDTO();
            Result<ProductInfoDTO> product = productService.getProduct(commentProductIdDTO.getProductId());
            commentProductInfoDTO.setName(product.getModel().getName());
            commentProductInfoDTO.setImgUrl(product.getModel().getFeatureImage());
            commentProductInfoDTO.setProductId(commentProductIdDTO.getProductId());
            commentProductInfoDTOS.add(commentProductInfoDTO);
        }
        pages.setTotalCount(productIds.getModel().getTotalCount());
        pages.setCurrentPage(productIds.getModel().getCurrentPage());
        pages.setRecords(commentProductInfoDTOS);
        return successGet(pages);
    }

    @AutoTesting
    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "评价商品列表(全部)", notes = "评价商品列表 [1002，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getCommentProducts", method = RequestMethod.GET)
    public Result<Page<MerchantProductCommentListDTO>> getCommentProducts(@ModelAttribute @ApiParam CommentProductListParam listParam){

        List<MerchantProductCommentListDTO> commentProductDTOS = new ArrayList<>();
        Page<MerchantProductCommentListDTO> pages = new Page<>();
        //获取评论列表
        Result<Page<CommentDTO>> result = commentService.getCommentProducts(listParam);
        if (result.getModel() == null || result.getModel().getRecords().isEmpty()) {
            pages.setCurrentPage(listParam.getCurrentPage());
            pages.setTotalCount(result.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        for (CommentDTO commentDTO : result.getModel().getRecords()) {
            //设置评论信息
            MerchantProductCommentListDTO commentProductDTO = new MerchantProductCommentListDTO();
            commentProductDTO.setContent(commentDTO.getContent());
            commentProductDTO.setReplyContent(commentDTO.getReplyContent());
            commentProductDTO.setAnonymous(commentDTO.getAnonymous());
            commentProductDTO.setGmtCreate(commentDTO.getGmtCreate());
            commentProductDTO.setImgUrls(commentDTO.getImgUrls());
            commentProductDTO.setId(commentDTO.getId());
            commentProductDTO.setGrade(commentDTO.getGrade());
            //查询评论用户信息
            Result<UserDTO> user = memberService.findMemberInfo(commentDTO.getMemberId());
            if(commentDTO.getAnonymous()){
                commentProductDTO.setHeadImg(merchantApiConfig.getDefaultHeadimg());
                commentProductDTO.setNickName(StringUtil.anonymous(user.getModel().getNickname()));
            }else{
                commentProductDTO.setHeadImg(user.getModel().getHeadimg());
                commentProductDTO.setNickName(user.getModel().getNickname());
            }
            if (user.getModel().getGradeEnum() != null) {
                commentProductDTO.setMemberGrade(user.getModel().getGradeEnum());
            }
            commentProductDTO.setLevel(user.getModel().getLevel());
            //查询商品信息
            Result<com.lawu.eshop.product.dto.CommentProductInfoDTO> product = productService.selectCommentProductInfo(commentDTO.getProductModelId());
            commentProductDTO.setName(product.getModel().getName());
            commentProductDTO.setPrice(product.getModel().getPrice());
            commentProductDTO.setSpec(product.getModel().getModelName());
            commentProductDTO.setFeatureImage(product.getModel().getFeatureImage());
            commentProductDTOS.add(commentProductDTO);
        }
        pages.setCurrentPage(result.getModel().getCurrentPage());
        pages.setTotalCount(result.getModel().getTotalCount());
        pages.setRecords(commentProductDTOS);
        return successGet(pages);
    }

    @Audit(date = "2017-04-26", reviewer = "孙林青")
    @ApiOperation(value = "评价商家列表", notes = "评价商家列表 [1005，1000]（章勇）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getCommentMerchantList", method = RequestMethod.GET)
    public Result<Page<CommentMerchantInfoDTO>> getCommentMerchantAllList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token, @ModelAttribute @ApiParam(required = true) CommentListParam param) {

        Long merchantId = UserUtil.getCurrentUserId(getRequest());
        CommentMerchantListParam listParam = new CommentMerchantListParam();
        listParam.setMerchantId(merchantId);
        listParam.setCurrentPage(param.getCurrentPage());
        listParam.setPageSize(param.getPageSize());
        List<CommentMerchantInfoDTO> commentMerchantDTOS = new ArrayList<>();
        Page<CommentMerchantInfoDTO> pages = new Page<>();
        //获取评论列表
        Result<Page<CommentDTO>> result = commentService.getCommentMerchantAllList(listParam);
        if (result.getModel() == null || result.getModel().getRecords().isEmpty()) {
            pages.setCurrentPage(param.getCurrentPage());
            pages.setTotalCount(result.getModel().getTotalCount());
            pages.setRecords(new ArrayList<>());
            return successGet(pages);
        }
        for (CommentDTO commentDTO : result.getModel().getRecords()) {
            CommentMerchantInfoDTO commentMerchantDTO = new CommentMerchantInfoDTO();
            commentMerchantDTO.setAnonymous(commentDTO.getAnonymous());
            commentMerchantDTO.setContent(commentDTO.getContent());
            commentMerchantDTO.setGmtCreate(commentDTO.getGmtCreate());
            commentMerchantDTO.setReplyContent(commentDTO.getReplyContent());
            List imgs = commentDTO.getImgUrls();
            if (imgs == null) {
                imgs = new ArrayList();
            }
            commentMerchantDTO.setImgUrls(imgs);
            commentMerchantDTO.setGrade(commentDTO.getGrade());
            commentMerchantDTO.setId(commentDTO.getId());
            //查询评论用户信息
            Result<UserDTO> user = memberService.findMemberInfo(commentDTO.getMemberId());
            if(commentDTO.getAnonymous()){
                commentMerchantDTO.setHeadImg(merchantApiConfig.getDefaultHeadimg());
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

    @Audit(date = "2018-05-15", reviewer = "孙林青")
    @ApiOperation(value = "查询商品评价好评率，综合评分", notes = "查询商品评价好评率，综合评分 [1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getCommentProductAvgGrade/{productId}", method = RequestMethod.GET)
    public Result<CommentGradeDTO> getCommentAvgGrade(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                      @PathVariable("productId") @ApiParam(value = "商品ID", required = true) Long productId) {
        Result<CommentGradeDTO> result = commentService.getCommentAvgGrade(productId);
        return successGet(result);
    }

}
