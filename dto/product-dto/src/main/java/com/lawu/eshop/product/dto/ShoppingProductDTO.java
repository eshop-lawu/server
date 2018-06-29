package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author meishuquan
 * @date 2017/4/21.
 */
public class ShoppingProductDTO {

    @ApiModelProperty(value = "商品分类")
    private List<RecommendProductCategoryDTO> recommendProductCategoryDTOS;

    @ApiModelProperty(value = "顶部推荐")
    private List<ProductSearchDTO> topProduct;

    @ApiModelProperty(value = "今日推荐")
    private List<ProductSearchDTO> recommendProduct;

    @ApiModelProperty(value = "热门商品")
    private List<ProductSearchDTO> hotProduct;

    @ApiModelProperty(value = "E店必购")
    private List<ProductSearchDTO> buyProduct;

    @ApiModelProperty(value = "特色好货")
    private List<ProductSearchDTO> featureProduct;

    @ApiModelProperty(value = "实惠单品")
    private List<ProductSearchDTO> benefitProduct;

    public List<ProductSearchDTO> getRecommendProduct() {
        return recommendProduct;
    }

    public void setRecommendProduct(List<ProductSearchDTO> recommendProduct) {
        this.recommendProduct = recommendProduct;
    }

    public List<ProductSearchDTO> getBuyProduct() {
        return buyProduct;
    }

    public void setBuyProduct(List<ProductSearchDTO> buyProduct) {
        this.buyProduct = buyProduct;
    }

    public List<ProductSearchDTO> getFeatureProduct() {
        return featureProduct;
    }

    public void setFeatureProduct(List<ProductSearchDTO> featureProduct) {
        this.featureProduct = featureProduct;
    }

    public List<ProductSearchDTO> getBenefitProduct() {
        return benefitProduct;
    }

    public void setBenefitProduct(List<ProductSearchDTO> benefitProduct) {
        this.benefitProduct = benefitProduct;
    }

    public List<ProductSearchDTO> getTopProduct() {
        return topProduct;
    }

    public void setTopProduct(List<ProductSearchDTO> topProduct) {
        this.topProduct = topProduct;
    }

    public List<RecommendProductCategoryDTO> getRecommendProductCategoryDTOS() {
        return recommendProductCategoryDTOS;
    }

    public void setRecommendProductCategoryDTOS(List<RecommendProductCategoryDTO> recommendProductCategoryDTOS) {
        this.recommendProductCategoryDTOS = recommendProductCategoryDTOS;
    }

    public List<ProductSearchDTO> getHotProduct() {
        return hotProduct;
    }

    public void setHotProduct(List<ProductSearchDTO> hotProduct) {
        this.hotProduct = hotProduct;
    }

    
}
