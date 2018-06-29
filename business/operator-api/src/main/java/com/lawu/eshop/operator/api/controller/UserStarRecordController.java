package com.lawu.eshop.operator.api.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.param.RankListOperatorParam;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.api.service.UserStarRecordService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;
import com.lawu.utils.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author zhangyong
 * @date 2018/3/10.
 */
@Api(tags = "starRecord")
@RestController
@RequestMapping(value = "starRecord/")
public class UserStarRecordController extends BaseController {

    @Autowired
    private UserStarRecordService userStarRecordService;

    @Autowired
    private MemberService memberService;

    @Audit(date = "2018-03-10", reviewer = "章勇")
    @ApiOperation(value = "查询用户星星排行榜", notes = "查询用户星星排行榜", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @PageBody
    @RequiresPermissions("starRank:list")
    @RequestMapping(value = "getStarRankList", method = RequestMethod.GET)
    public Result<Page<UserStarRankListDTO>> getStarRankList(@ModelAttribute RankListOperatorParam param) {
        RankListParam listParam = new RankListParam();
        if (StringUtils.isEmpty(param.getMonth())) {
            listParam.setReportDate(new Date());
        } else {
            listParam.setReportDate(DateUtil.getDateFormat(param.getMonth() + "-01"));
        }
        listParam.setCurrentPage(param.getCurrentPage());
        listParam.setPageSize(param.getPageSize());
        listParam.setAccount(param.getAccount());
        Result<Page<UserStarRankListDTO>> pageResult = userStarRecordService.getStarRankList(listParam);
        List<UserStarRankListDTO> listDTOS = pageResult.getModel().getRecords();
        for (UserStarRankListDTO listDTO : listDTOS) {
            Result<UserDTO> memberDTOResult = memberService.getMemberByNum(listDTO.getUserNum());
            UserDTO userDTO = memberDTOResult.getModel();
            listDTO.setAccount(userDTO.getAccount());
            listDTO.setHeadImg(userDTO.getHeadimg() == null ? "" : userDTO.getHeadimg());
            listDTO.setNickName(userDTO.getNickname() == null ? "" : userDTO.getNickname());
        }
        return pageResult;
    }

    @ApiOperation(value = "屏蔽星星排行", notes = "屏蔽星星排行", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_USER_STAR_HIDDEN,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "editStarStatus/{id}", method = RequestMethod.PUT)
    public Result editStarStatus(@PathVariable("id") Long id, @RequestParam("status") Boolean status) {
        userStarRecordService.editStarStatus(id, status);
        return successCreated();
    }

    @ApiOperation(value = "设置星星排行榜星星数量", notes = "设置星星排行榜星星数量", httpMethod = "POST")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_USER_STAR_ADD,idParamName ="id")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresAuthentication
    @RequestMapping(value = "addStarById/{id}", method = RequestMethod.POST)
    public Result addStarById(@PathVariable("id") Long id, @RequestParam("starCount") Integer starCount){
        userStarRecordService.addStarById(id, starCount);
        return successCreated();
    }
}
