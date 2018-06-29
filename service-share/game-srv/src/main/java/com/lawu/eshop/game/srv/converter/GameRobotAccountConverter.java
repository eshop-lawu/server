package com.lawu.eshop.game.srv.converter;

import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.common.dto.GameUserInfoDTO;
import com.lawu.eshop.game.srv.bo.GameUserInfoBO;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月22日
 * @updateDate 2018年5月22日
 */
public class GameRobotAccountConverter {
    
    private GameRobotAccountConverter() {
    }
    
    public static GameUserInfoDTO convert(GameUserInfoBO source) {
        if (source == null) {
            return null;
        }
        GameUserInfoDTO rtn = new GameUserInfoDTO();
        rtn.setHeadImg(source.getHeadImg());
        rtn.setNickName(source.getNickName());
        rtn.setRegionPath(source.getRegionPath());
        rtn.setUserNum(source.getUserNum());
        return rtn;
    }

    public static List<GameUserInfoDTO> convert(List<GameUserInfoBO> sources) {
        List<GameUserInfoDTO> rtn = new ArrayList<>();
        if (sources == null || sources.isEmpty()) {
            return rtn;
        }
        for (GameUserInfoBO item : sources) {
            rtn.add(convert(item));
        }
        return rtn;
    }

}
