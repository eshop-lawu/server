package com.lawu.eshop.mall.srv.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lawu.compensating.transaction.service.TransactionMainService;
import com.lawu.eshop.mall.constants.CommentAnonymousEnum;
import com.lawu.eshop.mall.constants.CommentGradeEnum;
import com.lawu.eshop.mall.constants.CommentStatusEnum;
import com.lawu.eshop.mall.constants.CommentTypeEnum;
import com.lawu.eshop.mall.dto.MemberProductCommentDTO;
import com.lawu.eshop.mall.param.*;
import com.lawu.eshop.mall.srv.bo.CommentGradeBO;
import com.lawu.eshop.mall.srv.bo.CommentProductBO;
import com.lawu.eshop.mall.srv.converter.CommentProductConverter;
import com.lawu.eshop.mall.srv.domain.CommentImageDO;
import com.lawu.eshop.mall.srv.domain.CommentImageDOExample;
import com.lawu.eshop.mall.srv.domain.CommentProductDO;
import com.lawu.eshop.mall.srv.domain.CommentProductDOExample;
import com.lawu.eshop.mall.srv.domain.extend.CommentProductDOView;
import com.lawu.eshop.mall.srv.mapper.CommentImageDOMapper;
import com.lawu.eshop.mall.srv.mapper.CommentProductDOMapper;
import com.lawu.eshop.mall.srv.mapper.extend.CommentProductDOMapperExtend;
import com.lawu.eshop.mall.srv.service.CommentProductService;
import com.lawu.eshop.mq.dto.order.ShoppingOrderAutoCommentNotification;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DateUtil;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/4/5.
 */
@Service
public class CommentProductServiceImpl implements CommentProductService {

	@Autowired
	private CommentProductDOMapper commentProductDOMapper;
	@Autowired
	private CommentImageDOMapper commentImageDOMapper;
	@Autowired
	private CommentProductDOMapperExtend commentProductDOMapperExtend;

	@Autowired
	@Qualifier("orderCommentProductTransactionMainServiceImpl")
	private TransactionMainService transactionMainService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer saveCommentProductInfo(Long memberId, CommentProductParam param, String headImg) {
		CommentProductDO commentProductDO = new CommentProductDO();
		commentProductDO.setMemberId(memberId);
		commentProductDO.setContent(param.getContent());
		commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
		commentProductDO.setIsAnonymous(param.getAnonymousEnum().val);// 匿名
		commentProductDO.setOrderItemId(param.getShoppingOrderItemId());
		commentProductDO.setMerchantId(param.getMerchantId());
		commentProductDO.setProductModelId(param.getProductModelId());
		commentProductDO.setGrade(param.getGradeEnum().val);
		commentProductDO.setProductId(param.getProductId());
		commentProductDO.setGmtCreate(new Date());
		commentProductDO.setGmtModified(new Date());
		if(StringUtils.isEmpty(headImg) || ",".equals(headImg)){//无图
			commentProductDO.setIsHaveImg(false);
		}else{
			commentProductDO.setIsHaveImg(true);
		}
		commentProductDOMapper.insert(commentProductDO);// 新增评价信息
		Long id = commentProductDO.getId();
		if (!StringUtils.isEmpty(headImg)) {
			String[] imgs = headImg.split(",");
			// 新增评价图片
			for (int i = 0; i < imgs.length; i++) {
				if (!StringUtils.isEmpty(imgs[i])) {
					CommentImageDO commentImageDO = new CommentImageDO();
					commentImageDO.setCommentId(id);
					commentImageDO.setImgUrl(imgs[i]);
					commentImageDO.setStatus(true);// 有效
					commentImageDO.setType(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val);// 评论商品
					commentImageDO.setGmtCreate(new Date());
					commentImageDO.setGmtModified(new Date());
					commentImageDOMapper.insert(commentImageDO);
				}
			}
		}
		// 更新评价状态 发消息
		transactionMainService.sendNotice(param.getShoppingOrderItemId());
		return id.intValue();
	}

