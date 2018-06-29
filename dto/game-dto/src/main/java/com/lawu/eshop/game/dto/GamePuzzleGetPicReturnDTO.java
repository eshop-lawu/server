package com.lawu.eshop.game.dto;

import java.util.List;

/**
 * 根据困难程度出图返回结果
 *
 * @author lihj <br>
 * @date 2018/3/10
 */
public class GamePuzzleGetPicReturnDTO {

	private List<GamePuzzleGetPicDTO> puzzleInfo;
	
	public List<GamePuzzleGetPicDTO> getPuzzleInfo() {
		return puzzleInfo;
	}

	public void setPuzzleInfo(List<GamePuzzleGetPicDTO> puzzleInfo) {
		this.puzzleInfo = puzzleInfo;
	}
	
}
