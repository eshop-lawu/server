package com.lawu.eshop.product.param;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * <p>
 * Description: api提交给svr的参数对象
 * </p>
 * @author Yangqh
 * @date 2017年3月27日 下午8:21:09
 *
 */
public class EditProductDataParam extends EditProductParam{

	@NotNull(message="merchantId不能为空")
	private Long merchantId;
	
	@NotBlank(message="merchantNum不能为空")
	private String merchantNum;
	
	private String featureImage;
	
	@NotBlank(message="productImages不能为空")
	private String productImages;

	//商品描述图片
	private String detailImages;
	
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}

	public String getFeatureImage() {
		return featureImage;
	}

	public void setFeatureImage(String featureImage) {
		this.featureImage = featureImage;
	}

	public String getProductImages() {
		return productImages;
	}

	public void setProductImages(String productImages) {
		this.productImages = productImages;
	}

	public String getDetailImages() {
		return detailImages;
	}

	public void setDetailImages(String detailImages) {
		this.detailImages = detailImages;
	}

}
