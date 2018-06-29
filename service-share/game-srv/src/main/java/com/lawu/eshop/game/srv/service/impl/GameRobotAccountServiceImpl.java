package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawu.eshop.game.srv.bo.GameUserInfoBO;
import com.lawu.eshop.game.srv.domain.GameRobotAccountDO;
import com.lawu.eshop.game.srv.domain.GameRobotAccountDOExample;
import com.lawu.eshop.game.srv.mapper.GameRobotAccountDOMapper;
import com.lawu.eshop.game.srv.service.GameRobotAccountService;

/**
 * 
 * @author lihj
 * @date 2018年5月14日
 */
@Service
public class GameRobotAccountServiceImpl implements GameRobotAccountService {

    @Autowired
    private GameRobotAccountDOMapper gameRobotAccountDOMapper;

    @Override
    public boolean checkMemberIsRobot(String num) {
        GameRobotAccountDOExample example = new GameRobotAccountDOExample();
        example.createCriteria().andUserNumEqualTo(num);
        long count = gameRobotAccountDOMapper.countByExample(example);
        if (count <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public List<GameUserInfoBO> findRobotUserInfo(List<String> userNums) {
        if (userNums == null || userNums.isEmpty()) {
            return null;
        }
        GameRobotAccountDOExample example = new GameRobotAccountDOExample();
        example.createCriteria().andUserNumIn(userNums);
        List<GameRobotAccountDO> gameRobotAccounts = gameRobotAccountDOMapper.selectByExample(example);
        List<GameUserInfoBO> rtn = new ArrayList<>();
        for (GameRobotAccountDO item : gameRobotAccounts) {
            GameUserInfoBO gameUserInfoBO = new GameUserInfoBO();
            gameUserInfoBO.setUserNum(item.getUserNum());
            // 已更新时间的时间戳作为随机数的种子
            Random random = new Random(item.getGmtModified().getTime());
            String[] headImgs = StringUtils.split(item.getHeadImg(), ",");
            String headImg = headImgs[random.nextInt(headImgs.length)];
            String[] nickNames = StringUtils.split(item.getNickname(), ",");
            String nickName = nickNames[random.nextInt(nickNames.length)];
            String[] regions = StringUtils.split(item.getRegion(), ",");
            String region = regions[random.nextInt(regions.length)];
            gameUserInfoBO.setHeadImg(headImg);
            gameUserInfoBO.setNickName(nickName);
            gameUserInfoBO.setRegionPath(region);
            rtn.add(gameUserInfoBO);
        }
        return rtn;
    }
    
}
