package com.lawu.eshop.product.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.lawu.eshop.product.constant.ProductStatusEnum;

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
public class EditProductParam {
	
	@ApiParam(value = "商品ID(新增时传0)", required = true)
	@NotNull(message="productId不能为空")
	private Long productId;

	@ApiParam(value = "商品类型ID", required = true)
	@NotNull(message="categoryId不能为空")
	private Integer categoryId;

	@ApiParam(value = "商品名称", required = true)
	@NotBlank(message="name不能为空")
	private String name;

	@ApiParam(value = "商品描述", required = true)
	@NotBlank(message="content不能为空")
	private String content;

	@ApiParam(value = "商品型号信息，格式：[{id,name,originalPrice,price,inventory,inventoryTrans,salesVolume}],新增时id=0,inventoryTrans=0,salesVolume=0，修改时inventoryTrans、salesVolume透传不做修改", required = true)
	@NotBlank(message="spec不能为空")
	private String spec;
	
	@ApiParam(value = "删除的型号ID(多个用英文逗号分开)", required = false)
	private String deleteSpecIds;
	
	@ApiParam(value = "商品图片url(格式：[url1,url2,...])", required = true)
	private String backProductImageUrls;
	
	@ApiParam(value = "商品描述图片url(格式：[url1,url2,...])", required = false)
	private String backProductDetailImageUrls;

	@ApiParam(value = "详情图片描述(以json字符串的格式传输，格式：[xxx,zzz,...]，顺序要与backProductDetailImageUrls一致)", required = false)
	private String imageContents;
	
	@ApiParam(value = "是否支持退换货(true|false)", required = true)
	@NotNull(message="isAllowRefund不能为空")
	private Boolean isAllowRefund;

	@ApiParam(value = "保存、上架、下架操作枚举（上架：PRODUCT_STATUS_UP、下架：PRODUCT_STATUS_DOWN、保存为空）", required = false)
	private ProductStatusEnum productStatus;

	@ApiParam(value = "关键词")
	private String keywords;
	
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

	public ProductStatusEnum getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(ProductStatusEnum productStatus) {
		this.productStatus = productStatus;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
