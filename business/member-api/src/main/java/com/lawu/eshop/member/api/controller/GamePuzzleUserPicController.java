package com.lawu.eshop.member.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.authorization.annotation.Authorization;
import com.lawu.authorization.util.UserUtil;
import com.lawu.eshop.framework.web.impl.constants.UserConstant;
import com.lawu.eshop.game.constants.GamePuzzleTypeEnum;
import com.lawu.eshop.game.constants.GamePuzzleUserPicStatusEnum;
import com.lawu.eshop.game.dto.GamePuzzleUserPicDTO;
import com.lawu.eshop.game.dto.GamePuzzleUserPicUploadNumberDTO;
import com.lawu.eshop.game.param.GamePuzzleBaseParam;
import com.lawu.eshop.game.param.GamePuzzleParam;
import com.lawu.eshop.game.query.GamePuzzleUserPicQuery;
import com.lawu.eshop.game.query.GamePuzzleUserPicRealQuery;
import com.lawu.eshop.member.api.service.GamePuzzleUserPicService;
import com.lawu.eshop.member.api.service.MemberService;
import com.lawu.eshop.user.dto.UserDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;
import com.lawu.framework.web.doc.annotation.Audit;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * @author meishuquan
 * @date 2018/3/9.
 */
@Api(tags = "gamePuzzleUserPic")
@RestController
@RequestMapping(value = "gamePuzzleUserPic/")
public class GamePuzzleUserPicController extends BaseController {

    @Autowired
    private GamePuzzleUserPicService gamePuzzleUserPicService;

    @Autowired
    private MemberService memberService;

    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @ApiOperation(value = "提交拼图", notes = "提交拼图。(梅述全)", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @Authorization
    @RequestMapping(value = "saveGamePuzzleUserPic", method = RequestMethod.POST)
    public Result saveGamePuzzleUserPic(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                        @ModelAttribute GamePuzzleBaseParam param) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<UserDTO> userDTOResult = memberService.getMemberByNum(userNum);
        GamePuzzleParam puzzleParam = new GamePuzzleParam();
        puzzleParam.setUserNum(userNum);
        puzzleParam.setUserNickname(isSuccess(userDTOResult) ? userDTOResult.getModel().getNickname() : "");
        puzzleParam.setImgPath(param.getImgPath());
        puzzleParam.setTypeEnum(GamePuzzleTypeEnum.PUZZLE);
        puzzleParam.setIsSimple(param.getIsSimple());
        puzzleParam.setIsCommon(param.getIsCommon());
        puzzleParam.setIsHard(param.getIsHard());
        puzzleParam.setUserPicStatusEnum(GamePuzzleUserPicStatusEnum.CHECK_PENDING);
        gamePuzzleUserPicService.saveGamePuzzleUserPic(puzzleParam);
        return successCreated();
    }

    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @ApiOperation(value = "统计提交的拼图数量", notes = "统计提交的拼图数量。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "getGamePuzzleUserPicUploadNumber", method = RequestMethod.GET)
    public Result<GamePuzzleUserPicUploadNumberDTO> getGamePuzzleUserPicUploadNumber(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        Result<GamePuzzleUserPicUploadNumberDTO> result = gamePuzzleUserPicService.getGamePuzzleUserPicUploadNumber(userNum);
        return successGet(result);
    }

    @Audit(date = "2018-03-15", reviewer = "孙林青")
    @ApiOperation(value = "提交的拼图记录", notes = "提交的拼图记录。(梅述全)", httpMethod = "GET")
    @ApiResponse(code = HttpCode.SC_OK, message = "success")
    @Authorization
    @RequestMapping(value = "listGamePuzzleUserPic", method = RequestMethod.GET)
    public Result<Page<GamePuzzleUserPicDTO>> listGamePuzzleUserPic(@RequestHeader(UserConstant.REQ_HEADER_TOKEN) String token,
                                                                    @ModelAttribute GamePuzzleUserPicQuery query) {
        String userNum = UserUtil.getCurrentUserNum(getRequest());
        GamePuzzleUserPicRealQuery realQuery = new GamePuzzleUserPicRealQuery();
        realQuery.setUserNum(userNum);
        realQuery.setCurrentPage(query.getCurrentPage());
        realQuery.setPageSize(query.getPageSize());
        Result<Page<GamePuzzleUserPicDTO>> result = gamePuzzleUserPicService.listGamePuzzleUserPic(realQuery);
        return successGet(result);
    }

}
