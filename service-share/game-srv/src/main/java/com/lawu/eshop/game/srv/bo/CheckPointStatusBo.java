package com.lawu.eshop.game.srv.bo;

/**
 * @author lihj
 * @date 2018/3/27
 */
public class CheckPointStatusBo {

    /**
     * 当前用户是否扣除成功
     */
    /*private boolean checkPoint;*/

    /**
     * 是否有等待扣除状态
     */
    private boolean waitDepoint;
    /**
     * 所有人是否扣除成功
     */
    private boolean allUserCheckPoint;

 /*   public boolean getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(boolean checkPoint) {
        this.checkPoint = checkPoint;
    }
*/
    public boolean getAllUserCheckPoint() {
        return allUserCheckPoint;
    }

    public void setAllUserCheckPoint(boolean allUserCheckPoint) {
        this.allUserCheckPoint = allUserCheckPoint;
    }

    public boolean getWaitDepoint() {
        return waitDepoint;
    }

    public void setWaitDepoint(boolean waitDepoint) {
        this.waitDepoint = waitDepoint;
    }
}
