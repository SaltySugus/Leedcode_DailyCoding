package org.sugus.leetcodeAlgorithm;

import java.util.List;

public class Day12 {
    /**
     * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
     *
     * k 是一个正整数，它的值小于或等于链表的长度。
     *
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;
        while(head != null){
            ListNode tail = pre;
            for(int i=0; i<k; i++){
                tail = tail.next;
                if(tail == null){
                    return hair.next;
                }
            }
            ListNode temp = tail.next;
            ListNode[] reverser = reverser(head, tail);
            head = reverser[0];
            tail = reverser[1];
            tail.next = temp;
            pre.next = head;
            pre = tail;
            head = tail.next;
        }
        return hair.next;
    }

    public ListNode[] reverser (ListNode head,ListNode tail){
        ListNode pre = tail.next;
        ListNode curr = head;
        while(pre != tail){
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return new ListNode[]{tail,head};
    }

    /**
     *给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int i = 0;
        for(int j = 1; j<nums.length; j++){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     */
    public double myPow(double x, int n) {
        if(x == 0.0){
            return x;
        }
        long N = n;
        return N>0 ? quickMul(x,N) : 1.0 / quickMul(x,-N);
    }
    public double quickMul(double x, long n){
        if(n == 0){
            return 1.0;
        }
        double y = quickMul(x,n/2);
        return n % 2 == 0 ? y*y : y*y*x;
    }
}
