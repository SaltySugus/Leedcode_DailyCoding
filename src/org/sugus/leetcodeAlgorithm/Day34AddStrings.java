package org.sugus.leetcodeAlgorithm;

public class Day34AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int curry = 0;
        for (int i = num1.length() - 1 , j = num2.length() - 1 ; j >= 0 || i >= 0 || curry != 0; i--, j--) {
            int addend1 = i < 0 ? 0 : Character.getNumericValue(num1.charAt(i));
            int addend2 = j < 0 ? 0 : Character.getNumericValue(num2.charAt(j));
            int temp = (addend1 + addend2 + curry) % 10;
            result.append(temp);
            curry = (addend1 + addend2 + curry) / 10;
        }
        return result.reverse().toString();
    }
}
