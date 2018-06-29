package com.lawu.eshop.product.srv.solr.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.solr.impl.entity.ProductSolr;

public interface ProductSolrService {
    
    /**
     * 更新商品索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void update(ProductSolr product);
    
    /**
     * 全面更新以及新增商品索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void save(ProductSolr product);
    
    /**
     * 删除所有商品索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月21日
     * @updateDate 2018年1月21日
     */
    void deleteAll();
    
    /**
     * 根据商品类别查询商品信息
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月21日
     * @updateDate 2018年1月21日
     */
    Page<ProductSolr> listProductByCategoryId(ProductSearchRealParam param);
    
    /**
     * 商品详情为你推荐(同类别按销量排行)
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月21日
     * @updateDate 2018年1月21日
     */
    Page<ProductSolr> listRecommendProduct(ProductSearchRealParam param);
    
    /**
     * 要购物首页猜你喜欢
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<ProductSolr> listYouLikeProduct(ProductSearchParam param);
    
    /**
     * 会员APP商品搜索
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<ProductSolr> listProductByName(ProductSearchRealParam param);
    
    /**
     * 根据搜索词查询相关的关键词
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<ProductSolr> findKeywordssByKeywordssStartingWith(String keyword);
    
    /**
     * 根据搜索词统计相关数据的总数
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月22日
     * @updateDate 2018年1月22日
     */
    Page<ProductSolr> findCountBykeyword(String keyword);
    
    /**
     * 查询日销量商品列表
     * @param param 查看参数
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    Page<ProductSolr> findProductSearchList(ProductSearchParam param);
    
    /**
     * 根据id集合批量删除索引
     * @param ids
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    void delete(List<Long> ids);
    
    /**
     * 根据id删除索引
     * @param ids
     * @author jiangxinjun
     * @createDate 2018年1月23日
     * @updateDate 2018年1月23日
     */
    void delete(Long id);
    
    /**
     * 批量全面更新以及新增商品索引
     * 
     * @author jiangxinjun
     * @createDate 2018年1月20日
     * @updateDate 2018年1月20日
     */
    void save(List<ProductSolr> list);

    /**
     * 商品列表
     *
     * @param param
     * @return
     * @author meishuquan
     */
    Page<ProductSolr> listProduct(ProductListSearchParam param);

}
