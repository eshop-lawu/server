package com.lawu.eshop.jobs.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.HelpRedpacketAttendSendDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketInviteAbnormalDTO;
import com.lawu.eshop.activity.dto.IdentifiedAsAbnormalPackageDTO;
import com.lawu.eshop.activity.dto.SignAbnormalAccountDTO;
import com.lawu.eshop.activity.param.AbnormalInviteParam;
import com.lawu.eshop.activity.param.SignAbnormalParam;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/1/3.
 */
@FeignClient(value = "activity-srv")
public interface HelpRedpacketAttendDetailService {

    /**
     * 查询待发放的红包详情记录
     *
     * @param offset
     * @param pageSize
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "helpRedpacketAttendDetail/listSendRedpacketAttendDetail", method = RequestMethod.GET)
    Result<List<HelpRedpacketAttendSendDTO>> listSendRedpacketAttendDetail(@RequestParam("offset") int offset, @RequestParam("pageSize") int pageSize);

    /**
     * 根据id查询发送红包信息
     *
     * @param id
     * @return
     * @author meishuquan
     */
    @RequestMapping(value = "helpRedpacketAttendDetail/getSendRedpacketAttendDetail/{id}", method = RequestMethod.GET)
    Result<HelpRedpacketAttendSendDTO> getSendRedpacketAttendDetail(@PathVariable("id") Long id, @RequestParam("mchBillno") String mchBillno);


    @RequestMapping(value = "helpRedpacketAttendDetail/executeUpdateActivitySuspectedStatus", method = RequestMethod.PUT)
    Result executeUpdateActivitySuspectedStatus();

    @RequestMapping(value = "helpRedpacketAttendDetail/queryAttendDetailList", method = RequestMethod.POST)
    Result<List<SignAbnormalAccountDTO>> queryAttendDetailList(@ModelAttribute SignAbnormalParam param);

    @RequestMapping(value = "helpRedpacketInvite/queryAbnormalInviteRecord", method = RequestMethod.POST)
    Result<HelpRedpacketInviteAbnormalDTO> queryAbnormalInviteRecord(@ModelAttribute AbnormalInviteParam param);

    @RequestMapping(value = "helpRedpacketAttendDetail/updateActivityAbnormalStatus/{id}", method = RequestMethod.PUT)
    Result updateActivityAbnormalStatus(@PathVariable("id") Long id,@RequestParam("status") Byte status);

    /**
     * 标识当前用户活动参与记录为异常<p>
     * 以及下级参与记录也标识为异常
     *
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月1日
     * @updateDate 2018年3月1日
     */
    @RequestMapping(value = "helpRedpacketAttendDetail/identifiedAsAbnormal",method = RequestMethod.PUT)
    Result<IdentifiedAsAbnormalPackageDTO> identifiedAsAbnormal(@RequestBody List<String> userNums, @RequestParam("activityId") Integer activityId);
}
