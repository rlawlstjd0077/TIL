package com.jinseong.soft.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FourthProblem {
    static Map<String, Double> scoreMap = new HashMap<>();
    static int height;
    static int width;
    static List<ContentsInfo> contents = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initScore();
        Comparator<ContentsInfo> compareByWatchInfo =
                Comparator.comparing(e -> e.getWatchInfo().getPriority());
        Comparator<ContentsInfo> compareByScore =
                Comparator.comparing(ContentsInfo::getScore).reversed();
        Comparator<ContentsInfo> compareByY =
                Comparator.comparing(ContentsInfo::getY);
        Comparator<ContentsInfo> compareByX =
                Comparator.comparing(ContentsInfo::getX);
        Comparator<ContentsInfo> compare =
                compareByWatchInfo
                        .thenComparing(compareByScore)
                        .thenComparing(compareByY)
                        .thenComparing(compareByX);
        contents.stream()
                .sorted(compare)
                .forEach(e -> System.out.println(e.toString()));
    }

    static void initScore() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = "4.0 3.0 2.1 4.3 5.0";
        scoreMap.put("A", Double.parseDouble(input.split(" ")[0]));
        scoreMap.put("B", Double.parseDouble(input.split(" ")[1]));
        scoreMap.put("C", Double.parseDouble(input.split(" ")[2]));
        scoreMap.put("D", Double.parseDouble(input.split(" ")[3]));
        scoreMap.put("E", Double.parseDouble(input.split(" ")[4]));

        input = "2 3";
        height = Integer.parseInt(input.split(" ")[0]);
        width = Integer.parseInt(input.split(" ")[1]);
        List<String> inputList = new ArrayList<>();
        for (int i = 0; i < height * 2; i++) {
            inputList.add(br.readLine());
        }

        for (int i = 0; i < height; i++) {
            String watchLineString = inputList.get(i);
            String scoreLineString = inputList.get(i + height);

            for (int j = 0; j < width; j++) {
                String watchString = watchLineString.split("")[j];
                String scoreString = scoreLineString.split("")[j];

                if (watchString.equals("W")) {
                    continue;
                }

                WatchInfo watchInfo = WatchInfo.valueOf(watchString);
                contents.add(new ContentsInfo(j, i, watchInfo, scoreString, scoreMap.get(scoreString)));
            }
        }
    }
}

class ContentsInfo {
    private final int x;
    private final int y;
    private final WatchInfo watchInfo;
    private String scoreString;
    private double score;

    public ContentsInfo(int x, int y, WatchInfo watchInfo, String scoreString, double score) {
        this.x = x;
        this.y = y;
        this.watchInfo = watchInfo;
        this.scoreString = scoreString;
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public WatchInfo getWatchInfo() {
        return watchInfo;
    }

    public String getScoreString() {
        return scoreString;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("%s %.1f %d %d", scoreString, score, y, x);
    }
}

enum WatchInfo {
    Y(0),
    O(1);

    private int priority;

    WatchInfo(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}