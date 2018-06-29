package com.lawu.eshop.product.srv.bo;

/**
 * @author meishuquan
 * @date 2018/4/16.
 */
public class ProductTypeCountBO {

    private Integer sellingCount;

    private Integer warehouseCount;

    private Integer shopwindowCount;

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
