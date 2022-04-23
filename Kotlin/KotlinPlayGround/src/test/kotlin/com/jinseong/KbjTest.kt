package com.jinseong

import java.io.File


/**
 * @author Jay
 */
fun main() {
    val targetDir = File("/Volumes/Eash/BJ-Temp/From-Transend/항목을 포함하는 새로운 폴더")

    targetDir.listFiles()
        .asSequence()
        .filter { !it.isFile }
        .filter { it.name.contains("kbj") }
        .filter {

            it.listFiles().any { it.isTarget() }
        }
        .forEach {
            val files = it.listFiles()
                .filter { it.isTarget() }

            if (files.size == 1) {
                val newFile = File("${files.first().parentFile.path}/${it.name}.${files.first().extension}")
                files.first().renameTo(newFile)
                //println("${files.first().name} -> ${newFile.path}")
            } else if (files.map { it.onlyFileName }.all { it.toIntOrNull() != null }) {
                files.forEach { subFile ->
                    val newFile = File("${subFile.parentFile.path}/${it.name}-${subFile.onlyFileName}.${subFile.extension}")
                    subFile.renameTo(newFile)
                    //println("${subFile.name} -> ${newFile.path}")
                }
                //println("${files.first().name} -> ${it.name}.${files.first().extension}")
            }
        }
}

val File.extension: String
    get() = name.substringAfterLast('.', "")

val File.onlyFileName: String
    get() = name.substringBefore('.', "")

fun File.isTarget() = !name.contains(".txt") && name != ".DS_Store"

fun String.intOrString() = toIntOrNull() ?: this
