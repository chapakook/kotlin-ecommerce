package kr.hhplus.be.server.domain.point

import jakarta.persistence.*
import kr.hhplus.be.server.support.ErrorCode.*
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@Table(name = "points")
class Point(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pointId: Long,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    var balance: Long,
    @Column(nullable = false)
    var updateMillis: Long,
) {
    companion object {
        fun create(pointId: Long, userId: Long, balance: Long): Point {
            require(balance > 0) { CREATE_BASE_POINT.message }
            return Point(pointId, userId, balance, LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
        }
    }

    fun charge(amount: Long) {
        require(amount > 0) { AMOUNT_MUST_BE_POSITIVE.message }
        balance += amount
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    fun use(amount: Long) {
        require(balance >= amount) { OUT_OF_POINT.message }
        balance -= amount
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}