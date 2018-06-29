package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
public interface MerchantStoreImageService {

    /**
     * 根据图片类型查询门店图片
     *
     * @param merchantId
     * @param merchantStoreImageEnum
     * @return
     */
    List<MerchantStoreImageBO> listMerchantStoreImageByType(Long merchantId, MerchantStoreImageEnum merchantStoreImageEnum);
    
    
    String selectLogoPath(Long merchantId);

    String selectLogoUrlByStoreId(Long id);

    String getStoreUrlByStoreId(Long id);
}
