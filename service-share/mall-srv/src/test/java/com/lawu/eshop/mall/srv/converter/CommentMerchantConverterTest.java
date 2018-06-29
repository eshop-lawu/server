package com.lawu.eshop.mall.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.dto.CommentDTO;
import com.lawu.eshop.mall.dto.CommentOperatorDTO;
import com.lawu.eshop.mall.srv.bo.CommentMerchantBO;
import com.lawu.eshop.mall.srv.domain.CommentMerchantDO;
import com.lawu.eshop.mall.srv.domain.extend.CommentMerchantDOView;

/**
 * @author zhangyong
 * @date 2017/7/19.
 */
public class CommentMerchantConverterTest {

    @Test
    public void converBO(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setReplyContent("reply");
        commentMerchantDO.setMemberId(1L);
        commentMerchantDO.setMerchantId(2L);
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setId(1L);
        CommentMerchantBO commentMerchantBO = CommentMerchantConverter.converBO(commentMerchantDO);
        Assert.assertTrue(commentMerchantDO.getReplyContent().equals(commentMerchantBO.getReplyContent()));
        Assert.assertTrue(commentMerchantDO.getContent().equals(commentMerchantBO.getContent()));
        Assert.assertTrue(commentMerchantDO.getMerchantId().equals(commentMerchantBO.getMerchantId()));
        Assert.assertTrue(commentMerchantDO.getMemberId().equals(commentMerchantBO.getMemberId()));
        Assert.assertTrue(commentMerchantDO.getGrade().equals(commentMerchantBO.getGrade()));
        Assert.assertTrue(commentMerchantDO.getAvgSpend().equals(commentMerchantBO.getAvgSpend()));
        Assert.assertTrue(commentMerchantDO.getId().equals(commentMerchantBO.getId()));
    }

    @Test
    public void converterDTOS(){
        List<CommentMerchantBO> list = new ArrayList<>();
        CommentMerchantBO commentMerchantBO = new CommentMerchantBO();
        commentMerchantBO.setAnonymous(true);
        commentMerchantBO.setContent("test");
        commentMerchantBO.setGmtCreate(new Date());
        commentMerchantBO.setReplyContent("reply");
        commentMerchantBO.setMemberId(1L);
        commentMerchantBO.setMerchantId(2L);
        commentMerchantBO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantBO.setAvgSpend(BigDecimal.TEN);
        commentMerchantBO.setId(1L);
        list.add(commentMerchantBO);
        List<CommentDTO> commentDTOS = CommentMerchantConverter.converterDTOS(list);
        Assert.assertTrue(commentDTOS.get(0).getReplyContent().equals(list.get(0).getReplyContent()));
        Assert.assertTrue(commentDTOS.get(0).getContent().equals(list.get(0).getContent()));
        Assert.assertTrue(commentDTOS.get(0).getMemberId().equals(list.get(0).getMemberId()));
        Assert.assertTrue(commentDTOS.get(0).getGrade().equals(list.get(0).getGrade()));
        Assert.assertTrue(commentDTOS.get(0).getAvgSpend().equals(list.get(0).getAvgSpend()));
        Assert.assertTrue(commentDTOS.get(0).getId().equals(list.get(0).getId()));
    }

    @Test
    public void converterBOFromView(){
        CommentMerchantDOView merchantDOView = new CommentMerchantDOView();
        merchantDOView.setContent("test");
        merchantDOView.setGmtCreate(new Date());
        merchantDOView.setAnonymous(true);
        merchantDOView.setReplyContent("reply");
        merchantDOView.setMemberId(1L);
        merchantDOView.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        CommentMerchantBO commentMerchantBO = CommentMerchantConverter.converterBOFromView(merchantDOView);
        Assert.assertTrue(merchantDOView.getReplyContent().equals(commentMerchantBO.getReplyContent()));
        Assert.assertTrue(merchantDOView.getContent().equals(commentMerchantBO.getContent()));
        Assert.assertTrue(merchantDOView.getMemberId().equals(commentMerchantBO.getMemberId()));
        Assert.assertTrue(merchantDOView.getGrade().equals(commentMerchantBO.getGrade()));
        Assert.assertTrue(merchantDOView.getGmtCreate().equals(commentMerchantBO.getGmtCreate()));
        Assert.assertTrue(merchantDOView.getAnonymous().equals(commentMerchantBO.isAnonymous()));
    }

    @Test
    public void converterOperatorDTOS(){
        List<CommentMerchantBO> commentMerchantBOS = new ArrayList<>();
        CommentMerchantBO commentMerchantBO = new CommentMerchantBO();
        commentMerchantBO.setGmtCreate(new Date());
        commentMerchantBO.setContent("test");
        commentMerchantBO.setId(1L);
        commentMerchantBO.setMerchantId(2L);
        commentMerchantBO.setUrlImgs(new ArrayList());
        commentMerchantBO.setMemberId(1L);
        commentMerchantBOS.add(commentMerchantBO);
        List<CommentOperatorDTO> list = CommentMerchantConverter.converterOperatorDTOS(commentMerchantBOS);
        Assert.assertTrue(commentMerchantBOS.get(0).getGmtCreate().equals(list.get(0).getGmtCreate()));
        Assert.assertTrue(commentMerchantBOS.get(0).getContent().equals(list.get(0).getContent()));
        Assert.assertTrue(commentMerchantBOS.get(0).getMemberId().equals(list.get(0).getMemberId()));
        Assert.assertTrue(commentMerchantBOS.get(0).getId().equals(list.get(0).getId()));
        Assert.assertTrue(commentMerchantBOS.get(0).getMemberId().equals(list.get(0).getMemberId()));
        Assert.assertTrue(commentMerchantBOS.get(0).getUrlImgs().equals(list.get(0).getUrlImgs()));
    }

}
