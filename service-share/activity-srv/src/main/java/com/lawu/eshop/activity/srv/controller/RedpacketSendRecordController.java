package com.lawu.eshop.activity.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.RedpacketMchBillnoDTO;
import com.lawu.eshop.activity.dto.RedpacketSendRecordDTO;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.eshop.activity.srv.bo.RedpacketSendRecordBO;
import com.lawu.eshop.activity.srv.converter.RedpacketSendRecordConverter;
import com.lawu.eshop.activity.srv.servcie.RedpacketSendRecordService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
@RestController
@RequestMapping(value = "redpacketSendRecord/")
public class RedpacketSendRecordController extends BaseController {

    @Autowired
    private RedpacketSendRecordService redpacketSendRecordService;

    /**
     * 查询待处理的发放红包记录商户订单号
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "listSendRedpacketMchBillno", method = RequestMethod.GET)
    public Result<List<RedpacketMchBillnoDTO>> listSendRedpacketMchBillno(@RequestParam int offset, @RequestParam int pageSize) {
        List<RedpacketSendRecordBO> sendRecordBOS = redpacketSendRecordService.listSendRedpacketMchBillno(offset, pageSize);
        return successGet(RedpacketSendRecordConverter.converRedpacketMchBillnoDTOS(sendRecordBOS));
    }

    /**
     * 保存发放红包记录、更新参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "saveRedpacketSendRecord", method = RequestMethod.POST)
    public Result<RedpacketSendRecordDTO> saveRedpacketSendRecord(@RequestBody RedpacketSendRecordParam param) {
        Long id = redpacketSendRecordService.saveRedpacketSendRecord(param);
        RedpacketSendRecordDTO recordDTO = new RedpacketSendRecordDTO();
        recordDTO.setId(id);
        return successCreated(recordDTO);
    }

    /**
     * 更新红包发送结果
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "updateRedpacketSendRecordResult", method = RequestMethod.PUT)
    public Result updateRedpacketSendRecordResult(@RequestBody RedpacketSendRecordParam param) {
        redpacketSendRecordService.updateRedpacketSendRecordResult(param);
        return successCreated();
    }

    /**
     * 更新发放红包记录、参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "updateRedpacketSendRecordStatus", method = RequestMethod.PUT)
    public Result updateRedpacketSendRecordStatus(@RequestBody RedpacketSendRecordParam param) {
        redpacketSendRecordService.updateRedpacketSendRecordStatus(param);
        return successCreated();
    }

}
