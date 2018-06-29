package com.lawu.eshop.cache.dto;

import java.util.List;

/**
 * @author lihj
 * @date 2018/3/27
 */
public class GamePuzzleCallBackCacheDTO {
    private List<GamePuzzleCacheDetail> detailList;

    public List<GamePuzzleCacheDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<GamePuzzleCacheDetail> detailList) {
        this.detailList = detailList;
    }
}
