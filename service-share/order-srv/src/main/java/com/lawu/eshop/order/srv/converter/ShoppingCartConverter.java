package com.lawu.eshop.order.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.order.dto.ShoppingCartDTO;
import com.lawu.eshop.order.param.ShoppingCartSaveParam;
import com.lawu.eshop.order.param.ShoppingCartUpdateParam;
import com.lawu.eshop.order.srv.bo.ShoppingCartBO;
import com.lawu.eshop.order.srv.domain.ShoppingCartDO;
import com.lawu.eshop.order.srv.domain.extend.ShoppingCartUpdateQuantityDO;

/**
 *
 * 购物车转换器
 *
 * @author Sunny
 * @date 2017/3/27
 */
public class ShoppingCartConverter {

    /**
     * 隐藏默认的构造器
     */
    private ShoppingCartConverter() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * BO转换
     *
     * @param shoppingCartDO
     * @return
     */
    public static ShoppingCartBO convert(ShoppingCartDO shoppingCartDO) {
        ShoppingCartBO rtn = null;
        if (shoppingCartDO == null) {
            return rtn;
        }

        rtn = new ShoppingCartBO();
        rtn.setId(shoppingCartDO.getId());
        rtn.setMerchantId(shoppingCartDO.getMerchantId());
        rtn.setMerchantStoreId(shoppingCartDO.getMerchantStoreId());
        rtn.setMerchantName(shoppingCartDO.getMerchantName());
        rtn.setProductId(shoppingCartDO.getProductId());
        rtn.setProductModelId(shoppingCartDO.getProductModelId());
        rtn.setQuantity(shoppingCartDO.getQuantity());
        rtn.setSalesPrice(shoppingCartDO.getSalesPrice());

        return rtn;
    }

    public static List<ShoppingCartBO> convertBOS(List<ShoppingCartDO> shoppingCartDOList) {
        List<ShoppingCartBO> rtn = null;
        if (shoppingCartDOList == null || shoppingCartDOList.isEmpty()) {
            return rtn;
        }

        rtn = new ArrayList<>();
        for (ShoppingCartDO shoppingCartDO : shoppingCartDOList) {
            rtn.add(convert(shoppingCartDO));
        }

        return rtn;
    }

    /**
     * DTO转换
     *
     * @param shoppingCartBO
     * @return
     */
    public static ShoppingCartDTO convert(ShoppingCartBO shoppingCartBO) {
        ShoppingCartDTO rtn = null;
        if (shoppingCartBO == null) {
            return rtn;
        }

        rtn = new ShoppingCartDTO();
        rtn.setId(shoppingCartBO.getId());
        rtn.setMerchantId(shoppingCartBO.getMerchantId());
        rtn.setMerchantStoreId(shoppingCartBO.getMerchantStoreId());
        rtn.setMerchantName(shoppingCartBO.getMerchantName());
        rtn.setProductId(shoppingCartBO.getProductId());
        rtn.setProductModelId(shoppingCartBO.getProductModelId());
        rtn.setQuantity(shoppingCartBO.getQuantity());
        rtn.setSalesPrice(shoppingCartBO.getSalesPrice());

        return rtn;
    }

    public static List<ShoppingCartDTO> convertDTOS(List<ShoppingCartBO> bos) {
        List<ShoppingCartDTO> rtn = new ArrayList<>();
        if (bos == null || bos.isEmpty()) {
            return rtn;
        }

        for (ShoppingCartBO bo : bos) {
            rtn.add(convert(bo));
        }

        return rtn;
    }

    /**
     * DO转换
     * 
     * @param param
     * @return
     */
    public static ShoppingCartDO convert(ShoppingCartSaveParam param, Long memberId) {
        ShoppingCartDO rtn = null;
        if (param == null) {
            return rtn;
        }
        rtn = new ShoppingCartDO();
        rtn.setMemberId(memberId);
        rtn.setMerchantId(param.getMerchantId());
        rtn.setMerchantStoreId(param.getMerchantStoreId());
        rtn.setMerchantName(param.getMerchantName());
        rtn.setProductId(param.getProductId());
        rtn.setProductModelId(param.getProductModelId());
        rtn.setQuantity(param.getQuantity());
        rtn.setSalesPrice(param.getSalesPrice());
        rtn.setGmtCreate(new Date());
        rtn.setGmtModified(new Date());
        return rtn;
    }

    /**
     * 
     * @param param
     * @param id
     * @return
     * @author Sunny
     * @date 2017年6月16日
     */
    public static ShoppingCartDO convert(ShoppingCartUpdateParam param, Long id) {
        ShoppingCartDO rtn = null;
        if (param == null) {
            return rtn;
        }

        rtn = new ShoppingCartDO();
        rtn.setId(id);
        rtn.setProductModelId(param.getProductModelId());
        rtn.setQuantity(param.getQuantity());
        rtn.setGmtModified(new Date());
        return rtn;
    }
}
