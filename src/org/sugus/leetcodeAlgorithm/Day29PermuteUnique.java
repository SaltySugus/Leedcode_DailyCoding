package org.sugus.leetcodeAlgorithm;

import java.util.*;

public class Day29PermuteUnique {

    private List<List<Integer>> answer = new ArrayList<>(0);

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> arr = new ArrayList<>(0);
        Arrays.stream(nums).sorted().forEach(arr::add);
        backTrack(arr, 0, arr.size() - 1);
        return answer;
    }

    private void backTrack(List<Integer> arr, int left, int right) {
        List<Integer> temp = new ArrayList<>(arr.size());
        temp.addAll(arr);
        if (left == right) {
            this.answer.add(temp);
            return;
        }
        for (int i = left; i <= right; i++) {
            if (i != left && temp.get(left).equals(temp.get(i))) {
                continue;
            }
            Collections.swap(temp, left, i);
            backTrack(temp, left + 1, right);
        }
    }
}
