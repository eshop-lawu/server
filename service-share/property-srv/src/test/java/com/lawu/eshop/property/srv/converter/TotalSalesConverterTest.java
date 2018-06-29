package com.lawu.eshop.property.srv.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.property.constants.MerchantTransactionTypeEnum;
import com.lawu.eshop.property.dto.TotalSalesDTO;
import com.lawu.eshop.property.srv.bo.TotalSalesBO;
import com.lawu.eshop.property.srv.domain.extend.TotalSalesDO;

/**
 * @author yangqh
 * @date 2017/7/19
 */
public class TotalSalesConverterTest {

    @Test
    public void convert1(){
        TotalSalesDO totalSalesDO = new TotalSalesDO();
        totalSalesDO.setAmount(new BigDecimal("1"));
        totalSalesDO.setTransactionType(new Byte("1"));
        TotalSalesBO bo = TotalSalesConverter.convert(totalSalesDO);
        Assert.assertNotNull(bo);
    }

    @Test
    public void convertTotalSalesBOList(){
        List<TotalSalesDO> totalSalesDOList = new ArrayList<>();
        TotalSalesDO totalSalesDO = new TotalSalesDO();
        totalSalesDO.setAmount(new BigDecimal("1"));
        totalSalesDO.setTransactionType(new Byte("1"));
        totalSalesDOList.add(totalSalesDO);
        List<TotalSalesBO> bos = TotalSalesConverter.convertTotalSalesBOList(totalSalesDOList);
        Assert.assertNotNull(bos);
    }

    @Test
    public void convertTotalSalesDTO(){
        List<TotalSalesBO> totalSalesBOList = new ArrayList<>();
        TotalSalesBO totalSalesBO = new TotalSalesBO();
        totalSalesBO.setAmount(new BigDecimal("1"));
        totalSalesBO.setTransactionType(MerchantTransactionTypeEnum.AD_RETURN_POINT);
        totalSalesBOList.add(totalSalesBO);
        TotalSalesDTO dto = TotalSalesConverter.convertTotalSalesDTO(totalSalesBOList);
        Assert.assertNotNull(dto);
    }
}
