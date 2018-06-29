package com.lawu.eshop.statistics.srv.service;

import com.lawu.eshop.statistics.srv.bo.UserActiveBO;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
public interface UserActiveService {
    Integer collectionMemberActiveDaily(Date date);

    Integer collectionMerchantActiveDaily(Date date);

    Integer collectionMemberActiveMonth(Date date);

    Integer collectionMerchantActiveMonth(Date date);

    Date getMemberActiveDaily();
    
    Date getMemberActiveMonth();
    
    List<UserActiveBO> collectionMemberActiveAreaDaily(String reportDate);

    List<UserActiveBO> collectionMerchantActiveAreaDaily(String reportDate);

    List<UserActiveBO> collectionMemberActiveAreaMonth(String reportDate);

    List<UserActiveBO> collectionMerchantActiveAreaMonth(String reportDate);
}
