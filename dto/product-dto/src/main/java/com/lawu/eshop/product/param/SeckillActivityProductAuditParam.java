package com.lawu.eshop.product.param;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 运营平台商品审核参数
 * 
 * @author jiangxinjun
 * @createDate 2017年12月1日
 * @updateDate 2017年12月1日
 */
public class SeckillActivityProductAuditParam {

    /**
     * 审核人员
     */
    @NotBlank(message = "审核人员不能为空")
    private String auditorAccount;

    public String getAuditorAccount() {
        return auditorAccount;
    }

    public void setAuditorAccount(String auditorAccount) {
        this.auditorAccount = auditorAccount;
    }

}
