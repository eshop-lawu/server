package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.constants.MemberTransactionTypeEnum;
import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.MonthlyBillDTO;
import com.lawu.eshop.property.dto.TransactionDetailBackageDTO;
import com.lawu.eshop.property.dto.TransactionDetailDTO;
import com.lawu.eshop.property.dto.TransactionDetailInfoMemberDTO;
import com.lawu.eshop.property.dto.TransactionDetailInfoMerchantDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMemberDTO;
import com.lawu.eshop.property.dto.TransactionDetailToMerchantDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMemberDTO;
import com.lawu.eshop.property.dto.foreign.TransactionDetailOfMerchantDTO;
import com.lawu.eshop.property.srv.bo.MonthlyBillBO;
import com.lawu.eshop.property.srv.bo.TransactionDetailBO;
import com.lawu.eshop.property.srv.bo.TransactionDetailH5InfoBO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;
import com.lawu.eshop.property.srv.domain.extend.MonthlyBillDO;
import com.lawu.framework.core.page.Page;

/**
 * 交易明细转换器
 *
 * @author Sunny
 * @date 2017/3/29
 */
public class TransactionDetailConverter {

    private TransactionDetailConverter() {
        throw new IllegalAccessError("Utility class");
    }

    public static TransactionDetailBO convert(TransactionDetailDO transactionDetailDO) {
        TransactionDetailBO rtn = null;

        if (transactionDetailDO == null || transactionDetailDO.getId() == null || transactionDetailDO.getId() <= 0) {
            return rtn;
        }
        rtn = new TransactionDetailBO();
        rtn.setAmount(transactionDetailDO.getAmount());
        rtn.setBizId(transactionDetailDO.getBizId());
        rtn.setGmtCreate(transactionDetailDO.getGmtCreate());
        rtn.setId(transactionDetailDO.getId());
        rtn.setRemark(transactionDetailDO.getRemark());
        rtn.setThirdTransactionNum(transactionDetailDO.getThirdTransactionNum());
        rtn.setTitle(transactionDetailDO.getTitle());
        rtn.setTransactionAccount(transactionDetailDO.getTransactionAccount());
        rtn.setTransactionNum(transactionDetailDO.getTransactionNum());
        rtn.setTransactionType(transactionDetailDO.getTransactionType());
        rtn.setUserNum(transactionDetailDO.getUserNum());
        rtn.setDirection(ConsumptionTypeEnum.getEnum(transactionDetailDO.getDirection()));
        rtn.setTransactionAccountType(TransactionPayTypeEnum.getEnum(transactionDetailDO.getTransactionAccountType()));
        return rtn;
    }

    public static List<TransactionDetailBO> convertBOS(List<TransactionDetailDO> transactionDetailDOS) {
        List<TransactionDetailBO> rtn = null;
        if (transactionDetailDOS == null || transactionDetailDOS.isEmpty()) {
            return rtn;
        }
        rtn = new ArrayList<>();
        for (TransactionDetailDO item : transactionDetailDOS) {
            rtn.add(convert(item));
        }
        return rtn;
    }

    public static TransactionDetailDTO convert(TransactionDetailBO transactionDetailBO) {
        TransactionDetailDTO rtn = null;
        if (transactionDetailBO == null) {
            return rtn;
        }
        rtn = new TransactionDetailDTO();
        rtn.setAmount(transactionDetailBO.getAmount());
        rtn.setBizId(transactionDetailBO.getBizId());
        rtn.setRemark(transactionDetailBO.getRemark());
        rtn.setTitle(transactionDetailBO.getTitle());
        rtn.setDirection(transactionDetailBO.getDirection());
        rtn.setTransactionDate(transactionDetailBO.getGmtCreate());
        return rtn;
    }

    public static List<TransactionDetailDTO> convertDTOS(List<TransactionDetailBO> transactionDetailBOS) {
        List<TransactionDetailDTO> rtn = new ArrayList<>();
        if (transactionDetailBOS == null || transactionDetailBOS.isEmpty()) {
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOS) {
            rtn.add(convert(transactionDetailBO));
        }
        return rtn;
    }

