package com.lawu.eshop.game.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/15.
 */
public class GameDialDetailDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "所需积分")
    private Integer point;

    @ApiModelProperty(value = "分享可免费次数")
    private Integer shareAttendCount;

    @ApiModelProperty(value = "奖品列表")
    private List<GameDialPrizeInfoDTO> prizeInfoDTOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getShareAttendCount() {
        return shareAttendCount;
    }

    public void setShareAttendCount(Integer shareAttendCount) {
        this.shareAttendCount = shareAttendCount;
    }

    public List<GameDialPrizeInfoDTO> getPrizeInfoDTOS() {
        return prizeInfoDTOS;
    }

    public void setPrizeInfoDTOS(List<GameDialPrizeInfoDTO> prizeInfoDTOS) {
        this.prizeInfoDTOS = prizeInfoDTOS;
    }
}
