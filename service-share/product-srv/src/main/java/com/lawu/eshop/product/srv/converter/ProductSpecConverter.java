package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.dto.OperatorProductSpecDTO;
import com.lawu.eshop.product.dto.ProductSpecDetailDTO;
import com.lawu.eshop.product.dto.ProductSpecListDTO;
import com.lawu.eshop.product.param.ProductSpecCustomParam;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecBO;
import com.lawu.eshop.product.srv.bo.ProductSpecBO;
import com.lawu.eshop.product.srv.domain.ProductSpecDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecDOView;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecConverter {
    public static List<ProductSpecBO> convertBOS(List<ProductSpecDO> specDOS) {
        if (specDOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductSpecBO> specBOS = new ArrayList<>();
        ProductSpecBO specBO;
        for (ProductSpecDO specDO : specDOS) {
            specBO = new ProductSpecBO();
            specBO.setId(specDO.getId());
            specBO.setSpecName(specDO.getSpecName());
            specBO.setProductCategoryId(specDO.getProductCategoryId());
            specBOS.add(specBO);
        }
        return specBOS;
    }

    public static List<ProductSpecListDTO> convertDTOS(List<ProductSpecBO> specBOS) {
        if (specBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductSpecListDTO> specDTOS = new ArrayList<>();
        ProductSpecListDTO specDTO;
        for (ProductSpecBO specBO : specBOS) {
            specDTO = new ProductSpecListDTO();
            specDTO.setId(specBO.getId());
            specDTO.setSpecName(specBO.getSpecName());
            specDTO.setProductCategoryId(specBO.getProductCategoryId());
            specDTOS.add(specDTO);
        }
        return specDTOS;
    }

    public static List<OperatorProductSpecBO> convertOperatorBOS(List<OperatorProductSpecDOView> specDOViews) {
        if (specDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductSpecBO> specBOS = new ArrayList<>();
        OperatorProductSpecBO specBO;
        for (OperatorProductSpecDOView specDOView : specDOViews) {
            specBO = new OperatorProductSpecBO();
            specBO.setId(specDOView.getId());
            specBO.setOrdinal(specDOView.getOrdinal().intValue());
            specBO.setSpecName(specDOView.getSpecName());
            specBO.setGmtCreate(specDOView.getGmtCreate());
            specBO.setCategoryName(specDOView.getCategoryName());
            specBOS.add(specBO);
        }
        return specBOS;
    }

    public static List<OperatorProductSpecDTO> convertOperatorDTOS(List<OperatorProductSpecBO> records) {
        if (records.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductSpecDTO> specDTOS = new ArrayList<>();
        OperatorProductSpecDTO specDTO;
        for (OperatorProductSpecBO specDOView : records) {
            specDTO = new OperatorProductSpecDTO();
            specDTO.setId(specDOView.getId());
            specDTO.setOrdinal(specDOView.getOrdinal());
            specDTO.setSpecName(specDOView.getSpecName());
            specDTO.setGmtCreate(specDOView.getGmtCreate());
            specDTO.setCategoryName(specDOView.getCategoryName());
            specDTOS.add(specDTO);
        }
        return specDTOS;
    }

    public static ProductSpecBO convertBO(ProductSpecDO specDO) {
        if (specDO == null) {
            return null;
        }
        ProductSpecBO specBO = new ProductSpecBO();
        specBO.setId(specDO.getId());
        specBO.setSpecName(specDO.getSpecName());
        specBO.setProductCategoryId(specDO.getProductCategoryId());
        specBO.setOrdinal(specDO.getOrdinal().intValue());
        return specBO;
    }

    public static ProductSpecDetailDTO convertDTO(ProductSpecBO specBO) {
        if (specBO == null) {
            return null;
        }
        ProductSpecDetailDTO detailDTO = new ProductSpecDetailDTO();
        detailDTO.setId(specBO.getId());
        detailDTO.setSpecName(specBO.getSpecName());
        detailDTO.setProductCategoryId(specBO.getProductCategoryId());
        detailDTO.setOrdinal(specBO.getOrdinal());
        return detailDTO;
    }

    public static List<ProductSpecCustomParam> convertProductSpecCustomParamList(List<ProductSpecBO> productSpecBOList) {
        if(productSpecBOList == null){
            return null;
        }
        List<ProductSpecCustomParam> productSpecList = new ArrayList<>();
        for (ProductSpecBO bo : productSpecBOList) {
            ProductSpecCustomParam param = new ProductSpecCustomParam();
            param.setSpecId(bo.getId());
            param.setSpecName(bo.getSpecName());
            productSpecList.add(param);
        }
        return productSpecList;
    }
}
