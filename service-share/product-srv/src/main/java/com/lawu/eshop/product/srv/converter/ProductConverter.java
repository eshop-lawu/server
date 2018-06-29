package com.lawu.eshop.product.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.dto.FreightDTO;
import com.lawu.eshop.product.constant.ProductPositionEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.ProductAllDTO;
import com.lawu.eshop.product.dto.ProductCategoryItemDTO;
import com.lawu.eshop.product.dto.ProductCategoryTierDTO;
import com.lawu.eshop.product.dto.ProductCityDTO;
import com.lawu.eshop.product.dto.ProductDetailDTO;
import com.lawu.eshop.product.dto.ProductEditDetailDTO;
import com.lawu.eshop.product.dto.ProductInfoDTO;
import com.lawu.eshop.product.dto.ProductListDTO;
import com.lawu.eshop.product.dto.ProductModelInfoDTO;
import com.lawu.eshop.product.dto.ProductQueryDTO;
import com.lawu.eshop.product.dto.ProductSearchDTO;
import com.lawu.eshop.product.dto.ProductSpecJsonDTO;
import com.lawu.eshop.product.dto.ProductTypeCountDTO;
import com.lawu.eshop.product.param.EditProductDataParam;
import com.lawu.eshop.product.param.EditProductDataParam_bak;
import com.lawu.eshop.product.param.FreightParam;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import com.lawu.eshop.product.srv.bo.ProductAllBO;
import com.lawu.eshop.product.srv.bo.ProductBO;
import com.lawu.eshop.product.srv.bo.ProductCategoryTierBO;
import com.lawu.eshop.product.srv.bo.ProductDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditDetailBO;
import com.lawu.eshop.product.srv.bo.ProductEditInfoBO;
import com.lawu.eshop.product.srv.bo.ProductInfoBO;
import com.lawu.eshop.product.srv.bo.ProductListBO;
import com.lawu.eshop.product.srv.bo.ProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ProductQueryBO;
import com.lawu.eshop.product.srv.bo.ProductSearchBO;
import com.lawu.eshop.product.srv.bo.ProductTypeCountBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.extend.ProductCategoryItemDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductCityDOView;
import com.lawu.eshop.product.srv.domain.extend.ProductDOView;
import com.lawu.eshop.product.srv.domain.extend.ShoppingProductDOView;
import com.lawu.eshop.solr.impl.entity.ProductSolr;
import com.lawu.utils.DateUtil;
import com.lawu.utils.StringUtil;

/**
 * 会员信息转换器
 *
 * @author Yangqh
 * @date 2017/3/13
 */
public class ProductConverter {

