package com.lawu.eshop.product.srv.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductSearchWordDTO;
import com.lawu.eshop.product.param.ProductListSearchParam;
import com.lawu.eshop.product.param.ProductSearchParam;
import com.lawu.eshop.product.param.ProductSearchRealParam;
import com.lawu.eshop.product.srv.converter.ProductConverter;
import com.lawu.eshop.product.srv.solr.service.ProductSolrService;
import com.lawu.eshop.solr.impl.entity.ProductSolr;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2017/3/30.
 */
@RestController
@RequestMapping(value = "productSolr/")
public class ProductSolrController extends BaseController {

    @Autowired
    private ProductSolrService productSolrService;

    /**
     * 根据商品类别查询商品信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "listProductByCategoryId", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listProductByCategoryId(@RequestBody ProductSearchRealParam param) {
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.listProductByCategoryId(param);
        if (productSolrPage == null || productSolrPage.getContent() == null || productSolrPage.getContent().isEmpty()) {
            return successCreated(ResultCode.NOT_FOUND_DATA);
        }
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertProductSearchDTOList(productSolrPage.getContent()));
        page.setTotalCount((int) productSolrPage.getTotalElements());
        page.setCurrentPage(param.getCurrentPage());
        return successCreated(page);
    }

    /**
     * 商品详情为你推荐(同类别按销量排行)
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "listRecommendProduct", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listRecommendProduct(@RequestBody ProductSearchRealParam param) {
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.listRecommendProduct(param);
        if (productSolrPage == null || productSolrPage.getContent() == null || productSolrPage.getContent().isEmpty()) {
            return successCreated(ResultCode.NOT_FOUND_DATA);
        }
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertProductSearchDTOList(productSolrPage.getContent()));
        page.setTotalCount((int) productSolrPage.getTotalElements());
        page.setCurrentPage(param.getCurrentPage());
        return successCreated(page);
    }

    /**
     * 要购物首页猜你喜欢
     *
     * @param productSearchParam
     * @return
     */
    @RequestMapping(value = "listYouLikeProduct", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listYouLikeProduct(@RequestBody ProductSearchParam productSearchParam) {
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.listYouLikeProduct(productSearchParam);
        if (productSolrPage == null || productSolrPage.getContent() == null || productSolrPage.getContent().isEmpty()) {
            return successCreated(ResultCode.NOT_FOUND_DATA);
        }
        Page<ProductSearchDTO> page = new Page<>();
        page.setRecords(ProductConverter.convertProductSearchDTOList(productSolrPage.getContent()));
        page.setTotalCount((int) productSolrPage.getTotalElements());
        page.setCurrentPage(productSearchParam.getCurrentPage());
        return successCreated(page);
    }

    /**
     * 会员APP商品搜索
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "listProductByName", method = RequestMethod.POST)
    public Result<Page<ProductSearchDTO>> listProductByName(@RequestBody ProductSearchRealParam param) {
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.listProductByName(param);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setRecords(ProductConverter.convertProductSearchDTOList(productSolrPage.getContent()));
        page.setTotalCount((int) productSolrPage.getTotalElements());
        return successCreated(page);
    }

    /**
     * 搜索词关联词频查询
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "listProductSearchWord", method = RequestMethod.GET)
    public Result<List<ProductSearchWordDTO>> listStoreSearchWord(@RequestParam String name) {
        List<ProductSearchWordDTO> searchWordDTOS = new ArrayList<>();
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.findKeywordssByKeywordssStartingWith(name);
        if (productSolrPage == null || productSolrPage.getContent() == null || productSolrPage.getContent().isEmpty()) {
            return successGet(searchWordDTOS);
        }
        for (ProductSolr item : productSolrPage.getContent()) {
            for (String keyword : item.getKeywordss()) {
                if (keyword.trim().startsWith(name)) {
                    ProductSearchWordDTO searchWordDTO = new ProductSearchWordDTO();
                    searchWordDTO.setName(keyword.trim());
                    org.springframework.data.domain.Page<ProductSolr> countPage = productSolrService.findCountBykeyword(keyword);
                    searchWordDTO.setCount((int) countPage.getTotalElements());
                    searchWordDTOS.add(searchWordDTO);
                }
            }
        }

        Set<ProductSearchWordDTO> searchWordDTOSet = new TreeSet<>((o1, o2) -> o1.getName().compareTo(o2.getName()));
        searchWordDTOSet.addAll(searchWordDTOS);
        List<ProductSearchWordDTO> resultDTOS = new ArrayList<>(searchWordDTOSet);
        return successGet(resultDTOS);
    }

    /**
     * 查询日销量商品列表
     * @param searchParam
     * @return
     * @author zhangy
     */
    @RequestMapping(method = RequestMethod.POST, value = "findProductSearchList")
    public List<ProductSearchDTO> findProductSearchList(@RequestBody  ProductSearchParam searchParam){
        org.springframework.data.domain.Page<ProductSolr> countPage = productSolrService.findProductSearchList(searchParam);
        return  ProductConverter.convertProductSearchDTOList(countPage.getContent());
    }

    /**
     * 商品列表
     *
     * @param param
     * @return
     * @author meishuquan
     */
    @RequestMapping(method = RequestMethod.POST, value = "listProduct")
    public Result<Page<ProductSearchDTO>> listProduct(@RequestBody ProductListSearchParam param) {
        org.springframework.data.domain.Page<ProductSolr> productSolrPage = productSolrService.listProduct(param);
        Page<ProductSearchDTO> page = new Page<>();
        page.setCurrentPage(param.getCurrentPage());
        page.setRecords(ProductConverter.convertProductSearchDTOList(productSolrPage.getContent()));
        page.setTotalCount((int) productSolrPage.getTotalElements());
        return successCreated(page);
    }

}
