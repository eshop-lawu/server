package com.lawu.eshop.activity.srv.servcie;

import java.util.List;

import com.lawu.eshop.activity.constants.PointLotteryActivityRecordStatusEnum;
import com.lawu.eshop.activity.param.PointLotteryActivityRecordParam;
import com.lawu.eshop.activity.query.PointLotteryActivityQueryParam;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendDetailBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityAttendRecordBO;
import com.lawu.eshop.activity.srv.bo.PointLotteryActivityRecordBO;
import com.lawu.eshop.activity.srv.domain.extend.PointLotteryRollView;
import com.lawu.framework.core.page.Page;

/**
 * 参与报名积分抽奖
 *
 * @author zhangrc
 * @Description
 * @date 2018年2月1日
 */
public interface PointLotteryActivityRecordService {

	/**
	 * 查询参与的抽奖列表
	 * 
	 * @param param
	 * @return
	 */
	Page<PointLotteryActivityAttendRecordBO> attendPrizePage(PointLotteryActivityRecordParam param);

	/**
	 * 分页查询当前活动的参与记录
	 * @param param 查询参数
	 * @return
	 * @author jiangxinjun
	 * @createDate 2018年2月1日
	 * @updateDate 2018年2月1日
	 */
	Page<PointLotteryActivityRecordBO> page(Long pointLotteryActivityId, PointLotteryActivityQueryParam param);

    /**
     * 最新20条开奖信息
     *
     * @return
     * @author meishuquan
     */
    List<PointLotteryRollView> listLatestLotteryInfo();

    /**
     * 统计活动参与次数
     *
     * @param pointLotteryActivityId
     * @param userNum
     * @return
     * @author meishuquan
     */
    Integer countPointLotteryActivityRecord(Long pointLotteryActivityId, String userNum);

	/**
	 * 根据id查询抽奖记录
	 *
	 * @param id
	 * @return
	 * @author meishuquan
	 */
	PointLotteryActivityRecordBO getPointLotteryActivityRecord(Long id);

	/**
	 * 更新抽奖活动状态
	 *
	 * @param id
	 * @param statusEnum
	 * @author meishuquan
	 */
	void updateLotteryStatus(Long id, PointLotteryActivityRecordStatusEnum statusEnum);
	
	
	/**
	 * 我的抽奖详情
	 * 
	 * @param userNum
	 * @param id 活动id
	 * @return
	 * @author zhangrc
	 */
	PointLotteryActivityAttendDetailBO getPointLotteryActivityAttendDetail(String userNum ,Long id);
	
	/**
	 * 我参与抽奖的次数
	 * @param userNum
	 * @return
	 * @author zhangrc
	 */
	Integer getAttendCount(String userNum);

}
