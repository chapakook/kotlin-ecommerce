package kr.hhplus.be.server.domain.point

class PointCommand {
    data class Find(val userId: Long)
    data class Charge(val userId: Long, val amount: Long)
    data class Use(val userId: Long, val amount: Long)
}