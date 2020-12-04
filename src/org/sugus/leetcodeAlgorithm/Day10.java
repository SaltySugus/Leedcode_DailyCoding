package org.sugus.leetcodeAlgorithm;

public class Day10 {
    /**
     *给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while(i<n){
            if(nums[i] == val){
                nums[i] = nums[n-1];
                n--;
            }
            i++;
        }
        return n;
    }

    /**
     *实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 尝试KMP算法
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] getPrefixArr(String needle) {
        int[] prefixArr = new int[needle.length()];
        int j = -1;
        prefixArr[0] = j;
        for(int i = 1; i<needle.length(); i++){
            while(j>=0 && needle.charAt(i) != needle.charAt(j+1)){
                j = prefixArr[j];
            }
            if(needle.charAt(i) == needle.charAt(j+1)){
                j++;
            }
            prefixArr[i] = j;
        }
        return prefixArr;
    }
    public int strStrByKMP(String haystack, String needle){
        if (needle.length() == 0) {
            return 0;
        }
        int[] next = getPrefixArr(needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while(j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == (needle.length() - 1) ) {
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Day10 day10 = new Day10();
        String haystack = "hello";
        String needle = "ll";
        System.out.println(day10.strStrByKMP(haystack, needle));
    }

}
