package org.sugus.leetcodeAlgorithm;

import java.util.*;

public class Day20 {
    /**
     *给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int end = 0;
        int maxStep = 0, step = 0;
        for(int i = 0; i<len-1; i++){
            maxStep = Math.max(maxStep,nums[i] + i);
            if(i == end){
                end = maxStep;
                step++;
            }
        }
        return step;
    }
    /**
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     */
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> output = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for(int i : nums){
            output.add(i);
        }
        int len = nums.length;
        backtrack(len,output,res,0);
        return res;
    }
    public void backtrack(int len, List<Integer> output, List<List<Integer>> res,
                          int first){
        if(first == len){
            res.add(new ArrayList<>(output));
        }
        for(int i = first; i<len; i++){
            Collections.swap(output,first,i);
            backtrack(len,output,res,first+1);
            Collections.swap(output,first,i);
        }
    }

    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Set<Integer> col = new HashSet<>();
        Set<Integer> dig1 = new HashSet<>();
        Set<Integer> dig2 = new HashSet<>();
        int[] queens = new int[n];
        Arrays.fill(queens,-1);
        backtrack(res,col,dig1,dig2,queens,n,0);
        return res;
    }
    public void backtrack(List<List<String>> res, Set<Integer> col,
                          Set<Integer> digs1, Set<Integer> digs2, int[] queens,
                          int n, int row){
        if(n == row){
            List<String> board = createBoard(n,queens);
            res.add(board);
        }else {
            for (int i = 0; i < n; i++) {
                if(col.contains(i)){
                    continue;
                }
                int dig1 = row - i;
                if(digs1.contains(dig1)){
                    continue;
                }
                int dig2 = row + i;
                if(digs2.contains(dig2)){
                    continue;
                }
                queens[row] = i;
                col.add(i);
                digs1.add(dig1);
                digs2.add(dig2);
                backtrack(res,col,digs1,digs2,queens,n,row+1);
                queens[row] = -1;
                col.remove(i);
                digs1.remove(dig1);
                digs2.remove(dig2);
            }
        }
    }
    public List<String> createBoard(int n,int[] queens){
        List<String> board = new ArrayList<>();
        for(int i = 0; i<n; i++){
            char[] row = new char[n];
            Arrays.fill(row,'.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
    /**
     * n皇后2
     */
    public int totalNQueens(int n) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> dig1 = new HashSet<>();
        Set<Integer> dig2 = new HashSet<>();
        int[] queens = new int[n];
        Arrays.fill(queens,-1);
        int total = 0;
        total = backtrack(col,dig1,dig2,queens,n,0);
        return total;
    }
    public int backtrack( Set<Integer> col,
                          Set<Integer> digs1, Set<Integer> digs2, int[] queens,
                          int n, int row){
        int total = 0;
        if(n == row){
            return 1;
        }else {
            for (int i = 0; i < n; i++) {
                if(col.contains(i)){
                    continue;
                }
                int dig1 = row - i;
                if(digs1.contains(dig1)){
                    continue;
                }
                int dig2 = row + i;
                if(digs2.contains(dig2)){
                    continue;
                }
                queens[row] = i;
                col.add(i);
                digs1.add(dig1);
                digs2.add(dig2);
                total += backtrack(col,digs1,digs2,queens,n,row+1);
                queens[row] = -1;
                col.remove(i);
                digs1.remove(dig1);
                digs2.remove(dig2);
            }
        }
        return total;
    }
}
