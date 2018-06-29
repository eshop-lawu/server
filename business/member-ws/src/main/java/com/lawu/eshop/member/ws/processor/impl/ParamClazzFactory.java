package com.lawu.eshop.member.ws.processor.impl;

import com.lawu.eshop.game.constants.ConnType;
import com.lawu.eshop.game.param.*;
import com.lawu.eshop.member.ws.param.GameMsgType;

/**
 * 参数类型工厂
 * @author Leach
 * @date 2018/3/14
 */
public class ParamClazzFactory {

    public static Class getParamClass(ConnType connType, GameMsgType msgType) {
        switch (connType) {
            case MINDPK:
                return getMindpkParamClass(msgType);
            case PUZZLE:
                return getPuzzleParamClass(msgType);
        }
        return null;
    }

    /**
     * 头脑pk游戏参数类型
     * @param msgType
     * @return
     */
    private static Class getMindpkParamClass(GameMsgType msgType) {
        switch (msgType) {
            case ONLINE:
                return MindPkOnlineParam.class;

            case READY:
                return MindPkReadyParam.class;

            case START:
                return MindPkStartParam.class;

            case SUBMIT:
                return MindPkSubmitParam.class;

            case REJECT:
                return MindPkRejectParam.class;

            case ABANDON:
                return MindPkAbandonParam.class;

            case TIMEOUT:
                return MindPkSubmitParam.class;
        }
        return null;
    }

    /**
     * 拼图游戏参数类型
     * @param msgType
     * @return
     */
    private static Class getPuzzleParamClass(GameMsgType msgType) {
        switch (msgType) {
            case ONLINE:
                return PuzzlePkOnlineParam.class;

            case READY:
                return MindPkReadyParam.class;
            case CHECK:
            	return GamePuzzleCheckParam.class;
            case START:
                return PuzzlePKStartParam.class;

            case SUBMIT:
                return PuzzlePKSubmitParam.class;

            case REJECT:
                return PuzzlePKRejectParam.class;

            case ABANDON:
                return PuzzlePkAbandonParam.class;

            case TIMEOUT:
                return null;
        }
        return null;
    }
}
