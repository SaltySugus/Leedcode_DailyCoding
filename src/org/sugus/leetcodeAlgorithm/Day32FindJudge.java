package org.sugus.leetcodeAlgorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day32FindJudge {
    public int findJudge(int n, int[][] trust) {
        // 给定数组trust 该数组由信任对trust[i] = [a, b]，表示编号a的人信任编号为b的人
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
