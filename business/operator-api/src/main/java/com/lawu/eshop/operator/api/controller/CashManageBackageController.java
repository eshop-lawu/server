package com.lawu.eshop.operator.api.controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.constants.MessageTypeEnum;
import com.lawu.eshop.common.constants.UserCommonConstant;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.param.MessageInfoParam;
import com.lawu.eshop.mall.param.MessageTempParam;
import com.lawu.eshop.operator.api.OperatorApiConfig;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.CashManageBackageService;
import com.lawu.eshop.operator.api.service.MessageService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.constants.CashOperEnum;
import com.lawu.eshop.property.constants.CashStatusEnum;
import com.lawu.eshop.property.dto.WithdrawCashBackageQueryDTO;
import com.lawu.eshop.property.dto.WithdrawCashBackageQuerySumDTO;
import com.lawu.eshop.property.param.CashBackageOperDataParam;
import com.lawu.eshop.property.param.CashBackageOperParam;
import com.lawu.eshop.property.param.CashBackageQueryDataParam;
import com.lawu.eshop.property.param.CashBackageQueryDetailParam;
import com.lawu.eshop.property.param.CashBackageQueryParam;
import com.lawu.eshop.property.param.CashBackageQuerySumParam;
import com.lawu.excel.util.ExcelExportRecordLoadCallback;
import com.lawu.excel.util.ExcelImportRowCallback;
import com.lawu.excel.util.ExcelImportRowResult;
import com.lawu.excel.util.ExcelUtils;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.shiro.util.UserUtil;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * Description: 运营后台提现管理
 * </p>
 *
 * @author Yangqh
 * @date 2017年4月10日 下午2:08:11
 */
