package org.sugus.leetcodeAlgorithm;

public class Day03 {

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     */

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        char[] c = s.toCharArray();
        int i = 0 , j = c.length-1;
        while(j>= i){
            if(c[i] != c[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(12331));
    }
}
