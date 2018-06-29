package com.lawu.eshop.product.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiParam;

/**
 * api接收app提交参数对象
 * <p>
 * Description: 
 * </p>
 * @author Yangqh
 * @date 2017年3月27日 下午8:21:09
 *
 */
public class EditProductParam_bak {
	
	@ApiParam(value = "商品ID(新增时传0)", required = true)
	@NotNull(message="productId不能为空(新增时传0)")
	private Long productId;

	@ApiParam(value = "商品类型ID", required = true)
	@NotNull(message="categoryId不能为空")
	private Integer categoryId;

	@ApiParam(value = "商品名称", required = true)
	@NotBlank(message="name不能为空")
	private String name;

	@ApiParam(value = "商品描述", required = true)
	private String content;

	@ApiParam(value = "商品型号信息，json格式：[{id,name,originalPrice,price,inventory,inventoryTrans,salesVolume},{},...],新增时id=0,inventoryTrans=0,salesVolume=0，修改时inventoryTrans透传不做修改", required = true)
	@NotBlank(message="spec不能为空")
	private String spec;
	
	@ApiParam(value = "删除的型号ID(多个用英文逗号分开)", required = false)
	private String deleteSpecIds;
	
	@ApiParam(value = "详情图片描述(以json字符串的格式传输，格式：[\"xxxxxx\",\"zzzzzzz\",...]，其顺序要和其详情图片index一致，没有文字描述空字符串处理)")
	@NotBlank(message="imageContents不能为空")
	private String imageContents;
	
//	@ApiParam(value = "新增为空，修改时回显的特征图片url(新增时传空,格式：url1,url2,url3....)")
//	private String backFeatureImageUrls;
	
	@ApiParam(value = "新增为空，修改时回显的滚动图片url(新增时传空,格式：[\"xxxxxx\",\"zzzzzzz\",...])")
	private String backProductImageUrls;
	
	@ApiParam(value = "新增为空，修改时回显的详情图片url(新增时传空,json格式：{\"productDetailImage-1\":[\"xxxx-1-1,xxxx-1-2\",\"zzzz-2-1,zzzz-2-2\"],\"productDetailImage-2\":[],...})")
	private String backProductDetailImageUrls;
	
	@ApiParam(value = "是否支持退换货(true|false)", required = true)
	@NotNull(message="isAllowRefund不能为空")
	private Boolean isAllowRefund;
	
//	public String getBackFeatureImageUrls() {
//		return backFeatureImageUrls;
//	}
//
//	public void setBackFeatureImageUrls(String backFeatureImageUrls) {
//		this.backFeatureImageUrls = backFeatureImageUrls;
//	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getBackProductImageUrls() {
		return backProductImageUrls;
	}

	public void setBackProductImageUrls(String backProductImageUrls) {
		this.backProductImageUrls = backProductImageUrls;
	}

	public String getBackProductDetailImageUrls() {
		return backProductDetailImageUrls;
	}

	public void setBackProductDetailImageUrls(String backProductDetailImageUrls) {
		this.backProductDetailImageUrls = backProductDetailImageUrls;
	}

	public String getImageContents() {
		return imageContents;
	}

	public void setImageContents(String imageContents) {
		this.imageContents = imageContents;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getIsAllowRefund() {
		return isAllowRefund;
	}

	public void setIsAllowRefund(Boolean isAllowRefund) {
		this.isAllowRefund = isAllowRefund;
	}

	public String getDeleteSpecIds() {
		return deleteSpecIds;
	}

	public void setDeleteSpecIds(String deleteSpecIds) {
		this.deleteSpecIds = deleteSpecIds;
	}

}
