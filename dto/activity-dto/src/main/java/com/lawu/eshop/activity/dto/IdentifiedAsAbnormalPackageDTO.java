package com.lawu.eshop.activity.dto;

import java.util.List;

/**
 * 标识异常记录包装DTO
 * 
 * @author jiangxinjun
 * @createDate 2018年3月1日
 * @updateDate 2018年3月1日
 */
public class IdentifiedAsAbnormalPackageDTO {
    
    /**
     * 异常记录集合
     */
    List<IdentifiedAsAbnormalDTO> abnormalList;

    public List<IdentifiedAsAbnormalDTO> getAbnormalList() {
        return abnormalList;
    }

    public void setAbnormalList(List<IdentifiedAsAbnormalDTO> abnormalList) {
        this.abnormalList = abnormalList;
    }
    
}
