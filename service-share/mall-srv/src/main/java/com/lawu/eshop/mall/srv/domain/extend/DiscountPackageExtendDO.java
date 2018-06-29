package com.lawu.eshop.mall.srv.domain.extend;

import java.util.List;

import com.lawu.eshop.mall.srv.domain.DiscountPackageContentDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageDO;
import com.lawu.eshop.mall.srv.domain.DiscountPackageImageDO;

/**
 * 优惠套餐扩展DO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DiscountPackageExtendDO extends DiscountPackageDO {

	private static final long serialVersionUID = 1L;
	
	private List<DiscountPackageContentDO> discountPackageContents;
	
	private List<DiscountPackageImageDO> discountPackageImages;

	public List<DiscountPackageContentDO> getDiscountPackageContents() {
		return discountPackageContents;
	}

	public void setDiscountPackageContents(List<DiscountPackageContentDO> discountPackageContents) {
		this.discountPackageContents = discountPackageContents;
	}

	public List<DiscountPackageImageDO> getDiscountPackageImages() {
		return discountPackageImages;
	}

	public void setDiscountPackageImages(List<DiscountPackageImageDO> discountPackageImages) {
		this.discountPackageImages = discountPackageImages;
	}
	
}