package com.lawu.eshop.member.ws.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.cache.dto.CheckPointsDeductionStatusDTO;
import com.lawu.eshop.cache.dto.VerifyAnswerDTO;
import com.lawu.eshop.cache.param.ReadyStartGameParam;
import com.lawu.eshop.cache.param.VerifyAnswerParam;
import com.lawu.framework.web.Result;

/**
 * 头脑PK缓存接口服务
 * @author jiangxinjun
 * @createDate 2018年3月14日
 * @updateDate 2018年3月14日
 */
@FeignClient(value = "cache-srv", path = "gameMindCache/")
public interface GameMindCacheService {
    
    /**
     * 每次用户上线, 把当前用户放入缓存中, 并且返回, 目前已经在组内的用户
     * 
     * @param userNum
     * @param attendNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月24日
     */
    @RequestMapping(value = "readyStartGame", method = RequestMethod.PUT)
    Result<List<String>> readyStartGame(@RequestBody ReadyStartGameParam param);
    
    /**
     * 校验所有参与用户积分扣除状态
     * 
     * @param userNum
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月14日
     * @updateDate 2018年3月14日
     */
    @RequestMapping(value = "checkPointsDeductionStatus", method = RequestMethod.PUT)
    Result<CheckPointsDeductionStatusDTO> checkPointsDeductionStatus(@RequestParam("userNum") String userNum);
    
    /**
     * 验证答案是否正确
     * 
     * @param userNum
     * @param questionId
     * @param rightAnswer
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月15日
     * @updateDate 2018年3月15日
     */
    @RequestMapping(value = "verifyAnswer", method = RequestMethod.PUT)
    Result<VerifyAnswerDTO> verifyAnswer(@RequestBody VerifyAnswerParam param);
    
}