@Api(tags = "cashBackage")
@RestController
@RequestMapping(value = "cashBackage/")
public class CashManageBackageController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CashManageBackageController.class);

    @Autowired
    private CashManageBackageService cashManageBackageService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private OperatorApiConfig operatorApiConfig;

    /**
     * 运营平台财务提现管理
     *
     * @param param
     * @return
     * @throws Exception
     */
    @PageBody
    @ApiOperation(value = "商家、用户提现管理", notes = "商家、用户提现明细查询,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "findCashInfo", method = RequestMethod.POST)
    @RequiresPermissions("withdraw:list")
    public Result<Page<WithdrawCashBackageQueryDTO>> findCashInfo(@RequestBody CashBackageQueryParam param) {
        CashBackageQueryDataParam dparam = new CashBackageQueryDataParam();
        dparam.setContent(param.getContent());
        dparam.setRegionPath(param.getRegionPath());
        dparam.setBeginDate(param.getBeginDate());
        dparam.setEndDate(param.getEndDate());
        dparam.setCashStatsuEnum(param.getCashStatsuEnum());
        dparam.setBankAccountTypeEnum(param.getBankAccountTypeEnum());
        dparam.setUserTypeEnum(param.getUserTypeEnum());
        dparam.setCurrentPage(param.getCurrentPage());
        dparam.setPageSize(param.getPageSize());
        dparam.setSortOrder(param.getSortOrder());
        dparam.setSortName(param.getSortName());
        Result<Page<WithdrawCashBackageQueryDTO>> dtos = cashManageBackageService.findCashInfo(dparam);
        return dtos;
    }

    @ApiOperation(value = "商家、用户提现管理总数统计", notes = "商家、用户提现明细查询总数统计,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "getTotalNum", method = RequestMethod.POST)
    @RequiresAuthentication
    public Result<WithdrawCashBackageQuerySumDTO> getTotalNum(
            @ModelAttribute @ApiParam CashBackageQuerySumParam param) {
        return cashManageBackageService.getTotalNum(param);
    }

    @PageBody
    @ApiOperation(value = "商家、用户提现详情", notes = "商家、用户提现详情查询,[]（杨清华）", httpMethod = "POST")
    @RequiresPermissions("withdraw:detail")
    @RequestMapping(value = "findCashInfoDetail", method = RequestMethod.POST)
    public Result<Page<WithdrawCashBackageQueryDTO>> findCashInfoDetail(
            @RequestBody @ApiParam CashBackageQueryDetailParam param) {

        Result<Page<WithdrawCashBackageQueryDTO>> dtos = cashManageBackageService.findCashInfoDetail(param);
        return dtos;
    }

    @SuppressWarnings("rawtypes")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.WITHDRAW_CASH_UPDATE)
    @ApiOperation(value = "商家、用户提现数据操作", notes = "商家、用户提现数据操作,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "updateWithdrawCash", method = RequestMethod.POST)
    @RequiresPermissions("withdraw:edit")
    public Result updateWithdrawCash(@Valid CashBackageOperParam param, HttpServletRequest request, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }

        CashBackageOperDataParam dparam = new CashBackageOperDataParam();
        dparam.setId(param.getId());
        dparam.setUserNum(param.getUserNum());
        dparam.setCashOperEnum(param.getCashOperEnum());
        dparam.setFailReason(param.getFailReason());
        dparam.setAuditUserName(UserUtil.getCurrentUserAccount());
        dparam.setAuditUserId(0L);

        // 发送站内消息
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        MessageTempParam messageTempParam = new MessageTempParam();
        if (CashOperEnum.ACCEPT.getVal().equals(param.getCashOperEnum().getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_APPLY);
        } else if (CashOperEnum.SUCCESS.getVal().equals(param.getCashOperEnum().getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_SUCCESS);
        } else if (CashOperEnum.FAILURE.getVal().equals(param.getCashOperEnum().getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_FAIL);
            messageTempParam.setFailReason(param.getFailReason());
        }

        List<String> idsArray = Arrays.asList(param.getId().split(","));
        List<String> userNumsArray = Arrays.asList(param.getUserNum().split(","));
        int success = 0, failure = 0;
        String failureMsg = "";
        for (int i = 0; i < idsArray.size(); i++) {
            dparam.setId(idsArray.get(i));
            Result result1 = cashManageBackageService.updateWithdrawCash(dparam);
            if (ResultCode.SUCCESS != result1.getRet()) {
                failureMsg = result1.getMsg();
                logger.error("提现操作({})失败！提现ID={}，失败原因：{}", param.getCashOperEnum(), idsArray.get(i), result1.getMsg());
                failure++;
                continue;
            }
            success++;

            String userNum = userNumsArray.get(i);
            if (userNum.startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                messageTempParam.setUserName("E店会员");
            } else if (userNum.startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                messageTempParam.setUserName("E店商家");
            }
            messageInfoParam.setMessageParam(messageTempParam);
            messageService.saveMessage(userNum, messageInfoParam);
        }
        if (failure == 0) {
            return successCreated();
        } else {
            if (idsArray.size() > 1) {
                return successCreated(ResultCode.FAIL, "成功" + success + "条，失败" + failure + "条。");
            } else {
                return successCreated(ResultCode.FAIL, "失败原因：" + failureMsg);
            }

        }
    }

    @ApiOperation(value = "导出提现数据", notes = "导出提现数据。（梅述全）", httpMethod = "POST")
    @RequestMapping(value = "exportExcel", method = RequestMethod.POST)
    @RequiresPermissions("withdraw:export")
    public void exportExcel(@ModelAttribute CashBackageQueryParam param) {
        CashBackageQueryDataParam dparam = new CashBackageQueryDataParam();
        dparam.setContent(param.getContent());
        dparam.setRegionPath(param.getRegionPath());
        dparam.setBeginDate(param.getBeginDate());
        dparam.setEndDate(param.getEndDate());
        dparam.setCashStatsuEnum(param.getCashStatsuEnum());
        dparam.setBankAccountTypeEnum(param.getBankAccountTypeEnum());
        dparam.setUserTypeEnum(param.getUserTypeEnum());
        dparam.setCurrentPage(param.getCurrentPage());
        dparam.setPageSize(param.getPageSize());
        dparam.setSortOrder(param.getSortOrder());
        dparam.setSortName(param.getSortName());
        Result<Page<WithdrawCashBackageQueryDTO>> pageResult = cashManageBackageService.findCashInfo(dparam);

        ZipSecureFile.setMinInflateRatio(0.001);
        OutputStream out = null;
        try {
            String fileName = DateUtil.getIntDateTime() + "_withdraw.xlsx";
            HttpServletResponse response = getResponse();
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            ExcelUtils.exportExcel(out, new ExcelExportRecordLoadCallback() {

                private int currentPage = 1;
                private int pageSize = 1000;
                private int totalCount = pageResult.getModel().getTotalCount();

                @Override
                public String[] getCellTitles() {
                    return new String[]{"ID", "编号", "用户账号", "所属区域", "提现单号", "时间", "卡号", "账户", "银行","账户区域", "支行", "申请金额(元)", "手续费(元)", "到账金额(元)", "状态", "处理人", "渠道"};
                }

                @Override
                public List<String[]> loadRecords() {
                    dparam.setCurrentPage(currentPage);
                    dparam.setPageSize(pageSize);
                    Result<Page<WithdrawCashBackageQueryDTO>> dtos = cashManageBackageService.findCashInfo(dparam);

                    List<String[]> records = new ArrayList<>();
                    for (WithdrawCashBackageQueryDTO dto : dtos.getModel().getRecords()) {
                        records.add(
                                new String[]{String.valueOf(dto.getId()), dto.getUserNum(), dto.getAccount(), dto.getRegionFullName(), dto.getCashNumber(), dto.getGmtCreate(), dto.getBankNo(), dto.getBusinessBankAccount(), dto.getBankName(),dto.getBankRegionName(), dto.getBankBranchName(), dto.getCashMoney().toString(), dto.getPoundage().toString(), dto.getMoney().toString(), dto.getCashStatsuEnum().getName(), dto.getAuditUserName(), dto.getChannel()});
                    }
                    currentPage++;
                    return records;
                }

                @Override
                public boolean isFinished() {
                    if ((currentPage - 1) * pageSize < totalCount) {
                        return false;
                    }
                    return true;
                }

                @Override
                public int getRowAccessWindowSize() {
                    return pageSize;
                }
            });

        } catch (IOException e) {
            logger.error("导出EXCEL异常=========={}", e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation(value = "导入提现数据批量处理", notes = "导入提现数据批量处理。（梅述全）", httpMethod = "POST")
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    @RequiresPermissions("withdraw:import")
    public Result importExcel(@RequestParam @ApiParam(required = true, value = "文件路径") String filePath,
                              @RequestParam @ApiParam(required = true, value = "修改状态") CashStatusEnum cashStatusEnum,
                              @RequestParam @ApiParam(required = true, value = "失败原因") String failReason) {
        CashBackageOperDataParam dparam = new CashBackageOperDataParam();
        dparam.setCashOperEnum(CashOperEnum.getEnum(cashStatusEnum.getVal()));
        dparam.setFailReason(failReason);
        dparam.setAuditUserId(0L);
        dparam.setAuditUserName(UserUtil.getCurrentUserAccount());

        // 发送站内消息
        MessageInfoParam messageInfoParam = new MessageInfoParam();
        messageInfoParam.setRelateId(0L);
        MessageTempParam messageTempParam = new MessageTempParam();
        if (CashOperEnum.ACCEPT.getVal().equals(CashOperEnum.getEnum(cashStatusEnum.getVal()).getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_APPLY);
        } else if (CashOperEnum.SUCCESS.getVal().equals(CashOperEnum.getEnum(cashStatusEnum.getVal()).getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_SUCCESS);
        } else if (CashOperEnum.FAILURE.getVal().equals(CashOperEnum.getEnum(cashStatusEnum.getVal()).getVal())) {
            messageInfoParam.setTypeEnum(MessageTypeEnum.MESSAGE_TYPE_WITHDRAW_FAIL);
            messageTempParam.setFailReason(failReason);
        }

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(operatorApiConfig.getImageUrl() + filePath);
            URLConnection conn = url.openConnection();
            DataInputStream in = new DataInputStream(conn.getInputStream());
            List<ExcelImportRowResult> excelImportRowResults = ExcelUtils.importExcel(
                    in, new ExcelImportRowCallback() {
                        @Override
                        public ExcelImportRowResult checkAndSave(int row, List<Object> cellValues) {
                            ExcelImportRowResult result = new ExcelImportRowResult();
                            result.setRowNum(row);
                            result.setCellValues(cellValues);

                            dparam.setId(cellValues.get(0).toString());
                            dparam.setUserNum(cellValues.get(1).toString());

                            //提现成功操作时，第三方账户提现不支持Excel批量处理
                            String channel = cellValues.get(1).toString();
                            if(!BankAccountTypeEnum.BANK.getName().equals(channel) && CashOperEnum.SUCCESS.getVal().equals(CashOperEnum.getEnum(cashStatusEnum.getVal()).getVal())){
                                result.setErr(true);
                                result.setBlockedErr(false);
                                return result;
                            }
                            Result result1 = cashManageBackageService.updateWithdrawCash(dparam);
                            if (ResultCode.SUCCESS == result1.getRet()) {
                                if (cellValues.get(1).toString().startsWith(UserCommonConstant.MEMBER_NUM_TAG)) {
                                    messageTempParam.setUserName("E店会员");
                                } else if (cellValues.get(1).toString().startsWith(UserCommonConstant.MERCHANT_NUM_TAG)) {
                                    messageTempParam.setUserName("E店商家");
                                }
                                messageInfoParam.setMessageParam(messageTempParam);
                                messageService.saveMessage(cellValues.get(1).toString(), messageInfoParam);
                            }
                            result.setErr(false);
                            result.setBlockedErr(false);

                            if (ResultCode.SUCCESS != result1.getRet()) {
                                result.setErr(true);
                            }
                            return result;
                        }
                    });
            for (ExcelImportRowResult rowResult : excelImportRowResults) {
                sb.append(rowResult.getCellValues().get(0).toString()).append(",");
            }
            String errIds = StringUtils.isEmpty(sb.toString()) ? "" : sb.substring(0, sb.length() - 1);
            return successCreated(errIds);
        } catch (FileNotFoundException e) {
            logger.error("EXCEL文件没找到--------{}", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理异常----------{}", e.getMessage());
        }
        return successCreated(ResultCode.FAIL);
    }

}
