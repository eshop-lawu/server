package com.lawu.eshop.user.param;

import com.lawu.framework.core.page.AbstractPageParam;
import io.swagger.annotations.ApiParam;

/**
 * @author lihj
 * @date 2018/6/8
 */
public class FansMerchantQueryParam extends AbstractPageParam {

    @ApiParam (name="longitude", value = "经度")
    private Double longitude;

    @ApiParam (name="latitude", value = "纬度")
    private Double latitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
