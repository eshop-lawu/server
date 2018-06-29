package com.lawu.eshop.property.srv.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.common.dto.CommissionInvitersUserDTO;
import com.lawu.eshop.property.constants.CommissionEnum;
import com.lawu.eshop.property.constants.PropertyType;
import com.lawu.eshop.property.constants.UserTypeEnum;
import com.lawu.eshop.property.param.CommissionParam;
import com.lawu.eshop.property.srv.PropertySrvApplicationTest;
import com.lawu.eshop.property.srv.domain.LoveDetailDO;
import com.lawu.eshop.property.srv.domain.LoveDetailDOExample;
import com.lawu.eshop.property.srv.domain.PointDetailDO;
import com.lawu.eshop.property.srv.domain.PointDetailDOExample;
import com.lawu.eshop.property.srv.domain.PropertyDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDO;
import com.lawu.eshop.property.srv.domain.PropertyInfoDOExample;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDOExample;
import com.lawu.eshop.property.srv.mapper.LoveDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PointDetailDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyDOMapper;
import com.lawu.eshop.property.srv.mapper.PropertyInfoDOMapper;
import com.lawu.eshop.property.srv.mapper.TransactionDetailDOMapper;
import com.lawu.eshop.property.srv.service.CommissionService;
import com.lawu.excel.util.ExcelImportRowCallback;
import com.lawu.excel.util.ExcelImportRowResult;
import com.lawu.excel.util.ExcelImportVerifyException;
import com.lawu.excel.util.ExcelUtils;
import com.lawu.utils.DateUtil;

