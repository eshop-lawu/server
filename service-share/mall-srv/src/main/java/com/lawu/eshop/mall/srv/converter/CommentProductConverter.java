package com.lawu.eshop.mall.srv.converter;

import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentOperatorDTO;
import com.lawu.eshop.mall.dto.CommentProductIdDTO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mall.srv.domain.CommentProductDO;
import com.lawu.eshop.mall.srv.domain.extend.CommentProductDOView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/6.
 */
public class CommentProductConverter {

    /**
     * 商品评价BO转换
     * @param commentProductDO
     * @return
     */
    public static CommentProductBO converterBO(CommentProductDO commentProductDO) {
        if (commentProductDO == null) {
            return null;
        }
        CommentProductBO commentProductBO = new CommentProductBO();
        commentProductBO.setContent(commentProductDO.getContent());
        commentProductBO.setGmtCreate(commentProductDO.getGmtCreate());
        commentProductBO.setAnonymous(commentProductDO.getIsAnonymous());
        commentProductBO.setReplyContent(commentProductDO.getReplyContent());
        commentProductBO.setMemberId(commentProductDO.getMemberId());
        commentProductBO.setProductId(commentProductDO.getProductId());
        commentProductBO.setProductModelId(commentProductDO.getProductModelId());
        commentProductBO.setGrade(commentProductDO.getGrade());
        commentProductBO.setId(commentProductDO.getId());
        return commentProductBO;
    }

    public static List<CommentDTO> converterDTOS(List<CommentProductBO> commentProductBOS) {
        if(commentProductBOS.isEmpty()){
            return new ArrayList<>();
        }
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentProductBO commentProductBO : commentProductBOS){
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setGmtCreate(commentProductBO.getGmtCreate());
            commentDTO.setContent(commentProductBO.getContent());
            commentDTO.setAnonymous(commentProductBO.isAnonymous());
            commentDTO.setImgUrls(commentProductBO.getUrlImgs());
            commentDTO.setMemberId(commentProductBO.getMemberId());
            commentDTO.setProductId(commentProductBO.getProductId());
            commentDTO.setProductModelId(commentProductBO.getProductModelId());
            commentDTO.setGrade(commentProductBO.getGrade());
            commentDTO.setReplyContent(commentProductBO.getReplyContent());
            commentDTO.setId(commentProductBO.getId());
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }

    public static CommentProductBO converterBOFromView(CommentProductDOView commentProductDOView) {
        if (commentProductDOView == null) {
            return null;
        }
        CommentProductBO commentProductBO = new CommentProductBO();
        commentProductBO.setContent(commentProductDOView.getContent());
        commentProductBO.setGmtCreate(commentProductDOView.getGmtCreate());
        commentProductBO.setAnonymous(commentProductDOView.getAnonymous());
        commentProductBO.setReplyContent(commentProductDOView.getReplyContent());
        commentProductBO.setMemberId(commentProductDOView.getMemberId());
        commentProductBO.setProductId(commentProductDOView.getProductId());
        commentProductBO.setProductModelId(commentProductDOView.getProductModelId());
        commentProductBO.setGrade(commentProductDOView.getGrade());
        return commentProductBO;
    }

    public static List<CommentOperatorDTO> converterOperatorDTOS(List<CommentProductBO> commentProductBOS) {
        if(commentProductBOS.isEmpty()){
            return new ArrayList<>();
        }
        List<CommentOperatorDTO> commentOperatorDTOS = new ArrayList<>();
        for (CommentProductBO commentProductBO : commentProductBOS){
            CommentOperatorDTO commentOperatorDTO = new CommentOperatorDTO();
            commentOperatorDTO.setGmtCreate(commentProductBO.getGmtCreate());
            commentOperatorDTO.setContent(commentProductBO.getContent());
            commentOperatorDTO.setId(commentProductBO.getId());
            commentOperatorDTO.setCommentToId(commentProductBO.getProductId());
            commentOperatorDTO.setUrlImgs(commentProductBO.getUrlImgs());
            commentOperatorDTO.setMemberId(commentProductBO.getMemberId());
            commentOperatorDTOS.add(commentOperatorDTO);
        }
        return commentOperatorDTOS;
    }


    public static List<CommentProductIdDTO> converterProIdDTOS(List<CommentProductBO> commentProductBOS) {
        if(commentProductBOS.isEmpty()){
            return new ArrayList<>();
        }
        List<CommentProductIdDTO> commentProductIdDTOS = new ArrayList<>();
        for (CommentProductBO commentProductBO : commentProductBOS){
            CommentProductIdDTO commentProductIdDTO = new CommentProductIdDTO();
            commentProductIdDTO.setProductId(commentProductBO.getProductId());
            commentProductIdDTO.setProductModelId(commentProductBO.getProductModelId());
            commentProductIdDTOS.add(commentProductIdDTO);
        }
        return commentProductIdDTOS;
    }
}
