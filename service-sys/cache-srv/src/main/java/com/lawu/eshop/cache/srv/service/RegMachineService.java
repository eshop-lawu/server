package com.lawu.eshop.cache.srv.service;

import com.lawu.eshop.cache.dto.AbnormalRedisCountDTO;
import com.lawu.eshop.cache.param.AbnormalInfoParam;
import com.lawu.eshop.cache.param.EarlyAbnormalParam;

/**
 * @author zhangyong
 * @date 2018/1/17.
 */
public interface RegMachineService {

    void addHfRegRedisRecord(AbnormalInfoParam param);

    AbnormalRedisCountDTO getAbnormalCount(String userNum);

    void addEarlyHfRedisRecord(EarlyAbnormalParam param);
}
