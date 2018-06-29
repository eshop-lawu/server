package com.lawu.eshop.member.ws.event;

import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.framework.core.event.AsyncEvent;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
public class RichPowerTaskEvent extends AsyncEvent {

    private String num;

    private GameTypeEnum gameTypeEnum;

    public RichPowerTaskEvent(Object source, String num, GameTypeEnum gameTypeEnum) {
        super(source);
        this.num = num;
        this.gameTypeEnum = gameTypeEnum;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public GameTypeEnum getGameTypeEnum() {
        return gameTypeEnum;
    }

    public void setGameTypeEnum(GameTypeEnum gameTypeEnum) {
        this.gameTypeEnum = gameTypeEnum;
    }
}
