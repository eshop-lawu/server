package com.lawu.eshop.game.srv.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lawu.eshop.game.srv.bo.GamePuzzlePointStartReturnBO;

public class GetPointAndStarUtil {


   /* public static void main(String[] args) {
        List<GamePuzzlePointStartReturnBO> rtn = new ArrayList<GamePuzzlePointStartReturnBO>();
        List<BigDecimal> scoreRateList = new ArrayList<BigDecimal>();
        scoreRateList.add(new BigDecimal("0.48"));
        scoreRateList.add(new BigDecimal("0.28"));
        scoreRateList.add(new BigDecimal("0.1"));
        scoreRateList.add(new BigDecimal("0.05"));
        scoreRateList.add(new BigDecimal("0"));
        List<Integer> starRateList = new ArrayList<Integer>();
        starRateList.add(1);
        starRateList.add(1);
        starRateList.add(1);
        starRateList.add(0);
        starRateList.add(0);
        BigDecimal totalScore = new BigDecimal(40);
        List<Integer> rankList = new ArrayList<Integer>();
        rankList.add(1);
        rankList.add(1);
        rankList.add(2);
        rankList.add(3);
        rankList.add(3);
        getScoreAndStar(rtn,0,rankList,scoreRateList,starRateList,totalScore);
        for (GamePuzzlePointStartReturnBO rc : rtn){
            System.out.println("积分："+rc.getPoint() + "，星星：" + rc.getStar());
        }
    }*/

    /**
     *
     * @param rtn 返回参数，用户所得积分和星星，与排名rankList顺序一直
     * @param index
     * @param rankList 排名
     * @param pointRateList 排名与积分，带序
     * @param starRateList 排名与星星，带序
     * @param totalScore 总参与积分
     */
    public static void getScoreAndStar(List<GamePuzzlePointStartReturnBO> rtn, int index, List<Integer> rankList, List<BigDecimal> pointRateList, List<Integer> starRateList, BigDecimal totalScore){
        if(!validateParam(rankList,pointRateList,starRateList)){
            System.out.println("参数错误！");
            return;
        }
        for (int i = index ; i < rankList.size() ; i++){
            if(rtn.size() == rankList.size()){
                break;
            }
            int repeatNum = getRepeatNum(rankList,rankList.get(i));
            if(repeatNum == 1){
            	GamePuzzlePointStartReturnBO rc = new GamePuzzlePointStartReturnBO(pointRateList.get(i).multiply(totalScore),starRateList.get(i));
                rtn.add(rc);//沒并列排名的情况

            } else if(repeatNum == rankList.size()) {

                //计算平均分
                BigDecimal rangeTotalScore = new BigDecimal(0);
                for (int j = 0 ; j < pointRateList.size() ; j++){
                    rangeTotalScore = rangeTotalScore.add(pointRateList.get(j));
                }
                BigDecimal rangeAvgScore = rangeTotalScore.multiply(totalScore).divide(new BigDecimal(repeatNum),6, BigDecimal.ROUND_DOWN);//并列排名平均

                //计算平均星星
                int rangeTotalStar = 0;
                for (int j = 0 ; j < starRateList.size() ; j++){
                    rangeTotalStar += starRateList.get(j).intValue();
                }
                rangeTotalStar = getAvgStar(rangeTotalStar);

                for (int j = 0 ; j < pointRateList.size() ; j++){
                	GamePuzzlePointStartReturnBO rc = new GamePuzzlePointStartReturnBO(rangeAvgScore,rangeTotalStar);
                    rtn.add(rc);//人数全并列
                }

            }else {
                //计算平均分
                BigDecimal rangTotalScore = new BigDecimal(0);
                int rangeTotalStar = 0;
                for (int j = i ; j < i + repeatNum ; j++){
                    rangTotalScore = rangTotalScore.add(pointRateList.get(j));
                    rangeTotalStar += starRateList.get(j).intValue();
                }
                BigDecimal rangeAvgScore = rangTotalScore.multiply(totalScore).divide(new BigDecimal(repeatNum),6, BigDecimal.ROUND_DOWN);//并列排名平均
                rangeTotalStar = getAvgStar(rangeTotalStar);

                for (int j = 1 ; j <= repeatNum ; j++){
                	GamePuzzlePointStartReturnBO rc = new GamePuzzlePointStartReturnBO(rangeAvgScore,rangeTotalStar);
                    rtn.add(rc);//并列排名的情况
                }
                getScoreAndStar(rtn,i+repeatNum,rankList,pointRateList,starRateList,totalScore);

            }
        }
    }

    /**
     * 校验数据
     * @param rankList
     * @param pointRateList
     * @param starRateList
     * @return
     */
    private static boolean validateParam(List<Integer> rankList, List<BigDecimal> pointRateList, List<Integer> starRateList) {
        if(rankList.size() != pointRateList.size() || rankList.size() != starRateList.size() || pointRateList.size() != starRateList.size()){
            return false;
        }
        return true;
    }

    /**
     * 获取星星
     * @param rangeTotalStar
     * @return
     */
    public static int getAvgStar(int rangeTotalStar){
        if(rangeTotalStar > 0){
            rangeTotalStar = 1;
        } else if(rangeTotalStar == 0){
            rangeTotalStar = 0;
        } else {
            rangeTotalStar = -1;
        }
        return rangeTotalStar;
    }

    /**
     * 获取重复数
     * @param list
     * @param rank
     * @return
     */
    public static int getRepeatNum(List<Integer> list,Integer rank){
        int n = 0;
        for (Integer r : list){
            if(rank.intValue() == r.intValue()){
                n++;
            }
        }
        return n;
    }

/*    static class RtnCommon {
        private BigDecimal point;
        private int star;

        public RtnCommon(BigDecimal point, int star) {
            this.point = point;
            this.star = star;
        }

        public BigDecimal getPoint() {
            return point;
        }

        public void setPoint(BigDecimal point) {
            this.point = point;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }
    }*/
}
