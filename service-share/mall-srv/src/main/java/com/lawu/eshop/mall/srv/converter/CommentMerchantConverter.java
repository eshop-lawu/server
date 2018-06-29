package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentOperatorDTO;
import com.lawu.eshop.mall.srv.bo.CommentMerchantBO;
import com.lawu.eshop.mall.srv.domain.CommentMerchantDO;
import com.lawu.eshop.mall.srv.domain.extend.CommentMerchantDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentMerchantConverter {
    /**
     * 商家评价BO转换
     *
     * @param commentMerchantDO
     * @return
     */
    public static CommentMerchantBO converBO(CommentMerchantDO commentMerchantDO) {
        if (commentMerchantDO == null) {
            return null;
        }
        CommentMerchantBO commentMerchantBO = new CommentMerchantBO();
        commentMerchantBO.setContent(commentMerchantDO.getContent());
        commentMerchantBO.setGmtCreate(commentMerchantDO.getGmtCreate());
        commentMerchantBO.setAnonymous(commentMerchantDO.getIsAnonymous());
        commentMerchantBO.setReplyContent(commentMerchantDO.getReplyContent());
        commentMerchantBO.setMemberId(commentMerchantDO.getMemberId());
        commentMerchantBO.setMerchantId(commentMerchantDO.getMerchantId());
        commentMerchantBO.setGrade(commentMerchantDO.getGrade());
        commentMerchantBO.setAvgSpend(commentMerchantDO.getAvgSpend());
        commentMerchantBO.setId(commentMerchantDO.getId());
        return commentMerchantBO;
    }

    public static List<CommentDTO> converterDTOS(List<CommentMerchantBO> commentMerchantBOS) {
        if (commentMerchantBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentMerchantBO commentMerchantBO : commentMerchantBOS) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setGmtCreate(commentMerchantBO.getGmtCreate());
            commentDTO.setContent(commentMerchantBO.getContent());
            commentDTO.setAnonymous(commentMerchantBO.isAnonymous());
            commentDTO.setImgUrls(commentMerchantBO.getUrlImgs());
            commentDTO.setMemberId(commentMerchantBO.getMemberId());
            commentDTO.setId(commentMerchantBO.getId());
            commentDTO.setGrade(commentMerchantBO.getGrade());
            commentDTO.setReplyContent(commentMerchantBO.getReplyContent());
            commentDTO.setAvgSpend(commentMerchantBO.getAvgSpend());
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    public static CommentMerchantBO converterBOFromView(CommentMerchantDOView commentMerchantDOView) {
        if (commentMerchantDOView == null) {
            return null;
        }
        CommentMerchantBO commentMerchantBO = new CommentMerchantBO();
        commentMerchantBO.setContent(commentMerchantDOView.getContent());
        commentMerchantBO.setGmtCreate(commentMerchantDOView.getGmtCreate());
        commentMerchantBO.setAnonymous(commentMerchantDOView.getAnonymous());
        commentMerchantBO.setReplyContent(commentMerchantDOView.getReplyContent());
        commentMerchantBO.setMemberId(commentMerchantDOView.getMemberId());
        commentMerchantBO.setGrade(commentMerchantDOView.getGrade());
        return commentMerchantBO;
    }

    public static List<CommentOperatorDTO> converterOperatorDTOS(List<CommentMerchantBO> commentMerchantBOS) {
        if (commentMerchantBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<CommentOperatorDTO> commentOperatorDTOS = new ArrayList<>();
        for (CommentMerchantBO commentMerchantBO : commentMerchantBOS) {
            CommentOperatorDTO commentOperatorDTO = new CommentOperatorDTO();
            commentOperatorDTO.setGmtCreate(commentMerchantBO.getGmtCreate());
            commentOperatorDTO.setContent(commentMerchantBO.getContent());
            commentOperatorDTO.setId(commentMerchantBO.getId());
            commentOperatorDTO.setCommentToId(commentMerchantBO.getMerchantId());
            commentOperatorDTO.setUrlImgs(commentMerchantBO.getUrlImgs());
            commentOperatorDTO.setMemberId(commentMerchantBO.getMemberId());
            commentOperatorDTOS.add(commentOperatorDTO);
        }
        return commentOperatorDTOS;
    }
}
