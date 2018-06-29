package com.lawu.eshop.user.param;

import com.lawu.eshop.common.constants.DelIndexTypeEnum;
import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author meishuquan
 * @date 2017/5/10.
 */
public class ListMerchantStoreParam extends AbstractPageParam{

    private Byte status;

    private Byte manageType;

    private DelIndexTypeEnum typeEnum;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getManageType() {
        return manageType;
    }

    public void setManageType(Byte manageType) {
        this.manageType = manageType;
    }

    public DelIndexTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(DelIndexTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
