package com.lawu.eshop.game.robot.param;

import com.lawu.eshop.game.robot.constants.ConnType;

/**
 * 加入游戏参数
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public class JoinGameParam {
    
    /**
     * 账号
     */
    private String account;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 游戏类型
     */
    private ConnType connType;
    
    /**
     * 分组编号
     */
    private String grouoNum;
    
    /**
     * 当前服务id(用于释放实例数量)
     */
    private Integer serverId;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ConnType getConnType() {
        return connType;
    }

    public void setConnType(ConnType connType) {
        this.connType = connType;
    }

    public String getGrouoNum() {
        return grouoNum;
    }

    public void setGrouoNum(String grouoNum) {
        this.grouoNum = grouoNum;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
    
}
