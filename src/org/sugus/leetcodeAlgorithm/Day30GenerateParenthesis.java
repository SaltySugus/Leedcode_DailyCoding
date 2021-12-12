package org.sugus.leetcodeAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Day30GenerateParenthesis {

    private List<String> parenthesis = new ArrayList<>(0);

    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return parenthesis;
    }

    private void dfs(int left, int right, String parent) {
        if (left == 0 && right == 0) {
            parenthesis.add(parent);
            return;
        }

        if (left > 0){
            dfs(left - 1, right, parent + "(");
        }

        if (right > left) {
            dfs(left, right - 1, parent + ")");
        }
    }

    public static void main(String[] args) {
        Day30GenerateParenthesis day30GenerateParenthesis = new Day30GenerateParenthesis();
        List<String> result = day30GenerateParenthesis.generateParenthesis(3);
        System.out.println(result);
    }
}
