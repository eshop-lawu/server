package com.lawu.eshop.activity.param;

import com.lawu.framework.core.page.AbstractPageParam;

/**
 * @author zhangyong
 * @date 2018/5/7.
 */
public class RichPowerTaskResetParam extends AbstractPageParam {

    private int offset;

    @Override
    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