    /**
     * BO转换
     *
     * @param productBOS
     * @return
     */
    public static List<ProductQueryDTO> convertDTOS(List<ProductQueryBO> productBOS) {
        List<ProductQueryDTO> productDTOS = new ArrayList<ProductQueryDTO>();
        if (productBOS == null || productBOS.isEmpty()) {
            return productDTOS;
        }

        for (ProductQueryBO productBO : productBOS) {
            ProductQueryDTO productDTO = new ProductQueryDTO();
            productDTO.setId(productBO.getId());
            productDTO.setMerchantId(productBO.getMerchantId());
            productDTO.setName(productBO.getName());
            productDTO.setCategory(productBO.getCategory());
            productDTO.setFeatureImage(productBO.getFeatureImage());
            productDTO.setGmtCreate(productBO.getGmtCreate());
            productDTO.setSpec(productBO.getSpec());
            productDTO.setStatus(productBO.getStatus());
            productDTO.setTotalInventory(productBO.getTotalInventory());
            productDTO.setTotalSalesVolume(productBO.getTotalSalesVolume());
            productDTO.setTotalFavorite(productBO.getTotalFavorite());
            productDTO.setMinPrice(productBO.getMinPrice());
            productDTO.setRemark(productBO.getRemark());
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    /**
     * BO转换
     *
     * @param productDO
     * @return
     */
    public static ProductQueryBO convertQueryBO(ProductDO productDO) {
        ProductQueryBO productBO = new ProductQueryBO();
        productBO.setId(productDO.getId());
        productBO.setMerchantId(productDO.getMerchantId());
        productBO.setName(productDO.getName());
        productBO.setRemark(productDO.getRemark());
        productBO.setFeatureImage(productDO.getFeatureImage());
        productBO.setGmtCreate(DateUtil.getDateFormat(productDO.getGmtCreate(), "yyyy-MM-dd"));
        productBO.setStatus(ProductStatusEnum.getEnum(productDO.getStatus()));
        productBO.setTotalInventory(productDO.getTotalInventory() == null ? "0" : productDO.getTotalInventory().toString());
        productBO.setTotalSalesVolume(productDO.getTotalSalesVolume() == null ? "0" : productDO.getTotalSalesVolume().toString());
        productBO.setTotalFavorite(productDO.getTotalFavorite() == null ? "0" : productDO.getTotalFavorite().toString());
        productBO.setMinPrice(productDO.getMinPrice() == null ? "0" : productDO.getMinPrice().toString());
        return productBO;
    }

    public static ProductQueryDTO convertDTO(ProductQueryBO productBO) {
        ProductQueryDTO productDTO = new ProductQueryDTO();
        productDTO.setId(productBO.getId());
        productDTO.setName(productBO.getName());
        productDTO.setCategory(productBO.getCategory());
        productDTO.setFeatureImage(productBO.getFeatureImage());
        productDTO.setGmtCreate(productBO.getGmtCreate());
        productDTO.setSpec(productBO.getSpec());
        productDTO.setStatus(productBO.getStatus());
        return productDTO;
    }

    /**
     * 用户端商品详情BO转换
     *
     * @param productDO
     * @return
     */
    public static ProductInfoBO convertInfoBO(ProductDO productDO) {
        ProductInfoBO productInfoBO = new ProductInfoBO();
        productInfoBO.setId(productDO.getId());
        productInfoBO.setName(productDO.getName());
        productInfoBO.setFeatureImage(productDO.getFeatureImage());
        productInfoBO.setContent(productDO.getContent());
        productInfoBO.setMerchantId(productDO.getMerchantId());
        productInfoBO.setMerchantNum(productDO.getMerchantNum());
        productInfoBO.setTotalSalesVolume(productDO.getTotalSalesVolume());
        productInfoBO.setTotalInventory(productDO.getTotalInventory());
        productInfoBO.setMaxPrice(String.valueOf(productDO.getMaxPrice()));
        productInfoBO.setMinPrice(String.valueOf(productDO.getMinPrice()));
        productInfoBO.setGmtCreate(productDO.getGmtCreate());
        productInfoBO.setCategoryId(productDO.getCategoryId());
        productInfoBO.setAllowRefund(productDO.getIsAllowRefund());
        productInfoBO.setProductStatus(ProductStatusEnum.getEnum(productDO.getStatus()));
        productInfoBO.setSpecInfo(productDO.getSpec());
        return productInfoBO;
    }

    /**
     * 商家编辑商品时商品详情BO转换
     *
     * @param productDO
     * @return
     */
    public static ProductEditInfoBO convertEditInfoBO(ProductDO productDO) {
        ProductEditInfoBO productEditInfoBO = new ProductEditInfoBO();
        productEditInfoBO.setId(productDO.getId());
        productEditInfoBO.setName(productDO.getName());
        productEditInfoBO.setCategory(Long.valueOf(productDO.getCategoryId()));
        productEditInfoBO.setFeatureImage(productDO.getFeatureImage());
        productEditInfoBO.setContent(productDO.getContent());
        productEditInfoBO.setMerchantId(productDO.getMerchantId());
        
        String imageContent = (productDO.getImageContent() == null || "".equals(productDO.getImageContent())) ? "[]" : productDO.getImageContent();
        List<String> imageContents = StringUtil.getJsonListToStringList(imageContent);
        productEditInfoBO.setImageContent(imageContents);
        
        productEditInfoBO.setAllowRefund(productDO.getIsAllowRefund());
        productEditInfoBO.setKeywords(productDO.getKeywords());
        return productEditInfoBO;
    }

    /**
     * 用户端商品详情DTO转换
     *
     * @param productBO
     * @return
     */
    public static ProductInfoDTO convertInfoDTO(ProductInfoBO productBO) {
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        productInfoDTO.setId(productBO.getId());
        productInfoDTO.setMerchantId(productBO.getMerchantId());
        productInfoDTO.setName(productBO.getName());
        productInfoDTO.setFeatureImage(productBO.getFeatureImage());
        productInfoDTO.setContent(productBO.getContent());
        productInfoDTO.setImagesHeadUrl(productBO.getImagesHeadUrl());
        productInfoDTO.setSpec(productBO.getSpec());
        productInfoDTO.setMaxPrice(productBO.getMaxPrice());
        productInfoDTO.setMinPrice(productBO.getMinPrice());
        productInfoDTO.setTotalSalesVolume(productBO.getTotalSalesVolume());
        productInfoDTO.setGmtCreate(productBO.getGmtCreate());
        return productInfoDTO;
    }

    /**
     * BO转换
     *
     * @param productDOList
     * @return
     */
    public static List<ProductInfoBO> convertInfoBO(List<ProductDO> productDOList) {
        List<ProductInfoBO> productInfoBOS = new ArrayList<>();
        if (productDOList == null || productDOList.isEmpty()) {
            return productInfoBOS;
        }
        for (ProductDO productDO : productDOList) {
            productInfoBOS.add(convertInfoBO(productDO));
        }
        return productInfoBOS;
    }

    /**
     * DTO转换
     *
     * @param productInfoBOList
     * @return
     */
    public static List<ProductInfoDTO> convertInfoDTO(List<ProductInfoBO> productInfoBOList) {
        List<ProductInfoDTO> productInfoDTOS = new ArrayList<>();
        if (productInfoBOList == null || productInfoBOList.isEmpty()) {
            return productInfoDTOS;
        }
        for (ProductInfoBO productInfoBO : productInfoBOList) {
            productInfoDTOS.add(convertInfoDTO(productInfoBO));
        }
        return productInfoDTOS;
    }

    /**
     * Param转DO
     *
     * @param param
     * @return
     */
    public static ProductDO convertDO(EditProductDataParam_bak param, Long id) {
        ProductDO productDO = new ProductDO();
        productDO.setName(param.getName());
        productDO.setCategoryId(param.getCategoryId());
        productDO.setMerchantId(param.getMerchantId());
        productDO.setContent(param.getContent());
        productDO.setFeatureImage(param.getFeatureImage());
        productDO.setImageContent(param.getImageContents());
        productDO.setIsAllowRefund(param.getIsAllowRefund());
        if (id == 0L) {
            productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
            productDO.setGmtCreate(new Date());
        }
        productDO.setGmtModified(new Date());
        return productDO;
    }

    public static ProductDO convertDO(EditProductDataParam param, Long id) {
        ProductDO productDO = new ProductDO();
        productDO.setName(param.getName());
        productDO.setCategoryId(param.getCategoryId());
        productDO.setContent(param.getContent());
        productDO.setFeatureImage(param.getFeatureImage());
        productDO.setImageContent(param.getImageContents());
        productDO.setIsAllowRefund(param.getIsAllowRefund());
        if (id == 0L) {
            productDO.setMerchantId(param.getMerchantId());
            productDO.setMerchantNum(param.getMerchantNum());
            productDO.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP.getVal());
            productDO.setGmtCreate(new Date());
        } else {
            if(param.getProductStatus() != null){
                productDO.setStatus(param.getProductStatus().getVal());
            }
        }
        productDO.setKeywords(param.getKeywords());
        productDO.setGmtModified(new Date());
        return productDO;
    }

    public static ProductSolr convertProductSolr(ProductDO productDO) {
        ProductSolr rtn = new ProductSolr();
        rtn.setId(productDO.getId());
        rtn.setFeatureImage(productDO.getFeatureImage());
        rtn.setName(productDO.getName());
        rtn.setCategoryId(productDO.getCategoryId());
        rtn.setAverageDailySales(productDO.getAverageDailySales() == null ? 0D : productDO.getAverageDailySales().doubleValue());
        rtn.setOriginalPrice(productDO.getMaxPrice() == null ? 0D : productDO.getMaxPrice().doubleValue());
        rtn.setPrice(productDO.getMinPrice() == null ? 0D : productDO.getMinPrice().doubleValue());
        rtn.setInventory(productDO.getTotalInventory());
        rtn.setSalesVolume(productDO.getTotalSalesVolume());
        rtn.setBrandId(productDO.getBrandId() == null ? 0 : productDO.getBrandId().intValue());
        rtn.setCityId(productDO.getCityId() == null ? 0 : productDO.getCityId().intValue());
        rtn.setExpressFree(productDO.getIsExpressFree() == null ? 0 : productDO.getIsExpressFree() ? 1 : 0);
        rtn.setAllowRefund(productDO.getIsAllowRefund() == null ? 0 : productDO.getIsAllowRefund() ? 1 : 0);
        rtn.setCategorySubitemId(productDO.getCategorySubitemId() == null ? 0 : productDO.getCategorySubitemId().intValue());
        if (StringUtils.isEmpty(productDO.getBrandName()) || productDO.getBrandName().startsWith("其他") || productDO.getBrandName().startsWith("其它")) {
            rtn.setBrandId(0);
        }
        if (StringUtils.isNotEmpty(productDO.getKeywords())) {
            rtn.setKeywords(productDO.getKeywords());
            rtn.setKeywordss(Arrays.asList(productDO.getKeywords().split(",")));
        }
        if (StringUtils.isNotEmpty(productDO.getProvinceName()) && StringUtils.isNotEmpty(productDO.getCityName())) {
            rtn.setShipmentAddress(productDO.getProvinceName() + productDO.getCityName());
        }
        return rtn;
    }

    public static List<ProductSearchDTO> convertProductSearchDTOList(List<ProductSolr> source) {
        List<ProductSearchDTO> rtn = new ArrayList<>();
        if (source == null || source.isEmpty()) {
            return rtn;
        }
        for (ProductSolr item : source) {
            ProductSearchDTO productSearchDTO = new ProductSearchDTO();
            productSearchDTO.setProductId(item.getId() == null ? 0 : item.getId());
            productSearchDTO.setFeatureImage(item.getFeatureImage() == null ? "" : item.getFeatureImage());
            productSearchDTO.setName(item.getName() == null ? "" : item.getName());
            productSearchDTO.setOriginalPrice(item.getOriginalPrice() == null ? 0.0D : item.getOriginalPrice());
            productSearchDTO.setPrice(item.getPrice() == null ? 0.0D : item.getPrice());
            productSearchDTO.setSalesVolume(item.getSalesVolume() == null ? 0 : item.getSalesVolume());
            productSearchDTO.setIsExpressFree(item.getExpressFree() != null && item.getExpressFree() > 0);
            productSearchDTO.setIsAllowRefund(item.getAllowRefund() != null && item.getAllowRefund() > 0);
            productSearchDTO.setShipmentAddress(item.getShipmentAddress());
            rtn.add(productSearchDTO);
        }
        return rtn;
    }

    /**
     * BO转换
     *
     * @param productDOS
     * @return
     */
    public static List<ProductSearchBO> convertBO(List<ProductDO> productDOS) {
        List<ProductSearchBO> productSearchBOS = new ArrayList<>();
        if (productDOS == null || productDOS.isEmpty()) {
            return productSearchBOS;
        }

        for (ProductDO productDO : productDOS) {
            ProductSearchBO productSearchBO = new ProductSearchBO();
            productSearchBO.setProductId(productDO.getId());
            productSearchBO.setName(productDO.getName());
            productSearchBO.setContent(productDO.getContent());
            productSearchBO.setFeatureImage(productDO.getFeatureImage());
            productSearchBO.setOriginalPrice(productDO.getMaxPrice().doubleValue());
            productSearchBO.setPrice(productDO.getMinPrice().doubleValue());
            productSearchBO.setSalesVolume(productDO.getTotalSalesVolume());
            productSearchBOS.add(productSearchBO);
        }
        return productSearchBOS;
    }

    /**
     * DTO转换
     *
     * @param productSearchBOS
     * @return
     */
    public static List<ProductSearchDTO> convertDTO(List<ProductSearchBO> productSearchBOS) {
        List<ProductSearchDTO> productSearchDTOS = new ArrayList<>();
        if (productSearchBOS == null || productSearchBOS.isEmpty()) {
            return productSearchDTOS;
        }

        for (ProductSearchBO productSearchBO : productSearchBOS) {
            ProductSearchDTO productSearchDTO = new ProductSearchDTO();
            productSearchDTO.setProductId(productSearchBO.getProductId());
            productSearchDTO.setName(productSearchBO.getName());
            productSearchDTO.setContent(productSearchBO.getContent());
            productSearchDTO.setFeatureImage(productSearchBO.getFeatureImage());
            productSearchDTO.setOriginalPrice(productSearchBO.getOriginalPrice());
            productSearchDTO.setPrice(productSearchBO.getPrice());
            productSearchDTO.setSalesVolume(productSearchBO.getSalesVolume());
            productSearchDTOS.add(productSearchDTO);
        }
        return productSearchDTOS;
    }

    /**
     * DTO转换
     *
     * @param productBOS
     * @return
     */
    public static List<ProductSearchDTO> convertSearchDTO(List<ProductBO> productBOS) {
        List<ProductSearchDTO> productSearchDTOS = new ArrayList<>();
        if (productBOS == null || productBOS.isEmpty()) {
            return productSearchDTOS;
        }
        for (ProductBO productBO : productBOS) {
            ProductSearchDTO productSearchDTO = new ProductSearchDTO();
            productSearchDTO.setProductId(productBO.getId());
            productSearchDTO.setSalesVolume(productBO.getTotalSalesVolume());
            productSearchDTO.setOriginalPrice(productBO.getMaxPrice());
            productSearchDTO.setPrice(productBO.getMinPrice());
            productSearchDTOS.add(productSearchDTO);
        }
        return productSearchDTOS;
    }

    /**
     * VIEW转BO
     *
     * @param productDOViewList
     * @return
     */
    public static List<ProductBO> convertBOS(List<ProductDOView> productDOViewList) {
        List<ProductBO> productBOS = new ArrayList<>();
        if(productDOViewList == null || productDOViewList.isEmpty()){
            return productBOS;
        }

        for(ProductDOView productDOView : productDOViewList){
            ProductBO productBO = new ProductBO();
            productBO.setId(productDOView.getId());
            productBO.setTotalSalesVolume(productDOView.getTotalSalesVolume());
            productBO.setMaxPrice(productDOView.getMaxPrice());
            productBO.setMinPrice(productDOView.getMinPrice());
            productBOS.add(productBO);
        }
        return productBOS;
    }

    public static List<ProductSearchBO> convertSearchBOS(List<ShoppingProductDOView> productDOViews) {
        List<ProductSearchBO> productSearchBOS = new ArrayList<>();
        if(productDOViews == null || productDOViews.isEmpty()){
            return productSearchBOS;
        }

        for(ShoppingProductDOView shoppingProductDOView : productDOViews){
            ProductSearchBO productSearchBO = new ProductSearchBO();
            productSearchBO.setName(shoppingProductDOView.getName());
            productSearchBO.setFeatureImage(shoppingProductDOView.getFeatureImage());
            productSearchBO.setOriginalPrice(shoppingProductDOView.getMaxPrice().doubleValue());
            productSearchBO.setProductId(shoppingProductDOView.getProductId());
            productSearchBO.setPrice(shoppingProductDOView.getMinPrice().doubleValue());
            productSearchBO.setSalesVolume(shoppingProductDOView.getTotalSalesVolume());
            productSearchBOS.add(productSearchBO);
        }
        return productSearchBOS;
    }

    public static List<ProductSearchBO> convertProductSearchBOS(List<ProductDO> productDOS) {
        List<ProductSearchBO> productSearchBOS = new ArrayList<>();
        if (productDOS == null || productDOS.isEmpty()) {
            return productSearchBOS;
        }

        for (ProductDO productDO : productDOS) {
            ProductSearchBO productSearchBO = new ProductSearchBO();
            productSearchBO.setName(productDO.getName());
            productSearchBO.setFeatureImage(productDO.getFeatureImage());
            productSearchBO.setOriginalPrice(productDO.getMaxPrice().doubleValue());
            productSearchBO.setProductId(productDO.getId());
            productSearchBO.setPrice(productDO.getMinPrice().doubleValue());
            productSearchBO.setSalesVolume(productDO.getTotalSalesVolume());
            productSearchBOS.add(productSearchBO);
        }
        return productSearchBOS;
    }

    public static ProductTypeCountDTO countCountDTO(ProductTypeCountBO countBO) {
        if (countBO == null) {
            return null;
        }

        ProductTypeCountDTO countDTO = new ProductTypeCountDTO();
        countDTO.setSellingCount(countBO.getSellingCount());
        countDTO.setWarehouseCount(countBO.getWarehouseCount());
        countDTO.setShopwindowCount(countBO.getShopwindowCount());
        countDTO.setSoldOutCount(countBO.getSoldOutCount());
        return countDTO;
    }

    public static List<ProductListBO> convertListBOS(List<ProductDO> productDOS) {
        List<ProductListBO> listBOS = new ArrayList<>();
        if (productDOS == null || productDOS.isEmpty()) {
            return listBOS;
        }

        for (ProductDO productDO : productDOS) {
            ProductListBO listBO = new ProductListBO();
            listBO.setId(productDO.getId());
            listBO.setName(productDO.getName());
            listBO.setFeatureImage(productDO.getFeatureImage());
            listBO.setStatus(productDO.getStatus());
            listBO.setPosition(productDO.getPosition());
            listBO.setTotalInventory(productDO.getTotalInventory());
            listBO.setTotalSalesVolume(productDO.getTotalSalesVolume());
            listBO.setGmtUp(productDO.getGmtUp());
            listBO.setGmtDown(productDO.getGmtDown());
            listBO.setGmtCreate(productDO.getGmtCreate());
            listBO.setMinPrice(productDO.getMinPrice());
            listBO.setTotalFavorite(productDO.getTotalFavorite());
            listBO.setCategoryName(productDO.getCategoryName());
            listBO.setCategorySubitemName(productDO.getCategorySubitemName());
            listBOS.add(listBO);
        }
        return listBOS;
    }

    public static List<ProductListDTO> convertListDTOS(List<ProductListBO> listBOS) {
        List<ProductListDTO> listDTOS = new ArrayList<>();
        if (listBOS == null || listBOS.isEmpty()) {
            return listDTOS;
        }

        for (ProductListBO listBO : listBOS) {
            ProductListDTO listDTO = new ProductListDTO();
            listDTO.setId(listBO.getId());
            listDTO.setName(listBO.getName());
            listDTO.setFeatureImage(listBO.getFeatureImage());
            listDTO.setStatusEnum(ProductStatusEnum.getEnum(listBO.getStatus()));
            listDTO.setPositionEnum(ProductPositionEnum.getEnum(listBO.getPosition()));
            listDTO.setTotalInventory(listBO.getTotalInventory());
            listDTO.setTotalSalesVolume(listBO.getTotalSalesVolume());
            listDTO.setGmtUp(listBO.getGmtUp());
            listDTO.setGmtDown(listBO.getGmtDown());
            listDTO.setGmtCreate(listBO.getGmtCreate());
            listDTO.setMinPrice(listBO.getMinPrice());
            listDTO.setTotalFavorite(listBO.getTotalFavorite());
            if (StringUtils.isNotEmpty(listBO.getCategoryName()) && StringUtils.isNotEmpty(listBO.getCategorySubitemName())) {
                listDTO.setProductCategory(listBO.getCategoryName() + listBO.getCategorySubitemName());
            } else {
                listDTO.setProductCategory(listBO.getCategoryName());
            }
            if (listDTO.getStatusEnum() == ProductStatusEnum.PRODUCT_STATUS_DEL) {
                listDTO.setTotalInventory(0);
            }
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }

    public static ProductDetailBO convertDetailBO(ProductDO productDO) {
        if (productDO == null) {
            return null;
        }

        ProductDetailBO detailBO = new ProductDetailBO();
        detailBO.setId(productDO.getId());
        detailBO.setMerchantId(productDO.getMerchantId());
        detailBO.setCategoryId(productDO.getCategoryId());
        detailBO.setCategorySubitemId(productDO.getCategorySubitemId());
        detailBO.setName(productDO.getName());
        detailBO.setContent(productDO.getContent());
        detailBO.setFeatureImage(productDO.getFeatureImage());
        detailBO.setSpec(productDO.getSpec());
        detailBO.setMinPrice(productDO.getMinPrice());
        detailBO.setMaxPrice(productDO.getMaxPrice());
        detailBO.setTotalSalesVolume(productDO.getTotalSalesVolume());
        detailBO.setTotalInventory(productDO.getTotalInventory());
        detailBO.setIsAllowRefund(productDO.getIsAllowRefund());
        detailBO.setStatus(productDO.getStatus());
        detailBO.setMerchantNum(productDO.getMerchantNum());
        detailBO.setProvinceName(productDO.getProvinceName());
        detailBO.setCityName(productDO.getCityName());
        detailBO.setFreight(productDO.getFreight());
        return detailBO;
    }

    public static ProductDetailDTO convertDetailDTO(ProductDetailBO detailBO) {
        if (detailBO == null) {
            return null;
        }

        ProductDetailDTO detailDTO = new ProductDetailDTO();
        detailDTO.setId(detailBO.getId());
        detailDTO.setMerchantId(detailBO.getMerchantId());
        detailDTO.setCategoryId(detailBO.getCategoryId());
        detailDTO.setCategorySubitemId(detailBO.getCategorySubitemId());
        detailDTO.setName(detailBO.getName());
        detailDTO.setContent(detailBO.getContent());
        detailDTO.setFeatureImage(detailBO.getFeatureImage());
        if (StringUtils.isNotEmpty(detailBO.getSpec())) {
            detailDTO.setSpecJsonDTOS(JSONObject.parseArray(detailBO.getSpec(), ProductSpecJsonDTO.class));
        }
        detailDTO.setMinPrice(detailBO.getMinPrice());
        detailDTO.setMaxPrice(detailBO.getMaxPrice());
        detailDTO.setTotalSalesVolume(detailBO.getTotalSalesVolume());
        detailDTO.setTotalInventory(detailBO.getTotalInventory());
        detailDTO.setIsAllowRefund(detailBO.getIsAllowRefund());
        detailDTO.setImagesHeadUrl(detailBO.getImagesHeadUrl());
        detailDTO.setImageDetail(detailBO.getImageDetail());
        detailDTO.setModelInfo(convertModelInfoDTOS(detailBO.getModelInfo()));
        detailDTO.setProductStatus(ProductStatusEnum.getEnum(detailBO.getStatus()));
        detailDTO.setMerchantUserNum(detailBO.getMerchantNum());
        if (StringUtils.isNotEmpty(detailBO.getProvinceName()) && StringUtils.isNotEmpty(detailBO.getCityName())) {
            detailDTO.setShipmentCity(detailBO.getProvinceName() + detailBO.getCityName());
        }
        if (StringUtils.isNotEmpty(detailBO.getFreight())) {
            FreightDTO freightDTO = JSONObject.parseObject(detailBO.getFreight(), FreightDTO.class);
            detailDTO.setDefaultPieceMoney(freightDTO.getDefaultPieceMoney());
        } else {
            detailDTO.setDefaultPieceMoney(BigDecimal.ZERO);
        }
        return detailDTO;
    }

    private static List<ProductModelInfoDTO> convertModelInfoDTOS(List<ProductModelInfoBO> modelInfoBOS) {
        List<ProductModelInfoDTO> modelInfoDTOS = new ArrayList<>();
        if (modelInfoBOS == null || modelInfoBOS.isEmpty()) {
            return modelInfoDTOS;
        }

        for (ProductModelInfoBO modelInfoBO : modelInfoBOS) {
            ProductModelInfoDTO modelInfoDTO = new ProductModelInfoDTO();
            modelInfoDTO.setId(modelInfoBO.getId());
            modelInfoDTO.setModelKey(modelInfoBO.getModelKey());
            modelInfoDTO.setPrice(modelInfoBO.getPrice());
            modelInfoDTO.setInventory(modelInfoBO.getInventory());
            modelInfoDTOS.add(modelInfoDTO);
        }
        return modelInfoDTOS;
    }

    public static ProductEditDetailBO convertEditDetailBO(ProductDO productDO) {
        if (productDO == null) {
            return null;
        }
        ProductEditDetailBO productEditDetailBO = new ProductEditDetailBO();
        productEditDetailBO.setId(productDO.getId());
        productEditDetailBO.setName(productDO.getName());
        productEditDetailBO.setCategoryId(productDO.getCategoryId());
        productEditDetailBO.setCategoryName(productDO.getCategoryName());
        productEditDetailBO.setCategorySubitemId(productDO.getCategorySubitemId());
        productEditDetailBO.setCategorySubitemName(productDO.getCategorySubitemName());
        productEditDetailBO.setBrandId(productDO.getBrandId());
        productEditDetailBO.setBrandName(productDO.getBrandName());
        productEditDetailBO.setMinPrice(productDO.getMinPrice());
        productEditDetailBO.setMaxPrice(productDO.getMaxPrice());
        productEditDetailBO.setTotalInventory(productDO.getTotalInventory());
        productEditDetailBO.setContent(productDO.getContent());
        productEditDetailBO.setAllowRefund(productDO.getIsAllowRefund());
        productEditDetailBO.setExpressFree(productDO.getIsExpressFree());
        if (productDO.getIsExpressFree()) {
            productEditDetailBO.setFreightParam(null);
        } else {
            productEditDetailBO.setFreightParam(JSONObject.parseObject(productDO.getFreight(),FreightParam.class));
        }
        productEditDetailBO.setProvinceId(productDO.getProvinceId());
        productEditDetailBO.setCityId(productDO.getCityId());
        productEditDetailBO.setDetailContent(JSON.parseArray(productDO.getImageContent(),String.class));
        productEditDetailBO.setProvinceName(productDO.getProvinceName());
        productEditDetailBO.setCityName(productDO.getCityName());

        //将商家自定义的规格返回
        List<ProductSpecJsonDTO> productSpecJsonDTOList = JSONObject.parseArray(productDO.getSpec(),ProductSpecJsonDTO.class);
        List<ProductSpecCustomParam> productSpecCustomParam = new ArrayList<>();
        for (ProductSpecJsonDTO productSpecJsonDTO : productSpecJsonDTOList) {
            if (productSpecJsonDTO.getSpecId().longValue() < 0) {
                ProductSpecCustomParam param = new ProductSpecCustomParam();
                param.setSpecId(productSpecJsonDTO.getSpecId());
                param.setSpecName(productSpecJsonDTO.getSpecName());
                productSpecCustomParam.add(param);
            }
        }
        if (!productSpecCustomParam.isEmpty()) {
            productEditDetailBO.setProductSpecCustomParam(productSpecCustomParam);
        }

        return productEditDetailBO;
    }

    public static ProductEditDetailDTO convertEditDetailDTO(ProductEditDetailBO detailBO) {
        ProductEditDetailDTO productEditDetailDTO = new ProductEditDetailDTO();
        productEditDetailDTO.setId(detailBO.getId());
        productEditDetailDTO.setName(detailBO.getName());

        StringBuilder categoryIdPath = new StringBuilder();
        StringBuilder categoryNamePath = new StringBuilder();
        categoryIdPath.append(detailBO.getCategoryId());
        categoryNamePath.append(detailBO.getCategoryName());
        if (detailBO.getCategorySubitemId() != null && detailBO.getCategorySubitemId() > 0){
            categoryIdPath.append("/").append(detailBO.getCategorySubitemId());
            categoryNamePath.append("/").append(detailBO.getCategorySubitemName());
        }

        productEditDetailDTO.setCategoryIdPath(categoryIdPath.toString());
        productEditDetailDTO.setCategoryNamePath(categoryNamePath.toString());
        productEditDetailDTO.setBrandId(detailBO.getBrandId());
        productEditDetailDTO.setBrandName(detailBO.getBrandName());
        productEditDetailDTO.setMinPrice(detailBO.getMinPrice());
        productEditDetailDTO.setMaxPrice(detailBO.getMaxPrice());
        productEditDetailDTO.setTotalInventory(detailBO.getTotalInventory());
        productEditDetailDTO.setContent(detailBO.getContent());
        productEditDetailDTO.setIsAllowRefund(detailBO.getAllowRefund());
        productEditDetailDTO.setIsExpressFree(detailBO.getExpressFree());
        productEditDetailDTO.setFreightParam(detailBO.getFreightParam());
        productEditDetailDTO.setProvinceId(detailBO.getProvinceId());
        productEditDetailDTO.setCityId(detailBO.getCityId());
        productEditDetailDTO.setTitleImages(detailBO.getTitleImages());
        productEditDetailDTO.setDetailImages(detailBO.getDetailImages());
        productEditDetailDTO.setDetailContent(detailBO.getDetailContent());
        productEditDetailDTO.setProductModelParam(detailBO.getProductModelParam());
        productEditDetailDTO.setDelivery(detailBO.getProvinceName()+detailBO.getCityName());
        productEditDetailDTO.setProductSpecCustomParam(detailBO.getProductSpecCustomParam());
        return productEditDetailDTO;
    }

    public static List<ProductCityDTO> convertProductCityDTOS(List<ProductCityDOView> cityDOViews) {
        List<ProductCityDTO> cityDTOS = new ArrayList<>();
        if (cityDOViews == null || cityDOViews.isEmpty()) {
            return cityDTOS;
        }

        for (ProductCityDOView cityDOView : cityDOViews) {
            ProductCityDTO cityDTO = new ProductCityDTO();
            cityDTO.setCityId(cityDOView.getCityId());
            cityDTO.setCityName(cityDOView.getCityName());
            cityDTOS.add(cityDTO);
        }
        return cityDTOS;
    }

    public static List<ProductCategoryTierDTO> convertProductCategoryTierDTOS(List<ProductCategoryTierBO> tierBOS) {
        List<ProductCategoryTierDTO> tierDTOS = new ArrayList<>();
        if (tierBOS == null || tierBOS.isEmpty()) {
            return tierDTOS;
        }

        for (ProductCategoryTierBO tierBO : tierBOS) {
            ProductCategoryTierDTO tierDTO = new ProductCategoryTierDTO();
            tierDTO.setId(tierBO.getId());
            tierDTO.setName(tierBO.getName());
            tierDTO.setItemDTOS(convertProductCategoryItemDTOS(tierBO.getItemDOViews()));
            tierDTOS.add(tierDTO);
        }
        return tierDTOS;
    }

    private static List<ProductCategoryItemDTO> convertProductCategoryItemDTOS(List<ProductCategoryItemDOView> itemDOViews) {
        List<ProductCategoryItemDTO> itemDTOS = new ArrayList<>();
        if (itemDOViews == null || itemDOViews.isEmpty()) {
            return itemDTOS;
        }

        for (ProductCategoryItemDOView itemDOView : itemDOViews) {
            ProductCategoryItemDTO itemDTO = new ProductCategoryItemDTO();
            itemDTO.setId(itemDOView.getId());
            itemDTO.setName(itemDOView.getName());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    public static List<ProductAllBO> convertProductAllBOList(List<ProductDO> productDOList) {
        if (productDOList == null) {
            return null;
        }
        List<ProductAllBO> productAllBOList = new ArrayList<>();
        for (ProductDO productDO : productDOList) {
            ProductAllBO productAllBO = new ProductAllBO();
            productAllBO.setId(productDO.getId());
            productAllBO.setCategoryId(productDO.getCategoryId());
            productAllBO.setMerchantId(productDO.getMerchantId());
            productAllBO.setMerchantNum(productDO.getMerchantNum());
            productAllBOList.add(productAllBO);
        }
        return productAllBOList;
    }

    public static List<ProductAllDTO> convertProductAllDTOList(List<ProductAllBO> productAllBOList) {
        if (productAllBOList == null) {
            return null;
        }
        List<ProductAllDTO> productAllDTOList = new ArrayList<>();
        for (ProductAllBO productAllBO : productAllBOList) {
            ProductAllDTO productAllDTO = new ProductAllDTO();
            productAllDTO.setId(productAllBO.getId());
            productAllDTO.setCategoryId(productAllBO.getCategoryId());
            productAllDTO.setMerchantId(productAllBO.getMerchantId());
            productAllDTO.setMerchantNum(productAllBO.getMerchantNum());
            productAllDTOList.add(productAllDTO);
        }
        return productAllDTOList;
    }
}
