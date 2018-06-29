package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.RedpacketMchBillnoDTO;
import com.lawu.eshop.activity.dto.RedpacketSendRecordDTO;
import com.lawu.eshop.activity.param.RedpacketSendRecordParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
@FeignClient(value = "activity-srv")
public interface RedpacketSendRecordService {

    /**
     * 查询待处理的发放红包记录商户订单号
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "redpacketSendRecord/listSendRedpacketMchBillno", method = RequestMethod.GET)
    Result<List<RedpacketMchBillnoDTO>> listSendRedpacketMchBillno(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

    /**
     * 保存发放红包记录、更新参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "redpacketSendRecord/saveRedpacketSendRecord", method = RequestMethod.POST)
    Result<RedpacketSendRecordDTO> saveRedpacketSendRecord(@RequestBody RedpacketSendRecordParam param);

    /**
     * 更新红包发送结果
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "redpacketSendRecord/updateRedpacketSendRecordResult", method = RequestMethod.PUT)
    Result updateRedpacketSendRecordResult(@RequestBody RedpacketSendRecordParam param);

    /**
     * 更新发放红包记录、参与详情记录
     *
     * @param param
     * @author meishuquan
     */
    @RequestMapping(value = "redpacketSendRecord/updateRedpacketSendRecordStatus", method = RequestMethod.PUT)
    Result updateRedpacketSendRecordStatus(@RequestBody RedpacketSendRecordParam param);

}
