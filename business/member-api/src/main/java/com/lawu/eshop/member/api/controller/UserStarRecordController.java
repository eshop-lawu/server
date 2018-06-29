package com.lawu.eshop.member.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.dto.RankListDTO;
import com.lawu.eshop.game.dto.UserStarRankListDTO;
import com.lawu.eshop.game.param.RankListParam;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.member.api.service.UserStarRecordService;
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
    @ApiOperation(value = "当月用户星星排行榜前20名", notes = "当月用户星星排行榜前20名", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getStarRankList", method = RequestMethod.GET)
    public Result<RankListDTO> getStarRankList(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        //当前用户排名信息查询
        Result<RankListDTO> rankListDTOResult = userStarRecordService.currentUserRank(userNum);
        RankListDTO rankDTO = rankListDTOResult.getModel();
        Result<UserDTO> currentMemberResult = memberService.getMemberByNum(userNum);
        rankDTO.setHeadImg(currentMemberResult.getModel().getHeadimg() == null ? "" : currentMemberResult.getModel().getHeadimg());
        rankDTO.setNickName(currentMemberResult.getModel().getNickname() == null ? "" : currentMemberResult.getModel().getNickname());

        //top20排名信息查询
        RankListParam param = new RankListParam();
        param.setCurrentPage(1);
        param.setPageSize(20);
        param.setReportDate(DateUtil.getFirstDayOfMonth(new Date()));
        param.setStatus(true);
        Result<Page<UserStarRankListDTO>> pageResult = userStarRecordService.getStarRankList(param);
        List<UserStarRankListDTO> listDTOS = pageResult.getModel().getRecords();
        for (UserStarRankListDTO listDTO : listDTOS) {
            Result<UserDTO> memberDTOResult = memberService.getMemberByNum(listDTO.getUserNum());
            UserDTO memberDTO = memberDTOResult.getModel();
            listDTO.setAccount(memberDTO.getAccount().substring(0, 3) + "****" + memberDTO.getAccount().substring(memberDTO.getAccount().length() - 4, memberDTO.getAccount().length()));
            listDTO.setHeadImg(memberDTO.getHeadimg() == null ? "" : memberDTO.getHeadimg());
            listDTO.setNickName(memberDTO.getNickname() == null ? "" : memberDTO.getNickname());
        }
        rankDTO.setRankList(listDTOS);
        return successGet(rankDTO);
    }

}
