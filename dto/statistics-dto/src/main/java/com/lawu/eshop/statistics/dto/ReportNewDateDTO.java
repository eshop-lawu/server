package com.lawu.eshop.statistics.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zhangrc
 * @date 2017/9/8.
 */
public class ReportNewDateDTO {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date gmtReport;

    public ReportNewDateDTO() {

    }

    public ReportNewDateDTO(Date gmtReport) {
		super();
		this.gmtReport = gmtReport;
	}

	public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }
}
