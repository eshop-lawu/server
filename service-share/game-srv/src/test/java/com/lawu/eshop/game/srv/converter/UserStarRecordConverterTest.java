package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.srv.bo.RankListBO;
import com.lawu.eshop.game.srv.domain.UserStarRecordDO;

/**
 * @author zhangyong
 * @date 2018/3/19.
 */
public class UserStarRecordConverterTest {

    @Test
    public void converterRankBOS(){
        List<UserStarRecordDO> recordDOS = new ArrayList<>();
        UserStarRecordDO recordDO = new UserStarRecordDO();
        recordDO.setUserNum("M123456");
        recordDO.setMonthStarCount(20);
        recordDOS.add(recordDO);
        List<RankListBO> listBOS = UserStarRecordConverter.converterRankBOS(recordDOS);
        Assert.assertEquals(recordDO.getUserNum(),listBOS.get(0).getUserNum());
        Assert.assertEquals(recordDO.getMonthStarCount(),listBOS.get(0).getMonthStarCount());
    }

    @Test
    public void converterRankDTOS(){
        List<RankListBO> records = new ArrayList<>();
        RankListBO listBO = new RankListBO();
        listBO.setUserNum("M123456");
        listBO.setMonthStarCount(20);
        records.add(listBO);
        List<UserStarRankListDTO> listDTOS  = UserStarRecordConverter.converterRankDTOS(records);
        Assert.assertEquals(listBO.getUserNum(),listDTOS.get(0).getUserNum());
        Assert.assertEquals(listBO.getMonthStarCount(),listDTOS.get(0).getStarCount());
    }
}
