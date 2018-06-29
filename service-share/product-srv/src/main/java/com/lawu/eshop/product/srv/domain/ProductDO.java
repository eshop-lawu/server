package com.lawu.eshop.product.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductDO implements Serializable {
    /**
     *
     * 主键
     * product.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 商家ID
     * product.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     *
     * 商家编号
     * product.merchant_num
     *
     * @mbg.generated
     */
    private String merchantNum;

    /**
     *
     * 分类ID
     * product.category_id
     *
     * @mbg.generated
     */
    private Integer categoryId;

    /**
     *
     * 商品分类名称
     * product.category_name
     *
     * @mbg.generated
     */
    private String categoryName;

    /**
     *
     * 子类目ID
     * product.category_subitem_id
     *
     * @mbg.generated
     */
    private Long categorySubitemId;

    /**
     *
     * 商品子类目名称
     * product.category_subitem_name
     *
     * @mbg.generated
     */
    private String categorySubitemName;

    /**
     *
     * 品牌
     * product.brand_id
     *
     * @mbg.generated
     */
    private Long brandId;

    /**
     *
     * 品牌名称
     * product.brand_name
     *
     * @mbg.generated
     */
    private String brandName;

    /**
     *
     * 商品规格json([{"list":[{"name":"L","id":1,"icon":""},{"name":"M","id":2,"icon":""}],"spec":{"name":"大小","id":1}},{"list":[{"name":"白色","id":3,"icon":""},{"name":"红色","id":4,"icon":""}],"spec":{"name":"颜色","id":2}}])
     * product.spec
     *
     * @mbg.generated
     */
    private String spec;

    /**
     *
     * 编号
     * product.num
     *
     * @mbg.generated
     */
    private String num;

    /**
     *
     * 名称
     * product.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * 关键词
     * product.keywords
     *
     * @mbg.generated
     */
    private String keywords;

    /**
     *
     * 特征图片
     * product.feature_image
     *
     * @mbg.generated
     */
    private String featureImage;

    /**
     *
     * 平均日销售
     * product.average_daily_sales
     *
     * @mbg.generated
     */
    private BigDecimal averageDailySales;

    /**
     *
     * 状态(1-删除2-上架3-下架)
     * product.status
     *
     * @mbg.generated
     */
    private Byte status;

    /**
     *
     * 位置（1-橱窗）
     * product.position
     *
     * @mbg.generated
     */
    private Byte position;

    /**
     *
     * 是否支持退换货(0-否1-是)
     * product.is_allow_refund
     *
     * @mbg.generated
     */
    private Boolean isAllowRefund;

    /**
     *
     * 商品总库存
     * product.total_inventory
     *
     * @mbg.generated
     */
    private Integer totalInventory;

    /**
     *
     * 商品总销量
     * product.total_sales_volume
     *
     * @mbg.generated
     */
    private Integer totalSalesVolume;

    /**
     *
     * 总收藏
     * product.total_favorite
     *
     * @mbg.generated
     */
    private Integer totalFavorite;

    /**
     *
     * 型号最低价
     * product.min_price
     *
     * @mbg.generated
     */
    private BigDecimal minPrice;

    /**
     *
     * 型号现价最低价对应的原价
     * product.max_price
     *
     * @mbg.generated
     */
    private BigDecimal maxPrice;

    /**
     *
     * 发货地，省
     * product.province_id
     *
     * @mbg.generated
     */
    private Long provinceId;

    /**
     *
     * 发货地，省名称
     * product.province_name
     *
     * @mbg.generated
     */
    private String provinceName;

    /**
     *
     * 发货地，市
     * product.city_id
     *
     * @mbg.generated
     */
    private Long cityId;

    /**
     *
     * 发货地，市名称
     * product.city_name
     *
     * @mbg.generated
     */
    private String cityName;

    /**
     *
     * 是否包邮（0-不包邮|1-包邮）
     * product.is_express_free
     *
     * @mbg.generated
     */
    private Boolean isExpressFree;

    /**
     *
     * 运费信息json({"defaultPieceNumber":"默认运费，多少件","defaultPieceMoney":"默认运费，多少件内多少钱","addPieceNumber":"每加多少件","addPieceMoney":"每加多少件，加多少钱"})
     * product.freight
     *
     * @mbg.generated
     */
    private String freight;

    /**
     *
     * 备注
     * product.remark
     *
     * @mbg.generated
     */
    private String remark;

    /**
     *
     * 完成上新时间
     * product.gmt_finish_rich_task
     *
     * @mbg.generated
     */
    private Date gmtFinishRichTask;

    /**
     *
     * 上架时间
     * product.gmt_up
     *
     * @mbg.generated
     */
    private Date gmtUp;

    /**
     *
     * 下架时间
     * product.gmt_down
     *
     * @mbg.generated
     */
    private Date gmtDown;

    /**
     *
     * 修改时间
     * product.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * product.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     *
     * 详细描述
     * product.content
     *
     * @mbg.generated
     */
    private String content;

    /**
     *
     * 商品详情图片描述
     * product.image_content
     *
     * @mbg.generated
     */
    private String imageContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.id
     *
     * @return the value of product.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.id
     *
     * @param id the value for product.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.merchant_id
     *
     * @return the value of product.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.merchant_id
     *
     * @param merchantId the value for product.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.merchant_num
     *
     * @return the value of product.merchant_num
     *
     * @mbg.generated
     */
    public String getMerchantNum() {
        return merchantNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.merchant_num
     *
     * @param merchantNum the value for product.merchant_num
     *
     * @mbg.generated
     */
    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum == null ? null : merchantNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.category_id
     *
     * @return the value of product.category_id
     *
     * @mbg.generated
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.category_id
     *
     * @param categoryId the value for product.category_id
     *
     * @mbg.generated
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.category_name
     *
     * @return the value of product.category_name
     *
     * @mbg.generated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.category_name
     *
     * @param categoryName the value for product.category_name
     *
     * @mbg.generated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.category_subitem_id
     *
     * @return the value of product.category_subitem_id
     *
     * @mbg.generated
     */
    public Long getCategorySubitemId() {
        return categorySubitemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.category_subitem_id
     *
     * @param categorySubitemId the value for product.category_subitem_id
     *
     * @mbg.generated
     */
    public void setCategorySubitemId(Long categorySubitemId) {
        this.categorySubitemId = categorySubitemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.category_subitem_name
     *
     * @return the value of product.category_subitem_name
     *
     * @mbg.generated
     */
    public String getCategorySubitemName() {
        return categorySubitemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.category_subitem_name
     *
     * @param categorySubitemName the value for product.category_subitem_name
     *
     * @mbg.generated
     */
    public void setCategorySubitemName(String categorySubitemName) {
        this.categorySubitemName = categorySubitemName == null ? null : categorySubitemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.brand_id
     *
     * @return the value of product.brand_id
     *
     * @mbg.generated
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.brand_id
     *
     * @param brandId the value for product.brand_id
     *
     * @mbg.generated
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.brand_name
     *
     * @return the value of product.brand_name
     *
     * @mbg.generated
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.brand_name
     *
     * @param brandName the value for product.brand_name
     *
     * @mbg.generated
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.spec
     *
     * @return the value of product.spec
     *
     * @mbg.generated
     */
    public String getSpec() {
        return spec;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.spec
     *
     * @param spec the value for product.spec
     *
     * @mbg.generated
     */
    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.num
     *
     * @return the value of product.num
     *
     * @mbg.generated
     */
    public String getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.num
     *
     * @param num the value for product.num
     *
     * @mbg.generated
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.name
     *
     * @return the value of product.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.name
     *
     * @param name the value for product.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.keywords
     *
     * @return the value of product.keywords
     *
     * @mbg.generated
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.keywords
     *
     * @param keywords the value for product.keywords
     *
     * @mbg.generated
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.feature_image
     *
     * @return the value of product.feature_image
     *
     * @mbg.generated
     */
    public String getFeatureImage() {
        return featureImage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.feature_image
     *
     * @param featureImage the value for product.feature_image
     *
     * @mbg.generated
     */
    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage == null ? null : featureImage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.average_daily_sales
     *
     * @return the value of product.average_daily_sales
     *
     * @mbg.generated
     */
    public BigDecimal getAverageDailySales() {
        return averageDailySales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.average_daily_sales
     *
     * @param averageDailySales the value for product.average_daily_sales
     *
     * @mbg.generated
     */
    public void setAverageDailySales(BigDecimal averageDailySales) {
        this.averageDailySales = averageDailySales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.status
     *
     * @return the value of product.status
     *
     * @mbg.generated
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.status
     *
     * @param status the value for product.status
     *
     * @mbg.generated
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.position
     *
     * @return the value of product.position
     *
     * @mbg.generated
     */
    public Byte getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.position
     *
     * @param position the value for product.position
     *
     * @mbg.generated
     */
    public void setPosition(Byte position) {
        this.position = position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.is_allow_refund
     *
     * @return the value of product.is_allow_refund
     *
     * @mbg.generated
     */
    public Boolean getIsAllowRefund() {
        return isAllowRefund;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.is_allow_refund
     *
     * @param isAllowRefund the value for product.is_allow_refund
     *
     * @mbg.generated
     */
    public void setIsAllowRefund(Boolean isAllowRefund) {
        this.isAllowRefund = isAllowRefund;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.total_inventory
     *
     * @return the value of product.total_inventory
     *
     * @mbg.generated
     */
    public Integer getTotalInventory() {
        return totalInventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.total_inventory
     *
     * @param totalInventory the value for product.total_inventory
     *
     * @mbg.generated
     */
    public void setTotalInventory(Integer totalInventory) {
        this.totalInventory = totalInventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.total_sales_volume
     *
     * @return the value of product.total_sales_volume
     *
     * @mbg.generated
     */
    public Integer getTotalSalesVolume() {
        return totalSalesVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.total_sales_volume
     *
     * @param totalSalesVolume the value for product.total_sales_volume
     *
     * @mbg.generated
     */
    public void setTotalSalesVolume(Integer totalSalesVolume) {
        this.totalSalesVolume = totalSalesVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.total_favorite
     *
     * @return the value of product.total_favorite
     *
     * @mbg.generated
     */
    public Integer getTotalFavorite() {
        return totalFavorite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.total_favorite
     *
     * @param totalFavorite the value for product.total_favorite
     *
     * @mbg.generated
     */
    public void setTotalFavorite(Integer totalFavorite) {
        this.totalFavorite = totalFavorite;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.min_price
     *
     * @return the value of product.min_price
     *
     * @mbg.generated
     */
    public BigDecimal getMinPrice() {
        return minPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.min_price
     *
     * @param minPrice the value for product.min_price
     *
     * @mbg.generated
     */
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.max_price
     *
     * @return the value of product.max_price
     *
     * @mbg.generated
     */
    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.max_price
     *
     * @param maxPrice the value for product.max_price
     *
     * @mbg.generated
     */
    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.province_id
     *
     * @return the value of product.province_id
     *
     * @mbg.generated
     */
    public Long getProvinceId() {
        return provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.province_id
     *
     * @param provinceId the value for product.province_id
     *
     * @mbg.generated
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.province_name
     *
     * @return the value of product.province_name
     *
     * @mbg.generated
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.province_name
     *
     * @param provinceName the value for product.province_name
     *
     * @mbg.generated
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.city_id
     *
     * @return the value of product.city_id
     *
     * @mbg.generated
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.city_id
     *
     * @param cityId the value for product.city_id
     *
     * @mbg.generated
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.city_name
     *
     * @return the value of product.city_name
     *
     * @mbg.generated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.city_name
     *
     * @param cityName the value for product.city_name
     *
     * @mbg.generated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.is_express_free
     *
     * @return the value of product.is_express_free
     *
     * @mbg.generated
     */
    public Boolean getIsExpressFree() {
        return isExpressFree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.is_express_free
     *
     * @param isExpressFree the value for product.is_express_free
     *
     * @mbg.generated
     */
    public void setIsExpressFree(Boolean isExpressFree) {
        this.isExpressFree = isExpressFree;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.freight
     *
     * @return the value of product.freight
     *
     * @mbg.generated
     */
    public String getFreight() {
        return freight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.freight
     *
     * @param freight the value for product.freight
     *
     * @mbg.generated
     */
    public void setFreight(String freight) {
        this.freight = freight == null ? null : freight.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.remark
     *
     * @return the value of product.remark
     *
     * @mbg.generated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.remark
     *
     * @param remark the value for product.remark
     *
     * @mbg.generated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.gmt_finish_rich_task
     *
     * @return the value of product.gmt_finish_rich_task
     *
     * @mbg.generated
     */
    public Date getGmtFinishRichTask() {
        return gmtFinishRichTask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.gmt_finish_rich_task
     *
     * @param gmtFinishRichTask the value for product.gmt_finish_rich_task
     *
     * @mbg.generated
     */
    public void setGmtFinishRichTask(Date gmtFinishRichTask) {
        this.gmtFinishRichTask = gmtFinishRichTask;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.gmt_up
     *
     * @return the value of product.gmt_up
     *
     * @mbg.generated
     */
    public Date getGmtUp() {
        return gmtUp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.gmt_up
     *
     * @param gmtUp the value for product.gmt_up
     *
     * @mbg.generated
     */
    public void setGmtUp(Date gmtUp) {
        this.gmtUp = gmtUp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.gmt_down
     *
     * @return the value of product.gmt_down
     *
     * @mbg.generated
     */
    public Date getGmtDown() {
        return gmtDown;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.gmt_down
     *
     * @param gmtDown the value for product.gmt_down
     *
     * @mbg.generated
     */
    public void setGmtDown(Date gmtDown) {
        this.gmtDown = gmtDown;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.gmt_modified
     *
     * @return the value of product.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.gmt_modified
     *
     * @param gmtModified the value for product.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.gmt_create
     *
     * @return the value of product.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.gmt_create
     *
     * @param gmtCreate the value for product.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.content
     *
     * @return the value of product.content
     *
     * @mbg.generated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.content
     *
     * @param content the value for product.content
     *
     * @mbg.generated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.image_content
     *
     * @return the value of product.image_content
     *
     * @mbg.generated
     */
    public String getImageContent() {
        return imageContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.image_content
     *
     * @param imageContent the value for product.image_content
     *
     * @mbg.generated
     */
    public void setImageContent(String imageContent) {
        this.imageContent = imageContent == null ? null : imageContent.trim();
    }
}