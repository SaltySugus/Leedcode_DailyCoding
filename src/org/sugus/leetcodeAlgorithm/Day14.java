package org.sugus.leetcodeAlgorithm;

public class Day14 {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     */

    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++){
            prefix = compare(prefix, strs[i]);
            if(prefix.length() == 0){
                break;
            }
        }
        return prefix;
    }
    public String compare(String s1,String s2){
        int len = Math.min(s1.length(),s2.length());
        int index = 0;
        while(index < len && s1.charAt(index) == s2.charAt(index)){
            index ++;
        }
        return s1.substring(0,index);
    }

    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }
        int len = height.length;
        int[] left_max = new int[len];
        int[] right_max = new int[len];
        int ans = 0;
        left_max[0] = height[0];
        right_max[len - 1] = height[len - 1];
        for(int i = 1; i < len; i++){
            left_max[i] = Math.max(left_max[i-1],height[i]);
        }
        for(int i = len-2; i >= 0; i--){
            right_max[i] = Math.max(right_max[i+1],height[i]);
        }
        for(int i = 0; i<len; i++){
            ans += Math.min(left_max[i],right_max[i]) - height[i];
        }
        return ans;
    }

    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int search(int[] nums, int target) {
        int res = -1;
        int pos = 0;
        int len = nums.length;
        if(len == 0){
            return res;
        }
        if(len == 1){
           return nums[0] == target ? 0 : -1;
        }
        for(; pos < nums.length; pos++){
            if(pos > 0 && nums[pos] < nums[pos-1]){
                break;
            }
        }
        if(pos  == len){
            int left = 0;
            int right = pos-1;
            while(left <= right){
                int mid = (right + left) / 2;
                if (nums[mid] == target){
                    res = mid;
                    break;
                }
                if(target > nums[mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }else if(target > nums[len-1]){
            int left = 0;
            int right = pos-1;
            while(left <= right){
                int mid = (right + left) / 2;
                if (nums[mid] == target) {
                    res = mid;
                    break;
                }
                if(target > nums[mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }else{
            int left = pos;
            int right = len - 1;
            while(left <= right){
                int mid = (right + left) / 2;
                if (nums[mid] == target) {
                    res = mid;
                    break;
                }
                if(target > nums[mid]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3};
        Day14 day = new Day14();
        System.out.println(day.search(nums, 1));
    }
}
