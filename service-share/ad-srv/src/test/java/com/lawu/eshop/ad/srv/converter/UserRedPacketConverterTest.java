package com.lawu.eshop.ad.srv.converter;

import java.math.BigDecimal;
import java.util.Date;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.constants.UserRedPacketEnum;
import com.lawu.eshop.ad.dto.UserRedPacketDTO;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.srv.bo.UserRedPacketBO;
import com.lawu.eshop.ad.srv.domain.UserRedPacketDO;
import com.lawu.utils.DateUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lihj
 * @date 2017/8/8.
 */
public class UserRedPacketConverterTest {


    @Test
    public void converDO() {
        UserRedPacketSaveParam param = new UserRedPacketSaveParam();
        param.setTotalCount(100);
        param.setTotalMoney(new BigDecimal(100));
        param.setRedPacketPutWayEnum(RedPacketPutWayEnum.PUT_WAY_LUCK);
        param.setUserAccount("15000000000");
        param.setUserNum("M00002");
        UserRedPacketDO userRed = UserRedPacketConverter.converDO(param);
        Assert.assertEquals(userRed.getTotalCount(), Integer.valueOf(param.getTotalCount()));
        Assert.assertEquals(userRed.getTotalMoney(), param.getTotalMoney());
        Assert.assertEquals(userRed.getType(), param.getRedPacketPutWayEnum().val);
        Assert.assertEquals(userRed.getUserAccount(), param.getUserAccount());
        Assert.assertEquals(userRed.getUserNum(), param.getUserNum());
    }

    @Test
    public void convertBO() {
        UserRedPacketDO userDO = new UserRedPacketDO();
        userDO.setGmtCreate(new Date());
        userDO.setId(1L);
        userDO.setStatus((byte) 1);
        userDO.setTotalCount(1);
        userDO.setTotalMoney(new BigDecimal(2));
        userDO.setType((byte) 1);
        userDO.setUserAccount("15000000000");
        userDO.setUserNum("M00002");
        UserRedPacketBO user = UserRedPacketConverter.convertBO(userDO);
        Assert.assertEquals(user.getGmtCreate(), userDO.getGmtCreate());
        Assert.assertEquals(user.getId(), userDO.getId());
        Assert.assertEquals(user.getUserRedPacketEnum().val, userDO.getStatus());
        Assert.assertEquals(user.getTotalCount(), userDO.getTotalCount());
        Assert.assertEquals(user.getTotalMoney(), userDO.getTotalMoney());
        Assert.assertEquals(user.getRedPacketPutWayEnum().val, userDO.getType());
        Assert.assertEquals(user.getUserAccount(), userDO.getUserAccount());
        Assert.assertEquals(user.getUserNum(), userDO.getUserNum());
    }

    @Test
    public void coventDTO() {
        UserRedPacketBO bo = new UserRedPacketBO();
        bo.setGmtCreate(new Date());
        bo.setId(1L);
        bo.setRedPacketPutWayEnum(RedPacketPutWayEnum.getEnum((byte) 1));
        bo.setTotalCount(5);
        bo.setTotalMoney(new BigDecimal(1000));
        bo.setUserRedPacketEnum(UserRedPacketEnum.getEnum((byte) 3));
        UserRedPacketDTO dto = UserRedPacketConverter.coventDTO(bo);
        Assert.assertEquals(dto.getGmtCreate(), bo.getGmtCreate());
        Assert.assertEquals(dto.getGmtCreateStr(), DateUtil.getDateFormat(bo.getGmtCreate()));
        Assert.assertEquals(dto.getId(), bo.getId());
        Assert.assertEquals(dto.getUserRedPacketEnum().val, bo.getUserRedPacketEnum().val);
        Assert.assertEquals(dto.getTotalCount(), bo.getTotalCount());
        Assert.assertEquals(dto.getTotalMoney(), bo.getTotalMoney());
        Assert.assertEquals(dto.getRedPacketPutWayEnum().val, bo.getRedPacketPutWayEnum().val);
    }


}