    public static Page<TransactionDetailDTO> convertDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        rtn.setRecords(convertDTOS(transactionDetailBOPage.getRecords()));
        return rtn;
    }

    public static TransactionDetailToMemberDTO convertTransactionDetailToMemberDTO(TransactionDetailBO transactionDetailBO) {
        TransactionDetailToMemberDTO rtn = null;
        if (transactionDetailBO == null) {
            return rtn;
        }
        rtn = new TransactionDetailToMemberDTO();
        rtn.setAmount(transactionDetailBO.getAmount());
        rtn.setBizId(transactionDetailBO.getBizId());
        rtn.setRemark(transactionDetailBO.getRemark());
        rtn.setTitle(transactionDetailBO.getTitle());
        rtn.setDirection(transactionDetailBO.getDirection());
        rtn.setTransactionType(MemberTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()));
        rtn.setTransactionDate(transactionDetailBO.getGmtCreate());
        return rtn;
    }

    public static List<TransactionDetailToMemberDTO> convertTransactionDetailToMemberDTOS(List<TransactionDetailBO> transactionDetailBOS) {
        List<TransactionDetailToMemberDTO> rtn = new ArrayList<>();
        if (transactionDetailBOS == null || transactionDetailBOS.isEmpty()) {
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOS) {
            rtn.add(convertTransactionDetailToMemberDTO(transactionDetailBO));
        }
        return rtn;
    }

    public static Page<TransactionDetailToMemberDTO> convertTransactionDetailToMemberDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailToMemberDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        rtn.setRecords(convertTransactionDetailToMemberDTOS(transactionDetailBOPage.getRecords()));
        return rtn;
    }

    public static TransactionDetailToMerchantDTO convertTransactionDetailToMerchantDTO(TransactionDetailBO transactionDetailBO) {
        TransactionDetailToMerchantDTO rtn = null;
        if (transactionDetailBO == null) {
            return rtn;
        }
        rtn = new TransactionDetailToMerchantDTO();
        rtn.setAmount(transactionDetailBO.getAmount());
        rtn.setBizId(transactionDetailBO.getBizId());
        rtn.setRemark(transactionDetailBO.getRemark());
        rtn.setTitle(transactionDetailBO.getTitle());
        rtn.setDirection(transactionDetailBO.getDirection());
        rtn.setTransactionType(MerchantTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()));
        rtn.setTransactionDate(transactionDetailBO.getGmtCreate());
        return rtn;
    }

    public static List<TransactionDetailToMerchantDTO> convertTransactionDetailToMerchantDTOS(List<TransactionDetailBO> transactionDetailBOS) {
        List<TransactionDetailToMerchantDTO> rtn = new ArrayList<>();
        if (transactionDetailBOS == null || transactionDetailBOS.isEmpty()) {
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOS) {
            rtn.add(convertTransactionDetailToMerchantDTO(transactionDetailBO));
        }
        return rtn;
    }

    public static Page<TransactionDetailToMerchantDTO> convertTransactionDetailToMerchantDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailToMerchantDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        rtn.setRecords(convertTransactionDetailToMerchantDTOS(transactionDetailBOPage.getRecords()));
        return rtn;
    }

    public static Page<TransactionDetailBackageDTO> convertTransactionDetailBackageDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailBackageDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        List<TransactionDetailBackageDTO> transactionDetailBackageDTOS = new ArrayList<>();
        if (transactionDetailBOPage.getRecords() == null || transactionDetailBOPage.getRecords().isEmpty()) {
            rtn.setRecords(transactionDetailBackageDTOS);
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOPage.getRecords()) {
            TransactionDetailBackageDTO transactionDetailBackageDTO = new TransactionDetailBackageDTO();
            transactionDetailBackageDTO.setTitle(transactionDetailBO.getTitle());
            transactionDetailBackageDTO.setPayType(transactionDetailBO.getTransactionAccountType().getName());
            transactionDetailBackageDTO.setUserNum(transactionDetailBO.getUserNum());
            transactionDetailBackageDTO.setAmount(transactionDetailBO.getAmount());
            transactionDetailBackageDTO.setTransactionDate(transactionDetailBO.getGmtCreate());
            transactionDetailBackageDTOS.add(transactionDetailBackageDTO);
        }
        rtn.setRecords(transactionDetailBackageDTOS);
        return rtn;
    }

    public static MonthlyBillBO convertMonthlyBillBO(List<MonthlyBillDO> monthlyBillDOList) {
        MonthlyBillBO rtn = new MonthlyBillBO();
        rtn.setTotalExpenditure(new BigDecimal(0));
        rtn.setTotalIncome(new BigDecimal(0));
        if (monthlyBillDOList == null || monthlyBillDOList.isEmpty()) {
            return rtn;
        }
        for (MonthlyBillDO item : monthlyBillDOList) {
            switch (ConsumptionTypeEnum.getEnum(item.getDirection())) {
                case EXPENDITURE:
                    rtn.setTotalExpenditure(item.getAmount());
                    break;
                case INCOME:
                    rtn.setTotalIncome(item.getAmount());
                    break;
                default:
                    break;
            }
        }
        return rtn;
    }

    public static MonthlyBillDTO convert(MonthlyBillBO monthlyBillBO) {
        MonthlyBillDTO rtn = new MonthlyBillDTO();
        rtn.setTotalExpenditure(monthlyBillBO.getTotalExpenditure());
        rtn.setTotalIncome(monthlyBillBO.getTotalIncome());
        return rtn;
    }

    public static TransactionDetailOfMemberDTO convertTransactionDetailOfMemberDTO(TransactionDetailBO transactionDetailBO) {
        TransactionDetailOfMemberDTO rtn = null;
        if (transactionDetailBO == null) {
            return rtn;
        }
        rtn = new TransactionDetailOfMemberDTO();
        rtn.setAmount(transactionDetailBO.getAmount());
        rtn.setDirection(transactionDetailBO.getDirection());
        rtn.setId(transactionDetailBO.getId());
        if (MemberTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()) != null) {
            rtn.setTitle(transactionDetailBO.getTitle());
            rtn.setTransactionCategory(MemberTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()).getPriorityCategory());
        }
        rtn.setTransactionDate(transactionDetailBO.getGmtCreate());
        return rtn;
    }

    public static List<TransactionDetailOfMemberDTO> convertTransactionDetailOfMemberDTOS(List<TransactionDetailBO> transactionDetailBOS) {
        List<TransactionDetailOfMemberDTO> rtn = new ArrayList<>();
        if (transactionDetailBOS == null || transactionDetailBOS.isEmpty()) {
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOS) {
            rtn.add(convertTransactionDetailOfMemberDTO(transactionDetailBO));
        }
        return rtn;
    }

    public static Page<TransactionDetailOfMemberDTO> convertTransactionDetailOfMemberDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailOfMemberDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        rtn.setRecords(convertTransactionDetailOfMemberDTOS(transactionDetailBOPage.getRecords()));
        return rtn;
    }

    public static TransactionDetailOfMerchantDTO convertTransactionDetailOfMerchantDTO(TransactionDetailBO transactionDetailBO) {
        TransactionDetailOfMerchantDTO rtn = null;
        if (transactionDetailBO == null) {
            return rtn;
        }
        rtn = new TransactionDetailOfMerchantDTO();
        rtn.setAmount(transactionDetailBO.getAmount());
        rtn.setTitle(transactionDetailBO.getTitle());
        rtn.setDirection(transactionDetailBO.getDirection());
        rtn.setTransactionCategory(MerchantTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()) == null ? null : MerchantTransactionTypeEnum.getEnum(transactionDetailBO.getTransactionType()).getPriorityCategory());
        rtn.setTransactionDate(transactionDetailBO.getGmtCreate());
        rtn.setId(transactionDetailBO.getId());
        return rtn;
    }

    public static List<TransactionDetailOfMerchantDTO> convertTransactionDetailOfMerchantDTOS(List<TransactionDetailBO> transactionDetailBOS) {
        List<TransactionDetailOfMerchantDTO> rtn = new ArrayList<>();
        if (transactionDetailBOS == null || transactionDetailBOS.isEmpty()) {
            return rtn;
        }
        for (TransactionDetailBO transactionDetailBO : transactionDetailBOS) {
            rtn.add(convertTransactionDetailOfMerchantDTO(transactionDetailBO));
        }
        return rtn;
    }

    public static Page<TransactionDetailOfMerchantDTO> convertTransactionDetailOfMerchantDTOPage(Page<TransactionDetailBO> transactionDetailBOPage) {
        Page<TransactionDetailOfMerchantDTO> rtn = new Page<>();
        rtn.setCurrentPage(transactionDetailBOPage.getCurrentPage());
        rtn.setTotalCount(transactionDetailBOPage.getTotalCount());
        rtn.setRecords(convertTransactionDetailOfMerchantDTOS(transactionDetailBOPage.getRecords()));
        return rtn;
    }


    public static TransactionDetailInfoMemberDTO convertToTransactionDetailH5InfoMemberDTO(TransactionDetailH5InfoBO transactionDetailH5InfoBO) {
        TransactionDetailInfoMemberDTO rtn = null;
        if(transactionDetailH5InfoBO == null){
            return null;
        }
        rtn = new TransactionDetailInfoMemberDTO();
        rtn.setTransactionCategory(MemberTransactionTypeEnum.getEnum(transactionDetailH5InfoBO.getTransactionType()).getPriorityCategory());
        rtn.setTitle(transactionDetailH5InfoBO.getTitle());
        rtn.setDirection(transactionDetailH5InfoBO.getDirection());
        rtn.setAmount(transactionDetailH5InfoBO.getAmount());
        rtn.setTransactionDesc(transactionDetailH5InfoBO.getTransactionDesc());
        rtn.setOtherDesc(transactionDetailH5InfoBO.getOtherDesc());
        rtn.setGmtDate(transactionDetailH5InfoBO.getGmtCreate());
        rtn.setOrderNum(transactionDetailH5InfoBO.getTransactionNum());
        rtn.setMemberTransactionTypeEnum(MemberTransactionTypeEnum.getEnum(transactionDetailH5InfoBO.getTransactionType()));
        rtn.setBizId(transactionDetailH5InfoBO.getBizId());
        return rtn;
    }

    public static TransactionDetailInfoMerchantDTO convertToTransactionDetailH5InfoMerchantDTO(TransactionDetailH5InfoBO transactionDetailH5InfoBO) {
        TransactionDetailInfoMerchantDTO rtn = null;
        if(transactionDetailH5InfoBO == null){
            return null;
        }
        rtn = new TransactionDetailInfoMerchantDTO();
        rtn.setTransactionCategory(MerchantTransactionTypeEnum.getEnum(transactionDetailH5InfoBO.getTransactionType()).getPriorityCategory());
        rtn.setTitle(transactionDetailH5InfoBO.getTitle());
        rtn.setDirection(transactionDetailH5InfoBO.getDirection());
        rtn.setAmount(transactionDetailH5InfoBO.getAmount());
        rtn.setTransactionDesc(transactionDetailH5InfoBO.getTransactionDesc());
        rtn.setOtherDesc(transactionDetailH5InfoBO.getOtherDesc());
        rtn.setGmtDate(transactionDetailH5InfoBO.getGmtCreate());
        rtn.setOrderNum(transactionDetailH5InfoBO.getTransactionNum());
        rtn.setMerchantTransactionTypeEnum(MerchantTransactionTypeEnum.getEnum(transactionDetailH5InfoBO.getTransactionType()));
        return rtn;
    }
}