/**
 * @author yangqh
 * @date 2017/7/12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PropertySrvApplicationTest.class)
public class CommissionServiceImplTest {

    @Autowired
    private CommissionService commissionService;
    @Autowired
    private PropertyDOMapper propertyDOMapper;
    @Autowired
    private TransactionDetailDOMapper transactionDetailDOMapper;
    @Autowired
    private LoveDetailDOMapper loveDetailDOMapper;
    @Autowired
    private PointDetailDOMapper pointDetailDOMapper;
    @Autowired
    private PropertyInfoDOMapper propertyInfoDOMapper;

    //行数配置
    private int ad_row = 1;//广告正确数据所在行数

    //VIP等级对应的各级提成
    //行数范围
    private int vipCommissionRowBegin = 2;
    private int vipCommissionRowEnd = 8;
    //列表范围
    private int vipCols = 2;//VIP列数
    private int correctCommissionCols1 = 3;  //正确上1级提成列数
    private int correctCommissionCols2 = 4;  //正确上2级提成列数
    private int correctCommissionCols3 = 5;  //正确上3级提成列数

    //邀请关系
    //行数范围
    private int inviter1RowBegin = 17;
    private int inviter1RowEnd = 21;
    private int inviter2RowBegin = 22;
    private int inviter2RowEnd = 26;
    private int inviter3RowBegin = 27;
    private int inviter3RowEnd = 31;
    //列数范围
    private int invite_user_num_cols = 1;
    private int invite_current_user_num_cols = 2;
    private int invite_level_cols = 3;
    private int invite_flag_cols = 4;
    private int invite_depth_cols = 5;

    private BigDecimal adClickMoney = new BigDecimal(1);//点广告金额
    private BigDecimal payMoney = new BigDecimal(1);//买单金额
    private BigDecimal shoppingMoney = new BigDecimal(1);//购物金额

    private String importFilePath = "F:/commission_template.xlsx";
    private String exportFilePath = "F:/commission_report_"+ DateUtil.getDateFormat(new Date(),"yyyyMMddHHmmss")+".xlsx";

    @Transactional(rollbackFor = Exception.class)
    @Rollback
    @Test
    public void calculation() {

        BaseData data = writeExcel(importFilePath);
        List<ResultData> resultDataList = new ArrayList<>();

        //初始化基础数据
        initProperty();
        List<CommissionInvitersUserDTO> inviters = new ArrayList<>();
        inviters.addAll(data.getInviters1());
        inviters.addAll(data.getInviters2());
        inviters.addAll(data.getInviters3());
        initPropertyInfo(inviters);

        //点广告业务
        Long adId = 1L;
        Long memberAdRecardId = 1L;
        adCommission(BizEnum.AD, data.getInviters1(), data, resultDataList, adId, memberAdRecardId);

        memberAdRecardId = 2L;
        adCommission(BizEnum.AD, data.getInviters2(), data, resultDataList, adId, memberAdRecardId);

        //买单
        Long bizId = 3L;
        salesAndVolumeCommission(BizEnum.PAY, data.getInviters1(), data, resultDataList, bizId, CommissionEnum.PAY);
        salesAndVolumeCommission(BizEnum.PAY, data.getInviters3(), data, resultDataList, bizId, CommissionEnum.PAY);

        //购物
        bizId = 4L;
        salesAndVolumeCommission(BizEnum.SHOPPING, data.getInviters2(), data, resultDataList, bizId, CommissionEnum.SHOPPING);
        salesAndVolumeCommission(BizEnum.SHOPPING, data.getInviters3(), data, resultDataList, bizId, CommissionEnum.SHOPPING);

        //结果导出
        exportReport(resultDataList, getResultBalanceAndPoint(inviters));

        System.out.println("finish!");

//        for (ResultData result : resultDataList) {
//            System.out.println(result);
//        }
//        //查询总余额和积分
//        for (BalanceAndPointTotalResult dto : getResultBalanceAndPoint(inviters)) {
//            System.out.println(dto.getUserNum() + "->余额:" + dto.getBalance() + ",积分:" + dto.getPoint() + ",爱心账户:" + dto.getLove());
//        }
    }

    /**
     * 导出结果报表
     *
     * @param resultDataList
     * @param totalResultList
     */
    public void exportReport(List<ResultData> resultDataList, List<BalanceAndPointTotalResult> totalResultList) {

        int rowNum = 0;
        FileOutputStream out = null;
        XSSFWorkbook workbook = null;
        XSSFSheet sh;
        try {
            out = new FileOutputStream(exportFilePath);
            workbook = new XSSFWorkbook();
            sh = workbook.createSheet();

            // 单元格样式map，只需要在生成标题单元格时创建一次，后续的数据单元格可共用
            Map<Integer, XSSFCellStyle> cellStyles = new HashMap<>();
            // 创建单元格标题行（即第一行）
            XSSFRow titleRow = sh.createRow(rowNum);
            String cellTitles[] = new String[]{"业务", "操作人（编号）", "上1级", "提成", "爱心账户", "上2级", "提成", "爱心账户", "上3级", "提成"};
            for (int i = 0; i < cellTitles.length; i++) {
                XSSFCellStyle cellStyle = fillCellStyle(workbook, IndexedColors.BRIGHT_GREEN.getIndex());
                createCell(titleRow, i, cellTitles[i], cellStyle);
                if (i == 1 || i == 2 || i == 5 || i == 8) {
                    sh.setColumnWidth(i, 20 * 256);
                } else {
                    sh.setColumnWidth(i, 12 * 256);
                }

            }
            rowNum++;
            // 加载数据
            for (int i = 0; i < resultDataList.size(); i++, rowNum++) {
                ResultData result = resultDataList.get(i);
                // 创建数据行
                XSSFRow row = sh.createRow(rowNum);
                String[] cellValues = {result.getBizEnum().getName(), result.getCurrentUserNum(),
                        result.getUserNum1(), result.isCommissionIsCorrect1() ? result.getCommissionValue1() : "#" + result.getCommissionValue1(), result.isLoveIsCorrect1() ? result.getLoveValue1() : "#" + result.getLoveValue1(),
                        result.getUserNum2(), result.isCommissionIsCorrect2() ? result.getCommissionValue2() : "#" + result.getCommissionValue2(), result.isLoveIsCorrect2() ? result.getLoveValue2() : "#" + result.getLoveValue2(),
                        result.getUserNum3(), result.isCommissionIsCorrect3() ? result.getCommissionValue3() : "#" + result.getCommissionValue3()
                };
                // 填充单元格
                for (int cellNum = 0; cellNum < cellTitles.length; cellNum++) {
                    if (cellValues[cellNum].startsWith("#")) {
                        createCell(row, cellNum, cellValues[cellNum], fillCellStyle(workbook, IndexedColors.RED.getIndex()));
                    } else {
                        createCell(row, cellNum, cellValues[cellNum], workbook.createCellStyle());
                    }
                }
            }

            //-----------------------------------------------------------------------------------------------------------------------------

            rowNum++;
            titleRow = sh.createRow(rowNum);
            cellTitles = new String[]{"序号", "用户编号", "余额", "积分", "爱心账户"};
            for (int i = 0; i < cellTitles.length; i++) {
                XSSFCellStyle cellStyle = fillCellStyle(workbook, IndexedColors.BRIGHT_GREEN.getIndex());
                createCell(titleRow, i, cellTitles[i], cellStyle);
            }
            rowNum++;
            // 加载数据
            for (int i = 0; i < totalResultList.size(); i++, rowNum++) {
                BalanceAndPointTotalResult result = totalResultList.get(i);
                // 创建数据行
                XSSFRow row = sh.createRow(rowNum);
                String[] cellValues = {(i + 1) + "", result.getUserNum(), result.getBalance().toString(), result.getPoint().toString(), result.getLove().toString()};
                // 填充单元格
                for (int cellNum = 0; cellNum < cellTitles.length; cellNum++) {
                    createCell(row, cellNum, cellValues[cellNum], cellStyles.get(cellNum));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private XSSFCellStyle fillCellStyle(XSSFWorkbook workbook, short index) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(index);
        return cellStyle;
    }

    private static void createCell(XSSFRow row, int cellNum, String value, XSSFCellStyle cellStyle) {
        XSSFCell cell = row.createCell(cellNum);
        cell.setCellValue(value);
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 查询总余额和积分
     *
     * @param inviters
     * @return
     */
    private List<BalanceAndPointTotalResult> getResultBalanceAndPoint(List<CommissionInvitersUserDTO> inviters) {
        List<BalanceAndPointTotalResult> totalResultList = new ArrayList<>();
        if (inviters == null) {
            List<PropertyInfoDO> list = propertyInfoDOMapper.selectByExample(new PropertyInfoDOExample());
            for (PropertyInfoDO pdo : list) {
                BalanceAndPointTotalResult result = new BalanceAndPointTotalResult();
                result.setUserNum(pdo.getUserNum());
                result.setBalance(list.get(0).getBalance());
                result.setPoint(list.get(0).getPoint());
                result.setLove(list.get(0).getLoveAccount());
                totalResultList.add(result);
            }
        } else {
            Set<String> userNumsSet = new HashSet<>();
            for (CommissionInvitersUserDTO dto : inviters) {
                userNumsSet.add(dto.getUserNum());
            }
            Iterator<String> itr = userNumsSet.iterator();
            while (itr.hasNext()) {
                String userNum = itr.next();
                PropertyInfoDOExample example = new PropertyInfoDOExample();
                example.createCriteria().andUserNumEqualTo(userNum);
                List<PropertyInfoDO> list = propertyInfoDOMapper.selectByExample(example);
                if (list != null && !list.isEmpty()) {
                    BalanceAndPointTotalResult result = new BalanceAndPointTotalResult();
                    result.setUserNum(userNum);
                    result.setBalance(list.get(0).getBalance());
                    result.setPoint(list.get(0).getPoint());
                    result.setLove(list.get(0).getLoveAccount());
                    totalResultList.add(result);
                }
            }
        }
        return totalResultList;
    }

    /**
     * 初始化资产记录
     *
     * @param inviters
     */
    private void initPropertyInfo(List<CommissionInvitersUserDTO> inviters) {
        PropertyInfoDO propertyInfoDO;
        for (CommissionInvitersUserDTO dto : inviters) {
            propertyInfoDO = new PropertyInfoDO();
            propertyInfoDO.setId(null);
            propertyInfoDO.setUserNum(dto.getUserNum());
            propertyInfoDOMapper.insertSelective(propertyInfoDO);
        }
    }

    /**
     * 验证买单和购物提成，返回封装数据
     *
     * @param bizEnum
     * @param inviters       邀请关系
     * @param data
     * @param resultDataList
     * @param bizId
     * @param commissionEnum
     */
    private void salesAndVolumeCommission(BizEnum bizEnum, List<CommissionInvitersUserDTO> inviters, BaseData data, List<ResultData> resultDataList, Long bizId, CommissionEnum commissionEnum) {
        CommissionParam param = new CommissionParam();
        param.setId(bizId);
        if (commissionEnum == CommissionEnum.PAY) {
            param.setActualAmount(payMoney);
            commissionService.calculationSalesAndVolume(param, inviters, CommissionEnum.PAY);
        } else if (commissionEnum == CommissionEnum.SHOPPING) {
            param.setActualAmount(shoppingMoney);
            commissionService.calculationSalesAndVolume(param, inviters, CommissionEnum.SHOPPING);
        }

        ResultData result = new ResultData();
        result.setBizEnum(bizEnum);
        checkTransactionDetail(result, inviters, null, bizId, commissionEnum, data.getPsResult());

        resultDataList.add(result);

    }

    /**
     * 验证广告提成，返回封装数据
     *
     * @param bizEnum
     * @param inviters         邀请关系
     * @param data
     * @param resultDataList
     * @param adId
     * @param memberAdRecardId
     */
    private void adCommission(BizEnum bizEnum, List<CommissionInvitersUserDTO> inviters, BaseData data, List<ResultData> resultDataList, Long adId, Long memberAdRecardId) {
        CommissionParam param = new CommissionParam();
        param.setId(memberAdRecardId);
        param.setTempId(adId);
        param.setActualAmount(adClickMoney);
        commissionService.calculationAd(param, inviters, CommissionEnum.AD);

        CorrectCommissionResult correct = new CorrectCommissionResult();
        correct.setCommission1(data.getAdResult().getCommission1());
        correct.setLove1(data.getAdResult().getLove1());
        correct.setCommission2(data.getAdResult().getCommission2());
        correct.setLove2(data.getAdResult().getLove2());
        correct.setCommission3_member(data.getAdResult().getCommission3_member());
        correct.setCommission3_merchant(data.getAdResult().getCommission3_merchant());

        ResultData result = new ResultData();
        result.setBizEnum(bizEnum);
        checkTransactionDetail(result, inviters, correct, memberAdRecardId, CommissionEnum.AD, null);

        resultDataList.add(result);
    }

    /**
     * @param result         返回的结果集
     * @param inviters       邀请关系
     * @param correct        提成的正确结果（广告，买单购物null）
     * @param bizId          业务ID
     * @param commissionEnum
     * @param psResult       不同等级下的提成数据（买单购物，广告null）
     */
    private void checkTransactionDetail(ResultData result, List<CommissionInvitersUserDTO> inviters, CorrectCommissionResult correct, Long bizId, CommissionEnum commissionEnum, Map<Integer, CorrectCommissionResult> psResult) {
        int i = 0;
        for (CommissionInvitersUserDTO dto : inviters) {
            String userNum = inviters.get(i).getUserNum();
            result.setCurrentUserNum(dto.getCurrentUserNum());
            if (dto.getDept() < 3) {
                TransactionDetailDOExample example = new TransactionDetailDOExample();
                example.createCriteria().andUserNumEqualTo(userNum).andBizIdEqualTo(bizId.toString());
                List<TransactionDetailDO> transactionDetailList = transactionDetailDOMapper.selectByExample(example);
                LoveDetailDOExample example1 = new LoveDetailDOExample();
                example1.createCriteria().andUserNumEqualTo(userNum).andBizIdEqualTo(bizId.toString());
                List<LoveDetailDO> loveDetailDOList = loveDetailDOMapper.selectByExample(example1);

                String commission = transactionDetailList == null || transactionDetailList.isEmpty() ? "无" : transactionDetailList.get(0).getAmount().toString();
                String love = loveDetailDOList == null || loveDetailDOList.isEmpty() ? "无" : loveDetailDOList.get(0).getAmount().toString();

                if (dto.getDept() == 1) {
                    result.setCommissionValue1(commission);
                    String actualCommission = "";
                    String actualLove = "";
                    if (commissionEnum == CommissionEnum.AD) {
                        actualCommission = correct.getCommission1().toString();
                        actualLove = correct.getLove1().toString();
                    } else {
                        actualCommission = psResult.get(dto.getLevel()).getCommission1().toString();
                        actualLove = psResult.get(dto.getLevel()).getLove1().toString();
                    }
                    result.setCommissionIsCorrect1(commission.equals(actualCommission));
                    result.setLoveIsCorrect1(love.equals(actualLove));
                    result.setLoveValue1(love);
                    result.setUserNum1(userNum);
                } else if (dto.getDept() == 2) {
                    result.setCommissionValue2(commission);
                    String actualCommission = "";
                    String actualLove = "";
                    if (commissionEnum == CommissionEnum.AD) {
                        actualCommission = correct.getCommission2().toString();
                        actualLove = correct.getLove2().toString();
                    } else {
                        actualCommission = psResult.get(dto.getLevel()).getCommission2().toString();
                        actualLove = psResult.get(dto.getLevel()).getLove2().toString();
                    }
                    result.setCommissionIsCorrect2(commission.equals(actualCommission));
                    result.setLoveIsCorrect2(love.equals(actualLove));
                    result.setLoveValue2(love);
                    result.setUserNum2(userNum);
                }
            } else {
                PointDetailDOExample example = new PointDetailDOExample();
                example.createCriteria().andUserNumEqualTo(userNum).andBizIdEqualTo(bizId.toString());
                List<PointDetailDO> pointDetailDOList = pointDetailDOMapper.selectByExample(example);
                String commission = pointDetailDOList == null || pointDetailDOList.isEmpty() ? "无" : pointDetailDOList.get(0).getPoint().toString();

                result.setCommissionValue3(commission);
                if (userNum.startsWith(UserTypeEnum.MEMBER.getName())) {
                    if (commissionEnum == CommissionEnum.AD) {
                        result.setCommissionIsCorrect3(commission.equals(correct.getCommission3_member().toString()));
                    } else {
                        result.setCommissionIsCorrect3(commission.equals(psResult.get(dto.getLevel()).getCommission3_member().toString()));
                    }
                } else if (userNum.startsWith(UserTypeEnum.MERCHANT.getName())) {
                    if (commissionEnum == CommissionEnum.AD) {
                        result.setCommissionIsCorrect3(commission.equals(correct.getCommission3_merchant().toString()));
                    } else {
                        result.setCommissionIsCorrect3(commission.equals(psResult.get(dto.getLevel()).getCommission3_merchant().toString()));
                    }
                }
                result.setUserNum3(userNum);
            }
            i++;
        }
    }

    /**
     * 初始化资产基础数据
     */
    private void initProperty() {
        PropertyDO pdo = new PropertyDO();
        pdo.setName(PropertyType.MEMBER_THIRD_PAY_POINT);
        pdo.setValue("10");
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.MERCHANT_THIRD_PAY_POINT);
        pdo.setValue("1");
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.ad_commission_0);
        pdo.setValue(PropertyType.ad_commission_0_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.ad_commission_1);
        pdo.setValue(PropertyType.ad_commission_1_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.ad_commission_2);
        pdo.setValue(PropertyType.ad_commission_2_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.ad_commission_3);
        pdo.setValue(PropertyType.ad_commission_3_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.love_account_scale);
        pdo.setValue(PropertyType.love_account_scale_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.sale_commission_0);
        pdo.setValue(PropertyType.sale_commission_0_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.sale_commission_1);
        pdo.setValue(PropertyType.sale_commission_1_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.sale_commission_2);
        pdo.setValue(PropertyType.sale_commission_2_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.sale_commission_3);
        pdo.setValue(PropertyType.sale_commission_3_default);
        propertyDOMapper.insertSelective(pdo);

        pdo.setId(null);
        pdo.setName(PropertyType.sale_commission_add_scope);
        pdo.setValue(PropertyType.sale_commission_add_scope_default);
        propertyDOMapper.insertSelective(pdo);
    }

    /**
     * 获取提成结果验证数据
     *
     * @param cellValues
     * @return
     */
    private BigDecimal[] getAdCommissionAndLove(List<Object> cellValues, int cols) {
        String commissionAndLove1 = cellValues.get(cols) == null ? "0,0" : cellValues.get(cols).toString();
        return getCommissionAndLoveArray(commissionAndLove1);
    }

    public BigDecimal[] getCommissionAndLoveArray(String commissionAndLove) {
        String[] array = commissionAndLove.split(",");
        String commission = array[0];
        String love = array[1];
        return new BigDecimal[]{new BigDecimal(commission), new BigDecimal(love)};
    }

    /**
     * 获取推荐关系封装DTO
     *
     * @param cellValues
     * @return
     */
    private CommissionInvitersUserDTO getCommissionInvitersUserDTO(List<Object> cellValues) {
        CommissionInvitersUserDTO dto = new CommissionInvitersUserDTO();
        dto.setUserNum(cellValues.get(invite_user_num_cols) == null ? "0" : cellValues.get(invite_user_num_cols).toString());
        dto.setCurrentUserNum(cellValues.get(invite_current_user_num_cols) == null ? "0" : cellValues.get(invite_current_user_num_cols).toString());
        dto.setDept(Integer.valueOf(cellValues.get(invite_depth_cols) == null ? "0" : cellValues.get(invite_depth_cols).toString()).intValue());
        dto.setLevel(Integer.valueOf(cellValues.get(invite_level_cols) == null ? "0" : cellValues.get(invite_level_cols).toString()).intValue());
        dto.setFlag(Integer.valueOf(cellValues.get(invite_flag_cols) == null ? "0" : cellValues.get(invite_flag_cols).toString()).intValue());
        return dto;
    }

    /**
     * 读取excel模板数据
     *
     * @param filePath
     * @return
     */
    public BaseData writeExcel(String filePath) {
        BaseData data = new BaseData();
        List<CommissionInvitersUserDTO> inviters1 = new ArrayList<>();
        List<CommissionInvitersUserDTO> inviters2 = new ArrayList<>();
        List<CommissionInvitersUserDTO> inviters3 = new ArrayList<>();
        Map<Integer, CorrectCommissionResult> psResult = new HashMap<>();

        try {
            FileInputStream in = new FileInputStream(filePath);
            List<ExcelImportRowResult> excelImportRowResults = ExcelUtils.importExcel(
                    in, new ExcelImportRowCallback() {
                        @Override
                        public ExcelImportRowResult checkAndSave(int row, List<Object> cellValues) {

                            ExcelImportRowResult result = new ExcelImportRowResult();
                            result.setRowNum(row);
                            result.setCellValues(cellValues);
                            result.setErr(true);
                            result.setBlockedErr(false);
                            return result;
                        }
                    });

            for (ExcelImportRowResult eResult : excelImportRowResults) {
                int rowNum = eResult.getRowNum();
                List<Object> cellValues = eResult.getCellValues();

                //提成结果数据
                if (rowNum > 0 && rowNum < 8) {
                    if (rowNum == ad_row) {
                        CorrectCommissionResult adResult = getCorrectCommissionResult(cellValues);
                        data.setAdResult(adResult);
                    } else if (rowNum > vipCommissionRowBegin && rowNum < vipCommissionRowEnd) {
                        String vipStr = cellValues.get(vipCols) == null ? "VIP1" : cellValues.get(vipCols).toString();
                        CorrectCommissionResult result = getCorrectCommissionResult(cellValues);
                        psResult.put(Integer.valueOf(vipStr.substring(vipStr.length() - 1)), result);
                    }
                } else if (rowNum > inviter1RowBegin && rowNum < inviter1RowEnd) {
                    CommissionInvitersUserDTO dto = getCommissionInvitersUserDTO(cellValues);
                    inviters1.add(dto);
                } else if (rowNum > inviter2RowBegin && rowNum < inviter2RowEnd) {
                    CommissionInvitersUserDTO dto = getCommissionInvitersUserDTO(cellValues);
                    inviters2.add(dto);
                } else if (rowNum > inviter3RowBegin && rowNum < inviter3RowEnd) {
                    CommissionInvitersUserDTO dto = getCommissionInvitersUserDTO(cellValues);
                    inviters3.add(dto);
                }
            }
        } catch (FileNotFoundException e) {
            Assert.fail(e.getMessage());
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        } catch (ExcelImportVerifyException e) {
            Assert.fail(e.getMessage());
        }

        data.setInviters1(inviters1);
        data.setInviters2(inviters2);
        data.setInviters3(inviters3);
        data.setPsResult(psResult);

        return data;
    }

    public CorrectCommissionResult getCorrectCommissionResult(List<Object> cellValues) {
        CorrectCommissionResult result = new CorrectCommissionResult();
        result.setCommission1(getAdCommissionAndLove(cellValues, correctCommissionCols1)[0]);
        result.setLove1(getAdCommissionAndLove(cellValues, correctCommissionCols1)[1]);
        result.setCommission2(getAdCommissionAndLove(cellValues, correctCommissionCols2)[0]);
        result.setLove2(getAdCommissionAndLove(cellValues, correctCommissionCols2)[1]);
        result.setCommission3_member(getAdCommissionAndLove(cellValues, correctCommissionCols3)[0]);
        result.setCommission3_merchant(getAdCommissionAndLove(cellValues, correctCommissionCols3)[1]);
        return result;
    }

    enum BizEnum {
        AD("点广告"),
        PAY("买单"),
        SHOPPING("购物");
        private String name;

        BizEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    class BalanceAndPointTotalResult {
        String userNum;
        BigDecimal balance;
        BigDecimal point;
        BigDecimal love;

        public String getUserNum() {
            return userNum;
        }

        public void setUserNum(String userNum) {
            this.userNum = userNum;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public void setBalance(BigDecimal balance) {
            this.balance = balance;
        }

        public BigDecimal getPoint() {
            return point;
        }

        public void setPoint(BigDecimal point) {
            this.point = point;
        }

        public BigDecimal getLove() {
            return love;
        }

        public void setLove(BigDecimal love) {
            this.love = love;
        }
    }

    class ResultData {
        BizEnum bizEnum;

        private String currentUserNum;
        private String userNum1;
        private String userNum2;
        private String userNum3;

        String commissionValue1;//上1级提成值
        boolean commissionIsCorrect1;//上1级提成是否正确
        String loveValue1;//上1级爱心账户值
        boolean loveIsCorrect1;//上1级爱心账户是否正确

        String commissionValue2;
        boolean commissionIsCorrect2;
        String loveValue2;
        boolean loveIsCorrect2;

        String commissionValue3;
        boolean commissionIsCorrect3;

        public String toString() {
            System.out.println(bizEnum.getName() + "-" + currentUserNum + "-上1级:" + userNum1 + "[提成:" + "," + commissionValue1 + "," + commissionIsCorrect1 + ",爱心账户:" + "," + loveValue1 + "," + loveIsCorrect1 + "]-上2级:" + userNum2 + "提成:" + "," + commissionValue2 + "," + commissionIsCorrect2 + ",爱心账户:" + "," + loveValue2 + "," + loveIsCorrect2 + "]-上3级:" + userNum3 + "提成:" + "," + commissionValue3 + "," + commissionIsCorrect3 + "]");
            return "";
        }

        public BizEnum getBizEnum() {
            return bizEnum;
        }

        public void setBizEnum(BizEnum bizEnum) {
            this.bizEnum = bizEnum;
        }

        public String getCurrentUserNum() {
            return currentUserNum;
        }

        public void setCurrentUserNum(String currentUserNum) {
            this.currentUserNum = currentUserNum;
        }

        public String getUserNum1() {
            return userNum1;
        }

        public void setUserNum1(String userNum1) {
            this.userNum1 = userNum1;
        }

        public String getUserNum2() {
            return userNum2;
        }

        public void setUserNum2(String userNum2) {
            this.userNum2 = userNum2;
        }

        public String getUserNum3() {
            return userNum3;
        }

        public void setUserNum3(String userNum3) {
            this.userNum3 = userNum3;
        }


        public String getCommissionValue1() {
            return commissionValue1;
        }

        public void setCommissionValue1(String commissionValue1) {
            this.commissionValue1 = commissionValue1;
        }

        public boolean isCommissionIsCorrect1() {
            return commissionIsCorrect1;
        }

        public void setCommissionIsCorrect1(boolean commissionIsCorrect1) {
            this.commissionIsCorrect1 = commissionIsCorrect1;
        }

        public String getLoveValue1() {
            return loveValue1;
        }

        public void setLoveValue1(String loveValue1) {
            this.loveValue1 = loveValue1;
        }

        public boolean isLoveIsCorrect1() {
            return loveIsCorrect1;
        }

        public void setLoveIsCorrect1(boolean loveIsCorrect1) {
            this.loveIsCorrect1 = loveIsCorrect1;
        }

        public String getCommissionValue2() {
            return commissionValue2;
        }

        public void setCommissionValue2(String commissionValue2) {
            this.commissionValue2 = commissionValue2;
        }

        public boolean isCommissionIsCorrect2() {
            return commissionIsCorrect2;
        }

        public void setCommissionIsCorrect2(boolean commissionIsCorrect2) {
            this.commissionIsCorrect2 = commissionIsCorrect2;
        }

        public String getLoveValue2() {
            return loveValue2;
        }

        public void setLoveValue2(String loveValue2) {
            this.loveValue2 = loveValue2;
        }

        public boolean isLoveIsCorrect2() {
            return loveIsCorrect2;
        }

        public void setLoveIsCorrect2(boolean loveIsCorrect2) {
            this.loveIsCorrect2 = loveIsCorrect2;
        }

        public String getCommissionValue3() {
            return commissionValue3;
        }

        public void setCommissionValue3(String commissionValue3) {
            this.commissionValue3 = commissionValue3;
        }

        public boolean isCommissionIsCorrect3() {
            return commissionIsCorrect3;
        }

        public void setCommissionIsCorrect3(boolean commissionIsCorrect3) {
            this.commissionIsCorrect3 = commissionIsCorrect3;
        }
    }

    class CorrectCommissionResult {
        BigDecimal commission1 = new BigDecimal(0);//上1级提成
        BigDecimal love1 = new BigDecimal(0);//上1级爱心账户
        BigDecimal commission2 = new BigDecimal(0);//上2级提成
        BigDecimal love2 = new BigDecimal(0);//上2级爱心账户
        BigDecimal commission3_member = new BigDecimal(0);//上3级提成-用户
        BigDecimal commission3_merchant = new BigDecimal(0);//上3级提成-商家

        public String toString() {
            System.out.println("[commission1=" + commission1 + ",love1=" + love1 + "],[commission2=" + commission2 + ",love2=" + love2 + "],commission3_member=" + commission3_member + ",commission3_merchant=" + commission3_merchant);
            return "";
        }

        public BigDecimal getCommission1() {
            return commission1;
        }

        public void setCommission1(BigDecimal commission1) {
            this.commission1 = commission1;
        }

        public BigDecimal getLove1() {
            return love1;
        }

        public void setLove1(BigDecimal love1) {
            this.love1 = love1;
        }

        public BigDecimal getCommission2() {
            return commission2;
        }

        public void setCommission2(BigDecimal commission2) {
            this.commission2 = commission2;
        }

        public BigDecimal getLove2() {
            return love2;
        }

        public void setLove2(BigDecimal love2) {
            this.love2 = love2;
        }

        public BigDecimal getCommission3_member() {
            return commission3_member;
        }

        public void setCommission3_member(BigDecimal commission3_member) {
            this.commission3_member = commission3_member;
        }

        public BigDecimal getCommission3_merchant() {
            return commission3_merchant;
        }

        public void setCommission3_merchant(BigDecimal commission3_merchant) {
            this.commission3_merchant = commission3_merchant;
        }
    }

    class BaseData {

        List<CommissionInvitersUserDTO> inviters1 = new ArrayList<>();
        List<CommissionInvitersUserDTO> inviters2 = new ArrayList<>();
        List<CommissionInvitersUserDTO> inviters3 = new ArrayList<>();

        //广告正确结果数据
        CorrectCommissionResult adResult = new CorrectCommissionResult();

        //买单、购物正确结果数据
        Map<Integer, CorrectCommissionResult> psResult = new HashMap<>();

        public List<CommissionInvitersUserDTO> getInviters1() {
            return inviters1;
        }

        public void setInviters1(List<CommissionInvitersUserDTO> inviters1) {
            this.inviters1 = inviters1;
        }

        public List<CommissionInvitersUserDTO> getInviters2() {
            return inviters2;
        }

        public void setInviters2(List<CommissionInvitersUserDTO> inviters2) {
            this.inviters2 = inviters2;
        }

        public List<CommissionInvitersUserDTO> getInviters3() {
            return inviters3;
        }

        public void setInviters3(List<CommissionInvitersUserDTO> inviters3) {
            this.inviters3 = inviters3;
        }

        public CorrectCommissionResult getAdResult() {
            return adResult;
        }

        public void setAdResult(CorrectCommissionResult adResult) {
            this.adResult = adResult;
        }

        public Map<Integer, CorrectCommissionResult> getPsResult() {
            return psResult;
        }

        public void setPsResult(Map<Integer, CorrectCommissionResult> psResult) {
            this.psResult = psResult;
        }
    }

}
