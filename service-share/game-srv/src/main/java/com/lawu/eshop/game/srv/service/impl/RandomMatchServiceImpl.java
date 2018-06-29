package com.lawu.eshop.game.srv.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawu.eshop.cache.constants.CacheGameTypeEnum;
import com.lawu.eshop.cache.dto.GameCommonNumDTO;
import com.lawu.eshop.cache.dto.GameMatchResultDTO;
import com.lawu.eshop.cache.dto.GameMindConfigDTO;
import com.lawu.eshop.cache.dto.GamePuzzleConfigCacheDTO;
import com.lawu.eshop.cache.param.CheckCacheMatchParam;
import com.lawu.eshop.cache.param.ExitMatchQueueParam;
import com.lawu.eshop.cache.param.MatchingRobotParam;
import com.lawu.eshop.common.constants.EnableEnum;
import com.lawu.eshop.common.exception.DataNotExistException;
import com.lawu.eshop.common.exception.WrongOperationException;
import com.lawu.eshop.game.constants.ConnType;
import com.lawu.eshop.game.constants.GameRobotAccountStatusEnum;
import com.lawu.eshop.game.constants.GameRobotServerStatusEnum;
import com.lawu.eshop.game.srv.domain.GameRobotAccountDO;
import com.lawu.eshop.game.srv.domain.GameRobotAccountDOExample;
import com.lawu.eshop.game.srv.domain.GameRobotServerDO;
import com.lawu.eshop.game.srv.domain.GameRobotServerDOExample;
import com.lawu.eshop.game.srv.mapper.GameRobotAccountDOMapper;
import com.lawu.eshop.game.srv.mapper.GameRobotServerDOMapper;
import com.lawu.eshop.game.srv.service.GameCommonCacheService;
import com.lawu.eshop.game.srv.service.GameConfigCacheService;
import com.lawu.eshop.game.srv.service.RandomMatchService;
import com.lawu.eshop.idworker.client.impl.BizIdType;
import com.lawu.eshop.idworker.client.impl.IdWorkerHelperImpl;
import com.lawu.framework.web.BaseController;
import com.lawu.framework.web.Result;
import com.lawu.utils.HttpUtil;

@Service
public class RandomMatchServiceImpl extends BaseController implements RandomMatchService {
    
    private static final Logger logger = LoggerFactory.getLogger(RandomMatchServiceImpl.class);
    
    @Autowired
    private GameCommonCacheService gameCommonCacheService;
    
    @Autowired
    private GameConfigCacheService gameConfigCacheService;
    
    @Autowired
    private GameRobotAccountDOMapper gameRobotAccountDOMapper;
    
