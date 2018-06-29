package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentMerchantBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public interface CommentMerchantService {
    /**
     * 新增买单评价商家
     * @param memberId
     * @param param
     * @param commentPic
     * @return
     */
    Integer saveCommentMerchantInfo(Long memberId, CommentMerchantParam param, String commentPic);

    /**
     * 获取全部商家评价列表
     * @param listParam
     * @return
     */
    Page<CommentMerchantBO> getCommentMerchantAllList(CommentMerchantListParam listParam);

    /**
     * 获取有图评价商家列表
     * @param listParam
     * @return
     */
    Page<CommentMerchantBO> getCommentMerchantListWithImgs(CommentMerchantListParam listParam);

    /**
     * 根据评价ID找到对应的评价商家信息
     * @param commentId
     * @return
     */
    CommentMerchantBO findMerchantComment(Long commentId, Long merchantId);

    /**
     * 回复商家评价
     * @param commentId
     * @param replyContent
     * @return
     */
    int replyMerchantComment(Long commentId, String replyContent);

    CommentGradeBO getCommentAvgGrade(Long merchantId);

    Page<CommentMerchantBO> getCommentMerchantListOperator(CommentListParam listParam);

    void delCommentMerchantInfo(Long commentId);

    Byte getGradeByOrderId(Long payOrderId, Long memberId);

    void payOrderAutoComment(PayOrderAutoCommentParam param);

    /**
     * 根据买单id统计商家评论总数
     *
     * @param payOrderId
     * @return
     * @author meishuquan
     */
    Integer countCommentMerchantByPayOrderId(Long payOrderId);
}
