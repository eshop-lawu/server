package com.lawu.eshop.game.srv.service;

import java.util.List;

import com.lawu.eshop.cache.param.GameMindAttendRecordResultParam;

/**
 * @author zhangyong
 * @date 2018/3/13.
 */
public interface GameMindAttendRecordResultService {
    void addAttendRecordsResult(List<GameMindAttendRecordResultParam> oldRecords);
}
