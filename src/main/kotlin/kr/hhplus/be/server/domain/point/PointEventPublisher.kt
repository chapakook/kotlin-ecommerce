package kr.hhplus.be.server.domain.point

interface PointEventPublisher {
    fun publish(event: PointEvent.PointUsed)
    fun publish(event: PointEvent.PointRestored)
    fun publish(event: PointEvent.PointUseFailed)
}