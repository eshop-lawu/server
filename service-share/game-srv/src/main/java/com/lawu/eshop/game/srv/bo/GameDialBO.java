package com.lawu.eshop.game.srv.bo;

import java.util.Date;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialBO {

    private Long id;

    private String name;

    private String imgPath;

    private Integer point;

    private Integer freeCount;
    
    private Integer shareAttendCount;

    private Byte status;

    private Date gmtModified;

    private Date gmtCreate;

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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(Integer freeCount) {
        this.freeCount = freeCount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

	public Integer getShareAttendCount() {
		return shareAttendCount;
	}

	public void setShareAttendCount(Integer shareAttendCount) {
		this.shareAttendCount = shareAttendCount;
	}
    
    
}
