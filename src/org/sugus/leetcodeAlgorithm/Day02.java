package org.sugus.leetcodeAlgorithm;

import java.util.*;

public class Day02 {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * a b c a b c b b
     * a a b
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int length = 1;
        if ("".equals(s)){
            return 0;
        }
        for (int i = 0 ;i < s.length(); i++){
            for(int j = i;j < s.length(); j++) {
                if (set.size() == 0) {
                    set.add(s.charAt(j));
                } else if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j));
                } else {
                    if (set.size() > length) {
                        length = set.size();
                    }
                    set.clear();
                    break;
                }
            }
        }
        return length;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     */
    public static int reverse(int x) {
        boolean flag = false;
        if(x<0){
            long l = -(long)x;
            flag = true;
            if(l > Integer.MAX_VALUE){
                return  0;
            }else{
                x = -x;
            }
        }
        String str = String.valueOf(x);
        str = new StringBuffer(str).reverse().toString();
        long l = Long.parseLong(str);
        if(flag){
            l = -l;
        }
        if(l > Integer.MAX_VALUE || l < Integer.MIN_VALUE){
            return  0;
        }else{
            return (int)l;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }
}
