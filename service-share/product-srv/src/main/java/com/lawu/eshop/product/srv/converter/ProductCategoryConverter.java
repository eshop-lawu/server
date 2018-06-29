package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.constant.ProductCategoryTypeEnum;
import com.lawu.eshop.product.dto.OperatorProductCategoryListDTO;
import com.lawu.eshop.product.dto.ProductCategoryDTO;
import com.lawu.eshop.product.dto.ProductCategoryListDTO;
import com.lawu.eshop.product.srv.bo.ProductCategoryBO;
import com.lawu.eshop.product.srv.domain.ProductCategoryeDO;
import com.lawu.utils.DataTransUtil;

/**
 *
 * 会员信息转换器
 *
 * @author Yangqh
 * @date 2017/3/13
 */
public class ProductCategoryConverter {

    /**
     * BO转换
     *
     * @param productCategoryeDO
     * @return
     */
    public static ProductCategoryBO convertBO(ProductCategoryeDO productCategoryeDO) {
        if (productCategoryeDO == null) {
            return null;
        }

        ProductCategoryBO productCategoryBO = new ProductCategoryBO();
        productCategoryBO.setId(productCategoryeDO.getId());
        productCategoryBO.setName(productCategoryeDO.getName());
        productCategoryBO.setLevel(DataTransUtil.byteToInt(productCategoryeDO.getLevel()));
        productCategoryBO.setParentId(productCategoryeDO.getParentId());
        productCategoryBO.setPath(productCategoryeDO.getPath());
        productCategoryBO.setType(productCategoryeDO.getType());
        productCategoryBO.setStatue(productCategoryeDO.getStatue());
        productCategoryBO.setVirtual(productCategoryeDO.getIsVirtual());
        productCategoryBO.setOrdinal(DataTransUtil.byteToInt(productCategoryeDO.getOrdinal()));
        productCategoryBO.setImageUrl(productCategoryeDO.getImageUrl());
        return productCategoryBO;
    }

    /**
     * DO转BO，集合
     * @param productCategoryeDOS
     * @return
     */
    public static List<ProductCategoryBO> convertBOS(List<ProductCategoryeDO>  productCategoryeDOS){
        if(productCategoryeDOS.isEmpty()){
            return new ArrayList<>();
        }
        List<ProductCategoryBO> productCategoryeBOS = new ArrayList<ProductCategoryBO>();
        for(ProductCategoryeDO d : productCategoryeDOS){
            ProductCategoryBO bo = ProductCategoryConverter.convertBO(d);
            productCategoryeBOS.add(bo);
        }
        return productCategoryeBOS;
    }

    /**
     * 转换DTO,集合
     * @param productCategoryBOS
     * @return
     */
    public static List<ProductCategoryDTO> convertDTOS( List<ProductCategoryBO> productCategoryBOS){
        if(productCategoryBOS.isEmpty()){
            return new ArrayList<>();
        }
        List<ProductCategoryDTO> productCategoryeDTOS = new ArrayList<>();
        for(ProductCategoryBO bo : productCategoryBOS){
            ProductCategoryDTO dto = ProductCategoryConverter.convertDTO(bo);
            productCategoryeDTOS.add(dto);
        }
        return productCategoryeDTOS;
    }

    /**
     * DTO转换
     *
     * @param productCategoryBO
     * @return
     */
    public static ProductCategoryDTO convertDTO(ProductCategoryBO productCategoryBO) {
        if (productCategoryBO == null) {
            return null;
        }

        ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
        productCategoryDTO.setId(productCategoryBO.getId());
        productCategoryDTO.setName(productCategoryBO.getName());
        productCategoryDTO.setPath(productCategoryBO.getPath());
        productCategoryDTO.setParentId(productCategoryBO.getParentId());
        productCategoryDTO.setLevel(productCategoryBO.getLevel());
        productCategoryDTO.setIsVirtual(productCategoryBO.getVirtual());
        productCategoryDTO.setOrdinal(productCategoryBO.getOrdinal());
        productCategoryDTO.setImageUrl(productCategoryBO.getImageUrl());
        return productCategoryDTO;
    }

    public static List<OperatorProductCategoryListDTO> convertOperatorDTOS(List<ProductCategoryBO> records) {
        if(records.isEmpty()){
            return new ArrayList<>();
        }
        List<OperatorProductCategoryListDTO> listDTOS =new ArrayList<>();
        OperatorProductCategoryListDTO listDTO ;
        for(ProductCategoryBO categoryBO : records){
            listDTO = new OperatorProductCategoryListDTO();
            listDTO.setId(categoryBO.getId());
            listDTO.setName(categoryBO.getName());
            listDTO.setImageUrl(categoryBO.getImageUrl());
            listDTO.setParentId(categoryBO.getParentId());
            listDTO.setLevel(categoryBO.getLevel());
            listDTO.setIsVirtual(categoryBO.getVirtual());
            listDTO.setStatue(categoryBO.getStatue());
            listDTO.setOrdinal(categoryBO.getOrdinal());
            listDTO.setType(ProductCategoryTypeEnum.getEnum(categoryBO.getType()));
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }

    public static List<ProductCategoryListDTO> convertHotDTOS(List<ProductCategoryBO> categoryBOS) {
        if (categoryBOS.isEmpty()) {
            return new ArrayList<>();
        }
        List<ProductCategoryListDTO> listDTOS = new ArrayList<>();
        ProductCategoryListDTO listDTO;
        for (ProductCategoryBO categoryBO : categoryBOS) {
            listDTO = new ProductCategoryListDTO();
            listDTO.setId(categoryBO.getId());
            listDTO.setImageUrl(categoryBO.getImageUrl());
            listDTO.setName(categoryBO.getName());
            listDTOS.add(listDTO);
        }
        return listDTOS;
    }
}
