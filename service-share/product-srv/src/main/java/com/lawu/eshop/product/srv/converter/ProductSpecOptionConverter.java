package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.dto.OperatorProductSpecOptionDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionDetailDTO;
import com.lawu.eshop.product.dto.ProductSpecOptionListDTO;
import com.lawu.eshop.product.srv.bo.OperatorProductSpecOptionBO;
import com.lawu.eshop.product.srv.bo.ProductSpecOptionBO;
import com.lawu.eshop.product.srv.domain.ProductSpecOptionDO;
import com.lawu.eshop.product.srv.domain.extend.OperatorProductSpecOptionDOView;

/**
 * @author zhangyong
 * @date 2018/4/16.
 */
public class ProductSpecOptionConverter {
    public static List<ProductSpecOptionBO> convertBOS(List<ProductSpecOptionDO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductSpecOptionBO> optionBOS = new ArrayList<>();
        ProductSpecOptionBO optionBO;
        for (ProductSpecOptionDO optionDO : list) {
            optionBO = new ProductSpecOptionBO();
            optionBO.setIconUrl(optionDO.getIconUrl());
            optionBO.setOptionName(optionDO.getOptionName());
            optionBO.setId(optionDO.getId());
            optionBO.setProductSpecId(optionDO.getProductSpecId());
            optionBOS.add(optionBO);
        }
        return optionBOS;
    }

    public static List<ProductSpecOptionListDTO> convertDTOS(List<ProductSpecOptionBO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductSpecOptionListDTO> optionDTOS = new ArrayList<>();
        ProductSpecOptionListDTO optionDTO;
        for (ProductSpecOptionBO optionBO : list) {
            optionDTO = new ProductSpecOptionListDTO();
            optionDTO.setIconUrl(optionBO.getIconUrl());
            optionDTO.setOptionName(optionBO.getOptionName());
            optionDTO.setId(optionBO.getId());
            optionDTO.setProductSpecId(optionBO.getProductSpecId());
            optionDTOS.add(optionDTO);
        }
        return optionDTOS;
    }

    public static ProductSpecOptionBO convertBO(ProductSpecOptionDO optionDO) {
        ProductSpecOptionBO optionBO = new ProductSpecOptionBO();
        optionBO.setIconUrl(optionDO.getIconUrl());
        optionBO.setOptionName(optionDO.getOptionName());
        optionBO.setId(optionDO.getId());
        optionBO.setProductSpecId(optionDO.getProductSpecId());
        optionBO.setOrdinal(optionDO.getOrdinal().intValue());
        return optionBO;
    }

    public static ProductSpecOptionDetailDTO convertDTO(ProductSpecOptionBO optionBO) {
        ProductSpecOptionDetailDTO optionDetailDTO = new ProductSpecOptionDetailDTO();
        optionDetailDTO.setIconUrl(optionBO.getIconUrl());
        optionDetailDTO.setOptionName(optionBO.getOptionName());
        optionDetailDTO.setId(optionBO.getId());
        optionDetailDTO.setProductSpecId(optionBO.getProductSpecId());
        optionDetailDTO.setOrdinal(optionBO.getOrdinal());
        return optionDetailDTO;
    }

    public static List<OperatorProductSpecOptionBO> convertOperatorBOS(List<OperatorProductSpecOptionDOView> optionDOViews) {
        if (optionDOViews.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductSpecOptionBO> optionBOS = new ArrayList<>();
        OperatorProductSpecOptionBO optionBO;
        for (OperatorProductSpecOptionDOView doView : optionDOViews) {
            optionBO = new OperatorProductSpecOptionBO();
            optionBO.setIconUrl(doView.getIconUrl());
            optionBO.setOptionName(doView.getOptionName());
            optionBO.setId(doView.getId());
            optionBO.setProductSpecId(doView.getProductSpecId());
            optionBO.setOrdinal(doView.getOrdinal().intValue());
            optionBO.setGmtCreate(doView.getGmtCreate());
            optionBO.setSpecName(doView.getSpecName());
            optionBOS.add(optionBO);
        }
        return optionBOS;
    }

    public static List<OperatorProductSpecOptionDTO> convertOperatorDTOS(List<OperatorProductSpecOptionBO> optionBOS) {
        if (optionBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<OperatorProductSpecOptionDTO> optionDTOS = new ArrayList<>();
        OperatorProductSpecOptionDTO optionDTO;
        for (OperatorProductSpecOptionBO optionBO : optionBOS) {
            optionDTO = new OperatorProductSpecOptionDTO();
            optionDTO.setIconUrl(optionBO.getIconUrl());
            optionDTO.setOptionName(optionBO.getOptionName());
            optionDTO.setId(optionBO.getId());
            optionDTO.setProductSpecId(optionBO.getProductSpecId());
            optionDTO.setOrdinal(optionBO.getOrdinal());
            optionDTO.setGmtCreate(optionBO.getGmtCreate());
            optionDTO.setSpecName(optionBO.getSpecName());
            optionDTOS.add(optionDTO);
        }
        return optionDTOS;
    }
}
