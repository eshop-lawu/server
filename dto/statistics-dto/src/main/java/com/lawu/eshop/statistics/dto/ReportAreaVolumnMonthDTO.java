package com.lawu.eshop.statistics.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

public class ReportAreaVolumnMonthDTO {
    private Long id;

    @ApiModelProperty(value = "总销售额")
	private BigDecimal reportTotalMoney;
	
	@ApiModelProperty(value = "消费类型: 1--买单, 2--购物")
	private Byte type;
	
	@ApiModelProperty(value = "省编号")
	private Integer provinceId;
	
	@ApiModelProperty(value = "市编号")
	private Integer cityId;
	
	@ApiModelProperty(value = "区编号")
	private Integer areaId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date gmtReport;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getReportTotalMoney() {
        return reportTotalMoney;
    }

    public void setReportTotalMoney(BigDecimal reportTotalMoney) {
        this.reportTotalMoney = reportTotalMoney;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getGmtReport() {
        return gmtReport;
    }

    public void setGmtReport(Date gmtReport) {
        this.gmtReport = gmtReport;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
