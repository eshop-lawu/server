package com.lawu.eshop.operator.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.UserGradeService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.dto.UserGradeDTO;
import com.lawu.eshop.user.param.UserGradeQuery;
import com.lawu.eshop.user.param.UserGradeUpdateParam;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 会员等级
 */
@RestController
@RequestMapping(value = "grade/")
public class UserGradeController extends BaseController {

    @Autowired
    private UserGradeService userGradeService;

    /**
     * 分页查询
     *
     * @param query
     * @return
     * @author yangqh
     */
    @PageBody
    @ApiOperation(value = "会员等级列表查询", notes = "会员等级列表查询,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @RequiresPermissions("grade:list")
    public Result<Page<UserGradeDTO>> page(@ModelAttribute UserGradeQuery query) {
        return successCreated(userGradeService.selectPage(query));
    }

    /**
     * @param param
     * @return
     * @author yangqh
     */
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.USER_GRADE_ADD)
    @ApiOperation(value = "会员等级新增保存", notes = "会员等级新增保存,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("grade:edit")
    public Result save(@ModelAttribute @Valid UserGradeUpdateParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return userGradeService.save(param);
    }

    /**
     * @param id
     * @param param
     * @param result
     * @return
     * @author yangqh
     */
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.USER_GRADE_UPDATE)
    @ApiOperation(value = "会员等级修改保存", notes = "会员等级修改保存,[]（杨清华）", httpMethod = "POST")
    @RequestMapping(value = "updateById/{id}", method = RequestMethod.POST)
    @RequiresPermissions("grade:edit")
    public Result updateById(@PathVariable("id") Long id, @ModelAttribute @Valid UserGradeUpdateParam param, BindingResult result) {
        String message = validate(result);
        if (message != null) {
            return successCreated(ResultCode.REQUIRED_PARM_EMPTY, message);
        }
        return userGradeService.updateById(id, param);
    }

    /**
     *
     * @param id
     * @return
     * @author yangqh
     */
    @ApiOperation(value = "id查询", notes = "id查询,[]（杨清华）", httpMethod = "GET")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @RequiresPermissions("grade:list")
    public Result<UserGradeDTO> selectUserGradeById(@PathVariable("id") Long id) {
        return successCreated(userGradeService.selectUserGradeById(id));
    }

    /**
     *
     * @return
     * @author meishuquan
     */
    @ApiOperation(value = "查询会员等级", notes = "查询会员等级。（梅述全）", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @RequestMapping(value = "selectLotteryActivityPointByGradeValue", method = RequestMethod.GET)
    public Result<List<UserGradeDTO>> selectLotteryActivityPointByGradeValue() {
        return userGradeService.selectLotteryActivityPointByGradeValue();
    }
}
