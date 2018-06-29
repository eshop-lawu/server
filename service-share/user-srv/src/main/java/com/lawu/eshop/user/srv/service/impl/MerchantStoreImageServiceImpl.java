package com.lawu.eshop.user.srv.service.impl;

import com.lawu.eshop.user.dto.MerchantStoreImageEnum;
import com.lawu.eshop.user.srv.UserSrvConfig;
import com.lawu.eshop.user.srv.bo.MerchantStoreImageBO;
import com.lawu.eshop.user.srv.converter.MerchantStoreImageConverter;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDO;
import com.lawu.eshop.user.srv.domain.MerchantStoreImageDOExample;
import com.lawu.eshop.user.srv.mapper.MerchantStoreImageDOMapper;
import com.lawu.eshop.user.srv.service.MerchantStoreImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/11.
 */
@Service
public class MerchantStoreImageServiceImpl implements MerchantStoreImageService {

    @Autowired
    private MerchantStoreImageDOMapper merchantStoreImageDOMapper;

    @Autowired
    private UserSrvConfig userSrvConfig;

    @Override
    public List<MerchantStoreImageBO> listMerchantStoreImageByType(Long merchantId, MerchantStoreImageEnum merchantStoreImageEnum) {
        MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
        merchantStoreImageDOExample.createCriteria().andMerchantIdEqualTo(merchantId).andTypeEqualTo(merchantStoreImageEnum.val).andStatusEqualTo(true);
        List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
        return MerchantStoreImageConverter.convertBO(merchantStoreImageDOS);
    }

	@Override
	public String selectLogoPath(Long merchantId) {
		 MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
	     merchantStoreImageDOExample.createCriteria().andMerchantIdEqualTo(merchantId).andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val).andStatusEqualTo(true);
	     List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
	     if(!merchantStoreImageDOS.isEmpty()){
	    	 return merchantStoreImageDOS.get(0).getPath();
	     }else{
	    	 return userSrvConfig.getMerchant_headimg();
	     }
		
	}

	@Override
	public String selectLogoUrlByStoreId(Long id) {
		MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
		merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(id).andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_LOGO.val).andStatusEqualTo(true);
		List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
		if (!merchantStoreImageDOS.isEmpty()) {
			return merchantStoreImageDOS.get(0).getPath();
		} else {
			return "";
		}
	}

	@Override
	public String getStoreUrlByStoreId(Long id) {
		MerchantStoreImageDOExample merchantStoreImageDOExample = new MerchantStoreImageDOExample();
		merchantStoreImageDOExample.createCriteria().andMerchantStoreIdEqualTo(id).andTypeEqualTo(MerchantStoreImageEnum.STORE_IMAGE_STORE.val).andStatusEqualTo(true);
		List<MerchantStoreImageDO> merchantStoreImageDOS = merchantStoreImageDOMapper.selectByExample(merchantStoreImageDOExample);
		if (!merchantStoreImageDOS.isEmpty()) {
			return merchantStoreImageDOS.get(0).getPath();
		} else {
			return "";
		}
	}
}
