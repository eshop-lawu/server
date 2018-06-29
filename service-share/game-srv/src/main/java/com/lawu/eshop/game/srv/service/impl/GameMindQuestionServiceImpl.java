package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.lawu.eshop.cache.constants.GameConfigStatusEnum;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.constants.GameQuestionLevelEnum;
import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionCategoryBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionListBO;
import com.lawu.eshop.game.srv.bo.GameQuestionResultBO;
import com.lawu.eshop.game.srv.converter.GameMindQuestionConverter;
import com.lawu.eshop.game.srv.domain.GameMindQuestionCategoryDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDOExample;
import com.lawu.eshop.game.srv.domain.GameMindQuestionDOExample.Criteria;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionCategoryDOMapper;
import com.lawu.eshop.game.srv.mapper.GameMindQuestionDOMapper;
import com.lawu.eshop.game.srv.mapper.extend.GameMindQuestionDOMapperExtend;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GameMindAttendRecordService;
import com.lawu.eshop.game.srv.service.GameMindCacheService;
import com.lawu.eshop.game.srv.service.GameMindQuestionService;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.framework.core.page.Page;
import com.lawu.framework.web.Result;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
@Service
public class GameMindQuestionServiceImpl implements GameMindQuestionService{
	
	@Autowired
	private GameMindQuestionDOMapper gameMindQuestionDOMapper;
	
	@Autowired
	private GameMindQuestionCategoryDOMapper gameMindQuestionCategoryDOMapper;

	
	@Autowired
	private GameMindAttendRecordService gameMindAttendRecordService;

	@Autowired
	private GameMindCacheService gameMindCacheService;

