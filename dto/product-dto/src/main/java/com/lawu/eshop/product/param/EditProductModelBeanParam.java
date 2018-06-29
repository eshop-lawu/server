package com.lawu.eshop.product.param;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EditProductModelBeanParam {

    @ApiModelProperty(value = "商品型号ID（新增传0）", required = true)
    @NotNull(message = "商品型号ID不能为空")
    private Long productModelId;

    @ApiModelProperty(value = "规格选项的id字符串，以,划分（与名称同序），自定义从-1递减", required = true)
    @NotBlank(message = "规格选项的id字符串不能为空")
    private String specOptionIdStr;

    @ApiModelProperty(value = "规格选项的名称字符串，以,划分（与id同序）", required = true)
    @NotBlank(message = "规格选项的名称字符串不能为空")
    private String specOptionNameStr;

    @ApiModelProperty(value = "库存", required = true)
    @NotNull(message = "商品型号库存不能为空")
    private Integer inventory;

    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "商品型号价格不能为空")
    private BigDecimal price;

    public Long getProductModelId() {
        return productModelId;
    }

    public void setProductModelId(Long productModelId) {
        this.productModelId = productModelId;
    }

    public String getSpecOptionIdStr() {
        return specOptionIdStr;
    }

    public void setSpecOptionIdStr(String specOptionIdStr) {
        this.specOptionIdStr = specOptionIdStr;
    }

    public String getSpecOptionNameStr() {
        return specOptionNameStr;
    }

    public void setSpecOptionNameStr(String specOptionNameStr) {
        this.specOptionNameStr = specOptionNameStr;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
