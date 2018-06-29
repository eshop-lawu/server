package com.lawu.eshop.mall.srv.service;

import com.lawu.eshop.mall.param.SuggestionListParam;
import com.lawu.eshop.mall.param.SuggestionParam;
import com.lawu.eshop.mall.srv.bo.SuggestionBO;
import com.lawu.framework.core.page.Page;

/**
 * 意见反馈服务接口
 *
 * @author Sunny
 * @date 2017/3/24
 */
public interface SuggestionService {
	
	/**
	 * 保存意见反馈
	 * @param param
	 * 
	 * @return 返回生成的id
	 */
	Integer save(String userNum, SuggestionParam param);

	/**
	 * 查询意见反馈记录分页
	 * @param pageParam
	 * @return
	 */
    Page<SuggestionBO> getSuggestionList(SuggestionListParam pageParam);
    
    /**
     * 删除反馈意见
     * @param id
     */
    void delSuggestion(Long id);
}
