package com.lawu.eshop.product.srv.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.common.dto.FreightDTO;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.eshop.product.dto.SeckillActivityProductModelInfoDTO;
import com.lawu.eshop.product.dto.ShoppingCartProductModelDTO;
import com.lawu.eshop.product.srv.bo.SeckillActivityProductModelInfoBO;
import com.lawu.eshop.product.srv.bo.ShoppingCartProductModelBO;
import com.lawu.eshop.product.srv.domain.ProductDO;
import com.lawu.eshop.product.srv.domain.ProductModelDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductDO;
import com.lawu.eshop.product.srv.domain.SeckillActivityProductModelDO;

/**
 * 购物车产品型号转换器
 * 
 * @author Sunny
 * @date 2017/3/30
 */
public class ShoppingCartProductModelConverter {
	
    private static Logger logger = LoggerFactory.getLogger(ShoppingCartProductModelConverter.class);
    
	/**
	 * 隐藏默认的构造函数
	 */
	private ShoppingCartProductModelConverter() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 * BO转换
	 * 
	 * @param productModelDO
	 * @param productDO
	 * @return
	 */
	public static ShoppingCartProductModelBO convert(ProductModelDO productModelDO, ProductDO productDO) {
		return convert(productModelDO, productDO, ShoppingCartProductModelBO.class);
	}
	
	/**
	 * 
	 * @param productModelDO
	 * @param productDO
	 * @param clazz 需要转换的类型
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月24日
	 * @updateDate 2017年11月24日
	 */
    public static ShoppingCartProductModelBO convert(ProductModelDO productModelDO, ProductDO productDO, Class<? extends ShoppingCartProductModelBO> clazz) {
        ShoppingCartProductModelBO rtn = null;
        if (productModelDO == null) {
            return rtn;
        }
        try {
            rtn = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            return rtn;
        }
        rtn.setId(productModelDO.getId());
        rtn.setInventory(productModelDO.getInventory());
        rtn.setMerchantId(productModelDO.getMerchantId());
        rtn.setName(productModelDO.getName());
        rtn.setOriginalPrice(productModelDO.getOriginalPrice());
        rtn.setPrice(productModelDO.getPrice());
        rtn.setProductId(productModelDO.getProductId());
        if (productDO != null) {
            rtn.setProductName(productDO.getName());
            rtn.setFeatureImage(productDO.getFeatureImage());
            if (ProductStatusEnum.PRODUCT_STATUS_UP.getVal().equals(productDO.getStatus())) {
                if (productModelDO.getStatus()) {
                    rtn.setStatus(ProductStatusEnum.PRODUCT_STATUS_UP);
                } else {
                    rtn.setGmtDown(productModelDO.getGmtModified());
                    rtn.setStatus(ProductStatusEnum.PRODUCT_STATUS_DEL);
                }
            } else {
                rtn.setStatus(ProductStatusEnum.getEnum(productDO.getStatus()));
                if (ProductStatusEnum.PRODUCT_STATUS_DOWN.getVal().equals(productDO.getStatus())) {
                    rtn.setGmtDown(productDO.getGmtDown());
                } else {
                    rtn.setGmtDown(productDO.getGmtModified());
                }
            }
            rtn.setIsAllowRefund(productDO.getIsAllowRefund());
            if (!productDO.getIsExpressFree() && StringUtils.isNotBlank(productDO.getFreight())) {
                rtn.setFreight(JSONObject.parseObject(productDO.getFreight(), FreightDTO.class));
            }
        }
        return rtn;
    }
	
    /**
     * 
     * @param productModelDO
     * @param productDO
     * @param seckillActivityDO
     * @param seckillActivityProductDO
     * @param seckillActivityProductModelDO
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月24日
     * @updateDate 2017年11月24日
     */
    public static SeckillActivityProductModelInfoBO convert(ProductModelDO productModelDO, ProductDO productDO, SeckillActivityDO seckillActivityDO, SeckillActivityProductDO seckillActivityProductDO, SeckillActivityProductModelDO seckillActivityProductModelDO) {
        SeckillActivityProductModelInfoBO rtn = (SeckillActivityProductModelInfoBO) convert(productModelDO, productDO, SeckillActivityProductModelInfoBO.class);
        rtn.setInventory(seckillActivityProductModelDO.getLeftCount());
        rtn.setActivityId(seckillActivityDO.getId());
        rtn.setActivityProductId(seckillActivityProductDO.getId());
		if (seckillActivityProductDO.getSaleMoney() == null) {
			  rtn.setPrice(seckillActivityDO.getSellingPrice());
		}else{
			  rtn.setPrice(seckillActivityProductDO.getSaleMoney());
		}
        return rtn;
    }
    
