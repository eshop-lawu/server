package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.dto.MemberProductCommentDTO;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mq.dto.order.ShoppingOrderAutoCommentNotification;
import com.lawu.framework.core.page.Page;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
public interface CommentProductService {
    /**
     * 新增评价信息
     * @param memberId
     * @param param
     * @param headImg
     * @return
     */
    Integer saveCommentProductInfo(Long memberId, CommentProductParam param,String headImg);

    /**
     * 商品评价列表
     * @param listParam
     * @return
     */
    Page<CommentProductBO> getCommentProducts(CommentProductListParam listParam);

    /**
     * 查询有图评价
     * @param listParam
     * @return
     */
    Page<CommentProductBO> getCommentProductsWithImgs(CommentProductListParam listParam);

    CommentProductBO findProductComment(Long commentId, Long merchantId);

    Integer replyProductComment(Long commentId, String replyContent);

    void delCommentProductInfo(Long commentId);

    CommentGradeBO getCommentAvgGrade(Long productId);

    Page<CommentProductBO> getCommentProductListOperator(CommentListParam listParam);

    /**
     * 定时器增加默认好评
     * @param notification
     */
    void saveCommentProductInfoOrderJob(ShoppingOrderAutoCommentNotification notification);

    Page<CommentProductBO> getProductCommentListByMerchantId(CommentMerchantListParam pageParam);

    Page<CommentProductBO> getProductCommentIdsByMerchantId(CommentMerchantListParam pageParam);

    void delCommentByProductModelId(Long productModelId);

    void delCommentByOrderItemId(Long orderItemId);

    /**
     * 用户端商品详情，查询商品最近1条商品评价
     * @param productId
     * @return
     * @author yangqh
     */
	List<MemberProductCommentDTO> geNewlyProductComment(Long productId);

	/**
     * 用户端商品详情，查询商品评价数量
     * @param productId
     * @return
     * @author yangqh
     */
	Integer getProductCommentCount(Long productId);
}
