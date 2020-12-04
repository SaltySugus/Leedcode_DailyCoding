package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day06 {
    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/container-with-most-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static int maxArea(int[] height) {
        int result = 0;
        int i = 0;
        int j = height.length-1;
        while(i != j){
            int min = Math.min(height[i], height[j]);
            int temp = min * (j-i);
            if(temp > result){
                result = temp;
            }
            if(min == height[i]){
                i++;
            }else{
                j--;
            }
        }
        return result;
    }

    /**
     *给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     */
    public static String intToRoman(int num) {
        if(num == 0 || num > 3999){
            return null;
        }
        Map<Integer,String> romaMap = new HashMap<>();
        romaMap.put(1,"I");
        romaMap.put(4,"IV");
        romaMap.put(5,"V");
        romaMap.put(9,"IX");
        romaMap.put(10,"X");
        romaMap.put(40,"XL");
        romaMap.put(50,"L");
        romaMap.put(90,"XC");
        romaMap.put(100,"C");
        romaMap.put(400,"CD");
        romaMap.put(500,"D");
        romaMap.put(900,"CM");
        romaMap.put(1000,"M");
        //将对应数字进行数位拆分再按照规则转换
        StringBuilder str = new StringBuilder();
        List<Integer> numList = new ArrayList<>();
        int temp = 1;
        while(num > 0){
            numList.add(num % 10 * temp);
            num = num / 10;
            temp *= 10;
        }
        for(Integer i : numList){
            if(romaMap.containsKey(i)){
                str.insert(0,romaMap.get(i));
            }else if(i>0 && i<10){
                if(i<4){
                    for(int x = 0; x<i; x++){
                        str.insert(0,romaMap.get(1));
                    }
                }else{
                    for(int x = 5; x<i; x++){
                        str.insert(0,romaMap.get(1));
                    }
                    str.insert(0,romaMap.get(5));
                }
            }else if(i>10 && i< 100){
                if(i<40){
                    for(int x = 0; x<i; x+=10){
                        str.insert(0,romaMap.get(10));
                    }
                }else{
                    for(int x = 50; x<i; x+=10){
                        str.insert(0,romaMap.get(10));
                    }
                    str.insert(0,romaMap.get(50));
                }
            }else if(i>100 && i<1000){
                if(i<400){
                    for(int x = 0; x<i; x+=100){
                        str.insert(0,romaMap.get(100));
                    }
                }else{
                    for(int x = 500; x<i; x+=100){
                        str.insert(0,romaMap.get(100));
                    }
                    str.insert(0,romaMap.get(500));
                }
            }else {
                for(int x = 0; x<i; x+=1000){
                    str.insert(0,romaMap.get(1000));
                }
            }
        }
        return str.toString();
    }

    //贪心算法
    public static String intToRoman2(int num){
        int[] value = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romeNum = {"M","CM","D","CD","C","XC","L","XL","X","IX","V"
                ,"IV","I"};
        StringBuilder res = new StringBuilder();
        for(int i = 0; i<value.length && num >= 0; i++){
            while(value[i] <= num){
                res.append(romeNum[i]);
                num -= value[i];
            }
        }
        return res.toString();
    }

    /**
     * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
     */
    public int romanToInt(String s) {
        int preNum = getSimple(s.charAt(0));
        int sum = 0;
        int temp = 0;
        for(int i = 1; i<s.length(); i++){
            temp = getSimple(s.charAt(i));
            if(preNum < temp){
                sum -= preNum;
            }else{
                sum += preNum;
            }
            preNum = temp;
        }
        sum += preNum;
        return sum;
    }
    public int getSimple(char s){
        switch(s){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(intToRoman2(58));
    }
}
