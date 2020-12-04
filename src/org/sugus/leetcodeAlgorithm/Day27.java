package org.sugus.leetcodeAlgorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Day27 {
    /**
     * 给定一个二叉树，返回它的 后序 遍历。
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = root;
        while (root != null || !stack.isEmpty()){
            while(root != null){
                stack.addLast(root);
                root = root.left;
            }
            root = stack.pollLast();
            assert root != null;
            if(root.right == null || root.right == pre){
                res.add(root.val);
                pre = root;
                root = null;
            }else{
                stack.addLast(root);
                root = root.right;
            }
        }
        return res;
    }
}
