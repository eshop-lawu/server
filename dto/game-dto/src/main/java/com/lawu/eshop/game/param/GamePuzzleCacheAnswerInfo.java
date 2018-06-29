package com.lawu.eshop.game.param;

import java.util.List;

import com.lawu.eshop.cache.dto.GamePuzzleCacheAnswerDetailInfo;

/**
 * 答题信息表
 * @author lihj <br>
 * @date 2018/3/17
 */
public class GamePuzzleCacheAnswerInfo {

    private List<GamePuzzleCacheAnswerDetailInfo> list;

    public List<GamePuzzleCacheAnswerDetailInfo> getList() {
        return list;
    }

    public void setList(List<GamePuzzleCacheAnswerDetailInfo> list) {
        this.list = list;
    }
}
