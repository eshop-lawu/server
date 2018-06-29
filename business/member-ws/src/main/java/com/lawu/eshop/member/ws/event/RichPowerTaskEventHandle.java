package com.lawu.eshop.member.ws.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lawu.eshop.activity.param.RichPowerTaskRecordParam;
import com.lawu.eshop.cache.constants.GameTypeEnum;
import com.lawu.eshop.cache.constants.PowerTaskTypeEnum;
import com.lawu.eshop.member.ws.channel.ChannelManager;
import com.lawu.eshop.member.ws.service.GameMindAttendRecordService;
import com.lawu.eshop.member.ws.service.GamePuzzleAttendRecordService;
import com.lawu.eshop.member.ws.service.RichPowerTaskRecordService;
import com.lawu.framework.core.event.AsyncEventHandle;
import com.lawu.framework.web.Result;

/**
 * @author meishuquan
 * @date 2018/5/3.
 */
@Component
public class RichPowerTaskEventHandle implements AsyncEventHandle<RichPowerTaskEvent> {

    @Autowired
    private RichPowerTaskRecordService richPowerTaskRecordService;

    @Autowired
    private GameMindAttendRecordService gameMindAttendRecordService;

    @Autowired
    private GamePuzzleAttendRecordService gamePuzzleAttendRecordService;

    @Override
    public void execute(RichPowerTaskEvent event) {
        RichPowerTaskRecordParam taskRecordParam = new RichPowerTaskRecordParam();
        taskRecordParam.setType(PowerTaskTypeEnum.GAME);

        if (event.getGameTypeEnum() == GameTypeEnum.MIND) {
            String groupNum = ChannelManager.getGroupNum(event.getNum());
            Result<List<String>> result = gameMindAttendRecordService.getAttendRecordUserNums(groupNum);
            if (result.getModel().isEmpty()) {
                return;
            }
            for (String userNum : result.getModel()) {
                taskRecordParam.setMemberNum(userNum);
                richPowerTaskRecordService.saveRichPowerTaskRecord(taskRecordParam);
            }
        } else if (event.getGameTypeEnum() == GameTypeEnum.PUZZLE) {
            Result<List<String>> result = gamePuzzleAttendRecordService.getAttendRecordUserNums(event.getNum());
            if (result.getModel().isEmpty()) {
                return;
            }
            for (String userNum : result.getModel()) {
                taskRecordParam.setMemberNum(userNum);
                richPowerTaskRecordService.saveRichPowerTaskRecord(taskRecordParam);
            }
        }
    }

}
