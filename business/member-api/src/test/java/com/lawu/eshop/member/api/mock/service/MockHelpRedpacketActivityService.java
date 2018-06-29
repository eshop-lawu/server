package com.lawu.eshop.member.api.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lawu.eshop.activity.constants.HelpRedpacketActivityStatusEnum;
import com.lawu.eshop.activity.constants.RedPacketTypeEnum;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.eshop.member.api.service.HelpRedpacketActivityService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@Service
public class MockHelpRedpacketActivityService extends BaseController implements HelpRedpacketActivityService {

    @Override
    public Result<HelpRedpacketActivityOpenDTO> isOpen() {
        HelpRedpacketActivityOpenDTO rtn = new HelpRedpacketActivityOpenDTO();
        rtn.setEndPic("endPic.jpg");
        rtn.setEndUrl("endUrl");
        rtn.setOpen(true);
        rtn.setStartPic("startPic.jpg");
        rtn.setStatus(HelpRedpacketActivityStatusEnum.REGISTING);
        return successGet(rtn);
    }

    @Override
    public Result<List<HelpRedpacketActivityOpenDTO>> openActivityList() {
        List<HelpRedpacketActivityOpenDTO> rtn = new ArrayList<>();
        HelpRedpacketActivityOpenDTO helpRedpacketActivityOpenDTO = new HelpRedpacketActivityOpenDTO();
        helpRedpacketActivityOpenDTO.setEndPic("endPic.jpg");
        helpRedpacketActivityOpenDTO.setEndUrl("endUrl");
        helpRedpacketActivityOpenDTO.setOpen(true);
        helpRedpacketActivityOpenDTO.setStartPic("startPic.jpg");
        helpRedpacketActivityOpenDTO.setStatus(HelpRedpacketActivityStatusEnum.REGISTING);
        rtn.add(helpRedpacketActivityOpenDTO);
        return successGet(rtn);
    }

    @Override
    public Result<RedpacketActivityInfoOfAttendDTO> infoOfAttend(Integer id) {
        RedpacketActivityInfoOfAttendDTO redpacketActivityInfoOfAttendDTO = new RedpacketActivityInfoOfAttendDTO();
        redpacketActivityInfoOfAttendDTO.setRedpacketType(RedPacketTypeEnum.BALANCE);
        return successGet(redpacketActivityInfoOfAttendDTO);
    }

    @Override
    public Result<RedpacketActivityInfoOfAttendDTO> infoOfAttend() {
        RedpacketActivityInfoOfAttendDTO redpacketActivityInfoOfAttendDTO = new RedpacketActivityInfoOfAttendDTO();
        redpacketActivityInfoOfAttendDTO.setRedpacketType(RedPacketTypeEnum.WX);
        return successGet(redpacketActivityInfoOfAttendDTO);
    }

    @Override
    public Result<HelpRedpacketActivityOpenDTO> isOpen(Integer id) {
        HelpRedpacketActivityOpenDTO rtn = new HelpRedpacketActivityOpenDTO();
        rtn.setEndPic("endPic.jpg");
        rtn.setEndUrl("endUrl");
        rtn.setOpen(true);
        rtn.setStartPic("startPic.jpg");
        rtn.setStatus(HelpRedpacketActivityStatusEnum.REGISTING);
        return successGet(rtn);
    }

}
