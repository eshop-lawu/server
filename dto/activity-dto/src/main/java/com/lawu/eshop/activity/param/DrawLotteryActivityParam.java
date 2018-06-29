package com.lawu.eshop.activity.param;

import com.lawu.eshop.activity.constants.DrawLotteryActivityStatusEnum;

/**
 * @author meishuquan
 * @date 2018/1/16.
 */
public class DrawLotteryActivityParam {

    private Long id;

    private String name;

    private String imgPath;

    private Byte grade;

    private DrawLotteryActivityStatusEnum statusEnum;

    private String remark;

    private String beginTime;

    private String endTime;

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

    public Byte getGrade() {
        return grade;
    }

    public void setGrade(Byte grade) {
        this.grade = grade;
    }

    public DrawLotteryActivityStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(DrawLotteryActivityStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
