package com.lawu.eshop.product.param;

import com.lawu.eshop.product.constant.ProductStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EditProductUpgradeParam {

    @ApiModelProperty(value = "商品ID(新增时传0)", required = true)
    @NotNull(message = "productId不能为空")
    private Long productId;

    @ApiModelProperty(value = "商品名称", required = true)
    @NotBlank(message = "商品名称不能为空")
    private String name;

    @ApiModelProperty(value = "类目ID字符串，用/分隔", required = true)
    @NotBlank(message = "类目ID不能为空")
    private String categoryIdPath;

    @ApiModelProperty(value = "类目名称字符串，用/分隔", required = true)
    @NotBlank(message = "类目名称不能为空")
    private String categoryNamePath;

    @ApiModelProperty(value = "商品品牌ID", required = true)
    @NotNull(message = "brandId不能为空")
    private Long brandId;

    @ApiModelProperty(value = "商品品牌，正常选择不传，选其他时填写")
    private String brandName;

    @ApiModelProperty(value = "商品规格，必须有序，自定义的规格或规格选项id传值从-1开始递减", required = true)
    @NotNull(message = "商品规格不能为空")
    private List<EditProductModelBeanParam> productModelParam;

    @ApiModelProperty(value = "需要删除的商品型号id集合")
    private List<Long> delProductModelIds;

    @ApiModelProperty(value = "商家自定义规格，没有自定义时传空")
    private List<ProductSpecCustomParam> productSpecCustomParam;

    @ApiModelProperty(value = "商品描述中的描述内容", required = true)
    private String content;

    @ApiModelProperty(value = "商品顶部图片", required = true)
    @NotNull(message = "商品顶部图片不能为空")
    private List<String> titleImages;

    @ApiModelProperty(value = "商品描述中的图片（与detailContent同序）")
    private List<String> detailImages;

    @ApiModelProperty(value = "商品描述中的图片说明（与detailImages同序，没有录入时填入空字符串）")
    private List<String> detailContent;

    @ApiModelProperty(value = "退换货", required = true)
    @NotNull(message = "退换货不能为空")
    private Boolean isAllowRefund;

    @ApiModelProperty(value = "是否包邮", required = true)
    @NotNull(message = "是否包邮")
    private Boolean isExpressFree;

    @ApiModelProperty(value = "运费")
    private FreightParam freightParam;

    @ApiModelProperty(value = "发货地省Id", required = true)
    @NotNull(message = "发货地不能为空")
    private Long provinceId;

    @ApiModelProperty(value = "发货地市Id", required = true)
    @NotNull(message = "发货地不能为空")
    private Long cityId;

    @ApiModelProperty(value = "操作枚举（加入仓库：PRODUCT_STATUS_TEMP、发布商品：PRODUCT_STATUS_UP）", required = true)
    @NotNull(message = "productStatus不能为空")
    private ProductStatusEnum productStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getDelProductModelIds() {
        return delProductModelIds;
    }

    public void setDelProductModelIds(List<Long> delProductModelIds) {
        this.delProductModelIds = delProductModelIds;
    }

    public String getCategoryIdPath() {
        return categoryIdPath;
    }

    public void setCategoryIdPath(String categoryIdPath) {
        this.categoryIdPath = categoryIdPath;
    }

    public String getCategoryNamePath() {
        return categoryNamePath;
    }

    public void setCategoryNamePath(String categoryNamePath) {
        this.categoryNamePath = categoryNamePath;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public List<EditProductModelBeanParam> getProductModelParam() {
        return productModelParam;
    }

    public void setProductModelParam(List<EditProductModelBeanParam> productModelParam) {
        this.productModelParam = productModelParam;
    }

    public List<ProductSpecCustomParam> getProductSpecCustomParam() {
        return productSpecCustomParam;
    }

    public void setProductSpecCustomParam(List<ProductSpecCustomParam> productSpecCustomParam) {
        this.productSpecCustomParam = productSpecCustomParam;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getTitleImages() {
        return titleImages;
    }

    public void setTitleImages(List<String> titleImages) {
        this.titleImages = titleImages;
    }

    public List<String> getDetailImages() {
        return detailImages;
    }

    public void setDetailImages(List<String> detailImages) {
        this.detailImages = detailImages;
    }

    public List<String> getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(List<String> detailContent) {
        this.detailContent = detailContent;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public Boolean getIsExpressFree() {
        return isExpressFree;
    }

    public void setIsExpressFree(Boolean expressFree) {
        isExpressFree = expressFree;
    }

    public FreightParam getFreightParam() {
        return freightParam;
    }

    public void setFreightParam(FreightParam freightParam) {
        this.freightParam = freightParam;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public ProductStatusEnum getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatusEnum productStatus) {
        this.productStatus = productStatus;
    }
}
