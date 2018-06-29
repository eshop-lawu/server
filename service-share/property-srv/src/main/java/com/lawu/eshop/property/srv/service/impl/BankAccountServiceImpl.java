package com.lawu.eshop.property.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lawu.eshop.property.constants.BankAccountTypeEnum;
import com.lawu.eshop.property.param.BandingWxDataParam;
import com.lawu.eshop.property.param.BandingWxEditDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditDataParam;
import com.lawu.eshop.property.param.BankAccountThirdAccountEditParam;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.property.constants.BankStatusEnum;
import com.lawu.eshop.property.param.BankAccountOperatorParam;
import com.lawu.eshop.property.param.BankAccountParam;
import com.lawu.eshop.property.srv.bo.BankAccountBO;
import com.lawu.eshop.property.srv.bo.BankAccountOperatorBO;
import com.lawu.eshop.property.srv.converter.BankAccountConverter;
import com.lawu.eshop.property.srv.domain.BankAccountDO;
import com.lawu.eshop.property.srv.domain.BankAccountDOExample;
import com.lawu.eshop.property.srv.domain.BankAccountDOExample.Criteria;
import com.lawu.eshop.property.srv.domain.BankDO;
import com.lawu.eshop.property.srv.mapper.BankAccountDOMapper;
import com.lawu.eshop.property.srv.mapper.BankDOMapper;
import com.lawu.eshop.property.srv.service.BankAccountService;
import com.lawu.utils.StringUtil;

