package com.lawu.eshop.game.srv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author meishuquan
 * @date 2018/3/13
 */
@Component
public class GameSrvConfig {

    @Value(value = "${lawu.game.room.page.number}")
    private Integer gameRoomPageNumber;

    @Value(value = "${lawu.game.puzzle.simple}")
    private Double gamePuzzleSimple;

    @Value(value = "${lawu.game.puzzle.commonly}")
    private Double gamePuzzleCommonly;

    @Value(value = "${lawu.game.puzzle.difficulty}")
    private Double gamePuzzleDifficulty;

    //答题延时时间
    @Value(value = "${lawu.game.answer.delay.time}")
    private Integer delayTime;

    public Integer getGameRoomPageNumber() {
        return gameRoomPageNumber;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public Double getGamePuzzleSimple() {
        return gamePuzzleSimple;
    }

    public void setGamePuzzleSimple(Double gamePuzzleSimple) {
        this.gamePuzzleSimple = gamePuzzleSimple;
    }

    public Double getGamePuzzleCommonly() {
        return gamePuzzleCommonly;
    }

    public void setGamePuzzleCommonly(Double gamePuzzleCommonly) {
        this.gamePuzzleCommonly = gamePuzzleCommonly;
    }

    public Double getGamePuzzleDifficulty() {
        return gamePuzzleDifficulty;
    }

    public void setGamePuzzleDifficulty(Double gamePuzzleDifficulty) {
        this.gamePuzzleDifficulty = gamePuzzleDifficulty;
    }
}
