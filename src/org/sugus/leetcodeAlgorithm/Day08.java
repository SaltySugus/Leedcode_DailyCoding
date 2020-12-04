package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Day08 {
    /**
     *给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int best = 10000;
        for(int a = 0; a<len; a++){
            if(a > 0 && nums[a] == nums[a-1]){
                continue;
            }
            int c = len-1, b = a+1;
            while(b<c){
                int sum = nums[a] + nums[b] + nums[c];
                if(sum == target){
                    return target;
                }
                if(Math.abs(sum - target) < Math.abs(best - target)){
                    best = sum;
                }
                if(sum < target){
                    int b1 = b+1;
                    while(b1<c && nums[b] == nums[b1]){
                        b1++;
                    }
                    b = b1;
                }else {
                    int c1 = c-1;
                    while(b<c1 && nums[c] == nums[c1]){
                        c1--;
                    }
                    c = c1;
                }
            }
        }
        return best;
    }

    /**
     *将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     */
    public String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, len); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goDown = !goDown;
            curRow += goDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null){
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for(int i = 0; i<n+1; i++){
            first = first.next;
        }
        while(first != null){
            second = second.next;
            first = first.next;
        }
        second.next = second.next.next;
        return head;
    }



    public static void main(String[] args) {
//        Day08 day08 = new Day08();
//        int[] arr = {-1,2,1,-4};
//        System.out.println(day08.threeSumClosest(arr,1));
    }
}
