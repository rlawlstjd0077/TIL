package com.jinseong.soft.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SixthProblem {

    static int width = 3;
    static int height = 5;

    static int maps[][] = new int[][]{
            {3, 4, 5},
            {2, 3, 9},
            {3, 9, 3},
            {4, 5, 1},
            {1, 3, 6}
    };
    static List<Integer> caseList = new ArrayList<Integer>();
    /*public static void main(String[] args) {
        runSomthing(0, 0, 0);
        System.out.println(caseList.stream().max(Comparator.comparing(Integer::intValue)).orElse(0));
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        width = Integer.parseInt(input.split(" ")[0]);
        height = Integer.parseInt(input.split(" ")[1]);

        maps = new int[height][width];

        for (int i = 0; i < height; i++) {
            String line = br.readLine();
            String[] split = line.split(" ");
            for (int j = 0; j < width; j++) {
                maps[i][j] = Integer.parseInt(split[j]);
            }
        }
        shopPassionately(0, 0, 0);
        System.out.println(caseList.stream().max(Comparator.comparing(Integer::intValue)).orElse(0));
    }

    static void shopPassionately(int x, int y, int sum) {
        sum += maps[y][x];

        if (x + 1 < width) {
            shopPassionately(x + 1, y, sum);
        }
        if (y + 1 < height) {
            shopPassionately(x, y + 1, sum);
        }

        caseList.add(sum);
    }
}
