package com.lawu.eshop.product.srv.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.EditRecommendProductCategoryParam;
import com.lawu.eshop.product.param.ListRecommendProductCategoryParam;
import com.lawu.eshop.product.srv.bo.RecommendProductCategoryBO;
import com.lawu.eshop.product.srv.converter.RecommendProductCategoryConverter;
import com.lawu.eshop.product.srv.domain.RecommendProductCategoryDO;
import com.lawu.eshop.product.srv.domain.RecommendProductCategoryDOExample;
import com.lawu.eshop.product.srv.mapper.RecommendProductCategoryDOMapper;
import com.lawu.eshop.product.srv.service.RecommendProductCategoryService;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
@Service
public class RecommendProductCategoryServiceImpl implements RecommendProductCategoryService {

    @Autowired
    private RecommendProductCategoryDOMapper recommendProductCategoryDOMapper;

    @Override
    public void saveRecommendProductCategory(EditRecommendProductCategoryParam param) {
        RecommendProductCategoryDO recommendProductCategoryDO = new RecommendProductCategoryDO();
        recommendProductCategoryDO.setCategoryId(param.getCategoryId());
        recommendProductCategoryDO.setCategoryName(param.getCategoryName());
        recommendProductCategoryDO.setIsvisible(param.getIsvisible());
        recommendProductCategoryDO.setOrdinal(param.getOrdinal());
        recommendProductCategoryDO.setGmtCreate(new Date());
        recommendProductCategoryDO.setImagePath(param.getImagePath());
        recommendProductCategoryDOMapper.insertSelective(recommendProductCategoryDO);
    }

    @Override
    public void deleteRecommendProductCategoryById(Long id) {
        recommendProductCategoryDOMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateRecommendProductCategoryById(Long id, EditRecommendProductCategoryParam param) {
        RecommendProductCategoryDO recommendProductCategoryDO = new RecommendProductCategoryDO();
        recommendProductCategoryDO.setId(id);
        recommendProductCategoryDO.setCategoryId(param.getCategoryId());
        recommendProductCategoryDO.setCategoryName(param.getCategoryName());
        recommendProductCategoryDO.setIsvisible(param.getIsvisible());
        recommendProductCategoryDO.setOrdinal(param.getOrdinal());
        recommendProductCategoryDO.setIsHot(param.getIsHot());
        recommendProductCategoryDO.setImagePath(param.getImagePath());
        recommendProductCategoryDOMapper.updateByPrimaryKeySelective(recommendProductCategoryDO);
    }

    @Override
    public RecommendProductCategoryBO getRecommendProductCategoryById(Long id) {
        RecommendProductCategoryDO recommendProductCategoryDO = recommendProductCategoryDOMapper.selectByPrimaryKey(id);
        return RecommendProductCategoryConverter.convertBO(recommendProductCategoryDO);
    }

    @Override
    public Page<RecommendProductCategoryBO> listRecommendProductCategory(ListRecommendProductCategoryParam listRecommendProductCategoryParam) {
        RecommendProductCategoryDOExample recommendProductCategoryDOExample = new RecommendProductCategoryDOExample();

        recommendProductCategoryDOExample.setOrderByClause("ordinal is null, ordinal");
        RowBounds rowBounds = new RowBounds(listRecommendProductCategoryParam.getOffset(), listRecommendProductCategoryParam.getPageSize());
        Page<RecommendProductCategoryBO> page = new Page<>();
        page.setTotalCount((int) recommendProductCategoryDOMapper.countByExample(recommendProductCategoryDOExample));
        page.setCurrentPage(listRecommendProductCategoryParam.getCurrentPage());

        List<RecommendProductCategoryDO> recommendProductCategoryDOList = recommendProductCategoryDOMapper.selectByExampleWithRowbounds(recommendProductCategoryDOExample, rowBounds);
        page.setRecords(RecommendProductCategoryConverter.convertBO(recommendProductCategoryDOList));
        return page;
    }

    @Override
    public List<RecommendProductCategoryBO> listAllRecommendProductCategory() {
        RecommendProductCategoryDOExample recommendProductCategoryDOExample = new RecommendProductCategoryDOExample();
        recommendProductCategoryDOExample.createCriteria().andIsvisibleEqualTo(true);
        recommendProductCategoryDOExample.setOrderByClause("ordinal is null, ordinal");
        List<RecommendProductCategoryDO> recommendProductCategoryDOList = recommendProductCategoryDOMapper.selectByExample(recommendProductCategoryDOExample);
        return RecommendProductCategoryConverter.convertBO(recommendProductCategoryDOList);
    }

    @Override
    public List<RecommendProductCategoryBO> getHotRecommendProductCategory() {
        RecommendProductCategoryDOExample example = new RecommendProductCategoryDOExample();
        example.createCriteria().andIsHotEqualTo(true).andIsvisibleEqualTo(true);
        example.setOrderByClause("ordinal is null, ordinal");
        List<RecommendProductCategoryDO> list = recommendProductCategoryDOMapper.selectByExample(example);
        return RecommendProductCategoryConverter.convertBO(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editHot(Long id, Boolean isHot) {
        RecommendProductCategoryDO categoryDO = new RecommendProductCategoryDO();
        categoryDO.setId(id);
        categoryDO.setIsHot(isHot);
        recommendProductCategoryDOMapper.updateByPrimaryKeySelective(categoryDO);
    }
}
