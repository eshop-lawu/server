package com.lawu.eshop.activity.srv.domain.extend;

import java.io.Serializable;
import java.util.Date;

public class HelpRedpacketAttendDetailDOExtend implements Serializable {

    private Long id;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}