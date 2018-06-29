package com.lawu.eshop.cache.srv.service;

import com.lawu.eshop.cache.constants.RedisDataTypeEnum;

public interface SerializeUtilCacheService {

	String get(String redisKey, RedisDataTypeEnum keyType,int serializeFlag);
}
