package com.lawu.eshop.product.srv.solr.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.eshop.solr.impl.BaseSolrServiceImpl;
import com.lawu.eshop.solr.impl.entity.ProductSolr;
import com.lawu.eshop.solr.impl.repository.ProductSolrRepository;

@Service
public class ProductSolrServiceImpl extends BaseSolrServiceImpl<ProductSolr, Long> implements ProductSolrService {

    private ProductSolrRepository repository;

    @Autowired
    private void setRepository(ProductSolrRepository repository) {
        this.repository = repository;
        super.init(repository);
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(ProductSolr product) {
        ProductSolr productSolr = repository.findOne(product.getId());
        if (product.getAverageDailySales() != null) {
            productSolr.setAverageDailySales(product.getAverageDailySales());
        }
        if (product.getCategoryId() != null) {
            productSolr.setCategoryId(product.getCategoryId());
        }
        if (product.getFeatureImage() != null) {
            productSolr.setFeatureImage(product.getFeatureImage());
        }
        if (product.getInventory() != null) {
            productSolr.setInventory(product.getInventory());
        }
        if (product.getName() != null) {
            productSolr.setName(product.getName());
        }
        if (product.getOriginalPrice() != null) {
            productSolr.setOriginalPrice(product.getOriginalPrice());
        }
        if (product.getPrice() != null) {
            productSolr.setPrice(product.getPrice());
        }
        if (product.getSalesVolume() != null) {
            productSolr.setSalesVolume(product.getSalesVolume());
        }
        repository.save(productSolr);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Page<ProductSolr> listProductByCategoryId(ProductSearchRealParam param) {
        Sort sort = new Sort(Direction.DESC, "averageDailySales_d", "id");
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize(), sort);
        return repository.findByCategoryId(param.getCategoryId(), null, pageable);
    }

    @Override
    public Page<ProductSolr> listRecommendProduct(ProductSearchRealParam param) {
        Sort sort = new Sort(Direction.DESC, "salesVolume_i", "id");
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize(), sort);
        return repository.findByCategoryId(param.getCategoryId(), param.getProductId().toString(), pageable);
    }

    @Override
    public Page<ProductSolr> listYouLikeProduct(ProductSearchParam param) {
        Sort sort = new Sort(Direction.DESC, "averageDailySales_d", "id");
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize(), sort);
        return repository.findAll(pageable);
    }

    @Override
    public Page<ProductSolr> listProductByName(ProductSearchRealParam param) {
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize());
        return repository.listProductByName(param.getName(), pageable);
    }

    @Override
    public Page<ProductSolr> findKeywordssByKeywordssStartingWith(String keyword) {
        Pageable pageable = new SolrPageRequest(0, 10);
        return repository.findKeywordssByKeywordssStartingWith(keyword, pageable);
    }

    @Override
    public Page<ProductSolr> findCountBykeyword(String keyword) {
        Pageable pageable = new SolrPageRequest(0, 10);
        return repository.findCountBykeyword(keyword, pageable);
    }

    @Override
    public Page<ProductSolr> findProductSearchList(ProductSearchParam param) {
        Sort sort = new Sort(Direction.DESC, "averageDailySales_d", "id");
        Pageable pageable = new SolrPageRequest(param.getCurrentPage() - 1, param.getPageSize(), sort);
        return repository.findAll(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(ProductSolr product) {
        repository.save(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Long> ids) {
        List<ProductSolr> idsToDelete = new ArrayList<>();
        for (Long id : ids) {
            ProductSolr item = new ProductSolr();
            item.setId(id);
            idsToDelete.add(item);
        }
        repository.delete(idsToDelete);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<ProductSolr> list) {
        repository.save(list);
    }

    @Override
    public Page<ProductSolr> listProduct(ProductListSearchParam param) {
        StringBuilder sb = new StringBuilder("");
        if (StringUtils.isNotEmpty(param.getName())) {
            sb.append("text:").append(param.getName()).append(" AND ");
        }
        if (param.getCategoryId() != null && param.getCategoryId() > 0) {
            sb.append("categoryId_i:").append(param.getCategoryId()).append(" AND ");
        }
        if (param.getCategorySubitemId() != null && param.getCategorySubitemId() > 0) {
            sb.append("categorySubitemId_i:").append(param.getCategorySubitemId()).append(" AND ");
        }
        if (param.getBrandId() != null && param.getBrandId() > 0) {
            sb.append("brandId_i:").append(param.getBrandId()).append(" AND ");
        }
        if (param.getCityId() != null && param.getCityId() > 0) {
            sb.append("cityId_i:").append(param.getCityId()).append(" AND ");
        }
        if (param.getMinPrice() != null && param.getMaxPrice() != null && param.getMinPrice() >= 0 && param.getMaxPrice() >= 0) {
            sb.append("price_d:[").append(param.getMinPrice()).append(" TO ").append(param.getMaxPrice()).append("]").append(" AND ");
        } else {
            if (param.getMinPrice() != null && param.getMinPrice() > 0) {
                sb.append("price_d:[").append(param.getMinPrice()).append(" TO 99999999]").append(" AND ");
            } else if (param.getMaxPrice() != null && param.getMaxPrice() > 0) {
                sb.append("price_d:[0 TO ").append(param.getMaxPrice()).append("]").append(" AND ");
            }
        }
        if (param.getIsExpressFree() != null) {
            sb.append("expressFree_i:").append(param.getIsExpressFree() ? 1 : 0).append(" AND ");
        }
        if (param.getIsAllowRefund() != null) {
            sb.append("allowRefund_i:").append(param.getIsAllowRefund() ? 1 : 0).append(" AND ");
        }
        if (param.getIsBrand() != null && param.getIsBrand()) {
            sb.append("-brandId_i:").append(0).append(" AND ");
        }

        SolrQuery query = new SolrQuery();
        if (StringUtils.isNotEmpty(sb.toString().trim())) {
            query.setParam("q", sb.substring(0, sb.length() - 5));
        } else {
            query.setQuery("*:*");
        }
        if (param.getSortEnum() != null) {
            query.setParam("sort", param.getSortEnum().getVal() + ",averageDailySales_d desc");
        } else {
            query.setParam("sort", "averageDailySales_d desc");
        }
        query.setStart(param.getOffset());
        query.setRows(param.getPageSize());
        return query(query, SolrRequest.METHOD.POST);
    }

}
