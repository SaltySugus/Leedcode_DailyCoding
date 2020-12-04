package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day16 {
    /**
     * 计算给定二叉树的所有左叶子之和。
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if(root == null){
            return 0;
        }
        if(root.left != null){
            if(root.left.right == null && root.left.left == null) {
                sum = root.left.val;
            }
            sum += sumOfLeftLeaves(root.left);
        }
        if(root.right != null){
           sum += sumOfLeftLeaves(root.right);
        }
        return sum;
    }

    /**
     *给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> sequence = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        dfs(candidates,0,target);
        return ans;
    }
    public void dfs(int[] candidates,int pos,int target){
        if(target == 0){
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if(pos == candidates.length || target < candidates[pos]){
            return;
        }

        dfs(candidates,pos+1,target);

        int times = target / candidates[pos];
        for(int i = 1; i<=times; i++){
            sequence.add(candidates[pos]);
            dfs(candidates,pos + 1, target - i * candidates[pos]);
        }
        for (int i = 1; i <= times; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }

    public static void main(String[] args) {
        Day16 day = new Day16();
        TreeNode treeRoot = new TreeNode(3);
        TreeNode tc1 = new TreeNode(9);
        TreeNode tc2 = new TreeNode(20);
        TreeNode tc3 = new TreeNode(15);
        TreeNode tc4 = new TreeNode(7);
        treeRoot.left = tc1; treeRoot.right = tc2;
        tc2.left = tc3; tc2.right = tc4;
        System.out.println(day.sumOfLeftLeaves(treeRoot));
    }
}
