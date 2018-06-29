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
public class EditProductDataParam_bak extends EditProductParam{

	@NotNull(message="merchantId不能为空")
	private Long merchantId;
	
	//@ApiParam(value = "详情图片，多张提交key为productDetailImage-'index'-N(例如：productDetailImage-1-1、productDetailImage-1-2,productDetailImage-2-1、productDetailImage-2-2,....)")
	//private String productDetailImages;
	private Map<String,List<String>> detailImageMap = null;//存放详情图片
	
	//@ApiParam(value = "特征图片(非必填,如果为空则取滚动图片第一张作为特征图片)")
	private String featureImage;
	
	//@ApiParam(value = "滚动图片，多张提交key为productIamge-N(修改时上传增量，例如：第1张：productIamge-1、第2张：productIamge-2...)")
	@NotBlank(message="productImages不能为空")
	private String productImages;
	
	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Map<String, List<String>> getDetailImageMap() {
		return detailImageMap;
	}

	public void setDetailImageMap(Map<String, List<String>> detailImageMap) {
		this.detailImageMap = detailImageMap;
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
	

}