	@Autowired
	private GameConfigCacheService gameConfigCacheService;
	
	
	@Autowired
	private GameMindQuestionDOMapperExtend gameMindQuestionDOMapperExtend;
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveGameMindQuestion(GameMindQuestionParam param) {
		
		GameMindQuestionCategoryDO categoryDO = gameMindQuestionCategoryDOMapper.selectByPrimaryKey(param.getCategoryId());
		
		GameMindQuestionDO record = new GameMindQuestionDO();
		record.setCategoryId(param.getCategoryId());
		record.setCategoryName(categoryDO.getName());
		record.setTitle(param.getTitle());
		if (param.getAnswers() != null && !param.getAnswers().isEmpty()) {
			record.setAnswers(JSONObject.toJSONString(param.getAnswers()));
        }
		record.setRightAnswer(param.getRightAnswer());
		record.setGmtModified(new Date());
		record.setDifficultyLevel(param.getLevelEnum().getVal());
		record.setStatus(GameConfigStatusEnum.ENABLE.getVal());
		if(param.getId() > 0){
			record.setId(param.getId());
			gameMindQuestionDOMapper.updateByPrimaryKeySelective(record);
			return;
		}
		
		record.setQuestionNum(IdWorkerHelperImpl.generate(BizIdType.QUESTION));
		record.setGmtCreate(new Date());
		gameMindQuestionDOMapper.insertSelective(record);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void setGameMindQuestion(Long id) {
		GameMindQuestionDO record = new GameMindQuestionDO();
		record.setId(id);
		record.setStatus(GameConfigStatusEnum.DISABLE.getVal());
		record.setGmtModified(new Date());
		gameMindQuestionDOMapper.updateByPrimaryKeySelective(record);
		
	}

	@Override
	public Page<GameMindQuestionBO> page(GameMindQuestionQuery query) {
		GameMindQuestionDOExample example = new GameMindQuestionDOExample();
		Criteria criteria = example.createCriteria();
		if (query.getCategoryId() != null) {
			criteria.andCategoryIdEqualTo(query.getCategoryId());
		}
		if (StringUtils.isNotEmpty(query.getTitle())) {
			criteria.andTitleLike("%" + query.getTitle() + "%");
		}
		criteria.andStatusEqualTo(GameConfigStatusEnum.ENABLE.getVal());
		RowBounds rowBounds = new RowBounds(query.getOffset(), query.getPageSize());
		int count = (int) (gameMindQuestionDOMapper.countByExample(example));
		example.setOrderByClause("id desc");
		List<GameMindQuestionDO> list = gameMindQuestionDOMapper.selectByExampleWithRowbounds(example, rowBounds);

		Page<GameMindQuestionBO> page = new Page<>();
		page.setCurrentPage(query.getCurrentPage());
		page.setTotalCount(count);
		page.setRecords(GameMindQuestionConverter.convertGameMindQuestionBOS(list));
		return page;
	}
	
    @Override
    @Transactional(rollbackFor = Exception.class)
    public GameQuestionResultBO getMindQuestionList(MindAttendParam param) {
        Result<GameMindConfigDTO> config = gameConfigCacheService.getGameMindConfig();
        GameMindConfigDTO configBO = config.getModel();
        if (configBO == null) {
            return null;
        } else {
            GameMindQuestionListBO listBO = getMindQuestions(configBO.getQuestionCount(),configBO.getQuestionSimpleCount());
            if (listBO == null || listBO.getQuestionDOS().isEmpty()) {
                return null;
            }
            List<GameMindQuestionDO> questionDOS = listBO.getQuestionDOS();
            List<Long> questionIds = listBO.getQuestionIds();
            /*
             * 新增头脑PK游戏参与表记录 并且扣除积分
             */
            param.setQuestionIds(StringUtils.join(questionIds, ","));
			Long attendId = gameMindAttendRecordService.addAttendRecords(param);
            // 设置题目缓存对应参与ID
            gameMindCacheService.setGameMindQuestionCache(attendId.toString(), param.getQuestionIds());
            GameQuestionResultBO resultBO = new GameQuestionResultBO();
            resultBO.setAttendIds(attendId.toString());
            resultBO.setCountDown(configBO.getCountDown());
            resultBO.setQuestionBOS(GameMindQuestionConverter.converter(questionDOS));
			resultBO.setScore(totalScore(configBO.getSecScore(), questionIds.size(), configBO.getLastScoreMultiple()));
			resultBO.setSuccessScore(configBO.getSuccessScore());
            return resultBO;
        }
    }

	@Override
	public List<GameMindQuestionCategoryBO> getGameMindQuestionCategory() {
		List<GameMindQuestionCategoryDO> list = gameMindQuestionCategoryDOMapper.selectByExample(null);
		List<GameMindQuestionCategoryBO> categorys = new ArrayList<>();
		for (GameMindQuestionCategoryDO gameMindQuestionCategoryDO : list) {
			GameMindQuestionCategoryBO category = new GameMindQuestionCategoryBO();
			category.setId(gameMindQuestionCategoryDO.getId());
			category.setName(gameMindQuestionCategoryDO.getName());
			categorys.add(category);
		}
		return categorys;
	}

	@Override
	public GameMindQuestionBO getQuestion(Long id,Integer rightAnswer) {
		GameMindQuestionDO record = gameMindQuestionDOMapper.selectByPrimaryKey(id);
		GameMindQuestionBO questionBO = GameMindQuestionConverter.convertGameMindQuestionBO(record);
		if (StringUtils.isNotBlank(questionBO.getAnswers())) {
			List<String> list = JSONObject.parseArray(questionBO.getAnswers(), String.class);
			if (0 <= rightAnswer && rightAnswer < list.size()) {
				questionBO.setAnswerName(list.get(rightAnswer));
			}
		}
		return questionBO;
	}
	
    @Override
	public GameMindQuestionListBO getMindQuestions(Integer configCount, Integer easyCount) {
		GameMindQuestionListBO rtn = new GameMindQuestionListBO();
		List<GameMindQuestionDO> questionDOS = new ArrayList<>();
		List<Long> questionIds = new ArrayList<>();
		rtn.setQuestionIds(questionIds);
		rtn.setQuestionDOS(questionDOS);
		Result<List<Long>> listResult = gameMindCacheService.getMindCacheQuestionIds(configCount, easyCount);
		if (listResult == null || listResult.getModel().isEmpty()) {
			return rtn;
		}

        GameMindQuestionDOExample example = new GameMindQuestionDOExample();
        example.createCriteria().andIdIn(listResult.getModel());
		List<GameMindQuestionDO> questionDOSList = gameMindQuestionDOMapper.selectByExample(example);
        if (questionDOSList.isEmpty()) {
            return rtn;
        }
		Collections.shuffle(questionDOSList);
        rtn.setQuestionDOS(questionDOSList);
        for(int i = 0;i<questionDOSList.size();i++){
			questionIds.add(questionDOSList.get(i).getId());
		}
        rtn.setQuestionIds(questionIds);
        return rtn;
    }

	/**
	 * 获取题目总分数
	 *
	 * @param params            分数list
	 * @param questionLength    题目数量
	 * @param lastScoreMultiple 最后一题倍数
	 * @return 总分数
	 */
	private Integer totalScore(List<SecScoreParam> params, Integer questionLength,Integer lastScoreMultiple) {
		Collections.sort(params, new Comparator<SecScoreParam>() {
			@Override
			public int compare(SecScoreParam o1, SecScoreParam o2) {
				return o1.getSecond() - o2.getSecond();
			}
		});

		return params.get(0).getPoint() * (questionLength - 1 + lastScoreMultiple);
	}
	
	
	@Override
	 @Transactional(rollbackFor = Exception.class)
	public void rebuildQuestion() {
		gameMindCacheService.removeMindQuestion();
		int currentPage = 0;
		while (true) {
			currentPage++;
			RowBounds rowBounds = new RowBounds(1000 * (currentPage - 1), 1000);
			//简单
			List<String> list = gameMindQuestionDOMapperExtend.rebuildQuestion(GameQuestionLevelEnum.SIMPLE.getVal(),rowBounds);
			gameMindCacheService.setGameSimpleMindQuestionIds(list);
			
			//困难
			rowBounds = new RowBounds(1000 * (currentPage - 1), 1000);
			List<String> diffs = gameMindQuestionDOMapperExtend.rebuildQuestion(GameQuestionLevelEnum.DIFFICULTY.getVal(),rowBounds);
			gameMindCacheService.setGameDifficultyMindQuestionIds(diffs);
			
			if(list.isEmpty() && diffs.isEmpty()){
				break;
			}
			
		}
		
	}

	@Override
	public GameMindQuestionBO get(Long id) {
		GameMindQuestionDO record = gameMindQuestionDOMapper.selectByPrimaryKey(id);
		GameMindQuestionBO questionBO = GameMindQuestionConverter.convertGameMindQuestionBO(record);
		return questionBO;
	}
	
}
