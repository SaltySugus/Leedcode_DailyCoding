package org.sugus.leetcodeAlgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day23 {
    /**
     *根据一棵树的中序遍历与后序遍历构造二叉树。
     *
     * 注意:
     * 你可以假设树中没有重复的元素
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> in_map = new HashMap<>();
        for(int i = 0; i<inorder.length; i++){
            in_map.put(inorder[i],i);
        }
        return createTree(in_map,0,inorder.length-1,postorder,inorder.length-1);
    }

    public TreeNode createTree(Map<Integer,Integer> in, int begin, int end, int[] postorder,int index){
        if(begin > end){
            return null;
        }
        int root_val = postorder[index];
        TreeNode root = new TreeNode(root_val);
        int pos = in.get(root_val);
        index--;
        root.right = createTree(in,pos+1,end,postorder, index);
        root.left = createTree(in,begin,pos-1,postorder, index);
        return root;
    }
    /**
     *
     *
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     *
     */
    public int minPathSum(int[][] grid) {
        int x = grid.length, y = grid[0].length;
        int[][] dp = new  int[x][y];
        dp[0][0] = grid[0][0];
        for(int i = 1; i<y; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for(int i = 1; i<x; i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for(int i = 1; i<x; i++){
            for(int j = 1; j<y; j++){
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1])+ grid[i][j];
            }
        }
        return dp[x-1][y-1];
    }
}
