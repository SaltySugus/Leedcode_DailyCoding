package org.sugus.leetcodeAlgorithm;

public class Day31Dividend {
    public int divide(int dividend, int divisor) {
        // 给定两个整数，被除数和除数，将两数相除，要求不使用乘法、除法和mod运算
        boolean sign = true;
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        if ((dividend > 0 && divisor > 0) || (dividend <0 && divisor < 0)) {
            sign = false;
        }

        // 防止累加过程中出现溢出现象
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;

        int ans = 0;

        // 当除数大于被除数时结束运算
        while (dividend <= divisor) {
            int tmp = divisor;
            int count =  1;
            // 对除数进行累加操作
            while (tmp >= dividend - tmp) {
                tmp += tmp;
                count += count;
            }
            dividend -= tmp;
            ans += count;
        }
        return sign ? -ans : ans;
    }
}
