package kr.hhplus.be.server.infrastructure.external

import kr.hhplus.be.server.domain.external.ExternalNotify
import kr.hhplus.be.server.domain.external.ExternalNotifyCommand
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ExternalNotifyImpl : ExternalNotify {
    private val log = LoggerFactory.getLogger(javaClass)
    override fun notify(cmd: ExternalNotifyCommand.Notify) {
        log.info("🚀 notify: $cmd")
    }
}