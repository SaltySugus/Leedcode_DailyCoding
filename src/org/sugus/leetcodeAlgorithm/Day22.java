package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day22 {
    /**
     *一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for(int i = 0; i<n; i++){
            dp[0][i] = 1;
        }
        for(int i = 0; i<m; i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i<m ; i++){
            for(int j = 1; j<n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     *一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for(int i = 0; i < n; i++){
            if(obstacleGrid[0][i] != 1){
                dp[0][i] = 1;
            }else{
                dp[0][i] = 0;
                for(int j = i; j<n; j++){
                    dp[0][j] = 0;
                }
                break;
            }
        }
        for(int i = 0; i < m; i++){
            if(obstacleGrid[i][0] != 1){
                dp[i][0] = 1;
            }else{
                dp[i][0] = 0;
                for(int j = i; j<m; j++){
                    dp[j][0] = 0;
                }
                break;
            }
        }
        for(int i = 1; i<m; i++){
            for(int j = 1; j<n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }else{
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
    /**
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        if (head.next == null) return head;
        int len = 1;
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
            len++;
        }
        temp.next = head;
        int tail = len - k%len - 1;
        ListNode new_tail = head;

        for(int i = 0; i<tail;i++){
            new_tail = new_tail.next;
        }
        ListNode new_head = new_tail.next;
        new_tail.next = null;
        return new_head;
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int[] findMode(TreeNode node){
        List<Integer> res = new ArrayList<>();
        TreeNode pNode = node;
        if(node == null){
            return new int[]{};
        }
        if(node.left == null && node.right == null){
            return new int[]{node.val};
        }
        while(pNode != null){
            if(pNode.left == null){
                res.add(pNode.val);
                pNode = pNode.right;
            }else{
                TreeNode temp = pNode.left;
                while (temp.right != null && temp.right != pNode){
                    temp = temp.right;
                }
                if (temp.right == null){
                    temp.right = pNode;
                    pNode = pNode.left;
                }else{
                    res.add(pNode.val);
                    temp.right = null;
                    pNode = pNode.right;
                }
            }
        }
        int[] a = new int[res.size()];
        for(int i = 0; i<res.size(); i++){
            a[i] = res.get(i);
        }
        List<Integer> counts = new ArrayList<>();
        int base = a[0];
        int count = 1;
        int max_count = 1;
        counts.add(base);
        for(int i = 1; i<a.length; i++){
            if(a[i] == base){
                count++;
            }else{
                count = 1;
                base = a[i];
            }
            if(count == max_count){
                counts.add(base);
            }
            if (count > max_count) {
                max_count = count;
                counts.clear();
                counts.add(base);
            }
        }
        int[] b = new int[counts.size()];
        for(int i = 0; i<b.length; i++){
            b[i] = counts.get(i);
        }
        return b ;
    }

    public static void main(String[] args) {
        Day22 day = new Day22();
        TreeNode node1 = new TreeNode(15);
        TreeNode node2 = new TreeNode(10);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(20);
        TreeNode node7 = new TreeNode(25);
        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.left = node6; node3.right = node7;

        int[] mode = day.findMode(node1);
        for (int i: mode) {
            System.out.println(i);
        }

    }
}
