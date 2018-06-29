package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.game.param.GameMindQuestionParam;
import com.lawu.eshop.game.param.MindAttendParam;
import com.lawu.eshop.game.query.GameMindQuestionQuery;
import com.lawu.eshop.game.srv.bo.GameMindQuestionBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionCategoryBO;
import com.lawu.eshop.game.srv.bo.GameMindQuestionListBO;
import com.lawu.eshop.game.srv.bo.GameQuestionResultBO;
import com.lawu.framework.core.page.Page;

/**
 * 题库
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public interface GameMindQuestionService {
	
	/**
	 * 保存题目
	 * @param param
	 */
	void saveGameMindQuestion(GameMindQuestionParam param);
	
	/**
	 * 禁用
	 * @param id
	 */
	void setGameMindQuestion(Long id);
	
	/**
	 * 题目列表
	 * @param query
	 * @return
	 */
	Page<GameMindQuestionBO> page(GameMindQuestionQuery query);
	
	/**
     * 生成参与记录已经返回题目
     * 
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月13日
     * @updateDate 2018年3月13日
     */
	GameQuestionResultBO getMindQuestionList(MindAttendParam param);
	
	
	/**
	 * 查询题目类型
	 * @return
	 */
	List<GameMindQuestionCategoryBO> getGameMindQuestionCategory();

	/**
	 *
	 * @param id 题目id
	 * @param rightAnswer 用户答题下标
	 * @return
	 */
	GameMindQuestionBO getQuestion(Long id,Integer rightAnswer);

	/**
	 * 根据配置的出题数, 获取随机的题目
	 *
	 * @param configCount 题目总数
	 * @param easyCount   简单数量
	 * @return 返回题目列表
	 */
	GameMindQuestionListBO getMindQuestions(Integer configCount, Integer easyCount);
	
	/**
	 * 同步题库到redis
	 * @return
	 */
	void rebuildQuestion();
	
	/**
	 * 
	 * 详情
	 * @param id
	 * @return
	 */
	GameMindQuestionBO get(Long id);

}
