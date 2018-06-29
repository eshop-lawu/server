package com.lawu.eshop.activity.srv.servcie.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.activity.constants.ActivityAttendStatusEnum;
import com.lawu.eshop.activity.constants.RedpacketSendRecordStatusEnum;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.converter.RedpacketSendRecordConverter;
import com.lawu.eshop.activity.srv.domain.HelpRedpacketAttendDetailDO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDO;
import com.lawu.eshop.activity.srv.domain.RedpacketSendRecordDOExample;
import com.lawu.eshop.activity.srv.mapper.HelpRedpacketAttendDetailDOMapper;
import com.lawu.eshop.activity.srv.mapper.RedpacketSendRecordDOMapper;
import com.lawu.eshop.activity.srv.servcie.RedpacketSendRecordService;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
@Service
public class RedpacketSendRecordServiceImpl implements RedpacketSendRecordService {

    @Autowired
    private RedpacketSendRecordDOMapper redpacketSendRecordDOMapper;

    @Autowired
    private HelpRedpacketAttendDetailDOMapper helpRedpacketAttendDetailDOMapper;

    @Override
    public List<RedpacketSendRecordBO> listSendRedpacketMchBillno(int offset, int pageSize) {
        RedpacketSendRecordDOExample example = new RedpacketSendRecordDOExample();
        List<Byte> statusList = new ArrayList<>();
        statusList.add(RedpacketSendRecordStatusEnum.SENDING.getVal());
        statusList.add(RedpacketSendRecordStatusEnum.SENT.getVal());
        statusList.add(RedpacketSendRecordStatusEnum.RFUND_ING.getVal());
        example.createCriteria().andStatusIn(statusList);
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        List<RedpacketSendRecordDO> sendRecordDOS = redpacketSendRecordDOMapper.selectByExampleWithRowbounds(example, rowBounds);
        return RedpacketSendRecordConverter.converBOS(sendRecordDOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveRedpacketSendRecord(RedpacketSendRecordParam param) {
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setActivityId(param.getActivityId());
        sendRecordDO.setAttendDetailId(param.getAttendDetailId());
        sendRecordDO.setUserNum(param.getUserNum());
        sendRecordDO.setOpenid(param.getOpenid());
        sendRecordDO.setMchBillno(param.getMchBillno());
        sendRecordDO.setTotalAmount(param.getTotalAmount());
        sendRecordDO.setStatus(param.getStatusEnum().getVal());
        sendRecordDO.setGmtCreate(new Date());
        redpacketSendRecordDOMapper.insertSelective(sendRecordDO);

        //更新红包参与详情
        HelpRedpacketAttendDetailDO attendDetailDO = helpRedpacketAttendDetailDOMapper.selectByPrimaryKey(param.getAttendDetailId());
        int sendCount = 1;
        if (attendDetailDO.getSendCount() != null && attendDetailDO.getSendCount() > 0) {
            sendCount += attendDetailDO.getSendCount();
        }
        attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setId(param.getAttendDetailId());
        attendDetailDO.setStatus(ActivityAttendStatusEnum.SEND.getVal());
        attendDetailDO.setSendCount(sendCount);
        attendDetailDO.setSendTime(new Date());
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(attendDetailDO);
        return sendRecordDO.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRedpacketSendRecordResult(RedpacketSendRecordParam param) {
        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(param.getId());
        sendRecordDO.setReturnCode(param.getReturnCode());
        sendRecordDO.setReturnMsg(param.getReturnMsg());
        sendRecordDO.setResultCode(param.getResultCode());
        sendRecordDO.setErrCode(param.getErrCode());
        sendRecordDO.setErrCodeDes(param.getErrCodeDes());
        sendRecordDO.setSendListId(param.getSendListId());
        sendRecordDO.setGmtModified(new Date());
        redpacketSendRecordDOMapper.updateByPrimaryKeySelective(sendRecordDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRedpacketSendRecordStatus(RedpacketSendRecordParam param) {
        RedpacketSendRecordDOExample example = new RedpacketSendRecordDOExample();
        example.createCriteria().andMchBillnoEqualTo(param.getMchBillno());
        List<RedpacketSendRecordDO> sendRecordDOS = redpacketSendRecordDOMapper.selectByExample(example);

        RedpacketSendRecordDO sendRecordDO = new RedpacketSendRecordDO();
        sendRecordDO.setId(sendRecordDOS.get(0).getId());
        sendRecordDO.setStatus(param.getStatusEnum().getVal());
        sendRecordDO.setRcvTime(param.getRcvTime());
        sendRecordDO.setRefundTime(param.getRefundTime());
        sendRecordDO.setGmtModified(new Date());
        redpacketSendRecordDOMapper.updateByPrimaryKeySelective(sendRecordDO);

        //更新红包参与详情
        HelpRedpacketAttendDetailDO attendDetailDO = new HelpRedpacketAttendDetailDO();
        attendDetailDO.setId(param.getAttendDetailId());
        if (param.getStatusEnum().getVal().byteValue() == RedpacketSendRecordStatusEnum.FAILED.getVal()) {
            attendDetailDO.setStatus(ActivityAttendStatusEnum.SEND_FAIL.getVal());
        } else if (param.getStatusEnum().getVal().byteValue() == RedpacketSendRecordStatusEnum.RECEIVED.getVal()) {
            attendDetailDO.setStatus(ActivityAttendStatusEnum.SEND_SUCCESS.getVal());
        } else if (param.getStatusEnum().getVal().byteValue() == RedpacketSendRecordStatusEnum.REFUND.getVal()) {
            attendDetailDO.setStatus(ActivityAttendStatusEnum.REFUND.getVal());
        } else {
            return;
        }
        attendDetailDO.setSendRemark(sendRecordDOS.get(0).getErrCodeDes());
        attendDetailDO.setGmtModified(new Date());
        helpRedpacketAttendDetailDOMapper.updateByPrimaryKeySelective(attendDetailDO);
    }

    @Override
    public RedpacketSendRecordBO getRedpacketSendRecord(Long attendDetailId, String mchBillno) {
        RedpacketSendRecordDOExample example = new RedpacketSendRecordDOExample();
        example.createCriteria().andAttendDetailIdEqualTo(attendDetailId).andMchBillnoEqualTo(mchBillno);
        List<RedpacketSendRecordDO> recordDOS = redpacketSendRecordDOMapper.selectByExample(example);
        if (recordDOS.isEmpty()) {
            return null;
        }
        return RedpacketSendRecordConverter.converBO(recordDOS.get(0));
    }

}
