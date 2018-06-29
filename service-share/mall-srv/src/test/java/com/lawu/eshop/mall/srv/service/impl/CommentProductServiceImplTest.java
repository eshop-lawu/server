package com.lawu.eshop.mall.srv.service.impl;

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
import com.lawu.eshop.mall.dto.MemberProductCommentDTO;
import com.lawu.eshop.mall.param.CommentListParam;
import com.lawu.eshop.mall.param.CommentMerchantListParam;
import com.lawu.eshop.mall.param.CommentProductListParam;
import com.lawu.eshop.mall.param.CommentProductParam;
import com.lawu.eshop.mall.srv.MallSrvApplicationTest;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mall.srv.domain.CommentImageDO;
import com.lawu.eshop.mall.srv.domain.CommentProductDO;
import com.lawu.eshop.mall.srv.domain.CommentProductDOExample;
import com.lawu.eshop.mall.srv.mapper.CommentImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.CommentProductDOMapper;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderAutoCommentNotification;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

/**
 * @author zhangyong
 * @date 2017/7/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MallSrvApplicationTest.class)
public class CommentProductServiceImplTest {

    @Autowired
    private CommentProductService commentProductService;

    @Autowired
    private CommentProductDOMapper commentProductDOMapper;

    @Autowired
    private CommentImageDOMapper commentImageDOMapper;

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveCommentProductInfo(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        List<CommentProductDO> list =commentProductDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());

        List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(null);
        Assert.assertNotNull(commentImageDOS);
        Assert.assertEquals(2,commentImageDOS.size());


    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentProducts(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        CommentProductListParam listParam =  new CommentProductListParam();
        listParam.setProductId(1L);
        listParam.setCurrentPage(1);
        listParam.setPageSize(10);
        Page<CommentProductBO> page = commentProductService.getCommentProducts(listParam);
        Assert.assertNotNull(page);
        Assert.assertEquals(1,page.getTotalCount().intValue());

    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentProductsWithImgs(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        commentProductService.saveCommentProductInfo(1L,param,"");

        CommentProductListParam listParam =  new CommentProductListParam();
        listParam.setProductId(1L);
        listParam.setCurrentPage(1);
        listParam.setPageSize(10);
        Page<CommentProductBO> page = commentProductService.getCommentProductsWithImgs(listParam);
        Assert.assertNotNull(page);
        Assert.assertEquals(1,page.getTotalCount().intValue());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void findProductComment(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);

        CommentProductBO commentProductBO = commentProductService.findProductComment(list.get(0).getId(),1L);
        Assert.assertNotNull(commentProductBO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void replyProductComment(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);

        commentProductService.replyProductComment(list.get(0).getId(),"replay");

        CommentProductDOExample example = new CommentProductDOExample();
        example.createCriteria().andReplyContentIsNotNull();
        List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExample(example);
        Assert.assertNotNull(commentProductDOS);
        Assert.assertEquals(1,commentProductDOS.size());

    }


    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delCommentProductInfo(){
        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setMemberId(1L);
        commentProductDO.setGmtCreate(new Date());
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setContent("test");
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDO.setProductModelId(1L);
        commentProductDOMapper.insert(commentProductDO);
        commentProductService.delCommentProductInfo(commentProductDO.getId());
        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals(CommentStatusEnum.COMMENT_STATUS_INVALID.val,list.get(0).getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getCommentAvgGrade(){
        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setMemberId(1L);
        commentProductDO.setGmtCreate(new Date());
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDO.setProductModelId(1L);
        commentProductDOMapper.insert(commentProductDO);

        CommentGradeBO commentGradeBO = commentProductService.getCommentAvgGrade(1L);

        Assert.assertNotNull(commentGradeBO);
        Assert.assertNotNull(commentGradeBO.getAvgGrade());
        Assert.assertNotNull(commentGradeBO.getGoodGrad());

    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void getCommentProductListOperator(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setContent("test2");
        commentProductDO.setGmtCreate(new Date());
        commentProductDO.setMemberId(1L);
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setProductModelId(1L);
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDOMapper.insert(commentProductDO);
        List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExample(null);
        Assert.assertEquals(2,commentProductDOS.size());

        CommentListParam param2 = new CommentListParam();
        param2.setCurrentPage(1);
        param2.setPageSize(10);
        param2.setBeginDate(DateUtil.getDate());
        param2.setEndDate(DateUtil.getDate());
        Page<CommentProductBO> page =  commentProductService.getCommentProductListOperator(param2);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(2,page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void saveCommentProductInfoOrderJob(){

        CommentProductDO commentProductDO = new CommentProductDO();
        commentProductDO.setContent("test2");
        commentProductDO.setGmtCreate(DateUtil.getDayBefore(new Date()));
        commentProductDO.setMemberId(1L);
        commentProductDO.setMerchantId(1L);
        commentProductDO.setProductId(1L);
        commentProductDO.setProductModelId(1L);
        commentProductDO.setOrderItemId(1L);
        commentProductDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
        commentProductDO.setIsAnonymous(true);
        commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
        commentProductDOMapper.insert(commentProductDO);


        ShoppingOrderAutoCommentNotification notification = new ShoppingOrderAutoCommentNotification();
        notification.setMemberId(1L);
        notification.setProductModelId(1L);
        notification.setMerchantId(1L);
        notification.setProductId(1L);
        notification.setShoppingOrderItem(1L);
        notification.setTransactionId(1L);
        commentProductService.saveCommentProductInfoOrderJob(notification);
        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentListByMerchantId(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        CommentMerchantListParam param1 =new CommentMerchantListParam();
        param1.setMerchantId(1L);
        param1.setCurrentPage(1);
        param1.setPageSize(10);
        Page<CommentProductBO>  page=  commentProductService.getProductCommentListByMerchantId(param1);
        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentIdsByMerchantId(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        CommentMerchantListParam listParam = new CommentMerchantListParam();
        listParam.setMerchantId(1L);
        listParam.setCurrentPage(1);
        listParam.setPageSize(10);
        Page<CommentProductBO>  page =  commentProductService.getProductCommentIdsByMerchantId(listParam);

        Assert.assertNotNull(page.getRecords());
        Assert.assertEquals(1,page.getRecords().size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public  void delCommentByProductModelId(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        commentProductService.delCommentByProductModelId(1L);

        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals(CommentStatusEnum.COMMENT_STATUS_INVALID.val,list.get(0).getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void delCommentByOrderItemId(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        commentProductService.delCommentByOrderItemId(1L);
        List<CommentProductDO> list = commentProductDOMapper.selectByExample(null);
        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals(CommentStatusEnum.COMMENT_STATUS_INVALID.val,list.get(0).getStatus());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void geNewlyProductComment(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);

        List<MemberProductCommentDTO> list = commentProductService.geNewlyProductComment(1L);

        Assert.assertNotNull(list);
        Assert.assertEquals(1,list.size());
    }

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void getProductCommentCount(){
        CommentProductParam param = new CommentProductParam();
        param.setContent("test");
        param.setAnonymousEnum(CommentAnonymousEnum.COMMENT_ANONYMOUS);
        param.setMerchantId(1L);
        param.setGradeEnum(CommentGradeEnum.COMMENT_STAR_LEVEL_FOUR);
        param.setProductId(1L);
        param.setProductModelId(1L);
        param.setShoppingOrderItemId(1L);
        String pic = "default/default_mediaUrl.png,default/default_mediaUrl2.png";
        commentProductService.saveCommentProductInfo(1L,param,pic);
        commentProductService.saveCommentProductInfo(2L,param,pic);
        commentProductService.saveCommentProductInfo(3L,param,pic);
        Integer count = commentProductService.getProductCommentCount(1L);
        Assert.assertEquals(3,count.intValue());

    }

}
