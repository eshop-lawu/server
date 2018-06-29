package com.lawu.eshop.jobs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangyong
 * @date 2017/5/22.
 */
@Component
public class JobsConfig {

    @Value(value = "${rongyun_message_download_url}")
    private String downLoadDir;

    @Value(value = "${industry_type_id}")
    private Integer industryTypeId;

    @Value(value = "${page_size}")
    private Integer pageSize;

    @Value(value = "${beh.analyze.tape.out.number}")
    private Integer tapeOutNumber;

    @Value(value = "${beh.analyze.same.ip.count}")
    private Integer sameIpCount;

    @Value(value = "${beh.analyze.same.ip.rate}")
    private Double sameIpRate;

    public String getDownLoadDir() {
        return downLoadDir;
    }

    public Integer getIndustryTypeId() {
        return industryTypeId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getTapeOutNumber() {
        return tapeOutNumber;
    }

    public Integer getSameIpCount() {
        return sameIpCount;
    }

    public Double getSameIpRate() {
        return sameIpRate;
    }

}
