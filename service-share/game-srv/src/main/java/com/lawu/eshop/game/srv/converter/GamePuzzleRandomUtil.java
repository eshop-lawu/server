package com.lawu.eshop.game.srv.converter;

/**
 * @author lihj
 * @date 2018/4/3
 */
public class GamePuzzleRandomUtil {

    public static int percentageRandom(double rate0,double rate1,double rate2){
        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= rate0) {
            return 0;
        } else if (randomNumber >= rate0 / 100 && randomNumber <= rate0 + rate1) {
            return 1;
        } else if (randomNumber >= rate0 + rate1 && randomNumber <= rate0 + rate1 + rate2) {
            return 2;
        }
        return 0;
    }
}
