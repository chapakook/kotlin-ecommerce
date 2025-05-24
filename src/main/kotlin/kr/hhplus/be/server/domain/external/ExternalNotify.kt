package kr.hhplus.be.server.domain.external

interface ExternalNotify {
    fun notify(cmd: ExternalNotifyCommand.Notify)
}