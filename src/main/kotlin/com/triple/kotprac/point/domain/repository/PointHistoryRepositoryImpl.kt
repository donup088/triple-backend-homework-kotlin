package com.triple.kotprac.point.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.triple.kotprac.point.domain.PointHistory
import com.triple.kotprac.point.domain.PointHistoryAction
import com.triple.kotprac.point.domain.QPointHistory
import com.triple.kotprac.point.domain.QPointHistory.pointHistory
import org.springframework.data.domain.Pageable

class PointHistoryRepositoryImpl(
        private val queryFactory: JPAQueryFactory
) : PointHistoryRepositoryCustom {
    override fun findByPlaceIdAndUserIdOrderByCreatedAtDesc(placeId: Long, userId: Long, pageable: Pageable): PointHistory? {
        val prePointHistoryId = queryFactory.select(pointHistory.id)
                .from(pointHistory)
                .where(pointHistory.placeId.eq(placeId)
                        .and(pointHistory.userId.eq(userId)))
                .orderBy(pointHistory.id.desc())
                .fetchFirst()

        return queryFactory.selectFrom(pointHistory)
                .where(pointHistory.id.eq(prePointHistoryId))
                .fetchOne()
    }

    override fun getPointSumByUserIdAndPlaceId(userId: Long, placeId: Long): Int {
        val myPointByPlaceId = queryFactory.select(pointHistory.id)
                .from(pointHistory)
                .where(pointHistory.placeId.eq(placeId)
                        .and(pointHistory.userId.eq(userId)))
                .fetch()

        return queryFactory.select(pointHistory.point.sum())
                .from(pointHistory)
                .where(pointHistory.id.`in`(myPointByPlaceId))
                .fetchOne() ?: 0
    }

    override fun isFirstReview(placeId: Long): Boolean {
        val addCount = queryFactory.select(pointHistory.count())
                .from(pointHistory)
                .where(pointHistory.placeId.eq(placeId)
                        .and(pointHistory.action.eq(PointHistoryAction.ADD)))
                .fetchOne()

        val deleteCount = queryFactory.select(pointHistory.count())
                .from(pointHistory)
                .where(pointHistory.placeId.eq(placeId)
                        .and(pointHistory.action.eq(PointHistoryAction.DELETE)))
                .fetchOne()
        return addCount == deleteCount
    }
}