	@Override
	public Page<CommentProductBO> getCommentProducts(CommentProductListParam listParam) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_VALID.val)
				.andProductIdEqualTo(listParam.getProductId());
		example.setOrderByClause("id desc");
		RowBounds rowBounds = new RowBounds(listParam.getOffset(), listParam.getPageSize());
		Page<CommentProductBO> page = new Page<>();
		page.setTotalCount(commentProductDOMapper.countByExample(example));
		page.setCurrentPage(listParam.getCurrentPage());

		// 查询评价列表
		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExampleWithRowbounds(example,
				rowBounds);
		List<CommentProductBO> commentProductBOS = new ArrayList<>();
		for (CommentProductDO commentProductDO : commentProductDOS) {
			CommentProductBO commentProductBO = CommentProductConverter.converterBO(commentProductDO);
			CommentImageDOExample imageDOExample = new CommentImageDOExample();
			imageDOExample.createCriteria().andCommentIdEqualTo(commentProductDO.getId())
					.andTypeEqualTo(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val);
			List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(imageDOExample);
			List<String> images = new ArrayList<String>();
			if (!commentImageDOS.isEmpty()) {
				for (int i = 0; i < commentImageDOS.size(); i++) {
					images.add(commentImageDOS.get(i).getImgUrl());
				}
				commentProductBO.setUrlImgs(images);
			}

			commentProductBOS.add(commentProductBO);
		}
		page.setRecords(commentProductBOS);
		return page;
	}

	@Override
	public Page<CommentProductBO> getCommentProductsWithImgs(CommentProductListParam listParam) {

		int totalCount = commentProductDOMapperExtend.selectCountByProductId(listParam.getProductId());

		Page<CommentProductBO> commentProductBOPage = new Page<>();
		commentProductBOPage.setTotalCount(totalCount);
		commentProductBOPage.setCurrentPage(listParam.getCurrentPage());

		CommentProductPageParam productPageParam = new CommentProductPageParam();
		productPageParam.setCurrentPage(listParam.getCurrentPage());
		productPageParam.setPageSize(listParam.getPageSize());
		productPageParam.setProductId(listParam.getProductId());
		// 查询评论列表信息
		List<CommentProductDOView> commentProductDOViews = commentProductDOMapperExtend
				.selectCommentsWithImg(productPageParam);

		Page<CommentProductBO> pages = new Page<>();
		List<CommentProductBO> commentProductBOS = new ArrayList<>();
		if (!commentProductDOViews.isEmpty()) {
			for (CommentProductDOView commentProductDOView : commentProductDOViews) {
				CommentProductBO commentProductBO = CommentProductConverter.converterBOFromView(commentProductDOView);
				// 查询对应的评价图片
				CommentImageDOExample imageDOExample = new CommentImageDOExample();
				imageDOExample.createCriteria().andCommentIdEqualTo(commentProductDOView.getId())
						.andTypeEqualTo(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val);
				List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(imageDOExample);
				List<String> images = new ArrayList<>();
				if (!commentImageDOS.isEmpty()) {
					for (int i = 0; i < commentImageDOS.size(); i++) {
						images.add(commentImageDOS.get(i).getImgUrl());
					}
					commentProductBO.setUrlImgs(images);
				}
				commentProductBOS.add(commentProductBO);
			}
		}
		pages.setCurrentPage(listParam.getCurrentPage());
		pages.setTotalCount(totalCount);
		pages.setRecords(commentProductBOS);
		return pages;
	}

	@Override
	public CommentProductBO findProductComment(Long commentId, Long merchantId) {
		CommentProductDOExample example =  new CommentProductDOExample();
		example.createCriteria().andIdEqualTo(commentId).andMerchantIdEqualTo(merchantId);
		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExample(example);
		if(commentProductDOS.isEmpty()){
			return null;
		}
		CommentProductBO commentProductBO = CommentProductConverter.converterBO(commentProductDOS.get(0));
		return commentProductBO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer replyProductComment(Long commentId, String replyContent) {
		CommentProductDO commentProductDO = new CommentProductDO();
		commentProductDO.setId(commentId);
		commentProductDO.setReplyContent(replyContent);
		commentProductDO.setGmtReply(new Date());
		return commentProductDOMapper.updateByPrimaryKeySelective(commentProductDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delCommentProductInfo(Long commentId) {
		CommentProductDO commentProductDO = new CommentProductDO();
		commentProductDO.setId(commentId);
		commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_INVALID.val);
		commentProductDOMapper.updateByPrimaryKeySelective(commentProductDO);
	}

	@Override
	public CommentGradeBO getCommentAvgGrade(Long productId) {
		CommentProductDOExample commentProductDOExample = new CommentProductDOExample();
		commentProductDOExample.createCriteria().andProductIdEqualTo(productId);
		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExample(commentProductDOExample);
		if (commentProductDOS.isEmpty()) {
			return null;
		}
		Double avgGrade = commentProductDOMapperExtend.selectAvgGrade(productId);
		avgGrade = new BigDecimal(avgGrade).setScale(2, RoundingMode.UP).doubleValue();
		Integer goodCount = commentProductDOMapperExtend.selectGoodGradeCount(productId);
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_VALID.val)
				.andProductIdEqualTo(productId);
		Integer totalCount = commentProductDOMapper.countByExample(example);
		double goodGrade =  BigDecimal.valueOf((double) goodCount / totalCount).setScale(2, RoundingMode.UP).doubleValue();
		CommentGradeBO commentGradeBO = new CommentGradeBO();
		commentGradeBO.setAvgGrade(avgGrade);
		commentGradeBO.setGoodGrad(goodGrade);
		return commentGradeBO;
	}

	@Override
	public Page<CommentProductBO> getCommentProductListOperator(CommentListParam listParam) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.setOrderByClause("id DESC");
		CommentProductDOExample.Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo(new Byte("1"));
		if(org.apache.commons.lang.StringUtils.isNotEmpty(listParam.getBeginDate())){
			criteria.andGmtCreateGreaterThanOrEqualTo(DateUtil.stringToDate(listParam.getBeginDate() + " 00:00:00"));
		}
		if(org.apache.commons.lang.StringUtils.isNotEmpty(listParam.getEndDate())){
			criteria.andGmtCreateLessThanOrEqualTo(DateUtil.stringToDate(listParam.getEndDate() + " 23:59:59"));
		}
		RowBounds rowBounds = new RowBounds(listParam.getOffset(), listParam.getPageSize());
		Page<CommentProductBO> page = new Page<>();
		page.setTotalCount(commentProductDOMapper.countByExample(example));
		page.setCurrentPage(listParam.getCurrentPage());

		// 查询评价列表
		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExampleWithRowbounds(example,
				rowBounds);
		if (commentProductDOS.isEmpty()) {
			return null;
		}
		List<CommentProductBO> commentProductBOS = new ArrayList<>();
		for (CommentProductDO commentProductDO : commentProductDOS) {
			CommentProductBO commentProductBO = CommentProductConverter.converterBO(commentProductDO);
			CommentImageDOExample commentImageExample=new CommentImageDOExample();
			commentImageExample.createCriteria().andCommentIdEqualTo(commentProductDO.getId())
			.andTypeEqualTo(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val).andStatusEqualTo(true);
			
			 List<CommentImageDO>  list=commentImageDOMapper.selectByExample(commentImageExample);
			 
			 List<String> listImg=new ArrayList<>();
			 for (CommentImageDO commentImageDO : list) {
				 listImg.add(commentImageDO.getImgUrl());
			 }
			commentProductBO.setUrlImgs(listImg);
			commentProductBOS.add(commentProductBO);
		}
		page.setRecords(commentProductBOS);
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCommentProductInfoOrderJob(ShoppingOrderAutoCommentNotification notification) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andOrderItemIdEqualTo(notification.getShoppingOrderItem());
		List<CommentProductDO> oldComment = commentProductDOMapper.selectByExample(example);
		if (!oldComment.isEmpty()) {
			return;
		}
		CommentProductDO productDO = new CommentProductDO();
		productDO.setMemberId(notification.getMemberId());
		productDO.setMerchantId(notification.getMerchantId());
		productDO.setProductId(notification.getProductId());
		productDO.setProductModelId(notification.getProductModelId());
		productDO.setOrderItemId(notification.getShoppingOrderItem());
		productDO.setIsAnonymous(CommentAnonymousEnum.COMMENT_ANONYMOUS.val);// 匿名
		productDO.setContent("好评");
		productDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
		productDO.setStatus(CommentStatusEnum.COMMENT_STATUS_VALID.val);
		productDO.setGmtModified(new Date());
		productDO.setGmtCreate(new Date());
		productDO.setGrade(CommentGradeEnum.COMMENT_STAR_LEVEL_FIVE.val);
		commentProductDOMapper.insert(productDO);
	}

	@Override
	public Page<CommentProductBO> getProductCommentListByMerchantId(CommentMerchantListParam pageParam) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_VALID.val)
				.andMerchantIdEqualTo(pageParam.getMerchantId());
		example.setOrderByClause("id desc");
		RowBounds rowBounds = new RowBounds(pageParam.getOffset(), pageParam.getPageSize());
		Page<CommentProductBO> page = new Page<>();
		page.setTotalCount(commentProductDOMapper.countByExample(example));
		page.setCurrentPage(pageParam.getCurrentPage());

		// 查询评价列表
		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExampleWithRowbounds(example,
				rowBounds);
		List<CommentProductBO> commentProductBOS = new ArrayList<>();
		for (CommentProductDO commentProductDO : commentProductDOS) {
			CommentProductBO commentProductBO = CommentProductConverter.converterBO(commentProductDO);
			CommentImageDOExample imageDOExample = new CommentImageDOExample();
			imageDOExample.createCriteria().andCommentIdEqualTo(commentProductDO.getId())
					.andTypeEqualTo(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val);
			List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(imageDOExample);
			List<String> images = new ArrayList<>();
			if (!commentImageDOS.isEmpty()) {
				for (int i = 0; i < commentImageDOS.size(); i++) {
					images.add(commentImageDOS.get(i).getImgUrl());
				}
				commentProductBO.setUrlImgs(images);
			}

			commentProductBOS.add(commentProductBO);
		}
		page.setRecords(commentProductBOS);
		return page;
	}

	@Override
	public Page<CommentProductBO> getProductCommentIdsByMerchantId(CommentMerchantListParam pageParam) {

		int totalCount = commentProductDOMapperExtend.getCommentIdsCountByMerchantId(pageParam.getMerchantId());
		if (totalCount == 0 || totalCount < 0) {
			return null;
		}
		List<CommentProductDOView> productDOViews = commentProductDOMapperExtend
				.getProductCommentIdsByMerchantId(pageParam);
		List<CommentProductBO> commentProductBOS = new ArrayList<>();
		for (CommentProductDOView commentProductDOView : productDOViews) {
			CommentProductBO commentProductBO = new CommentProductBO();
			commentProductBO.setProductId(commentProductDOView.getProductId());
			commentProductBOS.add(commentProductBO);
		}
		Page<CommentProductBO> page = new Page<>();
		page.setRecords(commentProductBOS);
		page.setCurrentPage(pageParam.getCurrentPage());
		page.setTotalCount(totalCount);
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delCommentByProductModelId(Long productModelId) {
		CommentProductDO commentProductDO = new CommentProductDO();
		commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_INVALID.val);
		commentProductDO.setGmtModified(new Date());
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andProductModelIdEqualTo(productModelId);
		commentProductDOMapper.updateByExampleSelective(commentProductDO,example);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delCommentByOrderItemId(Long orderItemId) {
		CommentProductDO commentProductDO = new CommentProductDO();
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andOrderItemIdEqualTo(orderItemId);
		commentProductDO.setStatus(CommentStatusEnum.COMMENT_STATUS_INVALID.val);
		commentProductDO.setGmtModified(new Date());
		commentProductDOMapper.updateByExampleSelective(commentProductDO, example);
	}

	@Override
	public List<MemberProductCommentDTO> geNewlyProductComment(Long productId) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_VALID.val).andProductIdEqualTo(productId);
		example.setOrderByClause(" id desc ");
		RowBounds rowBounds = new RowBounds(0, 1);

		List<CommentProductDO> commentProductDOS = commentProductDOMapper.selectByExampleWithRowbounds(example,rowBounds);
		List<MemberProductCommentDTO> dtos = new ArrayList<>();
		for (CommentProductDO comment : commentProductDOS) {
			MemberProductCommentDTO dto = new MemberProductCommentDTO();
			dto.setContent(comment.getContent());
			dto.setGmtCreate(comment.getGmtCreate());
			dto.setGrade(comment.getGrade());

			CommentImageDOExample imageDOExample = new CommentImageDOExample();
			imageDOExample.createCriteria().andCommentIdEqualTo(comment.getId()).andTypeEqualTo(CommentTypeEnum.COMMENT_TYPE_PRODUCT.val).andStatusEqualTo(true);
			List<CommentImageDO> commentImageDOS = commentImageDOMapper.selectByExample(imageDOExample);
			List<String> images = new ArrayList<>();
			if (!commentImageDOS.isEmpty()) {
				for (int i = 0; i < commentImageDOS.size(); i++) {
					images.add(commentImageDOS.get(i).getImgUrl());
				}
			}
			dto.setImgUrls(images);
			dto.setIsAnonymous(comment.getIsAnonymous());
			dto.setMemberId(comment.getMemberId());
			dto.setProductModelId(comment.getProductModelId());
			dto.setReplyContent(comment.getReplyContent());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public Integer getProductCommentCount(Long productId) {
		CommentProductDOExample example = new CommentProductDOExample();
		example.createCriteria().andStatusEqualTo(CommentStatusEnum.COMMENT_STATUS_VALID.val).andProductIdEqualTo(productId);
		return commentProductDOMapper.countByExample(example);
	}
}
