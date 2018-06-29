package com.lawu.eshop.user.srv.converter;

import com.lawu.eshop.user.dto.MerchantStoreImageDTO;
import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
public class MerchantStoreImageConverter {

    /**
     * BO转换
     *
     * @param merchantStoreImageDOS
     * @return
     */
    public static List<MerchantStoreImageBO> convertBO(List<MerchantStoreImageDO> merchantStoreImageDOS) {
        if (merchantStoreImageDOS.isEmpty()) {
            return null;
        }

        List<MerchantStoreImageBO> merchantStoreImageBOS = new ArrayList<>(merchantStoreImageDOS.size());
        for (MerchantStoreImageDO merchantStoreImageDO : merchantStoreImageDOS) {
            MerchantStoreImageBO merchantStoreImageBO = new MerchantStoreImageBO();
            merchantStoreImageBO.setId(merchantStoreImageDO.getId());
            merchantStoreImageBO.setPath(merchantStoreImageDO.getPath());
            merchantStoreImageBO.setType(merchantStoreImageDO.getType());
            merchantStoreImageBOS.add(merchantStoreImageBO);
        }
        return merchantStoreImageBOS;
    }

    /**
     * DTO转换
     *
     * @param merchantStoreImageBOS
     * @return
     */
    public static List<MerchantStoreImageDTO> convertDTO(List<MerchantStoreImageBO> merchantStoreImageBOS) {
        if (merchantStoreImageBOS.isEmpty()) {
            return null;
        }

        List<MerchantStoreImageDTO> merchantStoreImageDTOS = new ArrayList<>(merchantStoreImageBOS.size());
        for (MerchantStoreImageBO merchantStoreImageBO : merchantStoreImageBOS) {
            MerchantStoreImageDTO merchantStoreImageDTO = new MerchantStoreImageDTO();
            merchantStoreImageDTO.setPath(merchantStoreImageBO.getPath());
            merchantStoreImageDTO.setId(merchantStoreImageBO.getId());
            merchantStoreImageDTO.setMerchantStoreImageEnum(MerchantStoreImageEnum.getEnum(merchantStoreImageBO.getType()));
            merchantStoreImageDTOS.add(merchantStoreImageDTO);
        }
        return merchantStoreImageDTOS;
    }
}
