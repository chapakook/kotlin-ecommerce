package kr.hhplus.be.server.domain.order

interface ExternalNotify {
    fun notify(cmd: ExternalNotifyCommand.Notify)
}