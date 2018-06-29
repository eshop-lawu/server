package com.lawu.eshop.mall.param;

import com.lawu.eshop.common.constants.MessageTypeEnum;

import io.swagger.annotations.ApiParam;

/**
 * 新建站内信息记录实体类
 * Created by Administrator on 2017/3/30.
 */
public class MessageInfoParam {

    /**
     * 关联ID
     */
    @ApiParam(name = "relateId", value = "关联ID")
    private Long relateId;

    /**
     * 消息类型
     */
    @ApiParam(name = "typeEnum", value = "消息类型 MESSAGE_TYPE_REFUND:退款，MESSAGE_TYPE_APPRAISE：评价，MESSAGE_TYPEUS_NOTICE：通知",required = true)
    private MessageTypeEnum typeEnum;

    private MessageTempParam messageParam;
    
    private Boolean isPush;
    
    private String imgUrl;

    private String remark;

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public MessageTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(MessageTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public MessageTempParam getMessageParam() {
        return messageParam;
    }

    public void setMessageParam(MessageTempParam messageParam) {
        this.messageParam = messageParam;
    }

	public Boolean getIsPush() {
		return isPush;
	}

	public void setIsPush(Boolean isPush) {
		this.isPush = isPush;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
