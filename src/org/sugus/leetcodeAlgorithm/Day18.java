package org.sugus.leetcodeAlgorithm;

public class Day18 {
    /**
     * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for(int i = 0; i<len; i++){
            if(nums[i] < 0){
                nums[i] = len + 1;
            }
        }
        for(int i = 0; i<len; i++){
            int num = Math.abs(nums[i]);
            if(num < len){
                nums[num - 1] = -Math.abs(nums[num-1]);
            }
        }
        for(int i = 0; i<len; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return len + 1;
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     *
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     */

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        int len = lists.length;
        ListNode ans = null;
        for (int i = 0; i < len; i++) {
            ans = magic(ans,lists[i]);
        }
        return ans;
    }
    public ListNode magic(ListNode a, ListNode b){
        if(a == null || b == null){
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode aPtr = a, bPtr = b, tail = head;
        while (aPtr != null && bPtr != null){
            if(aPtr.val <= bPtr.val){
                tail.next = aPtr;
                aPtr = aPtr.next;
            }else{
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * f[x] = max(nums[x],f[x-1]+nums[x])
     * f[0] = nums[0]
     *
     */
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i<len; i++){
            dp[i] = Math.max(nums[i],dp[i-1] + nums[i]);
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 判断你是否能够到达最后一个位置。
     *
     * 跳跃游戏动态规划
     * dp[x] = (j to i) nums[j] + j >= iIndex && dp[j]
     * dp[0] = true
     */
    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[0] = true;

        for(int i = 1; i<nums.length; i++){
            for (int j = 0; j<i; j++){
                if (nums[j] + j >= i && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }
    /**
     * 跳跃游戏贪心算法
     */
    public boolean canJump2(int[] nums){
        int len = nums.length;
        int max = 0;
        for(int i = 0; i<len; i++){
            if(i <= max) {
                max = Math.max(max, i + nums[i]);
                if (max >= len - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     *
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     *
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/plus-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] plusOne(int[] digits) {
        int len = digits.length - 1;
        digits[len]  += 1;
        int round = 0;
        while (len >= 0){
            digits[len] += round;
            if(digits[len] >= 10){
                round = digits[len] / 10;
                digits[len] = digits[len] % 10;
                len--;
            }else {
                round = 0;
                break;
            }
        }
        if(round != 0){
            int[] newArr = new int[digits.length+1];
            newArr[0] = round;
            System.arraycopy(digits, 0, newArr, 1, newArr.length - 1);
            return newArr;
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,9};
        Day18 day = new Day18();
        day.plusOne(arr);
    }
}
