package com.lawu.eshop.activity.srv.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.activity.dto.GenerateLargeRedpacketDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityOpenDTO;
import com.lawu.eshop.activity.dto.HelpRedpacketActivityQueryDTO;
import com.lawu.eshop.activity.dto.RedpacketActivityInfoOfAttendDTO;
import com.lawu.eshop.activity.param.GenerateLargeRedpacketParam;
import com.lawu.eshop.activity.param.GenerateNormalRedpacketParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivitySaveParam;
import com.lawu.eshop.activity.param.HelpRedpacketActivityUpdateParam;
import com.lawu.eshop.activity.param.RedpacketActivityQueryParam;
import com.lawu.eshop.activity.param.SaveRedpacketParam;
import com.lawu.eshop.activity.srv.bo.ExpectedRedpacketAmountBO;
import com.lawu.eshop.activity.srv.bo.GenerateLargeRedpacketBO;
import com.lawu.eshop.activity.srv.bo.HelpRedpacketActivityBO;
import com.lawu.eshop.activity.srv.converter.HelpRedpacketActivityConverter;
import com.lawu.eshop.activity.srv.servcie.HelpRedpacketActivityService;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;

@RestController
@RequestMapping(value = "helpRedpacketActivity/")
public class HelpRedpacketActivityController extends BaseController {
    
    @Autowired
    private HelpRedpacketActivityService helpRedpacketActivityService;
    
