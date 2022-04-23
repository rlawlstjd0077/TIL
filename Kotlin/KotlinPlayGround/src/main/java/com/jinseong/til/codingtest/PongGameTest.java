package com.kata.spring.datasource;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ☆☆퐁 게임이 출시되었습니다. 이 게임은 같은 모양의 사탕을 깨트리는 게임입니다. 사탕은 4가지 모양이 있으며, 각각 1,2,3,4로 표시됩니다. 각각의 사탕은 가로 또는 세로로 인접한 사탕과 위치를 바꿀 수 있습니다. 위치를 바꿨을 때, 가로 또는 세로로 같은 모양의 사탕이 3개 이상 연결되어 있으면 해당 사탕이 모두 깨집니다.
 * 사탕을 깨트리는 방식은 다음과 같습니다.
 * <p>
 * 임의의 사탕을 선택하여, 가로 혹은 세로 방향으로 인접한 사탕과 위치를 바꾸어 봅니다. 단, 인접한 사탕과 위치를 바꿀 때는 게임 화면을 넘어갈 수 없습니다.
 * 위치를 바꾼 두 개의 사탕이 각각 가로 혹은 세로 방향으로 같은 모양의 사탕 3개 이상과 연결되면 깨집니다.
 * <p>
 * 플레이어는 게임을 하다가 서로 바꿔야 하는 위치를 모를 때, 힌트 보기를 사용할 수 있습니다. 힌트 보기를 누르면 서로 바꿔야 하는 지점을 알려줍니다. 이때 우리는 서로 바꿨을 때, 사탕을 깨트릴 수 있는 경우의 수를 구하려고 합니다. 위의 예시의 경우, 힌트를 줄 수 있는 경우의 수는 4 입니다. 단, 경우의 수를 고려할 때는 두 사탕 (a, b)를 바꿀 때 a를 b와 바꾸는 경우와 b를 a와 바꾸는 경우는 같은 경우로 계산합니다.
 *
 * 플레이어는 게임을 하다가 서로 바꿔야 하는 위치를 모를 때, 힌트 보기를 사용할 수 있습니다. 힌트 보기를 누르면 서로 바꿔야 하는 지점을 알려줍니다. 이때 우리는 서로 바꿨을 때, 사탕을 깨트릴 수 있는 경우의 수를 구하려고 합니다. 위의 예시의 경우, 힌트를 줄 수 있는 경우의 수는 4 입니다. 단, 경우의 수를 고려할 때는 두 사탕 (a, b)를 바꿀 때 a를 b와 바꾸는 경우와 b를 a와 바꾸는 경우는 같은 경우로 계산합니다.
 *
 * 게임 화면 board가 매개변수로 주어졌을 때, 힌트를 줄 수 있는 경우의 수를 return 하도록 solution 함수를 완성해 주세요. 위의 예시에서는 4를 return 하면 됩니다. 단, 사탕을 서로 바꿀 수 있는 모든 경우를 고려했을 때, 어느 경우에서도 가로 또는 세로로 3개 이상 연결되어 있지 않아 힌트를 줄 수 없을 때는 -1을 return 해주세요.
 *
 * 제한사항
 * 게임 화면 board는 2차원 배열로 주어지며, 1,2,3,4 로만 이루어져 있습니다.
 * 게임 화면 board의 가로, 세로 길이 : 3 이상 10 이하의 자연수이며, 가로와 세로의 길이는 같습니다.
 * 처음에 주어진 게임 화면에서는 가로 또는 세로로 같은 모양의 사탕이 3개 이상 연결되어 있는 경우는 없습니다.
 *
 * 입출력 예
 * board	answer
 * [[1,1,4,3],[3,2,1,4],[3,1,4,2],[2,1,3,3]]	4
 * [[1,2,1,2],[3,4,3,4],[1,2,1,2],[3,4,3,4]]	-1
 *
 */
public class PongGameTest {
    @Test
    public void test1() {
        Board board = Board.from(new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        });

        final boolean check = Checker.check(board.candies, 3);
        System.out.println(check);
    }

    @Test
    public void test() {
        Solution solution = new Solution();

        int result = solution.solution(new int[][]{
                {1, 1, 4, 3},
                {3, 2, 1, 4},
                {3, 1, 4, 2},
                {2, 1, 3, 3},
        });

        Assertions.assertThat(result).isEqualTo(4);

        result = solution.solution(new int[][]{
                {1, 2, 1, 2},
                {3, 4, 3, 4},
                {1, 2, 1, 2},
                {3, 4, 3, 4},
        });

        Assertions.assertThat(result).isEqualTo(4);
    }
}


class Solution {
    public int solution(int[][] boardArr) {
        Board board = Board.from(boardArr);
        return (int) BoardManager.countHintMatchCase(board);
    }
}

class Checker {
    private static final int MATCHABLE_COUNT = 3;

