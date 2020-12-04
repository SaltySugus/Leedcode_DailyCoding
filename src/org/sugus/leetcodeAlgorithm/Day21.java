package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Day21 {
    /**
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     */
    public int[][] generateMatrix(int n) {
        int loop = n/2;
        int dx = 0;
        int dy = 0;
        int mid = n/2;
        int[][] res = new int[n][n];
        int offset = 1;
        int count = 0;
        while(loop != 0){
            int j = dx;
            int i = dy;
            for(j = dy; j < dy+n-offset; j++){
                res[dx][j] = count ++;
            }
            for(i = dx; i< dx+n-offset; i++){
                res[i][j] = count ++;
            }
            for(;j>dy;j--){
                res[i][j] = count ++;
            }
            for(;i>dx;i--){
                res[i][j] = count ++;
            }
            dx++;
            dy++;
            offset += 2;
            loop--;
        }
        if(n%2 != 0){
            res[mid][mid] = count;
        }
        return res;
    }

    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> order = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return order;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                order.add(matrix[top][column]);
            }
            for (int row = top + 1; row <= bottom; row++) {
                order.add(matrix[row][right]);
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    order.add(matrix[bottom][column]);
                }
                for (int row = bottom; row > top; row--) {
                    order.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null){
            return t2;
        }
        if(t2 == null){
            return t1;
        }
        TreeNode merge = new TreeNode(t1.val + t2.val);
        merge.left = mergeTrees(t1.left,t2.left);
        merge.right = mergeTrees(t1.right,t2.right);
        return merge;
    }


    public static void main(String[] args) {
        Day21 day = new Day21();
        int[][] a = {{2,3}};
        List<Integer> integers = day.spiralOrder(a);
        for (int i:
             integers) {
            System.out.println(i);
        }
    }
}
