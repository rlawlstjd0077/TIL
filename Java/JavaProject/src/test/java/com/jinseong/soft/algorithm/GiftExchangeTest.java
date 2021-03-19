package com.jinseong.soft.algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.junit.jupiter.api.Test;

class GiftExchangeTest {
    private static final int MINIMUM_SEASON_COUPON_COUNT = 5;
    private static final int MINIMUM_GENERAL_COUPON_COUNT = 7;
    private static final int MINIMUM_SUN_COUPON_COUNT = 12;

    @Test
    void main() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String stringK = br.readLine();
        int K = Integer.parseInt(stringK);
        for (int i = 0; i < K; i++) {
            String couponString = br.readLine();
            long N = Long.parseLong(couponString.split(" ")[0]);
            long M = Long.parseLong(couponString.split(" ")[1]);

            System.out.println(calculateNumberOfCoupons(N, M));
        }
    }

    static long calculateNumberOfCoupons(long N, long M) {
        long count = 0;
        while(isValidCondition(N, M)) {
            long minSeasonCoupon = (N -= MINIMUM_SEASON_COUPON_COUNT) < 0 ?
                    N + MINIMUM_SEASON_COUPON_COUNT : MINIMUM_SEASON_COUPON_COUNT;
            long minCoupon = (M -= MINIMUM_GENERAL_COUPON_COUNT) < 0 ?
                    M + MINIMUM_GENERAL_COUPON_COUNT : MINIMUM_GENERAL_COUPON_COUNT;
            long sumCoupons = minSeasonCoupon + minCoupon;

            if (sumCoupons < MINIMUM_SUN_COUPON_COUNT) {
                long substraction = MINIMUM_SUN_COUPON_COUNT - sumCoupons;

                if (N < substraction) {
                    break;
                }

                N -= substraction;
            }
            count++;
        }
        return count;
    }

    static boolean isValidCondition(long n, long m) {
        boolean isValidSeasonCouponCount = n >= MINIMUM_SEASON_COUPON_COUNT;
        boolean isEnoughCoupons = n + m >= MINIMUM_SUN_COUPON_COUNT;
        return isValidSeasonCouponCount && isEnoughCoupons;
    }
}
