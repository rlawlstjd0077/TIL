package com.jinseong.soft.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecondProblem {
    static int length;
    static int count = 0;
    static String[] road;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        length = Integer.parseInt(input);
        String line = br.readLine();
        road = line.split("");

        vroom(0);
        System.out.println(count);
    }

    static void vroom(int position) {
        if (position + 1 == length) {
            count++;
            return;
        }

        if (isAvailableWay(position + 1)) {
            vroom(position + 1);
        }

        if (isAvailableWay(position + 2)) {
            vroom(position + 2);
        }
    }

    static boolean isAvailableWay(int nextPosition) {
        return nextPosition < length && road[nextPosition].equals("1");
    }
}
