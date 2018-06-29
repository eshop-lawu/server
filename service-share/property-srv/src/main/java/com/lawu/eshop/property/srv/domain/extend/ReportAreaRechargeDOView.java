package com.lawu.eshop.property.srv.domain.extend;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ReportAreaRechargeDOView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Byte status;

    private Byte channel;

    private String beginDate;

    private String endDate;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getChannel() {
        return channel;
    }

    public void setChannel(Byte channel) {
        this.channel = channel;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}