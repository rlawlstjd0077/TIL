package com.jinseong.til.concurrency

import jdk.jfr.Configuration
import jdk.jfr.Recording
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Paths

object ConcurrencySupport {

    lateinit var recording: Recording
    var start: Long = 0

    const val USERS = 1000
    const val PERSISTENCE_FORK_FACTOR = 30

    const val SERVICE_A_LATENCY: Long = 1000
    const val SERVICE_B_LATENCY: Long = 500
    const val PERSISTENCE_LATENCY: Long = 300

    private val log: Logger = LoggerFactory.getLogger(ConcurrencySupport::class.java)


    fun start() {
        try {
            recording = Recording(Configuration.getConfiguration("profile"))
            val destination = Paths.get("java-concurrency.jfr")
            recording.setDestination(destination)
            recording.start()
        } catch (e: Exception) {
            log.error("Error during starting of recording: {}", e.message)
        }
        log.info("Starting...")
        start = System.currentTimeMillis()
    }

    fun stop(threadCount: Int, expectedTime: Int) {
        val finished: Long = System.currentTimeMillis() - start
        log.info("---------------------------")
        log.info("{} ms - Total Time", finished)
        log.info("{} ms - Expected Time", expectedTime)
        log.info("{} ms - Administrative time", finished - expectedTime)
        log.info("{} - Legacy Thread Count", threadCount)
        recording.stop()
        recording.close()
        log.info("Recording at: {}", recording.getDestination().toAbsolutePath())
    }

    fun stop() {
        stop(fullConcurrencyThreadCount(), fullConcurrencyExpectedTime())
    }

    fun serviceA(i: Int): String? {
        Thread.sleep(SERVICE_A_LATENCY)
        return "A$i"
    }

    fun serviceB(i: Int): String? {
        Thread.sleep(SERVICE_B_LATENCY)
        return "B$i"
    }

    fun service(name: String, latency: Long, iteration: Int): String {
        Thread.sleep(latency)
        return name + iteration
    }

    fun persistence(persistence: Int, serviceA: String?, serviceB: String?): String? {
        Thread.sleep(PERSISTENCE_LATENCY)
        println("[{}] [{}] Persistence-{} - Executed" + serviceA + serviceB + persistence)
        return ""
    }

    fun fullConcurrencyThreadCount(): Int {
        return USERS + USERS * (2 + PERSISTENCE_FORK_FACTOR)
    }

    fun fullConcurrencyExpectedTime(): Int {
        return SERVICE_A_LATENCY.toInt() + PERSISTENCE_LATENCY.toInt()
    }
}
