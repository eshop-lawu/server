package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.query.FavoriteProductQuery;
import com.lawu.eshop.product.srv.bo.FavoriteProductBO;
import com.lawu.eshop.product.srv.converter.FavoriteProductConverter;
import com.lawu.eshop.product.srv.domain.FavoriteProductDO;
import com.lawu.eshop.product.srv.domain.FavoriteProductDOExample;
import com.lawu.eshop.product.srv.domain.extend.FavoriteProductView;
import com.lawu.eshop.product.srv.mapper.FavoriteProductDOMapper;
import com.lawu.eshop.product.srv.mapper.extend.FavoriteProductDOMapperExtend;
import com.lawu.eshop.product.srv.service.FavoriteProductService;
import com.lawu.eshop.product.srv.service.ProductService;
import com.lawu.framework.core.page.Page;

/**
 * 收藏商品接口实现类
 * 
 * @author zhangrc
 * @date 2017/03/30
 *
 */
@Service
public class FavoriteProductServiceImpl implements FavoriteProductService {

	@Autowired
	private FavoriteProductDOMapper favoriteProductDOMapper;

	@Autowired
	private FavoriteProductDOMapperExtend favoriteProductDOExtendMapper;

	@Autowired
	private ProductService productService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer save(Long memberId, Long productId) {
		FavoriteProductDOExample example = new FavoriteProductDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(productId);
		int count = favoriteProductDOMapper.countByExample(example);
		if (count == 1) {
			return 0;
		}
		FavoriteProductDO favoriteProductDO = new FavoriteProductDO();
		favoriteProductDO.setMemberId(memberId);
		favoriteProductDO.setProductId(productId);
		favoriteProductDO.setGmtCreate(new Date());
		Integer id = favoriteProductDOMapper.insert(favoriteProductDO);

		productService.editTotalFavorite(productId, 1, "A");

		return id;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer remove(Long productId, Long memberId) {
		FavoriteProductDOExample example = new FavoriteProductDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(productId);
		Integer i = favoriteProductDOMapper.deleteByExample(example);

		productService.editTotalFavorite(productId, 1, "M");

		return i;
	}

	@Override
	public Page<FavoriteProductBO> selectMyFavoriteProduct(Long memberId, FavoriteProductQuery query) {
		FavoriteProductDOExample example = new FavoriteProductDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		int count = favoriteProductDOMapper.countByExample(example);
		FavoriteProductView favoriteProductView = new FavoriteProductView();
		favoriteProductView.setMemberId(memberId);
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		List<FavoriteProductView> DOS = favoriteProductDOExtendMapper
				.selectMyFavoriteProductByRowbounds(favoriteProductView, rowBounds);
		Page<FavoriteProductBO> page = new Page<FavoriteProductBO>();
		page.setTotalCount(count);
		List<FavoriteProductBO> memberBOS = FavoriteProductConverter.convertBOS(DOS);
		page.setRecords(memberBOS);
		page.setCurrentPage(query.getCurrentPage());
		return page;
	}

	@Override
	public Integer getUserFavorite(Long productId, Long memberId) {
		FavoriteProductDOExample example = new FavoriteProductDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andProductIdEqualTo(productId);
		int count = favoriteProductDOMapper.countByExample(example);
		return count;
	}

	@Override
	public Integer getFavoriteProductCount(Long memberId) {
		FavoriteProductDOExample example = new FavoriteProductDOExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		return favoriteProductDOMapper.countByExample(example);
	}

}
