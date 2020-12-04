package org.sugus.leetcodeAlgorithm;

public class Day05 {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
     */
    public static String longestPalindrome(String s) {
        if (s.length() < 2){
            return s;
        }
        int len = s.length();
        int maxLen = 1;
        int begin = 0;

        boolean [][] chet = new boolean[len][len];
        char[] charArr = s.toCharArray();

        for (int i = 0; i < len; i++){
            chet[i][i] = true;
        }
        for (int j = 1; j<len; j++){
            for (int i = 0; i<len; i++){
                if(charArr[i] != charArr[j]){
                    chet[i][j] = false;
                }else if(j - i < 3){
                    chet[i][j] = true;
                }else {
                    chet[i][j] = chet[i + 1][j - 1];
                }
                if(chet[i][j] && j-i+1 > maxLen){
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }
        return s.substring(begin,begin+maxLen);
    }
}