    @Autowired
    private GameRobotServerDOMapper gameRobotServerDOMapper;
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GameMatchResultDTO checkMatch(CheckCacheMatchParam param) {
        Result<GameMatchResultDTO> checkCacheMatchEatchotherResult =  gameCommonCacheService.checkCacheMatchEatchother(param);
        if (!isSuccess(checkCacheMatchEatchotherResult)) {
            throw new WrongOperationException(checkCacheMatchEatchotherResult.getMsg());
        }
        GameMatchResultDTO gameMatchResultDTO = checkCacheMatchEatchotherResult.getModel();
        
        /*
         * 如果没有匹配上,给定机器人账号
         * 1.匹配超时
         * 2.判断配置是否开启机器人
         */
        Result<GameMindConfigDTO> gameMindConfigResult = gameConfigCacheService.getGameMindConfig();
        if (!isSuccess(gameMindConfigResult)) {
            throw new WrongOperationException(gameMindConfigResult.getMsg());
        }
        Integer robotEffectiveTime =  gameMindConfigResult.getModel().getRobotEffectiveTime() * 1000;
        if (gameMatchResultDTO.getAttendNum() == null
                // 匹配时间大于配置时间
                && (gameMatchResultDTO.getMatchStartTime() != null
                    && System.currentTimeMillis() - gameMatchResultDTO.getMatchStartTime() > robotEffectiveTime)) {
            boolean isEnabled = false;
            if (CacheGameTypeEnum.MIND == param.getType()) {
                GameMindConfigDTO gameMindConfig = gameMindConfigResult.getModel();
                isEnabled = EnableEnum.ENABLED == gameMindConfig.getRobotStatus();
            } else if (CacheGameTypeEnum.PUZZLE == param.getType()) {
                Result<GamePuzzleConfigCacheDTO> getGamePuzzleConfigResult = gameConfigCacheService.getGamePuzzleConfig();
                if (!isSuccess(getGamePuzzleConfigResult)) {
                    throw new WrongOperationException(getGamePuzzleConfigResult.getMsg());
                }
                GamePuzzleConfigCacheDTO gamePuzzleConfig = getGamePuzzleConfigResult.getModel();
                isEnabled = EnableEnum.ENABLED == gamePuzzleConfig.getRobotStatus();
            }
            if (!isEnabled) {
                return gameMatchResultDTO;
            }
            GameRobotAccountDOExample gameRobotAccountDOExample = new GameRobotAccountDOExample();
            gameRobotAccountDOExample.createCriteria().andStatusEqualTo(GameRobotAccountStatusEnum.IDLE.getVal());
            RowBounds gameRobotAccountRowBounds = new RowBounds(0, 1);
            List<GameRobotAccountDO> accountList = gameRobotAccountDOMapper.selectByExampleWithRowbounds(gameRobotAccountDOExample, gameRobotAccountRowBounds);
            // 没有可用的机器人账号
            if (accountList.isEmpty()) {
                return gameMatchResultDTO;
            }
            GameRobotAccountDO gameRobotAccount = accountList.get(0);

            GameRobotServerDOExample gameRobotServerDOExample = new GameRobotServerDOExample();
            gameRobotServerDOExample.createCriteria().andStatusEqualTo(GameRobotServerStatusEnum.ENABLE.getVal());
            // 找出实例数量最少的服务
            gameRobotServerDOExample.setOrderByClause("instances asc");
            RowBounds gameRobotServerRowBounds = new RowBounds(0, 1);
            List<GameRobotServerDO> serverList = gameRobotServerDOMapper.selectByExampleWithRowbounds(gameRobotServerDOExample, gameRobotServerRowBounds);
            // 没有可用的机器人服务
            if (serverList.isEmpty()) {
                return gameMatchResultDTO;
            }
            //退出匹配队列
            ExitMatchQueueParam exitMatchQueueParam = new ExitMatchQueueParam();
            exitMatchQueueParam.setType(param.getType());
            exitMatchQueueParam.setKey(param.getKey());
            gameCommonCacheService.exitMatchQueue(exitMatchQueueParam);
            GameRobotServerDO gameRobotServer = serverList.get(0);


            // 把用户个人的信息和机器人信息放入缓存中
            MatchingRobotParam matchingRobotParam = new MatchingRobotParam();
            BizIdType bizIdType = BizIdType.PUZZLE;
            ConnType connType =  ConnType.PUZZLE;
            if (CacheGameTypeEnum.MIND == param.getType()) {
                bizIdType = BizIdType.MINDGAME;
                connType = ConnType.MINDPK;
            }
            String attendNum = IdWorkerHelperImpl.generate(bizIdType);
            matchingRobotParam.setAttendNum(attendNum);
            matchingRobotParam.setType(param.getType());
            matchingRobotParam.setUserNums(new ArrayList<>());
            matchingRobotParam.getUserNums().add(param.getKey());
            matchingRobotParam.getUserNums().add(gameRobotAccount.getUserNum());
            gameCommonCacheService.matchingRobot(matchingRobotParam);
            
            // 发送请求通知机器人上线
            Map<String, String> params = new HashMap<>();
            params.put("account", gameRobotAccount.getAccount());
            params.put("password", gameRobotAccount.getPassword());
            params.put("grouoNum", attendNum);
            params.put("connType", connType.name());
            params.put("serverId", gameRobotServer.getId().toString());
            HttpUtil.doPost(gameRobotServer.getServicePath() + "/gameRobot/joinGame", params);
            
            GameRobotAccountDO gameRobotAccountUpdateDO = new GameRobotAccountDO();
            gameRobotAccountUpdateDO.setId(gameRobotAccount.getId());
            gameRobotAccountUpdateDO.setStatus(GameRobotAccountStatusEnum.USING.getVal());
            gameRobotAccountUpdateDO.setServerId(gameRobotServer.getId());
            gameRobotAccountUpdateDO.setGmtModified(new Date());
            gameRobotAccountDOMapper.updateByPrimaryKeySelective(gameRobotAccountUpdateDO);
            
            GameRobotServerDO gameRobotServerUpdateDO = new GameRobotServerDO();
            gameRobotServerUpdateDO.setId(gameRobotServer.getId());
            gameRobotServerUpdateDO.setInstances(gameRobotServer.getInstances() + 1);
            gameRobotServerUpdateDO.setGmtModified(new Date());
            gameRobotServerDOMapper.updateByPrimaryKeySelective(gameRobotServerUpdateDO);
            
            List<GameCommonNumDTO> battleUserInfos = new ArrayList<>();
            GameCommonNumDTO userInfo = new GameCommonNumDTO();
            userInfo.setRoomMaster(true);
            userInfo.setUserNum(param.getKey());
            userInfo.setRobot(false);
            battleUserInfos.add(userInfo);
            GameCommonNumDTO robotInfo = new GameCommonNumDTO();
            robotInfo.setRoomMaster(false);
            robotInfo.setUserNum(gameRobotAccount.getUserNum());
            robotInfo.setRobot(true);
            battleUserInfos.add(robotInfo);
            gameMatchResultDTO.setAttendNum(attendNum);
            gameMatchResultDTO.setCommonInfo(battleUserInfos);
        }
        return gameMatchResultDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void releaseRobotResources(String robotUserNum) {
        GameRobotAccountDOExample example = new GameRobotAccountDOExample();
        example.createCriteria().andUserNumEqualTo(robotUserNum);
        List<GameRobotAccountDO> accounts = gameRobotAccountDOMapper.selectByExample(example);
        if (accounts.isEmpty()) {
            throw new DataNotExistException("机器人账号数据不存在");
        }
        GameRobotAccountDO account = accounts.get(0);
        GameRobotAccountDO gameRobotAccountUpdateDO = new GameRobotAccountDO();
        gameRobotAccountUpdateDO.setId(account.getId());
        gameRobotAccountUpdateDO.setStatus(GameRobotAccountStatusEnum.IDLE.getVal());
        gameRobotAccountUpdateDO.setGmtModified(new Date());
        gameRobotAccountDOMapper.updateByPrimaryKeySelective(gameRobotAccountUpdateDO);
        
        GameRobotServerDO gameRobotServer = gameRobotServerDOMapper.selectByPrimaryKey(account.getServerId());
        if (gameRobotServer == null) {
            throw new DataNotExistException("机器人服务数据不存在");
        }
        GameRobotServerDO gameRobotServerUpdateDO = new GameRobotServerDO();
        gameRobotServerUpdateDO.setId(gameRobotServer.getId());
        gameRobotServerUpdateDO.setInstances(gameRobotServer.getInstances() > 0 ? gameRobotServer.getInstances() - 1 : 0);
        gameRobotServerUpdateDO.setGmtModified(new Date());
        gameRobotServerDOMapper.updateByPrimaryKeySelective(gameRobotServerUpdateDO);
    }

}
