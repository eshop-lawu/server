package com.lawu.eshop.game.robot.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author lihj <br>
 * @date 2018/3/10
 */
public class RandomPicUtil {

    public static String getSuccessKey(Long gameId, int key, int c) {
        if(key==0){
            key=5;
        }
        int total =c*c;
        List<String> list1 = initList(total);
        List<String> list = new ArrayList<String>();// 乘除减
        String cheng = (gameId * key) + "";
        String chu =(gameId%key)+"";
        String sub ="";
        if(gameId>=key){
            sub=(gameId-key)==0?"1":(gameId-key)+"";
        }else{
            sub=Math.abs((key-gameId))+"";
        }
        if (cheng.length() > 3) {
            cheng = cheng.substring(cheng.length() - 3, cheng.length());
        }
        //处理除法
        if(chu.length()>3){
            chu = chu.substring(chu.length() - 3, chu.length());
        }
        //处理减法
        if(sub.length()>3){
            sub = sub.substring(sub.length() - 3, sub.length());
        }

        Set<String> set = new LinkedHashSet<>();
        for (int k = 0; k < cheng.length(); k++) {
            String str =cheng.substring(k, k + 1).equals("0")?"5":cheng.substring(k, k + 1);
            set.add(str);
            list1.remove(str);
        }
        for (int k = 0; k < chu.length(); k++) {
            String str =chu.substring(k, k + 1).equals("0")?"5":chu.substring(k, k + 1);
            set.add(str);
            list1.remove(str);
        }
        for (int k = 0; k < sub.length(); k++) {
            String str =sub.substring(k, k + 1).equals("0")?"5":sub.substring(k, k + 1);
            set.add(str);
            list1.remove(str);
        }
        while(list1.size()>0){
            if(list1.size()>=2){
                set.add(list1.get(0)+"");
                set.add(list1.get(list1.size()-1)+"");
                list1.remove(list1.get(0)+"");
                list1.remove(list1.get(list1.size()-1)+"");
            }else{
                set.add(list1.get(0)+"");
                list1.remove(list1.get(0)+"");
            }
        }
        String cutImgNum =getVal(set).toString();
        return cutImgNum.substring(1,cutImgNum.length()-1).replaceAll("\\s*", "");
    }
    private static List<String> getVal(Set<String> set) {
        List<String> lt =new ArrayList<String>();
        Iterator<String> it =set.iterator();
        while(it.hasNext()){
            lt.add(it.next());
        }
        return lt;
    }
    private static List<String> initList(int total) {
        List<String> list =new ArrayList<>();
        for(int i=1;i<=total;i++){
            list.add(i+"");
        }
        return list;
    }
    public static String getRandomCutPic(int c){
        int total =c*c;
        List<String> list = initList(total);
        Collections.shuffle(list,new Random());
        String cutImgNum =list.toString();
        return cutImgNum.substring(1,cutImgNum.length()-1).replaceAll("\\s*", "");
    }

}
