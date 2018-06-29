package com.lawu.eshop.cache.srv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.cache.constants.RedisDataTypeEnum;
import com.lawu.eshop.cache.dto.SerializeBeanDTO;
import com.lawu.eshop.cache.srv.service.SerializeUtilCacheService;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

/**
 * 缓存数据反序列化
 */
@RestController
@RequestMapping(value = "serializeUtilCache/")
public class SerializeUtilCacheController extends BaseController {

    @Autowired
    private SerializeUtilCacheService serializeUtilCacheService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Result<SerializeBeanDTO> get(@RequestParam String redisKey, @RequestParam String keyType, @RequestParam int serializeFlag) {
        String value = serializeUtilCacheService.get(redisKey, RedisDataTypeEnum.get(keyType), serializeFlag);
        SerializeBeanDTO rtn = new SerializeBeanDTO();
        rtn.setJsonStr(value);
        return successGet(rtn);
    }

}
