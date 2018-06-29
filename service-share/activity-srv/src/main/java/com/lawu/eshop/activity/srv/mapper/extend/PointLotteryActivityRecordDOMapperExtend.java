package com.lawu.eshop.activity.srv.mapper.extend;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.lawu.eshop.activity.srv.domain.extend.PointLotteryActivityRecordDOExtend;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;

/**
 * 参与抽奖记录
 * 
 * @Description
 * @author zhangrc
 * @date 2018年2月1日
 */
public interface PointLotteryActivityRecordDOMapperExtend {
	
	/**
	 * 参与的抽奖列表
	 * 
	 * @param userNum
	 * @param rowBounds
	 * @return
	 */
	List<PointLotteryActivityRecordDOExtend> attendPrizePage(String userNum, RowBounds rowBounds);

	/**
	 * 查询记录条数
	 * @param userNum
	 * @return
	 */
	int selectCount(String userNum);

	/**
	 * 最新20条开奖信息
	 *
	 * @return
	 * @author meishuquan
	 */
	List<PointLotteryRollView> listLatestLotteryInfo();

}
