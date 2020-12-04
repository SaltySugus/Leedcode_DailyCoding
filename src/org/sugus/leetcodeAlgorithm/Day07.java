package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 {
    /**
     * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的每个数字在每个组合中只能使用一次。
     *
     * 说明：
     *
     * 所有数字（包括目标数）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates){
            int size = freq.size();
            if(freq.isEmpty() || num != freq.get(size-1)[0]){
                freq.add(new int[]{num,1});
            }else{
                ++freq.get(size-1)[1];
            }
        }
        dfs(0,target);
        return ans;
    }

    public void dfs(int pos, int rest){
        if(rest == 0){
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if(pos == freq.size() || rest < freq.get(pos)[0]){
            return;
        }

        dfs(pos+1,rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
        }
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for(int a = 0; a<n; a++){
            if(a>0 && nums[a] == nums[a-1]){
                continue;
            }
            int c = n-1;
            int target = -nums[a];
            for (int b = a+1; b<n; b++){
                if(b>a+1 && nums[b] == nums[b-1]){
                    continue;
                }
                while(b<c && nums[b] + nums[c] > target){
                    c--;
                }
                if(b == c){
                    break;
                }
                if(nums[b] + nums[c] == target){
                    List<Integer> temp = new ArrayList();
                    temp.add(nums[a]);
                    temp.add(nums[b]);
                    temp.add(nums[c]);
                    ans.add(temp);
                }
            }
        }
        return ans;
    }

    /**
     *给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     *
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int aLen = nums1.length, bLen = nums2.length;
        int totalLen = aLen+bLen;
        double result = 0.0;
        if(totalLen%2 != 0){
            int mid = totalLen / 2;
            result = sortMid(nums1,nums2,mid+1);
        }else{
            int mid1 = totalLen / 2 - 1, mid2 = totalLen /2;
            result = (sortMid(nums1,nums2,mid1 + 1) + sortMid(nums1,nums2, mid2+1))/ 2.0;
        }
        return result;
    }
    public double sortMid(int[] nums1, int[] nums2, int k){
        int index1 = 0, index2 = 0;
        int len1 = nums1.length, len2 = nums2.length;
        while(true) {
            if (index1 == len1) {
                return nums2[index2+k-1];
            }
            if (index2 == len2) {
                return nums1[index1+k-1];
            }
            if (k == 1) {
                return Math.min(nums1[index1],nums2[index2]);
            }
            int half = k/2;
            int newIndex1 = Math.min(index1 + half,len1) - 1;
            int newIndex2 = Math.min(index2 + half,len2) - 1;
            int ele1 = nums1[newIndex1], ele2 = nums2[newIndex2];
            if(ele1 <= ele2){
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 +1;
            }else{
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 +1;
            }
        }
    }


    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/4sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for(int a = 0; a<len; a++){
            if(a>0 && nums[a] == nums[a-1]){
                continue;
            }
            for(int b = a+1; b<len; b++){
                if(b>a+1 && nums[b] == nums[b-1]){
                    continue;
                }
                int half = target - (nums[a] + nums[b]);
                int d = len - 1;
                for(int c = b+1; c<len; c++){
                    if(c>b+1 && nums[c] == nums[c-1]){
                        continue;
                    }
                    while(c < d && nums[c] + nums[d] > half){
                        d--;
                    }
                    if(c == d){
                        break;
                    }
                    if(nums[c] + nums[d] == half){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[a]);
                        list.add(nums[b]);
                        list.add(nums[c]);
                        list.add(nums[d]);
                        res.add(list);
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int a=100;
        int b=200;
        method(a,b);
/*      a=200
        b=200     */
    }

    private static void method(int a, int b) {
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

}
