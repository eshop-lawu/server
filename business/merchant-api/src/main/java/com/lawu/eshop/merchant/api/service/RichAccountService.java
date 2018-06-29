package com.lawu.eshop.merchant.api.service;

import com.lawu.eshop.activity.dto.*;
import com.lawu.eshop.activity.param.ReceiveDiamondsParam;
import com.lawu.framework.web.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lihj
 * @date 2018/5/2
 */
@FeignClient(value = "activity-srv", path = "richAccount/")
public interface RichAccountService {

    @RequestMapping(method = RequestMethod.GET, value = "getPersonalRichAccountInfo")
    Result<PersonalRichAccountDTO> getPersonalRichAccountInfo(@RequestParam("userNum") String userNum);

    @RequestMapping(method = RequestMethod.GET, value = "getRichInfo")
	Result<RichDetailDTO> getRichInfo();
    
    @RequestMapping(method = RequestMethod.GET, value = "getRichPowerInfoRecord")
	Result<RichPowerInfoRecordDTO> getRichPowerInfoRecord(@RequestParam("userNum") String userNum);
    
    @RequestMapping(method = RequestMethod.GET, value = "getMyRichDiamondRecordInfo")
	Result<RichMyDiamondRecordInfoDTO> getMyRichDiamondRecordInfo(@RequestParam("userNum") String userNum);
    
    @RequestMapping(value = "getIdentityInfo",method = RequestMethod.GET)
    Result<IdentityInfoDTO> getIdentityInfo(@RequestParam("memberNum") String memberNum);
	
    /**
     * 领取钻石
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月4日
     * @updateDate 2018年5月4日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.PUT, value = "receiveDiamonds")
    public Result receiveDiamonds(@RequestParam("memberNum") String memberNum,
                                  @RequestBody ReceiveDiamondsParam param);

    /**
     * 绑定支付宝
     *
     * @param memberNum
     * @param alipayUserId
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(method = RequestMethod.PUT, value = "bindAlipayInfo")
    Result bindAlipayInfo(@RequestParam("memberNum") String memberNum, @RequestParam("alipayUserId") String alipayUserId);
}
