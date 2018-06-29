package com.lawu.eshop.mall.srv.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.constants.CommentStatusEnum;
import com.lawu.eshop.mall.constants.CommentTypeEnum;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentMerchantParam;
import com.lawu.eshop.mall.param.PayOrderAutoCommentParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentMerchantBO;
import com.lawu.eshop.mall.srv.domain.CommentImageDO;
import com.lawu.eshop.mall.srv.domain.CommentMerchantDO;
import com.lawu.eshop.mall.srv.domain.CommentMerchantDOExample;
import com.lawu.eshop.mall.srv.mapper.CommentImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.CommentMerchantDOMapper;
import com.lawu.eshop.mall.srv.service.CommentMerchantService;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class CommentMerchantServiceImplTest {

    @Autowired
    private CommentMerchantService commentMerchantService;

    @Autowired
    private CommentMerchantDOMapper commentMerchantDOMapper;

    @Autowired
    private CommentImageDOMapper commentImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveCommentMerchantInfo(){
        Long memberId = 1L;
        CommentMerchantParam param = new CommentMerchantParam();
        param.setMerchantId(1L);
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setAvgSpend(BigDecimal.valueOf(10L));
        param.setContent("test");
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE);
        param.setOrderId(3L);
        String  pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentMerchantService.saveCommentMerchantInfo(memberId,param,pic);

        List<CommentMerchantDO> list =commentMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);

        List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(null);
        Assert.assertNotNull(commentImageDOS);
        Assert.assertEquals(2,commentImageDOS.size());
    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentMerchantAllList(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        List<CommentMerchantDO> commentMerchantDOS = commentMerchantDOMapper.selectByExample(null);

        CommentImageDO commentImageDO = new CommentImageDO();
        commentImageDO.setImgUrl("default/default_mediaUrl2.png");
        commentImageDO.setType(CommentTypeEnum.COMMENT_TYPE_MERCHANT.val);
        commentImageDO.setStatus(true);
        commentImageDO.setGmtCreate(new Date());
        commentImageDO.setCommentId(commentMerchantDOS.get(0).getId());
        commentImageDOMapper.insert(commentImageDO);

        CommentImageDO commentImageDO2 = new CommentImageDO();
        commentImageDO2.setImgUrl("default/default_mediaUrl2.png");
        commentImageDO2.setType(CommentTypeEnum.COMMENT_TYPE_MERCHANT.val);
        commentImageDO2.setStatus(true);
        commentImageDO2.setGmtCreate(new Date());
        commentImageDO2.setCommentId(commentMerchantDOS.get(0).getId());
        commentImageDOMapper.insert(commentImageDO2);

        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setMerchantId(1L);
        Page<CommentMerchantBO> page = commentMerchantService.getCommentMerchantAllList(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getTotalCount().intValue());


    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentMerchantListWithImgs(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        List<CommentMerchantDO> commentMerchantDOS = commentMerchantDOMapper.selectByExample(null);

        CommentImageDO commentImageDO = new CommentImageDO();
        commentImageDO.setImgUrl("default/default_mediaUrl2.png");
        commentImageDO.setType(CommentTypeEnum.COMMENT_TYPE_MERCHANT.val);
        commentImageDO.setStatus(true);
        commentImageDO.setGmtCreate(new Date());
        commentImageDO.setCommentId(commentMerchantDOS.get(0).getId());
        commentImageDOMapper.insert(commentImageDO);

        CommentMerchantDO commentMerchantDO2 = new CommentMerchantDO();
        commentMerchantDO2.setGmtCreate(new Date());
        commentMerchantDO2.setMerchantId(1L);
        commentMerchantDO2.setPayOrderId(2L);
        commentMerchantDO2.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO2.setContent("test");
        commentMerchantDO2.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO2.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO2.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);


        CommentMerchantListParam param = new CommentMerchantListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setMerchantId(1L);
        Page<CommentMerchantBO> page = commentMerchantService.getCommentMerchantListWithImgs(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getTotalCount().intValue());


    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findMerchantComment(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        List<CommentMerchantDO> commentMerchantDOS = commentMerchantDOMapper.selectByExample(null);
        CommentMerchantBO commentMerchantBO = commentMerchantService.findMerchantComment(commentMerchantDOS.get(0).getId(),1L);
        Assert.assertNotNull(commentMerchantBO);
        Assert.assertEquals(1,1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void replyMerchantComment(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        List<CommentMerchantDO> c = commentMerchantDOMapper.selectByExample(null);
        commentMerchantService.replyMerchantComment(c.get(0).getId(),"test");
        CommentMerchantDOExample example = new CommentMerchantDOExample();
        example.createCriteria().andReplyContentIsNotNull();
        List<CommentMerchantDO> commentMerchantDOS = commentMerchantDOMapper.selectByExample(example);
        Assert.assertNotNull(commentMerchantDOS);
        Assert.assertEquals(1,1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void getCommentAvgGrade(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        CommentGradeBO commentGradeBO =commentMerchantService.getCommentAvgGrade(1L);
        Assert.assertNotNull(commentGradeBO);
        Assert.assertNotNull(commentGradeBO.getAverageConsumeAmount());
        Assert.assertNotNull(commentGradeBO.getAvgGrade());
        Assert.assertNotNull(commentGradeBO.getGoodGrad());
        Assert.assertEquals(1,1);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void getCommentMerchantListOperator(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        CommentMerchantDO commentMerchantDO2 = new CommentMerchantDO();
        commentMerchantDO2.setGmtCreate(new Date());
        commentMerchantDO2.setMerchantId(1L);
        commentMerchantDO2.setPayOrderId(1L);
        commentMerchantDO2.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO2.setContent("test");
        commentMerchantDO2.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO2.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO2.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO2);
        CommentListParam param = new CommentListParam();
        param.setCurrentPage(1);
        param.setPageSize(10);
        param.setBeginDate(DateUtil.getDate());
        param.setEndDate(DateUtil.getDate());

        Page<CommentMerchantBO> page = commentMerchantService.getCommentMerchantListOperator(param);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(2,page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void delCommentMerchantInfo(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);
        List<CommentMerchantDO> commentMerchantDOS = commentMerchantDOMapper.selectByExample(null);

        commentMerchantService.delCommentMerchantInfo(commentMerchantDOS.get(0).getId());
        CommentMerchantDOExample example = new CommentMerchantDOExample();
        example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_INVALID.val);
        List<CommentMerchantDO> dels = commentMerchantDOMapper.selectByExample(example);

        Assert.assertEquals(1,dels.size());
        Assert.assertEquals(CommentStatusEnum.COMMENT_STATUS_INVALID.val,dels.get(0).getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getGradeByOrderId(){
        CommentMerchantDO commentMerchantDO = new CommentMerchantDO();
        commentMerchantDO.setGmtCreate(new Date());
        commentMerchantDO.setMerchantId(1L);
        commentMerchantDO.setPayOrderId(1L);
        commentMerchantDO.setMemberId(10L);
        commentMerchantDO.setAvgSpend(BigDecimal.TEN);
        commentMerchantDO.setContent("test");
        commentMerchantDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentMerchantDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentMerchantDO.setIsAnonymous(true);
        commentMerchantDOMapper.insert(commentMerchantDO);

        Byte grade = commentMerchantService.getGradeByOrderId(1L,10L);
        Assert.assertNotNull(grade);
        Assert.assertEquals(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val,grade);

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void payOrderAutoComment(){
        PayOrderAutoCommentParam param = new PayOrderAutoCommentParam();
        param.setPayOrderId(1L);
        param.setAvgSpend(BigDecimal.TEN);
        param.setMerchantId(1L);
        param.setMemberId(1L);
        commentMerchantService.payOrderAutoComment(param);
        List<CommentMerchantDO> list = commentMerchantDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertTrue(list.size() == 1);
        Assert.assertTrue(list.get(0).getGrade() == CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR.val);
    }

}
