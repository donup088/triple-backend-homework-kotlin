package com.triple.kotprac

import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.PointHistoryType
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.PointService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@SpringBootTest
@ActiveProfiles("local")
class PointReviewLockTest(
    @Autowired private val pointService: PointService
) {

    @Test
    fun test() {
        val numberOfThreads = 3
        val latch = CountDownLatch(numberOfThreads)
        val executorService = Executors.newFixedThreadPool(numberOfThreads)

        for (i in 0 until numberOfThreads) {
            println(i)
            val request =
                PointRequest.Update(
                    PointHistoryType.REVIEW,
                    PointHistoryAction.ADD,
                    i.toLong() + 1,
                    i.toLong() + 1,
                    120,
                    listOf(),
                    "good"
                )
            executorService.execute {
                pointService.update(request)
                latch.countDown()
            }
        }
        latch.await()
        println("end")
    }

    @Test
    fun test2() {
        val request =
            PointRequest.Update(
                PointHistoryType.REVIEW,
                PointHistoryAction.ADD,
                3,
                2,
                5,
                listOf(),
                "good"
            )
        pointService.update(request)
    }
}