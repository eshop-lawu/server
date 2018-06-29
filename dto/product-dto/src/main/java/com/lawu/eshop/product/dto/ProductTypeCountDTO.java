package com.lawu.eshop.product.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/4/16.
 */
public class ProductTypeCountDTO {

    @ApiModelProperty(value = "出售中", required = true)
    private Integer sellingCount;

    @ApiModelProperty(value = "仓库", required = true)
    private Integer warehouseCount;

    @ApiModelProperty(value = "橱窗", required = true)
    private Integer shopwindowCount;

    @ApiModelProperty(value = "售完", required = true)
    private Integer soldOutCount;

    public Integer getSellingCount() {
        return sellingCount;
    }

    public void setSellingCount(Integer sellingCount) {
        this.sellingCount = sellingCount;
    }

    public Integer getWarehouseCount() {
        return warehouseCount;
    }

    public void setWarehouseCount(Integer warehouseCount) {
        this.warehouseCount = warehouseCount;
    }

    public Integer getShopwindowCount() {
        return shopwindowCount;
    }

    public void setShopwindowCount(Integer shopwindowCount) {
        this.shopwindowCount = shopwindowCount;
    }

    public Integer getSoldOutCount() {
        return soldOutCount;
    }

    public void setSoldOutCount(Integer soldOutCount) {
        this.soldOutCount = soldOutCount;
    }
}
