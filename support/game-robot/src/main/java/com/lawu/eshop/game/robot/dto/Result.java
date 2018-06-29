package com.lawu.eshop.game.robot.dto;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月10日
 * @updateDate 2018年5月10日
 */
public class Result<T> {

    /**
     * 返回码
     */
    private Integer ret;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 调试信息
     */
    private String debug;

    /**
     * 返回的数据
     */
    private T model;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
