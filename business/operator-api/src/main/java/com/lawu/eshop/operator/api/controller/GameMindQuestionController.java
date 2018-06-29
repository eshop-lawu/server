package com.lawu.eshop.operator.api.controller;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawu.eshop.framework.web.impl.ResultCode;
import com.lawu.eshop.framework.web.impl.annotation.PageBody;
import com.lawu.eshop.game.constants.GameQuestionLevelEnum;
import com.lawu.eshop.game.dto.GameMindQuestionCategoryDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDTO;
import com.lawu.eshop.game.dto.GameMindQuestionDetailDTO;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.operator.api.OperatorApiConfig;
import com.lawu.eshop.operator.api.log.LogRecord;
import com.lawu.eshop.operator.api.service.GameMindQuestionService;
import com.lawu.eshop.operator.constants.LogTitleEnum;
import com.lawu.eshop.operator.constants.OperationTypeEnum;
import com.lawu.excel.util.ExcelImportRowCallback;
import com.lawu.excel.util.ExcelImportRowResult;
import com.lawu.excel.util.ExcelUtils;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.HttpCode;
import com.lawu.framework.web.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

/**
 * 题库管理
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@Api(tags = "gameMindQuestion")
@RestController
@RequestMapping(value = "gameMindQuestion/")
public class GameMindQuestionController extends BaseController{
	
	@Autowired
	private GameMindQuestionService gameMindQuestionService;
	
	@Autowired
    private OperatorApiConfig operatorApiConfig;
	
	private static Logger logger = LoggerFactory.getLogger(GameMindQuestionController.class);
	
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.ADD ,title = LogTitleEnum.GAME_MIND_QUESTION_ADD)
	@ApiOperation(value = "添加题目", notes = "添加题目（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindQuestion:save")
    @RequestMapping(value = {"saveGameMindQuestion"}, method = RequestMethod.POST)
	public Result saveGameMindQuestion(@RequestBody @ApiParam GameMindQuestionParam param) {
		Result result = gameMindQuestionService.saveGameMindQuestion(param);
		return successCreated(result);
	}
    
    
    @SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_MIND_QUESTION_DISABLE)
	@ApiOperation(value = "题目禁用", notes = "题目禁用（张荣成）", httpMethod = "PUT")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindQuestion:disable")
    @RequestMapping(value = {"setGameMindQuestion/{id}"}, method = RequestMethod.PUT)
	public Result setGameMindQuestion(@PathVariable @ApiParam(required = true, value = "题目id") Long id) {
		Result result = gameMindQuestionService.setGameMindQuestion(id);
		return successCreated(result);
	}
    
    @SuppressWarnings("unchecked")
	@ApiOperation(value = "题目列表", notes = "题目列表（张荣成）", httpMethod = "POST")
    @PageBody
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindQuestion:list")
    @RequestMapping(value = {"page"}, method = RequestMethod.POST)
	public Result<Page<GameMindQuestionDTO>> page(@RequestBody @ApiParam GameMindQuestionQuery query) {
    	Result<Page<GameMindQuestionDTO>> result = gameMindQuestionService.page(query);
		return successCreated(result);
	}
    
    
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "题目分类", notes = "题目分类（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = { "getGameMindQuestionCategory" }, method = RequestMethod.GET)
	public Result<List<GameMindQuestionCategoryDTO>> getGameMindQuestionCategory() {
		Result<List<GameMindQuestionCategoryDTO>> result = gameMindQuestionService.getGameMindQuestionCategory();
		return successGet(result);
	}
	
	
	@ApiOperation(value = "导入题目数据批量处理", notes = "导入题目数据批量处理。（张荣成）", httpMethod = "POST")
    @RequestMapping(value = "importExcel", method = RequestMethod.POST)
    @RequiresPermissions("gameMindQuestion:import")
    public Result importExcel(@RequestParam @ApiParam(required = true, value = "文件路径") String filePath ,@RequestParam @ApiParam(required = true, value = "类型id") Long categoryId) {
		GameMindQuestionParam param = new GameMindQuestionParam();
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(operatorApiConfig.getImageUrl() + filePath);
            URLConnection conn = url.openConnection();
            DataInputStream in = new DataInputStream(conn.getInputStream());
            List<ExcelImportRowResult> excelImportRowResults = ExcelUtils.importExcel(
                    in, new ExcelImportRowCallback() {
                        @Override
                        public ExcelImportRowResult checkAndSave(int row, List<Object> cellValues) {
                            ExcelImportRowResult result = new ExcelImportRowResult();
                            result.setRowNum(row);
                            result.setCellValues(cellValues);

                            param.setCategoryId(categoryId);
                            param.setTitle(cellValues.get(0).toString());
                            List<String> answers = new ArrayList<>();
                            answers.add(cellValues.get(1).toString());
                            answers.add(cellValues.get(2).toString());
                            if(cellValues.get(3) !=null){
                            	answers.add(cellValues.get(3).toString());
                            }
                            if(cellValues.get(4) !=null){
                            	answers.add(cellValues.get(4).toString());
                            }
                            param.setAnswers(answers);
                            param.setRightAnswer(Integer.parseInt(cellValues.get(5).toString()));
                            param.setLevelEnum(GameQuestionLevelEnum.getEnum(Byte.parseByte(cellValues.get(6).toString())));
                          
                            Result result1 = gameMindQuestionService.saveGameMindQuestion(param);
                            
                            result.setErr(false);
                            result.setBlockedErr(false);

                            if (ResultCode.SUCCESS != result1.getRet()) {
                                result.setErr(true);
                            }
                            return result;
                        }
                    });
            for (ExcelImportRowResult rowResult : excelImportRowResults) {
                sb.append(rowResult.getCellValues().get(0).toString()).append(",");
            }
            String errIds = StringUtils.isEmpty(sb.toString()) ? "" : sb.substring(0, sb.length() - 1);
            return successCreated(errIds);
        } catch (FileNotFoundException e) {
            logger.error("EXCEL文件没找到--------{}", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("处理异常----------{}", e.getMessage());
        }
        return successCreated(ResultCode.FAIL);
    }
	
	
	@SuppressWarnings("rawtypes")
    @LogRecord(type =OperationTypeEnum.UPDATE ,title = LogTitleEnum.GAME_MIND_QUESTION_CACHE)
	@ApiOperation(value = "更新题库缓存", notes = "更新题库缓存（张荣成）", httpMethod = "POST")
    @ApiResponse(code = HttpCode.SC_CREATED, message = "success")
    @RequiresPermissions("gameMindQuestion:rebuild")
    @RequestMapping(value = {"rebuildQuestion"}, method = RequestMethod.POST)
	public Result rebuildQuestion() {
		Result result = gameMindQuestionService.rebuildQuestion();
		return successCreated(result);
	}
	
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "题目分类", notes = "题目分类（张荣成）", httpMethod = "GET")
	@ApiResponse(code = HttpCode.SC_CREATED, message = "success")
	@RequestMapping(value = { "get/{id}" }, method = RequestMethod.GET)
	public Result<GameMindQuestionDetailDTO> get(@PathVariable @ApiParam(required = true, value = "题目id") Long id) {
		Result<GameMindQuestionDetailDTO> result = gameMindQuestionService.get(id);
		return successGet(result);
	}

}
