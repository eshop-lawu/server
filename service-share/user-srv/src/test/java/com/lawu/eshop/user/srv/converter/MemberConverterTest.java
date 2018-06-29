package com.lawu.eshop.user.srv.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lawu.eshop.user.constants.UserSexEnum;
import com.lawu.eshop.user.dto.AccountDTO;
import com.lawu.eshop.user.dto.EfriendDTO;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.eshop.user.dto.MemberInfoForShoppingOrderDTO;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.eshop.user.param.UserParam;
import com.lawu.eshop.user.srv.bo.MemberBO;
import com.lawu.eshop.user.srv.domain.MemberDO;
import com.lawu.eshop.user.srv.domain.MemberProfileDO;
import com.lawu.framework.core.page.Page;
import com.lawu.utils.DataTransUtil;

/**
 * @author meishuquan
 * @date 2017/7/19.
 */
public class MemberConverterTest {

    @Test
    public void convertBO() {
        MemberDO memberDO = new MemberDO();
        memberDO.setId(100L);
        memberDO.setAccount("1366666666");
        memberDO.setPwd("123456");
        memberDO.setBirthday(new Date());
        memberDO.setNum("M0001");
        memberDO.setMobile("1366666666");
        memberDO.setHeadimg("pic");
        memberDO.setInviterId(100L);
        memberDO.setInviterType(DataTransUtil.intToByte(1));
        memberDO.setLevel(1);
        memberDO.setSex(DataTransUtil.intToByte(1));
        memberDO.setRegionPath("44/4403/440303");
        memberDO.setNickname("test");
        memberDO.setGtCid("8888");
        memberDO.setRyToken("8888");
        memberDO.setRegionName("广东省深圳市南山区");
        MemberBO memberBO = MemberConverter.convertBO(memberDO);
        Assert.assertNotNull(memberBO);
        Assert.assertEquals(memberDO.getId(), memberBO.getId());
    }

    @Test
    public void convertDO() {
        MemberBO memberBO = new MemberBO();
        memberBO.setId(100L);
        memberBO.setAccount("13666666666");
        memberBO.setBirthday(new Date());
        memberBO.setNum("M0001");
        memberBO.setPwd("123456");
        memberBO.setMobile("13666666666");
        memberBO.setGmtCreate(new Date());
        memberBO.setGmtModified(new Date());
        memberBO.setHeadimg("pic");
        memberBO.setInviterId(100L);
        memberBO.setInviterType(DataTransUtil.intToByte(1));
        memberBO.setLevel(1);
        memberBO.setStatus(DataTransUtil.intToByte(1));
        memberBO.setSex(DataTransUtil.intToByte(1));
        memberBO.setRegionPath("44/4403/440303");
        memberBO.setNickname("test");
        memberBO.setName("test");
        MemberDO memberDO = MemberConverter.convertDO(memberBO);
        Assert.assertNotNull(memberDO);
        Assert.assertEquals(memberBO.getId(), memberDO.getId());
    }

    @Test
    public void convertDTO() {
        MemberBO memberBO = new MemberBO();
        memberBO.setNum("M0001");
        memberBO.setAccount("13666666666");
        memberBO.setBirthday(new Date());
        memberBO.setUserSex(UserSexEnum.SEX_SECRET);
        memberBO.setRegionPath("44/4403/440303");
        memberBO.setNickname("test");
        memberBO.setHeadimg("pic");
        memberBO.setLevel(1);
        memberBO.setGtCid("8888");
        memberBO.setRyToken("8888");
        memberBO.setRegionName("广东省深圳市南山区");
        UserDTO userDTO = MemberConverter.convertDTO(memberBO);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(memberBO.getNum(), userDTO.getNum());
    }

    @Test
    public void convertDOOther() {
        UserParam userParam = new UserParam();
        userParam.setNickname("test");
        userParam.setRegionPath("44/4403/440303");
        userParam.setUserSexEnum(UserSexEnum.SEX_SECRET);
        userParam.setBirthday(new Date());
        userParam.setRegionName("广东省深圳市南山区");
        MemberDO memberDO = MemberConverter.convertDOOther(userParam);
        Assert.assertNotNull(memberDO);
        Assert.assertEquals(userParam.getNickname(), memberDO.getNickname());
    }

