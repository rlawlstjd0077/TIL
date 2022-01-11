package com.jinseong.test


object RectangleArranger {
    fun arrange(arr: Array<IntArray>): Array<String> {
        val rectangles = arr.map { Rectangle.from(it) }

        moveToBelow(rectangles)
        moveToLeft(rectangles)

        return rectangles.map { it.toDisplayString() }.toTypedArray()
    }

    private fun moveToBelow(rectangles: List<Rectangle>) {
        rectangles.forEach { targetRectangle ->
            /**
             * target rectangle의 최대치로 아래로 이동가능한 y좌표
             */
            val availableY = rectangles
                .map { it.topSide }
                .filter { topSide -> filterY(targetRectangle.bottomSide, topSide) }
                .maxOfOrNull { it.start.y } ?: 0

            /**
             * 현재 y축 위치를 기준으로 움직일 좌표
             */
            val yAxisLength = targetRectangle.bottomSide.y - availableY

            targetRectangle.leftY -= yAxisLength
            targetRectangle.rightY -= yAxisLength
        }
    }


    private fun moveToLeft(rectangles: List<Rectangle>) {
        rectangles.forEach { targetRectangle ->
            /**
             * target rectangle의 최대치로 아래로 이동가능한 x좌표
             */
            val availableX = rectangles
                .asSequence()
                .map { it.rightSide }
                .filter { rightSide -> filterX(targetRectangle.leftSide, rightSide) }
                .maxOfOrNull { it.x } ?: 0

            /**
             * 현재 x축 위치를 기준으로 움직일 좌표
             */
            val xAxisLength = targetRectangle.leftSide.x - availableX

            targetRectangle.leftX -= xAxisLength
            targetRectangle.rightX -= xAxisLength
        }
    }
}

data class Rectangle(
    var leftX: Int,
    var leftY: Int,
    var rightX: Int,
    var rightY: Int
) {
    val topSide: XAxisSide
        get() = XAxisSide(
            start = Point(leftX, rightY),
            end = Point(rightX, rightY),
        )

    val leftSide: YAxisSide
        get() = YAxisSide(
            start = Point(leftX, rightY),
            end = Point(leftX, leftY),
        )

    val rightSide: YAxisSide
        get() = YAxisSide(
            start = Point(rightX, rightY),
            end = Point(rightX, leftY),
        )

    val bottomSide: XAxisSide
        get() = XAxisSide(
            start = Point(leftX, leftY),
            end = Point(rightX, leftY),
        )

    /**
     * X 축 변의 양 끝점을 표현 (ex. (0,1) ---------- (5,1) )
     */
    data class XAxisSide(
        val start: Point,
        val end: Point
    ) {
        val xAxisRange: IntRange
            get() = start.x..end.x

        val y: Int
            get() = start.y
    }

    /**
     * Y 축 변의 양 끝점을 표현 (ex. (0,0)
     *                            |
     *                            |
     *                            |
     *                          (0,4)
     */
    data class YAxisSide(
        val start: Point,
        val end: Point
    ) {
        val yAxisRange: IntRange
            get() = start.y..end.y

        val x: Int
            get() = start.x
    }

    data class Point(
        val x: Int,
        val y: Int
    )

    companion object {
        fun from(arr: IntArray): Rectangle {
            return Rectangle(
                leftX = arr[0],
                leftY = arr[1],
                rightX = arr[2],
                rightY = arr[3]
            )
        }
    }
}

private fun Rectangle.toDisplayString(): String {
    return "$leftX $leftY $rightX $rightY"
}

private fun filterY(bottomSide: Rectangle.XAxisSide, topSide: Rectangle.XAxisSide): Boolean {
    return (bottomSide.y >= topSide.y) && bottomSide.contains(topSide)
}

private fun filterX(leftSide: Rectangle.YAxisSide, rightSide: Rectangle.YAxisSide): Boolean {
    return (leftSide.x >= rightSide.x) && leftSide.contains(rightSide)
}

private fun Rectangle.XAxisSide.contains(targetXAxisSide: Rectangle.XAxisSide): Boolean {
    val range = this.xAxisRange
    val targetRange = targetXAxisSide.xAxisRange

    return range.contains(targetRange)
}

private fun Rectangle.YAxisSide.contains(targetYAxisSide: Rectangle.YAxisSide): Boolean {
    val range = this.yAxisRange
    val targetRange = targetYAxisSide.yAxisRange

    return range.contains(targetRange)
}

private fun IntRange.contains(targetRange: IntRange): Boolean {
    return ((this.first == targetRange.first) || (this.last == targetRange.last)) ||
            (this.containsByExclusive(targetRange.first) || this.containsByExclusive(targetRange.last))
}

private fun IntRange.containsByExclusive(value: Int): Boolean {
    return first < value && value < last
}
