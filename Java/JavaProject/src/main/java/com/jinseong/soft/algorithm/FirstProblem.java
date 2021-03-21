package com.jinseong.soft.algorithm;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FirstProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        List<String> inputList = List.of("12:00 ~ 23:59", "11:00 ~ 18:00", "14:00 ~ 20:00");
        //String input = "3";
        int numberOfPeople = Integer.parseInt(input);

        List<ScheduleTime> timeList = new ArrayList<>();

        for (int i = 0; i < numberOfPeople; i++) {
            String time =br.readLine();
            //String time = inputList.get(i);
            timeList.add(
                    new ScheduleTime(LocalTime.parse(time.split(" ~ ")[0]), LocalTime.parse(time.split(" ~ ")[1])));
        }

        ScheduleTime overlappingTime = new ScheduleTime(LocalTime.MIN, LocalTime.MAX);

        timeList.stream()
                .sorted(Comparator.comparing(ScheduleTime::getStart))
                .forEach(e -> {
                    LocalTime overlappingStartTime =
                            e.getStart().isAfter(overlappingTime.getStart()) ? e.getStart() : overlappingTime.getStart();
                    LocalTime overlappingEndTime =
                            e.getEnd().isBefore(overlappingTime.getEnd()) ? e.getEnd() : overlappingTime.getEnd();

                    overlappingTime.setStart(overlappingStartTime);
                    overlappingTime.setEnd(overlappingEndTime);
                });

        System.out.println(overlappingTime);
    }
}

class ScheduleTime {
    LocalTime start;
    LocalTime end;

    public ScheduleTime(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return start + " ~ " + end;
    }
}