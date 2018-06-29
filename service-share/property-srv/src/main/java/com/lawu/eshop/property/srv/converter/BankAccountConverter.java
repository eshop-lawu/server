package com.lawu.eshop.property.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.dto.BankAccountDTO;
import com.lawu.eshop.property.dto.BankAccountOperatorDTO;
import com.lawu.eshop.property.dto.BankThirdAccountDTO;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.bo.BankAccountOperatorBO;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.utils.StringUtil;

/**
 * 银行卡信息转换
 *
 * @author zhangrc
 * @date 2017/03/29
 */
public class BankAccountConverter {
    /**
     * DO转BO
     *
     * @param bankAccountDO
     * @return
     */
    public static BankAccountBO convertBO(BankAccountDO bankAccountDO, String bankName, String url, String bgurl) {
        if (bankAccountDO == null) {
            return null;
        }
        BankAccountBO bankAccountBO = new BankAccountBO();
        bankAccountBO.setId(bankAccountDO.getId());
        bankAccountBO.setBankName(bankName);
        bankAccountBO.setIconUrl(url);
        bankAccountBO.setBgUrl(bgurl);
        bankAccountBO.setAccountName(bankAccountDO.getAccountName());
        bankAccountBO.setAccountNumber(bankAccountDO.getAccountNumber());
        bankAccountBO.setSubBranchName(bankAccountDO.getSubBranchName());
        bankAccountBO.setRegionPath(bankAccountDO.getRegionPath());
        bankAccountBO.setRegionName(bankAccountDO.getRegionName());
        return bankAccountBO;
    }

    /**
     * DOS 转BOS
     *
     * @param bankDOS
     * @return
     */
    public static List<BankAccountBO> convertBOS(List<BankAccountDO> bankAccountDOS, List<BankDO> bankDOS) {
        if (bankAccountDOS == null) {
            return null;
        }
        List<BankAccountBO> BOS = new ArrayList<BankAccountBO>();
        for (BankAccountDO bankAccountDO : bankAccountDOS) {
            bankAccountDO.setAccountNumber(bankAccountDO.getAccountNumber());
            String bankName = null;
            String url = null;
            String bgurl = null;
            for (BankDO bankBO : bankDOS) {
                if (bankAccountDO.getBankId().equals(bankBO.getId())) {
                    bankName = bankBO.getName();
                    url = bankBO.getIconUrl();
                    bgurl = bankBO.getBgUrl();
                }

            }
            BOS.add(convertBO(bankAccountDO, bankName, url, bgurl));
        }

        return BOS;
    }

    /**
     * BO转DTO
     *
     * @param bankAccountBO
     * @return
     */
    public static BankAccountDTO convertDTO(BankAccountBO bankAccountBO) {
        if (bankAccountBO == null) {
            return null;
        }
        BankAccountDTO bankAccountDTO = new BankAccountDTO();
        bankAccountDTO.setId(bankAccountBO.getId());
        bankAccountDTO.setAccountName(bankAccountBO.getAccountName());
        String accountNumber = bankAccountBO.getAccountNumber();
        String newAccountNumber = accountNumber.substring(accountNumber.length() - 4, accountNumber.length());
        bankAccountDTO.setAccountNumber(accountNumber);
        bankAccountDTO.setAccountNumberSuffix(newAccountNumber);
        bankAccountDTO.setBankName(bankAccountBO.getBankName());
        bankAccountDTO.setSubBranchName(bankAccountBO.getSubBranchName());
        String iconUrl = bankAccountBO.getIconUrl();
        bankAccountDTO.setIconUrl(iconUrl);
        bankAccountDTO.setIconUrlSmall(iconUrl.substring(0, iconUrl.length() - 4) + "_small.png");
        bankAccountDTO.setBgUrl(bankAccountBO.getBgUrl());
        bankAccountDTO.setBankId(bankAccountBO.getBankId());
        bankAccountDTO.setRegionPath(bankAccountBO.getRegionPath());
        bankAccountDTO.setRegionName(bankAccountBO.getRegionName());
        return bankAccountDTO;
    }

