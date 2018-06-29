package com.lawu.eshop.user.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.MerchantAuditInfoDTO;
import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.eshop.user.dto.param.MerchantAuditTypeEnum;
import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.eshop.user.srv.bo.MerchantStoreAuditBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreBO;
import com.lawu.eshop.user.srv.bo.MerchantStoreInfoBO;
import com.lawu.eshop.user.srv.converter.MerchantStoreAuditConverter;
import com.lawu.eshop.user.srv.service.MerchantAuditService;
import com.lawu.eshop.user.srv.service.MerchantStoreInfoService;
import com.lawu.eshop.user.srv.service.MerchantStoreService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
@RestController
@RequestMapping(value = "audit/")
public class MerchantAuditController extends BaseController {

    @Autowired
    private MerchantAuditService merchantAuditService;
    @Autowired
    private MerchantStoreInfoService merchantStoreInfoService;
    @Autowired
    private MerchantStoreService merchantStoreService;

    /**
     * 门店审核
     * 根据审核记录id更新审核记录
     *
     * @param storeAuditId
     * @param auditParam
     * @return
     */
    @RequestMapping(value = "updateMerchantAudit/{storeAuditId}", method = RequestMethod.PUT)
    public Result updateMerchantAudit(@PathVariable("storeAuditId") Long storeAuditId, @RequestBody MerchantAuditParam auditParam) {
        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantStoreAudit(storeAuditId, auditParam.getMerchantStoreId());
        if(merchantStoreAuditBO == null){
            return successCreated(ResultCode.STORE_AUDIT_RECORD_NOT_EXIST);
        }
        if(merchantStoreAuditBO.getStatus() != MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_UNCHECK.val.byteValue()){
            return successCreated(ResultCode.STORE_AUDIT_RECORD_AUDITED);
        }
        merchantAuditService.updateMerchantAudit(storeAuditId, auditParam);
        return successCreated();
    }

    /**
     * 查询门店审失败或待审核信息
     *
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "getMerchantAuditInfo/{merchantId}", method = RequestMethod.GET)
    public Result<MerchantAuditInfoDTO> getMerchantAuditInfo(@PathVariable(value = "merchantId") Long merchantId) {

        //查询门店信息
        MerchantStoreInfoBO merchantStoreBO = merchantStoreInfoService.selectMerchantStoreByMId(merchantId);
        if (merchantStoreBO == null) {
            return successGet(ResultCode.MERCHANT_STORE_NO_EXIST);
        }
        MerchantStoreAuditBO storeAuditBO = merchantAuditService.getMerchantAuditInfo(merchantId);
        MerchantAuditInfoDTO auditInfoDTO = new MerchantAuditInfoDTO();
        auditInfoDTO.setRemark(storeAuditBO.getRemark());
        auditInfoDTO.setGmtCreate(storeAuditBO.getGmtCreate());
        auditInfoDTO.setMerchantAuditStatusEnum(MerchantAuditStatusEnum.getEnum(storeAuditBO.getStatus()));
        auditInfoDTO.setStoreStatus(merchantStoreBO.getStatusEnum());
        auditInfoDTO.setStoreId(merchantStoreBO.getMerchantStoreId());
        auditInfoDTO.setAuditTypeEnum(MerchantAuditTypeEnum.getEnum(storeAuditBO.getType()));
        return successGet(auditInfoDTO);
    }

    /**
     * 查询所有门店
     *
     * @param auditParam
     * @return
     */
    @RequestMapping(value = "listAllStoreAudit", method = RequestMethod.POST)
    public Result<Page<MerchantStoreAuditDTO>> listAllStoreAudit(@RequestBody ListStoreAuditParam auditParam) {
        Page<MerchantStoreAuditBO> merchantStoreAuditBOPage = merchantAuditService.listAllStoreAudit(auditParam);

        Page<MerchantStoreAuditDTO> page = new Page<>();
        page.setCurrentPage(merchantStoreAuditBOPage.getCurrentPage());
        page.setTotalCount(merchantStoreAuditBOPage.getTotalCount());
        page.setRecords(MerchantStoreAuditConverter.convertDTO(merchantStoreAuditBOPage.getRecords()));
        return successGet(page);
    }

    /**
     * 查询门店审核详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getMerchantStoreAudit/{id}", method = RequestMethod.GET)
    public Result<MerchantStoreAuditDTO> getMerchantStoreAudit(@PathVariable Long id) {
        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getMerchantStoreAuditById(id);
        if (merchantStoreAuditBO == null) {
            return successGet(ResultCode.RESOURCE_NOT_FOUND);
        }
        return successGet(MerchantStoreAuditConverter.convertDTO(merchantStoreAuditBO));
    }

    /**
     * 最近一条审核记录信息
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "getRecentMerchantAuditRecord", method = RequestMethod.GET)
    public Result<MerchantStoreAuditDTO> getRecentMerchantAuditRecord(@RequestParam("merchantId") Long merchantId){
        MerchantStoreBO merchantStoreBO = merchantStoreService.selectMerchantStore(merchantId);
        if (merchantStoreBO == null) {
            return successGet(ResultCode.MERCHANT_STORE_NO_EXIST);
        }
        MerchantStoreAuditBO merchantStoreAuditBO = merchantAuditService.getRecentMerchantAuditRecord(merchantId);
        return successGet(MerchantStoreAuditConverter.convertDTO(merchantStoreAuditBO));
    }

    /**
     * 查询最新门店审核通过的记录ID
     *
     * @param merchantId
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "getRecentMerchantAuditSuccessRecord/{merchantId}", method = RequestMethod.GET)
    public Result<Long> getRecentMerchantAuditSuccessRecord(@PathVariable("merchantId") Long merchantId) {
        Long merchantStoreAuditId = merchantAuditService.getRecentMerchantAuditSuccessRecord(merchantId);
        return successGet(merchantStoreAuditId);
    }

}
