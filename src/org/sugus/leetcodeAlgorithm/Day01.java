package org.sugus.leetcodeAlgorithm;

import java.util.*;
import java.util.regex.Pattern;

public class Day01 {
    /**
     给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

     你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/two-sum
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    //哈希表法
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int flag = target - nums[i];
            if(map.containsKey(flag) && map.get(nums[i]) != i){
               return new int[]{i,map.get(flag)};
            }
        }
        return null;


    }
    //暴力法
    public int[] twoSum2(int[] nums, int target) {
       for(int i = 0;i<nums.length;i++){
           for (int j = i+1;j<nums.length;j++){
               if(nums[i] + nums[j] == target){
                   return new int[]{i,j};
               }
           }
       }
       return null;
    }



}