    @Test
    public void convertListBOS() {
        List<MemberProfileDO> mpList = new ArrayList<>();
        MemberProfileDO memberProfileDO = new MemberProfileDO();
        memberProfileDO.setId(100L);
        memberProfileDO.setInviteMemberCount(10);
        memberProfileDO.setInviteMemberCount2(10);
        mpList.add(memberProfileDO);

        List<MemberDO> memberDOS = new ArrayList<>();
        MemberDO memberDO = new MemberDO();
        memberDO.setId(100L);
        memberDO.setAccount("13666666666");
        memberDO.setName("test");
        memberDO.setMobile("13666666666".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        memberDO.setHeadimg("pic");
        memberDO.setSex(DataTransUtil.intToByte(1));
        memberDO.setRegionPath("44/4403/440303");
        memberDO.setNickname("test");
        memberDO.setGmtCreate(new Date());
        memberDO.setLevel(1);
        memberDOS.add(memberDO);
        List<MemberBO> memberBOS = MemberConverter.convertListBOS(memberDOS, mpList);
        Assert.assertNotNull(memberBOS);
        Assert.assertEquals(memberDO.getAccount(), memberBOS.get(0).getAccount());
    }

    @Test
    public void convertMDTO() {
        MemberBO memberBO = new MemberBO();
        memberBO.setId(100L);
        memberBO.setNum("M0001");
        memberBO.setName("test");
        memberBO.setNickname("test");
        memberBO.setBirthday(new Date());
        memberBO.setHeadimg("pic");
        memberBO.setLevel(1);
        memberBO.setMobile("1366666666");
        memberBO.setRegionPath("44/4403/440303");
        MemberDTO memberDTO = MemberConverter.convertMDTO(memberBO);
        Assert.assertNotNull(memberDTO);
        Assert.assertEquals(memberBO.getId(), memberDTO.getId());
    }

    @Test
    public void convertEDTO() {
        MemberBO memberBO = new MemberBO();
        memberBO.setNickname("test");
        memberBO.setHeadimg("pic");
        memberBO.setMobile("13666666666");
        memberBO.setLevel(1);
        memberBO.setGmtCreate(new Date());
        memberBO.setUserSex(UserSexEnum.SEX_SECRET);
        memberBO.setInviterCount(10);
        EfriendDTO memberDTO = MemberConverter.convertEDTO(memberBO);
        Assert.assertNotNull(memberDTO);
        Assert.assertEquals(memberBO.getNickname(), memberDTO.getNickname());
    }

    @Test
    public void convertListDOTS() {
        List<MemberBO> memberBOS = new ArrayList<>();
        MemberBO memberBO = new MemberBO();
        memberBO.setNickname("test");
        memberBO.setHeadimg("pic");
        memberBO.setMobile("13666666666");
        memberBO.setLevel(1);
        memberBO.setGmtCreate(new Date());
        memberBO.setUserSex(UserSexEnum.SEX_SECRET);
        memberBO.setInviterCount(10);
        memberBOS.add(memberBO);
        List<EfriendDTO> memberDTOS = MemberConverter.convertListDOTS(memberBOS);
        Assert.assertNotNull(memberDTOS);
        Assert.assertEquals(memberBO.getNickname(), memberDTOS.get(0).getNickname());
    }

    @Test
    public void convertPageDOTS() {
        Page<MemberBO> pageMemberBOS = new Page<>();
        List<MemberBO> BOS = new ArrayList<>();
        MemberBO memberBO = new MemberBO();
        memberBO.setNickname("test");
        memberBO.setHeadimg("pic");
        memberBO.setMobile("13666666666");
        memberBO.setLevel(1);
        memberBO.setGmtCreate(new Date());
        memberBO.setUserSex(UserSexEnum.SEX_SECRET);
        memberBO.setInviterCount(10);
        BOS.add(memberBO);
        pageMemberBOS.setCurrentPage(1);
        pageMemberBOS.setTotalCount(10);
        pageMemberBOS.setRecords(BOS);
        Page<EfriendDTO> pageDTO = MemberConverter.convertPageDOTS(pageMemberBOS);
        Assert.assertNotNull(pageDTO.getRecords());
        Assert.assertEquals(memberBO.getNickname(), pageDTO.getRecords().get(0).getNickname());
    }

    @Test
    public void convert() {
        MemberBO memberBO = new MemberBO();
        memberBO.setNum("M0001");
        MemberInfoForShoppingOrderDTO shoppingOrderDTO = MemberConverter.convert(memberBO);
        Assert.assertNotNull(shoppingOrderDTO);
        Assert.assertEquals(memberBO.getNum(), shoppingOrderDTO.getNum());
    }

    @Test
    public void convertAccountDOTS(){
        List<MemberBO> records = new ArrayList<>();
        MemberBO memberBO = new MemberBO();
        memberBO.setAccount("123");
        memberBO.setId(1L);
        memberBO.setNum("123");
        memberBO.setGmtCreate(new Date());
        memberBO.setIsFreeze(false);
        records.add(memberBO);
        List<AccountDTO> list = MemberConverter.convertAccountDOTS(records);
        Assert.assertNotNull(list);
        Assert.assertEquals(memberBO.getNum(), list.get(0).getNum());
    }

}
