package org.sugus.leetcodeAlgorithm;

public class Day33Multiply {

    private static final String ZERO = "0";

    public String multiply(String num1, String num2) {
        if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return ZERO;
        }
        String result = "0";
        for (int idx = num2.length() - 1; idx >= 0; idx--) {
            int curry = 0;
            StringBuilder temp = new StringBuilder();
            temp.append("0".repeat(Math.max(0, num2.length() - 1 - idx)));
            int m1 = Character.getNumericValue(num2.charAt(idx));
            for (int idy = num1.length() - 1; idy >= 0 || curry != 0; idy--) {
                int m2 = idy < 0 ? 0 : Character.getNumericValue(num1.charAt(idy));
                int tempResult = (m2 * m1 + curry) % 10;
                temp.append(tempResult);
                curry = (m2 * m1 + curry) / 10;
            }
            result = calculate(result, temp.reverse().toString());
        }
        return result;
    }

    private String calculate(String str1, String str2) {
        StringBuilder result = new StringBuilder();
        int curry = 0;
        for (int i = str1.length() - 1 , j = str2.length() - 1 ; j >= 0 || i >= 0 || curry != 0; i--, j--) {
            int addend1 = i < 0 ? 0 : Character.getNumericValue(str1.charAt(i));
            int addend2 = j < 0 ? 0 : Character.getNumericValue(str2.charAt(j));
            int temp = (addend1 + addend2 + curry) % 10;
            result.append(temp);
            curry = (addend1 + addend2 + curry) / 10;
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        String num1 = "123456789";
        String num2 = "987654321";

        Day33Multiply day33Multiply = new Day33Multiply();
        String multiply = day33Multiply.multiply(num1, num2);
        System.out.println(multiply);
    }
}


