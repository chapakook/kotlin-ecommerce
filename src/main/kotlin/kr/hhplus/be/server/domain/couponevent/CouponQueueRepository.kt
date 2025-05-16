package kr.hhplus.be.server.domain.couponevent

interface CouponQueueRepository {
    fun enqueue(couponEventId: Long, userId: Long): Boolean
    fun dequeue(couponEventId: Long, count: Int): List<Long>
    fun remove(couponEventId: Long, userId: Long)
}