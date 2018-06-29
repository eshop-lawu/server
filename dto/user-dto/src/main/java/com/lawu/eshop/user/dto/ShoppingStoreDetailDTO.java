package com.lawu.eshop.user.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
public class ShoppingStoreDetailDTO {

    @ApiModelProperty(value = "商家ID")
    private Long merchantId;

    @ApiModelProperty(value = "门店名称")
    private String name;

    @ApiModelProperty(value = "粉丝数量")
    private Integer fansCount;

    @ApiModelProperty(value = "是否关注：true--已关注，false--未关注")
    private Boolean isFans;

    @ApiModelProperty(value = "是否收藏：true--已收藏，false--未收藏")
    private Boolean isFavorite;

    @ApiModelProperty(value = "门店logo")
    private String logoPic;

    @ApiModelProperty(value = "门店是否存在（注销状态） true:是，false:否")
    private boolean isExistStore;

    private List<ProductSearchListDTO> productList;

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFansCount() {
        return fansCount;
    }

    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    public Boolean getFans() {
        return isFans;
    }

    public void setFans(Boolean fans) {
        isFans = fans;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getLogoPic() {
        return logoPic;
    }

    public void setLogoPic(String logoPic) {
        this.logoPic = logoPic;
    }

    public List<ProductSearchListDTO> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductSearchListDTO> productList) {
        this.productList = productList;
    }

    public boolean getIsExistStore() {
        return isExistStore;
    }

    public void setIsExistStore(boolean existStore) {
        isExistStore = existStore;
    }
}
