package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.dto.OperatorProductCategoryDTO;
import com.lawu.eshop.product.dto.RecommendProductCategoryDTO;
import com.lawu.eshop.product.srv.bo.RecommendProductCategoryBO;
import com.lawu.eshop.product.srv.domain.RecommendProductCategoryDO;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
public class RecommendProductCategoryConverter {

    /**
     * BO转换
     *
     * @param recommendProductCategoryDO
     * @return
     */
    public static RecommendProductCategoryBO convertBO(RecommendProductCategoryDO recommendProductCategoryDO) {
        if (recommendProductCategoryDO == null) {
            return null;
        }

        RecommendProductCategoryBO recommendProductCategoryBO = new RecommendProductCategoryBO();
        recommendProductCategoryBO.setId(recommendProductCategoryDO.getId());
        recommendProductCategoryBO.setCategoryId(recommendProductCategoryDO.getCategoryId());
        recommendProductCategoryBO.setCategoryName(recommendProductCategoryDO.getCategoryName());
        recommendProductCategoryBO.setGmtCreate(recommendProductCategoryDO.getGmtCreate());
        recommendProductCategoryBO.setIsvisible(recommendProductCategoryDO.getIsvisible());
        recommendProductCategoryBO.setIsHot(recommendProductCategoryDO.getIsHot());
        recommendProductCategoryBO.setOrdinal(recommendProductCategoryDO.getOrdinal());
        recommendProductCategoryBO.setImagePath(recommendProductCategoryDO.getImagePath());
        return recommendProductCategoryBO;
    }

    /**
     * DTO转换
     *
     * @param recommendProductCategoryBO
     * @return
     */
    public static RecommendProductCategoryDTO convertDTO(RecommendProductCategoryBO recommendProductCategoryBO) {
        if (recommendProductCategoryBO == null) {
            return null;
        }

        RecommendProductCategoryDTO recommendProductCategoryDTO = new RecommendProductCategoryDTO();
        recommendProductCategoryDTO.setId(recommendProductCategoryBO.getId());
        recommendProductCategoryDTO.setCategoryId(recommendProductCategoryBO.getCategoryId());
        recommendProductCategoryDTO.setCategoryName(recommendProductCategoryBO.getCategoryName());
        recommendProductCategoryDTO.setGmtCreate(recommendProductCategoryBO.getGmtCreate());
        return recommendProductCategoryDTO;
    }

    /**
     * BO转换
     *
     * @param recommendProductCategoryDOS
     * @return
     */
    public static List<RecommendProductCategoryBO> convertBO(List<RecommendProductCategoryDO> recommendProductCategoryDOS) {
        List<RecommendProductCategoryBO> recommendProductCategoryBOS = new ArrayList<>();
        if (recommendProductCategoryDOS == null || recommendProductCategoryDOS.isEmpty()) {
            return recommendProductCategoryBOS;
        }

        for (RecommendProductCategoryDO recommendProductCategoryDO : recommendProductCategoryDOS) {
            RecommendProductCategoryBO recommendProductCategoryBO = new RecommendProductCategoryBO();
            recommendProductCategoryBO.setId(recommendProductCategoryDO.getId());
            recommendProductCategoryBO.setCategoryId(recommendProductCategoryDO.getCategoryId());
            recommendProductCategoryBO.setCategoryName(recommendProductCategoryDO.getCategoryName());
            recommendProductCategoryBO.setGmtCreate(recommendProductCategoryDO.getGmtCreate());
            recommendProductCategoryBO.setIsvisible(recommendProductCategoryDO.getIsvisible());
            recommendProductCategoryBO.setIsHot(recommendProductCategoryDO.getIsHot());
            recommendProductCategoryBO.setOrdinal(recommendProductCategoryDO.getOrdinal());
            recommendProductCategoryBO.setImagePath(recommendProductCategoryDO.getImagePath());
            recommendProductCategoryBOS.add(recommendProductCategoryBO);
        }
        return recommendProductCategoryBOS;
    }