    /**
     * 查询助力红包活动是否开启
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2018年1月2日
     */
    @RequestMapping(value = "isOpen", method = RequestMethod.GET)
    public Result<HelpRedpacketActivityOpenDTO> isOpen(@RequestParam(name = "id", required = false) Integer id) {
        HelpRedpacketActivityBO helpRedpacketActivityBO = helpRedpacketActivityService.get(id);
        if (helpRedpacketActivityBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(HelpRedpacketActivityConverter.convertHelpRedpacketActivityOpenDTO(helpRedpacketActivityBO));
    }
    
    /**
     * 查询助力红包活动详情
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @RequestMapping(value = {"detail", "detail/{id}"}, method = RequestMethod.GET)
    public Result<HelpRedpacketActivityDTO> detail(@PathVariable(name = "id", required = false) Integer id) {
        HelpRedpacketActivityBO helpRedpacketActivityBO = helpRedpacketActivityService.get(id);
        if (helpRedpacketActivityBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        HelpRedpacketActivityDTO model = HelpRedpacketActivityConverter.convert(helpRedpacketActivityBO);
        ExpectedRedpacketAmountBO expectedRedpacketAmountBO = helpRedpacketActivityService.expectedRedpacketAmount(id, null);
        model.setExpectedMaxRedpacketAmount(expectedRedpacketAmountBO.getExpectedMaxRedpacketAmount());
        model.setExpectedMinRedpacketAmount(expectedRedpacketAmountBO.getExpectedMinRedpacketAmount());
        BigDecimal abnormalRedpacketTotalAmount = helpRedpacketActivityService.getAbnormalRedpacketTotalAmount(id);
        model.setAbnormalRedpacketTotalAmount(abnormalRedpacketTotalAmount);
        return successGet(model);
    }
    
    /**
     * 助力红包活动更新
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月28日
     * @updateDate 2017年12月28日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"update", "update/{id}"}, method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated HelpRedpacketActivityUpdateParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        helpRedpacketActivityService.update(id, param);
        return successCreated();
    }
    
    /**
     * 生成大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月29日
     * @updateDate 2017年12月29日
     */
    @RequestMapping(value = {"generateLargeRedpacket", "generateLargeRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result<GenerateLargeRedpacketDTO> generateLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated GenerateLargeRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            GenerateLargeRedpacketBO generateLargeRedpacketBO = helpRedpacketActivityService.generateLargeRedpacket(id, param);
            return successCreated(HelpRedpacketActivityConverter.convert(generateLargeRedpacketBO));
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }
    
    /**
     * 保存大额红包
     * @return
     * @author jiangxinjun
     * @createDate 2017年12月30日
     * @updateDate 2017年12月30日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"saveLargeRedpacket", "saveLargeRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result saveLargeRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated List<SaveRedpacketParam> params, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            helpRedpacketActivityService.saveLargeRedpacket(id, params);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }
    
    /**
     * 生成普通红包
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"generateNormalRedpacket", "generateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result generateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated GenerateNormalRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            helpRedpacketActivityService.generateNormalRedpacket(id, param);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }
    
    /**
     * 生成普通红包
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月2日
     * @updateDate 2018年1月2日
     */
    @RequestMapping(value = {"getNormalRedpacketTotalAmount", "getNormalRedpacketTotalAmount/{id}"}, method = RequestMethod.GET)
    public Result<BigDecimal> getNormalRedpacketTotalAmount(@PathVariable(name = "id", required = false) Integer id) {
        BigDecimal normalRedpacketTotalAmount = helpRedpacketActivityService.getNormalRedpacketTotalAmount(id);
        if (normalRedpacketTotalAmount == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(normalRedpacketTotalAmount);
    }
    
    /**
     * 重新生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的重新生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"againGenerateNormalRedpacket", "againGenerateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result againGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id, @RequestBody @Validated GenerateNormalRedpacketParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        try {
            helpRedpacketActivityService.againGenerateNormalRedpacket(id, param);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }
    
    /**
     * 继续生成普通红包
     * 考虑生成红包的过程中可能出现异常,需要手动的继续生成红包
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月8日
     * @updateDate 2018年1月8日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = {"continueGenerateNormalRedpacket", "continueGenerateNormalRedpacket/{id}"}, method = RequestMethod.PUT)
    public Result continueGenerateNormalRedpacket(@PathVariable(name = "id", required = false) Integer id) {
        try {
            helpRedpacketActivityService.continueGenerateNormalRedpacket(id);
            return successCreated();
        } catch (WrongOperationException e) {
            return successCreated(ResultCode.WRONG_OPERATION, e.getMessage());
        } catch (DataNotExistException e) {
            return successCreated(ResultCode.NOT_FOUND_DATA, e.getMessage());
        }
    }
    
    /**
     * 获取活动列表
     * 
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @RequestMapping(value = "list", method = RequestMethod.PUT)
    public Result<Page<HelpRedpacketActivityQueryDTO>> list(@RequestBody @Validated RedpacketActivityQueryParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        Page<HelpRedpacketActivityBO> HelpRedpacketActivityBOPage = helpRedpacketActivityService.list(param);
        Page<HelpRedpacketActivityQueryDTO> rtn = new Page<>();
        rtn.setCurrentPage(HelpRedpacketActivityBOPage.getCurrentPage());
        rtn.setTotalCount(HelpRedpacketActivityBOPage.getTotalCount());
        rtn.setRecords(HelpRedpacketActivityConverter.convertHelpRedpacketActivityQueryDTOList(HelpRedpacketActivityBOPage.getRecords()));
        return successCreated(rtn);
    }
    
    /**
     * 保存红包活动
     * 
     * @param param
     *            保存参数
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result save(@RequestBody @Validated HelpRedpacketActivitySaveParam param, BindingResult bindingResult) {
        String message = validate(bindingResult);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        helpRedpacketActivityService.save(param);
        return successCreated();
    }
    
    /**
     * 获取开启的活动列表
     * 
     * @author jiangxinjun
     * @createDate 2018年1月15日
     * @updateDate 2018年1月15日
     */
    @RequestMapping(value = "openActivityList", method = RequestMethod.GET)
    public Result<List<HelpRedpacketActivityOpenDTO>> openActivityList() {
        List<HelpRedpacketActivityBO> list = helpRedpacketActivityService.openActivityList();
        return successGet(HelpRedpacketActivityConverter.convertHelpRedpacketActivityOpenDTOList(list));
    }
    
    /**
     * 参与红包活动所需要的信息
     * @return
     * @author jiangxinjun
     * @createDate 2018年1月17日
     * @updateDate 2018年1月17日
     */
    @RequestMapping(value = {"infoOfAttend", "infoOfAttend/{id}"}, method = RequestMethod.GET)
    public Result<RedpacketActivityInfoOfAttendDTO> infoOfAttend(@PathVariable(name = "id", required = false) Integer id) {
        HelpRedpacketActivityBO helpRedpacketActivityBO = helpRedpacketActivityService.get(id);
        if (helpRedpacketActivityBO == null) {
            return successGet(ResultCode.NOT_FOUND_DATA);
        }
        return successGet(HelpRedpacketActivityConverter.convertRedpacketActivityInfoOfAttendDTO(helpRedpacketActivityBO));
    }
}
