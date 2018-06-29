package com.lawu.eshop.activity.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/2/6.
 */
public class PointLotteryActivityPrizeImageBO {

    private Long id;

    private Long pointLotteryActivityId;

    private String imagePath;

    private Integer orderNum;

    private Byte type;

    private Date gmtModified;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointLotteryActivityId() {
        return pointLotteryActivityId;
    }

    public void setPointLotteryActivityId(Long pointLotteryActivityId) {
        this.pointLotteryActivityId = pointLotteryActivityId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
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
}
