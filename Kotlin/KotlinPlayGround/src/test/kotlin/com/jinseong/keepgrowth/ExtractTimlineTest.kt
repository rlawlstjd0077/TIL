package com.jinseong.keepgrowth

import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ExtractTimlineTest {

    @Test
    fun test() {
        val paddingSeconds = 4L

        val filePath = "/Users/kimjs/Documents/KeepGrowth/Books/GreatExpectations_part1/greatexpectations_p1_1601_librivox.chapters.txt"
        val contents = File(filePath).useLines {
            it.map(FileContent::from).toList()
        }

        contents.forEach { println(it.toDisplayText(
            paddingSeconds = paddingSeconds,
            contentTemplate = "%s"
        )) }
    }
}

data class FileContent(
    val time: LocalTime,
    val content: String
) {

    companion object {
        fun from(str: String): FileContent {
            val split = str.split(" ")

            return FileContent(
                time = LocalTime.parse(split[0]),
                content = split.subList(1, split.size).joinToString(" ")
            )
        }
    }
}

fun FileContent.toDisplayText(paddingSeconds: Long, contentTemplate: String): String {
    val format = DateTimeFormatter.ofPattern("HH:mm:ss")
    val time = time.plusSeconds(paddingSeconds)
    return "${time.format(format)} ${contentTemplate.format(content)}"
}
