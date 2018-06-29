package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.common.constants.TransactionPayTypeEnum;
import com.lawu.eshop.property.constants.ConsumptionTypeEnum;
import com.lawu.eshop.property.dto.TransactionDetailDTO;
import com.lawu.eshop.property.srv.bo.TransactionDetailBO;
import com.lawu.eshop.property.srv.domain.TransactionDetailDO;

/**
 * @author yangqh
 * @date 2017/7/19
 */
public class TransactionDetailConverterTest {

    @Test
    public void convert(){
        TransactionDetailDO transactionDetailDO = new TransactionDetailDO();
        transactionDetailDO.setAmount(new BigDecimal("1"));
        transactionDetailDO.setBizId("1");
        transactionDetailDO.setGmtCreate(new Date());
        transactionDetailDO.setId(1L);
        transactionDetailDO.setRemark("");
        transactionDetailDO.setThirdTransactionNum("");
        transactionDetailDO.setTitle("");
        transactionDetailDO.setTransactionAccount("");
        transactionDetailDO.setTransactionNum("");
        transactionDetailDO.setTransactionType(new Byte("1"));
        transactionDetailDO.setUserNum("M10001");
        transactionDetailDO.setDirection(new Byte("1"));
        transactionDetailDO.setTransactionAccountType(new Byte("1"));

        TransactionDetailBO bo = TransactionDetailConverter.convert(transactionDetailDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertBOS(){
        List<TransactionDetailDO> transactionDetailDOS = new ArrayList<>();
        TransactionDetailDO transactionDetailDO = new TransactionDetailDO();
        transactionDetailDO.setAmount(new BigDecimal("1"));
        transactionDetailDO.setBizId("1");
        transactionDetailDO.setGmtCreate(new Date());
        transactionDetailDO.setId(1L);
        transactionDetailDO.setRemark("");
        transactionDetailDO.setThirdTransactionNum("");
        transactionDetailDO.setTitle("");
        transactionDetailDO.setTransactionAccount("");
        transactionDetailDO.setTransactionNum("");
        transactionDetailDO.setTransactionType(new Byte("1"));
        transactionDetailDO.setUserNum("M10001");
        transactionDetailDO.setDirection(new Byte("1"));
        transactionDetailDO.setTransactionAccountType(new Byte("1"));
        transactionDetailDOS.add(transactionDetailDO);
        List<TransactionDetailBO> bos = TransactionDetailConverter.convertBOS(transactionDetailDOS);
        Assert.assertNotNull(bos);
    }

    @Test
    public void convert1(){
        TransactionDetailBO transactionDetailBO = new TransactionDetailBO();
        transactionDetailBO.setAmount(new BigDecimal("1"));
        transactionDetailBO.setBizId("1");
        transactionDetailBO.setGmtCreate(new Date());
        transactionDetailBO.setId(1L);
        transactionDetailBO.setRemark("");
        transactionDetailBO.setThirdTransactionNum("");
        transactionDetailBO.setTitle("");
        transactionDetailBO.setTransactionAccount("");
        transactionDetailBO.setTransactionNum("");
        transactionDetailBO.setTransactionType(new Byte("1"));
        transactionDetailBO.setUserNum("M10001");
        transactionDetailBO.setDirection(ConsumptionTypeEnum.INCOME);
        transactionDetailBO.setTransactionAccountType(TransactionPayTypeEnum.ALIPAY);

        TransactionDetailDTO dto = TransactionDetailConverter.convert(transactionDetailBO);
        Assert.assertNotNull(dto);
    }

    @Test
    public void convertDTOS(){
        List<TransactionDetailBO> transactionDetailBOS = new ArrayList<>();
        TransactionDetailBO transactionDetailBO = new TransactionDetailBO();
        transactionDetailBO.setAmount(new BigDecimal("1"));
        transactionDetailBO.setBizId("1");
        transactionDetailBO.setGmtCreate(new Date());
        transactionDetailBO.setId(1L);
        transactionDetailBO.setRemark("");
        transactionDetailBO.setThirdTransactionNum("");
        transactionDetailBO.setTitle("");
        transactionDetailBO.setTransactionAccount("");
        transactionDetailBO.setTransactionNum("");
        transactionDetailBO.setTransactionType(new Byte("1"));
        transactionDetailBO.setUserNum("M10001");
        transactionDetailBO.setDirection(ConsumptionTypeEnum.INCOME);
        transactionDetailBO.setTransactionAccountType(TransactionPayTypeEnum.ALIPAY);
        transactionDetailBOS.add(transactionDetailBO);
        List<TransactionDetailDTO> dtos = TransactionDetailConverter.convertDTOS(transactionDetailBOS);
        Assert.assertNotNull(dtos);
    }
}
