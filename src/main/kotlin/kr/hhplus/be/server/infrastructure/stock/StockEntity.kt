package kr.hhplus.be.server.infrastructure.stock

import jakarta.persistence.*
import kr.hhplus.be.server.domain.stock.Stock

@Entity
@Table(name = "stocks")
class StockEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val stockId: Long,
    @Column(nullable = false)
    val productId: Long,
    @Column(nullable = false)
    var quantity: Int,
    @Column(nullable = false)
    val version: Int,
) {
    companion object {
        fun of(stock: Stock): StockEntity = with(stock) { StockEntity(stockId, productId, quantity, version) }
    }

    fun to(): Stock = Stock(stockId, productId, quantity, version)
}