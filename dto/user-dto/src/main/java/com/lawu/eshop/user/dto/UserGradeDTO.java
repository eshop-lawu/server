package com.lawu.eshop.user.dto;

import java.util.Date;

public class UserGradeDTO {

    private Long id;

    private String gradeName;

    private Byte gradeValue;

    private Integer gradeWeight;

    private Integer minGrowthValue;

    private Integer freeLotteryCount;

    private Integer nextFreeLotteryCount;

    private Integer lotteryActivityPoint;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Byte getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Byte gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Integer getGradeWeight() {
        return gradeWeight;
    }

    public void setGradeWeight(Integer gradeWeight) {
        this.gradeWeight = gradeWeight;
    }

    public Integer getMinGrowthValue() {
        return minGrowthValue;
    }

    public void setMinGrowthValue(Integer minGrowthValue) {
        this.minGrowthValue = minGrowthValue;
    }

    public Integer getLotteryActivityPoint() {
        return lotteryActivityPoint;
    }

    public void setLotteryActivityPoint(Integer lotteryActivityPoint) {
        this.lotteryActivityPoint = lotteryActivityPoint;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getFreeLotteryCount() {
        return freeLotteryCount;
    }

    public void setFreeLotteryCount(Integer freeLotteryCount) {
        this.freeLotteryCount = freeLotteryCount;
    }

    public Integer getNextFreeLotteryCount() {
        return nextFreeLotteryCount;
    }

    public void setNextFreeLotteryCount(Integer nextFreeLotteryCount) {
        this.nextFreeLotteryCount = nextFreeLotteryCount;
    }
}