    /**
     * DTO转换
     *
     * @param recommendProductCategoryBOS
     * @return
     */
    public static List<RecommendProductCategoryDTO> convertDTO(List<RecommendProductCategoryBO> recommendProductCategoryBOS) {
        List<RecommendProductCategoryDTO> recommendProductCategoryDTOS = new ArrayList<>();
        if (recommendProductCategoryBOS == null || recommendProductCategoryBOS.isEmpty()) {
            return recommendProductCategoryDTOS;
        }

        for (RecommendProductCategoryBO recommendProductCategoryBO : recommendProductCategoryBOS) {
            RecommendProductCategoryDTO recommendProductCategoryDTO = new RecommendProductCategoryDTO();
            recommendProductCategoryDTO.setId(recommendProductCategoryBO.getId());
            recommendProductCategoryDTO.setCategoryId(recommendProductCategoryBO.getCategoryId());
            recommendProductCategoryDTO.setCategoryName(recommendProductCategoryBO.getCategoryName());
            recommendProductCategoryDTO.setGmtCreate(recommendProductCategoryBO.getGmtCreate());
            recommendProductCategoryDTO.setImagePath(recommendProductCategoryBO.getImagePath());
            recommendProductCategoryDTOS.add(recommendProductCategoryDTO);
        }
        return recommendProductCategoryDTOS;
    }

    public static List<OperatorProductCategoryDTO> convertOperatorDTO(List<RecommendProductCategoryBO> records) {
        List<OperatorProductCategoryDTO> recommendProductCategoryDTOS = new ArrayList<>();
        if (records == null || records.isEmpty()) {
            return recommendProductCategoryDTOS;
        }

        for (RecommendProductCategoryBO recommendProductCategoryBO : records) {
            OperatorProductCategoryDTO recommendProductCategoryDTO = new OperatorProductCategoryDTO();
            recommendProductCategoryDTO.setId(recommendProductCategoryBO.getId());
            recommendProductCategoryDTO.setCategoryId(recommendProductCategoryBO.getCategoryId());
            recommendProductCategoryDTO.setCategoryName(recommendProductCategoryBO.getCategoryName());
            recommendProductCategoryDTO.setGmtCreate(recommendProductCategoryBO.getGmtCreate());
            recommendProductCategoryDTO.setIsvisible(recommendProductCategoryBO.getIsvisible());
            recommendProductCategoryDTO.setOrdinal(recommendProductCategoryBO.getOrdinal());
            recommendProductCategoryDTO.setIsHot(recommendProductCategoryBO.getIsHot());
            recommendProductCategoryDTO.setImagePath(recommendProductCategoryBO.getImagePath());
            recommendProductCategoryDTOS.add(recommendProductCategoryDTO);
        }
        return recommendProductCategoryDTOS;
    }

    public static OperatorProductCategoryDTO convertOperatorDTO(RecommendProductCategoryBO recommendProductCategoryBO) {
        if (recommendProductCategoryBO == null) {
            return null;
        }

        OperatorProductCategoryDTO recommendProductCategoryDTO = new OperatorProductCategoryDTO();
        recommendProductCategoryDTO.setId(recommendProductCategoryBO.getId());
        recommendProductCategoryDTO.setCategoryId(recommendProductCategoryBO.getCategoryId());
        recommendProductCategoryDTO.setCategoryName(recommendProductCategoryBO.getCategoryName());
        recommendProductCategoryDTO.setGmtCreate(recommendProductCategoryBO.getGmtCreate());
        recommendProductCategoryDTO.setIsvisible(recommendProductCategoryBO.getIsvisible());
        recommendProductCategoryDTO.setIsHot(recommendProductCategoryBO.getIsHot());
        recommendProductCategoryDTO.setOrdinal(recommendProductCategoryBO.getOrdinal());
        recommendProductCategoryDTO.setImagePath(recommendProductCategoryBO.getImagePath());
        return recommendProductCategoryDTO;
    }
}
