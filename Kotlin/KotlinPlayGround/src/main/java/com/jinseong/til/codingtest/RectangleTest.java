package com.jinseong.til.codingtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * n x m 크기의 직사각형을 자르려고 합니다. 직사각형의 각 변은 x축, y축에 평행하며, 직사각형의 왼쪽 아래 꼭짓점의 좌표를 (0, 0), 오른쪽 위 꼭짓점의 좌표를 (n, m)으로 하겠습니다. 다음은 n = 3, m = 4인 직사각형이 놓여있는 모습을 나타낸 그림입니다.
 * 이 직사각형을 x축, 혹은 y축에 평행한 선분을 이용해 여러 조각으로 잘라내려고 합니다. 직사각형을 자르는 선분은 x축 좌표와 y축 좌표를 이용해 표현합니다. 예를 들어 x축 좌표가 2라면 x = 2인 지점에서 y축에 평행한 선분을 이용해 직사각형을 잘라냅니다. 이때, 직사각형을 자를 때는 선분 위에 있는 직사각형이 모두 잘리도록 해야 합니다. 다음은 잘라내는 선분의 x축 좌표가 [2], y축 좌표가 [1, 2]인 경우를 나타냅니다.
 * 위 그림과 같이 선분이 지나가는 모든 지점을 잘라낸 후 만들어지는 작은 직사각형 중 넓이가 가장 큰 직사각형은 색칠한 부분이며 넓이는 4가 됩니다.
 * 직사각형의 오른쪽 위 꼭짓점을 나타내는 좌표 n과 m, 선분의 x축 좌표가 들어있는 배열 x_axis, y축 좌표가 들어있는 배열 y_axis가 매개변수로 주어질 때, 직사각형을 모두 잘라낸 후 만들어지는 작은 직사각형 중 넓이가 가장 큰 직사각형을 찾아 그 넓이를 return 하도록 solution 함수를 완성해주세요.
 * 제한사항
 * n, m은 오른쪽 위 꼭짓점의 좌표를 나타냅니다.
 * n, m은 2 이상 10,000 이하의 자연수입니다.
 * x_axis는 y축에 평행한 선분의 x축 좌표가 들어있는 배열이며, 길이는 100 이하입니다.
 * y_axis는 x축에 평행한 선분의 y축 좌표가 들어있는 배열이며, 길이는 100 이하입니다.
 * x_axis의 원소는 1 이상 n 미만의 자연수가 오름차순으로 들어있으며, 중복된 값이 들어있지 않습니다.
 * y_axis의 원소는 1 이상 m 미만의 자연수가 오름차순으로 들어있으며, 중복된 값이 들어있지 않습니다.
 *
 * n	m	x_axis	y_axis	result
 * 4	4	[1]	[3]	9
 * 3	4	[2]	[1,2]	4
 */

class RectangleExtractTest {
    @Test
    public void test() {
        List<Rectangle> rectangles = RectangleExtractor.extract(4, 4, new int[]{1}, new int[]{3});
        Assertions.assertThat(rectangles).hasSize(4);

        rectangles = RectangleExtractor.extract(4, 4, new int[0], new int[0]);
        Assertions.assertThat(rectangles).hasSize(1);
    }
}

public class RectangleTest {
    @Test
    public void test() {
        Solution solution = new Solution();

        int result = solution.solution(4, 4, new int[]{1}, new int[]{3});
        Assertions.assertThat(result).isEqualTo(9);

        int result2 = solution.solution(3, 4, new int[]{2}, new int[]{1, 2});
        Assertions.assertThat(result2).isEqualTo(4);

        int result3 = solution.solution(3, 4, new int[0], new int[]{1, 2});
        Assertions.assertThat(result3).isEqualTo(6);
    }

    @Test
    public void testEmptyAxis() {
        Solution solution = new Solution();
        int result = solution.solution(3, 4, new int[0], new int[0]);
        Assertions.assertThat(result).isEqualTo(12);
    }
}

class Solution {
    public int solution(int n, int m, int[] x_axis, int[] y_axis) {
        List<Rectangle> rectangles = RectangleExtractor.extract(n, m, x_axis, y_axis);

        return rectangles
                .stream()
                .mapToInt(Rectangle::getArea)
                .max()
                .orElseThrow();
    }
}

class RectangleExtractor {
    public static List<Rectangle> extract(int endX, int endY, int[] x_axis, int[] y_axis) {
        int startX = 0;
        int startY = 0;

        /**
         * star, end 를 포함한 axis list를 사용하도록 함
         */
        List<Integer> allxAxies = new ArrayList<>() {{
            add(startX);
            addAll(Arrays.stream(x_axis).boxed().collect(Collectors.toList()));
            add(endX);
        }};

        List<Integer> allyAxies = new ArrayList<>() {{
            add(startY);
            addAll(Arrays.stream(y_axis).boxed().collect(Collectors.toList()));
            add(endY);
        }};


        /**
         * Prev, Current Index 방식으로 접근하기 때문에 x, y axis 모두 첫 원소는 건너뜀.
         */
        List<Rectangle> rectangles = new ArrayList<>();

        for (int yIndex = 1; yIndex < allyAxies.size(); yIndex++) {
            for (int xIndex = 1; xIndex < allxAxies.size(); xIndex++) {
                int subRectangleStartX = allxAxies.get(xIndex - 1);
                int subRectangleStartY = allyAxies.get(yIndex - 1);
                int subRectangleEndX = allxAxies.get(xIndex);
                int subRectangleEndY = allyAxies.get(yIndex);
                Rectangle rectangle =
                        toRectangle(subRectangleStartX, subRectangleStartY, subRectangleEndX, subRectangleEndY);
                rectangles.add(rectangle);
            }
        }

        return rectangles;
    }

    private static Rectangle toRectangle(int startX, int startY, int endX, int endY) {
        int width = endX - startX;
        int height = endY - startY;
        return new Rectangle(startX, startY, width, height);
    }
}

class Rectangle {
    int x;
    int y;
    int width;
    int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return x == rectangle.x && y == rectangle.y && width == rectangle.width && height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
