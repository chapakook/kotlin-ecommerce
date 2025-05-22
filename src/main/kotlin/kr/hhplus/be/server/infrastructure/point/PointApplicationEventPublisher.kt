package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.PointEvent
import kr.hhplus.be.server.domain.point.PointEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class PointApplicationEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : PointEventPublisher {
    override fun publish(event: PointEvent.PointUsed) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: PointEvent.PointUseFailed) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: PointEvent.PointRestored) {
        applicationEventPublisher.publishEvent(event)
    }
}