package kr.hhplus.be.server.domain.stock

import jakarta.persistence.*
import kr.hhplus.be.server.support.ErrorCode.OUT_OF_STOCK
import kr.hhplus.be.server.support.ErrorCode.QUANTITY_MUST_BE_POSITIVE

@Entity
@Table(name = "stocks")
class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val stockId: Long,
    val productId: Long,
    var quantity: Int,
    val version: Int,
) {
    fun deduct(quantity: Int) {
        require(quantity > 0) { QUANTITY_MUST_BE_POSITIVE.message }
        require(this.quantity >= quantity) { OUT_OF_STOCK.message }
        this.quantity -= quantity
    }
}