package com.lawu.eshop.game.srv.bo;

import java.util.List;

import com.lawu.eshop.game.srv.domain.GameMindQuestionDO;

/**
 * @author zhangyong
 * @date 2018/3/16.
 */
public class GameMindQuestionListBO {

    private List<GameMindQuestionDO> questionDOS;

    private List<Long> questionIds;

    public List<GameMindQuestionDO> getQuestionDOS() {
        return questionDOS;
    }

    public void setQuestionDOS(List<GameMindQuestionDO> questionDOS) {
        this.questionDOS = questionDOS;
    }

    public List<Long> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Long> questionIds) {
        this.questionIds = questionIds;
    }
}
