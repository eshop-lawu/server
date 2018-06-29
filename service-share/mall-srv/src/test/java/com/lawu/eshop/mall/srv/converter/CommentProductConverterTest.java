package com.lawu.eshop.mall.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentOperatorDTO;
import com.lawu.eshop.mall.dto.CommentProductIdDTO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mall.srv.domain.CommentProductDO;
import com.lawu.eshop.mall.srv.domain.extend.CommentProductDOView;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class CommentProductConverterTest {

    @Test
    public void converterBO() {
        CommentProductDO productDO = new CommentProductDO();
        productDO.setContent("test");
        productDO.setGmtCreate(new Date());
        productDO.setIsAnonymous(true);
        productDO.setReplyContent("reply");
        productDO.setMemberId(1L);
        productDO.setProductId(2L);
        productDO.setProductModelId(1L);
        productDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        productDO.setId(1L);
        CommentProductBO commentProductBO = CommentProductConverter.converterBO(productDO);
        Assert.assertTrue(productDO.getContent().equals(commentProductBO.getContent()));
        Assert.assertTrue(productDO.getGmtCreate().equals(commentProductBO.getGmtCreate()));
        Assert.assertTrue(productDO.getIsAnonymous().equals(commentProductBO.isAnonymous()));
        Assert.assertTrue(productDO.getReplyContent().equals(commentProductBO.getReplyContent()));
        Assert.assertTrue(productDO.getMemberId().equals(commentProductBO.getMemberId()));
        Assert.assertTrue(productDO.getProductId().equals(commentProductBO.getProductId()));
        Assert.assertTrue(productDO.getProductModelId().equals(commentProductBO.getProductModelId()));
        Assert.assertTrue(productDO.getGrade().equals(commentProductBO.getGrade()));
        Assert.assertTrue(productDO.getId().equals(commentProductBO.getId()));


    }

    @Test
    public void converterDTOS() {
        List<CommentProductBO> commentProductBOS = new ArrayList<>();
        CommentProductBO commentProductBO = new CommentProductBO();
        commentProductBO.setGmtCreate(new Date());
        commentProductBO.setContent("test");
        commentProductBO.setAnonymous(true);
        commentProductBO.setUrlImgs(new ArrayList());
        commentProductBO.setMemberId(1L);
        commentProductBO.setProductId(1L);
        commentProductBO.setProductModelId(1L);
        commentProductBO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductBO.setReplyContent("test");
        commentProductBO.setId(1l);
        commentProductBOS.add(commentProductBO);
        List<CommentDTO> list = CommentProductConverter.converterDTOS(commentProductBOS);
        Assert.assertTrue(commentProductBOS.get(0).getContent().equals(list.get(0).getContent()));
        Assert.assertTrue(commentProductBOS.get(0).getGmtCreate().equals(list.get(0).getGmtCreate()));
        Assert.assertTrue(commentProductBOS.get(0).isAnonymous() == list.get(0).getAnonymous());
        Assert.assertTrue(commentProductBOS.get(0).getReplyContent().equals(list.get(0).getReplyContent()));
        Assert.assertTrue(commentProductBOS.get(0).getMemberId().equals(list.get(0).getMemberId()));
        Assert.assertTrue(commentProductBOS.get(0).getProductId().equals(list.get(0).getProductId()));
        Assert.assertTrue(commentProductBOS.get(0).getProductModelId().equals(list.get(0).getProductModelId()));
        Assert.assertTrue(commentProductBOS.get(0).getGrade().equals(list.get(0).getGrade()));
        Assert.assertTrue(commentProductBOS.get(0).getId().equals(list.get(0).getId()));
    }

    @Test
    public void converterBOFromView() {
        CommentProductDOView productDOView = new CommentProductDOView();
        productDOView.setContent("test");
        productDOView.setGmtCreate(new Date());
        productDOView.setAnonymous(true);
        productDOView.setReplyContent("test");
        productDOView.setMemberId(1L);
        productDOView.setProductId(1L);
        productDOView.setProductModelId(1l);
        productDOView.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);

        CommentProductBO commentProductBO = CommentProductConverter.converterBOFromView(productDOView);
        Assert.assertTrue(productDOView.getContent().equals(commentProductBO.getContent()));
        Assert.assertTrue(productDOView.getGmtCreate().equals(commentProductBO.getGmtCreate()));
        Assert.assertTrue(productDOView.getAnonymous().equals(commentProductBO.isAnonymous()));
        Assert.assertTrue(productDOView.getReplyContent().equals(commentProductBO.getReplyContent()));
        Assert.assertTrue(productDOView.getMemberId().equals(commentProductBO.getMemberId()));
        Assert.assertTrue(productDOView.getProductId().equals(commentProductBO.getProductId()));
        Assert.assertTrue(productDOView.getProductModelId().equals(commentProductBO.getProductModelId()));
        Assert.assertTrue(productDOView.getGrade().equals(commentProductBO.getGrade()));

    }

    @Test
    public void converterOperatorDTOS(){
        List<CommentProductBO> commentProductBOS = new ArrayList<>();
        CommentProductBO commentProductBO = new CommentProductBO();
        commentProductBO.setGmtCreate(new Date());
        commentProductBO.setContent("test");
        commentProductBO.setId(1L);
        commentProductBO.setProductId(1L);
        commentProductBO.setUrlImgs(new ArrayList());
        commentProductBO.setMemberId(1L);
        commentProductBOS.add(commentProductBO);
        List<CommentOperatorDTO> list = CommentProductConverter.converterOperatorDTOS(commentProductBOS);
        Assert.assertTrue(commentProductBOS.get(0).getContent().equals(list.get(0).getContent()));
        Assert.assertTrue(commentProductBOS.get(0).getGmtCreate().equals(list.get(0).getGmtCreate()));
        Assert.assertTrue(commentProductBOS.get(0).getMemberId().equals(list.get(0).getMemberId()));
        Assert.assertTrue(commentProductBOS.get(0).getProductId().equals(list.get(0).getCommentToId()));
        Assert.assertTrue(commentProductBOS.get(0).getUrlImgs().equals(list.get(0).getUrlImgs()));
        Assert.assertTrue(commentProductBOS.get(0).getId().equals(list.get(0).getId()));
    }

    @Test
    public void converterProIdDTOS(){
        List<CommentProductBO> commentProductBOS  = new ArrayList<>();
        CommentProductBO commentProductBO = new CommentProductBO();
        commentProductBO.setProductId(1L);
        commentProductBO.setProductModelId(1L);
        commentProductBOS.add(commentProductBO);
        List<CommentProductIdDTO> list = CommentProductConverter.converterProIdDTOS(commentProductBOS);
        Assert.assertTrue(commentProductBOS.get(0).getProductModelId().equals(list.get(0).getProductModelId()));
        Assert.assertTrue(commentProductBOS.get(0).getProductId().equals(list.get(0).getProductId()));
    }
}
