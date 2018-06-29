package com.lawu.eshop.game.srv.mapper.extend;

import com.lawu.eshop.game.srv.domain.extend.DeductStarDOView;
import com.lawu.eshop.game.srv.domain.extend.FreeCountDOView;

/**
 * 
 * @Description
 * @author zhangrc
 * @date 2018年3月12日
 */
public interface GameAccountDOMapperExtend {
	
	void shareMindAttendCount(FreeCountDOView view);
	
	
	void sharePuzzleAttendCount(FreeCountDOView view);
	
	
	void shareDialAttendCount(FreeCountDOView view);
	
	
	void deductStar(DeductStarDOView view);


	void addStar(DeductStarDOView view);

    void reduceFreeCount(String userNum);

	void accountStarReset();


	void reduceMindFree(FreeCountDOView view);


	void reducePuzzleFree(FreeCountDOView view);


	void reduceDialFree(FreeCountDOView view);


	void mindAccountFreeCountReset(Integer freeCount);


	void puzzleAccountFreeCountReset(Integer freeCount);


	void dialAccountFreeCountReset(Integer freeCount);


	int getMindFreeCount(String userNum);
	
	int getPuzzleFreeCount(String userNum);
	
	int getDialFreeCount(String userNum);
}
