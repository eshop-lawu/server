package com.lawu.eshop.product.srv.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductModelDO implements Serializable {
    /**
     *
     * 主键
     * product_model.id
     *
     * @mbg.generated
     */
    private Long id;

    /**
     *
     * 商家ID
     * product_model.merchant_id
     *
     * @mbg.generated
     */
    private Long merchantId;

    /**
     *
     * 商品ID
     * product_model.product_id
     *
     * @mbg.generated
     */
    private Long productId;

    /**
     *
     * 编号
     * product_model.num
     *
     * @mbg.generated
     */
    private String num;

    /**
     *
     * 名称
     * product_model.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     *
     * 原价
     * product_model.original_price
     *
     * @mbg.generated
     */
    private BigDecimal originalPrice;

    /**
     *
     * 现价
     * product_model.price
     *
     * @mbg.generated
     */
    private BigDecimal price;

    /**
     *
     * 销量
     * product_model.sales_volume
     *
     * @mbg.generated
     */
    private Integer salesVolume;

    /**
     *
     * 库存
     * product_model.inventory
     *
     * @mbg.generated
     */
    private Integer inventory;

    /**
     *
     * 状态(0-删除1-有效)
     * product_model.status
     *
     * @mbg.generated
     */
    private Boolean status;

    /**
     *
     * 规格选项1ID
     * product_model.spec_option_1
     *
     * @mbg.generated
     */
    private Long specOption1;

    /**
     *
     * 规格选项2ID
     * product_model.spec_option_2
     *
     * @mbg.generated
     */
    private Long specOption2;

    /**
     *
     * 规格选项3ID
     * product_model.spec_option_3
     *
     * @mbg.generated
     */
    private Long specOption3;

    /**
     *
     * 规格选项4ID
     * product_model.spec_option_4
     *
     * @mbg.generated
     */
    private Long specOption4;

    /**
     *
     * 规格选项5ID
     * product_model.spec_option_5
     *
     * @mbg.generated
     */
    private Long specOption5;

    /**
     *
     * 修改时间
     * product_model.gmt_modified
     *
     * @mbg.generated
     */
    private Date gmtModified;

    /**
     *
     * 创建时间
     * product_model.gmt_create
     *
     * @mbg.generated
     */
    private Date gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table product_model
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.id
     *
     * @return the value of product_model.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.id
     *
     * @param id the value for product_model.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.merchant_id
     *
     * @return the value of product_model.merchant_id
     *
     * @mbg.generated
     */
    public Long getMerchantId() {
        return merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.merchant_id
     *
     * @param merchantId the value for product_model.merchant_id
     *
     * @mbg.generated
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.product_id
     *
     * @return the value of product_model.product_id
     *
     * @mbg.generated
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.product_id
     *
     * @param productId the value for product_model.product_id
     *
     * @mbg.generated
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.num
     *
     * @return the value of product_model.num
     *
     * @mbg.generated
     */
    public String getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.num
     *
     * @param num the value for product_model.num
     *
     * @mbg.generated
     */
    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.name
     *
     * @return the value of product_model.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.name
     *
     * @param name the value for product_model.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.original_price
     *
     * @return the value of product_model.original_price
     *
     * @mbg.generated
     */
    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.original_price
     *
     * @param originalPrice the value for product_model.original_price
     *
     * @mbg.generated
     */
    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.price
     *
     * @return the value of product_model.price
     *
     * @mbg.generated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.price
     *
     * @param price the value for product_model.price
     *
     * @mbg.generated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.sales_volume
     *
     * @return the value of product_model.sales_volume
     *
     * @mbg.generated
     */
    public Integer getSalesVolume() {
        return salesVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.sales_volume
     *
     * @param salesVolume the value for product_model.sales_volume
     *
     * @mbg.generated
     */
    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.inventory
     *
     * @return the value of product_model.inventory
     *
     * @mbg.generated
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.inventory
     *
     * @param inventory the value for product_model.inventory
     *
     * @mbg.generated
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.status
     *
     * @return the value of product_model.status
     *
     * @mbg.generated
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.status
     *
     * @param status the value for product_model.status
     *
     * @mbg.generated
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.spec_option_1
     *
     * @return the value of product_model.spec_option_1
     *
     * @mbg.generated
     */
    public Long getSpecOption1() {
        return specOption1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.spec_option_1
     *
     * @param specOption1 the value for product_model.spec_option_1
     *
     * @mbg.generated
     */
    public void setSpecOption1(Long specOption1) {
        this.specOption1 = specOption1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.spec_option_2
     *
     * @return the value of product_model.spec_option_2
     *
     * @mbg.generated
     */
    public Long getSpecOption2() {
        return specOption2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.spec_option_2
     *
     * @param specOption2 the value for product_model.spec_option_2
     *
     * @mbg.generated
     */
    public void setSpecOption2(Long specOption2) {
        this.specOption2 = specOption2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.spec_option_3
     *
     * @return the value of product_model.spec_option_3
     *
     * @mbg.generated
     */
    public Long getSpecOption3() {
        return specOption3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.spec_option_3
     *
     * @param specOption3 the value for product_model.spec_option_3
     *
     * @mbg.generated
     */
    public void setSpecOption3(Long specOption3) {
        this.specOption3 = specOption3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.spec_option_4
     *
     * @return the value of product_model.spec_option_4
     *
     * @mbg.generated
     */
    public Long getSpecOption4() {
        return specOption4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.spec_option_4
     *
     * @param specOption4 the value for product_model.spec_option_4
     *
     * @mbg.generated
     */
    public void setSpecOption4(Long specOption4) {
        this.specOption4 = specOption4;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.spec_option_5
     *
     * @return the value of product_model.spec_option_5
     *
     * @mbg.generated
     */
    public Long getSpecOption5() {
        return specOption5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.spec_option_5
     *
     * @param specOption5 the value for product_model.spec_option_5
     *
     * @mbg.generated
     */
    public void setSpecOption5(Long specOption5) {
        this.specOption5 = specOption5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.gmt_modified
     *
     * @return the value of product_model.gmt_modified
     *
     * @mbg.generated
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.gmt_modified
     *
     * @param gmtModified the value for product_model.gmt_modified
     *
     * @mbg.generated
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_model.gmt_create
     *
     * @return the value of product_model.gmt_create
     *
     * @mbg.generated
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_model.gmt_create
     *
     * @param gmtCreate the value for product_model.gmt_create
     *
     * @mbg.generated
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}