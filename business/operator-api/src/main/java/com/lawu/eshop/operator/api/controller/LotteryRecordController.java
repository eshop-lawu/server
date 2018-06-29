package com.lawu.eshop.operator.api.controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.mall.constants.LotteryActivityStatusEnum;
import com.lawu.eshop.mall.dto.LotteryRecordOperatorDTO;
import com.lawu.eshop.mall.query.OperatorLotteryRecordQuery;
import com.lawu.eshop.operator.api.OperatorApiConfig;
import com.lawu.eshop.operator.api.service.LotteryActivityService;
import com.lawu.eshop.operator.api.service.LotteryRecordService;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.excel.util.ExcelExportRecordLoadCallback;
import com.lawu.excel.util.ExcelImportRowCallback;
import com.lawu.excel.util.ExcelImportRowResult;
import com.lawu.excel.util.ExcelUtils;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2017/11/27.
 */
@Api(tags = "lotteryRecord")
@RestController
@RequestMapping(value = "lotteryRecord/")
public class LotteryRecordController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(LotteryRecordController.class);

    @Autowired
    private LotteryRecordService lotteryRecordService;

    @Autowired
    private LotteryActivityService lotteryActivityService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private OperatorApiConfig operatorApiConfig;

    @ApiOperation(value = "参与抽奖列表", notes = "参与抽奖列表。（梅述全）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("lottery:record")
    @PageBody
    @RequestMapping(value = "listOperatorLotteryRecord", method = RequestMethod.POST)
    public Result<Page<LotteryRecordOperatorDTO>> listOperatorLotteryRecord(@RequestBody @ApiParam OperatorLotteryRecordQuery query) {
        Result<Page<LotteryRecordOperatorDTO>> result = lotteryRecordService.listOperatorLotteryRecord(query);
        if (!result.getModel().getRecords().isEmpty()) {
            for (LotteryRecordOperatorDTO operatorDTO : result.getModel().getRecords()) {
                Result<MemberDTO> memberResult = memberService.getMemberByAccount(operatorDTO.getAccount());
                if (isSuccess(memberResult)) {
                    operatorDTO.setNickName(memberResult.getModel().getNickname());
                }
            }
        }
        return result;
    }

    @ApiOperation(value = "导出抽奖信息", notes = "导出抽奖信息。（梅述全）", httpMethod = "POST")
    @RequestMapping(value = "exportExcel", method = RequestMethod.POST)
    @RequiresPermissions("lottery:export")
    public void exportExcel(@ModelAttribute OperatorLotteryRecordQuery query) {
        Result<Page<LotteryRecordOperatorDTO>> result = lotteryRecordService.listOperatorLotteryRecord(query);

        ZipSecureFile.setMinInflateRatio(0.001);
        OutputStream out = null;
        try {
            String fileName = DateUtil.getIntDateTime() + "_lottery.xlsx";
            HttpServletResponse response = getResponse();
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            ExcelUtils.exportExcel(out, new ExcelExportRecordLoadCallback() {

                private int currentPage = 1;
                private int pageSize = 1000;
                private int totalCount = result.getModel().getTotalCount();

                @Override
                public String[] getCellTitles() {
                    return new String[]{"账号", "昵称", "奖品名称", "抽奖次数"};
                }

                @Override
                public List<String[]> loadRecords() {
                    query.setCurrentPage(currentPage);
                    query.setPageSize(pageSize);
                    Result<Page<LotteryRecordOperatorDTO>> result = lotteryRecordService.listOperatorLotteryRecord(query);

                    List<String[]> records = new ArrayList<>();
                    for (LotteryRecordOperatorDTO dto : result.getModel().getRecords()) {
                        Result<MemberDTO> memberResult = memberService.getMemberByAccount(dto.getAccount());
                        if (isSuccess(memberResult)) {
                            dto.setNickName(memberResult.getModel().getName());
                        }

                        records.add(
                                new String[]{dto.getAccount(), dto.getNickName(), dto.getPrizeName(), String.valueOf(dto.getLotteryCount()),});
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

    @ApiOperation(value = "导出中奖结果模板", notes = "导出中奖结果模板。（梅述全）", httpMethod = "POST")
    @RequestMapping(value = "exportExcelTemplate", method = RequestMethod.POST)
    public void exportExcelTemplate() {
        ZipSecureFile.setMinInflateRatio(0.001);
        OutputStream out = null;
        try {
            String fileName = "lotteryTemplate.xlsx";
            HttpServletResponse response = getResponse();
            response.setContentType("multipart/form-data");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            ExcelUtils.exportExcel(out, new ExcelExportRecordLoadCallback() {

                private int currentPage = 1;
                private int pageSize = 1000;

                @Override
                public String[] getCellTitles() {
                    return new String[]{"中奖账号"};
                }

                @Override
                public List<String[]> loadRecords() {
                    List<String[]> records = new ArrayList<>();
                    records.add(
                            new String[]{"13666666666"});
                    records.add(
                            new String[]{"13888888888"});

                    currentPage++;
                    return records;
                }

                @Override
                public boolean isFinished() {
                    if (currentPage == 1) {
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

    @ApiOperation(value = "导入中奖结果处理", notes = "导入中奖结果处理。（梅述全）", httpMethod = "POST")
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    @RequiresPermissions("lottery:import")
    public Result importExcel(@RequestParam @ApiParam(required = true, value = "文件路径") String filePath,
                              @RequestParam @ApiParam(required = true, value = "活动ID") Long lotteryActivityId) {

        lotteryActivityService.updateLotteryActivityStatusById(lotteryActivityId, LotteryActivityStatusEnum.LOTTERYED);
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(operatorApiConfig.getImageUrl() + filePath);
            URLConnection conn = url.openConnection();
            DataInputStream in = new DataInputStream(conn.getInputStream());
            List<ExcelImportRowResult> excelImportRowResults = ExcelUtils.importExcel(
                    in, new ExcelImportRowCallback() {
                        @Override
                        public ExcelImportRowResult checkAndSave(int row, List<Object> cellValues) {
                            ExcelImportRowResult rowResult = new ExcelImportRowResult();
                            rowResult.setRowNum(row);
                            rowResult.setCellValues(cellValues);
                            rowResult.setErr(false);
                            rowResult.setBlockedErr(false);
                            if (cellValues.get(0) == null || StringUtils.isEmpty(cellValues.get(0).toString())) {
                                return rowResult;
                            }

                            Result result = lotteryRecordService.updateLotteryResult(lotteryActivityId, cellValues.get(0).toString());
                            if (ResultCode.SUCCESS != result.getRet()) {
                                rowResult.setErr(true);
                            }
                            return rowResult;
                        }
                    });
            for (ExcelImportRowResult rowResult : excelImportRowResults) {
                if (rowResult.getCellValues().get(0) != null && StringUtils.isNotEmpty(rowResult.getCellValues().get(0).toString())) {
                    sb.append(rowResult.getCellValues().get(0).toString()).append(",");
                }
            }
            String errIds = StringUtils.isEmpty(sb.toString()) ? "" : sb.substring(0, sb.length() - 1);
            return successCreated(errIds);
        } catch (FileNotFoundException e) {
            logger.error("EXCEL文件没找到--------{}", e.getMessage());
        } catch (Exception e) {
            logger.error("处理异常----------{}", e.getMessage());
        }
        return successCreated(ResultCode.FAIL);
    }

}
