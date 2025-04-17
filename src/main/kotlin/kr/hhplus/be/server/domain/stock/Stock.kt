package kr.hhplus.be.server.domain.stock

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.hhplus.be.server.support.ErrorCode.OUT_OF_STOCK
import kr.hhplus.be.server.support.ErrorCode.QUANTITY_MUST_BE_POSITIVE

@Entity
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