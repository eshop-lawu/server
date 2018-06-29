package com.lawu.eshop.product.srv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.product.dto.RecentlySeckillActivityDTO;
import com.lawu.eshop.product.dto.SeckillActivityDetailsDTO;
import com.lawu.eshop.product.dto.SeckillActivityInfoDTO;
import com.lawu.eshop.product.dto.SeckillActivityThatDayDTO;
import com.lawu.eshop.product.param.SeckillActivityPageQueryParam;
import com.lawu.eshop.product.param.SeckillActivitySaveParam;
import com.lawu.eshop.product.param.SeckillActivityUpdateParam;
import com.lawu.eshop.product.srv.bo.RecentlySeckillActivityBO;
import com.lawu.eshop.product.srv.bo.SeckillActivityBO;
import com.lawu.eshop.product.srv.converter.SeckillActivityConverter;
import com.lawu.eshop.product.srv.service.SeckillActivityService;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "seckillActivity/")
public class SeckillActivityController extends BaseController {
    
    @Autowired
    private SeckillActivityService seckillActivityService;
    
    /**
     * 根据查询参数分页查询活动列表
     * 用于运营平台
     * 
     * @param param
     * @param bindingResult
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "page", method = RequestMethod.PUT)
    public Result<Page<SeckillActivityInfoDTO>> page(@RequestBody @Validated SeckillActivityPageQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Page<SeckillActivityBO> page = seckillActivityService.page(param);
        Page<SeckillActivityInfoDTO> rtn = new Page<>();
        rtn.setCurrentPage(page.getCurrentPage());
        rtn.setTotalCount(page.getTotalCount());
        rtn.setRecords(SeckillActivityConverter.convertSeckillActivityInfoDTOList(page.getRecords()));
        return successCreated(rtn);
    }
    
    /**
     * 查询当天所有活动
     * 用于用户端
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    @RequestMapping(value = "thatday/list", method = RequestMethod.GET)
    public Result<List<SeckillActivityThatDayDTO>> thatDayList() {
        List<SeckillActivityBO> list = seckillActivityService.thatDayList();
        return successGet(SeckillActivityConverter.convertSeckillActivityThatDayDTOList(list));
    }
    
    /**
     * 查询当天所有活动
     * 用于用户端
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月23日
     * @updateDate 2017年11月23日
     */
    @Deprecated
    @RequestMapping(value = "recently/list", method = RequestMethod.GET)
    public Result<List<SeckillActivityThatDayDTO>> recentlyList() {
        List<SeckillActivityBO> list = seckillActivityService.recentlyList();
        return successGet(SeckillActivityConverter.convertSeckillActivityThatDayDTOList(list));
    }
    
    /**
     * 根据id查询活动详情
     * 用于运营平台
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Result<SeckillActivityDetailsDTO> detail(@PathVariable("id") Long id) {
        SeckillActivityBO seckillActivityBO = null;
        try {
            seckillActivityBO = seckillActivityService.get(id);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
        return successCreated(SeckillActivityConverter.convert(seckillActivityBO));
    }
    
    /**
     * 根据id删除抢购活动   
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.PUT)
    public Result<?> delete(@PathVariable("id") Long id) {
        try {
            seckillActivityService.delete(id);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            return successGet(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 根据id下架抢购活动
     * 下架之后抢购的活动的状态为已结束
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "down/{id}", method = RequestMethod.PUT)
    public Result<?> down(@PathVariable("id") Long id) {
        try {
            seckillActivityService.down(id);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            return successGet(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 根据id发布抢购活动   
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "release/{id}", method = RequestMethod.PUT)
    public Result<?> release(@PathVariable("id") Long id) {
        try {
            seckillActivityService.release(id);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            return successGet(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 根据id更新抢购活动
     * 
     * @param id 抢购活动id
     * @param param 抢购活动更新参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public Result<?> update(@PathVariable("id") Long id, @RequestBody @Validated SeckillActivityUpdateParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            seckillActivityService.update(id, param);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            return successGet(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 根据id审核抢购活动
     * 
     * @param id 抢购活动id
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月27日
     * @updateDate 2017年11月27日
     */
    @RequestMapping(value = "audit/{id}", method = RequestMethod.PUT)
    public Result<?> audit(@PathVariable("id") Long id) {
        try {
            seckillActivityService.audit(id);
        } catch (DataNotExistException e) {
            return successGet(ResultCode.NOT_FOUND_DATA, e.getMessage());
        } catch (WrongOperationException e) {
            return successGet(ResultCode.FAIL, e.getMessage());
        }
        return successCreated();
    }
    
    /**
     * 新增抢购活动
     * 
     * @param param 抢购活动保存参数
     * @return
     * @author jiangxinjun
     * @createDate 2017年11月28日
     * @updateDate 2017年11月28日
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Result<?> add(@RequestBody @Validated SeckillActivitySaveParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        seckillActivityService.add(param);
        return successCreated();
    }
    
    /**
     * 查询最近一场活动的首页图片以及倒计时
     * 用于用户端
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年2月28日
     * @updateDate 2018年2月28日
     */
    @Deprecated
    @GetMapping(value = "recently")
    public Result<RecentlySeckillActivityDTO> recentlyActivity() {
        RecentlySeckillActivityBO recentlyActivity = seckillActivityService.recentlyActivity();
        RecentlySeckillActivityDTO model = new RecentlySeckillActivityDTO();
        model.setActivityStatus(recentlyActivity.getActivityStatus());
        model.setCountdown(recentlyActivity.getCountdown());
        model.setHomePicture(recentlyActivity.getHomePicture());
        return successGet(model);
    }
}
