package com.lawu.eshop.activity.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;
import com.lawu.eshop.common.constants.MemberGradeEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/1/12.
 */
public class DrawLotteryActivityDTO {

    @ApiModelProperty(value = "活动id")
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String name;

    @ApiModelProperty(value = "活动图片")
    private String imgPath;

    @ApiModelProperty(value = "等级枚举")
    private MemberGradeEnum gradeEnum;

    @ApiModelProperty(value = "等级值")
    private Byte grade;

    @ApiModelProperty(value = "等级描述")
    private String gradeDes;

    @ApiModelProperty(value = "活动状态")
    private DrawLotteryActivityStatusEnum statusEnum;

    @ApiModelProperty(value = "状态描述")
    private String statusDes;

    @ApiModelProperty(value = "活动规则(以'|'分隔每一条规则)")
    private String remark;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public MemberGradeEnum getGradeEnum() {
        return gradeEnum;
    }

    public void setGradeEnum(MemberGradeEnum gradeEnum) {
        this.gradeEnum = gradeEnum;
    }

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public String getGradeDes() {
        return gradeDes;
    }

    public void setGradeDes(String gradeDes) {
        this.gradeDes = gradeDes;
    }

    public DrawLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
