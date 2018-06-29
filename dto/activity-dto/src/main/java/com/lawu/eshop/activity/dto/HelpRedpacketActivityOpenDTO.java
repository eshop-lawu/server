package com.lawu.eshop.activity.dto;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketActivityTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询助力红包活动是否开启
 * 
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@ApiModel
public class HelpRedpacketActivityOpenDTO {
    
    /**
     * 红包活动id
     */
    private Integer id;
    
    /**
     * 活动是否开放
     */
    @ApiModelProperty(value = "活动是否开放", required = true)
    private Boolean open;
    
    /**
    * 是否关闭活动入口
    */
    @ApiModelProperty(value = "是否关闭活动入口", required = true)
    private Boolean closeEntry;
    
    /**
     * 活动开始图片
     */
    @ApiModelProperty(value = "活动开始图片", required = true)
    private String startPic;

    /**
     * 活动结束图片
     */
    @ApiModelProperty(value = "活动结束图片", required = true)
    private String endPic;

    /**
     * 活动结束链接
     */
    @ApiModelProperty(value = "活动结束链接", required = true)
    private String endUrl;
    
    /**
     * 活动状态
     */
    @ApiModelProperty(value = "活动状态(NOT_STARTED-未开始|REGISTING-报名中|REGIST_END-报名结束|BEGINNING-开抢中|END-已结束)", required = true)
    private HelpRedpacketActivityStatusEnum status;
    
    /**
     * 红包活动类型
     */
    @ApiModelProperty(value = "红包活动类型(NEW_YEAR-新年活动|LANTERN-元宵活动)", required = true)
    private RedPacketActivityTypeEnum type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getCloseEntry() {
        return closeEntry;
    }

    public void setCloseEntry(Boolean closeEntry) {
        this.closeEntry = closeEntry;
    }

    public String getStartPic() {
        return startPic;
    }

    public void setStartPic(String startPic) {
        this.startPic = startPic;
    }

    public String getEndPic() {
        return endPic;
    }

    public void setEndPic(String endPic) {
        this.endPic = endPic;
    }

    public String getEndUrl() {
        return endUrl;
    }

    public void setEndUrl(String endUrl) {
        this.endUrl = endUrl;
    }

    public HelpRedpacketActivityStatusEnum getStatus() {
        return status;
    }

    public void setStatus(HelpRedpacketActivityStatusEnum status) {
        this.status = status;
    }

    public RedPacketActivityTypeEnum getType() {
        return type;
    }

    public void setType(RedPacketActivityTypeEnum type) {
        this.type = type;
    }
    
}