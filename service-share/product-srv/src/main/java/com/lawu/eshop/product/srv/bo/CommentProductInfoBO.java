package com.lawu.eshop.product.srv.bo;

/**
 * 
 * <p>
 * Description:商家查看评价时，关联出商品和其型号信息
 * </p>
 * 
 * @author Yangqh
 * @date 2017年4月17日 下午8:02:20
 *
 */
public class CommentProductInfoBO {

	private String featureImage;
	private String name;
	private String price;
	private String modelName;

	public String getFeatureImage() {
		return featureImage;
	}

	public void setFeatureImage(String featureImage) {
		this.featureImage = featureImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
