package com.lawu.eshop.user.srv.service;

import com.lawu.eshop.user.param.ListPropertyParam;
import com.lawu.eshop.user.srv.bo.PropertyBO;
import com.lawu.framework.core.page.Page;

/**
 * @author meishuquan
 * @date 2017/4/10.
 */
public interface PropertyService {

    /**
     * 保存系统参数信息
     *
     * @param name
     * @param value
     * @param remark
     */
    void saveProperty(String name, String value, String remark);

    /**
     * 根据ID删除系统参数
     *
     * @param id
     */
    void deletePropertyById(Long id);

    /**
     * 根据ID修改系统参数
     *
     * @param id
     * @param name
     * @param value
     * @param remark
     */
    void updatePropertyById(Long id, String name, String value, String remark);

    /**
     * 根据ID查询系统参数
     *
     * @param id
     * @return
     */
    PropertyBO getPropertyById(Long id);

    /**
     * 系统参数列表
     *
     * @param listPropertyParam
     * @return
     */
    Page<PropertyBO> listProperty(ListPropertyParam listPropertyParam);

    String getValue(String key);
}
