package com.lawu.eshop.product.srv.bo;

import java.util.List;

/**
 * 抢购活动商品扩展BO
 * 
 * @author jiangxinjun
 * @createDate 2017年11月24日
 * @updateDate 2017年11月24日
 */
public class SeckillActivityProductExtendBO extends SeckillActivityProductBO {
    
    /**
     * 抢购活动
     */
    private SeckillActivityBO seckillActivity;
    
    /**
     * 商品型号列表
     */
    private List<SeckillActivityProductModelBO> productModelList;

    public SeckillActivityBO getSeckillActivity() {
        return seckillActivity;
    }

    public void setSeckillActivity(SeckillActivityBO seckillActivity) {
        this.seckillActivity = seckillActivity;
    }

    public List<SeckillActivityProductModelBO> getProductModelList() {
        return productModelList;
    }

    public void setProductModelList(List<SeckillActivityProductModelBO> productModelList) {
        this.productModelList = productModelList;
    }
}