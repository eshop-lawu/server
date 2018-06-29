package com.lawu.eshop.product.query;

import com.lawu.eshop.product.constant.ProductSortFieldEnum;
import com.lawu.eshop.product.constant.ProductStatusEnum;
import com.lawu.framework.core.page.AbstractPageParam;
import com.lawu.framework.core.page.OrderType;

import io.swagger.annotations.ApiParam;

/**
 * 
 * <p>
 * Description: 接收app参数
 * </p>
 * @author Yangqh
 * @date 2017年3月28日 上午10:11:42
 *
 */
public class ProductQuery extends AbstractPageParam{
	
	@ApiParam(value = "是否是app调用", required = false)
	private boolean isApp;
	
	@ApiParam(value = "商品名称", required = false)
	private String name;
	
	@ApiParam(value = "商品分类ID(最后一级)", required = false)
	private String categoryId;
	
	@ApiParam(value = "排序方式", required = false)
	private OrderType orderType;
	
	@ApiParam(value = "排序字段(TOTAL_INVENTORY:库存、TOTAL_SALES_VOLUME：销量)", required = true)
    private ProductSortFieldEnum productSortFieldEnum;
	
	@ApiParam(value = "状态(PRODUCT_STATUS_DEL:删除、PRODUCT_STATUS_UP：上架、PRODUCT_STATUS_DOWN：下架)", required = true)
	private ProductStatusEnum productStatus;
	
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public ProductSortFieldEnum getProductSortFieldEnum() {
		return productSortFieldEnum;
	}
	public void setProductSortFieldEnum(ProductSortFieldEnum productSortFieldEnum) {
		this.productSortFieldEnum = productSortFieldEnum;
	}
	public ProductStatusEnum getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(ProductStatusEnum productStatus) {
		this.productStatus = productStatus;
	}
	public boolean getIsApp() {
		return isApp;
	}
	public void setIsApp(boolean isApp) {
		this.isApp = isApp;
	}
	
}