/**
 * 银行卡管理接口实现
 *
 * @author zhangrc
 * @date 2017/03/29
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountDOMapper bankAccountDOMapper;

    @Autowired
    private BankDOMapper bankDOMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveBankAccount(String userNum, BankAccountParam bankAccountParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setUserNum(userNum);
        bankAccountDO.setAccountName(bankAccountParam.getAccountName());
        bankAccountDO.setAccountNumber(bankAccountParam.getAccountNumber());
        bankAccountDO.setBankId(bankAccountParam.getBankId());
        bankAccountDO.setSubBranchName(bankAccountParam.getSubBranchName());
        bankAccountDO.setStatus(new Byte("1"));
        BankDO bankDO = bankDOMapper.selectByPrimaryKey(bankAccountParam.getBankId());
        String str = bankAccountParam.getAccountNumber().substring(bankAccountParam.getAccountNumber().length() - 4, bankAccountParam.getAccountNumber().length());
        String number = bankDO.getName() + "(" + str + ")";
        bankAccountDO.setNote(number);
        bankAccountDO.setGmtModified(new Date());
        bankAccountDO.setGmtCreate(new Date());
        bankAccountDO.setUserType(bankAccountParam.getUserType().getValue());
        if (bankAccountParam.getRegionPath() != null) {
            bankAccountDO.setRegionPath(bankAccountParam.getRegionPath());
        }
        if (bankAccountParam.getRegionName() != null) {
            bankAccountDO.setRegionName(bankAccountParam.getRegionName());
        }
        return bankAccountDOMapper.insertSelective(bankAccountDO);
    }

    @Override
    public List<BankAccountBO> selectMyBank(String userNum) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(BankStatusEnum.YES.getVal()).andAccountTypeEqualTo(BankAccountTypeEnum.BANK.getVal());
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        List<BankDO> bankDOS = new ArrayList<>();
        for (BankAccountDO bankAccountDO : list) {
            BankDO bankDO = bankDOMapper.selectByPrimaryKey(bankAccountDO.getBankId());
            bankDOS.add(bankDO);
        }
        return list.isEmpty() ? null : BankAccountConverter.convertBOS(list, bankDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer remove(Long id) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andIdEqualTo(id);
        BankAccountDO bankAccount = new BankAccountDO();
        bankAccount.setStatus(BankStatusEnum.NONE.getVal());
        bankAccount.setGmtModified(new Date());
        return bankAccountDOMapper.updateByExampleSelective(bankAccount, example);
    }

    @Override
    public Boolean selectByAccount(String account, Byte userType, String num, Byte bankAccountType) {
        BankAccountDOExample example = new BankAccountDOExample();
        Criteria c1 = example.createCriteria();
        Criteria c2 = example.createCriteria();
        c1.andAccountNumberEqualTo(account).andUserTypeEqualTo(userType).andStatusEqualTo(BankStatusEnum.YES.getVal()).andAccountTypeEqualTo(bankAccountType);
        c2.andAccountNumberEqualTo(account).andUserTypeEqualTo(userType).andIsBindForeverEqualTo(true);
        example.or(c2);
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        for (BankAccountDO bankAccountDO : list) {
            if (bankAccountDO.getStatus() == BankStatusEnum.YES.getVal()) {
                return true;
            } else {
                if (bankAccountDO.getUserNum().equals(num)) {
                    return false;
                } else {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public BankAccountBO selectAccount(Long id) {
        BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(id);
        BankAccountBO bankAccountBO = new BankAccountBO();
        bankAccountBO.setId(bankAccountDO.getId());
        bankAccountBO.setAccountName(bankAccountDO.getAccountName());
        bankAccountBO.setAccountNumber(bankAccountDO.getAccountNumber());
        bankAccountBO.setSubBranchName(bankAccountDO.getSubBranchName());
        bankAccountBO.setBankId(bankAccountDO.getBankId());
        BankDO bankDO = bankDOMapper.selectByPrimaryKey(bankAccountDO.getBankId());
        bankAccountBO.setBankName(bankDO.getName());
        bankAccountBO.setBankId(bankDO.getId());
        bankAccountBO.setRegionPath(bankAccountDO.getRegionPath());
        bankAccountBO.setRegionName(bankAccountDO.getRegionName());
        bankAccountBO.setIconUrl(bankDO.getIconUrl());
        bankAccountBO.setBgUrl(bankDO.getBgUrl());
        return bankAccountBO;
    }

    @Override
    public Integer updateBankAccount(Long id, BankAccountParam bankAccountParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setId(id);
        bankAccountDO.setAccountName(bankAccountParam.getAccountName());
        bankAccountDO.setAccountNumber(bankAccountParam.getAccountNumber());
        bankAccountDO.setBankId(bankAccountParam.getBankId());
        bankAccountDO.setSubBranchName(bankAccountParam.getSubBranchName());
        bankAccountDO.setGmtModified(new Date());
        if (bankAccountParam.getRegionPath() != null) {
            bankAccountDO.setRegionPath(bankAccountParam.getRegionPath());
        }
        if (bankAccountParam.getRegionName() != null) {
            bankAccountDO.setRegionName(bankAccountParam.getRegionName());
        }
        return bankAccountDOMapper.updateByPrimaryKeySelective(bankAccountDO);
    }

    @Override
    public String selectBankName(String num) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andUserNumEqualTo(num);
        example.setOrderByClause("gmt_create asc");
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        if (!list.isEmpty()) {
            return list.get(0).getAccountName();
        }
        return null;
    }

    @Override
    public void updateBankOperator(Long id, BankAccountOperatorParam param) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setId(id);
        bankAccountDO.setAccountName(param.getAccountName());
        bankAccountDO.setAccountNumber(param.getAccountNumber());
        bankAccountDO.setBankId(param.getBankId());
        bankAccountDO.setSubBranchName(param.getSubBranchName());
        bankAccountDO.setGmtModified(new Date());
        bankAccountDO.setAuditorId(param.getAuditorId());
        bankAccountDO.setAuditTime(new Date());
        bankAccountDO.setRemark(param.getRemark());
        bankAccountDOMapper.updateByPrimaryKeySelective(bankAccountDO);

    }

    @Override
    public List<BankAccountOperatorBO> selectBankOperator(String userNum) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(BankStatusEnum.YES.getVal()).andAccountTypeEqualTo(BankAccountTypeEnum.BANK.getVal());
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);

        List<BankAccountOperatorBO> boList = BankAccountConverter.convertOperatorBOS(list);

        for (BankAccountOperatorBO bankAccountOperatorBO : boList) {
            BankDO bankDO = bankDOMapper.selectByPrimaryKey(bankAccountOperatorBO.getBankId());
            bankAccountOperatorBO.setBankName(bankDO.getName());
        }
        return boList;
    }

    @Override
    public Boolean selectByAccountTypeAndUserNum(String userNum, BankAccountTypeEnum bankAccountTypeEnum) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andAccountTypeEqualTo(bankAccountTypeEnum.getVal()).andStatusEqualTo(BankStatusEnum.YES.getVal());
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveThirdAccount(BankAccountThirdAccountDataParam bankAccountThirdAccountDataParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setUserNum(bankAccountThirdAccountDataParam.getUserNum());
        bankAccountDO.setAccountName(bankAccountThirdAccountDataParam.getAccountName());
        bankAccountDO.setAccountNumber(bankAccountThirdAccountDataParam.getAccountNumber());
        bankAccountDO.setAccountType(bankAccountThirdAccountDataParam.getBankAccountTypeEnum().getVal());
        bankAccountDO.setUserType(bankAccountThirdAccountDataParam.getUserTypeEnum().getValue());
        bankAccountDO.setBankId(0);
        bankAccountDO.setSubBranchName("");
        bankAccountDO.setStatus(BankStatusEnum.YES.getVal());
        bankAccountDO.setNote(bankAccountThirdAccountDataParam.getBankAccountTypeEnum().getName()+"("+ StringUtil.hideString(bankAccountThirdAccountDataParam.getAccountNumber())+")");
        bankAccountDO.setGmtCreate(new Date());
        return bankAccountDOMapper.insertSelective(bankAccountDO);
    }

    @Override
    public List<BankAccountBO> selectThirdAccountList(String userNum) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andUserNumEqualTo(userNum).andStatusEqualTo(BankStatusEnum.YES.getVal()).andAccountTypeNotEqualTo(BankAccountTypeEnum.BANK.getVal());
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        return list.isEmpty() ? null : BankAccountConverter.convertThirdBOS(list);
    }

    @Override
    public BankAccountBO selectThirdAccount(Long id) {
        BankAccountDO bankAccountDO = bankAccountDOMapper.selectByPrimaryKey(id);
        BankAccountBO bankAccountBO = new BankAccountBO();
        bankAccountBO.setId(bankAccountDO.getId());
        bankAccountBO.setAccountName(bankAccountDO.getAccountName());
        bankAccountBO.setAccountNumber(bankAccountDO.getAccountNumber());
        bankAccountBO.setAccountType(bankAccountDO.getAccountType());
        return bankAccountBO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateThirdAccount(Long id, BankAccountThirdAccountEditDataParam bankAccountThirdAccountEditDataParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setId(id);
        bankAccountDO.setAccountName(bankAccountThirdAccountEditDataParam.getAccountName());
        bankAccountDO.setAccountNumber(bankAccountThirdAccountEditDataParam.getAccountNumber());
        bankAccountDO.setNote(bankAccountThirdAccountEditDataParam.getBankAccountTypeEnum().getName()+"("+ StringUtil.hideString(bankAccountThirdAccountEditDataParam.getAccountNumber())+")");
        bankAccountDO.setGmtModified(new Date());
        return bankAccountDOMapper.updateByPrimaryKeySelective(bankAccountDO);
    }

    @Override
    public boolean selectThirdAccountDouble(String userNum, Byte accountType) {
        BankAccountDOExample example = new BankAccountDOExample();
        example.createCriteria().andStatusEqualTo(BankStatusEnum.YES.getVal()).andUserNumEqualTo(userNum).andAccountTypeEqualTo(accountType);
        List<BankAccountDO> list = bankAccountDOMapper.selectByExample(example);
        if(list == null || list.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveWxAccount(BandingWxDataParam bandingWxDataParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setUserNum(bandingWxDataParam.getUserNum());
        bankAccountDO.setAccountName(bandingWxDataParam.getAccountName());
        bankAccountDO.setAccountNumber(bandingWxDataParam.getAccountNumber());
        bankAccountDO.setAccountType(BankAccountTypeEnum.WEIXIN.getVal());
        bankAccountDO.setUserType(bandingWxDataParam.getUserTypeEnum().getValue());
        bankAccountDO.setBankId(0);
        bankAccountDO.setSubBranchName("");
        bankAccountDO.setStatus(BankStatusEnum.YES.getVal());
        bankAccountDO.setNote(BankAccountTypeEnum.WEIXIN.getName()+"("+ StringUtil.hidingWxAccountName(bandingWxDataParam.getWxNickname())+")");
        bankAccountDO.setGmtCreate(new Date());
        return bankAccountDOMapper.insertSelective(bankAccountDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateWxAccount(Long id, BandingWxEditDataParam bandingWxEditDataParam) {
        BankAccountDO bankAccountDO = new BankAccountDO();
        bankAccountDO.setId(id);
        bankAccountDO.setAccountName(bandingWxEditDataParam.getAccountName());
        if(StringUtils.isNotBlank(bandingWxEditDataParam.getAccountNumber())){
            bankAccountDO.setAccountNumber(bandingWxEditDataParam.getAccountNumber());
        }
        if(StringUtils.isNotBlank(bandingWxEditDataParam.getWxNickname())){
            bankAccountDO.setNote(BankAccountTypeEnum.WEIXIN.getName()+"("+ StringUtil.hidingWxAccountName(bandingWxEditDataParam.getWxNickname())+")");
        }
        bankAccountDO.setGmtModified(new Date());
        return bankAccountDOMapper.updateByPrimaryKeySelective(bankAccountDO);
    }

}
