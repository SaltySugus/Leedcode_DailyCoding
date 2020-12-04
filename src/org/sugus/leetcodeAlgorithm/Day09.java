package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day09 {
    /**
     *将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(-1);
        ListNode head = pre;
        while(l1 !=  null && l2 != null){
            if(l1.val < l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return head.next;
    }

    /**
     * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> result = new ArrayList<>();
        if(root != null){
            queue.add(root);
        }else{
            return null;
        }
        while (!queue.isEmpty()){
            int temp = queue.size();
            double sum = 0;
            for(int i = 0; i<temp; i++){
                TreeNode node = queue.peek();
                queue.poll();
                assert node != null;
                sum += node.val;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            result.add(sum / temp);
        }
        return result;
    }

    public static void main(String[] args) {
        Day09 day09 = new Day09();
        String haystack = "mississippi";
        String needle = "issip";
        // int i = day09.strStrByKMP(haystack,needle);
        //System.out.println(i);
    }

}
