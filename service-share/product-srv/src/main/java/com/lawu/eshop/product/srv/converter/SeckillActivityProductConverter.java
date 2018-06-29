package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.product.constant.ActivityProductStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductBuyPageDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductInfoDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductInformationDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInformationDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductPartDTO;
import com.lawu.eshop.product.dto.SeckillActivityProductRecommendDTO;
import com.lawu.eshop.product.srv.bo.RecentlySeckillActivityBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductExtendBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductModelBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductPartBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductRecommendBO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductModelDO;

/**
 * 抢购活动商品转换类
 * 
 * @author jiangxinjun
 * @createDate 2017年11月23日
 * @updateDate 2017年11月23日
 */
public class SeckillActivityProductConverter {

    /**
     * SeckillActivityProductDO List转SeckillActivityProductBO List
     * 
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    public static List<SeckillActivityProductBO> convertSeckillActivityProductBOList(List<SeckillActivityProductDO> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<SeckillActivityProductBO> rtn = new ArrayList<>();
        for (SeckillActivityProductDO item : list) {
            rtn.add(convert(item));
        }
        return rtn;
    }
    
    /**
     * SeckillActivityProductDO转SeckillActivityProductBO
     * 
     * @param source
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月24日
     */
    public static SeckillActivityProductBO convert(SeckillActivityProductDO source) {
        return convert(source, null);
    }
    
    /**
     * SeckillActivityProductDO转SeckillActivityProductBO
     * 
     * @param source
     * @param target
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    public static SeckillActivityProductBO convert(SeckillActivityProductDO source, SeckillActivityProductBO target) {
        if (source == null) {
            return null;
        }
        SeckillActivityProductBO rtn = null;
        if (target == null) {
            rtn = new SeckillActivityProductBO();
        } else {
            rtn = target;
        }
        rtn.setActivityId(source.getActivityId());
        rtn.setAttentionCount(source.getAttentionCount());
        rtn.setId(source.getId());
        rtn.setLeftCount(source.getLeftCount());
        rtn.setMerchantId(source.getMerchantId());
        rtn.setOriginalPrice(source.getOriginalPrice());
        rtn.setProductId(source.getProductId());
        rtn.setProductModelCount(source.getProductModelCount());
        rtn.setProductName(source.getProductName());
        rtn.setProductPicture(source.getProductPicture());
        rtn.setReasons(source.getReasons());
        rtn.setStatus(ActivityProductStatusEnum.getEnum(source.getStatus()));
        rtn.setTurnover(source.getTurnover());
        rtn.setPriority(source.getPriority());
        rtn.setSaleMoney(source.getSaleMoney());
        rtn.setAuditorAccount(source.getAuditorAccount());
        rtn.setAuditTime(source.getAuditTime());
        return rtn;
    }
    
    /**
     * SeckillActivityProductBO转SeckillActivityProductBuyPageDTO
     * 
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    public static List<SeckillActivityProductBuyPageDTO> convertSeckillActivityProductBuyPageDTOList(List<SeckillActivityProductBO> list) {
        List<SeckillActivityProductBuyPageDTO> rtn = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return rtn;
        }
        for (SeckillActivityProductBO item : list) {
            SeckillActivityProductBuyPageDTO entry = new SeckillActivityProductBuyPageDTO();
            entry.setActivityId(item.getActivityId());
            entry.setLeftCount(item.getLeftCount());
            entry.setOriginalPrice(item.getOriginalPrice());
            entry.setProductId(item.getProductId());
            entry.setProductModelCount(item.getProductModelCount());
            entry.setProductName(item.getProductName());
            entry.setProductPicture(item.getProductPicture());
            entry.setActivityProductId(item.getId());
            entry.setSaleMoney(item.getSaleMoney());
            rtn.add(entry);
        }
        return rtn;
    }
    
    /**
     * SeckillActivityDO/SeckillActivityProductDO/SeckillActivityProductModelDO List
     * 转
     * SeckillActivityProductExtendBO
     * 
     * @param source
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    public static SeckillActivityProductExtendBO convert(SeckillActivityDO seckillActivityDO, SeckillActivityProductDO seckillActivityProductDO, List<SeckillActivityProductModelDO> seckillActivityProductModelDOList) {
        if (seckillActivityDO == null || seckillActivityProductDO == null || seckillActivityProductModelDOList== null || seckillActivityProductModelDOList.isEmpty()) {
            return null;
        }
        SeckillActivityProductExtendBO rtn = (SeckillActivityProductExtendBO) convert(seckillActivityProductDO, new SeckillActivityProductExtendBO());
        rtn.setSeckillActivity(SeckillActivityConverter.convert(seckillActivityDO));
        rtn.setProductModelList(SeckillActivityProductModelConverter.convertSeckillActivityProductModelBOList(seckillActivityProductModelDOList));
        return rtn;
    }
    
    /**
     * SeckillActivityProductExtendBO转SeckillActivityProductInformationDTO
     * 
     * @param source
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月27日
     */
    public static SeckillActivityProductInformationDTO convert(SeckillActivityProductExtendBO source) {
        if (source == null) {
            return null;
        }
        SeckillActivityProductInformationDTO rtn = new SeckillActivityProductInformationDTO();
        rtn.setAttentionCount(source.getAttentionCount());
        rtn.setActivityProductId(source.getId());
        rtn.setProductModelCount(source.getProductModelCount());
        rtn.setSoldQuantity(source.getProductModelCount() - source.getLeftCount());
        rtn.setSaleMoney(source.getSaleMoney());
        SeckillActivityBO seckillActivityBO = source.getSeckillActivity();
        if (seckillActivityBO != null) {
            rtn.setActivityStatus(seckillActivityBO.getActivityStatus());
            rtn.setMemberLevel(seckillActivityBO.getMemberLevel());
            rtn.setSellingPrice(seckillActivityBO.getSellingPrice());
        }
        rtn.setProductModelList(new ArrayList<>());
        if (source.getProductModelList() != null && !source.getProductModelList().isEmpty()) {
            for (SeckillActivityProductModelBO item : source.getProductModelList()) {
                SeckillActivityProductModelInformationDTO productModel = new SeckillActivityProductModelInformationDTO();
                productModel.setLeftCount(item.getLeftCount());
                productModel.setProductModelId(item.getProductModelId());
                productModel.setSeckillActivityProductModelId(item.getId());
                rtn.getProductModelList().add(productModel);
            }
        }
        return rtn;
    }
    