    public static boolean check(List<Candy> candies, int length) {
        for (int i = 0; i < length; i++) {
            int index = i;
            List<Candy> xAsixCandies = candies.stream()
                    .filter(e -> e.x == index)
                    .sorted(Comparator.comparing(Candy::getY))
                    .collect(Collectors.toList());

            List<Candy> yAsixCandies = candies.stream()
                    .filter(e -> e.y == index)
                    .sorted(Comparator.comparing(Candy::getX))
                    .collect(Collectors.toList());

            int xAxisMaxCount = caldulateMaxCount(xAsixCandies);
            int yAxisMaxCount = caldulateMaxCount(yAsixCandies);
            if (xAxisMaxCount >= MATCHABLE_COUNT) {
                return true;
            }

            if (yAxisMaxCount >= MATCHABLE_COUNT) {
                return true;
            }
        }

        return false;
    }

    private static int caldulateMaxCount(List<Candy> candies) {
        Set<Candy> set = new HashSet<>();
        int maxCount = 0;

        /**
         * current index, next index 구조로 탐색하므로 마지막 원소는 건너뜀
         */
        for (int i = 0; i < candies.size() - 1; i++) {
            Candy currentCandy = candies.get(i);
            Candy nextCandy = candies.get(i + 1);

            if (currentCandy.shape == nextCandy.shape) {
                set.add(currentCandy);
                set.add(nextCandy);
            } else {
                maxCount = Math.max(maxCount, set.size());
                set.clear();
            }
        }
        maxCount = Math.max(maxCount, set.size());

        return maxCount;
    }
}

class BoardManager {
    public static long countHintMatchCase(Board board) {
        long matchCaseCount = board.candies
                .stream()
                .mapToLong(candy -> countMatchCase(board, candy))
                .sum();

        return matchCaseCount == 0 ? -1 : matchCaseCount;
    }

    private static long countMatchCase(Board board, Candy candy) {
        for (MoveAction action : MoveAction.values()) {
            boolean result = move(action, board, candy);
            if (result == true) {
                return 1;
            }
        }
        return 0;
    }

    private static boolean move(MoveAction action, Board board, Candy candy) {
        Candy swapTarget = null;
        switch (action) {
            case UP -> swapTarget = board.findSwapTargetCandy(candy, candy.x, candy.y + 1);
            case DOWN -> swapTarget = board.findSwapTargetCandy(candy, candy.x, candy.y - 1);
            case LEFT -> swapTarget = board.findSwapTargetCandy(candy, candy.x - 1, candy.y);
            case RIGHT -> swapTarget = board.findSwapTargetCandy(candy, candy.x + 1, candy.y);
        }

        /**
         * 이동이 불가능한 경우 false 를 리턴함
         */
        if (swapTarget == null) {
            return false;
        }


        List<Candy> swapCandies = copyCandies(board.candies);
        swapCandy(candy, swapTarget, swapCandies);
        boolean checkResult = Checker.check(swapCandies, board.length);

        if (checkResult) {
            BoardManager.printCandies(swapCandies, board.length);
        }

        return checkResult;
    }

    private static void swapCandy(Candy first, Candy second, List<Candy> candies) {
        int tmpX = first.x;
        int tmpY = first.y;

        Candy candy1 = candies.stream().filter(e -> e.x == first.x && e.y == first.y).findFirst().get();
        Candy candy2 = candies.stream().filter(e -> e.x == second.x && e.y == second.y).findFirst().get();
        candy1.x = second.x;
        candy1.y = second.y;
        candy2.x = tmpX;
        candy2.y = tmpY;
    }

    private static List<Candy> copyCandies(List<Candy> candies) {
        return candies.stream()
                .map(e -> new Candy(e.shape, e.x, e.y))
                .collect(Collectors.toList());
    }

    private static void printCandies(List<Candy> candies, int length) {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                int finalY = y;
                int finalX = x;
                System.out.print(candies.stream().filter(e -> e.x == finalX && e.y == finalY).findFirst().get().shape + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

enum MoveAction {
    UP, LEFT, RIGHT, DOWN
}

class Board {
    private final List<Integer> availableRangeArr;
    final int length;
    final List<Candy> candies;

    public static Board from(int[][] board) {
        List<Candy> candies = new ArrayList<>();

        /**
         * 좌측 하단을 시작 좌표 (0,0) 으로 하기 위해서 주어진 배열의 X축을 반전시킴
         */
        for (int i = board.length - 1; i >= 0; i--) {
            int[] row = board[i];
            for (int j = 0; j < row.length; j++) {
                candies.add(new Candy(row[j], j, i));
            }
        }

        return new Board(board.length, candies);
    }

    private Board(int length, List<Candy> candies) {
        this.length = length;
        this.candies = candies;
        this.availableRangeArr = IntStream.range(0, length)
                .boxed()
                .collect(Collectors.toList());
    }

    public Candy findSwapTargetCandy(Candy candy, int x, int y) {
        if (!availableRangeArr.contains(x) || !availableRangeArr.contains(y)) {
            return null;
        }

        return candies.stream().filter(e -> e.x == x && e.y == y)
                .findFirst()
                .orElseThrow();
    }
}

class Candy {
    int shape;
    int x;
    int y;

    public Candy(int shape, int x, int y) {
        this.shape = shape;
        this.x = x;
        this.y = y;
    }

    public int getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return shape == candy.shape && x == candy.x && y == candy.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, x, y);
    }
}