    /**
     * BOS 转DTOS
     *
     * @param bankAccountBOS
     * @return
     */
    public static List<BankAccountDTO> convertDTOS(List<BankAccountBO> bankAccountBOS) {
        List<BankAccountDTO> DTOS = new ArrayList<BankAccountDTO>();
        if (bankAccountBOS == null) {
            return DTOS;
        }
        for (BankAccountBO bankAccountBO : bankAccountBOS) {
            BankAccountDTO bankAccountDTO = new BankAccountDTO();
            bankAccountDTO.setId(bankAccountBO.getId());
            bankAccountDTO.setAccountName(bankAccountBO.getAccountName());
            String accountNumber = bankAccountBO.getAccountNumber();
            String newAccountNumber = accountNumber.substring(accountNumber.length() - 4, accountNumber.length());
            bankAccountDTO.setAccountNumber(newAccountNumber);
            bankAccountDTO.setBankName(bankAccountBO.getBankName());
            bankAccountDTO.setSubBranchName(bankAccountBO.getSubBranchName());
            bankAccountDTO.setRegionPath(bankAccountBO.getRegionPath());
            bankAccountDTO.setRegionName(bankAccountBO.getRegionName());
            String iconUrl = bankAccountBO.getIconUrl();
            bankAccountDTO.setIconUrl(iconUrl);
            bankAccountDTO.setIconUrlSmall(iconUrl.substring(0, iconUrl.length() - 4) + "_small.png");
            bankAccountDTO.setBgUrl(bankAccountBO.getBgUrl());
            bankAccountDTO.setBankId(bankAccountBO.getBankId());
            DTOS.add(bankAccountDTO);
        }
        return DTOS;
    }

    public static List<BankAccountOperatorBO> convertOperatorBOS(List<BankAccountDO> list) {
        if (list == null) {
            return null;
        }
        List<BankAccountOperatorBO> BOS = new ArrayList<BankAccountOperatorBO>();

        for (BankAccountDO bankAccountDO : list) {

            BankAccountOperatorBO bankAccountBO = new BankAccountOperatorBO();
            bankAccountBO.setId(bankAccountDO.getId());
            bankAccountBO.setAccountName(bankAccountDO.getAccountName());
            bankAccountBO.setAccountNumber(bankAccountDO.getAccountNumber());
            bankAccountBO.setSubBranchName(bankAccountDO.getSubBranchName());
            bankAccountBO.setAuditorId(bankAccountDO.getAuditorId());
            bankAccountBO.setRemark(bankAccountDO.getRemark());
            bankAccountBO.setAuditorTime(bankAccountDO.getAuditTime());
            bankAccountBO.setBankId(bankAccountDO.getBankId());
            BOS.add(bankAccountBO);
        }
        return BOS;
    }


    public static List<BankAccountOperatorDTO> convertOperatorDTOS(List<BankAccountOperatorBO> list) {
        if (list == null) {
            return null;
        }
        List<BankAccountOperatorDTO> dtos = new ArrayList<BankAccountOperatorDTO>();

        for (BankAccountOperatorBO bankAccountOperatorBO : list) {

            BankAccountOperatorDTO bankAccountDTO = new BankAccountOperatorDTO();
            bankAccountDTO.setId(bankAccountOperatorBO.getId());
            bankAccountDTO.setAccountName(bankAccountOperatorBO.getAccountName());
            bankAccountDTO.setAccountNumber(bankAccountOperatorBO.getAccountNumber());
            bankAccountDTO.setSubBranchName(bankAccountOperatorBO.getSubBranchName());
            bankAccountDTO.setAuditorId(bankAccountOperatorBO.getAuditorId());
            bankAccountDTO.setRemark(bankAccountOperatorBO.getRemark());
            bankAccountDTO.setAuditorTime(bankAccountOperatorBO.getAuditorTime());
            bankAccountDTO.setBankName(bankAccountOperatorBO.getBankName());
            dtos.add(bankAccountDTO);

        }
        return dtos;
    }

    public static List<BankAccountBO> convertThirdBOS(List<BankAccountDO> bankAccountDOS) {
        if (bankAccountDOS == null) {
            return null;
        }
        List<BankAccountBO> BOS = new ArrayList<BankAccountBO>();
        for (BankAccountDO bankAccountDO : bankAccountDOS) {
            BankAccountBO rtn = new BankAccountBO();
            rtn.setId(bankAccountDO.getId());
            rtn.setAccountNumber(bankAccountDO.getAccountNumber());
            rtn.setAccountName(bankAccountDO.getAccountName());
            rtn.setAccountType(bankAccountDO.getAccountType());
            rtn.setNote(bankAccountDO.getNote());
            BOS.add(rtn);
        }
        return BOS;
    }