	/**
	 * DTO转换
	 * 
	 * @param shoppingCartProductModelBO
	 * @return
	 */
	public static ShoppingCartProductModelDTO convert(ShoppingCartProductModelBO shoppingCartProductModelBO) {
		ShoppingCartProductModelDTO rtn = null;
		if (shoppingCartProductModelBO == null) {
			return rtn;
		}
		rtn = convert(shoppingCartProductModelBO, ShoppingCartProductModelDTO.class);
		return rtn;
	}
	
	/**
	 * 
	 * @param shoppingCartProductModelBO
	 * @param clazz 需要转换的类
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月24日
	 * @updateDate 2017年11月24日
	 */
	public static ShoppingCartProductModelDTO convert(ShoppingCartProductModelBO shoppingCartProductModelBO, Class<? extends ShoppingCartProductModelDTO> clazz) {
        ShoppingCartProductModelDTO rtn = null;
        if (shoppingCartProductModelBO == null) {
            return rtn;
        }
        try {
            rtn = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
            return rtn;
        }
        rtn.setFeatureImage(shoppingCartProductModelBO.getFeatureImage());
        rtn.setId(shoppingCartProductModelBO.getId());
        rtn.setInventory(shoppingCartProductModelBO.getInventory());
        rtn.setIsAllowRefund(shoppingCartProductModelBO.getIsAllowRefund());
        rtn.setMerchantId(shoppingCartProductModelBO.getMerchantId());
        rtn.setName(shoppingCartProductModelBO.getName());
        rtn.setOriginalPrice(shoppingCartProductModelBO.getOriginalPrice());
        rtn.setPrice(shoppingCartProductModelBO.getPrice());
        rtn.setProductId(shoppingCartProductModelBO.getProductId());
        rtn.setProductName(shoppingCartProductModelBO.getProductName());
        rtn.setStatus(shoppingCartProductModelBO.getStatus());
        rtn.setGmtDown(shoppingCartProductModelBO.getGmtDown());
        rtn.setFreight(shoppingCartProductModelBO.getFreight());
        return rtn;
    }
	
	/**
	 * 
	 * @param seckillActivityProductModelInfoBO
	 * @return
	 * @author jiangxinjun
	 * @createDate 2017年11月24日
	 * @updateDate 2017年11月24日
	 */
	public static SeckillActivityProductModelInfoDTO convert(SeckillActivityProductModelInfoBO seckillActivityProductModelInfoBO) {
	    SeckillActivityProductModelInfoDTO rtn = null;
        if (seckillActivityProductModelInfoBO == null) {
            return rtn;
        }
        rtn = (SeckillActivityProductModelInfoDTO) convert(seckillActivityProductModelInfoBO, SeckillActivityProductModelInfoDTO.class);
        rtn.setActivityId(seckillActivityProductModelInfoBO.getActivityId());
        rtn.setActivityProductId(seckillActivityProductModelInfoBO.getActivityProductId());
        return rtn;
    }
	
	/**
	 * DTOS转换
	 * 
	 * @param shoppingCartProductModelBOS
	 * @return
	 */
	public static List<ShoppingCartProductModelDTO> convert(List<ShoppingCartProductModelBO> shoppingCartProductModelBOS) {
		List<ShoppingCartProductModelDTO> rtn = null;
		if (shoppingCartProductModelBOS == null || shoppingCartProductModelBOS.isEmpty()) {
			return rtn;
		}
		
		rtn = new ArrayList<>();
		for (ShoppingCartProductModelBO shoppingCartProductModelBO : shoppingCartProductModelBOS) {
			rtn.add(convert(shoppingCartProductModelBO));
		}
		
		return rtn;
	}
	
	   /**
     * BOS转换
     * 
     * @param productModelDOS
     * @param productDOS
     * @return
     */
    public static List<ShoppingCartProductModelBO> convert(List<ProductModelDO> productModelDOS, List<ProductDO> productDOS) {
        List<ShoppingCartProductModelBO> rtn = null;
        if (productModelDOS == null || productModelDOS.isEmpty() || productDOS == null || productDOS.isEmpty()) {
            return rtn;
        }
        Map<Long, ProductDO> productDOMap = new HashMap<>();
        for (ProductDO productDO : productDOS) {
            if (!productDOMap.containsKey(productDO.getId())) {
                productDOMap.put(productDO.getId(), productDO);
            }
        }
        rtn = new ArrayList<>();
        for (ProductModelDO productModelDO : productModelDOS) {
            rtn.add(convert(productModelDO, productDOMap.get(productModelDO.getProductId())));
        }
        return rtn;
    }
}
