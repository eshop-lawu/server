package com.lawu.eshop.operator.api.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.MerchantAuditService;
import com.lawu.eshop.operator.api.service.MerchantService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.api.service.UserService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.operator.dto.UserListDTO;
import com.lawu.eshop.user.constants.MerchantAuditStatusEnum;
import com.lawu.eshop.user.dto.MerchantSNSDTO;
import com.lawu.eshop.user.dto.MerchantStoreAuditDTO;
import com.lawu.eshop.user.param.ListStoreAuditParam;
import com.lawu.eshop.user.param.MerchantAuditParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2017/3/31.
 */
@Api(tags = "merchantAudit")
@RestController
@RequestMapping(value = "merchantAudit/")
public class MerchantAuditController extends BaseController {

    @Autowired
    private MerchantAuditService merchantAuditService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MerchantService merchantService;

    /**
     * 门店审核列表
     *
     * @param auditParam
     * @return
     */
    @ApiOperation(value = "门店列表", notes = "查询门店列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @PageBody
    @RequestMapping(value = "listStoreAudit", method = RequestMethod.POST)
    @RequiresPermissions("storeAudit:list")
    public Result<Page<MerchantStoreAuditDTO>> listStoreAudit(@RequestBody @ApiParam ListStoreAuditParam auditParam) {
        Result<Page<MerchantStoreAuditDTO>> result = merchantAuditService.listStoreAudit(auditParam);
        List<MerchantStoreAuditDTO> list = result.getModel().getRecords();
        if (list != null && !list.isEmpty()) {
            for (MerchantStoreAuditDTO merchantStoreAuditDTO : list) {
                if (merchantStoreAuditDTO.getAuditorId() != null && merchantStoreAuditDTO.getAuditorId() > 0) {
                    Result<UserListDTO> userListDTOResult = userService.findUserById(merchantStoreAuditDTO.getAuditorId());
                    if (isSuccess(userListDTOResult)) {
                        merchantStoreAuditDTO.setAuditorName(userListDTOResult.getModel().getName());
                    }
                }
            }
        }
        return result;
    }

    /**
     * 门店审核详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "门店审核详情", notes = "查询门店审核详情。[1002]（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequiresPermissions("storeAudit:detail")
    @RequestMapping(value = "getMerchantStoreAudit/{id}", method = RequestMethod.GET)
    public Result<MerchantStoreAuditDTO> getMerchantStoreAudit(@PathVariable @ApiParam(required = true, value = "门店审核ID") Long id) {
        return merchantAuditService.getMerchantStoreAuditById(id);
    }

    /**
     * 门店审核接口
     *
     * @param storeAuditId
     * @param auditParam
     * @return
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.STORE_AUDIT_SUCCESS,idParamName ="storeAuditId")
    @ApiOperation(value = "门店审核信息", notes = "根据门店审核记录ID更新对应信息  [1000]（章勇）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("storeAudit:audit")
    @RequestMapping(value = "updateMerchantAudit", method = RequestMethod.POST)
    public Result updateMerchantAudit(@RequestParam @ApiParam(required = true, value = "门店审核ID") Long storeAuditId,
                                      @ModelAttribute @ApiParam MerchantAuditParam auditParam) {
        Integer auditorId = 0;
        Result<UserListDTO> userResult = userService.getUserByAccount(UserUtil.getCurrentUserAccount());
        if(isSuccess(userResult)){
            auditorId = userResult.getModel().getId();
        }
        auditParam.setAuditorId(auditorId);
        Result result = merchantAuditService.updateMerchantAudit(storeAuditId, auditParam);
        if(!isSuccess(result)){
            return result;
        }

        //发送站内消息
        Result<MerchantStoreAuditDTO> storeAuditDTOResult = merchantAuditService.getMerchantStoreAuditById(storeAuditId);
        Result<MerchantSNSDTO> merchantSNSDTOResult = merchantService.selectMerchantInfo(storeAuditDTOResult.getModel().getMerchantId());

        MessageInfoParam messageInfoParam = new MessageInfoParam();
        MessageTempParam messageTempParam = new MessageTempParam();
        messageTempParam.setStoreName(storeAuditDTOResult.getModel().getName());
        messageInfoParam.setRelateId(storeAuditDTOResult.getModel().getStoreId());
        if (auditParam.getAuditStatusEnum().val == MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECKED.val) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_STORE_SUCCESS);
        } else if (auditParam.getAuditStatusEnum().val == MerchantAuditStatusEnum.MERCHANT_AUDIT_STATUS_CHECK_FAILED.val) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_CHECK_STORE_FAIL);
            messageTempParam.setFailReason(auditParam.getRemark());
        }
        messageInfoParam.setMessageParam(messageTempParam);
        messageService.saveMessage(merchantSNSDTOResult.getModel().getNum(), messageInfoParam);

        return result;
    }

    @ApiOperation(value = "查询最新门店审核通过记录ID", notes = "查询最新门店审核通过记录ID。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "getRecentMerchantAuditSuccessRecord/{merchantId}", method = RequestMethod.GET)
    public Result<Long> getRecentMerchantAuditSuccessRecord(@PathVariable @ApiParam(required = true, value = "商家ID") Long merchantId) {
        return merchantAuditService.getRecentMerchantAuditSuccessRecord(merchantId);
    }

}
