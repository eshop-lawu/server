package com.lawu.eshop.product.dto;

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
public class CommentProductInfoDTO {

	private String featureImage;	//特征图片
	private String name;			//商品名称
	private String price;			//型号的价格
	private String modelName;		//型号名称

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
