package com.lawu.eshop.mall.srv.bo;

import java.util.List;

/**
 * 优惠套餐扩展DO
 * 
 * @author Sunny
 * @date 2017年6月26日
 */
public class DiscountPackageExtendBO extends DiscountPackageBO {

	private List<DiscountPackageContentBO> discountPackageContents;
	
	private List<DiscountPackageImageBO> discountPackageImages;

	public List<DiscountPackageContentBO> getDiscountPackageContents() {
		return discountPackageContents;
	}

	public void setDiscountPackageContents(List<DiscountPackageContentBO> discountPackageContents) {
		this.discountPackageContents = discountPackageContents;
	}

	public List<DiscountPackageImageBO> getDiscountPackageImages() {
		return discountPackageImages;
	}

	public void setDiscountPackageImages(List<DiscountPackageImageBO> discountPackageImages) {
		this.discountPackageImages = discountPackageImages;
	}
	
}