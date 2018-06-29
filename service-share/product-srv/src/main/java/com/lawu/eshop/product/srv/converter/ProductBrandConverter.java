package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.dto.OperatorProductBrandDTO;
import com.lawu.eshop.product.dto.ProductBrandDetailDTO;
import com.lawu.eshop.product.dto.ProductBrandListDTO;
import com.lawu.eshop.product.srv.bo.OperatorProductBrandBO;
import com.lawu.eshop.product.srv.bo.ProductBrandBO;
import com.lawu.eshop.product.srv.domain.ProductBrandDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductBrandDOView;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductBrandConverter {
    public static List<ProductBrandBO> covertBOS(List<ProductBrandDO> brandDOS) {
        if (brandDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductBrandBO> brandBOS = new ArrayList<>();
        ProductBrandBO brandBO;
        for (ProductBrandDO productDO : brandDOS) {
            brandBO = new ProductBrandBO();
            brandBO.setId(productDO.getId());
            brandBO.setBrandName(productDO.getBrandName());
            brandBO.setProductCategoryId(productDO.getProductCategoryId());
            brandBO.setOrdinal(productDO.getOrdinal().intValue());
            brandBOS.add(brandBO);
        }
        return brandBOS;
    }

    public static List<ProductBrandListDTO> covertDTOS(List<ProductBrandBO> brandBOS) {
        if (brandBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductBrandListDTO> brandDTOS = new ArrayList<>();
        ProductBrandListDTO brandDTO;
        for (ProductBrandBO brandBO : brandBOS) {
            brandDTO = new ProductBrandListDTO();
            brandDTO.setId(brandBO.getId());
            brandDTO.setBrandName(brandBO.getBrandName());
            brandDTO.setProductCategoryId(brandBO.getProductCategoryId());
            brandDTOS.add(brandDTO);
        }
        return brandDTOS;
    }

    public static ProductBrandBO covertBO(ProductBrandDO brandDO) {
        if (brandDO == null) {
            return null;
        }
        ProductBrandBO brandBO = new ProductBrandBO();
        brandBO.setId(brandDO.getId());
        brandBO.setBrandName(brandDO.getBrandName());
        brandBO.setProductCategoryId(brandDO.getProductCategoryId());
        brandBO.setOrdinal(brandDO.getOrdinal().intValue());
        return brandBO;
    }

    public static ProductBrandDetailDTO covertDTO(ProductBrandBO brandBO) {
        if (brandBO == null) {
            return null;
        }
        ProductBrandDetailDTO brandDTO = new ProductBrandDetailDTO();
        brandDTO.setId(brandBO.getId());
        brandDTO.setBrandName(brandBO.getBrandName());
        brandDTO.setProductCategoryId(brandBO.getProductCategoryId());
        brandDTO.setOrdinal(brandBO.getOrdinal());
        return brandDTO;
    }

    public static List<OperatorProductBrandBO> covertOperatorBOS(List<OperatorProductBrandDOView> brandDOViews) {
        if (brandDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductBrandBO> brandBOS = new ArrayList<>();
        OperatorProductBrandBO brandBO;
        for (OperatorProductBrandDOView brandDOView : brandDOViews) {
            brandBO = new OperatorProductBrandBO();
            brandBO.setId(brandDOView.getId());
            brandBO.setOrdinal(brandDOView.getOrdinal().intValue());
            brandBO.setBrandName(brandDOView.getBrandName());
            brandBO.setGmtCreate(brandDOView.getGmtCreate());
            brandBO.setCategoryName(brandDOView.getCategoryName());
            brandBOS.add(brandBO);
        }
        return brandBOS;
    }

    public static List<OperatorProductBrandDTO> covertOperatorDTOS(List<OperatorProductBrandBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductBrandDTO> brandDTOS = new ArrayList<>();
        OperatorProductBrandDTO brandDTO;
        for (OperatorProductBrandBO brandBO : records) {
            brandDTO = new OperatorProductBrandDTO();
            brandDTO.setId(brandBO.getId());
            brandDTO.setOrdinal(brandBO.getOrdinal());
            brandDTO.setBrandName(brandBO.getBrandName());
            brandDTO.setGmtCreate(brandBO.getGmtCreate());
            brandDTO.setCategoryName(brandBO.getCategoryName());
            brandDTOS.add(brandDTO);
        }
        return brandDTOS;
    }
}
