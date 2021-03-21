package com.jinseong.soft.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ThirdProblem {
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int n = Integer.parseInt(input);
        map = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            String[] split = line.split("");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = split[j].equals("1");
            }
        }

        int[] resultArr = new int[n];
        for (int i = 0; i < n; i ++) {
            //TODO 개선의 여지 있음
            //if () { }
            int result = calculateNumberOfCase(i + 1, n);
            if (result == 0) {
                break;
            }
            resultArr[i] = result;
        }

        System.out.println("total: " + Arrays.stream(resultArr).sum());

        for (int i = 0; i < resultArr.length; i++) {
            if (resultArr[i] > 0) {
                System.out.printf("size[%d]: %d%n", i + 1, resultArr[i]);
            }
        }
    }

    static int calculateNumberOfCase(int squareLength, int mapLength) {
        int count = 0;
        for (int j = squareLength; j <= mapLength; j++) {
            for (int k = squareLength; k <= mapLength; k++) {
                if (isFitSpace(k - squareLength, j - squareLength, k, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    static boolean isFitSpace(int startX, int startY, int endx, int endy) {
        for (int y = startY; y < endy; y++) {
            for (int x = startX; x < endx; x++) {
                if (!map[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }
}
