package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.framework.core.page.Page;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
public interface MerchantAuditService {
    /**
     * 根据审核列表修改审核信息
     *
     * @param storeAuditId
     */
    void updateMerchantAudit(Long storeAuditId, MerchantAuditParam auditParam);

    MerchantStoreAuditBO getMerchantAuditInfo(Long merchantId);

    MerchantStoreAuditBO getMerchantAuditInfoByUncheck(Long merchantId, Byte status);

    /**
     * 查询审核门店列表
     *
     * @return
     */
    Page<MerchantStoreAuditBO> listAllStoreAudit(ListStoreAuditParam auditParam);

    /**
     * 查询门店审核详情
     *
     * @param id
     * @return
     */
    MerchantStoreAuditBO getMerchantStoreAuditById(Long id);

    MerchantStoreAuditBO getMerchantStoreAudit(Long id, Long merchantStoreId);

    void setAuditInfoShow(Long merchantId);

    MerchantStoreAuditBO getRecentMerchantAuditRecord(Long merchantId);

    /**
     * 查询最新门店审核通过的记录ID
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    Long getRecentMerchantAuditSuccessRecord(Long merchantId);

}
