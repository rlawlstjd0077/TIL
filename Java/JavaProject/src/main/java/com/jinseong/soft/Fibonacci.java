package com.jinseong.soft;

import java.math.BigInteger;

public class Fibonacci {
    static int[] memo;
    public static void main(String[] args) {
        System.out.println(solution(100000));
    }

    public static int afsa() {
        int n = 5;
        memo = new int[n + 1];
        return fibonacci(n);
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return n;
        } else if (memo[n] != 0) {
            return memo[n];
        } else {
            return memo[n] = fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static int solution(int n) {
        BigInteger num1 = new BigInteger("0");
        BigInteger num2 = new BigInteger("1");
        BigInteger sum = new BigInteger("0");

        for (int i = 0; i < n - 1; i++) {
            sum = num1.add(num2);
            num1 = num2;
            num2 = sum;
        }

        int answer = sum.mod(new BigInteger("1234567")).intValue();
        return answer;
    }
}
