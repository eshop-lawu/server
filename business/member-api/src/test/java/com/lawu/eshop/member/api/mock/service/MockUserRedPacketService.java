package com.lawu.eshop.member.api.mock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.ad.constants.RedPacketPutWayEnum;
import com.lawu.eshop.ad.dto.IsExistsRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedPacketAddReturnDTO;
import com.lawu.eshop.ad.dto.UserRedPacketDTO;
import com.lawu.eshop.ad.dto.UserRedpacketMaxMoneyDTO;
import com.lawu.eshop.ad.param.UserRedPacketSaveParam;
import com.lawu.eshop.ad.param.UserRedPacketSelectNumParam;
import com.lawu.eshop.member.api.service.UserRedPacketService;
import com.lawu.eshop.order.dto.ThirdPayCallBackQueryPayOrderDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Service
class MockUserRedPacketService extends BaseController implements UserRedPacketService {


    @Override
    public Result<UserRedPacketAddReturnDTO> addUserRedPacket(@RequestBody UserRedPacketSaveParam saveParam) {
        return successCreated();
    }

    @Override
    public Result<Page<UserRedPacketDTO>> selectUserRedPacketList(@RequestBody UserRedPacketSelectNumParam param) {
//        UserRedPacketDTO dto = new UserRedPacketDTO();
//        dto.setId(1L);
//        dto.setMemberId(1L);
//        dto.setRedPacketPutWayEnum(RedPacketPutWayEnum.PUT_WAY_COMMON);
//        dto.setTotalCount(1);
//        dto.setTotalMoney(new BigDecimal("1"));
        List<UserRedPacketDTO> list = new ArrayList<>();
//        list.add(dto);
        Page<UserRedPacketDTO> page = new Page<>();
        page.setCurrentPage(1);
        page.setRecords(list);
        page.setTotalCount(1);
        return successCreated(page);
    }

    @Override
    public Result<IsExistsRedPacketDTO> isExistsRedPacket(@PathVariable("redPacketId") Long redPacketId) {
        return null;
    }

    @Override
    public Result<UserRedpacketMaxMoneyDTO> getUserRedpacketMoney(@RequestParam("redPacketId") Long redPacketId, @RequestParam("userNum") String userNum) {
        return null;
    }

    @Override
    public Result<UserRedpacketMaxMoneyDTO> getUserRedpacketMaxMoney(@RequestParam("redPacketId") Long redPacketId) {
        UserRedpacketMaxMoneyDTO dto = new UserRedpacketMaxMoneyDTO();
        dto.setFlag(true);
        dto.setMoney(new BigDecimal("10"));
        return successCreated(dto);
    }

    @Override
    public Result<ThirdPayCallBackQueryPayOrderDTO> selectUserRedPacketInfoForThrid(@RequestParam("redPacketId") Long redPacketId) {
        return null;
    }
}
