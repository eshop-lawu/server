package com.lawu.eshop.member.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.framework.web.Result;

/**
 * 助力红包活动服务接口
 * @author jiangxinjun
 * @createDate 2017年12月28日
 * @updateDate 2017年12月28日
 */
@FeignClient(value = "activity-srv", path = "helpRedpacketActivity/")
public interface HelpRedpacketActivityService {
    
    /**
     * 查询助力红包活动是否开启
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @RequestMapping(value = "isOpen", method = RequestMethod.GET)
    Result<HelpRedpacketActivityOpenDTO> isOpen();
    
    /**
     * 查询助力红包活动是否开启
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @RequestMapping(value = "isOpen", method = RequestMethod.GET)
    Result<HelpRedpacketActivityOpenDTO> isOpen(@RequestParam(name = "id", required = false) Integer id);
    
    /**
     * 获取开启的活动列表
     * 
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @RequestMapping(value = "openActivityList", method = RequestMethod.GET)
    Result<List<HelpRedpacketActivityOpenDTO>> openActivityList();
    
    /**
     * 参与红包活动所需要的信息
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月17日
     * @updateDate 2018年1月17日
     */
    @Deprecated
    @RequestMapping(value = "infoOfAttend", method = RequestMethod.GET)
    Result<RedpacketActivityInfoOfAttendDTO> infoOfAttend();
    
    /**
     * 参与红包活动所需要的信息
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月17日
     * @updateDate 2018年1月17日
     */
    @RequestMapping(value = "infoOfAttend/{id}", method = RequestMethod.GET)
    Result<RedpacketActivityInfoOfAttendDTO> infoOfAttend(@PathVariable("id") Integer id);
}