    /**
     * SeckillActivityProductBO转SeckillActivityProductInfoDTO
     * 
     * @param list
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    public static List<SeckillActivityProductInfoDTO> convertSeckillActivityProductInfoDTOList(List<SeckillActivityProductBO> list) {
        List<SeckillActivityProductInfoDTO> rtn = new ArrayList<>();
        if (list == null || list.isEmpty()) {
            return rtn;
        }
        for (SeckillActivityProductBO item : list) {
            SeckillActivityProductInfoDTO entry = new SeckillActivityProductInfoDTO();
            entry.setLeftCount(item.getLeftCount());
            entry.setOriginalPrice(item.getOriginalPrice());
            entry.setProductId(item.getProductId());
            entry.setProductModelCount(item.getProductModelCount());
            entry.setProductName(item.getProductName());
            entry.setProductPicture(item.getProductPicture());
            entry.setActivityProductId(item.getId());
            entry.setAttentionCount(item.getAttentionCount());
            entry.setReasons(item.getReasons());
            entry.setStatus(item.getStatus());
            entry.setTurnover(item.getTurnover());
            entry.setPriority(item.getPriority());
            entry.setMerchantId(item.getMerchantId());
            entry.setSaleMoney(item.getSaleMoney());
            entry.setAuditorAccount(item.getAuditorAccount());
            entry.setAuditTime(item.getAuditTime());
            rtn.add(entry);
        }
        return rtn;
    }
    
    public static SeckillActivityProductPartBO convert(List<SeckillActivityProductBO> sources, RecentlySeckillActivityBO recentlySeckillActivityBO) {
        SeckillActivityProductPartBO rtn = new SeckillActivityProductPartBO();
        if (sources == null) {
            return rtn;
        }
        List<SeckillActivityProductRecommendBO> products = new ArrayList<>();
        if (sources != null && !sources.isEmpty()) {
            for (SeckillActivityProductBO item : sources) {
                SeckillActivityProductRecommendBO recommendBO = new SeckillActivityProductRecommendBO();
                recommendBO.setProductId(item.getProductId());
                recommendBO.setProductName(item.getProductName());
                recommendBO.setProductPicture(item.getProductPicture());
                recommendBO.setOriginalPrice(item.getOriginalPrice());
                recommendBO.setSaleMoney(item.getSaleMoney());
                recommendBO.setActivityStatus(recentlySeckillActivityBO.getActivityStatus());
                products.add(recommendBO);
            }
        }
        rtn.setProducts(products);
        rtn.setActivityStatus(recentlySeckillActivityBO.getActivityStatus());
        rtn.setCountdown(recentlySeckillActivityBO.getCountdown());
        return rtn;
    }
    
    public static SeckillActivityProductPartDTO convert(SeckillActivityProductPartBO sources) {
        SeckillActivityProductPartDTO rtn = new SeckillActivityProductPartDTO();
        if (sources == null) {
            return rtn;
        }
        List<SeckillActivityProductRecommendDTO> products = new ArrayList<>();
        if (sources.getProducts() != null && !sources.getProducts().isEmpty()) {
            for (SeckillActivityProductRecommendBO item : sources.getProducts()) {
                SeckillActivityProductRecommendDTO recommendDTO = new SeckillActivityProductRecommendDTO();
                recommendDTO.setProductId(item.getProductId());
                recommendDTO.setProductName(item.getProductName());
                recommendDTO.setProductPicture(item.getProductPicture());
                recommendDTO.setOriginalPrice(item.getOriginalPrice());
                recommendDTO.setSaleMoney(item.getSaleMoney());
                recommendDTO.setStatusEnum(item.getActivityStatus());
                products.add(recommendDTO);
            }
        }
        rtn.setRecommendDTOS(products);
        rtn.setActivityStatus(sources.getActivityStatus());
        rtn.setCountdown(sources.getCountdown());
        return rtn;
    }

}
