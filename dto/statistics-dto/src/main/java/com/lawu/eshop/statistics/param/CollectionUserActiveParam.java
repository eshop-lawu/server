package com.lawu.eshop.statistics.param;

import java.util.Date;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
public class CollectionUserActiveParam {

    private Date gmtCreate;

    private String cityId;

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
