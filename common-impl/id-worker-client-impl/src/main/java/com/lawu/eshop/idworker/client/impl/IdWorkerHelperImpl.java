package com.lawu.eshop.idworker.client.impl;

import com.lawu.idworker.client.util.IdWorkerHelper;

/**
 * @author Leach
 * @date 2017/10/24
 */
public class IdWorkerHelperImpl {
    
    /**
     * 生产编号
     * 
     * @param bizIdType 编号的类型
     * @return
     * @author jiangxinjun
     * @date 2017年10月25日
     */
    public static String generate(BizIdType bizIdType) {
        return bizIdType.getPrefix() + IdWorkerHelper.generate();
    }
}
