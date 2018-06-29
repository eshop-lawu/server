package com.lawu.eshop.game.query;

import com.lawu.framework.core.page.AbstractPageParam;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author meishuquan
 * @date 2018/3/16.
 */
public class GameDialRecordQuery extends AbstractPageParam {

    @ApiModelProperty(value = "转盘游戏id")
    private Long gameDialId;

    public Long getGameDialId() {
        return gameDialId;
    }

    public void setGameDialId(Long gameDialId) {
        this.gameDialId = gameDialId;
    }
}
