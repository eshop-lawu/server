package com.lawu.eshop.mall.srv.mapper.extend;

import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductPageParam;
import com.lawu.eshop.mall.srv.domain.extend.CommentProductDOView;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public interface CommentProductDOMapperExtend {

    List<CommentProductDOView> selectCommentsWithImg(CommentProductPageParam param);

    int selectCountByProductId(Long productId);

    int selectGoodGradeCount(Long productId);

    Double selectAvgGrade(Long productId);

    /**
     * 查询评论id总数按去除相同商品
     * @param pageParam
     * @return
     */
    int getCommentIdsCountByMerchantId(Long merchantId);

    List<CommentProductDOView> getProductCommentIdsByMerchantId(CommentMerchantListParam pageParam);
}
