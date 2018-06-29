package com.lawu.eshop.game.srv.bo;

import java.util.List;

/**
 * 拼图返回游戏加积分与加星星
 * @author lihj
 * @Date 2018年3月20日
 */
public class GamePuzzlePointStarBO {

	private List<GamePuzzlePointStarDetail> pointDetail;

	public List<GamePuzzlePointStarDetail> getPointDetail() {
		return pointDetail;
	}

	public void setPointDetail(List<GamePuzzlePointStarDetail> pointDetail) {
		this.pointDetail = pointDetail;
	}
	
}
