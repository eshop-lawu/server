package com.lawu.eshop.property.srv.bo;

import com.lawu.eshop.property.constants.ConsumptionTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PointDetailBO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键
     */
    private Long id;

    /**
     * 积分标题
     */
    private String title;

    /**
     * 积分编号
     */
    private String pointNum;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 积分类型
     */
    private Byte pointType;

    /**
     * 积分
     */
    private BigDecimal point;

    /**
     * 资金流向
     */
    private ConsumptionTypeEnum direction;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    private String bizId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPointNum() {
		return pointNum;
	}

	public void setPointNum(String pointNum) {
		this.pointNum = pointNum;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public Byte getPointType() {
		return pointType;
	}

	public void setPointType(Byte pointType) {
		this.pointType = pointType;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}
	
	public ConsumptionTypeEnum getDirection() {
		return direction;
	}

	public void setDirection(ConsumptionTypeEnum direction) {
		this.direction = direction;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
}