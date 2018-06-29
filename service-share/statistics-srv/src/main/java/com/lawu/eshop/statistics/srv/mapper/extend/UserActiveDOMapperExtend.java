package com.lawu.eshop.statistics.srv.mapper.extend;

import com.lawu.eshop.statistics.srv.domain.extend.UserActiveDOView;

import java.util.Date;
import java.util.List;

/**
 * @author zhangyong
 * @date 2017/6/29.
 */
public interface UserActiveDOMapperExtend {
    Integer collectionMemberActiveDaily(Date time);

    Integer collectionMerchantActiveDaily(Date time);

    Integer collectionMemberActiveMonth(Date nowDate);

    Integer collectionMerchantActiveMonth(Date nowDate);

    List<UserActiveDOView> collectionMemberActiveAreaDaily(Date time);

    List<UserActiveDOView>  collectionMerchantActiveAreaDaily(Date time);


    List<UserActiveDOView> collectionMemberActiveAreaMonth(Date time);

    List<UserActiveDOView> collectionMerchantActiveAreaMonth(Date time);
}
