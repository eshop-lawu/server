package com.lawu.eshop.game.dto;

import java.util.Date;

import com.lawu.eshop.game.constants.GameDialStatusEnum;

/**
 * @author zhangrc
 * @date 2018/3/16.
 */
public class GameDialDTO {

    private Long id;

    private String name;

    private String imgPath;

    private Integer point;

    private Integer freeCount;

    private GameDialStatusEnum status;
    
    private Integer shareAttendCount;

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

	public GameDialStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameDialStatusEnum status) {
		this.status = status;
	}

	public Integer getShareAttendCount() {
		return shareAttendCount;
	}

	public void setShareAttendCount(Integer shareAttendCount) {
		this.shareAttendCount = shareAttendCount;
	}
	

  
}
