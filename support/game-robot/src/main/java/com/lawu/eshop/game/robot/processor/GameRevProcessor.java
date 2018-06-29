package com.lawu.eshop.game.robot.processor;

/**
 * 
 * @author jiangxinjun
 * @createDate 2018年5月11日
 * @updateDate 2018年5月11日
 */
public interface GameRevProcessor {
    
    /**
     * 游戏上线
     * 
     * @author jiangxinjun
     * @createDate 2018年5月11日
     * @updateDate 2018年5月11日
     */
    void online(String userNum);
    
    /**
     * 开始游戏
     * @param param
     * @return
     * @author jiangxinjun
     * @createDate 2018年5月10日
     * @updateDate 2018年5月10日
     */
    void start(String userNum, Object model);

    /**
     * 提交成绩
     * @param model
     * @author jiangxinjun
     * @createDate 2018年5月10日
     * @updateDate 2018年5月10日
     */
    void submit(String userNum, Object model);

}
