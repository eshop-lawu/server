package com.lawu.eshop.mall.srv.mapper.extend;

import com.lawu.eshop.mall.param.CommentMerchantPageParam;
import com.lawu.eshop.mall.srv.domain.extend.CommentMerchantDOView;

import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public interface CommentMerchantDOMapperExtend {

    /**
     * 查询商家综合评分
     *
     * @param merchantId
     * @return
     */
    Double selectAvgGrade(Long merchantId);

    /**
     * 查询有图评价总数
     *
     * @param merchantId
     * @return
     */
    int selectCountByMerchantId(Long merchantId);

    /**
     * 查询有图评价信息列表
     *
     * @param merchantPageParam
     * @return
     */
    List<CommentMerchantDOView> selectCommentsWithImg(CommentMerchantPageParam merchantPageParam);

    Integer selectGoodGradeCount(Long merchantId);

    /**
     * 查询人均消费
     *
     * @param merchantId
     * @return
     */
    Double getAvgSpend(Long merchantId);
}
