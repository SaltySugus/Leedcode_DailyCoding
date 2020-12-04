package org.sugus.leetcodeAlgorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Day15 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     */
    public boolean isValid(String s) {
        int len = s.length();
        if(("").equals(s) || len % 2 != 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');
        for (int i = 0; i<len; i++){
            char a = s.charAt(i);
            if(map.containsKey(a)){
                stack.push(map.get(a));
            }else{
                if(!stack.isEmpty() && stack.peek() == a){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    /**
     *实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 2;
        while (i >= 0 && nums[i+1] <= nums[i] ){
            i--;
        }
        if(i >= 0){
            int j = len - 1;
            while(j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }
    public void reverse(int[] nums, int start){
        int i = start, j = nums.length - 1;
        while(i < j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     *给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int[] searchRange(int[] nums, int target) {
        int[] range = {-1,-1};
        int leftRange = search(nums,target,true);
        if(leftRange == nums.length || nums[leftRange] != target){
            return range;
        }
        range[0] = leftRange;
        range[1] = search(nums,target,false) - 1;
        return range;
    }
    public int search(int[] nums, int target, boolean left){
        int l = 0, r = nums.length;
        while(l < r){
            int mid = (l + r) / 2;
            if (nums[mid] > target || (left && target == nums[mid])){
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
    public static void main(String[] args) {
        Day15 day = new Day15();
        System.out.println(day.isValid("(){}[]"));
    }
}
