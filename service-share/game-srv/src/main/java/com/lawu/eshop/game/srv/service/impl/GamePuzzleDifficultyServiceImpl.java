package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.cache.constants.GameHardLevelEnum;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.GameDifficultyParam;
import com.lawu.eshop.common.constants.GameScoreLevelEnum;
import com.lawu.eshop.common.param.SecScoreParam;
import com.lawu.eshop.game.param.DifficultyInfoParam;
import com.lawu.eshop.game.srv.bo.GamePuzzleDifficultyBO;
import com.lawu.eshop.game.srv.bo.GamePuzzleMaxPointStartByDiffBO;
import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartByDiffBO;
import com.lawu.eshop.game.srv.converter.GamePuzzleDiffcultyConverter;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDO;
import com.lawu.eshop.game.srv.domain.GamePuzzleDifficultyDOExample;
import com.lawu.eshop.game.srv.mapper.GamePuzzleDifficultyDOMapper;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.GamePuzzleDifficultyService;
import com.lawu.framework.web.Result;

/**
 * @author zhangrc
 * @Description
 * @date 2018年3月10日
 */
@Service
public class GamePuzzleDifficultyServiceImpl implements GamePuzzleDifficultyService {

    @Autowired
    private GamePuzzleDifficultyDOMapper gamePuzzleDifficultyDOMapper;
    
    @Autowired
	private GameConfigCacheService gameConfigCacheService;

    /**
     * 根据困难级别获取信息
     *
     * @param level
     * @return
     */
    @Override
    public GamePuzzleDifficultyBO getPuzzleDifficultyByHardLevel(GameHardLevelEnum level) {
        GamePuzzleDifficultyDOExample example = new GamePuzzleDifficultyDOExample();
        example.createCriteria().andTypeEqualTo(level.getVal());
        List<GamePuzzleDifficultyDO> list = gamePuzzleDifficultyDOMapper.selectByExample(example);
        if (list.isEmpty()) return null;
        GamePuzzleDifficultyBO bo = GamePuzzleDiffcultyConverter.convertBO(list.get(0));
        return bo;
    }


    @Override
    public List<GamePuzzleDifficultyBO> getPuzzleDifficulty() {
        List<GamePuzzleDifficultyDO> list = gamePuzzleDifficultyDOMapper.selectByExample(null);
        List<GamePuzzleDifficultyBO> bolist = new ArrayList<>();
        for (GamePuzzleDifficultyDO gamePuzzleDifficultyDO : list) {
            GamePuzzleDifficultyBO bo = GamePuzzleDiffcultyConverter.convertBO(gamePuzzleDifficultyDO);
            bolist.add(bo);
        }

        return bolist;
    }

    @Override
    public GamePuzzleDifficultyBO getPuzzleDifficultyById(Long id) {
        GamePuzzleDifficultyDO game = gamePuzzleDifficultyDOMapper.selectByPrimaryKey(id);
        GamePuzzleDifficultyBO bo = GamePuzzleDiffcultyConverter.convertBO(game);
        return bo;
    }


	@Override
	public GamePuzzlePointStartByDiffBO getPuzzlePointStart(DifficultyInfoParam param) {
		
		Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO config =result.getModel();
		List<GameDifficultyParam> list =config.getDifficultys();
		GamePuzzlePointStartByDiffBO gamePuzzlePointStartByDiffBO = new GamePuzzlePointStartByDiffBO();
		for (GameDifficultyParam gameDifficultyParam : list) {
			List<SecScoreParam> params = gameDifficultyParam.getSecScore();
			if(param.getLevelEnum() == gameDifficultyParam.getLeverEnum()){
				Collections.sort(params, new Comparator<SecScoreParam>() {
		            @Override
		            public int compare(SecScoreParam o1, SecScoreParam o2) {
		                return o1.getSecond() - o2.getSecond();
		            }
		        });
		        Integer point = 0;
		        byte level=GameScoreLevelEnum.LEVEL_6.getValue();
				for (int i = 0; i < params.size(); i++) {
					if (param.getSecond() <= params.get(i).getSecond()) {
						point = params.get(i).getPoint();
						gamePuzzlePointStartByDiffBO.setScore(point);
						//level = params.get(i).getLevel().byteValue();
						gamePuzzlePointStartByDiffBO.setLevelEnum(GameScoreLevelEnum.getEnum(level));
						break;
					}

				}
			}
		}
		return gamePuzzlePointStartByDiffBO;
	}


	@Override
	public GamePuzzleMaxPointStartByDiffBO getPuzzleMaxPointLevel(GameHardLevelEnum levelEnum) {
		Result<GamePuzzleConfigCacheDTO> result = gameConfigCacheService.getGamePuzzleConfig();
		GamePuzzleConfigCacheDTO config =result.getModel();
		List<GameDifficultyParam> list =config.getDifficultys();
		GamePuzzleMaxPointStartByDiffBO gamePuzzlePointStartByDiffBO = new GamePuzzleMaxPointStartByDiffBO();
		for (GameDifficultyParam gameDifficultyParam : list) {
			List<SecScoreParam> params = gameDifficultyParam.getSecScore();
			if(levelEnum == gameDifficultyParam.getLeverEnum()){
				Collections.sort(params, new Comparator<SecScoreParam>() {
		            @Override
		            public int compare(SecScoreParam o1, SecScoreParam o2) {
		                return o1.getSecond() - o2.getSecond();
		            }
		        });
		        Integer point = 0;
		        byte level=GameScoreLevelEnum.LEVEL_6.getValue();
		        point = params.get(0).getPoint();
				gamePuzzlePointStartByDiffBO.setScore(point);
				//level = params.get(0).getLevel().byteValue();
				gamePuzzlePointStartByDiffBO.setLevelEnum(GameScoreLevelEnum.getEnum(level));
			}
		}
		return gamePuzzlePointStartByDiffBO;
	}
}
