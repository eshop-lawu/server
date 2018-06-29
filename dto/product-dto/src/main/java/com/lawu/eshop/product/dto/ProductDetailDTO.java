package com.lawu.eshop.product.dto;

import java.math.BigDecimal;
import java.util.List;

import com.lawu.eshop.product.constant.ProductStatusEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/17.
 */
public class ProductDetailDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "商家id")
    private Long merchantId;

    @ApiModelProperty(value = "类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "子类别id")
    private Long categorySubitemId;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品描述")
    private String content;

    @ApiModelProperty(value = "商品图片")
    private String featureImage;

    @ApiModelProperty(value = "规格信息")
    private List<ProductSpecJsonDTO> specJsonDTOS;

    @ApiModelProperty(value = "最小价格")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "最大价格")
    private BigDecimal maxPrice;

    @ApiModelProperty(value = "销量")
    private Integer totalSalesVolume;

    @ApiModelProperty(value = "库存")
    private Integer totalInventory;

    @ApiModelProperty(value = "是否支持退货")
    private Boolean isAllowRefund;

    @ApiModelProperty(value = "头部滚动图片")
    private List<String> imagesHeadUrl;

    @ApiModelProperty(value = "描述信息")
    private List<MemberProductImageDetailDTO> imageDetail;

    @ApiModelProperty(value = "型号信息")
    private List<ProductModelInfoDTO> modelInfo;

    @ApiModelProperty(value = "门店信息")
    private MemberProductStoreDTO storeDTO;

    @ApiModelProperty(value = "评价信息")
    private List<MemberProductCommentInfoDTO> comments;

    @ApiModelProperty(value = "评价数量")
    private Integer commentCount;

    @ApiModelProperty(value = "是否收藏")
    private boolean favoriteFlag;

    @ApiModelProperty(value = "购物车数量")
    private Integer shoppingCartNum;

    @ApiModelProperty(value = "PRODUCT_STATUS_DEL--已删除，PRODUCT_STATUS_UP--已上架，PRODUCT_STATUS_DOWN--已下架，PRODUCT_STATUS_TEMP--草稿")
    private ProductStatusEnum productStatus;

    @ApiModelProperty(value = "商家编号")
    private String merchantUserNum;

    @ApiModelProperty(value = "发货城市")
    private String shipmentCity;

    @ApiModelProperty(value = "默认运费")
    private BigDecimal defaultPieceMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategorySubitemId() {
        return categorySubitemId;
    }

    public void setCategorySubitemId(Long categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
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

    public String getFeatureImage() {
        return featureImage;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public List<ProductSpecJsonDTO> getSpecJsonDTOS() {
        return specJsonDTOS;
    }

    public void setSpecJsonDTOS(List<ProductSpecJsonDTO> specJsonDTOS) {
        this.specJsonDTOS = specJsonDTOS;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    public Integer getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }


    public List<String> getImagesHeadUrl() {
        return imagesHeadUrl;
    }

    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    public void setIsAllowRefund(Boolean allowRefund) {
        isAllowRefund = allowRefund;
    }

    public void setImagesHeadUrl(List<String> imagesHeadUrl) {
        this.imagesHeadUrl = imagesHeadUrl;
    }

    public List<MemberProductImageDetailDTO> getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(List<MemberProductImageDetailDTO> imageDetail) {
        this.imageDetail = imageDetail;
    }

    public List<ProductModelInfoDTO> getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(List<ProductModelInfoDTO> modelInfo) {
        this.modelInfo = modelInfo;
    }

    public MemberProductStoreDTO getStoreDTO() {
        return storeDTO;
    }

    public void setStoreDTO(MemberProductStoreDTO storeDTO) {
        this.storeDTO = storeDTO;
    }

    public List<MemberProductCommentInfoDTO> getComments() {
        return comments;
    }

    public void setComments(List<MemberProductCommentInfoDTO> comments) {
        this.comments = comments;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isFavoriteFlag() {
        return favoriteFlag;
    }

    public void setFavoriteFlag(boolean favoriteFlag) {
        this.favoriteFlag = favoriteFlag;
    }

    public Integer getShoppingCartNum() {
        return shoppingCartNum;
    }

    public void setShoppingCartNum(Integer shoppingCartNum) {
        this.shoppingCartNum = shoppingCartNum;
    }

    public ProductStatusEnum getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatusEnum productStatus) {
        this.productStatus = productStatus;
    }

    public String getMerchantUserNum() {
        return merchantUserNum;
    }

    public void setMerchantUserNum(String merchantUserNum) {
        this.merchantUserNum = merchantUserNum;
    }

    public String getShipmentCity() {
        return shipmentCity;
    }

    public void setShipmentCity(String shipmentCity) {
        this.shipmentCity = shipmentCity;
    }

    public BigDecimal getDefaultPieceMoney() {
        return defaultPieceMoney;
    }

    public void setDefaultPieceMoney(BigDecimal defaultPieceMoney) {
        this.defaultPieceMoney = defaultPieceMoney;
    }
}
