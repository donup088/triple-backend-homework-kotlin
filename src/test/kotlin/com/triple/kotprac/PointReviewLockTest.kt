package com.triple.kotprac

import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.PointHistoryType
import com.triple.kotprac.point.dto.PointRequest
import com.triple.kotprac.point.service.PointService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@SpringBootTest
class PointReviewLockTest(
    @Autowired private val pointService: PointService
) {

    @Test
    @Rollback(value = false)
    fun test() {
        val numberOfThreads = 3
        val latch = CountDownLatch(numberOfThreads)
        val executorService = Executors.newFixedThreadPool(numberOfThreads)

        for (i in 0 until numberOfThreads) {
            executorService.execute {
                val request =
                    PointRequest.Update(
                        PointHistoryType.REVIEW,
                        PointHistoryAction.ADD,
                        i.toLong() + 1,
                        i.toLong() + 1,
                        1,
                        listOf(),
                        "good"
                    )
                pointService.update(request)
                latch.countDown()
            }
        }

        Thread.sleep(500)
    }
}