    public static List<BankThirdAccountDTO> convertThirdDTO(List<BankAccountBO> thirdAccountBos) {
        if (thirdAccountBos == null) {
            thirdAccountBos = new ArrayList<>();
            BankAccountBO bo1 = new BankAccountBO();
            bo1.setAccountType(BankAccountTypeEnum.ALIPAY.getVal());
            thirdAccountBos.add(bo1);
            BankAccountBO bo2 = new BankAccountBO();
            bo2.setAccountType(BankAccountTypeEnum.WEIXIN.getVal());
            thirdAccountBos.add(bo2);
        } else if(thirdAccountBos.size() == 1){
            if(BankAccountTypeEnum.ALIPAY.getVal().equals(thirdAccountBos.get(0).getAccountType())){
                BankAccountBO bo = new BankAccountBO();
                bo.setAccountType(BankAccountTypeEnum.WEIXIN.getVal());
                thirdAccountBos.add(bo);
            }else if(BankAccountTypeEnum.WEIXIN.getVal().equals(thirdAccountBos.get(0).getAccountType())){
                BankAccountBO bo = new BankAccountBO();
                bo.setAccountType(BankAccountTypeEnum.ALIPAY.getVal());
                thirdAccountBos.add(bo);
            }
        }
        List<BankThirdAccountDTO> rtn = new ArrayList<>();
        for (BankAccountBO bankAccountBO : thirdAccountBos) {
            BankThirdAccountDTO dto = new BankThirdAccountDTO();
            dto.setId(bankAccountBO.getId());
            dto.setAccountName(bankAccountBO.getAccountName());
            String accountNumber = bankAccountBO.getAccountNumber();
            dto.setAccountNumberBefore(accountNumber);
            dto.setBankAccountTypeEnum(BankAccountTypeEnum.getEnum(bankAccountBO.getAccountType()));
            if (BankAccountTypeEnum.ALIPAY.getVal().equals(bankAccountBO.getAccountType())) {
                dto.setAccountNumber(StringUtil.hideString(accountNumber));
                dto.setIconUrl("bank/gerenzhongxin_alipay_1.png");
                dto.setIconUrlSmall("bank/gerenzhongxin_alipay_2.png");
                dto.setIconUrlSmallDefault("bank/gerenzhongxin_alipay_3.png");
                dto.setBgUrl("bank/gerenzhongxin_alipay_bg.png");
            } else if (BankAccountTypeEnum.WEIXIN.getVal().equals(bankAccountBO.getAccountType())) {
                dto.setAccountNumber(StringUtil.getCenterStr(bankAccountBO.getNote()));
                dto.setIconUrl("bank/gerenzhongxin_weixin_1.png");
                dto.setIconUrlSmall("bank/gerenzhongxin_weixin_2.png");
                dto.setIconUrlSmallDefault("bank/gerenzhongxin_weixin_3.png");
                dto.setBgUrl("bank/gerenzhongxin_weixin_bg.png");
            }
            rtn.add(dto);
        }

        List<BankThirdAccountDTO> newRtn = new ArrayList<>();
        for(int i = 0 ; i < 2 ; i++){
            if(i == 0){
                for (BankThirdAccountDTO dto : rtn) {
                    if (BankAccountTypeEnum.ALIPAY.getVal().equals(dto.getBankAccountTypeEnum().getVal())) {
                        newRtn.add(dto);
                        break;
                    }
                }
            } else if(i == 1){
                for (BankThirdAccountDTO dto : rtn) {
                    if (BankAccountTypeEnum.WEIXIN.getVal().equals(dto.getBankAccountTypeEnum().getVal())) {
                        newRtn.add(dto);
                        break;
                    }
                }
            }
        }
        return newRtn;
    }

    public static BankThirdAccountDTO convertThirdAccountDTO(BankAccountBO bo) {
        if (bo == null) {
            return null;
        }
        BankThirdAccountDTO dto = new BankThirdAccountDTO();
        dto.setId(bo.getId());
        dto.setAccountName(bo.getAccountName());
        dto.setAccountNumber(bo.getAccountNumber());
        dto.setBankAccountTypeEnum(BankAccountTypeEnum.getEnum(bo.getAccountType()));
        return dto;
    }
}
