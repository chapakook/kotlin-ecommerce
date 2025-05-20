package kr.hhplus.be.server.infrastructure.externalnotify

import kr.hhplus.be.server.domain.order.ExternalNotify
import kr.hhplus.be.server.domain.order.ExternalNotifyCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ExternalNotifyImpl : ExternalNotify {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun notify(cmd: ExternalNotifyCommand.Notify) {
        log.info("🚀 notify: $cmd")
    }
}