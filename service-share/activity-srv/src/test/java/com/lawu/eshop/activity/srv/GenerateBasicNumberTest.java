package com.lawu.eshop.activity.srv;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

public class GenerateBasicNumberTest {
    
    @Test
    public void generateBasicNumber() {
        BigDecimal shanghaiCompositeIndex = BigDecimal.valueOf(3168.96);
        BigDecimal shenzhenComponentIndex = BigDecimal.valueOf(10631.12);
        Long count = 229L;
        /*
         * 计算基数
         * 1.开奖日收盘时的上证指数 × 每日收盘时的深证成指 × 10000 = 12位数。（指数以证交所公布数字为准）；
         * 2.此12位数的前6位数字与后6位数字调换位置，倒序排列后（如首位是0，则直接抹去），再除以活动结束时的参与人数（每个抽奖号为一个人数），得到的余数加1即为获奖号码
         */
        String componentIndex = shanghaiCompositeIndex.multiply(shenzhenComponentIndex).multiply(new BigDecimal(10000)).setScale(0).toString();
        // 交换数值的前后部分
        int median = componentIndex.length() / 2;
        int remainder = componentIndex.length() % 2;
        String leftPart = componentIndex.substring(0, median);
        String rightPart = componentIndex.substring(componentIndex.length() - median);
        componentIndex = rightPart.concat(remainder != 0 ? String.valueOf(componentIndex.charAt(median + remainder)) : "").concat(leftPart);
        // 倒序排列
        StringBuilder reverseNum = new StringBuilder();
        for (int i = componentIndex.length() - 1; i >= 0; i--) {
            reverseNum.append(componentIndex.charAt(i));
        }
        // 计算中奖基数
        Integer lotteryBaseNum = new BigDecimal(reverseNum.toString()).remainder(new BigDecimal(count)).add(new BigDecimal(1)).intValue();
        System.out.println(lotteryBaseNum);
    }
    
    @Ignore
    @Test
    public void batchGenerateBasicNumber() {
        Map<Integer, Integer> lotteryBaseNumMap = new LinkedHashMap<>();
        for (int j = 1; j <= 300; j++) {
            BigDecimal shanghaiCompositeIndex = BigDecimal.valueOf(3168.96);
            BigDecimal shenzhenComponentIndex = BigDecimal.valueOf(10631.12);
            Long count = Long.valueOf(j);
            /*
             * 计算基数
             * 1.开奖日收盘时的上证指数 × 每日收盘时的深证成指 × 10000 = 12位数。（指数以证交所公布数字为准）；
             * 2.此12位数的前6位数字与后6位数字调换位置，倒序排列后（如首位是0，则直接抹去），再除以活动结束时的参与人数（每个抽奖号为一个人数），得到的余数加1即为获奖号码
             */
            String componentIndex = shanghaiCompositeIndex.multiply(shenzhenComponentIndex).multiply(new BigDecimal(10000)).setScale(0).toString();
            // 交换数值的前后部分
            int median = componentIndex.length() / 2;
            int remainder = componentIndex.length() % 2;
            String leftPart = componentIndex.substring(0, median);
            String rightPart = componentIndex.substring(componentIndex.length() - median);
            componentIndex = rightPart.concat(remainder != 0 ? String.valueOf(componentIndex.charAt(median + remainder)) : "").concat(leftPart);
            // 倒序排列
            StringBuilder reverseNum = new StringBuilder();
            for (int i = componentIndex.length() - 1; i >= 0; i--) {
                reverseNum.append(componentIndex.charAt(i));
            }
            // 计算中奖基数
            Integer lotteryBaseNum = new BigDecimal(reverseNum.toString()).remainder(new BigDecimal(count)).add(new BigDecimal(1)).intValue();
            System.out.println(lotteryBaseNum);
            if (!lotteryBaseNumMap.containsKey(lotteryBaseNum)) {
                lotteryBaseNumMap.put(lotteryBaseNum, 1);
            }
            lotteryBaseNumMap.put(lotteryBaseNum, lotteryBaseNumMap.get(lotteryBaseNum)+1);
        }
        List<Map.Entry<Integer, Integer>> lotteryBaseNumEntryList = new ArrayList<>(lotteryBaseNumMap.entrySet());
        lotteryBaseNumEntryList.sort((o1, o2) -> {
            return o2.getValue().compareTo(o1.getValue());
        });
        lotteryBaseNumEntryList.forEach((entry) -> {
            System.out.println(entry.getKey() + "------------->" + entry.getValue());
        });
    }
    
}
