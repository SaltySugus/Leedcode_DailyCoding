package org.sugus.leetcodeAlgorithm;

import java.util.ArrayDeque;
import java.util.Deque;

public class Day25 {
    /**
     * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     *
     * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
     *
     * 注意:
     * 不能使用代码库中的排序函数来解决这道题。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-colors
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void sortColors(int[] nums) {
        int p1 = 0, curr = 0, p2 = nums.length - 1;
        while (curr < p2) {
            if(nums[curr] == 0){
                int temp = nums[curr];
                nums[curr] = nums[p1];
                nums[p1] = temp;
                curr++;
                p1++;
            }else if(nums[curr] == 1){
                curr ++;
            }else if(nums[curr] == 2){
                int temp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2] = temp;
                p2--;
            }
        }
    }

    /**
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1){
            return heights[0];
        }
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(-1);
        for(int i = 0; i<heights.length; i++){
            while (stack.peekLast() != -1 && heights[i] < heights[stack.peekLast()]) {
                int curr = heights[stack.pollLast()];
                res = Math.max(res, (i - stack.peekLast() - 1) * curr);
            }
            stack.addLast(i);
        }
        while(!stack.isEmpty() && stack.peekLast() != -1){
            int curr = stack.pollLast();
            res = Math.max(res,
                    (heights.length - stack.peekLast() - 1)*heights[curr]);
        }
        return res;
    }

    public static void main(String[] args) {
        Day25 day = new Day25();
        int[] heights = {2,1,5,6,2,3};
        System.out.println(day.largestRectangleArea(heights));
    }
}
