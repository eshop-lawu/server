package com.lawu.eshop.user.param;

import java.util.Date;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/1/19.
 */
public class AbnormalJobParam extends AbstractPageParam{

    private Date startTime;

    private Date endTime;

    private Integer tapeOutNumber;

    private int offset;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTapeOutNumber() {
        return tapeOutNumber;
    }

    public void setTapeOutNumber(Integer tapeOutNumber) {
        this.tapeOutNumber = tapeOutNumber;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
