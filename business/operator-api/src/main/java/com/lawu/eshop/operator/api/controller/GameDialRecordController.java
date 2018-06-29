package com.lawu.eshop.operator.api.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.dto.GameDialRecordDTO;
import com.lawu.eshop.game.query.GameDailRecordPageQuery;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameDialRecordService;
import com.lawu.eshop.operator.api.service.MemberService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.eshop.user.dto.MemberDTO;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月16日
 */
@Api(tags = "gameDialRecord")
@RestController
@RequestMapping(value = "gameDialRecord/")
public class GameDialRecordController extends BaseController{
	
	@Autowired
	private GameDialRecordService gameDialRecordService;
	
	@Autowired
    private MemberService memberService;
	
	@SuppressWarnings("unchecked")
	@PageBody
	@ApiOperation(value = "查询中奖列表", notes = "查询中奖列表（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialRecord:list")
    @RequestMapping(value = {"page"}, method = RequestMethod.POST)
	public  Result<Page<GameDialRecordDTO>> page(@RequestBody GameDailRecordPageQuery query) {
    	Result<Page<GameDialRecordDTO>>  result = gameDialRecordService.page(query);
    	for (GameDialRecordDTO operatorDTO : result.getModel().getRecords()) {
            Result<MemberDTO> memberResult = memberService.getMemberByAccount(operatorDTO.getUserAccount());
            if (isSuccess(memberResult)) {
                operatorDTO.setNickName(memberResult.getModel().getNickname()); 
            }
        }
		return successCreated(result);
	}
	
	
	@ApiOperation(value = "发放奖品", notes = "发放奖品。（张荣成）", httpMethod = "PUT")
    @LogRecord(type = OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_DIAL_PRAIZE_SEND)
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameDialRecord:send")
    @RequestMapping(value = "sendPrize/{id}", method = RequestMethod.PUT)
    public Result sendPrize(@PathVariable Long id) {
		gameDialRecordService.sendPrize(id);
        return successCreated();
    }

}
